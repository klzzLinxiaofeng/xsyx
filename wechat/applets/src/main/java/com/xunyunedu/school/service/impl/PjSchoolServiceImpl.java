package com.xunyunedu.school.service.impl;

import com.xunyunedu.school.dao.PjSchoolDao;
import com.xunyunedu.school.pojo.PjSchool;
import com.xunyunedu.school.service.PjSchoolService;
import com.xunyunedu.team.pojo.SchoolTermPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PjSchoolServiceImpl implements PjSchoolService {

    @Autowired
    PjSchoolDao dao;

    @Override
    public PjSchool getById(Integer id){
        return dao.selectById(id);
    }

    @Override
    public SchoolTermPojo getBySchoolId(Integer schoolId,String startDate) {
        return dao.getTermBySchoolId(schoolId,startDate);
    }

}
