package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.FamilyMemberDao;
import com.xunyunedu.mergin.service.FamilyMemberService;
import com.xunyunedu.mergin.vo.FamilyMember;
import com.xunyunedu.mergin.vo.ParentMess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class FamilyMemberServiceImpl implements FamilyMemberService {
    @Autowired
    private FamilyMemberDao familyMemberDao;

    @Override
    public List<FamilyMember> findByStudentId(Integer studentId) {
        return familyMemberDao.findByStudentId(studentId);
    }

    @Override
    public FamilyMember add(FamilyMember familyMember) {
        if (familyMember == null) {
            return null;
        }
        Date createDate = familyMember.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        familyMember.setCreateDate(createDate);
        familyMember.setModifyDate(createDate);
        return familyMemberDao.create(familyMember);
    }

    @Override
    public FamilyMember modify(FamilyMember familyMember) {
        if (familyMember == null) {
            return null;
        }
        Date modify = familyMember.getModifyDate();
        familyMember.setModifyDate(modify != null ? modify : new Date());
        return familyMemberDao.update(familyMember);
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

    private ParentMess setParentMess(String name) {
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
