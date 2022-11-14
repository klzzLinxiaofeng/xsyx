package com.xunyunedu.pay.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.xunyunedu.pay.dao.CanteenDao;
import com.xunyunedu.pay.pojo.CanteenCardPojo;
import com.xunyunedu.pay.service.CanteenService;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import com.xunyunedu.util.httpclient.core.HttpRequestConfig;
import com.xunyunedu.util.httpclient.core.HttpRequestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yhc
 * @Date: 2020/11/6 9:30
 * @Description:
 */
@Service
public class CanteenServiceImpl implements CanteenService {
    final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CanteenDao canteenDao;

    @Override
    public JSONObject postData(String url) {
        try {
            HttpRequestConfig config = HttpRequestConfig.create().url(url)
                    .addHeader("Content-Type", "application/json");
            HttpRequestResult httpRequestResult = HttpClientUtils.get(config);
            String responseText = httpRequestResult.getResponseText();
            if (!StrUtil.hasEmpty(responseText)) {
                JSONObject jsonObject = JSONObject.parseObject(responseText);
                return jsonObject;
            }
        } catch (Exception e) {
            log.error("请求食堂接口失败,url: {}", url);
            return null;
        }
        return null;
    }


    /**
     * 新增补卡
     * @param canteenCardPojo
     */
    @Override
    public void createReplace(CanteenCardPojo canteenCardPojo) {
        canteenDao.createReplace(canteenCardPojo);
    }

    /**
     * 获取发卡记录
     * @param canteenCardPojo
     * @return
     */
    @Override
    public List<CanteenCardPojo> findHistoryByUserId(CanteenCardPojo canteenCardPojo) {
        // 只获取当前用户没有发新卡的记录
        canteenCardPojo.setIsSend(0);
        canteenCardPojo.setIsDeleted(0);
        return canteenDao.findHistoryByUserId(canteenCardPojo);
    }
}
