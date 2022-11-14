package platform.education.paper.util;

/**
 * 字符串工具类
 * @author pantq
 */
public class MqtStringUtil {


	/**
	 * 判断字符串是否为空
	 * @param str 
	 * @return
	 */
	public static boolean isNull(Object str) {  
        if(str == null || "".equals(str) || "null".equals(str)) {  
            return true;  
        } else {  
            return false;  
        }  
    }  
	
}
