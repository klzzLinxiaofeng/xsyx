package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * TaskTeam
 * @author AutoCreate
 *
 */
public class TaskTeam implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 任务id
	 */
	private Integer taskId;
	/**
	 * 班级id
	 */
	private Integer teamId;
	/**
	 * 统计记录表id
	 */
	private Integer pjExamId;
	/**
	 * 是否互评
	 */
	private Boolean isInterscoring;
	/**
	 * 互评开始时间
	 */
	private java.util.Date interscoreStartTime;
	/**
	 * 互评结束时间
	 */
	private java.util.Date interscoreFinishTime;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 删除标志
	 */
	private Boolean isDeleted;
	
	public TaskTeam() {
		
	}
	
	public TaskTeam(Integer id) {
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
	 * 获取任务id
	 * @return java.lang.Integer
	 */
	public Integer getTaskId() {
		return this.taskId;
	}
	
	/**
	 * 设置任务id
	 * @param taskId
	 * @type java.lang.Integer
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
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
	 * 获取统计记录表id
	 * @return java.lang.Integer
	 */
	public Integer getPjExamId() {
		return this.pjExamId;
	}
	
	/**
	 * 设置统计记录表id
	 * @param pjExamId
	 * @type java.lang.Integer
	 */
	public void setPjExamId(Integer pjExamId) {
		this.pjExamId = pjExamId;
	}
	
	/**
	 * 获取是否互评
	 * @return java.lang.Boolean
	 */
	public Boolean getIsInterscoring() {
		return this.isInterscoring;
	}
	
	/**
	 * 设置是否互评
	 * @param isInterscoring
	 * @type java.lang.Boolean
	 */
	public void setIsInterscoring(Boolean isInterscoring) {
		this.isInterscoring = isInterscoring;
	}
	
	/**
	 * 获取互评开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getInterscoreStartTime() {
		return this.interscoreStartTime;
	}
	
	/**
	 * 设置互评开始时间
	 * @param interscoreStartTime
	 * @type java.util.Date
	 */
	public void setInterscoreStartTime(java.util.Date interscoreStartTime) {
		this.interscoreStartTime = interscoreStartTime;
	}
	
	/**
	 * 获取互评结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getInterscoreFinishTime() {
		return this.interscoreFinishTime;
	}
	
	/**
	 * 设置互评结束时间
	 * @param interscoreFinishTime
	 * @type java.util.Date
	 */
	public void setInterscoreFinishTime(java.util.Date interscoreFinishTime) {
		this.interscoreFinishTime = interscoreFinishTime;
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
	
}