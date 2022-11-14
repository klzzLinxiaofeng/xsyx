package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.StudentAward;
import platform.education.generalTeachingAffair.vo.StudentAwardCondition;
import platform.education.generalTeachingAffair.service.StudentAwardService;
import platform.education.generalTeachingAffair.dao.StudentAwardDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentAwardServiceImpl implements StudentAwardService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentAwardDao studentAwardDao;

	public void setStudentAwardDao(StudentAwardDao studentAwardDao) {
		this.studentAwardDao = studentAwardDao;
	}
	
	@Override
	public StudentAward findStudentAwardById(Integer id) {
		try {
			return studentAwardDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public StudentAward add(StudentAward studentAward) {
		if(studentAward == null) {
    		return null;
    	}
    	Date createDate = studentAward.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	studentAward.setIsDelete(false);
    	studentAward.setCreateDate(createDate);
    	studentAward.setModifyDate(createDate);
		return studentAwardDao.create(studentAward);
	}

	@Override
	public StudentAward modify(StudentAward studentAward) {
		if(studentAward == null) {
    		return null;
    	}
    	Date modify = studentAward.getModifyDate();
    	studentAward.setModifyDate(modify != null ? modify : new Date());
		return studentAwardDao.update(studentAward);
	}
	
	@Override
	public void remove(StudentAward studentAward) {
		studentAward.setIsDelete(true);
		Date modify = studentAward.getModifyDate();
    	studentAward.setModifyDate(modify != null ? modify : new Date());
		studentAwardDao.delete(studentAward);
	}
	
	@Override
	public List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition, Page page, Order order) {
		//studentAwardCondition.setIsDelete(false);
		return studentAwardDao.findStudentAwardByCondition(studentAwardCondition, page, order);
	}
	
	@Override
	public List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition) {
		return studentAwardDao.findStudentAwardByCondition(studentAwardCondition, null, null);
	}
	
	@Override
	public List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition, Page page) {
		return studentAwardDao.findStudentAwardByCondition(studentAwardCondition, page, null);
	}
	
	@Override
	public List<StudentAward> findStudentAwardByCondition(StudentAwardCondition studentAwardCondition, Order order) {
		return studentAwardDao.findStudentAwardByCondition(studentAwardCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentAwardDao.count(null);
	}

	@Override
	public Long count(StudentAwardCondition studentAwardCondition) {
		return this.studentAwardDao.count(studentAwardCondition);
	}

	@Override
	public void remove(StudentAwardCondition studentAwardCondition) {
		this.studentAwardDao.deleteByCondition(studentAwardCondition);
	}

}
