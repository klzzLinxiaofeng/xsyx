package platform.education.commonResource.web.common.util;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;

import platform.education.commonResource.web.common.contants.SysContants;
import platform.education.commonResource.web.common.vo.UserInfo;

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
		return (UserInfo) session.getAttribute(SysContants.CURRENT_USER);
	}
	
	public static UserInfo getUserInfo(String jsessionId) {
		SessionKey sessionKey = new DefaultSessionKey(jsessionId);
		org.apache.shiro.mgt.SecurityManager sm = SecurityUtils.getSecurityManager();
		Session session = sm.getSession(sessionKey);
		return session != null ? (UserInfo) session.getAttribute(SysContants.CURRENT_USER) : null;
	}
	
	
	public static UserInfo getUserInfoShiro(String jsessionId) {
		SessionKey sessionKey = new DefaultSessionKey(jsessionId);
		org.apache.shiro.mgt.SecurityManager sm = SecurityUtils.getSecurityManager();
		Session session = sm.getSession(sessionKey);
		return session != null ? (UserInfo) session.getAttribute(SysContants.CURRENT_USER) : null;
	}
	
	public static Integer getRelateAppId() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		return session != null ? (Integer) session.getAttribute(SysContants.RELATE_APP_ID_KEY) : null;
	}

	public static void setRelateAppId(Integer relateAppId) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if (session != null) {
			session.setAttribute(SysContants.RELATE_APP_ID_KEY, relateAppId);
		}
	}
	
	public static Integer getRelateSchoolId() {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		return session != null ? (Integer) session.getAttribute(SysContants.RELATE_SCHOOL_ID_KEY) : null;
	}

	public static void setRelateSchoolId(Integer relateSchoolId) {
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		if (session != null) {
			session.setAttribute(SysContants.RELATE_SCHOOL_ID_KEY, relateSchoolId);
		}
	}
	
}

