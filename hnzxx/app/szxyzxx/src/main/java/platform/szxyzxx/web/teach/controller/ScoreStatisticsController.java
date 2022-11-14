package platform.szxyzxx.web.teach.controller;

import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import net.sf.json.JSONArray;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.szxyzxx.web.common.annotation.CurrentUser;
import platform.szxyzxx.web.common.controller.base.BaseController;
import platform.szxyzxx.web.common.vo.UserInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 成绩分析--成绩统计
 */
@Controller
@RequestMapping("/teach/scoreStatistics")
public class ScoreStatisticsController extends BaseController {

    private final static String viewBasePath = "/teach/scoreStatistics";

    private ObjectMapper mapper = new ObjectMapper();

    private DecimalFormat df = new DecimalFormat("0.00");

    @Autowired
    @Qualifier("examWorksService")
    private ExamWorksService examWorksService;

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
    @Qualifier("examWorksSubjectService")
    private ExamWorksSubjectService examWorksSubjectService;

    @Autowired
    @Qualifier("examStatMajorStudentService")
    private ExamStatMajorStudentService examStatMajorStudentService;

    @Autowired
    @Qualifier("scoreAnalysisHandleService")
    private ScoreAnalysisHandleService scoreAnalysisHandleService;
    
    @Autowired
    @Qualifier("baseRedisCache")
    private BaseRedisCache<Object> baseRedisCache;


    /**
     * 成绩统计主页
     * 根据type进入学生成绩、考务成绩页
     *
     * @param model
     * @param type
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(Model model, @RequestParam(value = "type", required = false) String type) {
        String viewPath = structurePath("/index");
        if ("student".equals(type)) {
            viewPath = structurePath("/stu_search");
        } else if ("exam".equals(type)) {
            viewPath = structurePath("/kw_nj");
        }
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * 获取全部年级
     */
    @RequestMapping(value = "/team/list/json")
    @ResponseBody
    public Object getAllTeam(@CurrentUser UserInfo user, @RequestParam(value = "year", required = false) String year) {
        return teamService.findAllTeamOfSchool(user.getSchoolId(), year);
    }

    /**
     * 学生查询结果页
     *
     * @param type  根据类型用不同条件查询
     * @param name
     * @param model
     * @return
     */
    @RequestMapping(value = "/student/list")
    public ModelAndView getStudentList(
            @CurrentUser UserInfo user,
            @RequestParam(value = "type", required = false) String type,
            @RequestParam(value = "teamId", required = false) String id,
            @RequestParam(value = "name", required = false) String name,
            HttpServletRequest request,
            Model model) throws UnsupportedEncodingException {
        int count = 0;
        if ("single".equals(type)) {
//            name = new String(request.getParameter("name").getBytes("iso-8859-1"), "utf-8");
            List<StudentVo> in = new ArrayList<>();
            List<StudentVo> out = new ArrayList<>();
            if (name != null && !"".equals(name) && !"".equals(name.trim())) {
                name = name.trim();
                List<StudentVo> list = studentService.findByTeamOrName(user.getSchoolId(), user.getSchoolYear(), null, name);
                if (list != null && list.size() > 0) {
                    for (StudentVo vo : list) {
                        if ("07".equals(vo.getStudyState())) {
                            out.add(vo);
                        } else {
                            in.add(vo);
                        }
                    }
                }
            }
            count = in.size() + out.size();
            model.addAttribute("in", in);
            model.addAttribute("out", out);

        } else if ("team".equals(type)) {
            String title = "";
            if (id != null && !"".equals(id)) {
                int teamId = Integer.parseInt(id);
                Team team = teamService.findTeamById(teamId);
                if (team != null) {
                    SchoolYear schoolYear = schoolYearService.findByYearAndSchoolId(user.getSchoolId(), team.getSchoolYear());
                    title = schoolYear.getName() + " " + team.getName();
                    List<StudentVo> list = studentService.findByTeamOrName(user.getSchoolId(), null, teamId, null);
                    count = list.size();
                    model.addAttribute("title", title);
                    model.addAttribute("list", list);
                }
            }
        }
        model.addAttribute("type", type);
        model.addAttribute("count", count);
        return new ModelAndView(structurePath("/stu_search_list"), model.asMap());
    }


    /**
     * 学生成绩查看
     */
    @RequestMapping(value = "/student/exam")
    public ModelAndView toStudentExamPage(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model) {
        String viewPath = null;
        if ("list".equals(sub)) {
            viewPath = structurePath("/stu_exam_list");
            List<ExamWorksVo> list = examWorksService.findExamWorksOfStudent(user.getSchoolId(), year, termCode, userId, page, null);
            model.addAttribute("list", list);
        } else {
            viewPath = structurePath("/stu_exam");
        }
        Student student = studentService.findStudentByUserId(userId);
        if (student != null) {
            model.addAttribute("name", student.getName());
        }
        model.addAttribute("userId", userId);
        return new ModelAndView(viewPath, model.asMap());
    }


    //*********************************************

