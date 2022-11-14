package com.xunyunedu.student.service.impl;

import com.xunyunedu.student.dao.StudentHealthArchiveTypeDao;
import com.xunyunedu.student.pojo.StudentHealthArchiveTypePojo;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.service.StudentHealthArchiveTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentHealthArchiveTypeServiceImpl implements StudentHealthArchiveTypeService {

    @Autowired
    private StudentHealthArchiveTypeDao studentHealthArchiveTypeDao;

    @Override
    public Integer createType(StudentHealthArchiveTypePojo entity) {
        return studentHealthArchiveTypeDao.insert(entity);
    }

    @Override
    public void updateTypeByStudentHealthId(StudentHealthArchiveTypePojo entity) {
        studentHealthArchiveTypeDao.updateTypeByStudentHealthId(entity);
    }

    @Override
    public List<StudentHealthArchiveTypePojo> findAllStudentHealthTypeArchive() {
        return studentHealthArchiveTypeDao.queryAll();
    }

    @Override
    public List<StudentPojo> getByTeamId(Integer teamId, Integer schoolId) {
        return studentHealthArchiveTypeDao.getByTeamId(teamId,schoolId);
    }


}
