package com.xunyunedu.indicators.service.impl;


import com.xunyunedu.indicators.dao.IndicatorsStudentDao;
import com.xunyunedu.indicators.pojo.IndicatorsPojo;
import com.xunyunedu.indicators.pojo.IndicatorsStudent;
import com.xunyunedu.indicators.service.IndicatorsStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicatorsStudentServiceImpl implements IndicatorsStudentService {
    @Autowired
    private IndicatorsStudentDao indicatorsStudentDao;
    @Override
    public IndicatorsStudent findByObject(Integer studentId, String schoolYear, Integer schoolId) {
        //这个学生所有的体测成绩
        List<IndicatorsPojo> list=indicatorsStudentDao.findBystudents(studentId,schoolYear,schoolId);
        //健康信息
        IndicatorsStudent studentList =new IndicatorsStudent();
        IndicatorsStudent studentList2=indicatorsStudentDao.findByIndicaStudent(schoolId,studentId);
        //请假信息
        List<Integer>  qingjia=indicatorsStudentDao.findByQingJia(studentId,schoolId);
        if(studentList2!=null){
            studentList.setBaogaoId(studentList2.getBaogaoId());
            studentList.setStuName(studentList2.getStuName());
            studentList.setStudentId(studentList2.getStudentId());
            studentList.setHealthReport(studentList2.getHealthReport());
            studentList.setYiWu(studentList2.getYiWu());
            studentList.setCervixReport(studentList2.getCervixReport());
        }
        if(list.size()>0){
            studentList.setIndicatorsPojo(list);
        }
        if(qingjia.size()>0){
            int a=0;
            studentList.setJiaDay(qingjia.size());
            for(Integer aa:qingjia){
                a+=aa;
            }
            studentList.setTianshu(a/24);
        }
        return studentList;
    }
}
