package com.xunyunedu.teacher.dao;

import com.xunyunedu.student.pojo.StudentAskingPojo;
import com.xunyunedu.teacher.condition.TeacherSearchCondition;
import com.xunyunedu.teacher.pojo.DepartmentTeacherPojo;
import com.xunyunedu.teacher.pojo.PublicTeacherPojo;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2020/11/2 9:47
 * @Description:
 */
public interface TeacherHomeDao {
    void modifyIndiaSatus(StudentAskingPojo studentAskingPojo);

    /**
     * 获取教师信息
     *
     * @param teacherPojo
     * @return
     */
    TeacherPojo getTeacherByCondition(TeacherPojo teacherPojo);


    List<PublicTeacherPojo> findByPublicClassIdAndSchoolId(@Param("cid") Integer cid, @Param("schoolId") Integer schoolId);


    /**
     * 修改教师
     *
     * @param teacherPojo
     * @return
     */
    void update(TeacherPojo teacherPojo);

    List<DepartmentTeacherPojo> getTeacherDeparment(@Param("id") Integer id);

    TeacherPojo selectById(Integer id);

    List<TeacherPojo> selectBySchoolId(Integer schoolId);

    List<Map<String, Object>> selectTeacherCoreInfo(TeacherSearchCondition teacherCondition);

    /**
     * 获取当前学年老师教的班级信息
     *
     * @param teacherPojo
     * @return
     */
    List<Map<Integer, String>> getTeacherClass(TeacherPojo teacherPojo);
}
