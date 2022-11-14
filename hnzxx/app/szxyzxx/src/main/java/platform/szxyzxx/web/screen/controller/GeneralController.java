package platform.szxyzxx.web.screen.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import platform.education.generalTeachingAffair.model.AmountTrendPojo;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.GeneralService;
import platform.education.generalTeachingAffair.service.HttpService;
import platform.service.storage.service.FileService;
import platform.szxyzxx.dto.seewo.SeewoOperateClient;
import platform.szxyzxx.dto.seewo.pojo.BasicResponseResult;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.AttendanceServiceListRecordsByUserDuringDateParam;
import platform.szxyzxx.dto.seewo.pojo.seewo.next.AttendanceServiceListRecordsByUserDuringDateRequest;
import platform.szxyzxx.schoolbus.pojo.GatePickInfo;
import platform.szxyzxx.schoolbus.service.BetterBusThirtyPartyApiService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.screen.vo.CountElement;
import platform.szxyzxx.web.screen.vo.SumMoneyVo;
import platform.szxyzxx.web.teach.util.PropertiesUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 总务大数据大屏展示
 *
 * @author: yhc
 * @Date: 2020/12/6 10:06
 * @Description:
 */
@RestController
@RequestMapping("/general")
public class GeneralController {

    @Autowired
    private GeneralService generalService;

    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    private BetterBusThirtyPartyApiService betterBusThirtyPartyApiService;

    @Autowired
    private FileService fileService;

    @Autowired
    @Qualifier("httpService")
    private HttpService httpService;

    private static final Integer schoolIds;
    private static String canteenUrl;
    private static String SchoolDaySumMoney;
    private static String SchoolDaySumMoneyList;
    private static String SchoolDayHotelCodePoint;

    static {
        schoolIds = Integer.valueOf(PropertiesUtil.getProperty("System.properties", "schoolId"));
        String fileName = "System.properties";
        // 食堂接口查询卡号信息
        canteenUrl = PropertiesUtil.getProperty(fileName, "canteen.server.address");
        SchoolDaySumMoney = PropertiesUtil.getProperty(fileName, "canteen.user.SchoolDaySumMoney");
        SchoolDaySumMoneyList = PropertiesUtil.getProperty(fileName, "canteen.user.SchoolDaySumMoneyList");
        SchoolDayHotelCodePoint = PropertiesUtil.getProperty(fileName, "canteen.user.SchoolDayHotelCodePoint");
    }

    /**
     * 获取当日充值总金额
     *
     * @return
     */
    @RequestMapping("/getSumAmount")
    public ResponseInfomation getSumAmount(@CurrentUser UserInfo user) {
        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
        BigDecimal amount = generalService.findAmount(schoolId);
        return new ResponseInfomation(amount, ResponseInfomation.OPERATION_SUC);
    }

    /**
     * 日充值趋势图
     *
     * @return
     */
    @RequestMapping("/amountTrend")
    public ResponseInfomation getamountTrend(@CurrentUser UserInfo user, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate) {
        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
        List<AmountTrendPojo> amountTrendPojoList = generalService.findSumAmount(schoolId, beginDate, endDate);
        return new ResponseInfomation(amountTrendPojoList, ResponseInfomation.OPERATION_SUC);
    }


    /**
     * 补卡人数统计（未处理）
     *
     * @return
     */
    @RequestMapping("/replaceCardTotal")
    public ResponseInfomation replaceCardTotal(@CurrentUser UserInfo user) {
        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
        Integer total = generalService.findReplaceCardTotal(schoolId, true, null, true);
        return new ResponseInfomation(total, ResponseInfomation.OPERATION_SUC);
    }


