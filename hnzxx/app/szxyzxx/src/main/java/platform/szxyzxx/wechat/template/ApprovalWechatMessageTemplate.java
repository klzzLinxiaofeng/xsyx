package platform.szxyzxx.wechat.template;

import platform.szxyzxx.wechat.pojo.WechatNoticeDataBuilder;

/**
 * 审批类微信订阅消息模板
 */
public class ApprovalWechatMessageTemplate implements WechatMessageTemplate {

    //private static final String TEMPLATE_ID="c-PkomPKgaUNBPn6-UPNUeUn59wl82g18SmygPkHcjU";
    private static final String TEMPLATE_ID="muL0ojfdgjVAoa2BhO0bfywcIDM_Hx5fxgX5itI5O8I";

    private Object data;
    /**
     *
     * @param typeName 审批类型名称，例如请假审批
     * @param approvalDetail 审批详情，例如你的xx请假审批已通过
     */
    public ApprovalWechatMessageTemplate(String typeName, String approvalDetail){
        //data=new WechatNoticeDataBuilder().addData("thing2",typeName).addData("thing4",approvalDetail).getData();
        data=new WechatNoticeDataBuilder().addData("thing2",typeName).addData("thing5",approvalDetail).getData();
    }


    @Override
    public String getTemplateId() {
        return TEMPLATE_ID;
    }

    @Override
    public Object getTemplateData() {
        return data;
    }


}
