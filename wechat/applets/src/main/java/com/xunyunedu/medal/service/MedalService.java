package com.xunyunedu.medal.service;

import com.xunyunedu.medal.model.Medal;
import com.xunyunedu.medal.model.MvAwardsMedal;
import com.xunyunedu.medal.model.PjSchoolTerm;

import java.util.List;
import java.util.Map;


/**
 * @description: 业务接口类
 * @author: cmb
 * @create: 2020-10-13 16:52
 **/
public interface MedalService {
//
    void crateMedal(Medal medal );

    List<Medal> findMedalAll();
    void deleteMedal(Integer id);
    void updateMedal(Medal medal);
    Medal findMeDalById(Integer id);
     void crateMedalStudent(List<MvAwardsMedal> awardsMedals);
    List<MvAwardsMedal> findMvAwardsMedalByStudent(Integer studentId);
    String getYearTerm(Integer schoolId,String year);
    MvAwardsMedal findMvAwardsMedalByStudentAndMeDalById(Integer id,Integer studentId);


}
