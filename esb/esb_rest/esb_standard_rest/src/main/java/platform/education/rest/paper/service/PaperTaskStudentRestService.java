package platform.education.rest.paper.service;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;
import platform.education.rest.common.annotation.RequireParam;
@Path("/paper/student")
public interface PaperTaskStudentRestService {
	/**
	 * 新的按科目索引试卷
	 * @param userId
	 * @param appKey
	 * @param page
	 * @param index
	 * @param subjectCode
	 * @return
	 * @throws ParseException
	 */
	@MqtApi
	@Path("/task/listByLearner")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object examListBySubject (@FormParam("userId") Integer userId, @FormParam("appKey")String appKey,@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,@DefaultValue("10") @FormParam("pageSize") Integer pageSize,@FormParam("subjectCode") String subjectCode)throws ParseException;
	
	
	
	/***
	 * 获取班级评卷情况
	 * @param taskId
	 * @param appKey
	 * @param teamId
	 * @return
	 */
	@MqtApi
	@Path("/task/correct/listByTeam")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getCorrectListByTeam (@FormParam("taskId") Integer taskId, @FormParam("appKey")String appKey,@FormParam("teamId") Integer teamId);
	
	/***
	 * 获取个人答卷情况
	 * @param taskId
	 * @param appKey
	 * @param teamId
	 * @return
	 */
	@MqtApi
	@Path("/task/correct/listByLearner")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getCorrectListByLearner (@FormParam("appKey")String appKey, 
			@RequireParam(true)@FormParam("taskId") Integer taskId,
			@RequireParam(false)@FormParam("userId") Integer userId,
			@RequireParam(false)@FormParam("unitId") Integer unitId);
	
	
	@MqtApi
	@Path("/response/check")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object responseCheck (@FormParam("appKey")String appKey, 
			@RequireParam(true)@FormParam("taskId") Integer taskId,
			@RequireParam(true)@FormParam("teamId") Integer teamId,
			@RequireParam(false)@FormParam("type")@DefaultValue("2") Integer type);
	
	/**
	 * 按题提交评卷结果
	 * 
	 * @param appKey
	 * @param taskId
	 * @param userId
	 * @param data
	 * @return
	 */
	
	@MqtApi
	@Path("task/correct/finish")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskCorrectFinish(@RequireParam(true) @FormParam("appKey") String appKey,
			@RequireParam(true) @FormParam("taskId") Integer taskId,
			@RequireParam(true) @FormParam("students") String students,
			@RequireParam(true) @FormParam("questionUuid") String questionUuid,
			@RequireParam(false) @FormParam("unitId") Integer unitId);
	
	/**
	 * 教师错题本
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 */
	
	@MqtApi
	@Path("/task/wrong/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object wrong(   @FormParam("appKey")String appKey,
					@FormParam("taskId")Integer taskId,
					@FormParam("teamId")Integer teamId);
	
	/**
	 * 设置互评列表
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @param startTime
	 * @param finishTime
	 * @param isInterscore
	 * @return
	 * @throws ParseException
	 */
	
	@MqtApi
	@Path("/task/interscore/start")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object interscoreStart(
			        @FormParam("appKey")String appKey,
					@FormParam("taskId")Integer taskId,
					@FormParam("teamId")Integer teamId,
					@FormParam("startTime")String startTime,
					@FormParam("finishTime")String finishTime,
					@FormParam("isInterscore")Integer isInterscore)throws ParseException;
	
	/**
	 * 学生互评列表
	 * @param userId
	 * @param appKey
	 * @param pageNumber
	 * @param pageSize
	 * @param subjectCode
	 * @return
	 * @throws ParseException
	 */
	
	@MqtApi
	@Path("/task/interscore/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object interscoreListBySubject (@FormParam("userId") Integer userId, @FormParam("appKey")String appKey,
			@FormParam("subjectCode") String subjectCode)throws ParseException;
	
	/**
	 * 互评详情
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 */
	
	@MqtApi
	@Path("/task/interscore/details")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object interscoreDetails  ( @FormParam("appKey")String appKey,
			@FormParam("taskId")Integer taskId,
			@FormParam("teamId")Integer teamId
			);
	
	/**
	 * 提示缺考人数和未互评人数
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 * @throws ParseException
	 */
	
	@MqtApi
	@Path("/task/interscore/isInterscore")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object InitInterscore(
			        @FormParam("appKey")String appKey,
					@FormParam("taskId")Integer taskId,
					@FormParam("teamId")Integer teamId
			)throws ParseException;
	/**
	 * 提示缺考人数和未互评人数
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 * @throws ParseException
	 */
	
	@MqtApi
	@Path("/task/getPaper")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getPaper(
			        @FormParam("appKey")String appKey,
					@FormParam("taskId")Integer taskId,
					@FormParam("userId")Integer userId,
					@FormParam("unitId")Integer unitId
			)throws ParseException;
	/**
	 * 单题评卷列表
	 * 
	 * @param appKey
	 * @param taskId
	 * @param unitId
	 * @param teamId
	 * @param questionUuid
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	
	@MqtApi
	@Path("task/correct/listByQuestion")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object correctListByQuestion(@RequireParam(true) @FormParam("appKey") String appKey,
			@RequireParam(true)  @FormParam("taskId") Integer taskId,
			@RequireParam(false) @FormParam("unitId") Integer unitId, 
			@RequireParam(true) @FormParam("teamId") Integer teamId,
			@RequireParam(true) @FormParam("questionUuid") String questionUuid,
			@DefaultValue("20") @FormParam("pageSize") Integer pageSize,
			@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber);

	/**
	 * 提交评卷结果
	 * 
	 * @param appKey
	 * @param taskId
	 * @param userId
	 * @param data
	 * @return
	 */
	
	@MqtApi
	@Path("/task/interscore/finish")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskInterscoreFinish(@RequireParam(true) @FormParam("appKey") String appKey,
			@RequireParam(true) @FormParam("taskId") Integer taskId,
			@RequireParam(true) @FormParam("userId") Integer userId,
			@DefaultValue("student") @FormParam("userType") String type,
			@RequireParam(true) @FormParam("data") String data);
	@MqtApi
	@Path("task/correct/listByQuestion/v4")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object correctListByQuestion(@RequireParam(true) @FormParam("appKey") String appKey,
			@RequireParam(true)  @FormParam("taskId") Integer taskId,
			@RequireParam(false) @FormParam("unitId") Integer unitId, 
			@RequireParam(true) @FormParam("teamId") Integer teamId,
			@RequireParam(true) @FormParam("userId") Integer userId,
			@RequireParam(true) @FormParam("questionUuid") String questionUuid,
			@DefaultValue("20") @FormParam("pageSize") Integer pageSize,
			@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber);
}
