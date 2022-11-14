package platform.szxyzxx.excelhelper.imports.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import platform.szxyzxx.excelhelper.exception.ExcelReadException;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.SheetResolver;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.excelhelper.util.AssertUtil;

public class DefaultImporter<T> implements Importer<T> {

	private SheetResolver<T> sheetResolver;

	public DefaultImporter(ExcelImportParam<T> params) {
		SingleRowSheetResolver<T> sheetResolver = new SingleRowSheetResolver<T>(params.getTypeConvertManager(),
				params.getResolveClass(), params.getTitleRowIndex());
		if (params.getStartRowIndex() != null)
			sheetResolver.setStartRowIndex(params.getStartRowIndex());
		if (params.getEndRowIndex() != null)
			sheetResolver.setEndRowIndex(params.getEndRowIndex());
		this.sheetResolver = sheetResolver;
	}

	@Override
	public List<T> importBy(InputStream is) {
		return importBy(is, 0);
	}

	@Override
	public List<T> importBy(Sheet sheet) {
		return sheetResolver.resovle(sheet);
	}

	@Override
	public List<T> importBy(InputStream is, int sheetIndex) {
		try {
			AssertUtil.assertNull(is, "Excel文件输入流不能为空");
			Workbook wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheetAt(sheetIndex);
			AssertUtil.assertNull(sheet, "没有索引为" + sheetIndex + "的Sheet");
			return importBy(sheet);
		} catch (EncryptedDocumentException | IOException | InvalidFormatException e) {
			throw new ExcelReadException("Excel文件读取失败", e);
		}
	}

	@Override
	public List<T> importBy(InputStream is, String sheetName) {
		try {
			AssertUtil.assertNull(is, "Excel文件输入流不能为空");
			Workbook wb = WorkbookFactory.create(is);
			Sheet sheet = wb.getSheet(sheetName);
			AssertUtil.assertNull(sheet, "没有名为为‘" + sheet + "’的Sheet");
			return importBy(sheet);
		} catch (EncryptedDocumentException | IOException | InvalidFormatException e) {
			throw new ExcelReadException("Excel文件读取失败", e);
		}
	}

	@Override
	public List<T> importBy(String filePath) throws FileNotFoundException {
		importBy(new FileInputStream(filePath));
		return null;
	}

	@Override
	public List<T> importBy(File file) throws FileNotFoundException {
		importBy(new FileInputStream(file));
		return null;
	}

}
