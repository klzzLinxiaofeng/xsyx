package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsMedalWinner
 * @author AutoCreate
 *
 */
public class ApsMedalWinner implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 对应的奖项
	 */
	private Integer medalId;
	/**
	 * 评比的学校  school.id
	 */
	private Integer schoolId;
	/**
	 * 评比的年级 grade.id
	 */
	private Integer gradeId;
	/**
	 * 评比的学年  school_year.year
	 */
	private String schoolYear;
	/**
	 * 评比的学期  school_term.code
	 */
	private String termCode;
	/**
	 * 评比阶段次序。如月份、周次等
	 */
	private String periodCode;
	/**
	 * 考核开始日期
	 */
	private java.util.Date startDate;
	/**
	 * 考核结束日期
	 */
	private java.util.Date finishDate;
	/**
	 * 获奖者的类型  pj.team=班级  pj.student=学生
	 */
	private String objectType;
	/**
	 * 获奖者的记录ID  team.id  student.id
	 */
	private Integer objectId;
	/**
	 * 获奖者名称
	 */
	private String name;
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
	
	public ApsMedalWinner() {
		
	}
	
	public ApsMedalWinner(Integer id) {
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
	 * 获取对应的奖项
	 * @return java.lang.Integer
	 */
	public Integer getMedalId() {
		return this.medalId;
	}
	
	/**
	 * 设置对应的奖项
	 * @param medalId
	 * @type java.lang.Integer
	 */
	public void setMedalId(Integer medalId) {
		this.medalId = medalId;
	}
	
	/**
	 * 获取评比的学校  school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置评比的学校  school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取评比的年级 grade.id
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置评比的年级 grade.id
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取评比的学年  school_year.year
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置评比的学年  school_year.year
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取评比的学期  school_term.code
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置评比的学期  school_term.code
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
	/**
	 * 获取评比阶段次序。如月份、周次等
	 * @return java.lang.String
	 */
	public String getPeriodCode() {
		return this.periodCode;
	}
	
	/**
	 * 设置评比阶段次序。如月份、周次等
	 * @param periodId
	 * @type java.lang.String
	 */
	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}
	
	/**
	 * 获取考核开始日期
	 * @return java.util.Date
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * 设置考核开始日期
	 * @param startDate
	 * @type java.util.Date
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 获取考核结束日期
	 * @return java.util.Date
	 */
	public java.util.Date getFinishDate() {
		return this.finishDate;
	}
	
	/**
	 * 设置考核结束日期
	 * @param finishDate
	 * @type java.util.Date
	 */
	public void setFinishDate(java.util.Date finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * 获取获奖者的类型  pj.team=班级  pj.student=学生
	 * @return java.lang.String
	 */
	public String getObjectType() {
		return this.objectType;
	}
	
	/**
	 * 设置获奖者的类型  pj.team=班级  pj.student=学生
	 * @param objectType
	 * @type java.lang.String
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	/**
	 * 获取获奖者的记录ID  team.id  student.id
	 * @return java.lang.Integer
	 */
	public Integer getObjectId() {
		return this.objectId;
	}
	
	/**
	 * 设置获奖者的记录ID  team.id  student.id
	 * @param objectId
	 * @type java.lang.Integer
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	
	/**
	 * 获取获奖者名称
	 * @return java.lang.String
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * 设置获奖者名称
	 * @param name
	 * @type java.lang.String
	 */
	public void setName(String name) {
		this.name = name;
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