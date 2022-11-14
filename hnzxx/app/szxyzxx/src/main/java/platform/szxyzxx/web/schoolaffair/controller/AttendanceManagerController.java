package platform.szxyzxx.web.schoolaffair.controller;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.vo.MicroManagerPojo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;


/**
 * 考勤管理
 *
 * @author: yhc
 * @Date: 2020/10/18 12:33
 * @Description:
 */
@Controller
@RequestMapping("/schoolaffair/attendanceManager")
public class AttendanceManagerController {
    private Logger log = LoggerFactory.getLogger(getClass());
    private final static String viewBasePath = "/schoolaffair/attendanceManager";

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") MicroManagerPojo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;

        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        return new ModelAndView(viewPath, model.asMap());
    }


    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
}
