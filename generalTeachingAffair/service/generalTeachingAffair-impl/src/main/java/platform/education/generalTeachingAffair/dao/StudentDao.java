package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.education.generalTeachingAffair.bo.TeamStudentInfo;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.user.model.Group;

import java.util.Date;
import java.util.List;

public interface StudentDao extends GenericDao<Student, java.lang.Integer> {
	List<Student> findByStudentShitang();
	List<Student> findStudentByCondition(StudentCondition studentCondition, Page page, Order order);
	List<Student> findStudentUniqByCondition(StudentCondition studentCondition, Page page, Order order);
	Student findById(Integer id);
	/**
	 * 找到当前学年下班级的学生
	 * @param teamId
	 * @return
	 */
	public List<Student> findStudentOfTeam(Integer teamId);
	public List<Student> findStudentOfTeam2(Integer teamId,Integer gradeId);

	public Student findByName(String name);

	public List<Student> findStudentIsNotTeamBySchoolId(Integer schoolId, String sex);

	public Student updateStudentSetTeamIsNull(Student student);

	public Student readStudentSetTeamIsNull(Student student);

	Integer findUniqueStudentId(Integer userId, Integer schoolId);

	Student findOfUser(Integer schoolId, Integer userId);

	/**
	 * 功能描述：此接口是通过用户ID查询出该学生信息（家长选课调用）
	 * @param userId
	 * @return
	 */
	Student findStudentByUserId(Integer userId);

	List<Student> findStudentByTeamId(Integer teamId);
	List<Student> findStudentByTeamId2(Integer teamId,Integer gradeId);
	List<Student> findStudentByTeamId(Integer teamId,Page page,Order order);
	List<Student> findStudentHasTeamByCondition(StudentCondition studentCondition, Page page, Order order);
	List<Student> findTeamStudentByCondition(StudentCondition studentCondition, Page page, Order order);

	List<Student> findStudentByOnlyCondition(StudentCondition studentCondition, Page page, Order order);

	List<Student> findStudentBySchoolRegion(SchoolCondition condition, Group group, Page page, Order order);

	public Student findStudentNumBySchoolId(Integer schoolId);

	public List<Student> findStudentOfTeamIdList(String[] arr,Integer schoolId);

	public List<Student> findStudentOfGradeIdList(String[] arr,Integer schoolId);

	public List<Student> findStudentListBySchoolId(Integer schoolId);

	public List<Student> findStudentByParent(ParentCondition parentCondition, Page page, Order order);
	/**
	 *
	 * @param studentCondition 根据班级查找对应的学生
	 * @param page
	 * @param order
	 * @return
	 */
	public List<Student> findStudentByTeam(StudentCondition studentCondition, Page page, Order order);

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
	List<Student> findFinishState(StudentCondition studentCondition, Page page,
			Order desc);


	List<StudentVo> findArchiveSummary(Integer teamId,String finishName, Boolean isFinish);

	List<Student> findbyUserIds(Integer[] userIds);


	//---------- 学生统计   start ----------
	/**
	 * 学生年级人数
	 */
	List<StatisticDate> findNumberOfGradeBySchoolId(Integer schoolId, String schoolYear);
	/**
	 * 班级数
	 */
	List<StatisticDate> findTeamNumberBySchoolId(Integer schoolId, String schoolYear);
	/**
	 * 籍贯
	 */
	List<StatisticDate> findNativePlaceBySchoolId(Integer schoolId);
	/**
	 * 健康状态
	 */
	List<StatisticDate> findHealthStatusBySchoolId(Integer schoolId);
	/**
	 * 入学方式
	 */
	List<StatisticDate> findEnrollTypeBySchoolId(Integer schoolId);
	/**
	 * 就读方式
	 */
	List<StatisticDate> findAttendSchoolTypeBySchoolId(Integer schoolId);
	/**
	 * 学生来源
	 */
	List<StatisticDate> findStudentSourceBySchoolId(Integer schoolId);

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
	 * 学生学校人数
	 */
	List<StatisticDate> findNumberOfSchoolByAreaCode(String areaCode);
	/**
	 * 班级数统计
	 */
	List<StatisticDate> findTeamNumberByAreaCode(String areaCode, String schoolYear);
	/**
	 * 籍贯
	 */
	List<StatisticDate> findNativePlaceByAreaCode(String areaCode);
	/**
	 * 健康状态
	 */
	List<StatisticDate> findHealthStatusByAreaCode(String areaCode);
	/**
	 * 入学方式
	 */
	List<StatisticDate> findEnrollTypeByAreaCode(String areaCode);
	/**
	 * 就读方式
	 */
	List<StatisticDate> findAttendSchoolTypeByAreaCode(String areaCode);

	/**
	 * 学生来源
	 */
	List<StatisticDate> findStudentSourceByAreaCode(String areaCode);
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

	List<Student> findStudentByParentOrStuName(ParentCondition parentCondition, Page page, Object object);

	List<StudentVo> findStudentVoByTeam(Integer schoolId, String schoolYear, Integer gradeId, Integer teamId);

	//List<Student> searchAllStu();

    List<Student> findStudentByNoSend(Student student);

    List<TeamStudentInfo> findBoByIsSendSeewo();

	List<StudentInfo> findSendSeewoStu(String schoolYear);

	List<Student> findCanSendSeewo();

	Integer updateAsSendSeewoByIds(Integer[] ids);

	Integer updateAsSendSeewoByTeamId(Integer teamId);

    void updateStuSendCanteen(Student student);

    List<Student> findStudentByNoSendCanteen(Student studentVO);

	List<HikvisionUserPo> findStudentSendHik(String schoolYear,Page page, Order order);
	/*绑定错误*/
	List<HikvisionUserPo> findStudentSendHikEr(Page page, Order order);

	String findTeamCode(Integer teamid);
    void updateHikvisionUserInfo(Integer[] arrayBindCard, Integer sendCards, Integer bindCards);

	void updateHikvisionUserInfoPersonAdd(int userId, Integer sendCards, Integer bindCards,String  persionId);

	void updateHikvisionUserInfoPerson(String[] arrayBindCard, Integer sendCards, Integer bindCards);

	List<CardList> findHikvisionBindCarStu(Page page, Order order);

	List<ModifyShoolBusCardStu> findModifySchoolBusCardStus(ModifyShoolBusCardStu modifyShoolBusCardStu);

    List<String> findNameById(String[] split);

	List<Student> findStudentByTeacherIdCondition(StudentCondition studentCondition, Integer teacherId, Page page, Order order);

	List<UntieCardList> findUntieHikvisionBindCarStu();
	Integer updateStuid(Student student);
	Integer findIddd(Integer id);
	Integer updateTeamStudent(Integer teamId,Integer studentId);
	Integer updateTeamStudent2(Integer teamId,Integer studentId);
	List<Student> findByall(Integer schoolId,String xq, Integer bg,Integer nj,String stuName,Page page);

}
