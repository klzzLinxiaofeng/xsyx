package platform.szxyzxx.web.common.contants;

import platform.szxyzxx.web.common.util.CasConfigAccessor;

public class CasConfig {
	
	private final static String CAS_ENABLE_KEY = "cas.enable";
	public final static boolean CAS_ENABLE = CasConfigAccessor.getBooleanProp(CAS_ENABLE_KEY);
	
	private final static String LOGIN_URL_KEY = "login.url";
	public final static String LOGIN_URL = CasConfigAccessor.getStringProp(LOGIN_URL_KEY);
	
	private final static String CAS_SERVER_URL_PREFIX_KEY = "cas.server.url.prefix";
	public final static String CAS_SERVER_URL_PREFIX = CasConfigAccessor.getStringProp(CAS_SERVER_URL_PREFIX_KEY);
	
	private final static String CAS_SERVICE_KEY = "cas.service";
	public final static String CAS_SERVICE = CasConfigAccessor.getStringProp(CAS_SERVICE_KEY);
	
	private final static String CAS_LOGOUT_REDIRECT_URL_KEY = "cas.logout.redirect.url";
	public final static String CAS_LOGOUT_REDIRECT_URL = CasConfigAccessor.getStringProp(CAS_LOGOUT_REDIRECT_URL_KEY);

	private final static String CAS_BASE_PAHT_KEY = "cas.base.path";
	public final static String CAS_BASE_PATH = CasConfigAccessor.getStringProp(CAS_BASE_PAHT_KEY);
	
	public static void main(String[] args) {
		System.out.println(CasConfig.CAS_SERVICE);
	}
	
}
