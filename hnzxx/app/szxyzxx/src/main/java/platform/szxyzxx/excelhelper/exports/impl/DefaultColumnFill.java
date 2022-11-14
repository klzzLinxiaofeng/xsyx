package platform.szxyzxx.excelhelper.exports.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import platform.szxyzxx.excelhelper.anno.DefaultColumnStyle;
import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.exports.CellStyleSetter;
import platform.szxyzxx.excelhelper.exports.CellValueHandler;
import platform.szxyzxx.excelhelper.exports.SheetColumnFill;
import platform.szxyzxx.excelhelper.exports.impl.handler.AppendSuffixValueHandler;
import platform.szxyzxx.excelhelper.exports.impl.handler.DateFormatValueHandler;
import platform.szxyzxx.excelhelper.exports.impl.handler.DefaultValueHandler;
import platform.szxyzxx.excelhelper.exports.impl.handler.FormulaValueHandler;
import platform.szxyzxx.excelhelper.exports.impl.handler.ReplaceValueHandler;
import platform.szxyzxx.excelhelper.pojo.CellValue;
import platform.szxyzxx.excelhelper.pojo.FieldMapping;
import platform.szxyzxx.excelhelper.util.CellStyleUtil;
import platform.szxyzxx.excelhelper.util.ExcelUtil;
import platform.szxyzxx.excelhelper.util.ReflectUtil;
import platform.szxyzxx.excelhelper.util.StringUtil;

public class DefaultColumnFill implements SheetColumnFill {
	/**
	 * 默认的样式注解
	 */
	private DefaultColumnStyle defaultStyleAnno;

	/**
	 * 信息管理系统 要使用对象的那个属性的值进行填充，以及该属性映射的其他关联数据
	 */
	private FieldMapping fieldMapping;
	/**
	 * 列名和对应的索引Map,解析公式表达式时使用
	 */
	private Map<String, Integer> colNameIndexMap;
	/**
	 * 单元格值设置器
	 */
	private CellValueHandler valueHandler;

	public DefaultColumnFill(FieldMapping fieldMapping, Map<String, Integer> colNameIndexMap,
			DefaultColumnStyle defaultStyleAnno) {
		this.fieldMapping = fieldMapping;
		this.colNameIndexMap = colNameIndexMap;
		this.valueHandler = createCellValueHandler(fieldMapping);
		this.defaultStyleAnno = defaultStyleAnno;
	}

	@Override
	public void fill(Sheet sheet, int satrtColumnIndex, int startRowIndex, List<?> fillDatas) {
		Workbook workbook = sheet.getWorkbook();
		// 默认的样式
		CellStyle dftStyle = CellStyleUtil.createCellStyleByAnno(workbook, defaultStyleAnno);
		// 有指定样式，但是没有指定CellStyleSetter时则不为空
		CellStyle annoStyle = CellStyleUtil.createCellStyleByAnnoCellStyleSetter(workbook,
				fieldMapping.getCellStyleSetter());
		String fieldName = fieldMapping.getField().getName();
		for (int i = 0, size = fillDatas.size(); i < size; i++) {
			Object dataObj = fillDatas.get(i);
			Row row = sheet.getRow(startRowIndex + i);
			if (row == null)
				row = sheet.createRow(startRowIndex + i);
			Cell cell = row.getCell(satrtColumnIndex);
			if (cell != null)
				throw new IllegalArgumentException(
						"指定的列索引：" + satrtColumnIndex + ",在第:" + row.getRowNum() + "列不为空，不能创建一整列");
			cell = row.createCell(satrtColumnIndex);
			// 调用getter
			Object getterValue = ReflectUtil.invokeGetter(dataObj, fieldName);
			CellValue changedValue=new CellValue(null);
			if(getterValue!=null) {
				// 设置单元格的值
				changedValue = valueHandler.handle(row.getRowNum(), getterValue);
				ExcelUtil.setCellValue(cell, changedValue.getValue(), changedValue.isFormula());
			}
			// 设置样式
			CellStyleSetter styleSetter = fieldMapping.getCellStyleSetter();
			CellStyle style = null;

			// 如果该列没有指定样式
			if (styleSetter == null) {
				// 如果默认样式也没指定则说明不需要设置样式
				if (dftStyle == null)
					continue;
				style = dftStyle;

			} else {
				// 说明当前列是指定了CellStyleSetter来设置样式的，那么就必须每次创建一个CellStyle实例
				if (annoStyle == null) {
					style = workbook.createCellStyle();
					styleSetter.setStyle(row, changedValue.getValue(), workbook.createFont(), style);
				} else
					// 说明当前列的样式是通过注解来定死的，不需要动态改变，也就不需要每次都创建CellStyle实例了
					style = annoStyle;
			}

			// 设置单元格样式
			cell.setCellStyle(style);

		}

	}

	/**
	 * 根据ExcelColumnExport注解的isFormula、replace、suffix的值，创建相应的CellValueSetter实例
	 * 
	 * @param fm
	 * @return
	 */
	private CellValueHandler createCellValueHandler(FieldMapping fm) {
		Class<?> fieldType = fm.getField().getType();
		ExcelColumnExport ece = fm.getEceAnno();
		if (ece.isFormula() && StringUtil.classIsString(fieldType)) {
			return new FormulaValueHandler(colNameIndexMap);
		} else if (fieldType == Date.class || fieldType == Calendar.class) {
			if (!ece.dateFormat().isEmpty())
				return new DateFormatValueHandler(ece.dateFormat());
		}else if (ece.replace().length > 0 && !ece.replace()[0].isEmpty())
			return new ReplaceValueHandler(ece.replace());
		else if (!ece.suffix().isEmpty())
			return new AppendSuffixValueHandler(ece.suffix());
		
		return new DefaultValueHandler();
	}

}