    /**
     * 一卡通统计
     *
     * @return
     */
    @RequestMapping("/onePassCardCount")
    public ResponseInfomation onePassCardCount(@CurrentUser UserInfo user) {
        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
        // 0学生 1教师
        Integer stuTotal = generalService.findReplaceCardTotal(schoolId, true, 0, false);
        Integer teacherTotal = generalService.findReplaceCardTotal(schoolId, true, 1, false);
        HashMap<String, Integer> map = new HashMap<>(2);
        map.put("stuTotal", stuTotal);
        map.put("teacherTotal", teacherTotal);
        return new ResponseInfomation(map, ResponseInfomation.OPERATION_SUC);
    }


//    /**
//     * 未发卡人数统计
//     *
//     * @return
//     */
//    @RequestMapping("/notCardTotal")
//    @ResponseBody
//    public ResponseInfomation notCardTotal(@CurrentUser UserInfo user) {
//        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
//        // 获取教师和学生未发卡人数
//        Integer teacherTotal = generalService.findnotCardTeacherTotal(schoolId);
//        Integer stuTotal = generalService.findnotCardStuTotal(schoolId);
//        return new ResponseInfomation(teacherTotal + stuTotal, ResponseInfomation.OPERATION_SUC);
//    }

    /**
     * 家校反馈统计
     *
     * @return
     */
    @RequestMapping("/feedbackTotal")
    public ResponseInfomation feedbackTotal(@CurrentUser UserInfo user) {
        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
        Integer feedbackTotal = generalService.findfeedbackTotal(schoolId);
        return new ResponseInfomation(feedbackTotal, ResponseInfomation.OPERATION_SUC);
    }

    /**
     * 家校反馈月趋势图
     *
     * @return
     */
    @RequestMapping("/feedbackMonthTotal")
    public ResponseInfomation feedbackMonthTotal(@CurrentUser UserInfo user, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate) {
        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
        List<AmountTrendPojo> feedbackTotal = generalService.findFeedbackMonthTotal(schoolId, beginDate, endDate);
        return new ResponseInfomation(feedbackTotal, ResponseInfomation.OPERATION_SUC);
    }

//    /**
//     * 报修统计反馈图
//     *
//     * @return
//     */
//    @RequestMapping("/repairMonthTotal")
//    @ResponseBody
//    public ResponseInfomation repairMonthTotal(@CurrentUser UserInfo user, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate) {
//        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
//        List<AmountTrendPojo> feedbackTotal = generalService.findFeedbackMonthTotal(schoolId, beginDate, endDate);
//        return new ResponseInfomation(feedbackTotal, ResponseInfomation.OPERATION_SUC);
//    }


    /**
     * 报修统计反馈图
     *
     * @param user
     * @param beginDate
     * @param endDate
     * @return
     */
    @RequestMapping("/repairTotal")
    public ResponseInfomation repairTotal(@CurrentUser UserInfo user, @RequestParam("beginDate") String beginDate, @RequestParam("endDate") String endDate) {
        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
        List<AmountTrendPojo> repairMonthTotal = generalService.findRepairMonthTotal(schoolId, beginDate, endDate);
        return new ResponseInfomation(repairMonthTotal, ResponseInfomation.OPERATION_SUC);
    }


    /**
     * 报修未处理
     *
     * @return
     */
    @RequestMapping("/repairUntreated")
    public ResponseInfomation repairUntreated(@CurrentUser UserInfo user) {
        Integer schoolId = user != null && user.getSchoolId() != null ? user.getSchoolId() : schoolIds;
        Map<String, Integer> untreatedTotal = generalService.findRepairUntreated(schoolId);
        return new ResponseInfomation(untreatedTotal, ResponseInfomation.OPERATION_SUC);
    }


    /**
     * 当日总消费
     * /api/mobile/VipUser/SchoolDaySumMoney?query_dateBegin=2021-02-01&query_dateEnd=2021-02-01
     *
     * @return
     */
    @RequestMapping("/schoolDaySumMoney")
    public String schoolDaySumMoney(@RequestParam String query_dateBegin, @RequestParam String query_dateEnd) {
        return httpService.sendData(canteenUrl + SchoolDaySumMoney + "?query_dateBegin=" + query_dateBegin + "&query_dateEnd=" + query_dateEnd);
    }

