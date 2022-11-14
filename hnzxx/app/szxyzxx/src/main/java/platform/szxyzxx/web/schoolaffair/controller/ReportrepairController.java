package platform.szxyzxx.web.schoolaffair.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.oa.vo.ApplyRepairCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

/**
 * @description: 修复统计
 * @author: cmb
 * @create: 2020-10-16 17:02
 **/
@Controller
@RequestMapping("/schoolaffair/schoolDataStatistics/reportrepair")
public class ReportrepairController {
    private final static String viewBasePath = "/schoolaffair/schoolDataStatistics/reportrepair";
    /**
    * @Description: 修复统计首页
    * @Param: * @param  
    * @return: org.springframework.web.servlet.ModelAndView
    * @Author: cmb
    * @Date: 2020/10/16
    */ 
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView ReportrepairIndex	(@CurrentUser
    UserInfo user,
    @RequestParam(value = "dm", required = false) String dm,
    @RequestParam(value = "sub", required = false) String sub,
    @RequestParam(value = "own", required = false) String own,
    @ModelAttribute("condition")
    ApplyRepairCondition condition,
    @ModelAttribute("page")
    Page page,
    @RequestParam(value = "approval", required = false) String approval,
    @ModelAttribute("order")
    Order order, Model model)  {

        return new ModelAndView(structurePath("/index"));
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

}
