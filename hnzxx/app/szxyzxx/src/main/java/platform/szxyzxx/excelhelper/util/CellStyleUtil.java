package platform.szxyzxx.excelhelper.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;

import platform.szxyzxx.excelhelper.exports.CellStyleSetter;
import platform.szxyzxx.excelhelper.exports.impl.AnnoCellStyleSetter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultCellStyleSetter;

public abstract class CellStyleUtil {
	
	public static void setStyleByAnno(CellStyle style, Font font, Object styleAnno) {
		/**
		 * 之所以使用反射调用方法，是因为好几个样式注解都是一模一样，无奈注解不能继承，所以只能通过反射，调用固定的方法名提高重用
		 */
		
		// 设置字体样式
		font.setBoldweight((short) invokeAnnoMethod(styleAnno, "boldweight"));
		short fontColor = (short) invokeAnnoMethod(styleAnno, "fontColor");
		if (fontColor != -1)
			font.setColor(fontColor);
		
		short fontHeightInPoints = (short) invokeAnnoMethod(styleAnno, "fontHeightInPoints");
		if (fontHeightInPoints != -1)
			font.setFontHeightInPoints(fontHeightInPoints);
		String fontName=(String) invokeAnnoMethod(styleAnno, "fontName");
		if(!fontName.isEmpty())
			font.setFontName(fontName);
		font.setItalic((boolean) invokeAnnoMethod(styleAnno, "fontItalic"));
		font.setStrikeout((boolean) invokeAnnoMethod(styleAnno, "fontStrikeout"));
		font.setUnderline((byte) invokeAnnoMethod(styleAnno, "fontUnderline"));
		// 设置单元格样式
		style.setAlignment((short) invokeAnnoMethod(styleAnno, "alignment"));
		short fillForegroundColor = (short) invokeAnnoMethod(styleAnno, "fillForegroundColor");
		if (fillForegroundColor != -1) {
			style.setFillForegroundColor(fillForegroundColor);
			style.setFillPattern((short) invokeAnnoMethod(styleAnno, "fillPattern"));
		}
		style.setVerticalAlignment((short) invokeAnnoMethod(styleAnno, "verticalAlignment"));
		
		//设置边框样式
		boolean showAllBorder=(boolean) invokeAnnoMethod(styleAnno, "showAllBorder");
		if(showAllBorder) {
			style.setBorderBottom(CellStyle.BORDER_THIN); //下边框
			style.setBorderLeft(CellStyle.BORDER_THIN);//上边框
			style.setBorderRight(CellStyle.BORDER_THIN);//右边框
			style.setBorderTop(CellStyle.BORDER_THIN);//左边框
		}
		style.setFont(font);

	}
	public static CellStyleSetter createCellStyleSetter(Object styleAnno) {
		if (styleAnno == null)
			return null;
		Class<?> styleClass = getAnnoStyleSetter(styleAnno);
		if (styleClass == DefaultCellStyleSetter.class)
			return new AnnoCellStyleSetter(styleAnno);
		return (CellStyleSetter) ReflectUtil.createObject(styleClass);
	}

	@SuppressWarnings("unchecked")
	public static Class<? extends CellStyleSetter> getAnnoStyleSetter(Object styleAnno) {
		try {
			return (Class<? extends CellStyleSetter>) ReflectUtil.invokeNoOverloadMethod(styleAnno, "styleSetter");
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 创建样式注解中所对应的CellStyle实例，如果注解指定了CellStyleSetter,则row=null和cellValue=null
	 * @param workbook
	 * @param styleAnno
	 * @return
	 */
	public static CellStyle createCellStyleByAnno(Workbook workbook,Object styleAnno) {
		if(styleAnno==null)
			return null;
		CellStyleSetter styleSetter=createCellStyleSetter(styleAnno);
		CellStyle style=workbook.createCellStyle();
		styleSetter.setStyle(null, null, workbook.createFont(), style);
		return style;
	}
	
	/**
	 * 如果CellStyleSetter是AnnoCellStyleSetter（说明整列的样式都一样，不需要动态改变）则创建默认的单元格style
	 * 
	 */
	public static CellStyle createCellStyleByAnnoCellStyleSetter(Workbook workbook, CellStyleSetter styleSetter) {
		if (styleSetter != null && styleSetter instanceof AnnoCellStyleSetter) {
			CellStyle dftStyle = workbook.createCellStyle();
			styleSetter.setStyle(null, null, workbook.createFont(), dftStyle);
			return dftStyle;
		}
		return null;
	}
	

	private static Object invokeAnnoMethod(Object styleAnno, String methodName) {
		try {
			return ReflectUtil.invokeNoOverloadMethod(styleAnno, methodName);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(e);
		}
	}

}
