package platform.szxyzxx.excelhelper.exception;

public class ExcelReadException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7839556632365867846L;

	
	public ExcelReadException(String msg) {
		super(msg);
	}
	public ExcelReadException(String msg,Throwable cause) {
		super(msg,cause);
	}
}
