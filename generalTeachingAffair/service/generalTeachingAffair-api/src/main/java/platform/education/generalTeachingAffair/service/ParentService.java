package platform.education.generalTeachingAffair.service;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Parent;
import platform.education.generalTeachingAffair.model.ParentStudent;
import platform.education.generalTeachingAffair.vo.ParentCondition;
import platform.education.generalTeachingAffair.vo.ParentVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ParentService {
    Parent findParentById(Integer id);
	   
	Parent add(Parent parent);
	   
	Parent modify(Parent parent);
	   
	void remove(Parent parent);
	   
	List<Parent> findParentByCondition(ParentCondition parentCondition, Page page, Order order);
	
	List<Parent> findParentByCondition(ParentCondition parentCondition);
	
	List<Parent> findParentByCondition(ParentCondition parentCondition, Page page);
	
	List<Parent> findParentByCondition(ParentCondition parentCondition, Order order);
	
	Long count();
	
	Long count(ParentCondition parentCondition);
	
	/**
	 * 功能描述：通过userId查询出对应的家长信息
	 * @param userId
	 * @return
	 */
	Parent findUniqueByUserId(Integer userId);

List<Parent> findParentByCondition2(ParentCondition parentCondition, Page page, Order order);
	
	List<Parent> findParentByCondition2(ParentCondition parentCondition);
	
	List<Parent> findParentByCondition2(ParentCondition parentCondition, Page page);
	
	List<Parent> findParentByCondition2(ParentCondition parentCondition, Order order);

	/**
	 * 获取学校所有家长的信息(根据修改时间增量获取)
	 * @param schoolId
	 * @return
	 */
	List<ParentVo> findIncrementDataByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer parentId, Boolean isGetNew);

	ParentVo findParentVoByUserId(Integer userId);

	List<ParentVo> findParentsByStudentUserId(Integer studentUserId);

	/**
	 * 创建学生家长信息(新版建校流程)
	 * @param schoolId
	 * @param appid
	 * @param gradeId
	 * @param teamId
	 * @param studentId
	 * @param guardian	家长名
	 * @param guardianPhone	家长手机号
	 * @param parentType	家长类型 4
	 */
	boolean addParentFromExcelImport(Integer schoolId, Integer appid, Integer gradeId, Integer teamId, Integer studentId,
			String guardian, String guardianPhone, String parentType, String parentRelation, String rank, String email);

	/**
	 * 根据手机号查询家长信息
	 * @param mobile
	 * @return
	 */
	Parent findParentByMobile(String mobile);

	void removeAllByUserId(Integer parentUserId, Integer schoolid, String type);

	Parent findParentByMobileAndSchoolId(String mobile, Integer schoolId);

	String deleteParentStudentRelate(Integer parentId, Integer studentId, String parentType);

	Map<String, Object> addParentFromExcelImport(
			String gradeName, String teamNumber, String studentName, Integer number,
			String parentName, String mobile, String relation,
			Integer schoolId, String schoolYear, Integer groupId, Integer appId);
	List<ParentStudent> findByUser(String stuName, String name, Integer state, String userName, String mobile, Page page);
}
