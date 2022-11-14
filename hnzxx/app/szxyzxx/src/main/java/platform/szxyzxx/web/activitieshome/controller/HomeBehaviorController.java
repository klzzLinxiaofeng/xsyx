package platform.szxyzxx.web.activitieshome.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.activitieshome.service.HomeBehaviorService;
import platform.szxyzxx.activitieshome.service.HomeIndicatorService;
import platform.szxyzxx.activitieshome.vo.HomeBehavior;
import platform.szxyzxx.activitieshome.vo.HomeIndicators;
import platform.szxyzxx.lifebehavior.vo.QueryPojo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:31
 * @Version 1.0
 */
@RestController
@RequestMapping("/homeBehavior")
public class HomeBehaviorController {
    @Autowired
    private HomeBehaviorService homeBehaviorService;
    @Autowired
    private HomeIndicatorService homeIndicatorService;
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
        List<HomeBehavior> list=homeBehaviorService.findByAll(queryPojo,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/activitieshome/homeindicatorsstudent/list";
        }else{
            batUrl="/activitieshome/homeindicatorsstudent/index";
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
        List<HomeBehavior> list=homeBehaviorService.findByAllTongji(user,schoolYear,schoolTrem,gradeId,teamId,stuName,page);
        model.addAttribute("list",list);
        String bathUrl;
        if(sub.equals("list")){
            bathUrl="/activitieshome/studenttongjiList";
        }else{
            bathUrl="/activitieshome/studenttongji";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    @RequestMapping("/list/indexx")
    public List<HomeIndicators> feindByList(@CurrentUser UserInfo userInfo,
                                            @RequestParam(value = "schoolYear",required = false) String schoolYear,
                                            @RequestParam(value = "schoolTrem",required = false) String schoolTrem){
        if(schoolYear!=null && schoolTrem!=null){
            userInfo.setSchoolTermCode(schoolTrem);
            userInfo.setSchoolYear(schoolYear);
        }
        List<HomeIndicators> list2= homeIndicatorService.findByAll(userInfo,null);
        return list2;
    }
}
