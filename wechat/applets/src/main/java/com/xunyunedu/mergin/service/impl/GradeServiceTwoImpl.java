package com.xunyunedu.mergin.service.impl;

import com.xunyunedu.mergin.dao.GradeTwoDao;
import com.xunyunedu.mergin.service.GradeTwoService;
import com.xunyunedu.mergin.vo.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class GradeServiceTwoImpl implements GradeTwoService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private GradeTwoDao gradeDao;

    @Override
    public Grade findGradeById(Integer id) {
        try {
            return gradeDao.findById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.info("数据库无存在ID为 {} ", id);
        }
        return null;
    }
}
