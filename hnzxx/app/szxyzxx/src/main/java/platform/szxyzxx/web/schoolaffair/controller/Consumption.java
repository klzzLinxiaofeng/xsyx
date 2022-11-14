package platform.szxyzxx.web.schoolaffair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description: 消费统计
 * @author: cmb
 * @create: 2020-10-16 17:03
 **/

@Controller
@RequestMapping("/schoolaffair/schoolDataStatistics/consumption")
public class Consumption {
    private final static String viewBasePath = "/schoolaffair/schoolDataStatistics/consumption";
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView BorrowIndex() {
        return new ModelAndView(structurePath("/index"));
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
}
