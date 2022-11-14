package platform.education.util;

/**
 * Redis所有Keys
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-18 19:51
 */
public class RedisKeys {

	/**
	 * 图形验证码redisKey
	 * 
	 * @param key
	 * @return
	 */
	public static String getCaptchaKey(String key) {
		return "buss:captcha:" + key;
	}

	/**
	 * 当前用户,使用userId为key
	 * 
	 * @param key
	 * @return
	 */
	public static String getOnlineUserKey(String userId) {
		return "online:user:" + userId;
	}

	/**
	 * 当前用户的所有权限，使用userId为key
	 * 
	 * @param key
	 * @return
	 */
	public static String getFrameworkAccountUrlKey(String userId) {
		return "netdisk:account:url:" + userId;
	}

}
