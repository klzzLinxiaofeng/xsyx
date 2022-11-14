package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/activity/")
public interface BpActivityRestService {

	/**
	 * 班级活动
	 * @param teamId
	 * @param termCode
	 * @param pageNumber
	 * @param pageSize
	 * @param sortItem
	 * @param sortType
	 * @return
	 */
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findActivity(@FormParam("teamId") Integer teamId,
                        @FormParam("termCode") String termCode,
                        @FormParam("searchId") Integer searchId,
                        @FormParam("searchType") Integer searchType,
                        @DefaultValue("10") @FormParam("pageSize") String pageSize,
                        @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("createTeamActivity")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createTeamActivity(
            @FormParam("teamId") Integer teamId,
            @FormParam("posterId") Integer posterId,
            @FormParam("name") String name,
            @FormParam("place") String place,
            @FormParam("image") String image,
            @FormParam("uuid") String uuid,
            @FormParam("startTime") Long startTime,
            @FormParam("finishTime") Long finishTime,
            @FormParam("comment") String comment,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("deleteTeamActivity")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteTeamActivity(@FormParam("activityId") Integer activityId,
                              @FormParam("appKey") String appKey, @FormParam("signage") String signage);
	
}
