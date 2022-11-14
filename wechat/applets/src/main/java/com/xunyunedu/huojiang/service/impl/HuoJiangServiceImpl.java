package com.xunyunedu.huojiang.service.impl;

import com.xunyunedu.common.dao.UploaderDao;
import com.xunyunedu.common.pojo.EntityFile;
import com.xunyunedu.huojiang.dao.HuoJiangDao;
import com.xunyunedu.huojiang.service.HuoJiangService;
import com.xunyunedu.huojiang.vo.ClassLunwen;
import com.xunyunedu.huojiang.vo.HuoJiang;
import com.xunyunedu.teacher.pojo.TeacherPojo;
import com.xunyunedu.util.ftp.FtpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.vo.TeacherVo;


import java.util.*;

@Service
public class HuoJiangServiceImpl implements HuoJiangService {
    @Autowired
    private HuoJiangDao huoJiangDao;
    @Autowired
    private UploaderDao uploaderDao;

    @Autowired
    private FtpUtils ftpUtils;
    @Override
    public List<HuoJiang> findByAll(Integer teacherId,String type) {
        List<HuoJiang> huoJiangList=huoJiangDao.findByAll(teacherId,type);
        List<HuoJiang> huoJiangs=new ArrayList<>();
        for(HuoJiang aa:huoJiangList){
            String [] taecherarr=aa.getTeacherNames().split(",");
            String [] taecherarrId=aa.getTeacherIds().split(",");
            for(int i=0;i<taecherarrId.length;i++){
                if(Integer.parseInt(taecherarrId[i])==teacherId){
                    HuoJiang huoJiang=aa.clone();
                    huoJiang.setTeacherIds(taecherarrId[i]);
                    huoJiang.setTeacherNames(taecherarr[i]);
                    huoJiang.setWinningTime(huoJiang.getWinningTime().substring(0,10));
                    huoJiangs.add(huoJiang);
                }
            }
        }
        return huoJiangs;
    }

    @Override
    public HuoJiang findById(Integer teacherId, Integer id) {
        HuoJiang huoJiangList=huoJiangDao.findById(id);
        HuoJiang huoJiang =new HuoJiang();
        String [] taecherarr=huoJiangList.getTeacherNames().split(",");
        String [] taecherarrId=huoJiangList.getTeacherIds().split(",");
        for(int i=0;i<taecherarrId.length;i++){
            if(Integer.parseInt(taecherarrId[i])==teacherId){
                huoJiang=huoJiangList.clone();
                huoJiang.setTeacherIds(taecherarrId[i]);
                huoJiang.setTeacherNames(taecherarr[i]);
                huoJiang.setWinningTime(huoJiang.getWinningTime().substring(0,10));
            }
        }
        if(huoJiang.getPictureId()!=null){
            List<Map<String,Object>> listw=new ArrayList<>();

            String [] pictureIds=huoJiang.getPictureId().split(",");
            for(int i=0;i<pictureIds.length;i++){
                Map<String,Object> map =new HashMap<>();
                EntityFile file = uploaderDao.findFileByUUID(pictureIds[i]);
                if (file != null) {
                    map.put("url", ftpUtils.relativePath2HttpUrl(file));
                    map.put("uuid",pictureIds[i]);
                }
                listw.add(map);
            }
            huoJiang.setPictureList(listw);
        }
        return huoJiang;
    }

    @Override
    public List<ClassLunwen> findByTongJi(String teacherName,Integer schoolId) {
        List<ClassLunwen> classLunwenList=new ArrayList<>();
        List<TeacherPojo> teachers=huoJiangDao.findTeacherByLikeNameAndSchool(teacherName,schoolId);
        for(TeacherPojo aa:teachers){
            ClassLunwen classLunwen=new ClassLunwen();
            ClassLunwen asd=huoJiangDao.findByTongJi(aa.getId());
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
}
