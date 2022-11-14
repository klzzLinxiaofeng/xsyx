package platform.szxyzxx.character.service;


import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.character.pojo.Evaluation;
import platform.szxyzxx.character.pojo.GongShiDaoChu;
import platform.szxyzxx.character.pojo.Records;

import java.util.List;

public interface EvaluationService {
    /*指标 start*/
    boolean create(Evaluation evaluation);
    List<Evaluation> findByAll();

    String abandonTime(String ids);

    Evaluation findById(int id);
    boolean update(Evaluation evaluation);

    /*指标 end*/
    /*评价 start*/
    List<Records> findByAllRecords(Integer schoolId, String xq, Integer bg, Integer nj, String stuName, Page page);

    List<Student> findByStudentAll(Integer schoolId, Integer bg, Integer nj,String xn);
    /*评价 end*/
    List<GongShiDaoChu> findByAllDaoChu();
}
