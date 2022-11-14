package com.xunyunedu.pay.service.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayOrderQueryRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.xunyunedu.config.pay.bean.WxMiniApp;
import com.xunyunedu.exception.BaseConstant;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.pay.dao.PayOrderDao;
import com.xunyunedu.pay.pojo.CanteenPojo;
import com.xunyunedu.pay.pojo.PayAmountPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;
import com.xunyunedu.pay.pojo.StudentPojo;
import com.xunyunedu.pay.service.PayService;
import com.xunyunedu.teacher.dao.TeacherHomeDao;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.util.Arith;
import com.xunyunedu.util.IPHelperUtil;
import com.xunyunedu.util.PropertiesUtil;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import com.xunyunedu.util.redis.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import redis.clients.jedis.Jedis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 支付实现
 *
 * @author: yhc
 * @Date: 2020/10/20 16:17
 * @Description:
 */
@Service
public class PayServiceImpl implements PayService {

    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private Snowflake snowflake;

    @Autowired
    private PayOrderDao payOrderDao;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private WxMiniApp wxMiniApp;

    @Autowired
    private TeacherHomeDao teacherHomeDao;

    /**
     * 统一下单 配置支付信息
     *食堂充值
     * @param payOrderPojo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WxPayUnifiedOrderRequest configOrderInfor(PayOrderPojo payOrderPojo) {
        String payNo = String.valueOf(snowflake.nextId());
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody("食堂充值");
        orderRequest.setOutTradeNo(payNo);
        Integer amountId = payOrderPojo.getPayAmountId();
       log.info("充值金额");
        if (amountId != null) {
            PayAmountPojo amount = payOrderDao.getAmount(amountId);
            log.info("amount.getPayAmount()");
            //amount.getPayAmount()
            orderRequest.setTotalFee((int) Arith.mul(amount.getPayAmount(), 100));
        }
        orderRequest.setSpbillCreateIp(IPHelperUtil.getIpAddr());
        orderRequest.setNotifyUrl(wxMiniApp.getDomainName() + "/pay/notify");
        orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
        orderRequest.setOpenid(payOrderPojo.getOpenid());
        orderRequest.setSignType(WxPayConstants.SignType.MD5);
        // 保存订单信息
        payOrderPojo.setOrderNumber(payNo);
        payOrderPojo.setPayType(1);
        payOrderPojo.setCreateTime(new Date());
        payOrderPojo.setIsPayed(0);
        payOrderPojo.setIsSendOrder(0);
        payOrderPojo.setPlaceOrderState(1);

        // 获取用户的账号和名称
        if (payOrderPojo.getUserType() == 1) {
            StudentPojo studentPojo = new StudentPojo();
            studentPojo.setSchoolId(payOrderPojo.getSchoolId());
            studentPojo.setId(payOrderPojo.getUserId());
            StudentPojo student = payOrderDao.getStuInfo(studentPojo);
            if (student != null) {
                // 食堂接口将登陆的账号作为工号
                payOrderPojo.setEmpName(student.getName());
                // 新增的用户以userName，食堂已经发卡的用户以empCode
                if (StrUtil.isNotEmpty(student.getEmpCode())) {
                    log.info("食堂卡号"+student.getEmpCode());
                    payOrderPojo.setEmpCode(student.getEmpCode());
                } else {
                    payOrderPojo.setEmpCode(student.getUserName());
                }
                String empCard = student.getEmpCard();
                if (StrUtil.isNotEmpty(empCard) && !("0").equals(empCard)) {
                    payOrderPojo.setEmpCard(empCard);
                } else {
                    log.error("学生食堂卡号为空！用户信息：{}", student.toString());
                    throw new BusinessRuntimeException(ResultCode.CARD_NOT_EXISTS);
                }
            }
        } else if(payOrderPojo.getUserType() == 2) {
            // 教师端
            TeacherPojo teacherPojo = new TeacherPojo();
            teacherPojo.setSchoolId(payOrderPojo.getSchoolId());
            teacherPojo.setId(payOrderPojo.getUserId());
            TeacherPojo teacher = teacherHomeDao.getTeacherByCondition(teacherPojo);
            if (teacher != null) {
                // 食堂接口将登陆的账号作为工号
                payOrderPojo.setEmpName(teacher.getName());
                // 新增的用户以userName，食堂已经发卡的用户以empCode
                if (StrUtil.isNotEmpty(teacher.getEmpCode())) {
                    payOrderPojo.setEmpCode(teacher.getEmpCode());
                } else {
                    payOrderPojo.setEmpCode(teacher.getUserName());
                }
                String empCard = teacher.getEmpCard();
                if (StrUtil.isNotEmpty(empCard) && !("0").equals(empCard)) {
                    payOrderPojo.setEmpCard(empCard);
                } else {
                    log.error("教师食堂卡号为空！用户信息：{}", teacher.toString());
                    throw new BusinessRuntimeException(ResultCode.CARD_NOT_EXISTS);
                }
            }
        } else {
            throw new BusinessRuntimeException(ResultCode.CREATE_ORDER_ERROR);
        }
        payOrderDao.createOrder(payOrderPojo);
        if (payOrderPojo.getId() == null) {
            throw new BusinessRuntimeException(ResultCode.CREATE_ORDER_ERROR);
        }
        // 下单成功保存订单,
        Jedis jedis = null;
        try {
            jedis = RedisPoolUtil.getConnect();
            // 消息发送，score 延迟5s
            jedis.zadd("pay:create:order", System.currentTimeMillis() + 5000, payNo);
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
        return orderRequest;
    }
    /**
     * 统一下单 配置支付信息
     *选课支付
     * @param payOrderPojo
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public WxPayUnifiedOrderRequest configXuanke(PayOrderPojo payOrderPojo) {
        String payNo = String.valueOf(snowflake.nextId());
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody("选课支付");
        orderRequest.setOutTradeNo(payNo);
        //Integer amountId = payOrderPojo.getPayAmountId();
          /*  //获取支付金额
            PayAmountPojo amount = payOrderDao.getXuankeAmount(amountId);*/
        payOrderPojo.getPayAmount();
            orderRequest.setTotalFee((int)Arith.mul(payOrderPojo.getPayAmount(),100));
          /* orderRequest.setTotalFee((int)Arith.mul(payOrderPojo.getPayAmount(),100));*/
        orderRequest.setSpbillCreateIp(IPHelperUtil.getIpAddr());
        //待定 ------------------------------------------------
        orderRequest.setNotifyUrl(wxMiniApp.getDomainName() + "/home/notify");
        orderRequest.setTradeType(WxPayConstants.TradeType.JSAPI);
        orderRequest.setOpenid(payOrderPojo.getOpenid());
        orderRequest.setSignType(WxPayConstants.SignType.MD5);

