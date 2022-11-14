package com.xunyunedu.student.service;

import com.xunyunedu.student.pojo.StudentHealthArchivePojo;
import com.xunyunedu.student.pojo.StudentPojo;


import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-09 15:58
 */
public interface StudentHealthArchiveService {

    StudentHealthArchivePojo findStudentHealthArchiveById(Integer id);


    //List<StudentHealthArchivePojo> getStudentCondition(StudentHealthArchivePojo studentHealthArchivePojo);

    List<StudentHealthArchivePojo> findAllStudentHealthArchive();


    void createHealth(StudentHealthArchivePojo studentHealthArchivePojo);

    //StudentPojo getStudent(Integer stuId);
//
//    void modifyStuMessage(StudentHealthArchivePojo studentHealthArchivePojo);


    void save(StudentHealthArchivePojo pojo);





    void delete(Integer id);

    void update(StudentHealthArchivePojo pojo);

    StudentHealthArchivePojo queryById(Integer id);

    List<StudentHealthArchivePojo> queryAll();


    void del(Integer id);

    List<StudentHealthArchivePojo> queryByStuId(Integer stuId);

    List<StudentHealthArchivePojo> queryByTeamId(Integer teamId);
}
