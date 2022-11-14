package platform.education.generalTeachingAffair.service.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import platform.education.generalTeachingAffair.model.ExamWorks;
import platform.education.generalTeachingAffair.service.ExamWorksService;
import platform.education.generalTeachingAffair.service.ExamWorksSubjectTemplateService;
import platform.education.generalTeachingAffair.service.ExamWorksTeamSubjectService;
import platform.education.generalTeachingAffair.service.TeamTeacherService;
import platform.education.generalTeachingAffair.service.test.base.BaseTest;
import platform.education.generalTeachingAffair.vo.ExamWorksTeamSubjectVo;
import platform.education.generalTeachingAffair.vo.ExamWorksVo;
import platform.education.generalTeachingAffair.vo.TeamTeacherVo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/2/1.
 */
public class ExamWorksServiceTest extends BaseTest {

    @Autowired
    @Qualifier("examWorksService")
    private ExamWorksService examWorksService;

    @Autowired
    @Qualifier("examWorksSubjectTemplateService")
    private ExamWorksSubjectTemplateService examWorksSubjectTemplateService;

    @Autowired
    @Qualifier("examWorksTeamSubjectService")
    private ExamWorksTeamSubjectService examWorksTeamSubjectService;

    @Autowired
    @Qualifier("teamTeacherService")
    private TeamTeacherService teamTeacherService;

    /**
     *  新建班级小测
     */
    @Test
    public void initClassExam() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer schoolId = 101;
        String schoolYear = "2016";
        String termCode = "101-2016-2";
        Integer gradeId = 303;
        Integer teamId = 1432;
        String subjectCode = "14";
        String examType = "20";
        Integer examRound = 1;
        String name = "数学单元小测一";
        Date beginDate = sdf.parse("2018-02-05");
        Date endDate = sdf.parse("2018-02-06");
        Boolean isShowRanking = true;
        Float fullScore = 100f;
        Float highScore = 90f;
        Float lowScore = 80f;
        Float passScore = 60f;
        Integer userId = 22163;
        Integer teacherId = 1397;
        examWorksService.initClassExam(schoolId, schoolYear, termCode, gradeId, teamId, subjectCode, examType,examRound,
                name, beginDate, endDate, isShowRanking, fullScore, highScore, lowScore, passScore ,teacherId);
    }

    /**
     * 新建统考
     * @throws ParseException
     */
    @Test
    public void initJointExam() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Integer schoolId = 101;
        String schoolYear = "2016";
        String termCode = "101-2016-2";
        String examType = "01";
        Integer examRound = null;
        String name = "期中考试";
        Date beginDate = sdf.parse("2018-02-07");
        Date endDate = sdf.parse("2018-02-08");
        Boolean isShowRanking = true;
        Integer teacherId = 1397;
        String gradeIds = "303";
        examWorksService.initJointExam(schoolId, schoolYear, termCode, examType, examRound, name, beginDate, endDate, isShowRanking, gradeIds, teacherId);
    }


    /**
     * 同步科目三率模板
     */
    @Test
    public void testSyncTemplate(){
        Integer schoolId = 111;
        examWorksSubjectTemplateService.syncSchoolTemplate(schoolId, null);
    }

    @Test
    public void testFindClassExamWorksByTeacherId(){
        List<ExamWorksVo> list = examWorksService.findClassExamWorksByTeacherId(101, "2016","101-2016-2", 1397, null, null);
        for (ExamWorksVo vo : list) {
            System.out.println(vo.getGradeName() + " / " + vo.getTeamName() + " / " + vo.getSubjectName()  + " / " + vo.getName()
                    + " / " + vo.getFullScore()  + " / " + vo.getHighScore() + " / " + vo.getLowScore() + " / " + vo.getPassScore());
        }
    }


    @Test
    public void testFindExamWorksContext() {
        Integer examWorksId = 11;
        List<ExamWorksTeamSubjectVo> list = examWorksTeamSubjectService.findExamWorksContext(examWorksId);
        for (ExamWorksTeamSubjectVo vo : list) {
            System.out.println(vo.getGradeName() +" / "+ vo.getTeamName() +" / "+ vo.getSubjectName() +" / "+ vo.getFullScore());
        }
    }


    @Test
    public void testDeleteExamGrade(){
        examWorksService.deleteExamOfGrade(8, 291);
    }


    @Test
    public void testFindMajorExamWorks(){
        List<ExamWorks> list = examWorksService.findMajorExamWorks(101, "2016", "101-2016-2");
        for (ExamWorks works : list) {
            System.out.println(works.getName() +" / "+ works.getExamType() +" / "+ works.getExamRound());
        }
    }
    @Test
    public void testFindMajorExamWorksByTeam (){
        List<Map<String, Object>> list = examWorksTeamSubjectService.findMajorExamWorksByTeam(41, 1173);
        for (Map<String, Object> map : list) {
            System.out.println(map.toString());
        }
    }

//    @Test
//    public void testTeamTeacher(){
//        int schoolId = 101;
//        String schoolYear = "2016";
//        int userId = 22163;
//        int teacherId = 1397;
//        List<TeamTeacherVo> list = teamTeacherService.findTeamOrGradeOfTeacher(schoolId, schoolYear, userId, teacherId, null, null);
//        System.out.println("**********************************");
//        for (TeamTeacherVo vo : list) {
//            System.out.println(vo.getTeamName() + " / " + vo.getTeamId() + " / " + vo.getType());
//        }
//    }

}
