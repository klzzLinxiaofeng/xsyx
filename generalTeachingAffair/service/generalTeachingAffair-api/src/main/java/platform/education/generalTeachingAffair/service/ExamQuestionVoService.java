package platform.education.generalTeachingAffair.service;

import platform.education.generalTeachingAffair.model.ExamQuestionVo;
import platform.education.generalTeachingAffair.model.StudentScore;

import java.util.List;

public interface ExamQuestionVoService {
    public List<ExamQuestionVo> findByAll(Integer examId);
    Integer updateFenzhi(Integer id,String fenzhi);
    List<ExamQuestionVo> findByTop(ExamQuestionVo examQuestionVo);

    Boolean updateScore(Integer studentId,Integer exanId,double score);

    StudentScore findByStudentScore(Integer studentId,Integer examId);

    Integer updateScores(StudentScore studentScore);
    Integer updateTaskRate(Integer examId);
}
