package com.xunyunedu.PublishAndAcceptJob.dao;

import com.xunyunedu.PublishAndAcceptJob.pojo.TeamPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.TeamTeacherPojo;
import com.xunyunedu.PublishAndAcceptJob.pojo.TeamTeacherVo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 老师发布作业dao层
 * @author lee
 * @Date 2020/12/06 16:02
 */
public interface TeamTeacherDao {

    List<TeamTeacherVo> findTeacherTeachInfo(@Param("userId")Integer userId,
                                             @Param("schoolYear")String schoolYear,
                                             @Param("teamId")Integer teamId);



    /**
     * 根据老师id查询对应科目
     * @param teacherId
     * @return
     */
    List<TeamTeacherPojo> findByTeacherId(@Param("teacherId") Integer teacherId);

    /**
     * 根据老师id，学科id查询对应的班级
     * @param teacherId
     * @param subjectId
     * @return
     */
    List<TeamPojo> findTeamByTeacherIdAndSubjectId(@Param("teacherId") Integer teacherId, @Param("subjectId") Integer subjectId);
}
