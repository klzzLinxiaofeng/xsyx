package com.xunyunedu.mergin.dao;

import com.xunyunedu.mergin.vo.TeamStudent;
import com.xunyunedu.mergin.vo.TeamStudentCondition;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TeamStudentDao extends GenericDao<TeamStudent, Integer> {
    List<TeamStudent> findTeamStudentByCondition(@Param("teamStudentCondition") TeamStudentCondition teamStudentCondition,
                                                 @Param("page") Page page,
                                                 @Param("order") Order order);
    TeamStudent findUnique(@Param("teamId") Integer teamId,@Param("studentId") Integer studentId);
}

