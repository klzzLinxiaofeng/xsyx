package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.StudentAidDao;
import platform.education.generalTeachingAffair.model.StudentAid;
import platform.education.generalTeachingAffair.service.StudentAidService;
import platform.education.generalTeachingAffair.vo.StudentAidCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentAidServiceImpl implements StudentAidService{

	private Logger log = LoggerFactory.getLogger(getClass());

	private StudentAidDao studentAidDao;



	public StudentAidDao getstudentAidDao() {
		return studentAidDao;
	}

	public void setstudentAidDao(StudentAidDao studentAidDao) {
		this.studentAidDao = studentAidDao;
	}

	@Override
	public StudentAid findStudentAidById(Integer id) {
		try {
			return studentAidDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public StudentAid add(StudentAid StudentAid) {
		if(StudentAid == null) {
			return null;
		}
		Date createDate = StudentAid.getCreateDate();
		if(createDate == null) {
			createDate = new Date();
		}
		StudentAid.setIsDelete(false);
		StudentAid.setCreateDate(createDate);
		StudentAid.setModifyDate(createDate);
		return studentAidDao.create(StudentAid);
	}

	@Override
	public StudentAid modify(StudentAid StudentAid) {
		if(StudentAid == null) {
			return null;
		}
		Date modify = StudentAid.getModifyDate();
		StudentAid.setModifyDate(modify != null ? modify : new Date());
		return studentAidDao.update(StudentAid);
	}

	@Override
	public void remove(StudentAid studentAid) {
		studentAid.setIsDelete(true);
		studentAid.setModifyDate(new Date());
		studentAidDao.delete(studentAid);
	}

	@Override
	public List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition, Page page, Order order) {
		return studentAidDao.findStudentAidByCondition(StudentAidCondition, page, order);


	}

	@Override
	public List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition) {
		return studentAidDao.findStudentAidByCondition(StudentAidCondition, null, null);
	}

	@Override
	public List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition, Page page) {
		return studentAidDao.findStudentAidByCondition(StudentAidCondition, page, null);
	}

	@Override
	public List<StudentAid> findStudentAidByCondition(StudentAidCondition StudentAidCondition, Order order) {
		return studentAidDao.findStudentAidByCondition(StudentAidCondition, null, order);
	}

	@Override
	public Long count() {
		return this.studentAidDao.count(null);
	}

	@Override
	public Long count(StudentAidCondition StudentAidCondition) {
		return this.studentAidDao.count(StudentAidCondition);
	}

}
