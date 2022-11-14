package platform.szxyzxx.web.schoolbus.controller;

import framework.generic.dao.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.service.storage.service.FileService;
import platform.szxyzxx.bobao.service.BoBaoDaPingService;
import platform.szxyzxx.bobao.vo.BoBaoDaPing;
import platform.szxyzxx.schoolbus.pojo.BusInfo;
import platform.szxyzxx.schoolbus.pojo.BusStudentSign;
import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.pojo.StudentSignInfo;
import platform.szxyzxx.schoolbus.service.BetterBusThirtyPartyApiService;
import platform.szxyzxx.schoolbus.service.BusApiService;
import platform.szxyzxx.schoolbus.service.BusStudentSignService;
import platform.szxyzxx.schoolbus.service.CarIdentifyService;
import platform.szxyzxx.schoolbus.util.SchoolBusTimeUtil;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.schoolbus.vo.CarIdentifyVo;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 校车管理-接送学生数据
 */
@Controller
@RequestMapping("/bus/pickData")
public class PickStudentController {

    private String viewBasePath = "/bus/pick";


    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    private BusStudentSignService signService;

    @Autowired
    private BusApiService busApiService;

    @Autowired
    FileService fileService;

    @Autowired
    private BetterBusThirtyPartyApiService thirtyApiService;

    @Autowired
    private CarIdentifyService carIdentifyService;
    @Autowired
    private BoBaoDaPingService boBaoDaPingService;
    @RequestMapping("/busIndex")
    public String busIndex(ModelMap modelMap, Page page) {
        String nowSchoolYear = getNowSchoolYear();
        busPick(modelMap, page, nowSchoolYear, SchoolBusTimeUtil.getNowDateStr(), SchoolBusTimeUtil.getNowDirection(), null, null, null);
        return viewBasePath + "/busIndex";
    }

    //校车接送数据
    @RequestMapping("/busPickList")
    public String busPick(ModelMap modelMap, Page page, String schoolYear, String date, Integer direction, Integer teamId, Integer gradeId, String name) {
        List<StudentSignInfo> signInfoList = signService.findStudentSignCoreInfoList(page, schoolYear, teamId, gradeId, date, direction, name);
        //没打卡学生
        List<StudentSignInfo> notSignStuList = new ArrayList<>();

        for (StudentSignInfo studentSignInfo : signInfoList) {
            List<BusStudentSign> signList = studentSignInfo.getSignList();
            if (signList != null && signList.size() > 0) {
                studentSignInfo.setBusInfo(busApiService.getByCarNo(signList.get(0).getSchoolBusCard()));
            } else {
                notSignStuList.add(studentSignInfo);
            }
            setSignInfoUpDown(studentSignInfo);
        }

        List<GatePickInfo> gatePickInfoList = thirtyApiService.getStuPickInfo(createUserIdListBy(notSignStuList), date, direction, direction == 0 ? 1 : 0);

        for (StudentSignInfo studentSignInfo : signInfoList) {
            if ((studentSignInfo.getSignList() == null || studentSignInfo.getSignList().size() == 0) && findGateInfoByUserId(studentSignInfo.getStuUserId().toString(), gatePickInfoList) != null) {
                studentSignInfo.setStuStatus(direction == 0 ? "家长接送(已进校)" : "家长接送(已出校)");
            }
        }


        setAllLeaveInfo(signInfoList, date, null);
        modelMap.put("list", signInfoList);
        modelMap.put("date", date);
        modelMap.put("direction", direction);
        return viewBasePath + "/busList";
    }


    @RequestMapping("/inOutCounIndex")
    public String inSchoolIndex(ModelMap modelMap, Page page, HttpSession session) {
        inOutCountList(modelMap, page, getNowSchoolYear(), SchoolBusTimeUtil.getNowDateStr(), null, null, null, null, true, session);
        return viewBasePath + "/inOutCountIndex";
    }

    //status:0已到校：校车打卡或者门禁刷卡，1：未到校，2:已出校，3:未出校
    @RequestMapping("/inOutCountList")
    public String inOutCountList(ModelMap modelMap, Page page, String schoolYear, String date, Integer status, Integer teamId, Integer gradeId, String name, Boolean refreshCache, HttpSession session) {
        if (date == null) {
            date = SchoolBusTimeUtil.getNowDateStr();
        }
        List<StudentSignInfo> signInfoList = null;
        List<StudentSignInfo> ydxList = null;
        List<StudentSignInfo> wdxList = null;
        List<StudentSignInfo> ycxList = null;
        List<StudentSignInfo> wcxList = null;
        String sessionKey = "inOutCountList";
        List[] cache = (List[]) session.getAttribute(sessionKey);
        if (!Objects.equals(true, refreshCache) && cache != null) {
            signInfoList = cache[0];
            ydxList = cache[1];
            wdxList = cache[2];
            ycxList = cache[3];
            wcxList = cache[4];
        } else {
            //当日学生以及学生校车刷卡集合
            signInfoList = signService.findStudentSignCoreInfoList(null, schoolYear, teamId, gradeId, date, null, name);
            //当日学生集合的门禁打卡集合
            List<GatePickInfo> gatePickInfoList = thirtyApiService.getListStuPickInfoByObjList(signInfoList, date);


            //设置学生进出校状态
            for (StudentSignInfo signInfo : signInfoList) {
                setInOutInfo(signInfo, gatePickInfoList, date, 0);
                setInOutInfo(signInfo, gatePickInfoList, date, 1);
            }

            //已到校
            ydxList = findInOutStatusList(signInfoList, gatePickInfoList, date, 0);
            //未到校,不在已到校里面的就是未到校
            wdxList = findNotInAndSetInStatus(signInfoList, ydxList);
            //已出校
            ycxList = findInOutStatusList(signInfoList, gatePickInfoList, date, 1);
            //未出校
            wcxList = findNotInAndSetInStatus(signInfoList, ycxList);
            cache = new List[5];
            cache[0] = signInfoList;
            cache[1] = ydxList;
            cache[2] = wdxList;
            cache[3] = ycxList;
            cache[4] = wcxList;
            session.setAttribute(sessionKey, cache);
        }

        modelMap.put("ydxCount", ydxList.size());
        modelMap.put("wdxCount", wdxList.size());
        modelMap.put("ycxCount", ycxList.size());
        modelMap.put("wcxCount", wcxList.size());

        List<StudentSignInfo> list = null;
        if (status == null) {
            list = signInfoList;
        } else if (status == 0) {
            list = ydxList;
        } else if (status == 1) {
            list = wdxList;
        } else if (status == 2) {
            list = ycxList;
        } else {
            list = wdxList;
        }
        modelMap.put("list", pagingList(page.getCurrentPage(), page.getPageSize(), list));
        page.init(list.size(), page.getPageSize(), page.getCurrentPage());
        modelMap.put("page", page);
        modelMap.put("date", date);
        return viewBasePath + "/inOutCountList";
    }


