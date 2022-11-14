package platform.szxyzxx.aesthetic.dao;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Student;
import platform.szxyzxx.aesthetic.pojo.AestheticPojo;
import platform.szxyzxx.aesthetic.pojo.ErWerMa;

import java.util.List;

public interface AestheticDao {
  List<AestheticPojo> findByAesthetic(Integer studentId, Integer schoolId, String schoolYear, String schoolTrem, Page page);
  AestheticPojo findByAestheticPojoId(Integer id);
  Student findByStudentId(Integer studentId,Integer schoolId);
  Integer create(AestheticPojo aestheticPojo);
  Integer update(AestheticPojo aestheticPojo);
  Integer createErweima(ErWerMa erWerMa);
  ErWerMa findByEeWeiMa(Integer studentId,String schoolYaer);
}
