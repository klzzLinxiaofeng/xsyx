package platform.education.rest.jw.service.vo;

import java.io.Serializable;

public class ClassEvaScoreDataVo implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String checkDate;
	
	private Integer studentId;
	
	private String studentName;
	
	private String userIcon;
	
	private String checkRange;
	
	private String badBehaviours;
	
	private Integer teacherId;
	
	private String teacherIdName;
	
	private String sex;

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getCheckRange() {
		return checkRange;
	}

	public void setCheckRange(String checkRange) {
		this.checkRange = checkRange;
	}

	public String getBadBehaviours() {
		return badBehaviours;
	}

	public void setBadBehaviours(String badBehaviours) {
		this.badBehaviours = badBehaviours;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherIdName() {
		return teacherIdName;
	}

	public void setTeacherIdName(String teacherIdName) {
		this.teacherIdName = teacherIdName;
	}

	public String getUserIcon() {
		return userIcon;
	}

	public void setUserIcon(String userIcon) {
		this.userIcon = userIcon;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
