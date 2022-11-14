package platform.szxyzxx.web.indicators.controller;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.indicators.pojo.IndicatorsStudent;
import platform.szxyzxx.indicators.service.IndicatorsStudentService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/indicators/student")
public class IndicatorsStudentController {
    @Autowired
    private IndicatorsStudentService inStuService;
    @Autowired
    private LoggerService loggerService;
    @Autowired
    private BasicSQLService basicSQLService;

    @RequestMapping("/list/json")
    public ModelAndView findByStudent(@CurrentUser UserInfo user,
                                      @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                      @ModelAttribute("page") Page page,
                                      @RequestParam(value = "sub", required = false) String sub,
                                      @RequestParam(value = "teamId",required = false) Integer teamId,
                                      @RequestParam(value = "stuName",required = false) String stuName,
                                      @RequestParam(value = "xn",required = false) String xn,
                                      Model model){
        List<Student> list=inStuService.findByStudent(user.getSchoolId(),gradeId,teamId,stuName,xn,page);
        model.addAttribute("list",list);
        String bathUrl;
        if(sub.equals("list")){
            bathUrl="/indicators/indicatorsStudent/list";
        }else{
            bathUrl="/indicators/indicatorsStudent/index";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    @RequestMapping("/index/bianji")
    public ModelAndView findByBianji(@CurrentUser UserInfo user,
                                     @RequestParam(value = "studentId",required = false) Integer studentId,
                                      Model model){
        IndicatorsStudent list=inStuService.findByIndicator(user, studentId);
        model.addAttribute("list",list);
        String bathUrl;
        bathUrl="/indicators/indicatorsStudent/bianji";
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    @RequestMapping("/bianji/update/dd")
    public Integer findByBianjiaa(@CurrentUser UserInfo userInfo,
                                      IndicatorsStudent indicatorsStudent){
        String sql1="select name from pj_student where is_delete=0  and id="+indicatorsStudent.getStudentId();
        List<Map<String,Object>> lists =basicSQLService.find(sql1);
        indicatorsStudent.setSchoolYear(userInfo.getSchoolYear());
        Integer num=inStuService.updateIn(indicatorsStudent);
        if(num>0){
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("体测健康管理");
            logger.setType(2);
            logger.setMessage("修改"+lists.get(0).get("name")+"体测健康" );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return num;
        }
        return  0;
    }
    @RequestMapping("/bianji/update/aa")
    public String findByBianjiUpdate(@CurrentUser UserInfo user,
                                      IndicatorsStudent indicatorsStudent){
        //indicatorsStudent.setSchoolYear(user.getSchoolYear());
        return  inStuService.updatess(indicatorsStudent);
    }


    @RequestMapping("/index/xiangqing")
    public ModelAndView findByxiangqing(@CurrentUser UserInfo user,
                                     @RequestParam(value = "studentId",required = false) Integer studentId,
                                     Model model){
        IndicatorsStudent list=inStuService.findByIndicator(user, studentId);
        model.addAttribute("list",list);
        String bathUrl;
        bathUrl="/indicators/indicatorsStudent/xianqing";
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
}
