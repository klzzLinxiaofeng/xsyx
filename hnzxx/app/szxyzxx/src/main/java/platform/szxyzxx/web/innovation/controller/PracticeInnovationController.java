package platform.szxyzxx.web.innovation.controller;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.innovation.pojo.PracticeInnovation;
import platform.szxyzxx.innovation.service.PracticeInnovationService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/*
 * 实践创新
 */
@RestController
@RequestMapping("/practice/innovation")
public class PracticeInnovationController {
    @Autowired
    private PracticeInnovationService prainnService;
    @Autowired
    @Qualifier("fileService")
    protected FileService fileService;
    @Autowired
    private BasicSQLService basicSQLService;


    @RequestMapping(value = "/list/json")
    public List<Grade> jsonList(@CurrentUser UserInfo userInfo,
                                @RequestParam(value = "schoolYear",required = false) String schoolYear){
        return  prainnService.gradejsonList(userInfo.getTeacherId(),schoolYear);
    }

    @RequestMapping(value = "/student/json")
    public ModelAndView findByStudent(@CurrentUser UserInfo userInfo,
                                      @RequestParam(value = "teamId",required = false) Integer teamId,
                                      @RequestParam(value = "stuName",required = false) String stuName,
                                      @RequestParam(value = "schoolYear",required = false) String schoolYear,
                                      @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                      @ModelAttribute("page") Page page,
                                      @RequestParam(value = "sub", required = false) String sub,
                                      Model model){
        List<Student> list = prainnService.findByStudent(userInfo, gradeId, schoolYear, teamId,stuName, page);
        model.addAttribute("list", list);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/innovation/list";
        } else {
            bathUrl = "/innovation/index";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    /*
    * 编辑和查看
    */
    @RequestMapping(value = "/innovation/inputOrchakan")
    public ModelAndView findByStudent(@CurrentUser UserInfo userInfo,
                                      @RequestParam(value = "id" ,required = false) Integer id,
                                      @RequestParam(value = "sub" ,required = false) String sub,
                                      Model model){
        PracticeInnovation practiceInnovations= prainnService.findByPraInner(userInfo,id);
        if (practiceInnovations.getPctreId() != null && !practiceInnovations.getPctreId().equals("")) {

            String [] uuid=practiceInnovations.getPctreId().split(",");
            Map<String,String> map=new HashMap<>();
            // 根据id查询封面的url
            for(int a=0;a<uuid.length;a++){
                FileResult file = fileService.findFileByUUID(uuid[a]);
                if (file != null) {
                    map.put(uuid[a],file.getHttpUrl());
                }
            }
            practiceInnovations.setPicter(map);
        }
        if (practiceInnovations.getJiangzhuanId() != null && !practiceInnovations.getJiangzhuanId().equals("")) {

            String[] uuid = practiceInnovations.getJiangzhuanId().split(",");
            Map<String, String> map = new HashMap<>();
            // 根据id查询奖状的url
            for (int a = 0; a < uuid.length; a++) {
                FileResult file = fileService.findFileByUUID(uuid[a]);
                if (file != null) {
                    map.put(uuid[a], file.getHttpUrl());
                }
            }
            practiceInnovations.setJiangzhuanPicter(map);
        }
        model.addAttribute("practiceInnovations",practiceInnovations);
        String bathUrl;
        if (sub.equals("inputs")) {
            bathUrl = "/innovation/input";
        } else {
            bathUrl = "/innovation/chakan";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    /*
     * 查看学生所有科学创新
     */
    @RequestMapping(value = "/chakan")
    public ModelAndView findByAestheticAll(@CurrentUser UserInfo userInfo,
                                           @RequestParam(value = "studentId" ,required = false) Integer studentId,
                                           @RequestParam(value = "schoolYear" ,required = false) String schoolYear,
                                           @RequestParam(value = "schoolTrem" ,required = false) String schoolTrem,
                                           @RequestParam(value = "sub" ,required = false) String sub,
                                           @ModelAttribute Page page,
                                           Model model){
        List<PracticeInnovation>   practiceInnovations=
                prainnService.findByPraInnerAll(userInfo,studentId,schoolYear,schoolTrem);
        model.addAttribute("practiceInnovations",practiceInnovations);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/innovation/chakanlist";
        } else {
            bathUrl = "/innovation/chakanindex";
        }
        model.addAttribute("studentId",studentId);
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    /*
    * 添加科学创新
    */
    @RequestMapping(value = "/innovation/input")
    public ModelAndView createView(@RequestParam Integer studentId,
                                      Model model){
        List<Map<String,Object>> list=basicSQLService.find("select name from pj_student where id="+studentId);
        model.addAttribute("studentId",studentId);
        model.addAttribute("studentName",list.get(0).get("name").toString());
        String bathUrl = "/innovation/bianji";

        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    @RequestMapping(value = "/inputCreate")
    public String createOrupdate(@CurrentUser UserInfo userInfo,
                                 PracticeInnovation practiceInnovation) {
        practiceInnovation.setSchoolYear(userInfo.getSchoolYear());
        practiceInnovation.setSchoolTrem(userInfo.getSchoolTermCode());
        String falg = prainnService.createOrupdate(practiceInnovation,userInfo);
        return falg;
    }
}
