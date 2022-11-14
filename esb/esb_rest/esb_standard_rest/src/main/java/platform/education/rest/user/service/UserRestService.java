package platform.education.rest.user.service;


import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;


/**
 * 
 * @author hmzhang 2016/07/25
 *
 */
@Path("user")
public interface UserRestService {
	
	
	@POST
	@Path("login")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object login(@FormParam("username") String username, @FormParam("password") String password, @FormParam("appKey") String appKey, @Context HttpServletRequest request);
	
	
	/**
	 * @function 根据用户ＩＤ获取用户的基本信息
	 * @param userId
	 * @date 2016-8-3
	 * @author panfei
	 * @return
	 */
	@POST
	@Path("info/get")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getUserInfo(@FormParam("userId") Integer userId);
	
	/**
	 * @Title: changePassword 
	 * @Description: 客户端修改密码
	 * @param userId
	 * @param password
	 * @param newPassword
	 * @param appKey
	 * @return
	 * @return: Object
	 */
	@POST
	@Path("password/change")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object changePassword(@FormParam("userId") Integer userId, @FormParam("password") String password, @FormParam("newPassword") String newPassword, @FormParam("appKey") String appKey);

	@POST
	@Path("role/get")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getRoleOfUser(@FormParam("userId") Integer userId, @FormParam("appKey") String appKey);
	
	/**
	 * 根据用户id与学校id获取用户信息(仅供教师\家长\学生使用)
	 * @return 用户信息
	 */
	@POST
	@Path("infomation/get")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getUserInfomation(@FormParam("username") String username);
	
	/**
	 * 根据用户ID获取我的学校,罗定邦专属
	 * @param userId
	 * @return 用户学校
	 */

	@POST
	@Path("getMySchool")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getMySchool(@FormParam("userId") Integer userId);


	@POST
	@Path("role/get/encryption")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getUserRoleByEncryption(@FormParam("userId") Integer userId);

}
