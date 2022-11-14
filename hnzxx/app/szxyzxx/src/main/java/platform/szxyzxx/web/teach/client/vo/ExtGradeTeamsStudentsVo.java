package platform.szxyzxx.web.teach.client.vo;

import java.util.List;

public class ExtGradeTeamsStudentsVo {
	/**
	 * 班级ID
	 */
	private Integer teamId;
	/**
	 * 班级名称
	 */
	private String teamName;
	/**
	 * 班级学生集合
	 */
	private List<ExtStudentVo> students;
	
	public Integer getTeamId() {
		return teamId;
	}
	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public List<ExtStudentVo> getStudents() {
		return students;
	}
	public void setStudents(List<ExtStudentVo> students) {
		this.students = students;
	}
	
}
