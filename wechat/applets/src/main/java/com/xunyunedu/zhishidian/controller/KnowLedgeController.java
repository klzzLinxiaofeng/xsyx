package com.xunyunedu.zhishidian.controller;


import com.xunyunedu.zhishidian.service.KnowLedgeService;
import com.xunyunedu.zhishidian.vo.KnowEvaluation;
import com.xunyunedu.zhishidian.vo.KnowLedge;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/KnowLedge")
@Api(value = "KnowLedge", description = "知识点接口")
public class KnowLedgeController {
    @Autowired
    private KnowLedgeService knowLedgeService;
    @RequestMapping("/findByAll")
    @ApiOperation(value = "课本列表", httpMethod = "GET")
    public List<KnowLedge> findByAll(@RequestParam Integer gradeId,
                                     @RequestParam Integer subjectId){
        return knowLedgeService.findByAll(gradeId,subjectId);
    }

    @RequestMapping("/findByPinjai")
    @ApiOperation(value = "知识点列表", httpMethod = "GET")
    public List<KnowEvaluation> findByPinjai(@RequestParam Integer studentId,
                                             @RequestParam Integer knowId){
        return knowLedgeService.findByPinjai(knowId,studentId);
    }
}
