package platform.education.rest.ps.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;

@Path("/apts/task/teacher")
public interface AptsTaskTeacherRestService {
	@MqtApi
	@POST
	@Path("/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object list(@FormParam("appKey")String appKey,@FormParam("userId") Integer userId,
			@FormParam("type") Integer type,
			@FormParam("schoolId") Integer schoolId,
			@DefaultValue("10")@FormParam("pageSize") Integer pageSize, 
			@DefaultValue("1")@FormParam("pageNumber") Integer pageNumber
			) throws Exception;
	@MqtApi
	@POST
	@Path("/detail")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object detail(@FormParam("appKey")String appKey,@FormParam("assessmentId") Integer assessmentId
			) throws Exception;
}
