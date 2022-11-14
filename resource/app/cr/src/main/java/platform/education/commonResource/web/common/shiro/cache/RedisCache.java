package platform.education.commonResource.web.common.shiro.cache;

import framework.generic.cache.redis.core.BaseRedisCache;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.util.SerializationUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by clouds on 2017/8/9.
 */
public class RedisCache<K, V> implements Cache<K, V> {

    private Logger logger;
    private BaseRedisCache<V> baseRedisCache;
    private String keyPrefix;
    private Long expire;

    public String getKeyPrefix() {
        return this.keyPrefix;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }

    public RedisCache(BaseRedisCache baseRedisCache) {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.keyPrefix = "shiro_redis_session:";
        if(baseRedisCache == null) {
            throw new IllegalArgumentException("Cache argument cannot be null.");
        } else {
            this.baseRedisCache = baseRedisCache;
        }
    }

    public RedisCache(BaseRedisCache baseRedisCache, String prefix) {
        this(baseRedisCache);
        this.keyPrefix = prefix;
    }

    public RedisCache(BaseRedisCache baseRedisCache, String prefix, Long expire) {
        this(baseRedisCache);
        this.keyPrefix = prefix;
        this.expire = expire;
    }

    public V get(K key) throws CacheException {
        this.logger.debug("根据key从Redis中获取对象 key [" + key + "]");
        try {
            if(key == null) {
                return null;
            } else {
                V v = null;
                if(key instanceof String) {
                    String preKey = this.keyPrefix + key;
                    v = this.baseRedisCache.getCacheObject(preKey);
                } else {

                    final byte[] preKey = SerializationUtils.serialize(key);

                    v = (V) this.baseRedisCache.getStringRedisTemplate().execute(new RedisCallback() {
                        @Override
                        public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {

                            return SerializationUtils.deserialize(redisConnection.get(preKey));

                        }
                    });
                }
                return v;
            }
        } catch (Throwable var4) {
            throw new CacheException(var4);
        }
    }

    public V put(K key, V value) throws CacheException {
        this.logger.debug("根据key从存储 key [" + key + "]");
        try {

            if (key instanceof String) {
                String preKey =  this.keyPrefix + key;
                this.baseRedisCache.setCacheObject(preKey, value, expire);
            } else {

                final byte[] preKey = SerializationUtils.serialize(key);
                final byte[] preVal = SerializationUtils.serialize(value);
                final Long expireFinal = expire;

                this.baseRedisCache.getStringRedisTemplate().execute(new RedisCallback() {
                    @Override
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        redisConnection.set(preKey, preVal);
                        return null;
                    }
                });
                this.baseRedisCache.getRedisTemplate().expire(preKey, expire, TimeUnit.SECONDS);

            }
            return value;
        } catch (Throwable var4) {
            throw new CacheException(var4);
        }
    }

    public V remove(K key) throws CacheException {
        this.logger.debug("从redis中删除 key [" + key + "]");

        try {
            V t = this.get(key);
            if (key instanceof String) {
                String preKey = this.keyPrefix + key;
                this.baseRedisCache.evict(preKey);
            } else {
                final byte[] preKey = SerializationUtils.serialize(key);
                this.baseRedisCache.getStringRedisTemplate().execute(new RedisCallback() {
                    @Override
                    public Object doInRedis(RedisConnection redisConnection) throws DataAccessException {
                        redisConnection.del(preKey);
                        return null;
                    }
                });
            }
            return t;
        } catch (Throwable var3) {
            throw new CacheException(var3);
        }
    }

    public void clear() throws CacheException {
        try {
            Set<String> t = this.baseRedisCache.getStringRedisTemplate().keys(this.keyPrefix + "*");
            if(!CollectionUtils.isEmpty(t)) {
                Iterator<String> i$ = t.iterator();
                while(i$.hasNext()) {
                    this.baseRedisCache.evict(i$.next());
                }

            }
        } catch (Throwable var6) {
            throw new CacheException(var6);
        }
    }

    public int size() {
        Set t = this.baseRedisCache.getStringRedisTemplate().keys(this.keyPrefix + "*");
        return t.size();
    }

    public Set<K> keys() {
        try {
            Set t = this.baseRedisCache.getStringRedisTemplate().keys(this.keyPrefix + "*");
            return t;
        } catch (Throwable var5) {
            throw new CacheException(var5);
        }

    }

    public Collection<V> values() {
        try {
            Set<K> t = this.baseRedisCache.getStringRedisTemplate().keys(this.keyPrefix + "*");
            if(!CollectionUtils.isEmpty(t)) {
                ArrayList values = new ArrayList(t.size());
                Iterator<K> i$ = t.iterator();

                while(i$.hasNext()) {
                    Object value = this.get(i$.next());
                    if(value != null) {
                        values.add(value);
                    }
                }

                return Collections.unmodifiableList(values);
            } else {
                return Collections.emptyList();
            }
        } catch (Throwable var6) {
            throw new CacheException(var6);
        }
    }
}
