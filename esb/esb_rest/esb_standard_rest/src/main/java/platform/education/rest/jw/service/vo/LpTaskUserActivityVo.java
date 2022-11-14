package platform.education.rest.jw.service.vo;

import java.util.List;

import platform.education.learningDesign.model.LpTaskUserActivity;

/**
 * LpTaskUserActivity
 * 
 * @author AutoCreate
 *
 */
public class LpTaskUserActivityVo {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 主键id
	 */
	private Integer id;
	/**
	 * 任务使用的导学案
	 */
	private Integer lpId;
	/**
	 * 对应任务
	 */
	private Integer taskId;
	/**
	 * 对应的任务单元
	 */
	private Integer unitId;
	/**
	 * 用户id
	 */
	private Integer userId;
	/**
	 * 文字内容
	 */
	private String content;
	/**
	 * 用户对内容评等的等级,可根据活动类型自定义
	 */
	private Integer degree;
	/**
	 * 记录创建时间
	 */
	private java.util.Date createDate;
	/**
	 * 记录修改时间
	 */
	private java.util.Date modifyDate;
	/**
	 * 记录是否删除
	 */
	private Boolean isDeleted;
	/**
	 * 引用，JSON格式，存用户所引用的讨论id
	 */
	private String quote;

	/** 一个任务下小结的记录总数 */
	private Integer activityCount;
	
	private String name;
	
	private List<String> files;

	public Integer getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLpId() {
		return lpId;
	}

	public void setLpId(Integer lpId) {
		this.lpId = lpId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getDegree() {
		return degree;
	}

	public void setDegree(Integer degree) {
		this.degree = degree;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public java.util.Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(java.util.Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
}