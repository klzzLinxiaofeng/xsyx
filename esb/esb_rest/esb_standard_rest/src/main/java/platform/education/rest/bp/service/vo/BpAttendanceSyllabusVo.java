package platform.education.rest.bp.service.vo;

import platform.education.clazz.vo.AttendancesSyllabusVo;

import java.util.List;

public class BpAttendanceSyllabusVo {
	
	
	private List<AttendancesSyllabusVo> turnOutList;//出勤
	
	private List<AttendancesSyllabusVo> absenceList;//缺勤

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
	
	
}
