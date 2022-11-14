package platform.education.oa.vo;
import platform.education.oa.model.Notice;
/**
 * Notice
 * @author AutoCreate
 *
 */
public class NoticeCondition extends Notice {
	private static final long serialVersionUID = 1L;
	/**
	 * 接收者ID
	 */
	private Integer receiverId;
	/**
	 * 教师ID
	 * 作用：部门中间表存的是教师ID，所以用教师ID作条件进行部门 的查找
	 */
	private Integer teacherId;
	/**
	 * 关键字搜索
	 */
	private String ssWord;
	/**
	 * 是否与我相关
	 */
	private Boolean isRelatedWithMe;
	/**
	 * 是否为部门记录
	 */
	private Boolean isDepartmentRecord;
	/**
	 * 是否是我发表的
	 */
	private Boolean isMePublish;
	/**
	 * baseline_date手机端分页
	 */
	private String baseline_date;
	/**
	 * new_or_old 手机端使用到
	 */
	private String new_or_old;
	/**
	 * 根据用户去查询  用户已删除的数据查不到
	 */
	private Integer userId;
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getBaseline_date() {
		return baseline_date;
	}
	public void setBaseline_date(String baseline_date) {
		this.baseline_date = baseline_date;
	}
	public String getNew_or_old() {
		return new_or_old;
	}
	public void setNew_or_old(String new_or_old) {
		this.new_or_old = new_or_old;
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
	public Boolean getIsMePublish() {
		return isMePublish;
	}
	public void setIsMePublish(Boolean isMePublish) {
		this.isMePublish = isMePublish;
	}
	public String getSsWord() {
		return ssWord;
	}
	public void setSsWord(String ssWord) {
		this.ssWord = ssWord;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public Integer getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(Integer receiverId) {
		this.receiverId = receiverId;
	}
	
}