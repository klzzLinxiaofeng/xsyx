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

@Path("/school/moral/increase/")
public interface IncentiveEvaRestService {
	
	
	/**
	 * 获取学生列表
	 * @param termCode
	 * @param teamId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("student/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findStudentsHaveIncEva(
			@FormParam("schoolYear") String schoolYear,
			@FormParam("termCode") String termCode, 
			@FormParam("teamId") Integer teamId, 
			@FormParam("checkDate") String checkDate);
	
	
	/**
	 * 录入激励评价
	 * @param studentId
	 * @param teamId
	 * @param teacherId
	 * @param checkDate
	 * @param studentItemDatas
	 * @return
	 */
	@POST
	@Path("batchSetScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchSetIncreaseScores(
			@FormParam("schoolId") Integer schoolId, 
			@FormParam("userId") Integer userId,
			@FormParam("termCode") String termCode,
			@FormParam("teamId") Integer teamId, 
			@FormParam("studentId") Integer studentId, 
			@FormParam("checkDate") String checkDate,
			@FormParam("stuItemDatas") String stuItemDatas);
	
	
	/**
	 * 获取激励评价
	 * @param termCode
	 * @param studentId
	 * @param checkDate
	 * @return
	 */
	@POST
	@Path("listByScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchGetIncreaseScores(
			@FormParam("termCode") String termCode, 
			@FormParam("studentId") Integer studentId,
			@FormParam("checkDate") String checkDate);
	
	
	/**
	 * 查看班级激励评价卡总数
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
	Object findIncreaseScoresForTeam(
			@FormParam("schoolYear") String schoolYear, 
			@FormParam("termCode") String termCode, 
			@FormParam("teamId") Integer teamId, 
			@FormParam("checkDate") String checkDate);
	
	
	/**
	 * 班级评价报表
	 * @param teamId
	 * @param termCode
	 * @param startDate
	 * @param finishDate
	 * @return
	 */
	@POST
	@Path("summaryByTeam")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findIncreaseCountForTeam(
			@FormParam("teamId") Integer teamId, 
			@FormParam("studentId") Integer studentId, 
			@FormParam("termCode") String termCode, 
			@FormParam("beginDate") String beginDate, 
			@FormParam("endDate") String endDate);
	
	
	/**
	 * 年级评价报表
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
	Object findIncreaseCountForGrade(
			@FormParam("gradeId") Integer gradeId,  
			@FormParam("termCode") String termCode, 
			@FormParam("beginDate") String beginDate, 
			@FormParam("endDate") String endDate);
	
	/**
	 * 全校评价报表
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
	Object findIncreaseCountForSchool(
			@FormParam("schoolId") Integer schoolId,  
			@FormParam("termCode") String termCode, 
			@FormParam("beginDate") String beginDate, 
			@FormParam("endDate") String endDate);
	
	
	/**
	 * 班级评价报表
	 */
	@GET
	@Path("summaryByTeam/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object findIncreaseCountForTeam(
			@QueryParam("teamId") Integer teamId, 
			@QueryParam("studentId") Integer studentId, 
			@QueryParam("termCode") String termCode, 
			@QueryParam("beginDate") String beginDate, 
			@QueryParam("endDate") String endDate,
			@QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 年级评价报表
	 */
	@GET
	@Path("summaryByGrade/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object findIncreaseCountForGrade(
			@QueryParam("gradeId") Integer gradeId,  
			@QueryParam("termCode") String termCode, 
			@QueryParam("beginDate") String beginDate, 
			@QueryParam("endDate") String endDate,
			@QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 全校评价报表
	 */
	@GET
	@Path("summaryBySchool/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object findIncreaseCountForSchool(
			@QueryParam("schoolId") Integer schoolId,  
			@QueryParam("termCode") String termCode, 
			@QueryParam("beginDate") String beginDate, 
			@QueryParam("endDate") String endDate,
			@QueryParam("jsonpCallback") String jsonpCallback);
}
