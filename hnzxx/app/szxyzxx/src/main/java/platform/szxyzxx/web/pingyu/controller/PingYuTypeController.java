package platform.szxyzxx.web.pingyu.controller;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.pingyumoban.service.PingYuMoBanService;
import platform.szxyzxx.pingyumoban.service.PingYuTypeService;
import platform.szxyzxx.pingyumoban.vo.PingYuType;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

@RestController
@RequestMapping("/pingyutype")
public class PingYuTypeController {
    @Autowired
    private PingYuTypeService pingYuTypeService;

    @Autowired
    private PingYuMoBanService pingYuMoBanService;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping(value = "/allType")
    public ModelAndView findByStudent(
                                      @ModelAttribute("page") Page page,
                                      @RequestParam(value = "name",required = false) String name,
                                      @RequestParam(value = "sub", required = false) String sub,
                                      Model model){
        List<PingYuType> list = pingYuTypeService.findByAll(name, page);
        model.addAttribute("list", list);
        String bathUrl;
        if (sub.equals("list")) {
            bathUrl = "/pingyu/pingyuType/list";
        } else {
            bathUrl = "/pingyu/pingyuType/index";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    @RequestMapping(value = "/createOrUpdate")
    public ModelAndView createOrUpdate(
                                      @RequestParam(value = "id",required = false) Integer id,
                                      Model model){
        if(id!=null && !id.equals("")){
            PingYuType pingYuType=pingYuTypeService.findById(id);
            model.addAttribute("pingYuType", pingYuType);
        }

        String bathUrl="/pingyu/pingyuType/input";
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }
    @RequestMapping("/create")
    public String create(@CurrentUser UserInfo userInfo, PingYuType pingYuType){
        Integer num=pingYuTypeService.create(pingYuType);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("评语模板类型");
            logger.setType(1);
            logger.setMessage("新增评语类型："+pingYuType.getName());
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "error";
        }
    }
    @RequestMapping("/update")
    public String update(@CurrentUser UserInfo userInfo, PingYuType pingYuType){
        Integer num=pingYuTypeService.update(pingYuType);
        if(num>0){
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("评语模板类型");
            logger.setType(2);
            logger.setMessage("修改评语类型："+pingYuType.getName());
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
        PingYuType pingYuType=pingYuTypeService.findById(id);
        Integer num=pingYuTypeService.updateDelete(id);
        if(num>0){
            pingYuMoBanService.updateShanchu(id);
            Loggers logger =new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("评语模板类型");
            logger.setType(3);
            logger.setMessage("删除评语类型："+pingYuType.getName());
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }else{
            return "error";
        }
    }
}
