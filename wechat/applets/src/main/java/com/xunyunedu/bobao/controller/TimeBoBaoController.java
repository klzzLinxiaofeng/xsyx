package com.xunyunedu.bobao.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.hikvision.artemis.sdk.config.ArtemisConfig;
import com.xunyunedu.bobao.service.BoBaoDaPingService;
import com.xunyunedu.bobao.util.HikvisionClient;
import com.xunyunedu.bobao.util.SchoolBusTimeUtil;
import com.xunyunedu.bobao.vo.BoBaoDaPing;
import com.xunyunedu.bobao.vo.HikDoorRequestData;
import com.xunyunedu.bobao.vo.HikTeamResponseVo;
import com.xunyunedu.core.service.BasicSQLService;
import com.xunyunedu.util.httpclient.HttpClientUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/broadCast")
@Api(value = "/broadCast", description = "播报")
public class TimeBoBaoController {
    Logger log = LoggerFactory.getLogger(TimeBoBaoController.class);
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private BoBaoDaPingService boBaoDaPingService;
    //获取所有设备id
    private static String devicesIdUrl;
    //tts转换接口
    private static String devicesTtsUrl;
    static {
        devicesIdUrl = "/api/getAllDevicesID";
        devicesTtsUrl = "/api/postTTSPlayMusic";
    }

    @RequestMapping("/bobao")
    @ApiOperation(value = "bobao", httpMethod = "GET")
    public Map<String,Object> findByQueRenDaoXiao(@RequestParam Integer studentId) {
        String format = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        basicSQLService.update("update bus_parent_pick  set is_ruxiao=1 where student_id="+studentId+"  and pick_date='"+format+"'");
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        return map;
    }

    /*
    * 手动确认到校
    * */
    @RequestMapping("/bobaoBanZhuren")
    @ApiOperation(value = "bobaoBanZhuren", httpMethod = "GET")
    public Map<String,Object> findByQuebobaoBanZhuren(@RequestParam Integer studentId,
                                                      @RequestParam Integer userId) {
        Map<String,Object> map=new HashMap<>();
       /* List<Map<String,Object>> mapList2=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id   where 1=1  and yur.user_id="+userId);
        for(Map<String,Object> aa:mapList2){
            if(aa.get("code").equals("CLASS_MASTER")){*/
                String format = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
               int num= basicSQLService.update("update bus_parent_pick  set is_ruxiao=1 where student_id="+studentId+"  and pick_date='"+format+"'");
               if(num>0){
                   map.put("code",200);
                   map.put("msg","确认到校成功");
                   return map;
               }else{
                   map.put("code",401);
                   map.put("msg","到校失败，请重试");
                   return map;
               }
           /* }
        }
        map.put("code",403);
        map.put("msg","无班主任权限");
        return map;*/
    }

    @RequestMapping("/findByShouDong")
    @ApiOperation(value = "手动开启", httpMethod = "GET")
    public void findByShouDong() throws InterruptedException {
        findByJieSong();

    }
    @RequestMapping("/findByShouDong2")
    @ApiOperation(value = "手动开启BoBao2", httpMethod = "GET")
    public void findByShouDong2() throws InterruptedException {
        findByJieSong2();

    }
    @RequestMapping("/findByShouDong3")
    @ApiOperation(value = "手动开启BoBao3", httpMethod = "GET")
    public void findByShouDong3() throws InterruptedException {
        findByJieSong3();

    }
    @RequestMapping("/findByShouDong4")
    @ApiOperation(value = "手动开启BoBao4", httpMethod = "GET")
    public void findByShouDong4() throws InterruptedException {
        findByJieSong4();

    }
    @RequestMapping("/findByShouDong5")
    @ApiOperation(value = "手动开启BoBao5", httpMethod = "GET")
    public void findByShouDong5() throws InterruptedException {
        findByJieSong5();

    }


    @RequestMapping("/TingZhiBoBao")
    @ApiOperation(value = "/TingZhibobao", httpMethod = "GET")
    public Map<String,Object> findByTingZhi(@RequestParam Integer id) {
        String format = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        basicSQLService.update("update bus_parent_pick  set is_bobao=1,is_ruxiao=2 where id="+id);
        Map<String,Object> map=new HashMap<>();
        map.put("code",200);
        map.put("msg","success");
        return map;
    }

