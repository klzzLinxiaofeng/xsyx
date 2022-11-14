package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/bwPictureAlbum/")
public interface BpPictureAlbumRestService {

	/**
	 * 获取班级相册
	 * @param teamId
	 * @param searchId
	 * @param searchType
	 * @param pageNumber
	 * @param pageSize
	 * @param sortItem
	 * @param ascending
	 * @return
	 */
	@POST
	@Path("getPictureAlbumList")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object getPictureAlbumList(
            @DefaultValue("-1") @FormParam("teamId") Integer teamId,
            @FormParam("searchId") Integer searchId,
            @FormParam("searchType") Integer searchType,
            @DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,
            @DefaultValue("10") @FormParam("pageSize") Integer pageSize,
            @DefaultValue("create_date") @FormParam("sortItem") String sortItem,
            @DefaultValue("false") @FormParam("ascending") boolean ascending,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage
    );

	/**
	 * 添加班级相册
	 * @return
	 */
	@POST
	@Path("createPictureAlbum")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createPictureAlbum(
            @FormParam("postUserId") Integer postUserId,
            @FormParam("teamId") Integer teamId,
            @DefaultValue("") @FormParam("name") String name,
            @FormParam("uuidList") String uuidList,
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage
    );

	/**
	 * 删除班级相册
	 * @param id
	 * @return
	 */
	@POST
	@Path("delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object delete(@FormParam("pictureAlbumId") Integer pictureAlbumId,
                  @FormParam("appKey") String appKey,
                  @FormParam("signage") String signage);


	/**
	 * 向相册添加照片
	 */
	@POST
	@Path("add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object add(@FormParam("pictureAlbumId") Integer pictureAlbumId,
               @FormParam("pictureUuid") String pictureUuid,
               @FormParam("appKey") String appKey);


	/**
	 * 删除相册中的照片
	 */
	@POST
	@Path("deleteFromAlbum")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteFromAlbum(
            @FormParam("pictureIds") String pictureIds,
            @FormParam("appKey") String appKey);
	
}
