package platform.education.rest.exam.service;

import java.text.ParseException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.RequireParam;

@Path("/exam")
public interface ExamRestService {
	/**
	 * 完成试卷调用的接口，主要用于doc下载提交，最新的平台2017-05-04无布置doc，只允许发布xep，所以暂时不用调用
	 * @param userId
	 * @param appKey
	 * @param taskId
	 * @param examId
	 * @param finishTime
	 * @return
	 */
	@Path("/study")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object unitFinsh1(@FormParam("userId") Integer userId, @FormParam("appKey")String appKey,
					@FormParam("taskId")Integer taskId, @FormParam("examId")Integer examId,
					@FormParam("finishTime")String finishTime);
	/**
	 * 完成试卷调用的接口，主要用于doc下载提交，最新的平台2017-05-04无布置doc，只允许发布xep，所以暂时不用调用
	 * @param userId
	 * @param appKey
	 * @param taskId
	 * @param examId
	 * @param finishTime
	 * @return
	 */
	@Path("/task/finish")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object unitFinsh(@FormParam("userId") Integer userId, @FormParam("appKey")String appKey,
					@FormParam("taskId")Integer taskId, @FormParam("examId")Integer examId,
					@FormParam("finishTime")String finishTime);
	/**
	 * 旧版本兼容随堂练获取试卷接口
	 * @param userId
	 * @param appKey
	 * @param page
	 * @param index
	 * @return
	 * @throws ParseException
	 */
	@Path("/listByExamList")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object examList (@FormParam("userId") Integer userId, @FormParam("appKey")String appKey,@DefaultValue("1") @FormParam("page") Integer page, @DefaultValue("10")@FormParam("index") Integer index)throws ParseException;
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
	@Path("/task/listByLearner")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object examListBySubject (@FormParam("userId") Integer userId, @FormParam("appKey")String appKey,@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,@DefaultValue("10") @FormParam("pageSize") Integer pageSize,@FormParam("subjectCode") String subjectCode)throws ParseException;
	
	
	/**
	 * 按教材目录索引试卷，2017-05-04这个版本目前没有调用这个接口
	 * @param userId
	 * @param appKey
	 * @param catalogId  目录id
	 * @return
	 */
	@Path("/task/listBycatalogId")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getExam (@FormParam("userId") Integer userId, @FormParam("appKey")String appKey,@FormParam("catalogId") Integer catalogId);
	
	/***
	 * 获取班级评卷情况
	 * @param taskId
	 * @param appKey
	 * @param teamId
	 * @return
	 */
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
	@Path("/task/correct/listByLearner")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getCorrectListByLearner (@FormParam("taskId") Integer taskId, @FormParam("appKey")String appKey, @FormParam("userId") Integer userId, @FormParam("unitId") Integer unitId);
	
	/**
	 * 提交评卷结果
	 * @param appKey
	 * @param taskId
	 * @param userId
	 * @param data
	 * @return
	 */
	@Path("/task/correct/finish")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object taskCorrectFinish (@FormParam("appKey")String appKey, @FormParam("taskId") Integer taskId, @FormParam("userId") Integer userId,
			@DefaultValue("teacher")@FormParam("userType")String userType, @FormParam("data") String data);
	/**
	 * 教师错题本
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 */
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
	@Path("/task/interscore/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object interscoreListBySubject (@FormParam("userId") Integer userId, @FormParam("appKey")String appKey,@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,@DefaultValue("10") @FormParam("pageSize") Integer pageSize,@FormParam("subjectCode") String subjectCode)throws ParseException;
	
	/**
	 * 互评详情
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 */
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
	@Path("task/correct/finish")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskCorrectFinish(@RequireParam(true) @FormParam("appKey") String appKey,
			@RequireParam(true) @FormParam("taskId") Integer taskId,
			@RequireParam(true) @FormParam("students") String students,
			@RequireParam(true) @FormParam("questionUuid") String questionUuid,
			@RequireParam(true) @FormParam("unitId") Integer unitId,
			@FormParam("files") String files);
}
