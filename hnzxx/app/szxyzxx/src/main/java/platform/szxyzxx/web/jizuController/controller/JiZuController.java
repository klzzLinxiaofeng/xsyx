/*
package platform.szxyzxx.web.jizuController.controller;


import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.jizuBiao.pojo.JiZuBiao;
import platform.szxyzxx.jizuBiao.pojo.JiZuTeacherBiao;
import platform.szxyzxx.jizuBiao.pojo.TeacherWoke;
import platform.szxyzxx.jizuBiao.service.JiZuBiaoService;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/jizu")
public class JiZuController {

    @Autowired
    private JiZuBiaoService jiZuBiaoService;
    @Autowired
    private BasicSQLService basicSQLService;

    * 跳转


    @RequestMapping("/indexView")
    public ModelAndView findByAll(){
        return  new ModelAndView("/jizuBiao/jiindex");
    }

    * 整体数据


    @RequestMapping("/AllJiZu")
    public List<Map<String,Object>> findByAll(@RequestParam String schoolYear,
                                              @RequestParam String schoolTrem,
                                              @RequestParam Integer zhoushu){
        return jiZuBiaoService.getAll(schoolYear,schoolTrem,zhoushu,null);
    }
    *查询所有级组


    @RequestMapping("/getAllJiZu")
    public List<JiZuBiao> findByAllJiZu(){
        return jiZuBiaoService.findByAllJiZu();
    }
    * 添加级组


    @RequestMapping("/createJiZu")
    public String createJiZu(JiZuBiao jiZuBiao){
        return jiZuBiaoService.create(jiZuBiao);
    }
     * 修改级组


    @RequestMapping("/updateJiZu")
    public String updateJiZu(@RequestParam String name,
                             @RequestParam Integer id){
        JiZuBiao jiZuBiao=new JiZuBiao();
        jiZuBiao.setId(id);
        jiZuBiao.setJizuName(name);
        return jiZuBiaoService.updateJiZuBiao(jiZuBiao);
    }
     * 删除级组


    @RequestMapping("/deleteJiZu")
    public String deleteJiZu(@RequestParam Integer id){
        JiZuBiao jiZuBiao=new JiZuBiao();
        jiZuBiao.setId(id);
        jiZuBiao.setIsDelete(1);
        return jiZuBiaoService.deleteJiZuBiao(jiZuBiao);
    }

     * 级组添加教师


    @RequestMapping("/addTeacherJiZu")
    public String addTeacherJiZu(@RequestParam Integer teacherId,@RequestParam Integer jiZuId){
        JiZuTeacherBiao jiZuTeacherBiao=new JiZuTeacherBiao();
        jiZuTeacherBiao.setJizuId(jiZuId);
        jiZuTeacherBiao.setTeacherId(teacherId);
        return jiZuBiaoService.createTeacherJiZu(jiZuTeacherBiao);
    }




    @RequestMapping("/updateTeacherJiZu")
    public String updateTeacherJiZu(@RequestParam Integer id,
                                    @RequestParam Integer teacherId,
                                    @RequestParam Integer jizuId){
        JiZuTeacherBiao jiZuTeacherBiao=new JiZuTeacherBiao();
        jiZuTeacherBiao.setId(id);
        jiZuTeacherBiao.setTeacherId(teacherId);
        return jiZuBiaoService.updateTeacherJiZu(jiZuTeacherBiao,jizuId);
    }


    @RequestMapping("/updateTeacherJiZuZhang")
    public String updateTeacherJiZuZhang(@RequestParam Integer teacherId,@RequestParam Integer id){
        return jiZuBiaoService.updateTeacherJiZuZhang(teacherId, id);
    }

    @RequestMapping("/findByAllTeacherId")
    public List<TeacherWoke> findByAllTeacherId(@RequestParam Integer teacherId){
        return jiZuBiaoService.findByAllTeacherId(teacherId);
    }
    @RequestMapping("/addTeacherWoke")
    public String addTeacherWoke(TeacherWoke teacherWoke){
        return jiZuBiaoService.createTeacherWoke(teacherWoke);
    }
    @RequestMapping("/deleteTeacherWoke")
    public String deleteTeacherWoke(@RequestParam Integer id){
        return jiZuBiaoService.updateDelete(id);
    }



    @RequestMapping("/updateTeacherWoke")
    public String updateTeacherWoke(@RequestParam Integer id,@RequestParam String content){
        return jiZuBiaoService.updateTeacherWoke(id,content);
    }



    @RequestMapping("/daochuWoke")
    public String daochuWoke(@RequestParam String schoolYear,
                             @RequestParam String schoolTerm,
                             @RequestParam Integer zhoushu,
                             HttpServletResponse response, HttpServletRequest request){
            List<Object> list = new ArrayList();
            List<TeacherWoke> list2 = jiZuBiaoService.findByDaoChu(schoolYear,schoolTerm,zhoushu);
            ParseConfig config = SzxyExcelTookit.getConfig("teacherWoke");
            StringBuffer excelName = new StringBuffer();
            excelName.append("第"+zhoushu+"周级组工作安排表.xls");
            String filename = excelName.toString();
            try {
                for (TeacherWoke studentScoreVo : list2) {
                    list.add(studentScoreVo);
            }
            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "success";
    }
     * 教师列表


    @RequestMapping("/teacherAll")
    public ModelAndView teacherAll(@RequestParam(value = "teacherName",required = false) String teacherName,
                                   @RequestParam(value = "bumenId",required = false) Integer bumenId,
                                   @RequestParam(value = "subjectId",required = false) String subjectId,
                                   @RequestParam(value = "jiZuId",required = false) Integer jiZuId,
                                   Page page,
                                   @RequestParam(value = "sub",required = false) String sub,
                                   Model model){
        List<Teacher> list=jiZuBiaoService.teacherAll(teacherName,bumenId,subjectId,jiZuId,page);
        model.addAttribute("list",list);
        model.addAttribute("jiZuId",jiZuId);
        String bath="";
        if(sub.equals("list")){
            bath="/jizuBiao/list";
        }else{
            bath="/jizuBiao/index";
        }
        return new ModelAndView(bath,model.asMap());
    }
     * 所有部门


    @RequestMapping("/depentAll")
    public List<Map<String,Object>> depentAll(@CurrentUser UserInfo userInfo){
        return basicSQLService.find("select * from pj_department where school_id= 215 and is_delete=0");
    }
     * 所有科目


    @RequestMapping("/subjectAll")
    public List<Map<String,Object>> subjectAll(@CurrentUser UserInfo userInfo){
        return basicSQLService.find("select * from pj_subject   where school_id= 215 and is_delete=0");
    }

     * 级组跳转


    @RequestMapping("/jizuBian")
    public ModelAndView subjectAll(@RequestParam Integer id,@RequestParam String name, Model model){
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return new ModelAndView("/jizuBiao/jizuBianji",model.asMap());
    }
     * 教师工作类容修改


    @RequestMapping("/contontBianji")
    public ModelAndView contontBianji(@RequestParam Integer id,@RequestParam String name, Model model){
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return new ModelAndView("/jizuBiao/zuoyecontont",model.asMap());
    }


}
*/
