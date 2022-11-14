package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.StudentMilitary;
import platform.education.generalTeachingAffair.vo.StudentMilitaryCondition;
import platform.education.generalTeachingAffair.service.StudentMilitaryService;
import platform.education.generalTeachingAffair.dao.StudentMilitaryDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentMilitaryServiceImpl implements StudentMilitaryService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentMilitaryDao studentMilitaryDao;

	public void setStudentMilitaryDao(StudentMilitaryDao studentMilitaryDao) {
		this.studentMilitaryDao = studentMilitaryDao;
	}
	
	@Override
	public StudentMilitary findStudentMilitaryById(Integer id) {
		try {
			return studentMilitaryDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public StudentMilitary add(StudentMilitary studentMilitary) {
		if(studentMilitary == null) {
    		return null;
    	}
    	Date createDate = studentMilitary.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	studentMilitary.setCreateDate(createDate);
    	studentMilitary.setModifyDate(createDate);
		return studentMilitaryDao.create(studentMilitary);
	}

	@Override
	public StudentMilitary modify(StudentMilitary studentMilitary) {
		if(studentMilitary == null) {
    		return null;
    	}
    	Date modify = studentMilitary.getModifyDate();
    	studentMilitary.setModifyDate(modify != null ? modify : new Date());
		return studentMilitaryDao.update(studentMilitary);
	}
	
	@Override
	public void remove(StudentMilitary studentMilitary) {
		try {
			studentMilitaryDao.delete(studentMilitary);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", studentMilitary.getId(), e);
			}
		}
	}
	
	@Override
	public List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition, Page page, Order order) {
		return studentMilitaryDao.findStudentMilitaryByCondition(studentMilitaryCondition, page, order);
	}
	
	@Override
	public List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition) {
		return studentMilitaryDao.findStudentMilitaryByCondition(studentMilitaryCondition, null, null);
	}
	
	@Override
	public List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition, Page page) {
		return studentMilitaryDao.findStudentMilitaryByCondition(studentMilitaryCondition, page, null);
	}
	
	@Override
	public List<StudentMilitary> findStudentMilitaryByCondition(StudentMilitaryCondition studentMilitaryCondition, Order order) {
		return studentMilitaryDao.findStudentMilitaryByCondition(studentMilitaryCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentMilitaryDao.count(null);
	}

	@Override
	public Long count(StudentMilitaryCondition studentMilitaryCondition) {
		return this.studentMilitaryDao.count(studentMilitaryCondition);
	}

}
