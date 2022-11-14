package platform.education.rest.jw.service.vo;

public class TeacherSalaryField {

	/**
	 * 工资表的字段名
	 */
	private String fieldName;
	/**
	 * 字段所代表的实际项目名
	 */
	private String attrName;
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
	public TeacherSalaryField(String fieldName, String attrName) {
		super();
		this.fieldName = fieldName;
		this.attrName = attrName;
	}
	public TeacherSalaryField() {
		super();
	}
	
	
	
}
