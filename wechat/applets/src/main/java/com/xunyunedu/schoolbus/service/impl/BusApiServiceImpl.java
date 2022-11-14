package com.xunyunedu.schoolbus.service.impl;

import com.alibaba.fastjson.JSON;
import com.xunyunedu.schoolbus.pojo.BusInfo;
import com.xunyunedu.schoolbus.service.BusApiService;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BusApiServiceImpl implements BusApiService {

    private volatile List<BusInfo> cachedList=null;
    private volatile String cacheDateStr=null;

    @Override
    public List<BusInfo> getAllBus() {
        String nowDateStr=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //每天重新获取一次校车列表
        if(cachedList==null || !nowDateStr.equals(cacheDateStr)){
            synchronized (this){
                if(cachedList==null || !nowDateStr.equals(cacheDateStr)) {
                    String responseString = HttpClientUtils.get("http://otsc.chuhangkj.com/api/Student/getSchoolBusList?SID=2309");
                    cachedList= JSON.parseArray(responseString, BusInfo.class);
                    if(cachedList.size()>0) {
                        for (BusInfo busInfo : cachedList) {
                            busInfo.setType(0);
                        }
                    }
                    String responseString2 = HttpClientUtils.get("https://sync.bzywlw.cn/getSchoolBusList");
                    List<BusInfo> gsList=JSON.parseArray(responseString2, BusInfo.class);
                    for (BusInfo busInfo : gsList) {
                        busInfo.setType(1);
                    }
                    cachedList.addAll(gsList);
                    cacheDateStr=nowDateStr;
                }
            }
        }

        return cachedList;

    }

    @Override
    public BusInfo getByCarNo(String carNo) {
        List<BusInfo> list=getAllBus();
        for (BusInfo busInfo : list) {
            if(carNo.equals(busInfo.getSchoolBusCard())){
                return busInfo;
            }
        }
        return null;
    }
}
