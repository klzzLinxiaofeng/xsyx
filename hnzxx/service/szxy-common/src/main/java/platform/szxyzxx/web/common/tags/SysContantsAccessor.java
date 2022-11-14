package platform.szxyzxx.web.common.tags;

import platform.szxyzxx.web.common.contants.InfoCode;
import platform.szxyzxx.web.common.contants.SysContants;
/**
 * <p>Title:SysContantsAccessor.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：自定义系统常量访问 方便用于EL获取常量值
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class SysContantsAccessor {
	
	public static String currentUserKey() {
		return InfoCode.CURRENT_USER;
	}
	
	public static String getDefaultSchoolName() {
		return SysContants.DEFAULT_SCHOOL_NAME;
	}
	
	public static Integer getSystemAppId() {
		return SysContants.SYSTEM_APP_ID;
	}
	
	public static Integer getMorningMaxLessonCount() {
		return SysContants.LESSON_COUNT_MORNING;
	}
	
	public static Integer getAfternoonMaxLessonCount() {
		return SysContants.LESSON_COUNT_AFTERNOON;
	}
	
	public static Integer getEveningMaxLessonCount() {
		return SysContants.LESSON_COUNT_EVENING;
	}
	
	public static String getSuperAdminCode() {
		return SysContants.SYSTEM_SUPER_ADMIN_CODE;
	}
	
	public static String getESBServerUrl() {
		return SysContants.ESB_SERVER_URL;
	}
	
}
