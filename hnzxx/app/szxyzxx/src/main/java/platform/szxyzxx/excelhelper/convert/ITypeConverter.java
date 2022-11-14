package platform.szxyzxx.excelhelper.convert;
/**
 * 类型转换器，将T1类型对象，转换为T2类型对象。对于java中的基本数据类型，请使用包装类的Class。如果子类T1或者T2使用了泛型，则必须实现IConvertTypeAware接口，否则泛型将会当作Object处理
 * @author chenjiaxin
 *
 * @param <T1> 将T1类型
 * @param <T2> 转换成T2类型
 */
public interface ITypeConverter<T1,T2> {
	T2 convert(T1 value);
}
