package platform.szxyzxx.web.schoolaffair.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Questionnaire;
import platform.education.generalTeachingAffair.model.StartPagePojo;
import platform.education.generalTeachingAffair.service.QuestionnaireService;
import platform.education.generalTeachingAffair.service.StartPageService;
import platform.education.generalTeachingAffair.vo.QuestionnaireCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * 启动页管理
 *
 * @author: yhc
 * @Date: 2020/12/13 17:24
 * @Description:
 */
@Controller
@RequestMapping("/start/page")
public class StartPageController extends BaseController {
    private Logger log = LoggerFactory.getLogger(getClass());
    private final static String viewBasePath = "/schoolaffair/startPageManager";

    @Autowired
    @Qualifier("startPageService")
    private StartPageService startPageService;

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") StartPagePojo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        conditionFilter(user, condition);
        order.setProperty(order.getProperty() != null ? order.getProperty() : "id");
        List<StartPagePojo> items = this.startPageService.findQuestionnaireVoByCondition(condition, page, order);

        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("items", toVos(items));

        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator() {
        return new ModelAndView(structurePath("/input"));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, StartPagePojo publicClass) {
        if (publicClass != null) {
            publicClass.setId(id);
        }
        return this.startPageService.abandon(publicClass);
    }


    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(StartPagePojo publicClass, @CurrentUser UserInfo user) {
        Integer schoolId = user.getSchoolId();
        if (schoolId != null) {
            publicClass.setSchoolId(schoolId);
        }
        publicClass = this.startPageService.add(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        StartPagePojo publicClass = this.startPageService.findStartPagePojoVoById(id);

        if (publicClass != null) {
            // 根据uuid查询图片的url
            FileResult file = fileService.findFileByUUID(publicClass.getUuid());
            if (file != null) {
                publicClass.setUrl(file.getHttpUrl());
                model.addAttribute("entity", file.getEntityFile());
            }
            model.addAttribute("publicClass", toVo(publicClass));
        }
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation edit(@ModelAttribute("publicClass") StartPagePojo publicClass, @CurrentUser UserInfo user) {
        Integer schoolId = user.getSchoolId();
        if (schoolId != null) {
            publicClass.setSchoolId(schoolId);
        }
        publicClass = this.startPageService.modify(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    private void conditionFilter(UserInfo user, StartPagePojo condition) {
        Integer schoolId = condition.getSchoolId();
        condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private List<StartPagePojo> toVos(List<StartPagePojo> publicClassList) {
        List<StartPagePojo> voList = new ArrayList<>();
        for (StartPagePojo publicClass : publicClassList) {
            voList.add(toVo(publicClass));
        }
        return voList;
    }

    private StartPagePojo toVo(StartPagePojo publicClass) {
        StartPagePojo vo = new StartPagePojo();
        BeanUtils.copyProperties(publicClass, vo);
        return vo;
    }
}
