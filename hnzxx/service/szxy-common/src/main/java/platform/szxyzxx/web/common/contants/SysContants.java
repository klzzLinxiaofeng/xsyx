package platform.szxyzxx.web.common.contants;

import platform.szxyzxx.web.common.util.SysContantsAccessor;

/**
 * <p>
 * Title:SysContants.java
 * </p>
 * <p>
 * Description:数字校园系统
 * </p>
 * <p>
 * Copyright: Copyright (c) 2010-2015
 * </p>
 * <p>
 * Company: 广州迅云教育科技有限公司
 * </p>
 * 
 * @Function 功能描述：系统常量
 * @Author 谭扬
 * @Version 1.0
 * @Date 2014年8月5日
 */
public class SysContants {

	/**
	 * 当前系统 内置用户类型 ：教师
	 */
	private final static String USER_TYPE_TEACHER_KEY = "user.type.teacher";
	public static String USER_TYPE_TEACHER = SysContantsAccessor.getStringProp(USER_TYPE_TEACHER_KEY);
	public final static String USER_TYPE_TEACHER_STR = "教师";

	/**
	 * 当前系统 内置用户类型 ：管理员
	 */
	private final static String USER_TYPE_ADMIN_KEY = "user.type.admin";
	public static String USER_TYPE_ADMIN = SysContantsAccessor.getStringProp(USER_TYPE_ADMIN_KEY);
	public final static String USER_TYPE_ADMIN_STR = "管理员";
	
	/**
	 * 当前系统 内置用户类型 ：家长
	 */
	private final static String USER_TYPE_PARENT_KEY = "user.type.parent";
	public static String USER_TYPE_PARENT = SysContantsAccessor.getStringProp(USER_TYPE_PARENT_KEY);
	public final static String USER_TYPE_PARENT_STR = "家长";
	
	/**
	 * 当前系统 内置用户类型 ：学生
	 */
	private final static String USER_TYPE_STUDENT_KEY = "user.type.student";
	public static String USER_TYPE_STUDENT = SysContantsAccessor.getStringProp(USER_TYPE_STUDENT_KEY);
	public final static String USER_TYPE_STUDENT_STR = "学生";
	
	
	/**
	 * 系统所分配的应用ID
	 */
	private final static String SYSTEM_APP_ID_KEY = "system.app.id";
	public static Integer SYSTEM_APP_ID = SysContantsAccessor.getIntegerProp(SYSTEM_APP_ID_KEY);
	
	/**
	 * 公共资源库的appID
	 */
	private final static String SYSTEM_COMMON_RESOURCE_APP_ID_KEY = "system.common.resource.app.id";
	public static Integer SYSTEM_COMMON_RESOURCE_APP_ID = SysContantsAccessor.getIntegerProp(SYSTEM_COMMON_RESOURCE_APP_ID_KEY);

	/**
	 * 
	 */
	private final static String SYSTEM_COMMON_RESOURCE_OWNER_ID_KEY = "system.common.resource.owner.id";
	public static Integer SYSTEM_COMMON_RESOURCE_OWNER_ID = SysContantsAccessor.getIntegerProp(SYSTEM_COMMON_RESOURCE_OWNER_ID_KEY);
	/**
	 * 默认密码
	 */
	private final static String DEFAULT_PASSWORD_KEY = "default.passowrd";
	public static String DEFAULT_PASSWORD = SysContantsAccessor.getStringProp(DEFAULT_PASSWORD_KEY);
	
	/**
	 * 默认学校名称
	 */
	private final static String DEFAULT_SCHOOL_NAME_KEY = "default.school.name";
	public static String DEFAULT_SCHOOL_NAME = SysContantsAccessor.getStringProp(DEFAULT_SCHOOL_NAME_KEY);
	
	/**
	 * 系统超级管理员唯一标识代码
	 */
	private final static String SYSTEM_SUPER_ADMIN_CODE_KEY = "system.super.admin.code";
	public static String SYSTEM_SUPER_ADMIN_CODE = SysContantsAccessor.getStringProp(SYSTEM_SUPER_ADMIN_CODE_KEY);


