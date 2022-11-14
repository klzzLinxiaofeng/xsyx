package com.xunyunedu.canteen.controller;

import com.github.pagehelper.PageInfo;
import com.xunyunedu.canteen.model.CanteenPublicityPojo;
import com.xunyunedu.canteen.model.CanteenRecipesPojo;
import com.xunyunedu.canteen.service.CanteenRecipesService;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * e小食堂
 *
 * @author: yhc
 * @Date: 2020/12/31 15:26
 * @Description:
 */
@RequestMapping("/canteen")
@RestController
public class CanteenRecipesController {
    Logger logger = LoggerFactory.getLogger(CanteenRecipesController.class);
    @Autowired
    private CanteenRecipesService canteenRecipesService;

    /**
     * 获取食谱列表
     *
     * @param example
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getMenuList")
    public ApiResult getQuestion(CanteenRecipesPojo example, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                 @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (example == null || example.getSchoolId() == null) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PageInfo<CanteenRecipesPojo> list = canteenRecipesService.selectByExample(example, pageNum, pageSize);
        return new ApiResult(ResultCode.SUCCESS, list);
    }


    /**
     * 食堂实景&食堂风采&采购源
     *
     * @param example
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/getCanteenList")
    public ApiResult getCanteenList(CanteenPublicityPojo example, @RequestParam(value = "pageNum", defaultValue = "0", required = false) Integer pageNum,
                                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        if (example == null || example.getType() == null) {
            logger.error("参数为空!");
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        PageInfo<CanteenPublicityPojo> list = canteenRecipesService.getCanteenPublicity(example, pageNum, pageSize);
        return new ApiResult(ResultCode.SUCCESS, list);
    }


}
