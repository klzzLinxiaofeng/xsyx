package platform.education.lads.dao;

import platform.education.lads.model.LadsActivityType;
import platform.education.lads.vo.LadsActivityTypeCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface LadsActivityTypeDao extends GenericDao<LadsActivityType, java.lang.Integer> {

	List<LadsActivityType> findLadsActivityTypeByCondition(LadsActivityTypeCondition ladsActivityTypeCondition, Page page, Order order);
	
	LadsActivityType findById(Integer id);
	
	Long count(LadsActivityTypeCondition ladsActivityTypeCondition);
	
	void deleteByCondition(LadsActivityTypeCondition ladsActivityTypeCondition);
        
        //以下是业务方法
        LadsActivityType findByUuid(String uuid);
}
