package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.PjGroup;
import platform.education.generalTeachingAffair.vo.PjGroupCondition;

import java.util.List;

public interface PjGroupService {
    PjGroup findPjGroupById(Integer id);
	   
	PjGroup add(PjGroup pjGroup);
	   
	PjGroup modify(PjGroup pjGroup);
	   
	void remove(PjGroup pjGroup);
	   
	List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition, Page page, Order order);
	
	List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition);
	
	List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition, Page page);
	
	List<PjGroup> findPjGroupByCondition(PjGroupCondition pjGroupCondition, Order order);
	
	Long count();
	
	Long count(PjGroupCondition pjGroupCondition);
	
}
