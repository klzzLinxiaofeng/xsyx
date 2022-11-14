package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Ken
 * @date 2017年4月28日 上午10:33:00
 */
@Path("/bp/bwFile/")
public interface BpBwFileRestService {
	
	/**
	 * 获取上传文件信息列表
	 * @author Ken
	 * @date 2017年5月10日 下午2:32:39
	 * @param id
	 * @param name 
	 * @param teamId 班级id
	 * @param objectType 类型
	 * @param searchId  搜索id 
	 * @param searchType 搜索方式 0:第一次查询; 1:下拉查询; 2:上拉查询
	 * @param pageNumber 页码
	 * @param pageSize 每页条数
	 * @param sortItem 排序字段
	 * @param ascending 是否升序
	 * @return
	 */
	@POST
	@Path("getBpBwFileList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getBpBwFileList(
            @FormParam("id") int id,
            @FormParam("name") String name,
            @DefaultValue("0") @FormParam("teamId") int teamId,
            @DefaultValue("1") @FormParam("objectType") int objectType,
            @FormParam("searchId") Integer searchId,
            @FormParam("searchType") Integer searchType,
            @DefaultValue("1") @FormParam("pageNumber") int pageNumber,
            @DefaultValue("10") @FormParam("pageSize") int pageSize,
            @DefaultValue("create_date") @FormParam("sortItem") String sortItem,
            @DefaultValue("false") @FormParam("ascending") boolean ascending);



	/**
	 * 获取班级相册
	 * @author Ken
	 * @date 2017年5月3日 上午11:17:42
	 * @param teamId
	 * @param objectType
	 * @return
	 */
	@POST
	@Path("findAlbumsList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	@Deprecated
	Object findAlbumsList(
            @DefaultValue("-1") @FormParam("teamId") int teamId,
            @FormParam("searchId") Integer searchId,
            @FormParam("searchType") Integer searchType,
            @DefaultValue("1") @FormParam("pageNumber") int pageNumber,
            @DefaultValue("10") @FormParam("pageSize") int pageSize,
            @DefaultValue("create_date") @FormParam("sortItem") String sortItem,
            @DefaultValue("false") @FormParam("ascending") boolean ascending
    );

	/**
	 * 添加上传文件信息
	 * @author Ken
	 * @date 2017年4月28日 上午10:47:33
	 * @param bpBwFile
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@POST
	@Path("addBpBwFile")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object addBpBwFile(
            @DefaultValue("默认视频文件名称") @FormParam("name") String name,
            @FormParam("teamId") int teamId,
            @FormParam("objectType") int objectType,
            @FormParam("fileId") String fileId,
            @FormParam("postUserId") int postUserId
    ) throws IllegalAccessException, InvocationTargetException;

	/**
	 * 修改上传文件信息
	 * @author Ken
	 * @date 2017年4月28日 下午3:15:57
	 * @param id 上传文件id 必需
	 * @param name 文件名称  不必需
	 * @param isDelete 是否删除 不必需
	 * @return
	 */
	@POST
	@Path("modifyBpBwFile")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object modifyBpBwFile(@FormParam("id") int id,
                          @FormParam("name") String name,
                          @DefaultValue("false") @FormParam("isDelete") boolean isDelete);
}
