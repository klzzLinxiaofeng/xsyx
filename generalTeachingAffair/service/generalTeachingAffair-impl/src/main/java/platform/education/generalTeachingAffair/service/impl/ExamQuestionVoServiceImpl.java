package platform.education.generalTeachingAffair.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.ExamQuestionVoDao;
import platform.education.generalTeachingAffair.model.ExamQuestionVo;
import platform.education.generalTeachingAffair.model.StudentScore;
import platform.education.generalTeachingAffair.service.ExamQuestionVoService;

import java.util.List;

@Service
public class ExamQuestionVoServiceImpl implements ExamQuestionVoService {

    @Autowired
    private ExamQuestionVoDao examQuestionVoDao;
    @Override
    public List<ExamQuestionVo> findByAll(Integer examId) {
        return examQuestionVoDao.findByAll(examId);
    }

    @Override
    public Integer updateFenzhi(Integer id, String fenzhi) {
        examQuestionVoDao.updateFenZhi(id,fenzhi);
        return examQuestionVoDao.updateScoreFenZhi(id,fenzhi);
    }

    @Override
    public List<ExamQuestionVo> findByTop(ExamQuestionVo subjectScorePojo) {
        return examQuestionVoDao.findByTop(subjectScorePojo);
    }

    @Override
    public Boolean updateScore(Integer studentId, Integer exanId, double score) {
        return 0<examQuestionVoDao.updateScore(studentId,exanId,score);
    }

    @Override
    public StudentScore findByStudentScore(Integer studentId, Integer examId) {
        return examQuestionVoDao.findByStudentScore(studentId,examId);
    }

    @Override
    public Integer updateScores(StudentScore studentScore) {
        return examQuestionVoDao.updateScores(studentScore.getStudentId(),studentScore.getExamTeamSubjectId(),Double.parseDouble(studentScore.getScore()));
    }

    @Override
    public Integer updateTaskRate(Integer examId) {
        return examQuestionVoDao.updateTaskRate(examId);
    }
}
