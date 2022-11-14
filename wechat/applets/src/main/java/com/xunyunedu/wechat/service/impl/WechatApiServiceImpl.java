package com.xunyunedu.wechat.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.xunyunedu.core.RedisDistributedLock;
import com.xunyunedu.core.constants.SystemCoreConstants;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.util.WeChatUtil;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import com.xunyunedu.wechat.service.WechatApiService;
import com.xunyunedu.util.PropertiesUtil;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.redis.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author jiaxin
 */
@Service
public class WechatApiServiceImpl implements WechatApiService {

    Logger logger= LoggerFactory.getLogger(WechatApiServiceImpl.class);

    private  RedisDistributedLock lock=new RedisDistributedLock(SystemCoreConstants.REDIS_LOCK_WECHAT_ACCESS_TOKEN_KEY);

    private static final String PROPERTY_FILE_NAME="wechat.properties";

    private static final String REDIS_ACCESS_TOKEN_KEY="wechat:api:accessToken";

    private static final String sessionHost;
    private static final String appId;
    private static final String secret;
    private static final String grantType;
    // 公众号
    private static final String publicAppId;
    private static final String publicSecret;

    static {
        sessionHost = PropertiesUtil.getProperty(PROPERTY_FILE_NAME, "auth.wechat.sessionHost");
        appId = PropertiesUtil.getProperty(PROPERTY_FILE_NAME, "auth.wechat.appId");
        secret = PropertiesUtil.getProperty(PROPERTY_FILE_NAME, "auth.wechat.secret");
        grantType = PropertiesUtil.getProperty(PROPERTY_FILE_NAME, "auth.wechat.grantType");

        publicAppId = PropertiesUtil.getProperty(PROPERTY_FILE_NAME, "public.wechat.appId");
        publicSecret = PropertiesUtil.getProperty(PROPERTY_FILE_NAME, "public.wechat.secret");
    }

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
        String resultJson= HttpClientUtils.postJson("https://api.weixin.qq.com/cgi-bin/message/subscribe/send?access_token="+token,dataJson);
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
        Jedis jedis=null;
        String lockKeyVal=null;
        try {
            jedis=RedisPoolUtil.getConnect();
            String token=jedis.get(REDIS_ACCESS_TOKEN_KEY);
            if(token!=null){
                return token;
            }

            //加锁防止多个线程调用微信API刷新access_token，因为每次调用微信API接口会导致之前的access_token失效
            // 之所以用分布式锁，因为会有applets和zxx两个独立部署的项目使用同样的access_token
            lockKeyVal=lock.lock(jedis,60*1000,10*1000,100);
            if(lockKeyVal==null){
                throw new RuntimeException("获取accessToken分布式锁失败");
            }

            //double check
            token=jedis.get(REDIS_ACCESS_TOKEN_KEY);
            if(token!=null){
                return token;
            }

            logger.info("开始获取微信access_ken");
            //此处应该设置响应超时时间，且应该小于分布式锁的过期时间，如果此处等待时间超过了分布式锁的过期时间，还是可能会将过期token保存至redis
            String resultJson=HttpClientUtils.get("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appId+"&secret="+secret);
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
                jedis.set(REDIS_ACCESS_TOKEN_KEY,token,"NX", "EX", expires-60);
                return token;
            }
            throw new RuntimeException("获取微信access_ken失败");
        } catch (Exception e) {
            throw new RuntimeException("获取微信access_ken异常",e);
        }finally {
            if(jedis!=null){
                if(lockKeyVal!=null) {
                    lock.release(jedis,lockKeyVal);
                }
                jedis.close();
            }
        }
    }


    @Override
    public String getPublicAccessToken() {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" + "&appid=" + publicAppId + "&secret=" + publicSecret;
        String str = httpRequest(url, "GET", null);
        com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.get("access_token").toString();
    }

    @Override
    public String httpRequest(String requestUrl, String requestMethod, String output) {
        try {
            URL url = new URL(requestUrl);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            if (null != output) {
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(output.getBytes("utf-8"));
                outputStream.close();
            }
            // 从输入流读取返回内容
            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            StringBuffer buffer = new StringBuffer();
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            connection.disconnect();
            return buffer.toString();
        } catch (Exception e) {
            logger.error("请求失败：", e);
        }
        return "";
    }


    /**
     * 获取wechat配置
     */
    @Override
    public String getWechatUrl(String code) {
        String url = "";
        if (StrUtil.hasEmpty(sessionHost, appId, secret, grantType)) {
            logger.error("读取配置微信数据失败！");
            throw new BusinessRuntimeException(ResultCode.READ_PROPERTIES_ERROR);
        }
        // 根据小程序的code向这个url发送请求
        url = sessionHost + "?appid=" + appId + "&secret=" + secret + "&js_code=" + code + "&" + grantType;
        return url;
    }

    /**
     * 获取文章列表
     *
     * @param token
     * @return
     * @throws IOException
     */
    @Override
    public String getContentList(String token, Integer offset, Integer count) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + token;
        HttpRequestConfig config = HttpRequestConfig.create().url(url)
                .addHeader("Content-Type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        HttpRequestResult httpRequestResult = null;
        com.alibaba.fastjson.JSONObject param = new com.alibaba.fastjson.JSONObject();
        param.put("type", "news");
        param.put("offset", offset);
        param.put("count", count);
        config.json(param.toJSONString());
        try {
            httpRequestResult = HttpClientUtils.post(config);
            if (httpRequestResult != null) {
                if (httpRequestResult.getCode() == 200) {
                    String responseText = httpRequestResult.getResponseText();
                    if (StrUtil.isNotEmpty(responseText)) {
                        return JSON.parseObject(responseText).toJSONString();
                    }
                }
            }
            logger.error("请求获取微信公众号文章失败！");
        } catch (IOException e) {
            logger.error("请求获取微信公众号文章失败！", e.getMessage());
        }

        return null;
    }


    /**
     * 获取文章列表
     *
     * @param token
     * @return
     * @throws IOException
     */
    @Override
    public String getMaterial(String token, String id) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + token;
        HttpRequestConfig config = HttpRequestConfig.create().url(url)
                .addHeader("Content-Type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        HttpRequestResult httpRequestResult = null;
        com.alibaba.fastjson.JSONObject param = new com.alibaba.fastjson.JSONObject();
        param.put("media_id", id);
        config.json(param.toJSONString());
        try {
            httpRequestResult = HttpClientUtils.post(config);
            if (httpRequestResult != null) {
                if (httpRequestResult.getCode() == 200) {
                    String responseText = httpRequestResult.getResponseText();
                    if (StrUtil.isNotEmpty(responseText)) {
                        return JSON.parseObject(responseText).toJSONString();
                    }
                }
            }
            logger.error("请求获取微信公众号文章详情失败！");
        } catch (IOException e) {
            logger.error("请求获取微信公众号文章详情失败！", e.getMessage());
        }
        return null;
    }
}
