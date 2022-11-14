package platform.szxyzxx.web.lifebehavior.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.lifebehavior.service.LifeIndicatorService;
import platform.szxyzxx.lifebehavior.vo.LifeIndicators;
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
 * 生活行为指标
 */
@RestController
@RequestMapping("/lifeIndicator")
public class LifeController {

    @Autowired
    private LifeIndicatorService lifeIndicatorService;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@ModelAttribute Page page,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  @CurrentUser UserInfo userInfo,
                                  Model model){
        List<LifeIndicators> list=lifeIndicatorService.findByAll(userInfo,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/lifeIndicators/list";
        }else{
            batUrl="/lifeIndicators/index";
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
        String batUrl="/lifeIndicators/input";
        if(id!=null){
            LifeIndicators lifeIndicators=lifeIndicatorService.findById(id);
            model.addAttribute("lifeIndicators",lifeIndicators);
        }
        return new ModelAndView(batUrl,model.asMap());
    }

    /**
     * 修改或添加指标
     * @param lifeIndicators
     * @param userInfo
     * @return
     */
    @RequestMapping("/createOrUpdate")
    public ResponseInfomation createOrUpdate(LifeIndicators lifeIndicators,
                                             @CurrentUser UserInfo userInfo){
        if(lifeIndicators.getId()!=null){
            Integer num=lifeIndicatorService.update(lifeIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("生活习惯指标");
                logger.setType(2);
                logger.setMessage("修改生活习惯指标："+lifeIndicators.getName() );
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
            }else{
                return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
            }
        }else{
            lifeIndicators.setSchoolYear(userInfo.getSchoolYear());
            lifeIndicators.setSchoolTrem(userInfo.getSchoolTermCode());
            Integer num=lifeIndicatorService.create(lifeIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("生活习惯指标");
                logger.setType(1);
                logger.setMessage("新增生活习惯指标："+lifeIndicators.getName() );
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
        logger.setMokuaiName("生活习惯指标");
        logger.setType(3);
        String[] split = ids.split(",");
        if(split.length>1){
            logger.setMessage("批量删除生活习惯指标" );
        }else{
            LifeIndicators lifeIndicators=lifeIndicatorService.findById(Integer.parseInt(split[0]));
            logger.setMessage("删除生活习惯指标："+lifeIndicators.getName() );
        }
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        String flag=lifeIndicatorService.updateDelete(ids);
        return flag;
    }

}
