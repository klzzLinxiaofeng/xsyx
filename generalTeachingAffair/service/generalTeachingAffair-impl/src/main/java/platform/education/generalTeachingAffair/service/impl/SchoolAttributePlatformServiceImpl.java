package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.SchoolAttributePlatform;
import platform.education.generalTeachingAffair.vo.SchoolAttributePlatformCondition;
import platform.education.generalTeachingAffair.service.SchoolAttributePlatformService;
import platform.education.generalTeachingAffair.dao.SchoolAttributePlatformDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolAttributePlatformServiceImpl implements SchoolAttributePlatformService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SchoolAttributePlatformDao schoolAttributePlatformDao;

	public void setSchoolAttributePlatformDao(SchoolAttributePlatformDao schoolAttributePlatformDao) {
		this.schoolAttributePlatformDao = schoolAttributePlatformDao;
	}
	
	@Override
	public SchoolAttributePlatform findSchoolAttributePlatformById(Integer id) {
		try {
			return schoolAttributePlatformDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SchoolAttributePlatform add(SchoolAttributePlatform schoolAttributePlatform) {
		if(schoolAttributePlatform == null) {
    		return null;
    	}
    	Date createDate = schoolAttributePlatform.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	schoolAttributePlatform.setCreateDate(createDate);
    	schoolAttributePlatform.setModifyDate(createDate);
		return schoolAttributePlatformDao.create(schoolAttributePlatform);
	}

	@Override
	public SchoolAttributePlatform modify(SchoolAttributePlatform schoolAttributePlatform) {
		if(schoolAttributePlatform == null) {
    		return null;
    	}
    	Date modify = schoolAttributePlatform.getModifyDate();
    	schoolAttributePlatform.setModifyDate(modify != null ? modify : new Date());
		return schoolAttributePlatformDao.update(schoolAttributePlatform);
	}
	
	@Override
	public void remove(SchoolAttributePlatform schoolAttributePlatform) {
		try {
			schoolAttributePlatformDao.delete(schoolAttributePlatform);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", schoolAttributePlatform.getId(), e);
			}
		}
	}
	
	@Override
	public List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition, Page page, Order order) {
		return schoolAttributePlatformDao.findSchoolAttributePlatformByCondition(schoolAttributePlatformCondition, page, order);
	}
	
	@Override
	public List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition) {
		return schoolAttributePlatformDao.findSchoolAttributePlatformByCondition(schoolAttributePlatformCondition, null, null);
	}
	
	@Override
	public List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition, Page page) {
		return schoolAttributePlatformDao.findSchoolAttributePlatformByCondition(schoolAttributePlatformCondition, page, null);
	}
	
	@Override
	public List<SchoolAttributePlatform> findSchoolAttributePlatformByCondition(SchoolAttributePlatformCondition schoolAttributePlatformCondition, Order order) {
		return schoolAttributePlatformDao.findSchoolAttributePlatformByCondition(schoolAttributePlatformCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.schoolAttributePlatformDao.count(null);
	}

	@Override
	public Long count(SchoolAttributePlatformCondition schoolAttributePlatformCondition) {
		return this.schoolAttributePlatformDao.count(schoolAttributePlatformCondition);
	}

}
