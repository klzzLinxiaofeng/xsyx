package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.LessonPlan;
import platform.education.generalTeachingAffair.vo.LessonPlanCondition;
import platform.education.generalTeachingAffair.vo.LessonPlanVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface LessonPlanService {
    LessonPlan findLessonPlanById(Integer id);
	   
	LessonPlan add(LessonPlan lessonPlan);
	   
	LessonPlan modify(LessonPlan lessonPlan);
	   
	void remove(LessonPlan lessonPlan);
	   
	List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition, Page page, Order order);
	
	List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition);
	
	List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition, Page page);
	
	List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition, Order order);
	
	Long count();
	
	Long count(LessonPlanCondition lessonPlanCondition);

	/**
	 * @function 该方法提供搜索列表数据   但是为了优化  请将学校的ID设置  如果不设置将返回null 没有数据
	 * @param condition
	 * @param page
	 * @param order
	 * @return List<LessonPlan>
	 */
	List<LessonPlanVo> findMoreByCondition(LessonPlanCondition condition,Page page, Order order);

	List<LessonPlanVo> findCountNumberByTeacher(Integer schoolId, String schoolYear, String termCode, String teacherId);

	List<LessonPlanVo> findCountNumberBySubject(Integer schoolId, String schoolYear, String termCode);

	List<LessonPlanVo> findCountNumberByGrade(Integer schoolId, String schoolYear, String termCode);
	
}
