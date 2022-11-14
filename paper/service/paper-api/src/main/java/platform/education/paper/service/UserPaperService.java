package platform.education.paper.service;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserRank;
import platform.education.paper.vo.UserPaperCondition;
import platform.education.paper.vo.UserPaperCorrectVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;
import java.util.Map;


public interface UserPaperService {
    UserPaper findUserPaperById(Integer id);
	   
	UserPaper add(UserPaper userPaper);
	   
	UserPaper modify(UserPaper userPaper);
	   
	void remove(UserPaper userPaper);
	   
	List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition, Page page, Order order);
	
	List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition);
	
	List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition, Page page);
	
	List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition, Order order);
	
	Long count();
	
	Long count(UserPaperCondition userPaperCondition);
	

	/**
	 * 
	* @Title: findUserPaperByPaperUuId
	* @author pantq 
	* @Description: 统计一份试卷每个人的得分情况 
	* @param paperUuId 试卷UUID
	* @param type 做题类型 默认4导学案,1：作业，2：考试，3：练习
	* @param ownerId 任务ID
	* @param teamId 班级ID
	* @return    设定文件 
	* @return List<UserRank>    返回类型 
	* @throws
	 */
	public List<UserRank> findUserPaperByPaperUuId(String paperUuId,Integer type,Integer ownerId,Integer teamId);
	
	
	/**
	 * 
	* @Title: findPaperQuestionCorrectRateByPaperUuId
	* @author pantq 
	* @Description: 统计一份试卷每题正确率
	* @param paperUuId 试卷UUID
	* @param type 做题类型 默认4导学案,1：作业，2：考试，3：练习
	* @param ownerId 任务ID
	* @param teamId 班级ID
	* @return    设定文件 
	* @return List<UserRank>    返回类型 
	* @throws
	 */
	List<UserRank> findPaperQuestionCorrectRateByPaperUuId(String paperUuId,Integer type,Integer ownerId,Integer teamId);
	
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
	List<UserPaper> findUserPaperAnswerCountByCondition(UserPaperCondition userPaperCondition);
	
	
	/**
	 * 
	* @Title: countUserPaperTeamTotalScore
	* @author pantq 
	* @Description: 获取班级总得分
	* @param ownerId
	* @param team_id
	* @return    设定文件 
	* @return Long    返回类型 
	* @throws
	 */
	Float countUserPaperTeamTotalScore(Integer ownerId,Integer teamId,Integer unitId,Integer paperType);
	
	/**
	 * 
	 * @param ownerId
	 * @return
	 */
	List<UserPaper> findUserPaperListByOwnerId(Integer ownerId,Integer objectId,Integer type);

	UserPaper findUserPaperByPaperTaskId(Integer taskId, Integer userId);
	
	List<UserPaper>findUserPaperUserTotalScore(Integer unitId,Integer ownerId,Integer type,Integer teamId);
	
	List<Map<Integer, Float>> countUserPaperTeamsTotalScore(Object[] list);
	
	List<Map<Integer,Integer>> findUserPaperAnswerCountByOwnerIdAndTeamId(Object[] list);
	
	Map<Integer,Float> countUserPaperTeamsTotalScoreObj(Object[] list,Integer ownerId,Integer unitId,Integer paperType);
	
	Map<Integer,Integer> countUserPaperAnswerCount(Object[] list,Integer ownerId,Integer unitId,Integer paperType);

	
	List<UserPaper>findUserPaperScoreByOwnerId(String paperUuid,Integer ownerId,Integer type);


	/***
	 * 获取学生完成试卷题目情况
	 * @param userPaperCondition
	 * @return
	 */
	List<UserPaperCorrectVo> findUserPaperCorrectByTaskId(Integer taskId, Integer objectId, Integer userId, String questionUuid,Page page);
	
	List<UserPaper> findUserPaperListByOwnerIds(Integer[] ownerIds,Integer objectId,Integer type);
	
	List<UserPaper>findUserPaperByOwnerIdsAndUserId(Integer[] ownerId,Integer objectId,Integer type,Integer userId);
	
	void deleteByOwnerIdAndType(Integer taskId,Integer type);

	List<UserPaper> findUserPaperByOwnerIdAndObjectId(Integer taskId, Integer unitId);

	UserPaper findUserPaperByCondition(Integer taskId, Integer unitId, Integer userId);

	UserPaper findUniqueUserPaper(Integer taskId, Integer userId, Integer unitId);

	List<UserPaper> findPaperResponseCheck(Integer taskId, Integer teamId);
   
	List<UserPaperCorrectVo> findUserPaperCorrectByTaskIdAndTeamId(Integer taskId, Integer objectId, Integer userId, Integer teamId,String questionUuid, Page page);
	
	void deleteNotInPaperUuid(String[] uuids);

}
