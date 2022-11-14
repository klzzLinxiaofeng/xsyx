package com.xunyunedu.student.service;

import com.xunyunedu.student.pojo.StudentHealthArchiveTypePojo;
import com.xunyunedu.student.pojo.StudentPojo;

import java.util.List;

public interface StudentHealthArchiveTypeService {

    Integer createType(StudentHealthArchiveTypePojo entity);

    void updateTypeByStudentHealthId(StudentHealthArchiveTypePojo entity);

    List<StudentHealthArchiveTypePojo> findAllStudentHealthTypeArchive();
    List<StudentPojo> getByTeamId(Integer teamId,  Integer schoolId);

}
