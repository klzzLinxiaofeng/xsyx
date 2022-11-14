package platform.szxyzxx.excelhelper.convert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import platform.szxyzxx.excelhelper.convert.dft.DoubleToBigDecimalConverter;
import platform.szxyzxx.excelhelper.convert.dft.DoubleToFloatConverter;
import platform.szxyzxx.excelhelper.convert.dft.DoubleToIntegerConverter;
import platform.szxyzxx.excelhelper.convert.dft.DoubleToShortConverter;
import platform.szxyzxx.excelhelper.convert.dft.StringToBigDecimalConverter;
import platform.szxyzxx.excelhelper.convert.dft.StringToDoubleConverter;
import platform.szxyzxx.excelhelper.convert.dft.StringToFloatConverter;
import platform.szxyzxx.excelhelper.convert.dft.StringToIntegerConverter;
import platform.szxyzxx.excelhelper.convert.dft.StringToShortConverter;
import platform.szxyzxx.excelhelper.pojo.TypeConvertKey;
import platform.szxyzxx.excelhelper.util.AssertUtil;
import platform.szxyzxx.excelhelper.util.BasicDataTypeUtil;
import platform.szxyzxx.excelhelper.util.ReflectUtil;

/**
 * 类型转换器管理,主要是根据ITypeConverter实现来注册、管理转换类型和映射的转换器。
 * 
 * @author chenjiaxin
 *
 */
public class TypeConvertManager {
	/**
	 * 类型key和类型转换器的对应关系map
	 */
	private final Map<TypeConvertKey, ITypeConverter<?, ?>> convertRelation = new HashMap<>();

	public TypeConvertManager() {
		init();
	}

	/**
	 * 注册一个转换器，可以覆盖默认的类型转换器
	 * 
	 * @param converter
	 */
	public void registerConveter(ITypeConverter<?, ?> converter) {
		AssertUtil.assertNull(converter, "类型转换器不能为空");
		List<TypeConvertKey> keys = genKeyByConverter(converter);
		for (TypeConvertKey typeConvertKey : keys) {
			convertRelation.put(typeConvertKey, converter);
		}
	}

	/**
	 * 根据源类型和要转换的类型获取相应的转换器，找不到将返回null
	 * 
	 * @param originClass
	 * @param convertClass
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T1, T2> ITypeConverter<T1, T2> getTypeConvert(Class<T1> originClass, Class<T2> convertClass) {
		return (ITypeConverter<T1, T2>) convertRelation.get(new TypeConvertKey(originClass, convertClass));
	}

	public void remove(TypeConvertKey key) {
		convertRelation.remove(key);
	}

	private List<TypeConvertKey> genKeyByConverter(ITypeConverter<?, ?> converter) {
		List<TypeConvertKey> keys = new ArrayList<>();
		
		Class<?> originClass=null;
		Class<?> convertClass=null;
		
		if(converter instanceof IConvertTypeAware) {
			IConvertTypeAware<?,?> typeAware=(IConvertTypeAware<?,?>)converter;
			originClass=typeAware.getOriginClass();
			convertClass=typeAware.getConvertToClass();
		}else {
			try {
				originClass = ReflectUtil.getMethodFirstParamClass(converter.getClass(), "convert");
				convertClass = ReflectUtil.getMethodReturnValueClass(converter.getClass(), "convert");
			} catch (NoSuchMethodException e) {
				// 不可能没有convert方法，所以就不进行异常处理了
			}
		}
		
		keys.add(new TypeConvertKey(originClass, convertClass));
		// 顺带生成基本数据类型key
		
		Class<?> basicConvertClass = BasicDataTypeUtil.getBasicClass(convertClass);
		if (basicConvertClass != null)
			 keys.add(new TypeConvertKey(originClass, basicConvertClass));
		
		return keys;
	}

	
	/**
	 * 注册一些默认的类型转换器
	 */
	private void init() {
		//支持Double转其他形式的数字
		registerConveter(new DoubleToFloatConverter());
		registerConveter(new DoubleToIntegerConverter());
		registerConveter(new DoubleToShortConverter());
		registerConveter(new DoubleToBigDecimalConverter());
		registerConveter(new DoubleToShortConverter());
		
		//支持String 转换成数字
		registerConveter(new StringToBigDecimalConverter());
		registerConveter(new StringToDoubleConverter());
		registerConveter(new StringToFloatConverter());
		registerConveter(new StringToIntegerConverter());
		registerConveter(new StringToShortConverter());
		
	}
}
