package platform.szxyzxx.excelhelper.exception;


public class SheetResolveException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5080316596349375173L;

	//发生异常的Sheet名字
	private String sheetName;
	
	public SheetResolveException() {
		
	}

	public SheetResolveException(String msg) {
		super(msg);
	}
	
	public SheetResolveException(String msg,String sheetName) {
		super(msg);
		this.sheetName=sheetName;
	}
	
	public SheetResolveException(String msg,Throwable cause) {
		super(msg,cause);
	}
	
	public SheetResolveException(String msg,String sheetName,Throwable cause) {
		super(msg,cause);
		this.sheetName=sheetName;
	}

	public String getSheetName() {
		return sheetName;
	}


}
