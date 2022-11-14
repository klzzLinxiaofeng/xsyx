package platform.szxyzxx.web.wu.development.controller;

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
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.wuyu.development.service.DevelopmentIndicatorsService;
import platform.szxyzxx.wuyu.development.vo.DevelopmentIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 11:19
 * @Version 1.0
 * 品德发展指标
 */
@RestController
@RequestMapping("/developmentIndicators")
public class DevelopmentIndicatorsController {
    @Autowired
    private DevelopmentIndicatorsService developmentIndicatorsService;
    @Autowired
    private LoggerService loggerService;
    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                  @ModelAttribute("page")Page page,
                                  @RequestParam(value = "code",required = false) String code,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  Model model){
        List<DevelopmentIndicators> list=developmentIndicatorsService.findByAll(code,userInfo.getSchoolYear(),userInfo.getSchoolTermCode(),page);
        String url="";
        if(sub.equals("list")){
            url="/wuyu/development/list";
        }else{
            url="/wuyu/development/index";
        }
        model.addAttribute("list",list);
        return new ModelAndView(url,model.asMap());
    }
    /**
     * 修改或添加跳转
     * @param model
     * @return
     */
    @RequestMapping("/addOrupdateView")
    public ModelAndView addOrupdateView(@RequestParam(value = "id",required = false) Integer id,
                                        Model model){
        String batUrl="/wuyu/development/input";
        if(id!=null){
            DevelopmentIndicators developmentIndicators=developmentIndicatorsService.findById(id);
            model.addAttribute("developmentIndicators",developmentIndicators);
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
    public ResponseInfomation createOrUpdate(DevelopmentIndicators developmentIndicators,
                                             @CurrentUser UserInfo userInfo){
        if(developmentIndicators.getId()!=null){
            Integer num=developmentIndicatorsService.update(developmentIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("品德发展");
                logger.setType(2);
                logger.setMessage("修改品德发展指标："+developmentIndicators.getName() );
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
            }else{
                return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
            }
        }else{
            developmentIndicators.setXn(userInfo.getSchoolYear());
            developmentIndicators.setXq(userInfo.getSchoolTermCode());
            Integer num=developmentIndicatorsService.create(developmentIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("品德发展指标");
                logger.setType(1);
                logger.setMessage("新增品德发展指标："+developmentIndicators.getName() );
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
        logger.setMokuaiName("品德发展指标");
        logger.setType(3);
        String[] split = ids.split(",");
        if(split.length>1){
            logger.setMessage("批量删除品德发展指标" );
        }else{
            DevelopmentIndicators developmentIndicators=developmentIndicatorsService.findById(Integer.parseInt(split[0]));
            logger.setMessage("删除品德发展指标："+developmentIndicators.getName() );
        }
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        String flag=developmentIndicatorsService.updateDelete(ids);
        return flag;
    }
}
