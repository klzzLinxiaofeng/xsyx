package platform.education.rest.basic.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/school/basic/subject/")
public interface SubjectRestService {
	
	@POST
	@Path("listByGrade")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSexNumber(@FormParam("gradeId") String gradeId, @FormParam("appKey") String appKey);

	/**
	 * 获取学校所有科目
	 * @return
	 */
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAllSubject(@FormParam("schoolId") Integer schoolId);
}
