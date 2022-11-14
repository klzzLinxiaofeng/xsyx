package platform.szxyzxx.schoolbus.service.impl;

import org.springframework.stereotype.Service;
import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.service.BusThirdPartyApiService;

import java.util.ArrayList;
import java.util.List;

/**
 * 门禁接口测试实现，用于返回一些假数据在测试环境下使用
 */
@Service("busThirdPartyApiServiceTestImpl")
public class BusThirdPartyApiServiceTestImpl implements BusThirdPartyApiService {

    @Override
    public List<GatePickInfo> getStudentGatePickInfo(String userId, String startTime, String endTime) {
        List<GatePickInfo> list=new ArrayList<>(1);
        if(userId.equals("112722")){
            GatePickInfo gatePickInfo=new GatePickInfo();
            gatePickInfo.setDoorName("C门左通道主板_门_1");
            gatePickInfo.setEventTime("2018-08-14T07:49:10+08:00");
            gatePickInfo.setInAndOutType(1);
            gatePickInfo.setPersonId(userId);
            GatePickInfo gatePickInfo2=new GatePickInfo();
            gatePickInfo2.setDoorName("C门左通道主板_门_1");
            gatePickInfo2.setEventTime("2018-08-14T18:49:10+08:00");
            gatePickInfo2.setInAndOutType(0);
            gatePickInfo2.setPersonId(userId);
            list.add(gatePickInfo);
            list.add(gatePickInfo2);
        }
        return list;
    }

    @Override
    public List<GatePickInfo> getListStudentGatePickInfo(List<String> userIds, String startTime, String endTime) {
        if(userIds.contains("112722")){
            return getStudentGatePickInfo("112722",startTime,endTime);
        }
        return null;
    }
}
