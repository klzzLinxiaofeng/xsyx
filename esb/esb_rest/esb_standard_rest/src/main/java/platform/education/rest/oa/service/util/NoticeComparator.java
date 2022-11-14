package platform.education.rest.oa.service.util;

import java.util.Comparator;

import platform.education.notice.model.Notice;

public class NoticeComparator implements Comparator<Notice> {

	@Override // 按发送时间降序排列
	public int compare(Notice o1, Notice o2) {
		int compareValue = o1.getPostTime().compareTo(o2.getPostTime());
		return compareValue == 0 ? 0 : (compareValue < 0 ? 1 : -1);
	}

}
