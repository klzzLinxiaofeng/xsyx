package platform.szxyzxx.excelhelper.exports.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import platform.szxyzxx.excelhelper.anno.ColumnStyle;
import platform.szxyzxx.excelhelper.anno.DefaultColumnStyle;
import platform.szxyzxx.excelhelper.anno.DefaultTitleColumnStyle;
import platform.szxyzxx.excelhelper.anno.ExcelColumnExport;
import platform.szxyzxx.excelhelper.anno.TitleColumnStyle;
import platform.szxyzxx.excelhelper.exports.CellStyleSetter;
import platform.szxyzxx.excelhelper.exports.SheetColumnFill;
import platform.szxyzxx.excelhelper.exports.SheetFill;
import platform.szxyzxx.excelhelper.pojo.FieldMapping;
import platform.szxyzxx.excelhelper.util.CellStyleUtil;
import platform.szxyzxx.excelhelper.util.CollectionUtil;
import platform.szxyzxx.excelhelper.util.ExcelUtil;
import platform.szxyzxx.excelhelper.util.ReflectUtil;

public class DefaultSheetFill<T> implements SheetFill<T> {

	private Class<T> exportClass;

	private List<FieldMapping> fieldMappings;

	private volatile List<SheetColumnFill> columnFills;

	public DefaultSheetFill(Class<T> exportClass) {
		this.exportClass = exportClass;
		this.fieldMappings = createFieldMappings(exportClass);
	}

	@Override
	public void fill(Sheet sheet, Row titleRow, int startColIndex, List<T> fillDatas,
			Map<Integer, Short> rowHeightMap) {
		// 填充标题行
		fillTitleRow(titleRow, startColIndex, fieldMappings);

		// 设置Sheet中的列宽
		setColumnWidth(titleRow, fieldMappings);

		if (fillDatas.size() > 0) {
			// 填充Sheet时开始行的索引
			int startRowIndex = titleRow.getRowNum() + 1;

			// 是否需要初始化columnFills
			if (columnFills == null) {
				synchronized (this) {
					if (columnFills == null)
						this.columnFills = createColumnFills(this.fieldMappings, titleRow);
				}
			}

			// 调用列填充器，一列一列的添加数据
			for (int i = 0, size = columnFills.size(); i < size; i++) {
				SheetColumnFill colFill = columnFills.get(i);
				colFill.fill(sheet, startColIndex + i, startRowIndex, fillDatas);
			}

		}

		// 设置Sheet中指定行的行高
		if (CollectionUtil.isNotEmpty(rowHeightMap))
			setRowHeight(sheet, rowHeightMap);
	}

	/**
	 * 设置titleRow所在Sheet的列宽
	 * 
	 * @param titleRow
	 * @param fieldMappings
	 */
	private void setColumnWidth(Row titleRow, List<FieldMapping> fieldMappings) {
		Sheet sheet = titleRow.getSheet();
		for (FieldMapping fieldMapping : fieldMappings) {
			ExcelColumnExport ece = fieldMapping.getEceAnno();
			int width = ece.width();
			if (width == -1)
				continue;
			int colIndex = ExcelUtil.findCellIndexByValue(titleRow, ece.value());
			sheet.setColumnWidth(colIndex, width);
		}
	}

	/**
	 * 根据行索引和行高map,设置sheet中行的行高
	 * 
	 * @param sheet
	 * @param rowHeightMap
	 */
	private void setRowHeight(Sheet sheet, Map<Integer, Short> rowHeightMap) {
		Short defaultHeight = rowHeightMap.get(-1);
		int mapSize = rowHeightMap.size();
		if (defaultHeight != null) {
			for (int i = sheet.getFirstRowNum(), size = sheet.getLastRowNum(); i <= size; i++) {
				Row row = sheet.getRow(i);
				if (mapSize == 1 || !rowHeightMap.containsKey(i))
					row.setHeight(defaultHeight);
				else
					row.setHeight(rowHeightMap.get(i));
			}

		} else {
			Set<Integer> keys = rowHeightMap.keySet();
			for (Integer key : keys) {
				Row row = sheet.getRow(key);
				if (row == null)
					throw new IllegalArgumentException("行高Map的行索引：" + key + ",不存在");
				row.setHeight(rowHeightMap.get(key));
			}
		}
	}

