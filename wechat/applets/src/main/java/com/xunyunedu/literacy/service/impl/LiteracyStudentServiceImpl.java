package com.xunyunedu.literacy.service.impl;

import com.xunyunedu.PublishAndAcceptJob.pojo.SubjectPojo;
import com.xunyunedu.literacy.dao.LiteracyStudentDao;
import com.xunyunedu.literacy.pojo.LiteracyStudent;
import com.xunyunedu.literacy.service.LiteracyStudentService;
import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.mergin.vo.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LiteracyStudentServiceImpl implements LiteracyStudentService {

    @Autowired
    private LiteracyStudentDao listuDao;
    @Override
    public List<SubjectPojo> findByStuId(Integer schoolId, Integer studentId,String schoolYear) {
        return listuDao.findByStuId(schoolId,studentId,schoolYear);
    }

    @Override
    public Map<String,Object> findByStudentId(Integer studentId, Integer subjectId,String schoolTrem, String schoolYear) {
        //该同学的学科素养评分
        List<LiteracyStudent> listudent=listuDao.findByStudentScore(subjectId,studentId,schoolYear,schoolTrem);
        //该同学的班级的平均素养评分
        List<LiteracyStudent> teamList=listuDao.findByTeamScore(subjectId,studentId,schoolYear,schoolTrem);
        //该同学的年级的平均学科素养评分
        List<LiteracyStudent> gradeList=listuDao.findByGradeScore(subjectId,studentId,schoolYear,schoolTrem);

        List<Map<String, Object>> lists=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        map.put("studentList",listudent);
        map.put("teamList",teamList);
        map.put("gradeList",gradeList);
        return map;
    }

    @Override
    public List<Team> findByTeacher(Integer schoolId, Integer teacherId,String schoolYear) {
        return listuDao.findByTeacher(schoolId, teacherId,schoolYear);
    }

    @Override
    public List<SubjectPojo> fidByTeacherId(Integer schoolId, Integer teamId, Integer teacherId) {
        return listuDao.fidByTeacherId(schoolId, teamId,teacherId );
    }

    @Override
    public List<Student> getByStudent(Integer schoolId, Integer teamId,String studentName) {
        return listuDao.findByStudent(schoolId, teamId,studentName);
    }
}
