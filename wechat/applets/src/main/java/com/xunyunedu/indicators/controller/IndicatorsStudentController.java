package com.xunyunedu.indicators.controller;

import com.xunyunedu.indicators.pojo.IndicatorsStudent;
import com.xunyunedu.indicators.service.IndicatorsStudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/indicators")
@Api(value = "/practice/innovation", description = "体测报告")
public class IndicatorsStudentController {
    Logger logger = LoggerFactory.getLogger(IndicatorsStudentController.class);

    @Autowired
    private IndicatorsStudentService indicatorsStudentService;
    @RequestMapping(value = "/findByObject",method = RequestMethod.GET)
    @ApiOperation(value = "学生端体制健康", httpMethod= "Get")
    public IndicatorsStudent findByObject(@RequestParam Integer studentId,
                                          @RequestParam String schoolYear,
                                          @RequestParam Integer schoolId){
        return indicatorsStudentService.findByObject(studentId,schoolYear,schoolId);
    }
}
