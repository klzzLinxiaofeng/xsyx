package com.xunyunedu.character.service.impl;

import com.xunyunedu.character.dao.EvaluationDao;
import com.xunyunedu.character.pojo.Evaluation;
import com.xunyunedu.character.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluationServiceImpl implements EvaluationService {
    @Autowired
    private EvaluationDao evaluationDao;
    @Override
    public List<Evaluation> findByAll() {
        return evaluationDao.findByAll();
    }

    @Override
    public Integer defenqingkuang(Integer studentId, Integer evalenId) {
        if(evaluationDao.defenqingkuang(studentId,evalenId)==null){
            List<Evaluation> list=evaluationDao.findByAll();
            for(Evaluation aa:list){
                if(aa.getId().equals(evalenId)){
                    return aa.getInitScore();
                }
            }
        }
        return evaluationDao.defenqingkuang(studentId,evalenId);
    }
}
