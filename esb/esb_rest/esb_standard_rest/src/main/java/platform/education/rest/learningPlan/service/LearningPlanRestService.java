package platform.education.rest.learningPlan.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;
import platform.education.rest.common.annotation.RequireParam;


@Path("learningPlan")
public interface LearningPlanRestService {

	@MqtApi
	/**
	 * 获取章节目录的树形结构
	 * @param appKey
	 * @param textbookId 书籍的id
	 * @param userId 用户的id
	 * @return
	 */
	@Path("textbook/catalog/tree/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findTextbookCatalogTree(@FormParam("textbookId")Integer textbookId,
			@FormParam("appKey")String appKey,
			@FormParam("userId")Integer userId);

	/**-----------------------------------------------导学案任务相关接口------------------------------*/

	/**
	 * 获取学生的导学案任务列表
	 * @param appKey
	 * @param userId 用户的id
	 * @param schoolId 学校的id
	 * @param subjectCode 科目 的code
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@MqtApi
	@Path("task/listByLearner")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object listByLearner( @FormParam("userId") Integer userId,
			 @FormParam("schoolId") Integer schoolId,
			 @FormParam("appKey") String appKey, @FormParam("subjectCode") String subjectCode,
			@DefaultValue("20") @FormParam("pageSize") Integer pageSize,
			@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber);

	/**
	 * 获取目录下的导学案
	 *
	 * @param appKey
	 * @param catalogId 目录的id
	 * @param userId 用户的id
	 * @param schoolId 学校的id
	 * @return
	 */
	@MqtApi
	@Path("task/listByCatalog")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object listByCatalog( @FormParam("appKey") String appKey,
			 @FormParam("catalogId") Integer catalogId,
			 @FormParam("userId") Integer userId,
			 @FormParam("schoolId") Integer schoolId);

	/**
	 * 获取老师发布的导学案任务
	 * 
	 *  @param appKey
	 * @param userId 用户id
	 * @param schoolId 学校id
	 * @param teamId 班级id
	 * @param subjectCode 科目code
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@MqtApi
	@Path("task/listByTeam")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object listByTeam( @FormParam("userId") Integer userId,
			 @FormParam("schoolId") Integer schoolId,
			 @FormParam("teamId") Integer teamId,
			 @FormParam("appKey") String appKey, @FormParam("subjectCode") String subjectCode,
			@DefaultValue("20") @FormParam("pageSize") Integer pageSize,
			@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber);

	/**
	 * 删除导学案任务
	 * @param appKey
	 * @param taskId 任务id
	 * @return
	 */
	@MqtApi
	@Path("task/delete")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskDelete( @FormParam("taskId") Integer taskId,
			 @FormParam("appKey") String appKey);

	/**
	 * 结束一个导学案学习单元
	 * @param appKey
	 * @param userId 用户id
	 * @param taskId 任务id
	 * @param unitId 单元id
	 * @return
	 */
	@MqtApi
	@Path("task/unit/finish")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object unitFinsh( @FormParam("userId") Integer userId,
			 @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("unitId") Integer unitId);

	/**
	 * 获取导学案目录和单元列表
	 * @param appKey
	 * @param learningPlanId 导学案id
	 * @param userId 用户id
	 * @param taskId 任务id
	 * @return
	 */
	@MqtApi
	@Path("task/catalogAndUnitWithConversation/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object lpCatalogAndUnitListWithConversation( @FormParam("appKey") String appKey,
			 @FormParam("learningPlanId") Integer learningPlanId,
			 @FormParam("userId") Integer userId,
			 @FormParam("taskId") Integer taskId);

	/**
	 * 获取导学案目录和单元列表
	 * @param appKey
	 * @param learningPlanId 导学案id
	 * @param userId 用户id
	 * @param taskId 任务id
	 * @return
	 */
	@MqtApi
	@Path("task/catalogAndUnit/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object lpCatalogAndUnitList( @FormParam("appKey") String appKey,
								 @FormParam("learningPlanId") Integer learningPlanId,
								 @FormParam("userId") Integer userId,
								 @FormParam("taskId") Integer taskId);

	/**
	 * 导学案单元解锁
	 * @param appKey
	 * @param learningPlanId 导学案id
	 * @param unitId 单元id
	 * @param taskId 任务id
	 * @return
	 */
	@Path("task/unlocked")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object unlockLpUnit(@RequireParam(true) @FormParam("appKey") String appKey,
			@RequireParam(false) @FormParam("unitId") Integer unitId,
			@RequireParam(false) @FormParam("taskId") Integer taskId);

	/**
	 * 获取导学案单元详细信息
	 * @param appKey
	 * @param learningPlanId 导学案id
	 * @param userId 用户id
	 * @param taskId 任务id
	 * @return
	 */
//	@MqtApi
	@Path("task/unit/get")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object getTaskUnitGet( @FormParam("appKey") String appKey,
			 @FormParam("unitId") Integer unitId,
			 @FormParam("userId") Integer userId,
			 @FormParam("taskId") Integer taskId);

	/**
	 * 获取导学案单元详细信息并开始学习(移动端如果不调，在调finish接口时默认调)
	 * @param appKey
	 * @param userId 用户id
	 * @param taskId 任务id
	 * @param unitId 单元id
	 * @param learningPlanId 导学案id
	 * @return
	 */
	@MqtApi
	@Path("task/unit/start")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object unitGetAndStart( @FormParam("userId") Integer userId,
			 @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("unitId") Integer unitId);

