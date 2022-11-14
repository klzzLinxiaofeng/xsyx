package com.xunyunedu.workAttendance.service;

import com.xunyunedu.workAttendance.model.PjTeamTeacher;
import org.apache.ibatis.annotations.Param;

public interface PjTeamTeacherService {

    PjTeamTeacher findTeacherByTeacherId(@Param("teacherId") Integer teacherId);
}
