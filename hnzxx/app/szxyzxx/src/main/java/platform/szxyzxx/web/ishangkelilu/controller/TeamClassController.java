package platform.szxyzxx.web.ishangkelilu.controller;


import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.ishangkelilu.pojo.TeamClassPassWord;
import platform.szxyzxx.ishangkelilu.service.TeamClassService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
/*
* 修改课堂行为密码
*/
@RestController
@RequestMapping("/TeamClassPassWord")
public class TeamClassController {
    Logger log = LoggerFactory.getLogger(TeamClassController.class);

    @Autowired
    private TeamClassService teamClassService;
    @Autowired
    private LoggerService loggerService;
    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@RequestParam(value = "gradeId",required = false) Integer gradeId,
                                  @RequestParam(value = "teamId",required = false) Integer teamId,
                                  @ModelAttribute Page page,
                                  @CurrentUser UserInfo userInfo,
                                  @RequestParam String sub, Model model) {
        List<TeamClassPassWord> teamClassPassWords=teamClassService.findByAll(gradeId,teamId,userInfo,page);
        model.addAttribute("list",teamClassPassWords);
        model.addAttribute("schoolYear",userInfo.getSchoolYear());
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/ishangkelilu/teamPass/list";
        }else{
            batUrl="/ishangkelilu/teamPass/index";
        }
        return new ModelAndView(batUrl,model.asMap());
    }

    @RequestMapping("/updateOrcreate")
    public String updateOrcreate(@RequestParam Integer teamId,
                                 @RequestParam String teamName,
                                 @RequestParam String passWord,
                                 @CurrentUser UserInfo userInfo){
        Loggers logger=new Loggers();
        logger.setCaozuoId(userInfo.getTeacherId());
        logger.setName(userInfo.getRealName());
        logger.setUsername(userInfo.getUserName());
        logger.setMobile(userInfo.getTelephone());
        logger.setMokuaiName("课堂行为密码设置");
        logger.setType(2);
        logger.setMessage("修改"+teamName+"的密码");
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        return  teamClassService.UpdateOrCreate(teamId,teamName,passWord,userInfo);
    }
    @RequestMapping("/updateOrcreateTwo")
    public String updateOrcreateTwo(
                                 @RequestParam String passWord,
                                 @CurrentUser UserInfo userInfo){
        Loggers logger=new Loggers();
        logger.setCaozuoId(userInfo.getTeacherId());
        logger.setName(userInfo.getRealName());
        logger.setUsername(userInfo.getUserName());
        logger.setMobile(userInfo.getTelephone());
        logger.setMokuaiName("课堂行为密码设置");
        logger.setType(2);
        logger.setMessage("一键密码设置");
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        return  teamClassService.updateOrcreateTwo(passWord,userInfo);
    }
}
