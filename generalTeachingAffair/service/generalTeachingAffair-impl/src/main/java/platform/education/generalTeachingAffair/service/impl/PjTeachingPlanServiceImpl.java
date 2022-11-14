package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.model.PjTeachingPlan;
import platform.education.generalTeachingAffair.vo.PjTeachingPlanCondition;
import platform.education.generalTeachingAffair.vo.PjTeachingPlanVo;
import platform.education.generalTeachingAffair.service.PjTeachingPlanService;
import platform.education.generalTeachingAffair.dao.PjTeachingPlanDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjTeachingPlanServiceImpl implements PjTeachingPlanService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjTeachingPlanDao pjTeachingPlanDao;

	public void setPjTeachingPlanDao(PjTeachingPlanDao pjTeachingPlanDao) {
		this.pjTeachingPlanDao = pjTeachingPlanDao;
	}
	
	@Override
	public PjTeachingPlan findPjTeachingPlanById(Integer id) {
		try {
			return pjTeachingPlanDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjTeachingPlan add(PjTeachingPlan pjTeachingPlan) {
		if(pjTeachingPlan == null) {
    		return null;
    	}
    	Date createDate = pjTeachingPlan.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjTeachingPlan.setCreateDate(createDate);
    	pjTeachingPlan.setModifyDate(createDate);
		return pjTeachingPlanDao.create(pjTeachingPlan);
	}

	@Override
	public PjTeachingPlan modify(PjTeachingPlan pjTeachingPlan) {
		if(pjTeachingPlan == null) {
    		return null;
    	}
    	Date modify = pjTeachingPlan.getModifyDate();
    	pjTeachingPlan.setModifyDate(modify != null ? modify : new Date());
		return pjTeachingPlanDao.update(pjTeachingPlan);
	}
	
	@Override
	public void remove(PjTeachingPlan pjTeachingPlan) {
		try {
			pjTeachingPlanDao.delete(pjTeachingPlan);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjTeachingPlan.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition, Page page, Order order) {
		return pjTeachingPlanDao.findPjTeachingPlanByCondition(pjTeachingPlanCondition, page, order);
	}
	
	@Override
	public List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition) {
		return pjTeachingPlanDao.findPjTeachingPlanByCondition(pjTeachingPlanCondition, null, null);
	}
	
	@Override
	public List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition, Page page) {
		return pjTeachingPlanDao.findPjTeachingPlanByCondition(pjTeachingPlanCondition, page, null);
	}
	
	@Override
	public List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition, Order order) {
		return pjTeachingPlanDao.findPjTeachingPlanByCondition(pjTeachingPlanCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjTeachingPlanDao.count(null);
	}

	@Override
	public Long count(PjTeachingPlanCondition pjTeachingPlanCondition) {
		return this.pjTeachingPlanDao.count(pjTeachingPlanCondition);
	}
	
	@Override
	public List<PjTeachingPlanVo> findMoreByCondition(PjTeachingPlanCondition condition,Page page, Order order) {
		if(condition.getSchoolId() == null || "".equals(condition.getSchoolId())){
			log.debug("请设置学校参数！！！");
			return new ArrayList<PjTeachingPlanVo>();
		}
		return this.pjTeachingPlanDao.findMoreByCondition(condition,page,order);
	}

	@Override
	public List<PjTeachingPlanVo> findCountNumberByTeacher(Integer schoolId, String schoolYear, String termCode, String teacherId) {
		return this.pjTeachingPlanDao.findCountNumberByTeacher(schoolId,schoolYear,termCode,teacherId);
	}

	@Override
	public List<PjTeachingPlanVo> findCountNumberBySubject(Integer schoolId, String schoolYear, String termCode) {
		return this.pjTeachingPlanDao.findCountNumberBySubject(schoolId,schoolYear,termCode);
	}

	@Override
	public List<PjTeachingPlanVo> findCountNumberByGrade(Integer schoolId, String schoolYear, String termCode) {
		return this.pjTeachingPlanDao.findCountNumberByGrade(schoolId,schoolYear,termCode);
	}

}
