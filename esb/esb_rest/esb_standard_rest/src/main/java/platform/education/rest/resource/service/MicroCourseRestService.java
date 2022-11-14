package platform.education.rest.resource.service;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * 微课接口重构
 * @ClassName: MicroCourseRestService 
 * @Description: 微课接口重构
 * @author: hmzhang
 * @date: 2016年10月17日 上午9:55:41
 */
@Path("/res/micro")
public interface MicroCourseRestService {
	
	/**
	 * 获取个人微课列表
	 * @Title: myMicroForJson 
	 * @Description: 获取个人微课列表
	 * @param userId 用户id
	 * @param pageNumber （非必填）当前页码
	 * @param pageSize （非必填）页面显示数
	 * @param title （非必填）按标题模糊查询
	 * @param appKey（非必填）
	 * @param request 
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("listByUser")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object myMicroForJson(@FormParam("userId") Integer userId, 
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@FormParam("title") String title, 
			@FormParam("appKey") String appKey,
			@Context HttpServletRequest request);
	
	
	/**
	 * 删除单个微课
	 * @Title: delete 
	 * @Description: 删除单个微课
	 * @param userId 用户id
	 * @param microId 微课id
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object delete(@FormParam("userId") Integer userId, 
			@FormParam("microId") String microId,
			@FormParam("appKey") String appKey);
	
	/**
	 * 按班级发布微课
	 * @Title: publishLesson 
	 * @Description: 按班级发布微课
	 * @param publisherId 发布者的id
	 * @param startDate 微课开始时间
	 * @param finishedDate 微课结束时间
	 * @param title 标题
	 * @param microList [{ "id": "074b786d61d646389989ad915ab88e6b", "title": "狼牙山五壮士"}]
	 * @param classList [{"relateId": "1", "relateName": "一年级一班[语文]", "relateType": "01"}]
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("publish/addByteam")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object publishLesson(@FormParam("publisherId") Integer publisherId, 
			@FormParam("startDate") String startDate,
			@FormParam("finishedDate") String finishedDate,
			@FormParam("title") String title,
			@FormParam("microList") String microList,
			@FormParam("classList") String classList,
			@FormParam("appKey") String appKey);
	
	/**
	 * 按班级获取已经发布的微课列表
	 * @Title: publishManager 
	 * @Description: 获取已经发布的微课列表
	 * @param userId 用户的userId
	 * @param relateId 查询班级的id
	 * @param pageNumber （非必填）当前页码
	 * @param pageSize （非必填）页面显示数
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("publish/listByTeam")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object publishManager(@FormParam("userId") Integer userId, 
			@FormParam("relateId") Integer relateId,
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@FormParam("appKey") String appKey);
	
	/**
	 * 删除单个已发布的微课
	 * @Title: deletePublished 
	 * @Description: 删除单个已发布的微课
	 * @param publishId 已发布微课的uuid
	 * @param relateId 班级的id
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("publish/delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object deletePublished(@FormParam("publishId") String publishId, 
			@FormParam("relateId") Integer relateId,
			@FormParam("appKey") String appKey);
	
	/**
	 * 获取批量发送评语页面的必要信息
	 * @Title: batchReviews 
	 * @Description: 获取评语页面
	 * @param publishId 已发布微课的uuid
	 * @param relateId 班级的id
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("batchReviews")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object batchReviews(@FormParam("publishId") String publishId, 
			@FormParam("relateId") Integer relateId,
			@FormParam("appKey") String appKey);
	
	/**
	 * 发送评语给学生
	 * @Title: saveReviews 
	 * @Description: 发送评语给学生
	 * @param reviews 评语
	 * @param publishId 已发布微课的uuid
	 * @param reward 评星 eg  1 :1星 2:2星 3:3星
	 * @param studentList 要发送的学生userId数组eg [321,21,234,212]
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("saveReviews")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object saveReviews(@FormParam("reviews") String reviews, 
			@FormParam("publishId") String publishId,
			@FormParam("reward") Integer reward,
			@FormParam("studentList") String studentList,
			@FormParam("appKey") String appKey);
	
	/**
	 * 获取已发布微课详情页的必要数据
	 * @Title: publishDetails 
	 * @Description: 获取已发布微课详情页的必要数据
	 * @param publishId 已发布微课的uuid
	 * @param relateId 班级的id
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("publish/detail")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object publishDetails(@FormParam("publishId") String publishId, 
			@FormParam("relateId") Integer relateId,
			@FormParam("appKey") String appKey);
	
	/**
	 * 微课星主页的数据
	 * @Title: microCourseData 
	 * @Description: 获取微课星主页的数据
	 * @param userId 用户id
	 * @param schoolId 学校id
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("data")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object microCourseData(@FormParam("userId") Integer userId, 
			@FormParam("schoolId") Integer schoolId,
			@FormParam("appKey") String appKey);
	
	/**
	 * ================================================学生端微课接口==================================================
	 */
	/**
	 * 获取某学生学习的微课列表
	 * @Title: studyList 
	 * @Description: 获取某学生学习的微课列表
	 * @param userId （必填）用户id
	 * @param subjectName （非必填）科目名称
	 * @param publishUuid （非必填）作业的uuid
	 * @param pageNumber （非必填）
	 * @param pageSize （非必填）
	 * @param appKey
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("student/studyList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object studyList(@FormParam("userId") Integer userId, 
			@FormParam("subjectName") String subjectName,
			@FormParam("publishUuid") String publishUuid,
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@FormParam("appKey") String appKey);
	
	/**
	 * 提交微课学习进度
	 * @Title: saveRecord 
	 * @Description: 提交微课学习进度
	 * @param microId 当前学习微课的id
	 * @param lastPlayTime （非必填）最后播放时间
	 * @param finishedFlag （必填）完成状态
	 * @param publisherId （非必填）微课发布人
	 * @param userId （必填）当前学习用户的userId
	 * @param publishLessonId （非必填）发布微课的id
	 * @param appKey
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("student/saveRecord")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object saveRecord(@FormParam("microId") String microId, 
			@FormParam("lastPlayTime") String lastPlayTime,
			@FormParam("finishedFlag") String finishedFlag,
			@FormParam("publisherId") String publisherId,
			@FormParam("userId") Integer userId,
			@FormParam("publishLessonId") String publishLessonId,
			@FormParam("appKey") String appKey);
	
	/**
	 * 获得学生的微课消息
	 * @Title: listByStudent 
	 * @date 2016年10月20日18:50:03
	 * @Description: 获得学生的微课消息
	 * @param studentUserId（必填）学生的userId
	 * @param pageNumber （非必填）当前页
	 * @param pageSize （非必填）页面大小
	 * @param sortItem （非必填）排序项
	 * @param sortType （非必填）排序类型
	 * @param appKey
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("student/microMessage/listByStudent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object listByStudent(@FormParam("studentUserId") Integer studentUserId, 
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@DefaultValue("create_date") @FormParam("sortItem") String sortItem,
			@DefaultValue("1") @FormParam("sortType") Integer sortType,
			@FormParam("appKey") String appKey);
	
	/**
	 * 删除消息
	 * @Title: deleteMicroMessage 
	 * @Description: 删除消息
	 * @param id 删除消息的id
	 * @param appKey
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("student/microMessage/delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object deleteMicroMessage(@FormParam("id") Integer id, 
			@FormParam("appKey") String appKey);
	
	/**
	 * 批量更新消息，更新为已读
	 * @Title: updateReadStatus 
	 * @Description: 批量更新消息，更新为已读
	 * @param ids
	 * @param appKey
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("student/microMessage/updateReadStatus")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object updateReadStatus(@FormParam("ids") String ids, 
			@FormParam("appKey") String appKey);
	
	/**
	 * 查询学生未学习的作业列表
	 * @Title: unStudyList 
	 * @Description: 查询学生未学习的作业列表
	 * @param userId （必填）学生用户的id
	 * @param subjectName （非必填）科目名称
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("student/unStudyList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object unStudyList(@FormParam("userId") String userId, 
			@FormParam("subjectName") String subjectName,
			@DefaultValue("1") @FormParam("pageNumber") String pageNumber,
			@DefaultValue("20") @FormParam("pageSize") String pageSize,
			@FormParam("appKey") String appKey);
	
	/**
	 * 获取学生的班级信息
	 * @Title: team 
	 * @Description: 获取学生的班级信息
	 * @param userId
	 * @param appKey
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("student/team")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object team(@FormParam("userId") String userId, 
			@FormParam("appKey") String appKey);
	
	/**
	 * 获取学生的科目
	 * @Title: subject 
	 * @Description: TODO
	 * @param userId
	 * @param appKey
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("student/subject")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object subject(@FormParam("userId") String userId, 
			@FormParam("appKey") String appKey);
	
}
