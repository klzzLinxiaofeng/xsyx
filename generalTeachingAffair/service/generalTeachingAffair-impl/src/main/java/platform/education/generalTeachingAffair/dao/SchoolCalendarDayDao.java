package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SchoolCalendarDay;
import platform.education.generalTeachingAffair.vo.SchoolCalendarDayCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SchoolCalendarDayDao extends GenericDao<SchoolCalendarDay, java.lang.Integer> {

	List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition, Page page, Order order);
	
	SchoolCalendarDay findById(Integer id);
	
	Long count(SchoolCalendarDayCondition schoolCalendarDayCondition);
	
}
