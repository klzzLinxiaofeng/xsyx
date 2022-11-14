package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/")
public interface BpTeamRestService {

	/**
	 * 根据权限获取 教师所教班级
	 * @param teacherId
	 * @param schoolYear
	 * @param type 		1：管理员  2：德育主任  3：班主任  4：科任教师
	 * @return
	 */
	@POST
	@Path("teacher/team/getMyTeams")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getMyTeam(@FormParam("schoolId") String schoolId,
					 @FormParam("userId") String userId, @FormParam("type") String type);


	/**
	 * 获取一个班级的学科
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("subject/listByTeam")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSubject(@FormParam("teamId") String teamId, @FormParam("teamIds") String teamIds);

	/**
	 * 获取一个班级的学生
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("student/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentList(@FormParam("teamId") String teamId);

	/**
	 * 设置一个班级的班长
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("student/setMonitor")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object setMonitor(@FormParam("teamId") Integer teamId,
					  @FormParam("userId") Integer userId);

	/**
	 * 获取一个班级的老师
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("teacher/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeacherList(@FormParam("teamId") String teamId);

	/**
	 * 获取一个学生所在的班级
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("student/teamList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentTeamList(@FormParam("userId") Integer userId);

	/**
	 * 修改头像
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("user/icon/change")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object iconChange(@FormParam("userId") String userId,
					  @FormParam("iconUuid") String iconUuid);

	/**
	 * 获取教室列表
	 * @param schoolId
	 * @return
	 */
	@POST
	@Path("room/getRoomList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getRoomList(@FormParam("schoolId") Integer schoolId);

}
