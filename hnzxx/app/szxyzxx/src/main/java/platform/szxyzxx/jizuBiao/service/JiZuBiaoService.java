package platform.szxyzxx.jizuBiao.service;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.szxyzxx.jizuBiao.pojo.JiZuBiao;
import platform.szxyzxx.jizuBiao.pojo.JiZuTeacherBiao;
import platform.szxyzxx.jizuBiao.pojo.TeacherWoke;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

public interface JiZuBiaoService {
    List<Map<String,Object>>  getAll( String schoolYear, String schoolTream, Integer zhoushu, Integer jizuId);
    List<Map<String,Object>>  AllJiZu( String schoolYear,String schoolTream,Integer zhoushu,UserInfo userInfo);
    List<JiZuBiao> findByAllJiZu(Integer jiZuId,Integer jizuceng);
    String create(JiZuBiao jiZuBiao);
    String createZiJiZu(JiZuBiao jiZuBiao);
    String deleteJiZuBiao(JiZuBiao jiZuBiao);
    String updateJiZuBiao(JiZuBiao jiZuBiao);
    String updateTeacherJiZuZhang( Integer teacherId,  Integer id);
    String createTeacherJiZu(JiZuTeacherBiao jiZuTeacherBiao);
    String updateTeacherJiZu(JiZuTeacherBiao jiZuTeacherBiao,Integer jiZuId);
    String createTeacherWoke(TeacherWoke teacherWoke);
    List<TeacherWoke> findByAllTeacherId(Integer teacherId,String schoolYear,String schoolTrem,Integer zhoushu);
    String updateDelete(Integer id);
    String updateTeacherWoke(Integer id,String tontent);
    List<Teacher> teacherAll(String teacher, Integer buMenId, String subjectId, Integer jiZuId, Page page);
    List<TeacherWoke> findByDaoChu(String schoolYear,String schoolTrem,Integer zhoushu);
    String addTeacherAllWoke(TeacherWoke teacherWoke);
}