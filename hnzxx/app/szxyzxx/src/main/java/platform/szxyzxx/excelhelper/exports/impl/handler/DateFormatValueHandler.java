package platform.szxyzxx.excelhelper.exports.impl.handler;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import platform.szxyzxx.excelhelper.exports.CellValueHandler;
import platform.szxyzxx.excelhelper.pojo.CellValue;

/**
 * 对日期类型数据进行格式化转换
 * 
 * @author chenjiaxin
 *
 */
public class DateFormatValueHandler implements CellValueHandler {
	SimpleDateFormat fmt;

	public DateFormatValueHandler(String formatPatern) {
		fmt = new SimpleDateFormat(formatPatern);
	}

	private String fmt(Object originValue) {
		if (originValue instanceof Calendar) {
			return fmt.format(new Date(((Calendar) originValue).getTimeInMillis()));
		}
		if (originValue instanceof Date)
			return fmt.format((Date) originValue);
		throw new IllegalArgumentException(originValue + "不是Calendar或者Date类型");
	}

	@Override
	public CellValue handle(int rowIndex, Object value) {
		return new CellValue(fmt(value));
	}
}
