package platform.szxyzxx.web.bbx.client.vo;

import java.util.Comparator;

/**
 * 给list排序
 * 
 * @author hmzhang 
 *
 */
public class ComTeamComparator implements Comparator{
	
	 public int compare(Object obj0, Object obj1) {
		 CommonTeacherTeam ctt0 = (CommonTeacherTeam)obj0;
		 CommonTeacherTeam ctt1 = (CommonTeacherTeam)obj1;
		 /**
		  * 根据年级排序，再根据班级排序
		  */
		 int flag = ctt0.getUniGradeCode().compareTo(ctt1.getUniGradeCode());
		 if(flag==0){
			 return ctt0.getTeamNumber().compareTo(ctt1.getTeamNumber());
		 }else{
			 return flag;
		 } 
	}
		
}
