package platform.education.oa.service;
import platform.education.oa.model.Schedule;
import platform.education.oa.vo.ScheduleCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface ScheduleService {
    Schedule findScheduleById(Integer id);
	   
	Schedule add(Schedule schedule);
	   
	Schedule modify(Schedule schedule);
	   
	void remove(Schedule schedule);
	   
	List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition, Page page, Order order);
	
	List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition);
	
	List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition, Page page);
	
	List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition, Order order);
	
	Long count();
	
	Long count(ScheduleCondition scheduleCondition);
	
	List<Schedule> findSheduleByDate(Integer ownerId,String ownerType,
			Integer userId, String starttime,String endtime);
	
	List<Schedule> findSheduleByUser(Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order);
	/**
	 * 根据时间间隔进行数据查找
	 * @param condition
	 * @return
	 */
	List<Schedule> findScheduleByInterval(ScheduleCondition condition);

	Integer countByData(ScheduleCondition scheduleCondition);
	
}
