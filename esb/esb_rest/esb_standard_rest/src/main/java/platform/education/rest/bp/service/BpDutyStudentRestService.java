package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/dutyStudent/")
public interface BpDutyStudentRestService {
	
	/**
	 * 值日生
	 * @param teamId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findDutyStudent(
            @FormParam("teamId") Integer teamId,
            @FormParam("startDate") Long startDate,
            @FormParam("endDate") Long endDate,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage
    );

	@POST
	@Path("createDutyStudent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createDutyStudent(
            @FormParam("teamId") Integer teamId,
            @FormParam("dutyDate") Long dutyDate,
            @FormParam("students") String students,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 值日生
	 * @param teamId
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@POST
	@Path("getDutyStudent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getDutyStudent(
            @FormParam("teamId") Integer teamId,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage
    );

	@POST
	@Path("createWeekDutyStudent")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createWeekDutyStudent(
            @FormParam("teamId") Integer teamId,
            @FormParam("schoolId") Integer schoolId,
            @FormParam("students") String students,
            @FormParam("week") Integer week,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
}
