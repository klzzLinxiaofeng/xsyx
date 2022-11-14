package platform.szxyzxx.schoolbus.service;

import platform.szxyzxx.schoolbus.pojo.GatePickInfo;

import java.util.List;

/**
 * 校车接送第三方api
 */
public interface BusThirdPartyApiService {

    /**
     * 查询时间段内，学生门禁打卡信息列表
     */
    List<GatePickInfo> getStudentGatePickInfo(String userId, String startTime, String endTime);


    /**
     * 查询时间段内，学生门禁打卡信息列表
     */
    List<GatePickInfo> getListStudentGatePickInfo(List<String> userIds, String startTime, String endTime);

}
