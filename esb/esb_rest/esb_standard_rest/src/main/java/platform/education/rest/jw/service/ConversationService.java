package platform.education.rest.jw.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;

/**
 * 师生讨论 2018-11-1
 * @author zhenxinghui
 */
@Path("/teacher/conversation/")
public interface ConversationService {
	
	
	/**
	 * 添加讨论
	 * @param termCode
	 * @return
	 */
//	@MqtApi
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object addConversation(
			@FormParam("appKey") String appKey,
			@FormParam("lpId") Integer lpId,
			@FormParam("taskId") Integer taskId,
			@FormParam("unitId") Integer unitId,
			@FormParam("userId") Integer userId,
			@FormParam("content") String content,
			@FormParam("files") String files,
			@FormParam("degree") Integer degree,
			@FormParam("quote") String quote);
	
	/**
	 * 获取所有讨论
	 * @param termCode
	 * @return
	 */
//	@MqtApi
	@POST
	@Path("find")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findConversation(
			@FormParam("appKey") String appKey,
			@FormParam("lpId") Integer lpId,
			@FormParam("taskId") Integer taskId,
			@FormParam("unitId") Integer unitId);
	
	Object findConversationDate(
			@FormParam("appKey") String appKey,
			@FormParam("lpId") Integer lpId,
			@FormParam("taskId") Integer taskId,
			@FormParam("unitId") Integer unitId);
	
	/**
	 * 获取讨论
	 * @param termCode
	 * @return
	 */
//	@MqtApi
	@POST
	@Path("findByUserId")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findConversationByUserId(
			@FormParam("appKey") String appKey,
			@FormParam("lpId") Integer lpId,
			@FormParam("taskId") Integer taskId,
			@FormParam("unitId") Integer unitId,
			@FormParam("userId") Integer userId);
	
	/**
	 * 删除讨论
	 * @param termCode
	 * @return
	 */
//	@MqtApi
	@POST
	@Path("delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteConversation(
			@FormParam("appKey") String appKey, 
			@FormParam("id") Integer id);
	
}















