package platform.szxyzxx.web.homewoke.controller;


import framework.generic.dao.Page;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Subject;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.SubjectService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.excelhelper.exports.Exporter;
import platform.szxyzxx.excelhelper.exports.impl.DefaultExporter;
import platform.szxyzxx.excelhelper.imports.Importer;
import platform.szxyzxx.excelhelper.imports.impl.DefaultImporter;
import platform.szxyzxx.excelhelper.pojo.ExcelExportParam;
import platform.szxyzxx.excelhelper.pojo.ExcelImportParam;
import platform.szxyzxx.homewoke.pojo.HomeWokePojo;
import platform.szxyzxx.homewoke.pojo.StudentHomeWoke;
import platform.szxyzxx.homewoke.service.HomeWokeService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wechat.template.ApprovalWechatMessageTemplate;
import platform.szxyzxx.wechat.template.WechatMessageTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
    * 作业发布，及关于作业的接口
    * autor ZY
    * 2022，01，07
     */
@Controller
@RequestMapping("/home/woke")
public class HomeWokeController {
    @Resource
    private BasicSQLService basicSQLService;
    @Autowired
    private HomeWokeService homeWokeService;
    @Autowired
    private TeamTeacherService  teamTeacherService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private GradeService gradeService;

    private Importer<HomeWokePojo> importer;

    private Exporter exporter;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;

    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;
    @Autowired
    private LoggerService loggerService;

    public HomeWokeController(){
        ExcelImportParam<HomeWokePojo> param=new ExcelImportParam<>(HomeWokePojo.class);
        param.setStartRowIndex(1);
        importer=new DefaultImporter<>(param);
        exporter=new DefaultExporter(new ExcelExportParam<>(HomeWokePojo.class,"作业列表"));

    }

/*
* 获取当前教师任教年级
*/
@RequestMapping("/getGradeAll")
@ResponseBody
public Map<Integer, Object> findByGradeAll(@CurrentUser UserInfo user,
                                           @RequestParam String schoolYear) {
   return teamTeacherService.getTeacherTeachGrade(user.getId(), schoolYear, user.getSchoolId());
}
    /*
     * 获取当前学年年级
     */
    @RequestMapping("/getgradeAlls")
    @ResponseBody
    public Map<Integer, Object> findByGradeAlls(@CurrentUser UserInfo user) {
        return teamTeacherService.getTeacherTeachGrade(user.getId(), user.getSchoolYear(), user.getSchoolId());
    }
    /*
     * 获取当前教师任教班级
     */
    @RequestMapping("/getTeamAll")
    @ResponseBody
    public Map<Integer, Object> findByTeamAll(@CurrentUser UserInfo user,
                                              @RequestParam Integer gradeId) {
        return teamTeacherService.getTeacherTeachTeam(user.getId(), user.getSchoolYear(),gradeId);
    }
    /*
     * 获取当前教师任教科目
     */
    @RequestMapping("/getSubjectAll")
    @ResponseBody
    public Map<Integer, Object> findBySubjectAll(@CurrentUser UserInfo user,
                                                 @RequestParam Integer gradeId,
                                                 @RequestParam Integer teamId) {
        Map<String, Object> map= teamTeacherService.getTeacherTeachSubject(user.getId(),user.getSchoolYear(),gradeId,teamId);
        Map<Integer, Object> subjectId=new HashMap<>();
        for(String key : map.keySet()){
            Subject subject=subjectService.findUnique(user.getSchoolId(),key);
            subjectId.put(subject.getId(),subject.getName());
        }
        return subjectId;
    }

