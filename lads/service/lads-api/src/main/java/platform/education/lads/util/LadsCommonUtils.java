/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package platform.education.lads.util;

import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Administrator
 */
public class LadsCommonUtils {

    public static String createUrlPara(HttpServletRequest request) {
        Map map = request.getParameterMap();
        String urlPara = "{";
        Set<Map.Entry> entryseSet = map.entrySet();
        int i = 0;
        for (Map.Entry entry : entryseSet) {
            if (!entry.getKey().equals("pagination.count") && !entry.getKey().equals("pagination.current")) {
                String value = ((String[]) entry.getValue())[0].toString();
                urlPara = urlPara + entry.getKey() + ":" + "'" + value + "'";
                if (i < entryseSet.size() - 1) {
                    urlPara = urlPara + ",";
                }
            }
            i++;
        }
        urlPara = urlPara + "}";
        return urlPara;
    }

    //判断文字是否是数字
    public static boolean isNumeric(String str) {
        for (int i = str.length(); --i >= 0;) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
