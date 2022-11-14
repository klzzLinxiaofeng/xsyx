package platform.szxyzxx.excelhelper.convert.dft;

public class StringToShortConverter extends AbstractStringToNumberConverter<Short> {

	@Override
	public Class<Short> getConvertToClass() {
		return Short.class;
	}

}
