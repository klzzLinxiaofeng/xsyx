package platform.education.commonResource.web.common.shiro.cache;

import framework.generic.cache.redis.core.BaseRedisCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by clouds on 2017/8/9.
 */
public class RedisCacheManager implements CacheManager {

    private static final Logger logger = LoggerFactory.getLogger(RedisCacheManager.class);
    private final ConcurrentMap<String, Cache> caches = new ConcurrentHashMap();
    private BaseRedisCache baseRedisCache;
    private String keyPrefix = "shiro_redis_cache:";
    private Long expire;

    public RedisCacheManager() {
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public <K, V> Cache<K, V> getCache(String name) throws CacheException {
        logger.debug("获取名称为: " + name + " 的RedisCache实例");
        Cache c = this.caches.get(name);
        if(c == null) {
            c = new RedisCache(this.baseRedisCache, this.keyPrefix, this.expire);
            this.caches.put(name, c);
        }
        return c;
    }

    public BaseRedisCache getBaseRedisCache() {
        return baseRedisCache;
    }

    public void setBaseRedisCache(BaseRedisCache baseRedisCache) {
        this.baseRedisCache = baseRedisCache;
    }
}
