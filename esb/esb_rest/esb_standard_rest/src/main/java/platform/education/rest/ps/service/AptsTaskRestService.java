package platform.education.rest.ps.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;

@Path("/apts/task/student")
public interface AptsTaskRestService {
	@MqtApi
	@POST
	@Path("/today/assessment")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object todayAssessment(@FormParam("appKey")String appKey,@FormParam("userId") Integer userId
			) throws Exception;
	@MqtApi
	@POST
	@Path("/assessment/board")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object assessmentBoard(@FormParam("appKey")String appKey,@FormParam("userId") Integer userId,@FormParam("assessmentId") Integer assessmentId
			) throws Exception;
	@MqtApi
	@POST
	@Path("/add/assessment")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object addAssessmen(@FormParam("appKey")String appKey,@FormParam("assessmentData") String assessmentData
			) throws Exception;
	@MqtApi
	@POST
	@Path("/my/assessmentTask")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object myAssessmentTask(@FormParam("appKey")String appKey,@FormParam("userId") Integer userId,
			@DefaultValue("10")@FormParam("pageSize") Integer pageSize, 
			@DefaultValue("1")@FormParam("pageNumber") Integer pageNumber
			) throws Exception;
}
