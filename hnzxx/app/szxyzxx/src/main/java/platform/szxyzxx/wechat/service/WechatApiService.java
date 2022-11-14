package platform.szxyzxx.wechat.service;


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

}
