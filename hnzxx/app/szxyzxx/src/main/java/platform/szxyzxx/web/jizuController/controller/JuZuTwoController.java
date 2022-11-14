package platform.szxyzxx.web.jizuController.controller;

import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
import platform.szxyzxx.notice.service.SystemWechatNotifyService;
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
@RequestMapping("/juzuAndjiaozhigong")
/*
* 级组工作表与部门工作表合并
*/
public class JuZuTwoController {
    @Autowired
    private JiZuBiaoService jiZuBiaoService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    @Qualifier("asyncWechatNoticeService")
    private SystemWechatNotifyService notifyService;
    /*
     * 跳转
     */
    @RequestMapping("/indexView")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,Model model){
        List<Map<String,Object>> mapList2=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id   where 1=1  and yur.user_id="+userInfo.getId());
            for(Map<String,Object> aa:mapList2){
                if(aa.get("code").equals("XING_ZHENG_REN_YUAN")){
                    model.addAttribute("isGuanli","true");
                    return  new ModelAndView("/woke/zhengheBiao",model.asMap());
                }else {
                    model.addAttribute("isGuanli","false");
                }
            }

        return  new ModelAndView("/woke/zhengheBiao",model.asMap());
    }
     //教职工
    @RequestMapping("/AllJiaoZhiGong")
    public List<Map<String,Object>> findByAll(@RequestParam Integer jizuId,
                                              @RequestParam String schoolYear,
                                              @RequestParam String schoolTrem,
                                              @RequestParam Integer zhoushu){
        return jiZuBiaoService.getAll(schoolYear,schoolTrem,zhoushu,jizuId);
    }
    //后勤
    @RequestMapping("/Allhouqin")
    public List<Map<String,Object>> findByHouQin(
                                                 @RequestParam Integer jizuId,
                                                 @RequestParam String schoolYear,
                                              @RequestParam String schoolTrem,
                                              @RequestParam Integer zhoushu){
        return jiZuBiaoService.getAll(schoolYear,schoolTrem,zhoushu,jizuId);
    }
    //级组列表
    @RequestMapping("/AllJiZu")
    public List<Map<String,Object>> AllJiZu(@CurrentUser UserInfo userInfo,
                                            @RequestParam String schoolYear,
                                              @RequestParam String schoolTrem,
                                              @RequestParam Integer zhoushu){
        return jiZuBiaoService.AllJiZu(schoolYear,schoolTrem,zhoushu,userInfo);
    }
    //级组列表
    @RequestMapping("/getAllJiZu")
    public List<JiZuBiao> findByAllJiZu(@RequestParam(value = "jizuId",required = false) Integer jizuId){
        if(jizuId!=null){
            return jiZuBiaoService.findByAllJiZu(jizuId,1);
        }else{
            return jiZuBiaoService.findByAllJiZu(jizuId,0);
        }
    }

    /*
     * 级组添加教师
     */
    @RequestMapping("/addTeacherJiZu")
    public String addTeacherJiZu(@RequestParam Integer teacherId,@RequestParam Integer jiZuId){
        JiZuTeacherBiao jiZuTeacherBiao=new JiZuTeacherBiao();
        jiZuTeacherBiao.setJizuId(jiZuId);
        jiZuTeacherBiao.setTeacherId(teacherId);
        return jiZuBiaoService.createTeacherJiZu(jiZuTeacherBiao);
    }

    /*
     * 级组删除教师
     */
    @RequestMapping("/updateTeacherJiZu")
    public String updateTeacherJiZu(@RequestParam Integer id,
                                    @RequestParam Integer teacherId,
                                    @RequestParam Integer jizuId){
        JiZuTeacherBiao jiZuTeacherBiao=new JiZuTeacherBiao();
        jiZuTeacherBiao.setId(id);
        jiZuTeacherBiao.setTeacherId(teacherId);
        return jiZuBiaoService.updateTeacherJiZu(jiZuTeacherBiao,jizuId);
    }
    /*
     * 设置级组组长
     */
    @RequestMapping("/updateTeacherJiZuZhang")
    public String updateTeacherJiZuZhang(@RequestParam Integer teacherId,@RequestParam Integer id){
        return jiZuBiaoService.updateTeacherJiZuZhang(teacherId, id);
    }
    /*
     * 根据教师id查询
     */
    @RequestMapping("/findByAllTeacherId")
    public List<TeacherWoke> findByAllTeacherId(@RequestParam Integer teacherId,
                                                @RequestParam String schoolYear,
                                                @RequestParam String schoolTrem,
                                                @RequestParam Integer zhoushu){
        return jiZuBiaoService.findByAllTeacherId(teacherId,schoolYear,schoolTrem,zhoushu);
    }

    /*
     * 教师列表
     */
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
            bath="/woke/list";
        }else{
            bath="/woke/index";
        }
        return new ModelAndView(bath,model.asMap());
    }
    /*
     * 所有部门
     */
    @RequestMapping("/depentAll")
    public List<Map<String,Object>> depentAll(@CurrentUser UserInfo userInfo){
        return basicSQLService.find("select * from pj_department where school_id= 215 and is_delete=0");
    }
    /*
     * 所有科目
     */
    @RequestMapping("/subjectAll")
    public List<Map<String,Object>> subjectAll(@CurrentUser UserInfo userInfo){
        return basicSQLService.find("select * from pj_subject   where school_id= 215 and is_delete=0");
    }

    /*
     * 级组跳转
     */
    @RequestMapping("/jizuBian")
    public ModelAndView subjectAll(@RequestParam Integer id,@RequestParam String name, Model model){
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return new ModelAndView("/woke/jizuBianji",model.asMap());
    }


    /*
     * 添加级组
     */
    @RequestMapping("/createJiZu")
    public String createJiZu(JiZuBiao jiZuBiao){
        return jiZuBiaoService.create(jiZuBiao);
    }
    /*
     * 修改级组
     */
    @RequestMapping("/updateJiZu")
    public String updateJiZu(@RequestParam String name,
                             @RequestParam Integer id){
        JiZuBiao jiZuBiao=new JiZuBiao();
        jiZuBiao.setId(id);
        jiZuBiao.setJizuName(name);
        return jiZuBiaoService.updateJiZuBiao(jiZuBiao);
    }
    /*
     * 删除级组
     */
    @RequestMapping("/deleteJiZu")
    public String deleteJiZu(@RequestParam Integer id){
        JiZuBiao jiZuBiao=new JiZuBiao();
        jiZuBiao.setId(id);
        jiZuBiao.setIsDelete(1);
        return jiZuBiaoService.deleteJiZuBiao(jiZuBiao);
    }
    /*
     * 添加子级组
     */
    @RequestMapping("/createZiJiZu")
    public String createZiJiZu(JiZuBiao jiZuBiao){
        return jiZuBiaoService.createZiJiZu(jiZuBiao);
    }
    /*
     * 添加教师工作类容
     */
    @RequestMapping("/addTeacherWoke")
    public String addTeacherWoke(TeacherWoke teacherWoke){
       String num= jiZuBiaoService.createTeacherWoke(teacherWoke);

       return num;
    }

    /*
     * 删除教师工作类容
     */
    @RequestMapping("/deleteTeacherWoke")
    public String deleteTeacherWoke(@RequestParam Integer id){
        return jiZuBiaoService.updateDelete(id);
    }
    /*
     * 修改教师工作类容
     */
    @RequestMapping("/updateTeacherWoke")
    public String updateTeacherWoke(@RequestParam Integer id,@RequestParam String content){
        return jiZuBiaoService.updateTeacherWoke(id,content);
    }
    /*
     * 教师工作类容修改
     */
    @RequestMapping("/contontBianji")
    public ModelAndView contontBianji(@RequestParam Integer id,@RequestParam String name, Model model){
        model.addAttribute("id",id);
        model.addAttribute("name",name);
        return new ModelAndView("/woke/zuoyecontont",model.asMap());
    }

    /*
     * 添加全组教师工作类容，子集组不受父级组影响
     */
    @RequestMapping("/addTeacherAllWoke")
    public String addTeacherAllWoke(TeacherWoke teacherWoke){
        return jiZuBiaoService.addTeacherAllWoke(teacherWoke);

    }

    /*
     * 导出
     */
    @RequestMapping("/daochuWoke")
    public String daochuWoke(@RequestParam String schoolYear,
                             @RequestParam String schoolTerm,
                             @RequestParam Integer zhoushu,
                             HttpServletResponse response, HttpServletRequest request){
        List<Object> list = new ArrayList();
        List<TeacherWoke> list2 = jiZuBiaoService.findByDaoChu(schoolYear,schoolTerm,zhoushu);
        ParseConfig config = SzxyExcelTookit.getConfig("teacherWoke");
        StringBuffer excelName = new StringBuffer();
        excelName.append("第"+zhoushu+"周教职工事项交办一览表.xls");
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


}
