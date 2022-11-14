package com.xunyunedu.mentalhealth.controller;

import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.logger.service.LoggerService;
import com.xunyunedu.logger.vo.Loggers;
import com.xunyunedu.mentalhealth.service.MentalBehaviorService;
import com.xunyunedu.mentalhealth.vo.MentalBehavior;
import com.xunyunedu.mentalhealth.vo.MentalIndicators;
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
@RequestMapping("/mentalBehavior")
@Api(value = "心理健康接口", description = "心理健康接口")
public class MentalBehaviorController {
    Logger logger = LoggerFactory.getLogger(MentalBehaviorController.class);

    @Autowired
    private MentalBehaviorService mentalBehaviorService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;
    @GetMapping("/lfindByEvaver")
    @ApiOperation(value = "心理健康类型列表接口", httpMethod = "GET")
    public List<MentalIndicators> findByEvaver(){
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        List<MentalIndicators> laborIndicators=mentalBehaviorService.findByaLL(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
        return laborIndicators;
    }

    @GetMapping("/defenqingkuang")
    @ApiOperation(value = "心理健康学生分数", httpMethod = "GET")
    public Integer defenqingkuang(@RequestParam Integer studentId,
                                  @RequestParam Integer evelaId){
        List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
        Integer num=mentalBehaviorService.findByFenShu(studentId,evelaId,mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
        return num;
    }

    @PostMapping("/add")
    @ApiOperation(value = "心理健康添加", httpMethod = "POST")
    public Map createrecord(@RequestBody MentalBehavior mentalBehavior) {
        List<Map<String,Object>> termInfo= basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        mentalBehavior.setSchoolYear(termInfo.get(0).get("school_year").toString());
        mentalBehavior.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
        Integer falg = mentalBehaviorService.create(mentalBehavior);
        Map map = new HashMap();
        if (falg>0) {
            Loggers logger = new Loggers();
            logger.setCaozuoId(mentalBehavior.getTeacherId());
            logger.setName(mentalBehavior.getTeacherName());
            List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+mentalBehavior.getTeacherId());
            logger.setUsername(teacher.get(0).get("user_name").toString());
            logger.setMobile(teacher.get(0).get("mobile").toString());
            logger.setMokuaiName("心理健康");
            logger.setType(1);
            logger.setMessage("新增"+mentalBehavior.getStudentName()+"心理健康记录");
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