    @RequestMapping("/parentIndex")
    public String parentIndex(ModelMap modelMap, Page page) {

        parentPick(getNowSchoolYear(), SchoolBusTimeUtil.getNowDateStr(), SchoolBusTimeUtil.getNowDirection(), null, null, null, page, modelMap);

        return viewBasePath + "/parentIndex";
    }


    //家长接送数据
    @RequestMapping("/parentPickList")
    public String parentPick(String schoolYear, String date, Integer direction, Integer teamId, Integer gradeId, String name, Page page, ModelMap modelMap) {

        StringBuilder sql = new StringBuilder("SELECT s.`name`,s.id student_id, s.user_id, t.name team_name, pp.`name` AS pname , pp.mobile,p.pick_date,p.direction,p.place,p.is_ruxiao FROM pj_student s  " +
                " LEFT JOIN pj_school_bus_manger m ON m.student_id = s.id " +
                " LEFT JOIN pj_team_student pts ON s.id = pts.student_id " +
                " LEFT JOIN pj_team t ON t.id = pts.team_id " +
                " LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id " +
                "LEFT JOIN pj_parent pp ON pp.user_id = ps.parent_user_id and pp.is_delete=0 and rank=1 " +
                "LEFT JOIN bus_parent_pick p ON p.student_id = s.id AND p.pick_date = '" + date + "' AND p.direction = " + direction + " WHERE m.id IS NULL AND s.is_delete = 0 and pts.is_delete=0");

        if (teamId != null) {
            sql.append(" and t.id=").append(teamId);
        }
        if (gradeId != null) {
            sql.append(" and t.grade_id=").append(gradeId);
        }
        if (StringUtils.isNotEmpty(name)) {
            sql.append(" and s.`name` like '%").append(name).append("%'");
        }

        if (teamId == null && gradeId == null) {
            sql.append(" and t.school_year='" + schoolYear + "'");
        }

        List<Map<String, Object>> list = basicSQLService.findByPaging(page, sql.toString());

        List<String> stuUserIds = new ArrayList<>(list.size());

        for (Map<String, Object> map : list) {
            stuUserIds.add(map.get("user_id").toString());
        }

        //查询时间段门禁打卡
        List<GatePickInfo> gatePickInfoList = thirtyApiService.getStuPickInfo(stuUserIds, date, direction, direction == 0 ? 1 : 0);

        for (Map<String, Object> map : list) {
            GatePickInfo gatePickInfo = findGateInfoByUserId(map.get("user_id").toString(), gatePickInfoList);
            String status = direction == 0 ? "未进校" : "未出校";
            if (gatePickInfo != null) {
                gatePickInfo.setEventTime(SchoolBusTimeUtil.formatGmtEventDateTime(gatePickInfo.getEventTime()));
                status = direction == 0 ? "已进校" : "已出校";
            }
            map.put("gateInfo", gatePickInfo);
            map.put("status", status);
        }

        setAllMapLeaveInfo(list, date, teamId);
        modelMap.put("list", list);
        modelMap.put("date", date);
        modelMap.put("direction", direction);

        return viewBasePath + "/parentList";
    }


    /**
     * 班级学生信息
     *
     * @param user
     * @return
     */
    @RequestMapping("/teamIndex")
    public String teamIndex(ModelMap modelMap, @CurrentUser UserInfo user) {
        Integer teamId = getUserManagedTeamId(user.getId());
        if (teamId == null) {
            return viewBasePath + "/error";
        }
        //teamList(modelMap, teamId, SchoolBusTimeUtil.getNowDateStr(), SchoolBusTimeUtil.getNowDirection());
        modelMap.put("teamId", teamId);
        return viewBasePath + "/teamIndex";

    }


