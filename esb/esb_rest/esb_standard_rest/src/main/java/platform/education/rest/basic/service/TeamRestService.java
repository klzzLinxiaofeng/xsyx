package platform.education.rest.basic.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/school/basic/team/")
public interface TeamRestService {
	
	/**
	 * 取得一个班级学生各性别总数
	 * 
	 * @param teamId 班级的id
	 * @return
	 * @author hmzhang
	 * @date 2016年07月26日
	 */
	@POST
	@Path("student/sex/number")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSexNumber(@FormParam("teamId") String teamId);
	
	
	/**
	 * 取得一个班级档案完成的信息和未完成的信息
	 * 
	 * @param teamId 班级的id
	 * @return
	 * @author panfei
	 * @date 2016年07月26日
	 */
	@POST
	@Path("student/archive/summary")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getArchiveSummary(@FormParam("teamId") Integer teamId);
	
	/**
	 * 获取班级学生家长名单
	 * 
	 * @param userId
	 * @return
	 * @author zhenxinghui
	 * @data 2016年07月28日
	 */
	@POST
	@Path("parent/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getGradeStudentParentMsg(@FormParam("teamId") String teamId,@FormParam("appKey") String appKey);
	
	/**
	 * @function 获取学生完整的学籍档案
	 * 注参的时候studentId必填，userId
	 * @param userId
	 * @param studentId
	 * @author panfei
	 * @date 2016-7-27
	 * @return
	 */
	@POST
	@Path("student/archive/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentCompleteList(@FormParam("teamId") Integer teamId);
	
	
	/**
	 * 获取班级学生名单
	 * @param teamId
	 * @param jsonpCallback
	 * @return
	 */
	@GET
	@Path("student/list")
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeamStudentList(@QueryParam("teamId") Integer teamId,@QueryParam("jsonpCallback") String jsonpCallback);


	/**
	 * 获取学校所有班级
	 * @return
	 */
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAllTeam(@FormParam("schoolId") Integer schoolId, @FormParam("schoolYear") String schoolYear);

	/**
	 * 获取教师所有科目班级
	 * @return
	 */
	@POST
	@Path("listOfTeacher")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAllTeamOfTeacher(@FormParam("schoolId") Integer schoolId, @FormParam("schoolYear") String schoolYear, @FormParam("userId") Integer userId);

	/**
	 * 获取多个班级的学生名单
	 * @param teamIds
	 * @return
	 */
	@POST
	@Path("students/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentList(@FormParam("teamIds") String teamIds);
}
