package com.xunyunedu.PublishAndAcceptJob.service;

import com.xunyunedu.PublishAndAcceptJob.pojo.TeamPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.TeamTeacherPojo;

import java.util.List;
import java.util.Map;

/**
 * 老师发布作业service层
 * @author lee
 * @Date 2020/12/06
 */
public interface TeamTeacherService {

    /**
     * 获取教师任教班级
     * @param userId 用户id
     * @param schoolYear 学年
     * @param gradeId 年级id
     * @return
     */
    List<Map<String, Object>> getTeacherTeachTeam(Integer userId, String schoolYear);

    /**
     *  获取教师任教科目
     * @param userId 用户id
     * @param schoolYear 学年
     * @param gradeId 年级id
     * @param teamId 班级id
     * @return
     */
    List<Map<String, Object>> getTeacherTeachSubject(Integer userId, String schoolYear, Integer teamId);



    /*------------------------作业发布弃用------------------*/
    List<TeamTeacherPojo> getTeamTeacherSubject(Integer teacherId);

    List<TeamPojo> getTeamByTeacherIdAndSubjectId(Integer teacherId, Integer subjectId);
}
