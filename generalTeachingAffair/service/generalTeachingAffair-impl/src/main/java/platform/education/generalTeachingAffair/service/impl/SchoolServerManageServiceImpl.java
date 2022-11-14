package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.SchoolServerManage;
import platform.education.generalTeachingAffair.vo.SchoolServerManageCondition;
import platform.education.generalTeachingAffair.service.SchoolServerManageService;
import platform.education.generalTeachingAffair.dao.SchoolServerManageDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolServerManageServiceImpl implements SchoolServerManageService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SchoolServerManageDao schoolServerManageDao;

	public void setSchoolServerManageDao(SchoolServerManageDao schoolServerManageDao) {
		this.schoolServerManageDao = schoolServerManageDao;
	}
	
	@Override
	public SchoolServerManage findSchoolServerManageById(Integer id) {
		try {
			return schoolServerManageDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SchoolServerManage add(SchoolServerManage schoolServerManage) {
		if(schoolServerManage == null) {
    		return null;
    	}
    	Date createDate = schoolServerManage.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	schoolServerManage.setCreateDate(createDate);
    	schoolServerManage.setModifyDate(createDate);
		return schoolServerManageDao.create(schoolServerManage);
	}

	@Override
	public SchoolServerManage modify(SchoolServerManage schoolServerManage) {
		if(schoolServerManage == null) {
    		return null;
    	}
    	Date modify = schoolServerManage.getModifyDate();
    	schoolServerManage.setModifyDate(modify != null ? modify : new Date());
		return schoolServerManageDao.update(schoolServerManage);
	}
	
	@Override
	public void remove(SchoolServerManage schoolServerManage) {
		try {
			schoolServerManageDao.delete(schoolServerManage);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", schoolServerManage.getId(), e);
			}
		}
	}
	
	@Override
	public List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition, Page page, Order order) {
		return schoolServerManageDao.findSchoolServerManageByCondition(schoolServerManageCondition, page, order);
	}
	
	@Override
	public List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition) {
		return schoolServerManageDao.findSchoolServerManageByCondition(schoolServerManageCondition, null, null);
	}
	
	@Override
	public List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition, Page page) {
		return schoolServerManageDao.findSchoolServerManageByCondition(schoolServerManageCondition, page, null);
	}
	
	@Override
	public List<SchoolServerManage> findSchoolServerManageByCondition(SchoolServerManageCondition schoolServerManageCondition, Order order) {
		return schoolServerManageDao.findSchoolServerManageByCondition(schoolServerManageCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.schoolServerManageDao.count(null);
	}

	@Override
	public Long count(SchoolServerManageCondition schoolServerManageCondition) {
		return this.schoolServerManageDao.count(schoolServerManageCondition);
	}

	@Override
	public SchoolServerManage findSchoolServerManageBySchoolId(Integer schoolId) {
		return this.schoolServerManageDao.findBySchoolId(schoolId);
	}

	@Override
	public List<SchoolServerManage> findSchoolServerManageBySchoolName(
			String schoolName) {
		return this.schoolServerManageDao.findBySchoolName(schoolName);
	}

}
