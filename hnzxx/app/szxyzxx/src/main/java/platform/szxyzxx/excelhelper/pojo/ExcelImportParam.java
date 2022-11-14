package platform.szxyzxx.excelhelper.pojo;

import platform.szxyzxx.excelhelper.convert.TypeConvertManager;
public class ExcelImportParam<T> {

	/**
	 * 类型转换管理器
	 */
	private TypeConvertManager typeConvertManager=new TypeConvertManager();
	/**
	 * 要解析（导入）类的Class对象
	 */
	private Class<T> resolveClass;
	/**
	 * 	Sheet中标题行所在索引
	 */
	private int titleRowIndex;
	/**
	 * 开始行的索引，如果不指定，默认从标题行的下一行开始解析
	 */
	private Integer startRowIndex;
	/**
	 * 结束行的索引，如果不指定，默认读取到最后一行
	 */
	private Integer endRowIndex;
	
	public ExcelImportParam(Class<T> resolveClass, int titleRowIndex) {
		super();
		this.resolveClass = resolveClass;
		this.titleRowIndex = titleRowIndex;
		this.startRowIndex=titleRowIndex+1;
	}
	
	public ExcelImportParam(Class<T> resolveClass) {
		this(resolveClass,0);
	}
	
	public Class<T> getResolveClass() {
		return resolveClass;
	}
	public void setResolveClass(Class<T> resolveClass) {
		this.resolveClass = resolveClass;
	}
	public int getTitleRowIndex() {
		return titleRowIndex;
	}
	public void setTitleRowIndex(int titleRowIndex) {
		this.titleRowIndex = titleRowIndex;
	}
	public Integer getStartRowIndex() {
		return startRowIndex;
	}
	public void setStartRowIndex(int startRowIndex) {
		if(startRowIndex>titleRowIndex)
			this.startRowIndex = startRowIndex;
	}
	public Integer getEndRowIndex() {
		return endRowIndex;
	}
	public void setEndRowIndex(int endRowIndex) {
		if(endRowIndex>=startRowIndex)
			this.endRowIndex = endRowIndex;
	}
	public TypeConvertManager getTypeConvertManager() {
		return typeConvertManager;
	}
}
