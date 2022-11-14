package platform.education.rest.gc.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("generalCode")
public interface GeneralCodeRestService {
	
	/**
	 * @function 一次查询多个基础代码分类的所有子项
	 * @param codes
	 *       codes是基础代码表的code值，在这里可以传多个以逗号隔开的形式
	 * @author panfei
	 * @date 2016-7-26     
	 * @return
	 */
	@POST
	@Path("/BatchList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getGeneralCodeList(@FormParam("codes") String codes, @FormParam("appKey") String appKey,@FormParam("version") String version);
	
	/**
	 * @function 获取桌面应用信息
	 * @param appKey
	 * @author panfei
	 * @date 2016-12-27     
	 * @return
	 */
	@POST
	@Path("/getDesktopAppInfo")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getDesktopAppInfo(@FormParam("appKey") String appKey);
	
	/**
	 * @function 获取课程表节次信息
	 * @param appKey
	 * @author panfei
	 * @date 2016-12-27     
	 * @return
	 */
	@POST
	@Path("/getSyllabusSection")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getSyllabusSection(@FormParam("appKey") String appKey);
	
	@POST
	@Path("/getKnolewgeNodeTree")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getKnolewgeNodeTree(
			@FormParam("appKey") String appKey,
			@FormParam("subjectCode") String subjectCode,
			@FormParam("stageCode") String stageCode,
			@FormParam("isGetAllSubject") Boolean isGetAllSubject
			);
	
	@POST
	@Path("/getKnolewgeNodeList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object getKnolewgeNodeList(
			@FormParam("appKey") String appKey,
			@FormParam("subjectCode") String subjectCode,
			@FormParam("stageCode") String stageCode
			);
}
