package platform.education.oa.dao;

import platform.education.oa.model.ScheduleRemind;
import platform.education.oa.vo.ScheduleRemindCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ScheduleRemindDao extends GenericDao<ScheduleRemind, java.lang.Integer> {

	List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition, Page page, Order order);
	
	ScheduleRemind findById(Integer id);
	
	Long count(ScheduleRemindCondition scheduleRemindCondition);
	
}
