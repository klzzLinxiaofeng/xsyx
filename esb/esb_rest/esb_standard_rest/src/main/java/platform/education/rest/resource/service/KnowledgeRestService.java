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

@Path("/res")
public interface KnowledgeRestService {
	    /**
	     * 推荐资源（微课和课件）
	     * @param userId
	     * @param subjectCode
	     * @param appKey
	     * @param maxSize
	     * @param request
	     * @return
	     * @throws Exception
	     */
		@POST
		@Path("userAction/knowledge/resource/list")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
		Object list(@FormParam("userId") Integer userId, 
				@FormParam("subjectCode") String subjectCode, 
				@FormParam("appKey") String appKey,
				@DefaultValue("20")@FormParam("maxSize") Integer maxSize,
				@Context HttpServletRequest request)throws Exception;
		/**
		 * 推荐题目
		 * @param userId
		 * @param subjectCode
		 * @param appKey
		 * @param maxSize
		 * @param request
		 * @return
		 * @throws Exception
		 */
		@POST
		@Path("userAction/knowledge/question/list")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
		Object questionList(@FormParam("userId") Integer userId, 
				@FormParam("subjectCode") String subjectCode, 
				@FormParam("appKey") String appKey,
				@DefaultValue("10")@FormParam("maxSize") Integer maxSize, 
				@Context HttpServletRequest request)throws Exception;
        /**
         * 学习轨迹，获取全部科目下面的知识点结构图
         * @param userId
         * @param appKey
         * @param request
         * @return
         */
		@POST
		@Path("userAction/knowledge/summary")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
		Object  studyLoad(@FormParam("userId") Integer userId, 
				@FormParam("appKey") String appKey,
				@Context HttpServletRequest request);
		/**
		 * 根据知识点id获取资源
		 * @param knowledgeId
		 * @param type
		 * @param appKey
		 * @param pageSize
		 * @param pageNumber
		 * @param request
		 * @return
		 */
		@POST
		@Path("knowledge/resource/list")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
		Object  KnowledgeOfType(
				@FormParam("knowledgeId") Integer knowledgeId, 
				@FormParam("type") Integer type, 
				@FormParam("appKey") String appKey,
				@DefaultValue("20")@FormParam("pageSize") Integer pageSize, 
				@DefaultValue("1")@FormParam("pageNumber") Integer pageNumber,
				@Context HttpServletRequest request);
		/**
		 * 根据知识点id获取问题
		 * @param knowledgeId
		 * @param appKey
		 * @param pageSize
		 * @param pageNumber
		 * @param request
		 * @return
		 */
		@POST
		@Path("knowledge/question/list")
		@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
		@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
		Object  KnowledgeOfQuestion(
				@FormParam("knowledgeId") Integer knowledgeId,
				@FormParam("appKey") String appKey,
				@DefaultValue("20")@FormParam("pageSize") Integer pageSize, 
				@DefaultValue("1")@FormParam("pageNumber") Integer pageNumber,
				@Context HttpServletRequest request);
		

}
