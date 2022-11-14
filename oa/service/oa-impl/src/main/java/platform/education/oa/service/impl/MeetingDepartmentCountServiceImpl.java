package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.MeetingDepartmentCount;
import platform.education.oa.vo.MeetingDepartmentCountCondition;
import platform.education.oa.service.MeetingDepartmentCountService;
import platform.education.oa.dao.MeetingDepartmentCountDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class MeetingDepartmentCountServiceImpl implements MeetingDepartmentCountService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private MeetingDepartmentCountDao meetingDepartmentCountDao;

	public void setMeetingDepartmentCountDao(MeetingDepartmentCountDao meetingDepartmentCountDao) {
		this.meetingDepartmentCountDao = meetingDepartmentCountDao;
	}
	
	@Override
	public MeetingDepartmentCount findMeetingDepartmentCountById(Integer id) {
		try {
			return meetingDepartmentCountDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public MeetingDepartmentCount add(MeetingDepartmentCount meetingDepartmentCount) {
		if(meetingDepartmentCount == null) {
    		return null;
    	}
    	Date createDate = meetingDepartmentCount.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	meetingDepartmentCount.setCreateDate(createDate);
    	meetingDepartmentCount.setModifyDate(createDate);
		return meetingDepartmentCountDao.create(meetingDepartmentCount);
	}

	@Override
	public MeetingDepartmentCount modify(MeetingDepartmentCount meetingDepartmentCount) {
		if(meetingDepartmentCount == null) {
    		return null;
    	}
    	Date modify = meetingDepartmentCount.getModifyDate();
    	meetingDepartmentCount.setModifyDate(modify != null ? modify : new Date());
		return meetingDepartmentCountDao.update(meetingDepartmentCount);
	}
	
	@Override
	public void remove(MeetingDepartmentCount meetingDepartmentCount) {
		try {
			meetingDepartmentCountDao.delete(meetingDepartmentCount);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", meetingDepartmentCount.getId(), e);
			}
		}
	}
	
	@Override
	public List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition, Page page, Order order) {
		return meetingDepartmentCountDao.findMeetingDepartmentCountByCondition(meetingDepartmentCountCondition, page, order);
	}
	
	@Override
	public List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition) {
		return meetingDepartmentCountDao.findMeetingDepartmentCountByCondition(meetingDepartmentCountCondition, null, null);
	}
	
	@Override
	public List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition, Page page) {
		return meetingDepartmentCountDao.findMeetingDepartmentCountByCondition(meetingDepartmentCountCondition, page, null);
	}
	
	@Override
	public List<MeetingDepartmentCount> findMeetingDepartmentCountByCondition(MeetingDepartmentCountCondition meetingDepartmentCountCondition, Order order) {
		return meetingDepartmentCountDao.findMeetingDepartmentCountByCondition(meetingDepartmentCountCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.meetingDepartmentCountDao.count(null);
	}

	@Override
	public Long count(MeetingDepartmentCountCondition meetingDepartmentCountCondition) {
		return this.meetingDepartmentCountDao.count(meetingDepartmentCountCondition);
	}

}
