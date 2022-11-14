package platform.education.rest.bp.service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/bp/notice/")
public interface BpInfoMoreRestService {
	
	/**
	 * 获取寻物启事列表
	 * @param teamId
	 * @return
	 */
	@POST
	@Path("lost/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findLost(@FormParam("teamId") Integer teamId,
                    @FormParam("searchId") Integer searchId,
                    @FormParam("searchType") Integer searchType,
                    @DefaultValue("10") @FormParam("pageSize") String pageSize,
                    @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 添加寻物启事
	 * @return
	 */
	@POST
	@Path("lost/create")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object create(
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage,
            @FormParam("teamId") Integer teamId,
            @DefaultValue("") @FormParam("content") String content
    );

	/**
	 * 添加寻物启事(选多个班)
	 * @return
	 */
	@POST
	@Path("lost/creates")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object creates(
            @FormParam("appKey") String appKey,
            @FormParam("signage") String signage,
            @FormParam("teamIds") String teamIds,
            @DefaultValue("") @FormParam("content") String content
    );

	/**
	 * 删除寻物启事
	 * @param studyShareId
	 * @return
	 */
	@POST
	@Path("delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object delete(@FormParam("noticeId") Integer noticeId,
                  @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 获取电子板报列表
	 * @param teamId
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("blackBoard/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object findBlackBoardNews(@FormParam("teamId") Integer teamId,
                              @FormParam("searchId") Integer searchId,
                              @FormParam("searchType") Integer searchType,
                              @DefaultValue("1") @FormParam("pageSize") String pageSize,
                              @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 添加电子板报
	 * @param teamId
	 * @param content
	 * @param file 文件uuid
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("createBlackBoardNews")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object createBlackBoardNews(
            @FormParam("teamId") Integer teamId,
            @FormParam("content") String content,
            @FormParam("file") String file,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);

	/**
	 * 删除电子板报
	 * @param id 数据id
	 * @param appKey
	 * @param signage
	 * @return
	 */
	@POST
	@Path("deleteBlackBoardNews")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object deleteBlackBoardNews(
            @FormParam("id") Integer id,
            @FormParam("appKey") String appKey, @FormParam("signage") String signage);
}
