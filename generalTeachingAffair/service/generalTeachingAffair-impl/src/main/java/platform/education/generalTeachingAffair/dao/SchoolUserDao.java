package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.SchoolUser;
import platform.education.generalTeachingAffair.vo.SchoolUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface SchoolUserDao extends GenericDao<SchoolUser, java.lang.Integer> {

	List<SchoolUser> findSchoolUserByCondition(SchoolUserCondition schoolUserCondition, Page page, Order order);

	SchoolUser findById(Integer id);

	Integer findSchoolIdByUserName(String userName);
	
	/**
	 * 功能描述：获得所有在校教师（user_type = 1）
	 * 2016-02-19
	 * @param schoolId
	 * @return
	 */
	List<SchoolUser> findSchoolUserOfTeacher(Integer schoolId, String userType);
	
	int updateNotInSchoolByType(String userType, Integer[] userIds);
	
	List<SchoolUser> findSiblingOfStudentUser(Integer[] parentUserIds, Integer studentUserId, Integer schoolId);
}
