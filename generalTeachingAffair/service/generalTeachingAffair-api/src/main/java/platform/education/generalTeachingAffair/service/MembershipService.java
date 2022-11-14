package platform.education.generalTeachingAffair.service;
import platform.education.generalTeachingAffair.model.Membership;
import platform.education.generalTeachingAffair.vo.MembershipCondition;
import platform.education.generalTeachingAffair.vo.MembershipVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface MembershipService {
    Membership findMembershipById(Integer id);
	   
	Membership add(Membership membership);
	   
	Membership modify(Membership membership);
	   
	void remove(Membership membership);
	   
	List<Membership> findMembershipByCondition(MembershipCondition membershipCondition, Page page, Order order);
	
	List<Membership> findMembershipByCondition(MembershipCondition membershipCondition);
	
	List<Membership> findMembershipByCondition(MembershipCondition membershipCondition, Page page);
	
	List<Membership> findMembershipByCondition(MembershipCondition membershipCondition, Order order);
	
	Long count();
	
	Long count(MembershipCondition membershipCondition);
	
	
	List<MembershipVo> findMembershipVoByCondition(MembershipCondition membershipCondition, Page page, Order order);
	

	/**
	 * 根据parentId、ownerId、ownerType、is_deleted查找对应的分校记录
	 * @param parentId
	 * @param ownerId
	 * @param ownerType
	 * @return
	 */
	Membership findByTerm(Integer parentId,String ownerId,String ownerType,Boolean isDeleted);
	
	/**
	 * 根据parentId、ownerId、ownerType、is_deleted查找对应的分校集合
	 * @param parentId
	 * @param ownerType
	 * @return
	 */
	List<Membership>findByTermList(Integer parentId,String ownerId,String ownerType,Boolean isDeleted);
	
	
}
