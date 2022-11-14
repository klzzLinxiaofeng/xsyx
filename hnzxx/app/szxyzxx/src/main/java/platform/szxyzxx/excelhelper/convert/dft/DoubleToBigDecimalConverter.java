package platform.szxyzxx.excelhelper.convert.dft;

import java.math.BigDecimal;

public class DoubleToBigDecimalConverter extends AbstractDoubleToNumberConverter<BigDecimal> {

	@Override
	public Class<BigDecimal> getConvertToClass() {
		return BigDecimal.class;
	}

}
