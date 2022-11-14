package com.xunyunedu.personinfor.service.impl;

import com.xunyunedu.personinfor.dao.KeXuanKeDao;
import com.xunyunedu.personinfor.pojo.KeXuanKe;
import com.xunyunedu.personinfor.service.KeXuanKeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeXuanKeServiceImpl implements KeXuanKeService {
    @Autowired
    private KeXuanKeDao keXuanKeDao;
    @Override
    public List<KeXuanKe> findByGradeIdKeXuanKes(Integer gradeId) {
        return keXuanKeDao.findByGradeIdKeXuanKes(gradeId);
    }

}
