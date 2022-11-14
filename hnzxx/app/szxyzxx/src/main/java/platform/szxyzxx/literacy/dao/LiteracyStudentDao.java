package platform.szxyzxx.literacy.dao;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Subject;
import platform.szxyzxx.literacy.pojo.LiteracyStudent;
import platform.szxyzxx.literacy.pojo.LiteracyVo;

import java.util.List;

public interface LiteracyStudentDao {
    List<LiteracyStudent> findByAll(Integer schoolId,String xn, Integer bj, Page page);
    List<Subject> findBySubId(Integer schoolId, Integer id,String gradeId,String year);
    List<LiteracyStudent> findByStudent(Integer schoolId, Integer studentId,Integer km,String xn,String xq, Page page);
    LiteracyStudent findByLiteracyId(Integer schoolId,Integer id);
    List<LiteracyStudent> findByliteracy(Integer id,Integer teamId);

    List<LiteracyVo> getAllSubjectZhibiao(String xn, String xq, Integer gradeId, Integer subjectId);

    LiteracyStudent findByStudentIdAndLiteracyId(Integer studentId,Integer liteacyId);

    Integer updatePingyu(Integer id, String pingyu);
    Integer updatePingFen(Integer stuId, Integer zhibiaoId, Integer fenshu);
}
