package com.xunyunedu.wechat.template;


import com.xunyunedu.notice.pojo.WechatNoticeDataBuilder;

/**
 * 学校通知、个人通知等通知类的微信订阅消息模板
 */
public class NoticeWechatMessageTemplate implements WechatMessageTemplate {

    //private static final String TEMPLATE_ID="QEEb5PR42KL8r2WulvSJ9uQxEXQ1l2qN8M_xkkKg3U4";
    private static final String TEMPLATE_ID="muL0ojfdgjVAoa2BhO0bfywcIDM_Hx5fxgX5itI5O8I";
    private Object data;
    /**
     *
     * @param receiverType 系统通知类型
     * @param title 通知标题
     */
    public NoticeWechatMessageTemplate(String receiverType, String title){
        //data=new WechatNoticeDataBuilder().addData("thing2",translateReceiverType(receiverType)).addData("thing4",title).getData();
        data=new WechatNoticeDataBuilder().addData("thing2",translateReceiverType(receiverType)).addData("thing5",title).getData();
    }


    @Override
    public String getTemplateId() {
        return TEMPLATE_ID;
    }

    @Override
    public Object getTemplateData() {
        return data;
    }

    private String translateReceiverType(String receiverType){
        if(receiverType.equals("school")){
            return "学校通知";
        }else if(receiverType.equals("dept")){
            return "部门通知";
        }else if(receiverType.equals("person")){
            return "个人通知";
        }else if(receiverType.equals("team")){
            return "班级通知";
        }
        return "";
    }


}
