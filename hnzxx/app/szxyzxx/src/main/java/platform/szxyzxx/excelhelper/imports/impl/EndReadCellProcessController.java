package platform.szxyzxx.excelhelper.imports.impl;

import platform.szxyzxx.excelhelper.exception.BreakRowException;
import platform.szxyzxx.excelhelper.imports.CellProcessController;
import platform.szxyzxx.excelhelper.util.ExcelUtil;

/**
 * 结束Sheet的行读取，对ExcelColumnImport注解的endValue的实现
 * @author chenjiaxin
 *
 */
public class EndReadCellProcessController implements CellProcessController {

	
	/**
	 * 如果属性值等于数组中的任意一个，将会忽略当前行
	 */
	private String[] endValues;

	private int colIndex;
	
	public EndReadCellProcessController(String[] endValues,int colIndex) {
		this.endValues=endValues;
		this.colIndex=colIndex;
	}


	@Override
	public void handle(int colIndex,Object cellVal) {
		if(colIndex==this.colIndex && ExcelUtil.ifIncludeCellValue(endValues, cellVal==null?null:cellVal.toString())){
			throw new BreakRowException();
		}
	}


}
