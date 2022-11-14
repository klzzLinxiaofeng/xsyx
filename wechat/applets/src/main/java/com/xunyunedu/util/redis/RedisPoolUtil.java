package com.xunyunedu.util.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * @author: yhc
 * @Date: 2020/9/19 15:40
 * @Description: redis连接池工具
 */
public class RedisPoolUtil {
    static Logger logger = LoggerFactory.getLogger(RedisPoolUtil.class);

    private static JedisPool jedisPool = null;
    private static String redisConfigFile = "redis.properties";

    /**
     * 不允许通过new创建该类的实例
     */
    private RedisPoolUtil() {
    }

    private static void initPool() {
        logger.info("初始化redis连接池...{}", Thread.currentThread().getName());
        //加载连接池配置文件
        Properties props = new Properties();
        try {
            props.load(RedisPoolUtil.class.getClassLoader().getResourceAsStream(redisConfigFile));
            JedisPoolConfig config = new JedisPoolConfig();

            config.setMaxTotal(Integer.valueOf(props.getProperty("redis.maxTotal")));
            config.setMaxIdle(Integer.valueOf(props.getProperty("redis.maxIdle")));
            config.setMinIdle(Integer.valueOf(props.getProperty("redis.minIdle")));

            config.setTestOnBorrow(Boolean.valueOf(props.getProperty("redis.testOnBorrow")));
            config.setTestOnReturn(Boolean.valueOf(props.getProperty("redis.testOnReturn")));
            //连接耗尽的时候，是否阻塞，false会抛出异常，true阻塞直到超时。默认为true。
            config.setBlockWhenExhausted(true);
            String pwd = props.getProperty("redis.password");
            if (pwd == null || ("").equals(pwd)) {
                jedisPool = new JedisPool(config, props.getProperty("redis.host")
                        , Integer.valueOf(props.getProperty("redis.port")).intValue(),
                        Integer.valueOf(props.getProperty("redis.timeout")).intValue());
            } else {
                jedisPool = new JedisPool(config, props.getProperty("redis.host")
                        , Integer.valueOf(props.getProperty("redis.port")).intValue(),
                        Integer.valueOf(props.getProperty("redis.timeout")).intValue()
                        , pwd);
            }
        } catch (IOException e) {
            logger.error("redis连接异常：{}", e.getMessage());
        }

    }

    /**
     * @param
     * @return Jedis
     * @description 获取redis 连接
     * @date 2020/9/21 13:10
     */
    public synchronized static Jedis getConnect() {
        logger.info("{} 开始获取连接----------------------", Thread.currentThread().getName());
        if (jedisPool == null) {
            initPool();
        }
        Jedis jedis = null;
        try {
            if (jedisPool != null) {
                jedis = jedisPool.getResource();
            }
        } catch (Exception e) {
            logger.error("获取redis连接失败 : {} ", e.toString());
        }
        return jedis;
    }


    /**
     * @param
     * @return
     * @description 关闭redis 连接
     * @date 2020/9/21 13:17
     */
    public static void closeConnect(Jedis jedis) {
        try {
            if (jedis != null) {
                jedis.close();
            }
        } catch (Exception e) {
            logger.error("{} 关闭redis连接: {}", Thread.currentThread().getName(), e.toString());
            if (jedis != null) {
                try {
                    // 2. 客户端主动关闭连接
                    jedis.disconnect();
                } catch (Exception e1) {
                    logger.error("disconnect jedis connection fail: ", e1);
                }
            }
        }
    }

    /**
     * @param
     * @return
     * @description 关闭池
     * @date 2020/9/21 13:21
     */
    public static void closePool() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }


    /**
     * 尝试获取分布式锁
     *
     * @param jedis      Redis客户端
     * @param lockKey    锁
     * @param value      请求标识
     * @param expireTime 超期时间
     * @return 是否获取成功
     */
//    public static boolean tryGetDistributedLock(Jedis jedis, String lockKey, String value, int expireTime) {
//        // key value 是否存在 超时设置 超时时间
//        String result = jedis.set(lockKey, value, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
//
//        if (LOCK_SUCCESS.equals(result)) {
//            return true;
//        }
//        return false;
//
//    }
}
