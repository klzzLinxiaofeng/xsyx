package platform.szxyzxx.excelhelper.convert.dft;

public class StringToDoubleConverter extends AbstractStringToNumberConverter<Double> {

	@Override
	public Class<Double> getConvertToClass() {
		return Double.class;
	}

}
