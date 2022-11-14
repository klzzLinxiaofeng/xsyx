package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.service.storage.vo.FileResult;
import platform.szxyzxx.kexuanke.service.KeXuanKeService;
import platform.szxyzxx.kexuanke.vo.GradeKeXuanKe;
import platform.szxyzxx.kexuanke.vo.KeXuanKe;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/teach/publicClass")
public class PublicClassController extends BaseController {
    private Logger log = LoggerFactory.getLogger(getClass());
    private final static String viewBasePath = "/teach/publicClass";
    private Integer status = 0;
    @Autowired
    private KeXuanKeService keXuanKeService;

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") PublicClassCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        conditionFilter(user, condition);
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        List<PublicClass> items = this.publicClassService.findPublicClassByCondition(condition, page, order);
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
                if (publicTimeVo != null && publicTimeVo.size() > 0) {
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
                List<PublicGradeRelatedVo> publicGradeList = this.publicClassService.findGradeInfoByClassId(id, user.getSchoolId());
                if (publicGradeList != null && publicGradeList.size() > 0) {
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
        if ("list".equals(sub)) {
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/index");
        }
        model.addAttribute("items", toVos(items));

        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/grade", method = RequestMethod.GET)
    @ResponseBody
    /**
     * @Params：[]
     * @Date：16:53 2020/9/21
     * @Description： 通过schoolId查询相关的年级信息
     */
    public Map<String, List<Grade>> findGradeBySchoolId(@CurrentUser UserInfo user,
                                                        Model model) {
        Map<String, List<Grade>> map = new HashMap<>();

        try {
            List<Grade> gradeAll = publicClassService.findGradeBySchoolId(user.getSchoolId());
            for (Grade grade : gradeAll) {
                grade.setStatus(this.status);
            }
            map.put("grade", gradeAll);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    public List<PublicClass> jsonList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") PublicClassCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage) {
        conditionFilter(user, condition);
        page = usePage ? page : null;
        return this.publicClassService.findPublicClassByCondition(condition, page, order);
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator(Model model) {
        this.status = 1;
        return new ModelAndView(structurePath("/input"));
    }

    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(PublicClass publicClass, @CurrentUser UserInfo user) {
        log.info("取值"+publicClass);
        this.status = 1;
        if(publicClass.getIsCailiao()==1){
            publicClass.setCailiaofei(0.00);
        }
        if(publicClass.getXuefei()==null){
            publicClass.setXuefei(0.00);
        }
        String fullNameArrs = publicClass.getFullNameArr();
        if (fullNameArrs != null && !("").equals(fullNameArrs)) {
            StringBuilder stringBuilder = new StringBuilder();
            String[] fullNameArr = fullNameArrs.split(",");
            for (int i = 0; i < fullNameArr.length; i++) {
                //	//1.0将年级年级字符串转成数字返回
                String fullName = fullNameArr[i];
                stringBuilder.append(stringGradeToInt(fullName));
                if (i != fullNameArr.length - 1) {
                    stringBuilder.append(",");
                }
            }
            publicClass.setGrade(stringBuilder.toString());
        }
        publicClass.setEnrollNumber(0);
        publicClass.setSchoolId(user.getSchoolId());
        publicClass.setSchoolYear(user.getSchoolYear());
        publicClassService.add(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }




    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        this.status = 2;
        PublicClass publicClass = publicClassService.findPublicClassById(id);
        String coverUuid = publicClass.getCoverUuid();
        if (coverUuid != null && !("").equals(coverUuid)) {
            // 根据uuid查询封面的url
            FileResult file = fileService.findFileByUUID(coverUuid);
            if (file != null) {
                publicClass.setCoverUrl(file.getHttpUrl());
            }
        }

        Integer ids = publicClass.getId();
        if (ids != null) {
            // 获取教师名称
            List<PublicTeacherVo> publicTeacher = this.publicClassService.findByClassId(ids, publicClass.getSchoolId());
            if (publicTeacher != null && publicTeacher.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < publicTeacher.size(); i++) {
                    String name = publicTeacher.get(i).getName();
                    stringBuilder.append(name);
                    sb.append(publicTeacher.get(i).getId());
                    if (i != publicTeacher.size() - 1) {
                        stringBuilder.append(",");
                        sb.append(",");
                    }
                }
                publicClass.setTeacherName(stringBuilder.toString());
                publicClass.setTeacherId(sb.toString());
            }
            // 获取对应的课程时间信息
            List<PublicTimeVo> publicTimeVo = this.publicClassService.findTimeInfoByClassId(ids, publicClass.getSchoolId());
            if (publicTimeVo != null && publicTimeVo.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < publicTimeVo.size(); i++) {
                    String name = publicTimeVo.get(i).getClassTime();
                    stringBuilder.append(name);
                    sb.append(publicTimeVo.get(i).getId());
                    if (i != publicTimeVo.size() - 1) {
                        stringBuilder.append(",");
                        sb.append(",");
                    }
                }
                publicClass.setClassTime(stringBuilder.toString());
                publicClass.setTimeId(sb.toString());
            }
            // 获取对应的年级信息
            List<PublicGradeRelatedVo> publicGradeList = this.publicClassService.findGradeInfoByClassId(id, publicClass.getSchoolId());
            if (publicGradeList != null && publicGradeList.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < publicGradeList.size(); i++) {
                    Integer gradeId = publicGradeList.get(i).getGrade();
                    String fullName = intGradeToString(gradeId);
                    stringBuilder.append(fullName);
                    if (i != publicGradeList.size() - 1) {
                        stringBuilder.append(",");
                    }
                }
                publicClass.setFullName(stringBuilder.toString());
            }
        }

        if (publicClass != null) {
            model.addAttribute("publicClass", publicClass);
        }
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping(value = "/viewer", method = RequestMethod.GET)
    public ModelAndView viewer(
            @RequestParam(value = "id", required = true) Integer id,
            Model model, @CurrentUser UserInfo user) {
        PublicClass publicClass = this.publicClassService.findPublicClassById(id);

        // 获取图片信息
        String coverUuid = publicClass.getCoverUuid();
        if (coverUuid != null && !("").equals(coverUuid)) {
            // 根据uuid查询封面的url
            FileResult file = fileService.findFileByUUID(coverUuid);
            if (file != null) {
                publicClass.setCoverUrl(file.getHttpUrl());
            }
        }
        Integer pid = publicClass.getId();
        if (id != null) {
            // 获取教师名称
            List<PublicTeacherVo> publicTeacher = this.publicClassService.findByClassId(pid, user.getSchoolId());
            if (publicTeacher != null && publicTeacher.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < publicTeacher.size(); i++) {
                    String name = publicTeacher.get(i).getName();
                    stringBuilder.append(name);
                    if (i != publicTeacher.size() - 1) {
                        stringBuilder.append(",");
                    }
                }
                publicClass.setTeacherName(stringBuilder.toString());
            }
            // 获取对应的课程时间信息
            List<PublicTimeVo> publicTimeVo = this.publicClassService.findTimeInfoByClassId(pid, user.getSchoolId());
            if (publicTimeVo != null && publicTimeVo.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < publicTimeVo.size(); i++) {
                    String name = publicTimeVo.get(i).getClassTime();
                    stringBuilder.append(name);
                    if (i != publicTimeVo.size() - 1) {
                        stringBuilder.append(",");
                    }
                }
                publicClass.setClassTime(stringBuilder.toString());
            }
            // 获取对应的年级信息
            List<PublicGradeRelatedVo> publicGradeList = this.publicClassService.findGradeInfoByClassId(id, user.getSchoolId());
            if (publicGradeList != null && publicGradeList.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < publicGradeList.size(); i++) {
                    Integer gradeId = publicGradeList.get(i).getGrade();
                    String fullName = intGradeToString(gradeId);
                    stringBuilder.append(fullName);
                    if (i != publicGradeList.size() - 1) {
                        stringBuilder.append(",");
                    }
                }
                publicClass.setFullName(stringBuilder.toString());
            }
        }


