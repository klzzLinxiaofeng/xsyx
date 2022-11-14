package platform.education.rest.common.code;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import framework.generic.cache.redis.core.BaseRedisCache;

public class AppValidatorImpl implements AppValidator {

	private BaseRedisCache<?> baseRedisCache;
	
    private Map<String, String> appMaps = new HashMap<String, String>();

    public void setAppMaps(Map<String, String> appMaps) {
        this.appMaps = appMaps;
    }

    @Override
    public boolean validateAppKey(String appKey) {
    	/**
    	Object value = baseRedisCache.getCacheObject(appKey);
    	boolean validate = false;
    	if(value!=null) {
    		validate = true;
    	}
    	return validate;
    	*/
        return appMaps.containsKey(appKey);
    }

    @Override
    public boolean validateSercet(String appKey, String sercet) {
    	/**
    	String sercetSelft = getSecretFromRedis(appKey);
    	return sercet.equals(sercetSelft);
    	*/
        String sercetSelft  = this.appMaps.get(appKey);
        return sercet.equals(sercetSelft);
    }

    @Override
    public String getSercet(String appKey) {
    	/**return getSecretFromRedis(appKey);*/
        return this.appMaps.get(appKey);
    }

	public BaseRedisCache<?> getBaseRedisCache() {
		return baseRedisCache;
	}

	public void setBaseRedisCache(BaseRedisCache<?> baseRedisCache) {
		this.baseRedisCache = baseRedisCache;
	}
	
	private String getSecretFromRedis(String appKey) {
		Object value = baseRedisCache.getCacheObject(appKey);
    	if(value==null) {
    		return null;
    	}
    	JSONObject result = JSON.parseObject(value.toString());
    	String appSecret = (String) result.get("appSecret");
    	return appSecret;
	}
}
