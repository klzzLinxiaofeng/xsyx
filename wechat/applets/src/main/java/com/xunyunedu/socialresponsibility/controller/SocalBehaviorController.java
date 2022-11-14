package com.xunyunedu.socialresponsibility.controller;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.logger.service.LoggerService;
import com.xunyunedu.logger.vo.Loggers;
import com.xunyunedu.socialresponsibility.service.SocialBehaviorService;
import com.xunyunedu.socialresponsibility.vo.SocialBehavior;
import com.xunyunedu.socialresponsibility.vo.SocialIndicators;
import com.xunyunedu.student.pojo.StudentPojo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author zhangyong
 * @Date 2022/11/4 16:21
 * @Version 1.0
 */
@RestController
@RequestMapping("/socalBehavior")
@Api(value = "社会责任接口", description = "社会责任接口")
public class SocalBehaviorController {
    Logger logger = LoggerFactory.getLogger(SocalBehaviorController.class);
    @Autowired
    private SocialBehaviorService laborBehaviorService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;
    @GetMapping("/lfindByEvaver")
    @ApiOperation(value = "社会责任类型列表接口", httpMethod = "GET")
    public List<SocialIndicators> findByEvaver(){
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        List<SocialIndicators> laborIndicators=laborBehaviorService.findByaLL(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
        return laborIndicators;
    }

    @GetMapping("/defenqingkuang")
    @ApiOperation(value = "社会责任学生分数", httpMethod = "GET")
    public Integer defenqingkuang(@RequestParam Integer studentId,
                                  @RequestParam Integer evelaId){
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        Integer num=laborBehaviorService.findByFenShu(studentId,evelaId,mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
        return num;
    }

    @PostMapping("/add")
    @ApiOperation(value = "社会责任添加", httpMethod = "POST")
    public Map createrecord(@RequestBody SocialBehavior socialBehavior) {
        List<Map<String,Object>> termInfo= basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        socialBehavior.setSchoolYear(termInfo.get(0).get("school_year").toString());
        socialBehavior.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
        Integer falg = laborBehaviorService.create(socialBehavior);
        Map map = new HashMap();
        if (falg>0) {
            Loggers logger = new Loggers();
            if(socialBehavior.getTeacherId()!=null){
                List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+socialBehavior.getTeacherId());
                logger.setUsername(teacher.get(0).get("user_name").toString());
                logger.setMobile(teacher.get(0).get("mobile").toString());
                logger.setCaozuoId(socialBehavior.getTeacherId());
                logger.setName(socialBehavior.getTeacherName());
                logger.setMessage("教师新增"+socialBehavior.getStudentName()+"社会责任记录");
            }else{
                List<Map<String,Object>> teacher=basicSQLService.find("select  pp.name,yu.user_name from pj_parent_student  pps " +
                        " inner join pj_student ps on ps.user_id=pps.student_user_id " +
                        " inner join pj_parent pp on pp.user_id=pps.parent_user_id " +
                        " inner join yh_user yu on yu.id=pps.parent_user_id " +
                        " where ps.id="+socialBehavior.getStudentId());
                logger.setUsername(teacher.get(0).get("user_name").toString());
                //家长姓名
                logger.setName(teacher.get(0).get("name").toString());
                logger.setMobile(null);
                logger.setMessage("家长新增"+socialBehavior.getStudentName()+"社会责任记录");
                logger.setCaozuoId(null);
            }

            logger.setMokuaiName("社会责任");
            logger.setType(1);

            logger.setSchoolYear(termInfo.get(0).get("school_year").toString());
            logger.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
            loggerService.create(logger);
            map.put("code", 200);
            map.put("susses", "评价成功");
            return map;
        }
        map.put("code", 400);
        map.put("susses", "评价失败");
        return map;
    }

    /*
    * 家长端搜索学生
    * 只能查询到自己孩子所在班级的其他同学
    */
    @GetMapping("/findByStudentId")
    @ApiOperation(value = "家长端学生搜索添加", httpMethod ="GET")
    public List<StudentPojo> findByStudentId(@RequestParam String studentName,
                                             @RequestParam Integer teamId,
                                             @RequestParam Integer studentId){
         return laborBehaviorService.findById(studentName, teamId,studentId);
    }
}
