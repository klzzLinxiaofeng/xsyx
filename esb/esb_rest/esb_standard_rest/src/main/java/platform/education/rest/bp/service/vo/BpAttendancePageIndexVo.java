package platform.education.rest.bp.service.vo;

import platform.education.clazz.vo.BpBwAttendancesVo;
import platform.education.clazz.vo.StaTeamAttendanceVo;

import java.util.List;

public class BpAttendancePageIndexVo {
	
	/**
	 * 今日上学考勤详情
	 */
	private List<BpBwAttendancesVo> todayAttendancesShangxue;
	
	/**
	 * 今日放学考勤详情
	 */
	private List<BpBwAttendancesVo> todayAttendancesFangxue;
	
	/**
	 * 昨日考勤
	 */
	private BpAttendanceVo yesterdayAttendance;
	
	/**
	 * 本周考勤统计
	 */
	private List<StaTeamAttendanceVo> staTeamWeekAttendance;

	public List<BpBwAttendancesVo> getTodayAttendancesShangxue() {
		return todayAttendancesShangxue;
	}

	public void setTodayAttendancesShangxue(
			List<BpBwAttendancesVo> todayAttendancesShangxue) {
		this.todayAttendancesShangxue = todayAttendancesShangxue;
	}

	public List<BpBwAttendancesVo> getTodayAttendancesFangxue() {
		return todayAttendancesFangxue;
	}

	public void setTodayAttendancesFangxue(
			List<BpBwAttendancesVo> todayAttendancesFangxue) {
		this.todayAttendancesFangxue = todayAttendancesFangxue;
	}

	public BpAttendanceVo getYesterdayAttendance() {
		return yesterdayAttendance;
	}

	public void setYesterdayAttendance(BpAttendanceVo yesterdayAttendance) {
		this.yesterdayAttendance = yesterdayAttendance;
	}

	public List<StaTeamAttendanceVo> getStaTeamWeekAttendance() {
		return staTeamWeekAttendance;
	}

	public void setStaTeamWeekAttendance(
			List<StaTeamAttendanceVo> staTeamWeekAttendance) {
		this.staTeamWeekAttendance = staTeamWeekAttendance;
	}
}
