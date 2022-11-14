package platform.education.paper.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserRank;
import platform.education.paper.vo.UserPaperCondition;
import platform.education.paper.vo.UserPaperCorrectVo;

public interface UserPaperDao extends GenericDao<UserPaper, java.lang.Integer> {

	
	void batchCharu(UserPaper userPaper);
	
	List<UserPaper> findUserPaperByCondition(UserPaperCondition userPaperCondition, Page page, Order order);
	
	UserPaper findById(Integer id);
	
	Long count(UserPaperCondition userPaperCondition);
	
	/**
	 * 
	* @Title: findUserPaperByPaperUuId
	* @author pantq 
	* @Description: 统计一份试卷每个人的得分情况 
	* @param paperUuid 试卷UUID
	* @param type 做题类型 默认4导学案,1：作业，2：考试，3：练习
	* @return    设定文件 
	* @return List<UserRank>    返回类型 
	* @throws
	 */
	List<UserRank> findUserPaperByPaperUuId(@Param("paperUuId") String paperUuId,@Param("type") Integer type,@Param("ownerId") Integer ownerId,@Param("teamId") Integer teamId);
	
	/**
	 * 
	* @Title: findPaperQuestionCorrectRateByPaperUuId
	* @author pantq 
	* @Description: 统计一份试卷每题正确率
	* @param paperUuid
	* @param type 做题类型 默认4导学案,1：作业，2：考试，3：练习
	* @return    设定文件 
	* @return List<UserRank>    返回类型 
	* @throws
	 */
	List<UserRank> findPaperQuestionCorrectRateByPaperUuId(@Param("paperUuId") String paperUuId,@Param("type") Integer type,@Param("ownerId") Integer ownerId,@Param("teamId") Integer teamId);
	
	
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
	* @param teamId
	* @return    设定文件 
	* @return Long    返回类型 
	* @throws
	 */
	Float countUserPaperTeamTotalScore(Integer ownerId,Integer teamId,Integer unitId,Integer paperType);
	
	
	public List<UserPaper> findUserPaperListByOwnerId(Integer ownerId,Integer objectId,Integer type);
	
	List<UserPaperCorrectVo> findUserPaperCorrectByTaskId(Integer taskId, Integer objectId, Integer userId, String questionUuid, Page page);

	UserPaper findUserPaperByPaperTaskId(Integer taskId, Integer userId);
	
	List<UserPaper> findUserPaperUserTotalScore(Integer unitId,Integer ownerId,Integer type,Integer teamId);
	
	List<Map<Integer,Float>> findUserPaperUserTotalScore(Object[] list);
	
	List<Map<Integer,Integer>> findUserPaperAnswerCountByOwnerIdAndTeamId(Object[] list);
	
	Map<Integer, Long> countUserPaperTeamsTotalScoreObj(Object[] list, Integer ownerId);
	
	Map<Integer, Integer> countUserPaperAnswerCount(Object[] list, Integer ownerId, Integer paperType);
	
	
	List<UserPaper>findUserPaperScoreByOwnerId(String paperUuid,Integer ownerId,Integer type);
	List<UserPaper>findUserPaperListByOwnerIds(Integer[] ownerId,Integer objectId,Integer type);
	List<UserPaper>findUserPaperByOwnerIdsAndUserId(Integer[] ownerId,Integer objectId,Integer type,Integer userId);
	
	void deleteByOwnerIdAndType(Integer taskId,Integer type);

	List<UserPaper> findPaperResponseCheck(Integer taskId, Integer teamId);
	
	List<UserPaperCorrectVo> findUserPaperCorrectByTaskIdAndTeamId(Integer taskId, Integer objectId, Integer userId, Integer teamId,String questionUuid, Page page);
	
	void deleteNotInPaperUuid(String[] uuids);
}
