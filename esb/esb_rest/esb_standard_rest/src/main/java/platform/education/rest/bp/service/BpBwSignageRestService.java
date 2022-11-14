package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("bp/signage/")
public interface BpBwSignageRestService {
	
	@POST
	@Path("binddingSignage")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	@Deprecated
	Object binddingSignage(@FormParam("schoolId") Integer schoolId, @FormParam("teamId") Integer teamId,
                           @FormParam("name") String name, @FormParam("createUserId") Integer createUserId, @FormParam("appKey") String appKey);

	@POST
	@Path("binddingSignageByRoom")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object binddingSignageByRoom(@FormParam("schoolId") Integer schoolId, @FormParam("roomId") Integer roomId,
                                 @FormParam("roomTypeCode") String roomTypeCode, @FormParam("name") String name,
                                 @FormParam("createUserId") Integer createUserId, @FormParam("appKey") String appKey);

	@POST
	@Path("binddingSignageByRoomWithNoHuanxin")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	@Deprecated
	Object binddingSignageByRoomWithNoHuanxin(@FormParam("schoolId") Integer schoolId, @FormParam("roomId") Integer roomId,
                                              @FormParam("roomTypeCode") String roomTypeCode, @FormParam("name") String name,
                                              @FormParam("createUserId") Integer createUserId, @FormParam("appKey") String appKey);
	
}
