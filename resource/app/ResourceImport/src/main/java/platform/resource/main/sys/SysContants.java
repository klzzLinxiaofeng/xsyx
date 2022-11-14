package platform.resource.main.sys;

import platform.resource.main.util.SysContantsAccessor;


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
	public final static String USER_TYPE_TEACHER = SysContantsAccessor.getStringProp(USER_TYPE_TEACHER_KEY);
	
	public final static String USER_TYPE_TEACHER_STR = "教师";
	
	/**
	 * 当前系统 内置用户类型 ：管理员
	 */
	private final static String USER_TYPE_ADMIN_KEY = "user.type.admin";
	public final static String USER_TYPE_ADMIN = SysContantsAccessor.getStringProp(USER_TYPE_ADMIN_KEY);
	
	public final static String USER_TYPE_ADMIN_STR = "管理员";
	
	/**
	 * 当前系统 内置用户类型 ：家长
	 */
	private final static String USER_TYPE_PARENT_KEY = "user.type.parent";
	public final static String USER_TYPE_PARENT = SysContantsAccessor.getStringProp(USER_TYPE_PARENT_KEY);
	public final static String USER_TYPE_PARENT_STR = "家长";
	
	/**
	 * 当前系统 内置用户类型 ：学生
	 */
	private final static String USER_TYPE_STUDENT_KEY = "user.type.student";
	public final static String USER_TYPE_STUDENT = SysContantsAccessor.getStringProp(USER_TYPE_STUDENT_KEY);
	public final static String USER_TYPE_STUDENT_STR = "学生";
	
	
	/**
	 * 系统所分配的应用ID
	 */
	private final static String SYSTEM_APP_ID_KEY = "system.app.id";
	public final static Integer SYSTEM_APP_ID = SysContantsAccessor.getIntegerProp(SYSTEM_APP_ID_KEY); 
	private static final String SYSTEM_OWNER_ID_KEY = "system.owner.id"; 
	public final static Integer SYSTEM_OWNER_ID = SysContantsAccessor.getIntegerProp(SYSTEM_OWNER_ID_KEY); 
	
	/**
	 * 默认密码
	 */
	private final static String DEFAULT_PASSWORD_KEY = "default.passowrd";
	public final static String DEFAULT_PASSWORD = SysContantsAccessor.getStringProp(DEFAULT_PASSWORD_KEY);
	
	/**
	 * 默认学校名称
	 */
	private final static String DEFAULT_SCHOOL_NAME_KEY = "default.school.name";
	public final static String DEFAULT_SCHOOL_NAME = SysContantsAccessor.getStringProp(DEFAULT_SCHOOL_NAME_KEY);
	
	/**
	 * 系统超级管理员唯一标识代码
	 */
	private final static String SYSTEM_SUPER_ADMIN_CODE_KEY = "system.super.admin.code";
	public final static String SYSTEM_SUPER_ADMIN_CODE = SysContantsAccessor.getStringProp(SYSTEM_SUPER_ADMIN_CODE_KEY);
	
	
	private final static String MAX_LESSON_COUNT_MORNING_KEY = "max.lesson.count.morning";
	public final static Integer LESSON_COUNT_MORNING = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_MORNING_KEY);
	
	private final static String MAX_LESSON_COUNT_AFTERNOON_KEY = "max.lesson.count.afternoon";
	public final static Integer LESSON_COUNT_AFTERNOON = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_AFTERNOON_KEY);
	
	private final static String MAX_LESSON_COUNT_EVENING_KEY = "max.lesson.count.evening";
	public final static Integer LESSON_COUNT_EVENING = SysContantsAccessor.getIntegerProp(MAX_LESSON_COUNT_EVENING_KEY);
	
	
	/************************************************************** common resource*****************************************************************/
	
	private final static String COMMON_RESOURCE_ENABLE_KEY = "common.resource.enable";
	public final static String COMMON_RESOURCE_ENABLE = SysContantsAccessor.getStringProp(COMMON_RESOURCE_ENABLE_KEY);
			
			
	private final static String COMMON_RESOURCE_BASE_PATH_KEY = "common.resource.base.path";
	public final static String COMMON_RESOURCE_BASE_PATH = SysContantsAccessor.getStringProp(COMMON_RESOURCE_BASE_PATH_KEY);
	
	
	/****************************************************************** oa ************************************************************************/
	
	private final static String OA_ENABLE_KEY = "oa.enable";
	public final static String OA_ENABLE = SysContantsAccessor.getStringProp(OA_ENABLE_KEY);
	
	private final static String OA_SERVER_BASEPATH_KEY = "oa.server.basePath";
	public final static String OA_SERVER_BASEPATH = SysContantsAccessor.getStringProp(OA_SERVER_BASEPATH_KEY);
	
	private final static String OA_PROJECT_URL_KEY = "oa.project.url";
	public final static String OA_PROJECT_URL = SysContantsAccessor.getStringProp(OA_PROJECT_URL_KEY);
	
	private final static String OA_PLAN_URL_KEY = "oa.plan.url";
	public final static String OA_PLAN_URL = SysContantsAccessor.getStringProp(OA_PLAN_URL_KEY);
	
	private final static String OA_MEETING_URL_KEY = "oa.meeting.notice.url";
	public final static String OA_MEETING_URL = SysContantsAccessor.getStringProp(OA_MEETING_URL_KEY);
	
	private final static String OA_VACATION_URL_KEY = "oa.vacation.url";
	public final static String OA_VACATION_URL = SysContantsAccessor.getStringProp(OA_VACATION_URL_KEY);
	
	private final static String OA_BUSSINESS_TRIP_URL_KEY = "oa.bussiness.trip.url";
	public final static String OA_BUSSINESS_TRIP_URL = SysContantsAccessor.getStringProp(OA_BUSSINESS_TRIP_URL_KEY);
	
	private final static String OA_NOTICE_URL_KEY = "oa.notice.url";
	public final static String OA_NOTICE_URL = SysContantsAccessor.getStringProp(OA_NOTICE_URL_KEY);
	
	private final static String OA_NOTICE_VIEWER_KEY = "oa.notice.viewer";
	public final static String OA_NOTICE_VIEWER = SysContantsAccessor.getStringProp(OA_NOTICE_VIEWER_KEY);
	
	private final static String OA_MEETING_VIEWER_KEY = "oa.meeting.viewer";
	public final static String OA_MEETING_VIEWER = SysContantsAccessor.getStringProp(OA_MEETING_VIEWER_KEY);
	
	private final static String OA_NOTICE_ACCESSOR_KEY = "oa.notice.accessor";
	public final static String OA_NOTICE_ACCESSOR = SysContantsAccessor.getStringProp(OA_NOTICE_ACCESSOR_KEY);
	
	private final static String OA_NOTICE_BUSSINESS_TRIP_ACCESSOR_KEY = "oa.notice.bussiness.trip.accessor";
	public final static String OA_NOTICE_BUSSINESS_TRIP_ACCESSOR = SysContantsAccessor.getStringProp(OA_NOTICE_BUSSINESS_TRIP_ACCESSOR_KEY);
	
	private final static String OA_VACATION_ACCESSOR_KEY = "oa.vacation.accessor";
	public final static String OA_VACATION_ACCESSOR = SysContantsAccessor.getStringProp(OA_VACATION_ACCESSOR_KEY);
	
	private final static String OA_MEETING_NOTICE_ACCESSOR_KEY = "oa.meeting.notice.accessor";
	public final static String OA_MEETING_NOTICE_ACCESSOR = SysContantsAccessor.getStringProp(OA_MEETING_NOTICE_ACCESSOR_KEY);
	
	private final static String OA_MEETING_ACCESSOR_KEY = "oa.meeting.accessor";
	public final static String OA_MEETING_ACCESSOR = SysContantsAccessor.getStringProp(OA_MEETING_ACCESSOR_KEY);
	
	private final static String OA_PLAN_ACCESSOR_KEY = "oa.plan.accessor";
	public final static String OA_PLAN_ACCESSOR = SysContantsAccessor.getStringProp(OA_PLAN_ACCESSOR_KEY);
	
	private final static String OA_MY_PROJECT_ACCESSOR_KEY = "oa.my.project.accessor";
	public final static String OA_MY_PROJECT_ACCESSOR = SysContantsAccessor.getStringProp(OA_MY_PROJECT_ACCESSOR_KEY);
	
	private final static String OA_PARTICIPATE_PROJECT_ACCESSOR_KEY = "oa.participate.project.accessor";
	public final static String OA_PARTICIPATE_PROJECT_ACCESSOR = SysContantsAccessor.getStringProp(OA_PARTICIPATE_PROJECT_ACCESSOR_KEY);
	
	private final static String OA_LOGOUT_URL_KEY = "oa.logout.url";
	public final static String OA_LOGOUT_URL = SysContantsAccessor.getStringProp(OA_LOGOUT_URL_KEY);
	
	private final static String OA_LOGIN_URL_KEY = "oa.login.url";
	public final static String OA_LOGIN_URL = SysContantsAccessor.getStringProp(OA_LOGIN_URL_KEY);
}
