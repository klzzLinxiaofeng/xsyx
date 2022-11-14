package com.xunyunedu.aesthetic.controller;


import com.xunyunedu.aesthetic.pojo.AestheticPojo;
import com.xunyunedu.aesthetic.service.AestheticService;
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
@RequestMapping("aesthetic")
@Api(value = "Aesthetic", description = "艺术审美接口")
public class AestheticController {
    Logger logger = LoggerFactory.getLogger(AestheticController.class);

    @Autowired
    private AestheticService aestheticService;

    @RequestMapping(value = "/findByAesthetic",method= RequestMethod.GET)
    @ApiOperation(value = "艺术审美学生端展示", httpMethod = "GET")
    public AestheticPojo findByAlls(@RequestParam Integer studentId,
                                    @RequestParam Integer schoolId){
        return aestheticService.findByAlls(studentId,schoolId);
    }


    @RequestMapping(value = "/findByStudent",method= RequestMethod.GET)
    @ApiOperation(value = "获取所有学生修改pyCode,后续废除", httpMethod = "GET")
    public String findByAllStudent(@RequestParam(value = "studentId",required = false) Integer studentId,
                                    @RequestParam Integer schoolId,
                                   @RequestParam Integer type){
        return aestheticService.findByAllStudent(studentId,schoolId,type);
    }


}
