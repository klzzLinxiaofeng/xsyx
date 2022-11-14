package com.xunyunedu.ishangkelilu.dao;

import com.xunyunedu.ishangkelilu.pojo.StudyHabits;
import com.xunyunedu.login.pojo.ZuoWeiHangLie;
import com.xunyunedu.team.pojo.TeamPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudyHabitsDao {
    List<TeamPojo> findByTeam(@Param("teacherId") Integer teacherId,
                             @Param("schoolId") Integer schoolId,
                             @Param("schoolYear") String schoolYear);

    Integer findByStudentTeamId(@Param("studentId") Integer studentId,
                              @Param("schoolId") Integer schoolId);
    List<Integer> findByStudentGradeId(@Param("studentId") Integer studentId,
                                       @Param("schoolId") Integer schoolId,
                                       @Param("schoolYear") String schoolYear
                                       );
   StudyHabits findByStudentStudyHabits(@Param("studentId") Integer studentId,
                                              @Param("leixing") Integer leixing);

            /*班级平均分*/
   StudyHabits findByTeamStudyHabits(
                                           @Param("teamId") Integer teamId,
           /*年级平均分*/                                   @Param("schoolId") Integer schoolId,
                                           @Param("leixing") Integer leixing);
   StudyHabits findByGradeStudyHabits (@Param("leixing") Integer leixing,
                                            @Param("gradeId") Integer gradeId,
                                            @Param("schoolId") Integer schoolId);

   Integer create(@Param("studyHabits")StudyHabits studyHabits);
    Integer createTwo(@Param("studyHabits")StudyHabits studyHabits);

   Integer findByteamNumber(@Param("teamId") Integer teamId);
    Integer findByGradeNumber(@Param("gradeId") Integer gradeId);
    StudyHabits findBysdjw(@Param("teamId") Integer teamId,@Param("leixing") Integer leixing);
    Integer update(@Param("studyHabits")StudyHabits studyHabits);

    List<StudyHabits> findByShangke(@Param("teamId")Integer teamId,@Param("teacherId") Integer teacherId);

    StudyHabits findByStudy(@Param("studyHabits")StudyHabits studyHabits);
    Integer createZanshi(@Param("studyHabits")StudyHabits studyHabits);
    Integer updateZanshi(@Param("studyHabits")StudyHabits studyHabits);
    Integer updateZanshi2(@Param("studyHabits")StudyHabits studyHabits);
    List<StudyHabits> findByXiake(@Param("teacherId") Integer teacherId,@Param("teamId") Integer teamId);
    ZuoWeiHangLie findByzuoweiHangfLie(@Param("teamId") Integer teamId);
}
