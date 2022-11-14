package com.xunyunedu.dateUtils.controller;

import com.xunyunedu.dateUtils.service.DateService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 仿日历工具接口
 */
@RestController
@RequestMapping("/dateTool")
public class DateUtilsController {

    @Resource
    DateService service;

    /**
     * 查询指定日期的天数列表
     * @return
     */
    @GetMapping("/getDate")
    public ApiResult getDate(String date){
        List<Integer> list = service.getDateList(date);
        return new ApiResult(ResultCode.SUCCESS, list);
    }

}
