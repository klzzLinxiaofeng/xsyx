package platform.szxyzxx.excelhelper.exception;
/**
 *代表不能进行类型转换
 * @author chenjiaxin
 *
 */
public class TypeConvertException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5315994779827673625L;
	
	public TypeConvertException() {
		
	}
	
	public TypeConvertException(String msg) {
		super(msg);
	}
	
	public TypeConvertException(String msg,Throwable cause) {
		super(msg,cause);
	}

}
