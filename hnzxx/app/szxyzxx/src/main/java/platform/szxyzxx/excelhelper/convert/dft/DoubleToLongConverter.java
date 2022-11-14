package platform.szxyzxx.excelhelper.convert.dft;

import platform.szxyzxx.excelhelper.convert.ITypeConverter;

public class DoubleToLongConverter implements ITypeConverter<Double, Long> {

	@Override
	public Long convert(Double value) {
		return value.longValue();
	}

	

}
