package platform.szxyzxx.web.bobao.controller;

import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.bobao.service.BoBaoDaPingService;
import platform.szxyzxx.bobao.service.BoBaoTimeService;
import platform.szxyzxx.bobao.vo.BoBaoDaPing;
import platform.szxyzxx.bobao.vo.BoBaoTime;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/GuangBo")
public class BoBaoController {
    Logger log = LoggerFactory.getLogger(BoBaoController.class);

    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private BoBaoTimeService boBaoTimeService;
    @Autowired
    private BoBaoDaPingService boBaoDaPingService;

    @RequestMapping("/bobaoTime")
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
        log.info("播报时间大小"+list.size());
        return list;
    }


    /*
     * 添加播报时间
     *
     */
    @RequestMapping("/createBoBaoTime")
    public String createBoBaoTime(
                                  @RequestParam("haoma") String haoma,
                                  @RequestParam("startTime") String startTime,
                                  @RequestParam("endTime") String endTime,
                                  @RequestParam(value = "id",required = false) Integer id,
                                  @CurrentUser UserInfo user){
        log.info("播报"+haoma+endTime+id);
        BoBaoTime boBaoTime =new BoBaoTime();
        if(id!=null){
            boBaoTime.setId(id);
            boBaoTime.setHaoma(haoma);
            boBaoTime.setStartTime(startTime);
            boBaoTime.setEndTime(endTime);
            Integer falg= boBaoTimeService.updateBoBaoTime(boBaoTime);
            if(falg>0){
                return "success";
            }
            return "error";
        }else{
            boBaoTime.setHaoma(haoma);
            boBaoTime.setStartTime(startTime);
            boBaoTime.setEndTime(endTime);
            Integer falg= boBaoTimeService.createBoBaoTime(boBaoTime);
            if(falg>0){
                return "success";
            }
            return "error";
        }
    }
    /*
     * 删除播报时间
     *
     */
    @RequestMapping("/deleteBoBaoTime")
    public String deleteBoBaoTime(@RequestParam("id") Integer id,
                                  @CurrentUser UserInfo user){
        if(id!=null){
            Integer falg=boBaoTimeService.deleteBoBaoTime(id);
            if(falg>0){
                return "success";
            }
            return "error";
        }
        return "参数为空";
    }


    /*
     * 播报大屏年级设置
     *
     */
    @RequestMapping("/viewDaPing")
    public ModelAndView findByAllSeet(@CurrentUser UserInfo userInfo,
                                      @RequestParam String sub,
                                      @ModelAttribute("page") Page page,
                                      Model model){
        List<BoBaoDaPing> boBaoDaPings=boBaoDaPingService.findByAll(userInfo);

        String view="";
        if(sub.equals("list")){
            view="/bus/daping/list";
        }else{
            view="/bus/daping/index";
        }
        model.addAttribute("items",boBaoDaPings);
        return  new ModelAndView(view,model.asMap());
    }
    /*
     * 播报大屏年级设置
     *
     */
    @RequestMapping("/updateViewDaPing")
    public ModelAndView updateViewDaPing(@RequestParam Integer id,
                                     Model model){
        BoBaoDaPing boBaoDaPings=boBaoDaPingService.findById(id);
        String view="";
            view="/bus/daping/input";
        model.addAttribute("boBaoDaPings",boBaoDaPings);
        return  new ModelAndView(view,model.asMap());
    }

    /*
     * 修改播报大屏年级设置
     *
     */
    @RequestMapping("/updateDaPing")
    public String  updateViewDaPing(BoBaoDaPing boBaoDaPing,
                                         @CurrentUser UserInfo userInfo){
        String [] gardeIds=boBaoDaPing.getGradeNames().split(",");
        boBaoDaPing.setGradeIds(boBaoDaPingService.findByGradeName(gardeIds,userInfo.getSchoolYear()));
       Integer num=boBaoDaPingService.updateViewDaPing(boBaoDaPing,userInfo);
       if(num>0){
           return "success" ;
       }else{
          return "fail";
       }

    }
    private Integer status = 0;
    /**
     * @Params：[]
     * @Date：16:53 2020/9/21
     * @Description： 通过schoolId查询相关的年级信息
     */
    @RequestMapping(value = "/grades", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, List<Grade>> findGradeBySchoolId(@CurrentUser UserInfo user,
                                                        Model model) {
        Map<String, List<Grade>> map = new HashMap<>();

        try {
            List<Grade> gradeAll = boBaoDaPingService.findGradeBySchoolId(user.getSchoolId(),user.getSchoolYear());
            for (Grade grade : gradeAll) {
                grade.setStatus(this.status);
            }
            map.put("grade", gradeAll);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
