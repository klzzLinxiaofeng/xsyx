package com.xunyunedu.socialresponsibility.service.impl;

import com.xunyunedu.socialresponsibility.dao.SocialalBehaviorDao;
import com.xunyunedu.socialresponsibility.service.SocialBehaviorService;
import com.xunyunedu.socialresponsibility.vo.SocialBehavior;
import com.xunyunedu.socialresponsibility.vo.SocialIndicators;
import com.xunyunedu.student.pojo.StudentPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class SocialBehaviorServiceImpl implements  SocialBehaviorService {
    @Autowired
    private SocialalBehaviorDao socialalBehaviorDao;
    @Override
    public List<SocialIndicators> findByaLL(String schoolYear, String schoolTrem) {
        return socialalBehaviorDao.findByaLL(schoolYear,schoolTrem);
    }

    @Override
    public Integer create(SocialBehavior socialBehavior) {
        return socialalBehaviorDao.create(socialBehavior);
    }

    @Override
    public Integer findByFenShu(Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem) {
        Integer num=socialalBehaviorDao.findByFenShu(studentId,zhibiaoId,schoolYear,schoolTrem);
        SocialIndicators socialIndicators=socialalBehaviorDao.findById(zhibiaoId);
        if(num!=null){
            return   socialIndicators.getScore()+num;
        }else{
            return   socialIndicators.getScore();
        }
    }

    @Override
    public List<StudentPojo> findById(String studentName, Integer teamId,Integer studentId) {
        return socialalBehaviorDao.findByteamId( studentName,  teamId,studentId);
    }
}
