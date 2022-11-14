package platform.szxyzxx.jizuBiao.service;

import platform.szxyzxx.jizuBiao.pojo.TeacherWoke;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;
import java.util.Map;

public interface JiaoZhiGongService {
    public Map<String,Object> findByTeacher(String schoolYear, String schoolTrem, String name, Integer zhoushu, UserInfo userInfo);
    String createTeackerWoke(TeacherWoke teacherWoke);
    List<TeacherWoke> findByDaoChu(String schoolYear, String schoolTrem, Integer zhoushu, String name,UserInfo userInfo);
}
