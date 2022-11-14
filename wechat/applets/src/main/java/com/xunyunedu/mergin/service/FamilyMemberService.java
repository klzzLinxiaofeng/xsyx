package com.xunyunedu.mergin.service;


import com.xunyunedu.mergin.vo.FamilyMember;
import com.xunyunedu.mergin.vo.ParentMess;

import java.util.List;

public interface FamilyMemberService {
    List<FamilyMember> findByStudentId(Integer studentId);

    FamilyMember add(FamilyMember familyMember);

    FamilyMember modify(FamilyMember familyMember);
    /**
     * 获取家庭成员信息（用于学生档案）
     * @param studentId
     * @return
     */
    List<ParentMess> findParentMessByStudentId(Integer studentId);
}
