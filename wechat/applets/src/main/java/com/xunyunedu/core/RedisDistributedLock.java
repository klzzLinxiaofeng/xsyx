package com.xunyunedu.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.UUID;

/**
 * 单机Redis分布式锁
 * @author jiaxin
 */
public class RedisDistributedLock {

    private static final Logger log= LoggerFactory.getLogger(RedisDistributedLock.class);

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    private String lockKey;

    public RedisDistributedLock(String lockKey) {
        this.lockKey = lockKey;
    }

    /**
     * 尝试获取锁，获取不到会最多等待maxWaitTime+sleepIntervalTime毫秒,只所以将expireTime、maxWaitTime、maxWaitTime作为参数是希望加锁的方法更加灵活通用，如果多出使用同一个lockKey,需要注意参数的一致性
     * @param jedis 加锁所需的jedis连接
     * @param expireTime 锁的过期时间(毫秒)
     * @param maxWaitTime 获取锁时，最大等待时间（毫秒）
     * @param sleepIntervalTime 当锁被占用时，线程每次sleep间隔（毫秒）
     * @return 设置的锁的key对应的value，用于释放锁使用
     */
    public String lock(Jedis jedis,int expireTime,int maxWaitTime,int sleepIntervalTime) {
        try {
            // 超过等待时间，加锁失败
            long waitEnd = System.currentTimeMillis() + maxWaitTime;
            String value = UUID.randomUUID().toString();
            while (System.currentTimeMillis() < waitEnd) {
                String result = jedis.set(lockKey, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                if (LOCK_SUCCESS.equals(result)) {
                    return value;
                }
                try {
                    Thread.sleep(sleepIntervalTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception ex) {
            log.error("lock error", ex);
        }
        return null;
    }

    /**
     * 释放锁
     * @param jedis 释放锁所需的jedis连接
     * @param lockKeyVal 加锁成功时返回的锁对应的value值，只有和当前lockKey的值时一致才会释放锁，即谁拥有锁，谁释放
     * @return 是否释放成功
     */
    public boolean release(Jedis jedis,String lockKeyVal) {
        if (lockKeyVal == null) {
            return false;
        }
        // key存在,且value和lockKeyVal一致时才删除，即谁拥有锁，谁释放
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = null;
        try {
            result = jedis.eval(script, Collections.singletonList(this.lockKey),
                    Collections.singletonList(lockKeyVal));
            if (RELEASE_SUCCESS.equals(result)) {
                log.debug("release lock success, value:{}", lockKeyVal);
                return true;
            }
        } catch (Exception e) {
            log.error("release lock error", e);
        }
        log.info("release lock failed, lockKeyVal:{}, result:{}", lockKeyVal, result);
        return false;
    }


}
