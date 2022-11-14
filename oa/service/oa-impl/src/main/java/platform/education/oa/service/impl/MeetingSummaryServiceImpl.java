package platform.education.oa.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.oa.model.MeetingSummary;
import platform.education.oa.vo.MeetingSummaryCondition;
import platform.education.oa.service.MeetingSummaryService;
import platform.education.oa.dao.MeetingSummaryDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class MeetingSummaryServiceImpl implements MeetingSummaryService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private MeetingSummaryDao meetingSummaryDao;

	public void setMeetingSummaryDao(MeetingSummaryDao meetingSummaryDao) {
		this.meetingSummaryDao = meetingSummaryDao;
	}
	
	@Override
	public MeetingSummary findMeetingSummaryById(Integer id) {
		try {
			return meetingSummaryDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public MeetingSummary add(MeetingSummary meetingSummary) {
		if(meetingSummary == null) {
    		return null;
    	}
    	Date createDate = meetingSummary.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	meetingSummary.setCreateDate(createDate);
    	meetingSummary.setModifyDate(createDate);
		return meetingSummaryDao.create(meetingSummary);
	}

	@Override
	public MeetingSummary modify(MeetingSummary meetingSummary) {
		if(meetingSummary == null) {
    		return null;
    	}
    	Date modify = meetingSummary.getModifyDate();
    	meetingSummary.setModifyDate(modify != null ? modify : new Date());
		return meetingSummaryDao.update(meetingSummary);
	}
	
	@Override
	public void remove(MeetingSummary meetingSummary) {
		try {
			meetingSummaryDao.delete(meetingSummary);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", meetingSummary.getId(), e);
			}
		}
	}
	
	@Override
	public List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition, Page page, Order order) {
		return meetingSummaryDao.findMeetingSummaryByCondition(meetingSummaryCondition, page, order);
	}
	
	@Override
	public List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition) {
		return meetingSummaryDao.findMeetingSummaryByCondition(meetingSummaryCondition, null, null);
	}
	
	@Override
	public List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition, Page page) {
		return meetingSummaryDao.findMeetingSummaryByCondition(meetingSummaryCondition, page, null);
	}
	
	@Override
	public List<MeetingSummary> findMeetingSummaryByCondition(MeetingSummaryCondition meetingSummaryCondition, Order order) {
		return meetingSummaryDao.findMeetingSummaryByCondition(meetingSummaryCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.meetingSummaryDao.count(null);
	}

	@Override
	public Long count(MeetingSummaryCondition meetingSummaryCondition) {
		return this.meetingSummaryDao.count(meetingSummaryCondition);
	}

}
