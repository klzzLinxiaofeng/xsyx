package platform.education.rest.jw.service.vo;

import java.util.List;
import java.util.Map;

import platform.education.learningDesign.model.LpTaskUserActivity;
import platform.education.rest.jw.service.vo.LpTaskUserActivityVo;

/**
 * LpTaskUserActivity
 * 
 * @author AutoCreate
 *
 */
public class LpTaskUserActivityJson{
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
	 * 文件。包含多个文件的uuid字符串
	 */
	private List<String> files;
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
	
	/**
	 * 引用的讨论内容
	 */
	private List<LpTaskUserActivityInfo> quoteList;
	
	private String iocnPath;
	
	private String role;
	
	private String name;

	public List<LpTaskUserActivityInfo> getQuoteList() {
		return quoteList;
	}

	public void setQuoteList(List<LpTaskUserActivityInfo> quoteList) {
		this.quoteList = quoteList;
	}

	public String getIocnPath() {
		return iocnPath;
	}

	public void setIocnPath(String iocnPath) {
		this.iocnPath = iocnPath;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
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

	public Integer getActivityCount() {
		return activityCount;
	}

	public void setActivityCount(Integer activityCount) {
		this.activityCount = activityCount;
	}
}