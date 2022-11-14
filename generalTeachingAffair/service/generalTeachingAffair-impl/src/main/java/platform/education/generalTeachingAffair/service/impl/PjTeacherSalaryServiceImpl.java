package platform.education.generalTeachingAffair.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import platform.education.generalTeachingAffair.dao.PjTeacherSalaryDao;
import platform.education.generalTeachingAffair.model.PjTeacherSalary;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.PjTeacherSalaryService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.utils.BeanUtilsSub;
import platform.education.generalTeachingAffair.vo.PjTeacherSalaryCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjTeacherSalaryServiceImpl implements PjTeacherSalaryService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjTeacherSalaryDao pjTeacherSalaryDao;
	
	@Autowired
	@Qualifier("teacherService")
	private TeacherService teacherService;
	
	public void setPjTeacherSalaryDao(PjTeacherSalaryDao pjTeacherSalaryDao) {
		this.pjTeacherSalaryDao = pjTeacherSalaryDao;
	}
	
	@Override
	public PjTeacherSalary findPjTeacherSalaryById(Integer id) {
		try {
			return pjTeacherSalaryDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjTeacherSalary addPjTeacherSalary(PjTeacherSalary pjTeacherSalary) throws IllegalArgumentException {
		if(pjTeacherSalary == null) {
			return null;
		}
		Date createDate = pjTeacherSalary.getCreateDate();
		if(createDate == null) {
			createDate = new Date();
			pjTeacherSalary.setCreateDate(createDate);
		}
		pjTeacherSalary.setModifyDate(createDate);
		pjTeacherSalary.setIsDeleted(false);
		
		PjTeacherSalaryCondition pjTeacherSalaryCondition = new PjTeacherSalaryCondition();
		pjTeacherSalaryCondition.setUserId(pjTeacherSalary.getUserId());
		pjTeacherSalaryCondition.setPayMonth(pjTeacherSalary.getPayMonth());
		pjTeacherSalaryCondition.setPayYear(pjTeacherSalary.getPayYear());
		completeParam(pjTeacherSalary.getSchoolId(), pjTeacherSalary.getUserId(), pjTeacherSalary);
		
		List<PjTeacherSalary> isExist = pjTeacherSalaryDao.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, null, null);
		if ( isExist != null && isExist.size() > 0 ) {
			pjTeacherSalary.setId(isExist.get(0).getId());
			return modify(pjTeacherSalary);
		}
		
		return pjTeacherSalaryDao.create(pjTeacherSalary);
	}
	
	private PjTeacherSalary completeParam(Integer schoolId , Integer userId, PjTeacherSalary pjTeacherSalary ) throws IllegalArgumentException {
		TeacherCondition teacherCondition = new TeacherCondition();
		teacherCondition.setSchoolId(schoolId);
		teacherCondition.setUserId(userId);
		List<Teacher> teacherList = teacherService.findTeacherByCondition(teacherCondition, null, null);
		if ( teacherList != null && teacherList.size() > 0 ) {
			pjTeacherSalary.setSchoolId(schoolId);
			pjTeacherSalary.setUserId(userId);
			pjTeacherSalary.setTeacherId(teacherList.get(0).getId());
		} else {
			throw new IllegalArgumentException("教师表中schoolId:"+schoolId+", userId:"+userId+"不存在");
		}
		return pjTeacherSalary;
	}
	

	@Override
	public PjTeacherSalary modify(PjTeacherSalary pjTeacherSalary) {
		if(pjTeacherSalary == null) {
    		return null;
    	}
		PjTeacherSalary output = pjTeacherSalaryDao.findById(pjTeacherSalary.getId());
		if ( output == null ) {
			throw new IllegalArgumentException("id:"+pjTeacherSalary.getId()+"数据不存在");
		}
		BeanUtilsSub.copyProperties(output, pjTeacherSalary);
		pjTeacherSalary.setModifyDate(new Date());
		pjTeacherSalary.setSalaryTotal(getTotalSalaryValue(output));
		return pjTeacherSalaryDao.update(pjTeacherSalary);
	}
	
	private Float getTotalSalaryValue(PjTeacherSalary output) {
		Float totalSalaryValue = output.getS1() + output.getS2()
				+ output.getS3() + output.getS4() + output.getS5()
				+ output.getS6() + output.getS7() + output.getS8()
				+ output.getS9() + output.getS10() + output.getS11()
				+ output.getS12() + output.getS13() + output.getS14()
				+ output.getS15() + output.getS16() + output.getS17()
				+ output.getS18() + output.getS19() + output.getS20();
		return totalSalaryValue;
	}
	
	@Override
	public void remove(PjTeacherSalary pjTeacherSalary) {
		try {
			pjTeacherSalaryDao.delete(pjTeacherSalary);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjTeacherSalary.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition, Page page, Order order) {
		return pjTeacherSalaryDao.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, page, order);
	}
	
	@Override
	public List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition) {
		return pjTeacherSalaryDao.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, null, null);
	}
	
	@Override
	public List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition, Page page) {
		return pjTeacherSalaryDao.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, page, null);
	}
	
	@Override
	public List<PjTeacherSalary> findPjTeacherSalaryByCondition(PjTeacherSalaryCondition pjTeacherSalaryCondition, Order order) {
		return pjTeacherSalaryDao.findPjTeacherSalaryByCondition(pjTeacherSalaryCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjTeacherSalaryDao.count(null);
	}

	@Override
	public Long count(PjTeacherSalaryCondition pjTeacherSalaryCondition) {
		return this.pjTeacherSalaryDao.count(pjTeacherSalaryCondition);
	}
	
	@Override
	public List<Integer> findPjTeacherSalaryYearBySchoolId(Integer schoolId) {
		return pjTeacherSalaryDao.findPjTeacherSalaryYearBySchoolId(schoolId);
//		return null;
	}

}
