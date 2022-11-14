package com.xunyunedu.student.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.student.pojo.StudentAskingPojo;
import com.xunyunedu.student.pojo.TeacherPojo;

/**
 * @author: yhc
 * @Date: 2020/in1
 * @Description:
 */
public interface StudentAskingService {


    TeacherPojo getTeacherById(Integer schooldId, Integer stuId);

    TeacherPojo getTeacher(Integer schooldId, Integer id);

    Integer addAskingInfo(StudentAskingPojo studentAskingPojo);

    StudentAskingPojo getStuAskingById(Integer schooldId, Integer id);

    PageInfo<StudentAskingPojo> getStuAskingList(StudentAskingPojo studentAskingPojo, Integer pageNum, Integer pageSize);
}