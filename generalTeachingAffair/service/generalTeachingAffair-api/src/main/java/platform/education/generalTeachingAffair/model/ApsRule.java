package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsRule
 * @author AutoCreate
 *
 */
public class ApsRule implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 制定的学校  school.id
	 */
	private Integer schoolId;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * 版本
	 */
	private String version;
	/**
	 * 适用评价对象类型  pj.team=班级  pj.student=学生
	 */
	private String objectType;
	/**
	 * 说明
	 */
	private String description;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modifyDate;
	
	public ApsRule() {
		
	}
	
	public ApsRule(Integer id) {
		this.id = id;
	}
	
	public Integer getKey() {
		return this.id;
	}

	/**
	 * 获取主键
	 * @return java.lang.Integer
	 */
	public Integer getId() {
		return this.id;
	}
	
	/**
	 * 设置主键
	 * @param id
	 * @type java.lang.Integer
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * 获取制定的学校  school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置制定的学校  school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取版本
	 * @return java.lang.String
	 */
	public String getVersion() {
		return this.version;
	}
	
	/**
	 * 设置版本
	 * @param version
	 * @type java.lang.String
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * 获取适用评价对象类型  pj.team=班级  pj.student=学生
	 * @return java.lang.String
	 */
	public String getObjectType() {
		return this.objectType;
	}
	
	/**
	 * 设置适用评价对象类型  pj.team=班级  pj.student=学生
	 * @param objectType
	 * @type java.lang.String
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	/**
	 * 获取说明
	 * @return java.lang.String
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * 设置说明
	 * @param description
	 * @type java.lang.String
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * 获取删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置删除标志
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	/**
	 * 获取创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取最后修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置最后修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}