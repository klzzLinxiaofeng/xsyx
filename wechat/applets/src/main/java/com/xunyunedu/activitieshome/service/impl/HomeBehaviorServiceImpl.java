package com.xunyunedu.activitieshome.service.impl;

import com.xunyunedu.activitieshome.dao.HomeBehaviorDao;
import com.xunyunedu.activitieshome.service.HomeBehaviorService;
import com.xunyunedu.activitieshome.vo.HomeBehavior;
import com.xunyunedu.activitieshome.vo.HomeIndicators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class HomeBehaviorServiceImpl implements HomeBehaviorService {
    @Autowired
    private HomeBehaviorDao homeBehaviorDao;
    @Override
    public List<HomeIndicators> findByaLL(String schoolYear, String schoolTrem) {
        return homeBehaviorDao.findByaLL(schoolYear,schoolTrem);
    }

    @Override
    public Integer create(HomeBehavior homeBehavior) {
        return homeBehaviorDao.create(homeBehavior);
    }

    @Override
    public Integer findByFenShu(Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem) {
        Integer num=homeBehaviorDao.findByFenShu(studentId,zhibiaoId,schoolYear,schoolTrem);
        HomeIndicators homeIndicators=homeBehaviorDao.findById(zhibiaoId);
        if(num!=null){
            return   homeIndicators.getScore()+num;
        }else{
            return   homeIndicators.getScore();
        }
    }

}
