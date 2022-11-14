package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.SchoolSystem;
import platform.education.generalTeachingAffair.vo.SchoolSystemCondition;
import platform.education.generalTeachingAffair.service.SchoolSystemService;
import platform.education.generalTeachingAffair.dao.SchoolSystemDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class SchoolSystemServiceImpl implements SchoolSystemService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private SchoolSystemDao schoolSystemDao;

	public void setSchoolSystemDao(SchoolSystemDao schoolSystemDao) {
		this.schoolSystemDao = schoolSystemDao;
	}
	
	@Override
	public SchoolSystem findSchoolSystemById(Integer id) {
		try {
			return schoolSystemDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public SchoolSystem add(SchoolSystem schoolSystem) {
		if(schoolSystem == null) {
    		return null;
    	}
		
		Integer schoolId = schoolSystem.getSchoolId();
		String stageCode = schoolSystem.getStageCode();
		String gradeCode = schoolSystem.getGradeCode();
		
		if(schoolId != null && stageCode != null && gradeCode != null) {
			SchoolSystem perSchoolSystem = this.schoolSystemDao.findUnique(schoolId, stageCode, gradeCode);
			if(perSchoolSystem != null) {
				return perSchoolSystem;
			}
			Date createDate = schoolSystem.getCreateDate();
	    	if(createDate == null) {
	    		createDate = new Date();
	    	}
	    	schoolSystem.setCreateDate(createDate);
	    	schoolSystem.setModifyDate(createDate);
			return schoolSystemDao.create(schoolSystem);
			
		}
		return null;
	}

	@Override
	public SchoolSystem modify(SchoolSystem schoolSystem) {
		if(schoolSystem == null) {
    		return null;
    	}
    	Date modify = schoolSystem.getModifyDate();
    	schoolSystem.setModifyDate(modify != null ? modify : new Date());
		return schoolSystemDao.update(schoolSystem);
	}
	
	@Override
	public void remove(SchoolSystem schoolSystem) {
		 schoolSystemDao.delete(schoolSystem);
	}
	
	@Override
	public List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition, Page page, Order order) {
		return schoolSystemDao.findSchoolSystemByCondition(schoolSystemCondition, page, order);
	}
	
	@Override
	public List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition) {
		return schoolSystemDao.findSchoolSystemByCondition(schoolSystemCondition, null, null);
	}
	
	@Override
	public List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition, Page page) {
		return schoolSystemDao.findSchoolSystemByCondition(schoolSystemCondition, page, null);
	}
	
	@Override
	public List<SchoolSystem> findSchoolSystemByCondition(SchoolSystemCondition schoolSystemCondition, Order order) {
		return schoolSystemDao.findSchoolSystemByCondition(schoolSystemCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.schoolSystemDao.count(null);
	}

	@Override
	public Long count(SchoolSystemCondition schoolSystemCondition) {
		return this.schoolSystemDao.count(schoolSystemCondition);
	}

	@Override
	public SchoolSystem findUnique(Integer schoolId, String stageCode,
			String gradeCode) {
		return this.schoolSystemDao.findUnique(schoolId, stageCode, gradeCode);
	}

	/**
	 * 功能描述：获得学制表本学校的年级
	 */
	@Override
	public List<SchoolSystem> findDefaultGradesOfSchool(Integer schoolId) {
		return this.schoolSystemDao.findDefaultGradesOfSchool(schoolId);
	}

}
