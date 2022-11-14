package platform.education.generalTeachingAffair.dao;

import org.apache.ibatis.annotations.Param;
import platform.education.generalTeachingAffair.model.ExamQuestionVo;
import platform.education.generalTeachingAffair.model.ExamTeamSubject;
import platform.education.generalTeachingAffair.model.StudentScore;

import java.util.List;

public interface ExamQuestionVoDao {
    List<ExamQuestionVo> findByAll(@Param("examId") Integer examId);

    Integer updateFenZhi(@Param("id") Integer id, @Param("fenzhi") String fenzhi);
    Integer updateTaskRate(@Param("examId") Integer examId);
    Integer updateScoreFenZhi(@Param("id") Integer id, @Param("fenzhi") String fenzhi);

    Integer updateDelete(@Param("id") Integer id);

    Integer updateScoreDelete(@Param("id") Integer id);

    List<ExamQuestionVo> findByTop(ExamQuestionVo examQuestionVo);
    StudentScore findByStudentScore(Integer studentId,Integer examId);
    Integer updateScore(Integer studentId,Integer examId,double score);
    Integer updateScores(Integer studentId,Integer examId,double score);
    List<ExamTeamSubject> findByExamTeamSubject(Integer schoolId,String schoolYear,String schoolTrem,String examName,String subjectCode);
    Integer updateExamSubject(Integer examId,Integer zhuangtai);

    List<ExamTeamSubject> findByExamTeam(Integer gradeId,String subjectCode,String schoolYear,String tremCode,Integer examType,Integer schoolId,String examName );
}