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

import platform.education.generalTeachingAffair.vo.StudentScoreData;

@Path("/school/moral/progress/")
public interface NormalEvaRestService {

	/**
	 * 设置 发展评价卡
	 * 
	 * @param termCode
	 * @param teamId
	 * @param teacherId
	 * @param checkDate
	 * @param studentScoreDatas
	 * @return
	 */
	@POST
	@Path("batchSetScore")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object batchSetNormalScores(
			@FormParam("userId") Integer userId,
			@FormParam("schoolId") Integer schoolId,
			@FormParam("termCode") String termCode,
			@FormParam("teamId") Integer teamId,
			@FormParam("checkDate") String checkDate,
			@FormParam("studentScoreDatas") String studentScoreDatas);

	/**
	 * 获取 发展卡总数
	 * 
	 * @param schoolYear
	 * @param termCode
	 * @param teamId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("listByScore")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object findNormalScoresForTeam(@FormParam("schoolYear") String schoolYear,
			@FormParam("termCode") String termCode,
			@FormParam("teamId") Integer teamId,
			@FormParam("checkDate") String checkDate);

	/**
	 * 发展评价卡 班级 评价报表
	 * 
	 * @param teamId
	 * @param termCode
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("summaryByTeam")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object findNormalCountForTeam(@FormParam("teamId") Integer teamId,
			@FormParam("studentId") Integer studentId,
			@FormParam("termCode") String termCode,
			@FormParam("beginDate") String beginDate,
			@FormParam("endDate") String endDate);

	/**
	 * 发展评价卡 年级 评价报表
	 * 
	 * @param gradeId
	 * @param termCode
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("summaryByGrade")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object findNormalCountForGrade(@FormParam("gradeId") Integer gradeId,
			@FormParam("termCode") String termCode,
			@FormParam("beginDate") String beginDate,
			@FormParam("endDate") String endDate);

	/**
	 * 发展评价卡 全校 评价报表
	 * 
	 * @param schoolId
	 * @param termCode
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("summaryBySchool")
	@Consumes({ MediaType.APPLICATION_FORM_URLENCODED })
	@Produces({ MediaType.APPLICATION_JSON })
	Object findNormalCountForSchool(@FormParam("schoolId") Integer schoolId,
			@FormParam("termCode") String termCode,
			@FormParam("beginDate") String beginDate,
			@FormParam("endDate") String endDate);
	
	/**
	 * 发展评价卡 班级 评价报表
	 * 
	 * @param teamId
	 * @param termCode
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("summaryByTeam/jsonp")
	@Produces({ MediaType.APPLICATION_JSON })
	Object findNormalCountForTeam(@QueryParam("teamId") Integer teamId,
			@QueryParam("studentId") Integer studentId,
			@QueryParam("termCode") String termCode,
			@QueryParam("beginDate") String beginDate,
			@QueryParam("endDate") String endDate,
			@QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 发展评价卡 班级/学生 评价报表
	 */
	@GET
	@Path("summaryByStu/jsonp")
	@Produces({ MediaType.APPLICATION_JSON })
	Object findNormalCountForStudent(@QueryParam("teamId") Integer teamId,
			@QueryParam("schoolYear") String schoolYear,
			@QueryParam("termCode") String termCode,
			@QueryParam("beginDate") String beginDate,
			@QueryParam("endDate") String endDate,
			@QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 发展评价卡 年级 评价报表
	 * 
	 * @param gradeId
	 * @param termCode
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("summaryByGrade/jsonp")
	@Produces({ MediaType.APPLICATION_JSON })
	Object findNormalCountForGrade(@QueryParam("gradeId") Integer gradeId,
			@QueryParam("termCode") String termCode,
			@QueryParam("beginDate") String beginDate,
			@QueryParam("endDate") String endDate,
			@QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 发展评价卡 全校 评价报表
	 * 
	 * @param schoolId
	 * @param termCode
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@GET
	@Path("summaryBySchool/jsonp")
	@Produces({ MediaType.APPLICATION_JSON })
	Object findNormalCountForSchool(@QueryParam("schoolId") Integer schoolId,
			@QueryParam("termCode") String termCode,
			@QueryParam("beginDate") String beginDate,
			@QueryParam("endDate") String endDate,
			@QueryParam("jsonpCallback") String jsonpCallback);

}
