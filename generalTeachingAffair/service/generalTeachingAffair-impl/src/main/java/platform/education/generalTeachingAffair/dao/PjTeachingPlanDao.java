package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjTeachingPlan;
import platform.education.generalTeachingAffair.vo.PjTeachingPlanCondition;
import platform.education.generalTeachingAffair.vo.PjTeachingPlanVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjTeachingPlanDao extends GenericDao<PjTeachingPlan, java.lang.Integer> {

	List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition, Page page, Order order);
	
	PjTeachingPlan findById(Integer id);
	
	Long count(PjTeachingPlanCondition pjTeachingPlanCondition);
	
	List<PjTeachingPlanVo> findMoreByCondition(PjTeachingPlanCondition condition,Page page, Order order);

	List<PjTeachingPlanVo> findCountNumberByTeacher(Integer schoolId, String schoolYear, String termCode, String teacherId);

	List<PjTeachingPlanVo> findCountNumberBySubject(Integer schoolId, String schoolYear, String termCode);

	List<PjTeachingPlanVo> findCountNumberByGrade(Integer schoolId, String schoolYear, String termCode);
}