    /**
     * 日消费趋势图
     * /api/mobile/VipUser/SchoolDaySumMoneyList
     *
     * @return
     */
    @RequestMapping("/schoolDaySumMoneyList")
    public SumMoneyVo SchoolDaySumMoneyList(@RequestParam String query_dateBegin, @RequestParam String query_dateEnd) {
        String json = httpService.sendData(canteenUrl + SchoolDaySumMoneyList + "?query_dateBegin=" + query_dateBegin + "&query_dateEnd=" + query_dateEnd);
        // 对日期进行排序
        if (!StrUtil.hasEmpty(json)) {
            SumMoneyVo sumMoneyVo = JSON.parseObject(json, new TypeReference<SumMoneyVo>() {
            });
            List<SumMoneyVo.DataBean> data = sumMoneyVo.getData();
            Collections.sort(data);
            sumMoneyVo.setData(data);
            return sumMoneyVo;
        }
        return null;
    }


    /**
     * 日消费区域分布
     * /api/mobile/VipUser/SchoolDaySumMoneyList
     *
     * @return
     */
    @RequestMapping("/schoolDayHotelCodePoint")
    public String schoolDayHotelCodePoint(@RequestParam String query_dateBegin, @RequestParam String query_dateEnd) {
        return httpService.sendData(canteenUrl + SchoolDayHotelCodePoint + "?query_dateBegin=" + query_dateBegin + "&query_dateEnd=" + query_dateEnd);
    }

    @RequestMapping("/leaveCount")
    public Map<String, Long> leaveCount() {
        String nowDate = getNowDate();
        String nextDate = getNextDay();

        Map<String, Long> map = new HashMap<>(2, 1);
        map.put("teacherLeaveCount", basicSQLService.findUniqueLong("SELECT count(1) c FROM `oa_applay_leave` l where l.start_date< '" + nextDate + "' and l.end_date >'" + nowDate + "' and l.audit_status='0' and l.audit_status='0' "));
        map.put("studentLeaveCount", basicSQLService.findUniqueLong("SELECT count(1) c FROM `pj_student_asking` l  where l.begin_date< '" + nextDate + "' and l.end_date >'" + nowDate + "' and l.is_deleted=0 and l.india_status=2 "));

        return map;
    }


    @RequestMapping("/approveCount")
    public List approveCount(Integer type) {

        String nowDate = getNowDate();
        String nextDate = getNextDay();

        List<CountElement> list = new ArrayList(5);

        list.add(new CountElement("请假", basicSQLService.findUniqueLong("SELECT count(1) c FROM `oa_applay_leave` l where l.create_date< '" + nextDate + "' and l.create_date >'" + nowDate + "' and  l.is_deleted=0")+basicSQLService.findUniqueLong("SELECT count(1) c FROM `pj_student_asking` l  where l.create_date< '" + nextDate + "' and l.create_date >'" + nowDate + "' and l.is_deleted=0 ")));
        list.add(new CountElement("公文", basicSQLService.findUniqueLong("SELECT count(1) c FROM oa_paper where create_date > '" + nowDate + "' and create_date < '" + nextDate + "' and is_deleted=0")));
        list.add(new CountElement("用章", basicSQLService.findUniqueLong("SELECT count(1) c FROM oa_applay_india where create_date > '" + nowDate + "' and create_date < '" + nextDate + "' and is_deleted=0")));
        list.add(new CountElement("校内活动", basicSQLService.findUniqueLong("SELECT count(1) c FROM at_in_school_activity where create_date > '" + nowDate + "' and create_date < '" + nextDate + "'")));
        list.add(new CountElement("校外活动", basicSQLService.findUniqueLong("SELECT count(1) c FROM at_out_school_activity where create_date > '" + nowDate + "' and create_date < '" + nextDate + "'")));

        return list;
    }

