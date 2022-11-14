package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.LessonPlan;
import platform.education.generalTeachingAffair.vo.LessonPlanCondition;
import platform.education.generalTeachingAffair.vo.LessonPlanVo;
import platform.education.generalTeachingAffair.service.LessonPlanService;
import platform.education.generalTeachingAffair.dao.LessonPlanDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class LessonPlanServiceImpl implements LessonPlanService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private LessonPlanDao lessonPlanDao;

	public void setLessonPlanDao(LessonPlanDao lessonPlanDao) {
		this.lessonPlanDao = lessonPlanDao;
	}
	
	@Override
	public LessonPlan findLessonPlanById(Integer id) {
		try {
			return lessonPlanDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public LessonPlan add(LessonPlan lessonPlan) {
		if(lessonPlan == null) {
    		return null;
    	}
    	Date createDate = lessonPlan.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	lessonPlan.setCreateDate(createDate);
    	lessonPlan.setModifyDate(createDate);
		return lessonPlanDao.create(lessonPlan);
	}

	@Override
	public LessonPlan modify(LessonPlan lessonPlan) {
		if(lessonPlan == null) {
    		return null;
    	}
    	Date modify = lessonPlan.getModifyDate();
    	lessonPlan.setModifyDate(modify != null ? modify : new Date());
		return lessonPlanDao.update(lessonPlan);
	}
	
	@Override
	public void remove(LessonPlan lessonPlan) {
		try {
			lessonPlanDao.delete(lessonPlan);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", lessonPlan.getId(), e);
			}
		}
	}
	
	@Override
	public List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition, Page page, Order order) {
		return lessonPlanDao.findLessonPlanByCondition(lessonPlanCondition, page, order);
	}
	
	@Override
	public List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition) {
		return lessonPlanDao.findLessonPlanByCondition(lessonPlanCondition, null, null);
	}
	
	@Override
	public List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition, Page page) {
		return lessonPlanDao.findLessonPlanByCondition(lessonPlanCondition, page, null);
	}
	
	@Override
	public List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition, Order order) {
		return lessonPlanDao.findLessonPlanByCondition(lessonPlanCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.lessonPlanDao.count(null);
	}

	@Override
	public Long count(LessonPlanCondition lessonPlanCondition) {
		return this.lessonPlanDao.count(lessonPlanCondition);
	}

	@Override
	public List<LessonPlanVo> findMoreByCondition(LessonPlanCondition condition,
			Page page, Order order) {
		if(condition.getSchoolId() == null || "".equals(condition.getSchoolId())){
			log.debug("请设置学校参数！！！");
			return new ArrayList<LessonPlanVo>();
		}
		return this.lessonPlanDao.findMoreByCondition(condition,page,order);
	}

	@Override
	public List<LessonPlanVo> findCountNumberByTeacher(Integer schoolId, String schoolYear, String termCode, String teacherId) {
		return this.lessonPlanDao.findCountNumberByTeacher(schoolId,schoolYear,termCode,teacherId);
	}

	@Override
	public List<LessonPlanVo> findCountNumberBySubject(Integer schoolId, String schoolYear, String termCode) {
		return this.lessonPlanDao.findCountNumberBySubject(schoolId,schoolYear,termCode);
	}

	@Override
	public List<LessonPlanVo> findCountNumberByGrade(Integer schoolId, String schoolYear, String termCode) {
		return this.lessonPlanDao.findCountNumberByGrade(schoolId,schoolYear,termCode);
	}

}
