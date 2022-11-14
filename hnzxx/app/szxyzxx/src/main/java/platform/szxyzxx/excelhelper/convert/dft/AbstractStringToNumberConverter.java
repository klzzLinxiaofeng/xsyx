package platform.szxyzxx.excelhelper.convert.dft;

/**
 * 将String转换成数字类型的抽象实现
 * 
 * @author chenjiaxin
 *
 * @param <T>
 */
public abstract class AbstractStringToNumberConverter<T> extends AbstractToStringConverter<String, T> {

	@Override
	public Class<String> getOriginClass() {
		return String.class;
	}
}
