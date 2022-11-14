package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.oa.model.Meeting;
import platform.education.oa.vo.MeetingCondition;
import platform.education.oa.vo.MeetingVo;
import platform.education.oa.service.MeetingService;
import platform.education.oa.dao.MeetingDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class MeetingServiceImpl implements MeetingService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private MeetingDao meetingDao;
	
    

	public void setMeetingDao(MeetingDao meetingDao) {
		this.meetingDao = meetingDao;
	}
	
	
	
	@Override
	public Meeting findMeetingById(Integer id) {
		 
		try {
			return meetingDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Meeting add(Meeting meeting) {
		if(meeting == null) {
    		return null;
    	}
    	Date createDate = meeting.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	meeting.setCreateDate(createDate);
    	meeting.setModifyDate(createDate);
		return meetingDao.create(meeting);
	}

	@Override
	public Meeting modify(Meeting meeting) {
		if(meeting == null) {
    		return null;
    	}
    	Date modify = meeting.getModifyDate();
    	meeting.setModifyDate(modify != null ? modify : new Date());
		return meetingDao.update(meeting);
	}
	
	@Override
	public void remove(Meeting meeting) {
		try {
			meetingDao.delete(meeting);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", meeting.getId(), e);
			}
		}
	}
	
	@Override
	public List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition, Page page, Order order) {
		return meetingDao.findMeetingByCondition(meetingCondition, page, order);
	}
	
	@Override
	public List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition) {
		return meetingDao.findMeetingByCondition(meetingCondition, null, null);
	}
	
	@Override
	public List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition, Page page) {
		return meetingDao.findMeetingByCondition(meetingCondition, page, null);
	}
	
	@Override
	public List<Meeting> findMeetingByCondition(MeetingCondition meetingCondition, Order order) {
		return meetingDao.findMeetingByCondition(meetingCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.meetingDao.count(null);
	}

	@Override
	public Long count(MeetingCondition meetingCondition) {
		return this.meetingDao.count(meetingCondition);
	}

	@Override
	public List<Meeting> findMeetingToUser(Integer schoolId, Integer userId,
			Page page, Order order) {
		 
		return this.meetingDao.findMeetingToUser(schoolId, userId,null,null, page, order);
	}

	public List<Meeting>  findMeetingToUser(Integer schoolId,
			Integer userId,Integer new_or_old,String baseline_date, Page page, Order order){
		return this.meetingDao.findMeetingToUser(schoolId, userId, new_or_old, baseline_date, page, order);
	}
	
	public List<Meeting>  findMeetingToSummary(Integer schoolId,
			String endtime, Page page, Order order){
		return this.meetingDao.findMeetingToSummary(schoolId, endtime, page, order);
	}



	@Override
	public List<Meeting> findMeeting(Integer schoolId, Integer userId,
			Integer fanwei, Integer department_id, Integer new_or_old,
			String baseline_date, Page page, Order order) {
		 
		return this.meetingDao.findMeeting(schoolId, userId, fanwei, department_id, new_or_old, baseline_date, page, order);
	}



	@Override
	public List<MeetingVo> findMeetingByRelatedWithMe(MeetingCondition condition,
			Page page, Order order) {
		// TODO Auto-generated method stub
		return this.meetingDao.findMeetingByRelatedWithMe(condition, page, order);
	}
}
