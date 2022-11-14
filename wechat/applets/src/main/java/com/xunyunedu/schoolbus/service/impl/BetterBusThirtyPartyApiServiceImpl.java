package com.xunyunedu.schoolbus.service.impl;

import com.alibaba.fastjson.JSON;
import com.xunyunedu.schoolbus.pojo.GatePickInfo;
import com.xunyunedu.schoolbus.service.BetterBusThirtyPartyApiService;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class BetterBusThirtyPartyApiServiceImpl implements BetterBusThirtyPartyApiService {
    @Override
    public GatePickInfo getStuPickInfo(Integer userId, String data, Integer direction, Integer doorDirection) {
        String nowDate=new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        Map<String,Object> param=new HashMap<>();
        param.put("date",nowDate);
        param.put("userId",userId.toString());
        param.put("schoolDirection",direction);
        param.put("doorDirection",direction==0? 1:0);
        try {
            String result=HttpClientUtils.postJson("http://localhost:8880/api/gate/getStuPickInfo", JSON.toJSONString(param));
            if(result==null || result.length()==0){
                return null;
            }
            return JSON.parseObject(result,GatePickInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
