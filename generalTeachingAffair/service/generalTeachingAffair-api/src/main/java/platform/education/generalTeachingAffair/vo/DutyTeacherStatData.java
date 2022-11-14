package platform.education.generalTeachingAffair.vo;

import platform.education.generalTeachingAffair.model.Teacher;

public class DutyTeacherStatData implements Comparable {
	
	//private static final long serialVersionUID = 1L;
	
	private Integer teacherId;
	
	private Integer userId;
	
	private String userName;
	
	private String name;
	
	private String sex;
	
	private Integer gradeId;
	
	private String gradeName;
	
	private Integer dutyDayCount;
	
	private Integer finishedDayCount;

	public Integer getDutyDayCount() {
		return dutyDayCount;
	}

	public void setDutyDayCount(Integer dutyDayCount) {
		this.dutyDayCount = dutyDayCount;
	}

	public Integer getFinishedDayCount() {
		return finishedDayCount;
	}

	public void setFinishedDayCount(Integer finishedDayCount) {
		this.finishedDayCount = finishedDayCount;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Integer getGradeId() {
		return gradeId;
	}

	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@Override
	public int compareTo(Object o) {
		DutyTeacherStatData data = (DutyTeacherStatData) o;
		int n = finishedDayCount.compareTo(data.getFinishedDayCount());
		return (n != 0 ? -n : -(dutyDayCount.compareTo(data.getDutyDayCount())));
	}
	
	
}
