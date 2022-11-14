package platform.education.rest.bp.service.vo;

import java.io.Serializable;

public class BpTopIndexInfoVo implements Serializable{
 
	private static final long serialVersionUID = 1L;

	private String schoolName;
	
	private String schoolIcon;
	
	private String teamName;
	
	private Long studentNum;
	
	private Long teacherNum;
	
	private String classTeacherName;
	
	private String classTeacherHeadUrl;
	
	private String monitorName;
	
	private String monitorHeadUrl;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Long getStudentNum() {
		return studentNum;
	}

	public void setStudentNum(Long studentNum) {
		this.studentNum = studentNum;
	}

	public Long getTeacherNum() {
		return teacherNum;
	}

	public void setTeacherNum(Long teacherNum) {
		this.teacherNum = teacherNum;
	}

	public String getClassTeacherName() {
		return classTeacherName;
	}

	public void setClassTeacherName(String classTeacherName) {
		this.classTeacherName = classTeacherName;
	}

	public String getClassTeacherHeadUrl() {
		return classTeacherHeadUrl;
	}

	public void setClassTeacherHeadUrl(String classTeacherHeadUrl) {
		this.classTeacherHeadUrl = classTeacherHeadUrl;
	}

	public String getMonitorName() {
		return monitorName;
	}

	public void setMonitorName(String monitorName) {
		this.monitorName = monitorName;
	}

	public String getMonitorHeadUrl() {
		return monitorHeadUrl;
	}

	public void setMonitorHeadUrl(String monitorHeadUrl) {
		this.monitorHeadUrl = monitorHeadUrl;
	}

	public String getSchoolIcon() {
		return schoolIcon;
	}

	public void setSchoolIcon(String schoolIcon) {
		this.schoolIcon = schoolIcon;
	}
		
}
