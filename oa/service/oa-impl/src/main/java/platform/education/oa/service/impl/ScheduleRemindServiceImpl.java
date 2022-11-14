package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.ScheduleRemind;
import platform.education.oa.vo.ScheduleRemindCondition;
import platform.education.oa.service.ScheduleRemindService;
import platform.education.oa.dao.ScheduleRemindDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ScheduleRemindServiceImpl implements ScheduleRemindService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ScheduleRemindDao scheduleRemindDao;

	public void setScheduleRemindDao(ScheduleRemindDao scheduleRemindDao) {
		this.scheduleRemindDao = scheduleRemindDao;
	}
	
	@Override
	public ScheduleRemind findScheduleRemindById(Integer id) {
		try {
			return scheduleRemindDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ScheduleRemind add(ScheduleRemind scheduleRemind) {
		if(scheduleRemind == null) {
    		return null;
    	}
    	Date createDate = scheduleRemind.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	scheduleRemind.setCreateDate(createDate);
    	scheduleRemind.setModifyDate(createDate);
		return scheduleRemindDao.create(scheduleRemind);
	}

	@Override
	public ScheduleRemind modify(ScheduleRemind scheduleRemind) {
		if(scheduleRemind == null) {
    		return null;
    	}
    	Date modify = scheduleRemind.getModifyDate();
    	scheduleRemind.setModifyDate(modify != null ? modify : new Date());
		return scheduleRemindDao.update(scheduleRemind);
	}
	
	@Override
	public void remove(ScheduleRemind scheduleRemind) {
		try {
			scheduleRemindDao.delete(scheduleRemind);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", scheduleRemind.getId(), e);
			}
		}
	}
	
	@Override
	public List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition, Page page, Order order) {
		return scheduleRemindDao.findScheduleRemindByCondition(scheduleRemindCondition, page, order);
	}
	
	@Override
	public List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition) {
		return scheduleRemindDao.findScheduleRemindByCondition(scheduleRemindCondition, null, null);
	}
	
	@Override
	public List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition, Page page) {
		return scheduleRemindDao.findScheduleRemindByCondition(scheduleRemindCondition, page, null);
	}
	
	@Override
	public List<ScheduleRemind> findScheduleRemindByCondition(ScheduleRemindCondition scheduleRemindCondition, Order order) {
		return scheduleRemindDao.findScheduleRemindByCondition(scheduleRemindCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.scheduleRemindDao.count(null);
	}

	@Override
	public Long count(ScheduleRemindCondition scheduleRemindCondition) {
		return this.scheduleRemindDao.count(scheduleRemindCondition);
	}

}
