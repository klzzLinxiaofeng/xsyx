package platform.szxyzxx.web.indicators.controller;


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
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.szxyzxx.indicators.pojo.IndicatorsPojo;
import platform.szxyzxx.indicators.service.IndicatorsService;
import platform.szxyzxx.logger.service.LoggerService;
import platform.szxyzxx.logger.vo.Loggers;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/indicators")
public class Indicatorscontroller {
    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private IndicatorsService indicatorsService;
    @Autowired
    private BasicSQLService basicSQLService;
    @Autowired
    private LoggerService loggerService;

    @RequestMapping("/index/grade")
    public List<Grade> findBygrade(@CurrentUser UserInfo user,
                                   @RequestParam(value = "schoolYear",required = false) String schoolYear){
        if(schoolYear==null){
            return indicatorsService.findBygrade(user,user.getSchoolYear());
        }
        return indicatorsService.findBygrade(user,schoolYear);
    }

    @RequestMapping("/index/json")
    public ModelAndView findBygrades(@CurrentUser UserInfo user,
                                    @RequestParam(value = "gradeId",required = false) Integer gradeId,
                                     @RequestParam(value = "schoolYear",required = false) String schoolYear,
                                     @ModelAttribute("page") Page page,
                                    @RequestParam(value = "sub", required = false) String sub,
                                    Model model){
        List<IndicatorsPojo> list=indicatorsService.findByAll(user,gradeId,schoolYear);
        model.addAttribute("list",list);
        String bathUrl;
        if(sub.equals("list")){
            bathUrl="/indicators/list";
        }else{
            bathUrl="/indicators/index";
        }
        ModelAndView modelAndView=new ModelAndView(bathUrl,model.asMap());
        return modelAndView;
    }

    @RequestMapping("/input")
    public ModelAndView findBygradess(){

        ModelAndView modelAndView=new ModelAndView("/indicators/input");
        return modelAndView;
    }

    /*
    *添加体测指标
    * */
    @RequestMapping("/input/create")
    public String crateIndicators( IndicatorsPojo indicatorsPojo,@CurrentUser UserInfo userInfo){
        Boolean flag=indicatorsService.create(indicatorsPojo,userInfo);
        if(flag){
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("体测指标管理");
            logger.setType(1);
            logger.setMessage("新增体测指标："+indicatorsPojo.getName() );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }
        return  "shibai";
    }

    @RequestMapping("/update")
    public ModelAndView findById(@RequestParam Integer id,Model model){
        String sql="select * from pj_indicators where is_delete=0  and id="+id;
        List<Map<String,Object>> lists =basicSQLService.find(sql);
        IndicatorsPojo indicatorsPojo=new IndicatorsPojo();
        indicatorsPojo.setId((Integer) lists.get(0).get("id"));
        indicatorsPojo.setName((String) lists.get(0).get("indicators_name"));
        indicatorsPojo.setDanwei((String) lists.get(0).get("danwei"));
        indicatorsPojo.setGradeId((Integer) lists.get(0).get("grade_id"));
        indicatorsPojo.setSchoolYear((String) lists.get(0).get("school_year"));
        model.addAttribute("indicatorsPojo",indicatorsPojo);
        ModelAndView modelAndView=new ModelAndView("/indicators/bianji",model.asMap());
        return modelAndView;
    }

    @RequestMapping("/input/update")
    public String findByUpdate( IndicatorsPojo indicatorsPojo,
                                @CurrentUser UserInfo userInfo) {
        String sql = "update pj_indicators set indicators_name='"+indicatorsPojo.getName()+"',danwei='"+indicatorsPojo.getDanwei()+  "'  where is_delete=0 and  id="+indicatorsPojo.getId();
        //关联表进行修改  后面完成
        int a = basicSQLService.update(sql);
        if (a > 0) {
            Loggers logger = new Loggers();
            logger.setCaozuoId(userInfo.getTeacherId());
            logger.setName(userInfo.getRealName());
            logger.setUsername(userInfo.getUserName());
            logger.setMobile(userInfo.getTelephone());
            logger.setMokuaiName("体测指标管理");
            logger.setType(2);
            logger.setMessage("修改体测指标："+indicatorsPojo.getName() );
            logger.setSchoolYear(userInfo.getSchoolYear());
            logger.setSchoolTrem(userInfo.getSchoolTermCode());
            loggerService.create(logger);
            return "success";
        }
        return "shibai";
    }


    @RequestMapping("/index/delete")
    public String findBydelete(@RequestParam Integer id,
                               @CurrentUser UserInfo userInfo) {
        String sql1="select indicators_name from pj_indicators where is_delete=0  and id="+id;
        List<Map<String,Object>> lists =basicSQLService.find(sql1);
        Date date=new Date();
        String sql = "update pj_indicators set  is_delete= 1 where is_delete=0 and id="+id;
        //关联表进行删除
        String sql2 = "update pj_indicators_student set  is_delete= 1  where is_delete=0 and indicators_id="+id;
        int a = basicSQLService.update(sql);
        int b=basicSQLService.update(sql2);
        if (a > 0) {
            if(b>0){
                Loggers logger = new Loggers();
                logger.setCaozuoId(userInfo.getTeacherId());
                logger.setName(userInfo.getRealName());
                logger.setUsername(userInfo.getUserName());
                logger.setMobile(userInfo.getTelephone());
                logger.setMokuaiName("体测指标管理");
                logger.setType(3);
                logger.setMessage("删除体测指标："+lists.get(0).get("indicators_name") );
                logger.setSchoolYear(userInfo.getSchoolYear());
                logger.setSchoolTrem(userInfo.getSchoolTermCode());
                loggerService.create(logger);
                return "success";
            }
            return "shibai";
        }
        return "shibai";
    }


}
