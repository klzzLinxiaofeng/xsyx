package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.education.generalTeachingAffair.bo.TeamStudentInfo;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.model.Group;
import platform.education.user.model.Profile;
import platform.education.user.model.Role;
import platform.education.user.model.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface StudentService {

	Student findStudentById(Integer id);

	Student findStudentByName(String name);

	Student add(Student student);

	Student modify(Student student);
	Integer updateTeamStudent(Integer teamId,Integer studentId);
	void remove(Student student);

	List<StudentInfo> findSendSeewoStu(String schoolYear);

	/**
	 * 找到当前学年下班级的学生
	 * @param teamId
	 * @return
	 */
	public List<Student> findStudentOfTeam(Integer teamId);

	public List<Student> findStudentOfTeam2(Integer teamId,Integer gardeId);

	public List<Student> findStudentByCondition(StudentCondition studentCondition, Page page, Order order);

	public List<Student> findStudentByTeacherIdCondition(StudentCondition studentCondition, Page page, Order order, Integer teacherId);

	/**
	 * 通过学校ID 获取未分班的学生
	 */
	public List<Student> findStudentIsNotTeamBySchoolId(Integer schoolId, String sex);

	/**
	 * 些方法不作为通用方法。比较特殊的用途
	 *
	 * 主要是将班级ID，和班级名称置为空
	 *
	 * @param student
	 * @return
	 */
	public Student modifyStudentSetTeamIsNull(Student student);

	public Integer findUniqueStudentId(Integer userId, Integer schoolId);

	/**
	 * @Method findOfUser
	 * @Function 功能描述：获取用户所关联的学生信息，假如是学生的话
	 * @param userId
	 * @param schoolId
	 * @return
	 * @Date 2015年6月29日
	 */
	public Student findOfUser(Integer schoolId, Integer userId);

	/**
	 * 功能描述：此接口是通过用户ID查询出该学生信息（家长选课调用）
	 *
	 * @param userId
	 * @return
	 */
	public Student findStudentByUserId(Integer userId);

	/**
	 * 此接口是提供给 遥见教育公司
	 *
	 *
	 * 通过班班获取已分完班的学生相关信息 姓名，头像，用户名，电话，班级ID，班级名，学校ID
	 */
	public List<Student> findStudentByTeamId(Integer teamId);

	public List<Student> findStudentByTeamId(Integer teamId,Page page,Order order);

	List<Student> findTeamStudentByCondition(StudentCondition studentCondition, Page page, Order order);

	// 保存学生信息---此方法没涉及档案信息（最小化保存）
	/**
	 * @param student
	 *            学生信息
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
	public Student addStudent(Student student, User user, Role role, Integer groupId, Integer appId, String regionCode, String level, Integer createUserId);

	// 保存学生信息---此方法没涉及档案信息、个人信息
	/**
	 * @param student
	 *            学生信息
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
	public Student addStudent(Student student, User user, Role role, Profile profile, Integer groupId, Integer appId, String regionCode, String level, Integer createUserId);

	/**
	 * 根据Student条件查询
	 *
	 * @param studentCondition
	 * @param page
	 * @param order
	 * @return
	 */
	public List<Student> findStudentByOnlyCondition(StudentCondition studentCondition, Page page, Order order);

	/**
	 * @Method findSchoolInfoByCondition
	 * @Function 功能描述：根据学校、组条件查询出所有学生
	 * @param condition
	 * @param group
	 * @param page
	 * @param order
	 * @return
	 * @Date 2015年4月27日
	 */
	public List<Student> findStudentBySchoolRegion(SchoolCondition condition, Group group, Page page, Order order);

	/**
	 * 统计当前学校下的学生数量
	 *
	 * @param schoolId
	 * @return
	 */
	public String findStudentNumBySchoolId(Integer schoolId);

	List<Student> findStudentHasTeamByCondition(StudentCondition studentCondition, Page page, Order order);

	// ==========================此接口是提供OA的=================================
	/**
	 * 根据传入的List<Integer>集合返回学生ID集合 type 1：班级ID类型，2：年级ID类型
	 *
	 * @param id
	 * @param type
	 * @return
	 */
	public List<Student> findStudentListByListId(List<Integer> idList, String type, Integer schoolId);

	/***
	 * 通过学校ID获取当前学校下所有学生
	 *
	 * @param schoolId
	 * @return
	 */
	public List<Student> findStudentListBySchoolId(Integer schoolId);

	List<Student> findStudentUniqByCondition(StudentCondition studentCondition,
			Page page, Order order);
    /**
     * 根据家长信息查找学生，分页以学生为主
     * @param parentCondition
     * @param page
     * @param order
     * @return
     */
	List<Student> findStudentByParent(ParentCondition parentCondition,
			Page page, Order order);
	/**
	 * 根据班级信息筛选对应的学生信息
	 * @param studentCondition
	 * @param page
	 * @param order
	 * @return
	 */
	List<Student> findStudentByTeam(StudentCondition studentCondition,
			Page page, Order order);

	List<Student> findStudentByTeam(StudentCondition studentCondition, Page page);

	List<Student> findStudentByTeam(StudentCondition studentCondition,
			Order order);

	List<Student> findStudentByTeam(StudentCondition studentCondition);

	/**
	 * 功能描述：用于学生统计 2015-11-5
	 * @return
	 */
	Long count();

	/**
	 * 功能描述：用于学生统计 2015-11-5
	 * @param studentCondition
	 * @return
	 */
	Long count(StudentCondition studentCondition);

	/**
	 * 功能描述：用于学生统计 2015-11-5
	 * @param schoolId
	 * @return
	 */
	public StudentVo findStudentNumberOfRaceData(Integer schoolId);

	/**
	 * @function 统计一个班级的学生数
	 * @param teamId
	 * @return
	 * @date 2016年03月04日
	 */
	public Long countByTeamId(Integer teamId);

	/**
	 * @function 照片统计模块 统计一个班级没有照片的人数
	 * @param teamId
	 * @return
	 * @date 2016年03月04日
	 */
	public Long countNoPhotoTeamId(Integer teamId);

	/**
	 * @function 照片统计模块 查询出一个班级没有照片的学生的姓名
	 * @param departmentId
	 * @return
	 * @date 2016年03月04日
	 */
	public String findNoPhotoName(Integer teamId);

	/**
	 * @function 照片统计模块 查询出一个班级有照片的学生的姓名
	 * @param departmentId
	 * @return
	 * @date 2016年03月04日
	 */
	public String findHasPhotoName(Integer teamId);

	/**
	 * @function 根据学生ID获取学生的基本信息
	 * @param studentId
	 * @date 2016-7-21
	 * @return
	 */
	public StudentArchiveComplete getStudentArchiveComplete(Integer studentId);
	/**
	 * @function 根据学生ID获取学生的基本信息2/pingyumoban/allType?sub=list
	 * @param studentId
	 * @date 2022-05-10
	 * @return
	 */
	public StudentArchiveComplete getStudentArchiveComplete2(Integer studentId,String year);

	/**
	 * @function 将学生的学籍信息完善
	 * @param studentId
	 * @param
	 */
	public StudentArchiveComplete setStudentArchiveComplete (Integer studentId, StudentArchiveComplete studentArchiveComplete,Boolean isComplet,Boolean isBindingMobile);

	/**
	 * @function 修改学生信息
	 * @param userDetailInfo
	 * @return
	 * @throws Exception
	 */
	public UserDetailInfo modfiyUserInfo(UserDetailInfo userDetailInfo,Boolean isBindingMobile)throws Exception;

	/**
	 * 功能描述：客户端学生导入
	 * 2016-2-2
	 * @param teacherDetailInfo
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	public ExtImportErrorMsg ImportInfoForStudent(ExtImportStudentVo extImportStudentVo,String password,String userType, Integer appId,Integer schoolId,Integer teamId,Boolean isBindingMobile);

	public UserDetailInfo saveUserInfo(UserDetailInfo userDetailInfo, String password,String userType,Integer appId,Boolean isBindingMobile) throws Exception;

	List<Student> findFinishState(StudentCondition studentCondition, Page page,
			Order desc);

	/**
	 * 功能描述：获取学生档案已完成和未完成的学生信息
	 * 2016-2-2
	 * @param teacherDetailInfo
	 * @param appId
	 * @return
	 * @throws Exception
	 */
	List<StudentVo> findArchiveSummary(Integer teamId,Boolean isFinish);


	/**
	 * 统计每个班的人数
	 * @param teamId
	 * @param studentId
	 * @throws Exception
	 */
	public void  upTeamStudentByTeamId();

	List<UserDetailInfo> findStudentDetailInfo(StudentCondition studentCondition)
			throws Exception;
	public void setMonitor(Integer userId,Integer teamId);


	public List<Student> findbyUserIds(Integer[] userIds);

	//---------- 学生统计   start ----------
	/**
	 * 学生年级人数
	 */
	public List<StatisticDate> findNumberOfGradeBySchoolId(Integer schoolId, String schoolYear);

	/**
	 * 班级数
	 */
	public List<StatisticDate> findTeamNumberBySchoolId(Integer schoolId, String schoolYear);

	/**
	 * 籍贯
	 */
	public List<StatisticDate> findNativePlaceBySchoolId(Integer schoolId);

	/**
	 * 健康状态
	 */
	public List<StatisticDate> findHealthStatusBySchoolId(Integer schoolId);

	/**
	 * 入学方式
	 */
	public List<StatisticDate> findEnrollTypeBySchoolId(Integer schoolId);

	/**
	 * 就读方式
	 */
	public List<StatisticDate> findAttendSchoolTypeBySchoolId(Integer schoolId);

	/**
	 * 学生来源
	 */
	public List<StatisticDate> findStudentSourceBySchoolId(Integer schoolId);

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
	 * 学生学校人数
	 */
	public List<StatisticDate> findNumberOfSchoolByAreaCode(String areaCode);

	/**
	 * 班级数统计
	 */
	public List<StatisticDate> findTeamNumberByAreaCode(String areaCode, String schoolYear);

	/**
	 * 籍贯
	 */
	public List<StatisticDate> findNativePlaceByAreaCode(String areaCode);

	/**
	 * 健康状态
	 */
	public List<StatisticDate> findHealthStatusByAreaCode(String areaCode);

	/**
	 * 入学方式
	 */
	public List<StatisticDate> findEnrollTypeByAreaCode(String areaCode);

	/**
	 * 就读方式
	 */
	public List<StatisticDate> findAttendSchoolTypeByAreaCode(String areaCode);
	
	/**
	 * 学生来源
	 */
	public List<StatisticDate> findStudentSourceByAreaCode(String areaCode);

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
	//---------- 学生统计   end ----------

	/**
	 * 批量获取学生档案信息
	 * @param userIds
	 * @return
	 */
	List<StudentArchiveCompleteVo> findCompleteByUserIds(int[] userIds);

	/**
	 * 获取学校的学生信息（可根据最后修改时间增量获取）
	 */
	List<StudentVo> findIncrementDataByModifyDate(Integer schoolId, String schoolYear, Boolean isDeleted, Date modifyDate, Integer studentId, Boolean isGetNew);

	List<Student> findTeamStudentByCondition1(StudentCondition studentCondition, Page page, Order order);

	List<StudentVo> findStudentVoByCondition(StudentCondition studentCondition, Page page, Order order);

	List<StudentVo> findByTeamOrName(Integer schoolId, String schoolYear, Integer teamId, String name);

	/**
	 * 新建校学生导入
     * @param grade 年级
     * @param team 班级
     * @param num 班内学号
     * @param name 姓名
     * @param guardianPhone 监护人手机
     * @param guardian 监护人
     * @param schoolId 学校id
     * @param schoolYear 学年
     * @param list 学生信息
     */
	Map<String, Object> addStudentFromExcelImport(String kh,String grade, String team, String num, String name, String guardianPhone,
												  String guardian, Integer schoolId, String schoolYear, Integer appid, String studentType, String parentType, List<EmployeeList> list);

	/**
	 * 添加学生
	 * @param userId
	 * @param schoolId
	 * @param gradeId
	 * @param teamId
	 * @param name
	 * @return
	 */
	Student addStudentByUserTeamSchoolIdAndName(Integer schoolId, Integer userId, Integer teamId,String name,String num,String kh);

	void removeAllStudentInfoById(Integer studentId, String studentType, String parentType);

	/**
	 * 获取学生及其班级年级信息
	 * @param schoolId
	 * @param schoolYear
	 * @param gradeId	可为null
	 * @param teamId   可为null
	 * @return  带有teamNumber, gradeName, 班内学号等数据
	 */
	List<StudentVo> findStudentVoByTeam(Integer schoolId, String schoolYear, Integer gradeId, Integer teamId);

	/**
	 * 获取没有发送到对应的端的学生数据
	 * @param student
	 * @return
	 */
    List<Student> findStudentByNoSend(Student student);

	List<TeamStudentInfo> findBoNotSendSeewo();

	/**
	 * 获取所有能发送到希沃的学生信息
	 * @return
	 */
	List<Student> findCanSendSeewo();

	boolean updateAsSendSeewo(List<Student> students);

	boolean updateAsSendSeewoByIds(Integer[] ids);

	boolean updateAsSendSeewoByTeamId(Integer teamId);

    List<Student> findStudentByNoSendCanteen(Student studentVO);


	StudentArchive findStudentArchiveByStudentId(Integer stuId);

	List<FamilyMember> findFamilyMemberByStudentId(Integer stuId);
	Map<String,Object>  updateasdasdas(Student student);

	List<Student> findByStudentShitang();

}