        // 保存订单信息
        payOrderPojo.setOrderNumber(payNo);
        payOrderPojo.setPayType(1);
        payOrderPojo.setCreateTime(new Date());
        payOrderPojo.setIsPayed(0);
        payOrderPojo.setIsSendOrder(0);
        payOrderPojo.setPlaceOrderState(1);
        payOrderPojo.setPayStatus(0);
        payOrderDao.createxuanke(payOrderPojo);
        if (payOrderPojo.getId() == null) {
            throw new BusinessRuntimeException(ResultCode.CREATE_ORDER_ERROR);
        }
        // 下单成功保存订单,
        Jedis jedis = null;
        try {
            jedis = RedisPoolUtil.getConnect();
            //延迟3秒
            Calendar cal1 = Calendar.getInstance();
            cal1.add(Calendar.SECOND,6);
            int second3later = (int) (cal1.getTimeInMillis() / 1000);
            // 消息发送，score 延迟5s
            jedis.zadd("pay:create:orders", System.currentTimeMillis() + second3later, payNo);
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
        return orderRequest;
    }

    @Override
    public PayOrderPojo getdingdan(String asda) {
        return payOrderDao.getPayOrderPojo(asda);
    }

    @Override
    public List<PayOrderPojo> findByAll() {
        return payOrderDao.findByAll();
    }

