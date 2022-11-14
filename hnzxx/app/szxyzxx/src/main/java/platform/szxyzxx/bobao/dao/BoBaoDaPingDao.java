package platform.szxyzxx.bobao.dao;

import platform.education.generalTeachingAffair.model.Grade;
import platform.szxyzxx.bobao.vo.BoBaoDaPing;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface BoBaoDaPingDao {
    List<BoBaoDaPing> findByAll(UserInfo userInfo);
    BoBaoDaPing findByGradeId(int [] gradeIds);
    BoBaoDaPing findById(Integer id);
    Integer updateViewDaPing(BoBaoDaPing boBaoDaPing, UserInfo userInfo);
    List<Grade> findGradeBySchoolId(Integer schoolId,String schoolYear);
    String findByGradeName(String [] array, String schoolYear);
}
