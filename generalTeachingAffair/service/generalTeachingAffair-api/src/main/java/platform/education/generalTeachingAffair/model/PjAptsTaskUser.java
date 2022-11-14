package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * PjAptsTaskUser
 * @author AutoCreate
 *
 */
public class PjAptsTaskUser implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 学校id
	 */
	private Integer schoolId;
	/**
	 * 学年代码
	 */
	private String schoolYear;
	/**
	 * 学期代码
	 */
	private String termCode;
	/**
	 * 任务开始时间
	 */
	private java.util.Date startDate;
	/**
	 * 任务结束时间
	 */
	private java.util.Date finishDate;
	/**
	 * 年级id
	 */
	private Integer gradeId;
	/**
	 * 班级id
	 */
	private Integer teamId;
	/**
	 * team_teacher_id
	 */
	private Integer teacherId;
	/**
	 * 任务id
	 */
	private Integer aptsTaskId;
	/**
	 * 总人数
	 */
	private Integer totalUserCount;
	/**
	 * 已评价人数
	 */
	private Integer scoredUserCount;
	/**
	 * 评价总分
	 */
	private Double score;
	/**
	 * period
	 */
	private Integer period;
	/**
	 * evType
	 */
	private Integer evType;
	/**
	 * scoringType
	 */
	private Integer scoringType;
	/**
	 * validPercent
	 */
	private Double validPercent;
	/**
	 * isValid
	 */
	private Boolean isValid;
	/**
	 * createDate
	 */
	private java.util.Date createDate;
	/**
	 * modifyDate
	 */
	private java.util.Date modifyDate;
	/**
	 * isDelete
	 */
	private Boolean isDelete;
	
	public PjAptsTaskUser() {
		
	}
	
	public PjAptsTaskUser(Integer id) {
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
	 * 获取学校id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置学校id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取学年代码
	 * @return java.lang.String
	 */
	public String getSchoolYear() {
		return this.schoolYear;
	}
	
	/**
	 * 设置学年代码
	 * @param schoolYear
	 * @type java.lang.String
	 */
	public void setSchoolYear(String schoolYear) {
		this.schoolYear = schoolYear;
	}
	
	/**
	 * 获取学期代码
	 * @return java.lang.String
	 */
	public String getTermCode() {
		return this.termCode;
	}
	
	/**
	 * 设置学期代码
	 * @param termCode
	 * @type java.lang.String
	 */
	public void setTermCode(String termCode) {
		this.termCode = termCode;
	}
	
	/**
	 * 获取任务开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getStartDate() {
		return this.startDate;
	}
	
	/**
	 * 设置任务开始时间
	 * @param startDate
	 * @type java.util.Date
	 */
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
	
	/**
	 * 获取任务结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getFinishDate() {
		return this.finishDate;
	}
	
	/**
	 * 设置任务结束时间
	 * @param finishDate
	 * @type java.util.Date
	 */
	public void setFinishDate(java.util.Date finishDate) {
		this.finishDate = finishDate;
	}
	
	/**
	 * 获取年级id
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置年级id
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取班级id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置班级id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取team_teacher_id
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置team_teacher_id
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取任务id
	 * @return java.lang.Integer
	 */
	public Integer getAptsTaskId() {
		return this.aptsTaskId;
	}
	
	/**
	 * 设置任务id
	 * @param aptsTaskId
	 * @type java.lang.Integer
	 */
	public void setAptsTaskId(Integer aptsTaskId) {
		this.aptsTaskId = aptsTaskId;
	}
	
	/**
	 * 获取总人数
	 * @return java.lang.Integer
	 */
	public Integer getTotalUserCount() {
		return this.totalUserCount;
	}
	
	/**
	 * 设置总人数
	 * @param totalUserCount
	 * @type java.lang.Integer
	 */
	public void setTotalUserCount(Integer totalUserCount) {
		this.totalUserCount = totalUserCount;
	}
	
	/**
	 * 获取已评价人数
	 * @return java.lang.Integer
	 */
	public Integer getScoredUserCount() {
		return this.scoredUserCount;
	}
	
	/**
	 * 设置已评价人数
	 * @param scoredUserCount
	 * @type java.lang.Integer
	 */
	public void setScoredUserCount(Integer scoredUserCount) {
		this.scoredUserCount = scoredUserCount;
	}
	
	/**
	 * 获取评价总分
	 * @return java.lang.Integer
	 */
	public Double getScore() {
		return this.score;
	}
	
	/**
	 * 设置评价总分
	 * @param score
	 * @type java.lang.Integer
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取period
	 * @return java.lang.Integer
	 */
	public Integer getPeriod() {
		return this.period;
	}
	
	/**
	 * 设置period
	 * @param period
	 * @type java.lang.Integer
	 */
	public void setPeriod(Integer period) {
		this.period = period;
	}
	
	/**
	 * 获取evType
	 * @return java.lang.Integer
	 */
	public Integer getEvType() {
		return this.evType;
	}
	
	/**
	 * 设置evType
	 * @param evType
	 * @type java.lang.Integer
	 */
	public void setEvType(Integer evType) {
		this.evType = evType;
	}
	
	/**
	 * 获取scoringType
	 * @return java.lang.Integer
	 */
	public Integer getScoringType() {
		return this.scoringType;
	}
	
	/**
	 * 设置scoringType
	 * @param scoringType
	 * @type java.lang.Integer
	 */
	public void setScoringType(Integer scoringType) {
		this.scoringType = scoringType;
	}
	
	/**
	 * 获取validPercent
	 * @return java.lang.Double
	 */
	public Double getValidPercent() {
		return this.validPercent;
	}
	
	/**
	 * 设置validPercent
	 * @param validPercent
	 * @type java.lang.Double
	 */
	public void setValidPercent(Double validPercent) {
		this.validPercent = validPercent;
	}
	
	/**
	 * 获取isValid
	 * @return java.lang.Boolean
	 */
	public Boolean getIsValid() {
		return this.isValid;
	}
	
	/**
	 * 设置isValid
	 * @param isValid
	 * @type java.lang.Boolean
	 */
	public void setIsValid(Boolean isValid) {
		this.isValid = isValid;
	}
	
	/**
	 * 获取createDate
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置createDate
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取modifyDate
	 * @return java.util.Date
	 */
	public java.util.Date getModifyDate() {
		return this.modifyDate;
	}
	
	/**
	 * 设置modifyDate
	 * @param modifyDate
	 * @type java.util.Date
	 */
	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	/**
	 * 获取isDelete
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
	}
	
	/**
	 * 设置isDelete
	 * @param isDelete
	 * @type java.lang.Boolean
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	
}