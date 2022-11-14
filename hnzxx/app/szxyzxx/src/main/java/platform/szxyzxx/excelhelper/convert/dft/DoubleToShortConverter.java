package platform.szxyzxx.excelhelper.convert.dft;

import platform.szxyzxx.excelhelper.convert.ITypeConverter;

public class DoubleToShortConverter implements ITypeConverter<Double, Short> {

	@Override
	public Short convert(Double value) {
		return value.shortValue();
	}

}
