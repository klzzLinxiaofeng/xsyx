package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.StudentAlteration;
import platform.education.generalTeachingAffair.vo.StudentAlterationCondition;
import platform.education.generalTeachingAffair.service.StudentAlterationService;
import platform.education.generalTeachingAffair.dao.StudentAlterationDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentAlterationServiceImpl implements StudentAlterationService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentAlterationDao studentAlterationDao;

	public void setStudentAlterationDao(StudentAlterationDao studentAlterationDao) {
		this.studentAlterationDao = studentAlterationDao;
	}
	
	@Override
	public StudentAlteration findStudentAlterationById(Integer id) {
		try {
			return studentAlterationDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public StudentAlteration add(StudentAlteration studentAlteration) {
		if(studentAlteration == null) {
    		return null;
    	}
    	Date createDate = studentAlteration.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	studentAlteration.setCreateDate(createDate);
    	studentAlteration.setModifyDate(createDate);
		return studentAlterationDao.create(studentAlteration);
	}

	@Override
	public StudentAlteration modify(StudentAlteration studentAlteration) {
		if(studentAlteration == null) {
    		return null;
    	}
    	Date modify = studentAlteration.getModifyDate();
    	studentAlteration.setModifyDate(modify != null ? modify : new Date());
		return studentAlterationDao.update(studentAlteration);
	}
	
	@Override
	public void remove(StudentAlteration studentAlteration) {
		try {
			studentAlterationDao.delete(studentAlteration);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", studentAlteration.getId(), e);
			}
		}
	}
	
	@Override
	public List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition, Page page, Order order) {
		return studentAlterationDao.findStudentAlterationByCondition(studentAlterationCondition, page, order);
	}
	
	@Override
	public List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition) {
		return studentAlterationDao.findStudentAlterationByCondition(studentAlterationCondition, null, null);
	}
	
	@Override
	public List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition, Page page) {
		return studentAlterationDao.findStudentAlterationByCondition(studentAlterationCondition, page, null);
	}
	
	@Override
	public List<StudentAlteration> findStudentAlterationByCondition(StudentAlterationCondition studentAlterationCondition, Order order) {
		return studentAlterationDao.findStudentAlterationByCondition(studentAlterationCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentAlterationDao.count(null);
	}

	@Override
	public Long count(StudentAlterationCondition studentAlterationCondition) {
		return this.studentAlterationDao.count(studentAlterationCondition);
	}

	
	@Override
	public StudentAlteration findStudentRecord(Integer teamId,
			Integer studentId, String alterType) {
		return this.studentAlterationDao.findStudentRecord(teamId, studentId, alterType);
	}

}
