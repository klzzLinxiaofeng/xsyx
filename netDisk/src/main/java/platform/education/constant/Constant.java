package platform.education.constant;

/**
 * 系统常量进行统一管理
 */
public abstract class Constant {

    private Constant() {
    }

    public static final String TOKEN = "X-Auth-Token"; // 从请求头中获取到token

    public static final Long ADMIN_ID = 1L; // 超级管理员ID,主要用于权限处理中，超级管理员拥有一切权限

    public static final String SALT = "mhy6XIaM5YRaQLwx"; // 盐

    public static final String PASSWORD = "123456"; // 默认密码

    /**
     * 默认过期时长（1天），单位：秒
     */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24;

    /**
     * 7天，单位：秒
     */
    public final static long SEVEN_DAY_EXPIRE = 60 * 60 * 24 * 7;

    /**
     * 15天，单位：秒
     */
    public final static long FIFTEEN_DAY_EXPIRE = 60 * 60 * 24 * 15;

    /**
     * 不设置过期时长
     */
    public final static long NOT_EXPIRE = -1;


}
