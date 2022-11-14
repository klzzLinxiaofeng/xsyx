package com.xunyunedu.pay.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BaseConstant;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.pay.pojo.CanteenPojo;
import com.xunyunedu.pay.pojo.OrderResultsPojo;
import com.xunyunedu.pay.pojo.PayAmountPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;
import com.xunyunedu.pay.service.PayService;
import com.xunyunedu.util.PropertiesUtil;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import com.xunyunedu.util.redis.RedisPoolUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

/**
 * 微信支付
 *
 * @author: yhc
 * @Date: 2020/10/20 11:43
 * @Description:
 */
@RequestMapping("/pay")
@RestController
@Api("CESHI")
public class PayController {

    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private PayService payService;

    /**
     * 获取支付金额信息 根据学校id
     *
     * @param payAmountPojo
     * @return
     */
    @GetMapping("/getAmount")
    @Authorization
    public ApiResult getAmount(PayAmountPojo payAmountPojo) {
        if (payAmountPojo == null || payAmountPojo.getSchoolId() == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        return new ApiResult(ResultCode.SUCCESS, payService.getAmount(payAmountPojo));
    }

    /**
     * 统一下单支付接口
     * @param payOrderPojo
     * @return
     */
    @PostMapping("/payOrder")
    @Authorization
    public ApiResult pay(@RequestBody PayOrderPojo payOrderPojo) {
        /*ApiResult apiResult=new ApiResult();
        apiResult.setCode(404);
        apiResult.setMsg("支付功能暂时关闭");
        return apiResult;*/
        log.info("数据" + payOrderPojo.getPayAmount());
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
                org.springframework.beans.BeanUtils.copyProperties(order, orderResultPojo);
                orderResultPojo.setOutTradeNo(orderRequest.getOutTradeNo());
            }
            log.info("统一下单--下单成功订单: {}", orderRequest.getOutTradeNo());

        } catch (Exception ee) {
            log.error("生成支付订单信息失败: {}", ee.toString());
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
        return new ApiResult(ResultCode.SUCCESS, orderResultPojo);
    }
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
    @RequestMapping("/notify")
    public String submit(@RequestBody String xmlData) {
        log.debug("微信支付回调开始...");
        return payService.payNotify(xmlData);
    }

    @PostMapping("/payOrdeShour")
    public ApiResult payss(@RequestBody PayOrderPojo payOrderPojo) {
        Integer i=payService.postData(payOrderPojo);
        if(i==1){
            return new ApiResult(ResultCode.SUCCESS);
        }
        return new ApiResult(ResultCode.FIND_FAIL);
    }


    /**
     * 查询订单
     *
     * @param transactionId
     * @param outTradeNo    需要传递商家订单号 outTradeNo
     * @return
     * @throws WxPayException
     */
    @GetMapping("/queryOrder")
    @Authorization
    public ApiResult queryOrder(String transactionId, String outTradeNo) {
        return new ApiResult(ResultCode.SUCCESS, payService.queryOrder(transactionId, outTradeNo));
    }

    /**
     * 扣款
     */
    @RequestMapping("/kouKuan")
    @ApiOperation("扣款")
    public Integer kouKuan(@RequestBody PayOrderPojo order) {
        String fileName = "application.properties";
        String ip = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        String addres = PropertiesUtil.getProperty(fileName, "canteen.server.url");
        //sendState = Boolean.parseBoolean(PropertiesUtil.getProperty(fileName, "send.canteen"));
        String url = ip + addres;
        try {
            CanteenPojo p = new CanteenPojo();
            p.setEmp_code(order.getEmpCode());
            p.setEmp_name(order.getEmpName());
            p.setEmp_card(order.getEmpCard());

            //p.setEmp_code("8610004076");
            //p.setEmp_name("一年级(9)班");
            // p.setEmp_card("1232223423");

//            p.setEmp_code("000013");
//            p.setEmp_name("姓名测试");
//            p.setEmp_card("0000131313");

            //PayAmountPojo amount = payOrderDao.getAmount(order.getPayAmountId());
            // 食堂接口以元为单位
            p.setChange_money(order.getPayAmount());
            p.setChange_flag(2);
            p.setClock_id("999");
            p.setCreatePerson(order.getEmpName());
            p.setChange_why("充值");
            p.setChange_type("微信线上");
            Object obj = JSONArray.toJSON(p);
            HttpRequestConfig config = HttpRequestConfig.create().url(url)
                    .addHeader("Content-Type", "application/json")
                    .httpEntityType(HttpEntityType.ENTITY_STRING)
                    .json(obj.toString());
            HttpRequestResult httpRequestResult = HttpClientUtils.post(config);
            log.debug("发送支付订单信息--返回信息: {}", httpRequestResult);
            if (BaseConstant.SUCCESS.getMesg().equals(httpRequestResult.getCode())) {
                String responseText = httpRequestResult.getResponseText();
                if (!StrUtil.hasEmpty(responseText)) {
                    JSONObject jsonObject = JSONObject.parseObject(responseText);
                    String statusCode = jsonObject.getString("statusCode");
                    String result = jsonObject.getString("result");
                    if (!StrUtil.hasEmpty(statusCode, result)) {
                        if (BaseConstant.SUCCESS.getMesg().toString().equals(statusCode) && "true".equals(result)) {
                            log.info("发送支付订单信息--食堂接口成功: {}", order.getOrderNumber());
                            return 1;
                        } else {
                            log.error("发送支付订单信息--食堂接口失败：{}， 返回信息：{}", order.getOrderNumber(), httpRequestResult);
                            return 0;
                        }
                    }
                }
            } else {
                log.error("发送支付订单信息--http请求食堂接口失败: {}", httpRequestResult.getCode());
            }
        } catch (Exception e) {
            log.error("发送支付订单信息--食堂接口失败: {}", e.getMessage());
        }
        return 0;
    }

}
