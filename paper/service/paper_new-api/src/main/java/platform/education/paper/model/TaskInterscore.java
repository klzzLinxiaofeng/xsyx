package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * TaskInterscore
 * @author AutoCreate
 *
 */
public class TaskInterscore implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 测试任务-taskID
	 */
	private Integer taskId;
	/**
	 * 测试任务执行的班级 pj_team.id
	 */
	private Integer teamId;
	/**
	 * 试卷ID 冗余字段
	 */
	private Integer paperId;
	/**
	 * 评卷人 user.id
	 */
	private Integer scoringUserId;
	/**
	 * 被评卷人 user.id
	 */
	private Integer scoredUserId;
	/**
	 * 被评卷人答卷记录 pa_user_paper.id
	 */
	private Integer scoredPaperId;
	/**
	 * 记录删除标志
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
	/**
	 * 评卷提交时间
	 */
	private java.util.Date scoringTime;
	
	public TaskInterscore() {
		
	}
	
	public TaskInterscore(Integer id) {
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
	 * 获取测试任务-taskID
	 * @return java.lang.Integer
	 */
	public Integer getTaskId() {
		return this.taskId;
	}
	
	/**
	 * 设置测试任务-taskID
	 * @param taskId
	 * @type java.lang.Integer
	 */
	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}
	
	/**
	 * 获取测试任务执行的班级 pj_team.id
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置测试任务执行的班级 pj_team.id
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取试卷ID 冗余字段
	 * @return java.lang.Integer
	 */
	public Integer getPaperId() {
		return this.paperId;
	}
	
	/**
	 * 设置试卷ID 冗余字段
	 * @param paperId
	 * @type java.lang.Integer
	 */
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * 获取评卷人 user.id
	 * @return java.lang.Integer
	 */
	public Integer getScoringUserId() {
		return this.scoringUserId;
	}
	
	/**
	 * 设置评卷人 user.id
	 * @param scoringUserId
	 * @type java.lang.Integer
	 */
	public void setScoringUserId(Integer scoringUserId) {
		this.scoringUserId = scoringUserId;
	}
	
	/**
	 * 获取被评卷人 user.id
	 * @return java.lang.Integer
	 */
	public Integer getScoredUserId() {
		return this.scoredUserId;
	}
	
	/**
	 * 设置被评卷人 user.id
	 * @param scoredUserId
	 * @type java.lang.Integer
	 */
	public void setScoredUserId(Integer scoredUserId) {
		this.scoredUserId = scoredUserId;
	}
	
	/**
	 * 获取被评卷人答卷记录 pa_user_paper.id
	 * @return java.lang.Integer
	 */
	public Integer getScoredPaperId() {
		return this.scoredPaperId;
	}
	
	/**
	 * 设置被评卷人答卷记录 pa_user_paper.id
	 * @param scoredPaperId
	 * @type java.lang.Integer
	 */
	public void setScoredPaperId(Integer scoredPaperId) {
		this.scoredPaperId = scoredPaperId;
	}
	
	/**
	 * 获取记录删除标志
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置记录删除标志
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
	
	/**
	 * 获取评卷提交时间
	 * @return java.util.Date
	 */
	public java.util.Date getScoringTime() {
		return this.scoringTime;
	}
	
	/**
	 * 设置评卷提交时间
	 * @param scoringTime
	 * @type java.util.Date
	 */
	public void setScoringTime(java.util.Date scoringTime) {
		this.scoringTime = scoringTime;
	}
	
}