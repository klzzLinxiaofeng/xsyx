package platform.szxyzxx.literacy.service;

import framework.generic.dao.Page;
import platform.szxyzxx.literacy.pojo.LiteracyStudent;
import platform.szxyzxx.literacy.pojo.LiteracyVo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

public interface LiteracyStudentService {
    List<LiteracyStudent>  findByAll(String xn, Integer nj, Integer bj, Integer km, Page page, UserInfo user);
    List<LiteracyStudent> findByStudent(Integer schoolId,Integer studentId,Integer subjectId,String xn,String xq, Page page);
    List<LiteracyStudent> findByliteracy(Integer id,Integer teamId);
    List<LiteracyVo> getAllSubjectZhibiao(String xn,String xq,Integer gradeId,Integer subjectId);
    Integer updatePingyu(Integer id, String pingyu);
    LiteracyStudent findByLiteracyId(Integer schoolId,Integer id);
    Integer updatePingFen(Integer stuId,Integer zhibiaoId,Integer fenshu);
}
