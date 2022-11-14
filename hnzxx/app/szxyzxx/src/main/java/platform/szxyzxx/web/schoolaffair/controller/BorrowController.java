package platform.szxyzxx.web.schoolaffair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: 借阅统计
 * @author: cmb
 * @create: 2020-10-16 16:43
 **/

@Controller
@RequestMapping("/schoolaffair/schoolDataStatistics/borrow")
public class BorrowController {
    private final static String viewBasePath = "/schoolaffair/schoolDataStatistics/borrow";

    /**
     * @Description:借阅统计首页
     * @Param: * @param
     * @return: org.springframework.web.servlet.ModelAndView
     * @Author: cmb
     * @Date: 2020/10/16
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView BorrowIndex() {
        return new ModelAndView(structurePath("/index"));
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

}
