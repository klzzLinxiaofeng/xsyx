package platform.education.rest.jw.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("school/membership")
public interface MembershipRestService {
	
	/**
	 * @function 1.返回学校里各级部门的层级组织关系
	 * 			 2、如果学校是分校，则同时返回与它平级的分校的组织关系，即从上一级返回所有分校组织结构
	 *              pj.general_school  总校，下设分校
	 *              pj.branch_school   分校，级别与学校平行，记录都在school表
	 *              pj.school          独立的学校，记录在school表
	 *              pj.school_zone     校区，学区
	 *              pj.dept            部门
	 *              pj.workgroup       工作组
	 * @param appKey
	 * @param schoolId
	 * @author panfei
	 * @date 2016-8-6
	 * @return
	 */
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getMembership(@FormParam("appKey") String appKey, @FormParam("schoolId") Integer schoolId,@FormParam("isGetDep")Boolean isGetDep);
	
	/**
	 * @function 根据部门获取人员信息
	 * @param appKey
	 * @param departmentId
	 * @author panfei
	 * @date 2016-8-6
	 * @return
	 */
	@POST
	@Path("department/teacher")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getDepartmentTeacher(@FormParam("appKey") String appKey, @FormParam("departmentId") Integer departmentId);
	
	/**
	 * @function 根据学校ID获取学校部门及人员信息
	 * @param appKey
	 * @param departmentId
	 * @author panfei
	 * @date 2016-8-6
	 * @return
	 */
	@POST
	@Path("deps/teachers")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSchoolDepartmentTeachers(@FormParam("appKey") String appKey, @FormParam("schoolId") Integer schoolId);
	
	/**
	 * @function 根据一个或多个班级获取教师信息
	 * @param appKey
	 * @param teamIds
	 * @author panfei
	 * @date 2016-8-6
	 * @return
	 */
	@POST
	@Path("team/teachers")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeamTeachers(@FormParam("appKey") String appKey, @FormParam("teamIds") String teamIds);
	
	/**
	 * @function 根据一个或多个班级获取家长信息
	 * @param appKey
	 * @param teamIds
	 * @author panfei
	 * @date 2016-8-6
	 * @return
	 */
	@POST
	@Path("team/parents")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getTeamParents(@FormParam("appKey") String appKey, @FormParam("teamIds") String teamIds);
}
