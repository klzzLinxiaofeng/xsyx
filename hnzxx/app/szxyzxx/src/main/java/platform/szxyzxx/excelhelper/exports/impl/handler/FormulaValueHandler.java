package platform.szxyzxx.excelhelper.exports.impl.handler;

import java.util.Map;
import java.util.Set;

import platform.szxyzxx.excelhelper.exports.CellValueHandler;
import platform.szxyzxx.excelhelper.pojo.CellValue;
import platform.szxyzxx.excelhelper.util.FormulaUtil;

/**
 * 将公式表达式转换成正确的Excel公式
 * 
 * @author chenjiaxin
 *
 */
public class FormulaValueHandler implements CellValueHandler {

	private Map<String, Integer> colNameIndex;

	public FormulaValueHandler(Map<String, Integer> colNameIndex) {
		this.colNameIndex = colNameIndex;
	}

	@Override
	public CellValue handle(int rowIndex, Object value) {
		String formulaExpression = value.toString();
		Set<String> colNames = this.colNameIndex.keySet();
		for (String colName : colNames) {
			if (formulaExpression.indexOf(colName) != -1) {
				String replacement = FormulaUtil.genColumnFormulaName(colNameIndex.get(colName)) + (rowIndex + 1);
				formulaExpression = formulaExpression.replaceAll(colName, replacement);
			}
		}
		return new CellValue(formulaExpression,true);
	}

}
