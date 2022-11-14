package platform.szxyzxx.jizuBiao.dao;

import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.szxyzxx.jizuBiao.pojo.JiZuBiao;
import platform.szxyzxx.jizuBiao.pojo.JiZuTeacherBiao;
import platform.szxyzxx.jizuBiao.pojo.TeacherWoke;

import java.util.List;

public interface JiZuBiaoDao {
     List<JiZuBiao> findByAll(Integer jizuId);
     List<JiZuBiao> findByAllIs(Integer jizuId,Integer jizuceng);
     List<JiZuBiao> findByAllJZu(Integer jizuCeng,Integer zijiZu);

     List<JiZuTeacherBiao> findByjizuId(Integer jizuId);

     Integer findByCount(Integer teacherId,String schoolYear, String schoolTerm, Integer zhoushu);
     //添加级组
     Integer create(JiZuBiao jiZuBiao);
     //添加子集组
     Integer createZijiZu(JiZuBiao jiZuBiao);
     Integer  updateTeacherJiZuZhang(Integer teacherId,Integer id);

     Integer updateJiZu(JiZuBiao jiZuBiao);
     //----------/---------------
     Integer createTeacherJiZu(JiZuTeacherBiao jiZuTeacherBiao);
     Integer updateTeacherJiZu(JiZuTeacherBiao jiZuTeacherBiao);
     void updateTeacherJiZuId(Integer jizuId);
     //--------------------------------------------
     //删除工作内容
     void updateTeacherWoke(Integer teacherId,Integer jizuId);
     Integer  createTeacherWoke(TeacherWoke teacherWoke);
     List<TeacherWoke> findByAllTeacherId(Integer teacherId ,String schoolYear,String schoolTrem,Integer zhoushu);
     Integer updateDelete(Integer id);
     Integer updateTeacherWokes(Integer id,String tontent);
     List<Integer> findByIdIntegers(Integer id);

     List<Teacher> findByTeacher(String name, Integer numenId, String subjectId, Integer jizuId, Page page);
     List<TeacherWoke> findByDaoChu(String schoolYear,String schoolTrem,Integer zhoushu);


}
