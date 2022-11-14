package com.xunyunedu.activitieshome.controller;


import com.xunyunedu.activitieshome.service.HomeBehaviorService;
import com.xunyunedu.activitieshome.vo.HomeBehavior;
import com.xunyunedu.activitieshome.vo.HomeIndicators;
import com.xunyunedu.core.service.BasicSQLService;
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
@RequestMapping("/homeJuJia")
@Api(value = "居家活动接口", description = "居家活动接口")
public class homeJuJiaController {
        Logger logger = LoggerFactory.getLogger(homeJuJiaController.class);

        @Autowired
        private HomeBehaviorService homeBehaviorService;
        @Autowired
        private BasicSQLService basicSQLService;
        @Autowired
        private LoggerService loggerService;
        @GetMapping("/lifeIndicators")
        @ApiOperation(value = "居家活动类型列表", httpMethod = "GET")
        public List<HomeIndicators> findByEvaver(){
            List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
            List<HomeIndicators> homeIndicatorsList=homeBehaviorService.findByaLL(mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
            return homeIndicatorsList;
        }

        @GetMapping("/defenqingkuang")
        @ApiOperation(value = "居家活动学生分数", httpMethod = "GET")
        public Integer defenqingkuang(@RequestParam Integer studentId,
                                      @RequestParam Integer evelaId){
            List<Map<String,Object>> mapList=basicSQLService.find("SELECT * FROM pj_school_term_current WHERE school_id = 215 ORDER BY id DESC");
            Integer num=homeBehaviorService.findByFenShu(studentId,evelaId,mapList.get(0).get("school_year").toString(),mapList.get(0).get("school_term_code").toString());
            return num;
        }

        @PostMapping("/add")
        @ApiOperation(value = "添加", httpMethod = "POST")
        public Map createrecord(@RequestBody HomeBehavior homeBehavior) {
            List<Map<String,Object>> termInfo=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
            homeBehavior.setSchoolTrem(termInfo.get(0).get("school_term_code").toString());
            homeBehavior.setSchoolYear(termInfo.get(0).get("school_year").toString());
            Integer falg = homeBehaviorService.create(homeBehavior);
            Map map = new HashMap();
            if (falg>0) {
                Loggers logger = new Loggers();
                logger.setCaozuoId(null);
                //家长端无家长姓名
                List<Map<String,Object>> teacher=basicSQLService.find("select  pp.name,yu.user_name from pj_parent_student  pps inner join pj_student ps on ps.user_id=pps.student_user_id inner join pj_parent pp on pp.user_id=pps.parent_user_id inner join yh_user yu on yu.id=pps.parent_user_id where ps.id="+homeBehavior.getStudentId());
                logger.setName(teacher.get(0).get("name").toString());
                logger.setUsername(teacher.get(0).get("user_name").toString());
                logger.setMobile(null);
                logger.setMokuaiName("居家活动");
                logger.setType(1);
                logger.setMessage("新增"+homeBehavior.getStudentName()+"居家活动记录");
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
