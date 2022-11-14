package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/teamNotice/")
public interface BpNoticeRestService {
	
	/**
	 * 通知公告
	 * @param teamId
	 * @param beginDate
	 * @param endDate
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
	Object findTeamNotice(@FormParam("teamId") Integer teamId,
                          @FormParam("searchId") Integer searchId,
                          @FormParam("searchType") Integer searchType,
                          @DefaultValue("20") @FormParam("pageSize") String pageSize,
                          @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("createTeamNotice")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createTeamNotice(
            @FormParam("teamId") Integer teamId,
            @FormParam("posterId") Integer posterId,
            @FormParam("posterName") String posterName,
            @FormParam("uuids") String uuids,
            @FormParam("title") String title,
            @FormParam("postTime") Long postTime,
            @FormParam("content") String content,
            @FormParam("startTime") Long startTime,
            @FormParam("finishTime") Long finishTime,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("createTeamNotices")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createTeamNotices(
            @FormParam("teamIds") String teamIds,
            @FormParam("posterId") Integer posterId,
            @FormParam("posterName") String posterName,
            @FormParam("uuids") String uuids,
            @FormParam("title") String title,
            @FormParam("postTime") Long postTime,
            @FormParam("content") String content,
            @FormParam("startTime") Long startTime,
            @FormParam("finishTime") Long finishTime,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	@POST
	@Path("deleteTeamNotice")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteTeamNotice(@FormParam("teamNoticeId") Integer teamNoticeId,
                            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
}