    /**
     * 微信付款成功或失败回调接口
     * 1. 检测当前订单是否是付款状态;
     * 2. 设置订单付款成功状态相关信息;
     * 3. 响应微信商户平台.
     *
     * @param xmlData 返回参数信息
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String payNotify(String xmlData) {
        log.info("开始回调");
        WxPayOrderNotifyResult result = null;
        try {
            result = wxPayService.parseOrderNotifyResult(xmlData);
            //resultCode表示业务结果：SUCCESS/FAIL
            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getResultCode())) {
                log.error("支付失败: {}", xmlData);
                throw new WxPayException("微信通知支付失败！");
            }
            //returnCode表示返回结果：SUCCESS/FAIL
            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getReturnCode())) {
                log.error("支付失败: {}", xmlData);
                throw new WxPayException("微信通知支付失败！");
            }
        } catch (WxPayException e) {
            // 支付失败修改订单信息
            PayOrderPojo payOrderPojo = new PayOrderPojo();
            payOrderPojo.setOrderNumber(result.getOutTradeNo());
            payOrderPojo.setIsPayed(2);
            payOrderDao.update(payOrderPojo);
            log.debug("支付回调订单支付失败: {}", result.toString());
            return WxPayNotifyResponse.fail(e.getErrCodeDes());
        }
        log.debug("支付回调内容: {}", result.toString());

        PayOrderPojo payOrderPojo = new PayOrderPojo();
        payOrderPojo.setOrderNumber(result.getOutTradeNo());
        // 获取订单信息
        PayOrderPojo payOrder = payOrderDao.getOrderInfoByNumber(payOrderPojo);
        if (payOrder == null) {
            return WxPayNotifyResponse.fail("订单不存在 sn=" + result.getOutTradeNo());
        }

        // 检查这个订单是否已经处理过
        if (payOrder.getIsPayed() == 1) {
            return WxPayNotifyResponse.success("订单已经处理成功!");
        }
        // 检查支付订单金额
        PayAmountPojo amount = payOrderDao.getAmount(payOrder.getPayAmountId());
        // 分转化成元
        String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
        //amount.getPayAmount()
        if (!totalFee.equals(Arith.doubleToString(amount.getPayAmount()))) {
            log.info("金额不正确");
            return WxPayNotifyResponse.fail(result.getOutTradeNo() + " : 支付金额不符合 totalFee=" + totalFee);
        }

        // 效验完成修改订单信息
        /*BigDecimal bigDecimal = new BigDecimal(totalFee);
        bigDecimal.setScale(2);*/
        log.info("成功"+1);
        payOrderPojo.setPayAmount(Double.valueOf(totalFee));
        payOrderPojo.setPayNo(result.getTransactionId());
        // 是否支付，2: 支付失败 , 1：支付成功，0：没有支付(取消支付,正在支付)
        payOrderPojo.setIsPayed(1);

