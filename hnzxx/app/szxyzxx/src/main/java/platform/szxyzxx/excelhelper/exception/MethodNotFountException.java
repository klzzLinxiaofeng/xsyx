package platform.szxyzxx.excelhelper.exception;
/**
 * 找不到方法异常
 * @author chenjiaxin
 *
 */
public class MethodNotFountException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7273646365895060272L;

	public MethodNotFountException(String msg) {
		super(msg);
	}
	
	public MethodNotFountException(String msg,Throwable cause) {
		super(msg,cause);
	}

	
}
