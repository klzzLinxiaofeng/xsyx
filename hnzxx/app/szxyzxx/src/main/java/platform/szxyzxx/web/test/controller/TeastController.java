package platform.szxyzxx.web.test.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.service.BetterBusThirtyPartyApiService;
import platform.szxyzxx.schoolbus.service.CarIdentifyService;
import platform.szxyzxx.schoolbus.util.SchoolBusTimeUtil;
import platform.szxyzxx.web.schoolbus.vo.CarIdentifyVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test/jiesong")
public class TeastController {

    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private CarIdentifyService carIdentifyService;
    @Autowired
    private BetterBusThirtyPartyApiService thirtyApiService;

    @RequestMapping("asd/getBy")
    public ModelAndView getByasd(){
        ModelAndView mov = new ModelAndView("bus/pick/schoolParentIndex");
        return mov;
    }

    @RequestMapping("asd/getDate")
    @ResponseBody
    public Map schoolParentList(){
        String viewPath="/bus/pick/schoolParentList";

        if(SchoolBusTimeUtil.getNowDirection()==0){
            return new HashMap(0);
        }
        //停车场接送-未进校和已进校（未出校）列表
        List<Map<String, Object>> parkIn=new ArrayList<>();
        //停车场接送-已出校列表
        List<Map<String, Object>> parkOut=new ArrayList<>();
        //校门口接送-未出校列表
        List<Map<String, Object>> gateIn=new ArrayList<>();
        //校门口接送-已出校列表
        List<Map<String, Object>> gateOut=new ArrayList<>();

        String nowDate=SchoolBusTimeUtil.getNowDateStr();

        //当日放学全校家长接送的学生以及家长的车牌
        List<Map<String, Object>> parentPickList = basicSQLService.find("SELECT s.id, s.`name`,s.user_id, s.team_name as teamName,pp.place,pp.manual_out_operator, p.license_plate FROM bus_parent_pick pp INNER JOIN pj_student s ON s.id = pp.student_id " +
                "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 WHERE pp.pick_date = '"+nowDate+"' AND pp.direction =1");
        if(parentPickList.size()==0){
            return new HashMap(0);
        }

        List<String> carNoList=new ArrayList<>();
        List<String> stuUserIdList=new ArrayList<>();
        for (Map<String, Object> pp : parentPickList) {
            String carNo=(String) pp.get("license_plate");
            if(pp.get("place").equals("停车场门口")){
                if(StringUtils.isNotEmpty(carNo)) {
                    carNoList.add(carNo);
                }
            }else{
                stuUserIdList.add(pp.get("user_id").toString());
            }
        }
        //车辆出校信息
        List<CarIdentifyVo> outList = carIdentifyService.getCardsMsg(1, SchoolBusTimeUtil.getDownBeginDateTime(nowDate), SchoolBusTimeUtil.getDownEndDateTime(nowDate), carNoList);
        //学生出校门禁打卡信息
        List<GatePickInfo> gatePickInfoList = thirtyApiService.getStuPickInfo(stuUserIdList, nowDate, 1, 0);

        for (Map<String, Object> pp : parentPickList) {
            String carNo=(String) pp.get("license_plate");
            if(pp.get("place").equals("停车场门口")){
                if(!pp.get("manual_out_operator").toString().equals("0") || (StringUtils.isNotEmpty(carNo) && findCarIdentifyList(carNo,outList)!=null)  ) {
                    parkOut.add(pp);
                }else{
                    parkIn.add(pp);
                }
            }else{
                if(findGateInfoByUserId(pp.get("user_id").toString(),gatePickInfoList)!=null){
                    gateOut.add(pp);
                }else{
                    gateIn.add(pp);
                }
            }
        }
        Map<String,Object> map=new HashMap<>();
        map.put("parkIn",parkIn);
        map.put("parkOut",parkOut);
        map.put("gateIn",gateIn);
        map.put("gateOut",gateOut);
        return map;
    }

    private CarIdentifyVo findCarIdentifyList(String carNo, List<CarIdentifyVo> list) {
        for (CarIdentifyVo carIdentifyVo : list) {
            if (carNo.contains(carIdentifyVo.getCarno())) {
                return carIdentifyVo;
            }
        }
        return null;
    }

    private GatePickInfo findGateInfoByUserId(String userId, List<GatePickInfo> list) {
        List<GatePickInfo> qualifierList = new ArrayList<>(0);
        if (list != null) {
            //只取第一条打卡
            for (GatePickInfo gatePickInfo : list) {
                if (gatePickInfo.getPersonId().equals(userId)) {
                    qualifierList.add(gatePickInfo);
                }
            }
        }
        if (qualifierList.size() == 0) {
            return null;
        }
        return qualifierList.get(0);
    }

}
