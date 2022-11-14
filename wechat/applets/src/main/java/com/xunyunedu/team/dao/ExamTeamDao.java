package com.xunyunedu.team.dao;

import com.xunyunedu.team.pojo.ExamTeamPojo;
import com.xunyunedu.team.pojo.ParamPojo;
import com.xunyunedu.team.pojo.TeamStuScorePojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;


/**
 * 班级/学生成绩信息查询
 *
 * @author lee
 */
public interface ExamTeamDao {
    /**
     * 班级成绩
     *
     * @return
     */
    List<ExamTeamPojo> read(ParamPojo pojo);

    /**
     * 学生成绩
     *
     * @param id
     * @param schoolId
     * @return
     */
    List<TeamStuScorePojo> readStudentScore(@Param("id") Integer id, @Param("schoolId") Integer schoolId);


    /**
     * 获取学生的考试历史记录分数
     *
     * @param paramPojo
     * @return
     */
    List<ExamTeamPojo> readStuScoreInfo(ParamPojo paramPojo);

    Integer getStuOrder(@Param("id") Integer id, @Param("stuId") Integer stuId, @Param("schoolId") Integer schoolId);

    /**
     * 获取当前学生的所有学期参与过考试的学期
     *
     * @param stuId
     * @param schoolId
     * @return
     */
    List<Map<String, String>> getStuTerm(@Param("stuId") Integer stuId, @Param("schoolId") Integer schoolId);

    /**
     * 获取学生在班级的排名
     *
     * @param id
     * @param schoolId
     * @param uuid
     * @return
     */
    Integer getTeamOrder(@Param("id") Integer id, @Param("schoolId") Integer schoolId, @Param("uuid") String uuid);

    /**
     * 获取学生在学校排名
     *
     * @param uuid
     * @param stuId
     * @param schoolId
     * @return
     */
    Integer getStuSchoolOrder(@Param("uuid") String uuid, @Param("stuId") Integer stuId, @Param("schoolId") Integer schoolId);
}
