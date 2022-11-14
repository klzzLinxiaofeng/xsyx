package platform.education.generalTeachingAffair.vo;

public class JudgeTeacher {
	
	private Integer userId;
	
	private Integer teacherId;
	
	private String teacherName;
	
	private String onDutyDate;
	
	private String url;
	
	private String sex;
	
	private Integer isChoose;

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

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getOnDutyDate() {
		return onDutyDate;
	}

	public void setOnDutyDate(String onDutyDate) {
		this.onDutyDate = onDutyDate;
	}

	public Integer getIsChoose() {
		return isChoose;
	}

	public void setIsChoose(Integer isChoose) {
		this.isChoose = isChoose;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	
}
