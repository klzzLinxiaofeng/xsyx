package platform.szxyzxx.huojiang.service.impl;


import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.dao.TeacherDao;
import platform.education.generalTeachingAffair.model.Teacher;
import platform.szxyzxx.huojiang.dao.ClassLunwenDao;
import platform.szxyzxx.huojiang.service.ClassLunwenService;
import platform.szxyzxx.huojiang.vo.ClassLunwen;
import platform.szxyzxx.huojiang.vo.DaochuPojo;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ClassLunwenServiceImpl implements ClassLunwenService {

    @Autowired
    private ClassLunwenDao classLunwenDao;
    @Autowired
    private TeacherDao teacherDao;
    @Override
    public List<ClassLunwen> findByAll(String startTime, String endTime, String type, String teacherName,Integer leixing,String theme, Page page) {
        List<ClassLunwen> classLunwenList=classLunwenDao.findByAll(startTime,endTime,type,teacherName,leixing,theme,page);
        List<ClassLunwen> classLunwens=new ArrayList<>();
        for(ClassLunwen aa:classLunwenList){
            String [] taecherarr=aa.getTeacherNames().split(",");
            String [] taecherarrId=aa.getTeacherIds().split(",");
            for(int a=0;a<taecherarr.length;a++){
                ClassLunwen classLunwen=aa.clone();
                classLunwen.setTeacherIds(taecherarrId[a]);
                classLunwen.setTeacherNames(taecherarr[a]);
                classLunwens.add(classLunwen);
            }
        }
        return classLunwens;
    }

    @Override
    public ClassLunwen findById(Integer id, Integer taecherId) {
        ClassLunwen classLunwen=classLunwenDao.findById(id);
        if(classLunwen.getTeacherIds()!=null && classLunwen.getTeacherNames()!=null){
            String [] taecherarr=classLunwen.getTeacherNames().split(",");
            String [] taecherarrId=classLunwen.getTeacherIds().split(",");
            ClassLunwen classLunwen2=classLunwen.clone();
            for(int a=0;a<taecherarr.length;a++){
                if(Integer.parseInt(taecherarrId[a])==taecherId){
                    classLunwen2.setTeacherIds(taecherarrId[a]);
                    classLunwen2.setTeacherNames(taecherarr[a]);
                    return classLunwen2;
                }
            }
        }
        return null;
    }

    @Override
    public List<DaochuPojo> findByDaoAll(String startTime, String endTime, String type, String teacherName, Integer leixing, String theme) {
        List<ClassLunwen> classLunwenList=classLunwenDao.findByAll(startTime,endTime,type,teacherName,leixing,theme,null);
        List<DaochuPojo> classLunwens=new ArrayList<>();
        for(ClassLunwen aa:classLunwenList){
            String [] taecherarr=aa.getTeacherNames().split(",");
            for(int a=0;a<taecherarr.length;a++){
                DaochuPojo daochuPojo=new DaochuPojo();
                daochuPojo.setTheme(aa.getTheme());
                daochuPojo.setAllocated(aa.getAllocated());
                daochuPojo.setWinningTime(aa.getWinningTime());
                daochuPojo.setNameWoke(aa.getNameWoke());
                daochuPojo.setStudentNames(aa.getStudentNames());
                daochuPojo.setWinningLevelName(aa.getWinningLevelName());
                daochuPojo.setDengciName(aa.getDengciName());
                daochuPojo.setShuXing(aa.getShuXing());
                daochuPojo.setType(aa.getType());
                daochuPojo.setScore(aa.getScore());
                daochuPojo.setJiXiaoDeFen(aa.getJiXiaoDeFen());
                daochuPojo.setTeacherNames(taecherarr[a]);
                classLunwens.add(daochuPojo);
            }
        }
        return classLunwens;
    }

    @Override
    public List<ClassLunwen> findByTongJi(String teacherName, UserInfo userInfo,Page page) {
        List<ClassLunwen> classLunwenList=new ArrayList<>();
        List<Teacher> teachers=teacherDao.findTeacherByLikeNameAndSchool(teacherName,userInfo.getSchoolId(),page,null);
        for(Teacher aa:teachers){
            ClassLunwen classLunwen=new ClassLunwen();
            ClassLunwen asd=classLunwenDao.findByTongJi(aa.getId());
            classLunwen.setTeacherNames(aa.getName());
            classLunwen.setEmpCode(aa.getEmpCode());
            if(asd!=null){
                classLunwen.setScore(asd.getScore());
                classLunwen.setJiXiaoDeFen(asd.getJiXiaoDeFen());
            }else{
                classLunwen.setScore(0);
                classLunwen.setJiXiaoDeFen(0);
            }
            classLunwen.setTeacherIds(aa.getId().toString());
            classLunwenList.add(classLunwen);
        }
        Collections.sort(classLunwenList, new Comparator<ClassLunwen>() {
            @Override
            public int compare(ClassLunwen o1, ClassLunwen o2) {
                if(o1.getScore()!=o2.getScore()){
                        return o2.getScore()-o1.getScore();
                }else{
                    return o2.getTeacherNames().compareTo(o1.getTeacherNames());
                }
            }
        });

        return classLunwenList;
    }

    @Override
    public List<ClassLunwen> findByhuojiangJiLu(Integer teacherId) {
        List<ClassLunwen> classLunwenList=classLunwenDao.findByhuojiangJiLu(teacherId);
        List<ClassLunwen> classLunwens=new ArrayList<>();
        for(ClassLunwen aa:classLunwenList){
            String [] taecherarr=aa.getTeacherNames().split(",");
            String [] taecherarrId=aa.getTeacherIds().split(",");
            for(int a=0;a<taecherarr.length;a++){
                if(Integer.parseInt(taecherarrId[a])==teacherId){
                    ClassLunwen classLunwen=aa.clone();
                    classLunwen.setTeacherIds(taecherarrId[a]);
                    classLunwen.setTeacherNames(taecherarr[a]);
                    classLunwens.add(classLunwen);
                }
            }
        }
        return classLunwens;
    }
}
