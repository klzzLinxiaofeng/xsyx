package platform.szxyzxx.web.activitieshome.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.activitieshome.service.HomeIndicatorService;
import platform.szxyzxx.activitieshome.vo.HomeIndicators;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 11:25
 * @Version 1.0
 * 居家活动指标
 */
@RestController
@RequestMapping("/homeIndicator")
public class HomeActivitcController {

    @Autowired
    private HomeIndicatorService homeIndicatorService;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                  @ModelAttribute Page page,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  Model model){
        List<HomeIndicators> list=homeIndicatorService.findByAll(userInfo,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/activitieshome/list";
        }else{
            batUrl="/activitieshome/index";
        }
        model.addAttribute("list",list);
        return new ModelAndView(batUrl,model.asMap());
    }

    /**
     * 修改或添加跳转
     * @param model
     * @return
     */
    @RequestMapping("/addOrupdateView")
    public ModelAndView addOrupdateView(@RequestParam(value = "id",required = false) Integer id,
                                  Model model){
        String batUrl="/activitieshome/input";
        if(id!=null){
            HomeIndicators homeIndicators=homeIndicatorService.findById(id);
            model.addAttribute("homeIndicators",homeIndicators);
        }
        return new ModelAndView(batUrl,model.asMap());
    }

    /**
     * 修改或添加指标
     * @param
     * @param userInfo
     * @return
     */
    @RequestMapping("/createOrUpdate")
    public ResponseInfomation createOrUpdate(HomeIndicators homeIndicators,
                                             @CurrentUser UserInfo userInfo){
        if(homeIndicators.getId()!=null){
            Integer num=homeIndicatorService.update(homeIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("居家活动指标");
                logger.setType(2);
                logger.setMessage("修改居家活动指标："+homeIndicators.getName() );
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
            }else{
                return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
            }
        }else{
            homeIndicators.setSchoolYear(userInfo.getSchoolYear());
            homeIndicators.setSchoolTrem(userInfo.getSchoolTermCode());
            Integer num=homeIndicatorService.create(homeIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("居家活动指标");
                logger.setType(1);
                logger.setMessage("新增生活习惯指标："+homeIndicators.getName() );
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
            }else{
                return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
            }
        }

    }
    /*删除指标*/
    @RequestMapping("/delete")
    public String delete(@CurrentUser UserInfo userInfo,
                         @RequestParam("ids") String ids){
        Loggers logger = new Loggers();
        logger.setCaozuoId(userInfo.getTeacherId());
        logger.setName(userInfo.getRealName());
        logger.setUsername(userInfo.getUserName());
        logger.setMobile(userInfo.getTelephone());
        logger.setMokuaiName("居家活动指标");
        logger.setType(3);
        String[] split = ids.split(",");
        if(split.length>1){
            logger.setMessage("批量删除居家活动指标" );
        }else{
            HomeIndicators homeIndicators=homeIndicatorService.findById(Integer.parseInt(split[0]));
            logger.setMessage("删除居家活动指标："+homeIndicators.getName() );
        }
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        String flag=homeIndicatorService.updateDelete(ids);
        return flag;
    }

}
