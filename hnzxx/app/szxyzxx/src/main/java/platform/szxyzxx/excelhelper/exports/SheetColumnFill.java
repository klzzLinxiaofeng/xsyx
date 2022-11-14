package platform.szxyzxx.excelhelper.exports;

import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

/**
 * 能够在Sheet上创建一整列，并进行数据填充
 * @author chenjiaxin
 *
 */
public interface SheetColumnFill {
	/**
	 * 指定Sheet以及列的位置，根据数据，创建一整列
	 * @param sheet 在那个Sheet上创建一列
	 * @param satrtColumnIndex y轴索引
	 * @param startRowIndex x轴索引
	 * @param fillDatas fillDatas集合的大小就是创建列的长度，集合中的对象的某个属性的值就是单元格的值
	 */
	void fill(Sheet sheet, int satrtColumnIndex, int startRowIndex, List<?> fillDatas);
}
