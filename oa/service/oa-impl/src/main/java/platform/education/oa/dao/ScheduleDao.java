package platform.education.oa.dao;

import platform.education.oa.model.Schedule;
import platform.education.oa.vo.ScheduleCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface ScheduleDao extends GenericDao<Schedule, java.lang.Integer> {

	List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition, Page page, Order order);
	
	Schedule findById(Integer id);
	
	Long count(ScheduleCondition scheduleCondition);
	
	List<Schedule> findSheduleByDate(Integer ownerId,String ownerType,
			Integer userId, String starttime,String endtime,Page page, Order order);
	
	List<Schedule> findSheduleByUser(Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order);

	List<Schedule> findScheduleByInterval(ScheduleCondition condition);

	Integer countByData(ScheduleCondition scheduleCondition);
	
}
