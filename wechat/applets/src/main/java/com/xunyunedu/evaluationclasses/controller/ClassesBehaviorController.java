package com.xunyunedu.evaluationclasses.controller;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.evaluationclasses.service.ClassesBehaviorService;
import com.xunyunedu.evaluationclasses.vo.ClassesBehavior;
import com.xunyunedu.evaluationclasses.vo.ClassesIndicators;
import com.xunyunedu.logger.service.LoggerService;
import com.xunyunedu.logger.vo.Loggers;
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
@RequestMapping("/classesBehavior")
@Api(value = "课间行为接口", description = "课间行为接口")
public class ClassesBehaviorController {
    Logger logger = LoggerFactory.getLogger(ClassesBehaviorController.class);
    @Autowired
    private ClassesBehaviorService classesBehaviorService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;
    @GetMapping("/lfindByEvaver")
    @ApiOperation(value = "课间行为类型列表接口", httpMethod = "GET")
    public List<ClassesIndicators> findByEvaver(){
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        List<ClassesIndicators> laborIndicators=classesBehaviorService.findByaLL(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
        return laborIndicators;
    }

    @GetMapping("/defenqingkuang")
    @ApiOperation(value = "课间行为学生分数", httpMethod = "GET")
    public Integer defenqingkuang(@RequestParam Integer studentId,
                                  @RequestParam Integer evelaId){
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        Integer num=classesBehaviorService.findByFenShu(studentId,evelaId,mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
        return num;
    }

    @PostMapping("/add")
    @ApiOperation(value = "课间行为添加", httpMethod = "POST")
    public Map createrecord(@RequestBody ClassesBehavior classesBehavior) {
        List<Map<String,Object>> termInfo= basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        classesBehavior.setSchoolYear(termInfo.get(0).get("school_year").toString());
        classesBehavior.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
        Integer falg = classesBehaviorService.create(classesBehavior);
        Map map = new HashMap();
        if (falg>0) {
            Loggers logger = new Loggers();
            List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+classesBehavior.getTeacherId());
            logger.setUsername(teacher.get(0).get("user_name").toString());
            logger.setMobile(teacher.get(0).get("mobile").toString());
            logger.setCaozuoId(classesBehavior.getTeacherId());
            logger.setName(classesBehavior.getTeacherName());
            logger.setMessage("教师新增"+classesBehavior.getStudentName()+"课间行为记录");
            logger.setMokuaiName("课间行为");
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
}
