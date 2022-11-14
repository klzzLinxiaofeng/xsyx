package com.xunyunedu.department.service;

import com.xunyunedu.department.condition.PjDepartmentTeacherCondition;
import com.xunyunedu.department.pojo.PjDepartmentTeacher;
import com.xunyunedu.exception.ApiResult;

import java.util.List;

public interface PjDepartmentTeacherService {
    ApiResult add(PjDepartmentTeacher departmentTeacher);

    ApiResult update(PjDepartmentTeacher departmentTeacher);

    List<PjDepartmentTeacher> getByTeacherId(Integer teacherId);

    List<PjDepartmentTeacher> getByDepartmentId(Integer departmentId);

    List<PjDepartmentTeacher> getByDepartmentIdAndSchoolId(Integer departmentId,Integer schoolId);

    PjDepartmentTeacher getOneByCondition(PjDepartmentTeacherCondition condition);

    List<PjDepartmentTeacher> getByCondition(PjDepartmentTeacherCondition condition);
    List<Integer> getUserIdListByCondition(PjDepartmentTeacherCondition condition);
}
