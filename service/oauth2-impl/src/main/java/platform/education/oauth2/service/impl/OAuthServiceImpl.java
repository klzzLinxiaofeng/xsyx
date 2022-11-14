package platform.education.oauth2.service.impl;

import com.alibaba.fastjson.JSONObject;
import framework.generic.cache.redis.core.BaseRedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.oauth2.service.OAuthService;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/1/18.
 */
public class OAuthServiceImpl implements OAuthService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private Map<String,String> appKeyAndAppSercetMap = new HashMap<String,String>();

    @Autowired
    @Qualifier("baseRedisCache")
    private BaseRedisCache baseRedisCache;


    public void setAppKeyAndAppSercetMap(Map<String, String> appKeyAndAppSercetMap) {
        this.appKeyAndAppSercetMap = appKeyAndAppSercetMap;
    }

    @Override
    public void addCode(String code, String value, Long timeout) {
        baseRedisCache.setCacheObject(generateCode(code), value, timeout);
    }

    @Override
    public String getValueByCode(String code) {
        return (String) baseRedisCache.getCacheObject(generateCode(code));
    }

    @Override
    public void removeCode(String code) {
        baseRedisCache.evict(generateCode(code));
    }

    @Override
    public boolean checkCode(String code) {
        return baseRedisCache.getCacheObject(generateCode(code)) != null;
    }

    @Override
    public void addAccessToken(String token, String value, Long timeout) {
        baseRedisCache.setCacheObject(generateToken(token), value, timeout);
    }

    @Override
    public String getValueByAccessToken(String token) {
        return (String) baseRedisCache.getCacheObject(generateToken(token));
    }

    @Override
    public void removeAccessToken(String token) {
        baseRedisCache.evict(generateToken(token));
    }

    @Override
    public boolean checkAccessToken(String token) {
        return baseRedisCache.getCacheObject(generateToken(token)) != null;
    }


    @Override
    public void addSingleSignOnAccessToken(String token, String value, Long timeout) {
        baseRedisCache.setCacheObject(generateSingleSignOnAccessToken(token), value, timeout);
    }

    @Override
    public String getValueBySingleSignOnAccessToken(String token) {
        return (String) baseRedisCache.getCacheObject(generateSingleSignOnAccessToken(token));
    }

    @Override
    public void removeSingleSignOnAccessToken(String token) {
        baseRedisCache.evict(generateSingleSignOnAccessToken(token));
    }

    @Override
    public boolean checkSingleSignOnAccessToken(String token) {
        return baseRedisCache.getCacheObject(generateSingleSignOnAccessToken(token)) != null;
    }


    @Override
    public void addRefreshAccessToken(String token, String value, Long timeout) {
        baseRedisCache.setCacheObject(generateRefreshAccessToken(token), value, timeout);
    }

    @Override
    public String getValueByRefreshAccessToken(String token) {
        return (String) baseRedisCache.getCacheObject(generateRefreshAccessToken(token));
    }

    @Override
    public boolean checkRefreshAccessToken(String token) {
        return baseRedisCache.getCacheObject(generateRefreshAccessToken(token)) != null;
    }

    @Override
    public long getRefreshAcessTokenOfExpire(String token) {
        return baseRedisCache.getCacheObjectExpire(generateRefreshAccessToken(token));
    }


    @Override
    public void addRefreshSingleSignOnAccessToken(String token, String value, Long timeout) {
        baseRedisCache.setCacheObject(generateRefreshSingleSignOnAccessToken(token), value, timeout);
    }

    @Override
    public String getValueByRefreshSingleSignOnAccessToken(String token) {
        return (String) baseRedisCache.getCacheObject(generateRefreshSingleSignOnAccessToken(token));
    }

    @Override
    public boolean checkRefreshSingleSignOnAccessToken(String token) {
        return baseRedisCache.getCacheObject(generateRefreshSingleSignOnAccessToken(token)) != null;
    }

    @Override
    public long getRefreshSingleSignOnAcessTokenOfExpire(String token) {
        return baseRedisCache.getCacheObjectExpire(generateRefreshSingleSignOnAccessToken(token));
    }

    @Override
    public boolean checkClientId(String clientId) {
        return appKeyAndAppSercetMap.containsKey(clientId);
    }

    @Override
    public boolean checkClientSecret(String clientId, String clientSecret) {
        String clientSecretVal = this.appKeyAndAppSercetMap.get(clientId);
        JSONObject jsonObject = JSONObject.parseObject(clientSecretVal);
        String appSercet = (String) jsonObject.get("appSercet");
        return clientSecretVal != null && clientSecret.equals(appSercet);
    }

    @Override
    public String getClientName(String clientId) {
        String clientSecretVal = this.appKeyAndAppSercetMap.get(clientId);
        JSONObject jsonObject = JSONObject.parseObject(clientSecretVal);
        return (String) jsonObject.get("appName");
    }


    private String generateCode(String code) {
        return "oauth2_code_" + code;
    }

    private String generateToken(String token) {
        return "oauth2_accessToken_" + token;
    }

    private String generateRefreshAccessToken(String token) {
        return "oauth2_refreshAccessToken_" + token;
    }

    private String generateSingleSignOnAccessToken(String token) {
        return "SingleSignOn_accessToken_" + token;
    }

    private String generateRefreshSingleSignOnAccessToken(String token) {
        return "SingleSignOn_refreshAccessToken_" + token;
    }

}
