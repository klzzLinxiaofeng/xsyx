package platform.szxyzxx.excelhelper.convert.dft;

public class StringToIntegerConverter extends AbstractStringToNumberConverter<Integer> {

	@Override
	public Class<Integer> getConvertToClass() {
		return Integer.class;
	}

}
