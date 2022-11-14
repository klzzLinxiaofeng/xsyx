package platform.education.generalTeachingAffair.service;

import java.util.List;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Membership;
import platform.education.generalTeachingAffair.vo.MembershipCondition;
import platform.education.generalTeachingAffair.vo.MembershipVo;

public interface SchoolMembershipService extends MembershipService{
	
	/**
	 * 添加总校区 
	 * @param name
	 * @return
	 */
	Membership addGeneral (String name,String appKey);
	
	/**
	 * 列出所有总校记录
	 * @return
	 */
	
	List<MembershipVo> findHeadSchools();
	
	/**
	 * 添加分校记录
	 * @param branchSchoolID是分校，来源pj_school.id
	 * @param generalSchoolUUID是总校，来源pj_membership.uuid
	 */
	void assignSchoolBranchToGeneral (Integer branchSchoolID, Integer generalSchoolId,String appKey);
	
	/**
	 * 移出分校区记录
	 * @param branchSchoolID是分校，来源pj_school.id
	 * @param generalSchoolUUID是总校，来源pj_membership.uuid
	 */
	void removeSchoolBranchFromGeneral (Integer branchSchoolID, Integer generalSchoolId);
	
	/**
	 * 修改分校区记录
	 * @param branchSchoolID是分校，来源pj_school.id
	 * @param generalSchoolUUID是总校，来源pj_membership.uuid
	 */
	void modifySchoolBranchFromGenneral(Integer branchSchoolID, Integer generalSchoolId);
	

}
