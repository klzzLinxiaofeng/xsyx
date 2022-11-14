package platform.szxyzxx.web.common.util.holder;

import framework.generic.facility.email.JavaEmailService;

/**
 * 
 * @author panweiliang
 * 
 *         邮箱业务器的持有者
 * 
 */
public class JavaEmailServiceHolder {

	private static JavaEmailServiceHolder instance;
	private JavaEmailService javaEmailService;

	public JavaEmailServiceHolder() {
		instance = this;
	}

	public static JavaEmailServiceHolder getInstance() {
		return instance;
	}

	public JavaEmailService getJavaEmailService() {
		return javaEmailService;
	}

	public void setJavaEmailService(JavaEmailService javaEmailService) {
		this.javaEmailService = javaEmailService;
	}

}
