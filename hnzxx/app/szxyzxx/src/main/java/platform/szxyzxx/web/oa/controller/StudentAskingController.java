package platform.szxyzxx.web.oa.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.StudentAskingService;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 学生请假
 *
 * @author yhc
 */
@Controller
@RequestMapping("/stu/stuAsking")
public class StudentAskingController extends BaseController {
    private static final Logger log = LoggerFactory
            .getLogger(StudentAskingController.class);

    private final static String viewBasePath = "/schoolaffair/stuAsking";


    @Autowired
    @Qualifier("studentAskingService")
    private StudentAskingService studentAskingService;

    /**
     * 请假学生列表
     *
     * @param user
     * @param dm
     * @param sub
     * @param condition
     * @param page
     * @param order
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") StudentAskingPojo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        List<StudentAskingPojo> canteens = this.studentAskingService.findCanteenByCondition(condition, page, order);
        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("studentAsking", canteens);
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * 查看详情
     *
     * @param id
     * @param condition
     * @param model
     * @param user
     * @return
     */
    @RequestMapping(value = "/viewer", method = RequestMethod.GET)
    public ModelAndView viewer(
            @RequestParam(value = "id", required = true) Integer id,
            @ModelAttribute("condition") StudentAskingPojo condition,
            Model model, @CurrentUser UserInfo user) {
        if (id != null) {
            List<StudentAskingPojo> list = this.studentAskingService.findCanteenByCondition(condition, null, null);
            if (list != null && list.size() > 0) {
                model.addAttribute("publicClass", list.get(0));
            }
        }
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
}