package com.xunyunedu.zhishidian.service;

import com.xunyunedu.zhishidian.vo.KnowEvaluation;
import com.xunyunedu.zhishidian.vo.KnowLedge;

import java.util.List;

public interface KnowLedgeService {
    List<KnowLedge> findByAll( Integer gradeId,Integer subject);


    List<KnowEvaluation> findByPinjai(Integer knowIdInteger,Integer studentId);
}
