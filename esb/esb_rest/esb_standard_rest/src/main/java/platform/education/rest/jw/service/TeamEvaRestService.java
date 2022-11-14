package platform.education.rest.jw.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import platform.education.generalTeachingAffair.vo.TeamScoreData;



@Path("/school/moral/team/")
public interface TeamEvaRestService {
	
	
	/**
	 * 获取加分项项目名单
	 * @param termCode
	 * @return
	 */
	@POST
	@Path("add/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findTeamTaskAddItems(@FormParam("termCode") String termCode);
	
	/**
	 * 获取减分项项目名单
	 * @param termCode
	 * @return
	 */
	@POST
	@Path("minus/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findTeamTaskMinusItems(@FormParam("termCode") String termCode);
	
	/**
	 * 设置多个评价项目的得分
	 * @param teacherId
	 * @param teamId
	 * @param gradeId
	 * @param checkDate
	 * @param teamScoreDatas
	 * @return
	 */
	@POST
	@Path("BatchSetScores")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchSetTeamEvaScore(@FormParam("schoolId") Integer schoolId, @FormParam("userId") Integer userId, 
			@FormParam("teamId") Integer teamId, @FormParam("gradeId")Integer gradeId, @FormParam("checkDate") String checkDate, 
			@FormParam("teamScoreDatas") String teamScoreDatas);
	
	
	/**
	 * 获取加分项得分数据
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("add/listByScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchGetTeamAddEvaScore(@FormParam("termCode") String termCode,@FormParam("teamId") Integer teamId, 
			@FormParam("checkDate") String checkDate);
	
	
	/**
	 * 获取减分项得分数据
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("minus/listByScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchGetTeamMinusEvaScore(@FormParam("termCode") String termCode,@FormParam("teamId") Integer teamId, 
			@FormParam("checkDate") String checkDate);
	
	
	/**
	 * 设置单个评价项目的得分
	 * @param taskItemId
	 * @param teamId
	 * @param gradeId
	 * @param teacherId
	 * @param score
	 * @param checkDate
	 * @param remark
	 * @param fileUUIDs
	 * @return
	 */
	@POST
	@Path("setScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object setTeamEvaScore(@FormParam("taskItemId") Integer taskItemId, @FormParam("teamId") Integer teamId, 
			@FormParam("gradeId") Integer gradeId, @FormParam("schoolId") Integer schoolId, @FormParam("userId") Integer userId, @FormParam("score") Float score,
			@FormParam("checkDate") String checkDate,@FormParam("remark")  String remark, @FormParam("fileUUIDs") String fileUUIDs);
	
	
	/**
	 * 获取单个评价项目的得分
	 * @param taskItemId
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("getScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeamEvaScore(@FormParam("taskItemId") Integer taskItemId, @FormParam("teamId") Integer teamId, @FormParam("checkDate") String checkDate);
	
	
	/**
	 * 查看加分项数据
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("add/listByView")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchGetAddScore(@FormParam("termCode") String termCode,@FormParam("teamId") Integer teamId, 
			@FormParam("checkDate") String checkDate);
	
	
	/**
	 * 查看减分项数据
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("minus/listByView")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchGetMinusScore(@FormParam("termCode") String termCode,@FormParam("teamId") Integer teamId, 
			@FormParam("checkDate") String checkDate);
	
	
	/**
	 * 查看页面数据
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("listByView")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchGetScore(@FormParam("termCode") String termCode,@FormParam("teamId") Integer teamId, 
			@FormParam("checkDate") String checkDate);


	/**
	 * 查看页面数据（合并）
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("listAllByView")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchGetAllScore(@FormParam("termCode") String termCode,@FormParam("teamId") Integer teamId,
							@FormParam("checkDate") String checkDate);


	/**
	 * 获取 班级 评价报表
	 * @param termCode
	 * @param teamId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("summaryByTeam")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object summaryTeamEvaluationTaskForTeam(@FormParam("termCode") String termCode, @FormParam("teamId") Integer teamId, 
			@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);
	
	
	/**
	 * 获取 年级 评价报表
	 * @param termCode
	 * @param gradeId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("summaryByGrade")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object summaryTeamEvaluationTaskForGrade(@FormParam("termCode") String termCode, @FormParam("gradeId") Integer gradeId, 
			@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);
	
	
	/**
	 * 获取 全校 评价报表
	 * @param termCode
	 * @param schoolId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("summaryBySchool")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object summaryTeamEvaluationTaskForSchool(@FormParam("termCode") String termCode, @FormParam("schoolId") Integer schoolId, 
			@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);
	
	/**
	 * 获取 班级 评价报表
	 * @param termCode
	 * @param teamId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("summaryByTeam/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object summaryTeamEvaluationTaskForTeam(@QueryParam("termCode") String termCode, @QueryParam("teamId") Integer teamId, 
			@QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate, @QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 获取 年级 评价报表
	 * @param termCode
	 * @param gradeId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("summaryByGrade/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object summaryTeamEvaluationTaskForGrade(@QueryParam("termCode") String termCode, @QueryParam("gradeId") Integer gradeId, 
			@QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate, @QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 获取 全校 评价报表
	 * @param termCode
	 * @param schoolId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("summaryBySchool/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object summaryTeamEvaluationTaskForSchool(@QueryParam("termCode") String termCode, @QueryParam("schoolId") Integer schoolId, 
			@QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate, @QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 查看值日教师列表
	 * @param role
	 * @param gradeId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("duty/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getJudgeTeachers(
			@FormParam("role") String role, 
			@FormParam("schoolId") Integer schoolId, 
			@FormParam("userId") Integer userId, 
			@FormParam("gradeId") Integer gradeId, 
			@FormParam("beginDate") String beginDate, 
			@FormParam("endDate") String endDate);
	
	/**
	 * 设置值日教师
	 * @param schoolId
	 * @param gradeId
	 * @param beginDate
	 * @param endDate
	 * @param teacherData
	 * @return
	 */
	@POST
	@Path("duty/add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchSetJudgeTeachers(
			@FormParam("schoolId") Integer schoolId,  
			@FormParam("gradeId") Integer gradeId, 
			@FormParam("beginDate") String beginDate, 
			@FormParam("endDate") String endDate, 
			@FormParam("teacherData") String teacherData,
			@FormParam("periodCode") String periodCode);
	
	/**
	 * 判断 教师当天是否值日
	 * @param schoolId
	 * @param gradeId
	 * @param beginDate
	 * @param endDate
	 * @param teacherData
	 * @return
	 */
	@POST
	@Path("duty/isOnDuty")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findJudgeTeacher(
			@FormParam("schoolId") Integer schoolId,  
			@FormParam("userId") Integer userId);
	
	/**
	 * 获取 教师值日的年级班级
	 * @param schoolId
	 * @param gradeId
	 * @param beginDate
	 * @param endDate
	 * @param teacherData
	 * @return
	 */
	@POST
	@Path("duty/listByGrade")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getJudgeGrades(
			@FormParam("schoolId") Integer schoolId,  
			@FormParam("userId") Integer userId);
	
	
	
	/**
	 * 值日教师统计数据
	 */
	@POST
	@Path("duty/statistics/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getDutyTeacherStatistics(
			@FormParam("termCode") String termCode,  
			@FormParam("gradeId") Integer gradeId, 
			@FormParam("beginDate") String beginDate,  
			@FormParam("endDate") String endDate);
	
	
}



