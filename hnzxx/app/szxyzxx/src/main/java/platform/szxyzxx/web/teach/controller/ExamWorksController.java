package platform.szxyzxx.web.teach.controller;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.ExamWorksVo;
import platform.education.generalTeachingAffair.vo.PjExamCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherCondition;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.util.ResponseInfomation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Administrator on 2018/2/1.
 */
@Controller
@RequestMapping("/teach/examWorks")
public class ExamWorksController extends BaseController {

    private static final Logger log = LoggerFactory.getLogger(ExamWorksController.class);

    private final static String viewBasePath = "/teach/examWorks";

    @Autowired
    @Qualifier("examWorksService")
    private ExamWorksService examWorksService;

    @Autowired
    @Qualifier("examWorksSubjectService")
    private ExamWorksSubjectService examWorksSubjectService;

    @Autowired
    @Qualifier("examWorksGradeService")
    private ExamWorksGradeService examWorksGradeService;

    @Autowired
    @Qualifier("examWorksTeamService")
    private ExamWorksTeamService examWorksTeamService;

    @Autowired
    @Qualifier("examWorksTeamSubjectService")
    private ExamWorksTeamSubjectService examWorksTeamSubjectService;

    @Autowired
    @Qualifier("examWorksSubjectTemplateService")
    private ExamWorksSubjectTemplateService examWorksSubjectTemplateService;

    @Autowired
    @Qualifier("scoreAnalysisHandleService")
    private ScoreAnalysisHandleService scoreAnalysisHandleService;


