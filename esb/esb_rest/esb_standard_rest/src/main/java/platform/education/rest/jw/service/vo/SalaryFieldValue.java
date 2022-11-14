package platform.education.rest.jw.service.vo;

public class SalaryFieldValue {

	/**
	 * 工资表的字段名
	 */
	private String fieldName;
	/**
	 * 字段所代表的实际项目名
	 */
	private String attrName;
	
	private Float value;

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}
	
	
	
	
}
