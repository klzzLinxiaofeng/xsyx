package platform.education.generalTeachingAffair.service;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PjAptsTaskScore;
import platform.education.generalTeachingAffair.vo.AssessmentScoreVo;
import platform.education.generalTeachingAffair.vo.PjAptsTaskScoreCondition;

import java.util.List;

public interface PjAptsTaskScoreService {
    PjAptsTaskScore findPjAptsTaskScoreById(Integer id);
	   
	PjAptsTaskScore add(PjAptsTaskScore pjAptsTaskScore);
	   
	PjAptsTaskScore modify(PjAptsTaskScore pjAptsTaskScore);
	   
	void remove(PjAptsTaskScore pjAptsTaskScore);
	   
	List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition, Page page, Order order);
	
	List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition);
	
	List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition, Page page);
	
	List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition, Order order);
	
	Long count();
	
	Long count(PjAptsTaskScoreCondition pjAptsTaskScoreCondition);
	
	void createBatch(PjAptsTaskScore[] pats);
	
	List<AssessmentScoreVo> findAssessmentCount(Integer taskUserId,Integer studentId);

    List<Float> findAvgScore(Integer id);
}
