package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.StudentAidPublish;
import platform.education.generalTeachingAffair.vo.StudentAidPublishCondition;
import platform.education.generalTeachingAffair.service.StudentAidPublishService;
import platform.education.generalTeachingAffair.dao.StudentAidPublishDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentAidPublishServiceImpl implements StudentAidPublishService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentAidPublishDao studentAidPublishDao;

	public void setStudentAidPublishDao(StudentAidPublishDao studentAidPublishDao) {
		this.studentAidPublishDao = studentAidPublishDao;
	}
	
	@Override
	public StudentAidPublish findStudentAidPublishById(Integer id) {
		try {
			return studentAidPublishDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public StudentAidPublish add(StudentAidPublish studentAidPublish) {
		if(studentAidPublish == null) {
    		return null;
    	}
    	Date createDate = studentAidPublish.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	studentAidPublish.setCreateDate(createDate);
    	studentAidPublish.setModifyDate(createDate);
		return studentAidPublishDao.create(studentAidPublish);
	}

	@Override
	public StudentAidPublish modify(StudentAidPublish studentAidPublish) {
		if(studentAidPublish == null) {
    		return null;
    	}
    	Date modify = studentAidPublish.getModifyDate();
    	studentAidPublish.setModifyDate(modify != null ? modify : new Date());
		return studentAidPublishDao.update(studentAidPublish);
	}
	
	@Override
	public void remove(StudentAidPublish studentAidPublish) {
		try {
			studentAidPublishDao.delete(studentAidPublish);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", studentAidPublish.getId(), e);
			}
		}
	}
	
	@Override
	public List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition, Page page, Order order) {
		return studentAidPublishDao.findStudentAidPublishByCondition(studentAidPublishCondition, page, order);
	}
	
	@Override
	public List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition) {
		return studentAidPublishDao.findStudentAidPublishByCondition(studentAidPublishCondition, null, null);
	}
	
	@Override
	public List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition, Page page) {
		return studentAidPublishDao.findStudentAidPublishByCondition(studentAidPublishCondition, page, null);
	}
	
	@Override
	public List<StudentAidPublish> findStudentAidPublishByCondition(StudentAidPublishCondition studentAidPublishCondition, Order order) {
		return studentAidPublishDao.findStudentAidPublishByCondition(studentAidPublishCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentAidPublishDao.count(null);
	}

	@Override
	public Long count(StudentAidPublishCondition studentAidPublishCondition) {
		return this.studentAidPublishDao.count(studentAidPublishCondition);
	}

	@Override
	public StudentAidPublish findBySchoolIdAndSchoolYear(Integer schoolId,
			String schoolYear) {
		return this.studentAidPublishDao.findBySchoolIdAndSchoolYear(schoolId, schoolYear);
	}

	@Override
	public StudentAidPublish makeSurePublish(Integer schoolId,
			String schoolYear, Boolean isPublish) {
		if(isPublish == null){
			isPublish = true;
		}
		StudentAidPublish aidPublish = findBySchoolIdAndSchoolYear(schoolId, schoolYear);
		if(aidPublish == null){
			aidPublish = new StudentAidPublish();
			aidPublish.setSchoolId(schoolId);
			aidPublish.setSchoolYear(schoolYear);
			aidPublish.setIsPublish(isPublish);
			aidPublish.setCount(1);
			aidPublish.setIsDeleted(false);
			aidPublish.setCreateDate(new Date());
			aidPublish.setModifyDate(new Date());
			aidPublish = this.studentAidPublishDao.create(aidPublish);
		}else{
			aidPublish.setIsPublish(isPublish);
			aidPublish.setModifyDate(new Date());
			int count = aidPublish.getCount();
			if(count >= 0){
				count++;
			}else{
				count = 1;
			}
			aidPublish.setCount(count);
			aidPublish = this.studentAidPublishDao.update(aidPublish);
		}
		return aidPublish;
	}

	@Override
	public boolean getHasPublish(Integer schoolId, String schoolYear, Integer count) {
		if(count == null){
			count = 1;
		}
		boolean hasPublish = false;
		StudentAidPublish aidPublish = findBySchoolIdAndSchoolYear(schoolId, schoolYear);
		if(aidPublish != null && count.equals(aidPublish.getCount()) && aidPublish.getIsPublish()){
			hasPublish = true;
		}
		return hasPublish;
	}

}
