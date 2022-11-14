package platform.szxyzxx.web.common.util;
/**
 * <p>Title:StringUnderlineHandler.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：取代字符串下划线并首字母大写
 * @Author 谭扬
 * @Version 1.0
 * @Date 2014年9月17日
 */
public class StringUnderlineHandler {
	public static String replaceUnderlineAndfirstToUpper(String srcStr,
			String org, String ob) {
		String newString = "";
		int first = 0;
		srcStr=srcStr.toLowerCase();
		while (srcStr.indexOf(org) != -1) {
			first = srcStr.indexOf(org);
			if (first != srcStr.length()) {
				newString = newString + srcStr.substring(0, first) + ob;
				srcStr = srcStr
						.substring(first + org.length(), srcStr.length());
				srcStr = firstCharacterToUpper(srcStr);
			}
		}
		newString = newString + srcStr;
		return newString;
	}
	
	/** 
	* 首字母大写 
	* @param srcStr 
	* @return 
	*/  
	public static String firstCharacterToUpper(String srcStr) {  
	   srcStr=srcStr.toLowerCase();
	   return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);  
	}  
}
