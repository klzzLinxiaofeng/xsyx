package com.xunyunedu.teacher.service;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.student.pojo.StudentAskingPojo;
import com.xunyunedu.teacher.condition.TeacherSearchCondition;
import com.xunyunedu.teacher.pojo.TeacherPojo;

import java.util.List;
import java.util.Map;

/**
 * 教师端首页
 *
 * @author: yhc
 * @Date: 2020/11/2 9:44
 * @Description:
 */
public interface TeacherHomeService {

    TeacherPojo getTeacherById(Integer teacherId);

    void modifyIndiaSatus(StudentAskingPojo studentAskingPojo);

//    <T> T getAgentInfo(ApprovePojo approvePojo, Integer pageNum, Integer pageSize, Integer indiaType);

    TeacherPojo getTeacherByCondition(TeacherPojo teacherPojo);

    void updateTeacherInfor(TeacherPojo teacherPojo);

    List<TeacherPojo> getBySchoolId(Integer schoolId);

    PageInfo pagingSelectTeacherCoreInfo(PageCondition<TeacherSearchCondition> pageCondition);

    TeacherPojo getByUserId(Integer userId);

    List<Integer> getTeacherTeamList(Integer userId,Integer type);

    /**
     * 得到班主任管理的班级id
     * @param userId 班主任用户id
     * @return
     */
    Integer getManagedTeamId(Integer userId);

    void updateTeacher(TeacherPojo teacherPojo);
}
