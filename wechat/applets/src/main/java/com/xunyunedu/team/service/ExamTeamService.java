package com.xunyunedu.team.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.team.pojo.ExamTeamPojo;
import com.xunyunedu.team.pojo.ParamPojo;
import com.xunyunedu.team.pojo.TeamStuScorePojo;

import java.util.List;
import java.util.Map;

/**
 * 班级学生成绩业务层
 *
 * @author: lee
 */
public interface ExamTeamService {

    /**
     * 班级成绩查询
     *
     * @param teacherId
     * @param schoolId
     * @return
     */
    PageInfo<ExamTeamPojo> getExamTeamList(ParamPojo pojo, Integer pageNum, Integer pageSize);

    /**
     * @param id
     * @param schoolId
     * @return
     */
    List<TeamStuScorePojo> getStudentScoreByTeamId(Integer id, Integer schoolId);

    Map<String, Object> getStudentScoreAnalysis(ParamPojo pojo, Integer pageNum, Integer pageSize);

    List<Map<String, String>> getStuTerm(Integer stuId, Integer schoolId);

    List<Map<Integer, String>> getTeacherClass(Integer teacherId, Integer schoolId);

    List<Map<Integer, String>> getTeacherSubject(Integer teamId, Integer schoolId, Integer type);

    List<Map<Integer, String>> getStuSubject(Integer stuId, Integer schoolId, Integer type);
}