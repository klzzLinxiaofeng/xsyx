package platform.education.rest.basic.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("school/basic/grade/")
public interface GradeRestService {
	/**
	 * @function 获取学校某个学年的所有年级
	 * @param schoolId
	 * @param schoolYear
	 * @author panfei
	 * @date 2016-7-26
	 * @return
	 */
	@POST
	@Path("listByYear")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getGradeByYear(@FormParam("schoolId") Integer schoolId,@FormParam("schoolYear") String schoolYear);
	
	@POST
	@Path("teachers")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getGradeTeachers(@FormParam("gradeIds") String gradeIds,@FormParam("schoolYear") String schoolYear,@FormParam("isGetAll")Boolean isGetAll);
}
