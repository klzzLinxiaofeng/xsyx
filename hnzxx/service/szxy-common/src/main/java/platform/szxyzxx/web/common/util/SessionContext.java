package platform.szxyzxx.web.common.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

/**
 * 
 * @author panweiliang
 *
 */
public class SessionContext {

	private static SessionContext instance;
	private HashMap<String, HttpSession> hm;

	private SessionContext() {
		hm = new HashMap<String, HttpSession>();
	}

	public static SessionContext getInstance() {
		if (instance == null) {
			instance = new SessionContext();
		}
		return instance;
	}

	public synchronized void AddSession(HttpSession session) {
		if (session != null) {
			hm.put(session.getId(), session);
		}
	}

	public synchronized void delSession(HttpSession session) {
		if (session != null) {
			hm.remove(session.getId());
		}
	}

	public synchronized HttpSession getSession(String session_id) {
		if (session_id == null) {
			return null;
		}
		return (HttpSession) hm.get(session_id);
	}
}