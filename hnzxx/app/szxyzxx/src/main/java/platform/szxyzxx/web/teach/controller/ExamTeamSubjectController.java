package platform.szxyzxx.web.teach.controller;

import cn.hutool.core.util.StrUtil;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import framework.generic.facility.poi.excel.config.ParseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.BasicSQLService;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.resource.utils.UUIDUtil;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.util.poi.excel.main.SzxyExcelTookit;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/teach/examTeamSubject")
public class ExamTeamSubjectController extends BaseController {

    private final static String viewBasePath = "/teach/examTeamSubject";

    @Autowired
    private BasicSQLService basicSQLService;

    /**
     * 查询考试日程安排信息
     *
     * @param user
     * @param dm
     * @param sub
     * @param condition
     * @param page
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView examTeamSubjectList(
            @CurrentUser UserInfo user,
            @RequestParam(value = "dm", required = false) String dm,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("condition") ExamTeamSubjectCondition condition,
            @ModelAttribute("page") Page page
    ) {
        ModelAndView mav = new ModelAndView();
        String viewPath = null;
        condition.setSchoolId(user.getSchoolId());
        condition.setTaskType(ExamTeamSubjectCondition.TASKTYPE_EXAM);//默认查询考试类别的任务
		/*if(sub == null){
			condition.setSchoolYear(user.getSchoolYear());
			condition.setTerm(user.getSchoolTermCode());
		}*/
        Order order = new Order();
        order.setProperty("create_date");
        order.setAscending(false);
        List<ExamTeamSubjectVo> examTeamSubjectVoList = null;
        if ("list".equals(sub)) {
            examTeamSubjectVoList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(this.teamService, this.studentService, condition, page, order);
            for (ExamTeamSubjectVo examTeamSubjectVo : examTeamSubjectVoList) {
                SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
                schoolYearCondition.setSchoolId(user.getSchoolId());
                schoolYearCondition.setYear(examTeamSubjectVo.getSchoolYear());
                SchoolYear schoolyear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
                if (schoolyear == null) {
                    schoolyear = new SchoolYear();
                }
                examTeamSubjectVo.setSchoolYearName(schoolyear.getName());
                SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
                schoolTermCondition.setSchoolYear(examTeamSubjectVo.getSchoolYear());
                schoolTermCondition.setCode(examTeamSubjectVo.getTerm());
                List<SchoolTerm> termList = this.schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
                if (termList.size() == 1) {
                    examTeamSubjectVo.setTermCodeName(termList.get(0).getName());
                }

                Subject subject = this.subjectService.findUnique(user.getSchoolId(), examTeamSubjectVo.getSubjectCode());
                if (subject != null && subject.getId() > 0) {
                    examTeamSubjectVo.setSubjectName(subject.getName());
                }
            }
            viewPath = structurePath("/list");
        } else {
            viewPath = structurePath("/examTeamSubjectList");
        }
        mav.addObject("examTeamSubjectVoList", examTeamSubjectVoList);
        String sql = "select yr.* from pj_teacher pt inner join  yh_user_role yur on yur.user_id=pt.user_id inner join yh_role yr on yr.id=yur.role_id where pt.user_id="+user.getId();
        List<Map<String,Object>> mapList=basicSQLService.find(sql);
        boolean juse=false;
        for(Map<String,Object> aa:mapList){
            if(aa.get("code").toString().equals("CHENGJIFENXI_GUANLIYUAN")){
               juse=true;
               break;
            }
        }
        mav.addObject("juese", juse);
        mav.setViewName(viewPath);

