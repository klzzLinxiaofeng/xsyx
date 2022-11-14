package platform.szxyzxx.web.common.shiro.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;
/**
 * <p>Title:ShiroSessionListener.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：session侦听器 主要用于管理 session缓存
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class ShiroSessionListener implements SessionListener {

//	public static Map<String, Session> userMap = new HashMap<String, Session>();
//	private ShiroSessionContext sc = ShiroSessionContext.getInstance();
	
	@Override
	public void onExpiration(Session session) {
//		sc.delSession(session);
	}

	@Override
	public void onStart(Session session) {
//		sc.AddSession(session);
	}

	@Override
	public void onStop(Session session) {
//		sc.delSession(session);
	}

}
