package platform.szxyzxx.jizuBiao.dao;

import platform.szxyzxx.jizuBiao.pojo.JiZuTeacherBiao;
import platform.szxyzxx.jizuBiao.pojo.TeacherWoke;

import java.util.List;

public interface JiaoZhiGongDao {
    //后勤
    List<JiZuTeacherBiao> findByHouQin(String name,Integer schoolId);
    //科任教师
    List<JiZuTeacherBiao> findByKeRen(String name,Integer schoolId);
    Integer createTeacherWoke(TeacherWoke teacherWoke);
    //导出
    List<JiZuTeacherBiao> findBydaochus(String name,Integer schoolId);
    //
    List<TeacherWoke> findBydaochuer(String schoolYear,String schoolTrem,Integer zhoushu,String name);

}
