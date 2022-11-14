package platform.szxyzxx.excelhelper.exports;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

/**
 * 单元格样式设置
 * @author chenjiaxin
 *
 */
public interface CellStyleSetter {
	/**
	 * 根据传入的参数设置单元格样式
	 * @param row 当前单元格所在行的Row对象，如果样式针对的是多列（例如在DefaultColumnStyle和DefaultTitleColumn注解中指定），则为null，cellValue也将为null
	 * @param cellValue
	 * @param font
	 * @param cellStyle
	 */
	void setStyle(Row row, Object cellValue, Font font, CellStyle cellStyle);
}