    @RequestMapping("/gradeStuCount")
    public List gradeStuCount() {
        return basicSQLService.find("SELECT g.`name`, count( tt.id) AS `value` FROM pj_grade g left JOIN pj_team_student tt ON g.id = tt.grade_id AND tt.is_delete = 0 inner join pj_student ps on ps.id=tt.student_id WHERE ps.study_state='01' and g.school_id = 215 and g.stage_code=2  AND g.school_year =( SELECT school_year FROM pj_school_term_current WHERE school_id = 215 ) GROUP BY g.`name` order by g.`grade_number` asc");
    }


    @RequestMapping("/teacherTitleCount")
    public List teacherTitleCount() {
        return basicSQLService.find("SELECT i.`name`, count( t.id) AS `value` FROM jc_gc_item i LEFT JOIN pj_teacher t ON i.table_code = 'JY-ZC' AND t.job_title = i.`value` AND t.is_delete = 0 AND t.school_id = 215 LEFT JOIN yh_user u ON u.id = t.user_id AND u.is_deleted = 0 AND u.state = '0' WHERE i.table_code = 'JY-ZC' AND i.`disable` = 00 GROUP BY i.`name` ORDER BY i.sort_order");
    }

    @RequestMapping("/teacherEducationCount")
    public List teacherEducationCount() {
        return basicSQLService.find("select i.`name`,count(t.id) as `value` from jc_gc_item i left join pj_teacher t on i.table_code='JY-XL' and t.qualification=i.`value` and t.is_delete=0 and t.school_id=215 left join yh_user u on u.id=t.user_id and u.is_deleted=0 and u.state='0'  where i.table_code='JY-XL' and i.`disable`=00 group by i.`name` order by i.sort_order");
    }

    @RequestMapping("/healthTypeCount")
    public List healthTypeCount() {
        String nowDate = getNowDate();
        String nextDate = getNextDay();
        return basicSQLService.find("SELECT i.`name` , count(t.id) as `value` FROM jc_gc_item i left JOIN(select a.id,t.health_type from pj_student_health_archive a inner join pj_student_health_archive_type t on a.id=t.student_health_id where a.is_delete=0 and a.create_date > '" + nowDate + "' and a.create_date<'" + nextDate + "' ) t on i.id=t.health_type where i.table_code='GB-JKZK3' group by i.`name` order by i.sort_order asc");
    }

    @RequestMapping("/healthGradeCount")
    public List healthGradeCount() {
        String nowDate = getNowDate();
        String nextDate = getNextDay();
        return basicSQLService.find("select g.`name`,count(a.id) as `value` from pj_grade g left join pj_team t on t.grade_id=g.id left join pj_student_health_archive a on a.team_id=t.id and a.is_delete=0 and a.create_date>'" + nowDate + "' and a.create_date<'" + nextDate + "'  where g.school_id=215 group by g.`name` order by grade_number asc  ");
    }

    @RequestMapping("/healthDateCount")
    public List healthDateCount() {
        List list = new ArrayList(5);
        List<String> fiveDay=preFiveDay();
        for (String day : fiveDay) {
            list.add(new CountElement(day,basicSQLService.findUniqueLong("select count(*) from pj_student_health_archive a where a.is_delete=0 and date_format(a.create_date,'%Y-%m-%d')='" + day + "'  ")));
        }
        return list;
    }



