package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.StudentHealthArchive;
import platform.education.generalTeachingAffair.vo.StudentHealthArchiveCondition;
import platform.education.generalTeachingAffair.service.StudentHealthArchiveService;
import platform.education.generalTeachingAffair.dao.StudentHealthArchiveDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentHealthArchiveServiceImpl implements StudentHealthArchiveService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentHealthArchiveDao studentHealthArchiveDao;






	public void setStudentHealthArchiveDao(StudentHealthArchiveDao studentHealthArchiveDao) {
		this.studentHealthArchiveDao = studentHealthArchiveDao;
	}
	
	@Override
	public StudentHealthArchive findStudentHealthArchiveById(Integer id) {
		try {



			return studentHealthArchiveDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public List<StudentHealthArchive> findStudentHealthArchive() {
		return studentHealthArchiveDao.findStudentHealthArchive();
	}

	@Override
	public StudentHealthArchive add(StudentHealthArchive studentHealthArchive) {
		if(studentHealthArchive == null) {
    		return null;
    	}
    	Date createDate = studentHealthArchive.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	studentHealthArchive.setIsDelete(0);
    	studentHealthArchive.setCreateDate(createDate);
    	studentHealthArchive.setModifyDate(createDate);
		return studentHealthArchiveDao.create(studentHealthArchive);
	}

	@Override
	public StudentHealthArchive modify(StudentHealthArchive studentHealthArchive) {
		if(studentHealthArchive == null) {
    		return null;
    	}
    	Date modify = studentHealthArchive.getModifyDate();
    	studentHealthArchive.setModifyDate(modify != null ? modify : new Date());
		return studentHealthArchiveDao.update(studentHealthArchive);
	}
	
	@Override
	public void remove(StudentHealthArchive studentHealthArchive) {
		 studentHealthArchiveDao.delete(studentHealthArchive);
	}
	
	@Override
	public List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition, Page page, Order order) {
		return studentHealthArchiveDao.findStudentHealthArchiveByCondition(studentHealthArchiveCondition, page, order);
	}
	
	@Override
	public List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition) {
		return studentHealthArchiveDao.findStudentHealthArchiveByCondition(studentHealthArchiveCondition, null, null);
	}
	
	@Override
	public List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition, Page page) {
		return studentHealthArchiveDao.findStudentHealthArchiveByCondition(studentHealthArchiveCondition, page, null);
	}
	
	@Override
	public List<StudentHealthArchive> findStudentHealthArchiveByCondition(StudentHealthArchiveCondition studentHealthArchiveCondition, Order order) {
		return studentHealthArchiveDao.findStudentHealthArchiveByCondition(studentHealthArchiveCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentHealthArchiveDao.count(null);
	}

	@Override
	public Long count(StudentHealthArchiveCondition studentHealthArchiveCondition) {
		return this.studentHealthArchiveDao.count(studentHealthArchiveCondition);
	}

	@Override
	public void remove(StudentHealthArchiveCondition studentHealthArchiveCondition) {
		this.studentHealthArchiveDao.deleteByCondition(studentHealthArchiveCondition);
	}

	/**
	 * 功能描述：通过pj_team_student.id查询该学生的相关健康信息
	 */
	@Override
	public StudentHealthArchive findUnique(Integer teamId, Integer studentId) {
		try {
			return studentHealthArchiveDao.findUnique(teamId, studentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
