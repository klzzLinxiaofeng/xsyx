package platform.education.rest.bp.service.vo;

import platform.education.clazz.vo.AttendancesSyllabusVo;

import java.util.List;

public class BpAttendancesSyllabusIndexVo {

	private List<AttendancesSyllabusVo> turnOutList;
	
	private List<AttendancesSyllabusVo> absenceList;
	
	private List<AttendancesSyllabusVo> attendanceList;

	public List<AttendancesSyllabusVo> getTurnOutList() {
		return turnOutList;
	}

	public void setTurnOutList(List<AttendancesSyllabusVo> turnOutList) {
		this.turnOutList = turnOutList;
	}

	public List<AttendancesSyllabusVo> getAbsenceList() {
		return absenceList;
	}

	public void setAbsenceList(List<AttendancesSyllabusVo> absenceList) {
		this.absenceList = absenceList;
	}

	public List<AttendancesSyllabusVo> getAttendanceList() {
		return attendanceList;
	}

	public void setAttendanceList(List<AttendancesSyllabusVo> attendanceList) {
		this.attendanceList = attendanceList;
	}
	
	
}
