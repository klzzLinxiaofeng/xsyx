package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.model.Group;
import platform.education.user.model.Profile;
import platform.education.user.model.Role;
import platform.education.user.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TeacherService {

	/**
	 * @Method findTeacherById
	 * @Function 功能描述：此函数只能拿到未作废且指定ID的teacher对象
	 * @param id
	 * @return
	 * @Date 2015年6月29日
	 */
	Teacher findTeacherById(Integer id);

	/**
	 * @Method getTeacherById
	 * @Function 功能描述：此函数可拿到整个数据库中指定ID的teacher对象，包括已作废的
	 * @param id
	 * @return
	 * @Date 2015年6月29日
	 */
	Teacher getTeacherById(Integer id);

	Teacher findTeacherByName(String name);

	Teacher add(Teacher teacher);

	Teacher modify(Teacher teacher);

	void remove(Teacher teacher);

	/**
	 * 获取学校下所有有教工记录
	 * 
	 * @param schoolId
	 * @return
	 */
	public List<Teacher> getTeacherOfSchool(Integer schoolId);

	/**
	 * 获取学校的所在在职教工记录
	 * 
	 * @param schoolId
	 * @return
	 */
	public List<Teacher> findActiveTeacherOfSchool(Integer schoolId);

	/**
	 * 获取班主任
	 * 
	 * @param teamId
	 * @return
	 */
	public List<Teacher> getMasterOfTeam(Integer teamId);

	/**
	 * 返回班级任课教师
	 * 
	 * @param teamId
	 * @return
	 */
	public List<Teacher> getSubjectTeacherOfTeam(Integer teamId);

	List<Teacher> findTeacherByCondition(TeacherCondition teacherCondition, Page page, Order order);

	List<TeacherVo> findTeacherVoByCondition(TeacherCondition condition, Page page, Order order);

	List<TeacherVo> findTeacherVoByGroupCondtion(TeacherCondition condition, Page page, Order order);

	Teacher findOfUser(Integer schoolId, Integer userId);

	Integer findUnqiueTeacherId(Integer userId, Integer schoolId);

	List<Teacher> findTeacherByNameAndSchool(String name, Integer schoolId);

	/**
	 * 根据班级ID 获取 教师姓名，头像，用户名，电话，教师类型：1：班主任，2：任课老师，任教科目，班级ID，学校ID。
	 * 
	 * @param teamId
	 * @return
	 */
	public List<Teacher> findTeacherListByTeamId(Integer teamId);
	
	public List<Teacher> findTeacherListByTeamId(Integer teamId,Order order);
	
	public List<Teacher> findTeacherListByTeamId(Integer teamId,Page page,Order order);

	/**
	 * 根据老师帐号ID获取该老师任教的班级信息列表接口
	 * 
	 * 班级ID，班级名称，该教师在该班级担任的职务例如：班主任/语文老师/数学老师
	 */
	public List<Teacher> findTeacherListByUserName(String userName);

	// 保存教师信息---此方法没涉及档案信息（最小化保存）
	/**
	 * @param teacher
	 *            教师信息
	 * @param user
	 *            用户信息
	 * @param role
	 *            角色信息
	 * @param groupId
	 *            组ID
	 * @param appId
	 *            应用ID
	 * @param regionCode
	 *            区域CODE
	 * @param level
	 *            区域级别
	 * @param createUserId
	 *            当前操作用户ID
	 * @return
	 */
	public Teacher addTeacher(Teacher teacher, User user, Role role, Integer groupId, Integer appId, String regionCode, String level, Integer createUserId);

	// 保存教师信息---此方法没涉及档案信息、个人信息
	/**
	 * @param teacher
	 *            教师信息
	 * @param user
	 *            用户信息
	 * @param role
	 *            角色信息
	 * @param profile
	 *            个人信息
	 * @param groupId
	 *            组ID
	 * @param appId
	 *            应用ID
	 * @param regionCode
	 *            区域CODE
	 * @param level
	 *            区域级别
	 * @param createUserId
	 *            当前操作用户ID
	 * @return
	 */
	public Teacher addTeacher(Teacher teacher, User user, Role role, Profile profile, Integer groupId, Integer appId, String regionCode, String level, Integer createUserId);

	/**
	 * 功能描述：查询出教师相关信息
	 * 
	 * @param condition
	 * @param page
	 * @param order
	 * @return
	 */
	List<TeacherVo> findTeacherVoByConditionMore(TeacherCondition condition, Page page, Order order);

	/**
	 * @Method findSchoolInfoByCondition
	 * @Function 功能描述：根据学校、组查询出所有教师
	 * @param condition
	 * @param group
	 * @param page
	 * @param order
	 * @return
	 * @Date 2015年4月27日
	 */
	public List<Teacher> findTeacherBySchoolRegion(SchoolCondition condition, Group group, Page page, Order order);

	/***
	 * 获取当前学校下所有的教师人数
	 * 
	 * @param schoolId
	 * @return
	 */
	public String findTeacherNumBySchoolId(Integer schoolId);

	/**
	 * 获取当前学校所有教师人数和学生人数 总和
	 * 
	 * @param schoolId
	 * @return
	 */
	public String findTeacherNumAndStudentNumBySchoolId(Integer schoolId);

	// ==========================此接口是提供OA的=================================
	/***
	 * 
	 * @param idList
	 * @param type
	 * @param schoolId
	 * @return
	 */
	public List<Teacher> findTeacherListByListId(List<Integer> idList, String type, Integer schoolId);

	/***
	 * 通过学校ID获取当前学校下所有教师
	 * 
	 * @param schoolId
	 * @return
	 */
	public List<Teacher> findTeacherListBySchoolId(Integer schoolId);

	/**
	 * 根据学校获取教师的男女比例
	 * @param schoolId
	 * @return
	 */
	TeacherVo findTeamTeacherNumberOfSexData(Integer schoolId);

	List<TeacherVo> findTeacherNumberOfStationData(Integer schoolId,String tableCode);
	
	List<TeacherVo> findTeacherNumberOfQualificationData(Integer schoolId,String tableCode);

	List<TeacherVo> findTeacherNumberOfAgeData(Integer schoolId);

	TeacherVo findTeacherNumberOfPoliticsData(Integer schoolId);

	List<TeacherVo> findNoDepartmentTeacherVoByCondition(
			TeacherCondition tCondition, Page page, Order order);
	
	/**
	 * 功能描述：返回班主任
	 * 2015-12-09
	 * @param teamId
	 * @return
	 */
	List<Teacher> getMastersOfTeam(Integer teamId);
	
	/**
	 * 功能描述：返回班级任课老师
	 * 2015-12-09
	 * @param teamId
	 * @return
	 */
	List<Teacher> getSubjectTeachersOfTeam(Integer teamId);
	
	/**
	 * 功能描述：返回班级指定科目的任课老师
	 * 2015-12-09
	 * @param teamId
	 * @param subjectCode
	 * @return
	 */
	List<Teacher> getSubjectTeachersOfTeam(Integer teamId, String subjectCode);
	
	/**
	 * 功能描述：返回指定学历的教师性别人数
	 * @param school
	 * @param qualification
	 * @return
	 */
	public TeacherVo findTeamTeacherNumberOfQualData(Integer school,String qualification);
	
	/**
	 * 功能描述：返回指定政治面貌的教师性别人数
	 * @param school
	 * @param occupationCode
	 * @return
	 */
	public TeacherVo findTeamTeacherNumberOfPoliticalStatusData(Integer school,String politicalStatus);
	/**
	 * 功能描述：返回指定岗位的教师性别人数
	 * @param school
	 * @param postStaffing
	 * @return
	 */
	public TeacherVo findTeamTeacherNumberOfPoststaffingData(Integer school,String postStaffing);
	/**
	 * 功能描述：返回是否外聘的教师性别人数
	 * @param school
	 * @param isExternal
	 * @return
	 */
	public TeacherVo findTeamTeacherNumberOfExternalData(Integer school,Boolean isExternal);
	
	/**
	 * @function 根据学年、年级批量获取教师
	 * @param gradeIds
	 * @param schoolYear
	 * @date 2016-12-9
	 * @author panfei
	 * @return
	 */
	public List<Teacher> findGradeOfTeacher(Integer gradeId,String schoolYear,Boolean isGetAll);
	
	/**
	 * @function 根据用户ID和学校ID获取教师的详细信息
	 * 			 目前获取教师详细信息 都是靠拼接数据，速度太慢
	 * @param userId
	 * @param schoolId
	 * @date 2017-1-9
	 * @return
	 */
	public TeacherDetailInfo findTeacherDetailInfoByUserId(Integer userId,Integer schoolId);
	
	
	public TeacherDetailInfoVo findTeacherDetailInfoById(Integer id);
	
	public void updateTeacherDetailInforById(Integer id) throws Exception;
	
	public void saveUserInfoList(List<UserDetailInfoVo> successMeaageVoList,String password,Integer appId) throws Exception;
	
	public void saveImportUserInfo(UserDetailInfoVo userDetailInfoVo,String password,Integer appId) throws Exception;
	
	public UserDetailInfo findUserDetailInfoById(String id) throws Exception;
	
	public List<UserDetailInfo> findAllUserDetailInfo(Integer schoolId) throws Exception;
	
	public void updateUserDetailInforById(Integer id) throws Exception;
	
	public TeacherDetailInfo addTeacherInfo(TeacherDetailInfo teacherDetailInfo, Integer appId ,String password,String userType) throws Exception;
	
	public Teacher modifyTeacherInfo(TeacherDetailInfo teacherDetailInfo);
	
	/**
	 * 功能描述：新增教师
	 * 2015-11-20
	 * @param teacherDetailInfo
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public TeacherDetailInfo addInfoForTeacher(TeacherDetailInfo teacherDetailInfo,TeacherAssist teacherAssist ,String password,Integer appId,String userType) throws Exception;
	
	/**
	 * 功能描述：修改教师
	 * 2015-11-20
	 * @param teacherDetailInfo
	 * @return
	 */
	public TeacherDetailInfo modifyInfoForTeacher(TeacherDetailInfo teacherDetailInfo);
	
	public void saveTeacherInfoList(List<TeacherDetailInfoVo> successMeaageVoList,String password,Integer appId) throws Exception;
	
	public List<TeacherDetailInfo> findAllTeacherDetailInfo(Integer schoolId) throws Exception;
	
	/**
	 * 根据查询条件导出教师数据
	 * @param teacherCondition
	 * @return
	 * @throws Exception
	 */
	List<TeacherDetailInfo> findTeacherDetailInfoByCondition(
			TeacherCondition teacherCondition) throws Exception;
	
	/**
	 * 功能描述：客户端教师导入
	 * 2016-1-27
	 * @param teacherDetailInfo
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public ExtImportTeacherErrorMsg ImportInfoForTeacher(ExtImportTeacherVo extImportTeacherVo, Integer appId,Boolean autoCreateDept,Integer schoolId,String password,String userType);
	
	
	public UserDetailInfo saveUserInfoFromClient(UserDetailInfoVo userDetailInfoVo, Integer appId,String password,String studentType,String parentRank,String parentRelation) throws Exception;
	
	public TeacherDetailInfo modifyProfileForTeacher(TeacherDetailInfo teacherDetailInfo);

	public TeacherDetailInfo modifySchoolUserForTeacher(TeacherDetailInfo teacherDetailInfo);

	public TeacherDetailInfo modifyTeamTeacher(TeacherDetailInfo teacherDetailInfo);

	public TeacherDetailInfo modifySubjectTeacher(TeacherDetailInfo teacherDetailInfo);

	public TeacherDetailInfo modifyDepartmentTeacher(TeacherDetailInfo teacherDetailInfo);
	
	/**
	 * 教师档案统计信息
	 */
	public TeacherArchiveVolist getTeacherArchiveMess(Integer departmentId);
	
	/**
	 * 根据部门获取教师信息
	 */
	public List<TeacherMessVo> findTeacherVoByDeptId(Integer departmentId);


	//-----------------教师统计  start-----------------------
	/**
	 * 教师年级人数
	 */
	public List<StatisticDate> findNumberOfGradeBySchoolId(Integer schoolId, String schoolYear);
	
	/**
	 * 工资
	 */
	public List<StatisticDate> findSalaryBySchoolId(Integer schoolId, String year, String month);
	
	/**
	 * 岗位编制
	 */
	public List<StatisticDate> findPostStaffingBySchoolId(Integer schoolId);
	
	/**
	 * 学位
	 */
	public List<StatisticDate> findQualificationBySchoolId(Integer schoolId);
	
	/**
	 * 证件类型
	 */
	public List<StatisticDate> findIdCardTypeBySchoolId(Integer schoolId);
	
	/**
	 * 婚姻状态
	 */
	public List<StatisticDate> findMarriageBySchoolId(Integer schoolId);
	
	/**
	 * 在职状态
	 */
	public List<StatisticDate> findJobStateBySchoolId(Integer schoolId);
	
	/**
	 * 男女比例
	 */
	public List<StatisticDate> findSexBySchoolId(Integer schoolId);
	
	/**
	 * 年龄
	 */
	public List<StatisticDate> findAgeBySchoolId(Integer schoolId);
	
	/**
	 * 政治面貌
	 */
	public List<StatisticDate> findPoliticalStatusBySchoolId(Integer schoolId);
	
	/**
	 * 民族
	 */
	public List<StatisticDate> findRaceBySchoolId(Integer schoolId);
	
	/**
	 * 户口性质
	 */
	public List<StatisticDate> findResidenceTypeBySchoolId(Integer schoolId);
	
	
	/**
	 * 教师学校人数
	 */
	public List<StatisticDate> findNumberOfSchoolByAreaCode(String areaCode);
	
	/**
	 * 工资
	 */
	public List<StatisticDate> findSalaryByAreaCode(String areaCode, String year, String month);
	
	/**
	 * 岗位编制
	 */
	public List<StatisticDate> findPostStaffingByAreaCode(String areaCode);
	
	/**
	 * 学位
	 */
	public List<StatisticDate> findQualificationByAreaCode(String areaCode);
	
	/**
	 * 证件类型
	 */
	public List<StatisticDate> findIdCardTypeByAreaCode(String areaCode);
	
	/**
	 * 婚姻状态
	 */
	public List<StatisticDate> findMarriageByAreaCode(String areaCode);
	
	/**
	 * 在职状态
	 */
	public List<StatisticDate> findJobStateByAreaCode(String areaCode);
	
	/**
	 * 男女比例
	 */
	public List<StatisticDate> findSexByAreaCode(String areaCode);
	
	/**
	 * 年龄
	 */
	public List<StatisticDate> findAgeByAreaCode(String areaCode);
	
	/**
	 * 政治面貌
	 */
	public List<StatisticDate> findPoliticalStatusByAreaCode(String areaCode);
	
	/**
	 * 民族
	 */
	public List<StatisticDate> findRaceByAreaCode(String areaCode);
	
	/**
	 * 户口性质
	 */
	public List<StatisticDate> findResidenceTypeByAreaCode(String areaCode);
	//-----------------教师统计  end-----------------------

	/**
	 * 获取学校的教师信息（可根据最后修改时间增量获取）
	 * @param isGetNew	新增/修改
	 * @return
	 */
	List<TeacherVo> findIncrementDataByModifyDate(Integer schoolId, Boolean isDeleted, Date modifyDate, Integer teacherId, Boolean isGetNew);

	List<TeacherVo> getAllSubjectTeachersByTeamId(Integer teamId);

	List<Teacher> findTeacherByUserId(Integer userId);

	/**
	 * 老师信息导入(新建校流程)
	 * @param job
	 * @param name
	 * @param sex
	 * @param alias
	 * @param phone
	 * @param department
	 * @param position
	 * @param schoolId
	 * @param appid
	 * @param teacherType
     * @return
	 */
	Map<String, Object> addTeacherFromExcelImport(String gh,String kh,String name, String sex, String alias, String phone,
                                                  String department, String position, Integer schoolId, Integer appid,
                                                  String teacherType, List<EmployeeList> list);

	/**
	 * 删除老师及其信息
	 * @param id
	 */
	boolean deleteTeacherAllInfoById(Integer id, String teacherType);

	boolean checkAliasIsRepeat(String name, String alias, Integer schoolId);

	List<Teacher> findTeacherListBySchoolId(Integer schoolId, Page page);

	boolean checkTeacherIsRepeat(String phone, Integer schoolId);

	List<Teacher> findTeacherByLikeNameAndSchool(String name, Integer schoolId, Page page, Order order);

    List<Teacher> findTeacherByNoSend(Teacher teacher);

	List<Teacher> findNotSendSeewo();
	List<Teacher> findCanSendSeewo();

	boolean updateSeewoStatusByIds(Integer[] ids);

    List<Teacher> findTeacherByNoSendCanteen(Teacher teacherVo);
	List<TeacherApiVo> findTeacherApiVoByCondition(TeacherCondition teacherCondition, Page page, Order order);

	/**
	 * 获取当前班主任下的所有学生
	 *
	 * @param schoolId
	 * @param teacherId
	 * @param schoolYear
	 * @return
	 */
	List<Map<String, Object>> findStuByTeacherId(Integer schoolId, Integer teacherId, String schoolYear);

	//推送成功修改，修改
	void updateTeacherSendCanteen(Teacher teacher);
	List<TeacherVo> findByUser(String name,Integer state,String userName,String modbie,Page page);


}
