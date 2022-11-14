package com.xunyunedu.mergin.dao;



import com.xunyunedu.mergin.vo.StudentArchive;
import framework.generic.dao.GenericDao;
import org.apache.ibatis.annotations.Param;

public interface StudentArchiveDao extends GenericDao<StudentArchive, Integer> {
    StudentArchive findByStudentId(@Param("studentId") Integer studentId);
}
