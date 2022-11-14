package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjAptsTaskJudge;
import platform.education.generalTeachingAffair.vo.PjAptsTaskJudgeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjAptsTaskJudgeDao extends GenericDao<PjAptsTaskJudge, java.lang.Integer> {

	List<PjAptsTaskJudge> findPjAptsTaskJudgeByCondition(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition, Page page, Order order);
	
	PjAptsTaskJudge findById(Integer id);
	
	Long count(PjAptsTaskJudgeCondition pjAptsTaskJudgeCondition);
	
}