    /**
     * 考务信息页
     *
     * @param year
     * @param termCode
     * @param isJoint
     * @param sub
     * @param page
     * @param order
     * @param model
     * @return
     */
    @RequestMapping(value = "/exam/index")
    public ModelAndView toExamWorksIndex(
            @CurrentUser UserInfo user,
            @RequestParam(value = "year", required = false) String year,
            @RequestParam(value = "termCode", required = false) String termCode,
            @RequestParam(value = "isJoint", required = false, defaultValue = "true") Boolean isJoint,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "sub", required = false) String sub,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model) {
        String viewPath = null;
        if ("list".equals(sub)) {
            viewPath = structurePath("/kw_list");
            List<ExamWorks> list = null;
            if (isJoint != null && isJoint) {
                list = examWorksService.findExamWorksByType(user.getSchoolId(), year, termCode, isJoint, null, page, null);
            } else {
                list = examWorksService.findExamWorksByType(user.getSchoolId(), year, termCode, isJoint, teamId, page, null);
            }
            model.addAttribute("list", list);
        } else {
//            viewPath = structurePath("/kw_index");
            if (isJoint != null && isJoint) {
                viewPath = structurePath("/kw_nj");
            } else {
                viewPath = structurePath("/kw_bj");
            }
        }
        model.addAttribute("isJoint", isJoint);
        return new ModelAndView(viewPath, model.asMap());
    }

    /**
     * 考务年级
     *
     * @param user
     * @param examWorksId
     * @param model
     * @return
     */
    @RequestMapping(value = "/exam/grade")
    public ModelAndView toExamWorksGrade(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            Model model) {
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        if (examWorks != null) {
            String title = getTitle(user.getSchoolId(), examWorks.getSchoolYear(), examWorks.getTermCode());
            List<Map<String, Object>> gradeList = examWorksGradeService.findOfExamWorksWithScore(examWorksId);
            List<Map<String, Object>> list = new ArrayList<>();
            for (Map<String, Object> map : gradeList) {
                Integer gradeId = (Integer) map.get("gradeId");
                List<Map<String, Object>> teamList = examWorksTeamService.findOfExamWorksWithScore(examWorksId, gradeId);
                map.put("teamList", teamList);
                list.add(map);
            }
            model.addAttribute("title", title);
            model.addAttribute("list", list);
            model.addAttribute("examWorks", examWorks);
        }
        return new ModelAndView(structurePath("/kw_tk_nj"), model.asMap());
    }

    /**
     * 考务班级
     *
     * @param user
     * @param examWorksId
     * @param gradeId
     * @param model
     * @return
     */
    @RequestMapping(value = "/exam/team")
    public ModelAndView toExamWorksTeam(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            Model model) {
        List<Map<String, Object>> list = examWorksTeamService.findOfExamWorksWithScore(examWorksId, gradeId);
        model.addAttribute("list", list);
        model.addAttribute("examWorksId", examWorksId);
        return new ModelAndView(structurePath("/kw_tk_bj"), model.asMap());
    }

    /**
     * 小测学生成绩
     */
    @RequestMapping(value = "/exam/student")
    public ModelAndView toExamWorksStudetn(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            Model model) {
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        if (examWorks != null) {
            String title = getTitle(user.getSchoolId(), examWorks.getSchoolYear(), examWorks.getTermCode());
            Team team = teamService.findTeamById(teamId);
            Subject subject = subjectService.findUnique(examWorks.getSchoolId(), examWorks.getSubjectCode());
            ExamWorksTeamSubject unique = examWorksTeamSubjectService.findUnique(examWorksId, teamId, examWorks.getSubjectCode());
            List<ExamStudent> studentList = examStudentService.findExamStudentsByExamId(unique.getExamId(), Order.desc("score"));

            model.addAttribute("teamName", team.getName());
            model.addAttribute("subjectName", subject.getName());
            model.addAttribute("title", title);
            model.addAttribute("examWorks", examWorks);
            model.addAttribute("list", studentList);
        }
        return new ModelAndView(structurePath("/kw_xc_score"), model.asMap());
    }

    /**
     * 学生个人分析
     * @param user
     * @param examWorksId
     * @param userId
     * @param teamId
     * @param subjectCode
     * @param sub   统考页面跳转 0=综合 1=单科 2=趋势
     * @param backSign  用于返回不用的页面 1=考务 2=年级 3=班级
     */
    @RequestMapping(value = "/analysis/student")
    public ModelAndView toStudentAnalysis(
            @CurrentUser UserInfo user,
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "backSign", required = false) String backSign,
            Model model) throws IOException {
        String viewPath = null;
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        if (examWorks != null) {
            if (examWorks.getIsJointExam()) {
                String title = getTitle(examWorks.getSchoolId(), examWorks.getSchoolYear(), examWorks.getTermCode());
                Student student = studentService.findStudentByUserId(userId);
                model.addAttribute("title", title);
                model.addAttribute("studentName", student.getName());
                model.addAttribute("examWorks", examWorks);

                Team team = teamService.findTeamById(teamId);
                ExamWorksGrade examWorksGrade = examWorksGradeService.findUnique(examWorksId, team.getGradeId());
                String jointExamCode = examWorksGrade.getJointExamCode();
                model.addAttribute("gradeId", team.getGradeId());

                //综合分析
                if ("0".equals(sub) || "".equals(sub) || sub == null) {
                    viewPath = structurePath("/tk/tk_single_analysis");

                    Integer gradeStudentCount = 0;  //年级人数
                    Integer teamStudentCount = 0;   //班级人数
                    Float teamTotalScore = 0f;      //班级所有人总分
                    List<Object> teamTotalScoreList = new ArrayList<>();       //用于总分分数段图
                    List<ExamStatMajorStudent> majorStudentList = this.examStatMajorStudentService.findExamStatMajorCount(examWorksId, jointExamCode, team.getId(), 1);
                    gradeStudentCount = examWorksGrade.getExamStudentCount();
                    if (majorStudentList != null && majorStudentList.size() > 0) {
                        teamStudentCount = majorStudentList.size();
                        for (ExamStatMajorStudent majorStudent : majorStudentList) {
                            if (majorStudent.getTotalScore() != null) {
                                teamTotalScore += majorStudent.getTotalScore();
                                teamTotalScoreList.add(majorStudent.getTotalScore());
                            }
                        }
                    } else {
                        //未导入成绩的
                        model.addAttribute("isPublished", false);
                    }

                    //全科
                    ExamStatMajorStudent majorStudent = examStatMajorStudentService.findExamStatMajorStudentByExamWorksIdAndStudentId(examWorksId, student.getId());
                    Map<String,Object> compositeMap = new HashMap<>();
                    if (majorStudent != null) {
                        compositeMap.put("totalScore", majorStudent.getTotalScore() != null ? majorStudent.getTotalScore() : 0);
                        compositeMap.put("teamRank", majorStudent.getTeamRank());
                        compositeMap.put("gradeRank", majorStudent.getGradeRank());
                        compositeMap.put("teamRankChange", majorStudent.getTeamRankChange());
                        compositeMap.put("gradeRankChange", majorStudent.getGradeRankChange());
                        compositeMap.put("allSubjectTotalScore", "--");
                        compositeMap.put("teamStudentCount", teamStudentCount);
                        compositeMap.put("gradeStudentCount", gradeStudentCount);
                        compositeMap.put("studentName", student.getName());
//                    model.addAttribute("compositeMap", compositeMap);
                    }
                    //单科
                    List<Map<String,Object>> userSubjectList = this.scoreAnalysisHandleService.findUserExamTeamSubjectScore(examWorksId, userId);
                    model.addAttribute("userSubjectList", userSubjectList);

                    //各学科得分雷达图  --  分数对比图
                    List<Object> subjectNameList = new ArrayList<>();       //科目名称
                    List<Object> userScoreList = new ArrayList<>();         //个人得分
                    List<Object> teamAvgScoreList = new ArrayList<>();      //班级平均分
                    List<Object> gradeAvgScoreList = new ArrayList<>();     //年级平均分
                    List<Object> gradeMaxScoreList = new ArrayList<>();     //年级最高分
                    Float gradeSubjectTotalScore = 0f;   //单科年级总分
                    float allSubjectTotalScore = 0;      //所有科目总分
                    if (userSubjectList != null && userSubjectList.size() > 0) {
                        for (Map<String, Object> objectMap : userSubjectList) {
                            subjectNameList.add(objectMap.get("subjectName"));
                            userScoreList.add(objectMap.get("score"));
                            teamAvgScoreList.add(objectMap.get("average_score"));
                            gradeSubjectTotalScore = scoreAnalysisHandleService.findGradeTotalScore(jointExamCode, (String) objectMap.get("subjectCode"));
//                            gradeSubjectTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(jointExamCode, (String) objectMap.get("subjectCode"));
                            Integer studentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(jointExamCode, (String) objectMap.get("subjectCode"));  //单科考试人数
                            if (gradeSubjectTotalScore != null && studentCount != null && studentCount != 0) {
                                gradeAvgScoreList.add(df.format((float)gradeSubjectTotalScore / studentCount));
                            } else {
                                gradeAvgScoreList.add(0);
                            }
                            Float maxScore = examWorksTeamSubjectService.findGradeMaxScore(jointExamCode, (String) objectMap.get("subjectCode"));
                            gradeMaxScoreList.add(maxScore);
                            allSubjectTotalScore += (float)objectMap.get("full_score");
                        }
                    }
                    compositeMap.put("allSubjectTotalScore", allSubjectTotalScore);
                    model.addAttribute("compositeMap", compositeMap);
                    model.addAttribute("subjectNameList", mapper.writeValueAsString(subjectNameList));
                    model.addAttribute("userScoreList", userScoreList);
                    model.addAttribute("teamAvgScoreList", teamAvgScoreList);
                    model.addAttribute("gradeAvgScoreList", gradeAvgScoreList);
                    model.addAttribute("gradeMaxScoreList", gradeMaxScoreList);

                    //年级名次分布图
                    List<Object> gradeRankList = new ArrayList<>();     //年级名次
                    List<Float> totalScoreList = new ArrayList<Float>();    //年级每个人总分
                    Float gradeTotalScore = 0f;             //年级总分
                    Float gradeAvgScore = 0f;               //年级平均分
                    int gradeAvgRank = 0;                   //年级平均分名次
                    float gradeMaxScore = 0;                //年级最高分
                    List<ExamStatMajorStudent> statMajorStudentList = this.examStatMajorStudentService.findExamStatMajorCount(examWorksId, jointExamCode, null, 1);
                    for (ExamStatMajorStudent statMajorStudent : statMajorStudentList) {
                        if (statMajorStudent.getStudentId() != null) {
                            if (student.getId() == statMajorStudent.getStudentId().intValue()) {
                                gradeRankList.add("{marker: {radius: 8},y:" + statMajorStudent.getGradeRank() + ",color:'#f18441'}");
                            } else {
                                gradeRankList.add(statMajorStudent.getGradeRank());
                            }
                        }

                        if (statMajorStudent.getTotalScore() != null) {
                            gradeTotalScore += statMajorStudent.getTotalScore();
                            totalScoreList.add(statMajorStudent.getTotalScore());
                            if (statMajorStudent.getTotalScore() > gradeMaxScore) {
                                gradeMaxScore = statMajorStudent.getTotalScore();
                            }
                        }
                    }
                    Integer examGradeStudentCount = 0;
                    if (statMajorStudentList != null) {
                        examGradeStudentCount = statMajorStudentList.size();
                    }
                    if (examGradeStudentCount != null && examGradeStudentCount != 0) {
                        gradeAvgScore = gradeTotalScore / examGradeStudentCount;
                        float result = this.teamAvgRank(totalScoreList, gradeAvgScore);
                        ExamStatMajorStudentCondition ees = new ExamStatMajorStudentCondition();
                        ees.setExamWorksId(examWorksId);
                        ees.setJointExamCode(examWorksGrade.getJointExamCode());
                        ees.setTotalScore(result);
                        ees.setIsDeleted(false);
                        List<ExamStatMajorStudent> list = this.examStatMajorStudentService.findExamStatMajorStudentByCondition(ees);
                        if (list != null && list.size() > 0) {
                            gradeAvgRank = list.get(0).getGradeRank();
                        }
                    }
                    model.addAttribute("gradeAvgRank", gradeAvgRank);
                    model.addAttribute("gradeRankList", gradeRankList);

                    //总分对比图
                    Map<String,Object> totalComparisonMap = new HashMap<>();
                    totalComparisonMap.put("gradeAvgScore", df.format(gradeAvgScore));
                    totalComparisonMap.put("gradeMaxScore", gradeMaxScore);
                    if (teamTotalScore != null && teamStudentCount != 0) {
                        totalComparisonMap.put("teamAvgScore", df.format(teamTotalScore / teamStudentCount));
                    }
                    model.addAttribute("totalComparisonMap", totalComparisonMap);

                    //总分分数段图
                    model.addAttribute("teamTotalScoreList", teamTotalScoreList);
                    model.addAttribute("gradeTotalScoreList", totalScoreList);

                }else if ("1".equals(sub)) {
                    viewPath = structurePath("/tk/tk_single_dk");

                    List<Map<String, Object>> subjectList = examWorksSubjectService.findStatSubjects(examWorksId, examWorksGrade.getGradeId(), null, true);
                    //初次进入默认选中第一个
                    if (subjectCode == null || "".equals(subjectCode)) {
                        subjectCode = (String) subjectList.get(0).get("subjectCode");
                    }
                    model.addAttribute("subjectList", subjectList);
                    model.addAttribute("subjectCode", subjectCode);

                    //得分概况
                    List<Map<String,Object>> userExamWorks = scoreAnalysisHandleService.findExamWorksByUserId(examWorksId, userId,subjectCode, null);
                    if (userExamWorks != null && userExamWorks.size() > 0) {
                        model.addAttribute("userExamWork", userExamWorks.get(0));
                    }

                    float gradeAvgScore = 0;    //年级单科平均分
                    Float gradeSubjectTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(jointExamCode, subjectCode);
                    Integer gradeStudentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(jointExamCode, subjectCode);
                    if (gradeSubjectTotalScore != null && gradeStudentCount != null && gradeStudentCount != 0) {
                        gradeAvgScore = gradeSubjectTotalScore / gradeStudentCount;
                    }
                    model.addAttribute("gradeAvgScore", df.format(gradeAvgScore));

                    //年级名次分布图
                    List<Float> gradeScoreList = new ArrayList<>();
                    List<Object> gradeRankList = new ArrayList<>();
                    List<Map<String, Object>> gradeRanks = examWorksTeamSubjectService.findGradeRankBySubjectCode(team.getGradeId(), subjectCode, examWorksId);
                    if (gradeRanks != null && gradeRanks.size() > 0) {
                        for (Map<String, Object> objectMap : gradeRanks) {
                            Integer user_id = (Integer) objectMap.get("user_id");
                            Integer gradeRank = (Integer) objectMap.get("grade_rank");
                            Float score = (Float) objectMap.get("score");
                            if (user_id != null) {
                                if (userId == user_id.intValue()) {
                                    gradeRankList.add("{marker: {radius: 8},y:" + gradeRank + ",color:'#f18441'}");
                                } else {
                                    gradeRankList.add(gradeRank);
                                }
                                gradeScoreList.add(score);
                            }
                        }
                    }
                    model.addAttribute("gradeRankList", gradeRankList);
                    model.addAttribute("gradeScoreList", gradeScoreList);

                    int gradeAvgRank = 0;       //平均分名次
                    float result = teamAvgRank(gradeScoreList, gradeAvgScore);
                    List<ExamStudent> list = this.examStudentService.findGradeRankByScoreJointCode(result,examWorksGrade.getJointExamCode(), subjectCode);
                    if (list != null && list.size() > 0) {
                        gradeAvgRank = list.get(0).getGradeRank();
                    }
                    model.addAttribute("gradeAvgRank", gradeAvgRank);

                } else if ("2".equals(sub)) {
                    viewPath = structurePath("/tk/tk_single_qs");

                    List<Map<String, Object>> subjectList = examWorksSubjectService.findStatSubjects(examWorksId, examWorksGrade.getGradeId(), null, true);
                    if (subjectCode == null || "".equals(subjectCode)) {
                        subjectCode = (String) subjectList.get(0).get("subjectCode");
                    }
                    model.addAttribute("subjectList", subjectList);
                    model.addAttribute("subjectCode", subjectCode);

                    List<Object> titleList = new ArrayList<>();             //考试名称
                    List<Object> userScoreList = new ArrayList<>();         //个人分数
                    List<Object> teamAvgScoreList = new ArrayList<>();       //班级平均分
                    List<Object> gradeAvgScoreList = new ArrayList<>();     //年级平均分
                    List<Object> userTeamRankList = new ArrayList<>();     //班级名次
                    List<Object> userGradeRankList = new ArrayList<>();    //年级名次
                    List<Map<String,Object>> scoreTrendList = scoreAnalysisHandleService.findUserExamWorksBySubjectCodeAndUserId(1, subjectCode, teamId, userId, null);
                    List<TeamStudent> teamStudentList = teamStudentService.findByTeamId(teamId);
                    Integer teamStuCount = teamStudentList != null ? teamStudentList.size() : 0;
                    Integer gradeStuCount = examWorksGrade.getExamStudentCount();
                    if (scoreTrendList != null && scoreTrendList.size() > 0) {
                        for (Map<String, Object> objectMap : scoreTrendList) {
                            //若没有分数，默认为0
                            titleList.add(objectMap.get("name"));
                            userScoreList.add((Float) objectMap.get("score") != -1 ? (Float) objectMap.get("score") : 0);
                            teamAvgScoreList.add(objectMap.get("average_score") != null && (Float) objectMap.get("average_score") != -1 ? (Float) objectMap.get("average_score") : 0);
                            //若没有排名，默认最后一名
                            userTeamRankList.add(objectMap.get("team_rank") != null ? (Integer) objectMap.get("team_rank") : teamStuCount);
                            userGradeRankList.add(objectMap.get("grade_rank") != null ? (Integer) objectMap.get("grade_rank") : gradeStuCount);

                            Integer exam_works_id = (Integer) objectMap.get("exam_works_id");
                            ExamWorksGrade worksGrade = examWorksGradeService.findUnique(exam_works_id, team.getGradeId());
                            Float gradeSubjectTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(worksGrade.getJointExamCode(), subjectCode);
                            Integer gradeStudentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(worksGrade.getJointExamCode(), subjectCode);
                            if (gradeSubjectTotalScore != null && gradeStudentCount != null && gradeStudentCount != 0) {
                                gradeAvgScoreList.add(df.format(gradeSubjectTotalScore / gradeStudentCount));
                            } else {
                                gradeAvgScoreList.add(0);
                            }
                        }
                    }

                    Collections.reverse(titleList);
                    Collections.reverse(userScoreList);
                    Collections.reverse(teamAvgScoreList);
                    Collections.reverse(gradeAvgScoreList);
                    Collections.reverse(userTeamRankList);
                    Collections.reverse(userGradeRankList);
                    model.addAttribute("titleList", mapper.writeValueAsString(titleList));
                    model.addAttribute("userScoreList", mapper.writeValueAsString(userScoreList));
                    model.addAttribute("teamAvgScoreList", teamAvgScoreList);
                    model.addAttribute("gradeAvgScoreList", gradeAvgScoreList);
                    model.addAttribute("userTeamRankList", mapper.writeValueAsString(userTeamRankList));
                    model.addAttribute("userGradeRankList", mapper.writeValueAsString(userGradeRankList));
                }


            } else {
                viewPath = structurePath("/xc/xc_single_analysis");

                String title = getTitle(examWorks.getSchoolId(), examWorks.getSchoolYear(), examWorks.getTermCode());
                Student student = studentService.findStudentByUserId(userId);
                model.addAttribute("title", title);
                model.addAttribute("studentName", student.getName());
                model.addAttribute("examWorks", examWorks);

                //得分概况
                List<Map<String,Object>> userExamWorks = scoreAnalysisHandleService.findExamWorksByUserId(examWorksId, userId, examWorks.getSubjectCode(), null);
                if (userExamWorks != null && userExamWorks.size() > 0) {
                    model.addAttribute("userExamWork", userExamWorks.get(0));
                }

                //班级名次分布图
                ExamWorksTeamSubject teamSubject = examWorksTeamSubjectService.findUnique(examWorksId, examWorks.getTeamId(), examWorks.getSubjectCode());
                List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(teamSubject.getExamId());
                List<Float> scoreList = new ArrayList<>();
                List<Object> rankList = new ArrayList<>();
                Float avgScore = 0f;
                Integer studentCount = 0;
                if (userExamWorks != null && userExamWorks.size() > 0) {
                    avgScore = (Float) userExamWorks.get(0).get("average_score");
                    studentCount = (Integer) userExamWorks.get(0).get("student_count");
                }
                int avgRank = 0;
                float maxScore = 0;
                if (examStudentList != null && examStudentList.size() > 0) {
                    for (ExamStudent examStudent : examStudentList) {
                        scoreList.add(examStudent.getScore());
                        if (examStudent.getUserId().intValue() == userId) {
                            rankList.add("{marker: {radius: 8},y:" + examStudent.getTeamRank() + ",color:'#f18441'}");
                        } else {
                            rankList.add(examStudent.getTeamRank());
                        }
                    }
                    maxScore = examStudentList.get(examStudentList.size() - 1).getScore();
                }

                if (avgScore != null && studentCount != null && studentCount != 0) {
                    float result = this.teamAvgRank(scoreList, avgScore);
                    for (ExamStudent examStudent : examStudentList) {
                        if (examStudent.getScore() == result) {
                            avgRank = examStudent.getTeamRank();
                            break;
                        }
                    }
                }
                model.addAttribute("scoreList", scoreList);
                model.addAttribute("rankList", rankList);
                model.addAttribute("avgRank", avgRank);
                model.addAttribute("maxScore", maxScore);

                //分数趋势
                List<Map<String, Object>> avgScoreList = examWorksTeamSubjectService.findAvgScoreOfStudent(userId, examWorks.getTeamId(), examWorks.getSubjectCode(), null);
                List<Object> userScoreList = new ArrayList<>();
                List<Object> teamAvgScoreList = new ArrayList<>();
                List<Object> teamRankList = new ArrayList<>();   //班内名次趋势
                List<Object> titleList = new ArrayList<>();
                for (Map<String, Object> objectMap : avgScoreList) {
                    Float score = (Float) objectMap.get("score");
                    if (score != -1) {
                        userScoreList.add(score);
                        teamAvgScoreList.add(objectMap.get("avgScore"));
                        teamRankList.add(objectMap.get("teamRank"));
                        titleList.add(objectMap.get("name"));
                    } else {
                        userScoreList.add(0);
                        teamAvgScoreList.add(0);
                        teamRankList.add(examStudentList != null ? examStudentList.size() : 0);
                        titleList.add(objectMap.get("name"));
                    }
                }
                Collections.reverse(userScoreList);
                Collections.reverse(teamAvgScoreList);
                Collections.reverse(teamRankList);
                Collections.reverse(titleList);
                model.addAttribute("userScoreList", userScoreList);
                model.addAttribute("teamAvgScoreList", teamAvgScoreList);
                model.addAttribute("teamRankList", teamRankList);
                model.addAttribute("titleList", mapper.writeValueAsString(titleList));
            }

        }
        model.addAttribute("examWorksId", examWorksId);
        model.addAttribute("userId", userId);
        model.addAttribute("teamId", teamId);
        model.addAttribute("backSign", backSign);
        return new ModelAndView(viewPath, model.asMap());
    }
	
	private List<Map<String, Object>> getAnaliysisRankResult(Integer examWorksId, Integer gradeId, Page page) {
		List<Integer> result = getStudentRank(examWorksId, gradeId, true);
		List<Integer> studentIds = null;
		if(page!=null) {
			studentIds = parseStudentRankResult(result, page);
			page.init(result.size(), page.getPageSize(), page.getCurrentPage());
		} else {
			studentIds = result;
		}
		
		List<Map<String, Object>> entityList = new ArrayList<Map<String, Object>>(studentIds.size());
		
		DecimalFormat decimalFormat=new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
		
		for (Integer studentId : studentIds) {
			List<Map<String, Object>> tmp = examWorksTeamSubjectService.findStudentScore(examWorksId, gradeId, studentId);
			StringBuffer buf = new StringBuffer();
			Float totalScore = 0.00f;
			for (int i = 0; i < tmp.size(); i++) {
				Map<String, Object> map = tmp.get(i);
				buf.append(map.get("subjectCode"));
				buf.append(",");
				Float score = (Float) map.get("score");
				
				Float range = score+1;
				if(!(-0.01<=  range && range <=0.01)) {
					totalScore+=score;
				}
				buf.append(score);
				buf.append(",");
				Integer gradeRank = (Integer) map.get("gradeRank");
				buf.append(gradeRank==null?"-":gradeRank);
				if((i+1)==tmp.size()) {
					map.put("scores", buf.toString());
					String formatScore = decimalFormat.format(totalScore);
					map.put("totalScore", formatScore.equals(".0")?"0.0":formatScore);
					entityList.add(map);
				} else {
					buf.append(";");
				}
			}
		}
		
		return entityList;
	}

	private List<Integer> parseStudentRankResult(List<Integer> result, Page page) {
		List<Integer> studentIds = new ArrayList<Integer>(page.getPageSize());
		
		Integer currentPage = page.getCurrentPage();
		Integer pageSize = page.getPageSize();
		
		Integer start = (currentPage-1) * pageSize;
		Integer end = start + pageSize;
		
		if(end-result.size()>0) {
			end = result.size();
		}
		
		for (int i = start; i < end; i++) {
			Integer studentId = result.get(i);
			if(studentId!=null) {
				studentIds.add(studentId);
			}
		}
		return studentIds;
	}

	private List<Integer> getStudentRank(Integer examWorksId, Integer gradeId, boolean userCache) {
		String key = getStudentRankKey(examWorksId, gradeId);
		
		List<Integer> result = (List<Integer>) baseRedisCache.getCacheObject(key);
		if(result==null || result.size()==0) {
			result = examWorksTeamSubjectService.findExamWorkStudentRank(examWorksId, gradeId);
			baseRedisCache.setCacheObject(key, result, 60*6L);
		}
		
		return result;
	}

	private String getStudentRankKey(Integer examWorksId, Integer gradeId) {
		return "exam_work_student_rank"+examWorksId+gradeId;
	}
	
	private String getStudentRankNameKey(Integer examWorksId, Integer gradeId) {
		return "exam_work_student_rank_name"+examWorksId+gradeId;
	}

    /**
     * 班级分析报告
     */
    @RequestMapping(value = "/analysis/team")
    public ModelAndView toTeamAnalysis(
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "form", required = false) String form,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model) throws IOException {
        String viewPath = structurePath("/tk/tk_bj_analysis");
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        if (examWorks != null) {

            //班级基础信息
            Map<String, Object> teamObject = examWorksTeamService.findOfExamWorksWithScore(examWorksId, gradeId, teamId);
            model.addAttribute("teamObject", teamObject);
            Map<String, Object> scoreMap = new HashMap<>();
            ExamWorksGrade worksGrade = examWorksGradeService.findUnique(examWorksId, gradeId);
            String jointExamCode = worksGrade.getJointExamCode();
            List<ExamStatMajorStudent> statMajorStudents = examStatMajorStudentService.findExamStatMajorCount(examWorksId, jointExamCode, teamId, 1);
            float teamAvgScore = 0;
            float teamTotalScore = 0;
            if (statMajorStudents != null && statMajorStudents.size() > 0) {
                scoreMap.put("maxScore", statMajorStudents.get(statMajorStudents.size() - 1).getTotalScore());    //班级总分最高分
                scoreMap.put("minScore", statMajorStudents.get(0).getTotalScore());         //班级总分最低分
                for (ExamStatMajorStudent majorStudent : statMajorStudents) {
                    teamTotalScore += majorStudent.getTotalScore();
                }
                teamAvgScore = teamTotalScore / statMajorStudents.size();
                scoreMap.put("averageScore", df.format(teamAvgScore));
                scoreMap.put("statStudentCount", statMajorStudents.size());     //实际考试人数
            }
            List<TeamStudent> teamStudents = teamStudentService.findByTeamId(teamId);
            scoreMap.put("studentCount", teamStudents.size());
            Map<String, Object> gradeScore = examWorksGradeService.findOfExamWorksWithScore(examWorksId, gradeId);
            scoreMap.put("totalScore", gradeScore.get("totalScore"));           //班级总分
            model.addAttribute("scoreMap", scoreMap);

            //考试科目
            List<Map<String, Object>> subjectList = examWorksSubjectService.findStatSubjects(examWorksId, gradeId, null, true);
            model.addAttribute("subjectList", subjectList);

            if ("1".equals(sub)) {
                //班级成绩单
                if (form != null && !"".equals(form)) {
                    viewPath = structurePath("/tk/tk_bj_cj_list");
                } else {
                    viewPath = structurePath("/tk/tk_bj_cj");
                }
                List<Map<String, Object>> totalList = examWorksTeamSubjectService.findScoreOfStudent(examWorksId, gradeId, teamId, null, page, null);
                
                model.addAttribute("totalList", totalList);
                model.addAttribute("statMajorStudents", statMajorStudents);

            } else if ("2".equals(sub)) {
                viewPath = structurePath("/tk/tk_bj_zh");
                //班级综合分析
                //班级各学科均分雷达图
                List<String> subjectNameList = new ArrayList<>();
                List<Object> gradeAvgScoreList = new ArrayList<>();
                List<Object> teamAvgScoreList = new ArrayList<>();
                for (Map<String, Object> objectMap : subjectList) {
                    String code = (String) objectMap.get("subjectCode");
                    String subjectName = (String) objectMap.get("subjectName");
                    subjectNameList.add(subjectName);

                    Float gradeTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(jointExamCode, code);
                    Integer gradeStudentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(jointExamCode, code);
                    if (gradeTotalScore != null && gradeStudentCount != null && gradeStudentCount != 0) {
                        gradeAvgScoreList.add(df.format((float) gradeTotalScore / gradeStudentCount));
                    } else {
                        gradeAvgScoreList.add(0);
                    }
                    Float teamSubjectScore = this.examWorksTeamSubjectService.findTeamTotalScoreBySubjectCode(jointExamCode, code, teamId);
                    Integer teamStudentCount = this.examWorksTeamSubjectService.findTeamStudentCountBySubjectCode(jointExamCode, code, teamId);
                    if (teamSubjectScore != null && teamStudentCount != null && teamStudentCount != 0) {
                        teamAvgScoreList.add(df.format((float)teamSubjectScore / teamStudentCount));
                    } else {
                        teamAvgScoreList.add(0);
                    }

                }
                model.addAttribute("subjectNameList", mapper.writeValueAsString(subjectNameList));
                model.addAttribute("gradeAvgScoreList", gradeAvgScoreList);
                model.addAttribute("teamAvgScoreList", teamAvgScoreList);

                //年级各班级总分平均分排名
                List<Object> teamNameList = new ArrayList<>();
                List<Object> teamAvgTotalScoreList = new ArrayList<>();
                List<Map<String, Object>> teamList = examWorksTeamService.findOfExamWorksWithScore(examWorksId, gradeId);
                for (Map<String, Object> objectMap : teamList) {
                    String teamName = (String) objectMap.get("teamName");
                    Integer team_id = (Integer) objectMap.get("teamId");
                    List<ExamStatMajorStudent> majorTeam = examStatMajorStudentService.findExamStatMajorCount(examWorksId, jointExamCode, team_id, 1);
                    float totalScore = 0;
                    float avgScore = 0;
                    if (majorTeam != null && majorTeam.size() > 0) {
                        for (ExamStatMajorStudent majorStudent : majorTeam) {
                            totalScore += majorStudent.getTotalScore();
                        }
                        avgScore = totalScore / majorTeam.size();
                    }
                    teamNameList.add(teamName);
                    teamAvgTotalScoreList.add(df.format(avgScore));
                }
                Collections.reverse(teamNameList);
                Collections.reverse(teamAvgTotalScoreList);
                model.addAttribute("teamNameList", mapper.writeValueAsString(teamNameList));
                model.addAttribute("teamAvgTotalScoreList", teamAvgTotalScoreList);

                //班级总分名次分布图
                List<Object> teamRankList = new ArrayList<Object>();
                List<Float> totalScoreList = new ArrayList<Float>();
                List<Object> studentNameList = new ArrayList<>();
                int teamAvgRank = 0;
                for (ExamStatMajorStudent majorStudent : statMajorStudents) {
                    teamRankList.add("{fs:" + majorStudent.getTotalScore() + ",y:" + majorStudent.getTeamRank() + "}");
                    totalScoreList.add(majorStudent.getTotalScore());
                }
                float result = this.teamAvgRank(totalScoreList, teamAvgScore);
                ExamStatMajorStudentCondition ees = new ExamStatMajorStudentCondition();
                ees.setExamWorksId(examWorksId);
                ees.setJointExamCode(jointExamCode);
                ees.setTeamId(teamId);
                ees.setTotalScore(result);
                ees.setIsDeleted(false);
                List<ExamStatMajorStudent> list = this.examStatMajorStudentService.findExamStatMajorStudentByCondition(ees);
                if (list != null && list.size() > 0) {
                    teamAvgRank = list.get(0).getTeamRank();
                }
                List<Map<String, Object>> totalList = examWorksTeamSubjectService.findScoreOfStudent(examWorksId, gradeId, teamId, null);
                for (Map<String, Object> objectMap : totalList) {
                    if (objectMap.get("userId") != null && objectMap.get("totalScore") != null && !objectMap.get("totalScore").equals(0.0)) {
                        studentNameList.add(objectMap.get("name"));
                    }
                }
                Collections.reverse(studentNameList);
                model.addAttribute("teamRankList", teamRankList);
                model.addAttribute("teamAvgRank", teamAvgRank);
                model.addAttribute("totalScoreList", totalScoreList);   //班级总分分数段图
                model.addAttribute("studentNameList", mapper.writeValueAsString(studentNameList));   //


            } else if ("3".equals(sub)) {
                viewPath = structurePath("/tk/tk_bj_dk");
                //班级单科分析
                if ((subjectCode == null || "".equals(subjectCode)) && subjectList != null && subjectList.size() > 0) {
                    subjectCode = (String) subjectList.get(0).get("subjectCode");
                }
                //班级三率分布
                ExamWorksTeamSubject teamSubject = examWorksTeamSubjectService.findUnique(examWorksId, teamId, subjectCode);
                ExamStat examStat = examStatService.findExamStatByExamId(teamSubject.getExamId());
                model.addAttribute("examStat", examStat);
                ExamWorksSubject examWorksSubject = examWorksSubjectService.findUnique(examWorksId, gradeId, subjectCode);
                model.addAttribute("examWorksSubject", examWorksSubject);

                //年级三率综合堆积对比图
                ExamWorksTeamSubjectCondition condition = new ExamWorksTeamSubjectCondition();
                condition.setExamWorksId(examWorksId);
                condition.setGradeId(gradeId);
                condition.setSubjectCode(subjectCode);
                condition.setIsDeleted(false);
                List<ExamWorksTeamSubject> teamSubjectList = examWorksTeamSubjectService.findExamWorksTeamSubjectByCondition(condition);
                if (teamSubjectList != null && teamSubjectList.size() > 0) {
                    Integer[] examIds = new Integer[teamSubjectList.size()];
                    for (int i = 0; i < teamSubjectList.size(); i++) {
                        examIds[i] = teamSubjectList.get(i).getExamId();
                    }
                    List<ExamStatVo> examStatVoList = examStatService.findExamStatByExamIds(examIds);

                    int highCount, lowCount, passCount, noPassCount, studentCount = 0;
                    List<String> teamNameList = new ArrayList<>();
                    List<Object> highCountList = new ArrayList<>();
                    List<Object> lowCountList = new ArrayList<>();
                    List<Object> passCountList = new ArrayList<>();
                    List<Object> noPassCountList = new ArrayList<>();
                    List<Object> teamAvgScoreList = new ArrayList<>();
                    CommonScoreRank commonScoreRank = null;
                    for (ExamStatVo statVo : examStatVoList) {

                        commonScoreRank = examStudentService.countScoreRate(statVo.getExamId(), statVo.getFullScore(), statVo.getHighScore(), statVo.getLowScore(), statVo.getPassScore());
                        if (commonScoreRank != null) {
                            noPassCount = commonScoreRank.getLevel4() != null ? commonScoreRank.getLevel4().intValue() : 0;
                        } else {
                            noPassCount = 0;
                        }

                        studentCount = statVo.getStudentCount();
                        highCount = verifyCount(statVo.getHighCount());
                        lowCount = verifyCount(statVo.getLowCount()) - verifyCount(statVo.getHighCount());
                        passCount = verifyCount(statVo.getPassCount()) - verifyCount(statVo.getLowCount());
//                        noPassCount = verifyCount(studentCount) - verifyCount(statVo.getPassCount());
                        teamNameList.add(statVo.getTeamName());
                        highCountList.add(getPercent(highCount, studentCount));
                        lowCountList.add(getPercent(lowCount, studentCount));
                        passCountList.add(getPercent(passCount, studentCount));
                        noPassCountList.add(getPercent(noPassCount, studentCount));
                        teamAvgScoreList.add(statVo.getAverageScore() != null && statVo.getAverageScore() > 0 ? statVo.getAverageScore() : 0);
                    }
                    Map<String, Object> teamScoreMap = new HashMap<>();
                    teamScoreMap.put("highCountList", highCountList);
                    teamScoreMap.put("lowCountList", lowCountList);
                    teamScoreMap.put("passCountList", passCountList);
                    teamScoreMap.put("noPassCountList", noPassCountList);
                    model.addAttribute("teamScoreMap", teamScoreMap);
                    model.addAttribute("teamNameList", mapper.writeValueAsString(teamNameList));
                    Collections.reverse(teamNameList);
                    Collections.reverse(teamAvgScoreList);
                    model.addAttribute("teamNameList_r", mapper.writeValueAsString(teamNameList));
                    //年级各班级单科平均分排名
                    model.addAttribute("teamAvgScoreList", mapper.writeValueAsString(teamAvgScoreList));

                    //班级单科名次分布图
                    List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(teamSubject.getExamId());
                    List<Object> teamRankList = new ArrayList<>();
                    List<Float> teamScoreList = new ArrayList<>();
                    List<Object> studentNameList = new ArrayList<>();
                    int examStudentCount = 0;       //参与考试的人数，有分数的
                    for (ExamStudent examStudent : examStudentList) {
                        teamRankList.add("{fs:" + examStudent.getScore() +",y:" + examStudent.getTeamRank() + "}");
                        teamScoreList.add(examStudent.getScore());
                        if (examStudent.getScore() != -1) {
                            examStudentCount++;
                        }
                        studentNameList.add(examStudent.getName());
                    }

                    int teamAvgRank = 0;
                    if (teamScoreList != null && teamScoreList.size() > 0 && examStat.getAverageScore() != null) {
                        float result = teamAvgRank(teamScoreList, examStat.getAverageScore());
                        for (ExamStudent examStudent : examStudentList) {
                            if (result == examStudent.getScore()) {
                                teamAvgRank = examStudent.getTeamRank();
                                break;
                            }
                        }
                    }
                    model.addAttribute("teamAvgRank", teamAvgRank);
                    model.addAttribute("teamRankList", teamRankList);
                    model.addAttribute("teamScoreList", teamScoreList); //班级单科分数段图
                    model.addAttribute("examStudentCount", examStudentCount);
                    model.addAttribute("studentNameList", mapper.writeValueAsString(studentNameList));

                }

            } else if ("4".equals(sub)) {
                viewPath = structurePath("/tk/tk_bj_qs");
                //班级趋势分析
                if ((subjectCode == null || "".equals(subjectCode)) && subjectList != null && subjectList.size() > 0) {
                    subjectCode = (String) subjectList.get(0).get("subjectCode");
                }
                if (subjectCode != null && !"".equals(subjectCode)) {
                    ExamWorksTeamSubject teamSubject = examWorksTeamSubjectService.findUnique(examWorksId, teamId, subjectCode);
                    ExamStat examStat = examStatService.findExamStatByExamId(teamSubject.getExamId());
                    model.addAttribute("examStat", examStat);
                    int examStudentCount = 0;       //参与考试的人数，有分数的
                    List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(teamSubject.getExamId());
                    for (ExamStudent examStudent : examStudentList) {
                        if (examStudent.getScore() != -1) {
                            examStudentCount++;
                        }
                    }
                    model.addAttribute("examStudentCount", examStudentCount);
                }

                //班级单科平均分趋势
                List<Map<String, Object>> list = examWorksTeamSubjectService.findAvgScoreOfGrade(examWorks.getSchoolId(), true, gradeId, teamId, subjectCode);
                List<Object> nameList = new ArrayList<>();
                List<Object> teamAvgScoreList = new ArrayList<>();
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> objectMap : list) {
                        nameList.add(objectMap.get("name"));
                        if (subjectCode != null && !"".equals(subjectCode)) {
                            teamAvgScoreList.add(objectMap.get("avgScore"));
                        } else {
                            teamAvgScoreList.add(objectMap.get("avgTotalScore"));
                        }
                    }
                }
                Collections.reverse(nameList);
                Collections.reverse(teamAvgScoreList);
                model.addAttribute("nameList", mapper.writeValueAsString(nameList));
                model.addAttribute("teamAvgScoreList", mapper.writeValueAsString(teamAvgScoreList));

                //班级单科平均分各班对比趋势
                List<Map<String, Object>> teamList = examWorksTeamService.findOfExamWorksWithScore(examWorksId, gradeId);
                List<Map<String, Object>> teamScoreList = new ArrayList<>();
                for (Map<String, Object> objectMap : teamList) {
                    Integer team_id = (Integer) objectMap.get("teamId");
                    List<Map<String, Object>> scoreOfTeam = examWorksTeamSubjectService.findAvgScoreOfGrade(examWorks.getSchoolId(), true, gradeId, team_id, subjectCode);
                    List<Object> avgScoreList = new ArrayList<>();
                    for (Map<String, Object> map : scoreOfTeam) {
                        if (subjectCode != null && !"".equals(subjectCode)) {
                            avgScoreList.add(map.get("avgScore"));
                        } else {
                            avgScoreList.add(map.get("avgTotalScore"));
                        }
                    }
                    Collections.reverse(avgScoreList);
                    Map<String, Object> teamMap = new HashMap<>();
                    teamMap.put("teamName", objectMap.get("teamName"));
                    teamMap.put("teamId", objectMap.get("teamId"));
                    teamMap.put("avgScoreList", avgScoreList);
                    teamScoreList.add(teamMap);
                }
                model.addAttribute("teamScoreList", JSONArray.fromObject(teamScoreList).toString());

            }

            //标题
            String title = getTitle(examWorks.getSchoolId(), examWorks.getSchoolYear(), examWorks.getTermCode());
            Team team = teamService.findTeamById(teamId);
            model.addAttribute("title", title);
            model.addAttribute("teamName", team.getName());

            model.addAttribute("examWorksId", examWorksId);
            model.addAttribute("gradeId", gradeId);
            model.addAttribute("teamId", teamId);
            model.addAttribute("subjectCode", subjectCode);
            model.addAttribute("examWorks", examWorks);
        }
        return new ModelAndView(viewPath, model.asMap());
    }


    @RequestMapping(value = "/analysis/team/xc")
    public ModelAndView toTeamAnalysis(
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "teamId", required = false) Integer teamId,
            Model model) throws IOException {
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        if (examWorks != null) {
            String title = getTitle(examWorks.getSchoolId(), examWorks.getSchoolYear(), examWorks.getTermCode());
            Team team = teamService.findTeamById(teamId);
            Subject subject = subjectService.findUnique(examWorks.getSchoolId(), examWorks.getSubjectCode());

            model.addAttribute("title", title);
            model.addAttribute("teamName", team.getName());
            model.addAttribute("subjectName", subject.getName());
            model.addAttribute("examWorks", examWorks);

            //班级基础数据
            ExamWorksSubject examWorksSubject = examWorksSubjectService.findUnique(examWorksId, examWorks.getGradeId(), examWorks.getSubjectCode());
            ExamWorksTeamSubject teamSubject = examWorksTeamSubjectService.findUnique(examWorksId, examWorks.getTeamId(), examWorks.getSubjectCode());
            ExamStat examStat = examStatService.findExamStatByExamId(teamSubject.getExamId());
            List<TeamStudent> studentList = teamStudentService.findByTeamId(examWorks.getTeamId());
            model.addAttribute("examWorksSubject", examWorksSubject);
            model.addAttribute("teamSubject", teamSubject);
            model.addAttribute("examStat", examStat);
            model.addAttribute("studentCount", studentList.size());

            //班级单科名次分布图
            List<ExamStudent> examStudentList = examStudentService.findExamStudentsByExamId(teamSubject.getExamId());
            List<Object> teamRankList = new ArrayList<>();
            List<Float> teamScoreList = new ArrayList<>();
            List<String> nameList = new ArrayList<>();
            int examStudentCount = 0;   //参数考试的人数（有得分的）
            for (ExamStudent examStudent : examStudentList) {
                teamRankList.add("{fs:" + examStudent.getScore() + ",y:" + examStudent.getTeamRank() + "}");
                teamScoreList.add(examStudent.getScore());
                nameList.add(examStudent.getName());
                if (examStudent.getScore() != null && examStudent.getScore() >= 0) {
                    examStudentCount ++;
                }
            }
            model.addAttribute("teamRankList", teamRankList);
            model.addAttribute("teamScoreList", teamScoreList);     //班级单科分数段图
            model.addAttribute("nameList", mapper.writeValueAsString(nameList));
            model.addAttribute("examStudentCount", examStudentCount);

            int avgRank = 0;
            if (examStat.getAverageScore() != null) {
                float result = teamAvgRank(teamScoreList, examStat.getAverageScore());
                for (ExamStudent examStudent : examStudentList) {
                    if (result == examStudent.getScore()){
                        avgRank = examStudent.getTeamRank();
                    }
                }
            }
            model.addAttribute("avgRank", avgRank);

            //班级单科平均分趋势
            List<Map<String, Object>> list = examWorksTeamSubjectService.findAvgScoreOfGrade(
                    examWorks.getSchoolId(), false, examWorks.getGradeId(), examWorks.getTeamId(), examWorks.getSubjectCode());
            List<Object> examNameList = new ArrayList<>();
            List<Object> teamAvgScoreList = new ArrayList<>();
            for (Map<String, Object> objectMap : list) {
                examNameList.add(objectMap.get("name"));
                teamAvgScoreList.add(objectMap.get("avgScore"));
            }
            Collections.reverse(examNameList);
            Collections.reverse(teamAvgScoreList);
            model.addAttribute("examNameList", mapper.writeValueAsString(examNameList));
            model.addAttribute("teamAvgScoreList", mapper.writeValueAsString(teamAvgScoreList));



        }

        return new ModelAndView(structurePath("/xc/xc_bj_analysis"), model.asMap());
    }


    /**
     * 年级分析报告
     */
    @RequestMapping(value = "/analysis/grade")
    public ModelAndView toGradeAnalysis(
            @RequestParam(value = "examWorksId", required = false) Integer examWorksId,
            @RequestParam(value = "gradeId", required = false) Integer gradeId,
            @RequestParam(value = "subjectCode", required = false) String subjectCode,
            @RequestParam(value = "sub", required = false) String sub,
            @RequestParam(value = "form", required = false) String form,
            @ModelAttribute("page") Page page,
            @ModelAttribute("order") Order order,
            Model model) throws IOException {
        String viewPath = structurePath("/tk/tk_nj_analysis");
        ExamWorks examWorks = examWorksService.findExamWorksById(examWorksId);
        if (examWorks != null) {

            String title = getTitle(examWorks.getSchoolId(), examWorks.getSchoolYear(), examWorks.getTermCode());
            Grade grade = gradeService.findGradeById(gradeId);
            model.addAttribute("title", title);
            model.addAttribute("gradeName", grade.getName());

            //参与统计的科目
            List<Map<String, Object>> subjectList = examWorksSubjectService.findStatSubjects(examWorksId, gradeId, null, true);
            model.addAttribute("subjectList", subjectList);
            //参与考试的班级，成绩及导入结果-- result： 0=未导入 1=部分导入 2=全部导入
            List<Map<String, Object>> teamList = examWorksTeamService.findOfExamWorksWithScore(examWorksId, gradeId);
            model.addAttribute("teamList", teamList);
            ExamWorksGrade examWorksGrade = examWorksGradeService.findUnique(examWorksId, gradeId);
            String jointExamCode = examWorksGrade.getJointExamCode();

            if ("1".equals(sub)) {
                if (form != null && !"".equals(form)) {
                    viewPath = structurePath("/tk/tk_nj_cj_list");
                } else {
                    viewPath = structurePath("/tk/tk_nj_cj");
                }
                //1.年级成绩单   totalList是exam_student表的数据，按总分（单科无分时设为0）降序，有各科分数信息，但无年级排名，无成绩录入时可查
                //List<Map<String, Object>> totalList = examWorksTeamSubjectService.findScoreOfStudent(examWorksId, gradeId, null, null, page, null);
                List<Map<String, Object>> totalList = getAnaliysisRankResult(examWorksId, gradeId, page);
                
                List<ExamStatMajorStudent> statMajorStudents = new ArrayList<ExamStatMajorStudent>(totalList.size());
                for (Map<String, Object> map : totalList) {
                	Integer studentId = (Integer) map.get("studentId");
                	ExamStatMajorStudent examStatMajorStudent = examStatMajorStudentService.
                			findExamStatMajorStudentByExamWorksIdAndStudentId(examWorksId, studentId);
                	statMajorStudents.add(examStatMajorStudent);
				}
                
                model.addAttribute("totalList", totalList);
                //statMajorStudents 为学生总成绩记录，flag=null时，总分可为null, 有年级排名，按年级排名降序
                //List<ExamStatMajorStudent> statMajorStudents = examStatMajorStudentService.findExamStatMajorCount(examWorksId, jointExamCode, null, 1);
                model.addAttribute("statMajorStudents", statMajorStudents);

            } else if ("2".equals(sub)) {
                viewPath = structurePath("/tk/tk_nj_zh");

                //年级基础信息
                int statStudentCount = 0;   //参与统计的人数
                int studentCount = 0;       //年级总人数
                float totalFullScore = 0;   //所有科目总分满分
                float totalScore = 0;       //所有科目总分
                float avgScore = 0;         //总分平均分
                float highestScore = 0;     //总分最高分
                float lowestScore = 0;      //总分最低分
                Map<String, Object> scoreMap = new HashMap<>();     //包含人数和分数的集合

                List<ExamStatMajorStudent> statMajorStudents = examStatMajorStudentService.findExamStatMajorCount(examWorksId, jointExamCode, null, 1);
                Map<String, Object> gradeScore = examWorksGradeService.findOfExamWorksWithScore(examWorksId, gradeId);
                if (statMajorStudents != null && statMajorStudents.size() > 0) {
                    for (ExamStatMajorStudent majorStudent : statMajorStudents) {
                        totalScore += majorStudent.getTotalScore();
                    }
                    statStudentCount = statMajorStudents.size();
                    avgScore = totalScore / statStudentCount;
                    highestScore = statMajorStudents.get(statMajorStudents.size() - 1).getTotalScore();
                    lowestScore = statMajorStudents.get(0).getTotalScore();
                }
                if (gradeScore != null) {
                    totalFullScore = (float)(double) gradeScore.get("totalScore");
                }
                studentCount = examWorksGrade.getExamStudentCount();
                scoreMap.put("statStudentCount", statStudentCount);
                scoreMap.put("studentCount", studentCount);
                scoreMap.put("totalFullScore", totalFullScore);
                scoreMap.put("avgScore", df.format(avgScore));
                scoreMap.put("highestScore", highestScore);
                scoreMap.put("lowestScore", lowestScore);
                model.addAttribute("scoreMap", scoreMap);

                //年级各学科均分雷达图
                List<String> subjectNameList = new ArrayList<>();       //科目名称列表
                List<Object> subjectAvgScoreList = new ArrayList<>();     //科目平均分列表
                float subjectAvgScore = 0;              //科目平均分
                for (Map<String, Object> objectMap : subjectList) {
                    String subject_code = (String) objectMap.get("subjectCode");
                    Float subjectTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(jointExamCode, subject_code);
                    Integer subjectStudentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(jointExamCode, subject_code);
                    if (subjectTotalScore != null && subjectStudentCount != null && subjectStudentCount != 0) {
                        subjectAvgScore = (float) subjectTotalScore / subjectStudentCount;
                    } else {
                        subjectAvgScore = 0;
                    }
                    subjectAvgScoreList.add(df.format(subjectAvgScore));
                    subjectNameList.add((String) objectMap.get("subjectName"));
                }
                model.addAttribute("subjectNameList", mapper.writeValueAsString(subjectNameList));
                model.addAttribute("subjectAvgScoreList", subjectAvgScoreList);

                //年级各班级总分平均分排名
                List<Object> teamNameList = new ArrayList<>();      //班级名称列表
                List<Object> teamAvgTotalScoreList = new ArrayList<>();    //班级总分平均分列表
                for (Map<String, Object> objectMap : teamList) {
                    Integer teamId = (Integer) objectMap.get("teamId");
                    List<ExamStatMajorStudent> majorTeam = examStatMajorStudentService.findExamStatMajorCount(examWorksId, jointExamCode, teamId, 1);
                    float teamTotalScore = 0;           //各班所有人总分
                    float teamAvgTotalScore = 0;        //班级总分平均分
                    if (majorTeam != null && majorTeam.size() > 0) {
                        for (ExamStatMajorStudent majorStudent : majorTeam) {
                            teamTotalScore += majorStudent.getTotalScore();
                        }
                        teamAvgTotalScore = teamTotalScore / majorTeam.size();
                    }
                    teamAvgTotalScoreList.add(df.format(teamAvgTotalScore));
                    teamNameList.add(objectMap.get("teamName"));
                }
                Collections.reverse(teamNameList);              //倒序排列
                Collections.reverse(teamAvgTotalScoreList);
                model.addAttribute("teamNameList", mapper.writeValueAsString(teamNameList));
                model.addAttribute("teamAvgTotalScoreList", teamAvgTotalScoreList);

                //年级总分名次分布图 -- 年级总分分数段图
                List<Float> totalScoreList = new ArrayList<>();     //总分列表（年级所有人）
                List<Object> gradeRankList = new ArrayList<>();     //年级排名列表
                int gradeAvgRank = 0;                   //年级平均分所在的名次
                List<String> studentNameList = new ArrayList<>();       //学生姓名列表
                for (ExamStatMajorStudent majorStudent : statMajorStudents) {
                    gradeRankList.add("{fs:" + majorStudent.getTotalScore() + ",y:" + majorStudent.getGradeRank() + "}");
                    totalScoreList.add(majorStudent.getTotalScore());
                }
                float result = this.teamAvgRank(totalScoreList, avgScore);
                ExamStatMajorStudentCondition ees = new ExamStatMajorStudentCondition();
                ees.setExamWorksId(examWorksId);
                ees.setJointExamCode(jointExamCode);
                ees.setTotalScore(result);
                ees.setIsDeleted(false);
                List<ExamStatMajorStudent> list = this.examStatMajorStudentService.findExamStatMajorStudentByCondition(ees);
                if (list != null && list.size() > 0) {
                    gradeAvgRank = list.get(0).getGradeRank();
                }
                
                studentNameList = this.getStudentRandNameList(examWorksId, gradeId);
                /**
                List<Map<String, Object>> totalList = examWorksTeamSubjectService.findScoreOfStudent(examWorksId, gradeId, null, null);
                for (Map<String, Object> objectMap : totalList) {
                    if (objectMap.get("userId") != null && objectMap.get("totalScore") != null && !objectMap.get("totalScore").equals(0.0)) {
                        studentNameList.add(objectMap.get("name"));
                    }
                }*/
                Collections.reverse(studentNameList);
                model.addAttribute("gradeRankList", gradeRankList);
                model.addAttribute("gradeAvgRank", gradeAvgRank);
                model.addAttribute("totalScoreList", totalScoreList);
                model.addAttribute("studentNameList", mapper.writeValueAsString(studentNameList));

            } else if ("3".equals(sub)) {
                viewPath = structurePath("/tk/tk_nj_dk");
                if ((subjectCode == null || "".equals(subjectCode)) && subjectList != null && subjectList.size() > 0) {
                    subjectCode = (String) subjectList.get(0).get("subjectCode");
                }
                ExamWorksSubject examWorksSubject = examWorksSubjectService.findUnique(examWorksId, gradeId, subjectCode);
                model.addAttribute("examWorksSubject", examWorksSubject);

                Float subjectTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(jointExamCode, subjectCode);       //科目总分
                Integer subjectStudentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(jointExamCode, subjectCode); //考试人数
                float avgScore = 0;     //单科平均分
                if (subjectTotalScore != null && subjectStudentCount != null && subjectStudentCount != 0) {
                    avgScore = subjectTotalScore / subjectStudentCount;
                }

                int gradeStudentCount = examWorksGrade.getExamStudentCount();   //年级学生人数
                float highestScore =0;  //最高分
                float lowestScore = 0;  //最低分
                int high = 0;           //年级优秀人数
                int low = 0;            //年级良好人数
                int pass = 0;           //年级及格人数
                int noPass = 0;         //年级不及格人数
                int highCount = 0;      //班级优秀人数
                int lowCount = 0;       //班级良好人数
                int passCount = 0;      //班级及格人数
                int noPassCount = 0;    //班级不及格人数
                List<String> teamNameList = new ArrayList<>();      //班级名
                List<Object> teamAvgScoreList = new ArrayList<>();  //各班平均分
                List<Object> highCountList = new ArrayList<>();     //各班优秀人数列表
                List<Object> lowCountList = new ArrayList<>();      //各班良好人数列表
                List<Object> passCountList = new ArrayList<>();     //各班及格人数列表
                List<Object> noPassCountList = new ArrayList<>();   //各班不及格人数列表

                ExamWorksTeamSubjectCondition condition = new ExamWorksTeamSubjectCondition();
                condition.setExamWorksId(examWorksId);
                condition.setGradeId(gradeId);
                condition.setSubjectCode(subjectCode);
                condition.setIsDeleted(false);
                List<ExamWorksTeamSubject> teamSubjectList = examWorksTeamSubjectService.findExamWorksTeamSubjectByCondition(condition);
                if (teamSubjectList != null && teamSubjectList.size() > 0) {
                    Integer[] examIds = new Integer[teamSubjectList.size()];
                    for (int i = 0; i < teamSubjectList.size(); i++) {
                        examIds[i] = teamSubjectList.get(i).getExamId();
                    }
                    List<ExamStatVo> examStatVoList = examStatService.findExamStatByExamIds(examIds);
                    int studentCount = 0; //班级学生人数
                    CommonScoreRank commonScoreRank = null;
                    if (examStatVoList != null && examStatVoList.size() > 0) {
//                        lowestScore = examStatVoList.get(0).getLowestScore() !=null ? examStatVoList.get(0).getLowestScore() : 0;
                        for (ExamStatVo statVo : examStatVoList) {
                            //主要用于获取不及格人数
                            commonScoreRank = examStudentService.countScoreRate(statVo.getExamId(), statVo.getFullScore(), statVo.getHighScore(), statVo.getLowScore(), statVo.getPassScore());
                            if (commonScoreRank != null) {
                                noPassCount = commonScoreRank.getLevel4() != null ? commonScoreRank.getLevel4().intValue() : 0;
                            } else {
                                noPassCount = 0;
                            }

                            studentCount = statVo.getStudentCount();
                            highCount = verifyCount(statVo.getHighCount());
                            lowCount = verifyCount(statVo.getLowCount()) - verifyCount(statVo.getHighCount());
                            passCount = verifyCount(statVo.getPassCount()) - verifyCount(statVo.getLowCount());
//                            noPassCount = verifyCount(studentCount) - verifyCount(statVo.getPassCount());
                            high += highCount;
                            low += lowCount;
                            pass += passCount;
                            noPass += noPassCount;
                            teamNameList.add(statVo.getTeamName());
                            highCountList.add(getPercent(highCount, studentCount));
                            lowCountList.add(getPercent(lowCount, studentCount));
                            passCountList.add(getPercent(passCount, studentCount));
                            noPassCountList.add(getPercent(noPassCount, studentCount));
                            teamAvgScoreList.add(statVo.getAverageScore() != null && statVo.getAverageScore() != -1 ? statVo.getAverageScore() : 0);
//                            if (statVo.getHighestScore() != null && highestScore < statVo.getHighestScore()) {
//                                highestScore = statVo.getHighestScore();
//                            }
//                            if (statVo.getLowestScore() != null && lowestScore > statVo.getLowestScore()) {
//                                lowestScore = statVo.getLowestScore();
//                            }
//                            if (lowestScore < 0) {
//                                lowestScore = 0;
//                            }
                        }
                    }
                }

                Map<String, Object> scoreMap = new HashMap<>();     //人数、分数集合
                scoreMap.put("gradeStudentCount", gradeStudentCount);
                scoreMap.put("subjectStudentCount", subjectStudentCount);
                scoreMap.put("avgScore", df.format(avgScore));
                scoreMap.put("highCount", high);
                scoreMap.put("lowCount", low);
                scoreMap.put("passCount", pass);
                scoreMap.put("noPassCount", noPass);
                scoreMap.put("highCountList", highCountList);
                scoreMap.put("lowCountList", lowCountList);
                scoreMap.put("passCountList", passCountList);
                scoreMap.put("noPassCountList", noPassCountList);
//                scoreMap.put("highestScore", highestScore);
//                scoreMap.put("lowestScore", lowestScore);
//                model.addAttribute("scoreMap", scoreMap);

                //年级各班级单科平均分排名
                model.addAttribute("teamNameList", mapper.writeValueAsString(teamNameList));
                Collections.reverse(teamNameList);
                Collections.reverse(teamAvgScoreList);
                model.addAttribute("teamNameList_r", mapper.writeValueAsString(teamNameList));
                model.addAttribute("teamAvgScoreList", mapper.writeValueAsString(teamAvgScoreList));

                //年级单科名次分布图
                List<Object> gradeRankList = new ArrayList<>();        //年级名次列表
                List<Float> gradeScoreList = new ArrayList<>();     //分数列表
                List<Object> studentNameList = new ArrayList<>();   //学生姓名列表
                List<Map<String, Object>> gradeRanks = examWorksTeamSubjectService.findGradeRankBySubjectCode(gradeId, subjectCode, examWorksId);
                if (gradeRanks != null && gradeRanks.size() > 0) {
                    for (Map<String, Object> objectMap : gradeRanks) {
                        gradeScoreList.add((Float) objectMap.get("score"));
                        gradeRankList.add("{fs:" + objectMap.get("score") + ",y:" + objectMap.get("grade_rank") + "}");
                        studentNameList.add(objectMap.get("name"));
                    }
                    highestScore = (float) gradeRanks.get(gradeRanks.size()-1).get("score");
                    lowestScore = (float) gradeRanks.get(0).get("score");
                }
                int gradeAvgRank = 0;       //年级平均分名次
                float result = teamAvgRank(gradeScoreList, avgScore);
                List<ExamStudent> list = this.examStudentService.findGradeRankByScoreJointCode(result, examWorksGrade.getJointExamCode(), subjectCode);
                if (list != null && list.size() > 0) {
                    gradeAvgRank = list.get(0).getGradeRank();
                }
                model.addAttribute("gradeAvgRank", gradeAvgRank);
                model.addAttribute("gradeRankList", gradeRankList);
                model.addAttribute("gradeScoreList", gradeScoreList);
                model.addAttribute("studentNameList", mapper.writeValueAsString(studentNameList));

                scoreMap.put("highestScore", highestScore);
                scoreMap.put("lowestScore", lowestScore);
                model.addAttribute("scoreMap", scoreMap);


            } else if ("4".equals(sub)) {
                viewPath = structurePath("/tk/tk_nj_qs");
                if ((subjectCode == null || "".equals(subjectCode)) && subjectList != null && subjectList.size() > 0) {
                    subjectCode = (String) subjectList.get(0).get("subjectCode");
                }

                ExamWorksSubject examWorksSubject = examWorksSubjectService.findUnique(examWorksId, gradeId, subjectCode);
                model.addAttribute("examWorksSubject", examWorksSubject);

                Float subjectTotalScore = this.examWorksTeamSubjectService.findGradeTotalScoreBySubjectCode(jointExamCode, subjectCode);       //科目总分
                Integer subjectStudentCount = this.examWorksTeamSubjectService.findGradeStudentCountBySubjectCode(jointExamCode, subjectCode); //考试人数
                float avgScore = 0;     //单科平均分
                if (subjectTotalScore != null && subjectStudentCount != null && subjectStudentCount != 0) {
                    avgScore = subjectTotalScore / subjectStudentCount;
                }
                int gradeStudentCount = examWorksGrade.getExamStudentCount();

                float highestScore =0;  //最高分
                float lowestScore = 0;  //最低分
                List<Map<String, Object>> gradeRanks = examWorksTeamSubjectService.findGradeRankBySubjectCode(gradeId, subjectCode, examWorksId);
                if (gradeRanks != null && gradeRanks.size() > 0) {
                    highestScore = (float) gradeRanks.get(gradeRanks.size()-1).get("score");
                    lowestScore = (float) gradeRanks.get(0).get("score");
                }

                Map<String, Object> scoreMap = new HashMap<>();     //人数、分数集合
                scoreMap.put("gradeStudentCount", gradeStudentCount);
                scoreMap.put("subjectStudentCount", subjectStudentCount);
                scoreMap.put("avgScore", df.format(avgScore));
                scoreMap.put("highestScore", highestScore);
                scoreMap.put("lowestScore", lowestScore);
                model.addAttribute("scoreMap", scoreMap);

                //年级单科平均分排名 -- 班级单科平均分各班对比趋势
                List<Object> nameList = new ArrayList<>();              //考试名称
                List<Object> gradeAvgScoreList = new ArrayList<>();     //年级平均分列表
                List<Map<String, Object>> list = examWorksTeamSubjectService.findAvgScoreOfGrade(examWorks.getSchoolId(), true, gradeId, null, subjectCode);
                if (list != null && list.size() > 0) {
                    for (Map<String, Object> objectMap : list) {
                        nameList.add(objectMap.get("name"));
                        gradeAvgScoreList.add(objectMap.get("avgScore"));
                    }
                }
                Collections.reverse(nameList);
                Collections.reverse(gradeAvgScoreList);
                model.addAttribute("nameList", mapper.writeValueAsString(nameList));
                model.addAttribute("gradeAvgScoreList", mapper.writeValueAsString(gradeAvgScoreList));

                //班级单科平均分各班对比趋势
                List<Map<String, Object>> teamScoreList = new ArrayList<>();    //班级平均分列表
                for (Map<String, Object> objectMap : teamList) {
                    Integer teamId = (Integer) objectMap.get("teamId");
                    List<Map<String, Object>> scoreOfTeam = examWorksTeamSubjectService.findAvgScoreOfGrade(examWorks.getSchoolId(), true, gradeId, teamId, subjectCode);
                    List<Object> avgScoreList = new ArrayList<>();
                    for (Map<String, Object> map : scoreOfTeam) {
                        avgScoreList.add(map.get("avgScore") != null ? map.get("avgScore") : 0);
                    }
                    Collections.reverse(avgScoreList);
                    Map<String, Object> teamMap = new HashMap<>();
                    teamMap.put("teamName", objectMap.get("teamName"));
                    teamMap.put("teamId", objectMap.get("teamId"));
                    teamMap.put("avgScoreList", avgScoreList);
                    teamScoreList.add(teamMap);
                }
                model.addAttribute("teamScoreList", JSONArray.fromObject(teamScoreList).toString());
            }


        }
        model.addAttribute("examWorks", examWorks);
        model.addAttribute("examWorksId", examWorksId);
        model.addAttribute("gradeId", gradeId);
        model.addAttribute("subjectCode", subjectCode);
        return new ModelAndView(viewPath, model.asMap());
    }

    private List<String> getStudentRandNameList(Integer examWorksId, Integer gradeId) {
    	String key = getStudentRankNameKey(examWorksId, gradeId);
    	List<String> result = (List<String>) baseRedisCache.getCacheObject(key);
    	
    	if(result==null || result.size()==0) {
    		List<Map<String, Object>> totalList = examWorksTeamSubjectService.findExamWorkStudentRankAndTotalScore(examWorksId, gradeId);
    		result = new ArrayList<String>();
        	for (Map<String, Object> map : totalList) {
    			String name = (String) map.get("name");
    			Double totalScore = (Double) map.get("totalScore");
    			if(!(-0.01<=  totalScore && totalScore <=0.01)) {
    				result.add(name);
    			}
    		}
			baseRedisCache.setCacheObject(key, result, 60*6L);
		}
    	
		return result;
	}

	private String getTitle(Integer schoolId, String year, String code) {
        SchoolYear schoolYear = schoolYearService.findByYearAndSchoolId(schoolId, year);
        SchoolTerm schoolTerm = schoolTermService.findSchoolTermByCode(schoolId, code);
        String title = schoolYear.getName() + " " + schoolTerm.getName();
        return title;
    }

    private float teamAvgRank(List<Float> scoreList, Float nearNum) {
        float result = 0;
        if (scoreList != null && scoreList.size() > 0) {
            // 接近的数字
            // 差值实始化
            float diffNum = Math.abs(scoreList.get(0) - nearNum);
            // 最终结果
            result = scoreList.get(0);
            for (Float integer : scoreList) {
                float diffNumTemp = Math.abs(integer - nearNum);
                if (diffNumTemp < diffNum) {
                    diffNum = diffNumTemp;
                    result = integer;
                }
            }
        }
        return result;
    }

    private int verifyCount(Integer count) {
        if (count != null) {
            return count;
        } else {
            return 0;
        }
    }

    private Object getPercent(Integer count, Integer total) {
        if (total != null && total != 0 && count != null) {
            return df.format((float)count * 100 / total);
        } else {
            return 0;
        }
    }

    private String structurePath(String subPath) {
        return viewBasePath + subPath;
    }

}
