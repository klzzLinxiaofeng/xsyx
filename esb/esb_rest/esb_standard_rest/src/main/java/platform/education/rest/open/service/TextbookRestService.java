package platform.education.rest.open.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.OpenApi;
import platform.education.rest.common.annotation.RequireParam;
import platform.education.rest.common.annotation.TokenType;

@Path("/open/res/")
public interface TextbookRestService {

	/**
	 * 获取教材相关学段
	 * 此接口支持获取平台公共教材相关的学段信息，同时也支持获取学校校本教材相关的学段信息。
	 * @param schoolId 学校ID
	 * @param subjectCode 适用学科编码
	 * @param versionCode 版本编码
	 * @return
	 */
	@OpenApi(token = TokenType.NONUSE_TOKEN) 
	@POST
	@Path("textbook/stage/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object stageList(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("schoolId")Integer schoolId,
			@FormParam("subjectCode")String subjectCode,
			@FormParam("versionCode")String versionCode);
	
	/**
	 * 获取教材相关学科
	 * 此接口支持获取平台公共教材相关的学科信息，同时也支持获取学校校本教材相关的学科信息
	 * @param schoolId 学校ID
	 * @param stageCode 学段编码
	 * @param versionCode 版本编码
	 * @return
	 */
	@OpenApi(token = TokenType.NONUSE_TOKEN)
	@POST
	@Path("textbook/subject/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object subjectList(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("schoolId")Integer schoolId,
			@FormParam("stageCode")String stageCode, 
			@FormParam("versionCode")String versionCode);
	
	/**
	 * 获取教材相关版本
	 * 此接口支持获取平台公共教材相关的版本信息，同时也支持获取学校校本教材相关的版本信息
	 * @param schoolId 学校ID
	 * @param stageCode 学段编码
	 * @param versionCode 学科编码
	 * @return
	 */
	@OpenApi(token = TokenType.NONUSE_TOKEN)
	@POST
	@Path("textbook/version/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object versionList(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("schoolId")Integer schoolId,
			@FormParam("stageCode")String stageCode, 
			@FormParam("subjectCode")String subjectCode);
	
	/**
	 * 获取教材相关册次
	 * 接口支持获取平台公共教材相关的册次信息，同时也支持获取学校校本教材相关的册次信息
	 * @param schoolId 学校ID
	 * @param stageCode 学段编码
	 * @param subjectCode 学科编码
	 * @param versionCode 版本编码
	 * @return
	 */
	@OpenApi(token = TokenType.NONUSE_TOKEN)
	@POST
	@Path("textbook/volumn/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object volumnList(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("schoolId")Integer schoolId,
			@FormParam("stageCode")String stageCode, 
			@FormParam("subjectCode")String subjectCode, 
			@FormParam("versionCode")String versionCode);
	
	/**
	 * 获取教材目录
	 * 根据学校ID、学段Code、学科Code、版本Code、册次Code获取教材相关的教材目录详细信息
	 * @param schoolId 学校ID
	 * @param stageCode 学段编码
	 * @param subjectCode 学科编码
	 * @param versionCode 版本编码
	 * @param volumnCode 册次编码
	 * @return
	 */
	@OpenApi(token = TokenType.NONUSE_TOKEN)
	@POST
	@Path("textbook/catalog/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object catalogList(
			@RequireParam(true)@FormParam("Sign") String sign,
			@RequireParam(true)@FormParam("AppKey") String appKey,
			@RequireParam(true)@FormParam("Timestamp") String timeStamp,
			@RequireParam(true)@FormParam("schoolId")Integer schoolId,
			@RequireParam(true)@FormParam("stageCode")String stageCode, 
			@RequireParam(true)@FormParam("subjectCode")String subjectCode, 
			@RequireParam(true)@FormParam("versionCode")String versionCode, 
			@RequireParam(true)@FormParam("volumnCode")String volumnCode);
}
