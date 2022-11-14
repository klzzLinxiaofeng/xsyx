package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/exam/")
public interface BpExamRestService {
	
	/**
	 * 获取考试信息(新)
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("findExamInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findExamInfo(@FormParam("teamId") Integer teamId,
                        @FormParam("currentTime") Long currentTime,
                        @FormParam("searchId") Integer searchId,
                        @FormParam("searchType") Integer searchType,
                        @DefaultValue("10") @FormParam("pageSize") String pageSize,
                        @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 获取考试信息(旧)
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findExamInfo(@FormParam("teamId") Integer teamId,
                        @FormParam("searchId") Integer searchId,
                        @FormParam("searchType") Integer searchType,
                        @DefaultValue("10") @FormParam("pageSize") String pageSize,
                        @FormParam("appKey") String appKey, @FormParam("signage") String signage);


	/**
	 * 添加考试
	 * @return
	 */
	@POST
	@Path("createExam")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createExam(
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage,
            @FormParam("teamId") Integer teamId,
            @FormParam("teacherName") String teacherName,
            @FormParam("subjectCode") String subjectCode,
            @FormParam("room") String room,
            @FormParam("startTime") Long startTime,
            @FormParam("finishTime") Long finishTime,
            @FormParam("schoolId") Integer schoolId,
            @DefaultValue("") @FormParam("content") String content
    );


	/**
	 * 删除考试
	 * @param studyShareId
	 * @return
	 */
	@POST
	@Path("delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object delete(@FormParam("examId") Integer examId,
                  @FormParam("appKey") String appKey, @FormParam("signage") String signage);
}
