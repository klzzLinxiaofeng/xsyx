package platform.szxyzxx.excelhelper.convert.dft;

public class DoubleToFloatConverter extends AbstractDoubleToNumberConverter<Float> {

	@Override
	public Class<Float> getConvertToClass() {
		return Float.class;
	}

}
