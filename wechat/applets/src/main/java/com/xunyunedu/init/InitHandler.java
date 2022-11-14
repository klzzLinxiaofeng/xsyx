package com.xunyunedu.init;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.WxPayService;
import com.xunyunedu.exception.BaseConstant;
import com.xunyunedu.pay.dao.PayOrderDao;
import com.xunyunedu.pay.pojo.CanteenPojo;
import com.xunyunedu.pay.pojo.PayAmountPojo;
import com.xunyunedu.pay.pojo.PayOrderPojo;
import com.xunyunedu.pay.service.PayService;
import com.xunyunedu.util.DateUtil;
import com.xunyunedu.util.PropertiesUtil;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import com.xunyunedu.util.redis.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 初始化信息处理
 *
 * @author: yhc
 * @Date: 2020/10/22 19:04
 * @Description:
 */
@Component
public class InitHandler {
    final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private PayOrderDao payOrderDao;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private PayService payService;

    /**
     * 食堂接口
     */
    private static String url;

    /**
     * 充值信息是否发送到食堂
     */
    private static Boolean sendState;

    /**
     * ip url 信息
     */
    public static Map<String, String> urlParam = new HashMap<>();

    @PostConstruct
    public void init() throws InterruptedException {
        // 加载初始化信息
        initParams();
        // 微信回调支付成功订单
        if (sendState) {
            notifOrder();
        }

        // 统一下单
        createOrder();

        /*findGetPay();*/
    }

