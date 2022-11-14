package platform.szxyzxx.excelhelper.pojo;
/**
 * 代表一个单元格的值，主要是能够体现出这个值是不是公式值
 * @author chenjiaxin
 *
 */
public class CellValue {
	private Object value;
	
	private boolean isFormula=false;
	
	public CellValue(Object value) {
		super();
		this.value = value;
	}
	public CellValue(Object value, boolean isFormula) {
		super();
		this.value = value;
		this.isFormula = isFormula;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public boolean isFormula() {
		return isFormula;
	}
	public void setFormula(boolean isFormula) {
		this.isFormula = isFormula;
	}
	
}
