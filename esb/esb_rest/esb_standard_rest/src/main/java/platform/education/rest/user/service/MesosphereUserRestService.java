package platform.education.rest.user.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;


/**
 * 登录中间层业务接口
 * @author pantq
 *创建时间：2017-11-02 19:20
 */
@Path("mesosphere/user")
public interface MesosphereUserRestService {
	
	/**
	 * 登录入口
	 * @param username 用户名
	 * @param password 密码
	 * @param appKey   
	 * @param userType 用户类型 1：教师，2：学生   
	 * @param timestamp 时间戳
	 * @param signature 数字签名
	 * @return
	 */
	@MqtApi
	@POST
	@Path("login")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object mesosphereLogin(@FormParam("username") String username, @FormParam("password") String password, @FormParam("appKey") String appKey,@FormParam("userType") String userType);
	
	
	/**
	 * 登录入口
	 * @param username 用户名
	 * @param password 密码
	 * @param appKey   
	 * @param userType 用户类型 1：教师，2：学生   
	 * @param timestamp 时间戳
	 * @param signature 数字签名
	 * @return
	 */
	@MqtApi
	@POST
	@Path("teaching/login")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object teachingLogin(@FormParam("username") String username, @FormParam("password") String password, @FormParam("appKey") String appKey);
	
	
}