	/**
	 * 获取拥有的目录结构
	 * 
	 * @param appKey
	 * @param textbookId
	 * @param userId
	 * @param schoolId
	 * @return
	 */
	@MqtApi
	@Path("task/catalog/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object catalogList( @FormParam("appKey") String appKey,
			 @FormParam("textbookId") Integer textbookId,
			 @FormParam("schoolId") Integer schoolId, @FormParam("userId") Integer userId);


	/**--------------------------------------------------小结相关接口---------------------------------------------------*/

	/**
	 * 获取所有小结单元
	 * @param appKey
	 * @param learningPlanId 导学案id
	 * @param unitType 单元类型
	 * @return
	 */
	@MqtApi
	@Path("activity/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object activityList( @FormParam("appKey") String appKey,
			 @FormParam("learningPlanId") Integer learningPlanId,
			@DefaultValue("11")@FormParam("unitType") Integer unitType);

	/**
	 * 2019-3-13
	 * 获取所有小结单元
	 * @param appKey
	 * @param taskId
	 * @return
	 */
//	@MqtApi
	@Path("activity/unitlist")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object activityUnitList( @FormParam("appKey") String appKey,
						 @FormParam("taskId") Integer taskId);


	/**
	 * 查看小结全部内容（教师）
	 * @param appKey
	 * @param taskId 任务id
	 * @param unitId 单元id
	 * @return
	 */
	@MqtApi
	@Path("task/activity/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskActivityList( @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("unitId") Integer unitId,
			 @FormParam("userId") Integer userId, @FormParam("pageSize") Integer pageSize,
			@FormParam("pageNumber") Integer pageNumber);

	/**
	 * 发表小结
	 * @param appKey
	 * @param taskId
	 * @param unitId
	 * @param userId
	 * @param content
	 * @param files
	 * @return
	 */
	@MqtApi
	@Path("task/activity/user/add")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object acitivityAdd( @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("unitId") Integer unitId,
			 @FormParam("userId") Integer userId,
			 @FormParam("content") String content, @FormParam("files") String files);

	/**
	 * 删除小结
	 * 
	 * @param appKey
	 * @param activityId
	 * @return
	 */
	@MqtApi
	@Path("task/activity/user/delete")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object acitivitydelete(@FormParam("appKey") String appKey,
			 @FormParam("activityId") Integer activityId);

	/**
	 * 获取个人的小结单元内容
	 * @param appKey
	 * @param taskId
	 * @param unitId
	 * @param userId
	 * @return
	 */
	@MqtApi
	@Path("task/activity/user/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object acitivityUserList( @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("unitId") Integer unitId,
			 @FormParam("userId") Integer userId,
			 @DefaultValue("1")@FormParam("pageSize")Integer pageSize,@DefaultValue("11")@FormParam("pageNumber") Integer pageNumber
			);


	/**---------------------------------------------------评卷相关接口-------------------------------------------------*/


	/**
	 * 获取导学案试卷单元
	 * @param appKey
	 * @param taskId
	 * @param subjectCode
	 * @return
	 */
	@MqtApi
	@Path("task/unit/paper/get")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object unitPaperGet( @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId, @FormParam("subjectCode") String subjectCode, @FormParam("type") Integer type);

	/**
	 * 设置互评时获取试卷单元列表
	 * 
	 */
	@MqtApi
	@Path("task/exam/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskExamList(@FormParam("appKey") String appKey,  @FormParam("taskId") Integer taskId);


	/**----------------------------------------------互评相关接口-------------------------------------------*/


	/**
	 * 开启或者关闭导学案互评时提示信息
	 * 
	 */
	@MqtApi
	@Path("task/interscore/isInterscore")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskIsInterscore( @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("unitId") Integer unitId);

	/**
	 * 开启或者关闭导学案互评
	 * 
	 */
	@MqtApi
	@Path("task/interscore/start")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskIsInterscoreStart( @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("unitId") Integer unitId,
			 @FormParam("isInterscore") Integer isInterscore,
			 @FormParam("startTime") String startTime,
			 @FormParam("finishTime") String finishTime);

	/**
	 * 学生互评列表
	 * 
	 * @param userId
	 * @param appKey
	 * @param pageNumber
	 * @param pageSize
	 * @param subjectCode
	 * @return
	 */
	@MqtApi
	@Path("/task/interscore/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object interscoreListBySubject( @FormParam("userId") Integer userId,
			 @FormParam("appKey") String appKey,
			@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,
			@DefaultValue("10") @FormParam("pageSize") Integer pageSize, @FormParam("subjectCode") String subjectCode);

	/**
	 * 互评详情
	 * 
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 */
	@MqtApi
	@Path("/task/interscore/details")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object interscoreDetails( @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("unitId") Integer unitId);

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
	Object taskInterscoreFinish( @FormParam("appKey") String appKey,
			 @FormParam("taskId") Integer taskId,
			 @FormParam("userId") Integer userId,
			 @FormParam("unitId") Integer unitId,
			@DefaultValue("student") @FormParam("userType") String type,
			 @FormParam("data") String data);

	/**
	 * 更新小结批注图片接口
	 * @param appKey
	 * @param activityFileId 单元图片ID
	 * @param markedFileUuid 单元图片UUID
	 * @return
	 */
	@MqtApi
	@Path("task/activity/user/modify")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object acitivityFileModify( @FormParam("appKey") String appKey,
			 @FormParam("activityFileId") Integer activityFileId,
			 @FormParam("markedFileUuid") String markedFileUuid);


}