    @RequestMapping("/homeWoke")
    public ModelAndView findByHomeAll() {
        String bathUrl = "/homeWoke/homeWorkindex";
        ModelAndView modelAndView = new ModelAndView(bathUrl);
        return modelAndView;
    }
    /*
     *根据班级，学校，科目，查询作业列表
     */
    @RequestMapping("/homeWokeAll")
    public ModelAndView findByHomeAll(@RequestParam(value = "teamId", required = false) Integer teamId,
                                      @RequestParam(value = "gradeId", required = false) Integer gradeId,
                                      @RequestParam(value = "schoolYear", required = false) String schoolYear,
                                      @RequestParam(value = "schoolTrem", required = false) String schoolTrem,
                                      @RequestParam(value = "subjectId", required = false) Integer subjectId,
                                      @RequestParam(value = "isHome", required = false) Integer isHome,
                                      @RequestParam(value = "text", required = false) String text,
                                      @RequestParam(value = "text2", required = false) String text2,
                                      @ModelAttribute("page") Page page,
                                      @CurrentUser UserInfo userInfo,
                                      @RequestParam(value = "sub", required = false) String sub,
                                      Model model) {
        List<HomeWokePojo> list = homeWokeService.findByAll(userInfo, teamId, gradeId, schoolYear,schoolTrem, subjectId, text,text2,isHome, page);
        for(HomeWokePojo aa:list){
            if(aa.getPictureUuid()!=null){
                FileResult file = fileService.findFileByUUID(aa.getPictureUuid());
                if (file != null) {
                    aa.setPicturUrl(file.getHttpUrl());
                }
            }
        }
        model.addAttribute("homeList", list);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/homeWoke/list";
        } else {
            bathUrl = "/homeWoke/homeWorkindex";
        }
        ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
        return modelAndView;
    }


    /*
     * 添加或编辑跳转页面
     */
    @RequestMapping("/createOrUpdate")
    public ModelAndView createOrUpdate(@RequestParam(value = "id", required = false) Integer id,
                                       Model model) {
        HomeWokePojo homeWokePojo = homeWokeService.findById(id);
        String bathUrl ;
        if (id == null) {
            bathUrl = "/homeWoke/input";
        } else {
            bathUrl = "/homeWoke/xiugai";
            if(homeWokePojo.getPictureUuid()!=null){
                FileResult file = fileService.findFileByUUID(homeWokePojo.getPictureUuid());
                if (file != null) {
                    homeWokePojo.setPicturUrl(file.getHttpUrl());
                }
            }
            model.addAttribute("homeWoke", homeWokePojo);

        }
        ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
        return modelAndView;
    }

    //添加作业
    @RequestMapping("/addHomeWoke")
    @ResponseBody
    public String CreateHomeAll(@CurrentUser UserInfo userInfo, HomeWokePojo homeWokePojo) {
        Date date = new Date();
        homeWokePojo.setCreateTime(date);
        homeWokePojo.setSchoolId(userInfo.getSchoolId());
        homeWokePojo.setTeacherId(userInfo.getTeacherId());
        homeWokePojo.setModieTime(date);
        String str = homeWokeService.createHomke(homeWokePojo);
        if(homeWokePojo.getIsHome()!=0) {
            List<Map<String, Object>> studentList = basicSQLService.find("select * from pj_student where is_delete=0 and school_id=" + homeWokePojo.getSchoolId() + " and team_id=" + homeWokePojo.getTeamId());
            for (Map<String, Object> aa : studentList) {
                String sql = "select pp.* from pj_parent pp inner join pj_parent_student pps on pps.parent_user_id=pp.user_id where pps.student_user_id=" + Integer.parseInt(aa.get("user_id").toString());
                List<Map<String, Object>> mapList = basicSQLService.find(sql);
                sendWechatNotice(mapList.get(0).get("user_id").toString());
            }
        }
        if(str.equals("success")) {
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("作业管理");
            logger.setType(1);
            logger.setMessage("新增" + homeWokePojo.getTeamName() + "的" + homeWokePojo.getSubjectName() + "的作业");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
        }
        return str;
    }
    private void sendWechatNotice(String userId){
        List notifyUserList=basicSQLService.find("select open_id from yh_user where id="+userId+" and open_id is not null");
        if(notifyUserList!=null && notifyUserList.size()>0) {
            WechatMessageTemplate wechatMessageTemplate=new ApprovalWechatMessageTemplate("作业通知","您的孩子有新的作业待查看");
            notifyService.sendWechatNotice(wechatMessageTemplate,notifyUserList,"open_id",null);
        }
    }

    //编辑作业
    @RequestMapping("/updateHomeWoke")
    @ResponseBody
    public String UpdateHomeAll(@CurrentUser UserInfo userInfo, HomeWokePojo homeWokePojo) {
        Date date = new Date();
        homeWokePojo.setModieTime(date);
        homeWokePojo.setSchoolId(userInfo.getSchoolId());
        String str = homeWokeService.UpdateHomke(homeWokePojo);
        if(str.equals("success")) {
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("作业管理");
            logger.setType(2);
            logger.setMessage("修改" + homeWokePojo.getTeamName() + "的" + homeWokePojo.getSubjectName() + "的作业");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
        }
        return str;
    }

    //删除作业
    @RequestMapping("/deleteHomeWoke")
    @ResponseBody
    public String deleteHomeAll(@CurrentUser UserInfo userInfo, @RequestParam Integer id) {
        HomeWokePojo homeWokePojo = homeWokeService.findById(id);
        Integer str = homeWokeService.updateIsDelete(1, id);
        if (str > 0) {
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("作业管理");
            logger.setType(3);
            logger.setMessage("删除" + homeWokePojo.getTeamName() + "的" + homeWokePojo.getSubjectName() + "的作业");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }
        return "删除失败";
    }

    /*
     * 查看详情跳转页面
     */
    @RequestMapping("/xiangqing")
    public ModelAndView findByXinagQing(@RequestParam Integer id,
                                        @RequestParam(value = "zhuangtai",required = false) Integer zhuangtai,
                                        Model model,
                                        @RequestParam String sub) {
        List<StudentHomeWoke> studentHomeWoke = homeWokeService.findByAllStudentAll(id,zhuangtai,null);
        String bathUrl ;
        if(sub.equals("list")){
            bathUrl= "/homeWoke/xiangqingList";
        }else{
            bathUrl= "/homeWoke/xiangqing";
        }
        model.addAttribute("id",id);
        model.addAttribute("homeWoke", studentHomeWoke);
        ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
        return modelAndView;
    }

    /*
     *导出作业列表
     */
    @RequestMapping("/daochu")
    @ResponseBody
    public void fingDaoChu(@RequestParam(value = "teamId",required = false) Integer teamId,
                           @RequestParam(value = "gradeId",required = false) Integer gradeId,
                           @RequestParam String schoolYear,
                           @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
                           @RequestParam(value = "subjectId",required = false) Integer subjectId,
                           @CurrentUser UserInfo userInfo,
                           @RequestParam(value = "text",required = false) String text,
                           @RequestParam(value = "text2",required = false) String text2,
                           @RequestParam(value = "isHome",required = false)  Integer isHome,
                           HttpServletResponse response) {
        try {
            //page传null不分页
            List<HomeWokePojo> list = homeWokeService.findByAll(userInfo, teamId, gradeId, schoolYear,schoolTrem, subjectId,text,text2,isHome, null);
            SimpleDateFormat dateFm = new SimpleDateFormat("yyyy-MM-dd"); //格式化当前系统日期

            for(HomeWokePojo aa:list){
                aa.setCreateTimeTwo( dateFm.format(aa.getCreateTime()));
           }
            Date date=new Date();
            SimpleDateFormat sdf2= new SimpleDateFormat("yyyyMMddhhmmss");
            Workbook wb = exporter.exportToNew(list);
            OutputStream out = response.getOutputStream();
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("作业列表"+sdf2.format(date)+".xls", "UTF-8"));
            response.setContentType("application/msexcel");
            wb.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /*
   * 收作业
   */
    @RequestMapping("/shouye")
    public ModelAndView findByShouzuoye(@RequestParam Integer id,
                                        Model model,
                                        @RequestParam(value = "zhuangtai",required = false) Integer zhuangtai,
                                        @RequestParam String sub) {
        List<StudentHomeWoke> studentHomeWoke = homeWokeService.findByAllStudentAll(id,zhuangtai,null);
        String bathUrl ;
        if(sub.equals("list")){
            bathUrl= "/homeWoke/shouzuoyeList";
        }else{
            bathUrl= "/homeWoke/shouzuoye";
        }
        model.addAttribute("id",id);
        model.addAttribute("homeWoke", studentHomeWoke);
        ModelAndView modelAndView = new ModelAndView(bathUrl, model.asMap());
        return modelAndView;
    }

    /*
    * 跳转作业评分页面
    */
    @RequestMapping("/pingfenView")
    public ModelAndView findByShouzuoye(@RequestParam Integer id,
                                        @RequestParam Integer jobid,
                                        Model model){
        model.addAttribute("id",id);
        model.addAttribute("jobid",jobid);
        StudentHomeWoke homeWokePojo=homeWokeService.findByStudentId(id);
        model.addAttribute("homeWokePojo",homeWokePojo);
        ModelAndView modelAndView=new ModelAndView("/homeWoke/pingfen",model.asMap());
        return modelAndView;
    }
    /*
    *作业评分
    */
    @RequestMapping("/pingfen")
    @ResponseBody
    public String findZuoyePingfen(@CurrentUser UserInfo userInfo,
                                   @RequestParam(value = "pingyu",required = false) String pingyu,
                                   @RequestParam Integer id,
                                   @RequestParam(value="dengji",required = false) String dengji
                                   ) {
        StudentHomeWoke studentHomeWoke=new StudentHomeWoke();
       studentHomeWoke.setId(id);
        studentHomeWoke.setPingyu(pingyu);
        studentHomeWoke.setDengji(dengji);
        String str = homeWokeService.findByPingfen(studentHomeWoke);
        return str;
    }

    /*
    *结束收作业 ，跳转到详情页面
    */

    @RequestMapping("/jieshu")
    @ResponseBody
    public String findByJieshu(@RequestParam Integer id) {
        List<StudentHomeWoke> studentHomeWoke = homeWokeService.findByJieshu(id,null);
        return "success";
    }

    /*
    *补交
    */
    @RequestMapping("/bujiao")
    @ResponseBody
    public String findByBujiao(@CurrentUser UserInfo userInfo,
                                      @RequestParam Integer id) {
        Integer num=homeWokeService.updatebujiao(id,null);
        if(num>0){
            return "success";
        }else{
            return "shibai";
        }
    }

    /*
    * 判断是否已经收作业
    */
    @RequestMapping("/banduan")
    @ResponseBody
    public String findByBuId(@RequestParam Integer id) {
        HomeWokePojo num=homeWokeService.findById(id);
        if(num.getIsStats()==0){
            return "false";
        }else{
            return "success";
        }
    }


    /*
    * 扫码枪收作业
    */

    @RequestMapping("/saoMaQiang")
    @ResponseBody
    public String findByBuId(@RequestParam Integer studentId,
                             @RequestParam Integer jobId,
                             @RequestParam String dengji) {
        homeWokeService.updateSaoMaQiang(studentId,jobId,dengji);
        return "success";
    }


    /**
     *  查询今天是否已经不知过作业
     * @date 2022-10-24
     */
    @RequestMapping("/findByTimeView")
    public ModelAndView findByIdYiBuZhi(@CurrentUser UserInfo userInfo,Model model) {
       List<Map<String,Object>> map= basicSQLService.find("select * from pj_homewoke_settime where is_delete=0 and school_id="+userInfo.getSchoolId());
        model.addAttribute("timeset",map.get(0));
        return new ModelAndView("/homeWoke/timesetting",model.asMap());
    }
    /**
     *  修改作业布置时间
     * @date 2022-10-24
     */
    @RequestMapping("/updateTime")
    @ResponseBody
    public String updateTime(@CurrentUser UserInfo userInfo,@RequestParam String times,@RequestParam Integer id) {
        if(id!=null){
          Integer num=basicSQLService.update("update pj_homewoke_settime set modiy_time=NOW(),datetime='"+times+"' where id="+id);
            if(num>0){
                return "success";
            }else{
                return "error";
            }
        }else{
            Integer num=basicSQLService.create("insert INTO pj_homewoke_settime(school_id,datetime,create_time,modiy_time,is_delete) " +
                    "value("+userInfo.getSchoolId()+",'"+times+"',NOW(),NOW(),0)");
            if(num>0) {
                return "success";
            }else{
             return "error";
            }
        }
    }/**
     *  查询
     * @date 2022-10-24
     */
    @RequestMapping("/findTime")
    @ResponseBody
    public Map<String,Object> findTime(@CurrentUser UserInfo userInfo) {
       List<Map<String, Object>> mapList=basicSQLService.find("select * from pj_homewoke_settime where is_delete=0 and  school_id="+userInfo.getSchoolId());
        return mapList.get(0);
    }


    //查询今天是否已经不知过作业
    @RequestMapping("/findByIdYiBuZhi")
    @ResponseBody
    public String findByIdYiBuZhi(@CurrentUser UserInfo userInfo,
                                  HomeWokePojo homeWokePojo) {
        Date date = new Date();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
        //代替时间字段
        homeWokePojo.setPicturUrl(formatter.format(date));
        homeWokePojo.setSchoolId(userInfo.getSchoolId());
        homeWokePojo.setTeacherId(userInfo.getTeacherId());
        String str = homeWokeService.findByIdYiBuZhi(homeWokePojo);
        return str;
    }

}
