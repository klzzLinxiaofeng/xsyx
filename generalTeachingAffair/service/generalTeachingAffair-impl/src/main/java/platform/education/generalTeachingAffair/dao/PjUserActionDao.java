package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjUserAction;
import platform.education.generalTeachingAffair.vo.PjUserActionCondition;
import platform.education.generalTeachingAffair.vo.PjUserActionHonorScoreVo;
import platform.education.generalTeachingAffair.vo.PjUserActionTestVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.Date;
import java.util.List;

public interface PjUserActionDao extends GenericDao<PjUserAction, java.lang.Integer> {

	List<PjUserAction> findPjUserActionByCondition(PjUserActionCondition pjUserActionCondition, Page page, Order order);
	
	PjUserAction findById(Integer id);
	
	Long count(PjUserActionCondition pjUserActionCondition);
	
	void createBatch(PjUserAction[] pjUserActions);
	
	Float findSumCountByType(String type,Integer userId,String startDate,String FinishDate,String subjectCode,String termCode);
	
	
	List<String> findPjUserActionByUserId(Integer userId, Integer teamId, Date date1, Date date2);
	
	List<PjUserActionHonorScoreVo> findHonorScoreByDay(String args0, Integer args1, Date args2, Date args3, String args4, Integer teamId);
	
	List<PjUserActionHonorScoreVo> findHonorScoreSum(String args0, Integer args1, Date args2, Date args3, String args4, Integer teamId);
	
	Double findStudentResByTypeSum(Integer userId, String type, Date date1, Date date2, String subjectCode, Integer teamId);
	
	Double findStudentAchievementSum(Integer args0, Date args1, Date args2, String subjectCode, String type1, String type2, Integer teamId);
	
	Double findQuestionCountSumByUserId(Integer userId, Integer teamId, String type, Date date1, Date date2, String subjectCode);
	
	PjUserActionTestVo findStudentTestSumByTeamId(Integer teamId, String type, String subjectCode, Date date1, Date date2, Integer userId);
	
	List<Integer> findUserIdListByTeamId(Integer teamId, String type, String subjectCode, Date date1, Date date2);
	
	List<Integer> findAppraiseStudentList(Integer teamId, String type, String subjectCode, Date date1, Date date2);
	
	Integer findAppraiseSum(Integer teamId, Integer userId, String type, String subjectCode, Date date1, Date date2);
}
