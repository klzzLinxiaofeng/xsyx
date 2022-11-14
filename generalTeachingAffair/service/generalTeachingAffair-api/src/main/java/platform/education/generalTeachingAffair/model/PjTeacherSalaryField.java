package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjTeacherSalaryField
 * @author AutoCreate
 *
 */
public class PjTeacherSalaryField implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * schoolId
	 */
	private Integer schoolId;
	/**
	 * 工资表的字段名
	 */
	private String fieldName;
	/**
	 * 字段所代表的实际项目名
	 */
	private String attrName;
	
	public PjTeacherSalaryField() {
		
	}
	
	public PjTeacherSalaryField(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取id
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置id
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取schoolId
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置schoolId
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取工资表的字段名
	 * @return java.lang.String
	 */
	public String getFieldName() {
		return this.fieldName;
	}
	
	/**
	 * 设置工资表的字段名
	 * @param fieldName
	 * @type java.lang.String
	 */
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	
	/**
	 * 获取字段所代表的实际项目名
	 * @return java.lang.String
	 */
	public String getAttrName() {
		return this.attrName;
	}
	
	/**
	 * 设置字段所代表的实际项目名
	 * @param attrName
	 * @type java.lang.String
	 */
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	
}