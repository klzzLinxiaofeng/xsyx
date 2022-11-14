package platform.szxyzxx.web.bbx.contants;

/**
 *班班星常量
 * @author huangyanchun
 *
 */
public class ContansOfBbx {

	/**
	 * 指的是班级账号开通状态
	 */
	public static final String accountState_open = "0"; 
	  
	/**
	 * 指的是操作权限方式为公开使用
	 */
	public static final String accessType_open = "0";
	
	/**
	 * 指的是操作权限方式为密码访问
	 */
	public static final String accessType_password = "1";
	
	/**
	 * 指某个班级的账号开通状态
	 */
	public static final String openState_open = "0";
	
	/**
	 * 指某个班级的账号关闭状态
	 */
	public static final String openState_forbidden = "2";
	
	
	/**
	 * 指某个班级的账号未开通状态
	 */
	public static final  String  openState_untutored = "1";
	
	
	/** 
	 * 指发送的通知的接收者类别为全体教师
	 */
	public final static Integer allTeacher = 1;

	/**
	 * 指发送的通知的接收者类别为指定年级 
	 */
	public final static Integer assignGrade = 4;

	/**
	 * 指发送的通知的接收者类别为指定班级
	 */
	public final static Integer assignTeam = 5;
	
	
	/**
	 * 开通环信的默认密码
	 */
	public final static String  DEFAULT_PASSWORD = "123456";
	
//	/**
//	 * 第三方天气接口url
//	 */
//	public static final String WEATHER_URL = "http://apis.baidu.com/heweather/weather/free";
//	
//	/**
//	 * 第三方天气接口url
//	 */
//	public static final String ICON_URL_PREXIF = "http://files.heweather.com/cond_icon/";
//	
//	/**
//	 * 获取天气接口 apiKey
//	 */
//	public static final String API_KEY = "7769a95ce4e7e7003f42c59c7d8c1752";
//	
	/**
	 * 欢迎词播放背景类型 为模板
	 */
	public static final String BACKGROUND_TYPE_TEMPLATE="1";
	
	/**
	 * 欢迎词播放背景类型 为颜色码
	 */
	public static final String BACKGROUND_TYPE_COLOR="2";
	
	
	/**
	 * 欢迎词默认播放背景
	 */
	public static final String BACKGROUND_COLOR="#ffffff";
	
}
