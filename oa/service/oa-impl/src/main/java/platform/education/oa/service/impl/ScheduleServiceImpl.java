package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.Schedule;
import platform.education.oa.vo.ScheduleCondition;
import platform.education.oa.service.ScheduleService;
import platform.education.oa.dao.ScheduleDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ScheduleServiceImpl implements ScheduleService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ScheduleDao scheduleDao;

	public void setScheduleDao(ScheduleDao scheduleDao) {
		this.scheduleDao = scheduleDao;
	}
	
	@Override
	public Schedule findScheduleById(Integer id) {
		try {
			return scheduleDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Schedule add(Schedule schedule) {
		if(schedule == null) {
    		return null;
    	}
    	Date createDate = schedule.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	schedule.setCreateDate(createDate);
    	schedule.setModifyDate(createDate);
		return scheduleDao.create(schedule);
	}

	@Override
	public Schedule modify(Schedule schedule) {
		if(schedule == null) {
    		return null;
    	}
    	Date modify = schedule.getModifyDate();
    	schedule.setModifyDate(modify != null ? modify : new Date());
		return scheduleDao.update(schedule);
	}
	
	@Override
	public void remove(Schedule schedule) {
		try {
			scheduleDao.delete(schedule);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", schedule.getId(), e);
			}
		}
	}
	
	@Override
	public List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition, Page page, Order order) {
		return scheduleDao.findScheduleByCondition(scheduleCondition, page, order);
	}
	
	@Override
	public List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition) {
		return scheduleDao.findScheduleByCondition(scheduleCondition, null, null);
	}
	
	@Override
	public List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition, Page page) {
		return scheduleDao.findScheduleByCondition(scheduleCondition, page, null);
	}
	
	@Override
	public List<Schedule> findScheduleByCondition(ScheduleCondition scheduleCondition, Order order) {
		return scheduleDao.findScheduleByCondition(scheduleCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.scheduleDao.count(null);
	}

	@Override
	public Long count(ScheduleCondition scheduleCondition) {
		return this.scheduleDao.count(scheduleCondition);
	}
  /**
   * 根据时间范围查询日程
   */
	public List<Schedule> findSheduleByDate(Integer ownerId,String ownerType,
			Integer userId, String starttime,String endtime){
		return this.scheduleDao.findSheduleByDate(ownerId, ownerType, userId, starttime, endtime, null, null);
	}
	public List<Schedule> findSheduleByUser(Integer ownerId,String ownerType,
			Integer userId, Integer new_or_old,String baseline_date,Page page, Order order){
		return this.scheduleDao.findSheduleByUser(ownerId, ownerType, userId, new_or_old, baseline_date, page, order);
	}

	@Override
	public List<Schedule> findScheduleByInterval(ScheduleCondition condition) {
		// TODO Auto-generated method stub
		return scheduleDao.findScheduleByInterval(condition);
	}

	@Override
	public Integer countByData(ScheduleCondition scheduleCondition) {
		// TODO Auto-generated method stub
		return this.scheduleDao.countByData(scheduleCondition);
	}
}
