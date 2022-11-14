package com.xunyunedu.personinfor.service;

import com.xunyunedu.personinfor.pojo.KeXuanKe;

import java.util.List;

public interface KeXuanKeService {
    List<KeXuanKe> findByGradeIdKeXuanKes(Integer gradeId);
}
