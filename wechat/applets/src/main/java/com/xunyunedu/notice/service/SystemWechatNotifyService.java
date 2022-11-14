package com.xunyunedu.notice.service;



import com.xunyunedu.notice.pojo.SystemWechatNotice;
import com.xunyunedu.wechat.template.WechatMessageTemplate;

import java.util.List;

/**
 * 添加系统通知以及发送微信通知
 * 微信通知文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/subscribe-message/subscribeMessage.send.html
 */
public interface SystemWechatNotifyService {
    /**
     * 发送系统通知和微信通知给指定的用户列表
     * @param notice 通知信息
     * @param receiverList 接收通知的用户信息集合，集合中的对象必须具有“userIdPropertyName”和“openIdPropertyName”指定的属性或者key(map对象则为key)
     * @param userIdPropertyName 用户id在集合对象中对应的属性名
     * @param openIdPropertyName 微信openid在集合对象中对应的属性名
     */
    void sendSystemAndWechatNotice(SystemWechatNotice notice, List receiverList, String userIdPropertyName, String openIdPropertyName);

    /**
     * 发送系统通知和微信通知给指定的用户
     * @param notice 通知信息
     * @param userId 接收通知的用户
     */
    void sendSystemAndWechatNotice(SystemWechatNotice notice, Integer userId);

    /**
     * 发送系统通知和微信通知给指定的用户
     * @param notice 通知信息
     * @param userId 接收用户userId
     * @param openId 接收用户的openId
     */
    void sendSystemAndWechatNotice(SystemWechatNotice notice, Object userId, Object openId);
    /**
     * 发送微信通知给指定的用户列表
     * @param template 微信订阅消息模板
     * @param receiverList 接收通知的用户信息集合，集合中的对象必须具有“userIdPropertyName”和“openIdPropertyName”指定的属性或者key(map对象则为key)
     * @param openIdPropertyName 微信openid在集合对象中对应的属性名
     * @param page 通知点击后打开的链接地址，为空则不打开链接
     */
    void sendWechatNotice(WechatMessageTemplate template, List receiverList, String openIdPropertyName, String page);
    /**
     * 发送微信通知给指定的用户
     * @param template 微信订阅消息模板
     * @param openId 接收用户的openId
     * @param page 通知点击后打开的链接地址，为空则不打开链接
     */
    void sendWechatNotice(WechatMessageTemplate template, String openId, String page);
}
