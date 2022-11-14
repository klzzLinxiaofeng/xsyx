package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.LessonPlan;
import platform.education.generalTeachingAffair.vo.LessonPlanCondition;
import platform.education.generalTeachingAffair.vo.LessonPlanVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LessonPlanDao extends GenericDao<LessonPlan, java.lang.Integer> {

	List<LessonPlan> findLessonPlanByCondition(LessonPlanCondition lessonPlanCondition, Page page, Order order);
	
	LessonPlan findById(Integer id);
	
	Long count(LessonPlanCondition lessonPlanCondition);

	List<LessonPlanVo> findMoreByCondition(LessonPlanCondition condition,Page page, Order order);

	List<LessonPlanVo> findCountNumberByTeacher(Integer schoolId, String schoolYear, String termCode, String teacherId);

	List<LessonPlanVo> findCountNumberBySubject(Integer schoolId, String schoolYear, String termCode);

	List<LessonPlanVo> findCountNumberByGrade(Integer schoolId, String schoolYear, String termCode);
	
}
