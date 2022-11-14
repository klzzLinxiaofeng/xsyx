package platform.education.rest.bp.service.vo;

import java.util.List;
import java.util.Map;

public class BpAttendanceVo {
	
	private Long attendanceDay;
	
	private List<PersonVo> lateList;
	
	private List<PersonVo> outEarlyList;
	
	private List<PersonVo> absentList;
	
	private List<PersonVo> leaveList;
	
	private List<Map<String, Object>>  attendanceTimeList;

	
	
	public List<Map<String, Object>> getAttendanceTimeList() {
		return attendanceTimeList;
	}

	public void setAttendanceTimeList(List<Map<String, Object>> attendanceTimeList) {
		this.attendanceTimeList = attendanceTimeList;
	}

	public Long getAttendanceDay() {
		return attendanceDay;
	}

	public void setAttendanceDay(Long attendanceDay) {
		this.attendanceDay = attendanceDay;
	}

	public List<PersonVo> getLateList() {
		return lateList;
	}

	public void setLateList(List<PersonVo> lateList) {
		this.lateList = lateList;
	}

	public List<PersonVo> getOutEarlyList() {
		return outEarlyList;
	}

	public void setOutEarlyList(List<PersonVo> outEarlyList) {
		this.outEarlyList = outEarlyList;
	}

	public List<PersonVo> getAbsentList() {
		return absentList;
	}

	public void setAbsentList(List<PersonVo> absentList) {
		this.absentList = absentList;
	}

	public List<PersonVo> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<PersonVo> leaveList) {
		this.leaveList = leaveList;
	}
	
	
}
