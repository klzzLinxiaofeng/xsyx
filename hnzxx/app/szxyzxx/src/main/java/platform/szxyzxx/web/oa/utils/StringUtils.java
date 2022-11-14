package platform.szxyzxx.web.oa.utils;


public class StringUtils {
	public static boolean isNotEmpty(String str) {
		if (str!= null && str.length()>0) {
			return true;
		} else {
			return false;
		}
	}

}
