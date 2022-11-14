package platform.szxyzxx.excelhelper.convert.dft;

/**
 * 将Double转换成其他能够解析double字符串的（如BigDecimmal,Float）的抽象实现,因为使用了泛型，所以要实现IConvertTypeAware
 * @author chenjiaxin
 *
 * @param <T>
 */
public abstract class AbstractDoubleToNumberConverter<T> extends AbstractToStringConverter<Double, T>{

	@Override
	public Class<Double> getOriginClass() {
		return Double.class;
	}

}
