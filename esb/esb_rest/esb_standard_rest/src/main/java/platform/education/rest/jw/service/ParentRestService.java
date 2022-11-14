package platform.education.rest.jw.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/school/parent/")
public interface ParentRestService {
	
	/**
	 * 根据家长的userId获取学生的信息
	 * 
	 * @param userId 
	 * @param schoolId
	 * @return
	 * @author hmzhang
	 * @date 2016年07月26日
	 */
	@POST
	@Path("children/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getMyChild(@FormParam("userId") String userId, @FormParam("schoolId") String schoolId);
	
	/**
	 * 获取家长的所有子女及其在校信息
	 * 
	 * @param userId
	 * @return
	 * @author zhenxinghui
	 * @data 2016年07月28日
	 */
	@POST
	@Path("children/listWithSchool")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentAtSchoolMsg(@FormParam("userId") String userId,@FormParam("appKey") String appKey);
	
	/**
	 * 获取子女的所有家长记录
	 * 
	 * @param userId
	 * @return
	 * @author zhenxinghui
	 * @data 2016年07月28日
	 */
	@POST
	@Path("listByChild")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentParentMsg(@FormParam("userId") String userId,@FormParam("appKey") String appKey);
	
	/**
	 * 获取家长的所有子女及其在校信息(跨域)
	 * @param userId
	 * @param jsonpCallback
	 * @return
	 */
	@GET
	@Path("children/listWithSchool/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentAtSchoolByJSONP(
			@QueryParam("userId") Integer userId,  
			@QueryParam("jsonpCallback") String jsonpCallback);
}
