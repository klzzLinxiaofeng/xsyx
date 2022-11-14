package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjTeachingPlan;
import platform.education.generalTeachingAffair.vo.PjTeachingPlanCondition;
import platform.education.generalTeachingAffair.vo.PjTeachingPlanVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PjTeachingPlanService {
    PjTeachingPlan findPjTeachingPlanById(Integer id);
	   
	PjTeachingPlan add(PjTeachingPlan pjTeachingPlan);
	   
	PjTeachingPlan modify(PjTeachingPlan pjTeachingPlan);
	   
	void remove(PjTeachingPlan pjTeachingPlan);
	   
	List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition, Page page, Order order);
	
	List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition);
	
	List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition, Page page);
	
	List<PjTeachingPlan> findPjTeachingPlanByCondition(PjTeachingPlanCondition pjTeachingPlanCondition, Order order);
	
	Long count();
	
	Long count(PjTeachingPlanCondition pjTeachingPlanCondition);
	
	List<PjTeachingPlanVo> findCountNumberByTeacher(Integer schoolId, String schoolYear, String termCode, String teacherId);

	List<PjTeachingPlanVo> findCountNumberBySubject(Integer schoolId, String schoolYear, String termCode);

	List<PjTeachingPlanVo> findCountNumberByGrade(Integer schoolId, String schoolYear, String termCode);

	/**
	 * @function 该方法提供搜索列表数据   但是为了优化  请将学校的ID设置  如果不设置将返回null 没有数据
	 * @param condition
	 * @param page
	 * @param order
	 * @return List<PjTeachingPlanVo>
	 */
	List<PjTeachingPlanVo> findMoreByCondition(PjTeachingPlanCondition condition,Page page, Order order);
}
