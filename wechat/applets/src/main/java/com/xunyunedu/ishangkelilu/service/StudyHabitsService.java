package com.xunyunedu.ishangkelilu.service;

import com.xunyunedu.ishangkelilu.pojo.StudyHabits;
import com.xunyunedu.login.pojo.ZuoWeiHangLie;
import com.xunyunedu.team.pojo.TeamPojo;

import java.util.List;
import java.util.Map;

public interface StudyHabitsService {
    List<TeamPojo> findByTeam(Integer teacherId, Integer schoolId, String schoolYear);
    Map<String,Object> findBystudentXiGuan(Integer studentId, Integer schoolId, String schoolYear);
    Boolean create(StudyHabits studyHabits);
    List<StudyHabits> findByShangke(Integer teamId, Integer teacherId);
    ZuoWeiHangLie findByzuoweiHangfLie(Integer teamId);
    String createOrupdate(StudyHabits studyHabits);
    Boolean findBychehui(Integer id);
    String findByXiake(Integer teacherId, Integer teamId);
}
