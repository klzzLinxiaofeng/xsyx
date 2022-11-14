package platform.szxyzxx.services.statistic.service.impl;

import org.springframework.core.task.TaskExecutor;
import platform.education.exam.service.ExamService;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.ExamQuestionVo;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.learningDesign.model.LpTaskExamUnit;
import platform.education.learningDesign.service.LpTaskExamUnitService;
import platform.education.learningDesign.vo.LpTaskExamUnitCondition;
import platform.education.paper.constants.PaperType;
import platform.education.paper.model.PaPaper;
import platform.education.paper.model.PaPaperCatalog;
import platform.education.paper.model.UserPaper;
import platform.education.paper.model.UserQuestion;
import platform.education.paper.service.*;
import platform.education.paper.vo.*;
import platform.szxyzxx.services.statistic.service.StatisticService;
import platform.szxyzxx.services.unit.ValueComparator;
import platform.szxyzxx.services.vo.TeamQuestionVo;
import platform.szxyzxx.web.common.util.JDBCHandle;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * 统计处理业务。临时处理方案
 *
 * @author Administrator
 */

public class NewStatisticServiceImpl implements StatisticService {

    private PjExamService pjExamService;

    private UserQuestionService userQuestionService;

    private UserPaperService userPaperService;

    private ExamStatService examStatService;

    private PaPaperService paPaperService;

    private ExamStudentService examStudentService;

    private ExamQuestionService examQuestionService;

    private SchoolTermCurrentService schoolTermCurrentService;

    private TeamService teamService;

    private SchoolTermService schoolTermService;

    private ExamService examService;

    private LpTaskExamUnitService lpTaskExamUnitService;

    private PaQuestionService paQuestionService;

    private PaPaperCatalogService paPaperCatalogService;

    @Resource(name = "publishExam_taskExecutor")
    private TaskExecutor taskExecutor;

    private TeamStudentService teamStudentService;

    public TeamStudentService getTeamStudentService() {
        return teamStudentService;
    }

    public void setTeamStudentService(TeamStudentService teamStudentService) {
        this.teamStudentService = teamStudentService;
    }

    public void setLpTaskExamUnitService(LpTaskExamUnitService lpTaskExamUnitService) {
        this.lpTaskExamUnitService = lpTaskExamUnitService;
    }

    public void setPjExamService(PjExamService pjExamService) {
        this.pjExamService = pjExamService;
    }

    public void setUserQuestionService(UserQuestionService userQuestionService) {
        this.userQuestionService = userQuestionService;
    }

    public void setUserPaperService(UserPaperService userPaperService) {
        this.userPaperService = userPaperService;
    }

    public SchoolTermCurrentService getSchoolTermCurrentService() {
        return schoolTermCurrentService;
    }

    public TeamService getTeamService() {
        return teamService;
    }

    public SchoolTermService getSchoolTermService() {
        return schoolTermService;
    }

    public ExamService getExamService() {
        return examService;
    }

    public PjExamService getPjExamService() {
        return pjExamService;
    }

    public void setExamStatService(ExamStatService examStatService) {
        this.examStatService = examStatService;
    }


    public void setExamStudentService(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }

    public void setExamQuestionService(ExamQuestionService examQuestionService) {
        this.examQuestionService = examQuestionService;
    }

