package platform.szxyzxx.excelhelper.imports;

import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

/**
 * Row解析器，用于将一个Excel文件Row,解析为一个对象
 * @author chenjiaxin
 * @param <T> 解析为什么样的Class
 */

public interface RowResolver<T> {
	/**
	 * 将Excel文件的一行或多行，解析为相应的实体
	 * @param row
	 * @param evaluator 解析公式单元格所必需的FormulaEvaluator实例
	 * @return
	 */
	T resolve(FormulaEvaluator evaluator, Row... rows);
}
