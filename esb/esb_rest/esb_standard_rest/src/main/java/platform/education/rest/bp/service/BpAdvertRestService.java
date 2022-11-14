package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/bpAdvert/")
public interface BpAdvertRestService {

	/**
	 * 获取某房室最新广告信息
	 * @param roomId
	 * @param pageNumber
	 * @param pageSize
	 * @param sortItem
	 * @param ascending
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("getAdvertList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAdvertList(
            @DefaultValue("-1") @FormParam("roomId") Integer roomId,
            @DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,
            @DefaultValue("1") @FormParam("pageSize") Integer pageSize,
            @DefaultValue("create_date") @FormParam("sortItem") String sortItem,
            @DefaultValue("false") @FormParam("ascending") boolean ascending,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage
    );
	
}
