package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.ClassTeacher;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.vo.ClassTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeamTeacherDao extends GenericDao<TeamTeacher, java.lang.Integer> {

	List<TeamTeacher> findTeamTeacherByCondition(TeamTeacherCondition teamTeacherCondition, Page page, Order order);
	
	TeamTeacher findById(Integer id);
	
	List<TeamTeacher> findClassTeacherByGradeIdAndTeamId(Integer gradeId,Integer teamId);
	
	/**
	 * 获取班班任
	 * @param classTeacherCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<ClassTeacher> findClassTeacherByCondition(ClassTeacherCondition classTeacherCondition, Page page, Order order);

	List<TeamTeacherVo> findTeacherNumberOfSubjectDate(Integer schoolId);
	
	List<TeamTeacherVo> findTeamTeacherVoByCondition(TeamTeacherCondition teamTeacherCondition);
	
	List<TeamTeacherVo> findTeamTeacherVoByConditionGroupByTeamId(TeamTeacherCondition teamTeacherCondition);

	//根据条件获取对应教师所教班级和年级
	List<TeamTeacherVo> findTeamTeacherGradeByCondition(TeamTeacherCondition teamTeacherCondition);

	TeamTeacher findTeamTeacherByTeamIdAndSubjectCode(Integer teamId,String subjectCode);
	
	/**
	 * 功能描述：返回班级的班主任和任课老师名单
	 * 2015-12-08
	 * @param teamId
	 * @return
	 */
	List<TeamTeacher> findTeachersOfTeam(Integer teamId);
	
	/**
	 * 功能描述：
	 * (teacherId)取得一个教师在一所学校的班主任和任课情况（即所有的TeamTeacher记录）
	 * (teacherId、schoolYear)取得一个教师某个学年在一所学校的班主任和任课情况
	 * 2015-12-08
	 * @param teacherId
	 * @param schoolYear
	 * @return
	 */
	List<TeamTeacher> findMyTeamTeacher(Integer teacherId, String schoolYear);
	
	
	List<TeamTeacherVo> findTeamTeacherVoByGroupBy(TeamTeacherCondition teamTeacherCondition);

	Integer findTeamTeacherByTeamId(Integer teamId);
	
	List<TeamTeacher> findByTeamIdAndType(Integer teamId, Integer type);

	List<TeamTeacherVo> findVoWithSubjectInfo(TeamTeacherCondition teamTeacherCondition);

	List<TeamTeacher> findIncrementByTeacherId(Integer schoolId, String schoolYear, Integer teacherId, Date modifyDate, Boolean isDeleted);

	List<TeamTeacherVo> findTeacherTeachInfo(Integer userId, String schoolYear, Integer gradeId, Integer teamId);
	
	List<TeamTeacher> findTeamTeacherBySchoolId(Integer schoolId,Integer type,String schoolYear);
	
	List<TeamTeacher> findTeamTeacherByConditionIncludeDelete(TeamTeacherCondition teamTeacherCondition, Page page, Order order);

	List<TeamTeacherVo> findTeamOrGradeOfTeacher(Integer schoolId, String schoolYear, Integer userId, Integer teacherId, Integer type, String groupType);

	void deleteByTeacherId(Integer teacherId);

	List<Map<String, String>> findStuInfo(Integer teamId, Integer gradeId, Integer schoolId);

	List<Map<String,Object>> findNotSendSeewo();

	int updateSeewoStatus(Integer[] ids);
}
