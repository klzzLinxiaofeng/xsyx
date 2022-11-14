package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.vo.TeamUserCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface TeamUserService {
    TeamUser findTeamUserById(Integer id);
	   
	TeamUser add(TeamUser teamUser);
	   
	TeamUser modify(TeamUser teamUser);
	   
	void remove(TeamUser teamUser);

	List<TeamUser> findByUserIdAndIsMaster(Integer userId,Boolean isMaster);
	   
	List<TeamUser> findTeamUserByCondition(TeamUserCondition teamUserCondition, Page page, Order order);
	
	List<TeamUser> findTeamUserByCondition(TeamUserCondition teamUserCondition);
	
	List<TeamUser> findTeamUserByCondition(TeamUserCondition teamUserCondition, Page page);
	
	List<TeamUser> findTeamUserByCondition(TeamUserCondition teamUserCondition, Order order);
	
	Long count();
	
	Long count(TeamUserCondition teamUserCondition);
	
	/**
	 * 功能描述：获取一个班的所有班级成员
	 * 2015-12-04
	 * @param teamId
	 * @return
	 */
	List<TeamUser> findTeamUserOfAll(Integer teamId);
	
	/**
	 * 功能描述：获取一个班的所有教师
	 * 2015-12-04
	 * @param teamId
	 * @return
	 */
	List<TeamUser> findTeamUserOfTeachers(Integer teamId);
	
	/**
	 * 功能描述：获取一个班的所有学生
	 * 2015-12-04
	 * @param teamId
	 * @return
	 */
	List<TeamUser> findTeamUserOfStudents(Integer teamId);
	
	/**
	 * 功能描述：获取一个班的所有家长
	 * 2015-12-04
	 * @param teamId
	 * @return
	 */
	List<TeamUser> findTeamUserOfParents(Integer teamId);
	
	/**
	 * 功能描述：通过teamId、userId获取唯一记录
	 * 2015-12-10
	 * @param teamId
	 * @param userId
	 * @return
	 */
	TeamUser findUnique(Integer teamId, Integer userId);

	void removeTeacherByUserId(Integer userId);

	void deleteByTeamId(Integer teamId);
	
}
