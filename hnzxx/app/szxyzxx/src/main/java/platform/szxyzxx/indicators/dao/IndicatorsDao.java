package platform.szxyzxx.indicators.dao;

import platform.education.generalTeachingAffair.model.Grade;
import platform.szxyzxx.indicators.pojo.IndicatorsPojo;
import platform.szxyzxx.indicators.pojo.StudentIndicaPojo;

import java.util.List;

public interface IndicatorsDao {

    List<Grade> findBygrade(Integer schoolId,String schoolYear);

    List<IndicatorsPojo> findByAll(Integer gradeId,Integer schoolId,String schoolYear);

    Integer create(IndicatorsPojo indicatorsPojo);

    List<Integer> findByGrade(Integer schoolId,Integer gradeId);
    Integer createStudent(StudentIndicaPojo studentIndicaPojo);
}
