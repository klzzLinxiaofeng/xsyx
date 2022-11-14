package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.dao.ExamQuestionDao;
import platform.education.generalTeachingAffair.dao.PjExamDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PjExamServiceImpl implements PjExamService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private PjExamDao pjExamDao;

    private ExamQuestionDao examQuestionDao;

    public ExamQuestionDao getExamQuestionDao() {
        return examQuestionDao;
    }

    public void setExamQuestionDao(ExamQuestionDao examQuestionDao) {
        this.examQuestionDao = examQuestionDao;
    }

    /**
     * 测试学生
     */
    private ExamStudentService examStudentService;

    /**
     * 班级考试统计
     */
    private ExamStatService examStatService;

    /**
     * 班级学生
     */
    private TeamStudentService teamStudentService;

    /**
     * 学生
     */
    private StudentService studentService;
    /**
     * 教师科目
     */
    private TeamTeacherService teamTeacherService;

    public void setTeamTeacherService(TeamTeacherService teamTeacherService) {
        this.teamTeacherService = teamTeacherService;
    }

    public void setPjExamDao(PjExamDao pjExamDao) {
        this.pjExamDao = pjExamDao;
    }

    public void setExamStudentService(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }

    public void setExamStatService(ExamStatService examStatService) {
        this.examStatService = examStatService;
    }

    public void setTeamStudentService(TeamStudentService teamStudentService) {
        this.teamStudentService = teamStudentService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public PjExam findPjExamById(Integer id) {
        try {
            return pjExamDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public PjExam add(PjExam pjExam) {
        if (pjExam == null) {
            return null;
        }
        Integer teacherId = getTeacherIdInTeanTeacher(pjExam.getSchoolYear(),
                pjExam.getGradeId(), pjExam.getTeamId(),
                pjExam.getSubjectCode());
        Date createDate = pjExam.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        pjExam.setCreateDate(createDate);
        pjExam.setModifyDate(createDate);
        pjExam.setTeacherId(teacherId);
        return pjExamDao.create(pjExam);
    }

    @Override
    public PjExam modify(PjExam pjExam) {
        if (pjExam == null) {
            return null;
        }
        Date modify = pjExam.getModifyDate();
        pjExam.setModifyDate(modify != null ? modify : new Date());
        return pjExamDao.update(pjExam);
    }

    @Override
    public void remove(PjExam pjExam) {
        try {
            pjExamDao.delete(pjExam);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjExam.getId(), e);
            }
        }
    }

    @Override
    public List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition,
                                              Page page, Order order) {
        return pjExamDao.findPjExamByCondition(pjExamCondition, page, order);
    }

    @Override
    public List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition) {
        return pjExamDao.findPjExamByCondition(pjExamCondition, null, null);
    }

    @Override
    public List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition,
                                              Page page) {
        return pjExamDao.findPjExamByCondition(pjExamCondition, page, null);
    }

    @Override
    public List<PjExam> findPjExamByCondition(PjExamCondition pjExamCondition,
                                              Order order) {
        return pjExamDao.findPjExamByCondition(pjExamCondition, null, order);
    }

    @Override
    public Long count() {
        return this.pjExamDao.count(null);
    }

    @Override
    public Long count(PjExamCondition pjExamCondition) {
        return this.pjExamDao.count(pjExamCondition);
    }

    @Override
    public List<PjExamVo> findPjExamMoreByCondition(PjExamCondition pjExamCondition,
                                                    Page page, Order order) {
        return pjExamDao.findPjExamMoreByCondition(pjExamCondition, page, order);
    }

    /**
     * 功能描述：初始化成绩录入数据，包括创建pj_exam表, pj_exam_student表, pj_exam_stat表 2015-01-06
     *
     * @param exam
     * @return
     */
    @Override
    public ExamResult InitExamData(PjExam exam) {
        Float fullScore = exam.getFullScorce();
        ExamResult result = new ExamResult();
        try {
            PjExam pjExam = null;
            // 初始化pj_exam表
            if (!exam.getExamType().equals("11") && !exam.getExamType().equals("12")) {
                pjExam = this.pjExamDao.findUnique(exam.getSchoolYear(),
                        exam.getTermValue(), exam.getTeamId(), exam.getExamType(),
                        exam.getExamRound(), exam.getSubjectCode());

            }
            if (pjExam == null) {
                exam = add(exam);
            } else {
                exam = pjExam;
            }

            if (exam != null) {
                result.setPjExam(exam);
                // 初始化pj_exam_student表,已存在的会进行覆盖
                initExamStudent(exam.getTeamId(), exam.getId());

                // 初始化pj_exam_stat表
                ExamStat examStat = examStatService.findExamStatByExamId(exam.getId());
                if (examStat == null) {
                    initExamStat(exam.getId(), fullScore);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setStatus(PjExamService.OPERATE_ERROR);
            return null;
        }
        result.setStatus(PjExamService.OPERATE_SUCCESS);
        return result;
    }

    /**
     * 功能描述：根据teamId获得班级学生，初始化pj_exam_student表 2015-01-06
     *
     * @param teamId
     * @param examId
     */
    public void initExamStudent(Integer teamId, Integer examId) {
        PjExam exam = pjExamDao.findById(examId);
        TeamStudentCondition condition = new TeamStudentCondition();
        condition.setTeamId(teamId);
        condition.setIsDelete(false);
        condition.setInState(true);
        List<TeamStudent> teamStudentList = teamStudentService
                .findTeamStudentByCondition(condition, null, null);
        if (!exam.getExamType().equals("11") && !exam.getExamType().equals("12")) {
            for (TeamStudent teamStudent : teamStudentList) {
                Student student = studentService.findStudentById(teamStudent
                        .getStudentId());
                if (student != null) {
                    addExamStudent(student, examId, teamStudent.getNumber());
                }
            }
        } else {
            ExamStudent[] eslist = new ExamStudent[teamStudentList.size()];
            int i = 0;
            for (TeamStudent teamStudent : teamStudentList) {
                ExamStudent examStudent = new ExamStudent();
                examStudent.setExamId(examId);
                examStudent.setStudentId(teamStudent.getStudentId());
                examStudent.setUserId(teamStudent.getUserId());
                examStudent.setNumber(teamStudent.getNumber()); // 学生班内编号（顺序编号）
                examStudent.setName(teamStudent.getName()); // 姓名（如果同班有同名用别名）
                examStudent.setTestType("01"); // 01--正常考试
                examStudent.setScore(-1F);
                examStudent.setCreateDate(new Date());
                examStudent.setModifyDate(new Date());
//			examStudent = examStudentService.add(examStudent);
                eslist[i] = examStudent;
                i++;
            }
            examStudentService.createBatch(eslist);
        }

    }

    public Integer getTeacherIdInTeanTeacher(String schoolYear,
                                             Integer gradeId, Integer teamId, String subjectCode) {
        Integer teacherId = null;
        TeamTeacher teamTeacher = null;
        TeamTeacherCondition teamTeacherCondition = new TeamTeacherCondition();
        teamTeacherCondition.setSchoolYear(schoolYear);
        teamTeacherCondition.setGradeId(gradeId);
        teamTeacherCondition.setTeamId(teamId);
        teamTeacherCondition.setSubjectCode(subjectCode);
        teamTeacherCondition.setDelete(false);
        List<TeamTeacher> list = teamTeacherService.findTeamTeacherByCondition(
                teamTeacherCondition, null, null);
        if (list.size() > 0) {
            teamTeacher = list.get(0);
        }
        if (teamTeacher != null) {
            teacherId = teamTeacher.getTeacherId();
        }
        return teacherId;
    }

    /**
     * 功能描述：为学生创建pj_exam_student表记录 2015-01-06
     *
     * @param student
     * @param examId
     * @param number
     * @return
     */
    public ExamStudent addExamStudent(Student student, Integer examId,
                                      Integer number) {

        // 如果记录中已经存在了某个学生 不再进行添加
        List<ExamStudent> examStudentList = examStudentService
                .findExamStudentsByExamId(examId);
        if (examStudentList.size() > 0) {
            for (ExamStudent es : examStudentList) {
                if (es.getStudentId().equals(student.getId())) {
                    return null;
                }
            }
        }

        ExamStudent examStudent = new ExamStudent();
        examStudent.setExamId(examId);
        examStudent.setStudentId(student.getId());
        examStudent.setUserId(student.getUserId());
        examStudent.setNumber(number); // 学生班内编号（顺序编号）
        examStudent.setName(student.getName()); // 姓名（如果同班有同名用别名）
        examStudent.setTestType("01"); // 01--正常考试
        examStudent.setScore(-1F);
        examStudent.setCreateDate(new Date());
        examStudent.setModifyDate(new Date());
        examStudent = examStudentService.add(examStudent);
        return examStudent;
    }

    /**
     * 功能描述：初始化pj_exam_stat表 2015-01-06
     *
     * @param examId
     * @return
     */
    public ExamStat initExamStat(Integer examId, Float fullScore) {
        ExamStat examStat = new ExamStat();
        examStat.setExamId(examId);
        examStat.setFullScore(fullScore);
        examStat.setAverageScore(0.0f);
        examStat.setTotalScore(0.0f);
        examStat.setStudentCount(0);
        examStat.setCreateDate(new Date());
        examStat.setModifyDate(new Date());
        examStat.setDataChanged(true);
        examStat = examStatService.add(examStat);
        return examStat;
    }

    /**
     * 功能描述：查找某学期内，某个班级的某个科目的所有考试记录，学期根据学年代码、学期国标代码确定 2016-01-06
     */
    @Override
    public List<PjExam> findExamsBySubject(String schoolYear, String termValue,
                                           Integer teamId, String subjectCode) {
        return this.pjExamDao.findExamsBySubject(schoolYear, termValue, teamId,
                subjectCode);
    }

    /**
     * 功能描述：查找某学期内，某个班级的某个考试类型的所有考试成绩记录，所有轮次 2016-01-06
     */
    @Override
    public List<PjExam> findExamsByType(String schoolYear, String termValue,
                                        Integer teamId, String examType) {
        return this.pjExamDao.findExamsByType(schoolYear, termValue, teamId,
                examType, null);
    }

    /**
     * 功能描述：查找某学期内，某个班级的某个考试类型的所有考试成绩记录，定位到具体某个轮次 2016-01-06
     */
    @Override
    public List<PjExam> findExamsByType(String schoolYear, String termValue,
                                        Integer teamId, String examType, Integer examRound) {
        return this.pjExamDao.findExamsByType(schoolYear, termValue, teamId,
                examType, examRound);
    }

    /**
     * 功能描述：逻辑上删除即数据库仍然保存记录，只是修改删除标识 2016-01-07
     */
    @Override
    public String abandon(PjExam pjExam) {
        if (pjExam != null) {
            pjExam.setIsDelete(true);
            try {
                pjExam = this.pjExamDao.update(pjExam);
                if (pjExam != null) {
                    // 删除某一科成绩后传入参数（examId）查询pj_exam_student表中与其相关记录并删除
                    examStudentService.removeExamStudents(pjExam.getId());
                    // 删除某一科成绩后删除pj_exam_stat中与其相关记录
                    ExamStat examStat = examStatService
                            .findExamStatByExamId(pjExam.getId());
                    if (examStat != null) {
                        examStatService.remove(examStat);
                    }
                    return PjExamService.OPERATE_SUCCESS;
                }
            } catch (Exception e) {
                if (log.isInfoEnabled()) {
                    log.info("删除失败", pjExam.getId(), e);
                }
                return PjExamService.OPERATE_ERROR;
            }
        }
        return PjExamService.OPERATE_FAIL;
    }

    /**
     * 功能描述：查询出唯一pj_exam记录 2016-01-07
     *
     * @param schoolYear
     * @param termValue
     * @param teamId
     * @param examType
     * @param examRound
     * @param subjectCode
     * @return
     */
    @Override
    public PjExam findUnique(String schoolYear, String termValue,
                             Integer teamId, String examType, Integer examRound,
                             String subjectCode) {
        try {
            return pjExamDao.findUnique(schoolYear, termValue, teamId,
                    examType, examRound, subjectCode);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("查询失败");
        }
        return null;
    }

    @Override
    public List<ExamQuestionWrongVo> findExamQuestionWrongbyExamId(
            Integer[] examIds, Integer type, float rate) {

        return examQuestionDao.findExamQuestionWrongbyExamId(examIds, type, rate);
    }

    @Override
    public List<ExamQuestionWrongVo> findExamQuestionWrongForStudent(
            Integer gradeId, Integer teamId, String subjectCode, Integer type, float rate, Order order) {
        return examQuestionDao.findExamQuestionWrongForStudent(gradeId, teamId, subjectCode, type, rate, order);
    }

    @Override
    public List<ExamTeamNumber> findTeamNumberOfCode(String code) {
        return pjExamDao.findTeamNumberOfCode(code);
    }

    @Override
    public void createBatch(PjExam[] eslist) {
        if (eslist != null && eslist.length > 0) {
            pjExamDao.createBatch(eslist);
        }
    }

    @Override
    public void modifyDelectByJointExamCode(String code, String type) {

        pjExamDao.updateDelectByJointExamCode(code, type);

    }

    @Override
    public StatisticsTeamScore findTeamAvgByCode(String code, String type, Integer order) {
        List<StatisticsTeamScore> glist = new ArrayList<StatisticsTeamScore>();
        List<TeamAvgScroeVo> list = new ArrayList<TeamAvgScroeVo>();
        list = pjExamDao.findTeamAvgByCode(code, type, order);
        StatisticsTeamScore ss = new StatisticsTeamScore();
        float score = 0;
        Integer count = 0;
        Float avg = 0.0f;
        if (list != null) {
            float[] a = new float[list.size()];
            int j = 0;
            for (TeamAvgScroeVo vo : list) {
                float sc = 0.0f;
                if (vo.getAverageScore() != null && vo.getStudentCount() != null && vo.getStudentCount() != 0) {
                    BigDecimal bg = new BigDecimal(vo.getTotalScore() + "");
                    BigDecimal bg2 = new BigDecimal(vo.getStudentCount() + "");
                    sc = bg.divide(bg2, 2, BigDecimal.ROUND_HALF_UP).floatValue();
//					sc=vo.getAverageScore();
                }
                vo.setAverageScore(sc);
                a[j] = sc;
                j++;
            }
            float[] b = new float[a.length];
            int[] c = new int[a.length];
            for (int i = 0; i < a.length; i++) {
                b[i] = a[i];
            }
            Arrays.sort(b);
            for (TeamAvgScroeVo vo : list) {
                if (vo.getAverageScore() != null) {

                    score = score + vo.getTotalScore();
                }
                float tavg = vo.getAverageScore() == null ? 0.0f : vo.getAverageScore();
                if (vo.getStudentCount() != null && vo.getStudentCount() != 0) {
                    count += vo.getStudentCount();
                }
//				if(vo.getStudentCount()!=null&&vo.getStudentCount()!=0){
//					count +=vo.getStudentCount();
//					BigDecimal bg=new BigDecimal(vo.getTotalScore()+"");
//					BigDecimal bg2=new BigDecimal(vo.getStudentCount()+"");
//					tavg=bg.divide(bg2,2,0).floatValue();
//				}
                vo.setAverageScore(tavg);
                vo.setTeamRank(getIndex1(tavg, b));
            }
            BigDecimal bg = new BigDecimal(1.0f + "");
            BigDecimal bg2 = new BigDecimal(1.0f + "");
            if (count != 0 && score != 0) {
                bg = new BigDecimal(score + "");
                bg2 = new BigDecimal(count + "");
                avg = bg.divide(bg2, 2, BigDecimal.ROUND_HALF_UP).floatValue();
            }
            if (order.intValue() == 0) {

                sortNode(list);
            }
            ss.setTeamAvgScore(list);
            ss.setGradeScoreAvg(avg);
            Map<Integer, TeamAvgScroeVo> map = new HashMap<Integer, TeamAvgScroeVo>();
            for (TeamAvgScroeVo v : list) {
                map.put(v.getExamId(), v);
            }
            ss.setTeamMap(map);
        }

        return ss;
    }

    private int getIndex(float e, float[] a) {
        int temp = 1;
        if (e == a[0]) {
            return temp;
        }
        for (int i = temp; i < a.length; i++) {
            if (e == a[i]) {
                return ++temp;
            } else {
                if (a[i - 1] == a[i]) {

                } else {
                    temp++;
                }
            }
        }
        return 0;
    }

    public static int getIndex1(float e, float[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            if (e == a[i]) {
                return a.length - i;
            }
        }
        return 0;
    }

    private void sortNode(List<TeamAvgScroeVo> nodeList) {
        Collections.sort(nodeList, new Comparator<TeamAvgScroeVo>() {
            public int compare(TeamAvgScroeVo node1, TeamAvgScroeVo node2) {
                int n1 = node1.getTeamRank();
                int n2 = node2.getTeamRank();
                if (n1 > n2) {
                    return 1;
                }
                if (n1 == n2) {
                    return 0;
                }
                return -1;
            }
        });
    }
}
