package com.xunyunedu.zhishidian.service.impl;


import com.xunyunedu.zhishidian.dao.KnowLedgeDao;
import com.xunyunedu.zhishidian.service.KnowLedgeService;
import com.xunyunedu.zhishidian.vo.KnowEvaluation;
import com.xunyunedu.zhishidian.vo.KnowLedge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KnowLedgeServiceImpl implements KnowLedgeService {
    @Autowired
    private KnowLedgeDao knowLedgeDao;

    @Override
    public List<KnowLedge> findByAll(Integer gradeId, Integer subject) {
        return knowLedgeDao.findByAll(gradeId,subject);
    }

    @Override
    public List<KnowEvaluation> findByPinjai(Integer knowId, Integer studentId) {
        List<KnowEvaluation> knowEvaluationList=knowLedgeDao.findByPinjai(knowId,0,null);
        for(KnowEvaluation aa:knowEvaluationList){
            List<KnowEvaluation> stusdentcd=knowLedgeDao.findByPinjai(knowId,1,aa.getKnowMenuId());
            for(KnowEvaluation bb:stusdentcd){
                KnowEvaluation knowEvaluation=knowLedgeDao.findById(bb.getKnowMenuId(),studentId);
                if(knowEvaluation!=null){
                    bb.setPingfen(knowEvaluation.getPingfen());
                    bb.setPingyu(knowEvaluation.getPingyu());
                    bb.setId(knowEvaluation.getId());
                }
            }
            aa.setKnowEvaluationList(stusdentcd);
        }
        return knowEvaluationList;
    }
}