    @Override
    public void initBatchExamStatistics(List<Integer> teamIds,
                                        Integer schoolId, Integer teacherId, String type, Integer paperId,
                                        String code) {
        String subjectCode = "";
        String termValue = "";
        Float fullScorce = 0.0f;
        String ppUuid = "";
        SchoolTermCurrent schoolTermCurrent = schoolTermCurrentService.findSchoolTermCurrentBySchoolId(schoolId);
        List<PaQuestionVo> volist = paQuestionService.findPaQuestionVoByPaperId(paperId);
        PaPaper pp = paPaperService.findPaPaperById(paperId);
        PaPaperCatalogCondition ppcondition = new PaPaperCatalogCondition();
        ppcondition.setPaperId(paperId);
        List<PaPaperCatalog> list = paPaperCatalogService.findPaPaperCatalogByCondition(ppcondition);
        if (list != null) {

            subjectCode = list.get(0).getSubjectCode();
        }

        if (subjectCode == null) {
            subjectCode = "";
        }
        if (pp != null) {
            fullScorce = pp.getScore();
            ppUuid = pp.getPaperUuid();
        }
        String scholYear = schoolTermCurrent.getSchoolYear();
        String termCode = schoolTermCurrent.getSchoolTermCode();
        SchoolTerm schoolTerm = getSchoolTermByCode(termCode);
        if (schoolTerm != null) {
            termValue = schoolTerm.getGbCode();
        }
        PjExam[] pjlist = new PjExam[teamIds.size()];
        ExamStat[] pslist = new ExamStat[teamIds.size()];
        Integer[] teamIDs = new Integer[teamIds.size()];
        List<ExamQuestion> eqlist = new ArrayList<ExamQuestion>();
        int i = 0;
        for (Integer teamId : teamIds) {

            Team team = teamService.findTeamById(teamId);
            PjExam pj = new PjExam();
            pj.setCreateDate(new Date());
            pj.setModifyDate(new Date());
            pj.setExamDate(new Date());
            pj.setSchoolYear(scholYear);
            pj.setTermCode(termCode);
            pj.setTermValue(termValue);
            pj.setTeacherId(teacherId);
            pj.setIsDelete(false);
            pj.setSchoolId(schoolId);
            if (team != null && team.getGradeId() != null) {
                pj.setGradeId(team.getGradeId());
            }
            pj.setTeamId(teamId);
            pj.setSubjectCode(subjectCode);
            pj.setJointExamCode(code);
            pj.setExamRound(1);
            pj.setExamType(type);
            pj.setPaperUuid(ppUuid);
            pj.setFullScorce(fullScorce);
            pjlist[i] = pj;
            teamIDs[i] = teamId;
            i++;
        }
        pjExamService.createBatch(pjlist);
        PjExamCondition pjc = new PjExamCondition();
        pjc.setIsDelete(false);
        pjc.setJointExamCode(code);
        pjc.setExamType(type);
        List<PjExam> pelist = pjExamService.findPjExamByCondition(pjc);
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (pelist != null) {
            i = 0;
            for (PjExam pj : pelist) {
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
            //ExamStudent
            List<TeamStudent> tslist = teamStudentService.findByTeamIds(teamIDs);
            if (tslist != null && tslist.size() > 0) {
                ExamStudent[] eslist = new ExamStudent[tslist.size()];
                i = 0;
                for (TeamStudent teamStudent : tslist) {
                    ExamStudent examStudent = new ExamStudent();
                    examStudent.setExamId(map.get(teamStudent.getTeamId()));
                    examStudent.setStudentId(teamStudent.getStudentId());
                    examStudent.setUserId(teamStudent.getUserId());
                    examStudent.setNumber(teamStudent.getNumber()); // 学生班内编号（顺序编号）
                    examStudent.setName(teamStudent.getName()); // 姓名（如果同班有同名用别名）
                    examStudent.setTestType("01"); // 01--正常考试
                    examStudent.setScore(-1F);
                    examStudent.setCreateDate(new Date());
                    examStudent.setModifyDate(new Date());
                    eslist[i] = examStudent;
                    i++;
                }
                examStudentService.createBatch(eslist);
            }
            examStatService.createBatch(pslist);
            examQuestionService.InitExamQuestionData(eqlist);
        }
    }

    public PaPaperCatalogService getPaPaperCatalogService() {
        return paPaperCatalogService;
    }

    public void setPaPaperCatalogService(PaPaperCatalogService paPaperCatalogService) {
        this.paPaperCatalogService = paPaperCatalogService;
    }

    public PaQuestionService getPaQuestionService() {
        return paQuestionService;
    }

    public void setPaQuestionService(PaQuestionService paQuestionService) {
        this.paQuestionService = paQuestionService;
    }

    private List<ExamQuestion> toExamQuestionList(List<PaQuestionVo> userQuestionResultList, Integer examId) {
        List<ExamQuestion> examQuestionList = new ArrayList<ExamQuestion>();
        if (userQuestionResultList != null && userQuestionResultList.size() > 0) {
            for (PaQuestionVo userQuestionResult : userQuestionResultList) {
                // 初始化ExamQuestion表
                ExamQuestion examQuestion = new ExamQuestion();
                examQuestion.setQuestionUuid(userQuestionResult.getUuid());
                examQuestion.setExamId(examId);
                examQuestion.setQuestionType(userQuestionResult.getQuestionType());
                examQuestion.setAnswerCount(0);
                examQuestion.setRightAnswerCount(0);
                examQuestion.setEmptyCount(0);
                examQuestion.setScore(0d);
                examQuestion.setAverageScore(0f);
                // 添加初始化参数
                examQuestion.setSubjectCode(userQuestionResult.getSubjectCode());
                examQuestion.setKnowledgeId(null);
                if (userQuestionResult.getDifficulity() != null) {
                    examQuestion.setDifficulity(Float.valueOf(userQuestionResult.getDifficulity() + ""));
                }
                examQuestion.setCognition(userQuestionResult.getCognition());
                examQuestion.setFullScore(userQuestionResult.getScore());
                examQuestion.setTeamRank(0);
                examQuestion.setGradeRank(0);
                examQuestion.setTotalTime(0);
                examQuestion.setAverageTime(0);
                examQuestion.setIsDeleted(false);
                examQuestion.setCreateDate(new Date());
                examQuestionList.add(examQuestion);
            }
        }
        return examQuestionList;
    }

    private SchoolTerm getSchoolTermByCode(String code) {
        SchoolTerm schoolTerm = null;
        SchoolTermCondition schoolTermCondition = new SchoolTermCondition();
        schoolTermCondition.setCode(code);
        schoolTermCondition.setIsDelete(false);
        List<SchoolTerm> schoolTermList = schoolTermService.findSchoolTermByCondition(schoolTermCondition, null, null);
        if (schoolTermList.size() > 0) {
            schoolTerm = schoolTermList.get(0);
        }
        return schoolTerm;
    }

    @Override
    public Boolean statisticHandle(Integer examId, Integer paperId,
                                   Integer ownerId, Integer type) {
        Boolean flag = false;
        try {
            if (type != null && PaperType.LEARNING_PLAN == type) {
                learningDesign(ownerId);
            } else {
                examStatisic(examId, ownerId, paperId);
            }
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     * 统计试卷
     */
    private Boolean examStatisic(Integer examId, Integer ownerId, Integer paperId) {

        // long startTime = System.currentTimeMillis();
        Boolean flag = false;
        // 添加统计任务。
        try {

            // 1. 先查询该份试卷是否有学生作答，无作答则直接返回不统计
            final List<UserPaper> userPapers = this.userPaperService.findUserPaperListByOwnerId(ownerId, null, PaperType.EXAM);

            if (userPapers != null && userPapers.size() > 0) { // 执行统计
                // 2 . 根据examId去pj_exam表查找记录
                PjExam newExam = this.pjExamService.findPjExamById(examId);
                if (newExam != null) {
                    UserQuestionCondition userQuestionCondition = new UserQuestionCondition();
                    userQuestionCondition.setOwnerId(ownerId);
//							userQuestionCondition.setTeamId(newExam.getTeamId());
                    userQuestionCondition.setType(PaperType.EXAM);
                    List<UserQuestion> uqlist = userQuestionService.findUserQuestionByCondition(userQuestionCondition);
                    // 2. 根据joint_exam_code值去查找同一一次任务多个班级的记录
                    PjExamCondition newpjExamCondition = new PjExamCondition();
                    newpjExamCondition.setJointExamCode(newExam.getJointExamCode());
                    newpjExamCondition.setExamType(PaperType.PAPER_CODE);
                    newpjExamCondition.setIsDelete(false);
                    final List<PjExam> pjExamList = this.pjExamService.findPjExamByCondition(newpjExamCondition);
                    Map<Integer, List<UserQuestion>> questionMap = new HashMap<Integer, List<UserQuestion>>();
                    questionMap.put(ownerId, uqlist);
                    // 更新pj_exam_student表数据
                    if (examStudentHandle(paperId, pjExamList, userPapers, questionMap, PaperType.EXAM)) {
                        // 处理 pj_exam_stat表记录
                        examStatHandle(pjExamList, userPapers, null, paperId, PaperType.EXAM);
                        // 3. pj_exam_question表数据处理
                        examQuestionHandle(pjExamList, userPapers, null, questionMap, PaperType.EXAM, newExam.getJointExamCode());
                    }
                    ;

                }

                flag = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 统计导学案 ownerId ：导学案任务ID 根据一个导学案的单元去循环统计
     */
    private void learningDesign(Integer ownerId) {

        // long startTime = System.currentTimeMillis();
        // 根据导学案taskID 去lp_task_exam_unit表查找导学案下相同CODE的试卷
        LpTaskExamUnitCondition lpTaskExamUnitCondition = new LpTaskExamUnitCondition();
        lpTaskExamUnitCondition.setDeleted(false);
        lpTaskExamUnitCondition.setTaskId(ownerId);
        List<LpTaskExamUnit> lpTaskExamUnitList = lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
        if (lpTaskExamUnitList != null && lpTaskExamUnitList.size() > 0) {
            // 找到导学案下有几个单元
            for (LpTaskExamUnit lteu : lpTaskExamUnitList) {
                lpTaskExamUnitCondition = new LpTaskExamUnitCondition();
                lpTaskExamUnitCondition.setJoinExamCode(lteu.getJoinExamCode());
                List<LpTaskExamUnit> lpTaskExamUnitList1 = lpTaskExamUnitService.findLpTaskExamUnitByCondition(lpTaskExamUnitCondition);
                Integer paperId = null;
                Integer untilId = null;
                // 找到这份卷所有ownerId用作批量查找
                if (lpTaskExamUnitList1 != null && lpTaskExamUnitList1.size() > 0) {
                    Integer[] ownerIds = new Integer[lpTaskExamUnitList1.size()];
                    for (int i = 0; i < lpTaskExamUnitList1.size(); i++) {
                        ownerIds[i] = lpTaskExamUnitList1.get(i).getTaskId();
                        if (i == 0) {
                            PjExam pje = this.pjExamService.findPjExamById(lpTaskExamUnitList1.get(i).getExamId());
                            PaPaperCondition condition = new PaPaperCondition();
                            condition.setPaperUuid(pje.getPaperUuid());
                            List<PaPaper> pa = paPaperService.findPaPaperByCondition(condition);
                            paperId = pa.get(0).getId();
                            untilId = lpTaskExamUnitList1.get(i).getUnitId();
                        }
                    }

                    // 通过所有班级的ownerId,找到已经做题的班级，重新装载ownerIds去查找userQuestion
                    final List<UserPaper> userPapers = this.userPaperService.findUserPaperListByOwnerIds(ownerIds, untilId, PaperType.LEARNING_PLAN);
                    if (userPapers != null && userPapers.size() > 0) {
                        ownerIds = new Integer[userPapers.size()];
                        int i = 0;
                        for (UserPaper up : userPapers) {
                            ownerIds[i] = up.getOwnerId();
                            i++;
                        }
                    }
                    // 查找到所有班级的做题记录，根据班级分组。
                    final List<UserQuestion> uqlist = this.userQuestionService.findUserQuestionByOwnerIds(ownerIds, untilId, PaperType.LEARNING_PLAN);
                    Map<Integer, List<UserQuestion>> questionMap = new HashMap<Integer, List<UserQuestion>>();
                    if (uqlist != null && uqlist.size() > 0) {
                        List<UserQuestion> ownList = new ArrayList<UserQuestion>();
                        for (UserQuestion uq : uqlist) {
                            ownList = new ArrayList<UserQuestion>();
                            if (questionMap.get(uq.getOwnerId()) != null) {
                                ownList = questionMap.get(uq.getOwnerId());
                            }
                            ownList.add(uq);
                            questionMap.put(uq.getOwnerId(), ownList);
                        }
                    }
                    if (userPapers != null && userPapers.size() > 0) {
                        // 通过code值去找到当前试卷单元的统计表的列表;
                        PjExamCondition pjExamCondition = new PjExamCondition();
                        pjExamCondition.setJointExamCode(lteu.getJoinExamCode());
                        pjExamCondition.setExamType(PaperType.LEARNING_CODE);
                        pjExamCondition.setIsDelete(false);
                        List<PjExam> pjExamList = pjExamService.findPjExamByCondition(pjExamCondition);
                        if (examStudentHandle(paperId, pjExamList, userPapers, questionMap, PaperType.LEARNING_PLAN)) {
                            // 处理 pj_exam_stat表记录
                            examStatHandle(pjExamList, userPapers, untilId, paperId, PaperType.LEARNING_PLAN);
                            // 3. pj_exam_question表数据计算
                            examQuestionHandle(pjExamList, userPapers, untilId, questionMap, PaperType.LEARNING_PLAN, lteu.getJoinExamCode());

                        }
                    }
                }
            }
        }

    }

    // 处理 examStudent表数据处理
    private boolean examStudentHandle(Integer paperId, List<PjExam> pjExamList, List<UserPaper> userPapers, Map<Integer, List<UserQuestion>> questionMap, Integer type) {
        boolean flag = false;
        // long startTime = System.currentTimeMillis();
        PaPaper paper = paPaperService.findPaPaperById(paperId);
        List<ExamStudent> examStudentList = null;
        List<ExamStudent> sumExamStudentList = new ArrayList<ExamStudent>();
        Map<Integer, Object> gradeMap = new HashMap<Integer, Object>();
        Map<Integer, Integer> rightMap = new HashMap<Integer, Integer>();
        Map<Integer, Integer> anwerMap = new HashMap<Integer, Integer>();
        for (List<UserQuestion> value : questionMap.values()) {

            for (UserQuestion uq : value) {
                Integer anwer = 0;
                if (anwerMap.get(uq.getUserId()) != null) {
                    anwer = anwerMap.get(uq.getUserId());
                }
                anwerMap.put(uq.getUserId(), anwer + 1);
                if (uq.getIsCorrect()) {
                    Integer right = 0;
                    if (rightMap.get(uq.getUserId()) != null) {
                        right = rightMap.get(uq.getUserId());
                    }
                    rightMap.put(uq.getUserId(), right + 1);
                }
            }

        }
        if (paper != null) {
            String paperUuid = paper.getPaperUuid();
            if (pjExamList != null && pjExamList.size() > 0) {
                for (PjExam pjExam : pjExamList) {
                    Map<Integer, Object> teamMap = new HashMap<Integer, Object>();
                    if (userPapers != null && userPapers.size() > 0) {
                        for (UserPaper userPaper : userPapers) {
                            // 有作答才执行统计
                            if (pjExam.getTeamId().intValue() == userPaper.getTeamId()) {

                                List<UserPaper> list = this.userPaperService.findUserPaperUserTotalScore(null, userPaper.getOwnerId(), type, userPaper.getTeamId());
                                // 根据EXAMID查找对应某个班级的学生纪律
                                examStudentList = this.examStudentService.findExamStudentsByExamId(pjExam.getId(), null);

                                if (list != null && list.size() > 0) {
                                    Map<Integer, Object> map = new HashMap<Integer, Object>();
                                    for (UserPaper up : list) {
                                        map.put(up.getUserId(), up.getScore() + "");
                                        gradeMap.put(up.getUserId(), up.getScore());
                                        if (pjExam.getTeamId().intValue() == userPaper.getTeamId()) {
                                            teamMap.put(up.getUserId(), up.getScore());
                                        }
                                    }
                                    ValueComparator bvc = new ValueComparator(teamMap);
                                    TreeMap<Integer, Integer> rank_map = new TreeMap<Integer, Integer>(bvc);
                                    rank_map = sort(teamMap);
                                    if (examStudentList != null && examStudentList.size() > 0) {

                                        for (ExamStudent es : examStudentList) {
                                            String score = "-1.0";

                                            if (map.get(es.getUserId()) != null) {
                                                score = (String) map.get(es.getUserId());
                                            }
                                            if (rank_map.get(es.getUserId()) != null) {
                                                es.setTeamRank(rank_map.get(es.getUserId()));
                                            } else {
                                                es.setTeamRank(0);
                                            }
                                            if (anwerMap.get(es.getUserId()) != null) {
                                                es.setAnswerCount(anwerMap.get(es.getUserId()));
                                            } else {
                                                es.setAnswerCount(0);
                                            }

                                            if (rightMap.get(es.getUserId()) != null) {
                                                es.setRightAnswerCount(rightMap.get(es.getUserId()));
                                            } else {
                                                es.setRightAnswerCount(0);
                                            }
                                            es.setScore(Float.valueOf(score));
                                            sumExamStudentList.add(es);
                                        }
                                    }

                                }

                                // 批量更新一个班级学生得分
//										String SQL = "UPDATE pj_exam_student es set es.score = ? where id = ?";
//										JDBCHandle.jdbcUpdateExamStudent(SQL, examStudentList);
                            }
                        }
                    }
                }
                ValueComparator bvc1 = new ValueComparator(gradeMap);
                TreeMap<Integer, Integer> grade_rank_map = new TreeMap<Integer, Integer>(bvc1);
                grade_rank_map = sort(gradeMap);
                if (sumExamStudentList != null) {
                    for (ExamStudent es : sumExamStudentList) {
                        if (grade_rank_map.get(es.getUserId()) != null) {
                            es.setGradeRank(grade_rank_map.get(es.getUserId()));
                        } else {
                            es.setGradeRank(0);
                        }
                    }
                    String SQL = "UPDATE pj_exam_student es set es.score = ? ,es.team_rank=?,es.grade_rank=?,es.answer_count=?,es.right_answer_count=?   where id = ?";
                    JDBCHandle.jdbcUpdateExamStudent(SQL, sumExamStudentList);
                    flag = true;
                }
                // long endTime = System.currentTimeMillis();
                // System.out.println("examStudentHandle程序运行时间： "
                // + ((endTime - startTime)) + "毫秒");
            }

        }
        return flag;
    }

    // examQuestion表数据处理
    private void examQuestionHandle(List<PjExam> pjExamList, List<UserPaper> userPapers, Integer unitId, Map<Integer, List<UserQuestion>> quetsionMap, Integer type, String code) {
        // 通过examId查询出来的
        List<ExamQuestion> examQuestionList = null;
        // 计算好的所有班级未做题列表
        List<ExamQuestion> notSumExamQuestionList = new ArrayList<ExamQuestion>();
        // 通过examId查询出来的
        List<ExamQuestion> notExamQuestionList = new ArrayList<ExamQuestion>();
        // 计算好的所有班级已做题列表
        List<ExamQuestion> sumExamQuestionList = new ArrayList<ExamQuestion>();
        // 所有班级的针对某道题的做题人数，key:questionUuid value RightCount*fullScore,用作求年级得分率
        Map<String, Object> teamCoutQuestionMap = new HashMap<String, Object>();
        // 所有班级的针对某道题的做题总分，key:questionUuid value score,用作求年级得分率
        Map<String, Object> teamScoreQuestionMap = new HashMap<String, Object>();
        // 所有班级的针对某道题的做题人数，key:questionUuid value AnwerCout,用作求年级正得分率
        Map<String, Integer> gradeAnwerCoutQuestionMap = new HashMap<String, Integer>();
        // 所有班级的针对某道题的做题总分，key:questionUuid value RightCout,用作求年级正得分率
        Map<String, Integer> gradeRightCoutQuestionMap = new HashMap<String, Integer>();
        // 每个班级的针对某道题的得分率，key:questionUuid value Map<Integer, Object>,用作排名
        Map<String, Object> teamRateQuestionMap = new HashMap<String, Object>();
        // 包含试卷和导学案答题统计
        List<ExamQuestionVo> evlist = new ArrayList<ExamQuestionVo>();
        List<TeamQuestionVo> volist = new ArrayList<TeamQuestionVo>();
        // 有人作答才執行統計
        if (pjExamList != null && pjExamList.size() > 0) {

            for (PjExam pjExam : pjExamList) {
                if (userPapers != null && userPapers.size() > 0) {
                    // 判断是否做过题
                    if (isExist(pjExam.getTeamId().intValue(), userPapers)) {
                        ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
                        examQuestionCondition.setExamId(pjExam.getId());
                        notExamQuestionList = this.examQuestionService.findExamQuestionByCondition(examQuestionCondition);
                        if (notExamQuestionList != null && notExamQuestionList.size() > 0) {
                            notSumExamQuestionList.addAll(notExamQuestionList);
                        }
                    }
                    for (UserPaper userPaper : userPapers) {
                        // 有作答才执行统计
                        if (pjExam.getTeamId().intValue() == userPaper.getTeamId()) {
                            Integer examId = pjExam.getId();
                            ExamQuestionCondition examQuestionCondition = new ExamQuestionCondition();
                            examQuestionCondition.setExamId(examId);
                            examQuestionList = this.examQuestionService.findExamQuestionByCondition(examQuestionCondition);
                            if (examQuestionList != null && examQuestionList.size() > 0) {
                                List<UserQuestion> list = new ArrayList<UserQuestion>();
                                List<UserQuestion> list1 = new ArrayList<UserQuestion>();
                                // 通过已知做题记录获取当前班级的做题记录
                                list1 = quetsionMap.get(userPaper.getOwnerId());
                                // 通过循环list,求出每个班每道题做题人数
                                Map<String, Integer> questionCount = new HashMap<String, Integer>(examQuestionList.size());
                                // 通过循环list,求出每个班每道题正确人数
                                Map<String, Integer> rightQuestionCount = new HashMap<String, Integer>(examQuestionList.size());
                                // 通过循环list,求出每个班每道题正确人数
                                Map<String, Integer> empytQuestionCount = new HashMap<String, Integer>(examQuestionList.size());
                                // 通过循环list,求出每个班每道题总分数
                                Map<String, Double> sumQuestion = new HashMap<String, Double>(examQuestionList.size());
                                //  通过循环list,求出每个班每道题总分数
                                Map<String, Integer> sumTimeQuestion = new HashMap<String, Integer>(examQuestionList.size());
                                if (unitId != null) {
                                    list.addAll(list1);
                                } else {
                                    for (UserQuestion uq : list1) {
                                        if (uq.getTeamId().intValue() == pjExam.getTeamId().intValue()) {
                                            list.add(uq);
                                        }
                                    }
                                }
                                // 遍历结算
                                if (list != null && list.size() > 0) {
                                    for (UserQuestion uq : list) {
                                        Integer count = questionCount.get(uq.getQuestionUuid());
                                        Integer rightCount = rightQuestionCount.get(uq.getQuestionUuid());
                                        Integer emptyCont = empytQuestionCount.get(uq.getQuestionUuid());
                                        Double sum = sumQuestion.get(uq.getQuestionUuid());
                                        Integer sumCount = sumTimeQuestion.get(uq.getQuestionUuid());
                                        if (sumCount == null) {
                                            if (uq.getAnswerTime() != null) {
                                                sumTimeQuestion.put(uq.getQuestionUuid(), uq.getAnswerTime());
                                            } else {
                                                sumTimeQuestion.put(uq.getQuestionUuid(), 0);
                                            }
                                        } else {
                                            int Tcount = 0;
                                            if (uq.getAnswerTime() != null) {
                                                Tcount = uq.getAnswerTime();
                                            }
                                            sumTimeQuestion.put(uq.getQuestionUuid(), sumCount + Tcount);
                                        }
                                        if (count == null) {
                                            questionCount.put(uq.getQuestionUuid(), 1);
                                        } else {
                                            questionCount.put(uq.getQuestionUuid(), count + 1);
                                        }

                                        if (rightCount == null && !uq.getIsCorrect()) {

                                            rightQuestionCount.put(uq.getQuestionUuid(), 0);
                                        } else if (rightCount == null && uq.getIsCorrect()) {
                                            rightQuestionCount.put(uq.getQuestionUuid(), 1);
                                        } else if (rightCount != null && uq.getIsCorrect()) {
                                            rightQuestionCount.put(uq.getQuestionUuid(), rightCount + 1);
                                        }

                                        if (emptyCont == null) {
                                            empytQuestionCount.put(uq.getQuestionUuid(), 0);
                                        } else if ("[]".equals(uq.getAnswer())) {
                                            empytQuestionCount.put(uq.getQuestionUuid(), emptyCont + 1);
                                        }
                                        if (sum == null && uq.getScore() == null) {
                                            sumQuestion.put(uq.getQuestionUuid(), 0.0);
                                        } else if (sum == null && uq.getScore() != null) {
                                            sumQuestion.put(uq.getQuestionUuid(), uq.getScore());
                                        } else if (sum != null && uq.getScore() != null) {
                                            sumQuestion.put(uq.getQuestionUuid(), sum + uq.getScore());
                                        }

                                    }


                                }
                                // 把计算好的值放入examQuestionList
                                for (ExamQuestion examQuestion : examQuestionList) {
                                    Integer answerCount = 0;
                                    Integer sumRightCount = gradeRightCoutQuestionMap.get(examQuestion.getQuestionUuid());
                                    Integer sumAnwerCount = gradeAnwerCoutQuestionMap.get(examQuestion.getQuestionUuid());
                                    answerCount = questionCount.get(examQuestion.getQuestionUuid());
                                    Integer sumTCount = sumTimeQuestion.get(examQuestion.getQuestionUuid());
                                    if (answerCount == null) {
                                        answerCount = 0;
                                    }

                                    if (answerCount != null) {

                                        examQuestion.setAnswerCount(answerCount);
                                    }
                                    if (sumTCount != null) {
                                        examQuestion.setTotalTime(sumTCount);
                                    }

                                    Integer emptyCount = 0;

                                    emptyCount = empytQuestionCount.get(examQuestion.getQuestionUuid());

                                    if (emptyCount != null) {
                                        examQuestion.setEmptyCount(emptyCount);
                                    }

                                    Integer rightAnswerCount = 0;
                                    rightAnswerCount = rightQuestionCount.get(examQuestion.getQuestionUuid());

                                    if (rightAnswerCount == null) {
                                        rightAnswerCount = 0;
                                    }
                                    examQuestion.setRightAnswerCount(rightAnswerCount);
                                    if (sumRightCount == null) {
                                        gradeRightCoutQuestionMap.put(examQuestion.getQuestionUuid(), rightAnswerCount);
                                    } else {
                                        gradeRightCoutQuestionMap.put(examQuestion.getQuestionUuid(), sumRightCount + rightAnswerCount);
                                    }
                                    if (sumAnwerCount == null) {
                                        gradeAnwerCoutQuestionMap.put(examQuestion.getQuestionUuid(), answerCount);
                                    } else {
                                        gradeAnwerCoutQuestionMap.put(examQuestion.getQuestionUuid(), sumAnwerCount + answerCount);
                                    }
                                    Double totalScore = 0.0;
                                    if (sumQuestion.get(examQuestion.getQuestionUuid()) != null) {
                                        totalScore = sumQuestion.get(examQuestion.getQuestionUuid());
                                    }
                                    examQuestion.setScore(totalScore);

                                    if (answerCount != null && answerCount != 0) {

                                        examQuestion.setAverageScore((float) (totalScore / answerCount));
                                    }

                                    Float fullScore = examQuestion.getFullScore() == null ? 0f : examQuestion.getFullScore();
                                    Float teamScoringRate = 0.0f;
                                    if (fullScore != 0 && answerCount != 0) {
                                        teamScoringRate = (float) (Float.parseFloat(totalScore.toString()) / (answerCount * fullScore));
                                    }
                                    if (teamScoringRate.isNaN()) {
                                        teamScoringRate = 0.0f;
                                    }
                                    // ---------------------------------------------
                                    if (teamCoutQuestionMap.get(examQuestion.getQuestionUuid()) != null) {
                                        Double dScore = (Double) teamCoutQuestionMap.get(examQuestion.getQuestionUuid()) + totalScore;
                                        teamCoutQuestionMap.put(examQuestion.getQuestionUuid(), dScore);
                                    } else {
                                        teamCoutQuestionMap.put(examQuestion.getQuestionUuid(), totalScore);
                                    }
                                    if (teamScoreQuestionMap.get(examQuestion.getQuestionUuid()) != null) {

                                        teamScoreQuestionMap.put(examQuestion.getQuestionUuid(), (Float) teamScoreQuestionMap.get(examQuestion.getQuestionUuid()) + answerCount * fullScore);
                                    } else {
                                        teamScoreQuestionMap.put(examQuestion.getQuestionUuid(), answerCount * fullScore);
                                    }
                                    TeamQuestionVo vo = new TeamQuestionVo();
                                    vo.setExamId(examQuestion.getExamId());
                                    vo.setQuestionUuid(examQuestion.getQuestionUuid());
                                    vo.setTeamRight(teamScoringRate);
                                    float rRate = 0.0f;
                                    String questionType = examQuestion.getQuestionType();
                                    if (questionType.contains("选") || questionType.contains("判")) {
//												rRate = teamScoringRate;
                                        if (examQuestion.getAnswerCount() == null || examQuestion.getAnswerCount() == 0) {
                                            vo.setTeamRight(rRate);
                                        } else {
                                            rRate = (float) examQuestion.getRightAnswerCount() / examQuestion.getAnswerCount();
                                            vo.setTeamRight(rRate);
                                        }
                                    } else {
                                        rRate = teamScoringRate;
                                    }
                                    volist.add(vo);
                                    examQuestion.setTeamScoringRate(rRate);
                                    sumExamQuestionList.add(examQuestion);
                                }
                            }

                        }
                    }
                }
            }
            // 把每道题的班级的得分率放入map
            if (volist != null && volist.size() > 0) {
                for (ExamQuestion eq : examQuestionList) {
                    Map<Integer, Object> teamScoringRateMap = new HashMap<Integer, Object>();
                    for (TeamQuestionVo vo : volist) {
                        if (eq.getQuestionUuid().equals(vo.getQuestionUuid())) {
                            teamScoringRateMap.put(vo.getExamId(), vo.getTeamRight());
                        }
                    }
                    teamRateQuestionMap.put(eq.getQuestionUuid(), teamScoringRateMap);
                }
            }
            Map<String, Object> gradeRateMap = new HashMap<String, Object>();
            Map<String, Object> sortRateQuestionMap = new HashMap<String, Object>();
            // 计算排名
            for (ExamQuestion eq : examQuestionList) {
                Double svm = 0.0;
                Double count = 0.0;
                float sum = 0.0f;
                count = (Double) teamCoutQuestionMap.get(eq.getQuestionUuid());
                sum = (Float) teamScoreQuestionMap.get(eq.getQuestionUuid());
                Integer anwerCount = gradeAnwerCoutQuestionMap.get(eq.getQuestionUuid());
                Integer rightCount = gradeRightCoutQuestionMap.get(eq.getQuestionUuid());
                if (eq.getQuestionType().contains("选") || eq.getQuestionType().contains("判")) {
                    if (anwerCount != null && anwerCount != 0 && rightCount != null && rightCount != 0) {
                        gradeRateMap.put(eq.getQuestionUuid(), (double) (float) rightCount / anwerCount);
                    } else {
                        gradeRateMap.put(eq.getQuestionUuid(), svm);
                    }
                } else {
                    Double rate = 0.0;
                    if (sum != 0) {
                        rate = count / sum;
                        java.text.DecimalFormat df = new java.text.DecimalFormat("#.0000");
                        rate = Double.valueOf(df.format(rate));
                    } else {
                        rate = 0.0;
                    }
                    gradeRateMap.put(eq.getQuestionUuid(), rate);
                }
                Map<Integer, Object> tr = (Map<Integer, Object>) teamRateQuestionMap.get(eq.getQuestionUuid());
                ValueComparator bvc = new ValueComparator(tr);
                TreeMap<Integer, Integer> rank_map = new TreeMap<Integer, Integer>(bvc);
                rank_map = sort(tr);
                sortRateQuestionMap.put(eq.getQuestionUuid(), rank_map);
            }
            // 计算年级得分率
            for (ExamQuestion eq : sumExamQuestionList) {
                Double rate = 0.0;
                Integer rank = 0;
                if (gradeRateMap.get(eq.getQuestionUuid()) != null) {
                    rate = (Double) gradeRateMap.get(eq.getQuestionUuid());
                }
                int scale = 2;// 设置位数
                BigDecimal bd = new BigDecimal(rate);
                BigDecimal bd1 = new BigDecimal(eq.getTeamScoringRate());
                int roundingMode = bd.ROUND_DOWN;
                bd = bd.setScale(scale, roundingMode);
                bd1 = bd1.setScale(scale, roundingMode);
                eq.setGradeScoringRate(bd.floatValue());
                eq.setTeamScoringRate(bd1.floatValue());
                Map<Integer, Integer> rank_map = new HashMap<Integer, Integer>();
                rank_map = (Map<Integer, Integer>) sortRateQuestionMap.get(eq.getQuestionUuid());
                if (rank_map != null) {
                    if (rank_map.get(eq.getExamId()) != null) {
                        rank = rank_map.get(eq.getExamId());
                    }
                }
                if (eq.getAverageScore() == null) {
                    eq.setAverageScore(0.0f);
                }
                if (eq.getTeamScoringRate().isNaN()) {
                    eq.setTeamScoringRate(0.0f);
                }
                if (eq.getGradeScoringRate() == null) {
                    eq.setGradeScoringRate(0.0f);
                }

                eq.setGradeRank(rank);
            }
            if (notSumExamQuestionList.size() > 0) {
                for (ExamQuestion eq : notSumExamQuestionList) {
                    Double rate = 0.0;
                    if (gradeRateMap.get(eq.getQuestionUuid()) != null) {
                        rate = (Double) gradeRateMap.get(eq.getQuestionUuid());
                    }
                    eq.setGradeScoringRate(Float.parseFloat(rate.toString()));
                }
            }
        }
        String SQL = "UPDATE pj_exam_question eq SET eq.answer_count = ?,eq.empty_count = ?, eq.right_answer_count = ? , eq.score = ?, eq.average_score = ?, eq.team_scoring_rate = ?,eq.grade_rank=?,eq.grade_scoring_rate=?,eq.total_time=? where id = ?";
        JDBCHandle.jdbcUpdateExamQuestion(SQL, sumExamQuestionList);
        String SQL1 = "UPDATE pj_exam_question eq SET eq.grade_scoring_rate=? where id = ?";
        JDBCHandle.jdbcUpdateExamQuestionGradeScoreRate(SQL1, notSumExamQuestionList);

    }

    private void examStatHandle(List<PjExam> pjExamList, List<UserPaper> userPapers, Integer unitId, Integer paperId, Integer type) {
        //	long startTime = System.currentTimeMillis();
        PaPaper paper = paPaperService.findPaPaperById(paperId);
        Map<Integer, Object> scoreMap = new HashMap<Integer, Object>();
        List<ExamStat> examStatList = new ArrayList<ExamStat>();
        if (paper != null)
            if (pjExamList != null && pjExamList.size() > 0) {
                for (PjExam pjExam : pjExamList) {
                    if (userPapers != null && userPapers.size() > 0) {
                        for (UserPaper userPaper : userPapers) {
                            // 有作答才执行统计
                            if (pjExam.getTeamId().intValue() == userPaper.getTeamId()) {
                                Integer teamId = userPaper.getTeamId();
                                Integer examId = pjExam.getId();

                                ExamStat examStat = this.examStatService.findExamStatByExamId(examId);

                                UserPaperCondition userPaperCondition = new UserPaperCondition();

                                userPaperCondition.setOwnerId(userPaper.getOwnerId());
                                userPaperCondition.setTeamId(teamId);
                                userPaperCondition.setType(type);
                                userPaperCondition.setObjectId(unitId);
                                // 实际应试人数
                                Long studentCount = userPaperService.count(userPaperCondition);

                                if (studentCount != null) {
                                    examStat.setStudentCount(Integer.parseInt(studentCount + ""));
                                }

                                // 全班总分
                                Float totalScore = userPaperService.countUserPaperTeamTotalScore(userPaper.getOwnerId(), teamId, unitId, type);
                                if (totalScore == null) {
                                    totalScore = 0F;
                                }
                                if (totalScore != null) {
                                    examStat.setTotalScore((float) totalScore);
                                }

                                // 全班平均分
                                if (studentCount != 0) {
                                    examStat.setAverageScore((float) totalScore / studentCount);// 平均分
                                }

                                List<ExamStudent> examStudentList = this.examStudentService.findExamStudentsByExamId(examId);

                                // 年级排名
                                Integer gradeRank = null;

                                // 高分人数
                                Integer highCount = 0;

                                // 低分人数
                                Integer lowCount = 0;

                                // 及格人数
                                Integer passCount = 0;

                                Float fullScore = paper.getScore();
                                examStat.setFullScore(fullScore); // 满分分数
                                Float highScore = (float) (fullScore * 0.9);
                                examStat.setHighScore(highScore); // 高分 / 优秀 分数

                                Float lowScore = (float) (fullScore * 0.75);
                                examStat.setLowScore(lowScore); // 低分 / 良好 分数

                                Float passScore = (float) (fullScore * 0.6);
                                examStat.setPassScore(passScore); // 及格分数

                                List<Float> list = new ArrayList<Float>();
                                // 学生成绩集合 做计算标准差做准备
                                for (ExamStudent es : examStudentList) {
                                    if (es.getScore().floatValue() != -1) {

                                        list.add(es.getScore());
                                        if (es.getScore() >= highScore) {
                                            highCount = highCount + 1;
                                        }
                                        if (es.getScore() >= lowScore) {
                                            lowCount = lowCount + 1;
                                        }
                                        if (es.getScore() >= passScore) {
                                            passCount = passCount + 1;
                                        }

                                    }
                                }


                                Float[] floatArray = new Float[list.size()];
                                for (int i = 0; i < list.size(); i++) {
                                    floatArray[i] = list.get(i);
                                }

                                float highestScore = Collections.max(list);
                                float lowestScore = Collections.min(list);

                                if (floatArray != null && floatArray.length > 0 && studentCount != 0) {
                                    examStat.setSdScore(getAdScore(floatArray, (float) (totalScore / studentCount)));// 标准差
                                    examStat.setMadValue(getMadValue(floatArray, (float) (totalScore / studentCount)));// 离差
                                    examStat.setMovValue(floatArray[floatArray.length - 1] - floatArray[0]);// 极差
                                }
                                examStat.setHighCount(highCount); // 高分人数
                                examStat.setLowCount(lowCount); // 低分人数
                                examStat.setPassCount(passCount); // 及格人数

                                //Float highestScore = examStudentService.findExamStudentHighestScoreByExamId(examId);
                                examStat.setHighestScore(highestScore); // 全班最高分
                                //Float lowestScore = examStudentService.findExamStudentLowestScoreByExamId(examId);
                                if (lowestScore == -1) {
                                    lowestScore = 0f;
                                }
                                examStat.setLowestScore(lowestScore); // 全班最低分
                                if (examStat.getAverageScore() == null) {
                                    examStat.setAverageScore(0.0f);
                                }
                                if (examStat.getSdScore() == null) {
                                    examStat.setSdScore(0.0f);
                                }
                                if (examStat.getMadValue() == null) {
                                    examStat.setMadValue(0.0f);
                                }
                                if (examStat.getMovValue() == null) {
                                    examStat.setMovValue(0.0f);
                                }
                                scoreMap.put(examStat.getExamId(), examStat.getTotalScore());
                                examStatList.add(examStat);
                                // this.examStatService.modify(examStat);
                            }
                        }
                    }
                }
                ValueComparator bvc = new ValueComparator(scoreMap);
                TreeMap<Integer, Integer> rank_map = new TreeMap<Integer, Integer>(bvc);
                rank_map = sort(scoreMap);
                for (ExamStat es : examStatList) {
                    if (rank_map.get(es.getExamId()) != null) {
                        es.setGradeRank(rank_map.get(es.getExamId()));
                    } else {
                        es.setGradeRank(0);
                    }
                }
                String SQL = "UPDATE pj_exam_stat es" + " SET es.student_count = ?, es.total_score = ?, es.average_score = ?, es.full_score = ?, es.high_score = ?, es.low_score = ?, es.pass_score = ?, es.sd_score = ?, es.mad_value = ?, es.mov_value = ?, es.high_count = ?, es.low_count = ?, es.pass_count = ?, es.highest_score = ?, es.lowest_score =?, es.grade_rank=?" + " WHERE" + "	id = ?";

                JDBCHandle.jdbcUpdateExamStat(SQL, examStatList);

                // long endTime = System.currentTimeMillis();
                // System.out.println("examStatHandle程序运行时间： "
                // + ((endTime - startTime)) + "毫秒");
            }
    }


    /**
     * @param array
     * @return
     * @function 获取标准差
     * @date 2016年1月26日
     */
    private Float getAdScore(Float[] array, Float avg) {
        double sum = 0f;
        for (int i = 0; i < array.length; i++) {
            sum += Math.sqrt(((double) array[i] - avg) * (array[i] - avg));
        }
        // return (float)(sum / (array.length - 1));
        return (float) (sum / (array.length));
    }

    /**
     * @param array
     * @param avg
     * @return
     * @function 获取离差
     * @date 2016年1月26日
     */
    private Float getMadValue(Float[] array, Float avg) {
        Float date = 0f;
        for (int i = 0; i < array.length; i++) {
            date += Math.abs(array[i] - avg);
        }
        return date / array.length;
    }

    private boolean isExist(Integer id, List<UserPaper> uplist) {
        boolean flag = true;
        for (UserPaper up : uplist) {
            if (up.getTeamId().intValue() == id) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private TreeMap<Integer, Integer> sort(Map<Integer, Object> tr) {
        ValueComparator bvc = new ValueComparator(tr);
        TreeMap<Integer, Object> sorted_map = new TreeMap<Integer, Object>(bvc);
        sorted_map.putAll(tr);
        tr.clear();
        tr.putAll(sorted_map);
        TreeMap<Integer, Integer> rank_map = new TreeMap<Integer, Integer>();
        for (Integer key : sorted_map.keySet()) {
            if (rank_map.size() == 0) {
                rank_map.put(key, 1);
            } else {
                if (tr.get(key) instanceof Float) {
                    if ((Float) tr.get(key) == (Float) tr.get((Integer) rank_map.ceilingKey(rank_map.size() - 1))) {
                        rank_map.put(key, rank_map.ceilingEntry(rank_map.size() - 1).getValue());
                    } else {
                        rank_map.put(key, rank_map.size() + 1);
                    }
                } else {
                    if ((Double) tr.get(key) == (Double) tr.get((Integer) rank_map.ceilingKey(rank_map.size() - 1))) {
                        rank_map.put(key, rank_map.ceilingEntry(rank_map.size() - 1).getValue());
                    } else {
                        rank_map.put(key, rank_map.size() + 1);
                    }
                }
            }
        }
        return rank_map;
    }

    @Override
    public ExamResult initExamStatistics(Integer teamId,
                                         Integer schoolId, Integer teacherId, String type,
                                         String examUUid, String code) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delPaperAnswer(List<Integer> taskIds, Integer type) {
        // TODO Auto-generated method stub

    }

    @Override
    public void initBatchExamStatistics(List<Integer> teamIds,
                                        Integer schoolId, Integer teacherId, String type,
                                        String examUUid, String code) {
        // TODO Auto-generated method stub

    }
}
