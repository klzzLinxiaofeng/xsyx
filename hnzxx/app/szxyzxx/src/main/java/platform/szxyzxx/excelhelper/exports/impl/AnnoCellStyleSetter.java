package platform.szxyzxx.excelhelper.exports.impl;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;

import platform.szxyzxx.excelhelper.exports.CellStyleSetter;
import platform.szxyzxx.excelhelper.util.CellStyleUtil;

public class AnnoCellStyleSetter implements CellStyleSetter {

	private Object styleAnno;
	
	public AnnoCellStyleSetter(Object styleAnno) {
		this.styleAnno=styleAnno;
	}
	
	@Override
	public void setStyle(Row row, Object cellValue, Font font, CellStyle cellStyle) {
		CellStyleUtil.setStyleByAnno(cellStyle, font, styleAnno);
	}

}
