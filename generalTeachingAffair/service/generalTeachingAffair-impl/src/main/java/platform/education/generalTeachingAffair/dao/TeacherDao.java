package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.model.Group;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeacherDao extends GenericDao<Teacher, java.lang.Integer> {

	List<Teacher> findTeacherByCondition(TeacherCondition teacherCondition);
	
	List<Teacher> findTeacherByCondition(TeacherCondition teacherCondition, Page page, Order order);

	Teacher findById(Integer id);

	Teacher findByName(String name);

	public List<Teacher> findTeacherOfSchool(Integer schoolId);

	List<Teacher> findNotSendSeewo();
	List<Teacher> findCanSendSeewo();

	public List<Teacher> findActiveTeacherOfSchool(Integer schoolId);

	public List<Teacher> findMasterOfTeam(Integer teamId);

	public List<Teacher> findSubjectTeacherOfTeam(Integer teamId);
	/**
	* @deprecated
	* dao里没对应实现
	*/
	public List<Teacher> findTeacherOfTeam(Integer teamId);

	List<TeacherApiVo> findTeacherApiVoByCondition(TeacherCondition teacherCondition, Page page, Order order);


	List<TeacherVo> findTeacherVoByCondtion(TeacherCondition condition, Page page, Order order);
	List<TeacherVo> findTeacherVoByGroupCondtion(TeacherCondition condition, Page page, Order order);

	Teacher findOfUser(Integer schoolId, Integer userId);

	Integer findUnqiueTeacherId(Integer userId, Integer schoolId);

	public List<Teacher> findTeacherByNameAndSchool(String name,Integer schoolId);

	public List<Teacher> findTeacherListByTeamId(Integer teamId);

	public List<Teacher> findTeacherListByTeamId(Integer teamId,Order order);

	public List<Teacher> findTeacherListByTeamId(Integer teamId,Page page,Order order);

	public List<Teacher> findTeacherListByUserName(String userName);

	/**
	 * 功能描述：查询出教师相关信息
	 * @param condition
	 * @param page
	 * @param order
	 * @return
	 */
	List<TeacherVo> findTeacherVoByConditionMore(TeacherCondition condition, Page page, Order order);

	List<Teacher> findTeacherBySchoolRegion(SchoolCondition condition, Group group, Page page, Order order);

	public Teacher findTeacherNumBySchoolId(Integer schoolId);

	public List<Teacher> findTeacherOfTeamIdList(String[] arr,Integer schoolId);

	public List<Teacher> findTeacherOfGradeIdList(String[] arr,Integer schoolId);

	public List<Teacher> findTeacherListBySchoolId(Integer schoolId);
	
	public List<Teacher> findTeacherListBySchoolId(Integer schoolId, Page page);

	TeacherVo findTeamTeacherNumberOfSexData(Integer schoolId);

	TeacherVo findTeamTeacherNumberOfData(Integer schoolId,String politicalStatus,String postStaffing,String qualification,Boolean isExternal);

	List<TeacherVo> findTeacherNumberOfStationData(Integer schoolId,String tableCode);

	List<TeacherVo> findTeacherNumberOfQualificationData(Integer schoolId,String tableCode);

	List<TeacherVo> findTeacherNumberOfAgeData(Integer schoolId);

	TeacherVo findTeacherNumberOfPoliticsData(Integer schoolId);

	List<TeacherVo> findNoDepartmentTeacherVoByCondition(TeacherCondition tCondition, Page page, Order order);

	/**
	 * 功能描述：返回班主任
	 * 2015-12-09
	 * @param teamId
	 * @return
	 */
	List<Teacher> findMastersOfTeam(Integer type, Integer teamId);

	/**
	 * 功能描述：
	 * (teamId)返回班级任课老师
	 * (teamId、subjectCode)返回班级指定科目的任课老师
	 * 2015-12-09
	 * @param teamId
	 * @param subjectCode
	 * @return
	 */
	List<Teacher> findSubjectTeachersOfTeam(Integer type, Integer teamId, String subjectCode);

	List<Teacher> findGradeOfTeacher(Integer gradeId, String schoolYear,Boolean isGetAll,Integer schoolId);

	/**
	 * @function 根据教师userId和学校ID获取教师的详细信息
	 * @param userId
	 * @param schoolId
	 * @date 2017-1-9
	 * @return
	 */
	TeacherDetailInfo findTeacherDetailInfoByUserId(Integer userId,Integer schoolId);

	/**
	 * 根据部门获取教师信息
	 * @param departmentId
	 * @return
	 */
	List<TeacherMessVo> findTeacherVoByDeptId(Integer departmentId);


	/**
	 * 获取学校的教师信息（可根据最后修改时间增量获取）
	 * @param schoolId
	 * @param isDeleted
	 * @param modifyDate
	 * @param teacherId
	 * @param isGetNew	新增/修改
	 * @return
	 */
	List<TeacherVo> findIncrementDataByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer teacherId, Boolean isGetNew);


	//-----------------教师统计  start-----------------------
	/**
	 * 教师年级人数
	 */
	List<StatisticDate> findNumberOfGradeBySchoolId(Integer schoolId, String schoolYear);

	/**
	 * 工资
	 */
	List<StatisticDate> findSalaryBySchoolId(Integer schoolId, String year, String month);

	/**
	 * 岗位编制
	 */
	List<StatisticDate> findPostStaffingBySchoolId(Integer schoolId);

	/**
	 * 学位
	 */
	List<StatisticDate> findQualificationBySchoolId(Integer schoolId);

	/**
	 * 证件类型
	 */
	List<StatisticDate> findIdCardTypeBySchoolId(Integer schoolId);

	/**
	 * 婚姻状态
	 */
	List<StatisticDate> findMarriageBySchoolId(Integer schoolId);

	/**
	 * 在职状态
	 */
	List<StatisticDate> findJobStateBySchoolId(Integer schoolId);

	/**
	 * 男女比例
	 */
	List<StatisticDate> findSexBySchoolId(Integer schoolId);

	/**
	 * 年龄
	 */
	List<StatisticDate> findAgeBySchoolId(Integer schoolId);

	/**
	 * 政治面貌
	 */
	List<StatisticDate> findPoliticalStatusBySchoolId(Integer schoolId);

	/**
	 * 民族
	 */
	List<StatisticDate> findRaceBySchoolId(Integer schoolId);

	/**
	 * 户口性质
	 */
	List<StatisticDate> findResidenceTypeBySchoolId(Integer schoolId);



	/**
	 * 教师学校人数
	 */
	List<StatisticDate> findNumberOfSchoolByAreaCode(String areaCode);

	/**
	 * 工资
	 */
	List<StatisticDate> findSalaryByAreaCode(String areaCode, String year, String month);

	/**
	 * 岗位编制
	 */
	List<StatisticDate> findPostStaffingByAreaCode(String areaCode);

	/**
	 * 学位
	 */
	List<StatisticDate> findQualificationByAreaCode(String areaCode);

	/**
	 * 证件类型
	 */
	List<StatisticDate> findIdCardTypeByAreaCode(String areaCode);

	/**
	 * 婚姻状态
	 */
	List<StatisticDate> findMarriageByAreaCode(String areaCode);

	/**
	 * 在职状态
	 */
	List<StatisticDate> findJobStateByAreaCode(String areaCode);

	/**
	 * 男女比例
	 */
	List<StatisticDate> findSexByAreaCode(String areaCode);

	/**
	 * 年龄
	 */
	List<StatisticDate> findAgeByAreaCode(String areaCode);

	/**
	 * 政治面貌
	 */
	List<StatisticDate> findPoliticalStatusByAreaCode(String areaCode);

	/**
	 * 民族
	 */
	List<StatisticDate> findRaceByAreaCode(String areaCode);

	/**
	 * 户口性质
	 */
	List<StatisticDate> findResidenceTypeByAreaCode(String areaCode);
	//-----------------教师统计  end-----------------------

	List<TeacherVo> findAllSubjectTeachersByTeamId(Integer teamId);

	List<Teacher> findTeacherByLikeNameAndSchool(String name, Integer schoolId, Page page, Order order);

	List<Teacher> findTeacherByNoSend(Teacher teacher);

	int updateByIds(Teacher teacher,Integer[] ids);

    void updateTeacherSendCanteen(Teacher teacher);

    List<Teacher> findTeacherByNoSendCanteen(Teacher teacherVo);

    List<HikvisionUserPo> findTeacherSendHik(Page page, Order order);

	void updateHikvisionUserInfo(Integer[] arrayBindCard, Integer sendCards, Integer bindCards);

	void updateHikvisionUserInfoPerson(String[] arrayBindCard, Integer sendCards, Integer bindCards);
	void updateHikvisionUserInfoPersonAdd(Integer arrayBindCard, Integer sendCards, Integer bindCards,String persionId);

	List<CardList> findHikvisionBindCarTeacher(Page page, Order order);

    List<UntieCardList> findUntieHikvisionBindCarTeacher();

    List<Map<String, Object>> findStuByTeacherId(Integer schoolId, Integer teacherId, String schoolYear);

    List<Teacher> findByTeacherShitang();
	List<TeacherVo> findByUser(String name, Integer state, String userName, String modbie, Page page);
}
