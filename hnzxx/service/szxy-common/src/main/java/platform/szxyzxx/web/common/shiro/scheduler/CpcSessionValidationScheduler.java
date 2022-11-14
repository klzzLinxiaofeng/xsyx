package platform.szxyzxx.web.common.shiro.scheduler;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.AbstractValidatingSessionManager;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.DefaultSessionManager;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.session.mgt.SessionValidationScheduler;
import org.apache.shiro.session.mgt.ValidatingSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ReflectionUtils;

import platform.szxyzxx.web.common.shiro.listener.ShiroSessionContext;
/**
 * <p>Title:CpcSessionValidationScheduler.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：
 * @Author 潘维良
 * @Version 1.0
 * @Date 2014年9月26日
 */
public class CpcSessionValidationScheduler implements SessionValidationScheduler, Runnable {
	
    /** Private internal log instance. */
    private static final Logger log = LoggerFactory.getLogger(CpcSessionValidationScheduler.class);

    ValidatingSessionManager sessionManager;
    private ScheduledExecutorService service;
    private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
    private boolean enabled = false;

    public CpcSessionValidationScheduler() {
        super();
    }

    public ValidatingSessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(ValidatingSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Creates a single thread {@link ScheduledExecutorService} to validate sessions at fixed intervals
     * and enables this scheduler. The executor is created as a daemon thread to allow JVM to shut down
     */
    //TODO Implement an integration test to test for jvm exit as part of the standalone example
    // (so we don't have to change the unit test execution model for the core module)
    public void enableSessionValidation() {
        if (this.interval > 0l) {
            this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    return thread;
                }
            });
            this.service.scheduleAtFixedRate(this, interval, interval, TimeUnit.MILLISECONDS);
            this.enabled = true;
        }
    }

    public void run() {
        if (log.isDebugEnabled()) {
            log.debug("Executing session validation...");
        }
        long startTime = System.currentTimeMillis();
        ShiroSessionContext context = ShiroSessionContext.getInstance();
        Map<Serializable, Session> sessionMap = context.getSessionMap();
        Collection<Session> sessions = sessionMap.values();
        Iterator<Session> sessionsIt = sessions.iterator();
        while(sessionsIt.hasNext()) {
            Session session = sessionsIt.next();
            Method validateMethod = ReflectionUtils.findMethod(AbstractValidatingSessionManager.class, "validate", Session.class, SessionKey.class);
            validateMethod.setAccessible(true);
            boolean isTimeOut = (boolean) ReflectionUtils.invokeMethod(validateMethod, sessionManager, session, new DefaultSessionKey(session.getId()));
            if(isTimeOut) {
            	session.stop();
            }
        }
        long stopTime = System.currentTimeMillis();
        if (log.isDebugEnabled()) {
            log.debug("Session validation completed successfully in " + (stopTime - startTime) + " milliseconds.");
        }
    }

    public void disableSessionValidation() {
        this.service.shutdownNow();
        this.enabled = false;
    }
	
}
