package platform.szxyzxx.excelhelper.exports.impl;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

import platform.szxyzxx.excelhelper.exports.CellStyleSetter;
/**
 * 默认的CellStyleSetter实现，其实啥都没做，存在的意义只是为了能够设置ExcelExportStyle注解的styleSetter()方法的默认值
 * @author chenjiaxin
 *
 */
public class DefaultCellStyleSetter implements CellStyleSetter {

	@Override
	public void setStyle(Row row, Object cellValue, Font font, CellStyle cellStyle) {
		
	}
	
}
