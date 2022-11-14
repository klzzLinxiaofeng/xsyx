package platform.education.lads.dao;

import platform.education.lads.model.LadsActivityTransition;
import platform.education.lads.vo.LadsActivityTransitionCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsActivityTransitionDao extends GenericDao<LadsActivityTransition, java.lang.Integer> {

	List<LadsActivityTransition> findLadsActivityTransitionByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition, Page page, Order order);
	
	LadsActivityTransition findById(Integer id);
	
	Long count(LadsActivityTransitionCondition ladsActivityTransitionCondition);
	
	void deleteByCondition(LadsActivityTransitionCondition ladsActivityTransitionCondition);
        
        //以下是业务方法
        LadsActivityTransition findByUuid(String uuid);
}
