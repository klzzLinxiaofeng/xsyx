package platform.szxyzxx.schoolbus.service;

import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.pojo.StudentSignInfo;

import java.util.List;
import java.util.Map;

public interface BetterBusThirtyPartyApiService {
    /**
     * 获取学生门禁打卡信息
     * @param userId 学生userId
     * @param date 日期
     * @param schoolDirection 方向（0：上学，1：放学）
     * @param doorDirection 进出方向（0：出，1：进）
     * @return
     */
    GatePickInfo getStuPickInfo(Integer userId,String date,Integer schoolDirection,Integer doorDirection);


    List<GatePickInfo> getStuPickInfo(List<String> userIds,String date,Integer schoolDirection,Integer doorDirection);

    List<GatePickInfo> getListStuPickInfoByObjList(List<StudentSignInfo> stuList, String date);

    /**
     * 查询学生用户id列表指定日期全部门禁打卡
     * @param userIds
     * @param date
     * @return
     */
    List<GatePickInfo> getListStuPickInfoByIdList(List<String> userIds, String date);

    /**
     * 根据条件，查找第一个符合条件的打卡信息
     * @param list 打卡列表
     * @param userId 学生userId
     * @param schoolDirection 方向（0：上学，1：放学）
     * @param doorDirection 进出方向（0：出，1：进）
     * @return
     */
    GatePickInfo findList(List<GatePickInfo> list,String date,Integer userId,Integer schoolDirection,Integer doorDirection);

}
