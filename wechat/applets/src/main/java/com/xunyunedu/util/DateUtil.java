package com.xunyunedu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author: yhc
 * @Date: 2020/9/20 20:46
 * @Description: 日期工具
 */
public class DateUtil {
    /**
     * @param nowTime 当前时间
     * @param endTime 结束时间
     * @return
     * @author sunran   判断当前时间在时间区间内
     */
    public static boolean isEffectiveDate(Date nowTime, Date endTime) {
        if (nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断订单是否超时（微信下单两小时超时） 多加一分钟
     *
     * @param createTime
     * @return
     */
    public static boolean timeOut(Date createTime) {
        Calendar createTimeInstance = Calendar.getInstance();
        createTimeInstance.setTime(createTime);
        createTimeInstance.add(Calendar.HOUR, 2);
        createTimeInstance.add(Calendar.MINUTE, 1);
        Calendar instance = Calendar.getInstance();
        long time = instance.getTime().getTime();
        long create = createTimeInstance.getTime().getTime();
        if (time >= create) {
            return true;
        }
        return false;
    }

    public static String getNowDateYYYY_MM_DD() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 获取当前日期是星期几
     *
     * @param dt
     * @return
     */
    public static Integer getWeekOfDate(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        System.out.println(w);
        return w;
    }

    /**
     * 获取指定日期所在周的周一
     *
     * @param date 日期
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            c.add(Calendar.DAY_OF_MONTH, -1);
        }
        c.add(Calendar.DATE, c.getFirstDayOfWeek() - c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }


    /**
     * 获取指定日期所在周的周日
     *
     * @param date 日期
     */
    public static Date getLastDayOfWeek(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        // 如果是周日直接返回
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            return date;
        }
        c.add(Calendar.DATE, 7 - c.get(Calendar.DAY_OF_WEEK) + 1);
        return c.getTime();
    }

    public static String getWeekNameOfDate(Date dt) {
        String[] weekDays = {"周日", "周一", "周二", "周三", "周四", "周五", "周六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

}
