package platform.szxyzxx.web.moral.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.service.SchoolTermService;
import platform.education.generalTeachingAffair.vo.ResposeLyauiVO;
import platform.education.generalTeachingAffair.vo.WeekStarParamVo;
import platform.education.generalTeachingAffair.vo.WeekStarResonseVo;
import platform.education.user.vo.UserCondition;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.vo.UserInfo;
import platform.szxyzxx.web.moral.service.StudentWeekStarService;
import platform.szxyzxx.web.schoolbus.vo.StuVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author: yhc
 * @Date: 2021/4/12 19:34
 * @Description: 周明星学生
 */
@Controller
@RequestMapping("/student/star/student")
public class StudentWeekStarController {
    Logger log = LoggerFactory.getLogger(StudentWeekStarController.class);


    @Autowired
    @Qualifier("schoolTermService")
    protected SchoolTermService schoolTermService;

    @Autowired
    private StudentWeekStarService studentWeekStarService;


    private final static String viewBasePath = "/moral";


    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }


    /**
     * 教师管理权限
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String indexStu(
            @CurrentUser UserInfo user) {
        String viewPath = null;
        Integer teacherId = user.getTeacherId();
        if (teacherId == null) {
            return structurePath("/student_week_star/403");
        } else {
            viewPath = structurePath("/student_week_star/index");
        }
        return viewPath;
    }


    /**
     * 管理员权限
     *
     * @return
     */
    @RequestMapping(value = "/indexAdmin")
    public String indexAdmin(
            @CurrentUser UserInfo user) {
        String viewPath = structurePath("/student_week_star/indexAdmin");
        return viewPath;
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator(@RequestParam(value = "teacherId", required = false) Integer teacherId,@RequestParam(value = "type", required = false) Integer type, Model model) {
        model.addAttribute("teacherId", teacherId);
        model.addAttribute("type", type);
        return new ModelAndView(structurePath("/classTeacher/input"), model.asMap());
    }


    @RequestMapping(value = "/creator2", method = RequestMethod.GET)
    public ModelAndView creator2() {
        return new ModelAndView(structurePath("/student_week_star/input"));
    }


    /**
     * 上传学生评分excel
     *
     * @return
     */
    @RequestMapping(value = "/read")
    @ResponseBody
    public ResposeLyauiVO read(MultipartFile file, HttpServletRequest request, @CurrentUser UserInfo user) {
        ResposeLyauiVO resposeLyauiVO = studentWeekStarService.readExcelFile(file, request, user);
        return resposeLyauiVO;
    }


    /**
     * 查询xx之星
     *
     * @param user
     * @param weekStarParamVo
     * @param condition
     * @param page
     * @param order
     * @return
     */
    @RequestMapping(value = "/json")
    @ResponseBody
    public StuVo json(
            @CurrentUser UserInfo user,
            @RequestParam(value = "type", required = false) Integer type,
            WeekStarParamVo weekStarParamVo,
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        if (user.getTeacherId() != null) {
            weekStarParamVo.setTeacherId(user.getTeacherId());
        }
        weekStarParamVo.setSchoolId(user.getSchoolId());
        List<WeekStarResonseVo> list = studentWeekStarService.findWeekStarStu(weekStarParamVo, page, type);

        StuVo stuVo = new StuVo();
        stuVo.setList(list);
        stuVo.setPage(page);
        return stuVo;
    }

    /**
     * 查询xx之星学生信息
     *
     * @param user
     * @param id
     * @param order
     * @return
     */
    @RequestMapping(value = "/stuScoreList")
    @ResponseBody
    public StuVo stuScoreList(
            @CurrentUser UserInfo user,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "ids", required = false) String ids,
            @RequestParam(value = "type", required = false) Integer type,
            @ModelAttribute("order") Order order) {
        List<Map<String, Object>> list = studentWeekStarService.findWeekStarStuById(id, ids, type);

        StuVo stuVo = new StuVo();
        stuVo.setList(list);
        return stuVo;
    }


    /**
     * 删除周明星学生
     *
     * @return
     */
    @RequestMapping(value = "/deleteWeekStarStu")
    @ResponseBody
    public String deleteWeekStarStu(Integer id) {
        studentWeekStarService.deleteWeekStarStu(id);
        return "success";
    }


    /**
     * 任课教师绩效评价（语数英）
     */

    @RequestMapping(value = "/classTeacherIndex1")
    public String classTeacherType1(String type, ModelMap modelMap) {
        modelMap.put("type",type);
        return structurePath("/classTeacher/index");
    }

    /**
     * 任课教师绩效评价（综合科）
     */
    @RequestMapping(value = "/classTeacherIndex2")
    public String classTeacherIndex2(
            @CurrentUser UserInfo user) {
        String viewPath = null;
        viewPath = structurePath("/classTeacher/indexOther");
        return viewPath;
    }


    /**
     * 任课教师绩效
     *
     * @param user
     * @param type      1: 语数英 0：综合科
     * @param year      下拉框学年搜索
     * @param condition
     * @param page
     * @param order
     * @return
     */
    @RequestMapping(value = "/theTeacherScoreList")
    @ResponseBody
    public StuVo json(
            @CurrentUser UserInfo user,
            @RequestParam(value = "type", required = false) Integer type,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "xq", required = false) String xq,
            @RequestParam(value = "name", required = false) String name,
            @ModelAttribute("condition") UserCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        List<Map<String, Object>> list = studentWeekStarService.findTheTeacherScoreList(type, year, user.getSchoolId(), page,xq, name);
        StuVo stuVo = new StuVo();
        stuVo.setList(list);
        stuVo.setPage(page);
        return stuVo;
    }


    /**
     * 上传任课教师评分excel
     *
     * @return
     */
    @RequestMapping(value = "/uploadTeacherExcel")
    @ResponseBody
    public ResposeLyauiVO uploadTeacherExcel(MultipartFile file, HttpServletRequest request, @CurrentUser UserInfo user) {
        ResposeLyauiVO resposeLyauiVO = studentWeekStarService.uploadTeacherExcel(file, request, user);
        return resposeLyauiVO;
    }
}
