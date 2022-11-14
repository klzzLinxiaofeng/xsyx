package platform.szxyzxx.excelhelper.convert.dft;

public class StringToFloatConverter extends AbstractStringToNumberConverter<Float> {

	@Override
	public Class<Float> getConvertToClass() {
		return Float.class;
	}

}
