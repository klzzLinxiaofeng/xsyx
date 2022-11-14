package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SchoolCalendarDay;
import platform.education.generalTeachingAffair.vo.SchoolCalendarDayCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolCalendarDayService {
    SchoolCalendarDay findSchoolCalendarDayById(Integer id);
	   
	SchoolCalendarDay add(SchoolCalendarDay schoolCalendarDay);
	   
	SchoolCalendarDay modify(SchoolCalendarDay schoolCalendarDay);
	   
	void remove(SchoolCalendarDay schoolCalendarDay);
	   
	List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition, Page page, Order order);
	
	List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition);
	
	List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition, Page page);
	
	List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition, Order order);
	
	Long count();
	
	Long count(SchoolCalendarDayCondition schoolCalendarDayCondition);
	
}
