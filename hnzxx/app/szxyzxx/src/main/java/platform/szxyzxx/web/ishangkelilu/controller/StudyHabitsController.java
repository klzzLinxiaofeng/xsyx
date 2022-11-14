package platform.szxyzxx.web.ishangkelilu.controller;


import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.ishangkelilu.pojo.*;
import platform.szxyzxx.ishangkelilu.service.StudyHabitsService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/study/habits")
public class StudyHabitsController {
    @Autowired
    private StudyHabitsService studyHabitsService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;

    private Importer<HabitsDaoChu> importer;

    private Exporter<HabitsDaoChu> exporter;

    public StudyHabitsController(){
        ExcelImportParam<HabitsDaoChu> param=new ExcelImportParam<>(HabitsDaoChu.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter<>(new ExcelExportParam<>(HabitsDaoChu.class,"学生评价细则列表"));

    }

    @RequestMapping("/student")
    public ModelAndView findByStudentZuoWei(@CurrentUser UserInfo userInfo,
                                            @RequestParam(value = "gradeId", required = false) Integer gradeId,
                                            @RequestParam(value = "schoolYear", required = false) String schoolYear,
                                            @RequestParam(value = "teamId", required = false) Integer teamId,
                                            @ModelAttribute("page") Page page,
                                            @RequestParam(value = "sub", required = false) String sub, Model model) {
        List<Student> list = studyHabitsService.findByStudent(userInfo, gradeId, schoolYear, teamId, page);
        model.addAttribute("list", list);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/ishangkelilu/list";
        } else {
            bathUrl = "/ishangkelilu/index";
        }
        ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
        return modelAndView;
    }
    @RequestMapping("/studentXuexi")
    public ModelAndView findByStudent(@CurrentUser UserInfo userInfo,
                                      @RequestParam(value = "gradeId", required = false) Integer gradeId,
                                      @RequestParam(value = "schoolYear", required = false) String schoolYear,
                                      @RequestParam(value = "teamId", required = false) Integer teamId,
                                      @ModelAttribute("page") Page page,
                                      @RequestParam(value = "sub", required = false) String sub, Model model) {
        List<Student> list = studyHabitsService.findByStudent(userInfo, gradeId, schoolYear, teamId, page);
        model.addAttribute("list", list);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/ishangkelilu/xiguan/list";
        } else {
            bathUrl = "/ishangkelilu/xiguan/xiguanindex";
        }
        ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
        return modelAndView;
    }

    @RequestMapping("/input")
    public ModelAndView findByStudyHabit(@CurrentUser UserInfo userInfo,
                                         @RequestParam(value = "studentId", required = false) Integer studentId,
                                         @RequestParam Integer bj,
                                         Model model) {
        StudyHabits studyHabits = new StudyHabits();
        studyHabits.setStudentId(studentId);
        studyHabits.setTeamId(bj);
        studyHabits.setTeacherId(userInfo.getTeacherId());
        model.addAttribute("studyHabits", studyHabits);
        ModelAndView modelAndView = new ModelAndView("/ishangkelilu/xiguan/input", model.asMap());
        return modelAndView;
    }

    @RequestMapping("/xiugaiOrAdd")
    public String findByStudyHabit(StudyHabits studyHabits) {
        Date date = new Date();
        studyHabits.setCreateTime(date);
        String str = studyHabitsService.createOrupdate(studyHabits);
        return str;
    }

    @RequestMapping("/xiake")
    public String findByxiake(
            @CurrentUser UserInfo userInfo,
            @RequestParam Integer teamId) {
        String str = studyHabitsService.findByXiake(userInfo, teamId);
        return str;
    }


    //座位表
    @RequestMapping("/zuoweitianjia")
    public ModelAndView findBytianjia(@RequestParam Integer zuowei, Model model) {
        model.addAttribute("zuowei", zuowei);
        ModelAndView modelAndView = new ModelAndView("/ishangkelilu/zuowei/index", model.asMap());
        return modelAndView;
    }


    //座位表
    @RequestMapping("/zhaoren")
    public ModelAndView findBytianjiachazhao(@RequestParam String stuName,
                                             @CurrentUser UserInfo userInfo, Model model,
                                             @ModelAttribute("page") Page page) {
        List<StudentXin> list = studyHabitsService.findBytianjiachazhao(stuName, userInfo);
        model.addAttribute("list", list);
        ModelAndView modelAndView = new ModelAndView("/ishangkelilu/zuowei/list", model.asMap());
        return modelAndView;
    }

    @RequestMapping("/zuowei")
    public List<ZuoWei> findByzuowei(@RequestParam Integer teamId,
                                     @CurrentUser UserInfo userInfo) {
        List<ZuoWei> list = studyHabitsService.findByZuowei(teamId);
        return list;
    }

    @RequestMapping("/addZuowei")
    public String createZuowei(@RequestParam Integer teamId,
                               @CurrentUser UserInfo userInfo,
                               @RequestParam Integer studentId,
                               @RequestParam Integer zuoweihao) {
        ZuoWei zuoWei = new ZuoWei();
        zuoWei.setHaoMa(zuoweihao);
        zuoWei.setStudentId(studentId);
        zuoWei.setTeamId(teamId);
        Boolean falg = studyHabitsService.createZuowei(zuoWei);
        if (falg) {
            return "success";
        }
        return "shibai";
    }

    @RequestMapping("/shangkejilu")
    public ModelAndView findByShangke(@RequestParam Integer teamId,
                                      @CurrentUser UserInfo userInfo, Model model) {
        ModelAndView modelAndView = new ModelAndView();

        List<StudyHabits> falg = studyHabitsService.findByShangke(teamId, userInfo.getTeacherId());
        model.addAttribute("list", falg);
        return new ModelAndView("/ishangkelilu/xiguan/list", model.asMap());
    }

    @RequestMapping("/chehui")
    public String findBychehui(@RequestParam Integer id) {
        Boolean falg = studyHabitsService.findBychehui(id);
        if (falg) {
            return "success";
        }
        return "shibai";
    }

    @RequestMapping("/shangkejiluView")
    public ModelAndView findByShangke(
    ) {
        ModelAndView modelAndView = new ModelAndView("ishanglelilu/list");
        return modelAndView;
    }

    @RequestMapping("/zuoweiView")
    public ModelAndView findByzuoweiBiao() {
        ModelAndView modelAndView = new ModelAndView("ishanglelilu/zuoweiList");
        return modelAndView;
    }

    //长连接
    @RequestMapping("/chnaglianjie")
    public List<StudyHabits> ajax(@RequestParam Integer teamId,
                                  @CurrentUser UserInfo userInfo, HttpServletResponse response) throws Exception {
            List<StudyHabits> falg2 = studyHabitsService.findByShangke(teamId, userInfo.getTeacherId());
                return falg2;
           // }
    }
    //座位表列行保存
    @RequestMapping("/zuoweiHangLie")
    public String zuoweiHangfLie(@RequestParam Integer teamId,
                                  @RequestParam Integer zu,
                                 @RequestParam Integer hang,
                                 @RequestParam Integer lie,
                                  @CurrentUser UserInfo userInfo) {
        ZuoWeiHangLie zuoWeiHangLie = studyHabitsService.findByzuoweiHangfLie(teamId,userInfo);
        if(zuoWeiHangLie==null){
            Boolean b=  studyHabitsService.createZuoweiHangLie(teamId,zu,hang,lie);
            if(!b){
                System.out.println("添加失败");
            }
        }else{
          Boolean b=  studyHabitsService.updateZuoweiHangLie(teamId,zu,hang,lie);
            if(!b){
                System.out.println("修改失败");
            }
        }
        return "success";
        // }
    }
    //
    //座位表列行保存
    @RequestMapping("/zuoweiHangList")
    public Map<String,Object> zuoweiHangfLie(@RequestParam Integer teamId,
                              @CurrentUser UserInfo userInfo) {
        Map<String,Object> map=new HashMap<>();
        ZuoWeiHangLie zuoWeiHangLie = studyHabitsService.findByzuoweiHangfLie(teamId,userInfo);
        if(zuoWeiHangLie!=null){
            map.put("stats","success");
            map.put("list",zuoWeiHangLie);
            return map;
        }
        map.put("stats","error");
        return map;
    }

    @RequestMapping("/StudyXiGuan")
    public ModelAndView findByStudyXiGuan(@CurrentUser UserInfo userInfo,
                                          @RequestParam(value = "studentName", required = false) String studentName,
                                          @RequestParam(value = "subjectId", required = false) Integer subjectId,
                                          @RequestParam(value = "teamId", required = false) Integer teamId,
                                          @RequestParam(value = "gradeId", required = false) Integer gradeId,
                                          @RequestParam(value = "schoolYear", required = false) String schoolYear,
                                          @RequestParam(value = "schoolTrem", required = false) String schoolTrem,
                                          @RequestParam(value = "startTime", required = false) String startTime,
                                          @RequestParam(value = "endTime", required = false) String endTime,
                                      @ModelAttribute("page") Page page,
                                      @RequestParam String qwe,
                                      @RequestParam(value = "sub", required = false) String sub, Model model) {
        if (qwe.equals("asd")) {
            if (userInfo.getTeacherId() != null) {
                List<StudyHabits> list = studyHabitsService.getAllStudy(gradeId, teamId, subjectId, studentName, schoolYear, schoolTrem,startTime,endTime, page);
                model.addAttribute("list", list);
                model.addAttribute("stats", true);
            } else {
                model.addAttribute("stats", false);
            }
        }else{
            if (userInfo.getTeacherId() != null) {
                model.addAttribute("stats", true);
            } else {
                model.addAttribute("stats", false);
            }
        }
            model.addAttribute("schoolYear", userInfo.getSchoolYear());
            String bathUrl;
            if (sub.equals("list")) {
                bathUrl = "/ishangkelilu/xiguan/xuexijilulist";
            } else {
                bathUrl = "/ishangkelilu/xiguan/xuexiilu";
            }
            ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
            return modelAndView;
        }

    @RequestMapping("/StudyBianJi")
    public ModelAndView findByStudyBianJi(@CurrentUser UserInfo userInfo,
                                          @RequestParam Integer id,
                                           Model model) {
        StudyHabits list=studyHabitsService.findById(id);
        model.addAttribute("list",list);
        String bathUrl = "/ishangkelilu/xiguan/xuexiInput";

        ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
        return modelAndView;
    }
    @RequestMapping("/StudentUpdate")
    public String findByStudyXiGuan(@CurrentUser UserInfo userInfo,
                                    @RequestParam Integer id,
                                    @RequestParam String studentName,
                                    @RequestParam(value = "pingyu",required = false) String pingyu
                                         ) {
            Integer num=studyHabitsService.updateStudy(id,pingyu);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("学习习惯");
            logger.setType(2);
            logger.setMessage("修改"+studentName+"的评语");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "false";
        }
    }

    /*
    *获取登录账号的年级
    */
    @RequestMapping("/teacherTeam")
    public  List<Map<String,Object>> findByStudyXiGuan(@CurrentUser UserInfo userInfo) {
        List<Map<String,Object>> list=basicSQLService.find("select * from pj_subject where code in (select subject_code from pj_team_teacher where teacher_id="+userInfo.getTeacherId()+" and is_delete=0 and subject_code is not null group by subject_code )");
        return  list;
    }

    /*
     *删除座位
     */
    @RequestMapping("/zuoweishanchu")
    public  String findByshanchuzuowei(@RequestParam Integer id) {
        String num=studyHabitsService.updatezuowei(id);
        return  num;
    }


    /*
     * 导出
     */
    @RequestMapping("/daochuTiaoZhuan")
    public ModelAndView daochuTiaoZhuan(){
        return new ModelAndView("/ishangkelilu/xiguan/daochu");

    }
    /*
     * 导出
     */
    @RequestMapping("/daochuWoke")
    public String daochuWoke(
                             @RequestParam(value = "teamId",required = false) Integer teamId,
                             @RequestParam(value = "gradeId",required = false) Integer gradeId,
                             @RequestParam(value = "schoolYear",required = false) String schoolYear,
                             @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
                             @RequestParam(value = "subjectId",required = false) Integer subjectId,
                             @RequestParam(value = "startDate",required = false) String startDate,
                             @RequestParam(value = "endDate",required = false) String  endDate,
                             HttpServletResponse response, HttpServletRequest request){
        try {
            //page传null不分页
            List<HabitsDaoChu> list2 = studyHabitsService.findByDaoChu(gradeId,teamId,schoolYear,schoolTrem,subjectId,startDate,endDate);
            if(list2.size()>=100000){
                return "超出当前导出数据最大值";
            }
            for(HabitsDaoChu aa:list2){
                String asd=zhuanhuanLeiXing(aa);
                aa.setType(asd);
            }
            String filename="";
            if(gradeId!=null){
                if(teamId!=null){
                    List<Map<String,Object>> map=basicSQLService.find("SELECT * FROM pj_team WHERE school_id = 215 and id="+teamId+"  ORDER BY id DESC");
                    filename=map.get(0).get("name").toString();
                }else{
                    List<Map<String,Object>> map=basicSQLService.find("SELECT * FROM pj_grade WHERE school_id = 215 and id="+gradeId+"  ORDER BY id DESC");
                    filename=map.get(0).get("name").toString();
                }
            }else{
                filename="全校";
            }
            Date date=new Date();
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
            filename+="行为评价细则"+sdf2.format(date)+".xls";
            Workbook wb = exporter.exportToNew(list2);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode(filename, "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
         return "success";
    }
    /*
     * 导出总分
     */
    @RequestMapping("/daochuzongfen")
    public String daochuzongfen(
            @RequestParam(value = "teamId",required = false) Integer teamId,
            @RequestParam(value = "gradeId",required = false) Integer gradeId,
            @RequestParam(value = "schoolYear",required = false) String schoolYear,
            @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
            @RequestParam(value = "subjectId",required = false) Integer subjectId,
            @RequestParam(value = "startDate",required = false) String startDate,
            @RequestParam(value = "endDate",required = false) String  endDate,
            HttpServletResponse response, HttpServletRequest request){
        List<Object> list = new ArrayList();
        List<HabitsDaoChu> list2 = studyHabitsService.daochuzongfen(gradeId,teamId,schoolYear,schoolTrem,subjectId,startDate,endDate);
        String filename="";
        if(gradeId!=null){
            if(teamId!=null){
                List<Map<String,Object>> map=basicSQLService.find("SELECT * FROM pj_team WHERE school_id = 215 and id="+teamId+"  ORDER BY id DESC");
                filename=map.get(0).get("name").toString();
            }else{
                List<Map<String,Object>> map=basicSQLService.find("SELECT * FROM pj_grade WHERE school_id = 215 and id="+gradeId+"  ORDER BY id DESC");
                filename=map.get(0).get("name").toString();
            }
        }else{
            filename="全校";
        }
        Date date=new Date();
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
        filename+="行为评价总和"+sdf2.format(date)+".xls";
        ParseConfig config = SzxyExcelTookit.getConfig("HabitsDaoChuZongHe");
        StringBuffer excelName = new StringBuffer();
        excelName.append(filename);
        String filenames = excelName.toString();
        try {
            for (HabitsDaoChu studentScoreVo : list2) {
                list.add(studentScoreVo);
            }
            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filenames);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "success";
    }

    public String zhuanhuanLeiXing(HabitsDaoChu habitsDaoChu){
        if(habitsDaoChu.getType().equals("1")) {
            return " 遵规守纪";
        }else if(habitsDaoChu.getType().equals("2")){
            return "准备到位";
        }else if(habitsDaoChu.getType().equals("3")){
            return "坐姿良好";
        }else if(habitsDaoChu.getType().equals("4")){
            return "写姿正确";
        }else if(habitsDaoChu.getType().equals("5")){
            return "认真听讲";
        }else if(habitsDaoChu.getType().equals("6")){
            return "积极发言";
        }else if(habitsDaoChu.getType().equals("7")){
            return "认真作业";
        }else if(habitsDaoChu.getType().equals("8")){
            return "听写优秀";
        }else if(habitsDaoChu.getType().equals("9")){
            return "计算优秀";
        }else if(habitsDaoChu.getType().equals("10")){
            return "练习优秀";
        }else if(habitsDaoChu.getType().equals("11")){
            return "行为良好";
        }else if(habitsDaoChu.getType().equals("12")){
            return "违反纪律";
        }else if(habitsDaoChu.getType().equals("13")){
            return "未作准备";
        }else if(habitsDaoChu.getType().equals("14")){
            return "坐姿不端";
        }else if(habitsDaoChu.getType().equals("15")){
            return "写姿不良";
        }else if(habitsDaoChu.getType().equals("16")){
            return "精神不振";
        }else if(habitsDaoChu.getType().equals("17")){
            return "小嘴吵闹";
        }else if(habitsDaoChu.getType().equals("18")){
            return "不做作业";
        }else if(habitsDaoChu.getType().equals("19")){
            return "听写欠佳";
        }else if(habitsDaoChu.getType().equals("20")){
            return "计算欠佳";
        }else if(habitsDaoChu.getType().equals("21")){
            return "练习欠佳";
        }else if(habitsDaoChu.getType().equals("22")){
            return "行为失当";
        }else if(habitsDaoChu.getType().equals("23")){
            return "课堂纪律";
        }else if(habitsDaoChu.getType().equals("24")){
            return "学习态度";
        }else if(habitsDaoChu.getType().equals("25")){
            return "倾听应答";
        }else if(habitsDaoChu.getType().equals("26")){
            return "质疑点评";
        }else if(habitsDaoChu.getType().equals("27")){
            return "实践思考";
        }else if(habitsDaoChu.getType().equals("28")){
            return "合作创新";
        }else{
            return "其他";
        }
    }

}
