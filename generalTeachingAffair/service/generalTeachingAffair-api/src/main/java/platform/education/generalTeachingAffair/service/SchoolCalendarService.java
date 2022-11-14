package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.SchoolCalendar;
import platform.education.generalTeachingAffair.vo.SchoolCalendarCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolCalendarService {
    SchoolCalendar findSchoolCalendarById(Integer id);
	   
	SchoolCalendar add(SchoolCalendar schoolCalendar);
	   
	SchoolCalendar modify(SchoolCalendar schoolCalendar);
	   
	void remove(SchoolCalendar schoolCalendar);
	   
	List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition, Page page, Order order);
	
	List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition);
	
	List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition, Page page);
	
	List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition, Order order);
	
	Long count();
	
	Long count(SchoolCalendarCondition condition);
	
}
