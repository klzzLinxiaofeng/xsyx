/**   
* @Title: DateUtil.java
* @Package platform.education.paper.util 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月27日 下午1:14:54 
* @version V1.0   
*/
package platform.education.paper.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 时间工具类
 * @author pantq
 * @date 2017年2月27日 下午1:14:54
 * 
 */
public class DateUtil {
	
	static String datetimeFormat = "yyyy-MM-dd HH:mm";
	
	/**
	 * 测试
	 */
	public static void main(String[] args) {
		/*System.out.println(DateUtil.isInTime("20:00-01:00", "01:00"));
		System.out.println(DateUtil.isInTime("20:00-01:00", "00:00"));
		System.out.println(DateUtil.isInTime("20:00-01:00", "03:00"));
		System.out.println();
		System.out.println(DateUtil.isInTime("20:00-23:00", "03:00"));
		System.out.println(DateUtil.isInTime("20:00-23:00", "22:00"));
		System.out.println(DateUtil.isInTime("20:00-23:00", "18:00"));
		System.out.println(DateUtil.isInTime("20:00-23:00", "20:00"));
		System.out.println(DateUtil.isInTime("20:00-23:01", "23:00"));
		*/
		System.out.println(compareDate("2017-05-16 20:10:00", new Date()));
	}

	/**
	 * 判断某一时间是否在一个区间内
	 * 
	 * @author quan
	 * @param sourceTime
	 *            时间区间,半闭合,如[10:00-20:00)
	 * @param curTime
	 *            需要判断的时间 如10:00
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static boolean isInTime(String sourceTime, String curTime) {
		if (sourceTime == null || !sourceTime.contains("-") || !sourceTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}
		if (curTime == null || !curTime.contains(":")) {
			throw new IllegalArgumentException("Illegal Argument arg:" + curTime);
		}
		String[] args = sourceTime.split("-");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		try {
			long now = sdf.parse(curTime).getTime();
			long start = sdf.parse(args[0]).getTime();
			long end = sdf.parse(args[1]).getTime();
			if (args[1].equals("00:00")) {
				args[1] = "24:00";
			}
			if (end < start) {
				if (now >= end && now < start) {
					return false;
				} else {
					return true;
				}
			} else {
				if (now >= start && now < end) {
					return true;
				} else {
					return false;
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Illegal Argument arg:" + sourceTime);
		}

	}

	/**
	 * 
	 * 判断时间是否在时间段内 *
	 * 
	 * @param date
	 * 
	 *            当前时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDateBegin
	 * 
	 *            开始时间 00:00:00
	 * 
	 * @param strDateEnd
	 * 
	 *            结束时间 00:05:00
	 * 
	 * @return
	 * 
	 */

	public static boolean isInDate(Date date, String strDateBegin,

			String strDateEnd) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String strDate = sdf.format(date);

		// 截取当前时间时分秒

		int strDateH = Integer.parseInt(strDate.substring(11, 13));

		int strDateM = Integer.parseInt(strDate.substring(14, 16));

		int strDateS = Integer.parseInt(strDate.substring(17, 19));

		// 截取开始时间时分秒

		int strDateBeginH = Integer.parseInt(strDateBegin.substring(0, 2));

		int strDateBeginM = Integer.parseInt(strDateBegin.substring(3, 5));

		int strDateBeginS = Integer.parseInt(strDateBegin.substring(6, 8));

		// 截取结束时间时分秒

		int strDateEndH = Integer.parseInt(strDateEnd.substring(0, 2));

		int strDateEndM = Integer.parseInt(strDateEnd.substring(3, 5));

		int strDateEndS = Integer.parseInt(strDateEnd.substring(6, 8));

		if ((strDateH >= strDateBeginH && strDateH <= strDateEndH)) {

			// 当前时间小时数在开始时间和结束时间小时数之间

			if (strDateH > strDateBeginH && strDateH < strDateEndH) {

				return true;

				// 当前时间小时数等于开始时间小时数，分钟数在开始和结束之间

			} else if (strDateH == strDateBeginH && strDateM >= strDateBeginM

					&& strDateM <= strDateEndM) {

				return true;

				// 当前时间小时数等于开始时间小时数，分钟数等于开始时间分钟数，秒数在开始和结束之间

			} else if (strDateH == strDateBeginH && strDateM == strDateBeginM

					&& strDateS >= strDateBeginS && strDateS <= strDateEndS) {

				return true;

			}

			// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数小等于结束时间分钟数

			else if (strDateH >= strDateBeginH && strDateH == strDateEndH

					&& strDateM <= strDateEndM) {

				return true;

				// 当前时间小时数大等于开始时间小时数，等于结束时间小时数，分钟数等于结束时间分钟数，秒数小等于结束时间秒数

			} else if (strDateH >= strDateBeginH && strDateH == strDateEndH

					&& strDateM == strDateEndM && strDateS <= strDateEndS) {

				return true;

			} else {

				return false;

			}

		} else {

			return false;

		}

	}
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
	
public static Boolean compareDate(String endDate,Date finishedDate) {
		boolean flag = false;
        DateFormat df = new SimpleDateFormat(datetimeFormat);
        Date date = new Date();
        String nowDateStr =  df.format(date);
        //延迟2分钟收工
        finishedDate.setMinutes(finishedDate.getMinutes() + 2);
        String finishedDateStr = df.format(finishedDate);
        try {
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
	
	
}
