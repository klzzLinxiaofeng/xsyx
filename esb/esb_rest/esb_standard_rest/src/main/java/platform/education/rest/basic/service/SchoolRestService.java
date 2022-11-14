package platform.education.rest.basic.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/school/")
public interface SchoolRestService {
	
	/**
	 * 根据学校id获取服务器地址信息
	 * @param schoolId
	 * @return
	 */
	@POST
	@Path("serverAddress/get")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSchoolServerManage(@FormParam("schoolId") Integer schoolId);
	
	
	/**
	 * 根据学校名称获取服务器地址信息
	 * @param schoolName
	 * @return
	 */
	@POST
	@Path("serverAddress/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSchoolServerManageBySchoolName(@FormParam("schoolName") String schoolName);
	
}
