package com.xunyunedu.mergin.service;

import com.xunyunedu.mergin.vo.TeamStudent;
import com.xunyunedu.mergin.vo.TeamStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface TeamStudentService {
    TeamStudent modify(TeamStudent teamStudent);
    List<TeamStudent> findTeamStudentByCondition(TeamStudentCondition teamStudentCondition, Page page, Order order);
    /**
     * 功能描述：通过班级ID,学生ID查询出唯一记录
     *
     * @param teamId
     * @param studentId
     * @return
     */
   TeamStudent findUnique(Integer teamId, Integer studentId);
}
