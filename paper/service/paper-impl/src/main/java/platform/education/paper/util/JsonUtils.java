/**   
* @Title: JsonUtils.java
* @Package platform.education.paper.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月23日 下午3:29:18 
* @version V1.0   
*/
package platform.education.paper.util;

import org.codehaus.jackson.map.ObjectMapper;

/** 
* @ClassName: JsonUtils 
* @Description: JSON工具类
* @author pantq
* @date 2017年2月23日 下午3:29:18 
*  
*/
public class JsonUtils {

	public static final ObjectMapper JSON_MAPPER = newObjectMapper(), JSON_MAPPER_WEB = newObjectMapper();

	private static ObjectMapper newObjectMapper() {
		ObjectMapper result = new ObjectMapper();
		return result;
	}

	public static ObjectMapper getObjectMapper() {
		return JSON_MAPPER;
	}
	
}
