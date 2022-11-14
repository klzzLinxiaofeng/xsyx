package platform.szxyzxx.excelhelper.exception;

public class RowResolveException extends SheetResolveException{

	private static final long serialVersionUID = 239212263297061858L;
	
	/**
	 * 发生异常的行的索引
	 */
	private int rowIndex;
	
	public RowResolveException(String msg) {
		super(msg);
	}

	public RowResolveException(String msg,Throwable cause) {
		super(msg,cause);
	}
	
	public RowResolveException(String msg,int rowIndex) {
		super(msg);
		this.rowIndex=rowIndex;
	}
	
	public RowResolveException(String msg,String sheetName,int rowIndex) {
		super(msg,sheetName);
		this.rowIndex=rowIndex;
	}
	
	public RowResolveException(String msg,Throwable cause,String sheetName,int rowIndex) {
		super(msg,sheetName,cause);
		this.rowIndex=rowIndex;
	}
	
	public RowResolveException(String msg,Throwable cause,int rowIndex) {
		super(msg,cause);
		this.rowIndex=rowIndex;
	}

	public int getRowIndex() {
		return rowIndex;
	}
	
}
