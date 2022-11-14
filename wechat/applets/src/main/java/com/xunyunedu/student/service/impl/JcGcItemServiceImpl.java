package com.xunyunedu.student.service.impl;

import com.xunyunedu.student.dao.JcGcItemDao;
import com.xunyunedu.student.pojo.JcGcItemPojo;
import com.xunyunedu.student.service.JcGcItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JcGcItemServiceImpl implements JcGcItemService {

    @Autowired
    private JcGcItemDao dao;

    @Override
    public List<JcGcItemPojo> queryAllHealthType() {
        return dao.getAllHealthType();
    }
}
