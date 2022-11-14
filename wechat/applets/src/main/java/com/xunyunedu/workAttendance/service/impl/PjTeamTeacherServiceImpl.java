package com.xunyunedu.workAttendance.service.impl;

import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.workAttendance.dao.PjTeamTeacherDao;
import com.xunyunedu.workAttendance.model.PjTeamTeacher;
import com.xunyunedu.workAttendance.service.PjTeamTeacherService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PjTeamTeacherServiceImpl implements PjTeamTeacherService {

    @Autowired
    private PjTeamTeacherDao dao;


    @Override
    public PjTeamTeacher findTeacherByTeacherId(@Param("teacherId") Integer teacherId) {
        List<PjTeamTeacher> teacher = dao.findTeacherByTeacherId(teacherId);
        if (teacher != null && teacher.size() > 0) {
            return teacher.get(0);
        } else {
            throw new BusinessRuntimeException(ResultCode.USER_ROLE_ERROR);
        }
    }
}
