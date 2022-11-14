package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.StudentPunish;
import platform.education.generalTeachingAffair.vo.StudentPunishCondition;
import platform.education.generalTeachingAffair.service.StudentPunishService;
import platform.education.generalTeachingAffair.dao.StudentPunishDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentPunishServiceImpl implements StudentPunishService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentPunishDao studentPunishDao;

	public void setStudentPunishDao(StudentPunishDao studentPunishDao) {
		this.studentPunishDao = studentPunishDao;
	}
	
	@Override
	public StudentPunish findStudentPunishById(Integer id) {
		try {
			return studentPunishDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public StudentPunish add(StudentPunish studentPunish) {
		if(studentPunish == null) {
    		return null;
    	}
    	Date createDate = studentPunish.getCreateDate();  
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	studentPunish.setIsDelete(false);
    	studentPunish.setCreateDate(createDate);
    	studentPunish.setModifyDate(createDate);
		return studentPunishDao.create(studentPunish);
	}

	@Override
	public StudentPunish modify(StudentPunish studentPunish) {
		if(studentPunish == null) {
    		return null;
    	}
    	Date modify = studentPunish.getModifyDate();
    	studentPunish.setModifyDate(modify != null ? modify : new Date());
		return studentPunishDao.update(studentPunish);
	}
	
	@Override
	public void remove(StudentPunish studentPunish) {
		
		if(studentPunish.getModifyDate() == null){
			studentPunish.setModifyDate(new Date());
		}
		  studentPunishDao.delete(studentPunish);
	}
	
	@Override
	public List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition, Page page, Order order) {
		//studentPunishCondition.setIsDelete(false);
		return studentPunishDao.findStudentPunishByCondition(studentPunishCondition, page, order);
	}
	
	@Override
	public List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition) {
		return studentPunishDao.findStudentPunishByCondition(studentPunishCondition, null, null);
	}
	
	@Override
	public List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition, Page page) {
		return studentPunishDao.findStudentPunishByCondition(studentPunishCondition, page, null);
	}
	
	@Override
	public List<StudentPunish> findStudentPunishByCondition(StudentPunishCondition studentPunishCondition, Order order) {
		return studentPunishDao.findStudentPunishByCondition(studentPunishCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentPunishDao.count(null);
	}

	@Override
	public Long count(StudentPunishCondition studentPunishCondition) {
		return this.studentPunishDao.count(studentPunishCondition);
	}

}
