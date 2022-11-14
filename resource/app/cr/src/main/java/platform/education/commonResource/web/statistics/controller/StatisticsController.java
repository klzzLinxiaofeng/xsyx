package platform.education.commonResource.web.statistics.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import framework.generic.cache.redis.core.BaseRedisCache;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.UnderlineStyle;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.commons.beanutils.ConvertUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import platform.education.commonResource.serivce.StatisticService;
import platform.education.commonResource.serivce.unit.JDBCUitl;
import platform.education.commonResource.web.common.annotation.CurrentUser;
import platform.education.commonResource.web.common.vo.UserInfo;
import platform.education.commonResource.web.paper.contans.PaperContans;
import platform.education.commonResource.web.statistics.util.ExcelExportUtil;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.ExamQuestionVo;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.learningDesign.model.*;
import platform.education.learningDesign.service.*;
import platform.education.learningDesign.vo.TaskVo;
import platform.education.learningDesign.vo.*;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.TeamQuestionOptions;
import platform.education.paper.model.*;
import platform.education.paper.service.*;
import platform.education.paper.util.MqtNewUtil;
import platform.education.paper.vo.*;
import platform.education.resource.model.StatisticsTask;
import platform.education.resource.service.StatisticsTaskService;
import platform.education.resource.vo.StatisticsTaskCondition;
import platform.education.user.model.Profile;
import platform.education.user.service.ProfileService;
import platform.service.storage.holder.FileServiceHolder;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
@RequestMapping("/statistics")
public class StatisticsController {

    @Resource
    private StatisticsTaskService statisticsTaskService;
    @Resource
    private PjExamService pjExamService;
    @Resource
    private ExamStatService examStatService;
    @Resource
    private ExamQuestionService examQuestionService;
    @Resource
    private UserQuestionService userQuestionService;
    @Resource
    private LpTaskExamUnitService lpTaskExamUnitService;
    @Resource
    private LpTaskService lpTaskService;
    @Resource
    private TaskTeamService taskTeamService;
    @Resource
    private ExamStudentService examStudentService;
    @Resource
    private PaPaperService paPaperService;
    @Resource
    private PaQuestionService paQuestionService;
    @Resource
    private TeamService teamService;
    @Resource
    private LearningPlanService learningPlanService;
    @Resource
    private LpTaskUserService lpTaskUserService;
    @Resource
    private StudentService studentService;
    @Resource
    private LpUnitService lpUnitService;
    @Resource
    private StatisticService statisticService;
    @Resource
    private TaskService taskService;
    @Resource
    private GradeService gradeService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private TaskUserService taskUserService;
    @Resource
    private TaskInterscoreService taskInterscoreService;
    @Resource
    private UserPaperService userPaperService;
    @Resource
    private UserWrongService userWrongService;
    @Resource
    private TeamStudentService teamStudentService;
    @Resource
    private PjAptsTaskService pjAptsTaskService;
    @Resource
    private LpTaskUnitUserService lpTaskUnitUserService;
    @Resource
    private LpUnitFileService lpUnitFileService;
    @Resource
    private PaPaperHandleService paPaperHandleService;
    @Resource
    private ProfileService profileService;
    @Resource
    private BaseRedisCache<Object> baseRedisCache;
    private static Connection conn = null;
    private static final String BathPath = "/paper";
    private static final String LpBathPath = "/learningplan/statistics";


    /**
     * 返回试卷统计视图
     *
     * @param examId
     * @param request
     * @return
     */
    @RequestMapping(value = "/tj/index")
    public String index(@RequestParam(value = "examId") Integer examId, HttpServletRequest request) {
        Integer stId = 0;
        Integer go = -1;
        ExamQuestionCondition qc = new ExamQuestionCondition();
        qc.setExamId(examId);
        List<ExamQuestion> eqList = examQuestionService.findExamQuestionByCondition(qc);
        request.setAttribute("list", eqList);
        PjExam exam = pjExamService.findPjExamById(examId);
        Team team = teamService.findTeamById(exam.getTeamId());
        Task task = taskService.findTaskByUuid(exam.getJointExamCode());
        StatisticsTaskCondition stc = new StatisticsTaskCondition();
        stc.setTaskId(task.getId());
        stc.setTaskUuid(task.getUuid());
        stc.setType(PaperType.EXAM);
        List<StatisticsTask> list = statisticsTaskService.findStatisticsTaskByCondition(stc);
        if (list != null && list.size() > 0) {
            stId = list.get(0).getId();
            go = list.get(0).getState();
        }
        request.setAttribute("stId", stId);
        request.setAttribute("go", go);
        request.setAttribute("code", exam.getJointExamCode());
        request.setAttribute("name", team.getName());
        request.setAttribute("taskId", task.getId());
        return BathPath + "/statistics";
    }

    /**
     * 新试卷统计主页
     *
     * @param examId
     * @param request
     */
    @RequestMapping(value = "/pa/tj/index")
    public String paIndex(@RequestParam(value = "examId") Integer examId, HttpServletRequest request) {
        Integer stId = 0;
        Integer go = -1;
        ExamQuestionCondition qc = new ExamQuestionCondition();
        qc.setExamId(examId);
        List<ExamQuestion> eqList = examQuestionService.findExamQuestionByCondition(qc);
        request.setAttribute("list", eqList);
        PjExam exam = pjExamService.findPjExamById(examId);
        Team team = teamService.findTeamById(exam.getTeamId());
        Task task = taskService.findTaskByUuid(exam.getJointExamCode());
        StatisticsTaskCondition stc = new StatisticsTaskCondition();
        stc.setTaskId(task.getId());
        stc.setTaskUuid(task.getUuid());
        stc.setType(PaperType.EXAM);
        List<StatisticsTask> statisticsTaskList = statisticsTaskService.findStatisticsTaskByCondition(stc);
        if (statisticsTaskList != null && statisticsTaskList.size() > 0) {
            stId = statisticsTaskList.get(0).getId();
            go = statisticsTaskList.get(0).getState();
        }

        PaPaper paPaper = paPaperService.findPaPaperById(task.getPaperId());

        request.setAttribute("stId", stId);
        request.setAttribute("go", go);
        request.setAttribute("code", exam.getJointExamCode());
        request.setAttribute("teamName", team.getName());
        request.setAttribute("taskId", task.getId());
        request.setAttribute("paPaper", paPaper);

        return BathPath + "/statistics/index";
    }

    /**
     * 新试卷数据
     *
     * @param taskId
     * @param examId
     * @param request
     */
    @RequestMapping(value = "/pa/tj/getData")
    public String getData(@RequestParam(value = "taskId") Integer taskId,
                          @RequestParam(value = "examId") Integer examId, HttpServletRequest request) {
        PjExam pjExam = pjExamService.findPjExamById(examId);

        int rank = 1; //本班完成率排行
        String finishRateSelf = ""; //本班完成率
        String peopleCount = ""; //本班完成人数
        String totalPeople = "";
        List<TaskUserVo> studentList = new ArrayList<TaskUserVo>(); //年级班级完成率排名表

        List<TaskTeamVo> taskTeamVoList = this.taskTeamService.findTaskTeamByTaskId(taskId);
        int index = 1;
        int x = 0, y = 0;
        int count = 0;
        String teamAvgScore = "";
        BigDecimal gradeAvgScore = new BigDecimal("0"), score = new BigDecimal("0");
        for (TaskTeamVo taskTeamVo : taskTeamVoList) {
            if (taskTeamVo.getTeamId().equals(pjExam.getTeamId())) {
                rank = index;
                finishRateSelf = taskTeamVo.getFinishRate();
                peopleCount = taskTeamVo.getTotalOfCompleted().split("/")[0];
                totalPeople = taskTeamVo.getTotalOfCompleted().split("/")[1];
                studentList = taskTeamVo.getTaskUserVoList();
                teamAvgScore = taskTeamVo.getAverageScore();
            }
            String[] toc = taskTeamVo.getTotalOfCompleted().split("/");
            BigDecimal a = new BigDecimal(toc[0]);
            BigDecimal b = new BigDecimal(toc[1]);
            x += a.intValue();
            y += b.intValue();

            List<ExamTeamScoreVo> examTeamScoreVoList = taskTeamVo.getExamTeamScoreVoList();
            for (ExamTeamScoreVo ets : examTeamScoreVoList) {
                BigDecimal ts = new BigDecimal(ets.getTotalScore());
                if (ets.getAverageScore() != null) {
                    score = score.add(ts);
                }
                if (ets.getStudentCount() != null) {
                    count += ets.getStudentCount();
                }
            }

            if (count > 0) {
                gradeAvgScore = score.divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_UP);
            }

            index++;
        }

        BigDecimal a = new BigDecimal(x);
        BigDecimal b = new BigDecimal(y);
        float percent = (a.floatValue() / b.floatValue()) * 100;
        BigDecimal gradeFinishRate = new BigDecimal(percent);

        // 模块一 完成情况
        request.setAttribute("rank", rank);
        request.setAttribute("gradeFinishRate", subZeroAndDot(gradeFinishRate.setScale(2, BigDecimal.ROUND_HALF_UP).toString()) + "%");
        request.setAttribute("finishRateSelf", finishRateSelf);
        request.setAttribute("peopleCount", peopleCount);
        request.setAttribute("totalPeople", totalPeople);
        request.setAttribute("studentList", studentList);
        request.setAttribute("taskTeamVoList", taskTeamVoList);

        // 已提交人数
        int submitPeoples = 0;
        // 学生答题情况
        List<ExamStudentVo> examStudentVoByExamIds = examStudentService.findExamStudentVoByExamIds(new Integer[]{examId});
        for (ExamStudentVo examStudentVo : examStudentVoByExamIds) {
            if (examStudentVo.getIsFinished()) {
                submitPeoples++;
            }
        }
        // 本班题目得分率
        JSONObject question = question(examId);
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(question);

        // 模块二 答题统计
        request.setAttribute("gradeAvgScore", gradeAvgScore.toString());
        request.setAttribute("teamAvgScore", teamAvgScore);
        request.setAttribute("submitPeoples", submitPeoples);
        request.setAttribute("studentScoreList", examStudentVoByExamIds);
        request.setAttribute("questionList", jsonArray);


        List<List<ExamQuestion>> items = new ArrayList<List<ExamQuestion>>();
        ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
        examQuestionCondition.setIsDeleted(false);
        examQuestionCondition.setExamId(examId);
        Order order = new Order("pos");
        order.setAscending(true);
        List<ExamQuestion> examQuestionList = examQuestionService.findExamQuestionByCondition(examQuestionCondition, order);
        items.add(examQuestionList);
        request.setAttribute("singleList", items);

        // 题目详情,按单元返回对应试卷内容
        Order tempOrder = new Order();
        tempOrder.setAscending(true);
        tempOrder.setProperty("nodeOrder");
        ArrayList<List<PaperQuestionTree>> singleDetails = new ArrayList<List<PaperQuestionTree>>();
        // 获取试卷id
        PaPaper paper = paPaperService.findPaPaperByUUid(pjExam.getPaperUuid());
        List<PaperQuestionTree> list = paQuestionService.findPaperQuestionTreeByPaperId(paper.getId(), tempOrder, 0);
        singleDetails.add(list);
        request.setAttribute("singleDetails", singleDetails);

