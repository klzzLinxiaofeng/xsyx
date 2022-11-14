package platform.szxyzxx.excelhelper.exception;
/**
 * 单元格类型为Error时的异常
 * @author chenjiaxin
 *
 */
public class ErrorCellTypeException extends CellResolveException{

	private static final long serialVersionUID = 4210684764496748976L;

	public ErrorCellTypeException(String msg) {
		super(msg);
	}

}
