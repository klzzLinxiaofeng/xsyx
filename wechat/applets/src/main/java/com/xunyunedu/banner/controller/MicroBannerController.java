package com.xunyunedu.banner.controller;

import com.xunyunedu.banner.pojo.MicroBanner;
import com.xunyunedu.banner.service.MicroBannerService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.student.service.MicroManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * banner
 * @author edison
 */
@RestController
@RequestMapping("/banner")
public class MicroBannerController {


    @Autowired
    MicroBannerService service;

    /**
     * 获取所有banner图
     */
    @GetMapping
    @RequestMapping("/getBannerList")
    public ApiResult<List<MicroBanner>> all(){
        ApiResult<List<MicroBanner>> apiResult = new ApiResult(ResultCode.SUCCESS);
        apiResult.setData(service.all());
        return apiResult;
    }

    /**
     * 获取banner图片对应的具体内容
     */
    @GetMapping
    @RequestMapping("/getContent")
    public ApiResult getContent(Integer id){
        if (id == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        Map<String, String> map = service.getContent(id);
        return new ApiResult(ResultCode.SUCCESS, map);
    }

}
