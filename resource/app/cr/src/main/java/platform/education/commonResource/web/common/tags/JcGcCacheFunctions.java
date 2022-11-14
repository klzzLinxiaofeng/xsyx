package platform.education.commonResource.web.common.tags;

import platform.education.commonResource.web.common.util.holder.JcGcCacheServiceHolder;
import platform.education.generalcode.service.JcGcCacheService;

public class JcGcCacheFunctions {
	
	private final static JcGcCacheService jcGcCacheService = JcGcCacheServiceHolder.getInstance().getJcGcCacheService();
	
	public static String getColValue(String tableCode, String value, String colName) {
		Object resultTmp = jcGcCacheService.getColumnValue(tableCode, value, colName);
		String result = null;
		if(resultTmp instanceof String) {
			result = (String) resultTmp;
		}
		return result;
	}
	
}
