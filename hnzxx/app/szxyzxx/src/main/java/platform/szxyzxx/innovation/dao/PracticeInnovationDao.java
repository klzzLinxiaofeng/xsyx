package platform.szxyzxx.innovation.dao;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.PublicClass;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.innovation.pojo.PracticeInnovation;

import java.util.List;

public interface PracticeInnovationDao {
   List<Grade> findByGrade(Integer teacherId, String schoolYear);
   List<Integer> findByTeamId(Integer teacherId, String schoolYear);
   List<Student> findByStudent(Integer schoolid,Integer gradeId, Integer  teamId,String stuName,String schoolYear,Page page);
   PracticeInnovation findByPraInner(Integer schoolId,Integer id);
   List<PracticeInnovation> findByPraInnerAll(Integer schoolId,Integer studentId,String schoolYear,String schoolTrem);
   Student findByStudentId(Integer schoolId,Integer studentId);
   List<PublicClass> findByPublicClass(Integer studentId);
   PracticeInnovation findByPracticeInnovation(Integer studentId);
   Integer create(PracticeInnovation practiceInnovation);

   Integer update(PracticeInnovation practiceInnovation);

   List<Student> findByStudentErweiMa(Integer schoolid, Integer gradeId, Integer teamId, String stuName, Page page);



}
