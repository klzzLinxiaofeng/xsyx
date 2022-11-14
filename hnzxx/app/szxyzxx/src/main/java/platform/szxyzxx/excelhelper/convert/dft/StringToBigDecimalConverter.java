package platform.szxyzxx.excelhelper.convert.dft;

import java.math.BigDecimal;

public class StringToBigDecimalConverter extends AbstractStringToNumberConverter<BigDecimal>{

	@Override
	public Class<BigDecimal> getConvertToClass() {
		return BigDecimal.class;
	}

	

}
