package platform.szxyzxx.web.bbx.vo;

import platform.education.generalTeachingAffair.model.Team;

public class BBXTeamVo {

	
	private Team team;
	
	/**
	 * 账号开通状态
	 */
	private String openState;

	/**
	 *账号操作权限 
	 */
	private String accessType;
	
	
	/**
	 * 一个班下的所有教师总数
	 */
	private Integer teacherCount;
	
	/**
	 *一个班下的所有学生总数
	 */
	private Integer studentCount;
	
	/**
	 * 已经开通的班级账号名
	 */
	private String account;
	
	
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public String getOpenState() {
		return openState;
	}

	public void setOpenState(String openState) {
		this.openState = openState;
	}
	
	public String getAccessType() {
		return accessType;
	}
	
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}

	public Integer getTeacherCount() {
		return teacherCount;
	}

	public void setTeacherCount(Integer teacherCount) {
		this.teacherCount = teacherCount;
	}

	public Integer getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(Integer studentCount) {
		this.studentCount = studentCount;
	}
	
	
	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	
	
}
