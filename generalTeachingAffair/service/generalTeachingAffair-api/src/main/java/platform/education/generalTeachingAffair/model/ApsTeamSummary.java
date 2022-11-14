package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsTeamSummary
 * @author AutoCreate
 *
 */
public class ApsTeamSummary implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 对应的评价任务  aps_task.id
	 */
	private Integer taskId;
	/**
	 * 学期代码
	 */
	private String termCode;
	/**
	 * 对应的奖项  aps_medal.id
	 */
	private Integer medalId;
	/**
	 * 适用学校   school.id
	 */
	private Integer schoolId;
	/**
	 * 评价班级  team.id
	 */
	private Integer teamId;
	/**
	 * 阶段次序。周期评价阶段的标识，如周次（W01 W02）  月份（M01 M02） 学期（T+term_code）
	 */
	private String periodCode;
	/**
	 * 排名
	 */
	private Integer rank;
	/**
	 * 常规分数
	 */
	private Float normalScore;
	/**
	 * 加分分数
	 */
	private Float addScore;
	/**
	 * 减分分数
	 */
	private Float deductScore;
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
	 * 最后修改时间
	 */
	private java.util.Date modifyDate;
	
	public ApsTeamSummary() {
		
	}
	
	public ApsTeamSummary(Integer id) {
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
	 * 获取对应的评价任务  aps_task.id
	 * @return java.lang.Integer
	 */
	public Integer getTaskId() {
		return this.taskId;
	}
	
	/**
	 * 设置对应的评价任务  aps_task.id
	 * @param taskId
	 * @type java.lang.Integer
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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
	 * 获取适用学校   school.id
	 * @return java.lang.Integer
	 */
	public Integer getSchoolId() {
		return this.schoolId;
	}
	
	/**
	 * 设置适用学校   school.id
	 * @param schoolId
	 * @type java.lang.Integer
	 */
	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}
	
	/**
	 * 获取评价班级  team.id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置评价班级  team.id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取阶段次序。周期评价阶段的标识，如周次（W01 W02）  月份（M01 M02） 学期（T+term_code）
	 * @return java.lang.String
	 */
	public String getPeriodCode() {
		return this.periodCode;
	}
	
	/**
	 * 设置阶段次序。周期评价阶段的标识，如周次（W01 W02）  月份（M01 M02） 学期（T+term_code）
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
	 * 获取常规分数
	 * @return java.lang.Float
	 */
	public Float getNormalScore() {
		return this.normalScore;
	}
	
	/**
	 * 设置常规分数
	 * @param normalScore
	 * @type java.lang.Float
	 */
	public void setNormalScore(Float normalScore) {
		this.normalScore = normalScore;
	}
	
	/**
	 * 获取加分分数
	 * @return java.lang.Float
	 */
	public Float getAddScore() {
		return this.addScore;
	}
	
	/**
	 * 设置加分分数
	 * @param addScore
	 * @type java.lang.Float
	 */
	public void setAddScore(Float addScore) {
		this.addScore = addScore;
	}
	
	/**
	 * 获取减分分数
	 * @return java.lang.Float
	 */
	public Float getDeductScore() {
		return this.deductScore;
	}
	
	/**
	 * 设置减分分数
	 * @param deductScore
	 * @type java.lang.Float
	 */
	public void setDeductScore(Float deductScore) {
		this.deductScore = deductScore;
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