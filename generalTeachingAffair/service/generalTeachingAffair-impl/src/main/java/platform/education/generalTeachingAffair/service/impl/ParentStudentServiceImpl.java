package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.ParentStudentDao;
import platform.education.generalTeachingAffair.model.ParentAccountStudent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.ParentStudentBus;
import platform.education.generalTeachingAffair.service.ParentStudentService;
import platform.education.generalTeachingAffair.vo.ParentMess;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.ParentVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ParentStudentServiceImpl implements ParentStudentService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private ParentStudentDao parentStudentDao;

	public void setParentStudentDao(ParentStudentDao parentStudentDao) {
		this.parentStudentDao = parentStudentDao;
	}
	
	@Override
	public ParentStudent findParentStudentById(Integer id) {
		try {
			return parentStudentDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public ParentStudent add(ParentStudent parentStudent) {
		if(parentStudent == null) {
    		return null;
    	}
    	Date createDate = parentStudent.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	parentStudent.setCreateDate(createDate);
    	parentStudent.setModifyDate(createDate);
    	parentStudent.setIsDelete(false);
		return parentStudentDao.create(parentStudent);
	}

	@Override
	public ParentStudent modify(ParentStudent parentStudent) {
		if(parentStudent == null) {
    		return null;
    	}
    	Date modify = parentStudent.getModifyDate();
    	parentStudent.setModifyDate(modify != null ? modify : new Date());
		return parentStudentDao.update(parentStudent);
	}
	
	@Override
	public void remove(ParentStudent parentStudent) {
		 parentStudent.setIsDelete(true);
		 parentStudent.setModifyDate(new Date());
		 parentStudentDao.delete(parentStudent);
	}
	
	@Override
	public List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition, Page page, Order order) {
		return parentStudentDao.findParentStudentByCondition(parentStudentCondition, page, order);
	}
	
	@Override
	public List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition) {
		parentStudentCondition.setIsDelete(false);
		return parentStudentDao.findParentStudentByCondition(parentStudentCondition, null, null);
	}
	@Override
	public List<ParentStudent> findParentStudentListBySchoolId(Integer schoolId) {
		return parentStudentDao.findParentStudentListBySchoolId(schoolId);
	}
	
	@Override
	public List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition, Page page) {
		parentStudentCondition.setIsDelete(false);
		return parentStudentDao.findParentStudentByCondition(parentStudentCondition, page, null);
	}
	
	@Override
	public List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition, Order order) {
		parentStudentCondition.setIsDelete(false);
		return parentStudentDao.findParentStudentByCondition(parentStudentCondition, null, order);
	}
	
	@Override
	public List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition) {
		
		return this.parentStudentDao.findParentIdByCondition(parentStudentCondition,null,null);
	}
	
	@Override
	public List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition, Page page) {
		
		return this.parentStudentDao.findParentIdByCondition(parentStudentCondition,page,null);
	}
	
	@Override
	public List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition, Order order) {
		
		return this.parentStudentDao.findParentIdByCondition(parentStudentCondition,null,order);
	}
	
	@Override
	public List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition, Page page, Order order) {
		
		return this.parentStudentDao.findParentIdByCondition(parentStudentCondition,page,order);
	}

	@Override
	public List<ParentStudent> findSutBusByGroupCondition(ParentStudentCondition parentStudentCondition, Page page, Order order) {

		return this.parentStudentDao.findSutBusByGroupCondition(parentStudentCondition,page,order);
	}


	@Override
	public List<ParentStudentBus> findParentSutBusByGroupCondition(ParentStudentCondition parentStudentCondition, Page page, Order order) {

		return this.parentStudentDao.findParentSutBusByGroupCondition(parentStudentCondition,page,order);
	}
	
	@Override
	public Long count() {
		return this.parentStudentDao.count(null);
	}

	@Override
	public Long count(ParentStudentCondition parentStudentCondition) {
		parentStudentCondition.setIsDelete(false);
		return this.parentStudentDao.count(parentStudentCondition);
	}

	@Override
	public List<ParentStudent> findParentStudentByStudentUserId(Integer studentUserId) {
		// TODO Auto-generated method stub
		return this.parentStudentDao.findParentStudentByStudentUserId(studentUserId);
	}

	@Override
	public ParentVo modifyMainGuardian(Integer parentUserId,Integer studentUserId) {
		ParentStudent parentStudent = null;
		ParentVo parentVo = null;
		ParentStudentCondition parentStudentCondition = new ParentStudentCondition();
		parentStudentCondition.setParentUserId(parentUserId);
		parentStudentCondition.setStudentUserId(studentUserId);
		List<ParentStudent> list = parentStudentDao.findParentStudentByCondition(parentStudentCondition, null, null);
		if(list != null && list.size() > 0){
			parentStudent = list.get(0);
			parentStudent.setRank("1");
			parentStudent = parentStudentDao.update(parentStudent);
		}
		if(parentStudent == null){
			parentVo = new ParentVo();
			parentVo.setErrorCode("020101");
			parentVo.setErrorInfo("学生家长关系不存在");
		}
		return parentVo;
	}

	@Override
	public ParentStudent findUnique(Integer parentUserId, Integer studentUserId) {
		return this.parentStudentDao.findUnique(parentUserId, studentUserId);
	}

	@Override
	public List<ParentMess> findParentMessByStudentUserId(Integer studentUserId){
		return parentStudentDao.findParentMessByStudentUserId(studentUserId);
	}

	@Override
	public List<ParentStudent> findIncrementByParentUserId(Integer schoolId, Integer parentUserId, Date modifyDate, Boolean isDeleted) {
		return this.parentStudentDao.findIncrementByParentUserId(schoolId, parentUserId, modifyDate, isDeleted);
	}

	@Override
	public void modifyNoRead(ParentStudent parentStudent, int i) {
		parentStudentDao.updateNoRead(parentStudent, i);
	}

	@Override
	public List<ParentStudent> findParentStudentByParentUserId(Integer parentUserId) {
		if(parentUserId==null) {
			return new ArrayList<ParentStudent>(1);
		} else {
			return parentStudentDao.findParentStudentByParentUserId(parentUserId);
		}
	}

	@Override
	public ParentStudent findMainGuardian(Integer studentUserId) {
		ParentStudentCondition condition = new ParentStudentCondition();
		condition.setStudentUserId(studentUserId);
		condition.setRank("1");
		condition.setIsDelete(false);
		List<ParentStudent> list = findParentStudentByCondition(condition);
		if (list != null && list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public List<ParentAccountStudent> findParentAccountAndStuInfo(ParentStudentCondition parentStudentCondition, Page page) {
		int totalRow=parentStudentDao.countParent(parentStudentCondition);
		if(totalRow % page.getPageSize()==0){
			page.setTotalPages(totalRow/page.getPageSize());
		}else{
			page.setTotalPages((totalRow/page.getPageSize()+1));
		}
		int start=(page.getCurrentPage() - 1) * page.getPageSize();
		if(StringUtils.isEmpty(parentStudentCondition.getStuName())) {
			return parentStudentDao.findParentAccountAndStu(parentStudentCondition,start , page.getPageSize());
		}
		return parentStudentDao.findParentAccountAndStu2(parentStudentCondition, start, page.getPageSize());
	}
}
