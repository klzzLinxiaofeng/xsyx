package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/attendance/")
public interface BpAttendanceRestService {
	
	/**
	 * 获取班级考勤明细
	 * @param teamId
	 * @param attendanceDay
	 * @param pageSize
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getAttendanceList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAttendanceList(
            @FormParam("teamId") Integer teamId,
            @FormParam("attendanceDay") Long attendanceDay,
            @DefaultValue("10") @FormParam("pageSize") String pageSize,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 教师发布考勤
	 * @param teamId
	 * @param type 1迟到 2早退 3缺勤 4请假
	 * @param userIds
	 * @param attendanceDay
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createAttendance")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createAttendance(
            @FormParam("teamId") Integer teamId,
            @FormParam("type") Integer type,
            @FormParam("userIds") String userIds,
            @FormParam("attendanceDay") Long attendanceDay,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 记录学生考勤时间
	 * @param cardId
	 * @param attendanceDay
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createAttendanceTime")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createAttendanceTime(
            @FormParam("cardId") String cardId,
            @FormParam("attendanceDay") Long attendanceDay,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 统计学校考勤情况
	 * @param schoolId
	 * @param startDay yyyy-MM-dd
	 * @param endDay
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("statisticsSchoolAttendance")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object statisticsSchoolAttendance(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("startDay") Long startDay,
            @FormParam("endDay") Long endDay,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 记录学生考勤时间
	 * @param cardId
	 * @param attendanceDay
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createAttendanceTimeBySchool")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createAttendanceTimeBySchool(
            @FormParam("cardId") String cardId,
            @FormParam("attendanceDay") Long attendanceDay,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("uuid") String uuid,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);




	/**
	 * 学生打卡时间列表
	 * @param teamId
	 * @param attendanceDay
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("listAttendanceTime")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object listAttendanceTime(
            @FormParam("teamId") Integer teamId,
            @FormParam("attendanceDay") Long attendanceDay,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 学生考勤统计
	 */
	@POST
	@Path("studentAttendanceList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object studentAttendanceList(
            @FormParam("teamId") Integer teamId,
            @FormParam("studentId") Integer studentId,
            @FormParam("week") Integer week,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 获取一个班级的学生加考勤情况
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("attendanceStudent/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAttendanceStudentList(
            @FormParam("teamId") String teamId,
            @FormParam("type") String type,
            @FormParam("attendanceDay") Long attendanceDay);


	/**
	 * 发布学生考勤
	 */
	@POST
	@Path("createStudentAttendance")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createStudentAttendance(
            @FormParam("teamId") Integer teamId,
            @FormParam("studentIds") String studentIds,
            @FormParam("attendanceDay") Long attendanceDay,
            @FormParam("remarks") String remarks,
            @FormParam("type") String type,
            @FormParam("isRemove") String isRemove,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);



	/**
	 * 学生打卡时间与考勤列表
	 * @param teamId
	 * @param attendanceDay
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("allAttendanceTimeList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object allAttendanceTimeList(
            @FormParam("teamId") Integer teamId,
            @FormParam("attendanceDay") Long attendanceDay,
            @DefaultValue("10") @FormParam("pageSize") String pageSize,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);



	/**
	 * 记录学生打卡
	 * @param cardId
	 * @param type 1行政考勤 2走班考勤
	 * @param attendanceDay
	 * @param schoolId
	 * @param syllabusLessonId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("recordCardBySchool")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object recordCardBySchool(
            @FormParam("cardId") String cardId,
            @FormParam("type") Integer type,
            @FormParam("attendanceDay") Long attendanceDay,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("syllabusLessonId") Integer syllabusLessonId,
            @FormParam("uuid") String uuid,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 走班考勤
	 */
	@POST
	@Path("attendanceSyllabus")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object attendanceSyllabus(
            @FormParam("lessonId") Integer lessonId,
            @FormParam("week") Integer week,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 学生查看考勤
	 */
	@POST
	@Path("studentAttendance")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object studentAttendance(
            @FormParam("lessonId") Integer lessonId,
            @FormParam("week") Integer week,
            @FormParam("userId") Integer userId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 查看走班课评价
	 */
	@POST
	@Path("attendanceEvaluate")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object attendanceEvaluate(
            @FormParam("lessonId") Integer lessonId,
            @FormParam("week") Integer week,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 添加评价
	 */
	@POST
	@Path("createEvaluate")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createEvaluate(
            @FormParam("lessonId") Integer lessonId,
            @FormParam("week") Integer week,
            @FormParam("content") String content,
            @FormParam("userId") Integer userId,
            @FormParam("item1") Double item1,
            @FormParam("item2") Double item2,
            @FormParam("item3") Double item3,
            @FormParam("item4") Double item4,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 补签
	 */
	@POST
	@Path("retroactive")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object retroactive(
            @FormParam("studentIds") String studentIds,
            @FormParam("week") Integer week,
            @DefaultValue("2") @FormParam("syllabusType") Integer syllabusType,
            @DefaultValue("0") @FormParam("isAttendance") Integer isAttendance,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 获取走班考勤
	 * @param id
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("syllabusLessonAttendance")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object syllabusLessonAttendance(
            @FormParam("syllabusLessonId") Integer syllabusLessonId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("week") Integer week,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage
    );

	/**
	 * 获取班级考勤模块页面详情
	 * @param schoolId
	 * @param teamId
	 * @param attendanceDay
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getAttendancePageIndex")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAttendancePageIndex(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("teamId") Integer teamId,
            @FormParam("attendanceDay") Long attendanceDay,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 学生个人考勤页面详情
	 * @param userId
	 * @param attendanceDay
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getStudentPersonalAttendancePageIndex")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentPersonalAttendancePageIndex(
            @FormParam("userId") Integer userId,
            @FormParam("attendanceDay") Long attendanceDay,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
	
}
