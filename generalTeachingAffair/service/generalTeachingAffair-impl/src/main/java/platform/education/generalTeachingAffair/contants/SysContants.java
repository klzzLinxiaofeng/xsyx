package platform.education.generalTeachingAffair.contants;

import platform.education.generalTeachingAffair.utils.SysConfigAccessor;

/**
 * <p>Title:SysContants.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：系统常量
 * @Author 李炜伦
 * @Version 1.0
 * @Date 2015年6月29日
 */
public class SysContants {
	
	/**
	 * 当前系统 内置用户类型 ：教师
	 */
	private final static String USER_TYPE_TEACHER_KEY = "user.type.teacher";
	public final static String USER_TYPE_TEACHER = SysConfigAccessor.getStringProp(USER_TYPE_TEACHER_KEY);
	
	public final static String USER_TYPE_TEACHER_STR = "教师";
	
	/**
	 * 当前系统 内置用户类型 ：管理员
	 */
	private final static String USER_TYPE_ADMIN_KEY = "user.type.admin";
	public final static String USER_TYPE_ADMIN = SysConfigAccessor.getStringProp(USER_TYPE_ADMIN_KEY);
	
	public final static String USER_TYPE_ADMIN_STR = "管理员";
	
	/**
	 * 当前系统 内置用户类型 ：家长
	 */
	private final static String USER_TYPE_PARENT_KEY = "user.type.parent";
	public final static String USER_TYPE_PARENT = SysConfigAccessor.getStringProp(USER_TYPE_PARENT_KEY);
	public final static String USER_TYPE_PARENT_STR = "家长";
	
	/**
	 * 当前系统 内置用户类型 ：学生
	 */
	private final static String USER_TYPE_STUDENT_KEY = "user.type.student";
	public final static String USER_TYPE_STUDENT = SysConfigAccessor.getStringProp(USER_TYPE_STUDENT_KEY);
	public final static String USER_TYPE_STUDENT_STR = "学生";
	
	
	
	public final static String CURRENT_USER = "resource_user_info";
        
        /**
	 * 标记当前从哪个app交互到公共资源库的session键
	 */
        public final static String RELATE_APP_ID_KEY = "relate_app_id";
        
        public final static String RELATE_SCHOOL_ID_KEY = "relate_school_id";
	
//	private final static String SYSTEM_SZXY_APP_ID_KEY = "system.szxy.app.id";
//	public final static Integer SYSTEM_SZXY_APP_ID = SysConfigAccessor.getIntegerProp(SYSTEM_SZXY_APP_ID_KEY);
	/**
	 * 从配置文件中拿到appid
	 */
	private final static String SYSTEM_APP_ID_KEY = "system.app.id";
	public final static Integer SYSTEM_APP_ID = SysConfigAccessor.getIntegerProp(SYSTEM_APP_ID_KEY);
	/**
	 * 从配置文件中拿到ownerId
	 */
	private final static String SYSTEM_OWNER_ID_KEY = "system.owner.id";
	public final static Integer SYSTEM_OWNER_ID = SysConfigAccessor.getIntegerProp(SYSTEM_OWNER_ID_KEY);
	
//	public final static String SZXY_BASE_PATH_KEY = "szxy.base.path";
//	public final static String SZXY_BASE_PATH = SysConfigAccessor.getStringProp(SZXY_BASE_PATH_KEY);
	/**
	 * 设置文档转换提交到转换服务的队列优先级
	 */
	public final static Integer MAX_PRIORITY = 1;
	
	/**
	 * 默认密码
	 */
	private final static String DEFAULT_PASSWORD_KEY = "default.passowrd";
	public final static String DEFAULT_PASSWORD = SysConfigAccessor.getStringProp(DEFAULT_PASSWORD_KEY);
	
	/**
	 * 默认学校名称
	 */
	private final static String DEFAULT_SCHOOL_NAME_KEY = "default.school.name";
	public final static String DEFAULT_SCHOOL_NAME = SysConfigAccessor.getStringProp(DEFAULT_SCHOOL_NAME_KEY);
	
	
	/****************************************给客户端调用**********************************************/
	private final static String TEACHINGAFFAIR_PARENT_PARENTRELATION_KEY = "teachingAffair.parent.parentRelation";
	public final static String TEACHINGAFFAIR_PARENT_PARENTRELATION = SysConfigAccessor.getStringProp(TEACHINGAFFAIR_PARENT_PARENTRELATION_KEY);
	public final static String TEACHINGAFFAIR_PARENT_PARENTRELATION_STR = "学生与家长关系";
	
	private final static String TEACHINGAFFAIR_PARENT_RANK_KEY = "teachingAffair.parent.rank";
	public final static String TEACHINGAFFAIR_PARENT_RANK = SysConfigAccessor.getStringProp(TEACHINGAFFAIR_PARENT_RANK_KEY);
	public final static String TEACHINGAFFAIR_PARENT_RANK_KEY_STR = "主监护人";
}
