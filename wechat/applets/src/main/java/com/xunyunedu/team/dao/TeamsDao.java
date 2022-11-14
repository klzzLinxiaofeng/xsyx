package com.xunyunedu.team.dao;

import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.pojo.TeamDO;
import com.xunyunedu.team.pojo.TeamPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 班级信息
 *
 * @author: yhc
 * @Date: 2020/12/18 15:50
 * @Description:
 */
public interface TeamsDao {

    List<TeamPojo> getTeamList(TeamPojo teamPojo);

    /**
     * 获取当前班级下的科目
     *
     * @param teamId
     * @param schoolId
     * @param type
     * @return
     */
    List<Map<Integer, String>> getTeacherSubject(@Param("teamId") Integer teamId, @Param("schoolId") Integer schoolId, @Param("type") Integer type);

    /**
     * 获取当前学生参与的考试科目
     *
     * @param stuId
     * @param schoolId
     * @return
     */
    List<Map<Integer, String>> getStuSubject(@Param("stuId") Integer stuId, @Param("schoolId") Integer schoolId, @Param("type") Integer type);


    List<TeamDO> getTeams(@Param("teacherId") Integer teacherId);

    List<StudentPojo> getTeamStus(@Param("teamId") Integer teamId);
    List<TeamPojo> findTeamOfGradeAndSchool(@Param("schoolId")Integer schoolId,
                             @Param("gradeId") Integer gradeId);
}
