package com.xunyunedu.wechat.template;

/**
 * 微信订阅消息模板
 */
public interface WechatMessageTemplate {
    /**
     * 返回微信订阅消息模板id
     * @return
     */
    String getTemplateId();

    /**
     * 返回订阅消息模板对应的data对象
     * @return
     */
    Object getTemplateData();

}