    /**
     * 初始化参数
     */
    private void initParams() {
        String fileName = "application.properties";
        // 食堂接口信息
        String ip = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        String addres = PropertiesUtil.getProperty(fileName, "canteen.server.url");
        sendState = Boolean.parseBoolean(PropertiesUtil.getProperty(fileName, "send.canteen"));
        url = ip + addres;
        // 图书馆接口信息
        String libraryIp = PropertiesUtil.getProperty(fileName, "library.server.address");
        String libraryUrl = PropertiesUtil.getProperty(fileName, "library.get.url");
        // 获取图片
        String libraryPicUrl = PropertiesUtil.getProperty(fileName, "library.api.Book.Get");

        // 获取食堂统计接口信息
        String userListUrl = PropertiesUtil.getProperty(fileName, "canteen.user.list.url");
        String asyncUrl = PropertiesUtil.getProperty(fileName, "canteen.list.async.url");
        String consumerUrl = PropertiesUtil.getProperty(fileName, "canteen.consumer.url");
        String payCountUrl = PropertiesUtil.getProperty(fileName, "canteen.pay.count.url");
        String consumerAvgUrl = PropertiesUtil.getProperty(fileName, "canteen.consumer.avg.url");
        String dayConsumerUrl = PropertiesUtil.getProperty(fileName, "canteen.day.consumer.url");
        String consumerRechargeUrl = PropertiesUtil.getProperty(fileName, "canteen.consumerRecharge.url");

        urlParam.put("userListUrl", ip + userListUrl);
        urlParam.put("asyncUrl", ip + asyncUrl);
        urlParam.put("consumerUrl", ip + consumerUrl);
        urlParam.put("payCountUrl", ip + payCountUrl);
        urlParam.put("consumerAvgUrl", ip + consumerAvgUrl);
        urlParam.put("dayConsumerUrl", ip + dayConsumerUrl);
        urlParam.put("consumerRechargeUrl", ip + consumerRechargeUrl);

        // 图书馆
        urlParam.put("libraryIp", libraryIp);
        urlParam.put("libraryUrl", libraryUrl);
        urlParam.put("libraryPicUrl", libraryPicUrl);

    }
    /**
     * 微信支付成功订单,发送到食堂接口
     * 获取redis中的数据
     */
    public void notifOrder() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Jedis jedis = null;
                while (!Thread.interrupted()) {
                    try {
                        log.info("正常");
                        jedis = RedisPoolUtil.getConnect();
                        //读取 score 在 0 到当前时间戳之间的消息
                        Set<String> set = jedis.zrangeByScore("pay:notify:order", 0, System.currentTimeMillis(), 0, 1);
                        if (set.isEmpty()) {
                            //如果消息是空的，则休息5秒然后继续
                            Thread.sleep(5000);
                            continue;
                        }
                        String orderNum = set.iterator().next();
                        PayOrderPojo order = getOrder(orderNum);
                        if (order != null) {
                            // 订单是否发送到远程接口，1：已发送(发送成功)，0：没有发送(包含发送失败)
                            Integer isSendOrder = order.getIsSendOrder();
                            if (isSendOrder == 1) {
                                // 移除队列所有为ordernum的数据
                                jedis.zrem("pay:notify:order", orderNum);
                                log.warn("发送支付订单信息--订单已发送到食堂接口,重复订单数据: {}", orderNum);
                                continue;
                            }
                            // 是否支付，2: 支付失败 , 1：支付成功，0：没有支付(取消支付,正在支付)
                            Integer isPayed = order.getIsPayed();
                            if (isPayed == 1) {
                                // 后面可以改为线程池方式,但是要注意这一批订单已经读取到了内存中(在线程池下改为循环队列会获取重复数据),宕机情况下会丢失
                                try {
                                    // 1: 成功 0:失败
                                    Integer state = postData(order);
                                    // 发送数据成功,修改数据库状态
                                    if (state == 1) {
                                        PayOrderPojo payOrderPojo = new PayOrderPojo();
                                        payOrderPojo.setOrderNumber(orderNum);
                                        payOrderPojo.setIsSendOrder(1);
                                        int update = payService.update(payOrderPojo);
                                        if (update != 0) {
                                            jedis.zrem("pay:notify:order", orderNum);
                                            log.info("统一下单队列--查询订单已支付成功: {}", orderNum);
                                        }
                                    } else {
                                        // 发送失败的订单再延迟5s,避免每次都是发送的都是失败的这个订单
                                        jedis.zadd("pay:create:order", System.currentTimeMillis() + 5000, orderNum);
                                        // 发送失败，休眠5秒避免频繁日志
                                        Thread.sleep(5000);
                                    }
                                } catch (Exception e) {
                                    log.error("发送支付订单信息--发送订单到食堂接口失败,重新放入队列, 订单: {}, 错误信息: {}", orderNum, e.getMessage());
                                }
                            } else {
                                jedis.zrem("pay:notify:order", orderNum);
                                log.warn("发送支付订单信息-- 有未支付成功订单: {}", orderNum);
                            }
                        }
                    } catch (Exception e) {
                        log.error("发送支付订单信息--获取订单发送食堂接口失败: {}", e.toString());
                    } finally {
                        if (jedis != null) {
                            RedisPoolUtil.closeConnect(jedis);
                        }
                    }
                }
            }
        }, "thread-applets-canteen-notify-order").start();

    }


    /**
     * 统一下单成功订单
     * 获取订单信息,判断订单状态
     */
    public void createOrder() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Jedis jedis = null;
                while (!Thread.interrupted()) {
                    try {
                        jedis = RedisPoolUtil.getConnect();
                        //读取 score 在 0 到当前时间戳之间的消息，在下单时会延迟5s，避免微信推送和查询同时，如果微信没有推送再进行查询
                        Set<String> set = jedis.zrangeByScore("pay:create:order", 0, System.currentTimeMillis(), 0, 1);
                        if (set.isEmpty()) {
                            //如果消息是空的，则休息5秒然后继续
                            Thread.sleep(5000);
                            continue;
                        }
                        // 先获取表订单, 没有再请求微信
                        String orderNum = set.iterator().next();
                        if (orderNum != null && !("").equals(orderNum)) {
                            PayOrderPojo orderInfoByNumber = getOrder(orderNum);
                            log.info("orderInfoByNumber"+orderInfoByNumber.getIsPayed());
                            if (orderInfoByNumber != null) {
                                log.info("isPayed"+orderInfoByNumber.getIsPayed());
                                // 是否支付，2: 支付失败 , 1：支付成功，0：没有支付(取消支付,正在支付)
                                Integer isPayed = orderInfoByNumber.getIsPayed();
                                if (isPayed == 1) {
                                    // 移除下单队列中的订单
                                    Transaction multi = jedis.multi();
                                    multi.zrem("pay:create:order", orderNum);
                                    // 支付完成的订单,保存到食堂redis队列中
                                    if (sendState) {
//                                      multi.lpush("pay:notify:order", orderNum);
                                        multi.zadd("pay:notify:order", System.currentTimeMillis(), orderNum);
                                    }
                                    multi.exec();
                                    log.info("统一下单队列--订单已支付成功: {}", orderNum);
                                } else if (isPayed == 0) {
                                    // 判断订单是否超过两个小时, 取消
                                    Date createTime = orderInfoByNumber.getCreateTime();
                                    boolean timeOut = DateUtil.timeOut(createTime);
                                    if (timeOut) {
                                        jedis.zrem("pay:create:order", orderNum);
                                        orderInfoByNumber.setIsPayed(2);
                                        payService.update(orderInfoByNumber);
                                        // 修改订单状态
                                        log.info("统一下单队列--订单超时未支付，删除订单信息:{}", orderNum);
                                        continue;
                                    }
                                    // 查询微信的订单信息 1:订单支付成功,修改表的状态,放入食堂队列 2:失败/取消删除订单 3: 一直在支付或者未支付状态删除订单,再重新下单指定延时
                                    WxPayOrderQueryResult result = wxPayService.queryOrder(null, orderNum);
                                    Integer i = queryOrder(orderNum, result);
                                    log.info("查询订单支付"+i);
                                    if (i == 1) {
                                        orderInfoByNumber.setPayNo(result.getTransactionId());
                                        // 是否支付，2: 支付失败 , 1：支付成功，0：没有支付(取消支付,正在支付)
                                        orderInfoByNumber.setIsPayed(1);
                                        // 分转化成元
                                        String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
                                        /*BigDecimal bigDecimal = new BigDecimal(totalFee);
                                        bigDecimal.setScale(2);*/
                                        orderInfoByNumber.setPayAmount(Double.valueOf(totalFee));
                                        try {
                                            String timeEnd = result.getTimeEnd();
                                            SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyyMMddHHmmss");
                                            orderInfoByNumber.setPayTime(yyyyMMddHHmmss.parse(timeEnd));
                                        } catch (ParseException e) {
                                            orderInfoByNumber.setPayTime(new Date());
                                            log.error("统一下单队列--获取微信支付回调日期失败");
                                        }
                                        // 效验完成修改订单信息
                                        int update = payService.update(orderInfoByNumber);
                                        log.info("开始修改update"+update);
                                        if (update != 0) {
                                            // 开启redis事务
                                            Transaction multi = jedis.multi();
                                            multi.zrem("pay:create:order", orderNum);
                                            // 后期改为保存订单信息, 减少db查询(实体需要序列化)
                                            if (sendState) {
                                                log.info("修改wancheng"+sendState);
//                                                multi.lpush("pay:notify:order", orderNum);
                                                multi.zadd("pay:notify:order", System.currentTimeMillis(), orderNum);
                                            }
                                            multi.exec();
                                            log.info("统一下单队列--查询订单已支付成功: {}", orderNum);
                                        }
                                    } else if (i == 3) {
                                        Transaction multi = jedis.multi();
                                        multi.zrem("pay:create:order", orderNum);
                                        // 延迟1分钟
                                        multi.zadd("pay:create:order", System.currentTimeMillis() + 5000, orderNum);
                                        multi.exec();
                                        // 延迟1分钟
//                                        jedis.zincrby("pay:create:order", +5000, orderNum);
                                        log.info("统一下单队列--订单支付中/未支付,重设队列延迟时间,订单: {}", orderNum);
                                    } else {
                                        jedis.zrem("pay:create:order", orderNum);
                                        log.info("统一下单队列--支付失败订单: {}", orderNum);
                                    }
                                }
                            }
                        }
                    } catch (Exception e) {
                        log.error("统一下单订单处理失败: {}", e.toString());
                    } finally {
                        if (jedis != null) {
                            RedisPoolUtil.closeConnect(jedis);
                        }
                    }
                }
            }
        }, "thread-applets-canteen-create-order").start();

    }

    /**
     * 查询订单
     * 后面可以将这些订单信息保存到表中
     *
     * @param outTradeNo
     * @return 1=SUCCESS—支付成功 2=PAYERROR--支付失败(其他原因，如银行返回失败) 3=USERPAYING--用户支付中（付款码支付） 3=NOTPAY—未支付 3=REVOKED—已撤销（付款码支付）
     * REFUND—转入退款 CLOSED—已关闭 (不考虑)
     * 1：支付成功，2: 支付失败 3:支付中
     */
    public Integer queryOrder(String outTradeNo, WxPayOrderQueryResult result) {
        try {
            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getResultCode())) {
                log.info("查询订单支付失败, 订单: {}", outTradeNo);
                return 2;
            }
            //returnCode表示返回结果：SUCCESS/FAIL
            if (!WxPayConstants.ResultCode.SUCCESS.equals(result.getReturnCode())) {
                log.info("查询订单支付失败, 订单: {}", outTradeNo);
                return 2;
            }

            String tradeState = result.getTradeState();
            if (WxPayConstants.WxpayTradeStatus.SUCCESS.equals(tradeState)) {
                return 1;
            }
            if (WxPayConstants.WxpayTradeStatus.PAY_ERROR.equals(tradeState)) {
                return 2;
            }
            // 如果为3一直在支付或者未支付状态删除订单,再重新下单指定延时
            if (WxPayConstants.WxpayTradeStatus.USER_PAYING.equals(tradeState) || WxPayConstants.WxpayTradeStatus.NOTPAY.equals(tradeState) || WxPayConstants.WxpayTradeStatus.REVOKED.equals(tradeState)) {
                return 3;
            }
        } catch (Exception e) {
            log.error("查询订单信息失败:{}", e.toString());
        }
        return 3;
    }


    /**
     * 获取订单信息
     *
     * @param orderNum
     * @return
     */
    public PayOrderPojo getOrder(String orderNum) {
        if (orderNum == null || ("").equals(orderNum)) {
            return null;
        }
        PayOrderPojo payOrderPojo = new PayOrderPojo();
        payOrderPojo.setOrderNumber(orderNum);
        PayOrderPojo orderInfoByNumber = payOrderDao.getOrderInfoByNumber(payOrderPojo);
        return orderInfoByNumber;
    }

    /**
     * 发送支付信息到食堂接口
     *
     * @return 1: 成功 0:失败
     */
    public  Integer postData(PayOrderPojo order) {
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

            PayAmountPojo amount = payOrderDao.getAmount(order.getPayAmountId());
            // 食堂接口以元为单位
            p.setChange_money(amount.getPayAmount());
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


    /*
    * 获取未支付订单
    */

}