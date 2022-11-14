package platform.education.paper.service;

import java.util.List;
import java.util.Map;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.AnswerSituationResult;
import platform.education.paper.model.PaperResult;
import platform.education.paper.model.UserFile;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.model.UserRank;
import platform.education.paper.model.UserWrong;
import platform.education.resource.model.UserAction;
import platform.education.resource.model.UserKnowledgeSummary;

/**
 * 
* @ClassName: PaperHandleService 
* @Description: 试题处理接口类 
* @author pantq
* @date 2017年2月23日 下午3:06:45 
*
 */
public interface PaperHandleService {

	/**
	 * 
	* @Title: paperSplit
	 *
	 *@author quan
	* @Description: 拆分试题接口 
	* @param userId 用户ID
	* @param filePath 文件路径
	* @param targerPath 目标路径
	* @param fileUuid 文件UUID
	* @param fileMd5 文件MD5
	* @param fileSize 文件大小
	* @param versionCode 版本CODE
	* @param stageCode 学段CODE
	* @param categoryCode 目录CODE
	* @param @return
	* @param @throws Exception    设定文件 
	* @return PaperResult   pa_paper表的主键ID
	* @throws
	 */
	public PaperResult paperSplit(Integer userId,String filePath, String targerPath, String fileUuid, String fileMd5, Long fileSize,String versionCode,String stageCode,String categoryCode) throws Exception;
	
	/**
	* @Title: uploadPaperAnswer
	* @author pantq 
	* @Description: 试卷应答答案上传
	* @param appKey 调用本接口的App的Key值
	* @param paperUuId 试卷UUID
	* @param teamId 班级ID
	* @param userId 用户ID
	* @param score 得分
	* @param answers 包含所有答题答案的JSON数据 
	* @param 	questionUuid 题目UUID
	* @param 	answer 题目正确答案
	* @param 	answerTime 题目答题时间（秒）
	* @param type 做题类型 默认4导学案,1：作业，2：考试，3：练习
	* @param ownerId 任务ID
	* @return    设定文件 
	* @return PaperResult    返回类型 
	* @throws
	 */
	public PaperResult uploadPaperAnswer(String paperUuId,Integer teamId,Double score, Integer userId, String answers,Integer type,Integer ownerId,Integer  resourceType,String schoolYear,String termCode,Integer unitId) throws Exception;
	
	
	
	
	/**
	 * 
	* @Title: isExistFile
	* @author pantq 
	* @Description: 判断文件是否存在服务器
	* @param fileMd5 文件MD5
	* @param paperUuId 试卷uuid
	* @return
	* @throws Exception    设定文件 
	* @return PaperResult    返回类型 
	* @throws
	 */
	
	public PaperResult isExistFile(String fileMd5,String paperUuId) throws Exception;
	
	/**
	 * 
	* @Title: findUserPaperByPaperUuId
	* @author pantq 
	* @Description: 统计一份试卷每个人的得分情况 
	* @param paperId 试卷Id
	* @param type 做题类型 默认4 导学案,1：作业，2：考试，3：练习
	* @param ownerId 任务ID
	* @param teamId 班级ID
	* @return    设定文件 
	* @return List<UserRank>    返回类型 
	* @throws
	 */
	public List<UserRank> findUserPaperByPaperId(Integer paperId,Integer type,Integer ownerId,Integer teamId); 
	
	
	/**
	 * 
	* @Title: findPaperQuestionCorrectRateByPaperUuId
	* @author pantq 
	* @Description: 统计一份试卷每题正确率
	* @param paperId 试卷ID
	* @param type 做题类型 默认4导学案,1：作业，2：考试，3：练习
	* @param ownerId 任务ID
	* @param teamId 班级ID
	* @return    设定文件 
	* @return List<UserRank>    返回类型 
	* @throws
	 */
	public List<UserRank> findPaperQuestionCorrectRateByPaperId(Integer paperId,Integer type,Integer ownerId,Integer teamId);
	
	/**
	* @Title: updatePaperUsedCount
	* @author pantq 
	* @Description: 更新pa_paper该试卷表的usedCount字段，和对应pa_question 的usedCount字段的值。 
	* @param paperId
	* @return    设定文件 
	* @return Boolean    返回类型 
	* @throws
	 */
	public Boolean updatePaperUsedCount(Integer paperId);
	
	
	/**
	 * 
	* @Title: findUserWrongList
	* @author pantq 
	* @Description: 根据相关条件查询错题本列表
	* @param userId 用户ID
	* @param subjectCode 科目CODE
	* @param page
	* @param order
	* @return    设定文件 
	* @return List<WrongPaper>    返回类型 
	* @throws
	 */
	public List<Map<String, Object>> findUserWrongList(Integer userId,String subjectCode,Page page, Order order);
	
	
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
	* @return Boolean    返回类型 
	* @throws
	 */
	public Boolean redo(Integer userWrongId, String answers) throws Exception;
	
	
	
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
	public List<AnswerSituationResult> answerSituation(Integer userId,String paperUuId,Integer ownerId) throws Exception;
	
	
	
	/**
	 * 
	* @Title: redo
	* @author pantq 
	* @Description: 错题本删除
	* @param userWrongId pa_user_wrong表ID
	* @param appKey 
	* @return
	* @throws ParseException    设定文件 
	* @return Boolean    返回类型 
	* @throws
	 */
	public Boolean wrongQuestioDelete(Integer userWrongId) throws Exception;
	
	
	
	public Boolean deletePaperInfo(Integer ownerId,Integer type);
	
	
}
