package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.MoralEvaluationStudent;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface MoralEvaluationStudentDao extends GenericDao<MoralEvaluationStudent, java.lang.Integer> {

	List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Page page, Order order);
	
	/**
	 * 功能描述： 关联查询出班级名称以及学生姓名
	 * @param moralEvaluationStudentCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<MoralEvaluationStudentVo> findMoralEvaluationStudentByConditionMore(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Page page, Order order);
	
	MoralEvaluationStudent findById(Integer id);
	
	Long count(MoralEvaluationStudentCondition moralEvaluationStudentCondition);
	
	void deleteByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition);
	
	/**
	 * 功能描述： 通过teamId(对应于pj_team.id)、studentId(对应于pj_student.id)查找唯一未删除的记录
	 * @param teamId
	 * @param studentId
	 * @return
	 */
	MoralEvaluationStudent findUnique(Integer teamId, Integer studentId);
	
}
