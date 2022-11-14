package platform.education.rest.jw.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/school/teacher/")
public interface TeacherRestService {
	
	/**
	 * 根据权限获取 教师所教班级
	 * @param teacherId
	 * @param schoolYear
	 * @param role 		1：管理员  2：德育主任  3：班主任  4：科任教师
	 * @return
	 */
	@POST
	@Path("team/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAllteam(@FormParam("schoolId") Integer schoolId, @FormParam("schoolYear") String schoolYear, 
			@FormParam("userId") Integer userId, @FormParam("role") String role);
	
	/**
	 * 根据权限获取 教师所教班级
	 * @param teacherId
	 * @param schoolYear
	 * @param role 		1：管理员  2：德育主任  3：班主任  4：科任教师
	 * @return
	 */
	@GET
	@Path("team/list/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object getAllteam(@QueryParam("schoolId") Integer schoolId, @QueryParam("schoolYear") String schoolYear, 
			@QueryParam("userId") Integer userId, @QueryParam("role") String role, @QueryParam("jsonpCallback") String jsonpCallback);
	
	/**
	 * 根据教师用户ID获取教师详细的信息
	 * @param userIds
	 * @return
	 * @author panfei
	 */
	@POST
	@Path("detailed/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeacherMess(@FormParam("userIds") String userIds,@FormParam("schoolId") Integer schoolId);
	
	
	/**
	 * 修改教师档案的信息（基本和辅助信息）
	 * @param datas
	 * @return
	 */
	@POST
	@Path("detailed/update")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object setTeacherMess(@FormParam("data") String data, @FormParam("userId") Integer userId,
			@FormParam("schoolId") Integer schoolId, @FormParam("isCompleted") Boolean isCompleted);
	
	
	/**
	 * 导入教师档案的辅助信息
	 * @param teacherInformation
	 * @return
	 */
	@POST
	@Path("assist/update")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object updateTeacherAssistMess(@FormParam("schoolId") Integer schoolId, @FormParam("teacherInformations") String teacherInformations);
	
	
	/**
	 * 根据userId获取单个教师最新的工资明细记录
	 * @param userId
	 * @return
	 */
	@POST
	@Path("salary/get")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeacherSalary(@FormParam("userId") Integer userId);
	
	/**
	 * 获取部门员工指定月份的工资记录表
	 * @param schoolId
	 * @param departmentId
	 * @param year
	 * @param month
	 * @return
	 */
	@POST
	@Path("salary/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeacherSalaryList(@FormParam("schoolId") Integer schoolId, 
			@FormParam("departmentId") Integer departmentId,
			@FormParam("year") Integer year,
			@FormParam("month") Integer month);
	
	/**
	 * 修改单个教师的工资明细项内容
	 * @param userId
	 * @param year
	 * @param month
	 * @param salaryTotal
	 * @param salary
	 * @return
	 */
	@POST
	@Path("salary/update")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object updateTeacherSalary(@FormParam("id") Integer id,
			@FormParam("year") Integer year,
			@FormParam("month") Integer month,
			@FormParam("salaryTotal") Float salaryTotal,
			@FormParam("salaryDetail") String salaryDetail);
	
	/**
	 * 获取工资记录表的明细项数据（表头）
	 * @param schoolId
	 * @return
	 */
	@POST
	@Path("salary/field/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeacherSalaryFieldList(@FormParam("schoolId") Integer schoolId);
	
	/**
	 * 按月份和部门批量导入教师工资表
	 * @param schoolId
	 * @param data
	 * @return
	 */
	@POST
	@Path("salary/import")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object importTeacherSalary(@FormParam("schoolId") Integer schoolId,
			@FormParam("fieldInfo") String fieldInfo,
			@FormParam("data") String data);
	
	/**
	 * 教师档案统计
	 * @param departmentId
	 * @return
	 */
	@POST
	@Path("archives/count")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeacherArchivesCount(@FormParam("departmentId") Integer departmentId);
	
}
