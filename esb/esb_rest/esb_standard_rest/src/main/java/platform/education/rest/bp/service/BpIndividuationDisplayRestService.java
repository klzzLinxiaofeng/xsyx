package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/")
public interface BpIndividuationDisplayRestService {

	/**
	 * 
	 * @return
	 */
	@POST
	@Path("individuationDisplay/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getIndividuationDisplay(
            @FormParam("teamId") Integer teamId,
            @FormParam("searchId") Integer searchId,
            @FormParam("searchType") Integer searchType,
            @FormParam("pageSize") Integer pageSize,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 *
	 * @return
	 */
	@POST
	@Path("individuationDisplay/create")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createIndividuationDisplay(
            @FormParam("teamIds") String teamIds,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("title") String title,
            @FormParam("contentType") Integer contentType,//1.文字    2.图片
            @FormParam("content") String content,
            @FormParam("uuids") String uuids,
            @FormParam("isCirculate") String isCirculate,//1.是    0.否
            @FormParam("examDate") Long examDate,
            @FormParam("startTime") Long startTime,
            @FormParam("finishTime") Long finishTime,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 *
	 * @return
	 */
	@POST
	@Path("individuationDisplay/delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteIndividuationDisplay(
            @FormParam("id") Integer id,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 *
	 * @return
	 */
	@POST
	@Path("individuationDisplay/getIndividuationDisplay")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getIndividuationDisplay(
            @FormParam("teamId") Integer teamId,
            @FormParam("currentTime") Long currentTime,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
	
}
