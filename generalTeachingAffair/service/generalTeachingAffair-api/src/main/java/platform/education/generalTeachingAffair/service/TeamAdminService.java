package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.TeamAdmin;
import platform.education.generalTeachingAffair.vo.TeamAdminCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.TeamAdminVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeamAdminService {
    TeamAdmin findTeamAdminById(Integer id);
	   
	TeamAdmin add(TeamAdmin teamAdmin);
	   
	TeamAdmin modify(TeamAdmin teamAdmin);
	   
	void remove(TeamAdmin teamAdmin);
	   
	List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition, Page page, Order order);
	
	List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition);
	
	List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition, Page page);
	
	List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition, Order order);
	
	Long count();
	
	Long count(TeamAdminCondition teamAdminCondition);

	List<TeamAdmin> findByTeamIds(Integer[] teamIds);

	List<TeamAdminVo> findByGradeIdAndTeamId(Integer gradeId, Integer teamId);

	List<TeamAdminVo> findByTeacherId(Integer schoolId, String schoolYear, Integer teacherId);

	/** 批量添加（一个年级多个班级）*/
	void addTeamAdminBatch(TeamAdmin teamAdmin, Integer[] teamIds);
	/** 批量删除（逻辑删除） */
	void removeBatch(Integer teacherId, Integer[] teamIds);

	/** 获取某个时间后的信息，用于同步教师信息 */
	List<TeamAdmin> findIncrementByTeacherId(Integer schoolId, String schoolYear, Integer teacherId, Date modifyDate, Boolean isDeleted);

	/**
	 *	班内级长人数超过5人的班级
	 */
	List<Integer> findFullConfigureTeams(Integer schoolId, String schoolYear, Integer count);
}
