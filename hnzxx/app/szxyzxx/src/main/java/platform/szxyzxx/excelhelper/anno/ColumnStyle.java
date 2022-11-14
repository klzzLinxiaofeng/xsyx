package platform.szxyzxx.excelhelper.anno;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import org.apache.poi.ss.usermodel.*;
import platform.szxyzxx.excelhelper.exports.CellStyleSetter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultCellStyleSetter;


/**
 * 导出成Excel时的样式设置
 * 
 * @author chenjiaxin
 *
 */
@Retention(RUNTIME)
@Target(FIELD)
public @interface ColumnStyle {
	/**
	 * 字体颜色,具体的值可以通过打印org.apache.poi.ss.usermodel.IndexedColors类的常量值获取<br/>
	 *	常用的颜色索引如下:</br> 
	 *白色：9，红色：10，蓝色：12，黄色：13，绿色：17
	 *
	 */
	short fontColor() default -1;

	/**
	 * 字体是否加粗，默认不加粗
	 * 
	 */
	short boldweight() default Font.BOLDWEIGHT_NORMAL;

	/**
	 * 字体是否有删除线，默认没有
	 * 
	 */
	boolean fontStrikeout() default false;

	/**
	 * 字体字体下划线，默认没有下划线
	 * 
	 */
	byte fontUnderline() default 0;

	/**
	 * 字体是否斜体，默认不是斜体
	 * 
	 */
	boolean fontItalic() default false;

	/**
	 * 字体大小
	 * 
	 */
	short fontHeightInPoints() default -1;

	/**
	 * 字体名字
	 * 
	 */
	String fontName() default "";

	/**
	 * 前景色(其实就是设置单元格的颜色)，取值和fontColor一样，通过IndexedColors类获得
	 * 
	 */
	short fillForegroundColor() default -1;

	/**
	 * 填充模式，默认FillPatternType.SOLID_FOREGROUND
	 * 
	 */
	short fillPattern() default CellStyle.SOLID_FOREGROUND;

	/**
	 * 水平对齐方式,默认靠左对齐
	 * 
	 */
	short alignment() default CellStyle.ALIGN_LEFT;

	/**
	 * 垂直对齐方式，默认居中
	 * 
	 */
	short verticalAlignment() default CellStyle.VERTICAL_CENTER;


	/**
	 * 是否显示所有边框（黑色），默认不设置边框样式
	 * @return
	 */
	boolean showAllBorder() default false;

	/**
	 * 单元格样式构建器的Class对象，设置后将会调用样式设置器来设置样式，而忽略注解上的其他样式值
	 * 
	 */
	Class<? extends CellStyleSetter> styleSetter() default DefaultCellStyleSetter.class;
};
