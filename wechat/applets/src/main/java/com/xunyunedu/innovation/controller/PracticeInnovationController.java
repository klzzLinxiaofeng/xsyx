package com.xunyunedu.innovation.controller;


import com.xunyunedu.innovation.pojo.PracticeInnovation;
import com.xunyunedu.innovation.service.PracticeInnovationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/practice/innovation")
@RestController
@Api(value = "/practice/innovation", description = "实践接口")
public class PracticeInnovationController {
    Logger logger = LoggerFactory.getLogger(PracticeInnovationController.class);
    @Autowired
    private PracticeInnovationService practiceInnovationService;

    @RequestMapping(value = "/studentAlls",method = RequestMethod.GET)
    @ApiOperation(value = "当前学生实践记录", httpMethod = "Get")
    public PracticeInnovation findByPracticeInnovation(@RequestParam Integer studentId,
                                              @RequestParam Integer schoolId){
        PracticeInnovation practiceInnovation=practiceInnovationService.findByStudentIdAll(studentId,schoolId);
        // 获取用户信息成功，将信息返回前端
        return  practiceInnovation;
    };
}
