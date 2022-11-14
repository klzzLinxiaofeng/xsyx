package platform.education.oa.dao;

import platform.education.oa.model.ScheduleUser;
import platform.education.oa.vo.ScheduleUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ScheduleUserDao extends GenericDao<ScheduleUser, java.lang.Integer> {

	List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition, Page page, Order order);
	
	ScheduleUser findById(Integer id);
	
	Long count(ScheduleUserCondition scheduleUserCondition);
	
}
