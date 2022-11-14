package platform.education.rest.bp.service.vo;

import platform.education.rest.basic.service.vo.ExtTeamStudentVo;

import java.util.List;

public class AttendanceStudentVo {
	
	private List<ExtTeamStudentVo> teamStudents;
	
	private List<ExtTeamStudentVo> removeStudents;

	public List<ExtTeamStudentVo> getTeamStudents() {
		return teamStudents;
	}

	public void setTeamStudents(List<ExtTeamStudentVo> teamStudents) {
		this.teamStudents = teamStudents;
	}

	public List<ExtTeamStudentVo> getRemoveStudents() {
		return removeStudents;
	}

	public void setRemoveStudents(List<ExtTeamStudentVo> removeStudents) {
		this.removeStudents = removeStudents;
	}
	
	
	
}
