package com.xunyunedu.task.Job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * quartz支付订单任务
 *
 *  @author: yhc
 *  @Date: 2020/10/21 22:58
 *  @Description:
 */
public class PayOrderJob {

    /**
     * 用于处理在
     */
    public void orderJob() {
        // 获取前30分钟到前15分钟的数据
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:00");
        Calendar beforeTime = Calendar.getInstance();
        beforeTime.add(Calendar.MINUTE, -30);
        Date startDate = beforeTime.getTime();
        beforeTime.add(Calendar.MINUTE, 15);
        Date endDate = beforeTime.getTime();

        // 查询数据库中的订单

            // 待支付的订单

                // 查询微信订单

                    // 支付完成的发送到远程接口, 添加字段是否发送到远程接口
    }

    /**
     * 支付成功但是没有发送到远程接口的数据
     */


}
