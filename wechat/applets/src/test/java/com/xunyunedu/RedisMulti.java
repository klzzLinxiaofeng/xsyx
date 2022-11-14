package com.xunyunedu;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.BaseConstant;
import com.xunyunedu.util.PropertiesUtil;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpEntityType;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import com.xunyunedu.util.redis.RedisPoolUtil;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class RedisMulti {
    private static Logger log = LoggerFactory.getLogger(RedisMulti.class);

    /**
     * 食堂线程池
     */
    private static ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue(100);
    private static ThreadFactory threadFactory = new CanteenNameTreadFactory();
    private static ThreadPoolExecutor canteenThreadPoolExecutor =
            new ThreadPoolExecutor(10, 15, 1, TimeUnit.MINUTES, queue, threadFactory, new ThreadPoolExecutor.CallerRunsPolicy());

//    public static void main(String[] args) {
//        addEmploye(new CanteenData("1232123"));
//    }

    public static void addEmploye(CanteenData orderNum) {
        try {
            System.out.println(queue.size() + "===开始");
            for (int i = 0; i < 20; i++) {
                canteenThreadPoolExecutor.submit(orderNum);
            }
            System.out.println(queue.size() + "===结束");
        } catch (Exception e) {
            log.error("食堂数据线程池异常: {}", e.getMessage());
        }
    }

    static class CanteenData implements Runnable {
        private String orderNum;

        public CanteenData(String orderNum) {
            this.orderNum = orderNum;
        }

        @Override
        public void run() {
            if (!StrUtil.hasEmpty(orderNum)) {
                try {
                    System.out.println("提交任务: " + orderNum);
                    Thread.sleep(100000);
                } catch (Exception e) {
                    log.error("请求远程接口失败：{}", e.getMessage());
                }
            }
        }
    }


    /**
     * 食堂自定义线程工厂
     */
    static class CanteenNameTreadFactory implements ThreadFactory {

        // 原子类实现自定义线程编号
        private final AtomicInteger atomicInteger = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            // 重命名线程
            Thread t = new Thread(r, "pay-order-thread-" + atomicInteger.getAndIncrement());
            return t;
        }
    }


    public void test() {
        Jedis jedis = RedisPoolUtil.getConnect();
        try {
            // 使用阻塞队列获取redis中最新的微信回调支付成功的订单
            Transaction multi = jedis.multi();
            multi.zrem("pay:create:order", "orderNum");
//            int i = 10/0;
            // 后期改为保存订单信息, 减少db查询
            multi.lpush("pay:notify:order", "orderNum");
            multi.zadd("pay:create:order", System.currentTimeMillis() + 300000, "orderNumsss");
            multi.exec();
        } catch (Exception e) {
            System.out.println("异常");
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
    }

    public void test2() {
        Jedis jedis = RedisPoolUtil.getConnect();
        try {
            jedis = RedisPoolUtil.getConnect();
            System.out.println(jedis.lpush("pay:notify:order", "循环队列数据") + "===========");
            String brpoplpush = jedis.brpoplpush("pay:notify:order", "pay:notify:order", 0);
            System.out.println(brpoplpush + "=====循环队里队尾数据");
            // 执行成功移除队列内容
            System.out.println(jedis.lrem("pay:notify:order", 0, "循环队列数据"));
        } catch (Exception e) {
            log.error("获取订单发送食堂接口失败: {}", e.getMessage());
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
    }

    @Test
    public void test5() {
        Jedis jedis = RedisPoolUtil.getConnect();
        try {
            jedis = RedisPoolUtil.getConnect();
            jedis.zadd("pay:test:zaddScore", System.currentTimeMillis(),"value");
        } catch (Exception e) {
            log.error("获取订单发送食堂接口失败: {}", e.getMessage());
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
    }

    class CanteenPojo implements Serializable {
        private static final long serialVersionUID = 1L;
        private String emp_code;
        private String emp_name;

        private String emp_card;
        private double change_money;
        private Integer change_flag;
        private String clock_id;
        private String createPerson;
        private String change_why;
        private String change_type;

        public String getEmp_code() {
            return emp_code;
        }

        public void setEmp_code(String emp_code) {
            this.emp_code = emp_code;
        }

        public String getEmp_name() {
            return emp_name;
        }

        public void setEmp_name(String emp_name) {
            this.emp_name = emp_name;
        }

        public String getEmp_card() {
            return emp_card;
        }

        public void setEmp_card(String emp_card) {
            this.emp_card = emp_card;
        }

        public double getChange_money() {
            return change_money;
        }

        public void setChange_money(double change_money) {
            this.change_money = change_money;
        }

        public Integer getChange_flag() {
            return change_flag;
        }

        public void setChange_flag(Integer change_flag) {
            this.change_flag = change_flag;
        }

        public String getClock_id() {
            return clock_id;
        }

        public void setClock_id(String clock_id) {
            this.clock_id = clock_id;
        }

        public String getCreatePerson() {
            return createPerson;
        }

        public void setCreatePerson(String createPerson) {
            this.createPerson = createPerson;
        }

        public String getChange_why() {
            return change_why;
        }

        public void setChange_why(String change_why) {
            this.change_why = change_why;
        }

        public String getChange_type() {
            return change_type;
        }

        public void setChange_type(String change_type) {
            this.change_type = change_type;
        }

        @Override
        public String toString() {
            return "CanteenPojo{" +
                    "emp_code='" + emp_code + '\'' +
                    ", emp_name='" + emp_name + '\'' +
                    ", emp_card='" + emp_card + '\'' +
                    ", change_money=" + change_money +
                    ", change_flag=" + change_flag +
                    ", clock_id='" + clock_id + '\'' +
                    ", createPerson='" + createPerson + '\'' +
                    ", change_why='" + change_why + '\'' +
                    ", change_type='" + change_type + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

    }

    @Test
    public void test3() {
        String fileName = "application.properties";
        String ip = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        String addres = PropertiesUtil.getProperty(fileName, "canteen.server.url");
        String url = ip + addres;
        try {
            CanteenPojo ca = new CanteenPojo();
            ca.setEmp_code("000013");
            ca.setEmp_name("姓名测试");
            ca.setEmp_card("0000131313");
            ca.setChange_money(-123);
            ca.setChange_flag(2);
            ca.setClock_id("999");
            ca.setCreatePerson("姓名测试");
            ca.setChange_why("充值");
            ca.setChange_type("微信线上");
            Object obj = JSONArray.toJSON(ca);
            HttpRequestConfig config = HttpRequestConfig.create().url("http://139.159.242.158:8090/api/meal/MealChangeMoney/add_PerRecharge")
                    .addHeader("Content-Type", "application/json")
                    .httpEntityType(HttpEntityType.ENTITY_STRING)
                    .json(obj.toString());
            HttpRequestResult httpRequestResult = HttpClientUtils.post(config);
            log.debug("食堂添加接口添加状态返回信息: {}", httpRequestResult);

            if (BaseConstant.SUCCESS.getMesg().equals(httpRequestResult.getCode())) {
                String responseText = httpRequestResult.getResponseText();
                if (!StrUtil.hasEmpty(responseText)) {
                    JSONObject jsonObject = JSONObject.parseObject(responseText);
                    String statusCode = jsonObject.getString("statusCode");
                    String result = jsonObject.getString("result");
                    if (!StrUtil.hasEmpty(statusCode, result)) {
                        System.out.println(statusCode);
                        System.out.println(BaseConstant.SUCCESS.getMesg());
                        System.out.println(BaseConstant.SUCCESS.getMesg().toString().equals(statusCode));
                        System.out.println("true".equals(result));
                        if (BaseConstant.SUCCESS.getMesg().toString().equals(statusCode) && "true".equals(result)) {
                            log.info("发送支付订单信息--食堂接口成功: {}",httpRequestResult);
                        } else {
                            log.info("发送支付订单信息--食堂接口失败：返回信息：{}", httpRequestResult);
                        }
                    }
                }
            } else {
                log.error("发送支付订单信息--请求食堂接口失败: {}", httpRequestResult.toString());
            }

        } catch (Exception e) {
            log.error("请求失败: {}", e.toString());
        }
    }

    @Test
    public void lowNumber() {
        String numStr = "一年级一班";
        Map<String, String> map = new HashMap<>(8);
        map.put("一", "1");
        map.put("二", "2");
        map.put("三", "3");
        map.put("四", "4");
        map.put("五", "5");
        map.put("六", "6");
        map.put("七", "7");
        map.put("八", "8");
        String substring = numStr.substring(3, 4);
        StringBuilder stringBuilder = new StringBuilder(numStr);
        stringBuilder.replace(3, 4, "(" + map.get(substring) + ")");
        System.out.println(stringBuilder.toString());
    }

}
