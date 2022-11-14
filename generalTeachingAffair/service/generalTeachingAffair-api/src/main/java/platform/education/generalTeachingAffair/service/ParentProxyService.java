package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentAccountStudent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.model.ParentStudentBus;
import platform.education.generalTeachingAffair.vo.ParentStudentCondition;
import platform.education.generalTeachingAffair.vo.ParentVo;
import platform.education.generalTeachingAffair.vo.StudentMergeParentVo;

import java.util.List;

public interface ParentProxyService {

	ParentVo modifyPage(ParentVo parentVo) throws Exception;

	List<ParentVo> getParentTemplate(ParentVo parentvo);

	void remove(ParentVo parentvo) throws Exception;

	ParentVo add(ParentVo parentvo) throws Exception;
	
	/**
	 * 功能描述：添加家长信息
	 * 2015-11-21
	 * @param parentvo
	 * @return
	 */
	ParentVo addInfoForParent(ParentVo parentvo);

	ParentVo addExcel(ParentVo parentvo) throws Exception;

	List<StudentMergeParentVo> findParentByStudent(ParentVo parentvo,
			Page page, Order order) throws Exception;

	ParentVo modify(ParentVo parentVo) throws Exception;

	ParentVo findStudent(ParentVo parentvo);

	List<ParentVo> findDownLoadParentByStudent(ParentVo parentvo, Page page,
			Order order) throws Exception;

	List<ParentVo> findStudentByParent(Integer parentUserId, Page page,
			Order order) throws Exception;

	List<ParentVo> findParentByStudentUserId(Integer studentUserId);

	void deleteParentByStuAndParent(ParentVo parentVo) throws Exception;

	List<ParentStudent> findParentStudentListBySchoolId(Integer schoolId);

	Integer findCountParentStudentListBySchoolId(Integer schoolId);

	boolean checkMobile(String mobile);
	/**
	 * 根据学校，年级，班级 集合概念数据，查找对应的家长信息集合
	 * @param parentStudentCondition 参数学校schoolId，年级gradeId，班级teamId可用
	 * @param page
	 * @param order
	 * @return 返回对应的家长信息列表
	 */
	List<Parent> findParentByGroupCondition(ParentStudentCondition parentStudentCondition,
			Page page, Order order);

	List<Parent> findParentByGroupCondition(ParentStudentCondition parentStudentCondition);

	List<ParentStudent> findParentByGroupCondition(ParentStudentCondition parentStudentCondition,
			Page page);


	List<ParentAccountStudent> findParentAccountAndStuInfo(ParentStudentCondition parentStudentCondition,
														   Page page);

	
	List<Parent> findParentByGroupCondition(ParentStudentCondition parentStudentCondition,
			Order order);


	List<ParentStudent> findSutBusByGroupCondition(ParentStudentCondition parentStudentCondition, Page page);

	List<ParentStudentBus> findParentSutBusByGroupCondition(ParentStudentCondition parentStudentCondition, Page page);
	List<ParentStudent> findByUser(String stuName,String name,Integer state,String userName,String mobile,Page page);

}
