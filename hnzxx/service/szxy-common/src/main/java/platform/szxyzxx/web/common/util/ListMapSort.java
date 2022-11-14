package platform.szxyzxx.web.common.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * <p>Title:ListMapSort.java</p>
 * <p>Description:数字校园系统</p>
 * <p>Copyright: Copyright (c) 2010-2015</p>
 * <p>Company: 广州迅云教育科技有限公司</p>
 * @Function 功能描述：缓存数据排序
 * @Author 谭扬
 * @Version 1.0
 * @Date 2014年9月29日
 */
public class ListMapSort {
	public static void main(String[] args) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> data1 = new HashMap<String, String>();
		Map<String, String> data2 = new HashMap<String, String>();
		Map<String, String> data3 = new HashMap<String, String>();
		data1.put("BEGIN_DATA", "2014-01-05");
		data2.put("BEGIN_DATA", "2014-01-01");
		data3.put("BEGIN_DATA", "2014-01-08");
		list.add(data1);
		list.add(data2);
		list.add(data3);
		// 排序前
		System.out.println("Before sort.");
		for (Map<String, String> m : list) {
			System.out.println(m.get("BEGIN_DATA"));
		}
		// 排序
		Collections.sort(list, new MapComparator());
		// 排序后
		System.out.println("After sort.");
		for (Map<String, String> m : list) {
			System.out.println(m.get("BEGIN_DATA"));
		}
	}

	public static class MapComparator implements Comparator<Map<String, String>> {
		@Override
		public int compare(Map<String, String> o1, Map<String, String> o2) {
			// TODO Auto-generated method stub
			String b1 = o1.get("BEGIN_DATA");
			String b2 = o2.get("BEGIN_DATA");
			if (b2 != null) {
				return b1.compareTo(b2);
			}
			return 0;
		}
	}
}
