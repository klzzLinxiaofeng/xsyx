package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.szxyzxx.literacy.pojo.LiteracyStudent;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.literacy.pojo.SubjectScorePojo;
import platform.szxyzxx.literacy.service.LiteracyService;
import platform.szxyzxx.literacy.service.LiteracyStudentService;
import platform.szxyzxx.util.Column;
import platform.szxyzxx.util.ExcelTool;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/teach/studentScore")
public class StudentScoreController extends BaseController {
    Logger log = LoggerFactory.getLogger(StudentScoreController.class);
    @Autowired
    private LiteracyStudentService literacyStudentService;
    @Autowired
    private LiteracyService literacyService;

    private final static String viewBasePath = "/teach/studentScore";

    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") StudentScoreCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order) {
        String viewPath = null;
        ModelAndView mav = new ModelAndView();
        page.setPageSize(Integer.MAX_VALUE);
		/*try {
			if(condition!=null&&condition.getExamName()!=null&&!"".equals(condition.getExamName()) ){
				String examName=new String((condition.getExamName()).getBytes("iso-8859-1"),"utf-8");
				condition.setExamName(examName);
			}
			
			
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
        List<StudentScoreVo> studentScoreList = getScoreList(user, condition, page, order);
        viewPath = structurePath("/index");
        mav.addObject("StudentScoreCondition", condition);
        mav.addObject("studentScoreList", studentScoreList);
        mav.setViewName(viewPath);

        return mav;
    }

    /**
     * 获取学生成绩
     *
     * @param user
     * @param condition
     * @param page
     * @param order
     * @return
     */
    private List<StudentScoreVo> getScoreList(UserInfo user,
                                              StudentScoreCondition condition, Page page, Order order) {
        List<StudentScoreVo> studentScoreList = new ArrayList<StudentScoreVo>();
        ExamTeamSubjectVo examTeamSubjectVo = null;
        ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
        examTeamSubjectCondition.setSchoolId(user.getSchoolId());
        examTeamSubjectCondition.setExamName(condition.getExamName());
        examTeamSubjectCondition.setExamType(condition.getExamType());
        examTeamSubjectCondition.setGradeId(condition.getGradeId());
        examTeamSubjectCondition.setTeamId(condition.getTeamId());
        examTeamSubjectCondition.setSchoolYear(condition.getSchoolYear());
        if (condition.getSchoolYear() == null || "".equals(condition.getSchoolYear())) {

        } else {
            SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
            schoolYearCondition.setSchoolId(user.getSchoolId());
            schoolYearCondition.setYear(condition.getSchoolYear());
            SchoolYear schoolyear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
            if (schoolyear == null) {
                schoolyear = new SchoolYear();
            }
            condition.setSchoolYearName(schoolyear.getName());

        }

        examTeamSubjectCondition.setSubjectCode(condition.getSubjectCode());
        examTeamSubjectCondition.setTeamId(condition.getTeamId());
        examTeamSubjectCondition.setTerm(condition.getTermCode());
        //获取考务信息
        List<ExamTeamSubjectVo> examList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService, examTeamSubjectCondition);
        if (examList == null) {
            //不存在对应的考务信息
        } else if (examList.size() == 1) {//考务信息
            examTeamSubjectVo = examList.get(0);
            condition.setExamTeamSubjectId(examTeamSubjectVo.getId());
            condition.setRateType(examTeamSubjectVo.getRateType());//评分类型
            condition.setSchoolId(user.getSchoolId());
            if (condition.getStudentId() != null && condition.getStudentId() > 0) {
                page.setPageSize(Integer.MAX_VALUE);
                studentScoreList = getStudentScoreFromStudentScore(condition, page, order, examTeamSubjectVo);
                if (studentScoreList.size() > 0) {

                } else {
                    page.setPageSize(Integer.MAX_VALUE);
                    studentScoreList = getStudentScoreFromStudent(user, condition, page, examTeamSubjectVo);
                }
            } else {
                if (examTeamSubjectVo.getTaskRate()) {//判断这次科目考试是否已经录入成绩====已经录入
                    List<StudentScoreVo> studentScoreListTemp1 = getStudentScoreFromStudentScore(condition, page, order, examTeamSubjectVo);
                    List<StudentScoreVo> studentScoreListTemp2 = getStudentScoreFromStudent(user, condition, page, examTeamSubjectVo);
                    if (studentScoreListTemp1 != null && studentScoreListTemp2 != null && studentScoreListTemp2.size() != studentScoreListTemp1.size()) {
                        Map<Integer, StudentScoreVo> map = new HashMap<Integer, StudentScoreVo>();
                        for (StudentScoreVo studentScoreVo : studentScoreListTemp2) {
                            studentScoreVo.setScore("");
                            map.put(studentScoreVo.getStudentId(), studentScoreVo);
                        }

                        for (StudentScoreVo studentScoreVo : studentScoreListTemp1) {
                            map.put(studentScoreVo.getStudentId(), studentScoreVo);
                        }

                        Set<Integer> set = map.keySet();

                        for (Integer studentId : set) {
                            studentScoreList.add(map.get(studentId));
                        }
                    } else {
                        for (StudentScoreVo studentScoreVo : studentScoreListTemp1) {
                            studentScoreList.add(studentScoreVo);
                        }
                    }


                } else {//判断这次科目考试是否已经录入成绩====未被录入
                    page.setPageSize(Integer.MAX_VALUE);
                    studentScoreList = getStudentScoreFromStudent(user, condition, page, examTeamSubjectVo);
                }
            }


        } else {
            //查询出来多个考务信息 ，非正常状态
        }
        SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
        schoolTermCondition.setSchoolId(user.getSchoolId());
        schoolTermCondition.setSchoolYear(condition.getSchoolYear());
        schoolTermCondition.setCode(condition.getTermCode());