    @RequestMapping("/xynjcqCount")
    public Object xynjcqCount() {
        String now = getNowDate();
        String next=getNextDay();
        //校园出勤
        List<CountElement> xycqList=new ArrayList<>(4);
        //年级出勤
        List<CountElement> njcqList=new ArrayList<>(4);


        //今日学生请假数
        long leaveCount=basicSQLService.findUniqueLong("SELECT count(1) c FROM `pj_student_asking` l  where l.begin_date< '" + next + "' and l.end_date >'" + now + "' and l.is_deleted=0 and l.india_status=2 ");
        //全校学生的userId和年级名称
        List<Map<String, Object>> stuGradeList = basicSQLService.find("SELECT s.user_id,g.`name` as gradeName FROM pj_team_student tt INNER JOIN pj_team t ON tt.team_id = t.id INNER JOIN pj_student s ON s.id = tt.student_id inner join pj_grade g on g.id=t.grade_id AND s.is_delete = 0 WHERE tt.is_delete = 0 AND t.school_year =( SELECT school_year FROM pj_school_term_current WHERE school_id = 215)");
        //全校学生上学入校门禁打卡记录
        List<GatePickInfo> gatePickInfoList=betterBusThirtyPartyApiService.getStuPickInfo(getUserIds(stuGradeList),now,0,1);

        //校园出勤统计

        //实到
        int sd=countSd(true,gatePickInfoList,null,null);
        //迟到
        int cd=countSd(false,gatePickInfoList,null,null);
        xycqList.add(new CountElement("实到",sd));
        xycqList.add(new CountElement("迟到",cd));
        xycqList.add(new CountElement("请假",leaveCount));
        xycqList.add(new CountElement("未到",stuGradeList.size()-sd-cd-leaveCount));

        //年级出勤统计
        njcqList.add(new CountElement("一年级",computeGradeSd("一年级",stuGradeList,gatePickInfoList)));
        njcqList.add(new CountElement("二年级",computeGradeSd("二年级",stuGradeList,gatePickInfoList)));
        njcqList.add(new CountElement("三年级",computeGradeSd("三年级",stuGradeList,gatePickInfoList)));
        njcqList.add(new CountElement("四年级",computeGradeSd("四年级",stuGradeList,gatePickInfoList)));
        njcqList.add(new CountElement("五年级",computeGradeSd("五年级",stuGradeList,gatePickInfoList)));
        njcqList.add(new CountElement("六年级",computeGradeSd("六年级",stuGradeList,gatePickInfoList)));


        Map<String,Object> map=new HashMap<>(2);
        map.put("xycq",xycqList);
        map.put("njcq",njcqList);

        return map;
    }

    @RequestMapping("/getTeacherAtten")
    public Object getTeacherAtten(){

        List<CountElement> countElements=new ArrayList<>(5);


        List<Map<String,Object>> teachers=basicSQLService.find("select t.mobile from pj_teacher t inner join yh_user u on u.id=t.user_id where t.school_id=215 and t.is_delete=0 and u.is_deleted=0 and u.state='0' and t.mobile is not null and t.mobile!=''");
        List<String> fiveDay=preFiveDay();

        String start=fiveDay.get(0);
        String end=fiveDay.get(4);

        Map<String,Integer> dayCountMap=new HashMap<>();
        for (String day : fiveDay) {
            dayCountMap.put(day,0);
        }

        for (Map<String, Object> teacher : teachers) {
            String mobile=(String) teacher.get("mobile");
            putDayMap(queryTeacherKq(mobile,start,end),dayCountMap);
        }

        BigDecimal sum=new BigDecimal(teachers.size());

        for (String day : dayCountMap.keySet()) {
            int dayCount=dayCountMap.get(day);
            Object r=dayCount;
            if(dayCount!=0 && teachers.size()>0){
                r=new BigDecimal(dayCount).divide(sum,3,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).stripTrailingZeros();
            }

            countElements.add(new CountElement(day,r));

        }

        return countElements;


    }



    @RequestMapping("/weekStar")
    public List getWeekStart(){
        List<Map<String,Object>> list=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        String year=(String) list.get(0).get("school_year");
        String term=(String) list.get(0).get("school_term_code");
        //取最近周，即最大周数
        String week=(String) basicSQLService.findUnique("SELECT max(st.`name`) m FROM pj_week_stu_term st INNER JOIN pj_school_term pst ON pst.id = st.year_term_id WHERE  pst.school_year='"+year+"' and pst.`code`='"+term+"'");
        return replaceRelativePath(basicSQLService.find("SELECT s.`name`,f.relative_path img FROM pj_week_stu ws INNER JOIN pj_week_stu_term st ON ws.week_id = st.id INNER JOIN pj_school_term pst ON pst.id = st.year_term_id INNER JOIN pj_student s ON s.id = ws.student_id left join pj_person p on p.id=s.person_id left join res_entity_file f on f.uuid=p.photo_uuid WHERE st.`name` = '"+week+"' AND pst.school_year = '"+year+"' AND pst.`code` = '"+term+"' ORDER BY ws.score DESC LIMIT 10"));
    }



