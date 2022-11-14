package platform.education.generalTeachingAffair.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.*;
import platform.education.generalTeachingAffair.vo.*;
import platform.education.generalTeachingAffair.dao.ExamWorksDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class ExamWorksServiceImpl implements ExamWorksService {

    private Logger log = LoggerFactory.getLogger(getClass());

    private ExamWorksDao examWorksDao;

    public void setExamWorksDao(ExamWorksDao examWorksDao) {
        this.examWorksDao = examWorksDao;
    }

    private TeamService teamService;

    private TeamStudentService teamStudentService;

    private TeacherService teacherService;

    private PjExamService pjExamService;

    private ExamStudentService examStudentService;

    private ExamStatService examStatService;

    private ExamStatMajorStudentService examStatMajorStudentService;

    private ExamWorksSubjectService examWorksSubjectService;

    private ExamWorksSubjectTemplateService examWorksSubjectTemplateService;

    private ExamWorksGradeService examWorksGradeService;

    private ExamWorksTeamService examWorksTeamService;

    private ExamWorksTeamSubjectService examWorksTeamSubjectService;

    public void setExamStatMajorStudentService(ExamStatMajorStudentService examStatMajorStudentService) {
        this.examStatMajorStudentService = examStatMajorStudentService;
    }

    public void setExamStatService(ExamStatService examStatService) {
        this.examStatService = examStatService;
    }

    public void setExamStudentService(ExamStudentService examStudentService) {
        this.examStudentService = examStudentService;
    }

    public void setPjExamService(PjExamService pjExamService) {
        this.pjExamService = pjExamService;
    }

    public void setTeacherService(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public void setTeamService(TeamService teamService) {
        this.teamService = teamService;
    }

    public void setTeamStudentService(TeamStudentService teamStudentService) {
        this.teamStudentService = teamStudentService;
    }

    public void setExamWorksSubjectService(ExamWorksSubjectService examWorksSubjectService) {
        this.examWorksSubjectService = examWorksSubjectService;
    }

    public void setExamWorksSubjectTemplateService(ExamWorksSubjectTemplateService examWorksSubjectTemplateService) {
        this.examWorksSubjectTemplateService = examWorksSubjectTemplateService;
    }

    public void setExamWorksGradeService(ExamWorksGradeService examWorksGradeService) {
        this.examWorksGradeService = examWorksGradeService;
    }

    public void setExamWorksTeamService(ExamWorksTeamService examWorksTeamService) {
        this.examWorksTeamService = examWorksTeamService;
    }

    public void setExamWorksTeamSubjectService(ExamWorksTeamSubjectService examWorksTeamSubjectService) {
        this.examWorksTeamSubjectService = examWorksTeamSubjectService;
    }

    @Override
    public ExamWorks findExamWorksById(Integer id) {
        try {
            return examWorksDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public ExamWorks add(ExamWorks examWorks) {
        if (examWorks == null) {
            return null;
        }
        Date createDate = examWorks.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        examWorks.setCreateDate(createDate);
        examWorks.setModifyDate(createDate);
        return examWorksDao.create(examWorks);
    }

    @Override
    public ExamWorks modify(ExamWorks examWorks) {
        if (examWorks == null) {
            return null;
        }
        Date modify = examWorks.getModifyDate();
        examWorks.setModifyDate(modify != null ? modify : new Date());
        return examWorksDao.update(examWorks);
    }

    @Override
    public void remove(ExamWorks examWorks) {
        try {
            examWorksDao.delete(examWorks);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.debug("删除数据库无存在ID为 {} ,异常为：{}", examWorks.getId(), e);
            }
        }
    }

    @Override
    public List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition, Page page, Order order) {
        return examWorksDao.findExamWorksByCondition(examWorksCondition, page, order);
    }

    @Override
    public List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition) {
        return examWorksDao.findExamWorksByCondition(examWorksCondition, null, null);
    }

    @Override
    public List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition, Page page) {
        return examWorksDao.findExamWorksByCondition(examWorksCondition, page, null);
    }

    @Override
    public List<ExamWorks> findExamWorksByCondition(ExamWorksCondition examWorksCondition, Order order) {
        return examWorksDao.findExamWorksByCondition(examWorksCondition, null, order);
    }

    @Override
    public Long count() {
        return this.examWorksDao.count(null);
    }

    @Override
    public Long count(ExamWorksCondition examWorksCondition) {
        return this.examWorksDao.count(examWorksCondition);
    }

    @Override
    public List<ExamWorks> findMajorExamWorks(Integer schoolId, String schoolYear, String termCode) {
        String[] examTypes = {"01", "02", "03", "12"};
        return this.examWorksDao.findMajorExamWorksByType(schoolId, schoolYear, termCode, examTypes);
    }

    @Override
    public List<ExamWorks> findMajorExamWorksByType(Integer schoolId, String schoolYear, String termCode, String type) {
        return this.examWorksDao.findMajorExamWorks(schoolId, schoolYear, termCode, type, null);
    }

    @Override
    public List<ExamWorksVo> findClassExamWorksByTeacherId(Integer schoolId, String schoolYear, String termCode, Integer teacherId, Page page, Order order) {
        return this.examWorksDao.findClassExamWorksByTeacherId(schoolId, schoolYear, termCode, teacherId, page, order);
    }

    @Override
    public void initJointExam(Integer schoolId, String schoolYear, String termCode, String examType,
                              Integer examRound, String name, Date beginDate, Date endDate,
                              Boolean isShowRanking, String gradeIds, Integer teacherId) {
        long firstTime = System.currentTimeMillis();
        //1.添加统考考务 -- 先判断是否存在
        List<ExamWorks> examWorksList = this.examWorksDao.findMajorExamWorks(schoolId, schoolYear, termCode, examType, examRound);
        if (examWorksList != null && examWorksList.size() > 0) {
            return;
        }

        ExamWorks examWorks = addExamWorks(schoolId, schoolYear, termCode, null, null, null, examType, name,
                examRound, true, beginDate, endDate, teacherId, isShowRanking);
        Integer examWorksId = examWorks.getId();
        String[] gradeStr = gradeIds.split(",");
        for (String str : gradeStr) {
            Integer gradeId = Integer.valueOf(str);
            initJointExamOfGrade(schoolId, schoolYear, termCode, examType, name, teacherId, examWorksId, gradeId);
        }

        long end = System.currentTimeMillis();
        System.out.println("---------------------耗时------------------------");
        System.out.println(end - firstTime);
        System.out.println("---------------------耗时------------------------");

    }

    @Override
    public void initJointExamOfGrade(Integer schoolId, String schoolYear, String termCode, String examType,
                                     String name, Integer teacherId, Integer examWorksId, Integer gradeId) {
        /* 2.添加三率一分
            进入年级考试科目设置时，页面从模板表(pj_exam_works_subject_template)中获取学校模板数据
            点击应用（保存）时先将数据存入pj_exam_works_subject表中，
            统考是若不选择，直接添加考务，则需从模板表中获取数据，再添加，添加前做判断是否已存在
         */
        Integer statSubjectCount = 0;    //需统计的科目总数
        List<ExamWorksSubject> examWorksSubjects = examWorksSubjectService.findSubjectsOfExamWorks(examWorksId, gradeId);
        if (examWorksSubjects == null || examWorksSubjects.size() == 0) {
            examWorksSubjects = new ArrayList<>();
            //TODO 设置考务科目配置信息 -- 三率一分
            //先从模板中获取数据，再存入
            List<ExamWorksSubjectTemplate> templates = examWorksSubjectTemplateService.acquireTemplateOfGrade(schoolId, gradeId);
            for (ExamWorksSubjectTemplate template : templates) {
                ExamWorksSubject ews = new ExamWorksSubject();
                ews.setExamWorksId(examWorksId);
                ews.setSchoolId(schoolId);
                ews.setGradeId(gradeId);
                ews.setSubjectCode(template.getSubjectCode());
                ews.setStatNeeded(template.getStatNeeded());
                ews.setFullScore(template.getFullScore());
                ews.setHighScore(template.getHighScore());
                ews.setLowScore(template.getLowScore());
                ews.setPassScore(template.getPassScore());
                ews.setCreateDate(new Date());
                ews.setModifyDate(new Date());
                ews.setIsDelteted(false);
                ews = examWorksSubjectService.add(ews);
                examWorksSubjects.add(ews);
                if (template.getStatNeeded()) {
                    statSubjectCount++;
                }
            }
        } else {
            for (ExamWorksSubject subject : examWorksSubjects) {
                if (subject.getStatNeeded()) {
                    statSubjectCount++;
                }
            }
        }

        //3.添加考务年级
//        List<Team> teamList = teamService.findTeamOfGrade(gradeId);
        List<Team> teamList = teamService.findNotEmptyTeam(gradeId);
        //若整个年级没有学生，跳过该年级的考务创建
        if (teamList == null || teamList.size() == 0) {
            return;
        }

        TeamStudentCondition teamStudentCondition = new TeamStudentCondition();
        teamStudentCondition.setGradeId(gradeId);
        teamStudentCondition.setIsDelete(false);
        teamStudentCondition.setInState(true);
        Integer studentCount = teamStudentService.count(teamStudentCondition).intValue();
        Integer teamCount = teamList.size();
//        Integer teamCount = 0;        //参与考试的班级总数
//        Integer studentCount = 0;    //参与考试的学生总数
//        if (teamList != null) {
//            teamCount = teamList.size();
//            TeamStudentCondition condition = new TeamStudentCondition();
//            condition.setGradeId(gradeId);
//            condition.setIsDelete(false);
//            condition.setInState(true);
//            Long count = teamStudentService.count(condition);
//            if (count != null) {
//                studentCount = count.intValue();
//            }
//        }

        String jointExamCode = UUID.randomUUID().toString().replaceAll("-", "");
        ExamWorksGrade ewGrade = new ExamWorksGrade();
        ewGrade.setExamWorksId(examWorksId);
        ewGrade.setSchoolId(schoolId);
        ewGrade.setGradeId(gradeId);
        ewGrade.setJointExamCode(jointExamCode);
        ewGrade.setStatSubjectCount(statSubjectCount);
        ewGrade.setExamTeamCount(teamCount);
        ewGrade.setExamStudentCount(studentCount);
        ewGrade.setFinishedTeamCount(0);
        ewGrade.setStatStudentCount(0);
        ewGrade.setIsDeleted(false);
        ewGrade.setCreateDate(new Date());
        ewGrade.setModifyDate(new Date());
        ewGrade = examWorksGradeService.add(ewGrade);
        Integer ewGradeId = ewGrade.getId();

        //4.添加考务班级
        for (Team team : teamList) {
            Integer teamId = team.getId();
            ExamWorksTeam ewTeam = new ExamWorksTeam();
            ewTeam.setExamWorksId(examWorksId);
            ewTeam.setExamWorksGradeId(ewGradeId);
            ewTeam.setSchoolId(schoolId);
            ewTeam.setGradeId(gradeId);
            ewTeam.setTeamId(teamId);
            ewTeam.setStatSubjectCount(statSubjectCount);
            ewTeam.setIsDeleted(false);
            ewTeam.setCreateDate(new Date());
            ewTeam.setModifyDate(new Date());
            examWorksTeamService.add(ewTeam);


            List<TeamStudent> studentList = teamStudentService.findByTeamId(teamId);
            //5.添加考务班级科目信息

            //批量添加pj_exam
            PjExam[] examList = new PjExam[examWorksSubjects.size()];
            for (int i = 0; i < examWorksSubjects.size(); i++) {
                String subjectCode = examWorksSubjects.get(i).getSubjectCode();
                PjExam exam = new PjExam();
                exam.setSchoolId(schoolId);
                exam.setGradeId(gradeId);
                exam.setTeamId(teamId);
                exam.setSubjectCode(subjectCode);
                exam.setJointExamCode(jointExamCode);
                exam.setName(name);
                exam.setExamType(examType);
                exam.setExamRound(1);
                exam.setExamDate(new Date());
                exam.setSchoolYear(schoolYear);
                exam.setTermCode(termCode);
                exam.setTermValue(termCode.substring(termCode.length() - 1));
                exam.setTeacherId(teacherId);
                exam.setIsDelete(false);
                exam.setCreateDate(new Date());
                exam.setModifyDate(new Date());
                examList[i] = exam;
            }
            pjExamService.createBatch(examList);

            PjExamCondition condition = new PjExamCondition();
            condition.setSchoolId(schoolId);
            condition.setSchoolYear(schoolYear);
            condition.setTermCode(termCode);
            condition.setGradeId(gradeId);
            condition.setTeamId(teamId);
            condition.setJointExamCode(jointExamCode);
            condition.setExamType(examType);
            condition.setIsDelete(false);
            List<PjExam> pjExamList = pjExamService.findPjExamByCondition(condition);

            //批量添加exam_works_team_subject, exam_student
            ExamWorksTeamSubject[] ewtsList = new ExamWorksTeamSubject[pjExamList.size()];
            ExamStudent[] examStudentList = new ExamStudent[pjExamList.size() * studentList.size()];
            ExamStat[] examStatList = new ExamStat[pjExamList.size()];
            int examStudentCount = 0;
            for (int i = 0; i < pjExamList.size(); i++) {
                PjExam exam = pjExamList.get(i);
                Integer examId = exam.getId();
                ExamWorksTeamSubject ewts = new ExamWorksTeamSubject();
                ewts.setExamWorksId(examWorksId);
                ewts.setExamWorksGradeId(ewGradeId);
                ewts.setExamId(examId);
                ewts.setSchoolId(schoolId);
                ewts.setGradeId(gradeId);
                ewts.setTeamId(teamId);
                ewts.setSubjectCode(exam.getSubjectCode());
                ewts.setTeacherId(teacherId);
                ewts.setIsDeleted(false);
                ewts.setCreateDate(new Date());
                ewts.setModifyDate(new Date());
                ewtsList[i] = ewts;

                //批量添加exam_student
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
                    examStudentList[examStudentCount] = examStudent;
                    examStudentCount++;
                }

                //批量添加exam_stat

                for (ExamWorksSubject examWorksSubject : examWorksSubjects) {
                    if (examWorksSubject.getSubjectCode().equals(exam.getSubjectCode())) {
                        ExamStat examStat = new ExamStat();
                        examStat.setExamId(examId);
                        examStat.setStudentCount(studentList.size());
                        examStat.setFullScore(examWorksSubject.getFullScore());
                        examStat.setHighScore(examWorksSubject.getHighScore());
                        examStat.setLowScore(examWorksSubject.getLowScore());
                        examStat.setPassScore(examWorksSubject.getPassScore());
                        examStat.setDataChanged(false);
                        examStat.setCreateDate(new Date());
                        examStat.setModifyDate(new Date());
                        examStatList[i] = examStat;
                        break;
                    }
                }


            }
            examWorksTeamSubjectService.createBatch(ewtsList);
            examStudentService.createBatch(examStudentList);
            examStatService.createBatch(examStatList);

            //批量添加exam_statMajor_student
            ExamStatMajorStudent[] esmsList = new ExamStatMajorStudent[studentList.size()];
            for (int i = 0; i < studentList.size(); i++) {
                ExamStatMajorStudent majorStudent = new ExamStatMajorStudent();
                majorStudent.setExamWorksId(examWorksId);
                majorStudent.setJointExamCode(jointExamCode);
                majorStudent.setTeamId(teamId);
                majorStudent.setStudentId(studentList.get(i).getStudentId());
                majorStudent.setIsDeleted(false);
                majorStudent.setCreateDate(new Date());
                majorStudent.setModifyDate(new Date());
                esmsList[i] = majorStudent;
            }
            examStatMajorStudentService.createBatch(esmsList);
        }
    }

    @Override
    public int initClassExam(Integer schoolId, String schoolYear, String termCode,
                             Integer gradeId, Integer teamId, String subjectCode,
                             String examType, Integer examRound, String name,
                             Date beginDate, Date endDate, Boolean isShowRanking,
                             Float fullScore, Float highScore, Float lowScore, Float passScore, Integer teacherId) {

        ExamWorks examWorks = addExamWorks(schoolId, schoolYear, termCode, gradeId, teamId, subjectCode, examType, name,
                examRound, false, beginDate, endDate, teacherId, isShowRanking);
        Integer examWorksId = examWorks.getId();

        //添加三率一分
        ExamWorksSubject examWorksSubject = examWorksSubjectService.findUnique(examWorksId, gradeId, subjectCode);
        ExamWorksSubject unique = new ExamWorksSubject();
        unique.setStatNeeded(true);
        unique.setFullScore(fullScore);
        unique.setHighScore(highScore);
        unique.setLowScore(lowScore);
        unique.setPassScore(passScore);
        if (examWorksSubject != null) {
            unique.setId(examWorksSubject.getId());
            examWorksSubjectService.modify(unique);
        } else {
            unique.setExamWorksId(examWorksId);
            unique.setSchoolId(schoolId);
            unique.setGradeId(gradeId);
            unique.setSubjectCode(subjectCode);
            unique.setIsDelteted(false);
            examWorksSubjectService.add(unique);
        }

        //添加exam
        PjExam exam = new PjExam();
        exam.setSchoolId(schoolId);
        exam.setGradeId(gradeId);
        exam.setTeamId(teamId);
        exam.setSubjectCode(subjectCode);
        exam.setJointExamCode(null);
        exam.setName(name);
        exam.setExamType(examType);
        exam.setExamRound(1);
        exam.setExamDate(new Date());
        exam.setSchoolYear(schoolYear);
        exam.setTermCode(termCode);
        exam.setTermValue(termCode.substring(termCode.length() - 1));
        exam.setTeamId(teacherId);
        exam.setIsDelete(false);
        exam.setCreateDate(new Date());
        exam.setModifyDate(new Date());
        exam = pjExamService.add(exam);

        Integer examId = exam.getId();

        //添加考务班级信息
        ExamWorksTeamSubject ewts = new ExamWorksTeamSubject();
        ewts.setExamWorksId(examWorksId);
        ewts.setExamWorksGradeId(null);
        ewts.setExamId(examId);
        ewts.setSchoolId(schoolId);
        ewts.setGradeId(gradeId);
        ewts.setTeamId(teamId);
        ewts.setSubjectCode(subjectCode);
        ewts.setTeacherId(teacherId);
        ewts.setIsDeleted(false);
        ewts.setCreateDate(new Date());
        ewts.setModifyDate(new Date());
        examWorksTeamSubjectService.add(ewts);

        //初始化pj_exam_student
        List<TeamStudent> studentList = teamStudentService.findByTeamId(teamId);
        ExamStudent[] examStudentList = new ExamStudent[studentList.size()];
        TeamStudent student = null;
        ExamStudent examStudent = null;
        for (int i = 0; i < studentList.size(); i++) {
            student = studentList.get(i);
            examStudent = new ExamStudent();
            examStudent.setExamId(examId);
            examStudent.setStudentId(student.getStudentId());
            examStudent.setUserId(student.getUserId());
            examStudent.setNumber(student.getNumber());
            examStudent.setName(student.getName());
            examStudent.setTestType("01");
            examStudent.setScore(-1.0f);
            examStudent.setCreateDate(new Date());
            examStudent.setModifyDate(new Date());
            examStudentList[i] = examStudent;
        }
        examStudentService.createBatch(examStudentList);

        //初始化pj_exam_stat
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

        return examWorksId;
    }

    private ExamWorks addExamWorks(Integer schoolId, String schoolYear, String termCode,
                                   Integer gradeId, Integer teamId, String subjectCode,
                                   String examType, String name, Integer examRound, Boolean isJointExam,
                                   Date beginDate, Date endDate, Integer teacherId, Boolean isShowRanking) {
        ExamWorks examWorks = new ExamWorks();
        examWorks.setUuid(UUID.randomUUID().toString().replaceAll("-", ""));
        examWorks.setSchoolId(schoolId);
        examWorks.setGradeId(gradeId);
        examWorks.setTeamId(teamId);
        examWorks.setSubjectCode(subjectCode);
        examWorks.setSchoolYear(schoolYear);
        examWorks.setTermCode(termCode);
        examWorks.setTermValue(termCode.substring(termCode.length() - 1));
        examWorks.setName(name);
        examWorks.setIsJointExam(isJointExam);
        examWorks.setExamType(examType);
        examWorks.setExamRound(examRound);
        examWorks.setExamDateBegin(beginDate);
        examWorks.setExamDateEnd(endDate);
        examWorks.setTeacherId(teacherId);
        examWorks.setShowRanking(isShowRanking);
        examWorks.setIsDeleted(false);
        examWorks.setCreateDate(new Date());
        examWorks.setModifyDate(new Date());
        return examWorksDao.create(examWorks);
    }

    @Override
    public void deleteExamOfGrade(Integer examWorksId, Integer gradeId) {
        ExamWorksSubject ews = null;
        ExamWorksGrade ewg = null;
        ExamWorksTeam ewt = null;
        ExamWorksTeamSubject ewts = null;
        PjExam exam = null;
        List<ExamWorksSubject> examWorksSubjectList = examWorksSubjectService.findSubjectsOfExamWorks(examWorksId, gradeId);
        for (ExamWorksSubject examWorksSubject : examWorksSubjectList) {
            ews = new ExamWorksSubject(examWorksSubject.getId());
            ews.setIsDelteted(true);
            examWorksSubjectService.modify(ews);
        }

        ExamWorksGrade examWorksGrade = examWorksGradeService.findUnique(examWorksId, gradeId);
        String jointExamCode = null;
        if (examWorksGrade != null) {
            ewg = new ExamWorksGrade(examWorksGrade.getId());
            ewg.setIsDeleted(true);
            examWorksGradeService.modify(ewg);
            jointExamCode = examWorksGrade.getJointExamCode();
        }

        List<ExamWorksTeam> examWorksTeamList = examWorksTeamService.findOfExamWorks(examWorksId, gradeId);
        for (ExamWorksTeam examWorksTeam : examWorksTeamList) {
            ewt = new ExamWorksTeam(examWorksTeam.getId());
            ewt.setIsDeleted(true);
            examWorksTeamService.modify(ewt);
        }

        ExamWorksTeamSubjectCondition ewtsCondition = new ExamWorksTeamSubjectCondition();
        ewtsCondition.setExamWorksId(examWorksId);
        ewtsCondition.setGradeId(gradeId);
        List<ExamWorksTeamSubject> worksTeamSubjectList = examWorksTeamSubjectService.findExamWorksTeamSubjectByCondition(ewtsCondition);
        for (ExamWorksTeamSubject teamSubject : worksTeamSubjectList) {
            ewts = new ExamWorksTeamSubject(teamSubject.getId());
            ewts.setIsDeleted(true);
            examWorksTeamSubjectService.modify(ewts);

            exam = new PjExam(teamSubject.getExamId());
            exam.setIsDelete(true);
            pjExamService.modify(exam);
        }

        if (jointExamCode != null && !"".equals(jointExamCode)) {
            ExamStatMajorStudentCondition condition = new ExamStatMajorStudentCondition();
            condition.setIsDeleted(false);
            condition.setExamWorksId(examWorksId);
            condition.setJointExamCode(jointExamCode);
            List<ExamStatMajorStudent> list = examStatMajorStudentService.findExamStatMajorStudentByCondition(condition);
            ExamStatMajorStudent esms = null;
            for (ExamStatMajorStudent statMajorStudent : list) {
                esms = new ExamStatMajorStudent(statMajorStudent.getId());
                esms.setIsDeleted(true);
                examStatMajorStudentService.modify(esms);
            }
        }

    }

    @Override
    public void modifyClassExam(Integer examWorksId, String name, Date beginDate, Date endDate, Float fullScore, Float highScore, Float lowScore, Float passScore) {
        ExamWorks examWorks = examWorksDao.findById(examWorksId);
        if (examWorks != null) {
            ExamWorks ew = new ExamWorks(examWorksId);
            ew.setName(name);
            ew.setExamDateBegin(beginDate);
            ew.setExamDateEnd(endDate);
            examWorksDao.update(ew);

            ExamWorksSubject subject = examWorksSubjectService.findUnique(examWorksId, examWorks.getGradeId(), examWorks.getSubjectCode());
            ExamWorksSubject ews = new ExamWorksSubject(subject.getId());
            ews.setFullScore(fullScore);
            ews.setHighScore(highScore);
            ews.setLowScore(lowScore);
            ews.setPassScore(passScore);
            examWorksSubjectService.modify(ews);

            ExamWorksTeamSubject teamSubject = examWorksTeamSubjectService.findUnique(examWorksId, examWorks.getTeamId(), examWorks.getSubjectCode());
            Integer examId = teamSubject.getExamId();
            PjExam exam = new PjExam(examId);
            exam.setName(name);
            pjExamService.modify(exam);

            ExamStat examStat = examStatService.findExamStatByExamId(examId);
            ExamStat es = new ExamStat(examStat.getId());
            es.setFullScore(fullScore);
            es.setHighScore(highScore);
            es.setLowScore(lowScore);
            es.setPassScore(passScore);
            examStatService.modify(es);
        }
    }

    @Override
    public void deleteClassExam(Integer examWorksId) {
        ExamWorks examWorks = examWorksDao.findById(examWorksId);
        if (examWorks != null) {
            ExamWorks ew = new ExamWorks(examWorksId);
            ew.setIsDeleted(true);
            examWorksDao.update(ew);

            ExamWorksSubject subject = examWorksSubjectService.findUnique(examWorksId, examWorks.getGradeId(), examWorks.getSubjectCode());
            ExamWorksSubject ews = new ExamWorksSubject(subject.getId());
            ews.setIsDelteted(true);
            examWorksSubjectService.modify(ews);

            ExamWorksTeamSubject teamSubject = examWorksTeamSubjectService.findUnique(examWorksId, examWorks.getTeamId(), examWorks.getSubjectCode());
            ExamWorksTeamSubject ewts = new ExamWorksTeamSubject(teamSubject.getId());
            ewts.setIsDeleted(true);
            examWorksTeamSubjectService.modify(ewts);

            PjExam exam = new PjExam(teamSubject.getExamId());
            exam.setIsDelete(true);
            pjExamService.modify(exam);
        }
    }

    @Override
    public List<ExamWorksVo> findExamWorksOfStudent(Integer schoolId, String schoolYear, String termCode, Integer userId, Page page, Order order) {
        return this.examWorksDao.findExamWorksOfStudent(schoolId, schoolYear, termCode, userId, page, order);
    }

    @Override
    public List<ExamWorks> findExamWorksByType(Integer schoolId, String schoolYear, String termCode, Boolean isJoint,Integer teamId, Page page, Order order) {
        return this.examWorksDao.findExamWorksByType(schoolId, schoolYear, termCode, isJoint, teamId, page, order);
    }
}
