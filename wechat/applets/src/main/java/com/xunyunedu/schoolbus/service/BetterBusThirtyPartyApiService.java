package com.xunyunedu.schoolbus.service;

import com.xunyunedu.schoolbus.pojo.GatePickInfo;

public interface BetterBusThirtyPartyApiService {

   GatePickInfo getStuPickInfo(Integer userid, String data, Integer direction, Integer doorDirection);

}
