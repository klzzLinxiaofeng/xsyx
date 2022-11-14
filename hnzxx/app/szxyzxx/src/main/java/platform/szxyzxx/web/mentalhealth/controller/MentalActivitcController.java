package platform.szxyzxx.web.mentalhealth.controller;

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
import platform.szxyzxx.mentalhealth.service.MentalIndicatorService;
import platform.szxyzxx.mentalhealth.vo.MentalIndicators;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 11:25
 * @Version 1.0
 * 心理健康指标
 */
@RestController
@RequestMapping("/mentalIndicator")
public class MentalActivitcController {

    @Autowired
    private MentalIndicatorService mentalIndicatorService;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                  @ModelAttribute Page page,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  Model model){
        List<MentalIndicators> list=mentalIndicatorService.findByAll(userInfo,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/mentalhealth/list";
        }else{
            batUrl="/mentalhealth/index";
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
        String batUrl="/mentalhealth/input";
        if(id!=null){
            MentalIndicators mentalIndicators=mentalIndicatorService.findById(id);
            model.addAttribute("mentalIndicators",mentalIndicators);
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
    public ResponseInfomation createOrUpdate(MentalIndicators mentalIndicators,
                                             @CurrentUser UserInfo userInfo){
        if(mentalIndicators.getId()!=null){
            Integer num=mentalIndicatorService.update(mentalIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("心理健康指标");
                logger.setType(2);
                logger.setMessage("修改心理健康指标："+mentalIndicators.getName() );
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
            }else{
                return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
            }
        }else{
            mentalIndicators.setSchoolYear(userInfo.getSchoolYear());
            mentalIndicators.setSchoolTrem(userInfo.getSchoolTermCode());
            Integer num=mentalIndicatorService.create(mentalIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("心理健康指标");
                logger.setType(1);
                logger.setMessage("新增心理健康指标："+mentalIndicators.getName() );
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
        logger.setMokuaiName("心理健康指标");
        logger.setType(3);
        String[] split = ids.split(",");
        if(split.length>1){
            logger.setMessage("批量删除心理健康指标" );
        }else{
            MentalIndicators mentalIndicators=mentalIndicatorService.findById(Integer.parseInt(split[0]));
            logger.setMessage("删除心理健康指标："+mentalIndicators.getName() );
        }
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        String flag=mentalIndicatorService.updateDelete(ids);
        return flag;
    }

}
