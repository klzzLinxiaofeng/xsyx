package platform.szxyzxx.wechat.service.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.utils.httpclient.HttpClientUtils;
import platform.szxyzxx.contants.SystemCoreConstants;
import platform.szxyzxx.core.RedisDistributedLock;
import platform.szxyzxx.wechat.service.WechatApiService;
import platform.szxyzxx.web.teach.util.PropertiesUtil;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WechatApiServiceImpl implements WechatApiService {

    Logger logger= LoggerFactory.getLogger(WechatApiServiceImpl.class);

    @Autowired
    RedisTemplate<String,String> redisTemplate;

    private RedisDistributedLock lock=new RedisDistributedLock(SystemCoreConstants.REDIS_LOCK_WECHAT_ACCESS_TOKEN_KEY);

    private static final String PROPERTY_FILE_NAME="wechat.properties";

    private static final String REDIS_ACCESS_TOKEN_KEY="wechat:api:accessToken";

    private static final String APP_ID= PropertiesUtil.getProperty(PROPERTY_FILE_NAME,"auth.wechat.appId");

    private static final String SECRET=PropertiesUtil.getProperty(PROPERTY_FILE_NAME,"auth.wechat.secret");

    @Override
    public String sendNotice(String templateId, Object data, String userOpenId, String page) {
        String token=getAccessToken();

        Map<String,Object> params=new HashMap<>(4,1);
        params.put("touser",userOpenId);
        params.put("template_id",templateId);
        params.put("data",data);
        if(page!=null){
            params.put("page",page);
        }
        String dataJson=JSONUtil.toJsonStr(params);
        String resultJson=HttpClientUtils.postJson("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+token,dataJson);
        JSONObject resultJSonObj=JSONUtil.parseObj(resultJson);
        if(resultJson==null){
            return "发送微信订阅通知失败";
        }
        Object errcode=resultJSonObj.get("errcode");
        if(errcode==null || !errcode.equals(0)){
            logger.error("微信通知发送失败：openId:["+userOpenId+"],templateId:["+templateId+"],data:["+dataJson+"],responseJson："+resultJson);
            return (String) resultJSonObj.get("errmsg");
        }
        return null;
    }

    @Override
    public String getAccessToken() {
        String lockVal=null;
        try {
            String token=getAccessTokenFromRedis();
            if(token!=null){
                return token;
            }
            //加锁防止多个线程调用微信API刷新access_token，因为每次调用微信API接口会导致之前的access_token失效
            // 之所以用分布式锁，因为会有applets和zxx两个独立部署的项目使用同样的access_token
            lockVal=lock.lock(redisTemplate,60*1000,10*1000,100);
            if(lockVal==null){
                throw new RuntimeException("获取accessToken分布式锁失败");
            }

            //double check
            token=getAccessTokenFromRedis();
            if(token!=null){
                return token;
            }
            logger.info("开始获取微信access_ken");
            //此处应该设置响应超时时间，且应该小于分布式锁的过期时间，如果此处等待时间超过了分布式锁的过期时间，还是可能会将过期token保存至redis
            String resultJson=HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+APP_ID+"&secret="+SECRET);
            if(resultJson!=null){
                JSONObject jsonObj=JSONUtil.parseObj(resultJson);
                Integer errcode=(Integer)jsonObj.get("errcode");
                if(errcode!=null && errcode!=0){
                    logger.info("获取微信access_ken失败,响应体："+resultJson);
                    return null;
                }
                token=(String)jsonObj.get("access_token");
                //过期时间，单位秒
                Integer expires=new Integer(jsonObj.get("expires_in").toString());
                //提前一分钟过期，保守一点，防止token过期时间比微信晚
                setAccessTokenToRedis(token,expires-60);
                return token;
            }
            throw new RuntimeException("获取微信access_ken失败");
        } catch (Exception e) {
            throw new RuntimeException("获取微信access_ken异常",e);
        }finally {
            if( lockVal!=null){
                lock.release(redisTemplate,lockVal);
            }
        }
    }

    private String getAccessTokenFromRedis(){
        //之所以不直接使用RedisTemplate的opsForValue方法，是因为目前StringRedisTemplate的valueSerializer为JdkSerializationRedisSerializer
        return redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                return jedis.get(REDIS_ACCESS_TOKEN_KEY);
            }
        });
    }

    private void setAccessTokenToRedis(final String accessToken,final int expires){
        //之所以不直接使用RedisTemplate的opsForValue方法，是因为目前StringRedisTemplate的valueSerializer为JdkSerializationRedisSerializer
        redisTemplate.execute(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection connection) throws DataAccessException {
                Jedis jedis = (Jedis) connection.getNativeConnection();
                jedis.set(REDIS_ACCESS_TOKEN_KEY,accessToken,"NX", "EX", expires);
                return null;
            }
        });
    }

}
