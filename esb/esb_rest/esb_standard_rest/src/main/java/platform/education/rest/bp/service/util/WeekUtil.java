package platform.education.rest.bp.service.util;

import platform.education.rest.bp.service.vo.WeekVo;

import java.text.DateFormatSymbols;
import java.util.*;


public class WeekUtil {
	public static List<WeekVo> getWeeks(Date begin,Date finish){
	     Calendar c_begin = new GregorianCalendar();
	     Calendar c_end = new GregorianCalendar();
	     Calendar end = new GregorianCalendar();
	     DateFormatSymbols dfs = new DateFormatSymbols();
	     c_begin.setTime(begin);
	     c_end.setTime(finish);
	     
	     List<WeekVo> list = new ArrayList<WeekVo>();
	     int count = 1;
	     end.setTime(finish);
	     c_end.add(Calendar.DAY_OF_YEAR, 1);  //结束日期下滚一天是为了包含最后一天
	     Boolean flag = true;
	     Date start = null;
	     while(c_begin.before(c_end)){
	    	 WeekVo vo = new WeekVo();
	    	 vo.setIsCurrent(false);
	    	 if(flag){
	    		 start = c_begin.getTime();
	    		 flag = false;
	    	 }
	      if(c_begin.get(Calendar.DAY_OF_WEEK)==Calendar.SUNDAY || c_begin.equals(end)){
	    	  vo.setBegin(start);
	    	  vo.setEnd(c_begin.getTime());
	    	  vo.setWeek(count);
	    	  if(vo.getBegin().before(new Date())&&vo.getEnd().after(new Date())){
	    		  vo.setIsCurrent(true);
	    	  }
	    	  list.add(vo);
	          count++;
	          flag = true;
	      }
	      
	      c_begin.add(Calendar.DAY_OF_YEAR, 1);
	     }
	     return list;
	}
	
	public static WeekVo Week(Date begin,Date finish,Date date){
		List<WeekVo> list = getWeeks(begin,finish);
		WeekVo week = new WeekVo();
		for(WeekVo vo:list){
			if(vo.getBegin().before(date)&&vo.getEnd().after(date)){
				week = vo;
				break;
			}
			
		}
		return week;
	}
	
	
	public static WeekVo getDate(Date begin,Date finish,Integer week){
		List<WeekVo> list = getWeeks(begin,finish);
		WeekVo weekVo = new WeekVo();
		for(WeekVo vo:list){
			if(vo.getWeek().equals(week)){
				weekVo = vo;
				break;
			}
			
		}
		return weekVo;
	}
	
	
}
