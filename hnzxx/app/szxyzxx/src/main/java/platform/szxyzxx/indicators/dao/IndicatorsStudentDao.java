package platform.szxyzxx.indicators.dao;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.indicators.pojo.IndicatorsStudent;

import java.util.List;

public interface IndicatorsStudentDao {
    List<Student> findByStudent(Integer schoolId, Integer gradeId, Integer teamId, String stuName, String xn, Page page);
    List<IndicatorsStudent> findBystudents(Integer studentId,String schoolYear,Integer schoolId);
   IndicatorsStudent findByIndicaStudent(Integer schoolId,Integer studentId);
    List<Integer>  findByQingJia(Integer studentId,Integer schoolId);
    Integer updateBaogao(IndicatorsStudent indicatorsStudent);
    Integer updateIndiStudent(IndicatorsStudent IndicatorsStudent);
    Student findByStudentId(Integer StudentId);
    Integer  findByStudentIdss(Integer studentId);
   Integer  create(IndicatorsStudent indicatorsStudent);
   Integer createStudent();
}
