package platform.education.generalTeachingAffair.vo;
import platform.education.generalTeachingAffair.model.StudentCheckAttendance;
/**
 * PjStudentCheckAttendance
 * @author AutoCreate
 *
 */
public class StudentCheckAttendanceVo extends StudentCheckAttendance {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 所在班级名称
	 */
	private String teamName;
	
	/**
	 * 学年名称
	 */
	private String schoolYearName;
	
	/**
	 * attendance_type=0出勤情况
	 */
	private Integer count0;
	/**
	 * attendance_type=1出勤情况
	 */
	private Integer count1;
	/**
	 * attendance_type=2出勤情况
	 */
	private Integer count2;
	/**
	 * attendance_type=3出勤情况
	 */
	private Integer count3;
	/**
	 * attendance_type=4出勤情况
	 */
	private Integer count4;
	/**
	 * attendance_type=5出勤情况
	 */
	private Integer count5;
	
	
	/**
	 * attendance_type=0、1的考勤总天数
	 */
	private Integer sum0;
	
	/**
	 * attendance_type=2、3的考勤总天数
	 */
	private Integer sum1;
	
	/**
	 * attendance_type=4、5的考勤总天数
	 */
	private Integer sum2;
	
	
	
	/**
	 * 获取所在班级称
	 * @return
	 */
	public String getTeamName() {
		return teamName;
	}

	/**
	 * 设置所在班级名称
	 * @param teamName
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Integer getCount0() {
		return count0;
	}

	public void setCount0(Integer count0) {
		this.count0 = count0;
	}

	public Integer getCount1() {
		return count1;
	}

	public void setCount1(Integer count1) {
		this.count1 = count1;
	}

	public Integer getCount2() {
		return count2;
	}

	public void setCount2(Integer count2) {
		this.count2 = count2;
	}

	public Integer getCount3() {
		return count3;
	}

	public void setCount3(Integer count3) {
		this.count3 = count3;
	}

	public Integer getCount4() {
		return count4;
	}

	public void setCount4(Integer count4) {
		this.count4 = count4;
	}

	public Integer getCount5() {
		return count5;
	}

	public void setCount5(Integer count5) {
		this.count5 = count5;
	}
	
	public Integer getSum0() {
		return sum0;
	}

	public void setSum0(Integer sum0) {
		this.sum0 = sum0;
	}

	public Integer getSum1() {
		return sum1;
	}

	public void setSum1(Integer sum1) {
		this.sum1 = sum1;
	}

	public Integer getSum2() {
		return sum2;
	}

	public void setSum2(Integer sum2) {
		this.sum2 = sum2;
	}

	public String getSchoolYearName() {
		return schoolYearName;
	}

	public void setSchoolYearName(String schoolYearName) {
		this.schoolYearName = schoolYearName;
	}

}