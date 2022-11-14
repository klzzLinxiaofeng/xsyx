package platform.szxyzxx.web.teach.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.SchoolYear;
import platform.education.generalTeachingAffair.model.Student;
import platform.education.generalTeachingAffair.model.StudentHealthArchive;
import platform.education.generalTeachingAffair.model.Team;
import platform.education.generalTeachingAffair.vo.SchoolYearCondition;
import platform.education.generalTeachingAffair.vo.StudentHealthArchiveCondition;
import platform.education.generalTeachingAffair.vo.StudentHealthArchiveVo;
import platform.service.storage.model.EntityFile;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;

@Controller
@RequestMapping("/teach/studentHealthArchive")
public class StudentHealthArchiveController extends BaseController {

    private final static String viewBasePath = "/teach/studentHealthArchive";

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") StudentHealthArchiveCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        order.setProperty(order.getProperty() != null ? order.getProperty()
                : "create_date");
        conditionFilter(user, condition);
        List<StudentHealthArchive> studentHealthArchives = null;
        List<StudentHealthArchiveVo> studentHealthArchiveVos = null;
        List<StudentHealthArchive> healthArchive = this.studentHealthArchiveService.findStudentHealthArchiveByCondition(condition, page, order);
        System.out.println(healthArchive);
        if ("list".equals(sub)) {

            studentHealthArchiveVos = toVos(
                    healthArchive, user);
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("studentHealthArchiveVos", studentHealthArchiveVos);
        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/view", method = RequestMethod.GET)
    public ModelAndView view(
            @CurrentUser UserInfo user,
            @RequestParam(value = "studentId", required = true) Integer studentId,
            Model model) {
        String viewPath = null;
        StudentHealthArchiveCondition condition = new StudentHealthArchiveCondition();
        conditionFilter(user, condition);
        condition.setStudentId(studentId);
        List<StudentHealthArchive> studentHealthArchiveList = this.studentHealthArchiveService.findStudentHealthArchiveByCondition(condition);
        List<StudentHealthArchiveVo> studentHealthArchiveVoList = toVos(studentHealthArchiveList, user);
        if (studentHealthArchiveVoList != null) {
            for (StudentHealthArchiveVo temp : studentHealthArchiveVoList) {
                EntityFile entity = this.entityFileService.findFileByUUID(temp.getAccessory());
                if (entity != null) {
                    temp.setAccessoryName(entity.getFileName());

                }
            }
        }

        model.addAttribute("studentHealthArchiveVoList", studentHealthArchiveVoList);
        viewPath = structurePath("/viewList");
        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentHealthArchive> jsonList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") StudentHealthArchiveCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage) {
        // conditionFilter(user, condition);
        page = usePage ? page : null;
        return this.studentHealthArchiveService
                .findStudentHealthArchiveByCondition(condition, page, order);
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator() {
        return new ModelAndView(structurePath("/add"));
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation add(StudentHealthArchive studentHealthArchive,
                                  @CurrentUser UserInfo user) {
        // 查询pj_student_health_archive表中是否存在记录，有则更新，无则添加
        StudentHealthArchive sha = new StudentHealthArchive();
        sha = this.studentHealthArchiveService.findUnique(studentHealthArchive.getTeamId(), studentHealthArchive.getStudentId());
        if (sha != null) {
            studentHealthArchive.setId(sha.getId());
            studentHealthArchive.setCreateDate(new Date());
            studentHealthArchive = this.studentHealthArchiveService.modify(studentHealthArchive);
        } else {
            studentHealthArchive.setSchoolId(user.getSchoolId());
            studentHealthArchive.setIsDelete(0);
            studentHealthArchive = this.studentHealthArchiveService.add(studentHealthArchive);
        }

        return studentHealthArchive != null ? new ResponseInfomation(
                studentHealthArchive.getId(), ResponseInfomation.OPERATION_SUC)
                : new ResponseInfomation();
    }


    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(
            StudentHealthArchive studentHealthArchive,
            @CurrentUser UserInfo user) {
        studentHealthArchive.setSchoolId(user.getSchoolId());
        studentHealthArchive.setIsDelete(0);
        studentHealthArchive = this.studentHealthArchiveService.add(studentHealthArchive);
        return studentHealthArchive != null ? new ResponseInfomation(studentHealthArchive.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    /**
     * 编辑页面
     * @param id
     * @param condition
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id,
            @ModelAttribute("condition") StudentHealthArchiveCondition condition,
            @CurrentUser UserInfo user, Model model) {

        StudentHealthArchive archive = this.studentHealthArchiveService.findStudentHealthArchiveById(id);


        if ( StringUtils.isNotEmpty(archive.getUuid())) {
            // 根据uuid查询图片的url

            String uuid = archive.getUuid();
            if (uuid != null && !("").equals(uuid)) {
                String[] split = uuid.split(",");
                List<String> imgUrls=new ArrayList<>(split.length);
                for (int i = 0; i < split.length; i++) {
                    FileResult imgs = fileService.findFileByUUID(split[i]);
                    if (imgs != null){
                        imgUrls.add(imgs.getHttpUrl());
                    }

                }

                model.addAttribute("imgUrls",imgUrls);
            }
        }



        StudentHealthArchiveVo studentHealthArchiveVo = toVo(archive, user);

        if (archive != null) {
            EntityFile entity = this.entityFileService.findFileByUUID(archive.getAccessory());
            model.addAttribute("entity", entity);
        }
        model.addAttribute("studentHealthArchiveVo", studentHealthArchiveVo);
        return new ModelAndView(structurePath("/input"), model.asMap());
    }


    /**
     * 详情页
     * @param id
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/viewer", method = RequestMethod.GET)
    public ModelAndView viewer(
            @RequestParam(value = "id", required = true) Integer id,
            @CurrentUser UserInfo user, Model model) {



        StudentHealthArchive studentHealthArchive = this.studentHealthArchiveService.findStudentHealthArchiveById(id);

        if ( StringUtils.isNotEmpty(studentHealthArchive.getUuid())) {
                // 根据uuid查询图片的url

                String uuid = studentHealthArchive.getUuid();
                if (uuid != null && !("").equals(uuid)) {
                    String[] split = uuid.split(",");
                    List<String> imgUrls=new ArrayList<>(split.length);
                    for (int i = 0; i < split.length; i++) {
                        FileResult imgs = fileService.findFileByUUID(split[i]);
                        if (imgs != null){
                            imgUrls.add(imgs.getHttpUrl());
                        }

                    }

                    model.addAttribute("imgUrls",imgUrls);
                }
        }



        StudentHealthArchiveVo studentHealthArchiveVo = toVo(studentHealthArchive, user);

        studentHealthArchiveVo.setTypes(studentHealthArchive.getTypes());
        if (studentHealthArchive != null) {
            EntityFile entity = this.entityFileService.findFileByUUID(studentHealthArchive.getAccessory());
            model.addAttribute("entity", entity);
        }

        //model.addAttribute("imgs", imgs);
        model.addAttribute("isCK", "disable");
        model.addAttribute("studentHealthArchiveVo", studentHealthArchiveVo);
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    /**
     * 删除 已屏蔽
     * @param id
     * @param studentHealthArchive
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id,
                         StudentHealthArchive studentHealthArchive) {
        if (studentHealthArchive != null) {
            studentHealthArchive.setId(id);
        }
        try {
            this.studentHealthArchiveService.remove(studentHealthArchive);
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(StudentHealthArchive studentHealthArchive) {
        studentHealthArchive = this.studentHealthArchiveService
                .modify(studentHealthArchive);
        return studentHealthArchive != null ? new ResponseInfomation(
                studentHealthArchive.getId(), ResponseInfomation.OPERATION_SUC)
                : new ResponseInfomation();
    }

    /**
     * 导出 已屏蔽
     * @param request
     * @param response
     * @param schoolYear
     * @param gradeId
     * @param teamId
     * @param studentId
     * @param healthType
     * @param condition
     * @param user
     */
    @RequestMapping(value = "/download")
    public void downLoadClassroom(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "studentId", required = false) Integer studentId,
            @RequestParam(value = "healthType", required = false) String healthType,
            @ModelAttribute("condition") StudentHealthArchiveCondition condition,
            @CurrentUser UserInfo user) {
        ParseConfig config = SzxyExcelTookit.getConfig("downloadHealth");
        conditionFilter(user, condition);
        condition.setSchoolYear(schoolYear);
        condition.setGradeId(gradeId);
        condition.setTeamId(teamId);
        condition.setStudentId(studentId);
        condition.setType(healthType);
        List<StudentHealthArchive> studentHealthArchiveList =
                this.studentHealthArchiveService.findStudentHealthArchiveByCondition(condition);
        List<StudentHealthArchiveVo> studentHealthArchiveVoList = toVos(studentHealthArchiveList, user);

        List<Object> list = new ArrayList<Object>();
        String[] titles = {"学年", "年级", "班级", "姓名", "健康类型"};
        config.setTitles(titles);
        config.setSheetTitle("学生健康档案信息表");
        String fileName = "学生健康档案信息表.xls";
        try {
            for (StudentHealthArchiveVo vo : studentHealthArchiveVoList) {
                list.add(vo);
            }
            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, fileName);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private List<StudentHealthArchiveVo> toVos(
            List<StudentHealthArchive> studentHealthArchiveList, UserInfo user) {
        List<StudentHealthArchiveVo> voList = new ArrayList<StudentHealthArchiveVo>();
        if (studentHealthArchiveList.size() > 0) {
            for (StudentHealthArchive studentHealthArchive : studentHealthArchiveList) {
                voList.add(toVo(studentHealthArchive, user));
            }
        }
        return voList;
    }

    private StudentHealthArchiveVo toVo(
            StudentHealthArchive studentHealthArchive, UserInfo user) {
        Student student = new Student();
        Grade grade = new Grade();
        Team team = new Team();

        StudentHealthArchiveVo vo = new StudentHealthArchiveVo();
        BeanUtils.copyProperties(studentHealthArchive, vo);

        // 获取学生姓名
        student = this.studentService.findStudentById(studentHealthArchive
                .getStudentId());
        if (student != null) {
            vo.setStudentName(student.getName());
        }
        // 获取班级名称
        team = this.teamService.findTeamById(studentHealthArchive.getTeamId());
        if (team != null) {
            vo.setSchoolYear(team.getSchoolYear()); // 学年的开始年份
            vo.setTeamName(team.getName()); // 班级名称
            SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
            schoolYearCondition.setSchoolId(user.getSchoolId());
            schoolYearCondition.setYear(team.getSchoolYear());
            // 获取学年名称
            SchoolYear schoolYear = this.schoolYearService
                    .findSchoolYearByYear(schoolYearCondition);
            if (schoolYear != null) {
                vo.setSchoolYearName(schoolYear.getName());
            }
            // 获取年级名称
            grade = this.gradeService.findGradeById(team.getGradeId());
            if (grade != null) {
                vo.setGradeId(grade.getId());
                vo.setGradeName(grade.getName());
            }
        }
        return vo;
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private void conditionFilter(UserInfo user,
                                 StudentHealthArchiveCondition condition) {
        Integer schoolId = condition.getSchoolId();
        //Integer isDeleted = condition.getIsDelete();
        //condition.setIsDelete(isDeleted != null ? isDeleted : 0);
        condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
    }

}
