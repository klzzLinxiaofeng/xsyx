package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.model.TeamAdmin;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.vo.TeamAdminCondition;
import platform.education.generalTeachingAffair.service.TeamAdminService;
import platform.education.generalTeachingAffair.dao.TeamAdminDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.TeamAdminVo;

public class TeamAdminServiceImpl implements TeamAdminService{

	private Logger log = LoggerFactory.getLogger(getClass());

	/** 级长类型 */
	private static final int TYPE_PRAEPOSTOR = 3;

	private TeamAdminDao teamAdminDao;

	public void setTeamAdminDao(TeamAdminDao teamAdminDao) {
		this.teamAdminDao = teamAdminDao;
	}

	private TeacherService teacherService;

	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@Override
	public TeamAdmin findTeamAdminById(Integer id) {
		try {
			return teamAdminDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public TeamAdmin add(TeamAdmin teamAdmin) {
		if(teamAdmin == null) {
    		return null;
    	}
    	Date createDate = teamAdmin.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	teamAdmin.setCreateDate(createDate);
    	teamAdmin.setModifyDate(createDate);
		return teamAdminDao.create(teamAdmin);
	}

	@Override
	public TeamAdmin modify(TeamAdmin teamAdmin) {
		if(teamAdmin == null) {
    		return null;
    	}
    	Date modify = teamAdmin.getModifyDate();
    	teamAdmin.setModifyDate(modify != null ? modify : new Date());
		return teamAdminDao.update(teamAdmin);
	}
	
	@Override
	public void remove(TeamAdmin teamAdmin) {
		try {
			teamAdminDao.delete(teamAdmin);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", teamAdmin.getId(), e);
			}
		}
	}
	
	@Override
	public List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition, Page page, Order order) {
		return teamAdminDao.findTeamAdminByCondition(teamAdminCondition, page, order);
	}
	
	@Override
	public List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition) {
		return teamAdminDao.findTeamAdminByCondition(teamAdminCondition, null, null);
	}
	
	@Override
	public List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition, Page page) {
		return teamAdminDao.findTeamAdminByCondition(teamAdminCondition, page, null);
	}
	
	@Override
	public List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition, Order order) {
		return teamAdminDao.findTeamAdminByCondition(teamAdminCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.teamAdminDao.count(null);
	}

	@Override
	public Long count(TeamAdminCondition teamAdminCondition) {
		return this.teamAdminDao.count(teamAdminCondition);
	}

	@Override
	public List<TeamAdmin> findByTeamIds(Integer[] teamIds) {
		return this.teamAdminDao.findByTeamIds(teamIds);
	}

	@Override
	public List<TeamAdminVo> findByGradeIdAndTeamId(Integer gradeId, Integer teamId) {
		return this.teamAdminDao.findByGradeIdAndTeamId(gradeId, teamId);
	}

	@Override
	public List<TeamAdminVo> findByTeacherId(Integer schoolId, String schoolYear, Integer teacherId) {
		return this.teamAdminDao.findByTeacherId(schoolId, schoolYear, teacherId);
	}

	@Override
	public void addTeamAdminBatch(TeamAdmin teamAdmin, Integer[] teamIds) {
		TeamAdmin ta = null;
		if (teamIds != null) {
			List<TeamAdmin> list = new ArrayList<TeamAdmin>();
			for (Integer teamId : teamIds) {
				ta = new TeamAdmin();
				ta.setSchoolId(teamAdmin.getSchoolId());
				ta.setGradeId(teamAdmin.getGradeId());
				ta.setTeamId(teamId);
				ta.setTeacherId(teamAdmin.getTeacherId());
				ta.setUserId(teamAdmin.getUserId());
				ta.setName(teamAdmin.getName());
				ta.setType(TYPE_PRAEPOSTOR);
				ta.setCreateDate(new Date());
				ta.setModifyDate(new Date());
				ta.setIsDeleted(false);
				list.add(ta);
			}
			if (list != null && list.size() > 0) {
				this.teamAdminDao.createTeamAdminBatch(list.toArray());
				Teacher teacher = new Teacher(teamAdmin.getTeacherId());
				teacher.setModifyDate(new Date());
				teacherService.modify(teacher);
			}
		}
	}

	@Override
	public void removeBatch(Integer teacherId, Integer[] teamIds) {
		this.teamAdminDao.updateTeamAdmin(teacherId, teamIds);
		Teacher teacher = new Teacher(teacherId);
		teacher.setModifyDate(new Date());
		teacherService.modify(teacher);
	}

	@Override
	public List<TeamAdmin> findIncrementByTeacherId(Integer schoolId, String schoolYear, Integer teacherId, Date modifyDate, Boolean isDeleted) {
		return this.teamAdminDao.findIncrementByTeacherId(schoolId, schoolYear, teacherId, modifyDate, isDeleted);
	}

	@Override
	public List<Integer> findFullConfigureTeams(Integer schoolId, String schoolYear, Integer count) {
		return this.teamAdminDao.findFullConfigureTeams(schoolId, schoolYear, count);
	}
}
