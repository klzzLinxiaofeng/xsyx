package com.xunyunedu.personinfor.dao;

import com.xunyunedu.personinfor.pojo.KeXuanKe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeXuanKeDao {
        List<KeXuanKe> findByGradeIdKeXuanKes(@Param("gradeId") Integer gradeId);
}
