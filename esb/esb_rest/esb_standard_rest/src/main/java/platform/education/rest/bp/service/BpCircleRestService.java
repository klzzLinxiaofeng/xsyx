package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/circle/")
public interface BpCircleRestService {

	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findCircle(
            @FormParam("teamId") Integer teamId,
            @FormParam("searchId") Integer searchId,
            @FormParam("searchType") Integer searchType,
            @DefaultValue("10") @FormParam("pageSize") String pageSize,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 添加班级动态
	 * @param teamId
	 * @param posterId
	 * @param content
	 * @param entityId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createCircleMessage")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createCircleMessage(
            @FormParam("teamId") Integer teamId,
            @FormParam("posterId") Integer posterId,
            @FormParam("content") String content,
            @FormParam("files") String files,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 删除班级动态
	 * @param id
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("deleteCircleMessage")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteCircleMessage(
            @FormParam("id") Integer id,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
	
}
