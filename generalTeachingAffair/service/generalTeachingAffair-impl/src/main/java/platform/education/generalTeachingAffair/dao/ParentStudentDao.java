package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ParentAccountStudent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.ParentStudentBus;
import platform.education.generalTeachingAffair.vo.ParentMess;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.Date;
import java.util.List;

public interface ParentStudentDao extends GenericDao<ParentStudent, java.lang.Integer> {

	List<ParentStudent> findParentStudentByCondition(ParentStudentCondition parentStudentCondition, Page page, Order order);
	List<ParentStudent> findParentStudentListBySchoolId(Integer schoolId);
	ParentStudent findById(Integer id);
	Long count(ParentStudentCondition parentStudentCondition);
	/**
	 * 
	 * @param parentStudentCondition 参数学校schoolId，年级gradeId，班级teamId可用
	 * @param page
	 * @param order
	 * @return
	 */
	List<ParentStudent> findParentIdByCondition(ParentStudentCondition parentStudentCondition, Page page, Order order);

	int countParent(ParentStudentCondition parentStudentCondition);

	List<ParentAccountStudent> findParentAccountAndStu(ParentStudentCondition parentStudentCondition,
													   Integer start,Integer pageSize);
	List<ParentAccountStudent> findParentAccountAndStu2(ParentStudentCondition parentStudentCondition,
													   Integer start,Integer pageSize);

	List<ParentStudent> findSutBusByGroupCondition(ParentStudentCondition parentStudentCondition, Page page, Order order);


	List<ParentStudentBus> findParentSutBusByGroupCondition(ParentStudentCondition parentStudentCondition, Page page, Order order);

	/**
	 * @function 根据一个学生的userId获取他的所有家长
	 * @param studentUserId
	 * @date 2016-2-22
	 * @return
	 */
	List<ParentStudent> findParentStudentByStudentUserId(Integer studentUserId);
	
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
	
	void updateNoRead(ParentStudent parentStudent, int i);
	
	List<ParentStudent> findParentStudentByParentUserId(Integer parentUserId);
}
