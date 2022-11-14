package platform.education.rest.open.service;

import platform.education.rest.common.annotation.OpenApi;
import platform.education.rest.common.annotation.TokenType;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * 获取单个信息接口
 * @author tangzh
 *
 */
@Path("/open/school/")
public interface BasicSingleRestService {

	/**
	 * 获取单个学校详细信息
	 * @param schoolId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getSchool(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("schoolId") Integer schoolId);
	
	/**
	 * 获取单个年级详细信息
	 * @param gradeId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("grade/get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getGrade(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("gradeId") Integer gradeId);
	
	/**
	 * 获取单个班级详细信息
	 * @param teamId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("team/get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getTeam(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("teamId") Integer teamId);

	/**
	 * 获取单个部门详细信息
	 * @param departmentId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("department/get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getDepartment(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("departmentId") Integer departmentId);

	/**
	 * 获取单个科目详细信息
	 * @param schoolId
	 * @param subjectCode  科目Code
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("subject/get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getSubject(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("schoolId") Integer schoolId, @FormParam("subjectCode") Integer subjectCode);

	/**
	 * 获取单个用户账号详细信息
	 * @param userId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("account/get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getAccount(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("userId") Integer userId);

	/**
	 * 获取单个教师详细信息
	 * @param userId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
    @POST
    @Path("teacher/get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getTeacher(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,  @FormParam("userId") Integer userId);

    /**
     * 获取单个学生详细信息
     * @param userId
     * @return
     */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
    @POST
    @Path("student/get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getStudent(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("userId") Integer userId);

    /**
     * 获取单个家长详细信息
     * @param userId
     * @return
     */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
    @POST
    @Path("parent/get")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object getParent(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp, @FormParam("userId") Integer userId);

}
