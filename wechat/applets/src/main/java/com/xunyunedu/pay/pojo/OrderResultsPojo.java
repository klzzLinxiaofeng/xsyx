package com.xunyunedu.pay.pojo;

import java.io.Serializable;

/**
 * 下单返回支付信息,包含订单
 *
 * @author: yhc
 * @Date: 2020/10/25 15:07
 * @Description:
 */
public class OrderResultsPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    private String appId;
    private String timeStamp;
    private String nonceStr;
    /**
     * 由于package为java保留关键字，因此改为packageValue. 前端使用时记得要更改为package
     */
    private String packageValue;
    private String signType;
    private String paySign;
    /**
     * 字段名：商户订单号.
     */
    private String outTradeNo;

    public OrderResultsPojo() {
    }


    public OrderResultsPojo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getPaySign() {
        return paySign;
    }

    public void setPaySign(String paySign) {
        this.paySign = paySign;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    @Override
    public String toString() {
        return "OrderResultPojo{" +
                "appId='" + appId + '\'' +
                ", timeStamp='" + timeStamp + '\'' +
                ", nonceStr='" + nonceStr + '\'' +
                ", packageValue='" + packageValue + '\'' +
                ", signType='" + signType + '\'' +
                ", paySign='" + paySign + '\'' +
                ", outTradeNo='" + outTradeNo + '\'' +
                '}';
    }
}
