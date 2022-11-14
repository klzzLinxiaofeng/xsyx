package com.xunyunedu.util;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;

/**
 * @author: yhc
 * @Date: 2020/9/19 9:44
 * @Description: 微信工具类
 */
public class WeChatUtil {
    static Logger logger = LoggerFactory.getLogger(WeChatUtil.class);
    private static String sessionHost;
    private static String appId;
    private static String secret;
    private static String grantType;
    // 公众号
    private static String publicAppId;
    private static String publicSecret;

    static {
        String fileName = "application.properties";
        sessionHost = PropertiesUtil.getProperty(fileName, "auth.wechat.sessionHost");
        appId = PropertiesUtil.getProperty(fileName, "auth.wechat.appId");
        secret = PropertiesUtil.getProperty(fileName, "auth.wechat.secret");
        grantType = PropertiesUtil.getProperty(fileName, "auth.wechat.grantType");

        publicAppId = PropertiesUtil.getProperty(fileName, "public.wechat.appId");
        publicSecret = PropertiesUtil.getProperty(fileName, "public.wechat.secret");
    }

    public static String httpRequest(String requestUrl, String requestMethod, String output) {
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
    public static String getWechatUrl(String code) {
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
     * 获取微信公众号access_token
     *
     * @return
     */
    public static String getAccessToken() {

        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential" + "&appid=" + publicAppId + "&secret=" + publicSecret;
        String str = WeChatUtil.httpRequest(url, "GET", null);
        JSONObject jsonObject = JSON.parseObject(str);
        return jsonObject.get("access_token").toString();
    }

    /**
     * 获取文章列表
     *
     * @param token
     * @return
     * @throws IOException
     */
    public static String getContentList(String token, Integer offset, Integer count) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=" + token;
        HttpRequestConfig config = HttpRequestConfig.create().url(url)
                .addHeader("Content-Type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        HttpRequestResult httpRequestResult = null;
        JSONObject param = new JSONObject();
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
    public static String getMaterial(String token, String id) {
        String url = "https://api.weixin.qq.com/cgi-bin/material/get_material?access_token=" + token;
        HttpRequestConfig config = HttpRequestConfig.create().url(url)
                .addHeader("Content-Type", "application/json")
                .httpEntityType(HttpEntityType.ENTITY_STRING);
        HttpRequestResult httpRequestResult = null;
        JSONObject param = new JSONObject();
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