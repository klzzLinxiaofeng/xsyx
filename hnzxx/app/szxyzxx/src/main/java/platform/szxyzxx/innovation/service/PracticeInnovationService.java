package platform.szxyzxx.innovation.service;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Grade;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.innovation.pojo.PracticeInnovation;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface PracticeInnovationService {
  List<Grade> gradejsonList(Integer teacherId, String schoolYear);
  List<Student> findByStudent(UserInfo userInfo, Integer  gradeId, String schoolYear, Integer teamId,String stuName, Page page);
  PracticeInnovation findByPraInner(UserInfo userInfo,Integer id);
  List<PracticeInnovation> findByPraInnerAll(UserInfo userInfo,Integer studentId,String schoolYear,String schoolTrem);
  String createOrupdate(PracticeInnovation practiceInnovation,UserInfo userInfo);
  List<Student> findByStudentErweiMa(UserInfo userInfo, Integer  gradeId, String schoolYear, Integer teamId,String stuName, Page page);


}
