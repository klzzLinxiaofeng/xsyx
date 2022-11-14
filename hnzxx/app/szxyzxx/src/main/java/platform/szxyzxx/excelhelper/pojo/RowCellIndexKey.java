package platform.szxyzxx.excelhelper.pojo;
/**
 * 行索引和单元格索引封装pojo
 * @author chenjiaxin
 *
 */
public class RowCellIndexKey {
	private int rowIndex=-1;
	private int cellIndex;
	
	public RowCellIndexKey(){
		
	}
	
	public RowCellIndexKey(int cellIndex) {
		this.cellIndex=cellIndex;
	}
	
	public RowCellIndexKey(int rowIndex,int cellIndex) {
		this.rowIndex=rowIndex;
		this.cellIndex=cellIndex;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}
	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}
	public int getCellIndex() {
		return cellIndex;
	}
	public void setCellIndex(int cellIndex) {
		this.cellIndex = cellIndex;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cellIndex;
		result = prime * result + rowIndex;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RowCellIndexKey other = (RowCellIndexKey) obj;
		if (cellIndex != other.cellIndex)
			return false;
		if (rowIndex != other.rowIndex)
			return false;
		return true;
	}
	
	
}
