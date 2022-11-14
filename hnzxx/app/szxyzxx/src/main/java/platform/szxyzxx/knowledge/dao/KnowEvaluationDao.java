package platform.szxyzxx.knowledge.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.knowledge.vo.KnowEvaluation;
import platform.szxyzxx.knowledge.vo.PdfVo;
import platform.szxyzxx.knowledge.vo.StudentVo;

import java.util.List;

public interface KnowEvaluationDao {
    List<StudentVo> findByAll(String schoolYear, Integer gradeId, Integer teamId , Page page);
    List<KnowEvaluation> findByPinjai(Integer knowId, Integer leven, Integer parentMenu ,Integer studentId,Page page);
    Integer create(KnowEvaluation knowEvaluation);
    Integer update(KnowEvaluation knowEvaluation);
    KnowEvaluation findById(Integer id);
    KnowEvaluation findByKnowId(Integer knowId, Integer studentId);
    List<PdfVo> findByPdf(Integer gradeId,Integer subjectId);
}
