package platform.szxyzxx.web.mentalhealth.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.mentalhealth.service.MentalBehaviorService;
import platform.szxyzxx.mentalhealth.service.MentalIndicatorService;
import platform.szxyzxx.mentalhealth.vo.MentalBehavior;
import platform.szxyzxx.mentalhealth.vo.MentalIndicators;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/mentalBehavior")
public class MentalBehaviorController {
    @Autowired
    private MentalBehaviorService mentalBehaviorService;
    @Autowired
    private MentalIndicatorService mentalIndicatorService;
    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                  @ModelAttribute Page page,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  QueryPojo queryPojo,
                                  Model model){
        if(queryPojo.getSchoolYear()==null || queryPojo.getSchoolYear().equals("")){
            queryPojo.setSchoolYear(userInfo.getSchoolYear());
            queryPojo.setSchoolTrem(userInfo.getSchoolTermCode());
        }
        List<MentalBehavior> list=mentalBehaviorService.findByAll(queryPojo,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/mentalhealth/mentalindicatorsstudent/list";
        }else{
            batUrl="/mentalhealth/mentalindicatorsstudent/index";
        }
        model.addAttribute("list",list);
        return new ModelAndView(batUrl,model.asMap());
    }


    /**
     *
     * @param user
     * @param page
     * @param schoolYear  学年
     * @param schoolTrem
     * @param gradeId
     * @param stuName
     * @param sub
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public ModelAndView feindByList(@CurrentUser UserInfo user,
                                    @ModelAttribute("page") Page page,
                                    @RequestParam(value = "schoolTrem", required = false) String schoolTrem,
                                    @RequestParam(value = "schoolYear", required = false) String schoolYear,
                                    @RequestParam(value = "teamId", required = false) Integer teamId,
                                    @RequestParam(value = "gradeId", required = false) Integer gradeId,
                                    @RequestParam(value = "stuName", required = false) String stuName,
                                    @RequestParam(value = "sub", required = false) String sub,
                                    Model model){
        List<MentalBehavior> list=mentalBehaviorService.findByAllTongji(user,schoolYear,schoolTrem,gradeId,teamId,stuName,page);
        model.addAttribute("list",list);
        String bathUrl;
        if(sub.equals("list")){
            bathUrl="/mentalhealth/studenttongjiList";
        }else{
            bathUrl="/mentalhealth/studenttongji";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    @RequestMapping("/list/indexx")
    public List<MentalIndicators> feindByList(@CurrentUser UserInfo userInfo,
                                              @RequestParam(value = "schoolYear",required = false) String schoolYear,
                                              @RequestParam(value = "schoolTrem",required = false) String schoolTrem){
        if(schoolYear!=null && schoolTrem!=null){
            userInfo.setSchoolTermCode(schoolTrem);
            userInfo.setSchoolYear(schoolYear);
        }
        List<MentalIndicators> list2= mentalIndicatorService.findByAll(userInfo,null);
        return list2;
    }
}
