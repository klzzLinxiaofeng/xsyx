package platform.szxyzxx.excelhelper.convert.dft;

import platform.szxyzxx.excelhelper.convert.ITypeConverter;

public class DoubleToIntegerConverter implements ITypeConverter<Double, Integer> {

	@Override
	public Integer convert(Double value) {
		return value.intValue();
	}
	
}
