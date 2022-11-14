package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/award/")
public interface BpAwardRestService {

	/**
	 * 班级荣誉列表
	 * @param teamId
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
	Object findTeamAward(@FormParam("teamId") Integer teamId,
                         @FormParam("searchId") Integer searchId,
                         @FormParam("searchType") Integer searchType,
                         @DefaultValue("10") @FormParam("pageSize") String pageSize,
                         @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 增加班级荣誉
	 * @param teamId
	 * @param name
	 * @param awardTime
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createTeamAward")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createTeamAward(@FormParam("teamId") Integer teamId,
                           @FormParam("name") String name,
                           @FormParam("awardTime") Long awardTime,
                           @FormParam("awardImage") String awardImage,
                           @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 删除班级荣誉
	 * @param teamAwardId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("deleteTeamAward")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteTeamAward(@FormParam("teamAwardId") Integer teamAwardId,
                           @FormParam("appKey") String appKey, @FormParam("signage") String signage);
}
