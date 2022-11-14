/**
 *
 */
package platform.education.lads.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * <font color="#AA2222"><b>String工具类</b></font><br />
 *
 * @Company 中国协同教学(集团)有限公司
 * @author Jacken(陈文光<chenjacken@gmail.com>)
 * @version 1.0 2010-11-25
 * @CreateDate 2010-11-25
 */
public class StrUtils {

    /**
     * <b>检查对象是否为null</b><br /> 返回： 如果对象不是
     * <code>null<code>也不是<code>""
     * <code>返回  true，否则返回 false
     *
     * @param o
     *            Object
     * @return boolean
     */
    public static boolean isNotNull(Object o) {
        if (o != null && !o.equals("")) {
            return true;
        }
        return false;
    }

    public static boolean checkChinese(String s) {
        s = new String(s.getBytes());//用GBK编码
        String pattern = "[\u4e00-\u9fa5]+";
        Pattern p = Pattern.compile(pattern);
        Matcher result = p.matcher(s);
        return result.find(); //是否含有中文字符
    }

    /**
     * 返回截取字符串，根据自定义长度截取
     *
     * @param sourceString 要截取的字符串
     * @param maxLength 要截取的长度 25
     * @return String
     */
    public static String subString(String sourceString, int maxLength) {
        String resultString = "";
        if (sourceString == null || sourceString.equals("") || maxLength < 1) {
            return resultString;
        } else if (sourceString.length() <= maxLength) {
            return sourceString;
        } else if (sourceString.length() > 2 * maxLength) {
            sourceString = sourceString.substring(0, 2 * maxLength);
        }

        if (sourceString.length() > maxLength) {
            char[] chr = sourceString.toCharArray();
            int strNum = 0;
            int strGBKNum = 0;
            boolean isHaveDot = false;

            for (int i = 0; i < sourceString.length(); i++) {
                if (chr[i] >= 0xa1) // 0xa1汉字最小位开始
                {
                    strNum = strNum + 2;
                    strGBKNum++;
                } else {
                    strNum++;
                }

                if (strNum == 2 * maxLength || strNum == 2 * maxLength + 1) {
                    if (i + 1 < sourceString.length()) {
                        isHaveDot = true;
                    }

                    break;
                }
            }

            resultString = sourceString.substring(0, strNum - strGBKNum);
            if (resultString.length() > 26) {
                resultString = resultString + "...";
            }
        }

        return resultString;
    }

    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String doubleTrans(double d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

    /**
     * 删除input字符串中的html格式
     *
     * @param input
     * @param length
     * @return
     */
    public static String filterHtml(String input, int length) {
        if (input == null || input.trim().equals("")) {
            return "";
        }
        // 去掉所有html元素,     
        String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
                "<[^>]*>", "");
        str = str.replaceAll("[(/>)<]", "");
        int len = str.length();
        if (len <= length) {
            return str;
        } else {
            str = str.substring(0, length);
            str += "......";
        }
        
        return str;
    }
}
