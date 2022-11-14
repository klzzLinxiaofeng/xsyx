package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SchoolCalendar;
import platform.education.generalTeachingAffair.vo.SchoolCalendarCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SchoolCalendarDao extends GenericDao<SchoolCalendar, java.lang.Integer> {

	List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition, Page page, Order order);
	
	SchoolCalendar findById(Integer id);
	
	Long count(SchoolCalendarCondition schoolCalendarCondition);
	
}
