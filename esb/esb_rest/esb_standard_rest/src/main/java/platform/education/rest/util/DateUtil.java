package platform.education.rest.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	private static String datetimeFormat = "yyyy-MM-dd HH:mm";
	/**
	 * 	
	* @Title: compare_date
	* @author pantq 
	* @Description: 判断两个日期的大小
	* @param endDate 结束时间 格式： yyyy-MM-dd HH:mm
	* @param nowDate 当前时间 格式： yyyy-MM-dd HH:mm
	* @return    设定文件 
	* @return int    返回类型  endDate 小于 nowDate 返回false，大于返回true
	* @throws
	 */
		
	public static Boolean compareDate(String timeStamp) {
			boolean flag = false;
	        DateFormat df = new SimpleDateFormat(datetimeFormat);
	        
	        //系统时间
	        Date date = new Date();
	        String nowDateStr =  df.format(date);
	        
	        try {
	        	//调用方时间戳
	        	Long time=new Long(timeStamp);  
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
		public static Date strToDate(String str,String fomatStr) {
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
			if(date!=null){
				 SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				 str = format.format(date);
			}
		  
		   return str;
		} 
		
		public static void main(String[] args) {
			
			 	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        long lt = new Long(new Date().getTime()+"");
		        Date date = new Date(lt);
		        String res = simpleDateFormat.format(date);
			System.out.println(res);
			System.out.println();
		}
}
