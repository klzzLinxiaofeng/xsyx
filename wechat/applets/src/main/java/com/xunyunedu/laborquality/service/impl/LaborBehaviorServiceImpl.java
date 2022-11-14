package com.xunyunedu.laborquality.service.impl;

import com.xunyunedu.laborquality.dao.LaborBehaviorDao;
import com.xunyunedu.laborquality.service.LaborBehaviorService;
import com.xunyunedu.laborquality.vo.LaborBehavior;
import com.xunyunedu.laborquality.vo.LaborIndicators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class LaborBehaviorServiceImpl implements LaborBehaviorService {
    @Autowired
    private LaborBehaviorDao laborBehaviorDao;
    @Override
    public List<LaborIndicators> findByaLL(String schoolYear, String schoolTrem) {
        return laborBehaviorDao.findByaLL(schoolYear,schoolTrem);
    }

    @Override
    public Integer create(LaborBehavior laborBehavior) {
        return laborBehaviorDao.create(laborBehavior);
    }

    @Override
    public Integer findByFenShu(Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem) {
        Integer num=laborBehaviorDao.findByFenShu(studentId,zhibiaoId,schoolYear,schoolTrem);
        LaborIndicators laborIndicators=laborBehaviorDao.findById(zhibiaoId);
        if(num!=null){
            return   laborIndicators.getScore()+num;
        }else{
            return   laborIndicators.getScore();
        }
    }
}
