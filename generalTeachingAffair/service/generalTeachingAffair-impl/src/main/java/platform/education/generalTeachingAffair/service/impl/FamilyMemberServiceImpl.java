package platform.education.generalTeachingAffair.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.FamilyMember;
import platform.education.generalTeachingAffair.vo.FamilyMemberCondition;
import platform.education.generalTeachingAffair.vo.ParentMess;
import platform.education.generalTeachingAffair.service.FamilyMemberService;
import platform.education.generalTeachingAffair.dao.FamilyMemberDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class FamilyMemberServiceImpl implements FamilyMemberService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private FamilyMemberDao familyMemberDao;

	public void setFamilyMemberDao(FamilyMemberDao familyMemberDao) {
		this.familyMemberDao = familyMemberDao;
	}
	
	@Override
	public FamilyMember findFamilyMemberById(Integer id) {
		try {
			return familyMemberDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public FamilyMember add(FamilyMember familyMember) {
		if(familyMember == null) {
    		return null;
    	}
    	Date createDate = familyMember.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	familyMember.setCreateDate(createDate);
    	familyMember.setModifyDate(createDate);
		return familyMemberDao.create(familyMember);
	}

	@Override
	public FamilyMember modify(FamilyMember familyMember) {
		if(familyMember == null) {
    		return null;
    	}
    	Date modify = familyMember.getModifyDate();
    	familyMember.setModifyDate(modify != null ? modify : new Date());
		return familyMemberDao.update(familyMember);
	}
	
	@Override
	public void remove(FamilyMember familyMember) {
		try {
			familyMemberDao.delete(familyMember);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", familyMember.getId(), e);
			}
		}
	}
	
	@Override
	public List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition, Page page, Order order) {
		return familyMemberDao.findFamilyMemberByCondition(familyMemberCondition, page, order);
	}
	
	@Override
	public List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition) {
		return familyMemberDao.findFamilyMemberByCondition(familyMemberCondition, null, null);
	}
	
	@Override
	public List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition, Page page) {
		return familyMemberDao.findFamilyMemberByCondition(familyMemberCondition, page, null);
	}
	
	@Override
	public List<FamilyMember> findFamilyMemberByCondition(FamilyMemberCondition familyMemberCondition, Order order) {
		return familyMemberDao.findFamilyMemberByCondition(familyMemberCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.familyMemberDao.count(null);
	}

	@Override
	public Long count(FamilyMemberCondition familyMemberCondition) {
		return this.familyMemberDao.count(familyMemberCondition);
	}

	@Override
	public List<FamilyMember> findByStudentId(Integer studentId) {
		return this.familyMemberDao.findByStudentId(studentId);
	}

	@Override
	public List<ParentMess> findParentMessByStudentId(Integer studentId) {
		ParentMess parentMess = null;
		List<ParentMess> parentMessList = new ArrayList<ParentMess>();
		List<FamilyMember> familyMemberList = this.familyMemberDao.findByStudentId(studentId);
		
		if (familyMemberList != null && familyMemberList.size() > 0) {
			for (FamilyMember familyMember : familyMemberList) {
				parentMess = new ParentMess();
				parentMess.setName(familyMember.getName() != null ? familyMember.getName() : "");
				parentMess.setMobile(familyMember.getMobile() != null ? familyMember.getMobile() : "");
				parentMess.setParentRelation(familyMember.getRelation() != null ? familyMember.getRelation() : "");
				parentMess.setPrealtionRemarks(familyMember.getRelationRemarks() != null ? familyMember.getRelationRemarks() : "");
				parentMess.setRace(familyMember.getRace() != null ? familyMember.getRace() : "");
				parentMess.setWorkingPlace(familyMember.getWorkingPlace() != null ? familyMember.getWorkingPlace() : "");
				parentMess.setPosition(familyMember.getPosition() != null ? familyMember.getPosition() : "");
				parentMess.setAddress(familyMember.getAddress() != null ? familyMember.getAddress() : "");
				parentMess.setResidenceAddressCode(familyMember.getResidenceAddress() != null ? familyMember.getResidenceAddress() : "");
				parentMess.setRank(familyMember.getRank() != null ? familyMember.getRank() : "");
				parentMess.setIdCardType(familyMember.getIdCardType() != null ? familyMember.getIdCardType() : "");
				parentMess.setIdCardNumber(familyMember.getIdCardNumber() != null ? familyMember.getIdCardNumber() : "");
				parentMess.setResidenceAddress("");
				parentMessList.add(parentMess);
			}
			// 家庭成员只有一个，添加一个默认值
			if (familyMemberList.size() == 1) {
				parentMess = setParentMess("家庭成员二");
				parentMessList.add(parentMess);
			}

		} else {
			parentMess = setParentMess("家庭成员一");
			parentMessList.add(parentMess);
			parentMess = setParentMess("家庭成员二");
			parentMessList.add(parentMess);
		}
		return parentMessList;
	}

	private ParentMess setParentMess(String name){
		ParentMess parentMess = new ParentMess();
		parentMess.setName(name);
		parentMess.setMobile("");
		parentMess.setParentRelation("");
		parentMess.setPrealtionRemarks("");
		parentMess.setRace("");
		parentMess.setWorkingPlace("");
		parentMess.setPosition("");
		parentMess.setAddress("");
		parentMess.setResidenceAddressCode("");
		parentMess.setRank("");
		parentMess.setIdCardType("");
		parentMess.setIdCardNumber("");
		parentMess.setResidenceAddress("");
		return parentMess;
	}
}
