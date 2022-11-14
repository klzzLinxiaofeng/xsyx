package platform.education.rest.common.constants;

import platform.education.rest.util.SysContantsAccessor;

/**
 * 
 * @author hmzhang 2016/07/25
 *
 */
public class SysContants {
	
	/**
	 * 当前系统 内置用户类型 ：教师
	 */
	public final static String USER_TYPE_TEACHER = "1";
	public final static String USER_TYPE_TEACHER_STR = "教师";
	
	/**
	 * 当前系统 内置用户类型 ：管理员
	 */
	public final static String USER_TYPE_ADMIN = "2";
	public final static String USER_TYPE_ADMIN_STR = "管理员";
	
	/**
	 * 当前系统 内置用户类型 ：家长
	 */
	public final static String USER_TYPE_PARENT = "3";
	public final static String USER_TYPE_PARENT_STR = "家长";
	
	/**
	 * 当前系统 内置用户类型 ：学生
	 */
	public final static String USER_TYPE_STUDENT = "4";
	public final static String USER_TYPE_STUDENT_STR = "学生";
	
	/**
	 * 当前系统基础代码版本号 
	 */
	private final static String JC_CODE_VERSION_KEY = "jc.code.version";
	public final static String JC_CODE_VERSION = SysContantsAccessor.getStringProp(JC_CODE_VERSION_KEY);
	public final static String JC_CODE_VERSION_STR = "当前系统基础代码版本";
	
	/**
	 * 获取桌面应用信息
	 */
	private final static String JC_DESKTOPAPPINFO_KEY = "jc.desktopApp.info";
	public final static String JC_DESKTOPAPPINFO_INFO = SysContantsAccessor.getStringProp(JC_DESKTOPAPPINFO_KEY);
	public final static String JC_DESKTOPAPPINFO_INFO_STR = "获取桌面应用信息";
	
	/**
	 * app默认头像
	 */
	private final static String APP_DEFAULT_USERICON_KEY = "app.default.userIcon";
	public final static String APP_DEFAULT_USERICON = SysContantsAccessor.getStringProp(APP_DEFAULT_USERICON_KEY);
	public final static String APP_DEFAULT_USERICON_STR = "app默认头像";
	
	/**
	 * 数校早上课程节次配置
	 */
	private final static String MAX_LESSON_COUNT_MORING_KEY = "max.lesson.count.morning";
	public final static String MAX_LESSON_COUNT_MORING = SysContantsAccessor.getStringProp(MAX_LESSON_COUNT_MORING_KEY);
	public final static String MAX_LESSON_COUNT_MORING_STR = "数校早上课程节次配置";
	
	/**
	 * 数校中午课程节次配置
	 */
	private final static String MAX_LESSON_COUNT_AFTERNOON_KEY = "max.lesson.count.afternoon";
	public final static String MAX_LESSON_COUNT_AFTERNOON = SysContantsAccessor.getStringProp(MAX_LESSON_COUNT_AFTERNOON_KEY);
	public final static String MAX_LESSON_COUNT_AFTERNOON_STR = "数校中午课程节次配置";
	
	/**
	 * 数校晚上课程节次配置
	 */
	private final static String MAX_LESSON_COUNT_EVENING_KEY = "max.lesson.count.evening";
	public final static String MAX_LESSON_COUNT_EVENING = SysContantsAccessor.getStringProp(MAX_LESSON_COUNT_EVENING_KEY);
	public final static String MAX_LESSON_COUNT_EVENING_STR = "数校晚上课程节次配置";


	/**
	 *	接口请求的最大时间误差
	 */
	private final static String REQUEST_ERROR_TIME_KEY = "request.error.time";
	public final static Integer REQUEST_ERROR_TIME = SysContantsAccessor.getIntegerProp(REQUEST_ERROR_TIME_KEY);
	public final static String REQUEST_ERROR_TIME_STR = "请求的最大时间误差";

	/**
	 * 	云平台appKey
	 */
	private final static String EDUCLOUD_APP_KEY_KEY = "educloud.app.key";
	public final static String EDUCLOUD_APP_KEY = SysContantsAccessor.getStringProp(EDUCLOUD_APP_KEY_KEY);
	public final static String EDUCLOUD_APP_KEY_STR = "云平台appKey";

	/**
	 * 	云平台appSecret
	 */
	private final static String EDUCLOUD_APP_SECRET_KEY = "educloud.app.secret";
	public final static String EDUCLOUD_APP_SECRET = SysContantsAccessor.getStringProp(EDUCLOUD_APP_SECRET_KEY);
	public final static String EDUCLOUD_APP_SECRET_STR = "云平台appSecret";
	
	/**
	 * 从配置文件中拿到appid
	 */
	private final static String SYSTEM_APP_ID_KEY = "system.app.id";
	public final static Integer SYSTEM_APP_ID = SysContantsAccessor.getIntegerProp(SYSTEM_APP_ID_KEY);
}
