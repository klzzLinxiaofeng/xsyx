package platform.education.rest.util;

/**
 * 根据省市区代码，判断当前代码是几级
 * @author Administrator
 *
 */
public class RegLevelUtil {
	public static int getLevel (String str) throws IllegalArgumentException {
        validate(str);
        return recursiveLevel(str);
    }

    private static void validate(String str) throws IllegalArgumentException {
        if(str == null) {
            throw new IllegalArgumentException("str is not null");
        }
        int length = str.length();

        if (length != 6 && length != 9 && length != 12) {
            throw new IllegalArgumentException("字符川的长度只能为6、9、12");
        }
    }

    private static int recursiveLevel(String str) {

        int length = str.length();
        if (length == 9) {
            return 4;
        }

        if (length == 12) {
            return 5;
        }

        if (str.indexOf("00") != -1) {
            str = str.substring(0, str.length() - 2);
            return recursiveLevel(str);
        } else {
            return str.length() / 2;
        }
    }
	
}
