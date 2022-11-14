package platform.education.commonResource.web.common.shiro.listener;

import java.io.Serializable;
import java.util.HashMap;

import org.apache.shiro.session.Session;

/**
 * <p>Title:ShiroSessionContext.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：用于缓存用户session 方便通过sessionId 获取session值
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class ShiroSessionContext {

	private static ShiroSessionContext instance;
	private HashMap<Serializable, Session> hm;

	private ShiroSessionContext() {
		hm = new HashMap<Serializable, Session>();
	}

	public static ShiroSessionContext getInstance() {
		if (instance == null) {
			instance = new ShiroSessionContext();
		}
		return instance;
	}

	public synchronized void AddSession(Session session) {
		if (session != null) {
			hm.put(session.getId(), session);
		}
	}

	public synchronized void delSession(Session session) {
		if (session != null) {
			hm.remove(session.getId());
		}
	}

	public synchronized Session getSession(String session_id) {
		if (session_id == null) {
			return null;
		}
		return (Session) hm.get(session_id);
	}
	
	public HashMap<Serializable, Session> getSessionMap() {
		return this.hm;
	}
}