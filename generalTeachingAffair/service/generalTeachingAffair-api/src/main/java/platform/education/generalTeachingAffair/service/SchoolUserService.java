package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface SchoolUserService {

	SchoolUser findSchoolUserById(Integer id);

	SchoolUser add(SchoolUser schoolUser);

	SchoolUser modify(SchoolUser schoolUser);

	void remove(SchoolUser schoolUser);

	List<SchoolUser> findSchoolUserByCondition(SchoolUserCondition schoolUserCondition, Page page, Order order);

	Integer findSchoolIdByUserName(String userName);
	
	/**
	 * 功能描述：获得所有在校教师（user_type = 1）
	 * 2016-02-19
	 * @param schoolId
	 * @return
	 */
	List<SchoolUser> findSchoolUserOfTeacher(Integer schoolId);
	
	/**
	 * 批量修改某类型用户为离校状态
	 * @param userType 1=教师 3=家长 4学生
	 * @param userIds
	 */
	int updateNotInSchoolByType(String userType, Integer[] userIds);
	
	/**
	 * 获取某学生同校在读的兄弟姐妹
	 * @param parentUserIds
	 * @param studentUserId
	 * @param schoolId
	 * @return
	 */
	List<SchoolUser> findSiblingOfStudentUser(Integer[] parentUserIds, Integer studentUserId, Integer schoolId);

	/**
	 * 根据userId获取SchoolUser记录
	 * @param userId
	 * @return
	 */
	SchoolUser findSchoolUserByUserId(Integer userId);

	SchoolUser findSchoolUserByUserId(Integer userId, Integer schoolId);

	SchoolUser findSchoolUserListByUserIdAndType(Integer userId, String type);

	SchoolUser addSchoolUser(SchoolUser schoolUser);

	List<SchoolUser> findSchoolUserListByUserId(Integer userid);
	
}
