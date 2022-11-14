package platform.education.oa.vo;

import platform.education.oa.model.Paper;

/**
 * Paper
 * 
 * @author AutoCreate
 *
 */
public class PaperCondition extends Paper {
	private static final long serialVersionUID = 1L;

	/**
	 * 接收者ID
	 */
	private Integer receiverId;

	/**
	 * 教师ID 作用：部门中间表存的教师ID，所以用教师ID作条件进行部门的查询
	 */
	private Integer teacherId;

	/**
	 * 关键字搜索
	 */
	private String searchWord;

	/**
	 * 是否为全部记录
	 */
	private Boolean isAll;

	/**
	 * 发布对象是否为全员
	 */
	private Boolean isQuanyuan;
	/**
	 * 是否与我相关
	 * 
	 * @return
	 */
	private Boolean isRelatedWithMe;

	/**
	 * 是否为部门记录
	 * 
	 * @return
	 */
	private Boolean isDepartmentRecord;

	/**
	 * 是否是我发布的
	 * 
	 * @return
	 */
	private Boolean isMyPublish;

	/**
	 * 接收者部门
	 * 
	 * @return
	 */
	private Integer departmentId;

	public Boolean getIsQuanyuan() {
		return isQuanyuan;
	}

	public void setIsQuanyuan(Boolean isQuanyuan) {
		this.isQuanyuan = isQuanyuan;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Boolean getIsAll() {
		return isAll;
	}

	public void setIsAll(Boolean isAll) {
		this.isAll = isAll;
	}

	public Boolean getIsRelatedWithMe() {
		return isRelatedWithMe;
	}

	public void setIsRelatedWithMe(Boolean isRelatedWithMe) {
		this.isRelatedWithMe = isRelatedWithMe;
	}

	public Boolean getIsDepartmentRecord() {
		return isDepartmentRecord;
	}

	public void setIsDepartmentRecord(Boolean isDepartmentRecord) {
		this.isDepartmentRecord = isDepartmentRecord;
	}

	public Boolean getIsMyPublish() {
		return isMyPublish;
	}

	public void setIsMyPublish(Boolean isMyPublish) {
		this.isMyPublish = isMyPublish;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}
}