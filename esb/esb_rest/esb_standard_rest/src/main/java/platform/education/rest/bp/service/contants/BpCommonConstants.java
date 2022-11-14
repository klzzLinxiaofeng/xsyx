package platform.education.rest.bp.service.contants;

import platform.education.rest.util.SysContantsAccessor;

public class BpCommonConstants {

	public static final Integer PAGE_SIZE = 3;
	public static final int PAGE_SIZE_ONE = 1;

	/**
	 * 班牌移动端appKey安卓
	 */
	private final static String APP_KEY_MOBILE_KEY = "app.key.mobile";
	public final static String APP_KEY_MOBILE = SysContantsAccessor.getStringProp(APP_KEY_MOBILE_KEY);
	public final static String APP_KEY_MOBILE_STR = "班牌移动端appKey";

	/**
	 * 班牌展示设备段appKey
	 */
	private final static String APP_KEY_SIGNAGE_KEY = "app.key.signage";
	public final static String APP_KEY_SIGNAGE = SysContantsAccessor.getStringProp(APP_KEY_SIGNAGE_KEY);
	public final static String APP_KEY_SIGNAGE_STR = "班牌appKey";



	/**推送类型**/
	/**班级形象**/
	public static final String PUSH_TEAM_IMAGE = "200";
	/**电子黑板版**/
	public static final String PUSH_BLACKBOARD = "201";
	/**寻物启示**/
	public static final String PUSH_NOTICE = "202";
	/**学习风采**/
	public static final String PUSH_STUDY_SHARE = "203";
	/**班级视频**/
	public static final String PUSH_CLASS_VIDIO = "204";
	/**班级相册**/
	public static final String PUSH_CLASS_PHOTO = "205";
	/**课程表**/
	public static final String PUSH_SYLLABUS = "206";
	/**欢迎词**/
	public static final String PUSH_WELCOME_TEXT = "131";
	/**考试信息**/
	public static final String PUSH_EXAM_INFO = "207";
	/**通知公告**/
	public static final String PUSH_TEAM_NOTICE = "121";
	/**值日生**/
	public static final String PUSH_DUTY_STUDENT = "125";
	/**班级活动**/
	public static final String PUSH_TEAM_ACTIVITY = "126";
	/**班级形象**/
	public static final String PUSH_TEAM_AWARD = "127";
	/**调课通知**/
	public static final String PUSH_LESSON_CHANGE = "128";
	/**考勤**/
	public static final String PUSH_ATTENDANCE = "210";
	/**定时开关机**/
	public static final String PUSH_SIGNAGE_AUTO = "208";
	/**班级动态**/
	public static final String PUSH_CIRCLE = "411";
	/**个性换展示**/
	public static final String INDIVIDUATION_DISPLAY  = "211";
	/**检测在线**/
	public static final String CHECK_ONLINE  = "212";

	//优先级
	public static final Integer PRIORITY_1 = 1;
	public static final Integer PRIORITY_2 = 2;
	public static final Integer PRIORITY_3 = 3;

	/**房室类型**/
	public static final String ROOM_TYPE_PU_TONG_JIAO_SHI = "PU_TONG_JIAO_SHI";

	/**课程类型**/
	/**特殊课、不上课**/
	public static final Integer COURSE_TYPE_0 = 0;
	/**行政课**/
	public static final Integer COURSE_TYPE_1 = 1;
	/**走班课**/
	public static final Integer COURSE_TYPE_2 = 2;

	/**用户类型**/
	/**教师**/
	public static final Integer USER_TYPE_TEACHER = 1;
	/**学生**/
	public static final Integer USER_TYPE_STUDENT = 4;

	//班牌appKey
	public static final String CLASSBRAND = "xunyun#classBrand#signage";
	public static final String SMARTBRAND = "xunyun#smartBrand#signage";
	public static final String MOBILE = "xunyun#classBrand#mobile";

	//模式
	public static final Integer MODULE_KE_JIAN = 1;
}
