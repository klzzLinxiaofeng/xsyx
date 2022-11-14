package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.SchoolCalendarDay;
import platform.education.generalTeachingAffair.vo.SchoolCalendarDayCondition;
import platform.education.generalTeachingAffair.service.SchoolCalendarDayService;
import platform.education.generalTeachingAffair.dao.SchoolCalendarDayDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolCalendarDayServiceImpl implements SchoolCalendarDayService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SchoolCalendarDayDao schoolCalendarDayDao;

	public void setSchoolCalendarDayDao(SchoolCalendarDayDao schoolCalendarDayDao) {
		this.schoolCalendarDayDao = schoolCalendarDayDao;
	}
	
	@Override
	public SchoolCalendarDay findSchoolCalendarDayById(Integer id) {
		try {
			return schoolCalendarDayDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SchoolCalendarDay add(SchoolCalendarDay schoolCalendarDay) {
		if(schoolCalendarDay == null) {
    		return null;
    	}
    	Date createDate = schoolCalendarDay.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	schoolCalendarDay.setCreateDate(createDate);
    	schoolCalendarDay.setModifyDate(createDate);
		return schoolCalendarDayDao.create(schoolCalendarDay);
	}

	@Override
	public SchoolCalendarDay modify(SchoolCalendarDay schoolCalendarDay) {
		if(schoolCalendarDay == null) {
    		return null;
    	}
    	Date modify = schoolCalendarDay.getModifyDate();
    	schoolCalendarDay.setModifyDate(modify != null ? modify : new Date());
		return schoolCalendarDayDao.update(schoolCalendarDay);
	}
	
	@Override
	public void remove(SchoolCalendarDay schoolCalendarDay) {
		 schoolCalendarDayDao.delete(schoolCalendarDay);
	}
	
	@Override
	public List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition, Page page, Order order) {
		return schoolCalendarDayDao.findSchoolCalendarDayByCondition(schoolCalendarDayCondition, page, order);
	}
	
	@Override
	public List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition) {
		return schoolCalendarDayDao.findSchoolCalendarDayByCondition(schoolCalendarDayCondition, null, null);
	}
	
	@Override
	public List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition, Page page) {
		return schoolCalendarDayDao.findSchoolCalendarDayByCondition(schoolCalendarDayCondition, page, null);
	}
	
	@Override
	public List<SchoolCalendarDay> findSchoolCalendarDayByCondition(SchoolCalendarDayCondition schoolCalendarDayCondition, Order order) {
		return schoolCalendarDayDao.findSchoolCalendarDayByCondition(schoolCalendarDayCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.schoolCalendarDayDao.count(null);
	}

	@Override
	public Long count(SchoolCalendarDayCondition schoolCalendarDayCondition) {
		return this.schoolCalendarDayDao.count(schoolCalendarDayCondition);
	}

}
