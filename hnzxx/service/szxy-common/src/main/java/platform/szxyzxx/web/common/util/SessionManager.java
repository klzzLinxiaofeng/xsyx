package platform.szxyzxx.web.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;

import platform.szxyzxx.web.common.contants.InfoCode;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * 
 * @author panweiliang
 *
 */
public class SessionManager {
	
	public static Object getAttribute(Object key){
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		return session.getAttribute(key);
	}
	
	public static UserInfo getUserInfo() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		return (UserInfo) session.getAttribute(InfoCode.CURRENT_USER);
	}
	
	public static UserInfo getUserInfo(String jsessionId) {
		SessionKey sessionKey = new DefaultSessionKey(jsessionId);
		org.apache.shiro.mgt.SecurityManager sm = SecurityUtils.getSecurityManager();
		Session session = sm.getSession(sessionKey);
		return session != null ? (UserInfo) session.getAttribute(InfoCode.CURRENT_USER) : null;
	}
	
	
	public static UserInfo getUserInfoShiro(String jsessionId) {
		SessionKey sessionKey = new DefaultSessionKey(jsessionId);
		org.apache.shiro.mgt.SecurityManager sm = SecurityUtils.getSecurityManager();
		Session session = sm.getSession(sessionKey);
		return session != null ? (UserInfo) session.getAttribute(InfoCode.CURRENT_USER) : null;
	}
}
