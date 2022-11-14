package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.SchoolCalendar;
import platform.education.generalTeachingAffair.vo.SchoolCalendarCondition;
import platform.education.generalTeachingAffair.service.SchoolCalendarService;
import platform.education.generalTeachingAffair.dao.SchoolCalendarDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolCalendarServiceImpl implements SchoolCalendarService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SchoolCalendarDao schoolCalendarDao;

	public void setSchoolCalendarDao(SchoolCalendarDao schoolCalendarDao) {
		this.schoolCalendarDao = schoolCalendarDao;
	}
	
	@Override
	public SchoolCalendar findSchoolCalendarById(Integer id) {
		try {
			return schoolCalendarDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SchoolCalendar add(SchoolCalendar schoolCalendar) {
		if(schoolCalendar == null) {
    		return null;
    	}
    	Date createDate = schoolCalendar.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	schoolCalendar.setCreateDate(createDate);
    	schoolCalendar.setModifyDate(createDate);
		return schoolCalendarDao.create(schoolCalendar);
	}

	@Override
	public SchoolCalendar modify(SchoolCalendar schoolCalendar) {
		if(schoolCalendar == null) {
    		return null;
    	}
    	Date modify = schoolCalendar.getModifyDate();
    	schoolCalendar.setModifyDate(modify != null ? modify : new Date());
		return schoolCalendarDao.update(schoolCalendar);
	}
	
	@Override
	public void remove(SchoolCalendar schoolCalendar) {
		 schoolCalendarDao.delete(schoolCalendar);
	}
	
	@Override
	public List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition, Page page, Order order) {
		return schoolCalendarDao.findSchoolCalendarByCondition(schoolCalendarCondition, page, order);
	}
	
	@Override
	public List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition) {
		return schoolCalendarDao.findSchoolCalendarByCondition(schoolCalendarCondition, null, null);
	}
	
	@Override
	public List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition, Page page) {
		return schoolCalendarDao.findSchoolCalendarByCondition(schoolCalendarCondition, page, null);
	}
	
	@Override
	public List<SchoolCalendar> findSchoolCalendarByCondition(SchoolCalendarCondition schoolCalendarCondition, Order order) {
		return schoolCalendarDao.findSchoolCalendarByCondition(schoolCalendarCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.schoolCalendarDao.count(null);
	}

	@Override
	public Long count(SchoolCalendarCondition schoolCalendarCondition) {
		return this.schoolCalendarDao.count(schoolCalendarCondition);
	}

}