    @RequestMapping("/teacherStar")
    public List teacherStar(){
        List<Map<String,Object>> list=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        String year=(String) list.get(0).get("school_year");
        String term=(String) list.get(0).get("school_term_code");
        return  replaceRelativePath(basicSQLService.find("SELECT t.`name` ,f.relative_path img FROM pj_the_teacher_main m INNER JOIN pj_teacher t ON m.teacher_id = t.id left join pj_person p on p.id=t.persion_id left join res_entity_file f on f.uuid=p.photo_uuid where m.school_year='"+year+"' and m.xq='"+term+"' and m.type=0 order by m.sum_score desc limit 10"));
    }


    /*
    * 教师学生人数
    **/
    @RequestMapping("teacherStuCount")
    public Map countTeacherStu(){
        Map<String,Object> map=new HashMap<>();
        List<Map<String,Object>> list=basicSQLService.find("select school_year,school_term_code from pj_school_term_current where school_id=215");
        String year=(String) list.get(0).get("school_year");
        map.put("stuCount",basicSQLService.findUniqueLong("select count(1) c from pj_student ps inner join pj_team pt on pt.id=ps.team_id inner join pj_grade pg on pg.id=pt.grade_id where pg.stage_code=2 and pg.school_year="+year+" and ps.is_delete=0 and pg.is_deleted=0 and pt.is_delete=0 and ps.study_state='01'"));
        map.put("jzgCount",basicSQLService.findUniqueLong("select count(1) c from pj_teacher t inner join yh_user u on u.id=t.user_id where t.job_state='11' and t.is_delete=0 and u.is_deleted=0 and u.state='0'"));
        map.put("teacherCount",basicSQLService.findUniqueLong("SELECT count( DISTINCT t.id) c FROM pj_teacher t INNER JOIN yh_user u ON u.id = t.user_id INNER JOIN yh_user_role ur ON ur.user_id = u.id AND ur.is_deleted = 0 INNER JOIN yh_role r ON r.id = ur.role_id WHERE t.job_state='11' and t.is_delete = 0 AND u.is_deleted = 0 AND u.state = '0' AND r.`name` in('科任教师','班主任','主任','行政人员','校长')"));
        return map;
    }




    private List<Map<String,Object>> replaceRelativePath(List<Map<String,Object>> list){
        for (Map<String, Object> map : list) {
            String img=(String) map.get("img");
            if(StringUtils.isNotEmpty(img)){
                map.put("img",fileService.relativePath2HttpUrl(img));
            }
        }
        return list;
    }


    private void putDayMap(List<Map<String,Object>> teacherKqList,Map<String,Integer> dayCountMap){
        for (String day : dayCountMap.keySet()) {
            for (Map<String, Object> kq : teacherKqList) {
                if(day.equals(kq.get("attendDate"))){
                    dayCountMap.put(day,dayCountMap.get(day).intValue()+1);
                    break;
                }
            }
        }
    }


