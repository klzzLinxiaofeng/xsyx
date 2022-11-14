package platform.szxyzxx.web.evaluationclasses.controller;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import platform.szxyzxx.evaluationclasses.service.ClassesIndicatorService;
import platform.szxyzxx.evaluationclasses.vo.ClassesIndicators;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/5 11:25
 * @Version 1.0
 * 社会责任指标
 */
@RestController
@RequestMapping("/classesIndicator")
public class ClassesActivitcController {

    @Autowired
    private ClassesIndicatorService classesIndicatorService;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/findByAll")
    public ModelAndView findByAll(@CurrentUser UserInfo userInfo,
                                  @ModelAttribute Page page,
                                  @RequestParam(value = "sub",required = false) String sub,
                                  Model model){
        List<ClassesIndicators> list=classesIndicatorService.findByAll(userInfo,page);
        String batUrl="";
        if(sub.equals("list")){
            batUrl="/evaluationclasses/list";
        }else{
            batUrl="/evaluationclasses/index";
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
        String batUrl="/evaluationclasses/input";
        if(id!=null){
            ClassesIndicators socialBehavior=classesIndicatorService.findById(id);
            model.addAttribute("socialBehavior",socialBehavior);
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
    public ResponseInfomation createOrUpdate(ClassesIndicators classesIndicators,
                                             @CurrentUser UserInfo userInfo){
        if(classesIndicators.getId()!=null){
            Integer num=classesIndicatorService.update(classesIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("社会责任指标");
                logger.setType(2);
                logger.setMessage("修改社会责任指标："+classesIndicators.getName() );
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
            }else{
                return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
            }
        }else{
            classesIndicators.setSchoolYear(userInfo.getSchoolYear());
            classesIndicators.setSchoolTrem(userInfo.getSchoolTermCode());
            Integer num=classesIndicatorService.create(classesIndicators);
            if(num>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("社会责任指标");
                logger.setType(1);
                logger.setMessage("新增社会责任指标："+classesIndicators.getName() );
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
        logger.setMokuaiName("社会责任指标");
        logger.setType(3);
        String[] split = ids.split(",");
        if(split.length>1){
            logger.setMessage("批量删除社会责任指标" );
        }else{
            ClassesIndicators socialIndicators=classesIndicatorService.findById(Integer.parseInt(split[0]));
            logger.setMessage("删除社会责任指标："+socialIndicators.getName() );
        }
        logger.setSchoolYear(userInfo.getSchoolYear());
        logger.setSchoolTrem(userInfo.getSchoolTermCode());
        loggerService.create(logger);
        String flag=classesIndicatorService.updateDelete(ids);
        return flag;
    }

}
