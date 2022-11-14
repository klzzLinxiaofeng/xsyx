package platform.szxyzxx.excelhelper.convert;
/**
 * 进行类型转换时告知T1(将什么类型)和T2（转换成什么类型）具体的Class对象，该接口只有在ITypeConverter的子类也使用泛型是才需要实现</br>
 * TypeConvertManager是通过获取ITypeConverter子类的convert方法上的参数Class（要转换的类型）和返回值Class（转换成什么样的类型），来进行注册的</br>
 * 一旦ITypeConverter子类使用泛型，那么通过反射获取convert方法上参数Class或返回值Class将会是Object（妈的假泛型）
 * @author chenjiaxin
 *
 * @param <T1>
 * @param <T2>
 */
public interface IConvertTypeAware<T1,T2> {
	/**
	 * 得到要转换的类型Class(将什么类型进行转换的Class)
	 * @return
	 */
	public Class<T1> getOriginClass();
	/**
	 * 得到转换成什么类型的Class
	 * @return
	 */
	public Class<T2> getConvertToClass();
}
