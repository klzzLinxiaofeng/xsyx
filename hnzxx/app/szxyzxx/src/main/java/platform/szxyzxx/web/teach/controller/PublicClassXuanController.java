package platform.szxyzxx.web.teach.controller;


import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.PublicClass;
import platform.education.generalTeachingAffair.model.PublicGradeRelatedVo;
import platform.education.generalTeachingAffair.model.PublicTeacherVo;
import platform.education.generalTeachingAffair.model.PublicTimeVo;
import platform.education.generalTeachingAffair.vo.PublicClassCondition;
import platform.education.generalTeachingAffair.vo.PublicClassVo;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Controller
@RequestMapping("/public/publicClassXuan")
public class PublicClassXuanController extends BaseController {
    private Logger log = LoggerFactory.getLogger(getClass());


    private final static String viewBasePath = "/teach/publicClass";
    private Integer status = 0;

    @RequestMapping(value = "/index/{leixing}")
    public ModelAndView index(
            @PathVariable Integer leixing,
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") PublicClassCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model,
                    HttpSession session
            ) {
        log.info(""+leixing);
        session.setAttribute("leixing", leixing);
        String viewPath = null;

        conditionFilter(user, condition);

        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");

        condition.setLeibie(leixing);
        List<PublicClass> items = this.publicClassService.findPublicClassByConditionLingxing(condition, page, order,leixing);
        for (PublicClass item : items) {

            // 获取图片信息
            String coverUuid = item.getCoverUuid();
            if (coverUuid != null && !("").equals(coverUuid)) {
                // 根据uuid查询封面的url
                FileResult file = fileService.findFileByUUID(coverUuid);
                if (file != null) {
                    item.setCoverUrl(file.getHttpUrl());
                }
            }
            Integer id = item.getId();

            if (id != null) {
                log.info("44");
                // 获取教师名称
                List<PublicTeacherVo> publicTeacher = this.publicClassService.findByClassId(id, user.getSchoolId());
                if (publicTeacher != null && publicTeacher.size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < publicTeacher.size(); i++) {
                        String name = publicTeacher.get(i).getName();
                        stringBuilder.append(name);
                        if (i != publicTeacher.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                    item.setTeacherName(stringBuilder.toString());
                }
                // 获取对应的课程时间信息
                List<PublicTimeVo> publicTimeVo = this.publicClassService.findTimeInfoByClassId(id, user.getSchoolId());
                log.info("66");
                if (publicTimeVo != null && publicTimeVo.size() > 0) {
                    log.info("77");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < publicTimeVo.size(); i++) {
                        String name = publicTimeVo.get(i).getClassTime();
                        stringBuilder.append(name);
                        if (i != publicTimeVo.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                    item.setClassTime(stringBuilder.toString());
                }

                // 获取对应的年级信息
                log.info("88");
                List<PublicGradeRelatedVo> publicGradeList = this.publicClassService.findGradeInfoByClassId(id, user.getSchoolId());
                log.info("99");
                if (publicGradeList != null && publicGradeList.size() > 0) {
                    log.info("210");
                    StringBuilder stringBuilder = new StringBuilder();
                    for (int i = 0; i < publicGradeList.size(); i++) {
                        Integer gradeId = publicGradeList.get(i).getGrade();
                        String fullName = intGradeToString(gradeId);
                        stringBuilder.append(fullName);
                        if (i != publicGradeList.size() - 1) {
                            stringBuilder.append(",");
                        }
                    }
                    item.setFullName(stringBuilder.toString());
                }
            }
        }
        viewPath = structurePath("/list2");
        if ("list".equals(sub)) {
            log.info("22222");
            viewPath = structurePath("/list2");
        } else {
            log.info("1111");
            viewPath = structurePath("/index2");
        }
        model.addAttribute("items", toVos(items));
        return new ModelAndView(viewPath, model.asMap());
    }

    private void conditionFilter(UserInfo user, PublicClassCondition condition) {
        Integer schoolId = condition.getSchoolId();
        Boolean isDeleted = condition.getIsDelete();
        condition.setIsDelete(isDeleted != null ? isDeleted : false);
        condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
    }
    private String intGradeToString(Integer grade) {
        Properties properties = new Properties();
        try {
            properties.load(PublicClassController.class.getClassLoader().getResourceAsStream("config/properties/custom/grade.properties"));
//			System.err.println("this is properties"+grade);
        } catch (IOException e) {
            log.debug("grade_properties获取失败：" + e.getStackTrace());
        }
        return properties.getProperty(grade + "");
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
    private List<PublicClassVo> toVos(List<PublicClass> publicClassList) {
        List<PublicClassVo> voList = new ArrayList<PublicClassVo>();
        for (PublicClass publicClass : publicClassList) {
            voList.add(toVo(publicClass));
        }
        return voList;
    }
    private PublicClassVo toVo(PublicClass publicClass) {
        PublicClassVo vo = new PublicClassVo();
        BeanUtils.copyProperties(publicClass, vo);
//        Teacher teacher = this.teacherService.findTeacherById(publicClass.getTeacherId());
//        if (teacher != null) {
//            vo.setTeacherName(teacher.getName());
//        vo.setTeacherName(publicClass.getTeacherName());
//        }
        return vo;
    }
}
