package com.xunyunedu.student.dao;

import com.xunyunedu.student.pojo.StudentHealthArchivePojo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Eternityhua
 * @create 2020-12-09 15:58
 */
public interface StudentHealthArchiveDao {
    StudentHealthArchivePojo findById(Integer id);

    List<StudentHealthArchivePojo> findAllStudentHealthArchive();

    void createHealth(StudentHealthArchivePojo studentHealthArchivePojo);

    void updateStuMessage(StudentHealthArchivePojo studentHealthArchivePojo);

    List<StudentHealthArchivePojo> getStudentCondition();


    void updateById(@Param("pojo") StudentHealthArchivePojo pojo);

    StudentHealthArchivePojo selectById(@Param("id") Integer id);

    void deleteStudent(List<Integer> ids);

    void deleteStu(Integer ids);

    void updateStatusById(Integer id);

    List<StudentHealthArchivePojo> getStuByStuId(@Param("stuId") Integer stuId);

    List<StudentHealthArchivePojo> queryByTeamId(@Param("teamId") Integer teamId);


//
//    List<StudentHealthArchivePojo> selectAllByNames(StudentHealthArchivePojo pojo);


}
