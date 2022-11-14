package platform.szxyzxx.web.oa.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @author ZenGx1n
 * @version 1.0
 * @date 2021-03-31 11:06
 */
public class DateUtils {

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
