package com.xunyunedu.mergin.service;

import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.mergin.vo.StudentArchiveComplete;
import com.xunyunedu.mergin.vo.StudentCondition;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

import java.util.List;

public interface StudentServiceTwo {
    public List<Student> findStudentByCondition(StudentCondition studentCondition, Page page, Order order);

    public StudentArchiveComplete setStudentArchiveComplete(Integer studentId,
                                                            StudentArchiveComplete studentArchiveComplete, Boolean isComplet, Boolean isBindingMobile);
        //2016-9-5 新增参数 isBindingMobile 是否绑定手机号为学生账号

    /**
     * @function 根据学生ID获取学生的基本信息
     * @param studentId
     * @date 2016-7-21
     * @return
     */
    public StudentArchiveComplete getStudentArchiveComplete(Integer studentId);
    }
