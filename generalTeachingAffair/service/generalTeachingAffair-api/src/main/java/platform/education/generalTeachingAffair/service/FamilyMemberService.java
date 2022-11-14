package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.FamilyMember;
import platform.education.generalTeachingAffair.vo.FamilyMemberCondition;
import platform.education.generalTeachingAffair.vo.ParentMess;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface FamilyMemberService {
    FamilyMember findFamilyMemberById(Integer id);
	   
	FamilyMember add(FamilyMember familyMember);
	   
	FamilyMember modify(FamilyMember familyMember);
	   
	void remove(FamilyMember familyMember);
	   
	List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition, Page page, Order order);
	
	List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition);
	
	List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition, Page page);
	
	List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition, Order order);
	
	Long count();
	
	Long count(FamilyMemberCondition familyMemberCondition);
	
	List<FamilyMember> findByStudentId(Integer studentId);
	
	/**
	 * 获取家庭成员信息（用于学生档案）
	 * @param studentId
	 * @return
	 */
	List<ParentMess> findParentMessByStudentId(Integer studentId);
}
