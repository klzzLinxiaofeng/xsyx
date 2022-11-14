package platform.szxyzxx.knowledge.service;

import framework.generic.dao.Page;
import platform.szxyzxx.knowledge.vo.KnowEvaluation;
import platform.szxyzxx.knowledge.vo.PdfVo;
import platform.szxyzxx.knowledge.vo.StudentVo;

import java.util.List;

public interface KnowEvaluationSercice {
    List<StudentVo> findByAll(String schoolYear, Integer gradeId, Integer teamId, Page page);
 List<KnowEvaluation> findByAllMenu(Integer knowId,Integer studentId, Page page);
 Integer createOrUpdate(KnowEvaluation knowEvaluation);
 KnowEvaluation findById(Integer id);
 List<PdfVo> findByPdf(Integer gradeId,Integer studentId,Integer subject);









}
