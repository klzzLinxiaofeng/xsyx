package com.xunyunedu.config.pay.bean;


import com.xunyunedu.util.PropertiesUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 小程序配置
 *
 * @author: yhc
 * @Date: 2020/10/20 14:33
 * @Description:
 */
@Component
@Configuration
public class WxMiniApp {
    /**
     * 设置微信小程序的appid
     */
    private static String appid;

    /**
     * 设置微信小程序的Secret
     */
    private static String secret;

    /**
     * 微信支付mchId
     */
    private static String mchId;

    /**
     * 微信支付mchKey
     */
    private static String mchKey;

    /**
     * sandbox 沙箱测试
     */
    private static String sandbox;

    /**
     * 回调地址 http://ip:port
     */
    private static String domainName;


    /**
     * 数据中心ID
     */
    private static Integer datacenterId;

    /**
     * 终端ID
     */
    private static Integer workerId;

    public WxMiniApp() {
    }

    static {
        String fileName = "application.properties";
        appid = PropertiesUtil.getProperty(fileName, "auth.wechat.appId");
        secret = PropertiesUtil.getProperty(fileName, "auth.wechat.secret");
        mchId = PropertiesUtil.getProperty(fileName, "pay.mchId");
        mchKey = PropertiesUtil.getProperty(fileName, "pay.mchKey");
//        sandbox = PropertiesUtil.getProperty(fileName, "pay.sandbox");
        domainName = PropertiesUtil.getProperty(fileName, "pay.domainName");
        String datacenter = PropertiesUtil.getProperty(fileName, "pay.datacenterId");
        String worker = PropertiesUtil.getProperty(fileName, "pay.workerId");
        datacenterId = datacenterId != null && !("").equals(datacenter) ? Integer.parseInt(datacenter): 1;
        workerId = worker != null && !("").equals(datacenter) ? Integer.parseInt(worker): 1;
    }

    public String getAppid() {
        return appid;
    }

    public String getSecret() {
        return secret;
    }

    public String getMchId() {
        return mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

//    public String getSandbox() {
//        return sandbox;
//    }

    public String getDomainName() {
        return domainName;
    }

    public Integer getDatacenterId() {
        return datacenterId;
    }

    public Integer getWorkerId() {
        return workerId;
    }
}
