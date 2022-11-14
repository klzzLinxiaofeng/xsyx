package platform.education.rest.user.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("public/app")
public interface AppRestService {
	
	/**
	 * @function 获取当前最新版本
	 * @param appKey
	 * @author panfei
	 * @date 2016-8-11
	 * @return
	 */
	@POST
	@Path("/release/getCurrent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getCurrent(@FormParam("appKey") String appKey);
	
	
	/**
	 * @function 获取当前最新版本
	 * @param appKey
	 * @author panfei
	 * @date 2016-8-11
	 * @return
	 */
	@GET
	@Path("/release/getCurrent/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object getCurrent(@QueryParam("appKey") String appKey, @QueryParam("jsonpCallback") String jsonpCallback);
}
