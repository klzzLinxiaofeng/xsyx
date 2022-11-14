package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.TeamUser;
import platform.education.generalTeachingAffair.vo.TeamUserCondition;
import platform.education.generalTeachingAffair.service.TeamUserService;
import platform.education.generalTeachingAffair.dao.TeamUserDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class TeamUserServiceImpl implements TeamUserService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private TeamUserDao teamUserDao;

	public void setTeamUserDao(TeamUserDao teamUserDao) {
		this.teamUserDao = teamUserDao;
	}
	
	@Override
	public TeamUser findTeamUserById(Integer id) {
		try {
			return teamUserDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public TeamUser add(TeamUser teamUser) {
		if(teamUser == null) {
    		return null;
    	}
    	Date createDate = teamUser.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	teamUser.setCreateDate(createDate);
    	teamUser.setModifyDate(createDate);
		return teamUserDao.create(teamUser);
	}

	@Override
	public TeamUser modify(TeamUser teamUser) {
		if(teamUser == null) {
    		return null;
    	}
    	Date modify = teamUser.getModifyDate();
    	teamUser.setModifyDate(modify != null ? modify : new Date());
		return teamUserDao.update(teamUser);
	}
	
	@Override
	public void remove(TeamUser teamUser) {
		try {
			teamUserDao.delete(teamUser);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", teamUser.getId(), e);
			}
		}
	}

	@Override
	public List<TeamUser> findByUserIdAndIsMaster(Integer userId, Boolean isMaster) {
		return teamUserDao.findByUserIdAndIsMaster(userId,isMaster);
	}

	@Override
	public List<TeamUser> findTeamUserByCondition(TeamUserCondition teamUserCondition, Page page, Order order) {
		return teamUserDao.findTeamUserByCondition(teamUserCondition, page, order);
	}
	
	@Override
	public List<TeamUser> findTeamUserByCondition(TeamUserCondition teamUserCondition) {
		return teamUserDao.findTeamUserByCondition(teamUserCondition, null, null);
	}
	
	@Override
	public List<TeamUser> findTeamUserByCondition(TeamUserCondition teamUserCondition, Page page) {
		return teamUserDao.findTeamUserByCondition(teamUserCondition, page, null);
	}
	
	@Override
	public List<TeamUser> findTeamUserByCondition(TeamUserCondition teamUserCondition, Order order) {
		return teamUserDao.findTeamUserByCondition(teamUserCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.teamUserDao.count(null);
	}

	@Override
	public Long count(TeamUserCondition teamUserCondition) {
		return this.teamUserDao.count(teamUserCondition);
	}

	/**
	 * 功能描述：获取一个班的所有班级成员
	 * 2015-12-04
	 */
	@Override
	public List<TeamUser> findTeamUserOfAll(Integer teamId) {
		return teamUserDao.findTeamUserOfAll(teamId);
	}

	/**
	 * 功能描述：获取一个班的所有教师
	 * 2015-12-04
	 */
	@Override
	public List<TeamUser> findTeamUserOfTeachers(Integer teamId) {
		return teamUserDao.findTeamUserOfTeachers(teamId);
	}

	/**
	 * 功能描述：获取一个班的所有学生
	 * 2015-12-04
	 */
	@Override
	public List<TeamUser> findTeamUserOfStudents(Integer teamId) {
		return teamUserDao.findTeamUserOfStudents(teamId);
	}

	/**
	 * 功能描述：获取一个班的所有家长
	 * 2015-12-04
	 */
	@Override
	public List<TeamUser> findTeamUserOfParents(Integer teamId) {
		return teamUserDao.findTeamUserOfParents(teamId);
	}

	/**
	 * 功能描述：通过teamId、userId获取唯一记录
	 * 2015-12-10
	 */
	@Override
	public TeamUser findUnique(Integer teamId, Integer userId) {
		return teamUserDao.findUnique(teamId, userId);
	}

	@Override
	public void removeTeacherByUserId(Integer userid) {
		TeamUserCondition condtion = new TeamUserCondition();
		condtion.setUserId(userid);
		condtion.setIsTeacher(true);
		List<TeamUser> result = teamUserDao.findTeamUserByCondition(condtion, null, null);
		
		Date now = new Date();
		
		for (TeamUser teamUser : result) {
			teamUser.setIsDeleted(true);
			teamUser.setModifyDate(now);
			teamUserDao.delete(teamUser);
		}
		
	}

	@Override
	public void deleteByTeamId(Integer teamId) {
		if(teamId==null) {
			return;
		}
		teamUserDao.deleteByTeamId(teamId);
	}

}