        return BathPath + "/statistics/content";
    }

    /**
     * 将该班级导学案，以及相关的其他班级的导学案信息
     *
     * @param taskId
     * @param number
     * @param request
     * @return
     */
    @RequestMapping(value = "/lp/tj/index")
    public String lpindex(@RequestParam(value = "taskId", required = true) Integer taskId,
                          @RequestParam(value = "number", required = false, defaultValue = "1") Integer number,
                          HttpServletRequest request) {
        List<LpTaskExamUnitVo> lpTaskExamUnitList = new ArrayList<LpTaskExamUnitVo>();
        lpTaskExamUnitList = lpTaskExamUnitService.findLpTaskExamUnitVoByTaskId(taskId);
        LpTask lt = lpTaskService.findLpTaskById(taskId);
        Team team = teamService.findTeamById(lt.getObjectId());
        request.setAttribute("lpTaskExamUnitList", lpTaskExamUnitList);
//		Integer unitId=0;
//		String code="";
//		if(list!=null&&list.size()>0){
//			unitId=list.get(0).getUnitId();
//			code=list.get(0).getJoinExamCode();
//		}
        Integer stId = 0;
        Integer go = -1;
        StatisticsTaskCondition stc = new StatisticsTaskCondition();
        stc.setTaskId(taskId);
        stc.setTaskUuid(lt.getUuid());
        stc.setType(PaperType.LEARNING_PLAN);
        List<StatisticsTask> list1 = statisticsTaskService.findStatisticsTaskByCondition(stc);
        if (list1 != null && list1.size() > 0) {
            stId = list1.get(0).getId();
            go = list1.get(0).getState();
        }
//		request.setAttribute("unitId", unitId);
//		request.setAttribute("code", code);
        request.setAttribute("teamName", team.getName());
        request.setAttribute("stId", stId);
        request.setAttribute("go", go);
//		request.setAttribute("number", number);

        LearningPlan learningPlan = learningPlanService.findLearningPlanById(lt.getLpId());

        List<LpUnit> unitList = lpUnitService.findUnitListByLpIdAndUnitType(lt.getLpId(), null);//获取导学案下的所有模块
        if (unitList == null || unitList.size() == 0) {
            return LpBathPath + "/statistics_new";
        }

        LpTaskCondition lpTaskCondition = new LpTaskCondition();
        lpTaskCondition.setLpId(lt.getLpId());
        lpTaskCondition.setObjectType("team");
        lpTaskCondition.setIsDeleted(false);
        // 查该班级导学案信息，以及相关的其他班级导学案信息
        List<LpTask> lpTaskList = lpTaskService.findLpTaskByCondition(lpTaskCondition);//获取导学案任务分配的所有班级
        if (lpTaskList != null && lpTaskList.size() > 0) {
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            for (LpTask lpTask : lpTaskList) { //遍历所有班级并统计出-- 单个模块下的 完成率 完成人数 平均分
                List<Map> list = new ArrayList<Map>();
                for (LpUnit lpUnit : unitList) {
                    Map<String, Object> map1 = new HashMap<String, Object>();
                    /* 完成率 -- 计算单个模块的完成率 */
                    List<LpTaskUnitUser> lpTaskUnitUserList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(lpTask.getId(), lpUnit.getId(), null);
                    Integer sum = 0; //总人数
                    Integer count = 0; //完成人数
                    for (LpTaskUnitUser lpTaskUnitUser : lpTaskUnitUserList) {
                        if (lpTaskUnitUser.getHasFinished()) {
                            count++;
                        }
                        sum++;
                    }
                    if (count != 0) {
                        BigDecimal big = new BigDecimal(count + "");
                        BigDecimal big2 = new BigDecimal(sum + "");
                        BigDecimal big3 = new BigDecimal(100.0f + "");
                        map1.put("finishRate", big.divide(big2, 4, BigDecimal.ROUND_HALF_UP).multiply(big3).floatValue());
                        map1.put("finishCount", count);
                    } else {
                        map1.put("finishRate", 0);
                        map1.put("finishCount", 0);
                    }

                    map1.put("unitId", lpUnit.getId());

                    /* 平均分 -- 计算单个模块的平均分 */
                    float avgScore = 0.0f;
                    int isPaper = 0;
                    if ("2".equals(lpUnit.getUnitType())) { //判断类型为试卷
                        isPaper = 1;
                        LpTaskExamUnit lpTaskExamUnit = lpTaskExamUnitService.findLpTaskExamUnitByTaskIdAndUnitId(lpTask.getId(), lpUnit.getId());
                        if (lpTaskExamUnit != null) {
                            ExamStat examStat = examStatService.findExamStatByExamId(lpTaskExamUnit.getExamId());
                            avgScore = examStat.getAverageScore() == null ? 0.0f : examStat.getAverageScore(); //班级单个模块平均分
                        }
                    }
                    map1.put("isPaper", isPaper);
                    map1.put("avgScore", avgScore);
                    list.add(map1);
                }
                map.put(lpTask.getObjectId(), list);
            }
            baseRedisCache.setCacheObject("tj:pj_task:" + taskId, map); //业务名:表名:id
        }

        request.setAttribute("unitList", unitList);
        request.setAttribute("learningPlan", learningPlan);
//		return LpBathPath+"/statistics";
        return LpBathPath + "/statistics_new";
    }

    /**
     * 返回导学案统计视图
     *
     * @param taskId
     * @param unitIdStr
     * @param request
     * @return
     */
    @RequestMapping(value = "/lp/tj/getData")
    public String getData(@RequestParam(value = "taskId", required = true) Integer taskId,
                          @RequestParam(value = "unitIdStr", required = true) String unitIdStr,
                          HttpServletRequest request) {
        LpTask lpTask = lpTaskService.findLpTaskById(taskId);
        Integer teamId = lpTask.getObjectId(); //当前班级ID
        List<TaskVo> lpTaskList = lpTaskService.findLpTaskListOrder(lpTask.getUuid());

        String[] split = unitIdStr.split(","); //选中的模块ID
        Integer[] splitInt = (Integer[]) ConvertUtils.convert(split, Integer.class);
        List<Integer> examIds = new ArrayList<Integer>();

        /*************************  完成情况  *************************/
        finishStatusData(lpTaskList, taskId, teamId, splitInt, request);

        /*************************  答题统计  *************************/
        examIds = answerStatisticsData(splitInt, taskId, teamId, request);

        /*************************  单题分析  *************************/
        singleAnalysis(examIds, request);

        return LpBathPath + "/statistics_new_content";
    }

    //单题分析
    private void singleAnalysis(List<Integer> examIds, HttpServletRequest request) {
        List<List<ExamQuestion>> items = new ArrayList<List<ExamQuestion>>();
        List<String> unitNames = new ArrayList<String>();
        List<Integer> unitIds = new ArrayList<Integer>();
        for (Integer examId : examIds) {
            ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
            examQuestionCondition.setIsDeleted(false);
            examQuestionCondition.setExamId(examId);
            Order order = new Order("pos");
            order.setAscending(true);
            List<ExamQuestion> examQuestionList = examQuestionService.findExamQuestionByCondition(examQuestionCondition, order);
            items.add(examQuestionList);

            LpTaskExamUnitCondition lpTaskExamUnitCondition = new LpTaskExamUnitCondition();
            lpTaskExamUnitCondition.setExamId(examId);
            List<LpTaskExamUnit> lpTaskExamUnitList = lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
            if (lpTaskExamUnitList != null && lpTaskExamUnitList.size() > 0) {
                LpUnit lpUnit = lpUnitService.findLpUnitById(lpTaskExamUnitList.get(0).getUnitId());
                unitNames.add(lpUnit.getTitle());
                unitIds.add(lpUnit.getId());
            }
        }
        request.setAttribute("unitNames", unitNames);
        request.setAttribute("unitIds", unitIds);
        request.setAttribute("singleList", items);

        // 题目详情,按单元返回对应试卷内容
        Order order = new Order();
        order.setAscending(true);
        order.setProperty("nodeOrder");
        ArrayList<List<PaperQuestionTree>> singleDetails = new ArrayList<List<PaperQuestionTree>>();
        for (Integer unitId : unitIds) {
            // 获取试卷id（试卷单元只有一份试卷）
            List<LpUnitFile> lpUnitFileList = lpUnitFileService.findLpUnitFileByLpUnitId(unitId);
            LpUnitFile lpUnitFile = lpUnitFileList.get(0);
            PaPaper paper = paPaperService.findPaPaperByUUid(lpUnitFile.getObjectUuid());
            List<PaperQuestionTree> list = paQuestionService.findPaperQuestionTreeByPaperId(paper.getId(), order, 0);
            singleDetails.add(list);
        }
        request.setAttribute("singleDetails", singleDetails);
    }

    //答题统计
    private List<Integer> answerStatisticsData(Integer[] splitInt, Integer taskId, Integer teamId, HttpServletRequest request) {
        float gradeAvgScore = 0.0f; //年级平均分
        float teamAvgScore = 0.0f; //班级平均分
        Integer submitPeoples = 0; //已提交人数
        Integer rankScore = 1; //年级排名：本班平均分在年级排名

        float gradeTotalScore = 0.0f; // 年级总分
        float teamTotalScore = 0.0f; //班级总分
        int i = 0;
        List<Integer> paperUnits = new ArrayList<Integer>();//试卷模块集合
        List<Float> avgList = new ArrayList<Float>(); //年级班级平均分集合


        Object cacheObject = baseRedisCache.getCacheObject("tj:pj_task:" + taskId);
        Map<Integer, Object> map = (Map<Integer, Object>) cacheObject;
        for (Entry<Integer, Object> entry : map.entrySet()) {
            Integer mapKey = entry.getKey();
            List<Map<String, Object>> mapValue = (List<Map<String, Object>>) entry.getValue();
            float avgScore = 0.0f;
            Integer t = 0;
            for (Map<String, Object> data : mapValue) {
                if (Arrays.asList(splitInt).contains(data.get("unitId")) && (Integer) data.get("isPaper") == 1) {
                    paperUnits.add((Integer) data.get("unitId"));
                    gradeTotalScore += (Float) data.get("avgScore");
                    avgScore += (Float) data.get("avgScore");
                    if (teamId.equals(mapKey)) { //本班的数据
                        teamAvgScore += (Float) data.get("avgScore");
                        i++;
                    }
                    t++;
                }
            }
            gradeTotalScore = gradeTotalScore / t;
            avgList.add(avgScore / t);
        }

        if (paperUnits.size() == 0) {
            request.setAttribute("questionList", null);
            request.setAttribute("rankScore", 0);
            request.setAttribute("gradeAvgScore", 0);
            request.setAttribute("teamAvgScore", 0);
            request.setAttribute("submitPeoples", 0);
            request.setAttribute("answerList", null);
            return new ArrayList<Integer>();
        }

        if (gradeTotalScore != 0.0f) {
            gradeAvgScore = gradeTotalScore / map.size();
            BigDecimal bg = new BigDecimal(gradeAvgScore);
            gradeAvgScore = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        }
        if (teamAvgScore != 0.0f) {
            teamAvgScore = teamAvgScore / i;
            BigDecimal bt = new BigDecimal(teamAvgScore);
            teamAvgScore = bt.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        }

        // list去重
        HashSet set = new HashSet(paperUnits);
        paperUnits.clear();
        paperUnits.addAll(set);
        List<Integer> examIds = lpTaskExamUnitService.findExamIdsByTaskIdAndUnitIds(taskId, paperUnits.toArray(new Integer[paperUnits.size()]));
        List<ExamStudentVo> examStudentVoByExamIds = examStudentService.findExamStudentVoByExamIds(examIds.toArray(new Integer[examIds.size()]));
        for (ExamStudentVo examStudentVo : examStudentVoByExamIds) {
            if (examStudentVo.getIsFinished()) {//已提交人数
                submitPeoples++;
            }
        }
        for (Float f : avgList) {
            if (f > teamAvgScore) {
                rankScore++;
            }
        }

        List<Map<String, Object>> list = question(examIds.toArray(new Integer[examIds.size()]));

        String paperUnitIds = "";
        for (Integer unitId : paperUnits) {
            paperUnitIds += unitId + ",";
        }
        paperUnitIds = paperUnitIds.substring(0, paperUnitIds.length() - 1);

        request.setAttribute("questionList", JSONArray.parseArray(JSON.toJSONString(list)));
        request.setAttribute("rankScore", rankScore);
        request.setAttribute("gradeAvgScore", subZeroAndDot(gradeAvgScore + ""));
        request.setAttribute("teamAvgScore", subZeroAndDot(teamAvgScore + ""));
        request.setAttribute("submitPeoples", submitPeoples);
        request.setAttribute("answerList", examStudentVoByExamIds);
        request.setAttribute("paperUnitIds", paperUnitIds);
        return examIds;
    }

    //完成情况
    private void finishStatusData(List<TaskVo> lpTaskList, Integer taskId, Integer teamId, Integer[] splitInt, HttpServletRequest request) {
        Integer rank = 1; //本班完成率排行
        float gradeFinishRate = 0.0f; //年级完成率
        float finishRateSelf = 0.0f; //本班完成率
        Integer peopleCount = 0; //本班完成人数
        float gradeFinish = 0.0f; //年级完成人数
        float gradeTotal = 0.0f; //年级总人数
        // 本班平均分
        float teamAvgScore = 0.0f;
        List<Map<String, String>> teamList = new ArrayList<Map<String, String>>(); //年级班级完成率排名表
        List<LpTaskUnitUserVo> studentList = new ArrayList<LpTaskUnitUserVo>(); //年级班级完成率排名表


        List<Float> finishList = new ArrayList<Float>();
        for (TaskVo taskVo : lpTaskList) {
            Map<String, String> teamMap = new HashMap<String, String>();
            List<LpTaskUnitUserVo> list = lpTaskUnitUserService.findLpTaskUnitUserVoByTaskIdAndArr(taskVo.getId(), splitInt, splitInt.length);
            Integer finishCount = 0; //班级完成人数
            for (LpTaskUnitUserVo lpTaskUnitUserVo : list) {
                finishCount += lpTaskUnitUserVo.getUnitFinish();
                if (taskVo.getTeamId().equals(teamId)) {
                    peopleCount += lpTaskUnitUserVo.getUnitFinish();
                    lpTaskUnitUserVo.setPersonRateStr(subZeroAndDot(lpTaskUnitUserVo.getPersonRate() + ""));
                    studentList.add(lpTaskUnitUserVo);
                }
                gradeFinish += lpTaskUnitUserVo.getUnitFinish();
                gradeTotal++;
            }
            float finishRate = ((float) finishCount / list.size()) * 100;
            BigDecimal b = new BigDecimal(finishRate);
            finishRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
            teamMap.put("finishRate", subZeroAndDot(finishRate + ""));
            Team team = teamService.findTeamById(taskVo.getTeamId());
            teamMap.put("teamName", team.getName());
            teamMap.put("peopleCount", finishCount + "");
            //当前班总人数
            teamMap.put("allPeopleCount", list.size() + "");
            teamList.add(teamMap);
            finishList.add(finishRate);
            if (taskVo.getTeamId().equals(teamId)) {
                finishRateSelf = finishRate;
            }
            //计算班平均分
            int i = 0;
            Object cacheObject = baseRedisCache.getCacheObject("tj:pj_task:" + taskId);
            Map<Integer, Object> map = (Map<Integer, Object>) cacheObject;
            for (Entry<Integer, Object> entry : map.entrySet()) {
                Integer mapKey = entry.getKey();
                List<Map<String, Object>> mapValue = (List<Map<String, Object>>) entry.getValue();
                for (Map<String, Object> data : mapValue) {
                    if (Arrays.asList(splitInt).contains(data.get("unitId")) && (Integer) data.get("isPaper") == 1) {
                        if (teamId.equals(mapKey)) { //本班的数据
                            teamAvgScore += (Float) data.get("avgScore");
                            i++;
                            if (teamAvgScore != 0.0f) {
                                teamAvgScore = teamAvgScore / i;
                                BigDecimal bt = new BigDecimal(teamAvgScore);
                                teamAvgScore = bt.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                            }
                        }
                    }
                }
            }
            // 当前班平均分
            teamMap.put("teamAvgScore", String.valueOf(teamAvgScore));
        }
        gradeFinishRate = (gradeFinish / gradeTotal) * 100;
        BigDecimal b = new BigDecimal(gradeFinishRate);
        gradeFinishRate = b.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        for (Float f : finishList) {
            if (f > finishRateSelf) {
                rank++;
            }
        }

//		teamList = teamList.stream().sorted(Comparator.comparing(StatisticsController::comparingByfinishRate).reversed()).collect(Collectors.toList());

        request.setAttribute("rank", rank);
        request.setAttribute("gradeFinishRate", subZeroAndDot(gradeFinishRate + ""));
        request.setAttribute("finishRateSelf", subZeroAndDot(finishRateSelf + ""));
        request.setAttribute("peopleCount", peopleCount);
        request.setAttribute("teamList", teamList);
        request.setAttribute("studentList", studentList);
    }

    private static Float comparingByfinishRate(Map<String, String> map) {
        return Float.parseFloat(map.get("finishRate"));
    }

    /**
     * 导学案统计导出
     */
    @RequestMapping(value = "/lp/export")
    public void exportExcel(
            @RequestParam(value = "taskId", required = true) Integer taskId,
            @RequestParam(value = "unitIds", required = false) String unitIds,
            HttpServletResponse response, HttpServletRequest request) {
        try {
            if (taskId != null) {
                LpTask task = lpTaskService.findLpTaskById(taskId);
                if (task != null) {
                    Team team = teamService.findTeamById(task.getObjectId());

                    //标题 班名+导学案名
                    String headline = team.getName() + " " + task.getTitle();
                    //导学案的所有单元， type：2=试卷
                    List<LpUnit> unitList = lpUnitService.findLpUnitByCatalogListOrder(task.getLpId(), null);
                    //试卷单元
                    List<LpTaskExamUnitVo> examUnitList = lpTaskExamUnitService.findLpTaskExamUnitVoByTaskId(taskId);

                    //获取选中的单元，单元名称
                    List<Integer> unitIdList = new ArrayList<Integer>();
                    List<String> unitNameList = new ArrayList<String>();
                    if (unitIds != null && !unitIds.equals("")) {
                        String[] split = unitIds.split(",");
                        for (String str : split) {
                            for (LpUnit unit : unitList) {
                                if (Integer.valueOf(str).equals(unit.getId())) {
                                    unitIdList.add(unit.getId());
                                    unitNameList.add(unit.getTitle());
                                    break;
                                }
                            }
                        }
                    } else {
                        for (LpUnit unit : unitList) {
                            unitIdList.add(unit.getId());
                            unitNameList.add(unit.getTitle());
                        }
                    }
                    //选中的考试单元
                    List<Integer> examIdList = new ArrayList<Integer>();
                    List<Integer> examUnitIdList = new ArrayList<Integer>();
                    List<String> examUnitNameList = new ArrayList<String>();
                    for (Integer unitId : unitIdList) {
                        for (LpTaskExamUnitVo examUnitVo : examUnitList) {
                            if (examUnitVo.getUnitId().equals(unitId)) {
                                examIdList.add(examUnitVo.getExamId());
                                examUnitIdList.add(examUnitVo.getUnitId());
                                examUnitNameList.add(examUnitVo.getUnitTitle());
                                break;
                            }
                        }
                    }

                    //工作表1--班级统计，表头1：统计内容、【选中单元名】、【全部汇总】
                    String sheetName1 = "班级统计";
                    String[] title1 = new String[unitNameList.size() * 2 + 3];
                    title1[0] = "统计内容";
                    title1[title1.length - 2] = "【全部汇总】";
                    title1[title1.length - 1] = "【全部汇总】";
                    for (int i = 0; i < unitNameList.size(); i++) {
                        title1[i * 2 + 1] = "【" + unitNameList.get(i) + "】";
                        title1[i * 2 + 2] = "【" + unitNameList.get(i) + "】";
                    }
                    //个人统计只显示考试单元
                    //工作表2--个人统计，表头2：学号、姓名、【选中单元名】、【全部汇总】
                    String sheetName2 = "个人统计";
                    String[] title2 = new String[examUnitNameList.size() * 2 + 4];
                    title2[0] = "学号";
                    title2[1] = "姓名";
                    title2[title2.length - 2] = "【全部汇总】";
                    title2[title2.length - 1] = "【全部汇总】";
                    for (int i = 0; i < examUnitNameList.size(); i++) {
                        title2[i * 2 + 2] = "【" + examUnitNameList.get(i) + "】";
                        title2[i * 2 + 3] = "【" + examUnitNameList.get(i) + "】";
                    }

                    String[][] content1 = getTeamStatisContent(taskId, task.getUuid(), unitIdList, title1);
                    String[][] content2 = getStudentStatisContent(examIdList, title2);

                    String fileName = headline + " 导学案.xls";
                    ExcelExportUtil.exportExcelToWebOfLP(fileName, headline, sheetName1, title1, content1, sheetName2, title2, content2, response, request);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //班级统计
    private String[][] getTeamStatisContent(Integer taskId, String uuid, List<Integer> unitIdList, String[] title) {
        //三项的返回结果：map内三个map:grade,team,rank; 每个key是unitId, value是值
        Map<String, Object> finishMap = getUnitFinishStatus(taskId, uuid, unitIdList);
        Map<String, Object> scoreMap = getUnitAverageScore(taskId, uuid, unitIdList);
        Map<String, Object> answerMap = getUnitRightAnswerPercent(taskId, uuid, unitIdList);

        Map<Integer, String> gradeFinishMap = (Map<Integer, String>) finishMap.get("grade");
        Map<Integer, String> teamFinishMap = (Map<Integer, String>) finishMap.get("team");
        Map<Integer, String> rankFinishMap = (Map<Integer, String>) finishMap.get("rank");
        Map<Integer, String> gradeScoreMap = (Map<Integer, String>) scoreMap.get("grade");
        Map<Integer, String> teamScoreMap = (Map<Integer, String>) scoreMap.get("team");
        Map<Integer, String> rankScoreMap = (Map<Integer, String>) scoreMap.get("rank");
        Map<Integer, String> gradeAnswerMap = (Map<Integer, String>) answerMap.get("grade");
        Map<Integer, String> teamAnswerMap = (Map<Integer, String>) answerMap.get("team");
        Map<Integer, String> rankAnswerMap = (Map<Integer, String>) answerMap.get("rank");

        String[][] content1 = new String[9][title.length];
        String first = "";
        String other = "";
        String value_all = "";
        Integer unitId = null;
        for (int i = 0; i < content1.length; i++) {
            value_all = "";
            switch (i) {
                case 0:
                    first = "完成情况";
                    other = "年级完成率";
                    value_all = gradeFinishMap.get(0);
                    break;
                case 1:
                    first = "完成情况";
                    other = "本班完成率";
                    value_all = teamFinishMap.get(0);
                    break;
                case 2:
                    first = "完成情况";
                    other = "年级排名";
                    value_all = rankFinishMap.get(0);
                    break;
                case 3:
                    first = "平均分统计";
                    other = "年级平均分";
                    value_all = gradeScoreMap.get(0);
                    break;
                case 4:
                    first = "平均分统计";
                    other = "年级平均分";
                    value_all = teamScoreMap.get(0);
                    break;
                case 5:
                    first = "平均分统计";
                    other = "年级排名";
                    value_all = rankScoreMap.get(0);
                    break;
                case 6:
                    first = "正确率统计";
                    other = "年级正确率";
                    value_all = gradeAnswerMap.get(0);
                    break;
                case 7:
                    first = "正确率统计";
                    other = "班级正确率";
                    value_all = teamAnswerMap.get(0);
                    break;
                case 8:
                    first = "正确率统计";
                    other = "年级排名";
                    value_all = rankAnswerMap.get(0);
                    break;
            }
            for (int j = 0; j < content1[i].length; j++) {
                if (j == 0) {
                    content1[i][j] = first;
                } else if (j == content1[i].length - 1) {
                    content1[i][j] = value_all;
                } else if (j % 2 == 1) {
                    content1[i][j] = other;
                } else if (j % 2 == 0) {
                    //填充参数
                    unitId = unitIdList.get(j / 2 - 1);
                    switch (i) {
                        case 0:
                            content1[i][j] = gradeFinishMap.get(unitId);
                            break;
                        case 1:
                            content1[i][j] = teamFinishMap.get(unitId);
                            break;
                        case 2:
                            content1[i][j] = rankFinishMap.get(unitId);
                            break;
                        case 3:
                            content1[i][j] = gradeScoreMap.get(unitId);
                            break;
                        case 4:
                            content1[i][j] = teamScoreMap.get(unitId);
                            break;
                        case 5:
                            content1[i][j] = rankScoreMap.get(unitId);
                            break;
                        case 6:
                            content1[i][j] = gradeAnswerMap.get(unitId);
                            break;
                        case 7:
                            content1[i][j] = teamAnswerMap.get(unitId);
                            break;
                        case 8:
                            content1[i][j] = rankAnswerMap.get(unitId);
                            break;
                    }
                }
            }
        }
        return content1;
    }

    //完成率
    private Map<String, Object> getUnitFinishStatus(Integer taskId, String uuid, List<Integer> unitIdList) {
        Integer[] unitIds = unitIdList.toArray(new Integer[unitIdList.size()]);
        //班级各单元完成率
        List<TaskVo> teamUnits = lpTaskService.findLpTaskVoByUnits(uuid, unitIds, 1);
        //年级各单元
        List<TaskVo> gradeUnits = lpTaskService.findLpTaskVoByUnits(uuid, unitIds, 2);
        //班级全部单元完成率
        List<TaskVo> teamSummary = lpTaskService.findLpTaskVoByUnitsSummary(uuid, unitIds, 1);
        //年级全单元
        List<TaskVo> gradeSummary = lpTaskService.findLpTaskVoByUnitsSummary(uuid, unitIds, 2);

        Map<String, Object> map = new HashMap<String, Object>();

        Map<Integer, String> gradeMap = new HashMap<Integer, String>();
        Map<Integer, String> teamMap = new HashMap<Integer, String>();
        Map<Integer, String> rankMap = new HashMap<Integer, String>();
        Float gradePercent = 0.0f;
        Float teamPercent = 0.0f;
        Integer rank = 0;
        //各单元数据
        for (Integer unitId : unitIdList) {
            //只获取本单元数据
            List<TaskVo> theTeamUnits = new ArrayList<TaskVo>();
            for (TaskVo vo : teamUnits) {
                if (vo.getUnitId().equals(unitId)) {
                    theTeamUnits.add(vo);
                }
            }
            //班级平均分、名次
            for (TaskVo vo : theTeamUnits) {
                if (vo.getId().equals(taskId)) {
                    teamPercent = correctTwoDecimalPlaces(vo.getPercent());
                    rank = theTeamUnits.indexOf(vo) + 1;
                    teamMap.put(unitId, teamPercent + "%");
                    rankMap.put(unitId, rank + "");
                    break;
                }
            }

            for (TaskVo vo : gradeUnits) {
                if (vo.getUnitId().equals(unitId)) {
                    gradePercent = correctTwoDecimalPlaces(vo.getPercent());
                    gradeMap.put(unitId, gradePercent + "%");
                    break;
                }
            }
        }
        //全部汇总数据
        gradePercent = 0.0f;
        teamPercent = 0.0f;
        rank = 0;
        for (TaskVo vo : teamSummary) {
            if (vo.getId().equals(taskId)) {
                teamPercent = correctTwoDecimalPlaces(vo.getPercent());
                rank = teamSummary.indexOf(vo) + 1;
                break;
            }
        }
        if (gradeSummary != null && !gradeSummary.isEmpty()) {
            gradePercent = correctTwoDecimalPlaces(gradeSummary.get(0).getPercent());
        }
        teamMap.put(0, teamPercent + "%");
        rankMap.put(0, rank + "");
        gradeMap.put(0, gradePercent + "%");

        map.put("grade", gradeMap);
        map.put("team", teamMap);
        map.put("rank", rankMap);

        return map;
    }

    //平均分
    private Map<String, Object> getUnitAverageScore(Integer taskId, String uuid, List<Integer> unitIdList) {
        Integer[] unitIds = unitIdList.toArray(new Integer[unitIdList.size()]);
        //本班各单元
        List<TaskExamUnitVo> teamUnits = lpTaskExamUnitService.findExamUnitWithScore(taskId, uuid, unitIds, 1);
        //年级各单元
        List<TaskExamUnitVo> gradeUnits = lpTaskExamUnitService.findExamUnitWithScore(null, uuid, unitIds, 2);
        //各班全单元
        List<TaskExamUnitVo> teamSummary = lpTaskExamUnitService.findExamUnitWithScore(null, uuid, unitIds, 3);
        //年级全单元
        List<TaskExamUnitVo> gradeSummary = lpTaskExamUnitService.findExamUnitWithScore(null, uuid, unitIds, 4);

        Map<String, Object> map = new HashMap<String, Object>();

        Map<Integer, String> gradeMap = new HashMap<Integer, String>();
        Map<Integer, String> teamMap = new HashMap<Integer, String>();
        Map<Integer, String> rankMap = new HashMap<Integer, String>();

        Float averageScore = 0.0f;
        Float gradeAverageScore = 0.0f;
        Integer rank = 0;
        for (Integer unitId : unitIdList) {
            for (TaskExamUnitVo vo : teamUnits) {
                if (vo.getUnitId().equals(unitId)) {
                    averageScore = correctTwoDecimalPlaces(vo.getAverageScore());
                    rank = vo.getGradeRank();
                    teamMap.put(unitId, averageScore + "");
                    rankMap.put(unitId, rank != null ? rank + "" : "0");
                    break;
                }
            }
            for (TaskExamUnitVo vo : gradeUnits) {
                if (vo.getUnitId().equals(unitId)) {
                    gradeAverageScore = correctTwoDecimalPlaces(vo.getAverageScore());
                    gradeMap.put(unitId, gradeAverageScore + "");
                    break;
                }
            }
        }

        //汇总数据
        averageScore = 0.0f;
        gradeAverageScore = 0.0f;
        rank = 0;
        for (TaskExamUnitVo vo : teamSummary) {
            if (vo.getTaskId().equals(taskId)) {
                averageScore = correctTwoDecimalPlaces(vo.getAverageScore());
                rank = teamSummary.indexOf(vo) + 1;
                teamMap.put(0, averageScore + "");
                rankMap.put(0, rank + "");
                break;
            }
        }
        if (gradeSummary != null && !gradeSummary.isEmpty()) {
            gradeAverageScore = correctTwoDecimalPlaces(gradeSummary.get(0).getAverageScore());
            gradeMap.put(0, gradeAverageScore + "");
        }

        map.put("grade", gradeMap);
        map.put("team", teamMap);
        map.put("rank", rankMap);

        return map;
    }

    //正确率
    private Map<String, Object> getUnitRightAnswerPercent(Integer taskId, String uuid, List<Integer> unitIdList) {
        Integer[] unitIds = unitIdList.toArray(new Integer[unitIdList.size()]);
        //本班各单元
        List<TaskExamUnitVo> teamUnits = lpTaskExamUnitService.findExamUnitWithRightAnswer(null, uuid, unitIds, 1);
        //年级各单元
        List<TaskExamUnitVo> gradeUnits = lpTaskExamUnitService.findExamUnitWithRightAnswer(null, uuid, unitIds, 2);
        //各班全单元
        List<TaskExamUnitVo> teamSummary = lpTaskExamUnitService.findExamUnitWithRightAnswer(null, uuid, unitIds, 3);
        //年级全单元
        List<TaskExamUnitVo> gradeSummary = lpTaskExamUnitService.findExamUnitWithRightAnswer(null, uuid, unitIds, 4);

        Map<String, Object> map = new HashMap<String, Object>();

        Map<Integer, String> gradeMap = new HashMap<Integer, String>();
        Map<Integer, String> teamMap = new HashMap<Integer, String>();
        Map<Integer, String> rankMap = new HashMap<Integer, String>();

        Float gradePercent = 0.0f;
        Float teamPercent = 0.0f;
        Integer rank = 0;

        for (Integer unitId : unitIdList) {
            //只获取本单元数据
            List<TaskExamUnitVo> theTeamUnits = new ArrayList<TaskExamUnitVo>();
            for (TaskExamUnitVo vo : teamUnits) {
                if (vo.getUnitId().equals(unitId)) {
                    theTeamUnits.add(vo);
                }
            }

            for (TaskExamUnitVo vo : theTeamUnits) {
                if (vo.getTaskId().equals(taskId)) {
                    teamPercent = correctTwoDecimalPlaces(vo.getPercent());
                    rank = theTeamUnits.indexOf(vo);
                    teamMap.put(unitId, teamPercent + "%");
                    rankMap.put(unitId, rank + "");
                    break;
                }
            }
            for (TaskExamUnitVo vo : gradeUnits) {
                if (vo.getUnitId().equals(unitId)) {
                    gradePercent = correctTwoDecimalPlaces(vo.getPercent());
                    gradeMap.put(unitId, gradePercent + "%");
                    break;
                }
            }
        }

        gradePercent = 0.0f;
        teamPercent = 0.0f;
        rank = 0;
        for (TaskExamUnitVo vo : teamSummary) {
            if (vo.getTaskId().equals(taskId)) {
                teamPercent = correctTwoDecimalPlaces(vo.getPercent());
                rank = teamSummary.indexOf(vo);
                teamMap.put(0, teamPercent + "%");
                rankMap.put(0, rank + "");
                break;
            }
        }
        if (gradeSummary != null && !gradeSummary.isEmpty()) {
            gradePercent = correctTwoDecimalPlaces(gradeSummary.get(0).getPercent());
            gradeMap.put(0, gradePercent + "%");
        }

        map.put("grade", gradeMap);
        map.put("team", teamMap);
        map.put("rank", rankMap);

        return map;
    }

    //学生个人统计
    private String[][] getStudentStatisContent(List<Integer> examIdList, String[] title) {
        String[][] content = new String[0][title.length];
        if (examIdList == null || examIdList.isEmpty()) {
            return content;
        }
        Integer[] examIds = examIdList.toArray(new Integer[examIdList.size()]);
        //所有试卷单元汇总
        List<ExamStudentVo> summaryList = examStudentService.findExamStudentVoByExamIdsWithType(examIds, 1);
        if (summaryList == null || summaryList.isEmpty()) {
            return content;
        } else {
            content = new String[summaryList.size() * 5][title.length];
        }
        //学生
        List<ExamStudentVo> examStudentVoList = examStudentService.findExamStudentVoByExamIdsWithType(examIds, null);
        Map<String, ExamStudentVo> studentVoMap = new HashMap<String, ExamStudentVo>();
        for (ExamStudentVo vo : examStudentVoList) {
            studentVoMap.put(vo.getExamId() + "-" + vo.getUserId(), vo);
        }

        String others = "";
        Integer examId = null;
        Integer userId = null;
        ExamStudentVo studentSummary = null;
        ExamStudentVo vo = null;
        for (int i = 0; i < content.length; i++) {
            int index = i / 5;    //求整，第几位学生
            int mod = i % 5;      //求余，第几小点
            switch (mod) {
                case 0:
                    others = "年级排名";
                    break;
                case 1:
                    others = "班级排名";
                    break;
                case 2:
                    others = "成绩";
                    break;
                case 3:
                    others = "正确率";
                    break;
                case 4:
                    others = "用时";
                    break;
            }
            studentSummary = summaryList.get(index);
            userId = studentSummary.getUserId();
            for (int j = 0; j < content[i].length; j++) {
                if (j == 0) {
                    content[i][j] = String.valueOf(studentSummary.getNumber());
                } else if (j == 1) {
                    content[i][j] = studentSummary.getName();
                } else if (j == content[i].length - 1) {
                    //汇总的年级排名、班级排名需要重新计算
                    switch (mod) {
                        case 0:
                            content[i][j] = studentSummary.getGradeRank() != null ? studentSummary.getGradeRank() + "" : "";
                            break;
                        case 1:
                            content[i][j] = studentSummary.getTeamRank() != null ? studentSummary.getTeamRank() + "" : "";
                            break;
                        case 2:
                            content[i][j] = studentSummary.getSumScore() + "";
                            break;
                        case 3:
                            content[i][j] = correctTwoDecimalPlaces(studentSummary.getRightPercent()) + "%";
                            break;
                        case 4:
                            content[i][j] = secondsToTime(studentSummary.getTotalTime()) + "";
                            break;
                    }
                } else if (j % 2 == 0) {
                    content[i][j] = others;
                } else if (j % 2 == 1) {
                    examId = examIdList.get(j / 2 - 1);
                    vo = studentVoMap.get(examId + "-" + userId);
                    //判断vo分数是否-1，输出值都是“缺考”；后面合并单元格时，判断是否有缺考字样，有就合并
                    switch (mod) {
                        case 0:
                            content[i][j] = vo.getGradeRank() != null ? vo.getGradeRank() + "" : "";
                            break;
                        case 1:
                            content[i][j] = vo.getTeamRank() != null ? vo.getTeamRank() + "" : "";
                            break;
                        case 2:
                            content[i][j] = vo.getScore() != -1 ? vo.getScore() + "" : "";
                            break;
                        case 3:
                            content[i][j] = correctTwoDecimalPlaces(vo.getRightPercent()) + "%";
                            break;
                        case 4:
                            content[i][j] = secondsToTime(vo.getTotalTime()) + "";
                            break;
                    }
                }
            }
        }
        return content;
    }

    //四舍五入，小数点后两位
    private float correctTwoDecimalPlaces(Double number) {
        if (number != null) {
            return new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        } else {
            return 0f;
        }
    }

    private float correctTwoDecimalPlaces(Float number) {
        if (number != null) {
            return new BigDecimal(number).setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        } else {
            return 0f;
        }
    }


    @RequestMapping(value = "/lp/tj/stat")
    @ResponseBody
    public JSONObject lpstat(
            @RequestParam(value = "taskId", required = true) Integer taskId, HttpServletRequest request) {
        LpTask lpTask = lpTaskService.findLpTaskById(taskId);
        JSONObject model = new JSONObject();

        if (lpTask != null) {
//    		String pancake="[]";
            //**获取班级任务完成情况排名信息*//*
            Map<String, Object> teamsOrder = geTeamOrderList(lpTask.getUuid(), lpTask.getId());
            //**排名信息列表*//*
            model.put("teamsOrder", teamsOrder.get("list"));
            //**本班的班级排名*//*
            model.put("teamOrder", teamsOrder.get("teamOrder"));
            //**本班完成率*//*
            model.put("teamPercent", teamsOrder.get("teamPercent"));
            //**本班完成人数*//*
            model.put("teamCount", teamsOrder.get("teamCount"));
            //**年级完成率*//*
            model.put("totalPercent", teamsOrder.get("totalPercent"));

            //**获取本班完成信息情况*//*
            Map<String, Object> detail = getTeamTaskList(lpTask.getId());
            //**本班完成情况列表*//*
            model.put("list", detail.get("list"));
            model.put("unitBoard", detail.get("unitBoard")); //统计模块列表
            model.put("finishCount", detail.get("finishCount")); //已完成数
            model.put("unFinishCount", detail.get("unFinishCount")); //未完成数
//			pancake="[['未完成':"+detail.get("noFinishCount")+"],['完成':"+detail.get("finishCount")+"]]";
//			System.out.println(pancake);
//			model.put("pancake", pancake);
            LearningPlan lp = learningPlanService.findLearningPlanById(lpTask.getLpId());
            if (lp != null) {
                model.put("lpName", lp.getTitle());
            }
        }
        System.out.println(model.toJSONString());
        return model;
    }

    @RequestMapping(value = "/tj/qContent")
    public String th(@RequestParam(value = "questionUuid", required = true) String questionUuid,
                     @RequestParam(value = "examId", required = true) Integer examId, HttpServletRequest request) {
        PjExam pj = pjExamService.findPjExamById(examId);
        PaPaper pa = paPaperService.findPaPaperByUUid(pj.getPaperUuid());
        PaQuestionVo vo = paQuestionService.findPosQuestionDetail(pa.getId(), questionUuid);
        request.setAttribute("vo", vo);
        return BathPath + "/posQuestion";
    }

    @RequestMapping(value = "/th")
    @ResponseBody
    public Object th(
            @RequestParam(value = "examId", required = true) Integer examId) {
        ExamQuestionCondition qc = new ExamQuestionCondition();
        qc.setExamId(examId);
        List<ExamQuestion> eqlist = examQuestionService.findExamQuestionByCondition(qc);
        return eqlist;
    }

    @RequestMapping(value = "/tj/stat")
    @ResponseBody
    public JSONObject stat(@RequestParam("examId") Integer examId) {
        PjExam pj = pjExamService.findPjExamById(examId);
        StatisticsTeamScore vo = pjExamService.findTeamAvgByCode(
                pj.getJointExamCode(), PaperType.PAPER_CODE, 1);
        StatisticsTeamScore vo1 = pjExamService.findTeamAvgByCode(
                pj.getJointExamCode(), PaperType.PAPER_CODE, 0);
        List<TeamAvgScroeVo> teamVo = vo.getTeamAvgScore();
        List<TeamAvgScroeVo> teamVo1 = vo1.getTeamAvgScore();
        String[] names = new String[teamVo.size() + 1];
        float[] scores = new float[teamVo.size() + 1];
        names[0] = "年级";
        scores[0] = vo.getGradeScoreAvg();
        int i = 1;
        int rank = 1;
        float score = 0.0f;
        for (TeamAvgScroeVo t : teamVo) {
            names[i] = t.getTeamNum() + "班";
            scores[i] = t.getAverageScore() == null ? 0.0f : t
                    .getAverageScore();
            if (t.getExamId().intValue() == examId.intValue()) {
                rank = t.getTeamRank();
                score = t.getAverageScore() == null ? 0.0f : t
                        .getAverageScore();
            }
            i++;
        }
        Order order = new Order();
        order.setProperty("score");
        List<ExamStudent> eslist = examStudentService.findExamStudentsByExamId(examId, order);
        List<Map<String, Object>> stlist = new ArrayList<Map<String, Object>>();
        ExamStatCondition esc = new ExamStatCondition();
        esc.setExamId(examId);
        ExamStat estat = examStatService.findExamStatByCondition(esc).get(0);
        for (ExamStudent es : eslist) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("number", es.getSchoolNumber() == null ? "无" : es.getSchoolNumber());
            map.put("name", es.getName());
            map.put("score", es.getScore());
            float percent = 0f;

            if (es.getScore() == null || es.getScore() == 0 || estat.getFullScore() == 0 || estat.getFullScore() == 0) {

            } else {
                percent = Math.round((es.getScore() / estat.getFullScore()) * 100);
            }
            map.put("percent", percent);
            stlist.add(map);
        }
        JSONObject obj = new JSONObject();
        obj.put("yList", names);
        obj.put("xList", scores);
        obj.put("gradeAvg", vo.getGradeScoreAvg());
        obj.put("teamAvg", score);
        obj.put("rank", rank);
        obj.put("table", teamVo1);
        obj.put("table2", stlist);
        System.out.println(obj.toJSONString());
        return obj;
    }

    @RequestMapping(value = "/tj/all/question")
    @ResponseBody
    public JSONObject allQuestion(@RequestParam("taskId") Integer taskId) {
        LpTask task = lpTaskService.findLpTaskById(taskId);
        LpTaskExamUnitCondition lpTaskExamUnitCondition = new LpTaskExamUnitCondition();
        lpTaskExamUnitCondition.setTaskId(taskId);
        List<LpTaskExamUnit> lteu = lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
        Map<Integer, LpTaskExamUnit> map = new HashMap<Integer, LpTaskExamUnit>();
        for (LpTaskExamUnit lu : lteu) {
            map.put(lu.getUnitId(), lu);
        }
        List<LpUnit> units = lpUnitService.findLpUnitByCatalogListOrder(task.getLpId(), 2);
        String[] name = new String[units.size() + 1];
        Float[] teamAvgScore = new Float[units.size() + 1];
        Float[] gradeAvgScore = new Float[units.size() + 1];
        Float[] sumScore = new Float[units.size()];
        int i = 0;
        List<Map<Integer, ExamStudent>> esMapList = new ArrayList<Map<Integer, ExamStudent>>();
        List<ExamStudent> frist = new ArrayList<ExamStudent>();
        for (LpUnit lu : units) {
            LpTaskExamUnit ltu = map.get(lu.getId());
            Integer examId = ltu.getExamId();
            PjExam pj = pjExamService.findPjExamById(examId);
            ExamStat eStat = examStatService.findExamStatByExamId(examId);
            StatisticsTeamScore vo = pjExamService.findTeamAvgByCode(
                    pj.getJointExamCode(), PaperType.LEARNING_CODE, 1);
            name[i] = lu.getTitle();
            teamAvgScore[i] = vo.getTeamMap().get(examId).getAverageScore();
            gradeAvgScore[i] = vo.getGradeScoreAvg();
            sumScore[i] = eStat.getFullScore() == null ? 0 : eStat.getFullScore();
            ExamStudentCondition condition = new ExamStudentCondition();
            condition.setExamId(examId);
            List<ExamStudent> esList = examStudentService.findExamStudentByCondition(condition);
            Map<Integer, ExamStudent> esMap = new HashMap<Integer, ExamStudent>();
            for (ExamStudent es : esList) {
                esMap.put(es.getUserId(), es);
            }
            if (i == 0) {
                frist.addAll(esList);
            }
            esMapList.add(esMap);
            i++;
        }
        Integer size = 3 + units.size();
        String[] line = new String[size];
        line[0] = "序号";
        line[1] = "学生姓名";
        line[size - 1] = "总分";
        int j = 2;
        for (LpUnit lu : units) {
            line[j] = lu.getTitle();
            j++;
        }
        Integer count = 0;
        List<String[]> tab = new ArrayList<String[]>();
        Integer x = 1;
        Integer userCount = 0;
        float allScore = 0;
        for (ExamStudent es : frist) {
            String[] lines = new String[size];
            lines[0] = x.toString();
            Boolean c = false;
            if (es.getScore() == -1) {

            } else {
                c = true;
            }
            Float sumSore = es.getScore() == -1 ? 0 : es.getScore();
            lines[1] = es.getName();
            lines[2] = es.getScore().toString();
            for (int z = 1; z < esMapList.size(); z++) {
                ExamStudent obj = esMapList.get(z).get(es.getUserId());
                lines[2 + z] = obj.getScore().toString();

                if (c) {

                } else {
                    if (obj.getScore() != -1) {
                        c = true;
                    }
                }
                BigDecimal sumSoreb = new BigDecimal(sumSore + "");
                BigDecimal objScoreb = new BigDecimal((obj.getScore() == -1.0f ? 0 : obj.getScore()) + "");
                sumSore = sumSoreb.add(objScoreb).floatValue();
            }


            if (!c) {
                sumSore = -1.0f;
            } else {
                userCount++;
            }
            lines[size - 1] = sumSore.toString();
            if (sumSore != -1) {
                BigDecimal allScoreb = new BigDecimal(allScore + "");
                BigDecimal sumSoreb = new BigDecimal(sumSore + "");
                allScore = allScoreb.add(sumSoreb).floatValue();
            }
            tab.add(lines);
            x++;
        }
        Collections.sort(tab, new ValueComparator());
        x = 1;
        for (String[] s : tab) {
            s[0] = x.toString();
            x++;
        }
        tab.add(0, line);
        Float teamAvg = 0.0f;
        Float gradeAvg = 0.0F;
        Float sScore = 0.0F;
        for (int h = 0; h < units.size(); h++) {
            teamAvg += teamAvgScore[h];
            gradeAvg += gradeAvgScore[h];
            sScore += sumScore[h];
        }
        BigDecimal b1 = new BigDecimal(allScore + "");
        BigDecimal b3 = new BigDecimal((userCount == 0 ? 1 : userCount) + "");
        BigDecimal b2 = new BigDecimal(units.size() + "");
        teamAvg = b1.divide(b3, 2, BigDecimal.ROUND_HALF_UP).floatValue();
        List<LpTaskExamUnitVo> list = lpTaskExamUnitService.findLpTaskExamUnitVoByTaskUuid(task.getUuid());
        Integer[] examIds = new Integer[list.size()];
        for (int n = 0; n < list.size(); n++) {
            LpTaskExamUnitVo vo = list.get(n);
            examIds[n] = vo.getExamId();
        }
        float f = examStatService.findSumScoreByExamIds(examIds);
        Integer gradeCount = examStudentService.findExamStudentFinishByExamIds(examIds).size();

        if (gradeCount == 0) {
            gradeCount = 1;
        }
        b1 = new BigDecimal(f + "");
        b3 = new BigDecimal(gradeCount + "");
        gradeAvg = b1.divide(b3, 2, BigDecimal.ROUND_HALF_UP).floatValue();
        int length = teamAvgScore.length - 1;
        teamAvgScore[length] = teamAvg;
        gradeAvgScore[length] = gradeAvg;
        name[length] = "总平均分";
        JSONObject obj = new JSONObject();
        obj.put("teamAvg", teamAvg);
        obj.put("gradeAvg", gradeAvg);
        obj.put("sumScore", sScore);
        obj.put("studentCount", frist.size());
        obj.put("count", userCount);
        obj.put("teamList", teamAvgScore);
        obj.put("gradeList", gradeAvgScore);
        obj.put("names", name);
        obj.put("tab", tab);
        return obj;
    }

    @RequestMapping(value = "/tj/question")
    @ResponseBody
    public JSONObject question(@RequestParam("examId") Integer examId) {
        ExamStat eStat = examStatService.findExamStatByExamId(examId);
        ExamQuestionCondition ec = new ExamQuestionCondition();
        ec.setExamId(examId);
        List<ExamQuestion> eqlist = examQuestionService
                .findExamQuestionByCondition(ec);
        int[] number = new int[eqlist.size()];
        int[] rank = new int[eqlist.size()];
        float[] teamRate = new float[eqlist.size()];
        float[] gradeRate = new float[eqlist.size()];
        for (int i = 0; i < eqlist.size(); i++) {
            ExamQuestion eq = eqlist.get(i);
            float f1 = eq.getGradeScoringRate() == null ? 0 : eq.getGradeScoringRate();
            float f2 = eq.getTeamScoringRate() == null ? 0 : eq.getTeamScoringRate();
            BigDecimal b1 = new BigDecimal(Float.toString(f1));
            BigDecimal b2 = new BigDecimal(Float.toString(f2));
            BigDecimal c = new BigDecimal(Float.toString(100f));
            number[i] = i + 1;
            rank[i] = eq.getGradeRank();
            teamRate[i] = b2.multiply(c).floatValue();
            gradeRate[i] = b1.multiply(c).floatValue();
        }

        PjExam pj = pjExamService.findPjExamById(examId);
        StatisticsTeamScore vo = pjExamService.findTeamAvgByCode(
                pj.getJointExamCode(), pj.getExamType(), 1);
        List<TeamAvgScroeVo> teamVo = vo.getTeamAvgScore();

        int trank = 1;
        float score = 0.0f;
        for (TeamAvgScroeVo t : teamVo) {
            if (t.getExamId().intValue() == examId.intValue()) {
                trank = t.getTeamRank();
                score = t.getAverageScore() == null ? 0.0f : t
                        .getAverageScore();
            }
        }
        Order order = new Order();
        order.setProperty("score");
        List<Map<String, Object>> stlist = new ArrayList<Map<String, Object>>();
        List<ExamStudent> eslist = examStudentService.findExamStudentsByExamId(examId, order);
        int i = 1;
        int count = 0;
        for (ExamStudent es : eslist) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("number", i);
            i++;
            map.put("name", es.getName());
            map.put("score", es.getScore());
            map.put("sum_time", es.getSumTime() == null ? -1 : es.getSumTime());
            map.put("userId", es.getUserId());
            if (es.getScore() != -1) {
                count++;
            }
            stlist.add(map);
        }
        JSONObject obj = new JSONObject();
        obj.put("number", number);
        obj.put("rank", rank);
        obj.put("teamRate", teamRate);
        obj.put("gradeRate", gradeRate);
        obj.put("gradeAvgScore", vo.getGradeScoreAvg());
        obj.put("teamAvgScore", score);
        obj.put("studentList", stlist);
        obj.put("studentCount", eslist.size());
        obj.put("sumScore", eStat.getFullScore());
        obj.put("count", count);
        System.out.println(obj.toJSONString());
        return obj;
    }

    /**
     * 根据examId返回对应的单题列表的统计数据
     *
     * @param examIds
     * @return
     */
    public List<Map<String, Object>> question(@RequestParam("examIds[]") Integer[] examIds) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (Integer examId : examIds) {
            LpTaskExamUnitCondition lpTaskExamUnitCondition = new LpTaskExamUnitCondition();
            lpTaskExamUnitCondition.setExamId(examId);
            List<LpTaskExamUnit> lpTaskExamUnitList = lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
            String unitName = "";
            if (lpTaskExamUnitList != null && lpTaskExamUnitList.size() > 0) {
                LpUnit lpUnit = lpUnitService.findLpUnitById(lpTaskExamUnitList.get(0).getUnitId());
                unitName = lpUnit.getTitle();
            }

            ExamStat eStat = examStatService.findExamStatByExamId(examId);
            ExamQuestionCondition ec = new ExamQuestionCondition();
            ec.setExamId(examId);
            List<ExamQuestion> eqlist = examQuestionService
                    .findExamQuestionByCondition(ec);
            int[] number = new int[eqlist.size()];
            int[] rank = new int[eqlist.size()];
            float[] teamRate = new float[eqlist.size()];
            float[] gradeRate = new float[eqlist.size()];
            for (int i = 0; i < eqlist.size(); i++) {
                ExamQuestion eq = eqlist.get(i);
                float f1 = eq.getGradeScoringRate() == null ? 0 : eq.getGradeScoringRate();
                float f2 = eq.getTeamScoringRate() == null ? 0 : eq.getTeamScoringRate();
                BigDecimal b1 = new BigDecimal(Float.toString(f1));
                BigDecimal b2 = new BigDecimal(Float.toString(f2));
                BigDecimal c = new BigDecimal(Float.toString(100f));
                number[i] = i + 1;
                rank[i] = eq.getGradeRank();
                teamRate[i] = b2.multiply(c).floatValue();
                gradeRate[i] = b1.multiply(c).floatValue();
            }

            PjExam pj = pjExamService.findPjExamById(examId);
            StatisticsTeamScore vo = pjExamService.findTeamAvgByCode(
                    pj.getJointExamCode(), pj.getExamType(), 1);
            List<TeamAvgScroeVo> teamVo = vo.getTeamAvgScore();

            int trank = 1;
            float score = 0.0f;
            for (TeamAvgScroeVo t : teamVo) {
                if (t.getExamId().intValue() == examId.intValue()) {
                    trank = t.getTeamRank();
                    score = t.getAverageScore() == null ? 0.0f : t
                            .getAverageScore();
                }
            }
            Order order = new Order();
            order.setProperty("score");
            List<Map<String, Object>> stlist = new ArrayList<Map<String, Object>>();
            List<ExamStudent> eslist = examStudentService.findExamStudentsByExamId(examId, order);
            int i = 1;
            int count = 0;
            for (ExamStudent es : eslist) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("number", i);
                i++;
                map.put("name", es.getName());
                map.put("score", es.getScore());
                map.put("sum_time", es.getSumTime() == null ? -1 : es.getSumTime());
                map.put("userId", es.getUserId());
                if (es.getScore() != -1) {
                    count++;
                }
                stlist.add(map);
            }
            Map<String, Object> obj = new HashMap<String, Object>();
            obj.put("number", number);
            obj.put("rank", rank);
            obj.put("teamRate", teamRate);
            obj.put("gradeRate", gradeRate);
            obj.put("gradeAvgScore", vo.getGradeScoreAvg());
            obj.put("teamAvgScore", score);
            obj.put("studentList", stlist);
            obj.put("studentCount", eslist.size());
            obj.put("sumScore", eStat.getFullScore());
            obj.put("count", count);
            obj.put("unitName", unitName);
            list.add(obj);
        }
        return list;
    }

    @RequestMapping(value = "/tj/questionRank")
    @ResponseBody
    public JSONObject questionRank(@RequestParam("examId") Integer examId) {
        Order o = new Order();
        o.setProperty("team_scoring_rate,pos");
        o.setAscending(true);
        Page page = new Page();
        page.setCurrentPage(1);
        page.setPageSize(10);
        ExamQuestionCondition condition = new ExamQuestionCondition();
        condition.setExamId(examId);
        List<ExamQuestion> eqlist = examQuestionService.findExamQuestionByCondition(condition, page, o);
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        for (ExamQuestion eq : eqlist) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pos", eq.getPos());
            map.put("wrong", eq.getTeamScoringRate() == null ? "100%" : 100 - (eq.getTeamScoringRate() * 100) + "%");
            map.put("examQuestionId", eq.getId());
            list.add(map);
        }
        JSONObject obj = new JSONObject();
        obj.put("list", list);
        return obj;
    }

    @RequestMapping(value = "/check/five")
    @ResponseBody
    public String singleQuestion(
            @RequestParam("stId") Integer stId) {
        StatisticsTask st = statisticsTaskService.findStatisticsTaskById(stId);
        Date date = st.getModifyDate();
        Date date1 = new Date();
        Calendar c1 = Calendar.getInstance();
        c1.setTime(date);
        c1.add(Calendar.MINUTE, 5);//加上五分钟
        if (date1.before(c1.getTime())) {
            return "fail";
        }
        return "success";
    }

    @RequestMapping(value = "/check/tj")
    @ResponseBody
    public Integer checkTj(
            @RequestParam("stId") Integer stId) {
        StatisticsTask st = statisticsTaskService.findStatisticsTaskById(stId);
        return st.getState();
    }

    @RequestMapping(value = "/paper/tj")
    @ResponseBody
    public Integer tj(
            @RequestParam(required = false, value = "objectId") Integer objectId,
            @RequestParam(required = false, value = "type") final Integer type,
            @RequestParam(required = false, value = "stId") Integer stId,
            @CurrentUser final UserInfo user
    ) {
        Integer objId1 = objectId;
        String uuid = "";
        if (type == PaperType.EXAM) {

            platform.education.paper.vo.TaskVo vo = taskService.findTaskVoByExamId(objectId);
            objId1 = vo.getId();
            uuid = vo.getUuid();
        } else {
            LpTask task = lpTaskService.findLpTaskById(objId1);
            uuid = task.getUuid();
        }
        final Integer objId = objectId;
        StatisticsTask stc = new StatisticsTask();
        int id = 0;
        final Integer ids;
        if (stId.intValue() == 0) {
            stc.setTaskId(objId1);
            stc.setType(type);
            stc.setCreateDate(new Date());
            stc.setModifyDate(new Date());
//			stc.setUserId(user.getUserId());
            stc.setState(0);
            stc.setTaskUuid(uuid);
            statisticsTaskService.add(stc);
            id = stc.getId();
        } else {
            stc = statisticsTaskService.findStatisticsTaskById(stId);
            stc.setState(0);
            statisticsTaskService.modify(stc);
            id = stc.getId();
        }
        ids = id;
        new Thread(new Runnable() {
            @Override
            public void run() {
                statisticService.statisticHandle(objId, type, ids);
            }
        }).start();
        ;
//		statisticService.statisticHandle(objectId, type,stc.getId());
        return ids;
    }

    /**
     * 根据单题id和单元id，返回答题分布对象
     *
     * @param examQuestionId
     * @param unitId
     * @return
     */
    @RequestMapping(value = "/tj/anwers")
    @ResponseBody
    public JSONObject anwers(
            @RequestParam("examQuestionId") Integer examQuestionId,
            @RequestParam(value = "unitId", required = false) Integer unitId) {
        ExamQuestion eq = examQuestionService
                .findExamQuestionById(examQuestionId);
        Integer teamId = 0;
        Integer taskId = 0;
        String questionUuId = eq.getQuestionUuid();
        if (unitId != null) {
            LpTaskExamUnitCondition lc = new LpTaskExamUnitCondition();
            lc.setExamId(eq.getExamId());
            LpTaskExamUnit lu = lpTaskExamUnitService
                    .findLpTaskExamUnitByCondition(lc).get(0);
            taskId = lu.getTaskId();
            LpTask lptask = lpTaskService.findLpTaskById(taskId);
            teamId = lptask.getObjectId();
        } else {
            TaskTeamCondition tt = new TaskTeamCondition();
            tt.setPjExamId(eq.getExamId());
            TaskTeam t = taskTeamService.findTaskTeamByCondition(tt).get(0);
            taskId = t.getTaskId();
            teamId = t.getTeamId();
        }
        List<TeamQuestionOptions> toList = userQuestionService
                .getTeamQuestionOptionsByQuestionUuIdAndOwnerId(teamId, taskId,
                        questionUuId, unitId);
        List<ExamStudent> esList = new ArrayList<ExamStudent>();
        ExamStudentCondition condition = new ExamStudentCondition();
        condition.setExamId(eq.getExamId());
        esList = examStudentService.findExamStudentByCondition(condition);
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (ExamStudent es : esList) {
            map.put(es.getUserId(), es.getName());
        }
        JSONObject obj = new JSONObject();
        for (TeamQuestionOptions tq : toList) {
            List<String> name = new ArrayList<String>();
            List<Integer> ids = new ArrayList<Integer>();
            if (tq.getUserIds() != null) {
                ids = tq.getUserIds();
            }
            for (Integer id : ids) {
                String stname = "匿名";
                if (map.get(id) != null) {
                    stname = map.get(id);
                }
                name.add(stname);
            }
            obj.put(tq.getQuestionOption(), name);
        }
        return obj;
    }

    /**
     * (旧版)试卷统计，返回该份试卷各班平均分
     *
     * @param code
     * @param questionUuid
     * @param unitId
     * @return
     */
    @RequestMapping(value = "/tj/questionRate")
    @ResponseBody
    public JSONObject questionRate(@RequestParam(value = "code") String code,
                                   @RequestParam(value = "questionUuid") String questionUuid,
                                   @RequestParam(value = "unitId", required = false) Integer unitId) {
        String type = PaperContans.PAPER_CODE;
        if (unitId != null) {
            type = PaperContans.LEARNINGPLAN;
        }
        List<ExamQuestionVo> eqlist = examQuestionService
                .findExamQuestionByCodeAndQuestionUuid(code, questionUuid, type);
        String[] names = new String[eqlist.size() + 1];
        float[] avgs = new float[eqlist.size() + 1];
        for (int i = 0; i < eqlist.size(); i++) {
            ExamQuestionVo vo = eqlist.get(i);
            names[i + 1] = vo.getTeamNumber() + "班";
            float bss = vo.getTeamScoringRate() == null ? 0 : vo.getTeamScoringRate();
            BigDecimal big = new BigDecimal(bss + "");
            BigDecimal big1 = new BigDecimal(100f + "");
            avgs[i + 1] = big.multiply(big1).floatValue();
        }
        JSONObject obj = new JSONObject();
        names[0] = "年级";
        if (eqlist.size() > 0) {
//			avgs[0] = Math.round((eqlist.get(0).getGradeScoringRate()==null?0:eqlist.get(0).getGradeScoringRate())*100);
            float f = eqlist.get(0).getGradeScoringRate() == null ? 0 : eqlist.get(0).getGradeScoringRate();
            BigDecimal big1 = new BigDecimal(100f + "");
            BigDecimal big = new BigDecimal(f + "");
            avgs[0] = big.multiply(big1).floatValue();
            obj.put("names", names);
            obj.put("avg", avgs);
        }
        System.out.println(obj.toJSONString());
        return obj;
    }

    @RequestMapping(value = "/export")
    public void export(@RequestParam("taskId") Integer taskId, @RequestParam("type") Integer type,
                       @RequestParam("teamId") Integer teamId,
                       HttpServletResponse response) throws Exception {
        OutputStream os = null;
        WritableWorkbook wwb = null;
        if (type.intValue() == PaperType.LEARNING_PLAN) {
            LpTask task = lpTaskService.findLpTaskById(taskId);
            teamId = task.getObjectId();
        }
        try {
            Team team = teamService.findTeamById(teamId);
            Grade g = gradeService.findGradeById(team.getGradeId());
            String publishName = "";
            String taskName = "";
            String publishDate = "";
            List<String> paperName = new ArrayList<String>();
            List<Integer> examIds = new ArrayList<Integer>();
            SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
            if (type.intValue() == PaperType.EXAM) {
                TaskTeam tt = taskTeamService.findTaskTeamUnique(taskId, teamId);
                Task task = taskService.findTaskById(taskId);
                publishName = task.getPublisherName();
                paperName.add(task.getTitle());
                examIds.add(tt.getPjExamId());
                taskName = task.getTitle();
                publishDate = sf.format(task.getCreateDate());
            } else {
                LpTask task = lpTaskService.findLpTaskById(taskId);
                Integer userId = task.getUserId();
                Teacher t = new Teacher();
                t = teacherService.findOfUser(team.getSchoolId(), userId);
                if (t != null) {
                    publishName = t.getName();
                }
                taskName = task.getTitle();
                List<LpTaskExamUnitVo> luList = lpTaskExamUnitService.findLpTaskExamUnitVoByTaskId(taskId);
                for (LpTaskExamUnitVo vo : luList) {
                    examIds.add(vo.getExamId());
                    paperName.add(vo.getUnitTitle());
                }
                publishDate = sf.format(task.getCreateDate());
            }
            String fileName = publishDate + taskName + g.getName() + team.getTeamNumber() + "班";
            fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
            os = response.getOutputStream();// 取得输出流
            response.reset();
            response.setHeader("Content-disposition", "attachment;filename="
                    + fileName + ".xls");// 设定输出文件头
            response.setContentType("application/msexcel");// 定义输出类型
            wwb = Workbook.createWorkbook(os);
            WritableSheet ws = wwb.createSheet(taskName, 1);
            ws.getSettings().setShowGridLines(true);
            WritableFont wf_title = new WritableFont(WritableFont.ARIAL,
                    10, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE); // 定义格式 字体 下划线 斜体 粗体

            WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
            wcf_title.setWrap(true);
            wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
            wcf_title
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            ws.setRowView(0, 500);
            jxl.write.Label label1 = new jxl.write.Label(0, 0,
                    "任务名称");
            ws.addCell(label1);
            label1 = new jxl.write.Label(1, 0,
                    taskName);
            ws.addCell(label1);
            label1 = new jxl.write.Label(0, 1,
                    "发布日期");
            ws.addCell(label1);
            label1 = new jxl.write.Label(1, 1,
                    publishDate);
            ws.addCell(label1);
            label1 = new jxl.write.Label(0, 2,
                    "发布人");
            ws.addCell(label1);
            label1 = new jxl.write.Label(1, 2,
                    publishName);
            ws.addCell(label1);
            label1 = new jxl.write.Label(0, 3,
                    "年级");
            ws.addCell(label1);
            label1 = new jxl.write.Label(1, 3,
                    g.getName());
            ws.addCell(label1);
            label1 = new jxl.write.Label(0, 4,
                    "班级");
            ws.addCell(label1);
            label1 = new jxl.write.Label(1, 4,
                    team.getTeamNumber() + "班");
            ws.addCell(label1);
            label1 = new jxl.write.Label(0, 6,
                    "学号");
            ws.addCell(label1);
            label1 = new jxl.write.Label(1, 6,
                    "姓名");
            ws.addCell(label1);
            int i = 2;
            for (String name : paperName) {
                if (type.intValue() == PaperType.EXAM) {
                    name = "成绩";
                }
                label1 = new jxl.write.Label(i, 6,
                        name);
                ws.addCell(label1);
                i++;
            }
            Integer examId = examIds.get(0);
            List<ExamStudent> list = examStudentService.findExamStudentsByExamId(examId, null);
            i = 7;
            for (ExamStudent es : list) {
                label1 = new jxl.write.Label(0, i,
                        es.getSchoolNumber() == null || es.getSchoolNumber().equals("") ? "无" : es.getSchoolNumber());
                ws.addCell(label1);
                label1 = new jxl.write.Label(1, i,
                        es.getName());
                ws.addCell(label1);
                label1 = new jxl.write.Label(2, i,
                        es.getScore() == -1 ? "缺考" : es.getScore().toString());
                ws.addCell(label1);
                i++;
            }
            for (int j = 1; j < examIds.size(); j++) {
                List<ExamStudent> objList = examStudentService.findExamStudentsByExamId(examId, null);
                i = 7;
                for (ExamStudent es : list) {
                    label1 = new jxl.write.Label(2 + j, i,
                            es.getScore() == -1 ? "缺考" : es.getScore().toString());
                    ws.addCell(label1);
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wwb != null) {
                wwb.write();
                wwb.close();
            }
            if (os != null) {
                os.close();
            }

        }

    }

    @RequestMapping(value = "fixData")
    public void fixData() {
        StatisticsTaskCondition stc = new StatisticsTaskCondition();
        List<StatisticsTask> list = statisticsTaskService.findStatisticsTaskByCondition(stc);
        if (list != null) {
            for (StatisticsTask st : list) {
                String uuid = "";
                if (st.getType() == PaperType.EXAM) {
                    Task task = taskService.findTaskById(st.getId());
                    uuid = task.getUuid();
                } else {
                    LpTask task = lpTaskService.findLpTaskById(st.getId());
                    uuid = task.getUuid();
                }
                st.setTaskUuid(uuid);
                statisticsTaskService.modify(st);
            }
        }

    }

    @RequestMapping(value = "fixData1")
    public void fixData1() throws Exception {
        try {
            conn = getConn();
            String sql = "SELECT uuid FROM em_exam_publish WHERE paper_id IS NOT NULL";
            List<Object[]> list = getAll(sql);
            for (Object[] o : list) {
                Integer userCount = 0;
                Integer finishedCount = 0;
                sql = "SELECT relate_id,pj_exam_id,is_interscoring,interscore_start_time,interscore_finish_time,create_date,modify_date FROM em_exam_relate WHERE publish_micro_lesson_id='" + o[0].toString() + "'";
                List<Object[]> erlist = new ArrayList<Object[]>();
                erlist = getAll(sql);
                List<Object[]> eprlist = new ArrayList<Object[]>();
                sql = "SELECT finished_flag,user_id,user_name,finished_date,team_id FROM em_exam_published_record WHERE published_micro_id='" + o[0].toString() + "'";
                eprlist = getAll(sql);
                userCount = eprlist.size();
                for (Object[] o2 : eprlist) {
                    Integer flag = Integer.valueOf(o2[0].toString());
                    if (flag == 1) {
                        finishedCount++;
                    }
                }
                sql = "SELECT uuid,title,start_date,finished_date,publisher_id,publisher_name,is_check,paper_id,create_date,modify_date,id FROM em_exam_publish WHERE uuid='" + o[0].toString() + "'";
                if (getAll(sql).size() > 0) {
                    Object[] taskObj = getAll(sql).get(0);
                    sql = "SELECT team_id,scored_user_id,scoring_user_id,scored_paper_id,scoring_time FROM em_exam_interscore WHERE  is_deleted=0 and exam_publish_id='" + o[0].toString() + "'";
                    List<Object[]> eilist = new ArrayList<Object[]>();
                    eilist = getAll(sql);
                    Date createDate = (Date) taskObj[8];
                    Date modifyDate = (Date) taskObj[9];
                    Task task = new Task();
                    task.setId(Integer.valueOf(taskObj[10].toString()));
                    task.setUuid(taskObj[0].toString());
                    task.setTitle(taskObj[1].toString());
                    task.setStartTime((Date) taskObj[2]);
                    task.setFinishTime((Date) taskObj[3]);
                    task.setPublisherId(Integer.valueOf(taskObj[4].toString()));
                    task.setPublisherName(taskObj[5].toString());
                    task.setIsCheck(Integer.valueOf(taskObj[6].toString()));
                    task.setPaperId(Integer.valueOf(taskObj[7].toString()));
                    task.setCreateDate(createDate);
                    task.setModifyDate(modifyDate);
                    task.setUserCount(userCount);
                    task.setFinishedCount(finishedCount);
                    taskService.add(task);
                    Integer taskId = Integer.valueOf(taskObj[10].toString());
                    List<TaskTeam> ttlist = new ArrayList<TaskTeam>();
                    for (Object[] o1 : erlist) {
                        TaskTeam tt = new TaskTeam();
                        tt.setTaskId(taskId);
                        tt.setCreateDate(createDate);
                        tt.setModifyDate(modifyDate);
                        tt.setTeamId(Integer.valueOf(o1[0].toString()));
                        tt.setPjExamId(Integer.valueOf(o1[1].toString()));
                        tt.setIsInterscoring((Boolean) o1[2]);
                        tt.setInterscoreStartTime((Date) o1[3]);
                        tt.setInterscoreFinishTime((Date) o1[4]);
                        ttlist.add(tt);
                    }
                    if (ttlist != null && ttlist.size() > 0) {
                        taskTeamService.createBatch(ttlist.toArray(new TaskTeam[ttlist.size()]));
                    }
                    List<TaskUser> tulist = new ArrayList<TaskUser>();
                    for (Object[] o2 : eprlist) {
                        TaskUser tu = new TaskUser();
                        tu.setCreateDate(createDate);
                        tu.setModifyDate(modifyDate);
                        tu.setTaskId(taskId);
                        tu.setFinishedFlag(Integer.valueOf(o2[0].toString()));
                        tu.setUserId(Integer.valueOf(o2[1].toString()));
                        tu.setName(o2[2].toString());
                        if (o2[3] != null) {
                            tu.setFinishedDate((Date) o2[3]);
                        }
                        if (o2[4] != null) {
                            tu.setTeamId(Integer.valueOf(o2[4].toString()));
                        }
                        tulist.add(tu);
                    }
                    if (tulist != null && tulist.size() > 0) {
                        taskUserService.createBatch(tulist.toArray(new TaskUser[tulist.size()]));
                    }
                    List<TaskInterscore> tilist = new ArrayList<TaskInterscore>();
                    for (Object[] o3 : eilist) {
                        TaskInterscore ti = new TaskInterscore();
                        ti.setCreateDate(createDate);
                        ti.setModifyDate(modifyDate);
                        ti.setTaskId(taskId);
                        ti.setPaperId(task.getPaperId());
                        ti.setTeamId(Integer.valueOf(o3[0].toString()));
                        ti.setScoredUserId(Integer.valueOf(o3[1].toString()));
                        ti.setScoringUserId(Integer.valueOf(o3[2].toString()));
                        ti.setScoredPaperId(Integer.valueOf(o3[3].toString()));
                        if (o3[4] != null) {
                            ti.setScoringTime((Date) o3[4]);
                        }
                        tilist.add(ti);
                    }
                    if (tilist != null && tilist.size() > 0) {
                        taskInterscoreService.createBatch(tilist.toArray(new TaskInterscore[tilist.size()]));
                    }
                } else {
                    System.out.println(o[0].toString());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }
    }

    @RequestMapping(value = "checkExport")
    @ResponseBody
    public String checkExport(@RequestParam("taskId") Integer taskId, @RequestParam("type") Integer type,
                              @RequestParam("teamId") Integer teamId,
                              HttpServletResponse response) {
        String uuid = "";
        Boolean b = false;
        if (type.intValue() == PaperType.EXAM) {
            Task task = taskService.findTaskById(taskId);
            uuid = task.getUuid();
            b = true;
        } else {
            LpTask task = lpTaskService.findLpTaskById(taskId);
            uuid = task.getUuid();
            List<LpTaskExamUnitVo> lplist = new ArrayList<LpTaskExamUnitVo>();
            lplist = lpTaskExamUnitService.findLpTaskExamUnitVoByTaskId(taskId);
            if (lplist != null && lplist.size() > 0) {
                b = true;
            }
        }
        if (!b) {
            return "noExam";
        }
        StatisticsTaskCondition stc = new StatisticsTaskCondition();
        stc.setTaskUuid(uuid);
        stc.setType(type);
        List<StatisticsTask> list = statisticsTaskService.findStatisticsTaskByCondition(stc);
        if (list != null && list.size() > 0) {
            return "success";
        }
        return "fail";
    }

    private Map<String, Object> geTeamOrderList(String uuid, Integer taskId) {
        //**班级排名信息*//
        Map<String, Object> teamsOrder = new HashMap<String, Object>();
        //**获取班级任务排名列表*//
        List<TaskVo> taskVoList = this.lpTaskService.findLpTaskListOrder(uuid);
        //**返回的班级排名列表*//
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(taskVoList.size());
        //**排名*//*
        Integer order = 1;
        //**同一批次的任务完成率*//*
        //**当前任务完成率*//*
        String prePercent = "";

        Integer totalCount = 0;
        Integer totalFinishCount = 0;
        Map<Integer, Team> teams = getTeams(taskVoList);
        BigDecimal b1 = new BigDecimal(1.0f);
        BigDecimal b2 = new BigDecimal(1.0f);
        BigDecimal b4 = new BigDecimal(100f);

        for (TaskVo taskVo : taskVoList) {

            Integer total = taskVo.getStudentCount();
            //**如果上一个任务完成率为空，赋值*//*
            if ("".equals(prePercent)) {
                prePercent = taskVo.getPercent().toString();
            }
            //**如果完成率不相等，排名++*//*
            if (!prePercent.equals(taskVo.getPercent().toString())) {
                order++;
                prePercent = taskVo.getPercent().toString();
            }
            //**同一批次的任务完成率累加*//*
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("order", order);
            b1 = new BigDecimal(taskVo.getFinishCount() * 1.0 + "");
            b2 = new BigDecimal(total + "");
            float b3 = 0.0f;
            if (total != 0) {
                b3 = b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).floatValue();
                b2 = new BigDecimal(b3 + "");
                b3 = b2.multiply(b4).floatValue();
            }
            //**本班班级排名信息*//*
            if (taskVo.getId() - taskId == 0) {
                teamsOrder.put("teamOrder", order);
                teamsOrder.put("teamPercent", b3);
                teamsOrder.put("teamCount", taskVo.getFinishCount());
            }
            //**班级完成率*//*
            map.put("percent", b3);
            //**班级完成人数*//*
            map.put("finishCount", taskVo.getFinishCount());

            totalFinishCount += taskVo.getFinishCount();

            Team team = teams.get(taskVo.getTeamId());

            if (total != null) {
                totalCount += total;
            }

            if (team != null) {
                map.put("teamName", team.getName());
            }
            list.add(map);
        }
        //**计算同一批次的任务完成率*//*
        if (totalCount - 0 != 0) {
            BigDecimal big = new BigDecimal(totalFinishCount * 1.0);
            BigDecimal big2 = new BigDecimal(totalCount + "");
            BigDecimal big3 = new BigDecimal(100.0f + "");
            teamsOrder.put("totalPercent", big.divide(big2, 4, BigDecimal.ROUND_HALF_UP).multiply(big3).floatValue());
        }
        teamsOrder.put("list", list);
        return teamsOrder;
    }

    private Map<String, Object> getTeamTaskList(Integer taskId) {
        LpTask task = lpTaskService.findLpTaskById(taskId);
        //**获取任务信息*//*
        LpTaskUserCondition lpTaskUserCondition = new LpTaskUserCondition();
        lpTaskUserCondition.setTaskId(taskId);
        List<LpTaskUser> lpTaskUserList = lpTaskUserService.findLpTaskUserByCondition(lpTaskUserCondition);
        LpTaskUnitUserCondition lpTaskUnitUserCondition = new LpTaskUnitUserCondition();
        lpTaskUnitUserCondition.setTaskId(taskId);
        List<LpTaskUnitUser> ltuuList = lpTaskUnitUserService.findLpTaskUnitUserByCondition(lpTaskUnitUserCondition);
        Map<Integer, Map<Integer, LpTaskUnitUser>> luMap = new HashMap<Integer, Map<Integer, LpTaskUnitUser>>();
        Map<Integer, Map<Integer, List<Integer>>> finishMap = new LinkedHashMap<Integer, Map<Integer, List<Integer>>>();
        Map<Integer, LpUnit> nameMap = new HashMap<Integer, LpUnit>();
        TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
        teamStudentCondition.setTeamId(task.getObjectId());
        List<TeamStudent> tsList = teamStudentService.findTeamStudentByCondition(teamStudentCondition, null, null);
        Map<Integer, String> tsMap = new HashMap<Integer, String>();
        for (TeamStudent ts : tsList) {
            tsMap.put(ts.getUserId(), ts.getName());
        }
        List<LpUnit> units = lpUnitService.findLpUnitByCatalogListOrder(task.getLpId(), null);
        for (LpUnit lu : units) {
            List<Integer> list = new ArrayList<Integer>();
            Map<Integer, List<Integer>> unitMap = new HashMap<Integer, List<Integer>>();
            //未完成
            unitMap.put(0, list);
            List<Integer> list1 = new ArrayList<Integer>();
            //已完成
            unitMap.put(1, list1);
            finishMap.put(lu.getId(), unitMap);
            nameMap.put(lu.getId(), lu);
        }
        for (LpTaskUnitUser lu : ltuuList) {
            Map<Integer, LpTaskUnitUser> ltMap = new HashMap<Integer, LpTaskUnitUser>();
            /**
             * key值1 为已完成，0为未完成
             */
            Map<Integer, List<Integer>> unitMap = new HashMap<Integer, List<Integer>>();
            unitMap = finishMap.get(lu.getUnitId());
            Integer key = 0;
            if (lu.getHasFinished()) {
                key = 1;
            }
            List<Integer> list = unitMap.get(key);
            list.add(lu.getUserId());
            unitMap.put(key, list);
            finishMap.put(lu.getUnitId(), unitMap);
            if (luMap.get(lu.getUserId()) != null) {
                ltMap = luMap.get(lu.getUserId());
            }
            ltMap.put(lu.getUnitId(), lu);
            luMap.put(lu.getUserId(), ltMap);
        }

        Map<String, Object> detail = new HashMap<String, Object>();
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>(lpTaskUserList.size());
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        //**完成人数*//*
        Integer finishCount = 0;
        //**未完成人数*//*
        Integer unFinishCount = 0;

        for (LpTaskUser ltu : lpTaskUserList) {
            if (ltu.getFinishTime() != null) {
                finishCount++;
            } else {
                unFinishCount++;
            }
        }
//		Map<Integer, Student> students = getStudents(lpTaskUserList);
        for (LpTaskUser lpTaskUser : lpTaskUserList) {
            Map<String, Object> map = new HashMap<String, Object>();
            //**获取学生信息*//*
            Map<Integer, LpTaskUnitUser> ltuuMap = luMap.get(lpTaskUser.getUserId());
            List<Map<String, Object>> unitList = new ArrayList<Map<String, Object>>();
            Integer sum = 0;
            Integer count = 0;
            int num = 1;
            for (LpUnit lu : units) {

                Map<String, Object> unitmap = new HashMap<String, Object>();
                LpTaskUnitUser lt = ltuuMap.get(lu.getId());
                unitmap.put("number", num);
                unitmap.put("name", lu.getTitle());
                unitmap.put("state", lt.getHasFinished() ? "已学习" : "未学习");
                unitmap.put("finishTime", lt.getFinishTime() == null ? "" : lt.getFinishTime().substring(0, lt.getFinishTime().length() - 2));
                unitList.add(unitmap);
                if (lt.getHasFinished()) {
                    count++;
                }
                sum++;
                num++;
            }
            BigDecimal big = new BigDecimal(count + "");
            BigDecimal big2 = new BigDecimal(sum + "");
            BigDecimal big3 = new BigDecimal(100.0f + "");
            map.put("totalPercent", big.divide(big2, 4, BigDecimal.ROUND_HALF_UP).multiply(big3).floatValue());
            map.put("unitList", unitList);
            String name = "匿名";
            if (tsMap.get(lpTaskUser.getUserId()) != null) {
                name = tsMap.get(lpTaskUser.getUserId());
            }
            //**姓名*//*
            map.put("studentName", name);
            map.put("userId", lpTaskUser.getUserId());
            list.add(map);
        }
        Map<String, Map<String, Object>> unitMap = new LinkedHashMap<String, Map<String, Object>>();
        //**完成率*//*
        for (Entry<Integer, Map<Integer, List<Integer>>> entry : finishMap.entrySet()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("hasFinish", (entry.getValue()).get(1).size());
            map.put("noFinish", (entry.getValue()).get(0).size());
            List<String> hasFinishList = new ArrayList<String>();
            for (Integer id : (entry.getValue()).get(1)) {
                String name = "匿名";
                if (tsMap.get(id) != null) {
                    name = tsMap.get(id);
                }
                hasFinishList.add(name);
            }
            map.put("hasFinishList", hasFinishList);
            map.put("hasFinishUserIdList", (entry.getValue()).get(1));
            map.put("noFinishUserIdList", (entry.getValue()).get(0));
            List<String> noFinishList = new ArrayList<String>();
            for (Integer id : (entry.getValue()).get(0)) {
                String name = "匿名";
                if (tsMap.get(id) != null) {
                    name = tsMap.get(id);
                }
                noFinishList.add(name);
            }
            map.put("noFinishList", noFinishList);
            unitMap.put(nameMap.get(entry.getKey()).getTitle(), map);
        }
        Collections.sort(list, new MapComparator());
        detail.put("list", list);
        detail.put("unitBoard", unitMap);
        detail.put("finishCount", finishCount);
        detail.put("unFinishCount", unFinishCount);

        return detail;
    }

    private Map<Integer, Team> getTeams(List<TaskVo> taskVoList) {
        Integer[] ids = new Integer[taskVoList.size()];
        for (int i = 0; i < taskVoList.size(); i++) {
            ids[i] = taskVoList.get(i).getTeamId();
        }

        List<Team> teams = teamService.findByIds(ids);
        Map<Integer, Team> teamsMap = new HashMap<Integer, Team>(taskVoList.size());

        for (Team team : teams) {
            teamsMap.put(team.getId(), team);
        }

        return teamsMap;
    }

    private Map<Integer, Student> getStudents(List<LpTaskUser> lpTaskUserList) {
        if (lpTaskUserList.size() == 0) {
            return null;
        }
        Integer[] ids = new Integer[lpTaskUserList.size()];
        for (int i = 0; i < lpTaskUserList.size(); i++) {
            ids[i] = lpTaskUserList.get(i).getUserId();
        }

        List<Student> students = studentService.findbyUserIds(ids);
        Map<Integer, Student> studentsMap = new HashMap<Integer, Student>(lpTaskUserList.size());

        for (Student student : students) {
            studentsMap.put(student.getUserId(), student);
        }

        return studentsMap;
    }

    private static Connection getConn() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://Quan:3305/ldb_old";
        String username = "root";
        String password = "noroot";
//	    String url = "jdbc:mysql://192.168.1.14:3306/ldb_new";
//	    String username = "root";
//	    String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    //给罗定邦用的
    private static Connection getldb() {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://127.0.0.1:3306/ldddddd";
        String username = "root";
        String password = "123456";
        Connection conn = null;
        try {
            Class.forName(driver); //classLoader,加载对应驱动
            conn = (Connection) DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    private static List<Object[]> getAll(String sql) {
//	    String sql = "select paper_uuid from em_exam_publish";
        PreparedStatement pstmt;
        List<Object[]> list = new ArrayList<Object[]>();
        try {
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            int col = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Object o[] = new Object[col];
                for (int i = 0; i < col; i++) {
                    o[i] = rs.getObject(i + 1);
                }
                list.add(o);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @RequestMapping(value = "/fix/anwers")
    @ResponseBody
    private String fixAnwers() throws SQLException {
        try {
            List<UserQuestion> uqlist = new ArrayList<UserQuestion>();
            conn = getConn();
            String sql = "SELECT puq.id,puq.answer,puq.content,puq.question_uuid,puq.question_type FROM pa_user_question  puq ";
            List<Object[]> objList = getAll(sql);
            for (Object[] o : objList) {
                String anwser = MqtNewUtil.fixOldDates(o[1].toString(), o[4] == null ? "" : o[4].toString(), o[2] == null ? "" : o[2].toString(), o[3].toString());
                UserQuestion uq = new UserQuestion();
                uq.setAnswer(anwser);
                uq.setId(Integer.valueOf(o[0].toString()));
                uqlist.add(uq);
            }
            String sql1 = "UPDATE pa_user_question  pq SET pq.answer= ? WHERE pq.id= ?";
            jdbcUpdateExamStudent(sql1, uqlist);
//			String anwser=MqtNewUtil.fixOldDates("[\"A\"]","radio","","d5450b47-90ba-413e-8a09-a4da5fd495d3");
//			System.out.println(anwser);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
        }

        return null;
    }

    public static List<String> getImgStr(String htmlStr) {
        List<String> pics = new ArrayList<String>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<input.*id\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(htmlStr);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("id\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        return pics;
    }

    public static void jdbcUpdateExamStudent(String sql, List<UserQuestion> datas) {
        if (datas == null || datas.size() == 0) {
            return;
        }
        //获取数据库链接
        Connection connection = null;
        PreparedStatement ps = null;
        try {

            connection = conn;
            if (connection != null) {
                //开启事务
                JDBCUitl.beginTx(connection, false);
                //开始时间，测试效率用。
                //long beginTime = System.currentTimeMillis();
                //预处理SQL
                ps = (PreparedStatement) connection.prepareStatement(sql);
                int len = datas.size();
                for (int i = 0; i < len; i++) {
                    UserQuestion userQuestion = datas.get(i);
                    ps.setString(1, userQuestion.getAnswer());
                    ps.setInt(2, userQuestion.getId());
                    //积攒SQL
                    ps.addBatch();
                    //当积攒到一定程度,就执行一次,并且清空记录
                    if ((i + 1) % 10000 == 0) {
                        ps.executeBatch();
                        //提交事务
                        JDBCUitl.commit(connection);
                        ps.clearBatch();
                    }

                }

                //总条数不是批量值整数倍,则还需要在执行一次
                if (len % 10000 != 0) {
                    ps.executeBatch();
                    JDBCUitl.commit(connection);
                    ps.clearBatch();
                }

                //long endTime = System.currentTimeMillis();
                //	System.out.println("jdbcUpdateExamStudent : "+(endTime - beginTime));
            }

        } catch (Exception e) {
            //遇到错误事务回滚
            JDBCUitl.rollback(connection);
            e.printStackTrace();
        } finally {
            //提交事务
            JDBCUitl.commit(connection);
            //把事务设置为自动提交
            JDBCUitl.beginTx(connection, true);
            //最后释放资源
//		        JDBCUitl.free(connection, ps);
        }
    }

    @RequestMapping("delLjData")
    @ResponseBody
    public String delLj() throws Exception {
        try {
            conn = getConn();
            String sql = "SELECT paper_uuid from pa_paper WHERE id>689";
            String sql2 = "SELECT uuid from pa_question WHERE id>17740";
            List<Object[]> pplist = new ArrayList<Object[]>();
            pplist = getAll(sql);
//			    PaPaperCondition condition=new PaPaperCondition();
//				List<PaPaper> pplist=paPaperService.findPaPaperByCondition(condition);
            String[] ppUuid = new String[pplist.size()];
            int i = 0;
            for (Object[] o : pplist) {
                ppUuid[i] = o[0].toString();
                i++;
            }
            List<Object[]> volist = new ArrayList<Object[]>();
            volist = getAll(sql2);
            String[] questionUUids = new String[volist.size()];
            i = 0;
            for (Object[] vo : volist) {
                questionUUids[i] = vo[0].toString();
                i++;
            }
            userPaperService.deleteNotInPaperUuid(ppUuid);
            userQuestionService.deleteByNotInQuesitonUuids(questionUUids);
            userWrongService.deleteNotInPaperUuid(ppUuid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
        return "success";
    }

    @RequestMapping("fixPaperId")
    @ResponseBody
    public String fixPaperId() throws Exception {
        conn = getConn();
        Connection connection = conn;
        try {
            PreparedStatement ps = null;
            String sql = "";
//			    String sql="SELECT pp.paper_uuid FROM res_resource rr JOIN em_exam ee ON rr.object_id = ee.uuid JOIN pa_paper pp ON ee.paper_id = pp.id JOIN res_resource_library rrl ON rr.library_id = rrl.uuid JOIN pj_school ps ON rrl.ower_id = ps.id WHERE rr.is_deleted = 0 AND rr.res_type = 4 AND rr.verify IN (0, 2, 4, 7, 8) AND ps.id = 207 ORDER BY rr.modify_date DESC";
//                List<Object []>list=getAll(sql);
//                String[] paperUuids=new String[list.size()];
//                int i=0;
//                for(Object[] o:list){
//                	paperUuids[i]=o[0].toString();
//                	i++;
//                }

            PaPaperCondition ppc = new PaPaperCondition();
            List<PaPaper> pplist = paPaperService.findPaPaperByCondition(ppc);
            Map<String, Integer> map = new HashMap<String, Integer>();
            for (PaPaper p : pplist) {
                map.put(p.getPaperUuid(), p.getId());
            }
            sql = "SELECT id,paper_uuid FROM pa_task";
            List<Object[]> list2 = getAll(sql);
            sql = "UPDATE pa_task SET paper_id= ? WHERE id= ?";
            connection = conn;
            if (connection != null) {
                //开启事务
                JDBCUitl.beginTx(connection, false);
                //开始时间，测试效率用。
                //long beginTime = System.currentTimeMillis();
                //预处理SQL
                ps = (PreparedStatement) connection.prepareStatement(sql);
                int i = 0;
                for (Object[] o : list2) {
                    if (map.get(o[1].toString()) != null) {
                        ps.setInt(2, Integer.valueOf(o[0].toString()));
                        Integer paperId = map.get(o[1].toString());
                        ps.setInt(1, paperId);
                        //积攒SQL
                        ps.addBatch();
                    }
                }
                ps.executeBatch();
                JDBCUitl.commit(connection);
                ps.clearBatch();
            }
            conn.close();
        } catch (Exception e) {
            JDBCUitl.rollback(connection);
            e.printStackTrace();
        }
        conn.close();
        return "success";
    }

    @RequestMapping("fixPaperData")
    @ResponseBody
    public String fixPaperData() throws Exception {
        try {
            conn = getldb();
            String sql = "SELECT id FROM pa_paper WHERE paper_data IS NULL";
            List<Object[]> pplist = new ArrayList<Object[]>();
            pplist = getAll(sql);
            sql = "update pa_paper set paper_data= ? where id= ?";
            //开启事务
            JDBCUitl.beginTx(conn, false);
            //开始时间，测试效率用。
            //long beginTime = System.currentTimeMillis();
            //预处理SQL
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            for (Object[] o : pplist) {
                int id = Integer.valueOf(o[0].toString());
                String data = paPaperService.findPaperJsonByDb(id);
                try {
                    ps.setInt(2, id);
                    ps.setString(1, data);
                    ps.addBatch();
                } catch (Exception e) {
                    System.out.println(id);
                }
            }
            ps.executeBatch();
            JDBCUitl.commit(conn);
            ps.clearBatch();

        } catch (Exception e) {
            JDBCUitl.rollback(conn);
            e.printStackTrace();
        }
        conn.close();
        return "success";
    }

    //寒假作业
    @RequestMapping("hw")
    public void homework(HttpServletResponse response) throws Exception {
        String sql = "SELECT ps.name,llp.title,MAX(ltuu.has_finished),pt.name FROM lp_learning_plan llp INNER JOIN lp_task_unit_user ltuu ON ltuu.lp_id=llp.id INNER JOIN lp_task lt ON lt.id=ltuu.task_id INNER JOIN pj_team pt ON pt.id=lt.object_id INNER JOIN pj_student ps ON ps.user_id=ltuu.user_id INNER JOIN pj_grade pg ON pg.id=pt.grade_id WHERE llp.title LIKE '%寒假%'  GROUP BY llp.id,ltuu.user_id ORDER BY pt.id";
        conn = getldb();
        List<Object[]> lplist = new ArrayList<Object[]>();
        lplist = getAll(sql);
        LinkedHashMap<String, Object> map = new LinkedHashMap<String, Object>();
        LinkedHashMap<String, Object> finaMap = new LinkedHashMap<String, Object>();

        for (Object[] o : lplist) {
            List<Object[]> list1 = new ArrayList<Object[]>();
            list1 = (List<Object[]>) map.get(o[3].toString());
            if (list1 == null) {
                list1 = new ArrayList<Object[]>();
            }
            list1.add(o);
            map.put(o[3].toString(), list1);
        }
        for (Entry<String, Object> olist : map.entrySet()) {
            LinkedHashMap<String, Integer> obj1 = new LinkedHashMap<String, Integer>();
            LinkedHashMap<String, Integer> obj2 = new LinkedHashMap<String, Integer>();
            String[][] a = new String[1000][1000];
            int i = 0;
            int j = 0;
            for (Object[] o : (List<Object[]>) olist.getValue()) {
                int a1 = 0;
                int b1 = 0;
                if (obj1.get(o[0].toString()) == null) {
                    obj1.put(o[0].toString(), i);
                    a1 = i;
                    i++;
                } else {
                    a1 = obj1.get(o[0].toString());
                }
                if (obj2.get(o[1].toString()) == null) {
                    obj2.put(o[1].toString(), j);
                    b1 = j;
                    j++;
                } else {
                    b1 = obj2.get(o[1].toString());
                }
                a[a1][b1] = o[2].toString();
            }
            Map<String, Object> max = new HashMap<String, Object>();
            max.put("trd", a);
            max.put("sec", obj2);
            max.put("frist", obj1);
            finaMap.put(olist.getKey(), max);
        }
        OutputStream os = null;
        WritableWorkbook wwb = null;
        String fileName = "罗定邦寒假作业统计";
        fileName = new String(fileName.getBytes("utf-8"), "ISO8859-1");
        os = response.getOutputStream();// 取得输出流
        response.reset();
        response.setHeader("Content-disposition", "attachment;filename="
                + fileName + ".xls");// 设定输出文件头
        response.setContentType("application/msexcel");// 定义输出类型
        wwb = Workbook.createWorkbook(os);
        int x = 0;
        for (Entry<String, Object> obj : finaMap.entrySet()) {
            WritableSheet ws = wwb.createSheet(obj.getKey(), x + 1);
            ws.getSettings().setShowGridLines(true);
            WritableFont wf_title = new WritableFont(WritableFont.ARIAL,
                    10, WritableFont.BOLD, false,
                    UnderlineStyle.NO_UNDERLINE); // 定义格式 字体 下划线 斜体 粗体

            WritableCellFormat wcf_title = new WritableCellFormat(wf_title); // 单元格定义
            wcf_title.setWrap(true);
            wcf_title.setAlignment(jxl.format.Alignment.CENTRE); // 设置对齐方式
            wcf_title
                    .setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            wcf_title.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
            ws.setRowView(0, 500);
            Map<String, Object> fmap = (Map<String, Object>) obj.getValue();
            LinkedHashMap<String, Object> one = (LinkedHashMap<String, Object>) fmap.get("frist");
            LinkedHashMap<String, Object> two = (LinkedHashMap<String, Object>) fmap.get("sec");
            int z = 0;
            for (Entry<String, Object> o : one.entrySet()) {
                jxl.write.Label label1 = new jxl.write.Label(0, z + 1,
                        o.getKey());
                ws.addCell(label1);
                z++;
            }
            z = 0;
            for (Entry<String, Object> o : two.entrySet()) {
                jxl.write.Label label1 = new jxl.write.Label(z + 1, 0,
                        o.getKey());
                ws.addCell(label1);
                z++;
            }
            String[][] b = (String[][]) fmap.get("trd");
            for (int i = 0; i < one.size(); i++) {
                for (int j = 0; j < two.size(); j++) {
                    String name = "未完成";
                    if (b[i][j].equals("true")) {
                        name = "完成";
                    }
                    jxl.write.Label label1 = new jxl.write.Label(j + 1, i + 1,
                            name);
                    ws.addCell(label1);
                }
            }
            x++;
        }
        if (wwb != null) {
            wwb.write();
            wwb.close();
        }
        if (os != null) {
            os.close();
        }
    }

    @RequestMapping("fixExamStudent")
    @ResponseBody
    private String fixLpTeamId() {
        TaskTeamCondition taskTeamCondition = new TaskTeamCondition();
        taskTeamCondition.setTaskId(1679);
        List<TaskTeam> ttlist = taskTeamService.findTaskTeamByCondition(taskTeamCondition);
        PjExamCondition pjExamCondition = new PjExamCondition();
        pjExamCondition.setJointExamCode("17a7f442684745a09af25554b3d805d6");
        List<PjExam> pjlist = pjExamService.findPjExamByCondition(pjExamCondition);
        PaPaper pp = paPaperService.findPaPaperById(1539);
        List<PaQuestionVo> volist = paQuestionService.findPaQuestionVoByPaperId(1539, 0, null);
        float fullScorce = pp.getScore();
        ExamStat[] pslist = new ExamStat[pjlist.size()];
        List<ExamQuestion> eqlist = new ArrayList<ExamQuestion>();
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Integer[] teamIDs = new Integer[pjlist.size()];
        for (int i = 0; i < pjlist.size(); i++) {
            PjExam pj = pjlist.get(i);
            teamIDs[i] = pj.getTeamId();
            map.put(pj.getTeamId(), pj.getId());
        }
        int i = 0;
        for (PjExam pj : pjlist) {
            List<ExamQuestion> teamEqlist = new ArrayList<ExamQuestion>();
            map.put(pj.getTeamId(), pj.getId());
            //examStat
            ExamStat examStat = new ExamStat();
            examStat.setExamId(pj.getId());
            examStat.setFullScore(fullScorce);
            examStat.setCreateDate(new Date());
            examStat.setModifyDate(new Date());
            examStat.setDataChanged(true);
            pslist[i] = examStat;
            i++;
            //ExamQuestion
            teamEqlist = toExamQuestionList(volist, pj.getId());
            eqlist.addAll(teamEqlist);
        }
        examStatService.createBatch(pslist);
        examQuestionService.InitExamQuestionData(eqlist);
//		 List<TeamStudentVo> tslist=teamStudentService.findTeamStudentVoByTeamIds(teamIDs);
//  	   if(tslist!=null&&tslist.size()>0){
//  		   ExamStudent[] eslist=new ExamStudent[tslist.size()];
//  		  int  i=0;
//  		   for(TeamStudentVo teamStudent:tslist){
//  			   ExamStudent examStudent=new ExamStudent();
//  				examStudent.setExamId(map.get(teamStudent.getTeamId()));
//  				examStudent.setStudentId(teamStudent.getStudentId());
//  				examStudent.setUserId(teamStudent.getUserId());
//  				examStudent.setNumber(teamStudent.getNumber()); // 学生班内编号（顺序编号）
//  				examStudent.setSchoolNumber(teamStudent.getStudentNumber()); // 学校内编号（顺序编号）
//  				examStudent.setName(teamStudent.getName()); // 姓名（如果同班有同名用别名）
//  				examStudent.setTestType("01"); // 01--正常考试
//  				examStudent.setScore(-1F);
//  				examStudent.setCreateDate(new Date());
//  				examStudent.setModifyDate(new Date());
//  				eslist[i]=examStudent;
//  				i++;
//  		   }
//  		   examStudentService.createBatch(eslist);
//  	   }
//		 for(TaskTeam tt:ttlist){
//			 tt.setPjExamId(map.get(tt.getTeamId()));
//			 taskTeamService.modify(tt);
//		 }
        return "success";
    }

    private List<ExamQuestion> toExamQuestionList(List<PaQuestionVo> userQuestionResultList, Integer examId) {
        List<ExamQuestion> examQuestionList = new ArrayList<ExamQuestion>();
        if (userQuestionResultList != null && userQuestionResultList.size() > 0) {
            for (PaQuestionVo userQuestionResult : userQuestionResultList) {
                // 初始化ExamQuestion表
                ExamQuestion examQuestion = new ExamQuestion();
                examQuestion.setQuestionUuid(userQuestionResult.getUuid());
                examQuestion.setExamId(examId);
                examQuestion.setQuestionType(userQuestionResult.getQuestionTypeString());
                // 添加初始化参数
                examQuestion.setSubjectCode(userQuestionResult.getSubjectCode());
                examQuestion.setPos(userQuestionResult.getPos());
                if (userQuestionResult.getDifficulity() != null) {
                    examQuestion.setDifficulity(Float.valueOf(userQuestionResult.getDifficulity() + ""));
                }
                examQuestion.setCognition(userQuestionResult.getCognition());
                examQuestion.setFullScore(userQuestionResult.getScore());
                examQuestion.setCreateDate(new Date());
                examQuestion.setModifyDate(new Date());
                examQuestionList.add(examQuestion);
            }
        }
        return examQuestionList;
    }

    @RequestMapping("initialise")
    @ResponseBody
    public String initialise() throws Exception {
        try {
            List<Map<String, Object>> mapList = pjAptsTaskService.findTodayAssessment(20952);
//		Map<String,Object> map=pjAptsTaskService.findAssessmentBoard(20952, 11);
            List<AssessmentItemVo> list = new ArrayList<AssessmentItemVo>();
            AssessmentItemVo vo = new AssessmentItemVo();
            vo.setItemId(197);
            vo.setScore(10);
            list.add(vo);
            AssessmentItemVo vo1 = new AssessmentItemVo();
            vo1.setItemId(198);
            vo1.setScore(10);
            list.add(vo1);
            AssessmentItemVo vo2 = new AssessmentItemVo();
            vo2.setItemId(199);
            vo2.setScore(10);
            list.add(vo2);
            pjAptsTaskService.addAssessment(54, 20952, "老师您教的真好", list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    @RequestMapping("apts")
    @ResponseBody
    public LinkedHashMap<String, Object> apts() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        PjAptsTaskUserCondition condition = new PjAptsTaskUserCondition();
        try {
            return pjAptsTaskService.findStatisticsByCondition(condition, df.parse("2018-01-01"), df.parse("2018-09-01"));
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedHashMap<String, Object>();
        }
    }

    @RequestMapping("/h5/score/details")
    public String scoreDetails(
            @RequestParam("taskId") Integer taskId,
            @RequestParam("userId") Integer userId,
            @RequestParam(value = "unitId", required = false) Integer unitId,
            HttpServletRequest request
    ) {
        Integer paperId = 0;
        String uuid = "";
        if (unitId == null) {
            Task pt = taskService.findTaskById(taskId);
            paperId = pt.getPaperId();
        } else {
            LpUnitFileCondition condition = new LpUnitFileCondition();
            condition.setLpUnitId(unitId);
            List<LpUnitFile> lpUnitFiles = lpUnitFileService.findLpUnitFileByCondition(condition);
            if (lpUnitFiles.size() > 0) {
                uuid = lpUnitFiles.get(0).getObjectUuid();
            }
            PaPaper pa = paPaperService.findPaPaperByUUid(uuid);
            paperId = pa.getId();
        }
        JSONObject obj = paPaperHandleService.findFullPaperJsonByPaperId(paperId, taskId, userId, unitId);
        JSONObject obj1 = obj.getJSONObject("studentInfo");
        obj1.put("icon", getImgSrc(userId));
        request.setAttribute("json", obj);
        System.out.println(obj);
        return BathPath + "/scoreDetails";
    }

    @RequestMapping("/score/details")
    public String scoreDetails1(
            @RequestParam("taskId") Integer taskId,
            @RequestParam("userId") Integer userId,
            @RequestParam(value = "unitId", required = false) Integer unitId,
            HttpServletRequest request
    ) {
        Integer paperId = 0;
        String uuid = "";
        if (unitId == null) {
            Task pt = taskService.findTaskById(taskId);
            paperId = pt.getPaperId();
        } else {
            LpUnitFileCondition condition = new LpUnitFileCondition();
            condition.setLpUnitId(unitId);
            List<LpUnitFile> lpUnitFiles = lpUnitFileService.findLpUnitFileByCondition(condition);
            if (lpUnitFiles.size() > 0) {
                uuid = lpUnitFiles.get(0).getObjectUuid();
            }
            PaPaper pa = paPaperService.findPaPaperByUUid(uuid);
            paperId = pa.getId();
        }
        JSONObject obj = paPaperHandleService.findFullPaperJsonByPaperId(paperId, taskId, userId, unitId);
        JSONObject obj1 = obj.getJSONObject("studentInfo");
        obj1.put("icon", getImgSrc(userId));
        request.setAttribute("json", obj);
        System.out.println(obj);
        return BathPath + "/scoreDetailsWeb";
    }

    /**
     * 公共方法利用用户的id获取图片的src
     *
     * @param userId
     * @param
     * @return
     */
    private String getImgSrc(Integer userId) {
        String outPutHtml = "";
        String def = "/res/images/no_pic.jpg";
        Profile profile = this.profileService.findByUserId(userId);
        if (profile != null) {
            String icon = profile.getIcon();
            outPutHtml = FileServiceHolder.getInstance().getFileService().relativePath2HttpUrlByUUID(icon);
        }
        if ("".equals(outPutHtml)) {
            ServletContext context = ContextLoader.getCurrentWebApplicationContext().getServletContext();
            outPutHtml = context.getContextPath() + def;
        }
        return outPutHtml;
    }

    public static class ValueComparator implements Comparator<String[]> {

        public int compare(String[] a, String[] b) {
            return Float.valueOf(b[b.length - 1]).compareTo(Float.valueOf(a[a.length - 1]));
        }
    }

    public static class MapComparator implements Comparator<Map<String, Object>> {

        public int compare(Map<String, Object> a, Map<String, Object> b) {

            return Float.valueOf(b.get("totalPercent").toString()).compareTo(Float.valueOf(a.get("totalPercent").toString()));
        }
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     *
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s) {
        if (s.indexOf(".") > 0) {
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    /***
     * 把秒转换为时分秒
     * @param seconds
     * @return
     */
    public static String secondsToTime(long seconds) {
        long h = 0;
        long d = 0;
        long s = 0;
        long temp = seconds % 3600;
        if (seconds > 3600) {
            h = seconds / 3600;
            if (temp != 0) {
                if (temp > 60) {
                    d = temp / 60;
                    if (temp % 60 != 0) {
                        s = temp % 60;
                    }
                } else {
                    s = temp;
                }
            }
        } else {
            d = seconds / 60;
            if (seconds % 60 != 0) {
                s = seconds % 60;
            }
        }
        return (h < 10 ? "0" + h : h) + ":" + (d < 10 ? "0" + d : d) + ":"
                + (s < 10 ? "0" + s : s);
    }

    /**
     * 学生答题情况详情
     *
     * @param taskId
     * @param userId
     * @param unitIds
     * @param request
     * @return
     */
    @RequestMapping("/score/detail/new")
    public String scorePaperDetail(
            @RequestParam("taskId") Integer taskId,
            @RequestParam("userId") Integer userId,
            @RequestParam(value = "unitIds", required = false) String unitIds,
            HttpServletRequest request) {

        String[] split = unitIds.split(",");
        Integer[] splitInt = (Integer[]) ConvertUtils.convert(split, Integer.class);

        List<JSONObject> items = new ArrayList<JSONObject>();
        for (Integer unitId : splitInt) {
            Integer paperId = 0;
            String uuid = "";
            if (unitId == null) {
                Task pt = taskService.findTaskById(taskId);
                paperId = pt.getPaperId();
            } else {
                LpUnitFileCondition condition = new LpUnitFileCondition();
                condition.setLpUnitId(unitId);
                List<LpUnitFile> lpUnitFiles = lpUnitFileService.findLpUnitFileByCondition(condition);
                if (lpUnitFiles.size() > 0) {
                    uuid = lpUnitFiles.get(0).getObjectUuid();
                }
                PaPaper pa = paPaperService.findPaPaperByUUid(uuid);
                paperId = pa.getId();
            }
            JSONObject obj = paPaperHandleService.findFullPaperJsonByPaperId(paperId, taskId, userId, unitId);
            JSONObject obj1 = obj.getJSONObject("studentInfo");
            obj1.put("icon", getImgSrc(userId));
            items.add(obj);
        }
//        request.setAttribute("json", obj);
        System.out.println(items);
        request.setAttribute("items", JSONArray.parseArray(JSON.toJSONString(items)));
        return BathPath + "/scoreDetailsWeb_new";
    }


    /**
     * 新导学案和试卷统计，返回各班平均分
     *
     * @param examId
     * @param questionUuid
     * @param unitId
     * @return
     */
    @RequestMapping(value = "/new/tj/questionRate")
    @ResponseBody
    public ArrayList<JSONObject> lpQuestionRate(@RequestParam(value = "examId") Integer examId,
                                                @RequestParam(value = "questionUuid") String questionUuid,
                                                @RequestParam(value = "unitId", required = false) Integer unitId) {
        ArrayList<JSONObject> teams = new ArrayList<JSONObject>();
        String type = PaperContans.PAPER_CODE;
        if (unitId != null) {
            type = PaperContans.LEARNINGPLAN;
        }
        PjExam exam = pjExamService.findPjExamById(examId);
        if (exam != null) {
            String jointExamCode = exam.getJointExamCode();
            List<ExamQuestionVo> examQuestionVos = examQuestionService.findExamQuestionByCodeAndQuestionUuid(jointExamCode, questionUuid, type);
            // 遍历
            for (ExamQuestionVo examQuestionVo : examQuestionVos) {
                JSONObject jsonObject = new JSONObject();
                String name = examQuestionVo.getTeamNumber() + "班";
                Float temp = examQuestionVo.getTeamScoringRate() == null ? 0 : examQuestionVo.getTeamScoringRate();
                BigDecimal big = new BigDecimal(temp + "");
                BigDecimal big1 = new BigDecimal(100f + "");
                Float average = big.multiply(big1).floatValue();
                // 返回班级名称，班级平均分
                jsonObject.put("name", name);
                jsonObject.put("avg", average);
                teams.add(jsonObject);
            }
            // 根据分数对列表进行降序排序
            Collections.sort(teams, new Comparator<JSONObject>() {
                @Override
                public int compare(JSONObject o1, JSONObject o2) {
                    Float n1 = o1.getFloat("avg");
                    Float n2 = o2.getFloat("avg");
                    if (n1 < n2) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
        }
        return teams;
    }
}
