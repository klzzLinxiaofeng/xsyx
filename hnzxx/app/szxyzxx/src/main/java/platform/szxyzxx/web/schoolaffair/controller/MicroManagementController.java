package platform.szxyzxx.web.schoolaffair.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.MicroTypePojo;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.service.MicroManagerService;
import platform.education.generalTeachingAffair.vo.MicroManagerPojo;
import platform.education.generalTeachingAffair.vo.UserCommentsPojo;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

/**
 * 校务管理-微课管理
 *
 * @author: yhc
 * @Date: 2020/10/16 10:33
 * @Description:
 */

@Controller
@RequestMapping("/micro/management")
public class MicroManagementController extends BaseController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private final static String viewBasePath = "/schoolaffair/mircroManager";

    @Autowired
    private BasicSQLService basicSQLService;

    @Autowired
    @Qualifier("microManagerService")
    protected MicroManagerService microManagerService;

    /*
    * 获取当前学期的年级
    */
    @RequestMapping(value = "/getGradel")
    @ResponseBody
    public List findBygetGradle(@CurrentUser UserInfo user){
        String sql=" SELECT * FROM pj_school_term_current  where school_id ="+user.getSchoolId();
        List<Map<String,Object>>list =basicSQLService.find(sql);
        String schoolYear = null;
        if (list != null) {
            // 当前学校所属的当前学期
            schoolYear = (String)list.get(0).get("school_year");
        }
        String sql2="select id, name, school_id from pj_grade where is_deleted = 0 and school_id ="+user.getSchoolId()+" and school_year ="+schoolYear;
        return basicSQLService.find(sql2);
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") MicroManagerPojo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        conditionFilter(user, condition);
        order.setProperty(order.getProperty() != null ? order.getProperty() : "id");
        Integer schoolId = user.getSchoolId();
        condition.setSchoolId(schoolId);
        Integer typeId = condition.getTypeId();
        boolean typeFlg = false;
        // 当为学校风采时课程对应所有年级
        if (typeId != null && typeId == 1){
            condition.setGradeId(null);
            typeFlg = true;
        }
        List<MicroManagerPojo> items = this.microManagerService.findMicroManagerByCondition(condition, page, order);
//        Integer gradeId = condition.getGradeId();
        // 课程类型为1 学校风采时，对应所有年级
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                // 当年级搜索时并且课程类型不是学校风采,搜索完成后重新修正年级
//                if (!typeFlg && gradeId != null) {
//                    Integer id = items.get(i).getId();
//                    // 获取当前课程对应的年级
//                    String gradeName = this.microManagerService.findMicroGradeNameById(id, schoolId);
//                    items.get(i).setGradeName(gradeName);
//                }
//                Integer teacherId = items.get(i).getTeacherId();
//                if (teacherId != null && teacherId != 0) {
//                    Teacher teacher = this.teacherService.findTeacherById(teacherId);
//                    if (teacher != null) {
//                        items.get(i).setTeacherName(teacher.getName());
//                    }
//                }
                // 根据uuid查询封面的url
                FileResult file = fileService.findFileByUUID(items.get(i).getCoverUuid());
                if (file != null) {
                    items.get(i).setCoverUrl(file.getHttpUrl());
                }

                // 根据uuid查询课件的url
                FileResult calssFile = fileService.findFileByUUID(items.get(i).getClassUuid());
                if (file != null) {
                    items.get(i).setClassUrl(calssFile.getHttpUrl());
                }

                // 根据uuid查询视频的url
                FileResult video = fileService.findFileByUUID(items.get(i).getVideoUuid());
                if (file != null) {
                    items.get(i).setVideoUrl(video.getHttpUrl());
                }
            }

        }
        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("items", items);

        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator(Model model) {
        return new ModelAndView(structurePath("/input"));
    }


    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(@RequestParam(value = "id", required = true) Integer id, Model model) {
        MicroManagerPojo microManagerPojo = this.microManagerService.findMicroManagerPojoById(id);
        if (microManagerPojo != null) {
            // 根据uuid查询封面的url
            FileResult file = fileService.findFileByUUID(microManagerPojo.getCoverUuid());
            if (file != null) {
                microManagerPojo.setCoverUrl(file.getHttpUrl());
                model.addAttribute("entity", file.getEntityFile());
            }

            // 根据uuid查询课件的url
            FileResult calssFile = fileService.findFileByUUID(microManagerPojo.getClassUuid());
            if (calssFile != null) {
                microManagerPojo.setClassUrl(calssFile.getHttpUrl());
                model.addAttribute("entity2", calssFile.getEntityFile());
            }

            // 根据uuid查询视频的url
            FileResult video = fileService.findFileByUUID(microManagerPojo.getVideoUuid());
            if (video != null) {
                microManagerPojo.setVideoUrl(video.getHttpUrl());
                model.addAttribute("entity3", video.getEntityFile());
            }
            model.addAttribute("publicClass", microManagerPojo);
        }
        return new ModelAndView(structurePath("/input"), model.asMap());
    }


    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(MicroManagerPojo publicClass, @CurrentUser UserInfo user) {
        conditionFilter(user, publicClass);

        publicClass = this.microManagerService.add(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation edit(@ModelAttribute("publicClass") MicroManagerPojo publicClass, @CurrentUser UserInfo user) {

        conditionFilter(user, publicClass);
        publicClass = this.microManagerService.modify(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, MicroManagerPojo publicClass) {
        if (publicClass != null) {
            publicClass.setId(id);
        }
        return this.microManagerService.abandon(publicClass);
    }

    /**
     * 评论管理
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
    @RequestMapping(value = "/comment/index")
    public ModelAndView commentIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") MicroManagerPojo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath;
        conditionFilter(user, condition);
        order.setProperty(order.getProperty() != null ? order.getProperty() : "id");
        condition.setSchoolId(user.getSchoolId());
        List<UserCommentsPojo> items = this.microManagerService.findStuCommentsPojoByCondition(condition, page, order);
        if ("list".equals(sub)) {
            viewPath = structurePath("/comment/list");
        } else {
            viewPath = structurePath("/comment/index");
        }
        model.addAttribute("items", items);
        model.addAttribute("microId", condition.getId());

        return new ModelAndView(viewPath, model.asMap());
    }


    /**
     * 删除评论(逻辑删除), 根据评论id
     *
     * @param userCommentsPojo
     * @return
     */
    @RequestMapping(value = "/comment/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public String commentDelete(@CurrentUser UserInfo user, UserCommentsPojo userCommentsPojo) {
        userCommentsPojo.setSchoolId(user.getSchoolId());
        return this.microManagerService.commentAbandon(userCommentsPojo);
    }


    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }


    private void conditionFilter(UserInfo user, MicroManagerPojo condition) {
        Integer schoolId = condition.getSchoolId();
        condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
    }

    /**
     * 获取类别下拉框信息
     *
     * @return
     */
    @RequestMapping(value = "/type", method = RequestMethod.GET)
    @ResponseBody
    public List<MicroTypePojo> type(@CurrentUser UserInfo user) {
        MicroTypePojo microTypePojo = new MicroTypePojo();
        microTypePojo.setSchoolId(user.getSchoolId());
        return this.microManagerService.findMicroType(microTypePojo);
    }
}
