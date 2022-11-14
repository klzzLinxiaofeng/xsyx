package com.xunyunedu.mentalhealth.service.impl;


import com.xunyunedu.mentalhealth.dao.MentalBehaviorDao;
import com.xunyunedu.mentalhealth.service.MentalBehaviorService;
import com.xunyunedu.mentalhealth.vo.MentalBehavior;
import com.xunyunedu.mentalhealth.vo.MentalIndicators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Author zhangyong
 * @Date 2022/11/3 14:03
 * @Version 1.0
 */
@Service
public class MentalBehaviorServiceImpl implements MentalBehaviorService {
    @Autowired
    private MentalBehaviorDao mentalBehaviorDao;
    @Override
    public List<MentalIndicators> findByaLL(String schoolYear, String schoolTrem) {
        return mentalBehaviorDao.findByaLL(schoolYear,schoolTrem);
    }

    @Override
    public Integer create(MentalBehavior mentalBehavior) {
        return mentalBehaviorDao.create(mentalBehavior);
    }

    @Override
    public Integer findByFenShu(Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem) {
        Integer num=mentalBehaviorDao.findByFenShu(studentId,zhibiaoId,schoolYear,schoolTrem);
        MentalIndicators laborIndicators=mentalBehaviorDao.findById(zhibiaoId);
        if(num!=null){
            return   laborIndicators.getScore()+num;
        }else{
            return   laborIndicators.getScore();
        }
    }
}
