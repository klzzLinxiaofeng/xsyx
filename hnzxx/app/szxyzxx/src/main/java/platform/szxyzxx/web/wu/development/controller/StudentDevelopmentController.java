package platform.szxyzxx.web.wu.development.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wuyu.development.service.StudentDevelopmentService;
import platform.szxyzxx.wuyu.development.vo.StudentDevelopment;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 17:17
 * @Version 1.0
 */
@RestController
@RequestMapping("/studentDevelopment")
public class StudentDevelopmentController {
    @Autowired
    private StudentDevelopmentService studentDevelopmentService;

    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@RequestParam(value = "schoolYear",required = false) String schoolYear,
                                  @RequestParam(value = "schoolTrem",required = false) String schoolTrem,
                                  @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                  @RequestParam(value = "teamId",required = false) Integer teamId,
                                  @RequestParam(value = "zhibiaoId",required = false) Integer zhibiaoId,
                                  @RequestParam(value = "stuName",required = false) String stuName,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  @CurrentUser UserInfo userInfo,
                                  @ModelAttribute("page")Page page,
                                  Model model){
        List<StudentDevelopment> list=new ArrayList<>();
        if(schoolYear==null || schoolTrem==null){
             list=studentDevelopmentService.findByAll(userInfo.getSchoolYear(),userInfo.getSchoolTermCode(),gradeId,teamId,zhibiaoId,stuName,page);

        }else {
             list = studentDevelopmentService.findByAll(schoolYear, schoolTrem, gradeId, teamId, zhibiaoId, stuName, page);

        }
        String url="";
        if(sub.equals("list")){
            url="/wuyu/development/socialindicatorsstudent/list";
        }else{
            url="/wuyu/development/socialindicatorsstudent/index";
        }
        model.addAttribute("list",list);
        return new ModelAndView(url,model.asMap());

    }
}
