package platform.szxyzxx.web.teach.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.PublicTeacherVo;
import platform.education.generalTeachingAffair.model.PublicTimeVo;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeacherVo;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

@RequestMapping(value = "/teach/teacher")
public class ExtTeacherController extends BaseController {

    private final static String viewBasePath = "/teach/teacher";

    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    public List<TeacherVo> jsonList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") TeacherCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage) {
        conditionFilter(user, condition);
        page = usePage ? page : null;
        return teacherService.findTeacherVoByCondition(condition, page, order);
    }

    @RequestMapping(value = "/list")
    public ModelAndView getList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") TeacherCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage,
            @RequestParam(value = "userOrder", required = false) boolean userOrder,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "excludeSelf", required = false) boolean excludeSelf,
            @RequestParam(value = "enableMultiCampus", required = false) boolean enableMultiCampus,
            @RequestParam(value = "enableBatch", required = false) String enableBatch,
            HttpServletRequest request,
            Model model) {

        if (request.getQueryString() != null && condition.getName() != null && !"".equals(condition.getName())) {
            String str = condition.getName();
            byte[] bytes;
            String name = "";
            try {
                bytes = str.getBytes("ISO-8859-1");
                name = new String(bytes, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            condition.setName(name);
        }


        if (enableMultiCampus) {
            if (condition.getSchoolId() == null) {
                condition.setSchoolId(user.getSchoolId());
            }
            // condition.setSchoolId(condition.getSchoolId());

        } else {

            conditionFilter(user, condition);
        }


        condition.setIsDelete(false);
        if (type == "1" || "1".equals(type)) {
            usePage = false;
        }
        condition.setExcludeSelf(excludeSelf);
        if (excludeSelf) {
            condition.setId(user.getTeacherId());
        }
        List<TeacherVo> items = this.teacherService.findTeacherVoByCondition(condition, usePage ? page : null, userOrder ? order : null);
        model.addAttribute("items", items);
        model.addAttribute("deptIdParam", condition.getDeptId());
        model.addAttribute("schoolIdParam", enableMultiCampus == true ? condition.getSchoolId() : user.getSchoolId());
        model.addAttribute("subjectCodeParam", condition.getSubjectCode());
        model.addAttribute("nameParam", condition.getName());
        model.addAttribute("enableBatch", enableBatch);
        model.addAttribute("excludeSelf", excludeSelf);
        model.addAttribute("enableMultiCampus", enableMultiCampus);
        return new ModelAndView(structurePath("/list" + type), model.asMap());
    }

    @RequestMapping(value = "/xiaozu/list")
    public ModelAndView getGroupList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") TeacherCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage,
            @RequestParam(value = "userOrder", required = false) boolean userOrder,
            @RequestParam(value = "type", required = false) String type,
            Model model) {
        conditionFilter(user, condition);
        List<TeacherVo> items = this.teacherService.findTeacherVoByGroupCondtion(condition, usePage ? page : null, userOrder ? order : null);
        model.addAttribute("items", items);
        return new ModelAndView(structurePath("/list" + type), model.asMap());
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private void conditionFilter(UserInfo user, TeacherCondition condition) {
        Integer schoolId = condition.getSchoolId();
        if (user != null && schoolId == null) {
            condition.setIsDelete(false);
            condition.setSchoolId(user.getSchoolId());
        }
        condition.setIsDelete(false);
    }


    @RequestMapping(value = "/listInfo")
    public ModelAndView listInfo(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") PublicTeacherVo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage,
            @RequestParam(value = "userOrder", required = false) boolean userOrder,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "excludeSelf", required = false) boolean excludeSelf,
            @RequestParam(value = "enableMultiCampus", required = false) boolean enableMultiCampus,
            @RequestParam(value = "enableBatch", required = false) String enableBatch,
            HttpServletRequest request,
            Model model) {
        if (condition.getSchoolId() == null) {
            condition.setSchoolId(user.getSchoolId());
        }
        condition.setIsDelete(0);
        // 为了解决这个项目没有文档什么说明都没有，不想使用分页的情况，源代码已经打包
        page.setPageSize(100000);
        List<PublicTeacherVo> items = this.publicClassService.findPublicClassTeacherInfoByCondition(condition, page, order);
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                // 根据uuid查询封面的url
                FileResult file = fileService.findFileByUUID(items.get(i).getCoverUuid());
                if (file != null) {
                    items.get(i).setCoverUrl(file.getHttpUrl());
                }
            }
        }
//        List<PublicTeacherVo> items = this.publicClassService.findPublicClassTeacherInfo(condition);
        model.addAttribute("items", items);
        model.addAttribute("schoolIdParam", enableMultiCampus == true ? condition.getSchoolId() : user.getSchoolId());
        model.addAttribute("enableBatch", enableBatch);
        model.addAttribute("excludeSelf", excludeSelf);
        model.addAttribute("enableMultiCampus", enableMultiCampus);
        return new ModelAndView(structurePath("/listInfo" + type), model.asMap());
    }


    @RequestMapping(value = "/time")
    public ModelAndView time(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") PublicTimeVo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage,
            @RequestParam(value = "userOrder", required = false) boolean userOrder,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "excludeSelf", required = false) boolean excludeSelf,
            @RequestParam(value = "enableMultiCampus", required = false) boolean enableMultiCampus,
            @RequestParam(value = "enableBatch", required = false) String enableBatch,
            HttpServletRequest request,
            Model model) {
        if (condition.getSchoolId() == null) {
            condition.setSchoolId(user.getSchoolId());
        }
        condition.setIsDelete(0);
        // 为了解决这个项目没有文档什么说明都没有，不想使用分页的情况，并且源代码已经打包
        page.setPageSize(100000);
        List<PublicTimeVo> items = this.publicClassService.findPublicClassTimeInfoByCondition(condition, page, order);
        model.addAttribute("items", items);
        model.addAttribute("schoolIdParam", enableMultiCampus == true ? condition.getSchoolId() : user.getSchoolId());
        model.addAttribute("enableBatch", enableBatch);
        model.addAttribute("excludeSelf", excludeSelf);
        model.addAttribute("enableMultiCampus", enableMultiCampus);
        return new ModelAndView(structurePath("/time"), model.asMap());
    }

}
