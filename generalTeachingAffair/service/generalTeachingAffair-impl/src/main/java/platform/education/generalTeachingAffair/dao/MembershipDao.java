package platform.education.generalTeachingAffair.dao;

import platform.education.generalTeachingAffair.model.Membership;
import platform.education.generalTeachingAffair.vo.MembershipCondition;
import platform.education.generalTeachingAffair.vo.MembershipVo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.dao.GenericDao;

import java.util.List;

public interface MembershipDao extends GenericDao<Membership, java.lang.Integer> {

	List<Membership> findMembershipByCondition(MembershipCondition membershipCondition, Page page, Order order);
	
	Membership findById(Integer id);
	
	Long count(MembershipCondition membershipCondition);
	
	
	List<MembershipVo> findMembershipVoByCondition(MembershipCondition membershipCondition, Page page, Order order);


	/**
	 * 根据parentId、ownerId、ownerType、is_deleted查找对应的分校集合
	 * @param parentId
	 * @param ownerType
	 * @return
	 */
	List<Membership>findByTermList(Integer parentId,String ownerId,String ownerType,Boolean isDeleted);
	
	/**
	 * 根据parentId、ownerId、ownerType、is_deleted查找对应的某一个分校记录
	 * @param parentId
	 * @param ownerId
	 * @param ownerType
	 * @return
	 */
	Membership findByTerm(Integer parentId,String ownerId,String ownerType,Boolean isDeleted);
	
}
