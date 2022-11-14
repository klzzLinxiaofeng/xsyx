package platform.education.rest.basic.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("res")
public interface TextbookRestService {
	
	/**
	 * 根据学校id获取书籍
	 * @param schooolId
	 * @param appKey
	 * @return
	 */
	@Path("textbook/school/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findTextbookList(@FormParam("schoolId") Integer schoolId, @FormParam("appKey")String appKey);
	
	/**
	 * 获取目录
	 * @param textbookId
	 * @param parentId
	 * @param appKey
	 * @return
	 */
	@Path("textbook/catalog/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findTextbookCatalogList(@FormParam("textbookId")Integer textbookId, @FormParam("parentId")Integer parentId, @FormParam("level")Integer level, @FormParam("appKey")String appKey);

	@POST
	@Path("school/hasPermission/learningDesgin/add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
	Object hasXiaoBenCPermission(@FormParam("userId") Integer userId, @FormParam("appKey") String appKey);
	
	/**
	 * 根据学校id获取书籍
	 * @param schooolId
	 * @param appKey
	 * @return
	 */
	@Path("school/subject/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findSubjectList(@FormParam("schoolId") Integer schoolId, @FormParam("userId") Integer userId, @FormParam("appKey")String appKey);

	@Path("textbook/catalog/tree/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findTextbookCatalogTree(@FormParam("textbookId")Integer textbookId, @FormParam("appKey")String appKey);

	@Path("textbook/school/tree/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findSchoolTreeList(@FormParam("schoolId") Integer schoolId, @FormParam("appKey") String appKey);

}