        String timeEnd = result.getTimeEnd();
        try {
            SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
            payOrderPojo.setPayTime(yyyyMMddHHmmss.parse(timeEnd));
        } catch (ParseException e) {
            payOrderPojo.setPayTime(new Date());
            log.error("获取微信支付回调日期失败");
        }
        payOrderDao.update(payOrderPojo);
        return WxPayNotifyResponse.success("处理成功!");
    }
    /** 选课的
     * 微信付款成功或失败回调接口
     * 1. 检测当前订单是否是付款状态;
     * 2. 设置订单付款成功状态相关信息;
     * 3. 响应微信商户平台.
     *
     * @param xmlData 返回参数信息
     * @return 操作结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String payNotifyXuanke(String xmlData) {
        WxPayOrderNotifyResult result = null;
        try {
            result = wxPayService.parseOrderNotifyResult(xmlData);
            //resultCode表示业务结果：SUCCESS/FAIL
            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getResultCode())) {
                log.error("支付失败: {}", xmlData);
                throw new WxPayException("微信通知支付失败！");
            }
            //returnCode表示返回结果：SUCCESS/FAIL
            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getReturnCode())) {
                log.error("支付失败: {}", xmlData);
                throw new WxPayException("微信通知支付失败！");
            }
        } catch (WxPayException e) {
            // 支付失败修改订单信息
            PayOrderPojo payOrderPojo = new PayOrderPojo();
            payOrderPojo.setOrderNumber(result.getOutTradeNo());
            payOrderPojo.setIsPayed(2);
            payOrderPojo.setPayStatus(2);
            payOrderDao.updateXuanke(payOrderPojo);
            log.debug("支付回调订单支付失败: {}", result.toString());
            return WxPayNotifyResponse.fail(e.getErrCodeDes());
        }
        log.debug("支付回调内容: {}", result.toString());

        PayOrderPojo payOrderPojo = new PayOrderPojo();
        payOrderPojo.setOrderNumber(result.getOutTradeNo());

        // 获取订单信息
        PayOrderPojo payOrder = payOrderDao.getOrderInfoByNumber(payOrderPojo);
        if (payOrder == null) {
            return WxPayNotifyResponse.fail("订单不存在 sn=" + result.getOutTradeNo());
        }

        // 检查这个订单是否已经处理过
        if (payOrder.getIsPayed() == 1) {
            return WxPayNotifyResponse.success("订单已经处理成功!");
        }
        // 检查支付订单金额
        PayAmountPojo amount = payOrderDao.getAmount(payOrder.getPayAmountId());
        // 分转化成元
        String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
        if (!totalFee.equals(Arith.doubleToString(amount.getPayAmount()))) {
            return WxPayNotifyResponse.fail(result.getOutTradeNo() + " : 支付金额不符合 totalFee=" + totalFee);
        }

        // 效验完成修改订单信息
        /*BigDecimal bigDecimal = new BigDecimal(totalFee);
        bigDecimal.setScale(2);*/
        payOrderPojo.setPayAmount(Double.valueOf(totalFee));
        payOrderPojo.setPayNo(result.getTransactionId());
        // 是否支付，2: 支付失败 , 1：支付成功，0：没有支付(取消支付,正在支付)
        payOrderPojo.setIsPayed(1);
        payOrderPojo.setPayStatus(1);

        String timeEnd = result.getTimeEnd();
        try {
            SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
            payOrderPojo.setPayTime(yyyyMMddHHmmss.parse(timeEnd));
        } catch (ParseException e) {
            payOrderPojo.setPayTime(new Date());
            log.error("获取微信支付回调日期失败");
        }

        payOrderDao.updateXuanke(payOrderPojo);

        return WxPayNotifyResponse.success("处理成功!");
    }


    /**
     * 微信查询订单
     *
     * @return
     */
    @Override
    public WxPayOrderQueryResult queryOrder(String transactionId, String outTradeNo) {
        try {
            WxPayOrderQueryRequest request = new WxPayOrderQueryRequest();
            request.setTransactionId(transactionId);
            request.setOutTradeNo(outTradeNo);
            return wxPayService.queryOrder(request);
        } catch (WxPayException e) {
            log.error("查询订单信息失败:{}", e.getErrCodeDes());
            throw new BusinessRuntimeException(ResultCode.SEARCH_ORDER_ERROR);
        }
    }

    /**
     * 修改订单状态
     *
     * @param orderInfoByNumber
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int update(PayOrderPojo orderInfoByNumber) {
        int i = 0;
        try {
            log.info("xiugai"+orderInfoByNumber);
            i = payOrderDao.update(orderInfoByNumber);
        } catch (Exception e) {
            log.error("修改订单状态失败,订单:{} 错误:{}", orderInfoByNumber.getOrderNumber(), e.getMessage());
            i = 0;
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return i;
    }


    /**
     * 修改选课订单状态
     *
     * @param orderInfoByNumber
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateXuanke(PayOrderPojo orderInfoByNumber) {
        int i = 0;
        try {
            i = payOrderDao.updateXuanke(orderInfoByNumber);
        } catch (Exception e) {
            log.error("修改订单状态失败,订单:{} 错误:{}", orderInfoByNumber.getOrderNumber(), e.getMessage());
            i = 0;
            // 手动回滚事务
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
        return i;
    }
    /**
     * 获取对应学校的支付金额
     *
     * @param payAmountPojo
     * @return
     */
    @Override
    public List<PayAmountPojo> getAmount(PayAmountPojo payAmountPojo) {
        return payOrderDao.getAmountBySchool(payAmountPojo);
    }

    public  Integer postData(PayOrderPojo order) {
        String fileName = "application.properties";
        // 食堂接口信息
        String ip = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        String addres = PropertiesUtil.getProperty(fileName, "canteen.server.url");
        //sendState = Boolean.parseBoolean(PropertiesUtil.getProperty(fileName, "send.canteen"));
       String  url = ip + addres;
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
    @Override
   public  Integer updateASFAS(Long id){
        return payOrderDao.updateasdasd(id);
    };

}
