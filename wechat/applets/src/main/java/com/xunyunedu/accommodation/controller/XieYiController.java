package com.xunyunedu.accommodation.controller;


import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/XieYi")
@Api(value = "/XieYi", description = "协议")
public class XieYiController {
    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping("/findByXieYi")
    @ApiOperation(value = "/findByXieYi", httpMethod = "GET")
    public ApiResult findByAll() {
        List<Map<String,Object>> map= basicSQLService.find("select * from zy_xieyi where id=1");
        if(Integer.parseInt(map.get(0).get("is_show").toString())==1){
            return new ApiResult(ResultCode.SUCCESS,false,map);
        }else{
            return new ApiResult(ResultCode.SUCCESS,true,map);
        }

    }

}
