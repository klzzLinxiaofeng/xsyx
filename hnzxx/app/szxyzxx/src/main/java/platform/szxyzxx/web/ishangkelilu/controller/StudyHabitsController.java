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
        exporter=new DefaultExporter<>(new ExcelExportParam<>(HabitsDaoChu.class,"????????????????????????"));

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


    //?????????
    @RequestMapping("/zuoweitianjia")
    public ModelAndView findBytianjia(@RequestParam Integer zuowei, Model model) {
        model.addAttribute("zuowei", zuowei);
        ModelAndView modelAndView = new ModelAndView("/ishangkelilu/zuowei/index", model.asMap());
        return modelAndView;
    }


    //?????????
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

    //?????????
    @RequestMapping("/chnaglianjie")
    public List<StudyHabits> ajax(@RequestParam Integer teamId,
                                  @CurrentUser UserInfo userInfo, HttpServletResponse response) throws Exception {
            List<StudyHabits> falg2 = studyHabitsService.findByShangke(teamId, userInfo.getTeacherId());
                return falg2;
           // }
    }
    //?????????????????????
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
                System.out.println("????????????");
            }
        }else{
          Boolean b=  studyHabitsService.updateZuoweiHangLie(teamId,zu,hang,lie);
            if(!b){
                System.out.println("????????????");
            }
        }
        return "success";
        // }
    }
    //
    //?????????????????????
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
            logger.setMokuaiName("????????????");
            logger.setType(2);
            logger.setMessage("??????"+studentName+"?????????");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "false";
        }
    }

    /*
    *???????????????????????????
    */
    @RequestMapping("/teacherTeam")
    public  List<Map<String,Object>> findByStudyXiGuan(@CurrentUser UserInfo userInfo) {
        List<Map<String,Object>> list=basicSQLService.find("select * from pj_subject where code in (select subject_code from pj_team_teacher where teacher_id="+userInfo.getTeacherId()+" and is_delete=0 and subject_code is not null group by subject_code )");
        return  list;
    }

    /*
     *????????????
     */
    @RequestMapping("/zuoweishanchu")
    public  String findByshanchuzuowei(@RequestParam Integer id) {
        String num=studyHabitsService.updatezuowei(id);
        return  num;
    }


    /*
     * ??????
     */
    @RequestMapping("/daochuTiaoZhuan")
    public ModelAndView daochuTiaoZhuan(){
        return new ModelAndView("/ishangkelilu/xiguan/daochu");

    }
    /*
     * ??????
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
            //page???null?????????
            List<HabitsDaoChu> list2 = studyHabitsService.findByDaoChu(gradeId,teamId,schoolYear,schoolTrem,subjectId,startDate,endDate);
            if(list2.size()>=100000){
                return "?????????????????????????????????";
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
                filename="??????";
            }
            Date date=new Date();
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
            filename+="??????????????????"+sdf2.format(date)+".xls";
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
     * ????????????
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
            filename="??????";
        }
        Date date=new Date();
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
        filename+="??????????????????"+sdf2.format(date)+".xls";
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
            return " ????????????";
        }else if(habitsDaoChu.getType().equals("2")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("3")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("4")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("5")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("6")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("7")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("8")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("9")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("10")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("11")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("12")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("13")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("14")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("15")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("16")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("17")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("18")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("19")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("20")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("21")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("22")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("23")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("24")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("25")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("26")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("27")){
            return "????????????";
        }else if(habitsDaoChu.getType().equals("28")){
            return "????????????";
        }else{
            return "??????";
        }
    }

}
