package platform.szxyzxx.excelhelper.pojo;

import java.lang.reflect.Field;

import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.exports.CellStyleSetter;
/**
 * 代表属性和属性所映射的参数封装，如属性上的注解
 * @author chenjiaxin
 *
 */
public class FieldMapping {
	/**
	 * Field实例
	 */
	private Field field;
	/**
	 * 属性对应的样式设置器，如果该属性对应的单元格不需要设置样式则为null
	 */
	private CellStyleSetter cellStyleSetter;
	/**
	 * 属性上的@ExcelColumnExport注解对象
	 */
	private ExcelColumnExport eceAnno;
	/**
	 * 标题行样式设置器
	 */
	private CellStyleSetter titleStyleSetter;
	
	public FieldMapping() {}
	public FieldMapping(Field field,CellStyleSetter cellStyleSetter,ExcelColumnExport eceAnno,CellStyleSetter titleStyleSetter) {
		this.field=field;
		this.cellStyleSetter=cellStyleSetter;
		this.eceAnno=eceAnno;
		this.titleStyleSetter=titleStyleSetter;
	}
	
	public FieldMapping(Field field,ExcelColumnExport eceAnno) {
		this(field,null,eceAnno,null);
	}
	
	public Field getField() {
		return field;
	}
	public void setField(Field field) {
		this.field = field;
	}
	public CellStyleSetter getCellStyleSetter() {
		return cellStyleSetter;
	}
	public void setCellStyleSetter(CellStyleSetter cellStyleSetter) {
		this.cellStyleSetter = cellStyleSetter;
	}
	public ExcelColumnExport getEceAnno() {
		return eceAnno;
	}
	public void setEceAnno(ExcelColumnExport eceAnno) {
		this.eceAnno = eceAnno;
	}
	public CellStyleSetter getTitleStyleSetter() {
		return titleStyleSetter;
	}
	public void setTitleStyleSetter(CellStyleSetter titleStyleSetter) {
		this.titleStyleSetter = titleStyleSetter;
	}
}