    @RequestMapping("/teamList")
    public String teamList(ModelMap modelMap, Integer teamId, String date, Integer direction) {
        //当前日期班级学生打卡记录
        List<StudentSignInfo> studentSignInfoList = signService.findStudentSignInfoList(teamId, date, direction);
        //设置请假信息
        setAllLeaveInfo(studentSignInfoList, date, teamId);

        //当前时间段班级门禁打卡信息
        List<GatePickInfo> teamGatePickInfoList = thirtyApiService.getStuPickInfo(createUserIdListBy(studentSignInfoList), date, direction, direction == 0 ? 1 : 0);
        //因为要设置状态，所以不直接使用缓存的车辆信息
        List<BusInfo> allBus = getAllBus();

        //设置校车下打卡的学生列表
        for (BusInfo bus : allBus) {
            List<StudentSignInfo> busStuList = findSignList(studentSignInfoList, bus.getSchoolBusCard());
            //设置上下车信息
            for (StudentSignInfo studentSignInfo : busStuList) {
                setSignInfoUpDown(studentSignInfo);
            }
            bus.setStuList(busStuList);
        }
        //校门口接送
        List<StudentSignInfo> xmkJs = null;
        //停车场接送，车辆进校会自动插入放学“停车场门口”家长接送
        List<StudentSignInfo> tccJs = null;
        if (direction == 1) {
            //当日家长接送学生
            //放学才会有接送数据
            List<Map<String, Object>> jieSongList = basicSQLService.find("select s.id,s.`name`,p.place,s.user_id,p.manual_out_operator,p.manual_out_time from bus_parent_pick p left join pj_student s on s.id=p.student_id where s.team_id=" + teamId + " and pick_date='" + date + "' and direction=" + direction);
            //查找并设置校门正门接送
            xmkJs = findAndSetPickInfoByJsPlace(studentSignInfoList, jieSongList, teamGatePickInfoList, "校门正门");
            //查找并设置停车场接送
            tccJs = findAndSetPickInfoByJsPlace(studentSignInfoList, jieSongList, teamGatePickInfoList, "停车场门口");

            setManualOutInfo(studentSignInfoList, jieSongList);

            //设置家长车辆进出场时间
            setParkPickParentInOutInfo(tccJs, date);
            //校门口接送
            modelMap.put("grateJsList", xmkJs);
            //停车场门口接送
            modelMap.put("parkJsList", tccJs);
            //当前家长接送学生姓名
            String parentInSchoolStuNames =getMapStringVals("name",jieSongList);
            modelMap.put("parentInSchoolStuNames", parentInSchoolStuNames);
        }

        //没有校车打卡，也没有家长放学接送
        List<StudentSignInfo> notSign = findNoUsed(studentSignInfoList);
        //没有校车打卡，也没有家长接送，但有门禁打卡
        List<StudentSignInfo> pickedList = setUsedAndPickInfAndGet(notSign, teamGatePickInfoList, direction, date);
        formatAllEventTime(pickedList);

        //添加校车列表应该乘坐该校车的学生
        for (BusInfo bus : allBus) {
            List<StudentSignInfo> busStuList = findAndSetUsedByCarList(studentSignInfoList, bus.getSchoolBusCard());
            if (busStuList != null) {
                if (bus.getStuList() == null) {
                    bus.setStuList(new ArrayList<StudentSignInfo>(busStuList.size()));
                }
                for (StudentSignInfo studentSignInfo : busStuList) {
                    setSignInfoUpDown(studentSignInfo);
                    bus.getStuList().add(studentSignInfo);
                }
            }
        }

        //当前未分配
        List<StudentSignInfo> notUsed = findNoUsed(studentSignInfoList);
        setAllStatus(notUsed, direction == 0 ? "未进校" : "未出校");
        if (direction == 0) {
            setAllStatus(pickedList, "已进校");
            notUsed.addAll(pickedList);
            //家长接送
            modelMap.put("parentJsList", notUsed);
        } else {
            setAllStatus(pickedList, "已出校");
            //校门口接送下添加打卡位置为校门口的学生
            xmkJs.addAll(findGatePlacePick(pickedList));
            //停车场接送下添加打了停车场出校卡，但是没有家长接送的学生，并设置parentInSchool为false
            tccJs.addAll(findParkPlacePick(pickedList));
            //未分配学生
            modelMap.put("noUsedList", notUsed);
        }

        //有学生的校车
        List<BusInfo> notEmpyBusList = new ArrayList<>(allBus.size());
        for (BusInfo bus : allBus) {
            if (bus.getStuList() != null && bus.getStuList().size() > 0) {
                notEmpyBusList.add(bus);
            }
        }

        //设置校车进出状态
        setBusStatus(notEmpyBusList, date, direction);

        //当前已进校车辆
        StringBuilder inSchoolCars = new StringBuilder();
        for (BusInfo busInfo : notEmpyBusList) {
            if ("已进校".equals(busInfo.getStatus())) {
                if (inSchoolCars.length() != 0) {
                    inSchoolCars.append(",");
                }
                inSchoolCars.append(busInfo.getSchoolBusCard());
            }
        }


        //校车以及校车下的学生
        modelMap.put("carList", notEmpyBusList);
        //状态为已进校校车车牌列表
        modelMap.put("inSchoolCars", inSchoolCars);

        modelMap.put("imgPrefix", fileService.getHttpPrefix() + "/" + fileService.getSpaceName());

        modelMap.put("direction", direction);

        modelMap.put("date", date);

        return viewBasePath + "/teamList";
    }   


    @RequestMapping("/schoolParentIndex")
    public String schoolParentIndex() {
        return viewBasePath + "/schoolParentIndex";
    }
    @RequestMapping("/schoolParentIndex2")
    public String schoolParentIndex2() {
        return viewBasePath + "/schoolParentIndex2";
    }
    @RequestMapping("/schoolParentIndex3")
    public String schoolParentIndex3() {
        return viewBasePath + "/schoolParentIndex3";
    }
    @RequestMapping("/schoolParentIndex4")
    public String schoolParentIndex4() {
        return viewBasePath + "/schoolParentIndex4";
    }
    @RequestMapping("/schoolParentIndex5")
    public String schoolParentIndex5() {
        return viewBasePath + "/schoolParentIndex5";
    }



