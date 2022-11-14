package com.xunyunedu.student.dao;

import com.xunyunedu.student.pojo.StudentAskingPojo;
import com.xunyunedu.student.pojo.TeacherPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/10/15 9:00
 * @Description:
 */

public interface StudentAskingDao {

    /**
     * 获取教师信息
     *
     * @param schooldId
     * @param stuId
     * @return
     */
    List<TeacherPojo> getTeacher(@Param("schooldId") Integer schooldId, @Param("stuId") Integer stuId, @Param("type") Integer type);

    /**
     * 获取指定教师信息
     *
     * @param schooldId
     * @param id
     * @return
     */
    TeacherPojo getTeacherById(@Param("schooldId") Integer schooldId, @Param("id") Integer id);

    /**
     * 添加请假信息
     *
     * @param studentAskingPojo
     */
    Integer addAskingInfo(StudentAskingPojo studentAskingPojo);

    /**
     * 查询请假详细信息
     *
     * @param schooldId
     * @param id
     * @return
     */
    StudentAskingPojo getStuAskingById(@Param("schooldId") Integer schooldId, @Param("id") Integer id);


    List<StudentAskingPojo> getStuAskingList(@Param("studentAskingPojo") StudentAskingPojo studentAskingPojo);
}