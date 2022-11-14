package com.xunyunedu.wechat.service;


import java.io.IOException;

/**
 * 微信小程序/公众号api
 */
public interface WechatApiService {

    /**
     * 发送订阅消息
     * @param templateId 模板id
     * @param data 模板对应的数据对象
     * @param userOpenId 用户openid
     * @param page 通知跳转地址，为空则不跳转
     * @return 错误信息，为空说明发送成功
     */
    String sendNotice(String templateId, Object data, String userOpenId, String page);
    /**
     * 获取access_token参数
     * @return
     */
    String getAccessToken();

    /**
     * 获取学校公众号access_token
     * @return
     */
    String getPublicAccessToken();

    String httpRequest(String requestUrl, String requestMethod, String output);

    /**
     * 获取wechat配置
     */
    String getWechatUrl(String code);
    /**
     * 获取文章列表
     *
     * @param token
     * @return
     * @throws IOException
     */
    String getContentList(String token, Integer offset, Integer count);
    /**
     * 获取文章列表
     *
     * @param token
     * @return
     * @throws IOException
     */
    String getMaterial(String token, String id);
}
