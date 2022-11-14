package com.xunyunedu.student.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.student.pojo.StudentPojo;
import com.xunyunedu.student.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 学生管理
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    Logger logger = LoggerFactory.getLogger(StudentAskingController.class);

    @Resource
    private StudentService service;

    @Autowired
    private BasicSQLService basicSQLService;


    /**
     * 查询班主任下的所有学生信息
     *
     * @param studentPojo 传入参数有 schoolId && teacherId
     * @return
     */
    @PostMapping("/queryStudent")
    @Authorization
    public ApiResult getStuMessage(@RequestBody StudentPojo studentPojo) {
        List<StudentPojo> students = service.getStudentByTeacherId(studentPojo);
        return new ApiResult(ResultCode.SUCCESS, students);
    }


    /**
     * 查询当前学年的学生信息
     * @return
     */
    @PostMapping("/queryNowSchoolYearStudent")
    public ApiResult queryNowSchoolYearStudent(@RequestBody PageCondition<StudentPojo> condition) {
        String nowSchoolYear=basicSQLService.getNowSchoolYear();
        StudentPojo studentPojo=condition.getCondition();
        PageInfo pageInfo =basicSQLService.findByPaging("select s.id,s.`name`,s.team_name from pj_student s inner JOIN pj_team_student pts ON s.id = pts.student_id and pts.is_delete=0 inner join pj_team t on pts.team_id=t.id and t.is_delete=0 where s.`name` like '%"+studentPojo.getName()+"%' and s.is_delete=0 and t.school_year='"+nowSchoolYear+"'",condition.getPageNum(),condition.getPageSize());
        return new ApiResult(ResultCode.SUCCESS, pageInfo);
    }


    /**
     * 获取学生信息修改状态
     *
     * @return
     */
    @GetMapping("/getAppletsInterrupteur")
    @Authorization
    public ApiResult getAppletsInterrupteur() {
        Map<String, Boolean> map = service.getAppletsInterrupteur();
        return new ApiResult(ResultCode.SUCCESS, map);
    }

}
