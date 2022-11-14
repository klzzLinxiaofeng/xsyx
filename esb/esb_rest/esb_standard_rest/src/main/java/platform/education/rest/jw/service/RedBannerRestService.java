package platform.education.rest.jw.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.generalTeachingAffair.vo.EvaluationMedalData;

@Path("/school/moral/redBanner/")
public interface RedBannerRestService {
	
	
	/**
	 * 获取  红旗班级
	 * @param termCode
	 * @param gradeID
	 * @param periodCode
	 * @return
	 */
	@POST
	@Path("listByEva")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findWeeklyGradeRedBanner(@FormParam("termCode") String termCode, @FormParam("gradeId") Integer gradeId, 
			@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate, @FormParam("periodCode") String periodCode);
	
	
	/**
	 * 评定 红旗
	 * @param termCode
	 * @param gradeID
	 * @param beginDate
	 * @param endDate
	 * @param periodCode
	 * @return
	 */
	@POST
	@Path("evaluate")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object evaluateWeeklyGradeRedBanner(@FormParam("termCode") String termCode, @FormParam("gradeId") Integer gradeId, 
			@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate, @FormParam("periodCode") String periodCode);
	
	
	/**
	 * 设置各年级的获奖分数
	 * @param evaluationMedalDatas
	 * @return
	 */
	@POST
	@Path("batchSetScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchSetRedBannerWeeklyStandardScore(@FormParam("scoreDatas") String scoreDatas);
	
	
	/**
	 * 获取各年级的获奖分数
	 * @param schoolId
	 * @param schoolYear
	 * @return
	 */
	@POST
	@Path("listByScore")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findRedBannerScores(@FormParam("schoolId") Integer schoolId, @FormParam("schoolYear") String schoolYear);
	
	
	/**
	 * 设置红旗评定的标准  （分数/名次）
	 * @param schoolId
	 * @param flag
	 * @return
	 */
	@POST
	@Path("setCriterion")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchSetJudgeCriterion(@FormParam("schoolId") Integer schoolId, @FormParam("schoolYear") String schoolYear,
			@FormParam("criterion") String criterion);
	
}
