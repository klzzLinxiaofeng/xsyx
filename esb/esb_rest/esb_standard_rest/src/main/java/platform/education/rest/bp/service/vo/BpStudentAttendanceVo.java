package platform.education.rest.bp.service.vo;

import platform.education.clazz.model.BpBwAttendances;

import java.util.List;

public class BpStudentAttendanceVo {
	
	
	private List<BpBwAttendances> lateList;
	
	private List<BpBwAttendances> outEarlyList;
	
	private List<BpBwAttendances> absentList;
	
	private List<BpBwAttendances> leaveList;

	public List<BpBwAttendances> getLateList() {
		return lateList;
	}

	public void setLateList(List<BpBwAttendances> lateList) {
		this.lateList = lateList;
	}

	public List<BpBwAttendances> getOutEarlyList() {
		return outEarlyList;
	}

	public void setOutEarlyList(List<BpBwAttendances> outEarlyList) {
		this.outEarlyList = outEarlyList;
	}

	public List<BpBwAttendances> getAbsentList() {
		return absentList;
	}

	public void setAbsentList(List<BpBwAttendances> absentList) {
		this.absentList = absentList;
	}

	public List<BpBwAttendances> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<BpBwAttendances> leaveList) {
		this.leaveList = leaveList;
	}
	
	
}