        return mav;
    }


    @RequestMapping(value = "/downLoadExcel")
    @ResponseBody
    public void downLoadExcel(
            @CurrentUser UserInfo user,
            @RequestParam(value = "schoolYear", required = false) String schoolYear,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "term", required = false) String term
            , HttpServletResponse response,
            HttpServletRequest request) {

        ExamTeamSubjectCondition condition = new ExamTeamSubjectCondition();
        if (schoolYear != null && !"".equals(schoolYear.trim())) {
            condition.setSchoolYear(schoolYear);
        } else {
            condition.setSchoolYear(null);
        }
        condition.setGradeId(gradeId);
        condition.setTeamId(teamId);

        if (term != null && !"".equals(term.trim())) {
            condition.setTerm(term);
        } else {
            condition.setTerm(null);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String time = sdf.format(new Date());
        String filename = "考试日程安排信息" + time + ".xls";
        condition.setSchoolId(user.getSchoolId());//添加学校查询条件
        condition.setTaskType(ExamTeamSubjectCondition.TASKTYPE_EXAM);//默认查询考试类别的任务
        Page page = new Page();
        page.setPageSize(Integer.MAX_VALUE);
        List<ExamTeamSubjectVo> examTeamSubjectVoList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(this.teamService, this.studentService, condition, page, null);
        for (ExamTeamSubjectVo examTeamSubjectVo : examTeamSubjectVoList) {
            SchoolYearCondition schoolYearCondition = new SchoolYearCondition();
            schoolYearCondition.setSchoolId(user.getSchoolId());
            schoolYearCondition.setYear(examTeamSubjectVo.getSchoolYear());
            SchoolYear schoolyear = this.schoolYearService.findSchoolYearByYear(schoolYearCondition);
            if (schoolyear == null) {
                schoolyear = new SchoolYear();
            }
            examTeamSubjectVo.setSchoolYearName(schoolyear.getName());
            SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
            schoolTermCondition.setSchoolYear(examTeamSubjectVo.getSchoolYear());
            schoolTermCondition.setCode(examTeamSubjectVo.getTerm());
            List<SchoolTerm> termList = this.schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
            if (termList.size() == 1) {
                examTeamSubjectVo.setTermCodeName(termList.get(0).getName());
            }

            Subject subject = this.subjectService.findUnique(user.getSchoolId(), examTeamSubjectVo.getSubjectCode());
            if (subject != null && subject.getId() > 0) {
                examTeamSubjectVo.setSubjectName(subject.getName());
            }

        }


//			String[] titles = { "学年 ", "学期", "考试名称","班级名称", "考试类型" , "考试科目", "是否在线", "考试开始时间", "考试结束时间", "考试人数"};
        String[] titles = {"学年 ", "学期", "考试名称", "班级名称", "考试类型", "考试科目", "考试日期", "考试人数"};
        String[] fieldNames = {"schoolYearName", "termCodeName", "examName", "teamName", "examType", "subjectCode", "preciseStartDate", "teamSum"};

        //List<Object> list = new ArrayList<Object>();
        List<Object> maps = new ArrayList<Object>();
        //for (ExamTeamSubjectVo vo : examTeamSubjectVoList) {
        for (int j = 0; j < examTeamSubjectVoList.size(); j++) {

            ExamTeamSubjectVo vo = examTeamSubjectVoList.get(j);
            if (vo.getId() > 0) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put(fieldNames[0], vo.getSchoolYearName());
                map.put(fieldNames[1], vo.getTermCodeName());
                map.put(fieldNames[2], vo.getExamName());
                map.put(fieldNames[3], vo.getTeamName());

                // 期中考试	1
                //期末考试	2
                //平时考试	3
                if (vo.getExamType() != null && !("").equals(vo.getExamType())) {
                    String name = vo.getExamType();
                    if (name.equals("1")) {
                        map.put(fieldNames[4], "期中考试");
                    } else if (name.equals("2")) {
                        map.put(fieldNames[4], "期末考试");
                    } else if (name.equals("3")) {
                        map.put(fieldNames[4], "平时考试");
                    }
                } else {
                    map.put(fieldNames[4], vo.getExamType());
                }
                map.put(fieldNames[5], vo.getSubjectCode());
                map.put(fieldNames[6], vo.getPreciseStartDate());
                map.put(fieldNames[7], vo.getTeamSum());
                maps.add(map);

                //list.add(vo);
            } else {
                //去除错误的
            }

        }
        ParseConfig config = SzxyExcelTookit.getConfig();
        config.setFieldNames(fieldNames);
        config.setTitles(titles);
        config.setSheetTitle("考试日程安排信息");
			
			/*
			Map<Object, Map<Object, Object>> codeWithValueMaps = new HashMap<Object, Map<Object, Object>>();
			Map<Object, Object> taskOnlineNameMaps = new HashMap<Object, Object>();
			taskOnlineNameMaps.put(false, "否");
			taskOnlineNameMaps.put(true, "是");
			
			codeWithValueMaps.put("taskOnlineNameMaps", taskOnlineNameMaps);
			config.setCodeWithValueMaps(codeWithValueMaps);*/
        try {
            //SzxyExcelTookit.exportExcel(maps, config, filename);
            SzxyExcelTookit.exportExcelToWEB(maps, config, request, response, filename);
            //SzxyExcelTookit.exportExcelToWEB(list, config, request, response, filename);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @RequestMapping(value = "/creator", method = RequestMethod.GET)
    public ModelAndView addExamTeamSubjectPage() {
        return new ModelAndView(structurePath("/add"));
    }



    /*
    * 添加考试日程
    */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseInfomation addExamTeamSubject(ExamTeamSubjectVo examTeamSubjectVo, @CurrentUser UserInfo user) {
        ExamTeamSubjectVo examTeamSubjectVoEnd = null;
        ResponseInfomation responseInfomation = null;

        //学年，学期，年级，班级，类型，科目，
        ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
        examTeamSubjectCondition.setSchoolYear(examTeamSubjectVo.getSchoolYear());
        examTeamSubjectCondition.setTerm(examTeamSubjectVo.getTerm());
        examTeamSubjectCondition.setGradeId(examTeamSubjectVo.getGradeId());
        examTeamSubjectCondition.setSchoolId(user.getSchoolId());
        examTeamSubjectCondition.setExamType(examTeamSubjectVo.getExamType());
        examTeamSubjectCondition.setSubjectCode(examTeamSubjectVo.getSubjectCode());
        if(examTeamSubjectVo.getExamType().equals("3")){
            examTeamSubjectCondition.setExamName(examTeamSubjectVo.getExamName());
        }
        boolean flag = true;
        List<Integer> teamList = this.examTeamSubjectService.getTeamIdByGrade(gradeService, teamService, examTeamSubjectVo);
        String examName = "";
        String subjectCode = "";
        if(!examTeamSubjectVo.getExamType().equals("4")){
            for (Integer teamID : teamList) {
                examTeamSubjectCondition.setTeamId(teamID);
                List<ExamTeamSubjectVo> examTeamSubjectVoList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService, examTeamSubjectCondition);
                if (examTeamSubjectVoList != null && examTeamSubjectVoList.size() > 0) {
                    ExamTeamSubjectVo vo = examTeamSubjectVoList.get(0);
                    flag = false;
                    examName = vo.getExamName();
                    subjectCode = vo.getSubjectCode();
                }
            }
        }
        if (!flag) {
            responseInfomation = new ResponseInfomation("这次考试" + examName + "科目已经添加过，不能继续添加");
        } else {
            examTeamSubjectVo.setSchoolId(user.getSchoolId());
            examTeamSubjectVo.setTaskType(ExamTeamSubjectCondition.TASKTYPE_EXAM);//从考务里面添加的任务默认是考试类别的任务
            examTeamSubjectVo.setTaskRate(false);//这次考试是否已经给学生添加成绩
            String uuid = UUIDUtil.getUUID();
            examTeamSubjectVoEnd = this.examTeamSubjectService.addVo(this.gradeService, this.teamService, examTeamSubjectVo, uuid);
            responseInfomation = examTeamSubjectVoEnd != null ? new ResponseInfomation(examTeamSubjectVoEnd.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation("添加考务失败");
        }
        return responseInfomation;
    }

    @RequestMapping(value = "/editor", method = RequestMethod.GET)
    public ModelAndView modifyExamTeamSubjectPage(
            @RequestParam(value = "id", required = true) Integer id) {
        ModelAndView mav = new ModelAndView();

        ExamTeamSubjectVo examTeamSubjectVo = this.examTeamSubjectService.findExamTeamSubjectVoById(this.teamService, this.studentService, id);

        mav.addObject("examTeamSubjectVo", examTeamSubjectVo);
        mav.setViewName(structurePath("/modify"));

        return mav;
    }

    @RequestMapping(value = "/viewer", method = RequestMethod.GET)
    public ModelAndView viewer(
            @RequestParam(value = "id", required = true) Integer id) {
        ModelAndView mav = new ModelAndView();
        ExamTeamSubjectVo examTeamSubjectVo = this.examTeamSubjectService.findExamTeamSubjectVoById(this.teamService, this.studentService, id);

        mav.addObject("isCK", "disable");
        mav.addObject("examTeamSubjectVo", examTeamSubjectVo);
        mav.setViewName(structurePath("/detail"));

        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public String delete(@PathVariable(value = "id") Integer id, ExamTeamSubject examTeamSubject) {
        if (id == null) {
            return ResponseInfomation.OPERATION_FAIL;
        }
        if (examTeamSubject != null) {
            examTeamSubject.setId(id);
        }
        this.examTeamSubjectService.remove(examTeamSubject);

        StudentScore studentScore = new StudentScore();
        studentScore.setExamTeamSubjectId(id);
        StudentScoreCondition studentScoreCondition = new StudentScoreCondition();
        studentScoreCondition.setExamTeamSubjectId(id);
        studentScoreCondition.setIsDelete(false);
        // 如果删除的数据为空会加锁，所以 加上判断
       /* Long count = studentScoreService.count(studentScoreCondition);
        if (count > 0) {*/
            studentScoreService.removeByExamTeamSubjectId(studentScore);
        /*}*/

        return ResponseInfomation.OPERATION_SUC;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseInfomation edit(@PathVariable(value = "id") Integer id, ExamTeamSubject examTeamSubject) {
        examTeamSubject.setId(id);
        ResponseInfomation responseInfomation = null;
        //学年，学期，年级，班级，类型，科目，
        ExamTeamSubjectCondition examTeamSubjectCondition = new ExamTeamSubjectCondition();
        examTeamSubjectCondition.setSchoolYear(examTeamSubject.getSchoolYear());
        examTeamSubjectCondition.setTerm(examTeamSubject.getTerm());
        examTeamSubjectCondition.setTeamId(examTeamSubject.getTeamId());
        examTeamSubjectCondition.setExamType(examTeamSubject.getExamType());
        examTeamSubjectCondition.setSubjectCode(examTeamSubject.getSubjectCode());
        examTeamSubjectCondition.setExamName(examTeamSubject.getExamName());
        List<ExamTeamSubjectVo> examTeamSubjectVoList = this.examTeamSubjectService.findExamTeamSubjectVoByCondition(studentService, examTeamSubjectCondition);
        if (examTeamSubjectVoList != null && examTeamSubjectVoList.size() > 1) {
            responseInfomation = new ResponseInfomation("要修改的考务已经存在多个，错误");
        } else if (examTeamSubjectVoList != null && examTeamSubjectVoList.size() == 1) {
            if (id != null && (id == examTeamSubjectVoList.get(0).getId() || examTeamSubjectVoList.get(0).getId().equals(id))) {
                boolean flgType = flgUpdateUUid(id, examTeamSubject);
                if (!flgType) {
                    examTeamSubject.setUuid(UUIDUtil.getUUID());
                }
                ExamTeamSubject examTeamSubjectEnd = this.examTeamSubjectService.modify(examTeamSubject);
                // 修改学生成绩表考试的名称，这个系统是根据名称判断排名
                StudentScore studentScore = new StudentScore();
                studentScore.setExamTeamSubjectId(id);
                studentScore.setExamName(examTeamSubject.getExamName());
                studentScoreService.modifyByexamTeamSubjectId(studentScore);

                responseInfomation = examTeamSubjectEnd != null ? new ResponseInfomation(examTeamSubjectEnd.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation("修改考务失败");
            } else {
                responseInfomation = new ResponseInfomation("要修改的考务已经存在，不能修改为这个考试安排");
            }

        } else {
            boolean flgType = flgUpdateUUid(id, examTeamSubject);
            if (!flgType) {
                examTeamSubject.setUuid(UUIDUtil.getUUID());
            }
            ExamTeamSubject examTeamSubjectEnd = this.examTeamSubjectService.modify(examTeamSubject);
            // 修改学生成绩表考试的名称，这个系统是根据名称判断排名
            StudentScore studentScore = new StudentScore();
            studentScore.setExamTeamSubjectId(id);
            studentScore.setExamName(examTeamSubject.getExamName());
            studentScoreService.modifyByexamTeamSubjectId(studentScore);

            responseInfomation = examTeamSubjectEnd != null ? new ResponseInfomation(examTeamSubjectEnd.getId(), ResponseInfomation.OPERATION_SUC) : new ResponseInfomation("修改考务失败");
        }
        //examTeamSubject.setTaskType(ExamTeamSubjectCondition.TASKTYPE_EXAM);//从考务里面添加的任务默认是考试类别的任务
        return responseInfomation;
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }


    /**
     * 比较考试名称、考试类型、考试科目、考试日期，是否修改，如修改，重新指定uuid，uuid：小程序根据这个判断是否为同一次考试，同一个年级的排名
     *
     * @param id
     * @return
     */
    public boolean flgUpdateUUid(Integer id, ExamTeamSubject examTeamSubject) {
        ExamTeamSubject subjectVo = examTeamSubjectService.findById(id);

        String examName = subjectVo.getExamName();
        String examType = subjectVo.getExamType();
        String subjectCode = subjectVo.getSubjectCode();
        Date preciseStartDate = subjectVo.getPreciseStartDate();
        if (StrUtil.isEmpty(examName) || !examName.equals(examTeamSubject.getExamName())) {
            return false;
        }
        if (StrUtil.isEmpty(examType) || !examType.equals(examTeamSubject.getExamType())) {
            return false;
        }
        if (StrUtil.isEmpty(subjectCode) || !subjectCode.equals(examTeamSubject.getSubjectCode())) {
            return false;
        }
        Date preciseStartDate1 = examTeamSubject.getPreciseStartDate();
        boolean sameDate = sameDate(preciseStartDate1, preciseStartDate);
        if (!sameDate) {
            return false;
        }
        return true;
    }

    /**
     * 判断年月日是否相等
     *
     * @param d1
     * @param d2
     * @return
     */
    public static boolean sameDate(Date d1, Date d2) {
        if (null == d1 || null == d2) {
            return false;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(d1);
        cal1.set(Calendar.HOUR_OF_DAY, 0);
        cal1.set(Calendar.MINUTE, 0);
        cal1.set(Calendar.SECOND, 0);
        cal1.set(Calendar.MILLISECOND, 0);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(d2);
        cal2.set(Calendar.HOUR_OF_DAY, 0);
        cal2.set(Calendar.MINUTE, 0);
        cal2.set(Calendar.SECOND, 0);
        cal2.set(Calendar.MILLISECOND, 0);
        return cal1.getTime().equals(cal2.getTime());
    }

}