    @RequestMapping("/schoolParentList")
    @ResponseBody
    public Map schoolParentList(@RequestParam Integer id) {
        if (SchoolBusTimeUtil.getNowDirection() == 0) {
            return new HashMap(0);
        }
        BoBaoDaPing boBaoDaPing=boBaoDaPingService.findById(id);
        //停车场接送-未进校和已进校（未出校）列表
        List<Map<String, Object>> parkIn = new ArrayList<>();
        //停车场接送-已出校列表
        List<Map<String, Object>> parkOut = new ArrayList<>();
        //校门口接送-未出校列表
        List<Map<String, Object>> gateIn = new ArrayList<>();
        //校门口接送-已出校列表
        List<Map<String, Object>> gateOut = new ArrayList<>();

        String nowDate = SchoolBusTimeUtil.getNowDateStr();

        //当日放学全校家长接送的学生以及家长的车牌
        List<Map<String, Object>> parentPickList = basicSQLService.find(
                "SELECT s.id, s.`name`,s.persionId, s.team_name as teamName,pp.id as ppid," +
                "pp.place,pp.manual_out_operator,pp.is_bobao,pp.is_ruxiao, p.license_plate FROM bus_parent_pick pp" +
                " INNER JOIN pj_student s ON s.id = pp.student_id " +
                        " inner join pj_team pt on s.team_id=pt.id " +
                        " inner join pj_grade pg on pg.id=pt.grade_id "+
                        "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                        "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                        "WHERE pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pg.id in("+boBaoDaPing.getGradeIds()+")");
        if (parentPickList.size() == 0) {
            return new HashMap(0);
        }
        List<String> carNoList = new ArrayList<>();
        List<String> stuUserIdList = new ArrayList<>();
        for (Map<String, Object> pp : parentPickList) {
            String carNo = (String) pp.get("license_plate");
            if (pp.get("place").equals("停车场门口")) {
                if (StringUtils.isNotEmpty(carNo)) {
                    carNoList.add(carNo);
                }
            } else {
                //stuUserIdList.add(pp.get("persionId").toString());
            }
        }
        //车辆出校信息
        List<CarIdentifyVo> outList = carIdentifyService.getCardsMsg(1, SchoolBusTimeUtil.getDownBeginDateTime(nowDate), SchoolBusTimeUtil.getDownEndDateTime(nowDate), carNoList);
        //学生出校门禁打卡信息
       // List<GatePickInfo> gatePickInfoList = thirtyApiService.getStuPickInfo(stuUserIdList, nowDate, 1, 0);

        for (Map<String, Object> pp : parentPickList) {
            String carNo = (String) pp.get("license_plate");
            if (pp.get("place").equals("停车场门口")) {
                if (!pp.get("manual_out_operator").toString().equals("0") || (StringUtils.isNotEmpty(carNo) && findCarIdentifyList(carNo, outList) != null)) {
                    //已出校
                    parkOut.add(pp);
                } else {
                    //未进校或已进校
                    parkIn.add(pp);
                }
            } else if(pp.get("place").equals("校门正门")) {
                /*if (findGateInfoByUserId(pp.get("persionId").toString(), gatePickInfoList) != null) {
                 */    //未出校
                if(Integer.parseInt(pp.get("is_ruxiao").toString())==1){
                    gateIn.add(pp);
                }else if(Integer.parseInt(pp.get("is_ruxiao").toString())==2)  {
                    gateOut.add(pp);
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("parkIn", parkIn);
        map.put("parkOut", parkOut);
        map.put("gateIn", gateIn);
        map.put("gateOut", gateOut);
        return map;
    }

    /**
     * 统计每天家长接送播报出校的学生数量
     * @author  zy
     * @date  2022-10-19
     */
    @RequestMapping("/tongji")
    public ModelAndView findByTongJi() {
        return new ModelAndView("/bus/parenttongji/index");
    }
    /**
     * 统计每天家长接送播报出校的学生数量
     * @author  zy
     * @date  2022-10-19
     */
    @RequestMapping("/tongjiAll")
    @ResponseBody
    public Map<String,Object> findByTongJi(@RequestParam(value ="sub",required = false) String sub,
                                     @RequestParam(value = "nowDate",required = false) String nowDate,
                                     Model model){
        if(nowDate==null) {
             nowDate = SchoolBusTimeUtil.getNowDateStr();
        }

        //当日放学全校家长接送的学生以及家长的车牌
        Map<String,Object> map=new HashMap<>();
        List<Map<String, Object>> parentPickList = basicSQLService.find(
                "SELECT count(pp.id) as number FROM bus_parent_pick pp" +
                        " INNER JOIN pj_student s ON s.id = pp.student_id " +
                        " inner join pj_team pt on s.team_id=pt.id " +
                        " inner join pj_grade pg on pg.id=pt.grade_id "+
                        "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                        "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                        "WHERE pp.pick_date = '" + nowDate + "' AND pp.direction =1");
        map.put("zongshu",parentPickList);

        List<Map<String, Object>> parentChuZhonShu = basicSQLService.find(
                "SELECT count(pp.id) as number FROM bus_parent_pick pp" +
                        " INNER JOIN pj_student s ON s.id = pp.student_id " +
                        " inner join pj_team pt on s.team_id=pt.id " +
                        " inner join pj_grade pg on pg.id=pt.grade_id "+
                        "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                        "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                        "WHERE pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pp.is_ruxiao=2");
        map.put("parentChuZhonShu",parentChuZhonShu);

        List<Map<String, Object>> parentPickList2 = basicSQLService.find(
                "SELECT pg.name,pg.id,count(pp.id) as number FROM bus_parent_pick pp" +
                        " INNER JOIN pj_student s ON s.id = pp.student_id " +
                        " inner join pj_team pt on s.team_id=pt.id " +
                        " inner join pj_grade pg on pg.id=pt.grade_id "+
                        "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                        "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                        "WHERE pp.pick_date = '" + nowDate + "' AND pp.direction =1  group by pg.id");
        List<Map<String, Object>> parentYiChuXiao = basicSQLService.find(
                "SELECT pg.id,count(pp.id) as number FROM bus_parent_pick pp" +
                        " INNER JOIN pj_student s ON s.id = pp.student_id " +
                        " inner join pj_team pt on s.team_id=pt.id " +
                        " inner join pj_grade pg on pg.id=pt.grade_id "+
                        "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                        "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                        "WHERE pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pp.is_ruxiao=2  group by pg.id");
       List<Map> list=new ArrayList();

        for(Map<String,Object> bb:parentPickList2){
            Map<String,Object> map1=new HashMap<>();
            map1.put("name",bb.get("name"));
            map1.put("gradeNumber",bb.get("number"));
            for(Map<String,Object> cc:parentYiChuXiao){
                if(cc.get("id").toString().equals(bb.get("id").toString())){
                    map1.put("yichuxiao",Integer.parseInt(cc.get("number").toString()));
                }
            }
            if(!map1.containsKey("yichuxiao")){
                map1.put("yichuxiao",0);
            }
            list.add(map1);
        }
        map.put("list",list);
        return map;
    }
    /*
    * 播报大屏1
    */
    @RequestMapping("/schoolParentList2")
    @ResponseBody
    public Map schoolParentList2(@RequestParam Integer id) {
        if (SchoolBusTimeUtil.getNowDirection() == 0) {
            return new HashMap(0);
        }
        BoBaoDaPing boBaoDaPing=boBaoDaPingService.findById(id);
        //停车场接送-未进校和已进校（未出校）列表
        List<Map<String, Object>> parkIn = new ArrayList<>();
        //停车场接送-已出校列表
        List<Map<String, Object>> parkOut = new ArrayList<>();
        //校门口接送-未出校列表
        List<Map<String, Object>> gateIn = new ArrayList<>();
        //校门口接送-已出校列表
        List<Map<String, Object>> gateOut = new ArrayList<>();

        String nowDate = SchoolBusTimeUtil.getNowDateStr();
        //当日放学全校家长接送的学生以及家长的车牌
        List<Map<String, Object>> parentPickList = basicSQLService.find(
                "SELECT s.id, s.`name`,s.persionId, s.team_name as teamName,pp.id as ppid," +
                        "pp.place,pp.manual_out_operator,pp.is_bobao, p.license_plate FROM bus_parent_pick pp" +
                        " INNER JOIN pj_student s ON s.id = pp.student_id " +
                        " inner join pj_team pt on s.team_id=pt.id " +
                        " inner join pj_grade pg on pg.id=pt.grade_id "+
                        "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                        "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                        "WHERE pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pg.id in("+boBaoDaPing.getGradeIds()+")");
        if (parentPickList.size() == 0) {
            return new HashMap(0);
        }
        List<String> carNoList = new ArrayList<>();
        List<String> stuUserIdList = new ArrayList<>();
        for (Map<String, Object> pp : parentPickList) {
            String carNo = (String) pp.get("license_plate");
            if (pp.get("place").equals("停车场门口")) {
                if (StringUtils.isNotEmpty(carNo)) {
                    carNoList.add(carNo);
                }
            } else {
                stuUserIdList.add(pp.get("persionId").toString());
            }
        }
        //车辆出校信息
        List<CarIdentifyVo> outList = carIdentifyService.getCardsMsg(1, SchoolBusTimeUtil.getDownBeginDateTime(nowDate), SchoolBusTimeUtil.getDownEndDateTime(nowDate), carNoList);
        //学生出校门禁打卡信息
        List<GatePickInfo> gatePickInfoList = thirtyApiService.getStuPickInfo(stuUserIdList, nowDate, 1, 0);

        for (Map<String, Object> pp : parentPickList) {
            String carNo = (String) pp.get("license_plate");
            if (pp.get("place").equals("停车场门口")) {
                if (!pp.get("manual_out_operator").toString().equals("0") || (StringUtils.isNotEmpty(carNo) && findCarIdentifyList(carNo, outList) != null)) {
                    //已出校
                    parkOut.add(pp);
                } else {
                    //未进校或已进校
                    parkIn.add(pp);
                }
            } else {
                if (findGateInfoByUserId(pp.get("persionId").toString(), gatePickInfoList) != null) {
                    //未出校
                    gateOut.add(pp);
                } else {
                    //已出校
                    gateIn.add(pp);
                }
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("parkIn", parkIn);
        map.put("parkOut", parkOut);
        map.put("gateIn", gateIn);
        map.put("gateOut", gateOut);
        return map;
    }


    @RequestMapping("/updateBoBao/{id}")
    @ResponseBody
    public Integer updateBoBao(@PathVariable Integer id){
          String sql="update  bus_parent_pick set is_bobao=1 where id="+id;
          Integer num =basicSQLService.update(sql);
          return num;
    }

    private void setParkPickParentInOutInfo(List<StudentSignInfo> list, String date) {
        if (list.size() == 0) {
            return;
        }
        StringBuilder stuUserIds = new StringBuilder();
        for (StudentSignInfo studentSignInfo : list) {
            if (stuUserIds.length() != 0) {
                stuUserIds.append(",");
            }
            stuUserIds.append(studentSignInfo.getStuUserId());
        }
        String sql = "SELECT p.license_plate, ps.student_user_id FROM pj_parent_student ps INNER JOIN pj_parent p ON p.user_id = ps.parent_user_id WHERE ps.student_user_id IN (" + stuUserIds + ") AND ps.is_delete = 0 AND ps.rank = 1 AND p.is_delete = 0 AND p.license_plate IS NOT NULL";

        List<Map<String, Object>> stuCarList = basicSQLService.find(sql);

        List<String> carNoList = new ArrayList<>(stuCarList.size());

        for (Map<String, Object> map : stuCarList) {
            carNoList.add(map.get("license_plate").toString());
        }

        List<CarIdentifyVo> inList = carIdentifyService.getCardsMsg(0, SchoolBusTimeUtil.getDownBeginDateTime(date), SchoolBusTimeUtil.getDownEndDateTime(date), carNoList);
        List<CarIdentifyVo> outList = carIdentifyService.getCardsMsg(1, SchoolBusTimeUtil.getDownBeginDateTime(date), SchoolBusTimeUtil.getDownEndDateTime(date), carNoList);
        if (inList.size() > 1) {
            Collections.sort(inList);
        }
        if (outList.size() > 1) {
            Collections.sort(outList);
        }
        for (Map<String, Object> stuCar : stuCarList) {
            String carNo = stuCar.get("license_plate").toString();
            StudentSignInfo studentSignInfo = findStuListByUserId(list, stuCar.get("student_user_id").toString());
            if (studentSignInfo == null || carNo == null) {
                continue;
            }
            CarIdentifyVo inInfo = findCarIdentifyList(carNo, inList);
            if (inInfo != null) {
                studentSignInfo.setParentInOutCarNo(inInfo.getCarno());
                studentSignInfo.setParentInTime(inInfo.getTime());
            }
            CarIdentifyVo outInfo = findCarIdentifyList(carNo, outList);
            if (outInfo != null) {
                if (studentSignInfo.getParentInOutCarNo() == null) {
                    studentSignInfo.setParentInOutCarNo(inInfo.getCarno());
                }
                studentSignInfo.setParentOutTime(outInfo.getTime());
            }
        }

    }

    private StudentSignInfo findStuListByUserId(List<StudentSignInfo> list, String userId) {
        for (StudentSignInfo stu : list) {
            if (userId.equals(stu.getStuUserId().toString())) {
                return stu;
            }
        }
        return null;
    }


    /**
     * 查找门禁打卡为大门的学生
     *
     * @param list
     * @return
     */
    private List<StudentSignInfo> findGatePlacePick(List<StudentSignInfo> list) {
        List<StudentSignInfo> acceptList = new ArrayList<>(0);
        for (StudentSignInfo signInfo : list) {
            if (signInfo.getGatePickInfo().getDoorName().startsWith("大门")) {
                acceptList.add(signInfo);
            }
        }
        return acceptList;
    }

    /**
     * 查找门禁打卡为停车场的学生
     *
     * @param list
     * @return
     */
    private List<StudentSignInfo> findParkPlacePick(List<StudentSignInfo> list) {
        List<StudentSignInfo> acceptList = new ArrayList<>(0);
        for (StudentSignInfo signInfo : list) {
            if (!signInfo.getGatePickInfo().getDoorName().startsWith("大门")) {
                acceptList.add(signInfo);
            }
        }
        return acceptList;
    }


    private List<BusInfo> getAllBus() {
        List<BusInfo> cachedList = busApiService.getAllBus();
        List<BusInfo> all = new ArrayList<>(cachedList.size());
        for (BusInfo busInfo : cachedList) {
            BusInfo newBusInfo = new BusInfo();
            BeanUtils.copyProperties(busInfo, newBusInfo);
            all.add(newBusInfo);
        }
        return all;
    }


    private void setBusStatus(List<BusInfo> busList, String date, Integer direction) {
        if (busList.size() == 0) {
            return;
        }
        List<String> busNoList = new ArrayList<>(busList.size());
        for (BusInfo busInfo : busList) {
            busNoList.add(busInfo.getSchoolBusCard());
        }

        List<CarIdentifyVo> carIdentifyInList = null;
        List<CarIdentifyVo> carIdentifyOutList = null;

        if (direction == 0) {
            carIdentifyInList = carIdentifyService.getCardsMsg(0, SchoolBusTimeUtil.getUpBeginDateTime(date), SchoolBusTimeUtil.getUpEndDateTime(date), busNoList);
            carIdentifyOutList = carIdentifyService.getCardsMsg(1, SchoolBusTimeUtil.getUpBeginDateTime(date), SchoolBusTimeUtil.getUpEndDateTime(date), busNoList);
        } else {
            carIdentifyInList = carIdentifyService.getCardsMsg(0, SchoolBusTimeUtil.getDownBeginDateTime(date), SchoolBusTimeUtil.getDownEndDateTime(date), busNoList);
            carIdentifyOutList = carIdentifyService.getCardsMsg(1, SchoolBusTimeUtil.getDownBeginDateTime(date), SchoolBusTimeUtil.getDownEndDateTime(date), busNoList);
        }
        if (carIdentifyInList.size() == 0 && carIdentifyOutList.size() == 0) {
            return;
        }

        //按时间降序，取时间段内第一个进出时间为进出时间
        if (carIdentifyInList.size() > 1) {
            Collections.sort(carIdentifyInList);
        }
        if (carIdentifyOutList.size() > 1) {
            Collections.sort(carIdentifyOutList);
        }
        for (BusInfo busInfo : busList) {
            CarIdentifyVo inInfo = findCarIdentifyList(busInfo.getSchoolBusCard(), carIdentifyInList);
            if (inInfo != null) {
                busInfo.setStatus("已进校");
                busInfo.setInTime(inInfo.getTime());
            }
            CarIdentifyVo outInfo = findCarIdentifyList(busInfo.getSchoolBusCard(), carIdentifyOutList);
            if (outInfo != null) {
                busInfo.setStatus("已出校");
                busInfo.setOutTime(outInfo.getTime());
            }
        }

    }


    private CarIdentifyVo findCarIdentifyList(String carNo, List<CarIdentifyVo> list) {
        for (CarIdentifyVo carIdentifyVo : list) {
            if (carNo.contains(carIdentifyVo.getCarno())) {
                return carIdentifyVo;
            }
        }
        return null;
    }

    private void formatAllEventTime(List<StudentSignInfo> list) {
        for (StudentSignInfo studentSignInfo : list) {
            GatePickInfo gatePickInfo = studentSignInfo.getGatePickInfo();
            if (gatePickInfo != null) {
                gatePickInfo.setEventTime(SchoolBusTimeUtil.formatGmtEventDateTime(gatePickInfo.getEventTime()));
            }
        }
    }

    private void setAllStatus(List<StudentSignInfo> list, String status) {
        for (StudentSignInfo studentSignInfo : list) {
            studentSignInfo.setStuStatus(status);
        }
    }


    private List<StudentSignInfo> setUsedAndPickInfAndGet(List<StudentSignInfo> list, List<GatePickInfo> gatePickInfoList, Integer direction, String date) {
        List<StudentSignInfo> qualifierList = new ArrayList<>();
        for (StudentSignInfo signInfo : list) {
            GatePickInfo gatePickInfo = thirtyApiService.findList(gatePickInfoList, date, signInfo.getStuUserId(), direction, direction == 0 ? 1 : 0);
            if (gatePickInfo != null) {
                signInfo.setGatePickInfo(gatePickInfo);
                signInfo.setUsed(true);
                qualifierList.add(signInfo);
            }
        }
        return qualifierList;
    }


    private List<String> createUserIdListBy(List<StudentSignInfo> list) {
        if (list == null) {
            return null;
        }
        List<String> userIds = new ArrayList<>(list.size());
        for (StudentSignInfo signInfo : list) {
            userIds.add(signInfo.getStuUserId().toString());
        }
        return userIds;
    }

    private String getNowSchoolYear() {
        return basicSQLService.findUnique("select school_year from pj_school_term_current where school_id=215").toString();
    }


    /**
     * 根据停车场位置查找并设置门禁打卡信息
     *
     * @param stuList
     * @param jieSongList
     * @param place
     * @return
     */
    private List<StudentSignInfo> findAndSetPickInfoByJsPlace(List<StudentSignInfo> stuList, List<Map<String, Object>> jieSongList, List<GatePickInfo> gatePickInfoList, String place) {

        if (jieSongList == null || jieSongList.size() == 0) {
            return new ArrayList<>(0);
        }
        List<StudentSignInfo> consistentList = new ArrayList<>(0);

        for (Map<String, Object> jsMap : jieSongList) {
            if (place.equals(jsMap.get("place"))) {
                StudentSignInfo studentSignInfo = findListById(stuList, Integer.valueOf(jsMap.get("id").toString()));
                if (studentSignInfo != null && !studentSignInfo.isUsed()) {
                    //标记这个学生已经被分配
                    studentSignInfo.setUsed(true);
                    GatePickInfo gatePickInfo = findGateInfoByUserId(jsMap.get("user_id").toString(), gatePickInfoList);
                    if (gatePickInfo == null) {
                        studentSignInfo.setStuStatus("未出校");
                    } else {
                        studentSignInfo.setStuStatus("已出校");
                    }
                    studentSignInfo.setGatePickInfo(gatePickInfo);
                    consistentList.add(studentSignInfo);
                }
            }
        }


        return consistentList;
    }

    /**
     * 设置手动停车场出校的停车场出场信息
     */
    private void setManualOutInfo(List<StudentSignInfo> stuList, List<Map<String, Object>> jieSongList){
        for (Map<String, Object> jsMap : jieSongList) {
            if ("停车场门口".equals(jsMap.get("place"))) {
                StudentSignInfo studentSignInfo = findListById(stuList, Integer.valueOf(jsMap.get("id").toString()));
                if (studentSignInfo != null && !jsMap.get("manual_out_operator").toString().equals("0")) {
                    studentSignInfo.setParentOutTime(jsMap.get("manual_out_time").toString()+"(手动设置出校)");
                    studentSignInfo.setParentInOutCarNo("");
                }
            }
        }
    }

    private StudentSignInfo findListById(List<StudentSignInfo> list, Integer stuId) {
        for (StudentSignInfo studentSignInfo : list) {
            if (stuId.equals(studentSignInfo.getStuId())) {
                return studentSignInfo;
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

    private Map<String, Object> findMapListById(List<Map<String, Object>> list, Integer stuId) {
        for (Map<String, Object> map : list) {
            if (stuId.equals(Integer.valueOf(map.get("student_id").toString()))) {
                return map;
            }
        }
        return null;
    }


    private List<StudentSignInfo> findNoUsed(List<StudentSignInfo> list) {
        List<StudentSignInfo> consistentList = new ArrayList<>(0);
        for (StudentSignInfo studentSignInfo : list) {
            if (!studentSignInfo.isUsed()) {
                consistentList.add(studentSignInfo);
            }
        }
        return consistentList;
    }


    /**
     * 查找打卡记录为指定车牌的学生
     *
     * @param list
     * @param carNo
     * @return
     */
    private List<StudentSignInfo> findSignList(List<StudentSignInfo> list, String carNo) {
        List<StudentSignInfo> consistentList = new ArrayList<>(0);
        for (StudentSignInfo studentSignInfo : list) {
            if (studentSignInfo.isUsed()) {
                continue;
            }
            //有打卡记录且打卡的是指定的车辆
            if (studentSignInfo.getSignList() != null && studentSignInfo.getSignList().size() > 0) {
                List<BusStudentSign> busStudentSignList = studentSignInfo.getSignList();
                for (BusStudentSign busStudentSign : busStudentSignList) {
                    if (carNo.equals(busStudentSign.getSchoolBusCard())) {
                        consistentList.add(studentSignInfo);
                        //标记这个学生已经被分配
                        studentSignInfo.setUsed(true);
                        break;
                    }
                }
            }
        }
        return consistentList;
    }

    /**
     * 查找乘坐指定车辆的学生列表
     *
     * @param list
     * @param carNo
     * @return
     */
    private List<StudentSignInfo> findAndSetUsedByCarList(List<StudentSignInfo> list, String carNo) {
        List<StudentSignInfo> consistentList = new ArrayList<>(0);
        for (StudentSignInfo studentSignInfo : list) {
            //已分配和不做校车的学生不放到校车列表下
            if (studentSignInfo.isUsed() || studentSignInfo.getmId() == null) {
                continue;
            }
            if (studentSignInfo.getLastByCarNo() != null && studentSignInfo.getLastByCarNo().equals(carNo)) {
                consistentList.add(studentSignInfo);
                studentSignInfo.setUsed(true);
            }
        }
        return consistentList;
    }


    /**
     * 设置学生集合的请假信息
     *
     * @param signInfoList
     * @param date
     */
    private void setAllLeaveInfo(List<StudentSignInfo> signInfoList, String date, Integer teamId) {
        List<Map<String, Object>> leaveList = getLeaveList(date, teamId);
        //设置请假时间
        for (Map<String, Object> leaveMap : leaveList) {
            StudentSignInfo studentSignInfo = findListById(signInfoList, Integer.valueOf(leaveMap.get("id").toString()));
            if (studentSignInfo != null) {
                studentSignInfo.setLeaveStartDate((Date) leaveMap.get("begin_date"));
                studentSignInfo.setLeaveEndDate((Date) leaveMap.get("end_date"));
            }
        }
    }


    private void setAllMapLeaveInfo(List<Map<String, Object>> list, String date, Integer teamId) {
        List<Map<String, Object>> leaveList = getLeaveList(date, teamId);
        //设置请假时间
        for (Map<String, Object> leaveMap : leaveList) {
            Map<String, Object> map = findMapListById(list, Integer.valueOf(leaveMap.get("id").toString()));
            if (map != null) {
                map.put("begin_date", (Date) leaveMap.get("begin_date"));
                map.put("end_date", (Date) leaveMap.get("end_date"));
            }
        }
    }


    private List<Map<String, Object>> getLeaveList(String date, Integer teamId) {
        //当天全部请假学生
        String leaveSql = "SELECT s.id, sa.begin_date, sa.end_date FROM pj_student_asking sa LEFT JOIN pj_student s ON sa.student_id = s.id WHERE sa.india_status = 2";
        if (teamId != null) {
            leaveSql += " and s.team_id=" + teamId;
        }

        leaveSql += " and '" + date + "' between sa.begin_date and sa.end_date";
        return basicSQLService.find(leaveSql);
    }


    //根据打卡，设置学生状态以及上车、下车信息
    private void setSignInfoUpDown(StudentSignInfo studentSignInfo) {
        List<BusStudentSign> signList = studentSignInfo.getSignList();
        if (signList == null || signList.size() == 0) {
            studentSignInfo.setStuStatus("未上车");
        } else if (signList.size() == 1) {
            studentSignInfo.setStuStatus("已上车");
            studentSignInfo.setUpTime(signList.get(0).getSignTime());
            studentSignInfo.setUpPlace(signList.get(0).getSignAddress());
        } else {
            studentSignInfo.setStuStatus("已下车");
            Collections.sort(signList, new Comparator<BusStudentSign>() {
                @Override
                public int compare(BusStudentSign o1, BusStudentSign o2) {
                    return o1.getSignTime().compareTo(o2.getSignTime());
                }
            });
            int lastIndex = signList.size() - 1;
            studentSignInfo.setUpTime(signList.get(0).getSignTime());
            studentSignInfo.setUpPlace(signList.get(0).getSignAddress());

            studentSignInfo.setDownTime(signList.get(lastIndex).getSignTime());
            studentSignInfo.setDownPlace(signList.get(lastIndex).getSignAddress());
        }

    }

    private Integer getUserManagedTeamId(Integer userId) {
        String sql = "SELECT tt.team_id FROM pj_team_teacher tt  WHERE tt.user_id = " + userId + " AND tt.is_delete = 0 AND tt.`type` = '1'";
        List<Map<String, Object>> teamInfoList = basicSQLService.find(sql);
        if (teamInfoList == null || teamInfoList.size() == 0) {
            return null;
        }
        return Integer.valueOf(teamInfoList.get(0).get("team_id").toString());
    }


    private List<StudentSignInfo> findNotInAndSetInStatus(List<StudentSignInfo> all, List<StudentSignInfo> list) {
        List<StudentSignInfo> notInList = new ArrayList<>(0);
        for (StudentSignInfo studentSignInfo : all) {
            if (!list.contains(studentSignInfo)) {
                notInList.add(studentSignInfo);
            }
        }
        return notInList;
    }

    /**
     * 查找已出校、已进校列表
     *
     * @param list             学生和当天校车打卡集合
     * @param gatePickInfoList 当天学生列表门禁刷卡集合
     * @param date             指定的日期
     * @param direction        方向，0：上学，1：放学
     * @return
     */
    private List<StudentSignInfo> findInOutStatusList(List<StudentSignInfo> list, List<GatePickInfo> gatePickInfoList, String date, Integer direction) {
        List<StudentSignInfo> qualifierList = new ArrayList<>();
        for (StudentSignInfo studentSignInfo : list) {
            List<BusStudentSign> upSignList = findSignListByDirection(studentSignInfo, direction);
            //有校车打卡或者有门禁打卡
            if ((upSignList != null && upSignList.size() > 0) || isGatePick(gatePickInfoList, studentSignInfo.getStuUserId(), direction, direction == 0 ? 1 : 0, date)) {
                qualifierList.add(studentSignInfo);
            }
        }
        return qualifierList;
    }

    /**
     * 设置学生进出校信息
     *
     * @param signInfo
     * @param gatePickInfoList
     * @param date
     * @param direction
     */
    private void setInOutInfo(StudentSignInfo signInfo, List<GatePickInfo> gatePickInfoList, String date, Integer direction) {

        List<BusStudentSign> upSignList = findSignListByDirection(signInfo, direction);
        if ((upSignList != null && upSignList.size() > 0)) {
            if (direction == 0) {
                signInfo.setInStatus("已进校");
                signInfo.setGoToType("校车");
            } else {
                signInfo.setOutStatus("已出校");
                signInfo.setOutType("校车");
            }
        } else if (isGatePick(gatePickInfoList, signInfo.getStuUserId(), direction, direction == 0 ? 1 : 0, date)) {
            if (direction == 0) {
                signInfo.setInStatus("已进校");
                signInfo.setGoToType("家长接送");
            } else {
                signInfo.setOutStatus("已出校");
                signInfo.setOutType("家长接送");
            }
        } else {
            if (direction == 0) {
                signInfo.setInStatus("未进校");
            } else {
                signInfo.setOutStatus("未出校");
            }
        }

    }


    private List<StudentSignInfo> pagingList(int pageNum, int pageSize, List<StudentSignInfo> list) {
        int start = pageSize * (pageNum - 1);
        int end = start + pageSize;
        if (end > list.size()) {
            end = list.size();
        }
        List<StudentSignInfo> pagingList = new ArrayList<>(pageSize);
        for (int i = start; i < end; i++) {
            pagingList.add(list.get(i));
        }
        return pagingList;
    }

    private boolean isGatePick(List<GatePickInfo> gatePickInfoList, Integer stuUserId, Integer schoolDirection, Integer doorDirection, String date) {
        if (thirtyApiService.findList(gatePickInfoList, date, stuUserId, schoolDirection, doorDirection) != null) {
            return true;
        }
        return false;
    }


    private List<BusStudentSign> findSignListByDirection(StudentSignInfo studentSignInfo, Integer direction) {
        List<BusStudentSign> signList = studentSignInfo.getSignList();
        if (signList == null || signList.size() == 0) {
            return signList;
        }
        List<BusStudentSign> qualifierList = new ArrayList<>(signList.size());
        for (BusStudentSign busStudentSign : signList) {
            if (direction.equals(busStudentSign.getDirection())) {
                qualifierList.add(busStudentSign);
            }
        }
        return qualifierList;
    }

    private String getMapStringVals(String key,List<Map<String,Object>> list){
        StringBuilder s=new StringBuilder();
        for (Map<String, Object> map : list) {
                if(s.length()>0){
                    s.append(",");
                }
               s.append(map.get(key));
        }
        return s.toString();
    }



}
