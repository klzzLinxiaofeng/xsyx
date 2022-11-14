package platform.education.paper.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.AnswerSituationResult;
import platform.education.paper.model.PaperAnswerTime;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.model.UserQuestionResult;
import platform.education.paper.vo.CognitionCountVo;
import platform.education.paper.vo.KnowledgeCountVo;
import platform.education.paper.vo.UserQuestionCondition;

import java.util.List;
import java.util.Map;


public interface UserQuestionDao extends GenericDao<UserQuestion, java.lang.Integer> {

	List<UserQuestion> findUserQuestionByCondition(UserQuestionCondition userQuestionCondition, Page page, Order order);

	UserQuestion findById(Integer id);

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


	/**
	 *
	* @Title: findUserQuestionAnswerCountByCondition
	* @author pantq
	* @Description: 查找应答人数
	* @param userQuestionCondition
	* @return    设定文件
	* @return Integer    返回类型
	* @throws
	 */
	Integer findUserQuestionAnswerCountByCondition(UserQuestionCondition userQuestionCondition);


	public List<UserQuestionResult> findUserQuestionByPaperId(Integer paperId);

	/**
	 * 根据班级ID和OWNERID查找用户做题情况
	 * @return
	 */
	List<Map> findUserQuestionByTeamIdAndOwnerId(Integer teamId,Integer ownerId,Integer type);

	/**
	 * @function 添加根据试卷 知识点ID 计算某学生的知识点得分
	 * @param paperUuid
	 * @param knoledgeId
	 * @param userId
     * @return
     */
	List<KnowledgeCountVo> findStudentAllScoreByKnoledgeId(String paperUuid, Integer[] knoledgeId, Integer userId,Integer ownerId,Integer type,Integer objId);

	/**
	 * @function 根据试卷获取题目认知度得分情况
	 * @param userId
	 * @param paperUuid
     * @return
     */
	List<CognitionCountVo> findUserCognitionCount(Integer userId, String paperUuid,Integer ownerId,Integer objId,Integer type);


	List<PaperAnswerTime> findUserQuestionAnswerTimeByUserIdAndOwnerId(Integer userId,Integer teamId,Integer type,Integer ownerId,Integer objId);


	List<Map<String,Object>> findUserQuestionAnswerCount(Integer teamId,Integer ownerId,Integer type,Boolean isCorrect,String answer);

	Long findUserQuestionScoreCount(Integer teamId,Integer ownerId,Integer type);

	List<Map<String,Object>> findUserQuestionAnswerTime(Integer teamId,Integer ownerId,Integer type);

	List<Map<String,Object>> findUserQuestionUserAnswerCount(String paperUuid,Integer ownerId,Integer type,Boolean isCorrect,Integer teamId);

	void batchUserQuestion(Object[] list);

	List<UserQuestion> findByOwnerIdByUserId(Integer ownerId, Integer userId, Integer objectId);

	Integer batchUpdateScoreAndIsCorrect(List<UserQuestion> userQuestions);
	
	
	List<UserQuestion> findUserQuestionListByQuestionUuids(Object[] list,Integer ownerId,Integer type);
	
	List<UserQuestion> findUserQuestionListByOwnerIdAndQuestionIds(Object[] questionIds, Integer ownerId,Integer userId, Integer unitId) ;

	public List<UserQuestion> findUserQuestionByOwnerIds(Integer ownerIds[],Integer objectId,Integer type);
	
	void deleteByOwnerIdAndType(Integer taskId,Integer type);
	
	List<UserQuestionResult> findUserQuestionByPaperIdDemo(Integer paperId);

	List<UserQuestion> findQuestionAnswerList(Integer taskId, Integer unitId, String questionUuid);
	
	public void deleteByNotInQuesitonUuids(String[] questionUuids);

}
