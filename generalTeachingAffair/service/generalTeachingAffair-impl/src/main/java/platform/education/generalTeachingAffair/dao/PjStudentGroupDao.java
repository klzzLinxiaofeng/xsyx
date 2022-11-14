package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.PjStudentGroup;
import platform.education.generalTeachingAffair.vo.PjStudentGroupCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface PjStudentGroupDao extends GenericDao<PjStudentGroup, java.lang.Integer> {

	List<PjStudentGroup> findPjStudentGroupByCondition(PjStudentGroupCondition pjStudentGroupCondition, Page page, Order order);
	
	
	PjStudentGroup findById(Integer id);
	
	
	Long count(PjStudentGroupCondition pjStudentGroupCondition);
	
	
	void createBatch(PjStudentGroup[] pglist);
	
	
	void deleteByTeamId(Integer teamId);
	
}
