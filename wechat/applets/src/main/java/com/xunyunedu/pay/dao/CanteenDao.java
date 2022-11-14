package com.xunyunedu.pay.dao;

import com.xunyunedu.pay.pojo.CanteenCardPojo;

import java.util.List;

/**
 *
 *  @author: yhc
 *  @Date: 2020/12/3 21:48
 *  @Description:
 */
public interface CanteenDao {
    void createReplace(CanteenCardPojo canteenCardPojo);

    List<CanteenCardPojo> findHistoryByUserId(CanteenCardPojo canteenCardPojo);
}
