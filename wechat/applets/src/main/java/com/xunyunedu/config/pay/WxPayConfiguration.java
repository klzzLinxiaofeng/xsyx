package com.xunyunedu.config.pay;

import cn.hutool.core.lang.Snowflake;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.service.impl.WxPayServiceImpl;
import com.xunyunedu.config.pay.bean.WxMiniApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信公众号配置文件
 *
 * @author: yhc
 * @Date: 2020/10/20 14:44
 * @Description:
 */
@Configuration
public class WxPayConfiguration {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    public WxPayConfiguration() {
    }

    @Autowired
    private WxMiniApp wxMiniApp;

    @Bean
    public WxPayService wxPayService() {
        return getWxMpPayServiceByAppId(wxMiniApp.getAppid());
    }

    @Bean
    public Snowflake snowflake() {
        return new Snowflake(wxMiniApp.getWorkerId(), wxMiniApp.getDatacenterId());
    }

    /**
     * 配置支付参数信息
     *
     * @param appid
     * @return
     */
    private WxPayService getWxMpPayServiceByAppId(String appid) {
        WxPayConfig payConfig = new WxPayConfig();
        payConfig.setAppId(appid);
        payConfig.setMchId(wxMiniApp.getMchId());
        payConfig.setMchKey(wxMiniApp.getMchKey());
        payConfig.setSignType(WxPayConstants.SignType.MD5);
        payConfig.setTradeType(WxPayConstants.TradeType.JSAPI);
        payConfig.setUseSandboxEnv(false);
        WxPayService wxPayService = new WxPayServiceImpl();

        wxPayService.setConfig(payConfig);
        return wxPayService;
    }



}
