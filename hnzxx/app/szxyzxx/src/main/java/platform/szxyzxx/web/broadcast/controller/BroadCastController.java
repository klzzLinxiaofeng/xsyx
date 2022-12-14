package platform.szxyzxx.web.broadcast.controller;


import cn.hutool.core.util.StrUtil;
import com.seewo.open.sdk.OpenApiRequest;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.bo.StudentInfo;
import platform.education.generalTeachingAffair.bo.StudentParentInfo;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.szxyzxx.core.service.WebApplicationContextAware;
import platform.szxyzxx.dto.seewo.SeewoOperateClient;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.card.CardApiSaveOrForceUpdateCardsRequest;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.*;
import platform.szxyzxx.schoolbus.service.CarIdentifyService;
import platform.szxyzxx.schoolbus.util.SchoolBusTimeUtil;
import platform.szxyzxx.web.schoolbus.vo.CarIdentifyVo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class BroadCastController {
    Logger log = LoggerFactory.getLogger(BroadCastController.class);
    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;
    private static Integer isSend;
    private static String url;
    private static String url2;

    private static String schoolBusModifyCard;
    private static String schoolBusModifyCard2;

    static {
        String fileName = "System.properties";
        // ??????????????????????????? ???????????????????????????????????????1:??? 0??? ??? ????????????????????????
        isSend = Integer.valueOf(PropertiesUtil.getProperty(fileName, "schoolBus.send"));
        // ???????????????
        url = PropertiesUtil.getProperty(fileName, "schoolBus.send.url");
        // ????????????
        url2 = PropertiesUtil.getProperty(fileName, "schoolBus.send.url2");

        // ??????????????????
        schoolBusModifyCard = PropertiesUtil.getProperty(fileName, "schoolBus.send.modifyCard");
        schoolBusModifyCard2 = PropertiesUtil.getProperty(fileName, "schoolBus.send.modifyCard2");
    }
    @Autowired
    private BasicSQLService basicSQLService;
    //??????????????????id
    private static String devicesIdUrl;
    //tts????????????
    private static String devicesTtsUrl;
    @Autowired
    StudentService studentService;
   /* @Autowired
    private BetterBusThirtyPartyApiService thirtyPartyApiService;*/
    static {
        devicesIdUrl = "/api/getAllDevicesID";
        devicesTtsUrl = "/api/postTTSPlayMusic";
    }

    public String[] bobaoIds() {
        String[] ids = httpService.findByids("http://10.170.74.155:25012", devicesIdUrl, devicesTtsUrl);
       // int[] array = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
        return ids;
    }

    public String bobaoTts(int[] ids, String text, Integer times,String bobaoHosts) {
        try {
            String str = httpService.findByTts(ids, text, times, bobaoHosts, devicesIdUrl, devicesTtsUrl);
            //int[] array = Arrays.stream(ids).mapToInt(Integer::parseInt).toArray();
            return str;
        }catch (Exception e){
            return "????????????";
        }
    }

     /**????????????????????????????????????
     **/

    @RequestMapping("/broadCast/bobao")
    public void findByJieSong() {
        //???????????????-???????????????
        List<Map<String, Object>> parkIn = new ArrayList<>();
        //???????????????-??????????????????????????????
        List<Map<String, Object>> parkIn2 = new ArrayList<>();
        //???????????????-???????????????
        List<Map<String, Object>> parkOut = new ArrayList<>();
 //???????????????-???????????????
        List<Map<String, Object>> gateIn = new ArrayList<>();
        //???????????????-???????????????
        List<Map<String, Object>> gateOut = new ArrayList<>();


        String nowDate = SchoolBusTimeUtil.getNowDateStr();
        //????????????????????????????????????????????????????????????
        List<Map<String, Object>> parentPickList = basicSQLService.find(
                "SELECT s.id, s.`name`,s.persionId, s.team_name as teamName,pp.id as ppid," +
                        "pp.place,pp.manual_out_operator, p.license_plate FROM bus_parent_pick pp" +
                        " INNER JOIN pj_student s ON s.id = pp.student_id " +
                        "LEFT JOIN pj_parent_student ps ON ps.student_user_id = s.user_id AND ps.is_delete = 0 AND ps.rank = 1 " +
                        "LEFT JOIN pj_parent p ON p.user_id = ps.parent_user_id AND p.is_delete = 0 WHERE pp.pick_date = '" + nowDate + "' AND pp.direction =1 and pp.is_bobao=0");
        if (parentPickList.size() == 0) {
            //return new HashMap(0);
        }
        List<String> carNoList = new ArrayList<>();
        List<String> stuUserIdList = new ArrayList<>();
        for (Map<String, Object> pp : parentPickList) {
            String carNo = (String) pp.get("license_plate");
            if (pp.get("place").equals("???????????????")) {
                if (org.apache.commons.lang3.StringUtils.isNotEmpty(carNo)) {
                    carNoList.add(carNo);
                }
            }else{
                //stuUserIdList.add(pp.get("persionId").toString());
            }
        }
        //??????????????????
        List<CarIdentifyVo> outList = getCardsMsg(1, SchoolBusTimeUtil.getDownBeginDateTime(nowDate), SchoolBusTimeUtil.getDownEndDateTime(nowDate), carNoList);
        //??????????????????
        List<CarIdentifyVo> inList = getCardsMsg(0, SchoolBusTimeUtil.getDownBeginDateTime(nowDate), SchoolBusTimeUtil.getDownEndDateTime(nowDate), carNoList);

        //??????????????????????????????
       // List<GatePickInfo> gatePickInfoList =thirtyPartyApiService.getStuPickInfo(stuUserIdList, nowDate, 1, 0);

        for (Map<String, Object> pp : parentPickList) {
            String carNo = (String) pp.get("license_plate");
            if (pp.get("place").equals("???????????????")) {
                if (!pp.get("manual_out_operator").toString().equals("0") || (org.apache.commons.lang3.StringUtils.isNotEmpty(carNo) && findCarIdentifyList(carNo, outList) != null)) {
                    //?????????
                    parkOut.add(pp);
                }
                if ((org.apache.commons.lang3.StringUtils.isNotEmpty(carNo) && findCarIdentifyList(carNo, inList) != null)) {
                    parkIn2.add(pp);
                }
            }
        }
        if (parkIn2.size() > 0) {
            List<Map<String,Object>> list=findByBoBaoTime();
            if(list.size()>0){
                Date date=new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                for(Map<String,Object> aa:list){
                    //????????????
                    String str2=aa.get("startTime").toString();
                    String startTime=str2.substring(0,str2.indexOf("T"));
                    String startTimeShiFen=str2.substring(str2.indexOf("T")+1,str2.length());
                    //????????????
                    String str3=aa.get("overTime").toString();
                    String endTime=str3.substring(0,str3.indexOf("T"));
                    String endTimeShiFen=str3.substring(str3.indexOf("T")+1,str3.length());
                    try {
                        Date kaishi = sdf2.parse(startTime);
                        Date jieshu = sdf2.parse(endTime);
                        Date date5= sdf2.parse(sdf2.format(date));
                        //log.info("??????"+(kaishi.compareTo(date5) <= 0)+"??????"+(jieshu.compareTo(date5) >= 0));
                        if(kaishi.compareTo(date5) <= 0 && jieshu.compareTo(date5) >= 0){
                            Date kaishiShiFen = sdf.parse(startTimeShiFen);
                            Date jieshuShiFen = sdf.parse(endTimeShiFen);
                            Date date2= sdf.parse(sdf.format(date));
                           // log.info("??????2"+(kaishiShiFen.compareTo(date2) < 0)+"??????"+(jieshuShiFen.compareTo(date2) > 0));
                            if(kaishiShiFen.compareTo(date2) <= 0 && jieshuShiFen.compareTo(date2) >= 0){
                                 String [] str= (String[]) aa.get("deiviceList");
                                int [] array = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
                              //  log.info("a"+array.toString());
                                for (Map<String, Object> bb : parkIn2) {
                                    //10330,10379,10380,10381    10.191.109.223
                                    String  bobaoHosts = "http://10.170.74.155:25012";
                                    String text = bb.get("teamName") + "???" + bb.get("name") + "??????,"+bb.get("teamName") + "???" + bb.get("name") + "??????,???????????????" + bb.get("place") + "??????";
                                    String tts = null;
                                    tts  = bobaoTts(array, text, 1,bobaoHosts);
                                    if(tts!=null){
                                        basicSQLService.update("update bus_parent_pick  set is_bobao=1 where id="+aa.get("ppid"));
                                    }
                                }

                            }
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        }else{
          //  log.info("????????????");
        }
    }



   /* private GatePickInfo findGateInfoByUserId(String userId, List<GatePickInfo> list) {
        List<GatePickInfo> qualifierList = new ArrayList<>(0);
        if (list != null) {
            //?????????????????????
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
*/
    private CarIdentifyVo findCarIdentifyList(String carNo, List<CarIdentifyVo> list) {
        for (CarIdentifyVo carIdentifyVo : list) {
            if (carNo.contains(carIdentifyVo.getCarno())) {
                return carIdentifyVo;
            }
        }
        return null;
    }

    @Autowired
    @Qualifier("carNamedParameterJdbcTemplate")
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


/**
     * ??????????????????
     * @param type      type 0:??? ???1??????
     * @param beginDate ????????????
     * @param endDate   ????????????
     * @param cars     ??????
     * @return*/


    public List<CarIdentifyVo> getCardsMsg(Integer type, String beginDate, String endDate, List<String> cars) {
        if(cars==null || cars.size()==0){
            return new ArrayList<>(0);
        }
        Map<String, Object> params = new HashMap<>(3);
        StringBuilder sql = new StringBuilder("select carno");
        Date date=new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 23:59:59");
        SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
        if (type == 0) {
            sql.append(",intime time from t_carin where intime");
        } else {
            sql.append(",outtime time from t_carout where outtime");
        }

        sql.append(" between'" +formatter2.format(date)+ "'and'"+formatter.format(date)+"' and carno in ( "+join(cars)+" )");

        RowMapper<CarIdentifyVo> rowMapper = new BeanPropertyRowMapper<>(CarIdentifyVo.class);
        List<CarIdentifyVo> list = null;
        try {
            list = namedParameterJdbcTemplate.query(sql.toString(),rowMapper);
        } catch (DataAccessException e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        }
        return list;
    }


    private String join(List<String> list){
        StringBuilder sb=new StringBuilder();
        for (String  s: list) {
            if(sb.length()!=0){
                sb.append(",");
            }
            sb.append("'").append(s.replace(",","','")).append("'");
        }
        return sb.toString();
    }


    @Scheduled(fixedDelay = 15000)//??????????????????
    public void test()
    {
        findByJieSong();
    }
    /*
    * ????????????
    **/
    @Scheduled(cron ="0 0 10,22 * * ?")//??????12??? 22???????????????
    public void test2()
    {
        jobMethod();
    }
    @Scheduled(cron ="0 0 11,23 * * ?")//??????11??? 22???????????????
    public void test3()
    {
        jobMethod2();
    }
    /*
    *????????????????????????*/
    @Scheduled(cron ="0 0 20 * * ?")//??????9??? 20???????????????
    public void test4() {
        syncData();;
    }
    /*
     *????????????????????????*/
    @Scheduled(cron ="0 0 21 * * ?")//??????9??? 20???????????????
    public void test5() {
        syncData2();
    }
    /*
    * ????????????????????????????????????????????????????????????????????????????????????
    **/
    @Scheduled(cron ="0 0/5 17-23 * * ?")//
    public void homeWoKe() {
       List<Map<String,Object>> object=basicSQLService.find("select * from pj_homewoke_settime where is_delete=0 and school_id=215");
        DateFormat format=new SimpleDateFormat("HH:mm:ss");
        String dates=format.format(format.format(new Date()));
        if(compTime(object.get(0).get("datetime").toString(),dates)){
            createHomeAll();
        }
    }


    /**
     * ?????????????????? ????????? ??????
     * @param timedate
     * @param nowdate
     * @return
     */
    public  boolean compTime(String timedate,String nowdate){
        try {
            if (timedate.indexOf(":")<0||timedate.indexOf(":")<0) {
                log.error("???????????????");
            }else{
                String[]array1 = timedate.split(":");
                int total1 = Integer.valueOf(array1[0])*3600+Integer.valueOf(array1[1])*60+Integer.valueOf(array1[2]);
                String[]array2 = nowdate.split(":");
                int total2 = Integer.valueOf(array2[0])*3600+Integer.valueOf(array2[1])*60+Integer.valueOf(array2[2]);
                return total1-total2<=0?true:false;
            }
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block;
            log.error("????????????");
        }
        return false;

    }

    /**
     *????????????????????????????????????????????????????????????????????????
     * ????????????????????????15-17?????????1??????????????????
     */
    @Scheduled(cron ="0 0/1 15-17 ? * 2-6")
    public void task(){
        try {

            log.debug("??????????????????????????????");

            Calendar now=Calendar.getInstance();
            now.setTime(new Date());
//            if(now.get(Calendar.HOUR_OF_DAY)<15){
//                return;
//            }

            if(WebApplicationContextAware.applicationContext==null){
                log.error("ApplicationContext???????????????????????????????????????");
                return;
            }

            CarIdentifyService carIdentifyService=WebApplicationContextAware.applicationContext.getBean(CarIdentifyService.class);
            BasicSQLService basicSQLService= WebApplicationContextAware.applicationContext.getBean(BasicSQLService.class);
            if(basicSQLService==null || carIdentifyService==null ){
                log.error("ApplicationContext?????????service???????????????????????????????????????");
                return;
            }
            //????????????
            String nowSchoolYear= basicSQLService.getNowSchoolYear();

            //?????????????????????????????????????????????
            List<Map<String,Object>> stuCarNoList=basicSQLService.find("SELECT s.id as stu_id,p.license_plate AS parent_car FROM pj_parent_student ps INNER JOIN pj_parent p ON ps.parent_user_id = p.user_id AND p.is_delete = 0 INNER JOIN pj_student s ON s.user_id = ps.student_user_id AND s.is_delete = 0 inner JOIN pj_team_student pts ON s.id = pts.student_id and pts.is_delete=0 INNER JOIN pj_team t ON t.id = pts.team_id WHERE p.license_plate IS NOT NULL AND t.school_year = '"+nowSchoolYear+"' and ps.rank=1 AND ps.is_delete = 0 and ps.rank=1");

            if(stuCarNoList==null || stuCarNoList.size()==0){
                log.debug("????????????????????????????????????");
                return;
            }
            List<String> carNoList=getAllKeyVal(stuCarNoList,"parent_car");

            String nowDate=SchoolBusTimeUtil.getNowDateStr();
            //??????????????????????????????
            List<CarIdentifyVo> carList=carIdentifyService.getCardsMsg(0,nowDate+" 15:00:00",nowDate+" 19:00:00",carNoList);

            if(carList==null || carList.size()==0){
                log.debug("?????????????????????????????????????????????");
                return;
            }
            //??????????????????????????????
            List<Map<String,Object>> parentPickList=basicSQLService.find("select student_id from bus_parent_pick where pick_date='"+nowDate+"' and direction=1");
            int count=0;
            for (CarIdentifyVo carIdentifyVo : carList) {
                //????????????????????????
                //?????????????????????????????????????????????id?????????????????????????????????????????????id
                List<String> stuIds= findPickedStuIdIfNoParent(parentPickList,stuCarNoList,carIdentifyVo.getCarno());
                if(stuIds.size()>0){
                    for (String stuId : stuIds) {
                        //????????????????????????
                        basicSQLService.update("insert into bus_parent_pick(student_id,pick_date,direction,place,create_type) values("+stuId+",'"+nowDate+"',1,'???????????????',1)");
                        //??????????????????????????????????????????????????????????????????????????????????????????,??????????????????????????????????????????????????????
                        Map<String,Object> picked=new HashMap<>(2,1);
                        picked.put("student_id",stuId);
                        parentPickList.add(picked);
                        count++;
                    }
                }
            }
            log.debug("???????????????????????????"+count);
        } catch (BeansException e) {
            e.printStackTrace();
            log.error("????????????????????????");
        }

    }

    private  String [] arr=new String[]{"??????","??????","??????"};
    /*
    * ????????????????????????????????????
    **/
    @RequestMapping("/addHomeWoke")
    public String createHomeAll() {
        log.info("????????????--???????????????????????????????????????????????????----??????");
        //????????????????????????????????????id
      List<Map<String,Object>> obj=basicSQLService.find("select * from pj_school_term_current where school_id=215");
         Integer schoolId=Integer.parseInt(obj.get(0).get("school_id").toString());
         String schoolTrem=obj.get(0).get("school_term_code").toString();
        String schoolYear=obj.get(0).get("school_year").toString();
        Date date = new Date();
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        //????????????
       List<Map<String, Object>> team=basicSQLService.find(" select pt.* from   pj_team pt  inner join pj_grade pg on pt.grade_id=pg.id where    pt.is_delete=0 and pt.school_id=215 and pg.school_year=2021 and pg.name in('?????????','?????????','?????????','?????????','?????????','?????????')    group by pt.id;\n");
       for(Map<String, Object> aa:team){
           Integer teamId=Integer.parseInt(aa.get("id").toString());
           for(int a=0;a<arr.length;a++){

               if(a==2){
                   String grade=aa.get("name").toString().substring(0,3);
                   if(grade.equals("?????????") || grade.equals("?????????")){
                       continue ;
                   }
               }
               String sql="select pjc.* from pj_publish_job_content pjc " +
                       " inner join pj_subject ps on ps.id=pjc.subject_id " +
                       " where pjc.school_id="+schoolId+" and pjc.team_id="+teamId+
                       " and ps.name='"+arr[a]+"' and pjc.school_trem='"+schoolTrem+"' " +
                       " and pjc.school_year="+schoolYear+" and  pjc.create_date like '%"+format.format(date)+"%'";
               //?????????????????????????????????????????????????????????????????????
               List<Map<String, Object>> homeWork=basicSQLService.find(sql);

               if(homeWork.size()>0){

               }else{
                   Integer gardeId=Integer.parseInt(aa.get("grade_id").toString());
                   List<Map<String, Object>> subject=basicSQLService.find("select * from pj_subject where is_delete=0 and school_id="+schoolId+" and name like'%"+arr[a]+"%'");
                  int subjectId=Integer.parseInt(subject.get(0).get("id").toString());
                   String sqlcr="insert into pj_publish_job_content(teacher_id,subject_id," +
                           "school_id,picture_uuid,content,team_id,create_date,modify_date," +
                           "student_id,is_delete,is_stats,is_home,grade_id,school_trem,school_year)" +
                           " value(null,"+subjectId+","+schoolId+",null,'???????????????',"+teamId+",NOW(),NOW(),null,0,0,1,"+gardeId+",'"+schoolTrem+"',"+schoolYear+")";
                 Integer num= basicSQLService.create(sqlcr);
                 log.info("??????????????????",num);
               }
           }
       }
        return null;
    }


/**
     * 1.??????????????????????????????
     * 2.???????????????????????????
     * 2021???12???31???zy????????????????????????????????????????????????quartz??????????????????
     */

    @RequestMapping("/broadCast/xiaoche")
    public void jobMethod() {
        log.info("???????????????????????????????????????..., ????????????: {}", isSend);
        if (isSend == 1 && StrUtil.isNotEmpty(url) && StrUtil.isNotEmpty(url2)) {
            httpService.syncSendShoolBusStu(url, url2);
        } else {
            log.error("?????????????????????????????????????????????????????????????????????");
        }
    }
    @RequestMapping("/broadCast/xiaoche/buka")
    public void jobMethod2() {
        // ???????????????????????????
        log.info("???????????????????????????????????????");
        // ?????????????????????????????????????????????
        if (StrUtil.isNotEmpty(schoolBusModifyCard) && StrUtil.isNotEmpty(schoolBusModifyCard2)) {
            // ??????????????????????????????????????????????????????????????????
            httpService.modifyCardSchoolBus(schoolBusModifyCard, schoolBusModifyCard2);
        } else {
            log.error("?????????????????????????????????????????????????????????????????????");
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
       log.info("??????????????????"+list.size());
       return list;
    }

///-----------------------????????????-----------------
    @RequestMapping("/broadCast/Seoow/teacher")
    public void syncData() {
        log.info("?????????????????????????????????");
        List<Map<String,Object>> plist=basicSQLService.find("SELECT t.mobile,t.`name`,t.emp_card FROM `pj_teacher` t inner join yh_user u on u.id=t.user_id where t.is_delete=0 and u.is_deleted=0 and u.`state`=0 and t.mobile is not null and t.mobile!=''  and t.school_id=215");
        log.info("??????????????????:"+plist.size());
        List<Map<String,Object>> slist=queryAll();
        log.info("?????????????????????"+slist.size());
        List<Map<String,Object>> addList=getAddList(plist,slist);
        log.info("??????????????????????????????"+addList.size());
        List<Map<String,Object>> delList=getDeleteList(plist,slist);
        log.info("??????????????????????????????"+delList.size());
        if(addList.size()>0) {
            addAll(addList);
        }
        if(delList.size()>0) {
            delete(delList);
        }
        log.info("????????????????????????");
        updateAllCarNo(plist);
        log.info("????????????????????????");
    }
    private static final String SEEWO_PROPERTY_NAME = "seewo.properties";
    private static final String SCHOOL_UID= PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.schoolUid");

    private static final String APP_ID = PropertiesUtil.getProperty(SEEWO_PROPERTY_NAME,"seewo.appid");

    public static   String getSchoolUid(){
        return SCHOOL_UID;
    }
    public static  String getAppId(){
        return APP_ID;
    }

    protected List<Map<String,Object>> getAddList(List<Map<String,Object>> platformList,List<Map<String,Object>> seewoList){
        List<Map<String,Object>> addList=new ArrayList<>(0);
        for (Map<String,Object> p : platformList) {
            if(findSeewoObjByPlatform(seewoList,p)==null){
                addList.add(p);
            }
        }
        return addList;
    }

    protected List<Map<String,Object>> getDeleteList(List<Map<String,Object>> platformList,List<Map<String,Object>> seewoList){
        List<Map<String,Object>> deleteList=new ArrayList<>(0);
        for (Map<String,Object> s : seewoList) {
            if(findPlatformObjBySeewo(platformList,s)==null){
                deleteList.add(s);
            }
        }
        return deleteList;
    }
    private Map<String,Object> findSeewoObjByPlatform(List<Map<String,Object>> list, Map<String,Object> p){
        for (Map<String,Object> s : list) {
            if(pkEqual(p,s)){
                return s;
            }
        }
        return null;
    }
    private Map<String,Object> findPlatformObjBySeewo(List<Map<String,Object>> list, Map<String,Object> s){
        for (Map<String,Object> p : list) {
            if(pkEqual(p,s)){
                return p;
            }
        }
        return null;
    }

    /**
     * ????????????????????????????????????
     */
    boolean pkEqual(Map<String,Object> platformObj, Map<String, Object> seewoObj) {
        return platformObj.get("mobile").toString().equals(seewoObj.get("phone").toString());
    }

    protected OpenApiRequest createQueryAllRequest() {
        TeacherApiListBySchoolUidParam param = new TeacherApiListBySchoolUidParam();
        TeacherApiListBySchoolUidParam.JSONRequestBody requestBody = TeacherApiListBySchoolUidParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        TeacherApiListBySchoolUidParam.MisThirdTeacherQueryDto query = TeacherApiListBySchoolUidParam.MisThirdTeacherQueryDto.builder()
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return new TeacherApiListBySchoolUidRequest(param);
    }
    protected OpenApiRequest createDeleteRequest(List<Map<String, Object>> delList) {

        List<String> mobiles=new ArrayList<>(delList.size());
        for (Map<String, Object> map : delList) {
            mobiles.add(map.get("phone").toString());
        }

        TeacherApiDeleteTeachersParam param = new TeacherApiDeleteTeachersParam();
        TeacherApiDeleteTeachersParam.JSONRequestBody requestBody = TeacherApiDeleteTeachersParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        TeacherApiDeleteTeachersParam.MisThirdTeacherQueryDto query = TeacherApiDeleteTeachersParam.MisThirdTeacherQueryDto.builder()
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .phones(mobiles)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return new TeacherApiDeleteTeachersRequest(param);
    }

    public BasicResponseResult addAll(List<Map<String,Object>> list) {
        if(list.size()>100){
            List<Map<String,Object>> oneBatch=new ArrayList<>(100);
            for (int i = 1; i <= list.size(); i++) {
                oneBatch.add(list.get(i-1));
                if(i%100==0){
                    simpleAddAll(oneBatch);
                    oneBatch=new ArrayList<>(100);
                }
            }
            if(oneBatch.size()>0){
                simpleAddAll(oneBatch);
            }
        }else{
            simpleAddAll(list);
        }

        return null;
    }

    private BasicResponseResult simpleAddAll(List<Map<String, Object>> list) {
        TeacherServiceBatchSaveOrUpdateTeacherParam param = new TeacherServiceBatchSaveOrUpdateTeacherParam();
        TeacherServiceBatchSaveOrUpdateTeacherParam.RequestBody requestBody = TeacherServiceBatchSaveOrUpdateTeacherParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        TeacherServiceBatchSaveOrUpdateTeacherParam.Query query = TeacherServiceBatchSaveOrUpdateTeacherParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        List<TeacherServiceBatchSaveOrUpdateTeacherParam.Teachers> teachersList=new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            TeacherServiceBatchSaveOrUpdateTeacherParam.Teachers teachers = TeacherServiceBatchSaveOrUpdateTeacherParam.Teachers.builder()
                    .account(map.get("mobile").toString())
                    .name(map.get("name").toString())
                    .accountType("phone")
                    .build();
            teachersList.add(teachers);
        }
        query.setTeachers(teachersList);
        param.setRequestBody(requestBody);
        return SeewoOperateClient.invoke(new TeacherServiceBatchSaveOrUpdateTeacherRequest(param));
    }





    public void updateAllCarNo(List<Map<String,Object>> list) {
        CardApiSaveOrForceUpdateCardsParam param = new CardApiSaveOrForceUpdateCardsParam();
        CardApiSaveOrForceUpdateCardsParam.RequestBody requestBody = CardApiSaveOrForceUpdateCardsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        CardApiSaveOrForceUpdateCardsParam.Object query = CardApiSaveOrForceUpdateCardsParam.Object.builder()
                .appId(getAppId())
                .userType("1")
                .type("0")
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        List<CardApiSaveOrForceUpdateCardsParam.CardsItem> cardsItems=new ArrayList<>(list.size());
        for (Map<String,Object> map : list) {
            String empCard=(String) map.get("emp_card");
            if(StringUtils.isEmpty(empCard) || empCard.equals("0")){
                continue;
            }
            CardApiSaveOrForceUpdateCardsParam.CardsItem cards = CardApiSaveOrForceUpdateCardsParam.CardsItem.builder()
                    .number(map.get("mobile").toString())
                    .cardId(empCard)
                    .name(map.get("name").toString())
                    .build();
            cardsItems.add(cards);
        }
        query.setCards(cardsItems);
        param.setRequestBody(requestBody);
        SeewoOperateClient.invoke(new CardApiSaveOrForceUpdateCardsRequest(param));
    }


    public List<Map<String, Object>> queryAll() {
        return SeewoOperateClient.invoke(createQueryAllRequest()).getPagingResult();
    }

    public BasicResponseResult delete(List<Map<String, Object>> queriedDelList) {
        return SeewoOperateClient.invoke(createDeleteRequest(queriedDelList));
    }


///-----------------------????????????end ??????start-----------------
@RequestMapping("/broadCast/Seoow/student")
    public void syncData2() {
        log.info("?????????????????????????????????");
        String nowSchoolYear=basicSQLService.getNowSchoolYear();
        List<StudentInfo> allStudent=studentService.findSendSeewoStu(nowSchoolYear);
        List<Map<String,Object>> allSeewoTeam=queryAll2();
        for (Map<String, Object> seewoTeam : allSeewoTeam) {
            String classUid=(String) seewoTeam.get("uid");
            log.info("?????????????????????"+seewoTeam.get("nickName"));
            List<StudentInfo> teamStuList=findStuListBy(seewoTeam.get("grade").toString(),seewoTeam.get("clazz").toString(),allStudent);
            log.info("????????????????????????"+teamStuList.size());
            List<Map<String,Object>> seewoTeamStuList=queryByClassUid(seewoTeam.get("uid").toString());
            log.info("????????????????????????"+seewoTeamStuList.size());
            List<Map<String,Object>> deleteList=getDeleteList2(teamStuList,seewoTeamStuList);
            if(deleteList.size()>0){
                log.info("??????????????????????????????"+deleteList.size());
                deleteAll(deleteList,classUid);
            }
            List<StudentInfo> addList=getAddList2(teamStuList,seewoTeamStuList);
            if(addList.size()>0){
                log.info("??????????????????????????????"+addList.size());
                addAll(addList,classUid);
            }
            List<Map<String,Object>> updateCarList=getUpdateList2(teamStuList,seewoTeamStuList);
            if(updateCarList.size()>0){
                log.info("????????????????????????????????????"+updateCarList.size());
               updateCarNo(updateCarList);
            }
        }

    }
    public List<Map<String, Object>> queryAll2() {
        return SeewoOperateClient.invoke(createQueryAllRequest2()).getPagingResult();
    }
    public OpenApiRequest createQueryAllRequest2() {
        ClassApiQueryClassDetailBySchoolUidParam param = new ClassApiQueryClassDetailBySchoolUidParam();
        ClassApiQueryClassDetailBySchoolUidParam.JSONRequestBody requestBody = ClassApiQueryClassDetailBySchoolUidParam.JSONRequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        ClassApiQueryClassDetailBySchoolUidParam.MisThirdClassQueryDto query = ClassApiQueryClassDetailBySchoolUidParam.MisThirdClassQueryDto.builder()
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return  new ClassApiQueryClassDetailBySchoolUidRequest(param);
    }
    private List<StudentInfo> findStuListBy(String gradeNum,String teamNum,List<StudentInfo> list){
        List<StudentInfo> quilfierList=new ArrayList<>(20);
        for (StudentInfo stu : list) {
            if(stu.getGradeNumber().toString().equals(gradeNum) && stu.getTeamNumber().toString().equals(teamNum)){
                quilfierList.add(stu);
            }
        }
        return quilfierList;
    }

    protected List<StudentInfo> getAddList2(List<StudentInfo> platformList,List<Map<String,Object>> seewoList){
        List<StudentInfo> addList=new ArrayList<>(0);
        for (StudentInfo p : platformList) {
            if(findSeewoObjByPlatform2(seewoList,p)==null){
                addList.add(p);
            }
        }
        return addList;
    }

    protected List<Map<String,Object>> getUpdateList2( List<StudentInfo> platformList,List<Map<String,Object>> seewoList){
        List<Map<String,Object>> updateList=new ArrayList<>(0);
        for (StudentInfo p : platformList) {
            Map<String,Object> s=findSeewoObjByPlatform2(seewoList,p);
            if(s!=null && needUpdateSeewoObj(p,s)){
                updateList.add(s);
            }
        }
        return updateList;
    }
    protected List<Map<String,Object>> getDeleteList2(List<StudentInfo> platformList,List<Map<String,Object>> seewoList){
        List<Map<String,Object>> deleteList=new ArrayList<>(0);
        for (Map<String,Object> s : seewoList) {
            if(findPlatformObjBySeewo2(platformList,s)==null){
                deleteList.add(s);
            }
        }
        return deleteList;
    }
    private Map<String,Object> findSeewoObjByPlatform2(List<Map<String,Object>> list, StudentInfo p){
        for (Map<String,Object> s : list) {
            if(pkEqual2(p,s)){
                return s;
            }
        }
        return null;
    }
    private StudentInfo findPlatformObjBySeewo2(List<StudentInfo> list, Map<String,Object> s){
        for (StudentInfo p : list) {
            if(pkEqual2(p,s)){
                return p;
            }
        }
        return null;
    }


    public List<Map<String, Object>> queryByClassUid(String classUid){
        StudentServiceListStudentByClassParam param = new StudentServiceListStudentByClassParam();
        StudentServiceListStudentByClassParam.RequestBody requestBody = StudentServiceListStudentByClassParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        StudentServiceListStudentByClassParam.Query query = StudentServiceListStudentByClassParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .classUid(classUid)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        return (List<Map<String, Object>>) SeewoOperateClient.invoke(new StudentServiceListStudentByClassRequest(param)).getData();
    }

    public BasicResponseResult addAll(List<StudentInfo> list, String classUid) {
        StudentServiceBatchSaveClassStudentsParam param = new StudentServiceBatchSaveClassStudentsParam();
        StudentServiceBatchSaveClassStudentsParam.RequestBody requestBody = StudentServiceBatchSaveClassStudentsParam.RequestBody.builder().build();
        param.setRequestBody(requestBody);
        StudentServiceBatchSaveClassStudentsParam.Query query = StudentServiceBatchSaveClassStudentsParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .classUid(classUid)
                .build();
        requestBody.setQuery(query);
        param.setRequestBody(requestBody);
        List<StudentServiceBatchSaveClassStudentsParam.StudentsItem> students=new ArrayList<>();
        for(int i=1;i<=list.size();i++){
            StudentInfo  stu=list.get(i-1);
            String empCar=stu.getCardCode();
            StudentServiceBatchSaveClassStudentsParam.StudentsItem.StudentsItemBuilder builder=StudentServiceBatchSaveClassStudentsParam.StudentsItem.builder()
                    .studentName(stu.getStudentName())
                    .studentCode(stu.getStudentCode().toString());
            if(StringUtils.isNotEmpty(empCar) && !empCar.equals("0") ){
                builder.cardNo(empCar);
            }
            //?????????????????????100???
            StudentServiceBatchSaveClassStudentsParam.StudentsItem student =builder.build();
            students.add(student);
            if(i%100==0){
                query.setStudents(students);
                SeewoOperateClient.invoke(new StudentServiceBatchSaveClassStudentsRequest(param));
                students=new ArrayList<>();
            }
        }
        if(students.size()>0){
            query.setStudents(students);
            SeewoOperateClient.invoke(new StudentServiceBatchSaveClassStudentsRequest(param));
        }


        //??????????????????
        addParents(list);


        return null;
    }
    private void addParents(List<StudentInfo> list){

        ParentServiceBatchSaveOrUpdateParentsParam param = new ParentServiceBatchSaveOrUpdateParentsParam();
        ParentServiceBatchSaveOrUpdateParentsParam.RequestBody requestBody = ParentServiceBatchSaveOrUpdateParentsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        ParentServiceBatchSaveOrUpdateParentsParam.Query query = ParentServiceBatchSaveOrUpdateParentsParam.Query.builder()
                .appId(getAppId())
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        //????????????????????????????????????100???
        List<ParentServiceBatchSaveOrUpdateParentsParam.StudentParentsItem> studentParents=new ArrayList<>(50);

        for(StudentInfo stu:list){
            List<StudentParentInfo> parentList=stu.getParentList();
            if(parentList==null ||parentList.size()==0){
                continue;
            }
            ParentServiceBatchSaveOrUpdateParentsParam.StudentParentsItem studentParent = ParentServiceBatchSaveOrUpdateParentsParam.StudentParentsItem.builder()
                    .studentCode(stu.getStudentCode().toString())
                    .build();


            int parentEndIndex=parentList.size();
            if(parentEndIndex>4){
                parentEndIndex=4;
            }
            List<ParentServiceBatchSaveOrUpdateParentsParam.ParentsItem> parents=new ArrayList<>(parentEndIndex);
            //?????????????????????4???
            for(int j=0;j<parentEndIndex;j++){
                StudentParentInfo parentInfo=parentList.get(j);
                ParentServiceBatchSaveOrUpdateParentsParam.ParentsItem parent = ParentServiceBatchSaveOrUpdateParentsParam.ParentsItem.builder()
                        .name(parentInfo.getParentName())
                        .phone(parentInfo.getParentPhone())
                        .index(j)
                        .build();
                parents.add(parent);
            }
            studentParent.setParents(parents);
            studentParents.add(studentParent);
            if(studentParents.size()>=100){
                query.setStudentParents(studentParents);
                SeewoOperateClient.invoke(new ParentServiceBatchSaveOrUpdateParentsRequest(param));
                studentParents=new ArrayList<>();
            }
        }

        if(studentParents.size()>0){
            query.setStudentParents(studentParents);
            SeewoOperateClient.invoke(new ParentServiceBatchSaveOrUpdateParentsRequest(param));
        }

    }


    public BasicResponseResult deleteAll(List<Map<String, Object>> seewoQueryList,String classUid) {
        StudentApiUnbindStudentToClassParam param = new StudentApiUnbindStudentToClassParam();
        StudentApiUnbindStudentToClassParam.JSONRequestBody requestBody = StudentApiUnbindStudentToClassParam.JSONRequestBody.builder().build();
        param.setRequestBody(requestBody);
        List<String> stuCodes=getAllStringVal("studentCode",seewoQueryList);
        StudentApiUnbindStudentToClassParam.MisThirdStudentQueryDto query = StudentApiUnbindStudentToClassParam.MisThirdStudentQueryDto.builder()
                //.classId("classId")
                .seewoClassId(classUid)
                .studentCodes(stuCodes)
                .schoolUid(getSchoolUid())
                .appId(getAppId())
                .build();
        requestBody.setQuery(query);
        return SeewoOperateClient.invoke(new StudentApiUnbindStudentToClassRequest(param));
    }
    private List<String> getAllStringVal(String key,List<Map<String,Object>> list){
        List<String> valList=new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            valList.add(map.get(key).toString());
        }
        return valList;
    }

    public BasicResponseResult updateCarNo(List<Map<String,Object>> seewoQueryList) {

        CardApiSaveOrForceUpdateCardsParam param = new CardApiSaveOrForceUpdateCardsParam();
        CardApiSaveOrForceUpdateCardsParam.RequestBody requestBody = CardApiSaveOrForceUpdateCardsParam.RequestBody.builder()
                .build();
        param.setRequestBody(requestBody);
        CardApiSaveOrForceUpdateCardsParam.Object query = CardApiSaveOrForceUpdateCardsParam.Object.builder()
                .appId(getAppId())
                .userType("0")
                .type("0")
                .schoolUid(getSchoolUid())
                .build();
        requestBody.setQuery(query);
        List<CardApiSaveOrForceUpdateCardsParam.CardsItem> cards=new ArrayList<>(seewoQueryList.size());
        for (Map<String, Object> map : seewoQueryList) {
            CardApiSaveOrForceUpdateCardsParam.CardsItem card = CardApiSaveOrForceUpdateCardsParam.CardsItem.builder()
                    .number(map.get("studentCode").toString())
                    .cardId(map.get("updateCarId").toString())
                    .name(map.get("userName").toString())
                    .build();
            cards.add(card);
        }

        query.setCards(cards);
        param.setRequestBody(requestBody);
        return SeewoOperateClient.invoke(new CardApiSaveOrForceUpdateCardsRequest(param));
    }
    boolean pkEqual2(StudentInfo platformObj, Map<String, Object> seewoObj) {
        return platformObj.getStudentCode().toString().equals(seewoObj.get("studentCode"));
    }

    boolean needUpdateSeewoObj(StudentInfo platformObj, Map<String, Object> seewoObj) {
        String empCard=platformObj.getCardCode();
        if( empCard!=null && !empCard.equals(seewoObj.get("cardId"))){
            seewoObj.put("updateCarId",empCard);
            return true;
        }
        return false;
    }


    private List<String> getAllKeyVal(List<Map<String,Object>> list,String key){
        List<String> valList=new ArrayList<>(list.size());
        for (Map<String, Object> map : list) {
            valList.add(map.get(key).toString());
        }
        return valList;
    }


    private List<String>  findPickedStuIdIfNoParent(List<Map<String,Object>> parentPickList, List<Map<String,Object>> stuCarNoList, String parentCarNo){
        List<String> stuIds=findStuIdByCarNo(stuCarNoList,parentCarNo);
        if(stuIds.size()>0){
            for (Map<String, Object> pickMap : parentPickList) {
                String existId=pickMap.get("student_id").toString();
                if(stuIds.contains(existId)){
                    stuIds.remove(existId);
                }
            }
        }
        return stuIds;
    }

    private List<String> findStuIdByCarNo(List<Map<String,Object>> list,String parentCarNo){
        //??????????????????????????????????????????
        List<String> carStuIds=new ArrayList<>(0);
        for (Map<String, Object> map : list) {
            if(map.get("parent_car").toString().contains(parentCarNo)){
                carStuIds.add( map.get("stu_id").toString());
            }
        }
        return carStuIds;
    }

}
