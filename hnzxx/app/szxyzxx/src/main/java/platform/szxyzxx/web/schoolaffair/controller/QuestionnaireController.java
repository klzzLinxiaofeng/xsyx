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
import platform.education.generalTeachingAffair.service.QuestionnaireService;
import platform.education.generalTeachingAffair.vo.QuestionnaireCondition;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/questionnaire")
public class QuestionnaireController extends BaseController {
    private Logger log = LoggerFactory.getLogger(getClass());
    private final static String viewBasePath = "/schoolaffair/questionnaireManagement";

    @Autowired
    @Qualifier("questionnaireService")
    protected QuestionnaireService questionnaireService;


    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") QuestionnaireCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        conditionFilter(user, condition);
        order.setProperty(order.getProperty() != null ? order.getProperty() : "id");
        condition.setSchoolId(user.getSchoolId());
        List<Questionnaire> items = this.questionnaireService.findQuestionnaireVoByCondition(condition, page, order);

        if (items != null && items.size() > 0) {
            for (Questionnaire item : items) {
                // 根据uuid查询图片的url
                FileResult file = fileService.findFileByUUID(item.getUuid());
                if(file != null){
                    item.setPicUrl(file.getHttpUrl());
                }
            }
        }

        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("items", toVos(items));

        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator(Model model) {

        return new ModelAndView(structurePath("/input"));
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, Questionnaire publicClass) {
        if (publicClass != null) {
            publicClass.setId(id);
        }
        return this.questionnaireService.abandon(publicClass);
    }


    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(Questionnaire publicClass, @CurrentUser UserInfo user) {
        Integer schoolId = user.getSchoolId();
        if (schoolId != null){
            publicClass.setSchoolId(schoolId);
        }
        publicClass = this.questionnaireService.add(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        Questionnaire publicClass = this.questionnaireService.findQuestionnaireVoById(id);

        if (publicClass != null) {
            // 根据uuid查询图片的url
            FileResult file = fileService.findFileByUUID(publicClass.getUuid());
            if(file != null){
                publicClass.setPicUrl(file.getHttpUrl());
            }
            model.addAttribute("publicClass", toVo(publicClass));
        }
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation edit(@ModelAttribute("publicClass") Questionnaire publicClass, @CurrentUser UserInfo user) {
        Integer schoolId = user.getSchoolId();
        if (schoolId != null){
            publicClass.setSchoolId(schoolId);
        }
        publicClass = this.questionnaireService.modify(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    private void conditionFilter(UserInfo user, QuestionnaireCondition condition) {
        Integer schoolId = condition.getSchoolId();
        condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private List<QuestionnaireCondition> toVos(List<Questionnaire> publicClassList) {
        List<QuestionnaireCondition> voList = new ArrayList<>();
        for (Questionnaire publicClass : publicClassList) {
            voList.add(toVo(publicClass));
        }
        return voList;
    }

    private QuestionnaireCondition toVo(Questionnaire publicClass) {
        QuestionnaireCondition vo = new QuestionnaireCondition();
        BeanUtils.copyProperties(publicClass, vo);
        return vo;
    }
}
