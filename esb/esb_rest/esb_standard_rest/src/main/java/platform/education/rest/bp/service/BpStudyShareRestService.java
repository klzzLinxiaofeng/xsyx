package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("bp/studyShare/")
public interface BpStudyShareRestService {
	
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object list(@FormParam("teamId") Integer teamId,
                @FormParam("searchId") Integer searchId,
                @FormParam("searchType") Integer searchType,
                @DefaultValue("10") @FormParam("pageSize") String pageSize,
                @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 添加学习风采
	 * @return
	 */
	@POST
	@Path("createStudyShare")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createStudyShare(
            @FormParam("teamId") Integer teamId,
            @FormParam("title") String title,
            @DefaultValue("") @FormParam("content") String content,
            @FormParam("uuids") String uuids,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 删除学习风采
	 * @param studyShareId
	 * @return
	 */
	@POST
	@Path("deleteStudyShare")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteStudyShare(@FormParam("studyShareId") Integer studyShareId,
                            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
}
