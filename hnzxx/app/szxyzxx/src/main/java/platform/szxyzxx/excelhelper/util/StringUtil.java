package platform.szxyzxx.excelhelper.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public abstract class StringUtil {
	/**
	 * 返回字符串的首字母变为大写的字符串
	 * 
	 * @param s
	 * @return
	 */
	public static String InitialCapitalization(String s) {
		char firstChar = s.charAt(0);
		firstChar = Character.toUpperCase(firstChar);
		return firstChar + s.substring(1);
	}
	/**
	 * 判断Class实例是否是字符串
	 * @param cls
	 * @return
	 */
	public static boolean classIsString(Class<?> cls) {
		if (cls == String.class || cls == StringBuffer.class || cls == StringBuilder.class)
			return true;
		return false;
	}

	/**
	 * 判断字符串是否是数字（整数、小数都可以）
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		if(str!=null && !str.isEmpty()) {
			Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
			return pattern.matcher(str).matches();
		}
		return false;
	}

	/**
	 * 将对象转换为字符串
	 *
	 * @param object
	 * @return
	 */
	public static String objectToString(Object object) {
		if (object == null) {
			return "";
		}
		if (object instanceof Date) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return df.format((Date) object);
		}
		if (object instanceof String) {
			return (String) object;
		}
		if (object instanceof Integer) {
			return ((Integer) object).intValue() + "";
		}
		if (object instanceof Double) {
			return ((Double) object).doubleValue() + "";
		}
		if (object instanceof Long) {
			return Long.toString(((Long) object).longValue());
		}
		if (object instanceof Float) {
			return Float.toHexString(((Float) object).floatValue());
		}
		if (object instanceof Boolean) {
			return Boolean.toString((Boolean) object);
		}
		if (object instanceof Short) {
			return Short.toString((Short) object);
		}
		return object.toString();
	}
	
}
