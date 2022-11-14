package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsStuSummary
 * @author AutoCreate
 *
 */
public class ApsStuSummary implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 对应的评价任务 aps_task_id
	 */
	private Integer taskId;
	/**
	 * 对应的奖项  aps_medal.id
	 */
	private Integer medalId;
	/**
	 * 适用学校  
	 */
	private Integer schoolId;
	/**
	 * 对应的学期
	 */
	private String termCode;
	/**
	 * 对应的年级
	 */
	private Integer gradeId;
	/**
	 * 对应的班级
	 */
	private Integer teamId;
	/**
	 * 对应的班级名
	 */
	private String teamName;
	/**
	 * 获奖学生id
	 */
	private Integer studentId;
	/**
	 * 对应的学生名
	 */
	private String studentName;
	/**
	 * 评价的阶段  月份（M01 M02） 学期（T01 T02)
	 */
	private String periodCode;
	/**
	 * 排名
	 */
	private Integer rank;
	/**
	 * 总卡数
	 */
	private Integer totalScore;
	/**
	 * 激励卡数
	 */
	private Integer addScore;
	/**
	 * 课堂优化卡数
	 */
	private Integer deductScore;
	/**
	 * 发展卡数
	 */
	private Integer normalScore;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	
	public ApsStuSummary() {
		
	}
	
	public ApsStuSummary(Integer id) {
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
	 * 获取对应的评价任务 aps_task_id
	 * @return java.lang.Integer
	 */
	public Integer getTaskId() {
		return this.taskId;
	}
	
	/**
	 * 设置对应的评价任务 aps_task_id
	 * @param taskId
	 * @type java.lang.Integer
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 获取对应的奖项  aps_medal.id
	 * @return java.lang.Integer
	 */
	public Integer getMedalId() {
		return this.medalId;
	}
	
	/**
	 * 设置对应的奖项  aps_medal.id
	 * @param medalId
	 * @type java.lang.Integer
	 */
	public void setMedalId(Integer medalId) {
		this.medalId = medalId;
	}
	
	/**
	 * 获取适用学校  
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置适用学校  
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取对应的学期
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置对应的学期
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
	/**
	 * 获取对应的年级
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置对应的年级
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取对应的班级
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置对应的班级
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取对应的班级名
	 * @return java.lang.String
	 */
	public String getTeamName() {
		return this.teamName;
	}
	
	/**
	 * 设置对应的班级名
	 * @param teamName
	 * @type java.lang.String
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	
	/**
	 * 获取获奖学生id
	 * @return java.lang.Integer
	 */
	public Integer getStudentId() {
		return this.studentId;
	}
	
	/**
	 * 设置获奖学生id
	 * @param studentId
	 * @type java.lang.Integer
	 */
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * 获取对应的学生名
	 * @return java.lang.String
	 */
	public String getStudentName() {
		return this.studentName;
	}
	
	/**
	 * 设置对应的学生名
	 * @param studentName
	 * @type java.lang.String
	 */
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	/**
	 * 获取评价的阶段  月份（M01 M02） 学期（T01 T02)
	 * @return java.lang.String
	 */
	public String getPeriodCode() {
		return this.periodCode;
	}
	
	/**
	 * 设置评价的阶段  月份（M01 M02） 学期（T01 T02)
	 * @param periodCode
	 * @type java.lang.String
	 */
	public void setPeriodCode(String periodCode) {
		this.periodCode = periodCode;
	}
	
	/**
	 * 获取排名
	 * @return java.lang.Integer
	 */
	public Integer getRank() {
		return this.rank;
	}
	
	/**
	 * 设置排名
	 * @param rank
	 * @type java.lang.Integer
	 */
	public void setRank(Integer rank) {
		this.rank = rank;
	}
	
	/**
	 * 获取总卡数
	 * @return java.lang.Integer
	 */
	public Integer getTotalScore() {
		return this.totalScore;
	}
	
	/**
	 * 设置总卡数
	 * @param totalScore
	 * @type java.lang.Integer
	 */
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	
	/**
	 * 获取激励卡数
	 * @return java.lang.Integer
	 */
	public Integer getAddScore() {
		return this.addScore;
	}
	
	/**
	 * 设置激励卡数
	 * @param addScore
	 * @type java.lang.Integer
	 */
	public void setAddScore(Integer addScore) {
		this.addScore = addScore;
	}
	
	/**
	 * 获取课堂优化卡数
	 * @return java.lang.Integer
	 */
	public Integer getDeductScore() {
		return this.deductScore;
	}
	
	/**
	 * 设置课堂优化卡数
	 * @param deductScore
	 * @type java.lang.Integer
	 */
	public void setDeductScore(Integer deductScore) {
		this.deductScore = deductScore;
	}
	
	/**
	 * 获取发展卡数
	 * @return java.lang.Integer
	 */
	public Integer getNormalScore() {
		return this.normalScore;
	}
	
	/**
	 * 设置发展卡数
	 * @param normalScore
	 * @type java.lang.Integer
	 */
	public void setNormalScore(Integer normalScore) {
		this.normalScore = normalScore;
	}
	
	/**
	 * 获取备注
	 * @return java.lang.String
	 */
	public String getRemark() {
		return this.remark;
	}
	
	/**
	 * 设置备注
	 * @param remark
	 * @type java.lang.String
	 */
	public void setRemark(String remark) {
		this.remark = remark;
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
	 * 获取修改时间
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置修改时间
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
}