package com.xunyunedu.pay.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.exception.ApiResult;
import com.xunyunedu.exception.BusinessRuntimeException;
import com.xunyunedu.exception.ResultCode;
import com.xunyunedu.init.InitHandler;
import com.xunyunedu.interceptor.Authorization;
import com.xunyunedu.pay.pojo.CanteenCardPojo;
import com.xunyunedu.pay.service.CanteenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 食堂接口
 *
 * @author: yhc
 * @Date: 2020/11/5 20:24
 * @Description:
 */
@RestController
@Api("食堂用户信息")
public class CanteenController {

    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CanteenService canteenService;

    /**
     * 用户信息
     * /api/mobile/VipUser/list
     *
     * @param emp_code 请求参数
     * @return
     */
    @GetMapping("/api/mobile/VipUser/list")
    @ApiOperation("用户信息")
    public JSONObject getUserList(@RequestParam String emp_code) {
        if (StrUtil.isEmpty(emp_code)) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String userListUrl = InitHandler.urlParam.get("userListUrl");
        return canteenService.postData(userListUrl + "?emp_code=" + emp_code);
    }

    @GetMapping("/faSongZhifu")
    @ApiOperation(value = "发送支付",httpMethod ="GET")
    public void findByFaSong() throws InterruptedException {
        MyListener myListener=new MyListener();
        myListener.findByShouDong();
    }

    /**
     * 用户消费明细
     * /api/meal/MealReportMx/listAsync
     *
     * @param meal_queryWhere
     * @param meal_dateBegin
     * @param meal_dateEnd
     * @param PageIndex
     * @param PageSize
     * @return
     */
    @GetMapping("/api/meal/MealReportMx/listAsync")
    @Authorization
    public JSONObject getConsumerDetail(@RequestParam String meal_queryWhere, @RequestParam String meal_dateBegin, @RequestParam String meal_dateEnd,
                                        @RequestParam String PageIndex, @RequestParam String PageSize) {
        if (StrUtil.isEmpty(meal_queryWhere) || StrUtil.isEmpty(meal_dateBegin) || StrUtil.isEmpty(meal_dateEnd) || StrUtil.isEmpty(PageIndex) || StrUtil.isEmpty(PageSize)) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String asyncUrl = InitHandler.urlParam.get("asyncUrl");
        JSONObject jsonObject = canteenService.postData(asyncUrl + "?meal_queryWhere=" + meal_queryWhere + "&meal_dateBegin=" + meal_dateBegin + "&meal_dateEnd=" + meal_dateEnd
                + "&PageIndex=" + PageIndex + "&PageSize=" + PageSize);
        return jsonObject;
    }

    /**
     * 用户消费统计
     * /api/mobile/VipUser/UserMonthMealMoney
     *
     * @param emp_code
     * @param query_dateBegin
     * @param query_dateEnd
     * @return
     */
    @GetMapping("/api/mobile/VipUser/UserMonthMealMoney")
    @Authorization
    public JSONObject getConsumerSum(@RequestParam String emp_code, @RequestParam String query_dateBegin, @RequestParam String query_dateEnd) {
        if (StrUtil.isEmpty(emp_code) || StrUtil.isEmpty(query_dateBegin) || StrUtil.isEmpty(query_dateEnd)) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String consumerUrl = InitHandler.urlParam.get("consumerUrl");
        JSONObject jsonObject = canteenService.postData(consumerUrl + "?emp_code=" + emp_code + "&query_dateBegin=" + query_dateBegin + "&query_dateEnd=" + query_dateEnd);
        return jsonObject;
    }

    /**
     * 用户充值统计
     * /api/mobile/VipUser/UserMonthRechargeMoney
     *
     * @param emp_code
     * @param query_dateBegin
     * @param query_dateEnd
     * @return
     */
    @GetMapping("/api/mobile/VipUser/UserMonthRechargeMoney")
    @Authorization
    public JSONObject getPayCount(@RequestParam String emp_code, @RequestParam String query_dateBegin, @RequestParam String query_dateEnd) {
        if (StrUtil.isEmpty(emp_code) || StrUtil.isEmpty(query_dateBegin) || StrUtil.isEmpty(query_dateEnd)) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String payCountUrl = InitHandler.urlParam.get("payCountUrl");
        JSONObject jsonObject = canteenService.postData(payCountUrl + "?emp_code=" + emp_code + "&query_dateBegin=" + query_dateBegin + "&query_dateEnd=" + query_dateEnd);
        return jsonObject;
    }

