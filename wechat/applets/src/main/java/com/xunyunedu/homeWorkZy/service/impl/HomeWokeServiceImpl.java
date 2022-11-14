package com.xunyunedu.homeWorkZy.service.impl;

import com.xunyunedu.PublishAndAcceptJob.pojo.StudentHomeWoke;
import com.xunyunedu.homeWorkZy.dao.HomeWorkMapping;
import com.xunyunedu.homeWorkZy.pojo.HomeWokePojo;
import com.xunyunedu.homeWorkZy.service.HomeWokeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class HomeWokeServiceImpl implements HomeWokeService {

    @Autowired
    private HomeWorkMapping homeWorkMapping;


    /*
    * 该教师所有作业
    */
    @Override
    public List<HomeWokePojo> findByAll(Integer schoolId, Integer teacherId) {
        return homeWorkMapping.findByAll(schoolId,teacherId);
    }


    /*
     * 某作业详情
     */
    @Override
    public List<StudentHomeWoke> findByStudentHomeWokeAll(Integer id,String studentName) {
        return homeWorkMapping.findByStudentHomeWokeAll(id,studentName);
    }

    @Override
    public Integer updateStudentHomeWokePing(StudentHomeWoke studentHomeWoke) {
        String dengji=findBydengji(studentHomeWoke);
        studentHomeWoke.setDengji(dengji);
        System.out.println(studentHomeWoke.getFenzhi());
       Integer num= homeWorkMapping.updateStudentHomeWokePing(studentHomeWoke);
        return num;
    }

    @Override
    public List<HomeWokePojo> findByAllStudentId(Integer schoolId, Integer studentId,Integer subjectId) {
        return homeWorkMapping.findByAllStudentId(schoolId,studentId,subjectId);
    }

    @Override
    public StudentHomeWoke findById(Integer id, Integer studentId, Integer schoolId) {
        return homeWorkMapping.findById(id,schoolId,studentId);
    }

    /*
     * 根据评分判断等级
     */
    public String findBydengji(StudentHomeWoke studentHomeWoke) {
        //评价等级   a+ 100,a90--99.5 ,b+85-89.5 b80-84.5  c70-79.5 d60-69.5 E<60
        Float fenzhi=studentHomeWoke.getFenzhi();
        if(fenzhi==100){
            return "A+";
        }if(fenzhi<100&&fenzhi>=90){
            return "A";
        }if(fenzhi<90&&fenzhi>=85){
            return "B+";
        }if(fenzhi<85&&fenzhi>=80){
            return "B";
        }if(fenzhi<80&&fenzhi>=70){
            return "C";
        }if(fenzhi<70&&fenzhi>=60){
            return "D";
        }if(fenzhi<60){
            return "E";
        }
        return null;
    }
}
