package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjAptsTaskJudge;
import platform.education.generalTeachingAffair.vo.PjAptsTaskJudgeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PjAptsTaskJudgeService {
    PjAptsTaskJudge findPjAptsTaskJudgeById(Integer id);
	   
	PjAptsTaskJudge add(PjAptsTaskJudge pjAptsTaskJudge);
	   
	PjAptsTaskJudge modify(PjAptsTaskJudge pjAptsTaskJudge);
	   
	void remove(PjAptsTaskJudge pjAptsTaskJudge);
	   
	List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition, Page page, Order order);
	
	List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition);
	
	List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition, Page page);
	
	List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition, Order order);
	
	Long count();
	
	Long count(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition);
	
}
