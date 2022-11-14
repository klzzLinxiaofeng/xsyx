package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.StudentCheckAttendance;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceCondition;
import platform.education.generalTeachingAffair.vo.StudentCheckAttendanceVo;
import platform.education.generalTeachingAffair.service.StudentCheckAttendanceService;
import platform.education.generalTeachingAffair.dao.StudentCheckAttendanceDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class StudentCheckAttendanceServiceImpl implements StudentCheckAttendanceService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentCheckAttendanceDao studentCheckAttendanceDao;

	public void setStudentCheckAttendanceDao(StudentCheckAttendanceDao studentCheckAttendanceDao) {
		this.studentCheckAttendanceDao = studentCheckAttendanceDao;
	}
	
	@Override
	public StudentCheckAttendance findStudentCheckAttendanceById(Integer id) {
		try {
			return studentCheckAttendanceDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	
	
	@Override
	public StudentCheckAttendance add(StudentCheckAttendance studentCheckAttendance) {
	if(studentCheckAttendance == null) {
    		return null;
    	}
		Date beginDate = studentCheckAttendance.getBeginDate();
    	Date endDate = studentCheckAttendance.getEndDate();
    	
    	if(beginDate == null) {
    		beginDate = new Date();
    	}
    	
    	studentCheckAttendance.setEndDate(beginDate);
    	studentCheckAttendance.setModifyDate(beginDate);
    	studentCheckAttendance.setEndDate(endDate);
    	studentCheckAttendance.setModifyDate(endDate);
		return studentCheckAttendanceDao.create(studentCheckAttendance);
	}

	@Override
	public StudentCheckAttendance modify(StudentCheckAttendance studentCheckAttendance) {
		if(studentCheckAttendance == null) {
    		return null;
    	}
    	Date modify = studentCheckAttendance.getModifyDate();
    	studentCheckAttendance.setModifyDate(modify != null ? modify : new Date());
		return studentCheckAttendanceDao.update(studentCheckAttendance);
	}
	
	@Override
	public void remove(StudentCheckAttendance studentCheckAttendance) {
		 studentCheckAttendanceDao.delete(studentCheckAttendance);
	}
	
	@Override
	public List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition, Page page, Order order) {
		return studentCheckAttendanceDao.findStudentCheckAttendanceByCondition(studentCheckAttendanceCondition, page, order);
	}
	
	@Override
	public List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition) {
		return studentCheckAttendanceDao.findStudentCheckAttendanceByCondition(studentCheckAttendanceCondition, null, null);
	}
	
	@Override
	public List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition, Page page) {
		return studentCheckAttendanceDao.findStudentCheckAttendanceByCondition(studentCheckAttendanceCondition, page, null);
	}
	
	@Override
	public List<StudentCheckAttendance> findStudentCheckAttendanceByCondition(StudentCheckAttendanceCondition studentCheckAttendanceCondition, Order order) {
		return studentCheckAttendanceDao.findStudentCheckAttendanceByCondition(studentCheckAttendanceCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentCheckAttendanceDao.count(null);
	}

	@Override
	public Long count(StudentCheckAttendanceCondition studentCheckAttendanceCondition) {
		return this.studentCheckAttendanceDao.count(studentCheckAttendanceCondition);
	}

	@Override
	public List<StudentCheckAttendanceVo> findGroupVoByStudentId(
			StudentCheckAttendanceCondition studentCheckAttendanceCondition,
			Page page,Order order) {
		return studentCheckAttendanceDao.findGroupVoByStudentId(studentCheckAttendanceCondition, page, order);
	}

	@Override
	public List<StudentCheckAttendance> findUnique(Integer schoolId,
			Integer studentId, String attendanceType) {
		return studentCheckAttendanceDao.findUnique(schoolId, studentId, attendanceType);
	}

	@Override
	public void abandon(StudentCheckAttendance studentCheckAttendance) {
		if(studentCheckAttendance!=null){
			studentCheckAttendance.setIsDeleted(1);
			try {
				studentCheckAttendance = this.studentCheckAttendanceDao.update(studentCheckAttendance);
			} catch (Exception e) {
				if(log.isInfoEnabled()){
					log.info("废弃 -> {} 失败，异常信息为 {}", studentCheckAttendance.getId(), e);	
				}
				
			}
		}
		
	}






}
