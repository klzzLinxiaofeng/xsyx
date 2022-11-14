package com.xunyunedu.mergin.dao;


import com.xunyunedu.mergin.vo.FamilyMember;
import framework.generic.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FamilyMemberDao extends GenericDao<FamilyMember, Integer> {
    List<FamilyMember> findByStudentId(@Param("jw") Integer studentId);
}