	private final static String MAX_LESSON_COUNT_MORNING_KEY = "max.lesson.count.morning";
	public static Integer LESSON_COUNT_MORNING = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_MORNING_KEY);
	
	private final static String MAX_LESSON_COUNT_AFTERNOON_KEY = "max.lesson.count.afternoon";
	public static Integer LESSON_COUNT_AFTERNOON = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_AFTERNOON_KEY);
	
	private final static String MAX_LESSON_COUNT_EVENING_KEY = "max.lesson.count.evening";
	public static Integer LESSON_COUNT_EVENING = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_EVENING_KEY);
	
	
	/************************************************************** common resource*****************************************************************/
	
	private final static String COMMON_RESOURCE_ENABLE_KEY = "common.resource.enable";
	public static String COMMON_RESOURCE_ENABLE = SysContantsAccessor.getStringProp(COMMON_RESOURCE_ENABLE_KEY);

	private final static String COMMON_RESOURCE_BASE_PATH_KEY = "common.resource.base.path";
	public static String COMMON_RESOURCE_BASE_PATH = SysContantsAccessor.getStringProp(COMMON_RESOURCE_BASE_PATH_KEY);

	private final static String  COMMON_RESOURCE_TRANSFER_KEY = "common.resource.transfer";
	public final static String  COMMON_RESOURCE_TRANSFER = SysContantsAccessor.getStringProp(COMMON_RESOURCE_TRANSFER_KEY);
	
	private final static String COMMON_RESOURCE_LOGOUT_URL_KEY = "common.resource.logout.url";
	public static String COMMON_RESOURCE_LOGOUT_URL = SysContantsAccessor.getStringProp(COMMON_RESOURCE_LOGOUT_URL_KEY);
	
	/************************************************************** sns resource*******************************************************************/
	
	private final static String SNS_ENABLE_KEY = "sns.enable";
	public static boolean SNS_ENABLE = SysContantsAccessor.getBooleanProp(SNS_ENABLE_KEY);
	
	private final static String SNS_BASE_PATH_KEY = "sns.base.path";
	public static String SNS_BASE_PATH = SysContantsAccessor.getStringProp(SNS_BASE_PATH_KEY);


	/****************************************给客户端调用**********************************************/
	private final static String TEACHINGAFFAIR_PARENT_PARENTRELATION_KEY = "teachingAffair.parent.parentRelation";
	public static String TEACHINGAFFAIR_PARENT_PARENTRELATION = SysContantsAccessor.getStringProp(TEACHINGAFFAIR_PARENT_PARENTRELATION_KEY);
	public final static String TEACHINGAFFAIR_PARENT_PARENTRELATION_STR = "学生与家长关系";
	
	private final static String TEACHINGAFFAIR_PARENT_RANK_KEY = "teachingAffair.parent.rank";
	public static String TEACHINGAFFAIR_PARENT_RANK = SysContantsAccessor.getStringProp(TEACHINGAFFAIR_PARENT_RANK_KEY);
	public final static String TEACHINGAFFAIR_PARENT_RANK_KEY_STR = "主监护人";
	
	/****************************************消息中心是否启用推送**********************************************/
	private final static String MESSAGE_ENABLE_KEY = "message.enable";
	public static boolean MESSAGE_ENABLE = SysContantsAccessor.getBooleanProp(MESSAGE_ENABLE_KEY);
	/**
	 * 设置文档转换提交到转换服务的队列优先级
	 */
	public final static Integer MAX_PRIORITY = 1;

	/**
	 * 第三方天气接口url
	 */
	private static final String WEATHER_URL_KEY = "weather.url";
	public static String WEATHER_URL = SysContantsAccessor.getStringProp(WEATHER_URL_KEY);
	public final static String WEATHER_URL_STR = "第三方天气接口url";
	/**
	 * 第三方天气接口url
	 */

	private static final String ICON_URL_PREXIF_KEY = "weather.icon.url.prexif";
	public static String ICON_URL_PREXIF = SysContantsAccessor.getStringProp(ICON_URL_PREXIF_KEY);
	public final static String ICON_URL_PREXIF_STR = "第三方天气接口url";
	
	
	/**
	 * 获取天气接口 apiKey
	 */

	private static final String API_KEY_KEY = "weather.api.key";
	public static String API_KEY = SysContantsAccessor.getStringProp(API_KEY_KEY);
	public final static String API_KEY_STR = "第三方天气接口url";
	
	
	/**
	 * 获取数校云平台appKey
	 */
	private static final String EDUCLOUD_APP_KEY = "educloud.app.key";
	public static String EDUCLOUD_KEY = SysContantsAccessor.getStringProp(EDUCLOUD_APP_KEY);
	public final static String EDUCLOUD_KEY_STR = "数校云平台appKey";
	
	/**
	 * 获取班班星平台appKey
	 */
	private static final String CLASSSTAR_APP_KEY = "classstar.app.key";
	public static String CLASSSTAR_KEY = SysContantsAccessor.getStringProp(CLASSSTAR_APP_KEY);
	public final static String CLASSSTAR_KEY_STR = "班班星平台appKey";


	private static final String ESB_SERVER_URL_KEY = "esb.server.url";
	public static String ESB_SERVER_URL = SysContantsAccessor.getStringProp(ESB_SERVER_URL_KEY);

	private static final String SZXY_BASE_PATH_KEY = "szxy.base.path";
	public static String SZXY_BASE_PATH = SysContantsAccessor.getStringProp(SZXY_BASE_PATH_KEY);

	//成绩分析
	private static final String SCORE_ANALYSIS_URL_KEY = "score.analysis.url";
	public static String SCORE_ANALYSIS_PATH = SysContantsAccessor.getStringProp(SCORE_ANALYSIS_URL_KEY);
	
	//十牛缴费接口URL
	private static final String SCORE_ANALYSIS_10NIU_PAY_URL_KEY = "score.analysis.10niu.pay.api.url";
	public static String SCORE_ANALYSIS_10NIU_PAY_URL_PATH = SysContantsAccessor.getStringProp(SCORE_ANALYSIS_10NIU_PAY_URL_KEY);


	/**
	 * 定南校园广播单点登录地址
	 */
	private static final String DN_BROADCAST_URL_KEY = "dn.broadcast.url";
	public static String DN_BROADCAST_URL_PATH = SysContantsAccessor.getStringProp(DN_BROADCAST_URL_KEY);
	
	/**
	 * 海康
	 */
	private static final String YH_BROADCAST_URL_KEY = "yh.broadcast.url";
	public static String YH_BROADCAST_URL_PATH = SysContantsAccessor.getStringProp(YH_BROADCAST_URL_KEY);
	
	
	/**
	 * CAS地址
	 */
	private final static String CASBASEPATHKEY = "cas.base.path";
	public static String CASBASEPATHURL= SysContantsAccessor.getStringProp(CASBASEPATHKEY);
	
	
	public static void reload() {
		SysContantsAccessor.reload();
		//成绩分析获取URL
		SCORE_ANALYSIS_PATH = SysContantsAccessor.getStringProp(SCORE_ANALYSIS_URL_KEY);
		
		//十牛缴费接口URL
		SCORE_ANALYSIS_10NIU_PAY_URL_PATH = SysContantsAccessor.getStringProp(SCORE_ANALYSIS_10NIU_PAY_URL_KEY);

		YH_BROADCAST_URL_PATH = SysContantsAccessor.getStringProp(YH_BROADCAST_URL_KEY);
		
		DN_BROADCAST_URL_PATH = SysContantsAccessor.getStringProp(DN_BROADCAST_URL_KEY);
		SZXY_BASE_PATH = SysContantsAccessor.getStringProp(SZXY_BASE_PATH_KEY);
		ESB_SERVER_URL = SysContantsAccessor.getStringProp(ESB_SERVER_URL_KEY);
		CLASSSTAR_KEY = SysContantsAccessor.getStringProp(CLASSSTAR_APP_KEY);
		EDUCLOUD_KEY = SysContantsAccessor.getStringProp(EDUCLOUD_APP_KEY);
		API_KEY = SysContantsAccessor.getStringProp(API_KEY_KEY);
		ICON_URL_PREXIF = SysContantsAccessor.getStringProp(ICON_URL_PREXIF_KEY);
		WEATHER_URL = SysContantsAccessor.getStringProp(WEATHER_URL_KEY);
		TEACHINGAFFAIR_PARENT_RANK = SysContantsAccessor.getStringProp(TEACHINGAFFAIR_PARENT_RANK_KEY);
		SNS_BASE_PATH = SysContantsAccessor.getStringProp(SNS_BASE_PATH_KEY);
		COMMON_RESOURCE_LOGOUT_URL = SysContantsAccessor.getStringProp(COMMON_RESOURCE_LOGOUT_URL_KEY);
		COMMON_RESOURCE_BASE_PATH = SysContantsAccessor.getStringProp(COMMON_RESOURCE_BASE_PATH_KEY);
		COMMON_RESOURCE_ENABLE = SysContantsAccessor.getStringProp(COMMON_RESOURCE_ENABLE_KEY);
		LESSON_COUNT_EVENING = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_EVENING_KEY);
		LESSON_COUNT_AFTERNOON = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_AFTERNOON_KEY);
		LESSON_COUNT_MORNING = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_MORNING_KEY);
		SYSTEM_SUPER_ADMIN_CODE = SysContantsAccessor.getStringProp(SYSTEM_SUPER_ADMIN_CODE_KEY);
		DEFAULT_SCHOOL_NAME = SysContantsAccessor.getStringProp(DEFAULT_SCHOOL_NAME_KEY);
		DEFAULT_PASSWORD = SysContantsAccessor.getStringProp(DEFAULT_PASSWORD_KEY);
		SYSTEM_COMMON_RESOURCE_OWNER_ID = SysContantsAccessor.getIntegerProp(SYSTEM_COMMON_RESOURCE_OWNER_ID_KEY);
		SYSTEM_COMMON_RESOURCE_APP_ID = SysContantsAccessor.getIntegerProp(SYSTEM_COMMON_RESOURCE_APP_ID_KEY);
		SYSTEM_APP_ID = SysContantsAccessor.getIntegerProp(SYSTEM_APP_ID_KEY);
		USER_TYPE_STUDENT = SysContantsAccessor.getStringProp(USER_TYPE_STUDENT_KEY);
		USER_TYPE_TEACHER = SysContantsAccessor.getStringProp(USER_TYPE_TEACHER_KEY);
		USER_TYPE_ADMIN = SysContantsAccessor.getStringProp(USER_TYPE_ADMIN_KEY);
		USER_TYPE_PARENT = SysContantsAccessor.getStringProp(USER_TYPE_PARENT_KEY);
		CASBASEPATHURL = SysContantsAccessor.getStringProp(CASBASEPATHKEY);
	}

	/**
	 * 是否使用新的桌面
	 */
	private final static String isUseNewHome_KEY = "system.isUseNewHome";
	public static Boolean isUseNewHome=SysContantsAccessor.getBooleanProp(isUseNewHome_KEY);
	
	
	
	
	
	
	
	
	
}
