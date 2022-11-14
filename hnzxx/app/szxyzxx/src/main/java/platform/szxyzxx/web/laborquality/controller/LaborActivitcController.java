package platform.szxyzxx.web.laborquality.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.laborquality.service.LaborIndicatorService;
import platform.szxyzxx.laborquality.vo.LaborIndicators;
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
 * 劳动素质指标
 */
@RestController
@RequestMapping("/laborIndicator")
public class LaborActivitcController {

    @Autowired
    private LaborIndicatorService laborIndicatorService;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                  @ModelAttribute Page page,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  Model model){
        List<LaborIndicators> list=laborIndicatorService.findByAll(userInfo,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/laborquality/list";
        }else{
            batUrl="/laborquality/index";
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
        String batUrl="/laborquality/input";
        if(id!=null){
            LaborIndicators idealIndicators=laborIndicatorService.findById(id);
            model.addAttribute("laborIndicators",idealIndicators);
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
    public ResponseInfomation createOrUpdate(LaborIndicators laborIndicators,
                                             @CurrentUser UserInfo userInfo){
        if(laborIndicators.getId()!=null){
            Integer num=laborIndicatorService.update(laborIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("劳动素养指标");
                logger.setType(2);
                logger.setMessage("修改劳动素养指标："+laborIndicators.getName() );
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
            }else{
                return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
            }
        }else{
            laborIndicators.setSchoolYear(userInfo.getSchoolYear());
            laborIndicators.setSchoolTrem(userInfo.getSchoolTermCode());
            Integer num=laborIndicatorService.create(laborIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("劳动素养指标");
                logger.setType(1);
                logger.setMessage("新增劳动素养指标："+laborIndicators.getName() );
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
        logger.setMokuaiName("劳动素养指标");
        logger.setType(3);
        String[] split = ids.split(",");
        if(split.length>1){
            logger.setMessage("批量删除劳动素养指标" );
        }else{
            LaborIndicators laborIndicators=laborIndicatorService.findById(Integer.parseInt(split[0]));
            logger.setMessage("删除劳动素养指标："+laborIndicators.getName() );
        }
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        String flag=laborIndicatorService.updateDelete(ids);
        return flag;
    }

}
