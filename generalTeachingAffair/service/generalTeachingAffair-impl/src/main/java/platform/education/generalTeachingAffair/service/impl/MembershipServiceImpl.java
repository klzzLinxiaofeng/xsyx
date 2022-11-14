package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.contants.MembershipContans;
import platform.education.generalTeachingAffair.dao.MembershipDao;
import platform.education.generalTeachingAffair.model.Membership;
import platform.education.generalTeachingAffair.model.School;
import platform.education.generalTeachingAffair.service.SchoolMembershipService;
import platform.education.generalTeachingAffair.service.SchoolService;
import platform.education.generalTeachingAffair.vo.MembershipCondition;
import platform.education.generalTeachingAffair.vo.MembershipVo;

public class MembershipServiceImpl implements SchoolMembershipService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private MembershipDao membershipDao;
	
	@Resource
	private SchoolService schoolService;

	public void setMembershipDao(MembershipDao membershipDao) {
		this.membershipDao = membershipDao;
	}
	

	public void setSchoolService(SchoolService schoolService) {
		this.schoolService = schoolService;
	}



	@Override
	public Membership findMembershipById(Integer id) {
		try {
			return membershipDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public Membership addGeneral(String name,String appKey) {
		Membership membership = new Membership();
		//appId统一设置为0;
		membership.setAppKey(appKey);
		membership.setOwnerId(MembershipContans.OWNER_ID);
		membership.setOwnerType(MembershipContans.OWNER_TYPE_GENERALSCHOOL);
		membership.setParentId(MembershipContans.PARANT_ID);
		// 创建顶级组织关系时，parent_id，owner_id，owner_type设为0
		membership.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
		membership.setName(name);
		membership.setRegionCode("");
		membership.setSortOrder(0);
		Date createDate = membership.getCreateDate();
		if (createDate == null) {
			createDate = new Date();
		}
		membership.setCreateDate(createDate);
		membership.setModifyDate(createDate);
		membership.setIsDeleted(false);
		
		return membershipDao.create(membership);
	}

	@Override
	public List<MembershipVo> findHeadSchools() {
		MembershipCondition condition = new MembershipCondition();
		condition.setParentId(MembershipContans.PARANT_ID);
		condition.setOwnerId(MembershipContans.OWNER_ID);
		condition.setOwnerType(MembershipContans.OWNER_TYPE_BRANCHSCHOOL);
		condition.setIsDeleted(false);
		//查询总校
		List<MembershipVo> items = this.membershipDao.findMembershipVoByCondition(condition, null, null);
		if(items.size()>0){
			for(MembershipVo membershipVo :items){
				
				String connectSchoolName = "";
				Integer connectSchoolCount = 0;
					List<Membership>childrenList = this.membershipDao.findByTermList(membershipVo.getId(), null,MembershipContans.OWNER_TYPE_BRANCHSCHOOL , false);
					connectSchoolCount = childrenList.size();
					if(childrenList.size()>0){
							for(Membership membership:childrenList){
								connectSchoolName+=membership.getName();
						}
				    }
				
				
				membershipVo.setConnectSchoolName("".equals(connectSchoolName)?"无":connectSchoolName);
				membershipVo.setConnectSchoolCount(connectSchoolCount);
				
			}
		}

		return items;
	}

	/**
	 * 添加分校区记录
	 */
	@Override
	public void assignSchoolBranchToGeneral (Integer branchSchoolID, Integer generalSchoolId,String appKey){
		
		School s = this.schoolService.findSchoolById(branchSchoolID);
		Membership membership = this.membershipDao.findByTerm(generalSchoolId, String.valueOf(branchSchoolID), MembershipContans.OWNER_TYPE_BRANCHSCHOOL, true);
		if(membership==null){
			membership = new Membership();
			membership.setName(s.getName());
			membership.setAppKey(appKey);
			membership.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
			membership.setParentId(generalSchoolId);
			membership.setOwnerId(String.valueOf(s.getId()));
			membership.setOwnerType(MembershipContans.OWNER_TYPE_BRANCHSCHOOL);
			membership.setRegionCode(s.getDistrict());
			membership.setSortOrder(1);
			membership.setCreateDate(new Date());
			membership.setModifyDate(new Date());
			membership.setIsDeleted(false);
			this.membershipDao.create(membership);
			
		}else{
			membership.setModifyDate(new Date());
			membership.setIsDeleted(false);
			this.membershipDao.update(membership);
			
		}
		
	}
	@Override
	public Membership add(Membership membership) {
		if(membership == null) {
    		return null;
    	}
		
    	Date createDate = membership.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	membership.setCreateDate(createDate);
    	membership.setModifyDate(createDate);
    	membership.setIsDeleted(false);
		return membershipDao.create(membership);
	}

	@Override
	public Membership modify(Membership membership) {
		if(membership == null) {
    		return null;
    	}
    	Date modify = membership.getModifyDate();
    	membership.setModifyDate(modify != null ? modify : new Date());
		return membershipDao.update(membership);
	}
	
	@Override
	public void remove(Membership membership) {
		try {
			membershipDao.delete(membership);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", membership.getId(), e);
			}
		}
	}
	
	@Override
	public List<Membership> findMembershipByCondition(MembershipCondition membershipCondition, Page page, Order order) {
		return membershipDao.findMembershipByCondition(membershipCondition, page, order);
	}
	
	@Override
	public List<Membership> findMembershipByCondition(MembershipCondition membershipCondition) {
		return membershipDao.findMembershipByCondition(membershipCondition, null, null);
	}
	
	@Override
	public List<Membership> findMembershipByCondition(MembershipCondition membershipCondition, Page page) {
		return membershipDao.findMembershipByCondition(membershipCondition, page, null);
	}
	
	@Override
	public List<Membership> findMembershipByCondition(MembershipCondition membershipCondition, Order order) {
		return membershipDao.findMembershipByCondition(membershipCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.membershipDao.count(null);
	}

	@Override
	public Long count(MembershipCondition membershipCondition) {
		return this.membershipDao.count(membershipCondition);
	}


	@Override
	public List<MembershipVo> findMembershipVoByCondition(
			MembershipCondition membershipCondition, Page page, Order order) {
		
		membershipCondition.setOwnerType(MembershipContans.OWNER_TYPE_GENERALSCHOOL);
		membershipCondition.setIsDeleted(false);
		List<MembershipVo>memberShipVoList = this.membershipDao.findMembershipVoByCondition(membershipCondition, page, order);
		if(memberShipVoList.size()>0){
			for(MembershipVo membershipVo :memberShipVoList){
				
				String connectSchoolName = ""; 
				Integer connectSchoolCount = 0; 
					List<Membership>childrenList = this.membershipDao.findByTermList(membershipVo.getId(), null,MembershipContans.OWNER_TYPE_BRANCHSCHOOL , false);
					connectSchoolCount = childrenList.size();
					if(childrenList.size()>0){
							for(Membership membership:childrenList){
								connectSchoolName+=membership.getName()+"、";
						}
				    }
				
				
				membershipVo.setConnectSchoolName("".equals(connectSchoolName)?"无":connectSchoolName.substring(0, connectSchoolName.length()-1));
				membershipVo.setConnectSchoolCount(connectSchoolCount);
				
			}
		}
		
		return memberShipVoList;
	}

	
	/**
	 * 移出分校区记录
	 */
	@Override
	public void removeSchoolBranchFromGeneral(Integer branchSchoolID,
			Integer generalSchoolId) {
		Membership membership = this.membershipDao.findByTerm(generalSchoolId, String.valueOf(branchSchoolID), MembershipContans.OWNER_TYPE_BRANCHSCHOOL,false);
		if(membership!=null){
			membership.setIsDeleted(true);
			membership.setModifyDate(new Date());
			this.membershipDao.update(membership);
			
		}
		
	}

	/**
	 * 修改分校区记录
	 */
	@Override
	public void modifySchoolBranchFromGenneral(Integer branchSchoolID,
			Integer generalSchoolId) {
		Membership membership = this.membershipDao.findByTerm(generalSchoolId, String.valueOf(branchSchoolID), MembershipContans.OWNER_TYPE_BRANCHSCHOOL,true);
		if(membership!=null){
			membership.setIsDeleted(false);
			membership.setModifyDate(new Date());
			this.membershipDao.update(membership);
			
		}
	}

	@Override
	public Membership findByTerm(Integer parentId,String ownerId,String ownerType,Boolean isDeleted){
		return this.membershipDao.findByTerm(parentId, ownerId, ownerType, isDeleted);
	}

	@Override
	public List<Membership> findByTermList(Integer parentId, String ownerId,
			String ownerType, Boolean isDeleted) {
		return this.membershipDao.findByTermList(parentId, ownerId, ownerType, isDeleted);
	}



}
