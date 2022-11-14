package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsTask
 * @author AutoCreate
 *
 */
public class ApsTask implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 使用的评分规则  aps_rule.id
	 */
	private Integer ruleId;
	/**
	 * 本次评价所在学校  school.id
	 */
	private Integer schoolId;
	/**
	 * 评价目标对象类型  pj.team=班级  pj.student=学生
	 */
	private String objectType;
	/**
	 * 名称（可空）
	 */
	private String name;
	/**
	 * 学年代码  school_year.year
	 */
	private String schoolYear;
	/**
	 * 学期代码  school_term.code
	 */
	private String termCode;
	/**
	 * 评价开始时间
	 */
	private java.util.Date startDate;
	/**
	 * 评价结束时间
	 */
	private java.util.Date finishDate;
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
	
	public ApsTask() {
		
	}
	
	public ApsTask(Integer id) {
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
	 * 获取使用的评分规则  aps_rule.id
	 * @return java.lang.Integer
	 */
	public Integer getRuleId() {
		return this.ruleId;
	}
	
	/**
	 * 设置使用的评分规则  aps_rule.id
	 * @param ruleId
	 * @type java.lang.Integer
	 */
	public void setRuleId(Integer ruleId) {
		this.ruleId = ruleId;
	}
	
	/**
	 * 获取本次评价所在学校  school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置本次评价所在学校  school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取评价目标对象类型  pj.team=班级  pj.student=学生
	 * @return java.lang.String
	 */
	public String getObjectType() {
		return this.objectType;
	}
	
	/**
	 * 设置评价目标对象类型  pj.team=班级  pj.student=学生
	 * @param objectType
	 * @type java.lang.String
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	/**
	 * 获取名称（可空）
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置名称（可空）
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 获取学年代码  school_year.year
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年代码  school_year.year
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取学期代码  school_term.code
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置学期代码  school_term.code
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
	/**
	 * 获取评价开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * 设置评价开始时间
	 * @param startDate
	 * @type java.util.Date
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 获取评价结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getFinishDate() {
		return this.finishDate;
	}
	
	/**
	 * 设置评价结束时间
	 * @param finishDate
	 * @type java.util.Date
	 */
	public void setFinishDate(java.util.Date finishDate) {
		this.finishDate = finishDate;
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