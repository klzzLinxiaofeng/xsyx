package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/personalSearch/")
public interface BpPersonalSearchRestService {

	
	@POST
	@Path("checkUserInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object checkUserInfo(@FormParam("cardId") String cardId, @FormParam("schoolId") Integer schoolId,
                         @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	@POST
	@Path("searchSyllabus")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object searchSyllabus(@FormParam("schoolId") Integer schoolId, @FormParam("userType") Integer userType, @FormParam("userId") Integer userId,
                          @FormParam("appKey") String appKey, @FormParam("signage") String signage);
	
}
