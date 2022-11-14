package com.xunyunedu.student.dao;

import com.xunyunedu.student.pojo.StudentHealthArchiveTypePojo;
import com.xunyunedu.student.pojo.StudentPojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentHealthArchiveTypeDao {

    Integer insert(StudentHealthArchiveTypePojo entity);

    void updateTypeByStudentHealthId(StudentHealthArchiveTypePojo entity);

    List<StudentHealthArchiveTypePojo> getTypeById(Integer studentHealthId);

    //void deleteByHealthId(@Param("studentHealthId") Integer studentHealthId);
    void deleteByHealthId(@Param("studentHealthId") Integer studentHealthId);

    List<Integer> selectByHealthId(Integer id);

    List<StudentHealthArchiveTypePojo> queryAll();
     List<StudentPojo> getByTeamId(@Param("teamId")Integer teamId,
                                   @Param("schoolId") Integer schoolId);


    //void delHealthTypeById(Integer id);
}
