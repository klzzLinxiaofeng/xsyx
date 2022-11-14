package platform.education.oa.service;
import platform.education.oa.model.ScheduleRemind;
import platform.education.oa.vo.ScheduleRemindCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ScheduleRemindService {
    ScheduleRemind findScheduleRemindById(Integer id);
	   
	ScheduleRemind add(ScheduleRemind scheduleRemind);
	   
	ScheduleRemind modify(ScheduleRemind scheduleRemind);
	   
	void remove(ScheduleRemind scheduleRemind);
	   
	List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition, Page page, Order order);
	
	List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition);
	
	List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition, Page page);
	
	List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition, Order order);
	
	Long count();
	
	Long count(ScheduleRemindCondition scheduleRemindCondition);
	
}
