package platform.szxyzxx.excelhelper.exports.impl.handler;

import platform.szxyzxx.excelhelper.exports.CellValueHandler;
import platform.szxyzxx.excelhelper.pojo.CellValue;

/**
 * 不会对单元格值进行任何处理
 * 
 * @author chenjiaxin
 *
 */
public class DefaultValueHandler implements CellValueHandler {

	@Override
	public CellValue handle(int rowIndex, Object value) {
		return new CellValue(value);
	}

}
