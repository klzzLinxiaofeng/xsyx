package com.xunyunedu.pay.service;

import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.pay.pojo.CanteenCardPojo;

import java.util.List;

/**
 * 食堂接口对接
 *
 * @author: yhc
 * @Date: 2020/11/6 9:29
 * @Description:
 */
public interface CanteenService {
    JSONObject postData(String url);

    void createReplace(CanteenCardPojo canteenCardPojo);

    List<CanteenCardPojo> findHistoryByUserId(CanteenCardPojo canteenCardPojo);
}
