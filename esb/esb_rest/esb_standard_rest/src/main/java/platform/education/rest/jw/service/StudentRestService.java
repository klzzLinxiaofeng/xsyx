package platform.education.rest.jw.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/school/student/")
public interface StudentRestService {
	
	/**
	 * @function 获取学生完整的学籍档案
	 * 注参的时候studentId必填，userId
	 * @param userId
	 * @param studentId
	 * @author panfei
	 * @date 2016-7-27
	 * @return
	 */
	@POST
	@Path("archive/getComplete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentComplete(@FormParam("userId") Integer userId,@FormParam("schoolId") Integer schoolId);
	
	/**
	 * 批量获取学生档案信息
	 * @param userIds
	 * @return
	 */
	@POST
	@Path("archive/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentCompleteList(@FormParam("userIds") String userIds);
	
	
	/**
	 * @function 保存学生学籍档案
	 * 注参的时候studentId必填，userId
	 * @param userId
	 * @param studentId
	 * @author panfei
	 * @date 2016-7-27
	 * @return
	 */
	@POST
	@Path("archive/setComplete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object setStudentComplete(@FormParam("data") String data,@FormParam("userId") Integer userId,@FormParam("schoolId") Integer schoolId,@FormParam("isComplet") Boolean isComplet,@FormParam("isBindingMobile") Boolean isBindingMobile);
	
	

	/**
	 * 判断学生档案是否允许进行编辑修改
	 * @param userId
	 * @param schoolId
	 * @return
	 * @author lianzetao
	 * @date  2016-7-29
	 */
	@POST
	@Path("archive/canEdit")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getArchiveEdit(@FormParam("userId") String userId, @FormParam("schoolId") String schoolId);
	
	
	/**
	 * 判断学生档案是否允许进行编辑修改
	 * @param teamId
	 * @return
	 * @author lianzetao
	 * @date  2016-8-3
	 */
	@POST
	@Path("archive/canEditByTeamId")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getArchiveEditByTeamId(@FormParam("teamId") Integer teamId);
	
	
	/**
	 * 获取学生完整的学籍档案相关字段信息
	 * @return
	 * @author lianzetao
	 * @date  2016-7-29
	 */
	@POST
	@Path("archive/getDataFields")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getStudentArchiveFields();
	
	
	/**
	 * 修改 学生档案是否允许进行编辑 的开关
	 * @param teamId
	 * @param interrupteur
	 * @return
	 * @author lianzetao
	 * @date  2016-8-1
	 */
	@POST
	@Path("archive/enableEditing")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object setEnableStudentArchiveEditing(@FormParam("teamId") Integer teamId, @FormParam("interrupteur") Boolean interrupteur);
	
	
}
