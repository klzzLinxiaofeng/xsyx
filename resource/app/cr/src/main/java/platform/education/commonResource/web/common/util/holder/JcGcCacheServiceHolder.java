package platform.education.commonResource.web.common.util.holder;

import platform.education.generalcode.service.JcGcCacheService;
/**
 * 
 * @author panweiliang
 * 
 */
public class JcGcCacheServiceHolder {

	private static JcGcCacheServiceHolder instance;
	private JcGcCacheService jcGcCacheService;

	public JcGcCacheServiceHolder() {
		instance = this;
	}

	public static JcGcCacheServiceHolder getInstance() {
		return instance;
	}

	public JcGcCacheService getJcGcCacheService() {
		return jcGcCacheService;
	}

	public void setJcGcCacheService(JcGcCacheService jcGcCacheService) {
		this.jcGcCacheService = jcGcCacheService;
	}

}
