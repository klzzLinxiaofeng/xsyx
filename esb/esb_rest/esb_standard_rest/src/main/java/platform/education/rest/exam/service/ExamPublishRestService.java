package platform.education.rest.exam.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/exam/teacher/task")
public interface ExamPublishRestService {
	/**
	 * 教师端任务列表的接口
	 * @param relateId
	 * @param appKey
	 * @param userId
	 * @param subjectCode
	 * @param pageSize
	 * @param pageNumber
	 * @return
	 */
	@Path("/listByTeam")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object list(@FormParam("relateId") Integer relateId, @FormParam("appKey")String appKey,
			@FormParam("userId")Integer userId,
			@FormParam("subjectCode")String subjectCode,
			@DefaultValue("10")@FormParam("pageSize") Integer pageSize, 
			@DefaultValue("1")@FormParam("pageNumber") Integer pageNumber
			);
	/**
	 * 根据删除一个班的发布记录
	 * @param examId
	 * @param appKey
	 * @param publisherId
	 * @param relateId
	 * @return
	 */
	@Path("/deteleByTeam")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object taskDelete(@FormParam("examId") Integer examId, @FormParam("appKey")String appKey,
			@FormParam("microPublishId") String microPublishId,
			@FormParam("relateId") Integer relateId);
}
