package platform.education.oa.vo;

public class SchoolMember {
   // 记录分校成员名字
	private String  schoolName;
	//分校的id
	private String   schoolId;
	// 记录分校通知数量
	private int noticeCount;
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public int getNoticeCount() {
		return noticeCount;
	}
	public void setNoticeCount(int noticeCount) {
		this.noticeCount = noticeCount;
	}

}
