package com.xunyunedu.courseSelection.controller;

import com.github.binarywang.wxpay.exception.WxPayException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment/")
public class WeKeController {

 /*   @PostMapping("/payOrder")
    @Authorization
    public ApiResult pay(@RequestBody PayOrderPojo payOrderPojo) {
//        return new ApiResult(ResultCode.PAY_NOT_ONLINE);
        if (payOrderPojo == null || payOrderPojo.getUserId() == null || payOrderPojo.getPayAmountId() == null || payOrderPojo.getOpenid() == null
                || payOrderPojo.getSchoolId() == null || payOrderPojo.getUserType() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        OrderResultsPojo orderResultPojo = new OrderResultsPojo();
        WxPayUnifiedOrderRequest orderRequest = null;
        // 生成订单信息
        try {
            orderRequest = payService.configOrderInfor(payOrderPojo);
            WxPayMpOrderResult order = null;
            // 微信服务器下单
            order = wxPayService.createOrder(orderRequest);
            if (null != order) {
                BeanUtils.copyProperties(order, orderResultPojo);
                orderResultPojo.setOutTradeNo(orderRequest.getOutTradeNo());
            }
            log.info("统一下单--下单成功订单: {}", orderRequest.getOutTradeNo());
        } catch (Exception e) {
            log.error("生成支付订单信息失败: {}", e.toString());
            // 修改下单状态
            String outTradeNo = orderRequest.getOutTradeNo();
            PayOrderPojo pay = new PayOrderPojo();
            pay.setOrderNumber(outTradeNo);
            pay.setPlaceOrderState(0);
            payService.update(pay);
            // 下单失败删除订单
            Jedis jedis = null;
            try {
                jedis = RedisPoolUtil.getConnect();
                jedis.zrem("pay:create:order", outTradeNo);
                log.info("删除下单失败订单: {}", outTradeNo);
            } finally {
                if (jedis != null) {
                    RedisPoolUtil.closeConnect(jedis);
                }
            }
            throw new BusinessRuntimeException(ResultCode.PAY_ORDER_ERROR);
        }
        return new ApiResult(ResultCode.SUCCESS, orderResultPojo);
    }*/

    /**
     * 微信回调接口
     * https://pay.weixin.qq.com/wiki/doc/api/wxa/wxa_api.php?chapter=23_8&index=6
     * ● 商户系统收到支付结果通知，需要在5秒内返回应答报文，否则微信支付认为通知失败，后续会重复发送通知。
     * ● 同样的通知可能会多次发送给商户系统，商户系统必须能够正确处理重复的通知。如果已处理过，直接给微信支付返回成功。
     *
     * @param xmlData
     * @return
     * @throws WxPayException
     */
  /*  @RequestMapping("/notify")
    public String submit(@RequestBody String xmlData) {
        log.debug("微信支付回调开始...");
        return payService.payNotify(xmlData);
    }*/
}
