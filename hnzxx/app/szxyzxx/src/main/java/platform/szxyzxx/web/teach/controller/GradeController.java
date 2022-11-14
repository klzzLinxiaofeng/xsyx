package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolTermCurrent;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.GradeCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.SchoolYearVo;
import platform.education.generalTeachingAffair.vo.TeamCondition;
import platform.education.generalcode.model.Stage;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.sys.vo.TreeVo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/***
 * 年级管理
 * @author admin
 *
 */
@Controller
@RequestMapping("/teach/grade")
public class GradeController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(GradeController.class);

    /**
     * 年级列表
     *
     * @return
     */
    @RequestMapping("/gradeList")
    public ModelAndView getGradeList(
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "dm", required = false) String dm,
            @ModelAttribute("gradeCondition") GradeCondition gradeCondition,
            @CurrentUser UserInfo user,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {

        String viewPath = "";
      /*  try {*/
            gradeCondition.setSchoolId(user.getSchoolId());

            //统计班级学生
            //this.teamStudentService.findStudentNumByGrade(user.getSchoolId());
            //List<Grade> gradeList = gradeService.findGradeByCondition(gradeCondition, page, order);
            List<Grade> gradeList = gradeService.findGradeByConditionTemp(gradeCondition, page, order);
            if ("list".equals(sub)) {
                viewPath = "/teach/grade/list";
            } else {
                viewPath = "/teach/grade/gradeList";
            }
            model.addAttribute("gradeList",gradeList);
            ModelAndView mav = new ModelAndView(viewPath,model.asMap());
            return mav;
       /* } catch (Exception e) {
            log.info("年级列表查询异常..");
            //e.printStackTrace();
        }
        return null;*/
    }

    public List<SchoolYearVo> getSchoolYearList(Integer schoolId) {
        List<SchoolYearVo> schoolYearVoList = new ArrayList<SchoolYearVo>();
        List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearOfSchool(schoolId);
        for (int i = 0; i < schoolYearList.size(); i++) {
            SchoolYearVo schoolYearVo = new SchoolYearVo();
            SchoolYear schoolYear = schoolYearList.get(i);
            SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolIdAndSchoolYearId(schoolId, schoolYear.getId());
            if (stc != null) {
                schoolYearVo.setFlag("1");
            } else {
                schoolYearVo.setFlag("0");
            }
            schoolYearVo.setYear(schoolYear.getYear());
            schoolYearVo.setName(schoolYear.getName());
            schoolYearVoList.add(schoolYearVo);
        }
        return schoolYearVoList;
    }


    /**
     * 修改班级
     *
     * @param id
     * @return
     */
    @RequestMapping("/modifyGrade")
    public ModelAndView modifyGrade(
            @RequestParam(value = "id", required = true) String id,
            @CurrentUser UserInfo user) {
        ModelAndView mav = null;
        try {
            mav = new ModelAndView();
            Grade grade = gradeService.findGradeById(Integer.parseInt(id));

            SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
            schoolYearCondition.setYear(grade.getSchoolYear());
            schoolYearCondition.setSchoolId(user.getSchoolId());
            SchoolYear schoolYear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
            List<Stage> stageList = jcStageService.findAll();
            List<SchoolYear> schoolYearList = schoolYearService.findSchoolYearByCondition(null, null, null);

            mav.addObject("schoolYear", schoolYear);
            mav.addObject("schoolYearList", schoolYearList);
            mav.addObject("grade", grade);
            mav.addObject("stageList", stageList);
            mav.setViewName("/teach/grade/modifyGrade");
        } catch (Exception e) {
            log.info("修改年级异常...");
            //e.printStackTrace();
        }
        return mav;
    }


    /**
     * 更新年级
     *
     * @param grade
     * @return
     */
    @RequestMapping("/updateGrade")
    @ResponseBody
    public String updateGrade(Grade grade) {
        try {
            grade.setModifyDate(new Date());
            gradeService.modify(grade);
        } catch (Exception e) {
            log.info("更新年级异常.....");
            e.printStackTrace();
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /**
     * 删除年级
     *
     * @param id
     */
    @RequestMapping("/deleteGrade")
    @ResponseBody
    public String deleteGrade(
            Grade grade,
            @RequestParam(value = "id", required = true) Integer id,
            @CurrentUser UserInfo user) {
        String returnMsg = "";
        try {
            TeamCondition teamCondition = new TeamCondition();
            teamCondition.setSchoolId(user.getSchoolId());
            teamCondition.setGradeId(id);
            teamCondition.setIsDelete(false);
            List<Team> teamList = this.teamService.findTeamByCondition(teamCondition, null, null);
            //System.out.println("teamList:"+teamList.size());
            if (!teamList.isEmpty()) {
                returnMsg = ResponseInfomation.NO_DELETE;
            } else {
                grade.setId(id);
                grade.setDelete(true);
                gradeService.modify(grade);

                returnMsg = ResponseInfomation.OPERATION_SUC;
            }
        } catch (Exception e) {
            log.info("删除年级异常....");
            e.printStackTrace();
            returnMsg = ResponseInfomation.OPERATION_FAIL;
            return returnMsg;
        }
        return returnMsg;
    }

    /**
     * 新增班级页面
     *
     * @return
     */
    @RequestMapping("/addGradePage")
    public ModelAndView addGradePage(@CurrentUser UserInfo user) {
        ModelAndView mav = new ModelAndView();
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(user.getSchoolId());
        mav.addObject("schoolTermCurrent", schoolTermCurrent);
        mav.addObject("schoolId", user == null || (user != null && user.getSchoolId() == null) ? 0 : user.getSchoolId());
        mav.setViewName("/teach/grade/addGradePage");
        return mav;
    }

    /**
     * 学段
     */
    public List<Stage> getStageVoList(String[] stageScope) {
        List<Stage> stageList = new ArrayList<Stage>();
        for (int i = 0; i < stageScope.length; i++) {
            String stage = stageScope[i];
            Stage se = this.jcStageService.findStageByCode(stage);
            if (se != null) {
                stageList.add(se);
            }
        }
        return stageList;
    }


    /***
     * 通过COde验证班级是否存在
     */
    @RequestMapping("/checkerGradeCode")
    @ResponseBody
    public boolean checkerGradeCode(
            @RequestParam(value = "code", required = true) String code) {
        List<Grade> gradeList = gradeService.findGradeByCode(code);
        boolean isExist = false;
        if (gradeList.isEmpty()) {
            isExist = true;
        } else {
            isExist = false;
        }
        return isExist;
    }


    /**
     * 保存年级
     *
     * @param grade
     */
    @RequestMapping("/addGrade")
    @ResponseBody
    public String addGrade(Grade grade,
                           @CurrentUser UserInfo user) {
        //Grade ge = null;
        try {
            grade.setFinishDate(new Date());
            grade.setBeginDate(new Date());
            grade.setCreateDate(new Date());
            grade.setModifyDate(new Date());
            grade.setSchoolId(user == null ? 0 : user.getSchoolId());
            grade.setTeamCount(0);
            grade.setDelete(false);
            gradeService.add(grade);
        } catch (Exception e) {
            log.info("..新增年级异常...");
            e.printStackTrace();
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    /**
     * @param user
     * @param condition
     * @param page
     * @param order
     * @param usePage
     * @return
     * @Method jsonList
     * @Function 功能描述：获取JSON数据
     * @Date 2015年5月18日
     */
    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    public List<Grade> jsonList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") GradeCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage) {

        conditionFilter(user, condition);
        page = usePage ? page : null;
        return this.gradeService.findGradeByCondition(condition, page, Order.asc("uni_grade_code"));
    }

    private void conditionFilter(UserInfo user, GradeCondition condition) {
        Integer schoolId = condition.getSchoolId();
        if (user != null && schoolId == null) {
            condition.setSchoolId(user.getSchoolId());
        }

        if (schoolId != null && !"".equals(schoolId)) {
            //当前学校所属的当前学期
            SchoolTermCurrent stc = this.schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
            condition.setSchoolYear(stc.getSchoolYear());
        }


    }

    /**
     * 获取年级信息ztree多选展示
     *
     * @author: yhc
     * @Date: 2020/12/8 15:37
     */
    @RequestMapping("/ztree/gradeList")
    @ResponseBody
    public List<TreeVo> getZtreeGradeList(
            @ModelAttribute("gradeCondition") GradeCondition gradeCondition,
            @CurrentUser UserInfo user) {
        gradeCondition.setSchoolId(user.getSchoolId());
        List<Grade> gradeList = gradeService.findGradeByConditionTemp(gradeCondition, null, null);
        return deptToTreeVo(gradeList);
    }

    private List<TreeVo> deptToTreeVo(List<Grade> depts) {
        List<TreeVo> treeVos = new ArrayList<TreeVo>();
        Page page = new Page();
        page.setPageSize(1);
        for (Grade grade : depts) {
            TreeVo vo = new TreeVo();
            vo.setId(String.valueOf(grade.getId()));
            vo.setpId(String.valueOf(0));
            vo.setName(grade.getName());
            vo.setIsParent(false);
            treeVos.add(vo);
        }
        return treeVos;
    }
}
