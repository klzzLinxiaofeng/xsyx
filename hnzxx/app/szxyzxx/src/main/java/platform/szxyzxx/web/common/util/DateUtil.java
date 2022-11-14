package platform.szxyzxx.web.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
		public static String dateToStr(Date date) {
			 String str = "";
			if(date!=null){
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				 str = format.format(date);
			}
		  
		   return str;
		} 

		/**
		* 字符串转换成日期
		* @param str
		* @return date
		*/
		public static Date strToDate(String str) {
		   SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
			if(date!=null){
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 str = format.format(date);
			}
		  
		   return str;
		} 
}
