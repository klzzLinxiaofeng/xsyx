package platform.szxyzxx.excelhelper.exports;

import platform.szxyzxx.excelhelper.pojo.CellValue;

/**
 * 单元格值处理器器，主要是对要设置的值进行逻辑处理
 * 
 * @author chenjiaxin
 *
 */
public interface CellValueHandler {
	/**
	 * 对单元格的值进行逻辑处理后返回最终要设置到单元格中的值
	 * @param rowIndex 当前单元格所在行的索引
	 * @param value 
	 * @return 逻辑处理后的值
	 */
	CellValue handle(int rowIndex, Object value);
}
