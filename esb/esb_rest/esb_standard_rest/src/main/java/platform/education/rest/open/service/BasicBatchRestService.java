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
 * 获取批量信息接口
 * @author tangzh
 *
 */
@Path("/open/school/")
public interface BasicBatchRestService {

	/**
	 * 获取学校所有年级信息
	 * @param schoolId
	 * @param schoolYear(非必须)
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("grade/find")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object findGrades(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
    		@FormParam("schoolId") Integer schoolId, @FormParam("schoolYear") String schoolYear);

	/**
	 * 获取年级所有班级信息
	 * @param gradeId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("grade/team/find")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object findTeams(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
    		@FormParam("gradeId") Integer gradeId);

	/**
	 * 获取班级所有学生信息
	 * @param teamId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("team/student/find")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object findStudents(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
    		@FormParam("teamId") Integer teamId);

	/**
	 * 获取班级所有任课教师信息
	 * @param teamId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("team/teacher/find")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object findTeachers(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
    		@FormParam("teamId") Integer teamId);

	/**
	 * 获取班级所有家长信息
	 * @param teamId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("team/parent/find")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object findParents(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
    		@FormParam("teamId") Integer teamId);

	/**
	 * 获取教师所有任课班级
	 * @param userId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("teacher/team/find")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object findTeamsByteacher(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
    		@FormParam("userId") Integer userId);
	
	/**
	 * 获取学校所有部门信息
	 * @param schoolId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("department/find")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object findDepartments(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
    		@FormParam("schoolId") Integer schoolId);

	/**
	 * 获取部门的所有部门成员信息
	 * @param departmentId
	 * @return
	 */
	@OpenApi(token = TokenType.OAUTH_TOKEN)
	@POST
    @Path("department/member/find")
    @Consumes({MediaType.APPLICATION_FORM_URLENCODED})
    @Produces({MediaType.APPLICATION_JSON})
    Object findMembersByDepartment(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
    		@FormParam("departmentId") Integer departmentId);

}
