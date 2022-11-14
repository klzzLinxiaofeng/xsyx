package platform.education.commonResource.web.common.util.holder;

import platform.education.generalcode.service.JcCacheService;

public class JcCacheServiceHolder {
	
	private static JcCacheServiceHolder instance;
	private JcCacheService jcCacheService;

	public JcCacheServiceHolder() {
		instance = this;
	}

	public static JcCacheServiceHolder getInstance() {
		return instance;
	}

	public JcCacheService getJcCacheService() {
		return jcCacheService;
	}

	public void setJcCacheService(JcCacheService jcCacheService) {
		this.jcCacheService = jcCacheService;
	}
}
