package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.bo.TeamBo;
import platform.education.generalTeachingAffair.model.HikTeamRequestVo;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamStructure;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;
import platform.education.generalTeachingAffair.vo.TeamVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeamDao extends GenericDao<Team, java.lang.Integer> {

	public List<Team> findTeamByCondition(TeamCondition condition);

	public List<Team> findTeamByCondition(TeamCondition teamCondition, Page page, Order order);

	public List<Team> findTeamOrderByIdByCondition(TeamCondition teamCondition, Page page, Order order);


	public Team findById(Integer id);

	public List<Team> findTeamOfGrade(Integer gradeId);

	public List<Team> findTeamOfGrade(Integer gradeId,Integer schoolId);

	public List<Team> findTeamOfGrade(Integer gradeId,Integer schoolId,Page page,Order order);

	public List<Team> findCurrentTeamOfSchool(Integer schoolId);

	public List<Team> findTeamByTeacherAndYear(Integer teacherId,String year);

	public List<Team> findCurrentTeamByTeacher(Integer teacherId);

	public List<Team> findTeamByTeacher(Integer teacherId);

	public List<Team> findCurrentTeamBySchoolIdAndYear(Integer schoolId,String year);

	public List<Team> findGradeByCode(String code);

	public List<Team> findCurrentTeamOfSchoolAndYearNotExistCurrentClass(Integer schoolId,String year,Integer teamId);

	/**
	 * 功能描述：学生年级统计 2015-11-05
	 * @param teamCondition
	 * @return
	 */
	Long count(TeamCondition teamCondition);

	public List<Team> findTeamOfGradeByAsc(Integer gradeId,Integer schoolId);

	List<Team> findByIds(Integer[] ids,Order order);

	public List<Team> findCurrentTeamByClassTeacher(Integer teacherId);

	public Team findTeamByCode(String code);

	public List<Team> findAdministrativeTeam(Integer gradeId, Integer schoolId);

	public Team findCurrentTeamOfStudent(Integer studentUserId, String schoolYear);

	List<TeamVo> findIncrementByModifyDate(Integer schoolId, String schoolYear, Boolean isDeleted, Date modifyDate, Integer teamId, Boolean isGetNew, Boolean isOrder);

	public List<TeamStructure> findAllTeamTreeBySchoolId(Integer schoolId);

	public List<TeamStructure>  findClassTeamTreeBySchoolId(Integer schoolId,Integer userId);

	List<Team> findNotEmptyTeam(Integer gradeId);

	List<Team> findByIsSendSeewo(Integer isSendSeewo);

	List<TeamBo> findBoByIsSendSeewo(Integer isSendSeewo);

	Integer updateAsSendSeewoByIds(Integer[] ids);

	List<Map<String, Object>> findNotSendSendSeewo();


	List<HikTeamRequestVo> findNotSendHik(Integer schoolIds,String schoolYear);


}
