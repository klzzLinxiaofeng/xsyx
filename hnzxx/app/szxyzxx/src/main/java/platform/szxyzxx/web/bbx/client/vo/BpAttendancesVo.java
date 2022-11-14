package platform.szxyzxx.web.bbx.client.vo;

import java.util.Date;
import java.util.List;

import platform.education.clazz.vo.BpBwAttendancesVo;

public class BpAttendancesVo {

	private Date attendanceDay;
	
	private String lateNames;
	
	private String outEarlyNames;
	
	private String absentNames;
	
	private String leaveNames;
	
	private List<BpBwAttendancesVo> lateList;
	
	private List<BpBwAttendancesVo> outEarlyList;
	
	private List<BpBwAttendancesVo> absentList;
	
	private List<BpBwAttendancesVo> leaveList;

	public Date getAttendanceDay() {
		return attendanceDay;
	}

	public void setAttendanceDay(Date attendanceDay) {
		this.attendanceDay = attendanceDay;
	}

	public String getLateNames() {
		return lateNames;
	}

	public void setLateNames(String lateNames) {
		this.lateNames = lateNames;
	}

	public String getOutEarlyNames() {
		return outEarlyNames;
	}

	public void setOutEarlyNames(String outEarlyNames) {
		this.outEarlyNames = outEarlyNames;
	}

	public String getAbsentNames() {
		return absentNames;
	}

	public void setAbsentNames(String absentNames) {
		this.absentNames = absentNames;
	}

	public String getLeaveNames() {
		return leaveNames;
	}

	public void setLeaveNames(String leaveNames) {
		this.leaveNames = leaveNames;
	}

	public List<BpBwAttendancesVo> getLateList() {
		return lateList;
	}

	public void setLateList(List<BpBwAttendancesVo> lateList) {
		this.lateList = lateList;
	}

	public List<BpBwAttendancesVo> getOutEarlyList() {
		return outEarlyList;
	}

	public void setOutEarlyList(List<BpBwAttendancesVo> outEarlyList) {
		this.outEarlyList = outEarlyList;
	}

	public List<BpBwAttendancesVo> getAbsentList() {
		return absentList;
	}

	public void setAbsentList(List<BpBwAttendancesVo> absentList) {
		this.absentList = absentList;
	}

	public List<BpBwAttendancesVo> getLeaveList() {
		return leaveList;
	}

	public void setLeaveList(List<BpBwAttendancesVo> leaveList) {
		this.leaveList = leaveList;
	}
	
	
	
}