        List<SchoolTermVo> schoolTermVoList = this.schoolTermService.findSchoolTermByConditionMore(schoolTermCondition, page, null);
        if (schoolTermVoList.size() == 1) {
            condition.setTermName(schoolTermVoList.get(0).getName());
        }
        if (examTeamSubjectVo != null) {
            for (StudentScore studentScore : studentScoreList) {
                studentScore.setExamTeamSubjectId(examTeamSubjectVo.getId());
            }
        }
        return studentScoreList;
    }


    @RequestMapping(value = "/list/json", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentScore> jsonList(
            @CurrentUser UserInfo user,
            @ModelAttribute("condition") StudentScoreCondition condition,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            @RequestParam(value = "usePage", required = false) boolean usePage) {

        page = usePage ? page : null;
        return this.studentScoreService.findStudentScoreByCondition(condition, page, order);
    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView creator() {
        return new ModelAndView(structurePath("/testadd"));
    }

    @RequestMapping(value = "/creator", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation creator(@CurrentUser UserInfo user, StudentScoreBean studentScoreBean) {

        List<StudentScoreVo> studentScoreList = studentScoreBean.getStudentScoreList();
        Integer examTeamSubjectId = studentScoreBean.getExamTeamSubjectId();

        Integer ratetype = studentScoreBean.getRateType();
        ExamTeamSubjectVo examTeamSubjectVo = this.examTeamSubjectService.findExamTeamSubjectVoById(teamService, studentService, examTeamSubjectId);

        int addSum = 0;

        addSum = addStudentScore(user, studentScoreBean, studentScoreList,
                examTeamSubjectId, ratetype, examTeamSubjectVo, addSum);
		
	/*	int addSum = 0;
		for (StudentScoreVo studentScoreVo : studentScoreList) {
			StudentScore studentScore = new StudentScore();

			studentScore.setExamTeamSubjectId(studentScoreVo.getExamTeamSubjectId());//考务日程
			studentScore.setScore(studentScoreVo.getScore());//分数
			studentScore.setStudentId(studentScoreVo.getStudentId());//学生
			studentScore.setSubjectCode(studentScoreVo.getSubjectCode());//科目
			StudentScore studentScoreEnd = this.studentScoreService.add(studentScore);
			if(studentScoreEnd != null && studentScoreEnd.getId()>0){
				addSum++;
			}
		}*/

        return addSum == studentScoreList.size() ? new ResponseInfomation(ResponseInfomation.OPERATION_SUC) : new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
    }

    private int addStudentScore(UserInfo user,
                                StudentScoreBean studentScoreBean,
                                List<StudentScoreVo> studentScoreList, Integer examTeamSubjectId,
                                Integer ratetype, ExamTeamSubjectVo examTeamSubjectVo, int addSum) {
        if (examTeamSubjectVo.getTaskRate()) {//已经登记过成绩
            for (StudentScoreVo studentScoreVo : studentScoreList) {
                StudentScore studentScore = new StudentScore();
                studentScore.setExamTeamSubjectId(examTeamSubjectId);
                studentScore.setId(studentScoreVo.getId());
                if (studentScoreVo.getScore() == null || "".equals(studentScoreVo.getScore())) {
                    studentScoreVo.setScore("");
                }
                studentScore.setScore(studentScoreVo.getScore());
                studentScore.setStudentId(studentScoreVo.getStudentId());
                studentScore.setComment(studentScoreVo.getComment());
                StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
                studentScoreCondition.setStudentId(studentScore.getStudentId());
                studentScoreCondition.setExamTeamSubjectId(studentScore.getExamTeamSubjectId());
                Page page = new Page();
                page.setPageSize(Integer.MAX_VALUE);
                List<StudentScore> list = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, null);
                if (list != null && list.size() == 1) {
                    this.studentScoreService.modify(studentScore);
                } else if (list != null && list.size() > 1) {

                } else {

                    studentScore.setSchoolId(user.getSchoolId());
                    studentScore.setExamTeamSubjectId(examTeamSubjectId);
                    if (studentScoreVo.getScore() == null || "".equals(studentScoreVo.getScore())) {
                        studentScoreVo.setScore("");
                    }
                    studentScore.setScore(studentScoreVo.getScore());
                    studentScore.setStudentId(studentScoreVo.getStudentId());
                    studentScore.setSubjectCode(studentScoreBean.getSubjectCode());
                    //////统计信息字段 start
                    studentScore.setSchoolYear(studentScoreBean.getSchoolYear());
                    studentScore.setTermCode(studentScoreBean.getTermCode());
                    studentScore.setGradeId(studentScoreBean.getGradeId());
                    studentScore.setTeamId(studentScoreBean.getTeamId());
                    studentScore.setExamType(studentScoreBean.getExamType());
                    studentScore.setExamName(studentScoreBean.getExamName());
                    studentScore.setComment(studentScoreVo.getComment());
                    //////统计信息字段 end
                    if(StringUtils.isEmpty(studentScore.getScore())){
                        studentScore.setScore("0");
                    }
                    this.studentScoreService.add(studentScore);
                }

                addSum++;
            }
        } else {//还没有登陆过成绩
            for (StudentScoreVo studentScoreVo : studentScoreList) {//登记学生信息
                StudentScore studentScore = new StudentScore();
                studentScore.setSchoolId(user.getSchoolId());
                studentScore.setExamTeamSubjectId(examTeamSubjectId);
                if (studentScoreVo.getScore() == null || "".equals(studentScoreVo.getScore())) {
                    studentScoreVo.setScore("");
                }
                studentScore.setScore(studentScoreVo.getScore());
                studentScore.setStudentId(studentScoreVo.getStudentId());
                studentScore.setSubjectCode(studentScoreBean.getSubjectCode());
                //////统计信息字段 start
                studentScore.setSchoolYear(studentScoreBean.getSchoolYear());
                studentScore.setTermCode(studentScoreBean.getTermCode());
                studentScore.setGradeId(studentScoreBean.getGradeId());
                studentScore.setTeamId(studentScoreBean.getTeamId());
                studentScore.setExamType(studentScoreBean.getExamType());
                studentScore.setExamName(studentScoreBean.getExamName());
                studentScore.setComment(studentScoreVo.getComment());
                //////统计信息字段 end
                this.studentScoreService.add(studentScore);
                addSum++;
            }
            modifyExamTeamSubjectTaskRate(examTeamSubjectId);//修改考务信息中中是否已经登记成绩为是
        }


//评分类型是否发送改变
        if (ratetype == examTeamSubjectVo.getRateType()) {//没有发生改变


        } else {//发生改变
            ExamTeamSubject examTeamSubject = new ExamTeamSubject();
            examTeamSubject.setId(examTeamSubjectId);
            examTeamSubject.setRateType(ratetype);
            this.examTeamSubjectService.modify(examTeamSubject);
        }

        this.statisticalScore();//统计成绩
        return addSum;
    }


    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView editor(
            @RequestParam(value = "id", required = true) Integer id, Model model) {
        StudentScore studentScore = this.studentScoreService.findStudentScoreById(id);
        model.addAttribute("studentScore", studentScore);
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping(value = "/viewer", method = RequestMethod.GET)
    public ModelAndView viewer(
            @RequestParam(value = "id", required = true) Integer id,
            Model model) {
        StudentScore studentScore = this.studentScoreService.findStudentScoreById(id);
        model.addAttribute("isCK", "disable");
        model.addAttribute("studentScore", studentScore);
        return new ModelAndView(structurePath("/input"), model.asMap());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, StudentScore studentScore) {
        if (studentScore != null) {
            studentScore.setId(id);
        }
        try {
            this.studentScoreService.remove(studentScore);
        } catch (Exception e) {
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, StudentScore studentScore) {
        studentScore.setId(id);
        studentScore = this.studentScoreService.modify(studentScore);
        return studentScore != null ? new ResponseInfomation(studentScore.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation();
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

    /**
     * 当考试还未被录入成绩时查询对应的学生信息
     *
     * @param user
     * @param condition
     * @param page
     * @param examTeamSubjectVo
     */
    private List<StudentScoreVo> getStudentScoreFromStudent(UserInfo user,
                                                            StudentScoreCondition condition, Page page, ExamTeamSubjectVo examTeamSubjectVo) {
        page.setPageSize(Integer.MAX_VALUE);
        List<StudentScoreVo> studentScoreList = new ArrayList<StudentScoreVo>();
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setSchoolId(user.getSchoolId());
        studentCondition.setSchoolYear(condition.getSchoolYear());
        studentCondition.setGradeId(condition.getGradeId());
        studentCondition.setTeamId(condition.getTeamId());
        studentCondition.setId(condition.getStudentId());
        if (condition.getStudentId() != null && condition.getStudentId() > 0) {
            studentCondition.setId(condition.getStudentId());
        }
        //List<Student> studentList = this.studentService.findStudentByCondition(studentCondition, page, null);

        List<Student> studentList = this.studentService.findStudentByTeam(studentCondition, page, null);
        for (Student student : studentList) {

            StudentScoreVo studentScoreVo = new StudentScoreVo();
			/*studentScoreVo.setExamTeamSubjectId(examTeamSubjectVo.getId());
			studentScoreVo.setSubjectCode(examTeamSubjectVo.getSubjectCode());*/
            studentScoreVo.setStudentId(student.getId());
            studentScoreVo.setStudentNumber(student.getStudentNumber());
            studentScoreVo.setStudentName(student.getName());
            studentScoreList.add(studentScoreVo);
        }

        return studentScoreList;
    }

    /**
     * 当已经添加过学生成绩时，从添加的学生成绩取值
     *
     * @param condition
     * @param page
     * @param order
     * @param examTeamSubjectVo
     */
    private List<StudentScoreVo> getStudentScoreFromStudentScore(
            StudentScoreCondition condition, Page page, Order order,

            ExamTeamSubjectVo examTeamSubjectVo) {
        page.setPageSize(Integer.MAX_VALUE);
        List<StudentScoreVo> studentScoreList = new ArrayList<StudentScoreVo>();
        StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
        studentScoreCondition.setExamTeamSubjectId(examTeamSubjectVo.getId());
        studentScoreCondition.setSubjectCode(condition.getSubjectCode());
        if (condition.getStudentId() != null && condition.getStudentId() > 0) {
            studentScoreCondition.setStudentId(condition.getStudentId());
        }
        List<StudentScore> stuScoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition, page, order);
        for (StudentScore studentScore : stuScoreList) {
            StudentScoreVo studentScoreVo = new StudentScoreVo();
            studentScoreVo.setExamTeamSubjectId(examTeamSubjectVo.getId());
            Student student = this.studentService.findStudentById(studentScore.getStudentId());
            studentScoreVo.setSubjectCode(examTeamSubjectVo.getSubjectCode());
            studentScoreVo.setStudentId(student.getId());
            studentScoreVo.setStudentNumber(student.getStudentNumber());
            studentScoreVo.setStudentName(student.getName());
            studentScoreVo.setId(studentScore.getId());
            studentScoreVo.setScore(studentScore.getScore());
            studentScoreVo.setComment(studentScore.getComment());
            studentScoreList.add(studentScoreVo);
        }
        return studentScoreList;
    }

    /**
     * 修改考务信息中中是否已经登记成绩为是
     *
     * @param examTeamSubjectId 考务信息id
     */
    private void modifyExamTeamSubjectTaskRate(Integer examTeamSubjectId) {
        ExamTeamSubject examTeamSubject = new ExamTeamSubject();
        examTeamSubject.setId(examTeamSubjectId);
        examTeamSubject.setTaskRate(true);
        this.examTeamSubjectService.modify(examTeamSubject);
    }

    /**
     * 统计学生成绩
     */
    private void statisticalScore() {

    }


    @RequestMapping(value = "/termCode")
    @ResponseBody
    public Map<String, String> termCode(
            @CurrentUser UserInfo user,
            @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "examType", required = false) String examType,
            @RequestParam(value = "examName", required = false) String examName,
            @RequestParam(value = "type", required = false) String type
    ) {
        ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
        examTeamSubjectCondition.setSchoolYear(schoolYear);
        examTeamSubjectCondition.setTerm(termCode);
        examTeamSubjectCondition.setGradeId(gradeId);
        examTeamSubjectCondition.setTeamId(teamId);
        examTeamSubjectCondition.setExamType(examType);
        examTeamSubjectCondition.setExamName(examName);
        examTeamSubjectCondition.setSchoolId(user.getSchoolId());
        List<ExamTeamSubjectVo> voList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService, examTeamSubjectCondition);
        Map<String, String> map = new LinkedHashMap<String, String>();
        if (type == null || "".equals(type)) {
            type = "请选择";
        }
        map.put(type, "");
        for (ExamTeamSubjectVo examTeamSubjectVo : voList) {
            String resultExamName = examTeamSubjectVo.getExamName();
            map.put(resultExamName, resultExamName);
        }

        return map;
    }

    @RequestMapping(value = "/subjectCode")
    @ResponseBody
    public Map<String, String> subjectCode(
            @CurrentUser UserInfo user,
            @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "examType", required = false) String examType,
            @RequestParam(value = "examName", required = false) String examName,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "type", required = false) String type) {
        Map<String, String> map = new HashMap<String, String>();
        ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
        examTeamSubjectCondition.setSchoolYear(schoolYear);
        examTeamSubjectCondition.setTerm(termCode);
        examTeamSubjectCondition.setGradeId(gradeId);
        examTeamSubjectCondition.setTeamId(teamId);
        examTeamSubjectCondition.setExamType(examType);
        examTeamSubjectCondition.setExamName(examName);
        examTeamSubjectCondition.setSubjectCode(subjectCode);
        examTeamSubjectCondition.setSchoolId(user.getSchoolId());
        List<ExamTeamSubjectVo> voList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService, examTeamSubjectCondition);
        for (ExamTeamSubjectVo examTeamSubjectVo : voList) {
            String resultSubjectCode = examTeamSubjectVo.getSubjectCode();
            map.put(resultSubjectCode, resultSubjectCode);
        }


        Set<String> set = map.keySet();
        map = new LinkedHashMap<String, String>();
        if (type == null || "".equals(type)) {
            type = "请选择";
        }
        map.put(type, "");

        for (String key : set) {
            if (key == null || type.equals(key)) {

            } else {
                Subject subject = this.subjectService.findUnique(user.getSchoolId(), key);
                map.put(subject.getName(), key);
            }

        }

        return map;
    }

    /**
     * 导出成绩模板页面
     *
     * @return
     */
    @RequestMapping(value = "/downLoadScorePage", method = RequestMethod.GET)
    public ModelAndView downLoadScorePage() {

        return new ModelAndView(structurePath("/downLoadScorePage"));
    }

    /**
     * 导入成绩页面
     */
    @RequestMapping("/upLoadScoreInfoPage")
    public ModelAndView upLoadScoreInfoPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(this.structurePath("/upLoadScoreInfoPage"));
        return mav;
    }

    /**
     * 导出学科素养成绩模板页面
     *
     * @return
     */
    @RequestMapping(value = "/downLoadSubjectView", method = RequestMethod.GET)
    public ModelAndView downLoadSubjectPage() {

        return new ModelAndView(structurePath("/subjectscore"));
    }


    /**
     * 导入成绩页面
     */
    @RequestMapping("/upLoadSubjectPage")
    public ModelAndView upLoadSubjectPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName(this.structurePath("/upLoadSubjectScore"));
        return mav;
    }

    /**
     * 导出成绩模板
     *
     * @param user
     * @return
     */
    @RequestMapping(value = "/downLoadScoreInfo")
    @ResponseBody
    public ResponseInfomation downLoadScore(@CurrentUser UserInfo user,
                                            @ModelAttribute("condition") StudentScoreCondition condition,
                                            @ModelAttribute("page") Page page,
                                            @ModelAttribute("order") Order order,
                                            HttpServletResponse response, HttpServletRequest request) {

        page.setPageSize(Integer.MAX_VALUE);
		/*try {
			if(condition!=null&&condition.getExamName()!=null&&!"".equals(condition.getExamName()) ){
				String examName=new String((condition.getExamName()).getBytes("iso-8859-1"),"utf-8");
				condition.setExamName(examName);
			}


		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
        ResponseInfomation tesponseInfomation = null;
        List<Object> list = new ArrayList<Object>();
        //查找数据
        List<StudentScoreVo> studentScoreList = getScoreList(user, condition, page, order);
        ParseConfig config = SzxyExcelTookit.getConfig("studentscorevo");

        StringBuffer excelName = new StringBuffer();
        String prefix = "+";
        String schoolYearName = condition.getSchoolYearName();
        excelName.append("学生成绩模板");
        excelName.append(prefix + schoolYearName);
        String termName = condition.getTermName();
        excelName.append(prefix + termName);
        Grade grade = this.gradeService.findGradeById(condition.getGradeId());
        if (grade != null) {
            String gradeName = grade.getName();
            excelName.append(prefix + gradeName);
        }
        Team team = this.teamService.findTeamById(condition.getTeamId());
        if (team != null) {
            String teamName = team.getName();
            excelName.append(prefix + teamName);
        }

        String examName = condition.getExamName();
        excelName.append(prefix + examName);
        Subject subject = this.subjectService.findUnique(user.getSchoolId(), condition.getSubjectCode());
        if (subject != null) {
            String subjectName = subject.getName();
            excelName.append(prefix + subjectName);
        }
        excelName.append(".xls");

        String filename = excelName.toString();
        try {
            for (StudentScoreVo studentScoreVo : studentScoreList) {
                list.add(studentScoreVo);
            }

            SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
        } catch (UnsupportedEncodingException e) {
            //log.error(e.getMessage());
            //tesponseInfomation = new ResponseInfomation("用户下载家长信息模板失败："+this.errorMessage(e.getMessage()));
            e.printStackTrace();
        }
        tesponseInfomation = new ResponseInfomation(ResponseInfomation.OPERATION_SUC);


        return tesponseInfomation;

    }
        /**
         * 下载学生学科成绩模板
         *
         * @throws IOException
         */
        @RequestMapping(value = "/downLoadSubjectScore")
        @ResponseBody
        public String downLoadSubject(@CurrentUser UserInfo user,
                                                @RequestParam("xn") String xn,
                                                @RequestParam("xq") String xq,
                                                @RequestParam("gradeId") Integer gradeId,
                                                @RequestParam("teamId") Integer teamId,
                                            @RequestParam("subjectId") Integer subjectId,
                                            @ModelAttribute("page") Page page,
                                                @ModelAttribute("order") Order order,
                                                HttpServletResponse response, HttpServletRequest request) {
            page.setPageSize(Integer.MAX_VALUE);
            SubjectScorePojo subjectScorePojo=new SubjectScorePojo();
            subjectScorePojo.setXn(xn);
            subjectScorePojo.setXq(xq);
            subjectScorePojo.setGradeId(gradeId);
            subjectScorePojo.setSubjectId(subjectId);
            subjectScorePojo.setTeamId(teamId);
            Subject subject=subjectService.findSubjectById(subjectId);
            Grade grade=gradeService.findGradeById(gradeId);
             //表格头部组装
            List<Map<String,String>> titleList=new ArrayList<>();
            Map<String,String> mapTop=new HashMap<String,String>();
            mapTop.put("评分程序标识(不可修改)","id");
            Map<String,String>  map1=new HashMap<String,String>();
            map1.put("学生程序标识(不可修改)","studentId");
            Map<String,String>  map2=new HashMap<String,String>();
            map2.put("班级名称(不可修改)","teamName");
            Map<String,String>  map3=new HashMap<String,String>();
            map3.put("姓名","stuName");//d_name
            titleList.add(mapTop);
            titleList.add(map1);
            //titleList.add(map2);
            titleList.add(map3);
            //查出表头
              List<LiteracyVo> literacyVoList=literacyService.findByAll(subjectScorePojo.getXq(),Integer.parseInt(subjectScorePojo.getXn()),subjectScorePojo.getGradeId(),subjectScorePojo.getSubjectId(),page);
            for (int i=0;i<literacyVoList.size();i++) {
                Map<String,String>  map4=new HashMap<String,String>();
                String showName = literacyVoList.get(i).getLiteracyName();
                map4.put(showName,"data"+i);
                titleList.add(map4);
            }
            //数据组装
            //查询数据
            List<Map<String,String>> fromDataList =getSubjects(user,subjectScorePojo,page,order);
            log.info(subject.getName()+grade.getName());
            String fileName1=grade.getName()+"("+subject.getName()+")"+"学科素养.xls";
            ExcelTool excelTool = new ExcelTool(fileName1,15,20);
             List<Column>  titleData=excelTool.columnTransformer(titleList);
            try {
                InputStream inputStream = excelTool.exportExcel(titleData, fromDataList, true, false);
                response.setCharacterEncoding("UTF-8");
                //response.setContentType("octets/stream");
                String userAgent = request.getHeader("User-Agent");
                byte[] bytes = userAgent.contains("MSIE") ? fileName1.getBytes() : fileName1.getBytes("UTF-8"); // name.getBytes("UTF-8")处理safari的乱码问题
               String name = new String(bytes, "ISO-8859-1"); // 各浏览器基本都支持ISO编码
                response.setContentType("octets/stream");
                response.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", name));
                ServletOutputStream outputStream = response.getOutputStream();
                //写文件
                int b;
                while((b=inputStream.read())!= -1)
                {
                    outputStream.write(b);
                }
                inputStream.close();
                outputStream.close();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return "success";
        }
       //组装模板导出所需的数据
       private List<Map<String,String>> getSubjects(UserInfo user,SubjectScorePojo subjectScorePojo,Page page,Order order){
            List<LiteracyVo> literacyVoList=literacyService.findByAll(subjectScorePojo.getXq(),Integer.parseInt(subjectScorePojo.getXn()),subjectScorePojo.getGradeId(),subjectScorePojo.getSubjectId(),page);
            List<Map<String,String>> literacyStudents=new ArrayList<>();
            if(literacyVoList!=null){
                int a=0;
                for(LiteracyVo aa:literacyVoList){
                    List<LiteracyStudent> literacyStudent=literacyStudentService.findByliteracy(aa.getId(),subjectScorePojo.getTeamId());
                    if(literacyStudents.size()>0){
                        for(Map<String,String> cc:literacyStudents) {
                            String strg=cc.get("studentId");
                            for(LiteracyStudent sa:literacyStudent){
                                if(strg.equals(sa.getStudentId().toString())){
                                    String str = aa.getId().toString();
                                    if (cc.get(str) == null || cc.get(str).equals("")) {
                                        cc.put("data"+a, sa.getFenshu().toString());
                                    }
                                }
                            }
                        }
                    }else{
                        for(LiteracyStudent sa:literacyStudent){
                            Map<String,String> map=new HashMap<>();
                            map.put("id",sa.getId().toString());
                            map.put("teamName",sa.getTeamName());
                            map.put("studentId",sa.getStudentId().toString());
                            map.put("stuName",sa.getStuName());
                            map.put("data"+a,sa.getFenshu().toString());
                            literacyStudents.add(map);
                        }
                    }
                    a++;
                }
            }
            return literacyStudents;
       }

       /*
       * 导入学科素养成绩
       *
       */
       @RequestMapping("/upLoadSubjectScore")
       @ResponseBody
       public Map<String, Object> upLoadSubjectScore( @RequestParam("fileUpload") MultipartFile fileUpload,
                                                      @ModelAttribute("subjectScorePojo") SubjectScorePojo subjectScorePojo,
                                                          @CurrentUser UserInfo user){
           Grade grade=gradeService.findGradeById(subjectScorePojo.getGradeId());
           try {
               String fileName = fileUpload.getOriginalFilename();//获取文件名
               String gardeIds=fileName.substring(0,3);
               InputStream is = null;
               if(!gardeIds.equals(grade.getName())){
                   return  null;
               }
               is = fileUpload.getInputStream();
               Workbook readexeclC= ExcelTool.getWorkbookType(is,fileName);
               Map<String,String> mapTop=new HashMap<>();
               mapTop.put("评分程序标识(不可修改)","id");
               mapTop.put("学生程序标识(不可修改)","studentId");
               mapTop.put("班级名称(不可修改)","teamName");
               mapTop.put("姓名","stuName");
               List<LiteracyVo> literacyVoList=literacyService.findByAll(user.getSchoolTermCode(),Integer.parseInt(user.getSchoolYear()),subjectScorePojo.getGradeId(),subjectScorePojo.getSubjectId(),null);
               for(int a=0;a<literacyVoList.size();a++){
                   mapTop.put(literacyVoList.get(a).getLiteracyName(),"data"+a);
               }
               List<Map<String,Object>> list=getJieXi(readexeclC,mapTop);
               log.info("返回的数据"+list.toString());
               //导入失败数据
               List<Map<String,Object>> errList=new ArrayList<>();
               //导入成功数据
               List<Map<String,Object>> successList=new ArrayList<>();
               //返回前端数据
               Map<String,Object> map=new HashMap<>();
               for(Map<String,Object> aa:list){
                   Integer studentId=Integer.parseInt(aa.get("studentId").toString());
                   String name=null;
                   for(int a=0;a<literacyVoList.size();a++){
                       Integer id=literacyVoList.get(a).getId();
                       //指标限制的最大分数
                       Integer maxScore=literacyVoList.get(a).getScore();
                       //导入的分数
                       Integer score=Integer.parseInt(aa.get("data"+a).toString());
                       if(score<=maxScore){
                          Boolean falg= literacyService.updatedaoruXuigai(studentId,id,score);
                            if(falg){
                                name=literacyVoList.get(a).getLiteracyName()+",";
                                aa.put("success","导入"+name+"指标信息成功");
                                successList.add(aa);
                            }else{
                                aa.put("error","未查询到此人的"+literacyVoList.get(a).getLiteracyName()+"指标信息");
                                errList.add(aa);
                            }
                       }else{
                           String liName=literacyVoList.get(a).getLiteracyName();
                           aa.put("error",liName+"分值不能大于"+maxScore);
                           errList.add(aa);
                           break;
                       }

                   }
               }
               map.put("status","success");
               map.put("error",errList);
               map.put("success",successList);
               map.put("biaotou",literacyVoList);
               log.info("errList"+errList.size()+"++++success"+successList+"----"+literacyVoList.size());
               return  map;
           } catch (Exception e) {
               e.printStackTrace();
           }
           return  null;
       }
       /*
       * 解析xls数据
       *
       */
    public List<Map<String,Object>> getJieXi(Workbook work,Map<String,String> map) throws Exception {
        if (null == work) {
            throw new Exception("创建Excel工作薄为空");
        }
        Sheet sheet = null;
        Row row = null;
        Cell cell = null;
        // 返回数据
        List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();

        // 遍历Excel中所有的sheet
        for (int i = 0; i < work.getNumberOfSheets(); i++) {
            sheet = work.getSheetAt(i);
            if (sheet == null)
                continue;
            // 取第一行标题
            row = sheet.getRow(0);
            String title[] = null;
            if (row != null) {
                title = new String[row.getLastCellNum()];
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    title[y] = (String) getCellValue(cell);
                }

            } else
                continue;
            log.info(title.toString());
            // 遍历当前sheet中的所有行
            for (int j = 2; j < sheet.getLastRowNum() + 1; j++) {
                row = sheet.getRow(j);
                Map<String, Object> m = new HashMap<String, Object>();
                // 遍历所有的列
                for (int y = row.getFirstCellNum(); y < row.getLastCellNum(); y++) {
                    cell = row.getCell(y);
                    String key = title[y];
                    // log.info(JSON.toJSONString(key));
                    m.put(map.get(key), getCellValue(cell));
                }
                ls.add(m);
            }

        }
        return ls;
    }

    /**
     * 描述：对表格中数值进行格式化
     *
     * @param cell
     * @return
     */
    public static Object getCellValue(Cell cell) {
        Object value = null;
        DecimalFormat df = new DecimalFormat("0"); // 格式化number String字符
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd"); // 日期格式化
        DecimalFormat df2 = new DecimalFormat("0"); // 格式化数字

        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if ("General".equals(cell.getCellStyle().getDataFormatString())) {
                    value = df.format(cell.getNumericCellValue());
                } else if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())) {
                    value = sdf.format(cell.getDateCellValue());
                } else {
                    value = df2.format(cell.getNumericCellValue());
                }
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                value = cell.getBooleanCellValue();
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            default:
                break;
        }
        return value;
    }
            /**
             * 导入学生成绩
             *
             * @throws IOException
             * */
    @RequestMapping("/upLoadScoreInfo")
    @ResponseBody
    public Map<String, Object> upLoadScoreInfoByModel(
            @RequestParam("fileUpload") MultipartFile fileUpload,
            @CurrentUser UserInfo user) {
        //错误信息列表
        StudentScoreBean studentScoreBean = new StudentScoreBean();
        studentScoreBean.setStudentScoreList(new ArrayList<StudentScoreVo>());
        List<StudentScoreVo> scoreList = studentScoreBean.getStudentScoreList();

        Map<String, Object> map = new HashMap<String, Object>();
        List<StudentScoreVo> errorScoreVoList = new ArrayList<StudentScoreVo>();
        List<StudentScoreVo> successScoreVoList = new ArrayList<StudentScoreVo>();


        String status = ResponseInfomation.OPERATION_SUC;
        String fileName = fileUpload.getOriginalFilename();//获取文件名
        InputStream is = null;
        try {
            is = fileUpload.getInputStream();
            String suffix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
            ParseConfig config = SzxyExcelTookit.getConfig("studentscorevo");
            int examId = 0;
            int errorNum = 0;
            //List<Object> excelList = SzxyExcelTookit.excelDataToModels(config, is, suffix);
            List<StudentScoreVo> excelList = this.excelDataToObject(is);
            for (int i = 0; i < excelList.size(); i++) {
                if (i != 0) {
                    StudentScoreVo vo = (StudentScoreVo) excelList.get(i);
                    if (vo != null && vo.getExamTeamSubjectId() != null && vo.getExamTeamSubjectId() == examId) {
                        //examId = vo.getExamTeamSubjectId();
                    } else {
                        vo.setErrorInfo("考试标识有问题，请重新下载模板");
                        errorScoreVoList.add(vo);
                        errorNum++;
                    }
                } else {
                    StudentScoreVo vo = (StudentScoreVo) excelList.get(i);
                    if (vo != null && vo.getExamTeamSubjectId() != null && vo.getExamTeamSubjectId() != 0) {
                        examId = vo.getExamTeamSubjectId();
                    } else {
                        vo.setErrorInfo("考试标识有问题，请重新下载模板");
                        errorScoreVoList.add(vo);
                        errorNum++;
                    }

                }
            }

            if (errorNum == 0) {
                for (Object object : excelList) {
                    StudentScoreVo vo = (StudentScoreVo) object;

                    if (vo != null && vo.getStudentId() != null && vo.getStudentName() != null) {
                        StudentCondition studentCondition = new StudentCondition();
                        studentCondition.setId(vo.getStudentId());
                        studentCondition.setName(vo.getStudentName());
                        Student student = this.studentService.findStudentById(vo.getStudentId());
                        if (student != null && student.getName().equals(vo.getStudentName())) {
                            if (vo.getId() != null) {
                                if (vo.getScore() == null || "".equals(vo.getScore())) {
                                    scoreList.add(vo);
                                } else {


                                    try {
                                        String sco = vo.getScore();
                                        BigDecimal score = new BigDecimal(sco);
                                        BigDecimal small = new BigDecimal("0");
                                        BigDecimal big = new BigDecimal("10000");
                                        if (score.compareTo(small) > 0 && score.compareTo(big) < 0) {
                                            if (sco.split("\\.").length == 1) {
                                                scoreList.add(vo);
                                            } else {
                                                //System.out.println(sco.length()-sco.split("\\.")[0].length()-1);
                                                if (sco.length() - sco.split("\\.")[0].length() - 1 > 2) {
                                                    //System.out.println("成绩小数点最多两位");
                                                    vo.setErrorInfo("成绩小数点最多两位");
                                                    errorScoreVoList.add(vo);
                                                } else {
                                                    scoreList.add(vo);
                                                }
                                            }

                                        } else {
                                            //System.out.println("成绩范围不正确0-10000");
                                            vo.setErrorInfo("成绩范围不正确0-10000");
                                            errorScoreVoList.add(vo);
                                        }
                                    } catch (Exception e) {
                                        //System.out.println("成绩类型不正确");
                                        vo.setErrorInfo("成绩类型不正确");
                                        errorScoreVoList.add(vo);
                                    }


                                }
                            } else {
                                StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
                                studentScoreCondition.setExamTeamSubjectId(examId);
                                studentScoreCondition.setStudentId(vo.getStudentId());
                                List<StudentScore> studentscoreList = this.studentScoreService.findStudentScoreByCondition(studentScoreCondition);
                                if (studentscoreList != null && studentscoreList.size() > 0) {
                                    status = "学生成绩模板不是最新的，请重新下载模板";
                                    vo.setErrorInfo("学生成绩模板不是最新的，请重新下载模板");
                                    errorScoreVoList.add(vo);
                                } else {

                                    if (vo.getScore() == null || "".equals(vo.getScore())) {
                                        scoreList.add(vo);
                                    } else {


                                        try {
                                            String sco = vo.getScore();
                                            BigDecimal score = new BigDecimal(sco);
                                            BigDecimal small = new BigDecimal("0");
                                            BigDecimal big = new BigDecimal("10000");
                                            if (score.compareTo(small) > -1 && score.compareTo(big) < 0) {
                                                if (sco.split("\\.").length == 1) {
                                                    scoreList.add(vo);
                                                } else {
                                                    //System.out.println(sco.length()-sco.split("\\.")[0].length()-1);
                                                    if (sco.length() - sco.split("\\.")[0].length() - 1 > 2) {
                                                        //System.out.println("成绩小数点最多两位");
                                                        vo.setErrorInfo("成绩小数点最多两位");
                                                        errorScoreVoList.add(vo);
                                                    } else {
                                                        scoreList.add(vo);
                                                    }
                                                }

                                            } else {
                                                //System.out.println("成绩范围不正确0-10000");
                                                vo.setErrorInfo("成绩范围不正确0-10000");
                                                errorScoreVoList.add(vo);
                                            }
                                        } catch (Exception e) {
                                            //System.out.println("成绩类型不正确");
                                            vo.setErrorInfo("成绩类型不正确");
                                            errorScoreVoList.add(vo);
                                        }

                                    }

                                }
                            }

                        } else {
                            vo.setErrorInfo("学生程序标示和姓名不正确，请重新下载模板");
                            errorScoreVoList.add(vo);
                        }
                    } else {
                        vo.setErrorInfo("学生程序标示和姓名不能为空");
                        errorScoreVoList.add(vo);
                    }

                }

                try {
                    studentScoreBean.setExamTeamSubjectId(examId);
                    int addSum = 0;
                    ExamTeamSubjectVo examTeamSubjectVo = this.examTeamSubjectService.findExamTeamSubjectVoById(teamService, studentService, examId);
                    if (examTeamSubjectVo != null) {
                        studentScoreBean.setExamName(examTeamSubjectVo.getExamName());
                        studentScoreBean.setExamType(examTeamSubjectVo.getExamType());
                        studentScoreBean.setGradeId(examTeamSubjectVo.getGradeId());
                        studentScoreBean.setRateType(examTeamSubjectVo.getRateType());
                        studentScoreBean.setSchoolYear(examTeamSubjectVo.getSchoolYear());
                        studentScoreBean.setSubjectCode(examTeamSubjectVo.getSubjectCode());
                        studentScoreBean.setTeamId(examTeamSubjectVo.getTeamId());
                        studentScoreBean.setTermCode(examTeamSubjectVo.getTerm());
                    }
                    addSum = this.addStudentScore(user, studentScoreBean, scoreList, examId, 0, examTeamSubjectVo, addSum);

                    if (scoreList != null && addSum == scoreList.size()) {
                        status = ResponseInfomation.OPERATION_SUC;
                        for (StudentScoreVo scorevo : scoreList) {
                            successScoreVoList.add(scorevo);
                        }
                    } else {
                        status = ResponseInfomation.OPERATION_FAIL;
                    }


                } catch (Exception e) {
                    status = ResponseInfomation.OPERATION_FAIL;
                    e.printStackTrace();
                }
            } else {

            }


        } catch (IOException e) {
            status = "针对成绩导入失败";
            e.printStackTrace();
        }

        //errorParentVoList = getTest("失败");
        //successParentVoList = getTest("成功");
        map.put("status", status);
        map.put("error", errorScoreVoList);
        map.put("success", successScoreVoList);


        return map;
    }


    private List<StudentScoreVo> excelDataToObject(InputStream is) {

        HSSFWorkbook workbook = null;
        DecimalFormat df = new DecimalFormat("#");
        List<StudentScoreVo> excelList = new ArrayList<StudentScoreVo>();
        try {
            workbook = new HSSFWorkbook(is);


            System.out.println("===SheetsNum===" + workbook.getNumberOfSheets()); // 获得Sheet数

            for (int i = 0; i < workbook.getNumberOfSheets(); i++) { // 开始读取
                if (null != workbook.getSheetAt(i)) {

                    HSSFSheet sheet = workbook.getSheetAt(i);// 获得一个Sheet
                    System.out.println("共有" + sheet.getLastRowNum() + "行");

                    for (int rowNumOfSheet = 1; rowNumOfSheet <= sheet.getLastRowNum(); rowNumOfSheet++) {
                        if (null != sheet.getRow(rowNumOfSheet)) {
                            // 获得一个row
                            StudentScoreVo vo = new StudentScoreVo();
                            HSSFRow row = sheet.getRow(rowNumOfSheet);
                            try {
                                Cell c0=row.getCell(0);
                                if(c0==null || StringUtils.isEmpty(c0.getStringCellValue())){
                                    continue;
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            System.out.print("第" + rowNumOfSheet + "行   ");
                            // System.out.print("一行有"+row.getLastCellNum()+"单元格");
                            for (short cellNumOfRow = 0; cellNumOfRow < row.getLastCellNum(); cellNumOfRow++) {
                                String strCell = "0";
                                HSSFCell cell = row.getCell(cellNumOfRow);
                                if(cell!=null){
                                    int cellType = cell.getCellType();
                                    switch (cellType) {
                                        case 0:// Numberic
                                            //String strCell = df.format(cell.getNumericCellValue());
                                            strCell = String.valueOf(cell.getNumericCellValue());
                                            System.out.print(strCell + " ");
                                            break;
                                        case 1:
                                            strCell = cell.getRichStringCellValue().getString();
                                            System.out.print(strCell + " ");
                                            break;
                                        default:
                                            System.out.println("数据格式不对！");
                                    }
                                }
                                try {
                                    switch (cellNumOfRow) {
                                        case 0:
                                            if (strCell == null || "".equals(strCell)) {
                                                vo.setExamTeamSubjectId(null);
                                            } else {
                                                String big = new DecimalFormat("0").format(Double.parseDouble(strCell));
                                                vo.setExamTeamSubjectId(Integer.parseInt(big.toString()));
                                            }

                                            break;
                                        case 1:
                                            if (strCell == null || "".equals(strCell)) {
                                                vo.setId(null);
                                            } else {
                                                String big = new DecimalFormat("0").format(Double.parseDouble(strCell));
                                                vo.setId(Integer.parseInt(big.toString()));
                                            }

                                            break;
                                        case 2:
                                            if (strCell == null || "".equals(strCell)) {
                                                vo.setStudentId(null);
                                            } else {
                                                String big = new DecimalFormat("0").format(Double.parseDouble(strCell));
                                                vo.setStudentId(Integer.parseInt(big.toString()));
                                            }
                                            break;
                                        case 3:
                                            vo.setStudentNumber(strCell);
                                            break;
                                        case 4:
                                            vo.setStudentName(strCell);
                                            break;
                                        case 5:
                                            vo.setScore(strCell);
                                            break;
                                        case 6:
                                            vo.setComment(strCell);
                                            break;
                                        default:
                                            System.out.println("列数不对！");
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    continue;

                                }

                            }
                            System.out.println("");
                            excelList.add(vo);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return excelList;
    }

}
