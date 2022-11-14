package com.xunyunedu.team.service;

import com.xunyunedu.team.pojo.TeamPojo;

import java.util.List;

/**
 * 班级信息
 *
 * @author: yhc
 * @Date: 2020/12/18 15:49
 * @Description:
 */
public interface TeamService {

    List<TeamPojo> getTeamList(TeamPojo teamPojo);
    List<TeamPojo> findTeamOfGradeAndSchool(Integer schoolId,Integer gradeId);

}
