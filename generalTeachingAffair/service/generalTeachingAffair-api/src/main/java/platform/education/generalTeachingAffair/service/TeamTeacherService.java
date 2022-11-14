package platform.education.generalTeachingAffair.service;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.ClassTeacher;
import platform.education.generalTeachingAffair.model.TeamTeacher;
import platform.education.generalTeachingAffair.vo.ClassTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeamTeacherService {
	
	/**
	 * 当前接口crud操作 成功时返回的状态值
	 */
	public final static String OPERATE_SUCCESS = "success";
	
	/**
	 * 当前接口crud操作 失败时返回的状态值
	 */
	public final static String OPERATE_FAIL = "fail";
	
	/**
	 * 系统异常造成的操作失败 系统返回的状态值
	 */
	public final static String OPERATE_ERROR = "error";
	
    TeamTeacher findTeamTeacherById(Integer id);
	   
	TeamTeacher add(TeamTeacher teamTeacher);
	   
	TeamTeacher modify(TeamTeacher teamTeacher);
	   
	void remove(TeamTeacher teamTeacher);
	   
	List<TeamTeacher> findTeamTeacherByCondition(TeamTeacherCondition teamTeacherCondition, Page page, Order order);
	
	List<TeamTeacher> findClassTeacherByGradeIdAndTeamId(Integer gradeId,Integer teamId);
	
	/**
	 * 获取班主任列表
	 * @param classTeacherCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<ClassTeacher> findClassTeacherByCondition(ClassTeacherCondition classTeacherCondition, Page page, Order order);

	List<TeamTeacherVo> findTeacherNumberOfSubjectBySchoolId(Integer schoolId);
	
	/**
	 * 获取教师教哪些班级
	 * @param teamTeacherCondition
	 * @return
	 */
	List<TeamTeacherVo> findTeamTeacherVoByCondition(TeamTeacherCondition teamTeacherCondition);
	/**
	 * 获取教师教哪些班级,班级唯一
	 * @param teamTeacherCondition
	 * @return
	 */
	List<TeamTeacherVo> findTeamTeacherVoByConditionGroupByTeamId(TeamTeacherCondition teamTeacherCondition);

	/**
	 * 根据条件获取对应教师所教班级和年级
	 * 
	 * @param teamTeacherCondition
	 * @return
	 */
	List<TeamTeacherVo> findTeamTeacherGradeByCondition(TeamTeacherCondition teamTeacherCondition);

	/**
	 * 根据班级Id和科目code获取teamteacher
	 * @param teamId
	 * @param subjectCode
	 * @return teamteacher
	 */
	TeamTeacher findTeamTeacherByTeamIdAndSubjectCode(Integer teamId,String subjectCode);
	
	/**
	 * @function 指派任课教师
	 * @param appId
	 * @param groupId
	 * @param teamTeacher
	 * @return success  OR error
	 */
	public String addOrModifyClassRoomTeacherOfTeam(Integer appId,Integer groupId,TeamTeacher teamTeacher);

	/**
	 * @function 指派班主任
	 * @param appId
	 * @param groupId
	 * @param gradeId
	 * @param teamId
	 * @param teacherId
	 * @return success  OR error
	 */
	public String  addOrModifyClassTeacher(Integer appId,Integer groupId,String gradeId,String teamId,String teacherId);
	
	/**
	 * 功能描述：返回班级的班主任和任课老师名单
	 * 2015-12-08
	 * @param teamId
	 * @return
	 */
	List<TeamTeacher> getTeachersOfTeam(Integer teamId);
	
	/**
	 * 功能描述：取得一个教师在一所学校的班主任和任课情况（即所有的TeamTeacher记录）
	 * 2015-12-08
	 * @param teacherId
	 * @return
	 */
	List<TeamTeacher> getMyTeamTeacher(Integer teacherId);
	
	/**
	 * 功能描述：取得一个教师某个学年在一所学校的班主任和任课情况
	 * 2015-12-08
	 * @param teacherId
	 * @param schoolId
	 * @param schoolYear
	 * @return
	 */
	List<TeamTeacher> getMyTeamTeacher(Integer teacherId, String schoolYear);
	
	/**
	 * 功能描述：取得一个教师当前学年在一所学校的班主任和任课情况
	 * 2015-12-08
	 * @param teacherId
	 * @param schoolId
	 * @return
	 */
	List<TeamTeacher> getMyCurrentTeamTeacher(Integer teacherId, Integer schoolId);
	
	/**
	 * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识
	 * 关联移除teamUser记录、userRole记录
	 * @param teamTeacher
	 * @return
	 * 2016-01-28
	 */
	String removeMasterFromTeam(TeamTeacher teamTeacher,Integer appId, Integer groupId, Integer schoolId);

	/**
	 *	移除科任教师
	 */
	String removeClassTeacherFromTeam(TeamTeacher teamTeacher,Integer appId, Integer groupId, Integer schoolId);
	/**
	 * 获取教师教的班级
	 * @param teamTeacherCondition
	 * @return
	 */
	List<TeamTeacherVo> findTeamTeacherVoByGroupBy(TeamTeacherCondition teamTeacherCondition);


	/**
	 * 统计一个班的老师数
	 * @param teamId
	 * @return
	 */
	Integer findTeamTeacherByTeamId(Integer teamId);
	
	/**
	 * 获取某班级的所有教师
	 * @param teamId
	 * @param typeMaster 1=班主任  2=科任教师  null=全部
	 * @return
	 */
	List<TeamTeacher> findByTeamIdAndType(Integer teamId, Integer type);

	/**
	 * 	获取教师所教的班级和科目信息
	 * @param teamTeacherCondition
	 * @return
	 */
	List<TeamTeacherVo> findVoWithSubjectInfo(TeamTeacherCondition teamTeacherCondition);

	/**
	 * 根据最后的修改时间获取班级教师数据
	 * @param schoolId
	 * @param schoolYear
	 * @param teacherId
	 * @param modifyDate
	 * @param isDeleted
	 * @return
	 */
	List<TeamTeacher> findIncrementByTeacherId(Integer schoolId, String schoolYear, Integer teacherId, Date modifyDate, Boolean isDeleted);

	/**
	 * 获取教师任教年级
	 * @param userId 用户id
	 * @param schoolYear 学年
	 * @param schoolId
	 * @return
	 */
	Map<Integer, Object> getTeacherTeachGrade(Integer userId, String schoolYear, Integer schoolId);

	/**
	 * 获取教师任教班级
	 * @param userId 用户id
	 * @param schoolYear 学年
	 * @param gradeId 年级id
	 * @return
	 */
	Map<Integer, Object> getTeacherTeachTeam(Integer userId, String schoolYear, Integer gradeId);

	/**
	 *  获取教师任教科目
	 * @param userId 用户id
	 * @param schoolYear 学年
	 * @param gradeId 年级id
	 * @param teamId 班级id
	 * @return
	 */
	Map<String, Object> getTeacherTeachSubject(Integer userId, String schoolYear, Integer gradeId, Integer teamId);
     
	 /**
	  * 找到某学年这种类型的所有老师 
	  * @param schoolId
	  * @param type  1为班主任，2为科任教师
	  * @param schoolYear  学年
	  * @return
	  */
	List<TeamTeacher> findTeamTeacherBySchoolId(Integer schoolId,Integer type,String schoolYear);
/**
	 * 已经删除的那些也能拿到
	 * @param teamTeacherCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<TeamTeacher> findTeamTeacherByConditionIncludeDelete(TeamTeacherCondition teamTeacherCondition, Page page, Order order);

/**
	 * 功能描述：获取教师所教的年级或班级
	 * userId和teacherId可只传其中一个
	 * type 1=班主任 2=科任教师
	 * groupType 1=按年级分组 2=按班级分组
	 */
	List<TeamTeacherVo> findTeamOrGradeOfTeacher(Integer schoolId, String schoolYear, Integer userId, Integer teacherId, Integer type, String groupType);
	
	
	/**
	 * 功能描述：逻辑上删除班级的科任教师或班主任
	 * 关联移除teamUser记录、userRole记录
	 * 2017-3-8
	 */
	String removeTeamTeacherFromTeam(TeamTeacher teamTeacher,Integer appId, Integer groupId, Integer schoolId);

	/**
	 * 根据老师id删除记录
	 * @param id
	 */
	void removeByTeacherId(Integer id);


	Map<String, Object> addTeamTeacherFromExcelImport(String gradeName, String teamNumber, String name, String subjectName, Integer type, Integer schoolId, String schoolYear, Integer groupId, Integer appId);


	/**
	 * 获取学生的接送信息
	 *
	 * @param teamId
	 * @param gradeId
	 * @param schoolId
	 * @return
	 */
	List<Map<String, String>> findStuInfo(Integer teamId, Integer gradeId, Integer schoolId);

	List<Map<String,Object>> findNotSendSeewo();
	boolean updateSeewoStatus(Integer[] ids);

	/**
	 * 是否班主任
	 * @param userId
	 * @return
	 */
	boolean userIsTeamManager(Integer userId);
}

