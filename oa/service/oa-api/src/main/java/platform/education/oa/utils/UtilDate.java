package platform.education.oa.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class UtilDate
{
  public static final String dtLong = "yyyyMMddHHmmss";
  public static final String dtLongs = "HHmmss";
  public static final String dtLongts = "HHmm";
  public static final String simple = "yyyy-MM-dd HH:mm:ss";
  public static final String simplet = "HH:mm";
  public static final String dtShort = "yyyyMMdd";
  public static final String simples = "yyyy-MM-dd 00:00:00";
  public static final String simpl = "yyyy-MM-dd";

  public static String getOrderNum()
  {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
    return df.format(date);
  }
  public static Integer getTaskNowTime() {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("HHmm");
    return Integer.valueOf(df.format(date));
  }
  public static Integer getTaskBeginTime(String date) {
    String[] s = date.split(":");
    String dd = s[0] + s[1];
    Integer d = Integer.valueOf(dd);

    return d;
  }
  public static Integer getTaskEndTime(String date) {
    String[] s = date.split(":");
    String dd = s[0] + s[1];
    Integer d = Integer.valueOf(dd);

    return d;
  }

  public static String getDateFormatter()
  {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(date);
  }

  public static String getDateFormatter(Date date)
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return df.format(date);
  }

  public static String getDateFormatter(Date date, String simple)
  {
    DateFormat df = new SimpleDateFormat(simple);
    return df.format(date);
  }

  public static Date getDateFormatters(Date date)
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    try {
      return df.parse(df.format(date));
    } catch (ParseException e) {
    }
    return null;
  }

  public static Date getDateFormatters(String date, String simpl)
  {
    DateFormat df = new SimpleDateFormat(simpl);
    try {
      return df.parse(date);
    } catch (ParseException e) {
    }
    return null;
  }

  public static Date getOldDate(long sum, Date date_today)
  {
    Date date_old = null;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

      String date = sdf.format(date_today);

      date_today = sdf.parse(date);

      long time_today = date_today.getTime();

      long time_old = 0L;

      sum = -sum;
      long time = 86400000L;
      time_old = sum * time + time_today;
      date_old = new Date(time_old);
    }
    catch (Exception localException)
    {
    }

    return date_old;
  }

  public static Date getDateFormatters(String date)
  {
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    try {
      return df.parse(date);
    } catch (ParseException e) {
    }
    return null;
  }

  public static Date getDateFormatters()
  {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
    try {
      return df.parse(df.format(date));
    } catch (ParseException e) {
    }
    return null;
  }

  public static String getDate()
  {
    Date date = new Date();
    DateFormat df = new SimpleDateFormat("yyyyMMdd");
    return df.format(date);
  }

	public static String getThree()
	{
		Random rad = new Random();
		return (new StringBuilder(String.valueOf(rad.nextInt(1000)))).toString();
	}

  public static int getLeftDay(Date d, Date date_today)
  {
    int sum = 0;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

      String date = sdf.format(date_today);

      Date date_old = null;

      SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
      String monthDay = sf.format(d);

      date_old = sdf.parse(date.split("-")[0] + "-" + monthDay);

      date_today = sdf.parse(date);

      long time_today = date_today.getTime();
      long time_old = date_old.getTime();

      sum = (int)(-(time_today - time_old) / 86400000L);
    }
    catch (Exception localException)
    {
    }
    return sum;
  }
  /**
   * ?????????????????? ????????????????????????????????????
   * @param d ????????????
   * @param date_today ????????????
   * @return int??????  ?????????
   */
  public static int getLeftDayTime(Date d, Date date_today)
  {
    int sum = 0;
    try {
    	DateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    	  long startT = dfs.parse(getDateFormatter(d)).getTime();
          
          long endwd = dfs.parse(getDateFormatter(date_today)).getTime();
          long ss = (endwd - startT) / (1000); // ????????????
          sum =   (int)ss / 60; // ???????????????
    }
    catch (Exception localException)
    {
    }
    return sum;
  }

  public static boolean getWeekDay()
  {
    boolean flag = false;

    SimpleDateFormat formatD = new SimpleDateFormat("E");
    Date d = new Date();
    String weekDay = "";
    try
    {
      weekDay = formatD.format(d);
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (("?????????".equals(weekDay)) || ("?????????".equals(weekDay)) || ("?????????".equals(weekDay)) || ("?????????".equals(weekDay)) || ("?????????".equals(weekDay))) {
      flag = true;
    }
    return flag;
  }

  /**
   * ??????????????????
   * @param args
   * oldDate : ??????????????????????????????????????????
   * return x??????  or x?????????  or x?????????  or x??????  or yyyy???MM???dd??? HH:mm:ss
   * if day > 7 show yyyy???MM???dd??? HH:mm:ss
   */
  public static String howAgoDistanceNow(Date oldDate){
	  DateFormat df = new SimpleDateFormat("yyyy???MM???dd??? HH:mm:ss");
	  String distanceNow = "";
	  Date nowDate = new Date();
		Long longAgo = nowDate.getTime() - oldDate.getTime();
		@SuppressWarnings("deprecation")
		int year = nowDate.getYear() - oldDate.getYear();
		long day = longAgo / (24 * 60 * 60 * 1000);
	    long hour = (longAgo / (60 * 60 * 1000) - day * 24);
	    long min = ((longAgo / (60 * 1000)) - day * 24 * 60 - hour * 60);
	    long s = (longAgo / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
	    if(year == 0){
	    	if(day == 0){
	    		if(hour == 0){
	    			if(min == 0){
	    				if(s == 0){
	    					distanceNow = "??????";
	    				}else{
	    					distanceNow = s + "??????";		    			
	    				}
	    			}else{
	    				distanceNow = min + "?????????";
			    	}
		    	}else{
		    		distanceNow = hour + "?????????";
		    	}
	    	}else{
//	    		distanceNow = day + "??????";
	    		if(day>7){
	    			distanceNow = df.format(oldDate);
	    		}else{
	    			distanceNow = day + "??????";
	    		}
	    	}
	    }else{
//	    	distanceNow = year + "??????";
	    	distanceNow = df.format(oldDate);
	    }
	  return distanceNow;
  }
  
  public static void main(String[] args)
  {
	  System.out.println(getOldDate(-2L, new Date()));
    System.out.println(getLeftDayTime(getDateFormatters("2015-07-01 16:47:45", "yyyy-MM-dd HH:mm:ss"),new Date() ));
  }
}