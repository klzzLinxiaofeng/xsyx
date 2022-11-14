package platform.szxyzxx.web.common.listener;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import platform.szxyzxx.web.common.util.SessionContext;

/**
 * 
 * <p>Title:SessionListener.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：session侦听器
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年7月25日
 */
public class SessionListener implements HttpSessionListener {

	public static Map<String, HttpSession> userMap = new HashMap<String, HttpSession>();
	private SessionContext sc = SessionContext.getInstance();

	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		sc.AddSession(httpSessionEvent.getSession());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		HttpSession session = httpSessionEvent.getSession();
		sc.delSession(session);
	}
}
