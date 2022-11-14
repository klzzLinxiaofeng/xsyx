package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/syllabus/")
public interface BpSyllabusRestService {

	
	@POST
	@Path("findSyllabus")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findSyllabus(@FormParam("schoolId") Integer schoolId, @FormParam("teamId") Integer teamId,
                        @FormParam("roomId") Integer roomId,
                        @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("findSyllabusByRoomId")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findSyllabusByRoomId(@FormParam("schoolId") Integer schoolId, @FormParam("roomId") Integer roomId,
                                @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 移动端班主任、管理者获取班级课程表
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getTable")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTable(@FormParam("teamId") Integer teamId,
                    @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 移动端班主任、管理者获取班级课程表
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getTableByRoom")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTableByRoom(@FormParam("roomId") Integer roomId,
                          @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 移动端学生获取课程表
	 * @param schoolId
	 * @param teamId
	 * @param userId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getStudentSyllabus")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentSyllabus(@FormParam("schoolId") Integer schoolId, @FormParam("teamId") Integer teamId, @FormParam("userId") Integer userId,
                              @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 移动端任课老师获取课程表
	 * @param schoolId
	 * @param teamId
	 * @param userId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getTeacherSyllabus")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeacherSyllabus(@FormParam("schoolId") Integer schoolId, @FormParam("teamId") Integer teamId, @FormParam("userId") Integer userId,
                              @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	@POST
	@Path("getLessonTime")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getLessonTime(@FormParam("teamId") Integer teamId,
                         @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/************************************************调课通知开始************************************************/
	@POST
	@Path("findLessonChangeList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findLessonChangeList(@FormParam("teamId") Integer teamId, @FormParam("appKey") String appKey,
                                @FormParam("signage") String signage);

	@POST
	@Path("findLessonChangeContentList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findLessonChangeContentList(@FormParam("teamId") Integer teamId, @FormParam("appKey") String appKey,
                                       @FormParam("signage") String signage);

	@POST
	@Path("createLessonChange")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createLessonChange(@FormParam("teamId") Integer teamId, @FormParam("appKey") String appKey,
                              @FormParam("changeDate") Long changeDate, @FormParam("posterId") Integer posterId,
                              @FormParam("content") String content, @FormParam("signage") String signage);

	@POST
	@Path("deleteLessonChange")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteLessonChange(@FormParam("id") Integer id,
                              @FormParam("appKey") String appKey,
                              @FormParam("signage") String signage);

	/************************************************调课通知结束************************************************/



	/**
	 * 添加课程表基础信息
	 * @param schoolId
	 * @param teamId
	 * @param days
	 * @param daysPlan
	 * @param lessonOfMorning
	 * @param lessonOfAfternoon
	 * @param lessonOfEvening
	 * @param lessonTimes
	 * @return
	 */
	@POST
	@Path("createSyllabus")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createSyllabus(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("teamId") Integer teamId,
            @FormParam("days") Integer days,
            @FormParam("daysPlan") String daysPlan,
            @FormParam("lessonOfMorning") Integer lessonOfMorning,
            @FormParam("lessonOfAfternoon") Integer lessonOfAfternoon,
            @FormParam("lessonOfEvening") Integer lessonOfEvening,
            @FormParam("lessonTimes") String lessonTime,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 修改课程表基础信息
	 * @param id
	 * @param schoolId
	 * @param teamId
	 * @param days
	 * @param daysPlan
	 * @param lessonOfMorning
	 * @param lessonOfAfternoon
	 * @param lessonOfEvening
	 * @param lessonTime
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("modifySyllabus")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object modifySyllabus(
            @FormParam("id") Integer id,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("teamId") Integer teamId,
            @FormParam("days") Integer days,
            @FormParam("daysPlan") String daysPlan,
            @FormParam("lessonOfMorning") Integer lessonOfMorning,
            @FormParam("lessonOfAfternoon") Integer lessonOfAfternoon,
            @FormParam("lessonOfEvening") Integer lessonOfEvening,
            @FormParam("lessonTimes") String lessonTime,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 获取班级科目及科目的任课老师信息列表
	 * @param teamId
	 * @param gradeId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getSubjectTeamTeacherList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSubjectTeamTeacherList(
            @FormParam("teamId") Integer teamId,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage);

	/**
	 * 向课程表中添加科目
	 * @param schoolId
	 * @param syllabusId 课程表id
	 * @param lesson  第几节课
	 * @param dayOfWeek 星期几,0=星期日
	 * @param teacherId 教师id
	 * @param subjectCode 科目编码
	 * @param subjectName 科目名称
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createSyllabusLesson")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createSyllabusLesson(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("teamId") Integer teamId,
            @FormParam("lesson") Integer lesson,
            @FormParam("dayOfWeek") String dayOfWeek,
            @FormParam("teacherId") Integer teacherId,
            @FormParam("subjectCode") String subjectCode,
            @FormParam("subjectName") String subjectName,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage
    );

	/**
	 * 修改课程表中科目
	 * @param id
	 * @param teacherId
	 * @param subjectCode
	 * @param subjectName
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("modifySyllabusLesson")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object modifySyllabusLesson(
            @FormParam("id") Integer id,
            @FormParam("teacherId") Integer teacherId,
            @FormParam("subjectCode") String subjectCode,
            @FormParam("subjectName") String subjectName,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage
    );

	/**
	 * 删除课程表中的一节课程信息
	 * @param id
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("deleteSyllabusLesson")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteSyllabusLesson(
            @FormParam("id") Integer id,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage
    );



	/**
	 * 获取走班学生
	 * @param id
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("syllabusLessonStudent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object syllabusLessonStudent(
            @FormParam("syllabusLessonId") Integer syllabusLessonId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("date") Long date,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage
    );
	
	
	
	
}