    /**获取每天所有学生接送信息
     *
     **/
    @Scheduled(cron = "0 55 11 * * ?") //每天
    public void findByJieSong() throws InterruptedException {
        BoBaoDaPing boBaoDaPing=boBaoDaPingService.findById(1);
        System.out.println(boBaoDaPing.getBoBaoHao());

            if (boBaoDaPing.getBoBaoHao() != null) {
                String array = "[" + boBaoDaPing.getBoBaoHao() + "]";
                while (true) {
                    try {
                        System.out.println("哈哈哈");
                        String nowDate = SchoolBusTimeUtil.getNowDateStr();
                        //当日放学全校家长接送的学生以及家长的车牌
                        List<Map<String, Object>> parentPickList = basicSQLService.find(
                                "SELECT s.id, s.`name`,s.persionId, s.team_name as teamName,pp.id as ppid," +
                                        "pp.place,pp.manual_out_operator,pp.is_bobao,pp.is_ruxiao, p.license_plate FROM bus_parent_pick pp" +
                                        " INNER JOIN pj_student s ON s.id = pp.student_id " +
                                        " inner join pj_team pt on s.team_id=pt.id " +
                                        " inner join pj_grade pg on pg.id=pt.grade_id " +
                                        "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                                        "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                                        "WHERE pp.is_ruxiao=1 and pp.place='校门正门' and pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pg.id in(" + boBaoDaPing.getGradeIds() + ")");
                        //海康门禁刷卡记录
                        String format = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                        String hkStartTime = format + "T15:40:00+08:00";
                        String hkEndTime = format + "T19:00:00+08:00";
                        ArtemisConfig artemisConfig = new ArtemisConfig();
                        artemisConfig.setHost("10.170.75.40:443");
                        artemisConfig.setAppKey("21687113");
                        artemisConfig.setAppSecret("SD9Wldb8EKABe77COrgS");

                        if (!parentPickList.isEmpty()) {
                            List<Map<String, Object>> list = findByBoBaoTime();
                            if (!list.isEmpty()) {
                                Date date = new Date();
                                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                                for (Map<String, Object> aa : list) {
                                    //开始时间
                                    String str2 = aa.get("startTime").toString();
                                    String startTime = str2.substring(0, str2.indexOf("T"));
                                    String startTimeShiFen = str2.substring(str2.indexOf("T") + 1, str2.length());
                                    //结束时间
                                    String str3 = aa.get("overTime").toString();
                                    String endTime = str3.substring(0, str3.indexOf("T"));
                                    String endTimeShiFen = str3.substring(str3.indexOf("T") + 1, str3.length());
                                        Date kaishi = sdf2.parse(startTime);
                                        Date jieshu = sdf2.parse(endTime);
                                        Date date5 = sdf2.parse(sdf2.format(date));
                                        //log.info("判断"+(kaishi.compareTo(date5) <= 0)+"第一"+(jieshu.compareTo(date5) >= 0));
                                        if (kaishi.compareTo(date5) <= 0 && jieshu.compareTo(date5) >= 0) {
                                            Date kaishiShiFen = sdf.parse(startTimeShiFen);
                                            Date jieshuShiFen = sdf.parse(endTimeShiFen);
                                            Date date2 = sdf.parse(sdf.format(date));
                                            // log.info("判断2"+(kaishiShiFen.compareTo(date2) < 0)+"第二"+(jieshuShiFen.compareTo(date2) > 0));
                                            if (kaishiShiFen.compareTo(date2) <= 0 && jieshuShiFen.compareTo(date2) >= 0) {
                                                for (Map<String, Object> bb : parentPickList) {
                                                    if (Integer.parseInt(bb.get("is_ruxiao").toString()) == 1) {
                                                        //海康获取记录是否出校
                                                        List<String> stringList = new ArrayList<>();
                                                        if (bb.get("persionId").toString() != null) {
                                                            stringList.add(bb.get("persionId").toString());
                                                            HikDoorRequestData hikDoorRequestData = new HikDoorRequestData(hkStartTime, hkEndTime, "大门", 1, 1000, stringList);
                                                            String json = JSONObject.toJSONString(hikDoorRequestData);
                                                            String data = HikvisionClient.hikvisionPost(artemisConfig, "/api/acs/v2/door/events", json);
                                                            //查看出校信息
                                                            HikTeamResponseVo responseVo = JSON.parseObject(data, new TypeReference<HikTeamResponseVo>() {
                                                            });
                                                            if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
                                                                List<HikTeamResponseVo.DataBean.SuccessesBean> successesBeanList = responseVo.getData().getList();
                                                                if (successesBeanList.size() > 0) {
                                                                    System.out.println("修改");
                                                                    basicSQLService.update("update bus_parent_pick  set is_bobao=1,is_ruxiao=2 where id=" + bb.get("ppid"));
                                                                } else {
                                                                    System.out.println("播报开始");
                                                                    String bobaoHosts = "http://10.170.74.48:25012";
                                                                    String banji="";
                                                                    if(bb.get("teamName").toString().substring(3, 7).equals("(10)")){
                                                                        banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                                    }else{
                                                                        banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                                    }
                                                                    String text = banji + "、" + banji;
                                                                    String tts = null;
                                                                    tts = bobaoTts(array, text, 1, bobaoHosts);
                                                                    if (tts != null) {
                                                                        log.info("播报完成");
                                                                        Thread.sleep(3500);
                                                                    } else {
                                                                        log.info("播报返回null");
                                                                        Thread.sleep(10000);
                                                                    }

                                                                }
                                                            } else {
                                                                log.error("海康组织--海康查询门禁失败！{}", responseVo.getMsg());
                                                                Thread.sleep(10000);
                                                            }
                                                        } else {

                                                        }
                                                    } else {
                                                        Thread.sleep(10000);
                                                    }
                                                }
                                            } else {
                                                Thread.sleep(10000);
                                            }
                                        } else {
                                            Thread.sleep(10000);
                                        }
                                }
                            }
                        } else {
                            log.info("暂无学生列表");
                            Thread.sleep(10000);
                        }
                        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                        LocalTime localTime = LocalTime.parse("20:00:00", dtf);
                        if (LocalTime.now().isAfter(localTime)) {
                            break;
                        }
                        log.info("error");
                    }catch (Exception e){
                        Thread.sleep(10000);
                    }
                }
            }
        }
    /**
     * 获取每天所有学生接送信息
     *
     **/
    @Scheduled(cron = "0 1 12 * * ?") //每天
    public void findByJieSong2() throws InterruptedException {
        BoBaoDaPing boBaoDaPing=boBaoDaPingService.findById(2);
        if(boBaoDaPing.getBoBaoHao()!=null) {
            String array = "[" + boBaoDaPing.getBoBaoHao() + "]";
            while (true) {
                try {
                    String nowDate = SchoolBusTimeUtil.getNowDateStr();
                    //当日放学全校家长接送的学生以及家长的车牌
                    List<Map<String, Object>> parentPickList = basicSQLService.find(
                            "SELECT s.id, s.`name`,s.persionId, s.team_name as teamName,pp.id as ppid," +
                                    " pp.place,pp.manual_out_operator,pp.is_bobao,pp.is_ruxiao, p.license_plate FROM bus_parent_pick pp" +
                                    " INNER JOIN pj_student s ON s.id = pp.student_id " +
                                    " inner join pj_team pt on s.team_id=pt.id " +
                                    " inner join pj_grade pg on pg.id=pt.grade_id " +
                                    "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                                    "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                                    "WHERE  pp.is_ruxiao=1 and pp.place='校门正门' and pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pg.id in(" + boBaoDaPing.getGradeIds() + ")");
                    //海康门禁刷卡记录
                    String format = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    String hkStartTime = format + "T15:40:00+08:00";
                    String hkEndTime = format + "T19:00:00+08:00";
                    ArtemisConfig artemisConfig = new ArtemisConfig();
                    artemisConfig.setHost("10.170.75.40:443");
                    artemisConfig.setAppKey("21687113");
                    artemisConfig.setAppSecret("SD9Wldb8EKABe77COrgS");

                    if (!parentPickList.isEmpty()) {
                        List<Map<String, Object>> list = findByBoBaoTime();
                        if (!list.isEmpty()) {
                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                            for (Map<String, Object> aa : list) {
                                //开始时间
                                String str2 = aa.get("startTime").toString();
                                String startTime = str2.substring(0, str2.indexOf("T"));
                                String startTimeShiFen = str2.substring(str2.indexOf("T") + 1, str2.length());
                                //结束时间
                                String str3 = aa.get("overTime").toString();
                                String endTime = str3.substring(0, str3.indexOf("T"));
                                String endTimeShiFen = str3.substring(str3.indexOf("T") + 1, str3.length());
                                try {
                                    Date kaishi = sdf2.parse(startTime);
                                    Date jieshu = sdf2.parse(endTime);
                                    Date date5 = sdf2.parse(sdf2.format(date));
                                    //log.info("判断"+(kaishi.compareTo(date5) <= 0)+"第一"+(jieshu.compareTo(date5) >= 0));
                                    if (kaishi.compareTo(date5) <= 0 && jieshu.compareTo(date5) >= 0) {
                                        Date kaishiShiFen = sdf.parse(startTimeShiFen);
                                        Date jieshuShiFen = sdf.parse(endTimeShiFen);
                                        Date date2 = sdf.parse(sdf.format(date));
                                        // log.info("判断2"+(kaishiShiFen.compareTo(date2) < 0)+"第二"+(jieshuShiFen.compareTo(date2) > 0));
                                        if (kaishiShiFen.compareTo(date2) <= 0 && jieshuShiFen.compareTo(date2) >= 0) {
                                            for (Map<String, Object> bb : parentPickList) {
                                                if (Integer.parseInt(bb.get("is_ruxiao").toString()) == 1) {
                                                    //海康获取记录是否出校
                                                    List<String> stringList = new ArrayList<>();
                                                    if (bb.get("persionId").toString() != null) {
                                                        stringList.add(bb.get("persionId").toString());
                                                        HikDoorRequestData hikDoorRequestData = new HikDoorRequestData(hkStartTime, hkEndTime, "大门出向", 1, 1000, stringList);
                                                        String json = JSONObject.toJSONString(hikDoorRequestData);
                                                        String data = HikvisionClient.hikvisionPost(artemisConfig, "/api/acs/v2/door/events", json);
                                                        //查看出校信息
                                                        HikTeamResponseVo responseVo = JSON.parseObject(data, new TypeReference<HikTeamResponseVo>() {
                                                        });
                                                        if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
                                                            List<HikTeamResponseVo.DataBean.SuccessesBean> successesBeanList = responseVo.getData().getList();
                                                            if (successesBeanList.size() > 0) {
                                                                log.info("修改");
                                                                basicSQLService.update("update bus_parent_pick  set is_bobao=1,is_ruxiao=2 where id=" + bb.get("ppid"));
                                                            } else {
                                                                log.info("播报开始");
                                                                String bobaoHosts = "http://10.170.74.48:25012";
                                                                String banji = "";
                                                                if (bb.get("teamName").toString().substring(3, 7).equals("(10)")) {
                                                                    banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                                } else {
                                                                    banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                                }
                                                                String text = banji + "、" + banji;
                                                                String tts = null;
                                                                tts = bobaoTts(array, text, 1, bobaoHosts);
                                                                if (tts != null) {
                                                                    log.info("播报完成");
                                                                    Thread.sleep(3500);
                                                                } else {
                                                                    log.info("播报返回null");
                                                                    Thread.sleep(10000);
                                                                }

                                                            }
                                                        } else {
                                                            log.error("海康组织--海康查询门禁失败！{}", responseVo.getMsg());
                                                            Thread.sleep(5000);
                                                        }
                                                    } else {

                                                    }
                                                } else {
                                                    Thread.sleep(10000);
                                                }
                                            }
                                        } else {
                                            Thread.sleep(10000);
                                        }
                                    } else {
                                        Thread.sleep(10000);
                                    }
                                } catch (ParseException e) {
                                    Thread.sleep(10000);
                                }
                            }
                        }
                    } else {
                        log.info("暂无学生列表");
                        Thread.sleep(10000);
                    }
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime localTime = LocalTime.parse("20:00:00", dtf);
                    if (LocalTime.now().isAfter(localTime)) {
                        break;
                    }
                }catch (Exception e){
                    log.error("线程异常，停止10秒");
                    Thread.sleep(10000);
                }
            }
        }
    }

    /**
     * 获取每天所有学生接送信息
     * 播报大屏3
     **/
    @Scheduled(cron = "0 2 12 * * ?") //每天
    public void findByJieSong3() throws InterruptedException {
        BoBaoDaPing boBaoDaPing=boBaoDaPingService.findById(3);
        if(boBaoDaPing.getBoBaoHao()!=null) {
            String array = "[" + boBaoDaPing.getBoBaoHao() + "]";
            while (true) {
                try {
                    String nowDate = SchoolBusTimeUtil.getNowDateStr();
                    //当日放学全校家长接送的学生以及家长的车牌
                    List<Map<String, Object>> parentPickList = basicSQLService.find(
                            "SELECT s.id, s.`name`,s.persionId, s.team_name as teamName,pp.id as ppid," +
                                    "pp.place,pp.manual_out_operator,pp.is_bobao,pp.is_ruxiao, p.license_plate FROM bus_parent_pick pp" +
                                    " INNER JOIN pj_student s ON s.id = pp.student_id " +
                                    " inner join pj_team pt on s.team_id=pt.id " +
                                    " inner join pj_grade pg on pg.id=pt.grade_id " +
                                    "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                                    "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                                    "WHERE pp.is_ruxiao=1 and  pp.place='校门正门' and pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pg.id in(" + boBaoDaPing.getGradeIds() + ")");
                    //海康门禁刷卡记录
                    String format = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                    String hkStartTime = format + "T15:40:00+08:00";
                    String hkEndTime = format + "T19:00:00+08:00";
                    ArtemisConfig artemisConfig = new ArtemisConfig();
                    artemisConfig.setHost("10.170.75.40:443");
                    artemisConfig.setAppKey("21687113");
                    artemisConfig.setAppSecret("SD9Wldb8EKABe77COrgS");

                    if (!parentPickList.isEmpty()) {
                        List<Map<String, Object>> list = findByBoBaoTime();
                        if (!list.isEmpty()) {
                            Date date = new Date();
                            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                            for (Map<String, Object> aa : list) {
                                //开始时间
                                String str2 = aa.get("startTime").toString();
                                String startTime = str2.substring(0, str2.indexOf("T"));
                                String startTimeShiFen = str2.substring(str2.indexOf("T") + 1, str2.length());
                                //结束时间
                                String str3 = aa.get("overTime").toString();
                                String endTime = str3.substring(0, str3.indexOf("T"));
                                String endTimeShiFen = str3.substring(str3.indexOf("T") + 1, str3.length());
                                try {
                                    Date kaishi = sdf2.parse(startTime);
                                    Date jieshu = sdf2.parse(endTime);
                                    Date date5 = sdf2.parse(sdf2.format(date));
                                    //log.info("判断"+(kaishi.compareTo(date5) <= 0)+"第一"+(jieshu.compareTo(date5) >= 0));
                                    if (kaishi.compareTo(date5) <= 0 && jieshu.compareTo(date5) >= 0) {
                                        Date kaishiShiFen = sdf.parse(startTimeShiFen);
                                        Date jieshuShiFen = sdf.parse(endTimeShiFen);
                                        Date date2 = sdf.parse(sdf.format(date));
                                        // log.info("判断2"+(kaishiShiFen.compareTo(date2) < 0)+"第二"+(jieshuShiFen.compareTo(date2) > 0));
                                        if (kaishiShiFen.compareTo(date2) <= 0 && jieshuShiFen.compareTo(date2) >= 0) {
                                            for (Map<String, Object> bb : parentPickList) {
                                                if (Integer.parseInt(bb.get("is_ruxiao").toString()) == 1) {
                                                    //海康获取记录是否出校
                                                    List<String> stringList = new ArrayList<>();
                                                    if (bb.get("persionId").toString() != null) {
                                                        stringList.add(bb.get("persionId").toString());
                                                        HikDoorRequestData hikDoorRequestData = new HikDoorRequestData(hkStartTime, hkEndTime, "大门出向", 1, 1000, stringList);
                                                        String json = JSONObject.toJSONString(hikDoorRequestData);
                                                        String data = HikvisionClient.hikvisionPost(artemisConfig, "/api/acs/v2/door/events", json);
                                                        //查看出校信息
                                                        HikTeamResponseVo responseVo = JSON.parseObject(data, new TypeReference<HikTeamResponseVo>() {
                                                        });
                                                        if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
                                                            List<HikTeamResponseVo.DataBean.SuccessesBean> successesBeanList = responseVo.getData().getList();
                                                            if (successesBeanList.size() > 0) {
                                                                for (HikTeamResponseVo.DataBean.SuccessesBean bean : successesBeanList) {
                                                               /* Integer clientId = bean.getInAndOutType();
                                                                if (clientId == 0) {*/
                                                                    basicSQLService.update("update bus_parent_pick  set is_bobao=1,is_ruxiao=2 where id=" + bb.get("ppid"));
                                                             /*   } else {
                                                                    log.info("海康数据有误");
                                                                }*/
                                                                }
                                                            } else {
                                                                String bobaoHosts = "http://10.170.74.48:25012";
                                                                String banji = "";
                                                                if (bb.get("teamName").toString().substring(3, 7).equals("(10)")) {
                                                                    banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                                } else {
                                                                    banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                                }
                                                                String text = banji + "、" + banji;
                                                                String tts = null;
                                                                tts = bobaoTts(array, text, 1, bobaoHosts);
                                                                if (tts != null) {
                                                                    log.info("播报完成");
                                                                    Thread.sleep(3500);
                                                                } else {
                                                                    log.info("播报返回null");
                                                                    Thread.sleep(10000);
                                                                }

                                                            }
                                                        } else {
                                                            log.error("海康组织--海康查询门禁失败！{}", responseVo.getMsg());
                                                            Thread.sleep(5000);
                                                        }
                                                    } else {

                                                    }
                                                } else {
                                                    Thread.sleep(10000);
                                                }
                                            }
                                        } else {
                                            Thread.sleep(10000);
                                        }
                                    } else {
                                        Thread.sleep(10000);
                                    }
                                } catch (ParseException e) {
                                    Thread.sleep(10000);
                                }
                            }
                        }
                    } else {
                        log.info("暂无学生列表");
                        Thread.sleep(10000);
                    }
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                    LocalTime localTime = LocalTime.parse("20:00:00", dtf);
                    if (LocalTime.now().isAfter(localTime)) {
                        break;
                    }
                }catch (Exception e){
                    log.error("线程异常，停止10秒");
                    Thread.sleep(10000);
                }
            }
        }
    }

    /**
     * 获取每天所有学生接送信息
     * 播报大屏4
     **/
    @Scheduled(cron = "0 3 12 * * ?") //每天
    public void findByJieSong4() throws InterruptedException {
        BoBaoDaPing boBaoDaPing=boBaoDaPingService.findById(4);
        if(boBaoDaPing.getBoBaoHao()!=null) {
            String array = "[" + boBaoDaPing.getBoBaoHao() + "]";
            while (true) {
                String nowDate = SchoolBusTimeUtil.getNowDateStr();
                //当日放学全校家长接送的学生以及家长的车牌
                List<Map<String, Object>> parentPickList = basicSQLService.find(
                        "SELECT s.id, s.`name`,s.persionId, s.team_name as teamName,pp.id as ppid," +
                                "pp.place,pp.manual_out_operator,pp.is_bobao,pp.is_ruxiao, p.license_plate FROM bus_parent_pick pp" +
                                " INNER JOIN pj_student s ON s.id = pp.student_id " +
                                " inner join pj_team pt on s.team_id=pt.id " +
                                " inner join pj_grade pg on pg.id=pt.grade_id " +
                                "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                                "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 " +
                                "WHERE pp.is_ruxiao=1 and  pp.place='校门正门' and pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pg.id in(" + boBaoDaPing.getGradeIds() + ")");
                //海康门禁刷卡记录
                String format = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                String hkStartTime = format + "T15:40:00+08:00";
                String hkEndTime = format + "T19:00:00+08:00";
                ArtemisConfig artemisConfig = new ArtemisConfig();
                artemisConfig.setHost("10.170.75.40:443");
                artemisConfig.setAppKey("21687113");
                artemisConfig.setAppSecret("SD9Wldb8EKABe77COrgS");

                if (!parentPickList.isEmpty()) {
                    List<Map<String, Object>> list = findByBoBaoTime();
                    if (!list.isEmpty()) {
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        for (Map<String, Object> aa : list) {
                            //开始时间
                            String str2 = aa.get("startTime").toString();
                            String startTime = str2.substring(0, str2.indexOf("T"));
                            String startTimeShiFen = str2.substring(str2.indexOf("T") + 1, str2.length());
                            //结束时间
                            String str3 = aa.get("overTime").toString();
                            String endTime = str3.substring(0, str3.indexOf("T"));
                            String endTimeShiFen = str3.substring(str3.indexOf("T") + 1, str3.length());
                            try {
                                Date kaishi = sdf2.parse(startTime);
                                Date jieshu = sdf2.parse(endTime);
                                Date date5 = sdf2.parse(sdf2.format(date));
                                //log.info("判断"+(kaishi.compareTo(date5) <= 0)+"第一"+(jieshu.compareTo(date5) >= 0));
                                if (kaishi.compareTo(date5) <= 0 && jieshu.compareTo(date5) >= 0) {
                                    Date kaishiShiFen = sdf.parse(startTimeShiFen);
                                    Date jieshuShiFen = sdf.parse(endTimeShiFen);
                                    Date date2 = sdf.parse(sdf.format(date));
                                    // log.info("判断2"+(kaishiShiFen.compareTo(date2) < 0)+"第二"+(jieshuShiFen.compareTo(date2) > 0));
                                    if (kaishiShiFen.compareTo(date2) <= 0 && jieshuShiFen.compareTo(date2) >= 0) {
                                        for (Map<String, Object> bb : parentPickList) {
                                            if (Integer.parseInt(bb.get("is_ruxiao").toString()) == 1) {
                                                //海康获取记录是否出校
                                                List<String> stringList = new ArrayList<>();
                                                if (bb.get("persionId").toString() != null) {
                                                    stringList.add(bb.get("persionId").toString());
                                                    HikDoorRequestData hikDoorRequestData = new HikDoorRequestData(hkStartTime, hkEndTime, "大门出向", 1, 1000, stringList);
                                                    String json = JSONObject.toJSONString(hikDoorRequestData);
                                                    String data = HikvisionClient.hikvisionPost(artemisConfig, "/api/acs/v2/door/events", json);
                                                    //查看出校信息
                                                    HikTeamResponseVo responseVo = JSON.parseObject(data, new TypeReference<HikTeamResponseVo>() {
                                                    });
                                                    if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
                                                        List<HikTeamResponseVo.DataBean.SuccessesBean> successesBeanList = responseVo.getData().getList();
                                                        if (successesBeanList.size() > 0) {
                                                            for (HikTeamResponseVo.DataBean.SuccessesBean bean : successesBeanList) {
                                                                    basicSQLService.update("update bus_parent_pick  set is_bobao=1,is_ruxiao=2 where id=" + bb.get("ppid"));
                                                            }
                                                        } else {
                                                            String bobaoHosts = "http://10.170.74.48:25012";
                                                            String banji="";
                                                            if(bb.get("teamName").toString().substring(3, 7).equals("(10)")){
                                                                banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                            }else{
                                                                banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                            }
                                                            String text = banji+"、" + banji;
                                                            String tts = null;
                                                            tts = bobaoTts(array, text, 1, bobaoHosts);
                                                            if (tts != null) {
                                                                Thread.sleep(3500);
                                                            } else {
                                                                log.info("播报返回null");
                                                                Thread.sleep(10000);
                                                            }

                                                        }
                                                    } else {
                                                        log.error("海康组织--海康查询门禁失败！{}", responseVo.getMsg());
                                                        Thread.sleep(5000);
                                                    }
                                                } else {

                                                }
                                            } else {
                                                Thread.sleep(10000);
                                            }
                                        }
                                    } else {
                                        Thread.sleep(10000);
                                    }
                                } else {
                                    Thread.sleep(10000);
                                }
                            } catch (ParseException e) {
                                Thread.sleep(10000);
                            }
                        }
                    }
                } else {
                    log.info("暂无学生列表");
                    Thread.sleep(10000);
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime localTime = LocalTime.parse("20:00:00", dtf);
                if (LocalTime.now().isAfter(localTime)) {
                    break;
                }
                log.info("error");
            }
        }
    }

    /**
     * 获取每天所有学生接送信息
     * 播报大屏5
     **/
    @Scheduled(cron = "0 4 12 * * ?") //每天
    public void findByJieSong5() throws InterruptedException {
        BoBaoDaPing boBaoDaPing=boBaoDaPingService.findById(5);
        if(boBaoDaPing.getBoBaoHao()!=null){
            String array ="["+boBaoDaPing.getBoBaoHao()+"]";
            while (true){
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
                                "WHERE pp.is_ruxiao=1 and  pp.place='校门正门' and pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pg.id in("+boBaoDaPing.getGradeIds()+")");
                //海康门禁刷卡记录
                String format = String.valueOf(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
                String hkStartTime=format+"T15:40:00+08:00";
                String hkEndTime=format+"T19:00:00+08:00";
                ArtemisConfig artemisConfig = new ArtemisConfig();
                artemisConfig.setHost("10.170.75.40:443");
                artemisConfig.setAppKey("21687113");
                artemisConfig.setAppSecret("SD9Wldb8EKABe77COrgS");

                if (!parentPickList.isEmpty()) {
                    List<Map<String,Object>> list=findByBoBaoTime();
                    if(!list.isEmpty()){
                        Date date=new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                        for(Map<String,Object> aa:list){
                            //开始时间
                            String str2=aa.get("startTime").toString();
                            String startTime=str2.substring(0,str2.indexOf("T"));
                            String startTimeShiFen=str2.substring(str2.indexOf("T")+1,str2.length());
                            //结束时间
                            String str3=aa.get("overTime").toString();
                            String endTime=str3.substring(0,str3.indexOf("T"));
                            String endTimeShiFen=str3.substring(str3.indexOf("T")+1,str3.length());
                            try {
                                Date kaishi = sdf2.parse(startTime);
                                Date jieshu = sdf2.parse(endTime);
                                Date date5= sdf2.parse(sdf2.format(date));
                                //log.info("判断"+(kaishi.compareTo(date5) <= 0)+"第一"+(jieshu.compareTo(date5) >= 0));
                                if(kaishi.compareTo(date5) <= 0 && jieshu.compareTo(date5) >= 0){
                                    Date kaishiShiFen = sdf.parse(startTimeShiFen);
                                    Date jieshuShiFen = sdf.parse(endTimeShiFen);
                                    Date date2= sdf.parse(sdf.format(date));
                                    // log.info("判断2"+(kaishiShiFen.compareTo(date2) < 0)+"第二"+(jieshuShiFen.compareTo(date2) > 0));
                                    if(kaishiShiFen.compareTo(date2) <= 0 && jieshuShiFen.compareTo(date2) >= 0){
                                        for (Map<String, Object> bb : parentPickList) {
                                            if(Integer.parseInt(bb.get("is_ruxiao").toString())==1){
                                                //海康获取记录是否出校
                                                List<String> stringList=new ArrayList<>();
                                                if(bb.get("persionId").toString()!=null){
                                                    stringList.add(bb.get("persionId").toString());
                                                    HikDoorRequestData hikDoorRequestData=new HikDoorRequestData(hkStartTime,hkEndTime,"大门出向",1,1000,stringList);
                                                    String json = JSONObject.toJSONString(hikDoorRequestData);
                                                    String data = HikvisionClient.hikvisionPost(artemisConfig, "/api/acs/v2/door/events", json);
                                                    //查看出校信息
                                                    HikTeamResponseVo responseVo = JSON.parseObject(data, new TypeReference<HikTeamResponseVo>() {
                                                    });
                                                    if (responseVo.getCode().equals("0") && responseVo.getMsg().equals("success") && responseVo.getData() != null && !responseVo.getData().equals("")) {
                                                        List<HikTeamResponseVo.DataBean.SuccessesBean> successesBeanList = responseVo.getData().getList();
                                                        if(successesBeanList.size()>0){
                                                            for (HikTeamResponseVo.DataBean.SuccessesBean bean : successesBeanList) {
                                                               /* Integer clientId = bean.getInAndOutType();
                                                                if (clientId==0) {*/
                                                                    basicSQLService.update("update bus_parent_pick  set is_bobao=1,is_ruxiao=2 where id=" + bb.get("ppid"));
                                                              /*  }else{
                                                                    log.info("海康数据有误");
                                                                }*/
                                                            }
                                                        }else{
                                                            String bobaoHosts = "http://10.170.74.48:25012";
                                                            String banji="";
                                                            if(bb.get("teamName").toString().substring(3, 7).equals("(10)")){
                                                                banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                            }else{
                                                                banji = bb.get("teamName").toString().substring(0, 1) + bb.get("teamName").toString().substring(4, 6) + "班、" + bb.get("name").toString();
                                                            }
                                                            String text = banji+"、" + banji;
                                                            String tts = null;
                                                            tts = bobaoTts(array, text, 1, bobaoHosts);
                                                            if (tts != null) {
                                                                Thread.sleep(3500);
                                                            }else{
                                                                log.info("播报返回null");
                                                                Thread.sleep(10000);
                                                            }

                                                        }
                                                    } else {
                                                        log.error("海康组织--海康查询门禁失败！{}", responseVo.getMsg());
                                                        Thread.sleep(5000);
                                                    }
                                                }else{

                                                }
                                            }else{
                                                Thread.sleep(10000);
                                            }
                                        }
                                    }else{
                                        Thread.sleep(10000);
                                    }
                                }else{
                                    Thread.sleep(10000);
                                }
                            } catch (ParseException e) {
                                Thread.sleep(10000);
                            }
                        }
                    }
                }else{
                    log.info("暂无学生列表");
                    Thread.sleep(10000);
                }
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
                LocalTime localTime=LocalTime.parse("20:00:00",dtf);
                if(LocalTime.now().isAfter(localTime)){
                    break;
                }
            }
        }
    }

    //播报接口
    public String bobaoTts(String ids, String text, Integer times,String bobaoHosts) {
        try {
            JSONObject param = new JSONObject();

            param.put("text", text);
            param.put("times", times);
            param.put("deviceIDArray",ids);
            String  idss = HttpClientUtils.postJson(bobaoHosts+devicesTtsUrl,param.toJSONString());
                return idss;
        }catch (Exception e){
            return "连接有误";
        }
    }

    public List<Map<String,Object>> findByBoBaoTime(){
        String sql="select * from bobao_times where is_delete=0";
        List<Map<String,Object>> list=new ArrayList<>();
        List<Map<String,Object>>  mapList=basicSQLService.find(sql);
        for(Map<String,Object> aa:mapList){
            Map<String,Object> map=new HashMap<>();
            String str=aa.get("haoma").toString();
            String [] array=str.split(",");
            map.put("playTimeId",aa.get("id"));
            map.put("deiviceList",array);
            map.put("startTime",aa.get("start_time"));
            map.put("overTime",aa.get("end_time"));
            list.add(map);
        }
        return list;
    }
}
