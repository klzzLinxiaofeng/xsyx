package platform.szxyzxx.exam.dao;

import platform.education.generalTeachingAffair.model.ExamQuestionScoreVo;

public interface ExamScoreDao {
    ExamQuestionScoreVo findById(Integer studentId, Integer examQueactionId, Integer teamId,String schoolYear,String subjectCode);
    Double findByTeamZongfen(Integer examQueactionId, Integer teamId,String schoolYear,String schoolTrem,String subjectCode,String examName);
    Double findByZongfen(Integer examQueactionId, Integer teamId,String schoolYear,String schoolTrem,String subjectCode,String examName);
}
