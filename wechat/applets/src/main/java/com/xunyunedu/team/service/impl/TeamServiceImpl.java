package com.xunyunedu.team.service.impl;

import com.xunyunedu.team.dao.TeamsDao;
import com.xunyunedu.team.pojo.TeamPojo;
import com.xunyunedu.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/12/18 15:49
 * @Description:
 */
@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamsDao teamsDao;

    @Override
    public List<TeamPojo> getTeamList(TeamPojo teamPojo) {
        return teamsDao.getTeamList(teamPojo);
    }

    @Override
    public List<TeamPojo> findTeamOfGradeAndSchool(Integer schoolId, Integer gradeId) {
        return teamsDao.findTeamOfGradeAndSchool(schoolId,gradeId);
    }
}
