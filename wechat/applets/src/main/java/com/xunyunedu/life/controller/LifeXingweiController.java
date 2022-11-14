package com.xunyunedu.life.controller;


import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.life.service.LifeXingweiService;
import com.xunyunedu.life.vo.LifeBehavior;
import com.xunyunedu.life.vo.LifeIndicators;
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
 * @Date 2022/11/4 9:30
 * @Version 1.0
 */
@RestController
@RequestMapping("/life")
@Api(value = "生活习惯接口", description = "生活习惯接口")
public class LifeXingweiController {
        Logger logger = LoggerFactory.getLogger(LifeXingweiController.class);

        @Autowired
        private LifeXingweiService lifeXingweiService;
        @Autowired
        private BasicSQLService basicSQLService;
        @Autowired
        private LoggerService loggerService;
        @GetMapping("/lifeIndicators")
        @ApiOperation(value = "生活习惯类型列表", httpMethod = "GET")
        public List<LifeIndicators> findByEvaver(){
            List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
            List<LifeIndicators> lifeIndicators=lifeXingweiService.findByaLL(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
            return lifeIndicators;
        }

        @GetMapping("/defenqingkuang")
        @ApiOperation(value = "生活习惯学生分数", httpMethod = "GET")
        public Integer defenqingkuang(@RequestParam Integer studentId,
                                      @RequestParam Integer evelaId){
            List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
            Integer num=lifeXingweiService.findByFenShu(studentId,evelaId,mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
            return num;
        }

        @PostMapping("/add")
        @ApiOperation(value = "添加", httpMethod = "POST")
        public Map createrecord(@RequestBody LifeBehavior records) {
            List<Map<String,Object>> termInfo=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
            records.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
            records.setSchoolYear(termInfo.get(0).get("school_year").toString());
            Integer falg = lifeXingweiService.create(records);
            Map map = new HashMap();
            if (falg>0) {
                Loggers logger = new Loggers();
                logger.setCaozuoId(records.getTeacherId());
                logger.setName(records.getTeacherName());
                List<Map<String,Object>> teacher=basicSQLService.find("select yu.user_name,pt.mobile from pj_teacher pt  inner join yh_user yu on yu.id=pt.user_id where pt.is_delete=0 and pt.id="+records.getTeacherId());
                logger.setUsername(teacher.get(0).get("user_name").toString());
                logger.setMobile(teacher.get(0).get("mobile").toString());
                logger.setMokuaiName("生活习惯");
                logger.setType(1);
                logger.setMessage("新增"+records.getStudentName()+"生活习惯记录");
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
