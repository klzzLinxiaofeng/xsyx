package platform.szxyzxx.excelhelper.exception;

public class InvokeMethodException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7384350111436502527L;
	
	public InvokeMethodException(String msg) {
		super(msg);
	}
	
	public InvokeMethodException(String msg,Throwable cause) {
		super(msg,cause);
	}

}
