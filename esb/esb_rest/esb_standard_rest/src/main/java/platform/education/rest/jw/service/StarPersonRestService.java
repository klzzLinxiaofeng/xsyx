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

@Path("/school/moral/star/")
public interface StarPersonRestService {
	
	
	/**
	 * 设置 星级个人的人数
	 * @param datas
	 * @param schoolId
	 * @return
	 */
	@POST
	@Path("batchSetCount")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchsetStarReachCount(@FormParam("datas") String datas,@FormParam("schoolId") Integer schoolId);
	
/**
 * 获取星级个人的人数
 * @param schoolId
 * @param termCode
 * @return
 */
	@POST
	@Path("listByCount")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object batchgetStarReachCount(@FormParam("schoolId") Integer schoolId, @FormParam("termCode") String termCode);
	
	
	/**
	 * 获取星级个人
	 * @param termCode
	 * @param objectId
	 * @param type
	 * @param checkDate
	 * @param schoolYear
	 * @return
	 */
	
	@POST
	@Path("listByEva")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getEvaluateStar(@FormParam("termCode") String termCode, @FormParam("objectId") Integer objectId, 
			@FormParam("type") String type,@FormParam("checkDate") String checkDate,
			@FormParam("schoolYear") String schoolYear);
	
	

/**
 * 评定星级个人
 * @param schoolYear
 * @param termCode
 * @param objectId
 * @param type
 * @param checkDate
 * @return
 */
	@POST
	@Path("evaluate")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object evaluatePersonalStar(@FormParam("schoolYear") String schoolYear,@FormParam("termCode")  String termCode, 
			@FormParam("objectId") Integer objectId, @FormParam("type") String type, 
			@FormParam("checkDate") String checkDate);
	
	
}
