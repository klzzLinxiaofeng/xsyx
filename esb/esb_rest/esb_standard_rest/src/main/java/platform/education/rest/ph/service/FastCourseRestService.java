package platform.education.rest.ph.service;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;

@Path("/course/")
public interface FastCourseRestService {
	

	@MqtApi
	@Path("findTeamStudentGroup")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object examListBySubject (@FormParam("teamId") Integer teamId, @FormParam("appKey")String appKey);
	
	@MqtApi
	@Path("addTeamStudentGroup")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object add (@FormParam("teamId") Integer teamId, @FormParam("appKey")String appKey,@FormParam("data")String data);
	@MqtApi
	@Path("reset")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object reload (@FormParam("teamId") Integer teamId, @FormParam("appKey")String appKey);
//	@MqtApi
	@Path("addData")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object addData (@FormParam("data") String data, @FormParam("appKey")String appKey);

}
