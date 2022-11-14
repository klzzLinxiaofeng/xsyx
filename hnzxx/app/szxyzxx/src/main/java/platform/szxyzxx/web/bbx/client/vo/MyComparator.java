package platform.szxyzxx.web.bbx.client.vo;

import java.util.Comparator;

public class MyComparator implements Comparator{


	// 这个method在这里没有用，所以没有具体实现
	@Override
    public boolean equals (Object o){return false;}

	@Override
	public int compare(Object o1, Object o2) {
		GradeClientVo s1 = (GradeClientVo)o1;
		GradeClientVo s2 = (GradeClientVo)o2;
		int code1 = Integer.parseInt(s1.getStageCode());
		int code2 = Integer.parseInt(s2.getStageCode());
	    if (code1!=code2) //如果名字不一样
	        return code1-code2;
	    else // 如果名字一样
	        return s1.getGradeNumber() - s2.getGradeNumber();
	}
	
	
	
}
