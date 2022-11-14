/**   
* @Title: PaperWrongQuestionRestService.java
* @Package platform.education.rest.paper.service 
* @Description: 错题本对外暴露业务接口
* @author pantq   
* @date 2017年2月24日 下午1:51:16 
* @version V1.0   
*/
package platform.education.rest.paper.service;

import java.text.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import platform.education.rest.common.annotation.MqtApi;

/** 
* @ClassName: PaperWrongQuestionRestService 
* @Description: 错题本对外暴露业务接口
* @author pantq
* @date 2017年2月24日 下午1:51:16 
*  
*/
@Path("/paper/")
public interface PaperWrongQuestionRestService {

	
	/**
	 * 
	* @Title: findUserWrongList
	* @author pantq 
	* @Description: 根据相关条件查询错题本列表
	* @param userId 用户ID
	* @param subjectCode 科目CODE
	* @param domain 域名
	* @param page
	* @param order
	* @return    设定文件 
	* @return List<WrongPaper>    返回类型 
	* @throws
	 */
	
	@Path("wrong/user/question/list")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object userWrongList (@FormParam("userId") Integer userId, @FormParam("appKey")String appKey, @FormParam("pageSize") Integer pageSize, @FormParam("pageNumber") Integer pageNumber,@FormParam("subjectCode") String subjectCode)throws ParseException;

	@Path("wrong/user/question/select")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object userWrongSelect (@FormParam("userId") Integer userId, @FormParam("appKey")String appKey, @FormParam("subjectCode") String subjectCode, @FormParam("teamId") Integer teamId)throws ParseException;

	
	/**
	 * 
	* @Title: redo
	* @author pantq 
	* @Description: 重做题目提交答案
	* @param userWrongId pa_user_wrong表ID
	* @param appKey 
	* @param answers 包含所有答题答案的JSON数据 
	* @param  questionUuid 题目UUID
	* @param  answer 题目正确答案
	* @param  answerTime 题目答题时间（秒）
	* @param  isCorrect 是否正确
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	@Path("wrong/question/redo")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object redo (@FormParam("userWrongId") Integer userWrongId, @FormParam("appKey")String appKey,
			@FormParam("answers") String answers)throws ParseException;
	
	
	/**
	 * 
	* @Title: delete
	* @author pantq 
	* @Description: 错题本删除
	* @param userWrongId
	* @param appKey
	* @return
	* @throws ParseException    设定文件 
	* @return Object    返回类型 
	* @throws
	 */
	
	@MqtApi
	@Path("wrong/question/delete")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.APPLICATION_JSON})
	Object delete (@FormParam("userWrongId") Integer userWrongId, @FormParam("appKey")String appKey)throws ParseException;

}