    @RequestMapping(value = "/index")
    public ModelAndView index(
            @CurrentUser UserInfo user,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            Model model) {
        String viewPath = "";
        if (type != null && !"".equals(type)) {
            Integer schoolId = user.getSchoolId();
            String title = getTitle(schoolId, year, termCode ,null);
            String name = "";
            if ("02".equals(type) || "01".equals(type)) {
                if ("01".equals(type)) {
                    name = "????????????";
                } else {
                    name = "????????????";
                }
                List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, year);
                //????????????????????????????????????????????????????????????????????????
                List<ExamWorks> examWorksList = examWorksService.findMajorExamWorksByType(schoolId, year, termCode, type);
                ExamWorks examWorks = null;
                Integer examWorksId = null;
                if (examWorksList != null && examWorksList.size() > 0) {
                    examWorks = examWorksList.get(0);
                    examWorksId = examWorks.getId();
                    List<ExamWorksGrade> examWorksGradeList = examWorksGradeService.findOfExamWorks(examWorksId);
                    String joinGrade = "";
                    for (ExamWorksGrade ewg : examWorksGradeList) {
                        joinGrade += ewg.getGradeId() + ",";
                    }
                    model.addAttribute("examWorks", examWorks);
                    model.addAttribute("joinGrade", joinGrade);
                    model.addAttribute("examGrades", JSONArray.fromObject(examWorksGradeList).toString());
                    viewPath = structurePath("/exam/modifyPage");
                } else {
                    viewPath = structurePath("/exam/addPage");
                }

                model.addAttribute("gradeList", gradeList);
                model.addAttribute("examWorksId", examWorksId);
            } else if ("03".equals(type) || "12".equals(type)) {
                //???????????????????????????????????????????????????
                int count = 10;
                if ("03".equals(type)) {
                    name = "?????????";
                } else {
                    name = "??????";
                    SchoolTerm schoolTerm = schoolTermService.findSchoolTermByCode(schoolId, termCode);
                    List<Object> months = getMonth(schoolTerm.getBeginDate(), schoolTerm.getFinishDate());
                    if (months != null) {
                        count = months.size();
                    }
                }
                List<ExamWorks> examWorksList = examWorksService.findMajorExamWorksByType(schoolId, year, termCode, type);

                model.addAttribute("examWorksList", examWorksList);
                model.addAttribute("count", count);

                viewPath = structurePath("/exam/monthlyIndex");
            }
            model.addAttribute("name", name);
            model.addAttribute("title", title);
            model.addAttribute("type", type);
            model.addAttribute("year", year);
            model.addAttribute("termCode", termCode);

        } else {
            viewPath = structurePath("/exam/index");
        }
        return new ModelAndView(viewPath, model.asMap());
    }

    private String getTitle(Integer schoolId, String year, String termCode, String examType) {
        String yearName = "";
        String termName = "";
        SchoolYear schoolYear = schoolYearService.findByYearAndSchoolId(schoolId, year);
        if (schoolYear != null) {
            yearName = schoolYear.getName();
        }
        SchoolTerm schoolTerm = schoolTermService.findSchoolTermByCode(schoolId, termCode);
        if (schoolTerm != null) {
            termName = schoolTerm.getName();
        }
        String title = "?????????" + yearName + " ?????????" + termName;
        if (examType != null && !"".equals(examType)) {
            String name = "";
            switch (examType) {
                case "01" : name = "????????????"; break;
                case "02" : name = "????????????"; break;
                case "03" : name = "?????????"; break;
                case "12" : name = "??????"; break;
                case "20" : name = "????????????"; break;
            }
            title += " ???????????????" + name;
        }
        return title;
    }

    /**
     * ??????????????????
     */
    @RequestMapping(value = "/addPage")
    public ModelAndView toAddPage(
            @CurrentUser UserInfo user,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "rounds", required = false) String rounds,
            Model model){
        Integer schoolId = user.getSchoolId();
        String yearName = "";
        String termName = "";
        SchoolYear schoolYear = schoolYearService.findByYearAndSchoolId(schoolId, year);
        if (schoolYear != null) {
            yearName = schoolYear.getName();
        }
        SchoolTerm schoolTerm = schoolTermService.findSchoolTermByCode(schoolId, termCode);
        if (schoolTerm != null) {
            termName = schoolTerm.getName();
        }
        String title = "?????????" + yearName + " ?????????" + termName;
        List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, year);
        List<Object> months = getMonth(schoolTerm.getBeginDate(), schoolTerm.getFinishDate());

        model.addAttribute("months", months);
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("title", title);
        model.addAttribute("rounds", rounds);
        model.addAttribute("type", type);
        model.addAttribute("year", year);
        model.addAttribute("termCode", termCode);
        return new ModelAndView(structurePath("/exam/addPage"), model.asMap());
    }

    /**
     * ????????????????????????????????????
     */
    private List<Object> getMonth(Date begin, Date end){
        List<Object> list = new ArrayList<>();
        String[] months = {"???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "???", "??????", "??????"};
        SimpleDateFormat sdf = new SimpleDateFormat("MM");

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(begin);
        min.set(min.get(Calendar.YEAR), min.get(Calendar.MONTH), 1);

        max.setTime(end);
        max.set(max.get(Calendar.YEAR), max.get(Calendar.MONTH), 2);

        Calendar curr = min;
        while (curr.before(max)) {
            Integer num = Integer.valueOf(sdf.format(curr.getTime()));
            Map<String, Object> map = new HashMap<>();
            map.put("num", num);
            map.put("month", months[num] + "??????");
            list.add(map);
            curr.add(Calendar.MONTH, 1);
        }

        return list;
    }


    /**
     * ??????????????????
     */
    @RequestMapping(value = "/modifyPage")
    public ModelAndView toModifyPage(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            Model model){
        Integer schoolId = user.getSchoolId();
        List<Grade> gradeList = gradeService.findGradeBySchoolYear(schoolId, year);
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        List<ExamWorksGrade> examWorksGradeList = examWorksGradeService.findOfExamWorks(examWorksId);
        String joinGrade = "";
        for (ExamWorksGrade ewg : examWorksGradeList) {
            joinGrade += ewg.getGradeId() + ",";
        }
        String title = getTitle(schoolId, year, termCode, null);

        model.addAttribute("examWorksId", examWorksId);
        model.addAttribute("examWorks", examWorks);
        model.addAttribute("title", title);
        model.addAttribute("joinGrade", joinGrade);
        model.addAttribute("examGrades", JSONArray.fromObject(examWorksGradeList).toString());
        model.addAttribute("gradeList", gradeList);
        model.addAttribute("type", type);
        model.addAttribute("year", year);
        model.addAttribute("termCode", termCode);
        return new ModelAndView(structurePath("/exam/modifyPage"), model.asMap());
    }



    /**
     *  ????????????????????????
     */
    @RequestMapping(value = "/subjectPage")
    public ModelAndView toSubjectPage(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "gradeCode", required = false) String gradeCode,
            Model model) {
        String viewPath = "";
        Integer schoolId = user.getSchoolId();
        List<Object> list = new ArrayList<>();
        List<SubjectGrade> subjectList = subjectGradeService.findSubjectGradeByGradeCode(schoolId, gradeCode);
        //??????examWorksId????????????--????????????ews?????????????????????--????????????ewst?????????
        if (examWorksId != null) {

            List<ExamWorksSubject> subjects = examWorksSubjectService.findSubjectsOfExamWorks(examWorksId, gradeId);
            for (SubjectGrade subjectGrade : subjectList) {
                ExamWorksSubject examWorksSubject = null;
                for (ExamWorksSubject ews : subjects) {
                    if (subjectGrade.getSubjectCode().equals(ews.getSubjectCode())) {
                        examWorksSubject = ews;
                        break;
                    }
                }
                //????????????????????????/????????????????????? ???????????????????????????????????????????????????????????????
                Map<String, Object> map = new HashMap<>();
                map.put("subjectName", subjectGrade.getSubjectName());
                map.put("subjectCode", subjectGrade.getSubjectCode());
                if (examWorksSubject != null) {
                    map.put("fullScore", examWorksSubject.getFullScore());
                    map.put("highScore", examWorksSubject.getHighScore());
                    map.put("lowScore", examWorksSubject.getLowScore());
                    map.put("passScore", examWorksSubject.getPassScore());
                    map.put("isStat", examWorksSubject.getStatNeeded());
                } else {
                    map.put("fullScore", 100f);
                    map.put("highScore", 90f);
                    map.put("lowScore", 80f);
                    map.put("passScore", 60f);
                    map.put("isStat", false);
                }
                list.add(map);
            }
            String json = JSONArray.fromObject(list).toString();
            model.addAttribute("json", json);

            viewPath = structurePath("/exam/modifySubjectPage");
        } else {

            List<ExamWorksSubjectTemplate> subjectTemplates = examWorksSubjectTemplateService.acquireTemplateOfGrade(schoolId, gradeId, gradeCode);
            for (SubjectGrade subjectGrade : subjectList) {
                ExamWorksSubjectTemplate template = null;
                for (ExamWorksSubjectTemplate examWorksSubjectTemplate : subjectTemplates) {
                    if (subjectGrade.getSubjectCode().equals(examWorksSubjectTemplate.getSubjectCode())) {
                        template = examWorksSubjectTemplate;
                        break;
                    }
                }
                Map<String, Object> map = new HashMap<>();
                map.put("subjectName", subjectGrade.getSubjectName());
                map.put("subjectCode", subjectGrade.getSubjectCode());
                if (template != null) {
                    map.put("fullScore", template.getFullScore());
                    map.put("highScore", template.getHighScore());
                    map.put("lowScore", template.getLowScore());
                    map.put("passScore", template.getPassScore());
                    map.put("isStat", template.getStatNeeded());
                } else {
                    map.put("fullScore", 100f);
                    map.put("highScore", 90f);
                    map.put("lowScore", 80f);
                    map.put("passScore", 60f);
                    map.put("isStat", false);
                }
                list.add(map);
            }
            viewPath = structurePath("/exam/addSubjectPage");
        }

        model.addAttribute("list", list);
        model.addAttribute("examWorksId", examWorksId);
        model.addAttribute("gradeId", gradeId);
        model.addAttribute("gradeCode", gradeCode);
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * ??????????????????????????????
     */
    @RequestMapping(value = "/addExamWorksSubject")
    @ResponseBody
    public Object addExamWorksSubject(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "gradeCode", required = false) String gradeCode,
            @RequestParam(value = "subjectList", required = false) String subjectList
    ) {
        Integer schoolId = user.getSchoolId();
        try {
            if (subjectList != null && !"".endsWith(subjectList)) {
                JSONArray jsonArray = JSONArray.fromObject(subjectList);
                if (jsonArray != null && jsonArray.size() > 0) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String subjectCode = jsonObject.getString("subjectCode");
                        double fullScore = jsonObject.getDouble("fullScore");
                        double highScore = jsonObject.getDouble("highScore");
                        double lowScore = jsonObject.getDouble("lowScore");
                        double passScore = jsonObject.getDouble("passScore");
                        boolean statNeeded = jsonObject.getBoolean("statNeeded");

                        //??????????????????????????????????????????id????????????????????????
                        ExamWorksSubjectTemplate ewsj = examWorksSubjectTemplateService.findUnique(schoolId, gradeId, gradeCode, subjectCode);
                        ExamWorksSubjectTemplate template = new ExamWorksSubjectTemplate();
                        template.setFullScore((float) fullScore);
                        template.setHighScore((float) highScore);
                        template.setLowScore((float) lowScore);
                        template.setPassScore((float) passScore);
                        template.setStatNeeded(statNeeded);
                        if (ewsj != null) {
                            template.setId(ewsj.getId());
                            examWorksSubjectTemplateService.modify(template);
                        } else {
                            template.setSchoolId(schoolId);
                            template.setGradeCode(gradeCode);
                            template.setSubjectCode(subjectCode);
                            template.setIsDelteted(false);
                            examWorksSubjectTemplateService.add(template);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("????????????????????????");
            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }
        return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
    }

    /**
     * ??????????????????????????????
     * @return
     */
    @RequestMapping(value = "/modifyExamWorksSubject")
    @ResponseBody
    public Object modifyExamWorksSubject(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "gradeCode", required = false) String gradeCode,
            @RequestParam(value = "subjectList", required = false) String subjectList,
            @RequestParam(value = "statCount", required = false) Integer statCount
    ){

        Integer schoolId = user.getSchoolId();
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        try {
            if (subjectList != null && !"".endsWith(subjectList)) {
                //??????examWorksGrade ??? examWorksTeam ??????statSubjectCount??????????????????????????????
                List<Integer> teams = new ArrayList<>();
                List<String> subjects = new ArrayList<>();

                String jointExamCode = null;
                ExamWorksGrade examWorksGrade = examWorksGradeService.findUnique(examWorksId, gradeId);
                if (examWorksGrade != null) {
                    ExamWorksGrade ewg = new ExamWorksGrade(examWorksGrade.getId());
                    ewg.setStatSubjectCount(statCount);
                    examWorksGradeService.modify(ewg);
                    jointExamCode = examWorksGrade.getJointExamCode();
                }
                List<ExamWorksTeam> ewtList = examWorksTeamService.findOfExamWorks(examWorksId, gradeId);
                if (ewtList != null && ewtList.size() > 0) {
                    ExamWorksTeam ewt = null;
                    for (ExamWorksTeam examWorksTeam : ewtList) {
                        teams.add(examWorksTeam.getTeamId());
                        ewt = new ExamWorksTeam(examWorksTeam.getId());
                        ewt.setStatSubjectCount(statCount);
                        examWorksTeamService.modify(ewt);
                    }
                }
                PjExamCondition condition = new PjExamCondition();
                condition.setJointExamCode(jointExamCode);
                condition.setSchoolId(schoolId);
                condition.setGradeId(gradeId);
                condition.setIsDelete(false);

                JSONArray jsonArray = JSONArray.fromObject(subjectList);
                if (jsonArray != null && jsonArray.size() > 0) {
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String subjectCode = jsonObject.getString("subjectCode");
                        float fullScore = (float) jsonObject.getDouble("fullScore");
                        float highScore =(float) jsonObject.getDouble("highScore");
                        float lowScore = (float)jsonObject.getDouble("lowScore");
                        float passScore =(float) jsonObject.getDouble("passScore");
                        boolean statNeeded = jsonObject.getBoolean("statNeeded");
                        subjects.add(subjectCode);

                        ExamWorksSubject ews = examWorksSubjectService.findUnique(examWorksId, gradeId, subjectCode);
                        ExamWorksSubject examWorksSubject = new ExamWorksSubject();
                        examWorksSubject.setFullScore(fullScore);
                        examWorksSubject.setHighScore(highScore);
                        examWorksSubject.setLowScore(lowScore);
                        examWorksSubject.setPassScore(passScore);
                        examWorksSubject.setStatNeeded(statNeeded);
                        if (ews != null) {
                            examWorksSubject.setId(ews.getId());
                            examWorksSubjectService.modify(examWorksSubject);
                        } else {
                            examWorksSubject.setExamWorksId(examWorksId);
                            examWorksSubject.setSchoolId(schoolId);
                            examWorksSubject.setGradeId(gradeId);
                            examWorksSubject.setSubjectCode(subjectCode);
                            examWorksSubject.setIsDelteted(false);
                            examWorksSubjectService.add(examWorksSubject);
                        }

                        //?????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????exam_works_team_subject
                        ExamWorksSubjectTemplate template = examWorksSubjectTemplateService.findSubjectTemplate(schoolId, gradeId, subjectCode);
                        if (template == null) {
                            template = new ExamWorksSubjectTemplate();
                            template.setSchoolId(schoolId);
                            template.setGradeCode(gradeCode);
                            template.setSubjectCode(subjectCode);
                            template.setStatNeeded(statNeeded);
                            template.setFullScore(fullScore);
                            template.setHighScore(highScore);
                            template.setLowScore(lowScore);
                            template.setPassScore(passScore);
                            template.setIsDelteted(false);
                            examWorksSubjectTemplateService.add(template);
                        }
                        //?????????????????????????????????
                        if (examWorksGrade != null) {
                            initTeamSubject(user.getTeacherId(), examWorksId, gradeId, schoolId, examWorks, jointExamCode, examWorksGrade.getId(), subjectCode, fullScore, highScore, lowScore, passScore);
                        }

                        if (jointExamCode != null) {
                            condition.setSubjectCode(subjectCode);
                            List<PjExam> examList = pjExamService.findPjExamByCondition(condition);
                            List<ExamStat> examStatList = new ArrayList<>();
                            ExamStat examStat = null;
                            ExamStat es = null;
                            for (PjExam exam : examList) {
                                examStat = examStatService.findExamStatByExamId(exam.getId());
                                es = new ExamStat(examStat.getId());
                                es.setFullScore(fullScore);
                                es.setHighScore(highScore);
                                es.setLowScore(lowScore);
                                es.setPassScore(passScore);
                                examStatList.add(es);
                            }
                            examStatService.batchUpdateExamStat(examStatList);
                        }

                    }
                }

                reStatisticalScore(examWorksId,gradeId, teams, subjects);

            }
        } catch (Exception e) {
            e.printStackTrace();
            log.info("????????????????????????");
            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }

        return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
    }

    private void initTeamSubject(Integer teacherId, Integer examWorksId, Integer gradeId, Integer schoolId,
                                 ExamWorks examWorks, String jointExamCode, Integer examWorksGradeId, String subjectCode,
                                 float fullScore, float highScore, float lowScore, float passScore) {
        List<ExamWorksTeam> examWorksTeams = examWorksTeamService.findOfExamWorks(examWorksId, gradeId);
        if (examWorksTeams != null && examWorksTeams.size() > 0) {
            for (ExamWorksTeam examWorksTeam : examWorksTeams) {
                Integer teamId = examWorksTeam.getTeamId();

                ExamWorksTeamSubject unique = examWorksTeamSubjectService.findUnique(examWorksId, teamId, subjectCode);
                if (unique == null) {
                    List<TeamStudent> studentList = teamStudentService.findByTeamId(teamId);

                    PjExam exam = new PjExam();
                    exam.setSchoolId(schoolId);
                    exam.setGradeId(gradeId);
                    exam.setTeamId(teamId);
                    exam.setSubjectCode(subjectCode);
                    exam.setName(examWorks.getName());
                    exam.setExamType(examWorks.getExamType());
                    exam.setExamRound(1);
                    exam.setExamDate(new Date());
                    exam.setSchoolYear(examWorks.getSchoolYear());
                    exam.setTermCode(examWorks.getTermCode());
                    exam.setTermValue(examWorks.getTermCode().substring(examWorks.getTermCode().length() -1));
                    exam.setTeacherId(teacherId);
                    exam.setIsDelete(false);
                    exam.setCreateDate(new Date());
                    exam.setModifyDate(new Date());
                    exam.setJointExamCode(jointExamCode);
                    exam = pjExamService.add(exam);

                    Integer examId = exam.getId();
                    ExamWorksTeamSubject teamSubject = new ExamWorksTeamSubject();
                    teamSubject.setExamWorksId(examWorksId);
                    teamSubject.setExamWorksGradeId(examWorksGradeId);
                    teamSubject.setExamId(examId);
                    teamSubject.setSchoolId(schoolId);
                    teamSubject.setGradeId(gradeId);
                    teamSubject.setTeamId(teamId);
                    teamSubject.setSubjectCode(subjectCode);
                    teamSubject.setTeacherId(teacherId);
                    teamSubject.setIsDeleted(false);
                    teamSubject.setCreateDate(new Date());
                    teamSubject.setModifyDate(new Date());
                    examWorksTeamSubjectService.add(teamSubject);

                    ExamStudent[] examStudentList = new ExamStudent[studentList.size()];
                    for (int j = 0; j < studentList.size(); j++) {
                        ExamStudent examStudent = new ExamStudent();
                        examStudent.setExamId(examId);
                        examStudent.setStudentId(studentList.get(j).getStudentId());
                        examStudent.setUserId(studentList.get(j).getUserId());
                        examStudent.setNumber(studentList.get(j).getNumber());
                        examStudent.setName(studentList.get(j).getName());
                        examStudent.setTestType("01");
                        examStudent.setScore(-1.0f);
                        examStudent.setCreateDate(new Date());
                        examStudent.setModifyDate(new Date());
                        examStudentList[j] = examStudent;
                    }
                    examStudentService.createBatch(examStudentList);

                    ExamStat examStat = new ExamStat();
                    examStat.setExamId(examId);
                    examStat.setStudentCount(studentList.size());
                    examStat.setFullScore(fullScore);
                    examStat.setHighScore(highScore);
                    examStat.setLowScore(lowScore);
                    examStat.setPassScore(passScore);
                    examStat.setDataChanged(false);
                    examStat.setCreateDate(new Date());
                    examStat.setModifyDate(new Date());
                    examStatService.add(examStat);
                }
            }
        }
    }

    private void reStatisticalScore(final Integer examWorksId, final Integer gradeId, final List<Integer> teams, final List<String> subjects){
        if (teams != null && teams.size() > 0 && subjects != null && subjects.size() > 0){
            ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
            cachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for (Integer teamId : teams) {
                        for (String subjectCode : subjects) {
                            ExamWorksTeamSubject teamSubject = examWorksTeamSubjectService.findUnique(examWorksId, teamId, subjectCode);
                            scoreAnalysisHandleService.importGeneralExamScore(examWorksId, gradeId, teamId,subjectCode,null,null,null,teamSubject.getExamId());
                        }
                    }
                }
            });
        }
    }



    /**
     *  ??????????????????
     */
    @RequestMapping(value = "/addExamWorks")
    @ResponseBody
    public Object addExamWorks(
            @CurrentUser UserInfo user,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "round", required = false) Integer round,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "begin", required = false) String begin,
            @RequestParam(value = "end", required = false) String end,
            @RequestParam(value = "isShow", required = false) Boolean isShow,
            @RequestParam(value = "gradeIds", required = false) String gradeIds,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "fullScore", required = false) Float fullScore,
            @RequestParam(value = "highScore", required = false) Float highScore,
            @RequestParam(value = "lowScore", required = false) Float lowScore,
            @RequestParam(value = "passScore", required = false) Float passScore
    ){

        Integer schoolId = user.getSchoolId();
        Integer teacherId = user.getTeacherId();
        SimpleDateFormat sdf = null;
        try {
            if (!"20".equals(type)) {
                sdf = new SimpleDateFormat("yyyy-MM-dd");
                examWorksService.initJointExam(schoolId, year, termCode, type, round, name, sdf.parse(begin), sdf.parse(end), isShow, gradeIds, teacherId);
                return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
            } else {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                int examWorksId = examWorksService.initClassExam(schoolId, year, termCode, gradeId, teamId, subjectCode, type, round,
                        name, sdf.parse(begin), sdf.parse(end), isShow, fullScore, highScore, lowScore, passScore, teacherId);
                return new ResponseInfomation(examWorksId, ResponseInfomation.OPERATION_SUC);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseInfomation(ResponseInfomation.OPERATION_FAIL);
        }

    }

    /**
     *  ????????????????????????
     */
    @RequestMapping(value = "/modifyExamWorks")
    @ResponseBody
    public Object modifyExamWorks(
            @CurrentUser UserInfo user,
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "begin", required = false) String begin,
            @RequestParam(value = "end", required = false) String end,
            @RequestParam(value = "isShow", required = false) Boolean isShow,
            @RequestParam(value = "addGrades", required = false) String addGrades,
            @RequestParam(value = "delGrades", required = false) String delGrades,
            @RequestParam(value = "fullScore", required = false) Float fullScore,
            @RequestParam(value = "highScore", required = false) Float highScore,
            @RequestParam(value = "lowScore", required = false) Float lowScore,
            @RequestParam(value = "passScore", required = false) Float passScore
    ) throws ParseException {
        Integer schoolId = user.getSchoolId();
        Integer teacherId = user.getTeacherId();
        SimpleDateFormat sdf = null;
        if ("20".equals(type)) {
            //??????????????????
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            examWorksService.modifyClassExam(id, name, sdf.parse(begin), sdf.parse(end), fullScore, highScore, lowScore, passScore);
        } else {
            //????????????
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            ExamWorks examWorks = examWorksService.findExamWorksById(id);
            if (examWorks != null) {
                String[] gradeIds = null;
                if (addGrades != null && !"".equals(addGrades)) {
                    gradeIds = addGrades.split(",");
                    for (String gradeId : gradeIds) {
                        examWorksService.initJointExamOfGrade(schoolId, year, termCode, type, name, teacherId, id, Integer.valueOf(gradeId));
                    }
                }

                if (delGrades != null && !"".equals(delGrades)) {
                    gradeIds = delGrades.split(",");
                    for (String gradeId : gradeIds) {
                        examWorksService.deleteExamOfGrade(id, Integer.valueOf(gradeId));
                    }
                }
                examWorks = new ExamWorks(id);
                examWorks.setName(name);
                examWorks.setExamDateBegin(sdf.parse(begin));
                examWorks.setExamDateEnd(sdf.parse(end));
                examWorks.setShowRanking(isShow);
                examWorksService.modify(examWorks);

            }
        }

        return new ResponseInfomation(ResponseInfomation.OPERATION_SUC);
    }


    /**
     * ??????????????????
     */
    @RequestMapping(value = "/class/index")
    public ModelAndView classExamIndex(Model model) {
        return new ModelAndView(structurePath("/exam/classExamIndex"), model.asMap());
    }

    /**
     * ??????????????????
     */
    @RequestMapping(value = "/class/list")
    public Object classExamList(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model
    ){
        List<ExamWorksVo> list = examWorksService.findClassExamWorksByTeacherId(user.getSchoolId(), year, termCode, user.getTeacherId(), page, order);
        model.addAttribute("list", list);
        return new ModelAndView(structurePath("/exam/classExamList"), model.asMap());
//        ModelAndView mav = new ModelAndView();
//        mav.addObject("list", list);
//        mav.setViewName(structurePath("/classExamList"));
//        return mav;
    }

    /**
     * ??????/?????????????????????
     * type ????????????????????????????????????type=pc???pc?????????
     */
    @RequestMapping(value = "/class/addOrUpdate")
    public Object toAddClassExamPage(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "id", required = false) Integer id,
            Model model
    ){
        String viewPath = "";
        if (id != null) {
            ExamWorks examWorks = examWorksService.findExamWorksById(id);
            if (examWorks != null) {
                ExamWorksSubject worksSubject = examWorksSubjectService.findUnique(examWorks.getId(), examWorks.getGradeId(), examWorks.getSubjectCode());
                Team team = teamService.findTeamById(examWorks.getTeamId());
                if (team != null) {
                    model.addAttribute("teamName", team.getName());
                }
                Subject subject = subjectService.findUnique(examWorks.getSchoolId(), examWorks.getSubjectCode());
                if (subject != null) {
                    model.addAttribute("subjectName", subject.getName());
                }
                model.addAttribute("examWorks", examWorks);
                model.addAttribute("worksSubject", worksSubject);
            }
            viewPath = structurePath("/exam/modifyClassExamPage");
        } else {
            List<TeamTeacherVo> teamList = getTeacherTeams(user.getSchoolId(), year, user.getId(), user.getTeacherId(), null);
            model.addAttribute("teamList", teamList);
            //type=pc,?????????pc?????????????????????
            model.addAttribute("type", type);
            viewPath = structurePath("/exam/addClassExamPage");
        }
        model.addAttribute("year", year);
        model.addAttribute("termCode", termCode);
        return new ModelAndView(viewPath, model.asMap());
    }

    //????????????
    @RequestMapping(value = "/subject/list/json")
    @ResponseBody
    public Object getSubjectOfTeacher(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId
    ){
        return teamTeacherService.getTeacherTeachSubject(user.getId(), year, gradeId, teamId);
    }

    //????????????????????????
    @RequestMapping(value = "/list/json")
    @ResponseBody
    public Object getMajorExamWorks(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode
    ){
        return examWorksService.findMajorExamWorks(user.getSchoolId(), year, termCode);
    }

    //????????????
    @RequestMapping(value = "/subject/score/json")
    @ResponseBody
    public Object getSubjectScore(
            @CurrentUser UserInfo user,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "gradeId", required = false) Integer gradeId
    ){
        return examWorksSubjectTemplateService.findSubjectTemplate(user.getSchoolId(), gradeId, subjectCode);
    }

    //????????????????????????
    @RequestMapping(value = "/team/list/json")
    @ResponseBody
    public Object getTeamsOfTeacher(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "isMaster", required = false, defaultValue = "false") Boolean isMaster){
        return getTeacherTeams(user.getSchoolId(), year, user.getId(), user.getTeacherId(), isMaster);
    }

    /**
     * ??????????????????
     */
    @RequestMapping(value = "/class/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public Object getSubjectScore(@RequestParam(value = "id", required = true) Integer id){
        try {
            examWorksService.deleteClassExam(id);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }



    /**
     *  ????????????--??????????????????
     */
    @RequestMapping(value = "/manage/index")
    public ModelAndView toManageIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            Model model){
        String viewPath = "";
        List<ExamWorks> list = examWorksService.findMajorExamWorks(user.getSchoolId(), user.getSchoolYear(), user.getSchoolTermCode());
        model.addAttribute("list", list);
        model.addAttribute("category", 0);
        model.addAttribute("year", year);
        model.addAttribute("termCode", termCode);
        model.addAttribute("examWorksId", examWorksId);
        model.addAttribute("gradeId", gradeId);
        model.addAttribute("teamId", teamId);
        viewPath = structurePath("/manage/index");
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     *  ????????????--??????????????????(??????)
     */
    @RequestMapping(value = "/manage/teacher/index")
    public ModelAndView toTeacherManageIndex(
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @CurrentUser UserInfo user,
            Model model){
        String viewPath = "";
        List<ExamWorks> list = examWorksService.findMajorExamWorks(user.getSchoolId(), user.getSchoolYear(), user.getSchoolTermCode());
        List<TeamTeacherVo> teamList = getTeacherTeams(user.getSchoolId(), user.getSchoolYear(), user.getId(), user.getTeacherId(), true);
        model.addAttribute("list", list);
        model.addAttribute("teamList", teamList);
        model.addAttribute("category", 1);
        model.addAttribute("year", year);
        model.addAttribute("termCode", termCode);
        model.addAttribute("examWorksId", examWorksId);
        model.addAttribute("teamId", teamId);
        viewPath = structurePath("/manage/index1");
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * ????????????--??????????????????
     * isMain ???????????????????????????????????????????????????????????????
     */
    @RequestMapping(value = "/manage/class/index")
    public ModelAndView toManageClassIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "isMain", required = false, defaultValue = "true") Boolean isMain,
            Model model){
        String viewPath = "";
        List<TeamTeacherVo> teamList = getTeacherTeams(user.getSchoolId(), user.getSchoolYear(), user.getId(), user.getTeacherId(), null);
        int category = 3;
        if (isMain) {
            category = 2;
        }
        model.addAttribute("teamList", teamList);
        model.addAttribute("isMain", isMain);
        model.addAttribute("category", category);
        model.addAttribute("year", year);
        model.addAttribute("termCode", termCode);
        model.addAttribute("teamId", teamId);
        viewPath = structurePath("/manage/index_teacher");
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * ????????????--?????????????????????
     */
    @RequestMapping(value = "/manage/list")
    public Object majorExamWorkListOfTeam(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "isManager", required = false, defaultValue = "true") Boolean isManager,
            Model model
    ) {
        List<Map<String, Object>> list = null;
        boolean isPublish = false;
        if (examWorksId != null && teamId != null) {
            list = examWorksTeamSubjectService.findMajorExamWorksByTeam(examWorksId, teamId);
            ExamWorksTeam examWorksTeam = examWorksTeamService.findUnique(examWorksId, teamId);
            if (examWorksTeam != null && examWorksTeam.getPublishTime() != null) {
                isPublish = true;
            }
        }
        if (!isManager) {
           TeamTeacherCondition condition = new TeamTeacherCondition();
           condition.setUserId(user.getId());
           condition.setTeamId(teamId);
            List<TeamTeacher> teamList = teamTeacherService.findTeamTeacherByCondition(condition, null, null);
            boolean isMaster = false;
            String subjects = "";
            if (teamList != null && teamList.size() > 0) {
                for (TeamTeacher teamTeacher : teamList) {
                    if (teamTeacher.getType() == 1) {
                        isMaster = true;
                    } else {
                        subjects += "," + teamTeacher.getSubjectCode();
                    }
                }
                if (subjects.length() > 0) {
                    subjects = subjects.substring(1);
                }
            }
            model.addAttribute("isMaster", isMaster);
            model.addAttribute("subjects", subjects);
        }
        model.addAttribute("list", list);
        model.addAttribute("isPublish", isPublish);
        model.addAttribute("isManager", isManager);
        return new ModelAndView(structurePath("/manage/list"), model.asMap());
    }

    @RequestMapping(value = "/manage/class/list")
    public Object classExamWorkListOfTeam(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model
    ){
        List<Map<String, Object>> list = null;
        if (teamId != null) {
            list = examWorksTeamSubjectService.findClassExamWorksByTeam(
                    user.getSchoolId(), year, termCode, user.getTeacherId(), teamId, page, null);
        }
        model.addAttribute("list", list);
        return new ModelAndView(structurePath("/manage/list_teacher"), model.asMap());
    }

    /**
     * ????????????
     * examWorksId, teamId, ewtsId ??????web????????????
     * examWorksId, teamId, subjectCode ??????pc?????????,???????????????????????????,subjectCode????????????????ticket=***?????????????????????
     * type?????????????????????  ???????????????????????????=report?????????????????????=pcReport??????pc??????????????????
     * year,termCode,examWorksId,gradeId,teamId  ????????????????????????????????????????????????--???????????????map?????????
     * category ??????????????????????????? 0=????????????(?????????)???1=????????????(??????) 2=????????????(?????????) 3=2=????????????(??????)
     * isMain ???????????????????????????????????????????????????
     */
    @RequestMapping(value = "/manage/achievement")
    public Object toAchievementPage(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "ewtsId", required = false) Integer ewtsId,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "category", required = false) Integer category,
            @RequestParam(value = "isMain", required = false, defaultValue = "true") Boolean isMain,
            Model model
    ) {
        String viewPath = "";
        if (subjectCode != null && !"".equals(subjectCode) && subjectCode.indexOf("?") != -1) {
            subjectCode= subjectCode.substring(0, subjectCode.indexOf("?"));
        }
        if (ewtsId == null) {
            ExamWorksTeamSubject teamSubject = examWorksTeamSubjectService.findUnique(examWorksId, teamId, subjectCode);
            ewtsId = teamSubject != null ? teamSubject.getId() : null;
        }
        Map<String, Object> map = examWorksTeamSubjectService.findMajorExamWorksByTeam(examWorksId, teamId, ewtsId);
        String schoolYear = (String) map.get("schoolYear");
        String termCode = (String) map.get("termCode");
        String examType = (String) map.get("examType");
        Integer examId = (Integer) map.get("examId");

        String title = getTitle(user.getSchoolId(), schoolYear, termCode, examType);
        String teamName = "";
        Team team = teamService.findTeamById(teamId);
        if (team != null) {
            teamName = team.getName();
        }
        List<ExamStudent> studentList = examStudentService.findExamStudentsByExamId(examId, Order.desc("score"));

        viewPath = structurePath("/manage/achievement");
        if ("report".equals(type) || "pcReport".equals(type)) {
            Float highScore = (Float) map.get("highScore");
            Float lowScore = (Float) map.get("lowScore");
            Float passScore = (Float) map.get("passScore");
            Integer high = 0;
            Integer low = 0;
            Integer pass = 0;
            Integer noPass = 0;
            for (ExamStudent student : studentList) {
                Float score = student.getScore();
                if (score != -1) {
                    if (score >= highScore) {
                        high++;
                    } else if (score >= lowScore) {
                        low++;
                    } else if (score >= passScore) {
                        pass++;
                    } else {
                        noPass++;
                    }
                }
            }
            model.addAttribute("high", high);
            model.addAttribute("low", low);
            model.addAttribute("pass", pass);
            model.addAttribute("noPass", noPass);
            model.addAttribute("type", type);
            viewPath = structurePath("/manage/report");
        } else {
            //?????????????????????????????????????????????
            boolean isShow = false;
            if (category == 1) {
                TeamTeacherCondition condition = new TeamTeacherCondition();
                condition.setUserId(user.getId());
                condition.setTeamId(teamId);
                List<TeamTeacher> teamList = teamTeacherService.findTeamTeacherByCondition(condition, null, null);
                if (teamList != null && teamList.size() > 0) {
                    String sc = (String) map.get("subjectCode");
                    for (TeamTeacher teamTeacher : teamList) {
                        if (teamTeacher.getType() == 1){
                            isShow = true;
                        } else if (sc.equals(teamTeacher.getSubjectCode())){
                            isShow = true;
                        }
                    }
                }
            } else {
                isShow = true;
            }
            model.addAttribute("isShow", isShow);
        }
        model.addAttribute("title", title);
        model.addAttribute("teamName", teamName);
        model.addAttribute("map", map);
        model.addAttribute("studentList", studentList);

        model.addAttribute("category", category);
        model.addAttribute("isMain", isMain);
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * ????????????--????????????
     */
    @RequestMapping(value = "/manage/publish")
    @ResponseBody
    public Object publishReport(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "ids", required = false) String ids){
        try {
            if (ids != null && !"".equals(ids)) {
                String[] ewtsIds = ids.split(",");
                int length = ewtsIds.length;
                for (String id : ewtsIds) {
                    ExamWorksTeamSubject teamSubject = new ExamWorksTeamSubject(Integer.valueOf(id));
                    teamSubject.setPublishTeacherId(user.getTeacherId());
                    teamSubject.setPublishTime(new Date());
                    examWorksTeamSubjectService.modify(teamSubject);
                }
                if (examWorksId != null) {
                    ExamWorksTeam worksTeam = examWorksTeamService.findUnique(examWorksId, teamId);
                    if (worksTeam != null) {
                        Integer gradeId = worksTeam.getGradeId();
                        worksTeam = new ExamWorksTeam(worksTeam.getId());
                        worksTeam.setFinishedSubjectCount(length);
                        worksTeam.setPublishTeacherId(user.getTeacherId());
                        worksTeam.setPublishTime(new Date());
                        examWorksTeamService.modify(worksTeam);


                        //??????exam_works_grade??????finishedTeamCount???statStudentCount
                        Long teamCount = examWorksTeamService.countPublishTeam(examWorksId, gradeId);
                        Long studentCount = examWorksTeamService.countPublishStudent(examWorksId, gradeId);

                        ExamWorksGrade worksGrade = examWorksGradeService.findUnique(examWorksId, gradeId);
                        worksGrade = new ExamWorksGrade(worksGrade.getId());
                        worksGrade.setFinishedTeamCount(Integer.valueOf(String.valueOf(teamCount)));
                        worksGrade.setStatStudentCount(Integer.valueOf(String.valueOf(studentCount)));
                        worksGrade.setLastPublishTime(new Date());
                        examWorksGradeService.modify(worksGrade);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseInfomation.OPERATION_FAIL;
        }
        return ResponseInfomation.OPERATION_SUC;
    }

    private List<TeamTeacherVo> getTeacherTeams(Integer schoolId, String schoolYear, Integer userId, Integer teacherId, Boolean isMaster){
        List<TeamTeacherVo> teamList = null;
        if (isMaster != null && isMaster) {
            TeamTeacherCondition condition = new TeamTeacherCondition();
            condition.setSchoolId(schoolId);
            condition.setSchoolYear(schoolYear);
            condition.setUserId(userId);
            condition.setTeacherId(teacherId);
            teamList = teamTeacherService.findVoWithSubjectInfo(condition);
        } else {
            teamList = teamTeacherService.findTeamOrGradeOfTeacher(schoolId, schoolYear, userId, teacherId, 2, "2");
        }
        return teamList;
    }


    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }
}
