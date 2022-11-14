package com.xunyunedu.student.service.impl;

import com.xunyunedu.exception.BaseConstant;
import com.xunyunedu.student.dao.StudentDao;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.service.StudentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentDao dao;

    @Override
    public List<StudentPojo> getStudentByTeacherId(@Param("studentPojo") StudentPojo studentPojo) {
        return dao.getStudentByTeacherId(studentPojo);
    }

    @Override
    public List<StudentPojo> getIdByUserId(String name,Integer teamId) {
        return dao.getIdByUserId(name,teamId);
    }

    /**
     * 判断小程序个人是否修改对应的学生信息
     *
     * @return
     */
    @Override
    public Map<String, Boolean> getAppletsInterrupteur() {
        Map<String, Boolean> map = dao.getAppletsInterrupteur(BaseConstant.APPLETS_TYPE_EDIT.getMesg().toString());
        return map;
    }

    @Override
    public List<StudentPojo> findStudentVoByTeam(Integer schoolId, String schoolYear, Integer gradeId, Integer teamId) {
        return dao.findStudentVoByTeam( schoolId,  schoolYear,  gradeId,  teamId);

    }
}
