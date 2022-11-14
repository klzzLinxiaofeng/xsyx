package platform.szxyzxx.web.ishangkelilu.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.ishangkelilu.pojo.StudyType;
import platform.szxyzxx.ishangkelilu.service.StudyTypeService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

@RestController
@RequestMapping("/studey/type")
public class StudyTypeController {
    @Autowired
    private StudyTypeService studyTypeService;
    @Autowired
    private LoggerService loggerService;
    @RequestMapping(value = "/allType")
    public ModelAndView findByStudent(
            @ModelAttribute("page") Page page,
            @RequestParam(value = "name",required = false) String name,
            @RequestParam(value = "sub", required = false) String sub,
            Model model){
        List<StudyType> list = studyTypeService.findByAll(name, page);
        model.addAttribute("list", list);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/ishangkelilu/type/list";
        } else {
            bathUrl = "/ishangkelilu/type/index";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    @RequestMapping(value = "/createOrUpdate")
    public ModelAndView createOrUpdate(
            @RequestParam(value = "id",required = false) Integer id,
            Model model){
        if(id!=null && !id.equals("")){
            StudyType studyType=studyTypeService.findById(id);
            model.addAttribute("studyType", studyType);
        }

        String bathUrl="/ishangkelilu/type/input";
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    @RequestMapping("/create")
    public String create(StudyType studyType){
        Integer num=studyTypeService.create(studyType);
        if(num>0){
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/update")
    public String update(@CurrentUser UserInfo userInfo,
                         StudyType studyType){
        Integer num=studyTypeService.update(studyType);
        if(num>0){
            Loggers logger=new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("课堂评价指标");
            logger.setType(2);
            logger.setMessage("修改课堂评价指标");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/delete")
    public String updateDelete(@CurrentUser UserInfo userInfo,
                               @RequestParam(value = "id") Integer id){
        StudyType studyType=studyTypeService.findById(id);
        Integer num=studyTypeService.updateDelete(id);
        if(num>0){
            Loggers logger=new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("课堂评价指标");
            logger.setType(3);
            logger.setMessage("删除课堂评价"+studyType.getName()+"指标");
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "error";
        }
    }
}
