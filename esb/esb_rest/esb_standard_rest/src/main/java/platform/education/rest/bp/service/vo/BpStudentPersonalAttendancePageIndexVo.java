package platform.education.rest.bp.service.vo;

import platform.education.clazz.vo.StaStudentAttendanceVo;

import java.io.Serializable;
import java.util.List;

public class BpStudentPersonalAttendancePageIndexVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer studentUserId;
	
	private String studentName;
	
	private String studentIcon;

	/**
	 * 周考勤统计
	 */
	private StaStudentAttendanceVo weekStaStudentAttendance;
	/**
	 * 周考勤详情
	 */
	private List<StaStudentAttendanceVo> weekStaStudentAttendanceDetailLilst;
	/**
	 * 月考勤统计
	 */
	private StaStudentAttendanceVo monthStaStudentAttendance;
	/**
	 * 月考勤详情
	 */
	private List<StaStudentAttendanceVo> monthStaStudentAttendanceDetailLilst;
	
	public Integer getStudentUserId() {
		return studentUserId;
	}

	public void setStudentUserId(Integer studentUserId) {
		this.studentUserId = studentUserId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentIcon() {
		return studentIcon;
	}

	public void setStudentIcon(String studentIcon) {
		this.studentIcon = studentIcon;
	}

	public StaStudentAttendanceVo getWeekStaStudentAttendance() {
		return weekStaStudentAttendance;
	}

	public void setWeekStaStudentAttendance(
			StaStudentAttendanceVo weekStaStudentAttendance) {
		this.weekStaStudentAttendance = weekStaStudentAttendance;
	}

	public List<StaStudentAttendanceVo> getWeekStaStudentAttendanceDetailLilst() {
		return weekStaStudentAttendanceDetailLilst;
	}

	public void setWeekStaStudentAttendanceDetailLilst(
			List<StaStudentAttendanceVo> weekStaStudentAttendanceDetailLilst) {
		this.weekStaStudentAttendanceDetailLilst = weekStaStudentAttendanceDetailLilst;
	}

	public StaStudentAttendanceVo getMonthStaStudentAttendance() {
		return monthStaStudentAttendance;
	}

	public void setMonthStaStudentAttendance(
			StaStudentAttendanceVo monthStaStudentAttendance) {
		this.monthStaStudentAttendance = monthStaStudentAttendance;
	}

	public List<StaStudentAttendanceVo> getMonthStaStudentAttendanceDetailLilst() {
		return monthStaStudentAttendanceDetailLilst;
	}

	public void setMonthStaStudentAttendanceDetailLilst(
			List<StaStudentAttendanceVo> monthStaStudentAttendanceDetailLilst) {
		this.monthStaStudentAttendanceDetailLilst = monthStaStudentAttendanceDetailLilst;
	}
	
	
}