    /**
     * 所有用户时间段内平均消费
     * /api/mobile/VipUser/UserMonthAvgMoney
     *
     * @param query_dateBegin
     * @param query_dateEnd
     * @return
     */
    @GetMapping("/api/mobile/VipUser/UserMonthAvgMoney")
    @Authorization
    public JSONObject getConsumerAvg(@RequestParam String query_dateBegin, @RequestParam String query_dateEnd) {
        if (StrUtil.isEmpty(query_dateBegin) || StrUtil.isEmpty(query_dateEnd)) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String consumerAvgUrl = InitHandler.urlParam.get("consumerAvgUrl");
        JSONObject jsonObject = canteenService.postData(consumerAvgUrl + "?query_dateBegin=" + query_dateBegin + "&query_dateEnd=" + query_dateEnd);
        return jsonObject;
    }

    /**
     * 用户日期段内的消费额
     * /api/mobile/VipUser/UserDaySumMoney
     *
     * @param emp_code
     * @param query_dateBegin
     * @param query_dateEnd
     * @return
     */
    @GetMapping("/api/mobile/VipUser/UserDaySumMoney")
    @Authorization
    public JSONObject getUserDayConsumerSum(@RequestParam String emp_code, @RequestParam String query_dateBegin, @RequestParam String query_dateEnd) {
        if (StrUtil.isEmpty(emp_code) || StrUtil.isEmpty(query_dateBegin) || StrUtil.isEmpty(query_dateEnd)) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String dayConsumerUrl = InitHandler.urlParam.get("dayConsumerUrl");
        JSONObject jsonObject = canteenService.postData(dayConsumerUrl + "?emp_code=" + emp_code + "&query_dateBegin=" + query_dateBegin + "&query_dateEnd=" + query_dateEnd);
        return jsonObject;
    }



    /**
     * 查询充值消费金额
     * /api/mobile/VipUser/SchoolAccount?emp_code=J065&query_dateBegin=2020-12-1&query_dateEnd=2020-12-30
     *
     * @param emp_code
     * @param query_dateBegin
     * @param query_dateEnd
     * @return
     */
    @GetMapping("/api/mobile/VipUser/SchoolAccount")
    @Authorization
    public JSONObject getConsumerRecharge(@RequestParam String emp_code, @RequestParam String query_dateBegin, @RequestParam String query_dateEnd) {
        if (StrUtil.isEmpty(emp_code) || StrUtil.isEmpty(query_dateBegin) || StrUtil.isEmpty(query_dateEnd)) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        String consumerRechargeUrl = InitHandler.urlParam.get("consumerRechargeUrl");
        JSONObject jsonObject = canteenService.postData(consumerRechargeUrl + "?emp_code=" + emp_code + "&query_dateBegin=" + query_dateBegin + "&query_dateEnd=" + query_dateEnd);
        return jsonObject;
    }




    /**
     * 补卡功能
     *
     * @return
     */
    @PostMapping("/canteen/replace/card")
    @Authorization
    public ApiResult chooseClass(@RequestBody CanteenCardPojo canteenCardPojo) {
        if (canteenCardPojo == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        canteenService.createReplace(canteenCardPojo);
        return new ApiResult(ResultCode.SUCCESS);
    }

    /**
     * 查询补卡申请
     *
     * @param canteenCardPojo
     * @return
     */
    @GetMapping("/canteen/replace/commit/history")
    @Authorization
    public ApiResult commitHistory(CanteenCardPojo canteenCardPojo) {
        if (canteenCardPojo == null) {
            throw new BusinessRuntimeException(ResultCode.PARAMS_IS_NULL);
        }
        List<CanteenCardPojo> cardPojoList = canteenService.findHistoryByUserId(canteenCardPojo);
        if (CollUtil.isNotEmpty(cardPojoList)) {
            return new ApiResult(ResultCode.SUCCESS, cardPojoList.get(0));
        }
        return new ApiResult(ResultCode.SUCCESS, null);
    }

}
