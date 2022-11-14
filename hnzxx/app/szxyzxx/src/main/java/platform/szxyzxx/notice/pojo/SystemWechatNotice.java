package platform.szxyzxx.notice.pojo;

import platform.szxyzxx.notice.constants.SystemNoticeDataIdType;
import platform.szxyzxx.notice.constants.SystemNoticeType;
import platform.szxyzxx.wechat.template.WechatMessageTemplate;

/**
 * 系统和微信通知pojo
 * @author jiaxin
 */
public class SystemWechatNotice {
    /**
     * 系统通知标题，必填
     */
    private String title;

    /**
     * 系统通知内容，必填
     */
    private String content;

    /**
     * 系统通知类型，必填
     */
    private SystemNoticeType systemNoticeType;

    /**
     * 数据id，必填，例如要发送一条学生请假通知，则此处为学生请假申请id
     */
    private String dataId;

    /**
     * dataId对应的数据类别，如学生请假等，必填
     */
    private SystemNoticeDataIdType dataIdType;

    /**
     * 微信通知点击后的跳转地址，非必填,为空不跳转
     */
    private String wechatPage;

    /**
     * 微信订阅消息模板,必填
     */
    private WechatMessageTemplate wechatMessageTemplate;




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public SystemNoticeType getSystemNoticeType() {
        return systemNoticeType;
    }

    public void setSystemNoticeType(SystemNoticeType systemNoticeType) {
        this.systemNoticeType = systemNoticeType;
    }

    public String getDataId() {
        return dataId;
    }

    public void setDataId(String dataId) {
        this.dataId = dataId;
    }

    public String getWechatPage() {
        return wechatPage;
    }

    public void setWechatPage(String wechatPage) {
        this.wechatPage = wechatPage;
    }

    public WechatMessageTemplate getWechatMessageTemplate() {
        return wechatMessageTemplate;
    }

    public void setWechatMessageTemplate(WechatMessageTemplate wechatMessageTemplate) {
        this.wechatMessageTemplate = wechatMessageTemplate;
    }

    public SystemNoticeDataIdType getDataIdType() {
        return dataIdType;
    }

    public void setDataIdType(SystemNoticeDataIdType dataIdType) {
        this.dataIdType = dataIdType;
    }
}
