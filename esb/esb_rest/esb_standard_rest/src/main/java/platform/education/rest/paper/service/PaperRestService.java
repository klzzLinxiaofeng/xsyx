/**   
* @Title: PaperRestService.java
* @Package platform.education.rest.paper.service 
* @Description: TODO(用一句话描述该文件做什么) 
* @author pantq   
* @date 2017年2月24日 下午1:51:16 
* @version V1.0   
*/
package platform.education.rest.paper.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;

/** 
* @ClassName: PaperRestService 
* @Description: 题库对外暴露业务接口
* @author pantq
* @date 2017年2月24日 下午1:51:16 
*  
*/
@Path("/res/paper/")
public interface PaperRestService {

	/**
	 * 	
	* @Title: uploadPaperAnswer
	* @author pantq 
	* @Description: 试卷应答答案上传
	* @param appKey 调用本接口的App的Key值
	* @param paperUuId 试卷UUID
	* @param userId 用户ID
	* @param answers 包含所有答题答案的JSON数据 
	* @param  questionUuid 题目UUID
	* @param  answer 题目正确答案
	* @param  answerTime 题目答题时间（秒）
	* @param  isCorrect 是否正确
	* @param type 做题类型 默认4导学案,1：作业，2：考试，3：练习
	* @return    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	@MqtApi
	@POST
	@Path("answer/upload")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object uploadPaperAnswer(
			@FormParam("appKey") String appKey,
			@FormParam("paperUuId") String paperUuId,
			@FormParam("teamId")Integer teamId,
			@FormParam("score") Double score,
			@FormParam("userId") Integer userId,
			@FormParam("answers") String answers,
			@FormParam("type") Integer type,
			@FormParam("ownerId") Integer ownerId,
			@FormParam("unitId") Integer unitId) throws Exception;
	
	/**
	 * 
	* @Title: isExistFile
	* @author pantq 
	* @Description: 判断文件是否存在服务器
	* @param fileMd5 文件MD5
	* @param paperUuid 试卷uuid
	* 
	* @return
	* @throws Exception    设定文件 
	* @return PaperResult    返回类型 
	* @throws
	 */
	@MqtApi
	@POST
	@Path("isExistFile")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object isExistFile(@FormParam("appKey") String appKey,
			@FormParam("fileMd5") String fileMd5) throws Exception;
	
	
	/**
	 * 
	* @Title: answerSituation
	* @author pantq 
	* @Description: 个人答题情况
	* @param appKey 
	* @param userId 用户ID
	* @param paperUuId 试卷UUID
	* @param ownerId 任务ID
	* @return
	* @throws Exception    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	@MqtApi
	@POST
	@Path("user/answer/list")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object answerSituation(@FormParam("appKey")String appKey,@FormParam("userId") Integer userId,
			@FormParam("paperUuId") String paperUuId,@FormParam("ownerId") Integer ownerId) throws Exception;
	
	@MqtApi
	@POST
	@Path("test")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object test(@FormParam("appKey")String appKey);
	
	
	/**
	 * 上传试卷前判断同一份资源是否上传到个人或者校本库
	 * @param paperUuid 试卷UUID
	 * @param ownerMode 类型：资源拥有方 0=公开 1=单位学校 2=个人
	 * @return 
	 */
	@MqtApi
	@POST
	@Path("isExistPersonal")
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	public Object isExistPersonal(@FormParam("paperUuid")String paperUuid,@FormParam("ownerMode") Integer ownerMode);
	
}
