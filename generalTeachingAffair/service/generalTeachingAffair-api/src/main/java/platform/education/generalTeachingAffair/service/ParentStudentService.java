package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.ParentAccountStudent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.ParentStudentBus;
import platform.education.generalTeachingAffair.vo.ParentMess;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.ParentVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.Date;
import java.util.List;

public interface ParentStudentService {
    ParentStudent findParentStudentById(Integer id);

	ParentStudent add(ParentStudent parentStudent);

	ParentStudent modify(ParentStudent parentStudent);

	void remove(ParentStudent parentStudent);

	List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition, Page page, Order order);

	List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition);

	List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition, Page page);

	List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition, Order order);

	Long count();

	Long count(ParentStudentCondition parentStudentCondition);

	List<ParentStudent> findParentStudentListBySchoolId(
			Integer schoolId);
	/**
	 *
	 * @param parentStudentCondition 可传参数为 schoolID,teamId,gradeId
	 * @return 返回为 含有 parentId的list
	 */
	List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition);

	List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition,
			Page page, Order order);


	List<ParentAccountStudent> findParentAccountAndStuInfo(ParentStudentCondition parentStudentCondition,
														   Page page);

	List<ParentStudent> findSutBusByGroupCondition(ParentStudentCondition parentStudentCondition,
			Page page, Order order);


	List<ParentStudentBus> findParentSutBusByGroupCondition(ParentStudentCondition parentStudentCondition,
															Page page, Order order);

	List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition,
			Order order);

	List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition,
			Page page);

	/**
	 * @function 根据一个学生获取他的所有家长
	 * @param userId
	 * @date 2016-2-22
	 * @return
	 */
	List<ParentStudent> findParentStudentByStudentUserId(Integer userId);

	ParentVo modifyMainGuardian(Integer parentId, Integer studentId);
	/**
	 * 根据家长用户ID和学生用户ID获取唯一信息
	 * @param parentUserId
	 * @data 2016-7-28
	 * @return
	 */
	ParentStudent findUnique (Integer parentUserId, Integer studentUserId);

	/**
	 * 根据学生userId获取家长信息（用于学生档案）
	 * @param studentUserId
	 * @return
	 */
	List<ParentMess> findParentMessByStudentUserId(Integer studentUserId);

	List<ParentStudent> findIncrementByParentUserId(Integer schoolId, Integer parentUserId, Date modifyDate, Boolean isDeleted);

	void modifyNoRead(ParentStudent parentStudent, int i);

	List<ParentStudent> findParentStudentByParentUserId(Integer parentUserId);

	/**
	 * 获取学生主监护人
	 * @param studentUserId
	 * @return
	 */
	ParentStudent findMainGuardian(Integer studentUserId);
}
