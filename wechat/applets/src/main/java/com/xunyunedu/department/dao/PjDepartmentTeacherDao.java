package com.xunyunedu.department.dao;

import com.xunyunedu.department.condition.PjDepartmentTeacherCondition;
import com.xunyunedu.department.pojo.PjDepartmentTeacher;

import java.util.List;

public interface PjDepartmentTeacherDao {


    Integer insert(PjDepartmentTeacher pjDepartmentTeacher);

    Integer update(PjDepartmentTeacher pjDepartmentTeacher);

    List<PjDepartmentTeacher> selectByTeacherId(Integer teacherId);

    List<PjDepartmentTeacher> selectByCondition(PjDepartmentTeacherCondition condition);
    List<Integer> selectUserIdListByCondition(PjDepartmentTeacherCondition condition);

}
