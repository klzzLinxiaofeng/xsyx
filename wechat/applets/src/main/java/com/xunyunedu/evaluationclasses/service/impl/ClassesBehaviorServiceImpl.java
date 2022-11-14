package com.xunyunedu.evaluationclasses.service.impl;

import com.xunyunedu.evaluationclasses.dao.ClassesBehaviorDao;
import com.xunyunedu.evaluationclasses.service.ClassesBehaviorService;
import com.xunyunedu.evaluationclasses.vo.ClassesBehavior;
import com.xunyunedu.evaluationclasses.vo.ClassesIndicators;
import com.xunyunedu.student.pojo.StudentPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class ClassesBehaviorServiceImpl implements ClassesBehaviorService {
    @Autowired
    private ClassesBehaviorDao classesBehaviorDao;
    @Override
    public List<ClassesIndicators> findByaLL(String schoolYear, String schoolTrem) {
        return classesBehaviorDao.findByaLL(schoolYear,schoolTrem);
    }

    @Override
    public Integer create(ClassesBehavior classesBehavior) {
        return classesBehaviorDao.create(classesBehavior);
    }

    @Override
    public Integer findByFenShu(Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem) {
        Integer num=classesBehaviorDao.findByFenShu(studentId,zhibiaoId,schoolYear,schoolTrem);
        ClassesIndicators classesIndicators=classesBehaviorDao.findById(zhibiaoId);
        if(num!=null){
            return   classesIndicators.getScore()+num;
        }else{
            return   classesIndicators.getScore();
        }
    }

    @Override
    public List<StudentPojo> findById(String studentName, Integer teamId,Integer studentId) {
        return classesBehaviorDao.findByteamId( studentName,  teamId,studentId);
    }
}
