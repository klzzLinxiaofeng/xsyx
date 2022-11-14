package platform.szxyzxx.excelhelper.exception;


/**
 * 单元格解析异常
 * 
 * @author chenjiaxin
 *
 */
public class CellResolveException extends RowResolveException {

	private static final long serialVersionUID = 7447057660717524145L;
	// 异常列的位置
	private int colIndex = -1;

	public CellResolveException(String msg) {
		super(msg);
	}

	public CellResolveException(String msg, int rowIndex, int colIndex) {
		super(msg,rowIndex);
		this.colIndex=colIndex;
	}

	public CellResolveException(String msg, String sheetName, int rowIndex, int colIndex) {
		super(msg,sheetName,rowIndex);
		this.colIndex=colIndex;
		
	}

	public CellResolveException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public CellResolveException(String msg, Throwable cause, int rowIndex, int colIndex) {
		super(msg, cause,rowIndex);
		this.colIndex = colIndex;
	}

	public int getColIndex() {
		return colIndex;
	}

}
