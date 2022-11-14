package platform.education.commonResource.web.common.tags;

import platform.education.commonResource.web.common.util.holder.JcCacheServiceHolder;
import platform.education.generalcode.service.JcCacheService;

public class JcCacheFunctions {
	
	private final static JcCacheService jcCacheService = JcCacheServiceHolder.getInstance().getJcCacheService();
	
	public static String getValue(String tn, String paramName, String value, String echoField) {
		Object val = jcCacheService.findUniqueByParam(tn, paramName, value, echoField);
		String result = null;
		if(val instanceof String) {
			result = (String) val;
		}
		return result;
	}
	
}
