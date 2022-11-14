package platform.education.rest.jw.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import platform.education.generalTeachingAffair.vo.StudentItemData;

@Path("/school/moral/class/")
public interface ClassOptimizingRestService {
	
	
	/**
	 * 获取课堂优化项目
	 * @param termCode
	 * @return
	 */
	@POST
	@Path("listByItems")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findDeductMarksItems(String termCode);
	
	
	/**
	 * 录入学生的课堂优化项目
	 * @param schoolId
	 * @param userId
	 * @param teamId
	 * @param studentId
	 * @param checkDate
	 * @param checkRange
	 * @param stuItemDatas
	 * @return
	 */
	@POST
	@Path("batchSetScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchSetClassScores(@FormParam("schoolId") Integer schoolId, @FormParam("userId") Integer userId, @FormParam("termCode") String termCode,
			@FormParam("teamId") Integer teamId, @FormParam("studentId") Integer studentId, @FormParam("checkDate") String checkDate, 
			@FormParam("checkRange") String checkRange, @FormParam("stuItemDatas") String stuItemDatas);
	
	
	/**
	 * 获取学生的课堂优化项目
	 * @param termCode
	 * @param studentId
	 * @param checkDate
	 * @param checkRange
	 * @return
	 */
	@POST
	@Path("listByScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchGetClassScores(@FormParam("termCode") String termCode, @FormParam("studentId") Integer studentId, 
			@FormParam("checkDate") String checkDate, @FormParam("checkRange") String checkRange);
	
	
	/**
	 * 获取 已进行课堂优化评价  的学生
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @param checkRange
	 * @return
	 */
	@POST
	@Path("student/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findStudentsHaveClassEva(@FormParam("schoolYear") String schoolYear, @FormParam("termCode") String termCode, 
			@FormParam("teamId") Integer teamId, @FormParam("checkDate") String checkDate, @FormParam("checkRange") String checkRange);
	
	
	/**
	 * 查看一周的课堂优化数据
	 * @param schoolYear
	 * @param termCode
	 * @param teamId
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	@POST
	@Path("listByTeam")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findClassScoresForTeam(@FormParam("termCode") String termCode, @FormParam("teamId") Integer teamId, 
			@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);
	
	
	/**
	 * 班级 评价报表
	 * @param termCode
	 * @param teamId
	 * @param studentId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("summaryByTeam")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findClassOptimizingCountForTeam( @FormParam("termCode") String termCode, @FormParam("teamId") Integer teamId,
			 @FormParam("studentId") Integer studentId, @FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);

	
	
	/**
	 * 年级 评价报表
	 * @param gradeId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	@POST
	@Path("summaryByGrade")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findClassOptimizingCountForGrade(@FormParam("termCode") String termCode, @FormParam("gradeId") Integer gradeId,
			@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);
	
	/**
	 * 全校 评价报表
	 * @param schoolId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	@POST
	@Path("summaryBySchool")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findClassOptimizingCountForSchool(@FormParam("termCode") String termCode, @FormParam("schoolId") Integer schoolId, 
			@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);
	
	/**
	 * 班级 评价报表
	 * @param termCode
	 * @param teamId
	 * @param studentId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("summaryByTeam/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object findClassOptimizingCountForTeam( @QueryParam("termCode") String termCode, @QueryParam("teamId") Integer teamId,
			@QueryParam("studentId") Integer studentId, @QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate
			, @QueryParam("jsonpCallback") String jsonpCallback);
	
	
	
	/**
	 * 年级 评价报表
	 * @param gradeId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	@GET
	@Path("summaryByGrade/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object findClassOptimizingCountForGrade(@QueryParam("termCode") String termCode, @QueryParam("gradeId") Integer gradeId,
			@QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate, @QueryParam("jsonpCallback") String jsonpCallback);
	
	/**
	 * 全校 评价报表
	 * @param schoolId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	@GET
	@Path("summaryBySchool/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object findClassOptimizingCountForSchool(@QueryParam("termCode") String termCode, @QueryParam("schoolId") Integer schoolId, 
			@QueryParam("beginDate") String beginDate, @QueryParam("endDate") String endDate, @QueryParam("jsonpCallback") String jsonpCallback);
	
}
