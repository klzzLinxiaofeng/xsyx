package platform.szxyzxx.bobao.service;

import platform.education.generalTeachingAffair.model.Grade;
import platform.szxyzxx.bobao.vo.BoBaoDaPing;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface BoBaoDaPingService {
    List<BoBaoDaPing> findByAll(UserInfo userInfo);
    BoBaoDaPing findById(Integer id);
    Integer updateViewDaPing(BoBaoDaPing boBaoDaPing, UserInfo userInfo);
    List<Grade>  findGradeBySchoolId(Integer schoolId,String schoolYear);
    String findByGradeName(String [] array,String schoolYear);
}
