package platform.education.generalTeachingAffair.utils;

import platform.education.generalTeachingAffair.model.PjAptsTaskUser;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtil {
    private static String datetimeFormat = "yyyy-MM-dd HH:mm";

    /**
     * @Title compare_date
     * @param timeStamp
     * @return
     */
    public static Boolean compareDate(String timeStamp) {
        boolean flag = false;
        DateFormat df = new SimpleDateFormat(datetimeFormat);

        //系统时间
        Date date = new Date();
        String nowDateStr = df.format(date);

        try {
            //调用方时间戳
            Long time = new Long(timeStamp);
            String d = df.format(time);
            Date timeStampDate = df.parse(d);
            //延迟2分钟收工
            timeStampDate.setMinutes(timeStampDate.getMinutes() + 10);
            String finishedDateStr = df.format(timeStampDate);
            Date dt1 = df.parse(finishedDateStr);
            Date dt2 = df.parse(nowDateStr);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                flag = true;
                return flag;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return flag;
            } else {
                return flag;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return flag;
    }

    public static String dateToStr(Date date) {
        String str = "";
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            str = format.format(date);
        }

        return str;
    }

    /**
     * 字符串转换成日期
     *
     * @param str
     * @return date
     */
    public static Date strToDate(String str, String fomatStr) {
        SimpleDateFormat format = new SimpleDateFormat(fomatStr);
        Date date = null;
        try {
            date = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    public static String dateToStrSS(Date date) {
        String str = "";
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str = format.format(date);
        }

        return str;
    }


    public static Map<String, Object> getWeekMonAndSun() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
        map.put("monday", df1.parse((df.format(cal.getTime()) + " 00:00:00")));
        System.out.println(df.format(cal.getTime()));
        // 这种输出的是上个星期周日的日期，因为老外那边把周日当成第一天
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        // 增加一个星期，才是我们中国人理解的本周日的日期
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        map.put("sunday", df1.parse((df.format(cal.getTime()) + " 23:59:00")));
        System.out.println(df.format(cal.getTime()));
        return map;
    }

    public static LinkedHashMap<Integer, PjAptsTaskUser> fl(Date startDate, Date FinishDate) throws Exception {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        LinkedHashMap<Integer, PjAptsTaskUser> map = new LinkedHashMap<Integer, PjAptsTaskUser>();
        Date md = startDate;
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int i = 1;
        while (!md.after(FinishDate)) {
            int start = dayForWeek(md);
            PjAptsTaskUser tt = new PjAptsTaskUser();
            tt.setStartDate(md);
            md = getSomeDay(md, 7 - start);
            if (md.after(FinishDate)) {
                md = FinishDate;
            }
            tt.setFinishDate(md);
            map.put(i, tt);
            i++;
            md = getSomeDay(md, 1);
        }
//	        for(Entry<Integer, PjAptsTaskUser> t:map.entrySet()){
//	        	System.out.println(sf.format(t.getValue().getStartDate())+"-----第"+t.getKey()+"周-----"+sf.format(t.getValue().getFinishDate()));
//	        }
        return map;
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 修要判断的时间
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */
    public static int dayForWeek(Date startDate) throws Exception {
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    public static Date getSomeDay(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 返回第几周
     *
     * @param startDate
     * @param FinishDate
     * @param date
     * @return
     * @throws Exception
     */
    public static int findWeekCount(Date startDate, Date FinishDate, Date date) throws Exception {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 获取本周一的日期
        date = cal.getTime();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LinkedHashMap<Integer, PjAptsTaskUser> map = new LinkedHashMap<Integer, PjAptsTaskUser>();
        Date md = startDate;
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        int i = 1;
        while (!md.after(FinishDate)) {
            int start = dayForWeek(md);
            PjAptsTaskUser tt = new PjAptsTaskUser();
            tt.setStartDate(md);
            md = getSomeDay(md, 7 - start);
            if (md.after(FinishDate)) {
                md = FinishDate;
            }
            tt.setFinishDate(md);
            map.put(i, tt);
            if (!(tt.getStartDate().compareTo(date) == -1)) {
                return i;
            }
            i++;
            md = getSomeDay(md, 1);
        }
        return 0;
    }

    public static String findWeekNum(Date date) {
        String str = "";
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int weekday = c.get(Calendar.DAY_OF_WEEK);
        if (weekday == 1) {
            str = "星期天";
        } else if (weekday == 7) {
            str = "星期六";
        } else if (weekday == 6) {
            str = "星期五";
        } else if (weekday == 5) {
            str = "星期四";
        } else if (weekday == 4) {
            str = "星期三";
        } else if (weekday == 3) {
            str = "星期二";
        } else if (weekday == 2) {
            str = "星期一";
        }
        return str;
    }

    /**
     * 把秒转换为时分秒
     *
     * @param seconds
     * @return
     */
    public static String secondsToTime(long seconds) {
        long h = 0;
        long d = 0;
        long s = 0;
        long temp = seconds % 3600;
        if (seconds > 3600) {
            h = seconds / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = seconds / 60;
            if (seconds % 60 != 0) {
                s = seconds % 60;
            }
        }
        return (h < 10 ? "0" + h : h) + ":" + (d < 10 ? "0" + d : d) + ":"
                + (s < 10 ? "0" + s : s);
    }
}
