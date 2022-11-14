package platform.education.oa.vo;
import platform.education.oa.model.Meeting;
/**
 * Meeting
 * @author AutoCreate
 *
 */
public class MeetingCondition extends Meeting {
	private static final long serialVersionUID = 1L;
	private Boolean isRelatedWithMe;
	private Boolean isDepartmentRecord;
	private Boolean isMePublish;
	private Boolean isAllRecord;
	private String ssWord;
	private Integer teacherId;
	private Integer userId;
	private Integer departmentId;
	//=====baselineDate 手机端使用====
	private String baselineDate;
	private String new_or_old;
	
	public String getNew_or_old() {
		return new_or_old;
	}
	public void setNew_or_old(String new_or_old) {
		this.new_or_old = new_or_old;
	}
	public String getBaselineDate() {
		return baselineDate;
	}
	public void setBaselineDate(String baselineDate) {
		this.baselineDate = baselineDate;
	}
	//=========================
	public Integer getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}
	public String getSsWord() {
		return ssWord;
	}
	public void setSsWord(String ssWord) {
		this.ssWord = ssWord;
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
	public Boolean getIsAllRecord() {
		return isAllRecord;
	}
	public void setIsAllRecord(Boolean isAllRecord) {
		this.isAllRecord = isAllRecord;
	}
}