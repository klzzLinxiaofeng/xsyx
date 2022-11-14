package com.xunyunedu.development.service.impl;

import com.xunyunedu.development.dao.StudentDevelopmentDao;
import com.xunyunedu.development.service.StudentDevelopmentService;
import com.xunyunedu.development.vo.DevelopmentIndicators;
import com.xunyunedu.development.vo.StudentDevelopment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 17:14
 * @Version 1.0
 */
@Service
public class StudentDevelopmentServiceImpl implements StudentDevelopmentService {
    @Autowired
    private StudentDevelopmentDao studentDevelopmentDao;

    @Override
    public List<DevelopmentIndicators> findByAll(String schoolYear, String schoolTrem, String code) {
        return studentDevelopmentDao.findByAll(schoolYear,schoolTrem,code);
    }

    @Override
    public List<StudentDevelopment> findByStudentAll(String schoolYear, String schoolTrem, Integer pingjiaId) {
        return studentDevelopmentDao.findByStudentAll(schoolYear,schoolTrem,pingjiaId);
    }

    @Override
    public StudentDevelopment findByStudentId(Integer id) {
        return studentDevelopmentDao.findByStudentId(id);
    }

    @Override
    public Integer create(StudentDevelopment studentDevelopment) {
        return studentDevelopmentDao.create(studentDevelopment);
    }
}
