package platform.szxyzxx.literacy.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.literacy.pojo.LiteracyStudent;
import platform.szxyzxx.literacy.pojo.LiteracyVo;

import java.util.List;

public interface LiteracyDao {
    List<LiteracyVo> findByAll(String xq, Integer xn, Integer nj, Integer km, Page page);
    Integer   create(LiteracyVo literacyVo);
    Integer   createsss(LiteracyStudent literacyStudent,Long aa);
    LiteracyVo findById(Integer id);
    Integer update(LiteracyVo literacyVo);
    Integer updatefenshu(Integer id,Integer score,String pingyu);
    //导入修改分数
    Integer updateDaorufenshu(Integer studentId,Integer lituId,Integer score);

    List<LiteracyStudent> getByStudent(Integer gradeId,Integer schoolId);
    Integer updatestudent(Integer literacyId);
    List<LiteracyVo> findByName(LiteracyVo literacyVo);
    LiteracyVo findBySchoolYearAndSchoolTrem(String year,
                                             String schoolTrem,
                                             Integer gardeId,
                                             Integer subjectId,
                                             String name);
}
