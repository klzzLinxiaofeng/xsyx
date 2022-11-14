package platform.education.paper.model;

import framework.generic.dao.Model;
/**
 * Task
 * @author AutoCreate
 *
 */
public class Task implements Model<Integer> {
	
	
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
	private Integer id;
	/**
	 * 发布的试卷ID
	 */
	private Integer paperId;
	/**
	 * 发布者userId
	 */
	private Integer publisherId;
	/**
	 * 任务名称
	 */
	private String title;
	/**
	 * 结束时间
	 */
	private java.util.Date finishTime;
	/**
	 * 开始时间
	 */
	private java.util.Date startTime;
	/**
	 * 任务参与总人数
	 */
	private Integer userCount;
	/**
	 * 任务提交人数
	 */
	private Integer finishedCount;
	/**
	 * isCheck
	 */
	private Integer isCheck;
	/**
	 * 创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 是否删除
	 */
	private Boolean isDeleted;
	
	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	private String uuid;
	
	private String publisherName;
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Task() {
		
	}
	
	public Task(Integer id) {
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
	 * 获取发布的试卷ID
	 * @return java.lang.Integer
	 */
	public Integer getPaperId() {
		return this.paperId;
	}
	
	/**
	 * 设置发布的试卷ID
	 * @param paperId
	 * @type java.lang.Integer
	 */
	public void setPaperId(Integer paperId) {
		this.paperId = paperId;
	}
	
	/**
	 * 获取发布者userId
	 * @return java.lang.Integer
	 */
	public Integer getPublisherId() {
		return this.publisherId;
	}
	
	/**
	 * 设置发布者userId
	 * @param publisherId
	 * @type java.lang.Integer
	 */
	public void setPublisherId(Integer publisherId) {
		this.publisherId = publisherId;
	}
	
	/**
	 * 获取任务名称
	 * @return java.lang.String
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * 设置任务名称
	 * @param title
	 * @type java.lang.String
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 获取结束时间
	 * @return java.util.Date
	 */
	public java.util.Date getFinishTime() {
		return this.finishTime;
	}
	
	/**
	 * 设置结束时间
	 * @param finishTime
	 * @type java.util.Date
	 */
	public void setFinishTime(java.util.Date finishTime) {
		this.finishTime = finishTime;
	}
	
	/**
	 * 获取开始时间
	 * @return java.util.Date
	 */
	public java.util.Date getStartTime() {
		return this.startTime;
	}
	
	/**
	 * 设置开始时间
	 * @param startTime
	 * @type java.util.Date
	 */
	public void setStartTime(java.util.Date startTime) {
		this.startTime = startTime;
	}
	
	/**
	 * 获取任务参与总人数
	 * @return java.lang.Integer
	 */
	public Integer getUserCount() {
		return this.userCount;
	}
	
	/**
	 * 设置任务参与总人数
	 * @param userCount
	 * @type java.lang.Integer
	 */
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	
	/**
	 * 获取任务提交人数
	 * @return java.lang.Integer
	 */
	public Integer getFinishedCount() {
		return this.finishedCount;
	}
	
	/**
	 * 设置任务提交人数
	 * @param finishedCount
	 * @type java.lang.Integer
	 */
	public void setFinishedCount(Integer finishedCount) {
		this.finishedCount = finishedCount;
	}
	
	/**
	 * 获取isCheck
	 * @return java.lang.Integer
	 */
	public Integer getIsCheck() {
		return this.isCheck;
	}
	
	/**
	 * 设置isCheck
	 * @param isCheck
	 * @type java.lang.Integer
	 */
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
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
	 * 获取是否删除
	 * @return java.lang.Boolean
	 */
	public Boolean getIsDeleted() {
		return this.isDeleted;
	}
	
	/**
	 * 设置是否删除
	 * @param isDeleted
	 * @type java.lang.Boolean
	 */
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}