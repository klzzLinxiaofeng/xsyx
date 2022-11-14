package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsMedal
 * @author AutoCreate
 *
 */
public class ApsMedal implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 适用评价对象类型 pj.team=班级 pj.student=学生
	 */
	private String objectType;
	/**
	 * 适用学校 school.id
	 */
	private Integer schoolId;
	/**
	 * 适用年级  jc_grade.code
	 */
	private String gradeCode;
	/**
	 * 名称 
	 */
	private String name;
	/**
	 * 达标分数（可以获得本奖项的最低得分）
	 */
	private Float reachScore;
	/**
	 * 入围次数（可以获得本奖项的数目限制）
	 */
	private Integer reachCount;
	/**
	 * 评比标准  1：分数  2：名次
	 */
	private String judgeCriterion;
	/**
	 * 评比周期 week=周  month=月  term=学期   year=学年
	 */
	private String runPeriod;
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
	
	public ApsMedal() {
		
	}
	
	public ApsMedal(Integer id) {
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
	 * 获取适用评价对象类型 pj.team=班级 pj.student=学生
	 * @return java.lang.String
	 */
	public String getObjectType() {
		return this.objectType;
	}
	
	/**
	 * 设置适用评价对象类型 pj.team=班级 pj.student=学生
	 * @param objectType
	 * @type java.lang.String
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}
	
	/**
	 * 获取适用学校 school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置适用学校 school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取适用年级  jc_grade.code
	 * @return java.lang.String
	 */
	public String getGradeCode() {
		return this.gradeCode;
	}
	
	/**
	 * 设置适用年级  jc_grade.code
	 * @param gradeCode
	 * @type java.lang.String
	 */
	public void setGradeCode(String gradeCode) {
		this.gradeCode = gradeCode;
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
	 * 获取达标分数（可以获得本奖项的最低得分）
	 * @return java.lang.Float
	 */
	public Float getReachScore() {
		return this.reachScore;
	}
	
	/**
	 * 设置达标分数（可以获得本奖项的最低得分）
	 * @param reachScore
	 * @type java.lang.Float
	 */
	public void setReachScore(Float reachScore) {
		this.reachScore = reachScore;
	}
	
	/**
	 * 获取入围次数（可以获得本奖项的数目限制）
	 * @return java.lang.Integer
	 */
	public Integer getReachCount() {
		return this.reachCount;
	}
	
	/**
	 * 设置入围次数（可以获得本奖项的数目限制）
	 * @param reachCount
	 * @type java.lang.Integer
	 */
	public void setReachCount(Integer reachCount) {
		this.reachCount = reachCount;
	}
	
	/**
	 * 获取评比周期 week=周  month=月  term=学期   year=学年
	 * @return java.lang.String
	 */
	public String getRunPeriod() {
		return this.runPeriod;
	}
	
	/**
	 * 设置评比周期 week=周  month=月  term=学期   year=学年
	 * @param runPeriod
	 * @type java.lang.String
	 */
	public void setRunPeriod(String runPeriod) {
		this.runPeriod = runPeriod;
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

	public String getJudgeCriterion() {
		return judgeCriterion;
	}

	public void setJudgeCriterion(String judgeCriterion) {
		this.judgeCriterion = judgeCriterion;
	}
	
}