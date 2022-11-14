package platform.szxyzxx.web.teach.util;

import java.util.Date;

public class DateUtils {
	public static Long getDateBetween(Date end, Date begin) {
		if(end==null || begin==null) {
			return 0L;
		}
		Long diff = end.getTime() - begin.getTime();
		diff = diff/(1000*60*60*24);
		return diff;
	}
}
