package platform.education.rest.open.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import platform.education.rest.common.annotation.OpenApi;
import platform.education.rest.common.annotation.RequireParam;
import platform.education.rest.common.annotation.TokenType;

/**
 * @功能描述: 资源操作行为接口
 * @author pantq
 * @eamail:pantuquan@gmail.com
 * @version:1.0
 * @创建时间:2018年1月19日下午3:24:56
 */

@Path("/open/res/")
public interface ResourceActionRestService {

	/**
	 * 根据资源UUID获取资源的相关评论信息.
	 * @param resUuid 资源UUID
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/comment/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object commentListByResUuid(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,@RequireParam(true)@FormParam("resUuid") String resUuid);
	
	
	/**
	 * 用户发表资源评论需提供资源的UUID、用户ID以及评论内容。
	 * @param resUuid 资源UUID
	 * @param userId 用户ID
	 * @param content 评论内容
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/comment/add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object addComment( @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,@RequireParam(true)@FormParam("resUuid")String resUuid,@RequireParam(true)@FormParam("userId")Integer userId,@RequireParam(true)@FormParam("content")String content);
	
	
	/**
	 * 用户可删除自己发表过的资源评论。
	 * @param commentId 评论ID
	 * @param userId 用户ID
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/comment/delete")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object deleteComment( @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,@RequireParam(true)@FormParam("commentId")Integer commentId,@RequireParam(true)@FormParam("userId")Integer userId);
	
	
	
	/**
	 * 用户可对当前访问的未点赞的资源进行点赞操作。
	 * @param resUuid 资源ID
	 * @param userId 用户ID
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/like/add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object addLike(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,@RequireParam(true)@FormParam("resUuid")String resUuid,@RequireParam(true)@FormParam("userId")Integer userId);
	
	
	
	/**
	 * 用户可对当前访问的已点赞的资源进行取消点赞操作.
	 * @param resUuid 资源ID
	 * @param userId 用户ID
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/like/remove")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object removeLike(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,@RequireParam(true)@FormParam("resUuid")String resUuid,@RequireParam(true)@FormParam("userId")Integer userId);
	
	
	

	/**
	 * 根据用户ID、资源类型获取用户相关的资源收藏列表。
	 * @param userId 用户ID
	 * @param resType 资源类型
	 * @param pageNumber 分页的页数，第几页，默认为1
	 * @param pageSize 每页返回记录的数目，默认为20
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/favorite/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object favoriteListByUserIdAndResType(
			 @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("userId")Integer userId,
			@RequireParam(true)@FormParam("resType")String resType,
			@FormParam("content")String content,
			@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,
			@DefaultValue("20") @FormParam("pageSize") Integer pageSize);
	
	/**
	 * 用户可对当前访问的未收藏的资源进行收藏操作。
	 * @param resUuid 资源ID
	 * @param userId 用户ID
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/favorite/add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object addFavorite( @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,@RequireParam(true)@FormParam("resUuid")String resUuid,@RequireParam(true)@FormParam("userId")Integer userId);
	
	
	/**
	 * 用户可对当前访问的已收藏的资源进行取消收藏操作。
	 * @param resUuid 资源ID
	 * @param userId 用户ID
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/favorite/remove")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object removeFavorite( @FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,@RequireParam(true)@FormParam("resUuid")String resUuid,@RequireParam(true)@FormParam("userId")Integer userId);
	
	/**
	 * 用户可对当前访问的资源进行下载操作，此接口主要用于向平台反馈此资源的下载量。
	 * @param resUuid 资源ID
	 * @param userId 用户ID
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/download/add")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object addDownload(@FormParam("Sign") String sign, @FormParam("AppKey") String appKey, @FormParam("Timestamp") String timeStamp,@RequireParam(true)@FormParam("resUuid")String resUuid,@RequireParam(true)@FormParam("userId")Integer userId);
	
	
	/**
	 * 未经授权的用户不能上传校本资源，因此用户在上传校本资源前需要查询此用户是否有上传权限。
	 * @param schoolId 学校ID
	 * @param userId 用户ID
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/permission/get")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object permissionGet(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey, 
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("schoolId")Integer schoolId,
			@RequireParam(true)@FormParam("userId")Integer userId);
	
}
