package platform.szxyzxx.web.teach.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import platform.education.generalTeachingAffair.model.SchoolTerm;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.vo.SchoolTermCondition;

public class DateUtil {
	
	private static SchoolTermService schoolTermService;
	
	public void setSchoolTermService(SchoolTermService schoolTermService) {
		this.schoolTermService = schoolTermService;
	}

	/**
	 * 获取月份的第一天和最后一天
	 * @param date  xxxx年x月
	 * @return
	 * @throws ParseException
	 */
	public static Date[] monthTime(String date) throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int year = Integer.parseInt(date.substring(0, date.indexOf("年")));
		int month = Integer.parseInt(date.substring(date.indexOf("年") + 1, date.indexOf("月")));

		// 当月第一天
		Calendar cal1 = Calendar.getInstance();
		cal1.set(Calendar.YEAR, year);
		cal1.set(Calendar.MONTH, month - 1);
		cal1.set(Calendar.DAY_OF_MONTH, cal1.getMinimum(Calendar.DATE));
		String begin = sdf.format(cal1.getTime());
		Date beginDate = sdf.parse(begin);

		// 当月最后一天
		Calendar cal2 = Calendar.getInstance();
		cal2.set(Calendar.YEAR, year);
		cal2.set(Calendar.MONTH, month - 1);
		cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DATE));
		String end = sdf.format(cal2.getTime());
		Date endDate = sdf.parse(end);

		Date[] monthDate = { beginDate, endDate };
		return monthDate;
	}
	
	/**
	 * 获取周次的第一天和最后一天
	 * @param week  第x周(xxxx-xx-xx~xxxx-xx-xx)
	 * @return
	 * @throws ParseException
	 */
	public static Date[] weekTime(String week) throws ParseException {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		week = week.substring(week.indexOf("(")+1, week.indexOf(")"));
		String[] dayStr = week.split("~\\s*");

		String startDate = dayStr[0];
		Date beginDate = sdf.parse(startDate);
		String finishDate = dayStr[1];
		Date endDate = sdf.parse(finishDate);

		Date[] weekDate = { beginDate, endDate };
		return weekDate;
	}
	
	public static Date[] startAndEndTime(Integer schoolId, String termCode, String month, String week) throws ParseException{
		Date beginDate = null;
		Date endDate = null;
		if (week != null && !"".equals(week)) {
			Date[] weekDate = weekTime(week);
			beginDate = weekDate[0];
			endDate = weekDate[1];
		} else if (month != null && !"".equals(month)) {
			Date[] monthDate = monthTime(month);
			beginDate = monthDate[0];
			endDate = monthDate[1];
		} else if (termCode != null && !"".equals(termCode)) {
			SchoolTermCondition condition = new SchoolTermCondition();
			condition.setSchoolId(schoolId);
			condition.setCode(termCode);
			List<SchoolTerm> list = schoolTermService.findSchoolTermByCondition(condition, null, null);
			beginDate = list.get(0).getBeginDate();
			endDate = list.get(0).getFinishDate();
		}
		Date [] dateArr = {beginDate, endDate};
		return dateArr;
	}
	
}
