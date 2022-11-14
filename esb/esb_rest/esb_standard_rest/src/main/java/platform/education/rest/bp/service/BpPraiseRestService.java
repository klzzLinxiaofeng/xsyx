package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/praise/")
public interface BpPraiseRestService {
	
	/**
	 * 表扬栏
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
	Object findPraise(
            @FormParam("teamId") Integer teamId,
            @FormParam("searchDate") String searchDate,
            @FormParam("searchType") Integer searchType,
            @DefaultValue("10") @FormParam("pageSize") String pageSize,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 获取所有勋章, 供表扬时选择
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("findPraiseMedalsList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findPraiseMedalsList(
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 发布表扬
	 * @param schoolId
	 * @param teamId
	 * @param medalId
	 * @param content
	 * @param students
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createPraise")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createPraise(
            @FormParam("schoolId") Integer schoolId,
            @FormParam("teamId") Integer teamId,
            @FormParam("medalId") Integer medalId,
            @FormParam("content") String content,
            @FormParam("posterId") Integer posterId,
            @FormParam("students") String students,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	@POST
	@Path("deletePraise")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deletePraise(
            @FormParam("praiseId") Integer praiseId,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	
	
}
