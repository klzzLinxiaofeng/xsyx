package com.xunyunedu.character.service;

import com.xunyunedu.character.pojo.Evaluation;

import java.util.List;

public interface EvaluationService{
    List<Evaluation> findByAll();
    Integer defenqingkuang(Integer studentId,Integer evalenId);
}
