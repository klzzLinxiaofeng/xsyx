package platform.szxyzxx.excelhelper.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import platform.szxyzxx.excelhelper.exception.InvokeMethodException;
import platform.szxyzxx.excelhelper.exception.MethodNotFountException;
import platform.szxyzxx.excelhelper.exception.ReflectOptionException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 反射操作工具类
 * @author chenjiaxin
 *
 */
public abstract class ReflectUtil {
	
	/**
	 * 根据对象和属性名，调用get方法
	 * @param obj
	 * @param propertyName
	 * @return
	 */
	public static Object invokeGetter(Object obj,String propertyName) {
		String getterLastName=StringUtil.InitialCapitalization(propertyName);
		String getterName="get"+getterLastName;
		try {
			return invokeNoOverloadMethod(obj,getterName);
		} catch (NoSuchMethodException e) {
			String errorMsg=e.getMessage();
			//boolean类型属性的getter可能是以is开头的
			if(fieldTypeisBoolean(obj,propertyName)) {
				try {
					return invokeNoOverloadMethod(obj,"is"+getterLastName);
				} catch (NoSuchMethodException e1) {
					errorMsg=obj+"的"+propertyName+"属性getXXX或者isXXX的getter方法";
				}
			}
			throw new MethodNotFountException(errorMsg);
		}
	}
	
	private static boolean fieldTypeisBoolean(Object obj,String fieldName) {
		try {
			Class<?> fieldType=obj.getClass().getDeclaredField(fieldName).getType();
			if(fieldType==boolean.class || fieldType==Boolean.class)
				return true;
		} catch (NoSuchFieldException | SecurityException e) {
			throw new RuntimeException(e);
		}
		return false;
	}
	
	/**
	 * 根据对象和属性名，调用set方法
	 * @param obj
	 * @param propertyName
	 * @param setValue set方法的参数
	 */
	public static void invokeSetter(Object obj,String propertyName,Object setValue) {
		String setterLastName=StringUtil.InitialCapitalization(propertyName);
		String setterName="set"+setterLastName;
		try {
			 invokeNoOverloadMethod(obj,setterName,setValue);
		} catch (NoSuchMethodException e) {
			//boolean类型属性的setter可能是没有is的
			if(fieldTypeisBoolean(obj,propertyName)) {
				try {
					 invokeNoOverloadMethod(obj,setterName.replaceAll("Is", ""),setValue);
				} catch (NoSuchMethodException e1) {
					throw new MethodNotFountException(obj+"的"+propertyName+"属性getXXX或者isXXX的setter方法");
				}
			}
			
		}
	}
	
	
	/**
	 * 调用没有重载的方法，调用方法受访问修饰符的控制。如果该方法有重载，则是拿第一个方法，很可能发生异常
	 * @param obj 调用方法的对象，如果是静态方法则传该方法所在类的Class对象
	 * @param methodName 方法名
	 * @param params 调用方法时传的参数
	 * @return
	 * @throws NoSuchMethodException
	 */
	public static Object invokeNoOverloadMethod(Object obj,String methodName,Object... params) throws NoSuchMethodException {
		//支持调用静态方法
		Class<?> cls=obj.getClass();
		if(obj instanceof Class) {
			cls=(Class<?>) obj;
			obj=null;
		}
		Method method=getNoOverloadMethod(cls,methodName);
		try {
			return method.invoke(obj, params);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new InvokeMethodException("调用"+obj+"的"+methodName+"方法失败", e);
		}
	}
	
	/**
	 * 得到方法第一个参数的Class对象
	 * @param cls 方法所在的Class对象
	 * @param methodName 方法名
	 * @return 
	 * @throws NoSuchMethodException 根据名字找不到方法
	 */
	public static Class<?> getMethodFirstParamClass(Class<?> cls,String methodName) throws NoSuchMethodException{
		Method method=getNoOverloadMethod(cls,methodName);
		 Class<?>[] paramClsArr=method.getParameterTypes();
		 if(paramClsArr.length>0)
			 return paramClsArr[0];
		return null;
	}
	
	
	
	/**
	 * 得到方法返回值的Class对象
	 * @param cls
	 * @param methodNaem
	 * @return
	 * @throws NoSuchMethodException
	 */
	public static Class<?> getMethodReturnValueClass(Class<?> cls,String methodNaem) throws NoSuchMethodException{
		Method method=getNoOverloadMethod(cls,methodNaem);
		return method.getReturnType();
	}
	
