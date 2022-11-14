package platform.szxyzxx.web.bbx.util;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 一些日期的基本功能
 * @author hmzhang
 *
 */
public class DateUtil {
	private final static SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");  
	private final static SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private final static SimpleDateFormat timeSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	
	/** 
     * 获得本天的开始时间，即2012-01-01 00:00:00 
     *  
     * @return 
     */  
    public static Date getCurrentDayStartTime() {  
        Date now = new Date();  
        try {  
            now = shortSdf.parse(shortSdf.format(now));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return now;  
    } 
    
    /** 
     * 获得某一天的开始时间，即2012-01-01 00:00:00 
     *  
     * @return 
     */  
    public static Date getDayStartTime(Date date) {  
        try {  
        	date = shortSdf.parse(shortSdf.format(date));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return date;  
    } 
    
    /**
     * 获得几天后的时间
     * @param num
     * @return
     */
    public static Date getDayslaterDate(Integer num){
    	Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
        calendar.add(Calendar.DAY_OF_YEAR, num);
        Date date = calendar.getTime();
        try {  
        	date = shortSdf.parse(shortSdf.format(date));  
        } catch (Exception e) {  
            e.printStackTrace();  
        } 
        return date;
    }
    
    
    /** 
     * 获得本天的结束时间，即2012-01-01 23:59:59 
     *  
     * @return 
     */  
    public static Date getCurrentDayEndTime() {  
        Date now = new Date();  
        try {  
            now = longSdf.parse(shortSdf.format(now) + " 23:59:59");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return now;  
    }  
    
    /**
     * 重载方法，获得某一天的结束时间
     * @param date
     * @return
     */
    public static Date getDayEndTime(Date date) {  
        try {  
            date = longSdf.parse(shortSdf.format(date) + " 23:59:59");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return date;  
    }
    
    /** 
     * 获得本周的第一天，周一 
     *  
     * @return 
     */  
    public static Date getCurrentWeekDayStartTime() {  
        Calendar c = Calendar.getInstance();  
        try {  
            int weekday = c.get(Calendar.DAY_OF_WEEK) - 2;  
            c.add(Calendar.DATE, -weekday);  
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return c.getTime();  
    }  
  
    /** 
     * 获得本周的最后一天，周日 
     *  
     * @return 
     */  
    public static Date getCurrentWeekDayEndTime() {  
        Calendar c = Calendar.getInstance();  
        try {  
            int weekday = c.get(Calendar.DAY_OF_WEEK);  
            c.add(Calendar.DATE, 8 - weekday);  
            c.setTime(longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59"));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return c.getTime();  
    }  

    /**
	   * 设置多久之后
	   * @param args
	   * oldDate : 指的是想要计算距离现在的时间
	   * return x秒前  or x分钟前  or x小时前  or x天前  or yyyy年MM月dd日 HH:mm:ss
	   * if day > 7 show yyyy年MM月dd日 HH:mm:ss
	   */
	  public static String howAgoDistanceNow(Date oldDate){
		  DateFormat df = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
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
		    					distanceNow = "刚刚";
		    				}else{
		    					distanceNow = s + "秒前";		    			
		    				}
		    			}else{
		    				distanceNow = min + "分钟前";
				    	}
			    	}else{
			    		distanceNow = hour + "小时前";
			    	}
		    	}else{
//		    		distanceNow = day + "天前";
		    		if(day>7){
		    			distanceNow = df.format(oldDate);
		    		}else{
		    			distanceNow = day + "天前";
		    		}
		    	}
		    }else{
//		    	distanceNow = year + "年前";
		    	distanceNow = df.format(oldDate);
		    }
		  return distanceNow;
	  }
	 
	  /**
		 * 根据日期获得星期
		 * 
		 * @param date
		 * @return
		 */
	public static String getWeekOfDate(Date date) {
		String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return weekDaysName[intWeek];
	}
	
	/**
	 * 判断一个日期是否在一段时间内
	 */
	public static boolean isInDate(Date date,Date startDate,Date endDate){
		boolean s1 = startDate.getTime()<=date.getTime();
		boolean s2 = date.getTime()<=endDate.getTime();
		return s1&s2;
	}
	/**
	 * 获得指定时间前几天的时间
	 * @param date
	 * @param count
	 * @param add  加或者减
	 * @return
	 */
	public static Date getDay(Date date,int count,boolean add) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, add?+count:-count);
		date = calendar.getTime();
		return date;
	}
	
	/**
	 * date转为需要格式的String
	 * @param date
	 * @return
	 */
	public static String dateToString(Date date){
		if(date==null){
		  return null;	
		}
		return longSdf.format(date);
	}
	
	
	/**
	 * date转换成需要格式的String（只转换到时分，不转换到秒 ）
	 * @param date
	 * @return
	 */
   public static String timeToString(Date date){
	   if(date==null){
		   return null;
	   }
	   
	   return timeSdf.format(date);
   }
   
   /** 
    * 获得某时间所在周的7天时间数组（倒序，周日排第一） 
    * @return 
    */  
	public static List<Date> getWeekDayList(Date date) { 
		List<Date> weekList = new ArrayList<Date>();	    
		try {  
			Calendar c = Calendar.getInstance(); 
    	   	c.setTime(date);	   	   
    	   	int weekday = c.get(Calendar.DAY_OF_WEEK);  
			c.add(Calendar.DATE, 8 - weekday);  
			c.setTime(shortSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00"));  
				
			Date weekendDay = c.getTime();
			weekList.add(weekendDay);
			for(int i=1; i<7; i++){
				c = Calendar.getInstance();  
				c.setTime(weekendDay);
				c.add(Calendar.DATE, -i);
				weekList.add(c.getTime());
			}
		} catch (Exception e) {  
			e.printStackTrace();  
       }  
		return weekList;  
	} 
	
	/**
	 * 获取当前时间n周前的日期
	 * @param n
	 * @return
	 */
	public static Date getNWeekDay(Integer n){
		Calendar c = Calendar.getInstance(); 
		try { 
			Date date = new Date(); 
	   	    c.setTime(date);
	   	    c.add(Calendar.DATE, (-7)*n);
		} catch (Exception e) {  
			e.printStackTrace();   
		}
		return c.getTime();
	}
   
	/**
	 * 根据日期获得星期
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfDateInt(Date date) {
		//String[] weekDaysName = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int intWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1;
		return intWeek;
	} 
	
}
