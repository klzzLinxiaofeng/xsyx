package platform.szxyzxx.excelhelper.convert.dft;

import platform.szxyzxx.excelhelper.convert.IConvertTypeAware;
import platform.szxyzxx.excelhelper.convert.ITypeConverter;
import platform.szxyzxx.excelhelper.exception.TypeConvertException;
import platform.szxyzxx.excelhelper.util.ReflectUtil;
/**
 * 通过调用T2类型的String构造方法进行类型转换,因为使用了泛型，所以要实现IConvertTypeAware
 * @author chenjiaxin
 *
 * @param <T1>
 * @param <T2>
 */
public abstract class AbstractToStringConverter<T1,T2> implements ITypeConverter<T1, T2>,IConvertTypeAware<T1, T2>{

	private Class<T2> convertToClass;
	
	public AbstractToStringConverter() {
		this.convertToClass=getConvertToClass();
	}

	@Override
	public T2 convert(T1 value) {
		try {
			return ReflectUtil.createObject(convertToClass, value.toString());
		} catch (Exception e) {
			throw new TypeConvertException("不能够将"+value+"("+getOriginClass().getName()+"),转换成"+convertToClass.getName()+"类型",e);
		}
	}

}
