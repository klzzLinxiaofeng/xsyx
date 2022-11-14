package platform.szxyzxx.schoolbus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.pojo.StudentSignInfo;
import platform.szxyzxx.schoolbus.service.BetterBusThirtyPartyApiService;
import platform.szxyzxx.schoolbus.service.BusThirdPartyApiService;
import platform.szxyzxx.schoolbus.util.SchoolBusTimeUtil;

import java.util.*;

@Service
public class BetterBusThirtyPartyApiServiceImpl implements BetterBusThirtyPartyApiService {

    @Autowired
    @Qualifier("busThirdPartyApiServiceImpl")
    private BusThirdPartyApiService busThirdPartyApiService;

    @Autowired
    private BasicSQLService basicSQLService;


    @Override
    public GatePickInfo getStuPickInfo(Integer userId, String date, Integer schoolDirection, Integer doorDirection) {
        String[] timeInterval = getTimeInterval(date, schoolDirection);
        List<GatePickInfo> list = busThirdPartyApiService.getStudentGatePickInfo(userId.toString(), timeInterval[0], timeInterval[1]);
        //放学出校打卡增加特殊打卡记录：“忘记带卡”
        if(schoolDirection==1 && doorDirection==0 && list!=null){
            list.addAll(createForgetCarGatePickInfoList(date,userId));
        }
        if (list != null) {
            for (GatePickInfo gatePickInfo : list) {
                if (doorDirection.equals(gatePickInfo.getInAndOutType())) {
                    return gatePickInfo;
                }
            }
        }
        return null;
    }

    @Override
    public List<GatePickInfo> getStuPickInfo(List<String> userIds, String date, Integer schoolDirection, Integer doorDirection) {
        List<GatePickInfo> qualifierList=new ArrayList<>();
        String[] timeInterval = getTimeInterval(date, schoolDirection);
        List<GatePickInfo> list = busThirdPartyApiService.getListStudentGatePickInfo(userIds, timeInterval[0], timeInterval[1]);
        if (list != null) {
            for (GatePickInfo gatePickInfo : list) {
                if (doorDirection.equals(gatePickInfo.getInAndOutType())) {
                    qualifierList.add(gatePickInfo);
                }
            }
        }

        sort(qualifierList);

        //放学出校打卡增加特殊打卡记录：“忘记带卡”
        //因为数据不会太多，而且调用方会根据userId查找对应的打卡记录，所以就不根据userIds筛选了，直接返回当日全部“忘带卡”记录
        //因为查找只查找第一次打卡记录，所以特殊打卡放最后，这样如果真的打卡了还是返回的真实打卡记录
        if(schoolDirection==1 && doorDirection==0 && list!=null){
            qualifierList.addAll(createForgetCarGatePickInfoList(date,null));
        }

        return qualifierList;
    }

    @Override
    public GatePickInfo findList(List<GatePickInfo> list, String date, Integer userId, Integer schoolDirection, Integer doorDirection) {
        if(list==null || list.size()==0){
            return null;
        }
        for (GatePickInfo gatePickInfo : list) {
            if (userId.toString().equals(gatePickInfo.getPersonId()) && doorDirection.equals(gatePickInfo.getInAndOutType())) {
                String eventTime = gatePickInfo.getEventTime();
                //上学
                if (schoolDirection.equals(0)) {
                    //0-11点打卡算上学
                    if (eventTime.compareTo(SchoolBusTimeUtil.getUpBeginGmtDateTimeStr(date)) >= 0 && eventTime.compareTo(SchoolBusTimeUtil.getUpEndGmtDateTimeStr(date)) < 0) {
                        return gatePickInfo;
                    }
                } else {
                    //12-24算放学
                    if (eventTime.compareTo(SchoolBusTimeUtil.getUpBeginGmtDateTimeStr(date)) >= 0 && eventTime.compareTo(SchoolBusTimeUtil.getUpEndGmtDateTimeStr(date)) <= 0) {
                        return gatePickInfo;
                    }
                }
                return gatePickInfo;
            }
        }
        return null;
    }

    @Override
    public List<GatePickInfo> getListStuPickInfoByObjList(List<StudentSignInfo> stuList, String date) {
        List<String> userIds = new ArrayList<>(stuList.size());
        for (int i = 0; i < stuList.size(); i++) {
            userIds.add(stuList.get(i).getStuUserId().toString());
        }
        List<GatePickInfo> list= getListStuPickInfoByIdList(userIds, date);
        sort(list);
        return list;
    }


    @Override
    public List<GatePickInfo> getListStuPickInfoByIdList(List<String> userIds, String date) {
        List<GatePickInfo> list=busThirdPartyApiService.getListStudentGatePickInfo(userIds, SchoolBusTimeUtil.getUpBeginGmtDateTimeStr(date), SchoolBusTimeUtil.getDownEndGmtDateTimeStr(date));
        //放学出校打卡增加特殊打卡记录：“忘记带卡”
        //因为数据不会太多，而且调用方会根据userId查找对应的打卡记录，所以就不根据userIds筛选了，直接返回当日全部“忘带卡”记录
        if(list!=null) {
            list.addAll(createForgetCarGatePickInfoList(date, null));
        }
        return list;
    }



    private String[] getTimeInterval(String date, Integer direction) {
        String[] timeInterval = new String[2];
        if (direction == 0) {
            timeInterval[0] = SchoolBusTimeUtil.getUpBeginGmtDateTimeStr(date);
            timeInterval[1] = SchoolBusTimeUtil.getUpEndGmtDateTimeStr(date);
        } else {
            timeInterval[0] = SchoolBusTimeUtil.getDownBeginGmtDateTimeStr(date);
            timeInterval[1] = SchoolBusTimeUtil.getDownEndGmtDateTimeStr(date);
        }
        return timeInterval;
    }

    private List<GatePickInfo> createForgetCarGatePickInfoList(String date,Integer userId){
        String sql="select p.place,p.manual_out_time,s.user_id from bus_parent_pick p inner join pj_student s on s.id=p.student_id where p.pick_date='"+date+"' and p.direction=1 and p.manual_out_operator!=0";
        if(userId!=null){
            sql+=" and s.user_id="+userId;
        }
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        List<GatePickInfo> list=new ArrayList<>(mapList.size());
        for (Map<String, Object> map : mapList) {
            GatePickInfo gatePickInfo=new GatePickInfo();
            gatePickInfo.setEventTime(map.get("manual_out_time").toString());
            gatePickInfo.setDoorName(map.get("place").toString()+"(手动设置出校)");
            gatePickInfo.setInAndOutType(0);
            gatePickInfo.setPersonId(map.get("user_id").toString());
            list.add(gatePickInfo);
        }
        return list;
    }


    private void sort(List<GatePickInfo> list){
        //按打卡时间升序
        if(list.size()>1){
            Collections.sort(list, new Comparator<GatePickInfo>() {
                @Override
                public int compare(GatePickInfo o1, GatePickInfo o2) {
                    return o1.getEventTime().compareTo(o2.getEventTime());
                }
            });
        }
    }

}
