package com.xunyunedu.mergin.dao;

import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.mergin.vo.StudentCondition;
import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentTwoDao extends GenericDao<Student, Integer> {
    List<Student> findStudentByCondition(@Param("studentCondition") StudentCondition studentCondition, Page page, Order order);

    Student findById(@Param("id") Integer id);
    List<Student> findStudentByOnlyCondition(@Param("studentCondition")StudentCondition studentCondition, Page page, Order order);
}
