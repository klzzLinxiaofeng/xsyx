package platform.szxyzxx.excelhelper.imports.impl;

import platform.szxyzxx.excelhelper.exception.IgnoreRowException;
import platform.szxyzxx.excelhelper.imports.CellProcessController;
import platform.szxyzxx.excelhelper.util.ExcelUtil;
/**
 * 判断是否忽略某一行,其实就是对ExcelColumnImport注解的ignoreValue的实现支持
 * @author chenjiaxin
 *
 */
public class IgnoreRowCellProcessController implements CellProcessController {
	/**
	 * 如果属性值等于数组中的任意一个，将会忽略当前行
	 */
	private String[] ignoreValues;
	private int colIndex;
	public IgnoreRowCellProcessController(String[] ignoreValues,int colIndex) {
		this.ignoreValues=ignoreValues;
		this.colIndex=colIndex;
	}

	@Override
	public void handle(int colIndex,Object cellVal) {
		if(colIndex==this.colIndex && ExcelUtil.ifIncludeCellValue(ignoreValues, cellVal==null?null:cellVal.toString())) {
			throw new IgnoreRowException();
		}
	}



}
