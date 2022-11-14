package platform.szxyzxx.excelhelper.exports;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * 对一个Sheet进行数据填充，这个Sheet可以是一个空Sheet，也可以是一个有数据的Sheet，如果有数据则会追加
 * @author chenjiaxin
 *
 * @param <T> 填充表格数据的类型，即一个SheetFill实例，只能使用某一类对象填充数据
 */
public interface SheetFill<T> {
	/**
	 * 将数据，填充至指定Sheet中
	 * @param sheet 要填充的Sheet
	 * @param titleRow 当前Sheet标题行
	 * @param startColIndex 开始列的索引
	 * @param fillData 填充的数据
	 * @param rowHeightMap 行索引和行高Map,如果想所有行都一个高度，可将索引设为-1
	 */
	void fill(Sheet sheet, Row titleRow, int startColIndex, List<T> fillData, Map<Integer, Short> rowHeightMap);
	
}
