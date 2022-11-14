package com.xunyunedu.act.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.act.pojo.InSchoolActivity;
import com.xunyunedu.act.service.InSchoolActivityService;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BaseConstant;
import com.xunyunedu.exception.PageCondition;
import com.xunyunedu.exception.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 校内活动
 */
@RestController
@RequestMapping("/act/schoolIn")
public class InSchoolActivityController {

    @Autowired
    private InSchoolActivityService service;

    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 创建校内活动
     * @param act 活动信息
     * @return
     */
    @PostMapping("/add")
    public ApiResult add(@RequestBody InSchoolActivity act){
        act.setCreateDate(new Date());
        act.setState(0);
        service.create(act);
        return ApiResult.of(ResultCode.SUCCESS);
    }

    /**
     * 分页查询校内活动
     * @param condition 查询参数
     * @return
     */
    @PostMapping("/page")
    public PageInfo pageSelect(@RequestBody PageCondition<InSchoolActivity> condition){
        return service.selectList(condition);
    }

    /**
     * 查询校内活动详情
     * @param id 校内活动id
     * @return 活动信息
     */
    @GetMapping("/detail")
    public ApiResult detail(Integer id){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(service.selectById(id));
        return apiResult;
    }

    /**
     * 获取活动室列表
     * @return
     */
    @GetMapping("/roomList")
    public ApiResult roomList(){
        ApiResult apiResult=ApiResult.of(ResultCode.SUCCESS);
        apiResult.setData(basicSQLService.find("select id,name from  at_in_school_room where is_deleted=0"));
        return apiResult;
    }


}
