package com.xunyunedu.PublishAndAcceptJob.service.impl;

import com.xunyunedu.PublishAndAcceptJob.dao.TeamTeacherDao;
import com.xunyunedu.PublishAndAcceptJob.pojo.TeamPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.TeamTeacherPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.TeamTeacherVo;
import com.xunyunedu.PublishAndAcceptJob.service.TeamTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 老师发布作业service层实现类
 * @author lee
 * @Date 2020/12/06
 */
@Service
public class TeamTeacherServiceImpl implements TeamTeacherService {


    @Autowired
    private TeamTeacherDao teamTeacherDao;

    @Override
    public List<Map<String, Object>> getTeacherTeachTeam(Integer userId, String schoolYear) {
        List<TeamTeacherVo> voList = teamTeacherDao.findTeacherTeachInfo(userId, schoolYear, null);
        List<Map<String, Object>> list=new ArrayList<>();

        for (TeamTeacherVo teamTeacherVo : voList) {
            Map<String, Object> teamMap = new HashMap<String, Object>();
            teamMap.put("teamId",teamTeacherVo.getTeamId() );
            teamMap.put("teamName",teamTeacherVo.getTeamName());
            list.add(teamMap);
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getTeacherTeachSubject(Integer userId, String schoolYear, Integer teamId) {
        List<TeamTeacherVo> voList = teamTeacherDao.findTeacherTeachInfo(userId, schoolYear, teamId);
        List<Map<String,Object>> list=new ArrayList<>();

        for (TeamTeacherVo teamTeacherVo : voList) {
            Map<String, Object> subjectMap = new HashMap<String, Object>();
            subjectMap.put("subjectCode",teamTeacherVo.getSubjectCode());
            subjectMap.put("subjectName", teamTeacherVo.getSubjectName());
            list.add(subjectMap);
        }
        return list;
    }

    /**
     * 根据teacherId查询
     * @param teacherId  入参
     * @return 返回数据
     */
    @Override
    public List<TeamTeacherPojo> getTeamTeacherSubject(Integer teacherId) {
        return teamTeacherDao.findByTeacherId(teacherId);
    }

    /**
     * 根据老师id，学科id查询班级
     * @param teacherId
     * @param subjectId
     * @return
     */
    @Override
    public List<TeamPojo> getTeamByTeacherIdAndSubjectId(Integer teacherId, Integer subjectId) {
        List<TeamPojo> list = teamTeacherDao.findTeamByTeacherIdAndSubjectId(teacherId,subjectId);
        return list;
    }
}
