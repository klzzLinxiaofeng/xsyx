package platform.education.rest.paper.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;
import platform.education.rest.common.annotation.RequireParam;
@Path("/paper/teacher/task")
public interface PaperTaskRestService {
	/**
	 * 教师端任务列表的接口
	 * @param relateId
	 * @param appKey
	 * @param userId
	 * @param subjectCode
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@MqtApi
	@Path("/listByTeam")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object list(@FormParam("relateId") Integer relateId, @FormParam("appKey")String appKey,
			@FormParam("userId")Integer userId,
			@FormParam("subjectCode")String subjectCode,
			@DefaultValue("10")@FormParam("pageSize") Integer pageSize, 
			@DefaultValue("1")@FormParam("pageNumber") Integer pageNumber
			);
	/**
	 * 根据删除一个班的发布记录
	 * @param examId
	 * @param appKey
	 * @param publisherId
	 * @param relateId
	 * @return
	 */
	@MqtApi
	@Path("/deteleByTeam")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object taskDelete(@FormParam("appKey")String appKey,
			                    @FormParam("examId") Integer examId);
	/**
	 * 设置互评时获取试卷单元列表
	 * 
	 */
	@MqtApi
	@Path("/exam/list")
	@POST
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object taskExamList(@FormParam("appKey") String appKey, @RequireParam(true) @FormParam("taskId") Integer taskId,@RequireParam(true) @FormParam("teamId") Integer teamId);
	
	/**
	 * 教师错题本 旧版
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 */
	@MqtApi
	@Path("/wrong/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object wrong(   @FormParam("appKey")String appKey,
					@FormParam("taskId")Integer taskId,
					@FormParam("teamId")Integer teamId);
	/**
	 * 教师错题本
	 * @param appKey
	 * @param taskId
	 * @param teamId
	 * @return
	 */
	@MqtApi
	@Path("/wrong/add")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object addFavorites( 
					@FormParam("appKey")String appKey,
					@FormParam("isFavorites")Integer isFavorites,
					@FormParam("userId")Integer userId,
					@FormParam("userQuestionId")Integer userQuestionId);
	@MqtApi
	@Path("/wrongBoard/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object wrong(   @FormParam("appKey")String appKey,
					@FormParam("taskId")Integer taskId,
					@FormParam("type")Integer type,
					@FormParam("userId")Integer userId,
					@FormParam("teamId")Integer teamId);
	@MqtApi
	@Path("/wrongBoard/details")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object wrongdetails(
					@FormParam("appKey")String appKey,
					@FormParam("userId")Integer userId,
					@FormParam("examQuestionId")Integer examQuestionId);
}
