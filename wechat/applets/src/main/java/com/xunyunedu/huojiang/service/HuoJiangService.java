package com.xunyunedu.huojiang.service;

import com.xunyunedu.huojiang.vo.ClassLunwen;
import com.xunyunedu.huojiang.vo.HuoJiang;

import java.util.List;

public interface HuoJiangService {
    List<HuoJiang> findByAll(Integer teacherId,String type);
    HuoJiang findById(Integer teacherId,Integer id);
    List<ClassLunwen> findByTongJi(String teacherName,Integer schoolId);
}
