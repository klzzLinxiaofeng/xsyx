package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.PjStudentGroup;
import platform.education.generalTeachingAffair.vo.PjStudentGroupCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface PjStudentGroupService {
    PjStudentGroup findPjStudentGroupById(Integer id);
	   
	PjStudentGroup add(PjStudentGroup pjStudentGroup);
	   
	PjStudentGroup modify(PjStudentGroup pjStudentGroup);
	   
	void remove(PjStudentGroup pjStudentGroup);
	   
	List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition, Page page, Order order);
	
	List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition);
	
	List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition, Page page);
	
	List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition, Order order);
	
	Long count();
	
	Long count(PjStudentGroupCondition pjStudentGroupCondition);
	
	void createBatch(PjStudentGroup[] pjStudentGroups);
	
	void deleteByTeamId(Integer teamId);
	
	void addPjStudentGroupByDate(String data,Integer teamId);
	
}
