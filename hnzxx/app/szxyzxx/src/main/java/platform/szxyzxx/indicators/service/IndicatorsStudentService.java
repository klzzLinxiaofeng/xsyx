package platform.szxyzxx.indicators.service;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.indicators.pojo.IndicatorsStudent;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface IndicatorsStudentService {
    List<Student> findByStudent(Integer schoolId, Integer gradeId, Integer teamId, String stuName,String xn, Page page);
    IndicatorsStudent findByIndicator(UserInfo user , Integer studentId);
    Integer updateIn(IndicatorsStudent indicatorsStudent);
   String  updatess(IndicatorsStudent indicatorsStudent);
}
