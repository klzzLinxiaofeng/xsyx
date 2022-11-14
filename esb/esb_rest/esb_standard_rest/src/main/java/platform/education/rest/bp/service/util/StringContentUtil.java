package platform.education.rest.bp.service.util;

public class StringContentUtil {
	/**
	 * 截取消息内容给客户端使用
	 * @param sourceString
	 * @return
	 */
	public static String getString(String sourceString){
		if(sourceString.length()>20){
			return sourceString.substring(0,20);
		} else {
			return sourceString;
		}
	}
	
}
