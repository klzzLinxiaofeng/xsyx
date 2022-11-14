package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/")
public interface BpCourseRestService {

	/**
	 * 学生选科页面
	 * @param teacherId
	 * @param schoolYear
	 * @return
	 */
	@POST
	@Path("courseStudent/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getCourseStudent(@FormParam("schoolId") Integer schoolId,
                            @FormParam("userId") Integer userId,
                            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 学生选科
	 * @param teacherId
	 * @param schoolYear
	 * @return
	 */
	@POST
	@Path("courseStudent/creator")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object creatorCourseStudent(
            @FormParam("courseConfigDetailId") Integer courseConfigDetailId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("userId") Integer userId,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
	
	
	
}
