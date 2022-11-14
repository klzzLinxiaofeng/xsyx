package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * PaUserPaper
 * @author AutoCreate
 *
 */
public class PaUserPaper implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id;
	/**
	 * 发布练习
	 */
	private String paperUuid;
	/**
	 * 这个userid指的是使用者的id或者有使用权的id
	 */
	private Integer userId;
	/**
	 * 完成时间
	 */
	private java.util.Date finishedTime;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 卷面得分
	 */
	private Double score;
	/**
	 * 记录创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 题目类型：4.导学案 1.作业 2.考试 3.练习
	 */
	private Integer type;
	/**
	 * 任务id
	 */
	private Integer ownerId;
	/**
	 * teamId
	 */
	private Integer teamId;
	/**
	 * 整卷评分结束
	 */
	private Boolean scoreFinished;
	/**
	 * 最后评卷时间
	 */
	private java.util.Date scoreTime;
	/**
	 * 当type 为4时导学案下面试卷单元id，为了统计区分单元
	 */
	private Integer objectId;
	
	public PaUserPaper() {
		
	}
	
	public PaUserPaper(Integer id) {
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
	 * 获取发布练习
	 * @return java.lang.String
	 */
	public String getPaperUuid() {
		return this.paperUuid;
	}
	
	/**
	 * 设置发布练习
	 * @param paperUuid
	 * @type java.lang.String
	 */
	public void setPaperUuid(String paperUuid) {
		this.paperUuid = paperUuid;
	}
	
	/**
	 * 获取这个userid指的是使用者的id或者有使用权的id
	 * @return java.lang.Integer
	 */
	public Integer getUserId() {
		return this.userId;
	}
	
	/**
	 * 设置这个userid指的是使用者的id或者有使用权的id
	 * @param userId
	 * @type java.lang.Integer
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	/**
	 * 获取完成时间
	 * @return java.util.Date
	 */
	public java.util.Date getFinishedTime() {
		return this.finishedTime;
	}
	
	/**
	 * 设置完成时间
	 * @param finishedTime
	 * @type java.util.Date
	 */
	public void setFinishedTime(java.util.Date finishedTime) {
		this.finishedTime = finishedTime;
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
	 * 获取卷面得分
	 * @return java.lang.Double
	 */
	public Double getScore() {
		return this.score;
	}
	
	/**
	 * 设置卷面得分
	 * @param score
	 * @type java.lang.Double
	 */
	public void setScore(Double score) {
		this.score = score;
	}
	
	/**
	 * 获取记录创建时间
	 * @return java.util.Date
	 */
	public java.util.Date getCreateDate() {
		return this.createDate;
	}
	
	/**
	 * 设置记录创建时间
	 * @param createDate
	 * @type java.util.Date
	 */
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	
	/**
	 * 获取题目类型：4.导学案 1.作业 2.考试 3.练习
	 * @return Integer
	 */
	public Integer getType() {
		return this.type;
	}
	
	/**
	 * 设置题目类型：4.导学案 1.作业 2.考试 3.练习
	 * @param type
	 * @type Integer
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	
	/**
	 * 获取任务id
	 * @return java.lang.Integer
	 */
	public Integer getOwnerId() {
		return this.ownerId;
	}
	
	/**
	 * 设置任务id
	 * @param ownerId
	 * @type java.lang.Integer
	 */
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	/**
	 * 获取teamId
	 * @return java.lang.Integer
	 */
	public Integer getTeamId() {
		return this.teamId;
	}
	
	/**
	 * 设置teamId
	 * @param teamId
	 * @type java.lang.Integer
	 */
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	
	/**
	 * 获取整卷评分结束
	 * @return java.lang.Boolean
	 */
	public Boolean getScoreFinished() {
		return this.scoreFinished;
	}
	
	/**
	 * 设置整卷评分结束
	 * @param scoreFinished
	 * @type java.lang.Boolean
	 */
	public void setScoreFinished(Boolean scoreFinished) {
		this.scoreFinished = scoreFinished;
	}
	
	/**
	 * 获取最后评卷时间
	 * @return java.util.Date
	 */
	public java.util.Date getScoreTime() {
		return this.scoreTime;
	}
	
	/**
	 * 设置最后评卷时间
	 * @param scoreTime
	 * @type java.util.Date
	 */
	public void setScoreTime(java.util.Date scoreTime) {
		this.scoreTime = scoreTime;
	}
	
	/**
	 * 获取当type 为4时导学案下面试卷单元id，为了统计区分单元
	 * @return java.lang.Integer
	 */
	public Integer getObjectId() {
		return this.objectId;
	}
	
	/**
	 * 设置当type 为4时导学案下面试卷单元id，为了统计区分单元
	 * @param objectId
	 * @type java.lang.Integer
	 */
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	
}