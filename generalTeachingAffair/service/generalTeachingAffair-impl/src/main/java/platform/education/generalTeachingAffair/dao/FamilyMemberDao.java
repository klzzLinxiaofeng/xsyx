package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.FamilyMember;
import platform.education.generalTeachingAffair.vo.FamilyMemberCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface FamilyMemberDao extends GenericDao<FamilyMember, java.lang.Integer> {

	List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition, Page page, Order order);
	
	FamilyMember findById(Integer id);
	
	Long count(FamilyMemberCondition familyMemberCondition);
	
	List<FamilyMember> findByStudentId(Integer studentId);
	
}
