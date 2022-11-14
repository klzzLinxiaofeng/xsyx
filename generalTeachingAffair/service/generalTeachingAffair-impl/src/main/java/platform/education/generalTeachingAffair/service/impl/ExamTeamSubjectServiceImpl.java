package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import platform.education.generalTeachingAffair.dao.ExamQuestionVoDao;
import platform.education.generalTeachingAffair.dao.ExamTeamSubjectDao;
import platform.education.generalTeachingAffair.dao.StudentDao;
import platform.education.generalTeachingAffair.dao.TeamDao;
import platform.education.generalTeachingAffair.model.*;
import platform.education.generalTeachingAffair.service.ExamTeamSubjectService;
import platform.education.generalTeachingAffair.service.GradeService;
import platform.education.generalTeachingAffair.service.StudentService;
import platform.education.generalTeachingAffair.service.TeamService;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectCondition;
import platform.education.generalTeachingAffair.vo.ExamTeamSubjectVo;
import platform.education.generalTeachingAffair.vo.StudentCondition;
import platform.education.generalTeachingAffair.vo.TeamCondition;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExamTeamSubjectServiceImpl implements ExamTeamSubjectService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ExamTeamSubjectDao examTeamSubjectDao;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private ExamQuestionVoDao examQuestionVoDao;
    @Autowired
    private TeamDao teamDao;


    public void setExamTeamSubjectDao(ExamTeamSubjectDao examTeamSubjectDao) {
        this.examTeamSubjectDao = examTeamSubjectDao;
    }

    @Override
    public ExamTeamSubjectVo findExamTeamSubjectVoById(TeamService teamService, StudentService studentService, Integer id) {
        try {

            ExamTeamSubject examTeamSubject = examTeamSubjectDao.findById(id);
            Team team = teamService.findTeamById(examTeamSubject.getTeamId());
            ExamTeamSubjectVo vo = new ExamTeamSubjectVo();
            try {
                BeanUtils.copyProperties(vo, examTeamSubject);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            Integer teamSum = this.getStudentNumByTeam(studentService, examTeamSubject);
            vo.setTeamSum(teamSum);
            vo.setTaskOnlineName(this.isOnline(vo.getTaskOnline()));
            vo.setGradeId(team.getGradeId());
            return vo;
        } catch (Exception e) {
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }

    @Override
    public List<ExamTeamSubjectVo> findGradeByCondition(Integer gradeId,Integer schoolId, String schoolYear,String schoolTrem,Integer type) {
        return examTeamSubjectDao.findGradeByCondition(gradeId,schoolId,schoolYear,schoolTrem,type);
    }

    @Override
    public List<Double> findAvgScoreTeam(Integer schoolId, String schoolYear, String schoolTrem, Integer gradeId,String code, String type) {
        return examTeamSubjectDao.findAvgScoreTeam( schoolId, schoolYear, schoolTrem, gradeId,code, type);
    }

    @Override
    public List<Double> findAvgScoreGrade(Integer schoolId, String schoolYear, String schoolTrem, Integer gradeId, String code) {
        return examTeamSubjectDao.findAvgScoreGrade( schoolId, schoolYear, schoolTrem, gradeId,code);
    }

    @Override
    public ExamTeamSubject add(ExamTeamSubject examTeamSubject) {
        if (examTeamSubject == null) {
            return null;
        }
        Date createDate = examTeamSubject.getCreateDate();
        if (createDate == null) {
            createDate = new Date();
        }
        examTeamSubject.setIsDelete(false);
        examTeamSubject.setCreateDate(createDate);
        examTeamSubject.setModifyDate(createDate);

        ExamTeamSubject examTeamSubject1= examTeamSubjectDao.create(examTeamSubject);
        //添加到题目表中
        System.out.println("考试类型"+examTeamSubject.getExamType());
        List<Student> studentList=studentDao.findStudentByTeamId2(examTeamSubject.getTeamId(),examTeamSubject.getGradeId());
        System.out.println(examTeamSubject.getExamNumber());
        for(int i= 0;i<examTeamSubject.getExamNumber();i++){
            ExamQuestionVo examQuestionVo=new ExamQuestionVo();
            examQuestionVo.setExamTeamSubjectId(examTeamSubject.getId());
            examQuestionVo.setSubjectCode(examTeamSubject.getSubjectCode());
            examQuestionVo.setCreateTime(examTeamSubject.getCreateDate());
            examQuestionVo.setModiyTime(examTeamSubject.getModifyDate());
            examQuestionVo.setTitle(null);
            examQuestionVo.setSchoolYear(examTeamSubject.getSchoolYear());
            examQuestionVo.setSchoolTrem(examTeamSubject.getTerm());
            examQuestionVo.setGradeId(examTeamSubject.getGradeId());
            examQuestionVo.setTeamId(examTeamSubject.getTeamId());
            examQuestionVo.setExamName(examTeamSubject.getExamName());
            examQuestionVo.setExamType(examTeamSubject.getExamType());
            examQuestionVo.setSchoolId(examTeamSubject.getSchoolId());
            examQuestionVo.setOrder(i+1);
            examQuestionVo.setFenzhi(null);
            examTeamSubjectDao.createquest(examQuestionVo);
            //添加到分值表
            for(Student aa:studentList){
                ExamQuestionScoreVo examQuestionScoreVo=new ExamQuestionScoreVo();
                try {
                    BeanUtils.copyProperties(examQuestionScoreVo, examQuestionVo);
                    examQuestionScoreVo.setStudentId(aa.getId());
                    examQuestionScoreVo.setExamQueactionId(examQuestionVo.getId());
                    examTeamSubjectDao.createquestScore(examQuestionScoreVo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        //判断
        return examTeamSubject1;
    }

    @Override
    public ExamTeamSubject modify(ExamTeamSubject examTeamSubject) {
        if (examTeamSubject == null) {
            return null;
        }
//        Date modify = examTeamSubject.getModifyDate();
//        examTeamSubject.setModifyDate(modify != null ? modify : new Date());
        List<Student> studentList=studentDao.findStudentByTeamId2(examTeamSubject.getTeamId(),examTeamSubject.getGradeId());
        List<ExamQuestionVo> examQuestionVos=examQuestionVoDao.findByAll(examTeamSubject.getId());
        for(ExamQuestionVo aa:examQuestionVos){
            //先删除题目表中的题目，在重新添加
            examQuestionVoDao.updateScoreDelete(aa.getId());
            examQuestionVoDao.updateDelete(aa.getId());
        }
        for(int i= 0;i<examTeamSubject.getExamNumber();i++){
            ExamQuestionVo examQuestionVo=new ExamQuestionVo();
            examQuestionVo.setExamTeamSubjectId(examTeamSubject.getId());
            examQuestionVo.setSubjectCode(examTeamSubject.getSubjectCode());
            examQuestionVo.setCreateTime(new Date());
            examQuestionVo.setModiyTime(new Date());
            examQuestionVo.setTitle(null);
            examQuestionVo.setSchoolYear(examTeamSubject.getSchoolYear());
            examQuestionVo.setSchoolTrem(examTeamSubject.getTerm());
            examQuestionVo.setGradeId(examTeamSubject.getGradeId());
            examQuestionVo.setTeamId(examTeamSubject.getTeamId());
            examQuestionVo.setExamName(examTeamSubject.getExamName());
            examQuestionVo.setExamType(examTeamSubject.getExamType());
            examQuestionVo.setSchoolId(examTeamSubject.getSchoolId());
            examQuestionVo.setOrder(i+1);
            examQuestionVo.setFenzhi(null);
            examTeamSubjectDao.createquest(examQuestionVo);
            //添加到分值表
            for(Student aa:studentList){
                ExamQuestionScoreVo examQuestionScoreVo=new ExamQuestionScoreVo();
                try {
                    BeanUtils.copyProperties(examQuestionScoreVo, examQuestionVo);
                    examQuestionScoreVo.setStudentId(aa.getId());
                    examQuestionScoreVo.setExamQueactionId(examQuestionVo.getId());
                    examTeamSubjectDao.createquestScore(examQuestionScoreVo);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }


        return examTeamSubjectDao.update(examTeamSubject);
    }

    @Override
    public void remove(ExamTeamSubject examTeamSubject) {
        examTeamSubject.setIsDelete(true);
        examTeamSubject.setModifyDate(new Date());
        List<ExamQuestionVo> examQuestionVos=examQuestionVoDao.findByAll(examTeamSubject.getId());
        for(ExamQuestionVo aa:examQuestionVos){
            //先删除题目表中的题目，在重新添加
            examQuestionVoDao.updateScoreDelete(aa.getId());
            examQuestionVoDao.updateDelete(aa.getId());
        }
        examTeamSubjectDao.delete(examTeamSubject);

    }


    @Override
    public List<ExamTeamSubjectVo> findExamTeamSubjectVoByCondition(TeamService teamService, StudentService studentService, ExamTeamSubjectCondition examTeamSubjectCondition, Page page, Order order) {
        examTeamSubjectCondition.setIsDelete(false);
        List<ExamTeamSubjectVo> examTeamSubjectVoList = new ArrayList<ExamTeamSubjectVo>();
        List<ExamTeamSubject> examTeamSubjectList = new ArrayList<ExamTeamSubject>();
        if (examTeamSubjectCondition.getTeamId() != null && examTeamSubjectCondition.getTeamId() > 0) {
            examTeamSubjectList = examTeamSubjectDao.findExamTeamSubjectByCondition(examTeamSubjectCondition, page, order);
        } else {
            if (examTeamSubjectCondition.getGradeId() != null && examTeamSubjectCondition.getGradeId() > 0) {
                TeamCondition teamCondition = new TeamCondition();
                teamCondition.setGradeId(examTeamSubjectCondition.getGradeId());
                teamCondition.setSchoolYear(examTeamSubjectCondition.getSchoolYear());
                List<Team> teamList = new ArrayList<Team>();
                teamList = teamService.findTeamByCondition(teamCondition, null, null);
                for (Team team : teamList) {
                    examTeamSubjectCondition.setTeamId(team.getId());
                    List<ExamTeamSubject> examTeamSubjectListTemp = examTeamSubjectDao.findExamTeamSubjectByCondition(examTeamSubjectCondition, page, order);
                    for (ExamTeamSubject examTeamSubject : examTeamSubjectListTemp) {
                        examTeamSubjectList.add(examTeamSubject);
                    }
                }

            } else {
                examTeamSubjectList = examTeamSubjectDao.findExamTeamSubjectByCondition(examTeamSubjectCondition, page, order);
            }
        }

        for (ExamTeamSubject examTeamSubject : examTeamSubjectList) {
            ExamTeamSubjectVo vo = new ExamTeamSubjectVo();
            try {
                BeanUtils.copyProperties(vo, examTeamSubject);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            Integer teamSum = this.getStudentNumByTeam(studentService, examTeamSubject);
            vo.setTeamSum(teamSum);
            vo.setTaskOnlineName(this.isOnline(vo.getTaskOnline()));
            vo.setTeamName(teamService.findTeamById(examTeamSubject.getTeamId()).getName());
            examTeamSubjectVoList.add(vo);
        }
        return examTeamSubjectVoList;
    }

    @Override
    public List<ExamTeamSubjectVo> findExamTeamSubjectVoByCondition(StudentService studentService, ExamTeamSubjectCondition examTeamSubjectCondition) {

        examTeamSubjectCondition.setIsDelete(false);
        List<ExamTeamSubjectVo> examTeamSubjectVoList = new ArrayList<ExamTeamSubjectVo>();
		/*Page page = new Page();
		page.setPageSize(Integer.MAX_VALUE);*/
        List<ExamTeamSubject> examTeamSubjectList = examTeamSubjectDao.findExamTeamSubjectByCondition(examTeamSubjectCondition, null, null);
        for (ExamTeamSubject examTeamSubject : examTeamSubjectList) {
            ExamTeamSubjectVo vo = new ExamTeamSubjectVo();
            try {
                BeanUtils.copyProperties(vo, examTeamSubject);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            Integer teamSum = this.getStudentNumByTeam(studentService, examTeamSubject);
            vo.setTeamSum(teamSum);
            examTeamSubjectVoList.add(vo);
        }
        return examTeamSubjectVoList;

    }

    @Override
    public List<ExamTeamSubjectVo> findExamTeamSubjectVoByCondition(StudentService studentService, ExamTeamSubjectCondition examTeamSubjectCondition, Page page) {

        examTeamSubjectCondition.setIsDelete(false);
        List<ExamTeamSubjectVo> examTeamSubjectVoList = new ArrayList<ExamTeamSubjectVo>();
        page.setPageSize(Integer.MAX_VALUE);
        List<ExamTeamSubject> examTeamSubjectList = examTeamSubjectDao.findExamTeamSubjectByCondition(examTeamSubjectCondition, page, null);
        for (ExamTeamSubject examTeamSubject : examTeamSubjectList) {
            ExamTeamSubjectVo vo = new ExamTeamSubjectVo();
            try {
                BeanUtils.copyProperties(vo, examTeamSubject);
            } catch (Exception e) {
                log.error(e.getMessage());
                e.printStackTrace();
            }
            Integer teamSum = this.getStudentNumByTeam(studentService, examTeamSubject);
            vo.setTeamSum(teamSum);
            examTeamSubjectVoList.add(vo);
        }
        return examTeamSubjectVoList;


    }

    @Override
    public List<ExamTeamSubjectVo> findExamTeamSubjectVoByCondition(StudentService studentService, ExamTeamSubjectCondition examTeamSubjectCondition, Order order) {


        examTeamSubjectCondition.setIsDelete(false);
        List<ExamTeamSubjectVo> examTeamSubjectVoList = new ArrayList<ExamTeamSubjectVo>();
        Page page = new Page();
        page.setPageSize(Integer.MAX_VALUE);
        List<ExamTeamSubject> examTeamSubjectList = examTeamSubjectDao.findExamTeamSubjectByCondition(examTeamSubjectCondition, page, order);
        for (ExamTeamSubject examTeamSubject : examTeamSubjectList) {
            ExamTeamSubjectVo vo = new ExamTeamSubjectVo();
            try {
                BeanUtils.copyProperties(vo, examTeamSubject);
            } catch (Exception e) {
                log.error(e.getMessage());
            }
            Integer teamSum = this.getStudentNumByTeam(studentService, examTeamSubject);
            vo.setTeamSum(teamSum);
            examTeamSubjectVoList.add(vo);
        }
        return examTeamSubjectVoList;


    }

    @Override
    public Long count() {
        return this.examTeamSubjectDao.count(null);
    }

    @Override
    public Long count(ExamTeamSubjectCondition examTeamSubjectCondition) {
        examTeamSubjectCondition.setIsDelete(false);
        return this.examTeamSubjectDao.count(examTeamSubjectCondition);
    }

    @Override
    public ExamTeamSubjectVo addVo(GradeService gradeService, TeamService teamService, ExamTeamSubjectVo examTeamSubjectVo, String uuid) {

        ExamTeamSubject examTeamSubject = new ExamTeamSubject();

        List<Integer> teamList = this.getTeamIdByGrade(gradeService, teamService, examTeamSubjectVo);

        try {
            examTeamSubjectVo.setCreateDate(new Date());
            examTeamSubjectVo.setModifyDate(new Date());
            examTeamSubjectVo.setPreciseEndDate(new Date());
            /**
             * 2021年1月12日10:18:47
             * 新增一个uuid用来标识同一个年级的考试日程
             */
            examTeamSubjectVo.setUuid(uuid);
            for (Integer teamid : teamList) {
                BeanUtils.copyProperties(examTeamSubject, examTeamSubjectVo);
                examTeamSubject.setTeamId(teamid);
                this.add(examTeamSubject);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
        }


        ExamTeamSubjectVo examTeamSubjectVoEnd = new ExamTeamSubjectVo();
        examTeamSubjectVoEnd.setId(1);
        return examTeamSubjectVoEnd;
    }

    public List<Integer> getTeamIdByGrade(GradeService gradeService, TeamService teamService, ExamTeamSubjectVo examTeamSubjectVo) {

        List<Integer> list = new ArrayList<Integer>();
        Integer gradeId = examTeamSubjectVo.getGradeId();
        String teamIdCollection = examTeamSubjectVo.getTeamIdCollection();
        if (gradeId == null || gradeId == 0) {
            gradeId = null;
        }
        if ((gradeId == null || gradeId == 0) && this.isNull(teamIdCollection)) {

            List<Grade> gradeList = gradeService.findGradeBySchoolYear(examTeamSubjectVo.getSchoolId(), examTeamSubjectVo.getSchoolYear());

            for (Grade grade : gradeList) {
                TeamCondition teamCondition = new TeamCondition();
                teamCondition.setGradeId(grade.getId());
                teamCondition.setSchoolYear(examTeamSubjectVo.getSchoolYear());
                List<Team> teamList = teamService.findTeamByCondition(teamCondition, null, null);

                if (teamList != null && teamList.size() > 0) {
                    for (Team team : teamList) {
                        list.add(team.getId());
                    }
                }
            }

        } else if (!(gradeId == null || gradeId == 0) && this.isNull(teamIdCollection)) {

            TeamCondition teamCondition = new TeamCondition();
            teamCondition.setGradeId(gradeId);
            teamCondition.setSchoolYear(examTeamSubjectVo.getSchoolYear());
            List<Team> teamList = teamService.findTeamByCondition(teamCondition, null, null);

            if (teamList != null && teamList.size() > 0) {
                for (Team team : teamList) {
                    list.add(team.getId());
                }
            }

        } else if (!(gradeId == null || gradeId == 0) && !this.isNull(teamIdCollection)) {
            String[] teamArray = teamIdCollection.split(",");
            for (String teamStr : teamArray) {
                Integer teamid = Integer.parseInt(teamStr);
                list.add(teamid);
            }

        }

        return list;
    }

    @Override
    public ExamTeamSubject findById(Integer id) {
        return examTeamSubjectDao.findById(id);
    }
    @Override
    public ExamTeamSubject findByExamId(Integer id) {
        return examTeamSubjectDao.findByExamId(id);
    }

    /**
     * 获取一个班级中的有效人数
     *
     * @param studentService
     * @param
     * @return
     */
    private Integer getStudentNumByTeam(StudentService studentService, ExamTeamSubject examTeamSubject) {
       Team team=teamDao.findById(examTeamSubject.getTeamId());

        //可以修改一个学生的查询条件 进行性能优化
        StudentCondition studentCondition = new StudentCondition();
        studentCondition.setSchoolYear(examTeamSubject.getSchoolYear());
        studentCondition.setTeamId(examTeamSubject.getTeamId());
        studentCondition.setGradeId(team.getGradeId());
        List<Student> studentList = studentService.findStudentByCondition(studentCondition, null, null);
        //List<Student> studentList = studentService.getStudentOfTeam(teamid);

        return studentList == null ? 0 : studentList.size();

    }

    @Override
    public ExamTeamSubject addVo(ExamTeamSubjectVo examTeamSubjectVo) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * 判断是否为null
     *
     * @param str
     * @return
     */
    private boolean isNull(String str) {
        boolean flag = false;
        if (str == null || "".equals(str)) {
            flag = true;
        }
        return flag;
    }

    private String isOnline(int isonline) {

        if (isonline == 1) {
            return "是";
        } else if (isonline == 0) {
            return "否";
        } else {
            return "其他";
        }

    }

    public static void main(String[] args) {
        ExamTeamSubject examTeamSubject = new ExamTeamSubject();
        ExamTeamSubjectVo examTeamSubjectVo = new ExamTeamSubjectVo();
        examTeamSubjectVo.setCreateDate(new Date());
        examTeamSubjectVo.setPreciseStartDate(new Date());
        examTeamSubjectVo.setGradeId(1);
        examTeamSubjectVo.setId(1);
        examTeamSubjectVo.setIsDelete(false);
        examTeamSubjectVo.setModifyDate(new Date());
        examTeamSubjectVo.setExamName("name");
        examTeamSubjectVo.setSchoolId(1);
        examTeamSubjectVo.setSchoolYear("2003");
        examTeamSubjectVo.setPreciseEndDate(new Date());
        examTeamSubjectVo.setSubjectCode("002");
        examTeamSubjectVo.setSubjectName("subjectName");
        examTeamSubjectVo.setTeamId(2);
        examTeamSubjectVo.setTeamName("teamName");
        examTeamSubjectVo.setExamType("type");
        examTeamSubjectVo.setTeamIdCollection("examTeamSubjectVo");

        try {
            BeanUtils.copyProperties(examTeamSubject, examTeamSubjectVo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(examTeamSubject.getExamName());
        System.out.println(examTeamSubject.getSchoolYear());
        System.out.println(examTeamSubject.getSubjectCode());
        System.out.println(examTeamSubject.getExamType());
        System.out.println(examTeamSubject.getCreateDate());
        System.out.println(examTeamSubject.getPreciseEndDate());
        System.out.println(examTeamSubject.getId());
        System.out.println(examTeamSubject.getIsDelete());
        System.out.println(examTeamSubject.getModifyDate());
        System.out.println(examTeamSubject.getPreciseStartDate());
        System.out.println(examTeamSubject.getTeamId());
    }

}
