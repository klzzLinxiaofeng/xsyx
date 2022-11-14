package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.StudentEmployment;
import platform.education.generalTeachingAffair.vo.StudentEmploymentCondition;
import platform.education.generalTeachingAffair.service.StudentEmploymentService;
import platform.education.generalTeachingAffair.dao.StudentEmploymentDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentEmploymentServiceImpl implements StudentEmploymentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentEmploymentDao studentEmploymentDao;

	public void setStudentEmploymentDao(StudentEmploymentDao studentEmploymentDao) {
		this.studentEmploymentDao = studentEmploymentDao;
	}
	
	@Override
	public StudentEmployment findStudentEmploymentById(Integer id) {
		try {
			return studentEmploymentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public StudentEmployment add(StudentEmployment studentEmployment) {
		if(studentEmployment == null) {
    		return null;
    	}
    	Date createDate = studentEmployment.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	studentEmployment.setCreateDate(createDate);
    	studentEmployment.setModifyDate(createDate);
		return studentEmploymentDao.create(studentEmployment);
	}

	@Override
	public StudentEmployment modify(StudentEmployment studentEmployment) {
		if(studentEmployment == null) {
    		return null;
    	}
    	Date modify = studentEmployment.getModifyDate();
    	studentEmployment.setModifyDate(modify != null ? modify : new Date());
		return studentEmploymentDao.update(studentEmployment);
	}
	
	@Override
	public void remove(StudentEmployment studentEmployment) {
		try {
			studentEmploymentDao.delete(studentEmployment);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", studentEmployment.getId(), e);
			}
		}
	}
	
	@Override
	public List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition, Page page, Order order) {
		return studentEmploymentDao.findStudentEmploymentByCondition(studentEmploymentCondition, page, order);
	}
	
	@Override
	public List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition) {
		return studentEmploymentDao.findStudentEmploymentByCondition(studentEmploymentCondition, null, null);
	}
	
	@Override
	public List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition, Page page) {
		return studentEmploymentDao.findStudentEmploymentByCondition(studentEmploymentCondition, page, null);
	}
	
	@Override
	public List<StudentEmployment> findStudentEmploymentByCondition(StudentEmploymentCondition studentEmploymentCondition, Order order) {
		return studentEmploymentDao.findStudentEmploymentByCondition(studentEmploymentCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentEmploymentDao.count(null);
	}

	@Override
	public Long count(StudentEmploymentCondition studentEmploymentCondition) {
		return this.studentEmploymentDao.count(studentEmploymentCondition);
	}

}
