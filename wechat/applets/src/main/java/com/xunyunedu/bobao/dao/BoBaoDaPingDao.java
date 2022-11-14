package com.xunyunedu.bobao.dao;

import com.xunyunedu.bobao.vo.BoBaoDaPing;

public interface BoBaoDaPingDao {
    BoBaoDaPing findById(Integer id);
    BoBaoDaPing findByGradeId(int [] gradeIds);
}
