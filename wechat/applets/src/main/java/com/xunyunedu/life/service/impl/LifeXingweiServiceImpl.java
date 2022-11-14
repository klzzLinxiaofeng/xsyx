package com.xunyunedu.life.service.impl;

import com.xunyunedu.life.dao.LifeXingweiDao;
import com.xunyunedu.life.service.LifeXingweiService;
import com.xunyunedu.life.vo.LifeBehavior;
import com.xunyunedu.life.vo.LifeIndicators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 9:36
 * @Version 1.0
 */
@Service
public class LifeXingweiServiceImpl implements LifeXingweiService {
    @Autowired
    private LifeXingweiDao lifeXingweiDao;
    @Override
    public List<LifeIndicators> findByaLL(String schoolYear, String schoolTrem) {
        return lifeXingweiDao.findByaLL(schoolYear,schoolTrem);
    }

    @Override
    public Integer create(LifeBehavior lifeBehavior) {
        return lifeXingweiDao.create(lifeBehavior);
    }

    @Override
    public Integer findByFenShu(Integer studentId, Integer zhibiaoId, String schoolYear, String schoolTrem) {
       Integer num=lifeXingweiDao.findByFenShu(studentId,zhibiaoId,schoolYear,schoolTrem);
       LifeIndicators lifeIndicators=lifeXingweiDao.findById(zhibiaoId);
        if(num!=null){
          return   lifeIndicators.getScore()+num;
        }else{
            return   lifeIndicators.getScore();
        }
    }
}
