package com.xunyunedu.schoolbus.service;


import com.xunyunedu.schoolbus.pojo.BusInfo;

import java.util.List;

/**
 * 校车系统相关api
 */
public interface BusApiService {

    /**
     * 获取所有校车信息
     * @return
     */
    List<BusInfo> getAllBus();

    /**
     * 根据卡号查询校车信息
     * @param carNo
     * @return
     */
    BusInfo getByCarNo(String carNo);

}
