package platform.education.rest.jw.service;

import java.util.Date;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import platform.education.generalTeachingAffair.vo.TeamScoreData;



@Path("/group/summarize")
public interface GroupSummarizeService {
	
	
	/**
	 * 添加总结
	 * @return
	 */
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object addSummarize(@FormParam("appKey") String appKey, @FormParam("userId") Integer userId,
                        @FormParam("taskId") Integer taskId, @FormParam("unitId") Integer unitId,
                        @FormParam("content") String content, @FormParam("files") String[] files);
	
	/**
	 * 获取总结
	 * @return
	 */
	@POST
	@Path("find")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findSummarize(@FormParam("appKey") String appKey, @FormParam("taskId") Integer taskId, @FormParam("unitId") Integer unitId);
	
	/**
	 * 根据groupId获取总结
	 * @return
	 */
	@POST
	@Path("findByGroupId")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findSummarizeByGroupNumber(@FormParam("appKey") String appKey, @FormParam("unitId") Integer unitId,
                                      @DefaultValue ("-1")@FormParam(value = "groupNumber") Integer groupNumber,
                                      @FormParam("taskId") Integer taskId, @FormParam("userId") Integer userId);

	/**
	 * 删除总结
	 * @param termCode
	 * @return
	 */
	@POST
	@Path("delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteSummarize(@FormParam("appKey") String appKey, @FormParam("userActivityId") Integer userActivityId);
	
}



