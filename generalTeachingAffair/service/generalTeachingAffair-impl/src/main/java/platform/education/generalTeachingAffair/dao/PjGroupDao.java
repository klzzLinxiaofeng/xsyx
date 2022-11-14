package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PjGroup;
import platform.education.generalTeachingAffair.vo.PjGroupCondition;

import java.util.List;

public interface PjGroupDao extends GenericDao<PjGroup, Integer> {

	List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition, Page page, Order order);
	
	PjGroup findById(Integer id);
	
	Long count(PjGroupCondition pjGroupCondition);
	
}
