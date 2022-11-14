package platform.szxyzxx.web.huojiang.controller;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.service.storage.service.FileService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.huojiang.service.HuoJiangService;
import platform.szxyzxx.huojiang.vo.HuoJiang;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/huojiang")
public class HuoJiangController {
        @Autowired
    private HuoJiangService huoJiangService;
    @Autowired
    @Qualifier("fileService")
    private FileService fileService;
    @Autowired
    private BasicSQLService basicSQLService;

        @RequestMapping("/findByAll")
        public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                      @RequestParam Integer type,
                                      @RequestParam(value = "isShenhe",required = false) Integer isShenhe,
                                      @RequestParam(value = "shenqingren",required = false) String shenqingren,
                                      @RequestParam(value = "sub",required = false) String sub,
                                      @ModelAttribute Page page, Model model){
            List<Map<String, Object>> list=basicSQLService.find("select yr.* from yh_user_role yur inner join yh_role yr on yr.id=yur.role_id where yur.is_deleted=0 and  yur.user_id="+userInfo.getId());
            for(Map<String, Object> aa:list){
                if(aa.get("code").toString().equals("SCHOOL_MANAGER")  ||aa.get("code").toString().equals("SCHOOL_MASTER")){
                    model.addAttribute("flag",true);
                }
            }
            List<HuoJiang> huoJiangList=new ArrayList<>();
            if(type==2){
                huoJiangList=huoJiangService.findByAll( 2 ,shenqingren,userInfo.getRealName(),page);
            }else{
                huoJiangList=huoJiangService.findByAll(type,shenqingren,null,page);
            }
            model.addAttribute("id",type);
            model.addAttribute("userInfo",userInfo);
            for(HuoJiang aa:huoJiangList){
                List<Map<String,Object>> listw=new ArrayList<>();
                Map<String,Object> map =new HashMap<>();
                if(aa.getPictureId()!=null){
                    String [] fujianarr=aa.getPictureId().split(",");
                    for(int i=0;i<fujianarr.length;i++){
                        FileResult file = fileService.findFileByUUID(fujianarr[i]);
                        if (file != null) {
                            map.put("url",file.getHttpUrl());
                            map.put("uuid",fujianarr[i]);
                        }
                        listw.add(map);
                    }
                }
                aa.setPictureList(listw);
            }
            model.addAttribute("list",huoJiangList);
            String batUrl="";
            if(sub!=null){
                if(sub.equals("list")){
                    batUrl="/jiangxiang/huojiang/list";
                }else{
                    batUrl="/jiangxiang/huojiang/index";
                }
            }else {
                batUrl="/jiangxiang/huojiang/index";
            }
            return new ModelAndView(batUrl,model.asMap());
        }

        @RequestMapping("/createAndUpdate")
        public ModelAndView create(@RequestParam Integer type,Model model){
            model.addAttribute("type",type);
            return new ModelAndView("/jiangxiang/huojiang/input", model.asMap());
        }
        /*
        * 添加
        */
        @RequestMapping("/create")
        public String create(@CurrentUser UserInfo userInfo,HuoJiang huoJiang){
            huoJiang.setShenqingren(userInfo.getId());
            huoJiang.setShenqingName(userInfo.getRealName());
            huoJiang.setSchoolYear(userInfo.getSchoolYear());
            huoJiang.setSchoolTrem(userInfo.getSchoolTermCode());
           Integer num= huoJiangService.create(huoJiang);
           if(num>0){
               return "success";
           }else{
               return "error";
           }
        }
        /*
        * 删除
        */
        @RequestMapping("/deleteHuojiang")
        public String updateHuoJiang(@RequestParam Integer id){
            Integer num= huoJiangService.updateHuoJiang(id);
            if(num>0){
                return "success";
            }else{
                return "error";
            }
        }
        /*
         *学生列表
         */
        @RequestMapping("/findByStudent")
        public List<Map<String ,Object>> findByStudent(@CurrentUser UserInfo userInfo,
                                            @RequestParam(value ="studentName",required = false) String studentName,
                                            @RequestParam String sub,
                                           @RequestParam(value ="gradeId",required = false) Integer gradeId,
                                           @RequestParam(value ="teamId",required = false) Integer teamId){
            String sql="select ps.* from pj_student ps inner join pj_team_student pts on pts.student_id=ps.id  inner join pj_grade pg on pg.id=pts.grade_id where ps.is_delete=0 and pg.school_year="+userInfo.getSchoolYear();
            if(gradeId!=null){
                sql+=" and  pts.grade_id="+gradeId;
            }
            if(teamId!=null){
                sql+="  and pts.team_id="+teamId;
            }
            if(studentName!=null){
                sql+="  and ps.name like '%"+studentName+"%'";
            }
            sql+= " group by ps.id  order by ps.team_id,ps.emp_code asc";
            List<Map<String ,Object>> mapList=basicSQLService.find(sql);
            return mapList;
        }

        /*
         *教师列表
         */
        @RequestMapping("/findByTeacher")
        public  List<Map<String ,Object>> findByTeacher(@RequestParam String sub,
                                                        @RequestParam(value ="teacherName",required = false) String teacherName){
            String sql="select * from pj_teacher where is_delete=0";
            if(teacherName!=null){
                sql+=" and name like '%"+teacherName+"%'";
            }
            List<Map<String ,Object>> mapList=basicSQLService.find(sql);
            return mapList;
        }
    /*
     *年级列表
     */
    @RequestMapping("/findByGrade")
    public  List<Map<String ,Object>> findByGrade(@CurrentUser UserInfo userInfo){
        String sql="select * from pj_grade where is_deleted=0 and school_year="+userInfo.getSchoolYear()+" and school_id="+userInfo.getSchoolId() +"  ORDER BY stage_code, grade_number";
        List<Map<String ,Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }
    /*
     *班级列表
     */
    @RequestMapping("/findByTeam")
    public  List<Map<String ,Object>> findByTeam(@CurrentUser UserInfo userInfo,
                                                 @RequestParam(value = "gradeId",required = false) Integer gradeId){
        String sql="select * from pj_team where is_delete=0  and school_id="+userInfo.getSchoolId();
        if(gradeId!=null){
            sql+=" and grade_id="+gradeId;
        }
        List<Map<String ,Object>> mapList=basicSQLService.find(sql);
        return mapList;
    }

    /*
     *审核
     */
    @RequestMapping("/shenhe")
    public String shenhe(@RequestParam Integer id,
                         @RequestParam Integer zhuantai){
        Integer num= huoJiangService.updateShenHe(id,zhuantai);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }

    /*
     *获奖级别列表
     */
    @RequestMapping("/huoJiangJiBie")
    public List<Map<String,Object>> huoJiangJiBie(){
        String sql="select id,name from zy_jixiaoscore where is_delete=0 and jibie=1";
       return basicSQLService.find(sql);
    }
    /*
     *获奖等次列表
     */
    @RequestMapping("/huojiangdengci")
    public List<Map<String,Object>> huojiangdengci(){
        String sql="select id,name from zy_jixiaoscore where is_delete=0 and jibie=2";
        return basicSQLService.find(sql);
    }
        /*
        * 详情
        * */
    @RequestMapping("/huojiangxiangqing")
    public ModelAndView huojiangxiangqing(@RequestParam Integer id,Model model){
        HuoJiang huoJiang=huoJiangService.findById(id);
        List<Map<String,Object>> listw=new ArrayList<>();
        if(huoJiang.getPictureId()!=null){
            String [] fujianarr=huoJiang.getPictureId().split(",");
            for(int i=0;i<fujianarr.length;i++){
                Map<String,Object> map =new HashMap<>();
                FileResult file = fileService.findFileByUUID(fujianarr[i]);
                if (file != null) {
                    map.put("url",file.getHttpUrl());
                    map.put("uuid",fujianarr[i]);
                }
                listw.add(map);
            }
        }
        huoJiang.setPictureList(listw);
        model.addAttribute("huoJiang",huoJiang);
        return new ModelAndView("/jiangxiang/huojiang/xiangqing", model.asMap());
    }

}
