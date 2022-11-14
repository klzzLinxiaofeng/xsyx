package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.TeamAdmin;
import platform.education.generalTeachingAffair.vo.TeamAdminCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.TeamAdminVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeamAdminDao extends GenericDao<TeamAdmin, Integer> {

	List<TeamAdmin> findTeamAdminByCondition(TeamAdminCondition teamAdminCondition, Page page, Order order);
	
	TeamAdmin findById(Integer id);
	
	Long count(TeamAdminCondition teamAdminCondition);

	List<TeamAdmin> findByTeamIds(Integer[] teamIds);

	List<TeamAdminVo> findByGradeIdAndTeamId(Integer gradeId, Integer teamId);

	List<TeamAdminVo> findByTeacherId(Integer schoolId, String schoolYear, Integer teacherId);

	void createTeamAdminBatch(Object[] array);

	void updateTeamAdmin(Integer teacherId, Integer[] teamIds);

	List<TeamAdmin> findIncrementByTeacherId(Integer schoolId, String schoolYear, Integer teacherId, Date modifyDate, Boolean isDeleted);

	List<Integer> findFullConfigureTeams(Integer schoolId, String schoolYear, Integer count);
}
