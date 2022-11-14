package com.xunyunedu;

import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.service.WxPayService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.pay.pojo.OrderResultsPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;
import com.xunyunedu.pay.service.PayService;
import com.xunyunedu.util.redis.RedisPoolUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

public class WxZhifu {
    @Autowired
        static  PayService payService;
    static WxPayService wxPayService;
    public static void main(String[] args) {

        PayOrderPojo payOrderPojo=new PayOrderPojo();
        payOrderPojo.setSchoolId(215);
        payOrderPojo.setPayAmount(0.02);
        payOrderPojo.setOpenid("12312412");
        //微信支付
        ApiResult apiResult=new ApiResult();
        System.out.println("数据"+payOrderPojo.getPayAmount());
        if (payOrderPojo == null || payOrderPojo.getUserId() == null || payOrderPojo.getPayAmount() == null || payOrderPojo.getOpenid() == null
                || payOrderPojo.getSchoolId() == null || payOrderPojo.getUserType() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        OrderResultsPojo orderResultPojo = new OrderResultsPojo();
        WxPayUnifiedOrderRequest orderRequest = null;
        // 生成订单信息
        try {
            orderRequest = payService.configXuanke(payOrderPojo);
            WxPayMpOrderResult order = null;
            // 微信服务器下单
            order = wxPayService.createOrder(orderRequest);
            if (null != order) {
                BeanUtils.copyProperties(order, orderResultPojo);
                orderResultPojo.setOutTradeNo(orderRequest.getOutTradeNo());
            }

            System.out.println("统一下单--下单成功订单: {}"+orderRequest.getOutTradeNo());

        } catch (Exception ee) {
            System.out.println("生成支付订单信息失败: {}"+ee.toString());
            // 修改下单状态
            String outTradeNo = orderRequest.getOutTradeNo();
            PayOrderPojo pay = new PayOrderPojo();
            pay.setOrderNumber(outTradeNo);
            //下单状态 0失败  1成功
            pay.setPlaceOrderState(0);
            // 下单失败删除订单
            Jedis jedis = null;
            try {
                jedis = RedisPoolUtil.getConnect();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (jedis != null) {
                    RedisPoolUtil.closeConnect(jedis);
                }
            }
            throw new BusinessRuntimeException(ResultCode.PAY_ORDER_ERROR);
        }
        System.out.println(ResultCode.SUCCESS+""+orderResultPojo);
    }
}
