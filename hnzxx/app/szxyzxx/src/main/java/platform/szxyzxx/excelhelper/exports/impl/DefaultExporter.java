package platform.szxyzxx.excelhelper.exports.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.SheetFill;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.util.AssertUtil;
import platform.szxyzxx.excelhelper.util.FormulaUtil;

public class DefaultExporter<T> implements Exporter<T> {

	private SheetFill<T> sheetFill;
	private String sheetName;
	private boolean isExcel2003;
	private boolean fixTitleRow;
	private int titleRowIndex;
	private int startColIndex;
	private boolean autoFilteTitleRow;
	Map<Integer, Short> rowHeightMap;

	public DefaultExporter(ExcelExportParam<T> exportParam) {
		Class<T> exportClass = exportParam.getExportClass();
		this.titleRowIndex = exportParam.getTitleRowIndex();
		this.startColIndex = exportParam.getStartColIndex();
		this.rowHeightMap = exportParam.getRowHeightMap();
		this.sheetName = exportParam.getSheetName();
		this.isExcel2003 = exportParam.isExcel2003();
		this.fixTitleRow = exportParam.isFixTitleRow();
		this.autoFilteTitleRow = exportParam.isAutoFilteTitleRow();
		sheetFill = new DefaultSheetFill<>(exportClass);
	}

	@Override
	public Workbook exportToNew(List<T> exportDatas) {
		Sheet sheet = this.createSheet();
		return this.export(sheet, titleRowIndex, exportDatas);
	}

	@Override
	public Workbook exportToStreamSheet(InputStream is, List<T> exportDatas)
			throws EncryptedDocumentException, IOException, InvalidFormatException {
		Sheet sheet = this.createSheet(WorkbookFactory.create(is));
		return this.export(sheet, titleRowIndex, exportDatas);
	}

	@Override
	public void exportToWorkbook(Workbook wb, List<T> exportDatas) {
		Sheet sheet = this.createSheet(wb);
		this.export(sheet, titleRowIndex, exportDatas);
	}

	@Override
	public Workbook exportToExistSheet(InputStream is, int existSheetIndex, int titleRowIndex, List<T> exportDatas)
			throws EncryptedDocumentException, IOException, InvalidFormatException {
		Sheet sheet = WorkbookFactory.create(is).getSheetAt(existSheetIndex);
		AssertUtil.assertNull(sheet, "Sheet索引：" + existSheetIndex + ",不存在");
		return this.export(sheet, titleRowIndex, exportDatas);
	}

	@Override
	public Workbook exportToExistSheet(InputStream is, int existSheetIndex, List<T> exportDatas)
			throws EncryptedDocumentException, IOException, InvalidFormatException {
		return exportToExistSheet(is, existSheetIndex, 0, exportDatas);
	}

	@Override
	public void exportToExistSheet(Sheet existSheet, int titleRowIndex, List<T> exportDatas) {
		AssertUtil.assertNull(existSheet, "existSheet 不能为空");
		this.export(existSheet, titleRowIndex, exportDatas);
	}

	private Workbook export(Sheet sheet, int titleRowIndex, List<T> exportDatas) {
		Row titleRow = sheet.getRow(titleRowIndex);
		int startColIndex = this.startColIndex;
		if (titleRow == null)
			titleRow = sheet.createRow(titleRowIndex);
		else
			startColIndex = titleRow.getLastCellNum();

		sheetFill.fill(sheet, titleRow, startColIndex, exportDatas, rowHeightMap);

		// 是否固定标题行
		if (this.fixTitleRow)
			sheet.createFreezePane(titleRowIndex, 1);

		// 是否对标题行列增加自动筛选
		if (this.autoFilteTitleRow) {
			CellRangeAddress rangeAddress = CellRangeAddress.valueOf(genRangeAddressByTitleRow(titleRow));
			sheet.setAutoFilter(rangeAddress);
		}
		Workbook wb=sheet.getWorkbook();
		//设置强制重新计算公式，解决追加xlsx表格时，公式不计算bug(WPS不会显示)
		//wb.setForceFormulaRecalculation(true);
		return wb;
	}

	private String genRangeAddressByTitleRow(Row titleRow) {
		int titleRowNum = titleRow.getRowNum()+1;
		StringBuffer sb=new StringBuffer("A");
		sb.append(titleRowNum)
		.append(":")
		.append(FormulaUtil.genColumnFormulaName(titleRow.getLastCellNum() - 1))
		.append(titleRowNum);
		return sb.toString();
	}

	@Override
	public void exportToExistSheet(Sheet existSheet, List<T> exportDatas) {
		exportToExistSheet(existSheet, 0, exportDatas);
	}

	private Sheet createSheet() {
		Workbook wb = null;
		if (this.isExcel2003) {
			wb = new HSSFWorkbook();
			closeWorkbook(wb);
		} else
			wb = new XSSFWorkbook();
		return createSheet(wb);
	}

	private Sheet createSheet(Workbook wb) {
		Sheet sheet = wb.createSheet(this.sheetName);
		return sheet;
	}

	private void closeWorkbook(Workbook wb) {
//		try {
//			wb.close();
//		} catch (IOException e) {
//			throw new RuntimeException(e);
//		}
	}

}
