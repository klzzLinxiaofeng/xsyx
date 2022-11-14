package com.xunyunedu.department.service.impl;

import com.xunyunedu.department.condition.PjDepartmentTeacherCondition;
import com.xunyunedu.department.dao.PjDepartmentTeacherDao;
import com.xunyunedu.department.pojo.PjDepartmentTeacher;
import com.xunyunedu.department.service.PjDepartmentTeacherService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PjDepartmentTeacherServiceImpl implements PjDepartmentTeacherService {

    @Autowired
    PjDepartmentTeacherDao dao;


    @Override
    public ApiResult add(PjDepartmentTeacher departmentTeacher){
        return dao.insert(departmentTeacher)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public ApiResult update(PjDepartmentTeacher departmentTeacher){
        return dao.update(departmentTeacher)>0?ApiResult.of(ResultCode.SUCCESS):ApiResult.of(ResultCode.SAVE_FAIL);
    }

    @Override
    public List<PjDepartmentTeacher> getByTeacherId(Integer teacherId){
        return dao.selectByTeacherId(teacherId);
    }

    @Override
    public List<PjDepartmentTeacher> getByDepartmentId(Integer departmentId) {
        return getByCondition(new PjDepartmentTeacherCondition().setDepartmentId(departmentId).setIsDelete(false));
    }

    @Override
    public List<PjDepartmentTeacher> getByDepartmentIdAndSchoolId(Integer departmentId, Integer schoolId) {
        return getByCondition(new PjDepartmentTeacherCondition().setDepartmentId(departmentId).setIsDelete(false).setSchoolId(schoolId));
    }


    @Override
    public PjDepartmentTeacher getOneByCondition(PjDepartmentTeacherCondition condition){

        List<PjDepartmentTeacher> list = dao.selectByCondition(condition);
        if(list == null){
            return null;
        }
        return list.get(0);

    }

    @Override
    public List<PjDepartmentTeacher> getByCondition(PjDepartmentTeacherCondition condition){
        return dao.selectByCondition(condition);
    }

    @Override
    public List<Integer> getUserIdListByCondition(PjDepartmentTeacherCondition condition) {
        return dao.selectUserIdListByCondition(condition);
    }
}
