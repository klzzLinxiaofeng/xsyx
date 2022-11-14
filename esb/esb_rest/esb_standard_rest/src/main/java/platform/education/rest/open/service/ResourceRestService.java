package platform.education.rest.open.service;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import platform.education.rest.common.annotation.OpenApi;
import platform.education.rest.common.annotation.RequireParam;
import platform.education.rest.common.annotation.TokenType;

@Path("/open/res/")
public interface ResourceRestService {

	/**
	 * 根据教材目录code、资源类型获取教材目录的相关资源列表
	 * @param catalogCode 教材目录code
	 * @param resType 资源类型
	 * @param pageNumber 分页的页数，第几页，默认为1
	 * @param pageSize 每页返回记录的数目，默认为20
	 * @return
	 */
	@OpenApi(token = TokenType.NONUSE_TOKEN)
	@POST
	@Path("textbook/resource/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object listByCatalog(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("catalogCode")String catalogCode,
			@RequireParam(true)@FormParam("resType")String resType,
			@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,
			@DefaultValue("20") @FormParam("pageSize") Integer pageSize,
			@FormParam("sortItem")@DefaultValue("create_date")String sortItem,
			@FormParam("sortType")@DefaultValue("0")Integer sortType);
	
	
	/**
	 * 根据知识节点ID、资源类型获取知识点的相关资源列表
	 * @param nodeId 知识节点ID
	 * @param resType 资源类型
	 * @param pageNumber 分页的页数，第几页，默认为1
	 * @param pageSize 每页返回记录的数目，默认为20
	 * @return
	 */
	@OpenApi(token = TokenType.NONUSE_TOKEN)
	@POST
	@Path("knowledge/resource/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object listByKnowledge(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("nodeId")Integer nodeId,
			@RequireParam(true)@FormParam("resType")String resType,
			@DefaultValue("1") @FormParam("pageNumber") Integer pageNumber,
			@DefaultValue("20") @FormParam("pageSize") Integer pageSize);
	
	
	/**
	 * 根据实体资源ID、资源类型获取实体资源文件的相关详细信息
	 * @param objectId 资源实体记录ID
	 * @param resType 资源类型
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("resource/get")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object getDetail(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("resUuid")String resUuid,
			@RequireParam(true)@FormParam("userId")Integer userId,
			@FormParam("Token")String Token);
	
	
	/**
	 * 根据学校ID、文本内容（资源名称等）、资源类型等条件查询教材目录的相关资源列表。
	 * @param schoolId 学校ID
	 * @param content 文本内容
	 * @param stageCode 学段Code
	 * @param subjectCode 学科Code
	 * @param resType 资源类型
	 * @param pageNumber 分页的页数
	 * @param pageSize 每页返回记录的数目
	 * @param sortItem 排序的项
	 * @return
	 */
	@OpenApi(token = TokenType.NONUSE_TOKEN)
	@POST
	@Path("textbook/resource/need/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object resourceNeedList(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("schoolId")Integer schoolId,
			@FormParam("content")String content,
			@FormParam("stageCode")String stageCode,
			@FormParam("subjectCode")String subjectCode,
			@RequireParam(true)@FormParam("resType")String resType,
			@FormParam("pageNumber")@DefaultValue("1")Integer pageNumber,
			@FormParam("pageSize")@DefaultValue("20")Integer pageSize,
			@FormParam("sortItem")@DefaultValue("create_date")String sortItem,
			@FormParam("sortType")@DefaultValue("0")Integer sortType);
	
	
	/**
	 * 根据用户ID、资源类型、文本内容（资源名称等）获取用户相关的个人资源列表
	 * @param userId 用户ID
	 * @param resType 资源类型
	 * @param content 文本内容
	 * @param pageNumber 分页的页数
	 * @param pageSize 每页返回记录的数目
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/personal/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object personalList(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("userId")Integer userId,
			@RequireParam(true)@FormParam("resType")String resType,
			@FormParam("content")String content,
			@FormParam("pageNumber")@DefaultValue("1")Integer pageNumber,
			@FormParam("pageSize")@DefaultValue("20")Integer pageSize,
			@FormParam("Token")String Token);
	
	/**
	 * 用户可上传单个资源文件到个人资源库，多个资源文件多次调用接口上传。
	 * @param userId 用户ID
	 * @param resData 包含资源相关信息的JSON对象
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/personal/upload")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object personalUpload(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("userId")Integer userId,
			@RequireParam(true)@FormParam("resData")String resData,
			@FormParam("Token")String Token);
	
	/**
	 * 用户可上传单个资源文件到个人资源库，多个资源文件多次调用接口上传。
	 * @param userId 用户ID
	 * @param resData 包含资源相关信息的JSON对象
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/school/upload")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object schoolUpload(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("userId")Integer userId,
			@RequireParam(true)@FormParam("resData")String resData,
			@FormParam("Token")String Token);
	
	/**
	 * 用户可上传单个资源文件到个人资源库，多个资源文件多次调用接口上传。
	 * @param userId 用户ID
	 * @param resData 包含资源相关信息的JSON对象
	 * @return
	 * @throws IOException 
	 * @throws ServletException 
	 */
	//@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/file/part/upload")
	@Consumes({MediaType.MULTIPART_FORM_DATA+";charset=ISO-8859-1"})
	@Produces({MediaType.APPLICATION_JSON})
	public Object fileUpload(MultipartFormDataInput input);
	
	/**
	 * 断点续传接口
	 * @param input 文件
	 * @return
	 */
	//@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/file/upload")
	@Consumes({MediaType.MULTIPART_FORM_DATA+";charset=ISO-8859-1"})
	@Produces({MediaType.APPLICATION_JSON})
	public Object filePartUpload(MultipartFormDataInput input);
	
	/**
	 * 判断文件是否已经存在
	 * @param md5 文件md5
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/file/exist")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object fileIsExit(@RequireParam(true)@FormParam("md5")String md5);

	/**
	 * 计算文件md5
	 * @param input 文件
	 * @return
	 * @throws IOException
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/file/md5")
	@Consumes({MediaType.MULTIPART_FORM_DATA+";charset=ISO-8859-1"})
	@Produces({MediaType.APPLICATION_JSON})
	Object getM5d(MultipartFormDataInput input) throws IOException;
	
	/**
	 * 修改资源信息接口
	 * @param md5 文件md5
	 * @return
	 */
	@OpenApi(token = TokenType.SINGLE_SIGN_ON_TOKEN)
	@POST
	@Path("action/resource/update")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object resourceUpdate(@RequireParam(true)@FormParam("resUuid")String resUUid,
			@RequireParam(true)@FormParam("userId")Integer userId,
			@RequireParam(true)@FormParam("resType")Integer resType,
			@FormParam("title")String title,
			@FormParam("description")String description,
			@FormParam("catalogCode")String catalogCode,
			@FormParam("fileUuid")String fileUuid);

}
