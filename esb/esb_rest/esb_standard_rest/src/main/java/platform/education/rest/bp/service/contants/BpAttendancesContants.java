package platform.education.rest.bp.service.contants;

public class BpAttendancesContants {
	
	/** 行政考勤*/
	public static final Integer ADMINISTRATION = 0;
	/** 课程考勤*/
	public static final Integer CLASS = 1;
	
	
	
	/** 上学时间类型*/
	public static final Integer ATTENDANCE_LATE_TIME = 1;
	/** 放学时间类型*/
	public static final Integer ATTENDANCE_OUT_EARLY_TIME = 2;
	
	
	
	/** 迟到类型*/
	public static final Integer ATTENDANCE_TYPE_LATE = 1;
	/** 早退类型*/
	public static final Integer ATTENDANCE_TYPE_OUT_EARLY = 2;
	/** 缺勤类型*/
	public static final Integer ATTENDANCE_TYPE_ABSENT = 3;
	/** 请假类型*/
	public static final Integer ATTENDANCE_TYPE_LEAVE = 4;
	
	/**上课考勤  出勤**/
	public static final Integer LESSON_TURN_OUT = 1;
	/**上课考勤  缺勤**/
	public static final Integer LESSON_ABSENCE = 2;
	/**上课考勤  请假**/
	public static final Integer LESSON_LEAVE = 3;
}
