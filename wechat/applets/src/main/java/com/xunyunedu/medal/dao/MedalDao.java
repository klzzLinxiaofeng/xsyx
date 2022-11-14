package com.xunyunedu.medal.dao;

import com.xunyunedu.medal.model.Medal;

import com.xunyunedu.medal.model.MvAwardsMedal;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @program: education
 * @description: 奖章dao
 * @author: cmb
 * @create: 2020-10-13 16:19
 **/
public interface MedalDao {
    void crateMedal(Medal medal);
    List<Medal> findMedalAll();
    void deleteMedal(@Param("id") Integer id);
    void updateMedal(Medal medal);
    Medal findMeDalById(@Param("id") Integer id);

    void crateMedalStudent(List<MvAwardsMedal> awardsMedals);
    List<MvAwardsMedal> findMvAwardsMedalByStudent(@Param("studentId")Integer studentId );
    MvAwardsMedal findMvAwardsMedalByStudentAndMeDalById(Map map);

}
