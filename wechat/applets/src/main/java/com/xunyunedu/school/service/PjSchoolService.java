package com.xunyunedu.school.service;

import com.xunyunedu.school.pojo.PjSchool;
import com.xunyunedu.team.pojo.SchoolTermPojo;

public interface PjSchoolService {
    PjSchool getById(Integer id);

    SchoolTermPojo getBySchoolId(Integer schoolId,String startDate);
}
