package platform.education.paper.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.*;
import platform.education.paper.vo.CognitionCountVo;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.UserQuestionCondition;
import platform.education.resource.model.UserAction;
import platform.education.resource.model.UserKnowledgeSummary;

import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonNode;


public interface UserQuestionService {
    UserQuestion findUserQuestionById(Integer id);
	   
	UserQuestion add(UserQuestion userQuestion);
	   
	UserQuestion modify(UserQuestion userQuestion);
	   
	void remove(UserQuestion userQuestion);
	   
	List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition, Page page, Order order);
	
	List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition);
	
	List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition, Page page);
	
	List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition, Order order);
	
	Long count();
	
	Long count(UserQuestionCondition userQuestionCondition);
	
	/**
	 * 
	* @Title: answerSituation
	* @author pantq 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @param userId
	* @param paperUuId
	* @param ownerId
	* @return    设定文件 
	* @return List<AnswerSituationResult>    返回类型 
	* @throws
	 */
	public List<AnswerSituationResult> findUserQuestionByUserIdAndPaperUuId(Integer userId, String paperUuId, Integer ownerId);
	
	
	/**
	 * 
	* @Title: findUserQuestionByOwnerId
	* @author pantq 
	* @Description: 根据ownerId查询题目相关信息
	* @param ownerId
	* @return    设定文件 
	* @return List<UserQuestionResult>    返回类型 
	* @throws
	 */
	public List<UserQuestionResult> findUserQuestionByOwnerId(Integer ownerId,Integer objectId,Integer type);
	
	/**
	 * 
	* @Title: getTeamQuestionOptionsByQuestionUuIdAndOwnerId
	* @author pantq 
	* @Description: 根据班级ID和ownerId和questionUUID获取 本班该道题选项的次数统计
	* @param teamId 班级ID
	* @param ownerId 任务ID
	* @param questionUuId 题目uuid
	* @return    设定文件 
	* @return TeamQuestionOptions    返回类型 
	* @throws
	 */
	public List<TeamQuestionOptions> getTeamQuestionOptionsByQuestionUuIdAndOwnerId(Integer teamId,Integer ownerId,String questionUuId,Integer unitId);
	
	/**
	 * 
	* @Title: sumScore
	* @author pantq 
	* @Description: 统计某一题的所有分数
	* @param ownerId 任务ID
	* @param teamId 班级ID
	* @param questionUuid 试题UUID
	* @return    设定文件 
	* @return Long    返回类型 
	* @throws
	 */
	Long findSumScore(Integer ownerId,Integer teamId,String questionUuid);
	
	public List<UserQuestionResult> findUserQuestionByPaperId(Integer paperId);
	
	public void processPaperAdd(List<UserQuestion> userQuestionsList,List<UserAction> userActionList,List<UserKnowledgeSummary> userKnowledgeSummaryList,List<UserWrong> userWrongList);
	/**
	 * 根据班级ID和OWNERID查找用户做题情况
	 * @return
	 */
	List<Map> findUserQuestionByTeamIdAndOwnerId(Integer teamId,Integer ownerId,Integer type);

	/**
	 * @function 添加根据试卷 知识点ID 计算某学生的知识点得分率
	 * @param paperUuid
	 * @param knoledgeId
	 * @param userId
	 * @return
	 */
	List<KnowledgeCountVo> findStudentAllScoreByKnoledgeId(String paperUuid, Integer[] knoledgeId, Integer userId,Integer ownerId,Integer type,Integer objId);

	/**
	 * @function 获取某个学生对某试卷认知度类型得分百分比
	 * @param userId
	 * @param paperUuid
     * @return
     */
	List<CognitionCountVo> findUserCognitionCount(Integer userId, String paperUuid,Integer ownerId,Integer objId,Integer type);
	
	
	List<PaperAnswerTime> findUserQuestionAnswerTimeByUserIdAndExamId(Integer userId,Integer teamId,Integer type,Integer ownerId,Integer objId);
	
	
	void processUserAction(JsonNode answersList,Integer ownerId,Integer userId,String schoolYear,String termCode,Integer resourceType);
	
	
	List<Map<String,Object>> findUserQuestionAnswerCount(Integer teamId,Integer ownerId,Integer type,Boolean isCorrect,String answer);
	
	
	Long findUserQuestionScoreCount(Integer teamId,Integer ownerId,Integer type);
	
	
	List<Map<String,Object>> findUserQuestionAnswerTime(Integer teamId,Integer ownerId,Integer type);
	
	
	List<Map<String,Object>> findUserQuestionUserAnswerCount(String paperUuid,Integer ownerId,Integer type,Boolean isCorrect,Integer teamId);
	
	
	 void batchAnswer(List<UserQuestion> userQuestionsList,List<UserWrong> userWrongList);
	 
	 Boolean batchAddBigData(List<UserAction> userActionList,List<UserKnowledgeSummary> userKnowledgeSummaryList);
	 
	 void batchModifyBigData(List<UserKnowledgeSummary> userKnowledgeSummaryList);

	 Map<String, UserQuestion> findMapByOwnerIdAndUserId(Integer ownerId, Integer objectId);
	 
	 Map<String, UserQuestion> findMapByOwnerIdAndUserId(Integer ownerId, Integer userId, Integer objectId);

	/**
	 * 批量更新用户针对题目的所得分数以及正确情况
	 * @param userQuestions
	 * @return
	 */
	Integer batchUpdateScoreAndIsCorrect(List<UserQuestion> userQuestions);
	
	
	Map<String,UserQuestion> findQuestionListByUserQuestionUuidsAndOnwerId(Object[] list,Integer ownerId,Integer type);
	
	public List<UserQuestion> findUserQuestionByOwnerIds(Integer ownerIds[],Integer objectId,Integer type);

	
	Map<String,UserQuestion> findUserQuestionListByOwnerIdAndQuestionIds(List questionIds,Integer ownerId,Integer userId,Integer unitId);
	
	void deleteByOwnerIdAndType(Integer taskId,Integer type);
	
	List<UserQuestionResult> findUserQuestionByPaperIdDemo(Integer paperId);

	Map<String, UserQuestion> findQuestionAnswerList(Integer taskId, Integer unitId, String questionUuid);
	
	void deleteByNotInQuesitonUuids(String[] questionUuids);
	
}
