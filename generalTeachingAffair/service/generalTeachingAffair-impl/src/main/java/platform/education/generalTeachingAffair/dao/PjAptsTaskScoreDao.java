package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjAptsTaskScore;
import platform.education.generalTeachingAffair.vo.AssessmentScoreVo;
import platform.education.generalTeachingAffair.vo.PjAptsTaskScoreCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjAptsTaskScoreDao extends GenericDao<PjAptsTaskScore, java.lang.Integer> {

	List<PjAptsTaskScore> findPjAptsTaskScoreByCondition(PjAptsTaskScoreCondition pjAptsTaskScoreCondition, Page page, Order order);
	
	PjAptsTaskScore findById(Integer id);
	
	Long count(PjAptsTaskScoreCondition pjAptsTaskScoreCondition);
	
	void createBatch(PjAptsTaskScore[] pats);
	
	List<AssessmentScoreVo> findAssessmentCount(Integer taskUserId,Integer studentId);

    List<Float> findAvgScore(Integer taskUserId);
}
