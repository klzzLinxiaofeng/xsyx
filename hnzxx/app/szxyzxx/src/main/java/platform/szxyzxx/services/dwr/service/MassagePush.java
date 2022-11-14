package platform.szxyzxx.services.dwr.service;

import java.util.Collection;

import org.directwebremoting.Browser;
import org.directwebremoting.ScriptBuffer;
import org.directwebremoting.ScriptSession;

public class MassagePush {
	public static void push(final String data, final String func) throws Exception {
		Runnable run = new Runnable() {
			private ScriptBuffer script = new ScriptBuffer();
			public void run() {
				// 设置要调用的 js及参数
				script.appendCall(func, data);
				// 得到所有ScriptSession
				Collection<ScriptSession> sessions = Browser.getTargetSessions();
				// 遍历每一个ScriptSession
				for (ScriptSession scriptSession : sessions) {
					scriptSession.addScript(script);
				}
			}
		};
		// 执行推送
		Browser.withAllSessions(run);
	}
}