        model.addAttribute("isCK", "disable");
        if (publicClass != null) {
            model.addAttribute("publicClass", toVo(publicClass));
        }
        return new ModelAndView(structurePath("/viewer"), model.asMap());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, PublicClass publicClass) {
        if (publicClass != null) {
            publicClass.setId(id);
        }
        return this.publicClassService.abandon(publicClass);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, PublicClass publicClass, @CurrentUser UserInfo user) {
        publicClass.setId(id);

        if(publicClass.getIsCailiao()==1){

            publicClass.setCailiaofei(0.00);
        }

        this.status = 2;
        if (user != null) {
            publicClass.setSchoolId(user.getSchoolId());
        }
        publicClass = this.publicClassService.modify(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    /**
     * 查看已经报名学生信息
     *
     * @param id
     * @param model
     * @return
     * @date 2020/11/18 修改
     */
    @RequestMapping(value = "/checkEnrollStu", method = RequestMethod.GET)
    public ModelAndView checkEnrollStu(@RequestParam(value = "id", required = true) Integer id,
                                       Model model, @CurrentUser UserInfo user) {
        ClassSelectionCondition condition = new ClassSelectionCondition();
        condition.setPublicClassId(id);
        condition.setIsDelete(false);
//        List<ClassSelection> classSelectionList = this.publicClassService.findClassSelectionByCondition(condition);
//        model.addAttribute("voList", toClaSelVos(classSelectionList));
        if (user != null) {
            List<Integer> list = publicClassService.findPublicUserByIdAndSchoolId(id, user.getSchoolId());
            model.addAttribute("voList", toClaSelVos(id, list));
        }

        model.addAttribute("publicClassId", id);
        return new ModelAndView(structurePath("/check"));
    }

    @RequestMapping(value = "/addStu", method = RequestMethod.GET)
    public ModelAndView addStu(@RequestParam(value = "id", required = true) Integer id,
                               Model model, @CurrentUser UserInfo user) {
        ClassSelectionCondition condition = new ClassSelectionCondition();
        condition.setPublicClassId(id);
        condition.setIsDelete(false);
//        List<ClassSelection> classSelectionList = this.publicClassService.findClassSelectionByCondition(condition);
//        model.addAttribute("voList", toClaSelVos(classSelectionList));
        if (user != null) {
            List<Integer> list = publicClassService.findPublicUserByIdAndSchoolId(id, user.getSchoolId());
            model.addAttribute("voList", toClaSelVos(id, list));
        }

        model.addAttribute("publicClassId", id);
        return new ModelAndView(structurePath("/check"));
    }

    private List<ClassSelectionVo> toClaSelVos(Integer id, List<Integer> list) {
        List<ClassSelectionVo> voList = new ArrayList<>();
        for (Integer integer : list) {
            ClassSelection classSelection = new ClassSelection();
            classSelection.setStudentId(integer);
            ClassSelectionVo classSelectionVo = toClaSelVo(classSelection);
            classSelectionVo.setPublicClassId(id);
            voList.add(classSelectionVo);
        }
        return voList;
    }

    private ClassSelectionVo toClaSelVo(ClassSelection classSelection) {
        ClassSelectionVo vo = new ClassSelectionVo();
        BeanUtils.copyProperties(classSelection, vo);
        Student student = this.studentService.findStudentById(classSelection.getStudentId());
        if (student != null) {
            vo.setStudentName(student.getName());               //学生名称
            vo.setStudentNumber(student.getStudentNumber());   //学生 学籍号
            vo.setSex(student.getSex());                      //学生性别
            Integer teamId = student.getTeamId();
            if (teamId != null) {
                Team team = this.teamService.findTeamById(teamId);
                if (team != null) {
                    vo.setTeamName(team.getName());          //学生所在班级名称
                }
            }
        }
        return vo;
    }


    //导出课程信息
    @RequestMapping(value = "/download")
    public void download(
            HttpServletRequest request,
            HttpServletResponse response,
            @CurrentUser UserInfo user,
            @RequestParam(value = "publicClassId", required = true) Integer publicClassId) {
        ParseConfig config = SzxyExcelTookit.getConfig("downloadPubCla");
        PublicClass publicClass = this.publicClassService.findPublicClassById(publicClassId);
        ClassSelectionCondition condition = new ClassSelectionCondition();
        condition.setSchoolId(user.getSchoolId());
        condition.setIsDelete(false);
        condition.setPublicClassId(publicClassId);

//        List<ClassSelection> classSelectionList = this.publicClassService.findClassSelectionByCondition(condition);
        List<Integer> publicList = publicClassService.findPublicUserByIdAndSchoolId(publicClassId, user.getSchoolId());
        List<ClassSelectionVo> classSelectionListVoList = new ArrayList<ClassSelectionVo>();
        if (publicList != null && publicList.size() > 0) {
            for (Integer integer : publicList) {
                ClassSelection classSelection = new ClassSelection();
                classSelection.setStudentId(integer);
                ClassSelectionVo classSelectionVo = toClaSelVo(classSelection);
                if (publicClass != null) {
                    BeanUtils.copyProperties(publicClass, classSelectionVo);
                    classSelectionVo.setPublicClassName(publicClass.getName());
                    // 获取教师名称
                    List<PublicTeacherVo> publicTeacher = this.publicClassService.findByClassId(publicClassId, user.getSchoolId());
                    if (publicTeacher != null && publicTeacher.size() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < publicTeacher.size(); i++) {
                            String name = publicTeacher.get(i).getName();
                            stringBuilder.append(name);
                            if (i != publicTeacher.size() - 1) {
                                stringBuilder.append(",");
                            }
                        }
                        classSelectionVo.setTeacherName(stringBuilder.toString());
                    }
                    // 获取上课日期
                    // 获取对应的课程时间信息
                    List<PublicTimeVo> publicTimeVo = this.publicClassService.findTimeInfoByClassId(publicClassId, user.getSchoolId());
                    if (publicTimeVo != null && publicTimeVo.size() > 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < publicTimeVo.size(); i++) {
                            String name = publicTimeVo.get(i).getClassTime();
                            stringBuilder.append(name);
                            if (i != publicTimeVo.size() - 1) {
                                stringBuilder.append(",");
                            }
                        }
                        classSelectionVo.setClassTime(stringBuilder.toString());
                    }
                }
                classSelectionListVoList.add(classSelectionVo);
            }
        }

        List<Object> list = new ArrayList<Object>();
        String[] titles = {"课程名称", "上课教师", "上课时间", "选课开始日期", "课程总节数", "人数上限", "选课截止日期", "姓名", "性别", "班级", "学籍号"};
        config.setTitles(titles);
        config.setSheetTitle("课程信息表");
        String fileName = "课程信息表.xls";

        try {
            for (ClassSelectionVo vo : classSelectionListVoList) {
                list.add(vo);
            }
            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, fileName);
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
        }
    }


    //导出课程信息
    @RequestMapping(value = "/download/classCount")
    public void downloadClassCount(
            HttpServletRequest request,
            HttpServletResponse response,
            @CurrentUser UserInfo user, @ModelAttribute("condition") CountPublicClassVo condition
            , @RequestParam(value = "type", required = true) Integer type) {

        condition.setSchoolId(user.getSchoolId());
        List<Object> list = new ArrayList<>();
        String fileName = "";
        ParseConfig config = null;

        if (type == 1) {
            config = SzxyExcelTookit.getConfig("classCount");
            List<CountPublicClassVo> countPublicList = publicClassService.findCountClass(condition);
            if (countPublicList != null && countPublicList.size() > 0) {
                for (CountPublicClassVo countPublicClassVo : countPublicList) {
                    list.add(countPublicClassVo);
                }
            }
            String[] titles = {"课程名称", "人数上限", "已报名人数"};
            config.setTitles(titles);
            config.setSheetTitle("课程信息表");
            fileName = "课程统计表.xls";
        } else if (type == 2){
            config = SzxyExcelTookit.getConfig("choseClassStu");
            List<CountPublicClassVo> countPublicList = publicClassService.findChoseClassStu(condition);
            if (countPublicList != null && countPublicList.size() > 0) {
                for (CountPublicClassVo countPublicClassVo : countPublicList) {
                    list.add(countPublicClassVo);
                }
            }
            String[] titles = {"课程名称", "学生名称", "班级名称"};
            config.setTitles(titles);
            config.setSheetTitle("已选课学生信息");
            fileName = "已选课学生信息.xls";
        } else if (type == 3){
            config = SzxyExcelTookit.getConfig("noChoseClassStu");
            List<CountPublicClassVo> countPublicList = publicClassService.findNoChoseClassStu(condition);
            if (countPublicList != null && countPublicList.size() > 0) {
                for (CountPublicClassVo countPublicClassVo : countPublicList) {
                    list.add(countPublicClassVo);
                }
            }
            String[] titles = {"学生名称", "班级名称"};
            config.setTitles(titles);
            config.setSheetTitle("未选课学生信息");
            fileName = "未选课学生信息.xls";
        }
        try {
            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, fileName);
        } catch (UnsupportedEncodingException e) {
            log.error(e.toString());
        }
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    private void conditionFilter(UserInfo user, PublicClassCondition condition) {
        Integer schoolId = condition.getSchoolId();
        Boolean isDeleted = condition.getIsDelete();
        condition.setIsDelete(isDeleted != null ? isDeleted : false);
        condition.setSchoolId(schoolId != null ? schoolId : user.getSchoolId());
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


    /**
     * @Author：cmb
     * @Params：[grade]
     * @Date：14:12 2020/9/23
     * @Description:1.0将年级数字转成年级字符串返回
     */
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
    /**
     * @Author：cmb
     * @Params：[key]
     * @Date：14:12 2020/9/23
     * @Description：1.0将年级年级字符串转成数字返回
     */
    private Integer stringGradeToInt(String fullName) {
        Properties properties = new Properties();
        String property = null;
        try {
            properties.load(PublicClassController.class.getClassLoader().getResourceAsStream("config/properties/custom/grade.properties"));
            property = properties.getProperty(fullName);

//			System.err.println("this is properties"+property);
        } catch (IOException e) {
            log.debug("grade_properties获取失败：" + e.getStackTrace());
        }
        return Integer.valueOf(property);
    }


    @RequestMapping(value = "/teacher/index")
    public ModelAndView teacherIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") PublicTeacherVo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model) {
        String viewPath = null;
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        condition.setSchoolId(user.getSchoolId());
        List<PublicTeacherVo> items = this.publicClassService.findPublicClassTeacherInfoByCondition(condition, page, order);
//        List<PublicTeacherVo> items = this.publicClassService.findPublicClassTeacherInfo(condition);
        if (items != null && items.size() > 0) {
            for (int i = 0; i < items.size(); i++) {
                // 根据uuid查询封面的url
                FileResult file = fileService.findFileByUUID(items.get(i).getCoverUuid());
                if (file != null) {
                    items.get(i).setCoverUrl(file.getHttpUrl());
                }
            }
        }

        if ("list".equals(sub)) {
            viewPath = structurePath("/module/teacherList");
        } else {
            viewPath = structurePath("/module/index");
        }
        if ("all".equals(type)) {
            viewPath = structurePath("/module/teacherIndex");
        }
        model.addAttribute("items", items);
        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping(value = "/teacher/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creatorTeacher(PublicTeacherVo publicTeacher, @CurrentUser UserInfo user) {
        if (user != null && publicTeacher != null) {
            publicTeacher.setSchoolId(user.getSchoolId());
        }
        publicClassService.addTeacher(publicTeacher);
        ResponseInfomation ss = publicTeacher != null ? new ResponseInfomation(publicTeacher.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
        return ss;
    }

    @RequestMapping(value = "/teacher/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation update(PublicTeacherVo publicClass) {
        this.publicClassService.modifyTeacher(publicClass);
        return publicClass != null ? new ResponseInfomation(publicClass.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    @RequestMapping(value = "/teacher/editor", method = RequestMethod.GET)
    public ModelAndView editorTeacher(
            @RequestParam(value = "id") Integer id, Model model) {
        PublicTeacherVo publicTeacher = this.publicClassService.findByTeacherId(id);
        if (publicTeacher != null) {
            // 根据uuid查询封面的url
            FileResult file = fileService.findFileByUUID(publicTeacher.getCoverUuid());
            if (file != null) {
                publicTeacher.setCoverUrl(file.getHttpUrl());
            }
            model.addAttribute("publicClass", publicTeacher);
        }
        return new ModelAndView(structurePath("/module/input"), model.asMap());
    }

    @RequestMapping(value = "/teacher/creator", method = RequestMethod.GET)
    public ModelAndView creatorTeacher(Model model) {

        return new ModelAndView(structurePath("/module/input"));
    }


    @RequestMapping(value = "/teacher/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("ids") String ids) {
        return this.publicClassService.abandonTeacher(ids);
    }


    /**
     * 上课时间
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
    @RequestMapping(value = "/class/index")
    public ModelAndView classIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") PublicTimeVo condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order, Model model) {
        String viewPath = null;
        order.setProperty(order.getProperty() != null ? order.getProperty() : "create_date");
        List<PublicTimeVo> items = this.publicClassService.findPublicClassTimeInfoByCondition(condition, page, order);

        if ("list".equals(sub)) {
            viewPath = structurePath("/time/list");
        } else {
            viewPath = structurePath("/time/index");
        }
        model.addAttribute("items", items);
        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping(value = "/class/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creatorTime(PublicTimeVo publicTimeVo, @CurrentUser UserInfo user) {
        if (user != null && publicTimeVo != null) {
            publicTimeVo.setSchoolId(user.getSchoolId());
        }
        publicClassService.addTime(publicTimeVo);
        ResponseInfomation ss = publicTimeVo != null ? new ResponseInfomation(publicTimeVo.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
        return ss;
    }

    @RequestMapping(value = "/class/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation updateTime(PublicTimeVo publicTimeVo) {
        this.publicClassService.modifyTime(publicTimeVo);
        return publicTimeVo != null ? new ResponseInfomation(publicTimeVo.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }


    @RequestMapping(value = "/class/editor", method = RequestMethod.GET)
    public ModelAndView editorTime(
            @RequestParam(value = "id") Integer id, Model model) {
        PublicTimeVo publicTimeVo = this.publicClassService.findByTimeId(id);
        if (publicTimeVo != null) {
            model.addAttribute("publicTimeVo", publicTimeVo);
        }
        return new ModelAndView(structurePath("/time/input"), model.asMap());
    }

    @RequestMapping(value = "/class/creator", method = RequestMethod.GET)
    public ModelAndView creatorTime(Model model) {

        return new ModelAndView(structurePath("/time/input"));
    }


    @RequestMapping(value = "/class/delete", method = RequestMethod.POST)
    @ResponseBody
    public String deleteTime(@RequestParam("ids") String ids) {
        return this.publicClassService.abandonTime(ids);
    }


    /**
     * 添加学生选课
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/addPublicClassStu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation createPublicClassStu(PublicClass publicClass, @CurrentUser UserInfo user) {
        if (user != null && publicClass != null) {
            publicClass.setSchoolId(user.getSchoolId());
            publicClassService.createPublicClassStu(publicClass);
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
    }


    /**
     * 删除学生选课
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/delPublicClassStu", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation delPublicClassStu(PublicClass publicClass, @CurrentUser UserInfo user) {
        if (user != null && publicClass != null) {
            publicClass.setSchoolId(user.getSchoolId());
            publicClassService.removePublicClassStu(publicClass);
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
    }

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ModelAndView export(Model model) {
        this.status = 1;
        return new ModelAndView(structurePath("/export"));
    }



    @RequestMapping(value = "/indexKeXuan")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @ModelAttribute("page") Page page,
            @RequestParam String sub,
             Model model) {
        List<GradeKeXuanKe> list=keXuanKeService.findByAll(user.getSchoolId(),user.getSchoolYear(),page);
      String viewPath="";
        if ("list".equals(sub)) {
            viewPath = "/teach/publicClass/kexuanke/list";
        } else {
            viewPath = "/teach/publicClass/kexuanke/index";
        }
        model.addAttribute("items",list);

        return new ModelAndView(viewPath, model.asMap());
    }

    @RequestMapping(value = "/updateKeXuanKe")
    @ResponseBody
    public ResponseInfomation delPublicClassStu(@CurrentUser UserInfo user,
                                                @RequestParam Integer gradeId,
                                                @RequestParam Integer zhuangtai) {
        if (gradeId != null && zhuangtai != null) {
            List<KeXuanKe> list=keXuanKeService.findByGradeIdKeXuanKes(gradeId);

            if(list.size()<=0){
                KeXuanKe keXuanKe=new KeXuanKe();
                keXuanKe.setGradeId(gradeId);
                keXuanKe.setZhuantai(zhuangtai);
                keXuanKe.setSchoolYear(user.getSchoolYear());
                keXuanKe.setSchoolTrem(user.getSchoolTermCode());
                keXuanKeService.createKeXuanKe(keXuanKe);
            }else{
                keXuanKeService.updateKeXuanKe(gradeId,zhuangtai);
            }
            return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
        }
        return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
    }

}