    private Object computeGradeSd(String gradeName,List<Map<String, Object>> stuGradeList,List<GatePickInfo> gatePickInfoList){
        BigDecimal sum=new BigDecimal(countByGradeName(gradeName,stuGradeList));
        if(sum.intValue()==0){
            return 0;
        }
        BigDecimal sdCount=new BigDecimal(countSd(true,gatePickInfoList,gradeName,stuGradeList));
        if(sdCount.intValue()==0){
            return 0;
        }

        return sdCount.divide(sum,3,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100")).stripTrailingZeros();
    }


    private int countByGradeName(String gradeName,List<Map<String, Object>> stuGradeList){
        int count=0;
        for (Map<String, Object> map : stuGradeList) {
            if(gradeName.equals(map.get("gradeName"))){
                count++;
            }
        }
        return count;
    }


    private int countSd(boolean isSd,List<GatePickInfo> gatePickInfoList,String gradeName,List<Map<String, Object>> stuGradeList){
        List<String> countedUserIds=new ArrayList<>();
        for (GatePickInfo gatePickInfo : gatePickInfoList) {

            //一个学生在上学时间段可能有多个入校打卡，不能做重复统计，因为打卡记录按打卡时间降序，所系只需要第一次的打卡记录，后面的跳过不统计
            if(countedUserIds.contains(gatePickInfo.getPersonId())){
                continue;
            }

            int compareResult=formatEventTime(gatePickInfo.getEventTime()).compareTo("08:20");

            if( (isSd && compareResult<1) || (!isSd && compareResult>0) ){
                //是否统计某个年级的出勤
                if(gradeName!=null && stuGradeList!=null){
                    if(gradeName.equals(findTeamNameByUserId(gatePickInfo.getPersonId(),stuGradeList))){
                        countedUserIds.add(gatePickInfo.getPersonId());
                    }
                }else {
                    countedUserIds.add(gatePickInfo.getPersonId());
                }
            }
        }
        return countedUserIds.size();
    }

    private String findTeamNameByUserId(String userId,List<Map<String,Object>> stuGradeList){
        for (Map<String, Object> map : stuGradeList) {
            if(map.get("user_id").toString().equals(userId)){
                return (String) map.get("gradeName");
            }
        }
        return null;
    }

    private String formatEventTime(String eventTime){
        //2018-08-14T19:49:10+08:00;
        if(eventTime!=null && eventTime.length()==25){
            return eventTime.substring(11,16);
        }
        return eventTime;

    }

    private List<String> getUserIds(List<Map<String, Object>> stuGradeList) {
        List<String> userIds = new ArrayList<>(stuGradeList.size());
        for (Map<String, Object> map : stuGradeList) {
            userIds.add(map.get("user_id").toString());
        }
        return userIds;
    }



    private String getNowDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    private String getNextDay() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        c.add(Calendar.DAY_OF_MONTH, 1);
        return sdf.format(c.getTime());
    }


    private List<Map<String, Object>> queryTeacherKq(String mobile, String startDate, String endDate){

        AttendanceServiceListRecordsByUserDuringDateParam param = new AttendanceServiceListRecordsByUserDuringDateParam();
        AttendanceServiceListRecordsByUserDuringDateParam.RequestBody requestBody = AttendanceServiceListRecordsByUserDuringDateParam.RequestBody.builder().build();
        param.setRequestBody(requestBody);
        AttendanceServiceListRecordsByUserDuringDateParam.Query query = AttendanceServiceListRecordsByUserDuringDateParam.Query.builder()
                .appId(SeewoOperateClient.getAppId())
                .schoolUid(SeewoOperateClient.getSchoolUid())
                .userCode(mobile.toString())
                .userCodeType(5)
                .attendType(2)
                .startDate(startDate)
                .endDate(endDate)
                .page(1)
                .pageSize(20)
                .build();
        requestBody.setQuery(query);
        try {
            BasicResponseResult result=SeewoOperateClient.invoke( new AttendanceServiceListRecordsByUserDuringDateRequest(param));
            return (List<Map<String,Object>>)((Map)result.getData()).get("result");
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(0);
        }
    }


    private List<String> preFiveDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);

        c.add(Calendar.DAY_OF_MONTH,-4);

        List<String> days=new ArrayList<>(5);
        for(int i=0;i<5;i++){
            days.add(sdf.format(c.getTime()));
            c.add(Calendar.DAY_OF_MONTH,1);
        }
        return days;
    }


}
