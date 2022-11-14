package com.xunyunedu;

import com.xunyunedu.util.redis.RedisPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Test {
    static Logger logger = LoggerFactory.getLogger(Test.class);
    private static ThreadPoolExecutor threadPoolExecutor;
    private static ArrayBlockingQueue<Runnable> arrayBlockingQueue;
    private static ThreadFactory threadFactory;
    private static RejectedExecutionHandler rejectedExecutionHandler;


    public static void main(String[] args) {
        String test="三年级(1)班";
        String sss=test.substring(0,1)+test.substring(4,5)+"班";
        System.out.println(sss);
      /*  List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", 1);

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("id", 3);
        Map<String, Object> map3 = new HashMap<String, Object>();
        map3.put("id", 4);
        Map<String, Object> map4 = new HashMap<String, Object>();
        map4.put("id", 2);
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("id", 4);
        list.add(map1);
        list.add(map3);
        list.add(map2);
        list.add(map4);
        list.add(map5);
        //排序前
        System.out.println("---------------排序前------------------");
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }
        //先根据ID在根据NAME进行排序
        Collections.sort(list, new Comparator<Map<String, Object>>() {
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                Integer sort1= (Integer) o1.get("id");//从list中拿出来第一个的id和name拼接到一起
                Integer sort2=(Integer) o2.get("id");//从list中拿出来第二个的id和name拼接到一起
                return sort1.compareTo(sort2);//利用String类的compareTo方法
            }
        });
        //排序后
        System.out.println("---------------排序后------------------");
        for (Map<String, Object> map : list) {
            System.out.println(map);
        }

*/

     /*   Date d = new Date();
        SimpleDateFormat sbf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date asd=(Date)"2021-01-19 20:14:00";
        System.out.println(sbf.format(d)<asd);*/

/*//        new Test().test();
//        public static void main(String[] args) {
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -30);// 5分钟之前的时间
        Date beforeD = beforeTime.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        String before5 = simpleDateFormat.format(beforeD);  // 前五分钟时间
        System.out.println(before5);
        beforeTime.add(Calendar.MINUTE, 15);// 5分钟之前的时间
        Date before15 = beforeTime.getTime();
        System.out.println(simpleDateFormat.format(before15));*/
//        }

    }

    public void test() {
        // 使用jdk提供的拒绝策略
//        RejectedExecutionHandler callerRunsPolicy = new ThreadPoolExecutor.CallerRunsPolicy();
        threadFactory = new NameTreadFactory();
        rejectedExecutionHandler = new Rejected();

        arrayBlockingQueue = new ArrayBlockingQueue(60);
        threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, arrayBlockingQueue, threadFactory, rejectedExecutionHandler);
        for (int i = 0; i < 100; i++) {
            try {
                threadPoolExecutor.execute(new TestRunnable(10, "test"));
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }

    //    @org.junit.Test
    public void tes() {
        Jedis jedis = null;
        try {
            jedis = RedisPoolUtil.getConnect();
            Long aLong = jedis.lpush("pay:notify:order", "订单id4");
            System.out.println(aLong + "=============");
            List<String> brpop = jedis.blpop(0, "pay:notify:order");
            for (String s : brpop) {
                System.out.println(s);
            }


        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
    }

//    @org.junit.Test
    public void createOrder() {
        //构造消息生产者
        Jedis jedis = null;
//        for (int i = 0; i < 5; i++) {
        try {
            jedis = RedisPoolUtil.getConnect();
            System.out.println("=================添加数据");
            //消息发送，score 延迟 5分钟
            jedis.zadd("pay:create:order", System.currentTimeMillis() + 60000, "订单");
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
//        }
//        //构造一个消息消费者
        loop();

    }


    /**
     * 消息消费
     */
    public void loop() {
        Jedis jedis = null;
        try {
            jedis = RedisPoolUtil.getConnect();
            while (!Thread.interrupted()) {
                //读取 score 在 0 到当前时间戳之间的消息
                Set<String> zrange = jedis.zrangeByScore("pay:create:order", 0, System.currentTimeMillis(), 0, 1);
                if (zrange.isEmpty()) {
                    //如果消息是空的，则休息500毫秒然后继续
                    try {
                        System.out.println("休息 500 毫秒");
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        break;
                    }
                    continue;
                }
                //如果读取到了消息，则直接读取消息出来
                String next = zrange.iterator().next();
//                if (jedis.zrem("pay:create:order", next) > 0) {
//                    //抢到了，接下来处理业务
//                    System.out.println("==========================获取数据===" + next);
//
//                    jedis.zadd("pay:create:order", System.currentTimeMillis() + 50000, "add数据");
//                }
                // 重试延迟时间
                jedis.zincrby("pay:create:order", +60000, next);
            }
        } finally {
            if (jedis != null) {
                RedisPoolUtil.closeConnect(jedis);
            }
        }
    }


    /**
     * 自定义线程处理
     */
    public class TestRunnable implements Runnable {
        private Integer i;
        private String str;

        public TestRunnable(Integer i, String str) {
            this.i = i;
            this.str = str;
        }

        @Override
        public void run() {
            logger.info("线程池测试 {} , {}", i, str);
        }
    }

    /**
     * 自定义线程工厂
     */
    class NameTreadFactory implements ThreadFactory {

        // 原子类实现自定义线程编号
        private final AtomicInteger atomicInteger = new AtomicInteger(1);

        @Override
        public Thread newThread(Runnable r) {
            // 重命名线程
            Thread t = new Thread(r, "my-thread-" + atomicInteger.getAndIncrement());
            return t;
        }
    }

    /**
     * 拒绝策略
     */
    class Rejected implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            doLog(r, executor);
        }

        private void doLog(Runnable r, ThreadPoolExecutor e) {
            logger.error("拒绝策略，线程池数量：{}，队列数量：{}，队列数据已满！：{}", e.getPoolSize(), e.getQueue().size(), r.toString());
        }
    }


    //测试

}
