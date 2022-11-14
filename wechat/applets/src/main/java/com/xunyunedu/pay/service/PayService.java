package com.xunyunedu.pay.service;

import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.xunyunedu.pay.pojo.PayAmountPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/10/20 16:17
 * @Description:
 */
public interface PayService {

    WxPayUnifiedOrderRequest configOrderInfor(PayOrderPojo payOrderPojo);

    String payNotify(String xmlData);

    String payNotifyXuanke(String xmlData);

    WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo);

    int update(PayOrderPojo orderInfoByNumber);

    int updateXuanke(PayOrderPojo orderInfoByNumber);

    List<PayAmountPojo> getAmount(PayAmountPojo payAmountPojo);
     WxPayUnifiedOrderRequest configXuanke(PayOrderPojo payOrderPojo);
    PayOrderPojo getdingdan(String asda);
    /*
    * 获取所有已支付，未发送到食堂的订单
    */
    List<PayOrderPojo> findByAll();
    Integer updateASFAS(Long id);
    Integer postData(PayOrderPojo order);

}
