package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.StudentActivityDao;
import platform.education.generalTeachingAffair.model.StudentActivity;
import platform.education.generalTeachingAffair.service.PracticeService;
import platform.education.generalTeachingAffair.service.StudentActivityService;
import platform.education.generalTeachingAffair.vo.StudentActivityCondition;

public class StudentActivityServiceImpl implements StudentActivityService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private StudentActivityDao studentActivityDao;

	public void setStudentActivityDao(StudentActivityDao studentActivityDao) {
		this.studentActivityDao = studentActivityDao;
	}
	
	@Override
	public StudentActivity findStudentActivityById(Integer id) {
		try {
			return studentActivityDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public StudentActivity add(StudentActivity studentActivity) {
		if(studentActivity == null) {
    		return null;
    	}
    	Date createDate = studentActivity.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	studentActivity.setCreateDate(createDate);
    	studentActivity.setModifyDate(createDate);
		return studentActivityDao.create(studentActivity);
	}

	@Override
	public StudentActivity modify(StudentActivity studentActivity) {
		if(studentActivity == null) {
    		return null;
    	}
    	Date modify = studentActivity.getModifyDate();
    	studentActivity.setModifyDate(modify != null ? modify : new Date());
		return studentActivityDao.update(studentActivity);
	}
	
	@Override
	public void remove(StudentActivity studentActivity) {
		try {
			studentActivityDao.delete(studentActivity);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", studentActivity.getId(), e);
			}
		}
	}
	
	@Override
	public List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition, Page page, Order order) {
		return studentActivityDao.findStudentActivityByCondition(studentActivityCondition, page, order);
	}
	
	@Override
	public List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition) {
		return studentActivityDao.findStudentActivityByCondition(studentActivityCondition, null, null);
	}
	
	@Override
	public List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition, Page page) {
		return studentActivityDao.findStudentActivityByCondition(studentActivityCondition, page, null);
	}
	
	@Override
	public List<StudentActivity> findStudentActivityByCondition(StudentActivityCondition studentActivityCondition, Order order) {
		return studentActivityDao.findStudentActivityByCondition(studentActivityCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.studentActivityDao.count(null);
	}

	@Override
	public Long count(StudentActivityCondition studentActivityCondition) {
		return this.studentActivityDao.count(studentActivityCondition);
	}

	@Override
	public String moveTo(StudentActivity studentActivity) {
		if(studentActivity != null) {
			studentActivity.setIsDelete(true);
			try {
				studentActivity = this.studentActivityDao.update(studentActivity);
				if(studentActivity != null){
					return PracticeService.OPERATE_SUCCESS;
				}
			} catch (Exception e) {
				if(log.isInfoEnabled()) {
					log.info("废弃 -> {} 失败，异常信息为 {}", studentActivity.getId(), e);
				}
				return StudentActivityService.OPERATE_ERROR;
			}
		}
		return StudentActivityService.OPERATE_FAIL;
	}

}
