package platform.education.generalTeachingAffair.model;

import framework.generic.dao.Model;
/**
 * ApsTaskJudge
 * @author AutoCreate
 *
 */
public class ApsTaskJudge implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 适用的评价任务 aps_task.id
	 */
	private Integer taskId;
	/**
	 * 学期代码
	 */
	private String termCode;
	/**
	 * 年级Id
	 */
	private Integer gradeId;
	/**
	 * 评价人教师ID teacher.id
	 */
	private Integer teacherId;
	/**
	 * 评价人用户ID yh_user.id
	 */
	private Integer userId;
	/**
	 * 值日时间
	 */
	private java.util.Date onDutyDate;
	/**
	 * 值日时间对应的星期
	 */
	private String dayOfWeek;
	/**
	 * 值日时间对应的周次
	 */
	private String week;
	/**
	 * 标识是否有值日
	 */
	private Boolean isFinished;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	/**
	 * 创建时间（执行评价时间）
	 */
	private java.util.Date createDate;
	/**
	 * 最后修改时间
	 */
	private java.util.Date modifyDate;
	
	public ApsTaskJudge() {
		
	}
	
	public ApsTaskJudge(Integer id) {
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
	 * 获取适用的评价任务 aps_task.id
	 * @return java.lang.Integer
	 */
	public Integer getTaskId() {
		return this.taskId;
	}
	
	/**
	 * 设置适用的评价任务 aps_task.id
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
	 * 获取年级Id
	 * @return java.lang.Integer
	 */
	public Integer getGradeId() {
		return this.gradeId;
	}
	
	/**
	 * 设置年级Id
	 * @param gradeId
	 * @type java.lang.Integer
	 */
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}
	
	/**
	 * 获取评价人教师ID teacher.id
	 * @return java.lang.Integer
	 */
	public Integer getTeacherId() {
		return this.teacherId;
	}
	
	/**
	 * 设置评价人教师ID teacher.id
	 * @param teacherId
	 * @type java.lang.Integer
	 */
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	
	/**
	 * 获取评价人用户ID yh_user.id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置评价人用户ID yh_user.id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取值日时间
	 * @return java.util.Date
	 */
	public java.util.Date getOnDutyDate() {
		return this.onDutyDate;
	}
	
	/**
	 * 设置值日时间
	 * @param onDutyDate
	 * @type java.util.Date
	 */
	public void setOnDutyDate(java.util.Date onDutyDate) {
		this.onDutyDate = onDutyDate;
	}
	
	/**
	 * 获取值日时间对应的星期
	 * @return java.lang.String
	 */
	public String getDayOfWeek() {
		return this.dayOfWeek;
	}
	
	/**
	 * 设置值日时间对应的星期
	 * @param dayOfWeek
	 * @type java.lang.String
	 */
	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}
	
	/**
	 * 获取值日时间对应的周次
	 * @return java.lang.String
	 */
	public String getWeek() {
		return this.week;
	}
	
	/**
	 * 设置值日时间对应的周次
	 * @param week
	 * @type java.lang.String
	 */
	public void setWeek(String week) {
		this.week = week;
	}
	
	/**
	 * 获取标识是否有值日
	 * @return java.lang.Boolean
	 */
	public Boolean getIsFinished() {
		return this.isFinished;
	}
	
	/**
	 * 设置标识是否有值日
	 * @param isFinished
	 * @type java.lang.Boolean
	 */
	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
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
	 * 获取创建时间（执行评价时间）
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置创建时间（执行评价时间）
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