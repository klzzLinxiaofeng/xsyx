package platform.education.commonResource.web.common.shiro.dao;

import framework.generic.cache.redis.core.BaseRedisCache;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by clouds on 2017/8/9.
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
    private String keyPrefix = "shiro_redis_session:";
    private BaseRedisCache<Session> baseRedisCache;
    private long expire = 1800;


    public void setBaseRedisCache(BaseRedisCache<Session> baseRedisCache) {
        this.baseRedisCache = baseRedisCache;
    }

    public void setExpire(long expire) {
        this.expire = expire;
    }

    private void saveSession(Session session) throws UnknownSessionException {
        if(session != null && session.getId() != null) {
            String key = this.getStringKey(session.getId());
            session.setTimeout(expire * 1000);
            baseRedisCache.setCacheObject(key, session, expire);
        } else {
            logger.error("session or session id is null");
        }
    }


    private String getStringKey(Serializable sessionId) {
        String preKey = this.keyPrefix + sessionId;
        return preKey;
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if(sessionId == null) {
            logger.error("session id is null");
            return null;
        } else {
            Session session = this.baseRedisCache.getCacheObject((String) this.getStringKey(sessionId));
            return session;
        }
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        this.saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if(session != null && session.getId() != null) {
            baseRedisCache.evict(this.getStringKey(session.getId()));
        } else {
            logger.error("session or session id is null");
        }
    }

    @Override
    public Collection<Session> getActiveSessions() {

        HashSet sessions = new HashSet();
        Set keys = baseRedisCache.getStringRedisTemplate().keys(this.keyPrefix + "*");
        if(keys != null && keys.size() > 0) {
            Iterator i$ = keys.iterator();

            while(i$.hasNext()) {
                String key = (String)i$.next();
                Session s = this.baseRedisCache.getCacheObject(key);
                sessions.add(s);
            }
        }

        return sessions;
    }
}
