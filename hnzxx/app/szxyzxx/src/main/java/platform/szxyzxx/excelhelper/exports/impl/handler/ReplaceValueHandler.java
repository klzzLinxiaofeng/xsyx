package platform.szxyzxx.excelhelper.exports.impl.handler;

import java.util.HashMap;
import java.util.Map;

import platform.szxyzxx.excelhelper.exports.CellValueHandler;
import platform.szxyzxx.excelhelper.pojo.CellValue;

/**
 * 对String值进行替换
 * 
 * @author chenjiaxin
 *
 */
public class ReplaceValueHandler implements CellValueHandler {
	/**
	 * 源值（被替换值）和要替换的值得映射Map
	 * 
	 */
	Map<String, String> originAndReplaceMap;

	/**
	 * 
	 * @param repArr
	 *            被替换值和替换值得数组，格式如"{true=是,false=否}"
	 */
	public ReplaceValueHandler(String[] repArr) {
		originAndReplaceMap = createMapByExpressionArr(repArr);
	}

	private Map<String, String> createMapByExpressionArr(String[] repArr) {
		Map<String, String> repValueMap = new HashMap<>();
		for (String repStr : repArr) {
			String[] repValueArr = repStr.split("=");
			if (repValueArr.length != 2)
				throw new IllegalArgumentException("replace表达式：[" + repStr + "],格式不正确");
			repValueMap.put(repValueArr[0], repValueArr[1]);
		}
		return repValueMap;
	}

	@Override
	public CellValue handle(int rowIndex, Object value) {
		Object repValue = originAndReplaceMap.get(value.toString());
		if (repValue == null)
			repValue = "";
		return new CellValue(repValue);
	}

}
