package platform.education.rest.open.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.OpenApi;
import platform.education.rest.common.annotation.RequireParam;
import platform.education.rest.common.annotation.TokenType;

@Path("/open/res/knowledge")
public interface KnowledgeRestService {
	
	/**
	 * 获取知识点结构
	 * 根据学段Code、学科Code获取知识点的相关详细信息
	 * @param stageCode 学段编码 
	 * @param subjectCode 学科编码
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object knowledgeList(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("stageCode")String stageCode,
			@RequireParam(true)@FormParam("subjectCode")String subjectCode);
}
