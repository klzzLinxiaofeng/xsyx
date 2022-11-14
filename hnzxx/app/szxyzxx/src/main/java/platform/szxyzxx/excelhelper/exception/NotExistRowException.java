package platform.szxyzxx.excelhelper.exception;

public class NotExistRowException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3565047510758577874L;

	public NotExistRowException(){
		
	}
	
	public NotExistRowException(String msg){
		super(msg);
	}
	
}
