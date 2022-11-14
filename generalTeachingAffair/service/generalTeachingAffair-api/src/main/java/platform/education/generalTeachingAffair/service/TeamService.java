package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.bo.TeamBo;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalTeachingAffair.vo.TeamEvaScoreData;
import platform.education.generalTeachingAffair.vo.TreeVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.TeamVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeamService {

	List<Team> findByIds(List<Integer> ids);
	
    Team findTeamById(Integer id);
	   
	Team add(Team team);
	   
	Team modify(Team team);
	   
	void remove(Team team);
	/**
	 * 返回年级的所有班级
	 * @param gradeId
	 * @return
	 */
	public List<Team> findTeamOfGrade(Integer gradeId);
	/**
	 * 通过学校ID和年级ID，得到班级列表
	 */
	public List<Team> findTeamOfGradeAndSchool(Integer gradeId,Integer schoolId);
	
	public List<Team> findTeamOfGradeAndSchool(Integer gradeId,Integer schoolId,Page page,Order order);
	/**
	 * 返回学校当前学年的所有班级
	 * @param schoolId
	 * @return
	 */
	public List<Team> findCurrentTeamOfSchool(Integer schoolId);
	/**
	 * 返回学校指定学年的所有班级
	 * @param schoolId
	 * @param year
	 * @return
	 */
	public List<Team> findCurrentTeamOfSchoolAndYear(Integer schoolId,String year);
	/**
	 * 通过班主任或任课教师获取班级
	 * @param teacherId
	 * @return
	 */
	public List<Team> findTeamByTeacher(Integer teacherId);
	/**
	 * 获取班主任或任课教师在指定学年所教的班级
	 * @param teacherId
	 * @param year
	 * @return
	 */
	public List<Team> findTeamByTeacher(Integer teacherId,String year);
	/**
	 * 获取班主任或任课教师当前正在教的班级
	 * @param teacherId
	 * @return
	 */
	public List<Team> findCurrentTeamByTeacher(Integer teacherId);
	
	public List<Team> findGradeByCode(String code);
	
	   
	public List<Team> findTeamByCondition(TeamCondition teamCondition, Page page, Order order);

	public List<Team> findCurrentTeamOfSchoolAndYearNotExistCurrentClass(Integer schoolId,String year,Integer teamId);

	/**
	 * 功能描述：学校学生年级统计 2015-11-05
	 * @return
	 */
	Long count();
	
	/**
	 * 功能描述：学校学生年级统计 2015-11-05
	 * @param teamCondition
	 * @return
	 */
	Long count(TeamCondition teamCondition);
	
	
	List<Team> findTeamOfGradeByAsc(Integer gradeId);
	
	//分班
	public void addPlacementStudent(String teamId,String studentId)throws Exception;
	
	//自动分班
	public void autoPlacementStudent(String teamId,String studentId)throws Exception;
	
	//=============调班===========
	public void addOrUpdateStudent(String teamId0,String studentId0,String teamId1,String studentId1) throws Exception;

	List<Team> findByIds(Integer[] ids);

	/**
	 * 获取老师任职班主任的班级
	 * @param teacherId
	 * @return
	 */
	public List<Team> findCurrentTeamByClassTeacher(Integer teacherId);
	
	Team findTeamByCode(String code);
	
	/**
	 * 获取行政班级(类型为行政班或为空的)
	 */
	public List<Team> findAdministrativeTeam(Integer gradeId, Integer schoolId);
	
	/**
	 * 通过学生的userId 获取所选学年所在的班级 （针对学生升级后学年未切换的情况）
	 * @param teamId	student表中team_id
	 * @param schoolYear	当前学年
	 * @return
	 */
	public Team findCurrentTeamOfStudent(Integer studentUserId, String schoolYear);
	
	/**
	 * 通过学生的teamId 获取当前学年的所在的班级 （多一步去查询当前学年）
	 * @param teamId
	 * @return
	 */
	public Team findCurrentTeamOfStudent(Integer studentUserId, Integer schoolId);

	/**
	 * 获取学校学年班级（可增量）
	 * @param isGetNew	新增/修改
	 * @param isOrder	按修改时间/默认排序
	 * @return
	 */
	List<TeamVo> findIncrementByModifyDate(Integer schoolId, String schoolYear, Boolean isDeleted, Date modifyDate, Integer teamId, Boolean isGetNew, Boolean isOrder);

	/**
	 * 根据学校ID找出所有学年，年级，班级的组织关系
	 * @param schoolId
	 * @return
	 */
	public List<TreeVo> findAllTeamTreeBySchoolId(Integer schoolId);

	/**
	 * 根据学校ID和用户ID找出对应任教教师和班主任的学年，年级，班级的组织关系
	 * @param schoolId
	 * @param userId
	 * @return
	 */
	public List<TreeVo> findClassTeamTreeBySchoolId(Integer schoolId,Integer userId);

	public List<Team> findByIdsOfOrder(Integer[] ids,Order order);

	/**
	 * 根据年级获取班级（班内的学生数不为0）
	 * @param gradeId
	 * @return
	 */
	List<Team> findNotEmptyTeam(Integer gradeId);


	/**
	 * 获取某学年下的所有班级
	 * @param schoolId
	 * @param schoolYear
	 * @return	List<Map<String, Object>> 为多个年级的集合，每个年级包含多个班级
	 */
	List<Object> findAllTeamOfSchool(Integer schoolId, String schoolYear);

	/**
	 * 根据学校id年级id和班级名字查询班级
	 * @param schoolId 学校id
	 * @param gradeId 年级id
	 * @param teamName 班级名称
	 * @param gradeName 年级名称
	 * @return
	 */
	Team findBySchoolGradeIdAndName(Integer schoolId, Integer gradeId, String gradeName, String teamName, String schoolYear);

	/**
	 * 根据学校id年级id和班级名字添加班级
	 * @param schoolId
	 * @param gradeId
	 * @param gradeName
	 * @param teamName
	 * @return
	 */
	Team addTeamUseSchoolGradeIdAndName(Integer schoolId, Integer gradeId, String gradeName, String teamName, String schoolYear);

	void deleteAllInfoById(Integer teamId, String studentType, String parentType);

	boolean updateAsSendSeewo(List<Team> teams);

	boolean updateAsSendSeewoByIds(Integer[] ids);

	List<Team> findNotSendSeewo();

	List<TeamBo> findBoNotSendSeewo();

	List<Map<String,Object>> getNotSendSendSeewo();

}
