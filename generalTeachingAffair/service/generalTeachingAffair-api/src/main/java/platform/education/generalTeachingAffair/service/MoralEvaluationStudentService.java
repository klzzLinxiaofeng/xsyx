package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.MoralEvaluationResult;
import platform.education.generalTeachingAffair.model.MoralEvaluationStudent;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentCondition;
import platform.education.generalTeachingAffair.vo.MoralEvaluationStudentVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface MoralEvaluationStudentService {
	
	/**
	 * 当前接口crud操作 成功时返回的状态值
	 */
	public final static String OPERATE_SUCCESS = "success";
	
	/**
	 * 当前接口crud操作 失败时返回的状态值
	 */
	public final static String OPERATE_FAIL = "fail";
	
	/**
	 * 系统异常造成的操作失败 系统返回的状态值
	 */
	public final static String OPERATE_ERROR = "error";
	
    MoralEvaluationStudent findMoralEvaluationStudentById(Integer id);
	   
	MoralEvaluationStudent add(MoralEvaluationStudent moralEvaluationStudent);
	   
	MoralEvaluationStudent modify(MoralEvaluationStudent moralEvaluationStudent);
	   
	void remove(MoralEvaluationStudent moralEvaluationStudent);
	   
	List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Page page, Order order);
	
	List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition);
	
	List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Page page);
	
	List<MoralEvaluationStudent> findMoralEvaluationStudentByCondition(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Order order);
	
	/**
	 * 功能描述： 关联查询出班级名称以及学生姓名
	 * @param moralEvaluationStudentCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<MoralEvaluationStudentVo> findMoralEvaluationStudentByConditionMore(MoralEvaluationStudentCondition moralEvaluationStudentCondition, Page page, Order order);
	
	Long count();
	
	Long count(MoralEvaluationStudentCondition moralEvaluationStudentCondition);
	
	void remove(MoralEvaluationStudentCondition moralEvaluationStudentCondition);
	
	/**
	 * 功能描述： 通过teamId(对应于pj_team.id)、studentId(对应于pj_student.id)查找唯一未删除的记录
	 * @param teamId
	 * @param studentId
	 * @return
	 */
	MoralEvaluationStudent findUnique(Integer teamId, Integer studentId);
	
	/**
	 * 功能描述：逻辑上删除用户账号即数据库仍然保存记录，只是修改删除标识
	 * @param moralEvaluationItem
	 * @return
	 */
	String abandon(MoralEvaluationStudent moralEvaluationStudent);
	
	/**
	 * 功能描述：添加对某个学生的评价结果
	 * @param moralEvaluationStudentVo
	 * @param classifications
	 * @return
	 */
	String addMoralEvaluationResult(Integer SchoolId, MoralEvaluationStudent moralEvaluationStudent, List<MoralEvaluationResult> moralEvaluationResults);
	
	/**
	 * 功能描述：修改对某个学生的评价结果
	 * @param id
	 * @param moralEvaluationStudent
	 * @param classifications
	 * @return
	 */
	String modifyMoralEvaluationResult(Integer id, MoralEvaluationStudent moralEvaluationStudent, List<MoralEvaluationResult> moralEvaluationResults);
}
