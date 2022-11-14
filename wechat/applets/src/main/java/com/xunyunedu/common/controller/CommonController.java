package com.xunyunedu.common.controller;

import com.xunyunedu.common.service.CommonService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 公共工具接口
 *
 *
 * @author: yhc
 * @Date: 2021/1/21 15:48
 * @Description:
 */
@RequestMapping("/common/tools")
@RestController
public class CommonController {

    Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CommonService commonService;

    /**
     * 获取下拉框信息
     * 查询 jc_gc_item表
     *
     * @param code
     * @return
     */
    @GetMapping("/jcItem")
    @ResponseBody
    public ApiResult jcItem(@RequestParam String code) {
        if (code == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<Map<String, Integer>> result = commonService.getValueByCode(code);
        return new ApiResult(ResultCode.SUCCESS, result);
    }
}
