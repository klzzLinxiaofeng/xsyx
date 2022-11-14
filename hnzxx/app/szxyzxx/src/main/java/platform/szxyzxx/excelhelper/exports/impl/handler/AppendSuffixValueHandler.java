package platform.szxyzxx.excelhelper.exports.impl.handler;

import platform.szxyzxx.excelhelper.exports.CellValueHandler;
import platform.szxyzxx.excelhelper.pojo.CellValue;

/**
 * 在值后面追加一个字符串
 * 
 * @author chenjiaxin
 *
 */
public class AppendSuffixValueHandler implements CellValueHandler {

	private String appendStr;

	public AppendSuffixValueHandler(String appendStr) {
		this.appendStr = appendStr;
	}

	@Override
	public CellValue handle(int rowIndex, Object value) {
		return new CellValue(value + appendStr);
	}

}
