package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjUserAction;
import platform.education.generalTeachingAffair.vo.PjUserActionCondition;
import platform.education.generalTeachingAffair.vo.PjUserActionHonorScoreVo;
import platform.education.generalTeachingAffair.vo.PjUserActionTestVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface PjUserActionService {
    PjUserAction findPjUserActionById(Integer id);
	   
	PjUserAction add(PjUserAction pjUserAction);
	   
	PjUserAction modify(PjUserAction pjUserAction);
	   
	void remove(PjUserAction pjUserAction);
	   
	List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition, Page page, Order order);
	
	List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition);
	
	List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition, Page page);
	
	List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition, Order order);
	
	Long count();
	
	Long count(PjUserActionCondition pjUserActionCondition);
	
	void addJSONData(String data);
	
	void createBatch(PjUserAction[] pjUserActions);
	
	Float findSumCountByType(String type,Integer userId,String startDate,String FinishDate,String subjectCode,String termCode);
	/**
	 * 课堂表现json
	 * @param userId
	 * @param startDate
	 * @param FinishDate
	 * @param subjectCode
	 * @return
	 */
	Map<String,Object> findCoursePerformance(Integer userId,String startDate,String FinishDate,String subjectCode,String termCode);
	
	/**
	 * 获取一段时期内光荣榜分数
	 * @return
	 */
	List<PjUserActionHonorScoreVo> findHonorScore(Integer args1, Date args2, Date args3, String args4, String args5, Integer teamId);
	
	/**
	 * 课堂参与：获取完成练习
	 */
	Double findStudentPractice(Integer userId, Date args1, Date args2, String subjectCode, Integer teamId);
	
	/**
	 * 课堂参与：获取成果展示
	 */
	Double findStudentAchievement(Integer userId, Date args1, Date args2, String subjectCode, Integer teamId);
	
	/**
	 * 课堂参与：获取抢答
	 */
	Double findStudentAnswer(Integer userId, Date args1, Date args2, String subjectCode, Integer teamId);
	
	/**
	 * QuestionCount的value
	 */
	Double findQuestionCountSumByUserId(Integer userId, Integer teamId, Date date1, Date date2, String subjectCode);
	
	/**
	 * 随堂小测：获取所有用户在一个时间段内的正确率
	 */
	PjUserActionTestVo findStudentTestSumByTeamId(Integer teamId, String subjectCode, Date date1, Date date2, Integer userId);
	
	/**
	 * 随堂小测：获取随堂小测需要查询的userid
	 */
	List<Integer> findUserIdListByTeamId(Integer teamId, String subjectCode, Date date1, Date date2);
	
	/**
	 * 获取时间段内学生所有信息
	 */
	List<String> findPjUserActionByUserId(Integer userId, Integer teamId, Date date1, Date date2);
	
	/**
	 * 获取随堂评价该班的所有学生的user_id
	 */
	List<Integer> findClassAssessmentStudentList(Integer teamId, String type, String subjectCode, Date date1, Date date2);
	
	/**
	 * 获取随堂评价一个学生的分数总和
	 */
	Integer findAppraiseSum(Integer teamId, Integer userId, String type, String subjectCode, Date date1, Date date2);
}
