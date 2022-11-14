package platform.szxyzxx.excelhelper.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BasicDataTypeUtil {
	/**
	 * 基本数据类型Class和包装类型的对应关系Map
	 */
	private static final Map<Class<?>,Class<?>> basicWrapMap=new HashMap<>();
	
	static {
		basicWrapMap.put(byte.class, Byte.class);
		basicWrapMap.put(int.class, Integer.class);
		basicWrapMap.put(short.class, Short.class);
		basicWrapMap.put(float.class, Float.class);
		basicWrapMap.put(double.class, Double.class);
		basicWrapMap.put(long.class, Long.class);
		basicWrapMap.put(char.class, Character.class);
		basicWrapMap.put(boolean.class, Boolean.class);
	}
	
	/**
	 * 比较两个Class对象是否相等，基本数据类型和包装数据类型我们也认为是相等的，如int==Integer
	 * @param c1
	 * @param c2
	 * @return
	 */
	public static boolean equal(Class<?> c1,Class<?> c2) {
		if(c1==c2)
			return true;
		Class<?> compareClass;
		if((compareClass=getWrapClass(c1))!=null && compareClass==c2)
			return true;
		if((compareClass=getWrapClass(c2))!=null && compareClass==c1)
			return true;
		return false;
	}
	/**
	 * 根据基本类型的Class，得到包装类型的Class
	 * @param basicClass
	 * @return
	 */
	public static Class<?> getWrapClass(Class<?> basicClass) {
		return basicWrapMap.get(basicClass);
	}
	/**
	 * 根据包装类型Class，得到基本类型Class
	 * @param warpClass
	 * @return
	 */
	public static Class<?> getBasicClass(Class<?> warpClass){
		Set<Class<?>> keys=basicWrapMap.keySet();
		for (Class<?> key : keys) {
			Class<?> wrap=basicWrapMap.get(key);
			if(wrap==warpClass)
				return key;
		}
		return null;
	}
}
