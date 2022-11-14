package platform.szxyzxx.web.teach.controller;


import com.alibaba.fastjson.JSON;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.education.generalTeachingAffair.service.SchoolYearService;
import platform.education.generalTeachingAffair.service.TeacherService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.TeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 接送管理模块
 *
 * @author: yhc
 * @Date: 2020/10/14 9:22
 * @Description:
 */
@Controller
@RequestMapping("/class/after")
public class PickUpManagerController {

    private static final Logger log = LoggerFactory.getLogger(PickUpManagerController.class);
    private final static String viewBasePath = "/teach/pickUpManager/";


    @Autowired
    @Qualifier("teacherService")
    protected TeacherService teacherService;
    /**
     * 班主任
     */
    @Autowired
    @Qualifier("teamTeacherService")
    protected TeamTeacherService teamTeacherService;

    /**
     * 跳转到首页
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index() {
        return new ModelAndView(viewBasePath + "index");
    }

    /**
     * 获取班级信息
     */
    @RequestMapping(value = "/getClassInfo")
    @ResponseBody
    public List<TeamTeacherVo> getClassInfo(@CurrentUser UserInfo user, @ModelAttribute("page") Page page) {
        SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
        String year = yyyy.format(new Date());
        // 根据账号获取schoolid
        TeacherCondition teacherCondition = new TeacherCondition();
        teacherCondition.setUserId(user.getId());
        List<Teacher> teacherList = teacherService.findTeacherByCondition(teacherCondition, page, Order.desc("create_date"));
        if (teacherList != null && teacherList.size() > 0) {
            TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
            teamTeacherCondition.setSchoolId(teacherList.get(0).getSchoolId());
            teamTeacherCondition.setTeacherId(teacherList.get(0).getId());
            teamTeacherCondition.setSchoolYear(year);
            teamTeacherCondition.setPosition("班主任");
            teamTeacherCondition.setType(1);
            List<TeamTeacherVo> teamList = teamTeacherService.findTeamTeacherVoByGroupBy(teamTeacherCondition);
            return teamList;
        }
        return null;
    }


    /**
     * 根据班主任和班级获取学生的接送信息
     *
     * @param user
     * @param page
     * @param teamId
     * @param gradeId
     * @return
     */
    @RequestMapping(value = "/getStuLocationInfo")
    @ResponseBody
    public String getStuLocationInfo(@CurrentUser UserInfo user, @ModelAttribute("page") Page page
            , @RequestParam(value = "teamId") Integer teamId
            , @RequestParam(value = "gradeId") Integer gradeId) {
        if (teamId == null || gradeId == null) {
            log.error("获取参数为空");
            return null;
        }
        try {
            SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
            // 根据账号获取schoolid
            TeacherCondition teacherCondition = new TeacherCondition();
            teacherCondition.setUserId(user.getId());
            List<Teacher> teacherList = teacherService.findTeacherByCondition(teacherCondition, page, Order.desc("create_date"));
            if (teacherList != null && teacherList.size() > 0) {
                TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
                teamTeacherCondition.setSchoolId(teacherList.get(0).getSchoolId());
                teamTeacherCondition.setGradeId(gradeId);
                teamTeacherCondition.setTeamId(teamId);
                List<Map<String, String>> list = teamTeacherService.findStuInfo(teamId, gradeId, teacherList.get(0).getSchoolId());
                return JSON.toJSONString(list);
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return null;
    }

}
