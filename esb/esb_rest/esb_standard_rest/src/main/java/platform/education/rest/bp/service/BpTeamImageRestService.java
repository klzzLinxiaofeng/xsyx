package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/teamImage/")
public interface BpTeamImageRestService {
	
	@POST
	@Path("createTeamImage")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createTeamImage(
            @FormParam("teamId") Integer teamId,
            @FormParam("signature") String signature,
            @FormParam("backgroundFile") String backgroundFile,
            @FormParam("backgroundTemplate") String backgroundTemplate,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 查看班级形象
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getTeamImage")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeamImage(@FormParam("teamId") Integer teamId,
                        @FormParam("appKey") String appKey, @FormParam("signage") String signage);

}