	/**
	 * 根据方法名获取方法，如果方法名没有重载方法可调用此方法，如果有重载则只是返回第一个匹配名字的方法。如果根据方法名找不到方法则返回null。
	 * 此方法会去父类查找方法
	 * @param cls
	 * @param methodName
	 * @return
	 * @throws NoSuchMethodException 
	 */
	
	public static Method getNoOverloadMethod(Class<?> cls,String methodName) throws NoSuchMethodException {
		List<Method> thisethods=getMethodsByNeme(cls, methodName);
		if(thisethods.size()>0)
			return thisethods.get(0);
		List<Method> parentsMethods=getMethodsByParents(cls, methodName);
		if(parentsMethods.size()>0)
			return parentsMethods.get(0);
		throw new NoSuchMethodException(cls+"没有方法："+methodName);
	}
	
	
	/**
	 * 根据方法名，去所有父类(Object类除外)查找匹配该名字的方法
	 * @param cls
	 * @param methodName
	 * @return
	 */
	public static List<Method> getMethodsByParents(Class<?> cls,String methodName){
		List<Method> thisMatchedMethods=new ArrayList<>();
		Class<?> parentCls=null;
		while((parentCls=cls.getSuperclass())!=null && parentCls!=Object.class) {
			List<Method> parentMatchedMethods=getMethodsByNeme(parentCls,methodName);
			if(parentMatchedMethods.size()>0)
				thisMatchedMethods.addAll(parentMatchedMethods);
		}
		return thisMatchedMethods;
	}
	
	
	/**
	 * 根据方法名，查找cls类中所有名字匹配的方法
	 * @param cls
	 * @param methodName
	 * @return
	 */
	public static List<Method> getMethodsByNeme(Class<?> cls,String methodName) {
		List<Method>  matchedMethods=new ArrayList<>();
		Method[] methods=cls.getDeclaredMethods();
		for (Method method : methods) {
			if(method.getName().equals(methodName))
				matchedMethods.add(method);
		}
		
		return matchedMethods;
	}
	
	/**
	 * 获取Class中有指定注解的字段，如果要获取全部字段，注解Class可以为空。字段顺序按照由父-->子的顺序添加
	 * @param cls
	 * @param annoClass
	 * @return
	 */
	public static <T extends Annotation> List<Field> getFieldsByAnnoClass(Class<?> cls,Class<T> annoClass){
		List<Field> fields=new ArrayList<>();
		List<Class<?>> parents=new ArrayList<>();
		Class<?> parentCls=null;
		while((parentCls=cls.getSuperclass())!=null && parentCls!=Object.class) {
			parents.add(parentCls);
		}
		//从最顶上的父类开始取属性
		for(int i=parents.size()-1;i>-1;i--) {
			if(annoClass!=null)
				addByAnnoClass(fields,parents.get(i).getDeclaredFields(),annoClass);
			else
				fields.addAll(Arrays.asList(parents.get(i).getDeclaredFields()));
		}
		if(annoClass!=null)
			addByAnnoClass(fields, cls.getDeclaredFields(), annoClass);
		else
			fields.addAll(Arrays.asList(cls.getDeclaredFields()));
		return fields;
	}
	
	public static <T> T createObject(Class<T> cls,Object... args) {
		try {
			Constructor<T> noneParamConstructor=cls.getConstructor(getTypesByObjects(args));
			return noneParamConstructor.newInstance(args);
		} catch (Exception e) {
			throw new ReflectOptionException("创建\""+cls+"\"的对象失败,构造方法参数列表"+Arrays.asList(args),e);
		}
	}
	
	
	private static <T extends Annotation> void addByAnnoClass(List<Field> fields,Field[] addFields,Class<T> annoClass) {
		for (Field field : addFields) {
			T anno=field.getAnnotation(annoClass);
			if(anno!=null)
				fields.add(field);
		}
	}
	
	/**
	 * 根据对象数组，获取对象的Class数组,如果对象为null，将会抛出非法参数异常
	 * @param objs
	 * @return
	 */
	private static Class<?>[] getTypesByObjects(Object... objs){
		
		Class<?>[] clsArr=new Class<?>[objs.length];
		for(int i=0;i<objs.length;i++) {
			Object obj=objs[i];
			AssertUtil.assertNull(obj, "获取对象的Class对象时，对象不能为null");
			clsArr[i]=obj.getClass();
		}
		return clsArr;
	}
}
