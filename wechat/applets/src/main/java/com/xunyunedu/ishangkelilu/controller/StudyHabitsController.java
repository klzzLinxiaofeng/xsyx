package com.xunyunedu.ishangkelilu.controller;


import com.xunyunedu.ishangkelilu.pojo.StudyHabits;
import com.xunyunedu.ishangkelilu.service.StudyHabitsService;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.service.StudentService;
import com.xunyunedu.team.pojo.TeamPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/study/habits")
@Api(value = "学习习惯接口", description = "学习习惯接口")
public class StudyHabitsController {
    Logger logger = LoggerFactory.getLogger(StudyHabitsController.class);
    @Autowired

    private StudyHabitsService studyHabitsService;

    @Autowired
    private StudentService studentService;

    @GetMapping(value = "/team")
    @ApiOperation(value = "任课的班级", httpMethod = "GET")
    public List<TeamPojo> findByStudent(@RequestParam(value = "teacherId",required = false) Integer teacherId,
                                   @RequestParam(value = "schoolId",required = false) Integer schoolId,
                                   @RequestParam(value = "schoolYear",required = false) String schoolYear){
        List<TeamPojo> teamPojo=studyHabitsService.findByTeam(teacherId,schoolId,schoolYear);
        return teamPojo;
    }
        /**
         * @description: 名字查找
         * @return Student
        */
    @GetMapping("/student")
    @ApiOperation(value = "名字查找", httpMethod = "GET")
    public List<StudentPojo> findByStudentId(@RequestParam(value = "stuName",required = false) String stuName,
                                 @RequestParam(value = "teamId",required = false) Integer teamId){
        List<StudentPojo> student=studentService.getIdByUserId(stuName,teamId);
        return student;
    }

    @GetMapping("/student/studyXiGuan")
    @ApiOperation(value = "学生展示图", httpMethod = "GET")
    public Map<String,Object> findBystudyXiGuan(@RequestParam(value = "schoolId",required = false) Integer schoolId,
                                                @RequestParam(value = "studentId",required = false) Integer studentId,
                                                @RequestParam  String  schoolYear){
        Map map=studyHabitsService.findBystudentXiGuan(studentId,schoolId,schoolYear);
        return map;
    }
    @PostMapping("/student/studyXiGuan")
    @ApiOperation(value = "教师端评分", httpMethod = "POST")
    public Boolean create(@RequestBody  StudyHabits studentHabits){
       Boolean falg= studyHabitsService.create(studentHabits);
       if(falg){
           return true;
       }
        return false;
    }
}