	/**
	 * 根据fieldMappings，填充标题行
	 */
	private void fillTitleRow(Row titleRow, int startIndex, List<FieldMapping> fieldMappings) {
		Sheet sheet = titleRow.getSheet();
		Workbook wb = sheet.getWorkbook();
		// 默认样式，即其他未指定列名的样式
		CellStyle dftStyle = CellStyleUtil.createCellStyleByAnno(wb,
				exportClass.getAnnotation(DefaultTitleColumnStyle.class));
		for (int i = 0, size = fieldMappings.size(); i < size; i++) {
			FieldMapping fieldMapping = fieldMappings.get(i);
			String columnName = fieldMapping.getEceAnno().value();
			Cell cell = titleRow.createCell(startIndex + i);
			// 设置列名
			cell.setCellValue(columnName);
			CellStyle cs = dftStyle;
			CellStyleSetter currStyleSetter = fieldMapping.getTitleStyleSetter();
			// 如果当前标题列没有样式注解
			if (currStyleSetter == null) {
				// 如果也没有设置默认样式则不设置样式
				if (cs == null)
					continue;
			} else {// 如果指定了样式，则使用指定的样式
				cs = wb.createCellStyle();
				currStyleSetter.setStyle(titleRow, columnName, wb.createFont(), cs);
			}

			cell.setCellStyle(cs);

		}
	}

	/**
	 * 获取有ExcelColumnExport注解的属性，对进行降序排序，以及获取字段上的ColumnStyle注解，创建SheetColumnFill集合
	 */
	private List<SheetColumnFill> createColumnFills(List<FieldMapping> fieldMappings, Row titleRow) {
		List<SheetColumnFill> sheetColumnFills = new ArrayList<>();
		Map<String, Integer> colNameIndexMap = createColNameIndexMap(titleRow);
		for (FieldMapping fm : fieldMappings) {
			sheetColumnFills.add(
					new DefaultColumnFill(fm, colNameIndexMap, exportClass.getAnnotation(DefaultColumnStyle.class)));
		}
		return sheetColumnFills;
	}

	/**
	 * 获取有ExcelColumnExport注解的属性，对进行降序排序，以及获取字段上的ColumnStyle注解，创建FieldMappings集合
	 */
	private List<FieldMapping> createFieldMappings(Class<?> cls) {
		List<FieldMapping> fieldMappings = new ArrayList<>();
		List<Field> fields = ReflectUtil.getFieldsByAnnoClass(cls, ExcelColumnExport.class);
		sortFields(fields);
		for (Field field : fields) {
			ExcelColumnExport eceAnno = field.getAnnotation(ExcelColumnExport.class);
			// 检查注解上的列名是否重复
			if (checkColNameIsExist(eceAnno, fieldMappings))
				throw new IllegalArgumentException("列名：" + eceAnno.value() + ",已存在");
			CellStyleSetter styleSetter = CellStyleUtil.createCellStyleSetter(field.getAnnotation(ColumnStyle.class));
			CellStyleSetter titleStyleSetter = CellStyleUtil
					.createCellStyleSetter(field.getAnnotation(TitleColumnStyle.class));
			fieldMappings.add(new FieldMapping(field, styleSetter, eceAnno, titleStyleSetter));
		}
		return fieldMappings;
	}

	/**
	 * 检查ExcelColumnExport里面的colName是否在FieldMapping集合的eceAnno属性的colName中存在，即colName必须唯一
	 * 
	 * @param eceAnno
	 * @param fieldMappings
	 * @return
	 */
	private boolean checkColNameIsExist(ExcelColumnExport eceAnno, List<FieldMapping> fieldMappings) {
		for (FieldMapping fieldMapping : fieldMappings) {
			if (fieldMapping.getEceAnno().value().equals(eceAnno.value()))
				return true;
		}
		return false;
	}

	/**
	 * 根据标题行，创建列名和索引的映射map
	 * 
	 * @param titleRow
	 * @return
	 */
	private Map<String, Integer> createColNameIndexMap(Row titleRow) {
		Map<String, Integer> colNameIndexMap = new HashMap<String, Integer>();
		int start = titleRow.getFirstCellNum();
		int end = titleRow.getLastCellNum();
		for (int i = start; i <= end; i++) {
			Cell titleColCell = titleRow.getCell(i);
			// 创建一个Row,他的Cell集合是有最少为5
			if (titleColCell == null)
				break;
			String colName = titleColCell.getStringCellValue();
			colNameIndexMap.put(colName, i);
		}
		return colNameIndexMap;
	}

	/**
	 * 依照field上的ExcelColumnExport注解的orderNum，降序排序
	 * 
	 * @param fields
	 */
	private void sortFields(List<Field> fields) {
		int size = fields.size() - 1;
		// 冒泡排序,采用的是将大的往上面排，当然也可以将小的往下面排
		for (int i = 0; i < size; i++) {
			for (int j = size; j > i; j--) {
				Field curr = fields.get(j);
				Field previous = fields.get(j - 1);
				int currNum = curr.getAnnotation(ExcelColumnExport.class).orderNum();
				int previousNum = previous.getAnnotation(ExcelColumnExport.class).orderNum();
				if (currNum > previousNum) {
					fields.set(j, previous);
					fields.set(j - 1, curr);
				}
			}
		}
	}

}
