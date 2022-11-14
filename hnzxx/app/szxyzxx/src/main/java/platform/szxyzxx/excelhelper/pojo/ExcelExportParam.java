package platform.szxyzxx.excelhelper.pojo;

import java.util.HashMap;
import java.util.Map;
/**
 * 导出时所必需的参数设置，部分参数只有在导出一个新Sheet时才会使用(如startColIndex,isExcel2003,sheetName)
 * @author chenjiaxin
 *
 * @param <T>
 */
public class ExcelExportParam<T> {
	/**
	 * 有@ExcelColumnExport注解的Class对象
	 */
	private Class<T> exportClass;
	/**
	 * 创建新Sheet时Sheet的名字
	 */
	private String sheetName;
	/**
	 * 当需要创建一个新的Workbook实例时，是否是以Excel 97-2003格式（xls）导出，如果不是将以xlsx格式导出
	 */
	private boolean isExcel2003=true;
	/**
	 * 标题行所在索引
	 */
	private int titleRowIndex=0;
	/**
	 * 开始列的索引，只有遇到导出一个新Sheet时才会使用
	 */
	private int startColIndex=0;
	/**
	 * 是否固定标题行
	 */
	private boolean fixTitleRow=true;
	
	/**
	 * 标题行是否自动筛选
	 * @return
	 */
	boolean autoFilteTitleRow=false;
	
	/**
	 * 行索引和行高映射Map
	 */
	private Map<Integer,Short> rowHeightMap=new HashMap<>(0);
	
	
	
	public ExcelExportParam(Class<T> exportClass, String sheetName, boolean isExcel2003, int titleRowIndex,
			int startColIndex, boolean fixTitleRow,boolean autoFiteTitleRow) {
		this.exportClass = exportClass;
		this.sheetName = sheetName;
		this.isExcel2003 = isExcel2003;
		this.titleRowIndex = titleRowIndex;
		this.startColIndex = startColIndex;
		this.fixTitleRow = fixTitleRow;
		this.autoFilteTitleRow=autoFiteTitleRow;
	}

	public ExcelExportParam(Class<T> exportClass,String SheetName) {
		this(exportClass,SheetName,0);
	}
	
	public ExcelExportParam(Class<T> exportClass,String SheetName,int titleRowIndex) {
		this(exportClass,SheetName,true,titleRowIndex,0,true,false);
	}
	
	public Class<T> getExportClass() {
		return exportClass;
	}
	public void setExportClass(Class<T> exportClass) {
		this.exportClass = exportClass;
	}
	public int getTitleRowIndex() {
		return titleRowIndex;
	}
	public void setTitleRowIndex(int titleRowIndex) {
		this.titleRowIndex = titleRowIndex;
	}
	public int getStartColIndex() {
		return startColIndex;
	}
	public void setStartColIndex(int startColIndex) {
		this.startColIndex = startColIndex;
	}
	public Map<Integer, Short> getRowHeightMap() {
		return rowHeightMap;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public boolean isExcel2003() {
		return isExcel2003;
	}

	public void setExcel2003(boolean isExcel2003) {
		this.isExcel2003 = isExcel2003;
	}

	public boolean isFixTitleRow() {
		return fixTitleRow;
	}

	public void setFixTitleRow(boolean fixTitleRow) {
		this.fixTitleRow = fixTitleRow;
	}

	public void setRowHeightMap(Map<Integer, Short> rowHeightMap) {
		this.rowHeightMap = rowHeightMap;
	}

	public boolean isAutoFilteTitleRow() {
		return autoFilteTitleRow;
	}

	public void setAutoFilteTitleRow(boolean autoFilteTitleRow) {
		this.autoFilteTitleRow = autoFilteTitleRow;
	}
	
	
	
}
