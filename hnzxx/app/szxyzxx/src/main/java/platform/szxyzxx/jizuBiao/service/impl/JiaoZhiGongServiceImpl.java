package platform.szxyzxx.jizuBiao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.jizuBiao.dao.JiZuBiaoDao;
import platform.szxyzxx.jizuBiao.dao.JiaoZhiGongDao;
import platform.szxyzxx.jizuBiao.pojo.JiZuTeacherBiao;
import platform.szxyzxx.jizuBiao.pojo.TeacherWoke;
import platform.szxyzxx.jizuBiao.service.JiaoZhiGongService;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JiaoZhiGongServiceImpl implements JiaoZhiGongService {
    @Autowired
    private JiaoZhiGongDao jiaoZhiGongDao;
    @Autowired
    private JiZuBiaoDao jiZuBiaoDao;

    @Override
    public Map<String,Object> findByTeacher(String schoolYear, String schoolTrem, String name, Integer zhoushu, UserInfo userInfo) {
        Map<String,Object> list=new HashMap<>();
        List<JiZuTeacherBiao> list1=jiaoZhiGongDao.findByHouQin(name,userInfo.getSchoolId());
        List<JiZuTeacherBiao> list2=jiaoZhiGongDao.findByKeRen(name,userInfo.getSchoolId());
        if(list1.size()>0){
            for(JiZuTeacherBiao aa:list1){
                Integer num=jiZuBiaoDao.findByCount(aa.getTeacherId(),schoolYear,schoolTrem,zhoushu);
                aa.setZongshu(num);
            }
        }
        if(list2.size()>0){
            for(JiZuTeacherBiao aa:list2){
                Integer num=jiZuBiaoDao.findByCount(aa.getTeacherId(),schoolYear,schoolTrem,zhoushu);
                aa.setZongshu(num);
            }
        }
        list.put("keren",list2);
        list.put("houqin",list1);
        return list;
    }

    @Override
    public String createTeackerWoke(TeacherWoke teacherWoke) {
        Integer num=jiaoZhiGongDao.createTeacherWoke(teacherWoke);
        if(num>0){
            return "success";
        }
        return "error";
    }

    @Override
    public List<TeacherWoke> findByDaoChu(String schoolYear, String schoolTrem, Integer zhoushu, String name,UserInfo userInfo) {
        List<TeacherWoke> teacherWokeList=jiaoZhiGongDao.findBydaochuer(schoolYear,schoolTrem,zhoushu,name);
        return teacherWokeList;
    }
}
