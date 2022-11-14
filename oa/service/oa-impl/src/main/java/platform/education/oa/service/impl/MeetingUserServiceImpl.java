package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.MeetingUser;
import platform.education.oa.vo.MeetingUserCondition;
import platform.education.oa.service.MeetingUserService;
import platform.education.oa.dao.MeetingUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class MeetingUserServiceImpl implements MeetingUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private MeetingUserDao meetingUserDao;

	public void setMeetingUserDao(MeetingUserDao meetingUserDao) {
		this.meetingUserDao = meetingUserDao;
	}
	
	@Override
	public MeetingUser findMeetingUserById(Integer id) {
		try {
			return meetingUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public MeetingUser add(MeetingUser meetingUser) {
		if(meetingUser == null) {
    		return null;
    	}
    	Date createDate = meetingUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	meetingUser.setCreateDate(createDate);
    	meetingUser.setModifyDate(createDate);
		return meetingUserDao.create(meetingUser);
	}

	@Override
	public MeetingUser modify(MeetingUser meetingUser) {
		if(meetingUser == null) {
    		return null;
    	}
    	Date modify = meetingUser.getModifyDate();
    	meetingUser.setModifyDate(modify != null ? modify : new Date());
		return meetingUserDao.update(meetingUser);
	}
	
	@Override
	public void remove(MeetingUser meetingUser) {
		try {
			meetingUserDao.delete(meetingUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", meetingUser.getId(), e);
			}
		}
	}
	
	@Override
	public List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition, Page page, Order order) {
		return meetingUserDao.findMeetingUserByCondition(meetingUserCondition, page, order);
	}
	
	@Override
	public List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition) {
		return meetingUserDao.findMeetingUserByCondition(meetingUserCondition, null, null);
	}
	
	@Override
	public List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition, Page page) {
		return meetingUserDao.findMeetingUserByCondition(meetingUserCondition, page, null);
	}
	
	@Override
	public List<MeetingUser> findMeetingUserByCondition(MeetingUserCondition meetingUserCondition, Order order) {
		return meetingUserDao.findMeetingUserByCondition(meetingUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.meetingUserDao.count(null);
	}

	@Override
	public Long count(MeetingUserCondition meetingUserCondition) {
		return this.meetingUserDao.count(meetingUserCondition);
	}

	@Override
	public MeetingUser findByUserId(Integer meetingId, Integer userId) {
		 
		return this.meetingUserDao.findByUserId(meetingId, userId);
	}

}
