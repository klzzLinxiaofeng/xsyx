package com.xunyunedu.literacy.controller;

import com.xunyunedu.PublishAndAcceptJob.pojo.SubjectPojo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.literacy.service.LiteracyStudentService;
import com.xunyunedu.mergin.vo.Student;
import com.xunyunedu.mergin.vo.Team;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/literacy/subject")
@Api(value = "/literacy/subject", description = "学科素养接口")
public class LiteracyController {
    Logger logger = LoggerFactory.getLogger(LiteracyController.class);
    @Autowired
    private LiteracyStudentService listuService;
    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping(value = "/subjectObj",method =RequestMethod.GET)
    @ApiOperation(value = "学科素养接口科目")
    public  List<SubjectPojo> getBySubject(@RequestParam Integer schoolId,
                                  @RequestParam Integer studentId,@RequestParam String schoolYear){
        List<SubjectPojo> subjectPojo=listuService.findByStuId(schoolId, studentId,schoolYear);
        return subjectPojo;
    }

    @RequestMapping(value = "/subStudentSuyang",method =RequestMethod.GET)
    @ApiOperation(value = "学科素养接口展示图")
    public  Map<String,Object> getByStudentSuyang(
                                  @RequestParam Integer studentId,
                                  @RequestParam Integer subjectId,
                                  @RequestParam String schoolTrem,
                                  @RequestParam(value = "schoolYear",required = false) String schoolYear){
        Map<String,Object> map =listuService.findByStudentId(studentId, subjectId,schoolTrem,schoolYear);
        return map;
    }
    @RequestMapping(value = "/teamObj",method =RequestMethod.GET)
    @ApiOperation(value = "学科素养接口老师任课班级")
    public List<Team> getByTeacherTeam(
            @RequestParam Integer teacherId,@RequestParam Integer schoolId,@RequestParam String schoolYear){
        List<Map<String,Object>> ListMap=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id where yur.user_id=(select user_id from pj_teacher  where id="+teacherId+")");
            for(Map<String,Object> aa:ListMap){
                if(aa.get("code").toString().equals("SCHOOL_MASTER")
                        || aa.get("code").toString().equals("SCHOOL_MASTER")
                        ||  aa.get("code").toString().equals("SCHOOL_MANAGER")
                        || aa.get("code").toString().equals("NIANJIZU")
                        || aa.get("code").toString().equals("XING_ZHENG_REN_YUAN")){
                    List<Team> team =listuService.findByTeacher(schoolId, null,schoolYear);
                    return team;
                }
            }
        List<Team> team =listuService.findByTeacher(schoolId, teacherId,schoolYear);
        return team;
    }
    @RequestMapping(value = "/subTeachersubject",method =RequestMethod.GET)
    @ApiOperation(value = "学科素养接口老师任课的科目")
    public List<SubjectPojo> getByTeachersubject( @RequestParam Integer teamId,
                                          @RequestParam Integer teacherId,
                                          @RequestParam Integer schoolId){
        List<Map<String,Object>> ListMap=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id where yur.user_id=(select user_id from pj_teacher  where id="+teacherId+")");

        for(Map<String,Object> aa:ListMap){
            if(aa.get("code").toString().equals("SCHOOL_MASTER")){
                List<SubjectPojo> subjectPojoList =listuService.fidByTeacherId(schoolId,teamId, null);
                return subjectPojoList;
            }
        }
        List<SubjectPojo> subjectPojoList =listuService.fidByTeacherId(schoolId,teamId, teacherId);
        return subjectPojoList;
    }

    @RequestMapping(value = "/subjectStudent",method =RequestMethod.GET)
    @ApiOperation(value = "对应班级的学生")
    public List<Student> getByStudent(@RequestParam(value = "studentName", required = false) String studentName,
                                  @RequestParam Integer teamId,
                                  @RequestParam Integer schoolId){
        List<Student> studentList =listuService.getByStudent(schoolId,teamId,studentName);
        return studentList;
    }
    @RequestMapping(value = "/getBySchoolTRem",method =RequestMethod.GET)
    @ApiOperation(value = "学期")
    public List<Map<String,Object>> getBySchoolTRem(@RequestParam String schoolId,
                                                    @RequestParam String schoolYear){
        List<Map<String,Object>> list=basicSQLService.find("select code,name from  pj_school_term WHERE 1=1 AND is_delete = 0 AND school_year ="+schoolYear+" AND school_id ="+schoolId);
        return list;
    }


}
