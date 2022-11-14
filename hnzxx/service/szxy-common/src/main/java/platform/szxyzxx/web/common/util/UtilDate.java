package platform.szxyzxx.web.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 计算创建时间具体当前系统时间有多长
 * @author huangyanchun
 *
 */
public class UtilDate {

	
	/**获取当前时间与创建时间的间隔字段*/
	public static String getTimeInterval(Date tag){
		if(tag != null){
			Date current = new Date();
			long interval = current.getTime() - tag.getTime();
			current = null;
			if(interval < (60 * 1000))
				//60秒内
				return "刚刚";
			else if(interval < (60 * 60 * 1000))
				//60分钟内
				return interval / (60 * 1000) + "分钟前";
			else if(interval < (7 * 24 * 60 * 60 * 1000)){
				Calendar calendar = Calendar.getInstance();
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 1);
				//设置为今天的开始时间
				Date dateOfStart = calendar.getTime();
				//是否是这一天的开始之前
				if(interval < (24 * 60 * 60 * 1000)){
					boolean flag = tag.before(dateOfStart);
					if(flag){
						//一天以内并日期在零点之前
						return 1 + "天前";
					}
					//24小时内
					return interval / (60 * 60 * 1000) + "小时前";
				}
				//设置为发布日期的那一天的开始时间
				calendar.setTime(tag);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 1);
				dateOfStart = calendar.getTime();
				current = new Date();
				interval = current.getTime() - dateOfStart.getTime();
				current = null;
				//一周前
				return (interval / (24 * 60 * 60 * 1000)) + "天前";
			}
			else
				//具体日期
				return timeStampToStr(tag, "yyyy-MM-dd");
		}
		return null;
	}

	
	/**
	 * 时间戳转化为时间格式
	 * @param data 时间
	 * @param dataFormat  格式 yyyy-MM-dd HH:mm:ss   yyyy-MM-dd
	 * @return
	 */
		public static String timeStampToStr(Date data,String dataFormat) {
			SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);
			String date = sdf.format(data);
			return date;
		}
	
	
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
}
