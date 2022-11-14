package com.xunyunedu.core.constants;

/**
 * @author jiaxin
 */
public interface SystemCoreConstants {
    /**
     * 对微信access_token加redis分布式锁的key
     */
    String REDIS_LOCK_WECHAT_ACCESS_TOKEN_KEY="lock.wechat.access_toke";

}
