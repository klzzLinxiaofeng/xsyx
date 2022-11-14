package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ScheduleUser;
import platform.education.oa.vo.ScheduleUserCondition;
import platform.education.oa.service.ScheduleUserService;
import platform.education.oa.dao.ScheduleUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ScheduleUserServiceImpl implements ScheduleUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ScheduleUserDao scheduleUserDao;

	public void setScheduleUserDao(ScheduleUserDao scheduleUserDao) {
		this.scheduleUserDao = scheduleUserDao;
	}
	
	@Override
	public ScheduleUser findScheduleUserById(Integer id) {
		try {
			return scheduleUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ScheduleUser add(ScheduleUser scheduleUser) {
		if(scheduleUser == null) {
    		return null;
    	}
    	Date createDate = scheduleUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	scheduleUser.setCreateDate(createDate);
    	scheduleUser.setModifyDate(createDate);
		return scheduleUserDao.create(scheduleUser);
	}

	@Override
	public ScheduleUser modify(ScheduleUser scheduleUser) {
		if(scheduleUser == null) {
    		return null;
    	}
    	Date modify = scheduleUser.getModifyDate();
    	scheduleUser.setModifyDate(modify != null ? modify : new Date());
		return scheduleUserDao.update(scheduleUser);
	}
	
	@Override
	public void remove(ScheduleUser scheduleUser) {
		try {
			scheduleUserDao.delete(scheduleUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", scheduleUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition, Page page, Order order) {
		return scheduleUserDao.findScheduleUserByCondition(scheduleUserCondition, page, order);
	}
	
	@Override
	public List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition) {
		return scheduleUserDao.findScheduleUserByCondition(scheduleUserCondition, null, null);
	}
	
	@Override
	public List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition, Page page) {
		return scheduleUserDao.findScheduleUserByCondition(scheduleUserCondition, page, null);
	}
	
	@Override
	public List<ScheduleUser> findScheduleUserByCondition(ScheduleUserCondition scheduleUserCondition, Order order) {
		return scheduleUserDao.findScheduleUserByCondition(scheduleUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.scheduleUserDao.count(null);
	}

	@Override
	public Long count(ScheduleUserCondition scheduleUserCondition) {
		return this.scheduleUserDao.count(scheduleUserCondition);
	}

}
