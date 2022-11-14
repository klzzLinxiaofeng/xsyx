package platform.education.oa.service;
import platform.education.oa.model.ScheduleUser;
import platform.education.oa.vo.ScheduleUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ScheduleUserService {
    ScheduleUser findScheduleUserById(Integer id);
	   
	ScheduleUser add(ScheduleUser scheduleUser);
	   
	ScheduleUser modify(ScheduleUser scheduleUser);
	   
	void remove(ScheduleUser scheduleUser);
	   
	List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition, Page page, Order order);
	
	List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition);
	
	List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition, Page page);
	
	List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition, Order order);
	
	Long count();
	
	Long count(ScheduleUserCondition scheduleUserCondition);
	
}
