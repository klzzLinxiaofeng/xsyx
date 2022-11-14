package platform.education.rest.basic.service;

import platform.education.rest.common.annotation.MqtApi;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("school/basic/term/")
public interface TermRestService {
	
	/**
	 * @function 返回学校所有学年
	 * @param schoolid
	 * @author panfei
	 * @date 2016-7-26
	 * @return
	 */
	@POST
	@Path("year/listAll")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAllSchoolYear(@FormParam("schoolId") Integer schoolId);
	
	/**
	 * 返回学校所有学期
	 * @param schoolId
	 * @return
	 */
	@POST
	@Path("listAll")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getAllSchoolTerm(@FormParam("schoolId") Integer schoolId);


	/**
	 * 返回所有学期（加密，用于成绩分析）
	 * @param schoolId
	 * @return
	 */
	@MqtApi
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getSchoolTerm(@FormParam("schoolId") Integer schoolId);
	
	
	/**
	 * 返回学校所有学期
	 * @param schoolId
	 * @return
	 */
	@GET
	@Path("listAll/jsonp")
	@Produces({MediaType.APPLICATION_JSON})
	Object getAllSchoolTerm(@QueryParam("schoolId") Integer schoolId, @QueryParam("jsonpCallback") String jsonpCallback);
	
	
	/**
	 * 返回学期的所有周次
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("week/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getWeek(@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);
	
	
	/**
	 * 返回学期的所有周次
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("month/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getMonth(@FormParam("beginDate") String beginDate, @FormParam("endDate") String endDate);
}
