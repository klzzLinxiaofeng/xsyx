package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.TeamStudentDao;
import com.xunyunedu.mergin.service.TeamStudentService;
import com.xunyunedu.mergin.vo.TeamStudent;
import com.xunyunedu.mergin.vo.TeamStudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TeamStudentServiceImpl implements TeamStudentService {
    @Autowired
    private TeamStudentDao teamStudentDao;
    @Override
    public TeamStudent modify(TeamStudent teamStudent) {
        return teamStudentDao.update(teamStudent);
    }

    @Override
    public List<TeamStudent> findTeamStudentByCondition(TeamStudentCondition teamStudentCondition, Page page, Order order)
    {
        return teamStudentDao.findTeamStudentByCondition(teamStudentCondition, page, order);
    }

    @Override
    public TeamStudent findUnique(Integer teamId, Integer studentId) {
        try {
            return teamStudentDao.findUnique(teamId, studentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
