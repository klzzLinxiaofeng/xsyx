package platform.education.generalTeachingAffair.vo;

import java.util.List;

public class DayInfo {

	private String date;
	
	private String dayOfWeek;
	
	private Integer isCurrent;
	
	private List<JudgeTeacher> teacherList;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public List<JudgeTeacher> getTeacherList() {
		return teacherList;
	}

	public void setTeacherList(List<JudgeTeacher> teacherList) {
		this.teacherList = teacherList;
	}

	public Integer getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Integer isCurrent) {
		this.isCurrent = isCurrent;
	}
	
	
}
