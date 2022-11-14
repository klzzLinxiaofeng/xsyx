package com.xunyunedu.culturalideal.service.impl;

import com.xunyunedu.culturalideal.dao.IdealBehaviorDao;
import com.xunyunedu.culturalideal.service.IdealBehaviorService;
import com.xunyunedu.culturalideal.vo.IdealBehavior;
import com.xunyunedu.culturalideal.vo.IdealIndicators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class IdealBehaviorServiceImpl implements IdealBehaviorService {
    @Autowired
    private IdealBehaviorDao idealBehaviorDao;
    @Override
    public List<IdealIndicators> findByaLL(String schoolYear, String schoolTrem) {
        return idealBehaviorDao.findByaLL(schoolYear,schoolTrem);
    }

    @Override
    public Integer create(IdealBehavior idealBehavior) {
        return idealBehaviorDao.create(idealBehavior);
    }

    @Override
    public Integer findByFenShu(Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem) {
        Integer num=idealBehaviorDao.findByFenShu(studentId,zhibiaoId,schoolYear,schoolTrem);
        IdealIndicators idealIndicators=idealBehaviorDao.findById(zhibiaoId);
        if(num!=null){
            return   idealIndicators.getScore()+num;
        }else{
            return   idealIndicators.getScore();
        }
    }

}
