package com.xunyunedu.schoolbus.service.impl;

import com.xunyunedu.schoolbus.dao.BusParentPickDao;
import com.xunyunedu.schoolbus.pojo.BusParentPick;
import com.xunyunedu.schoolbus.service.BusParentPickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusParentPickServiceImpl implements BusParentPickService {
    @Autowired
    private BusParentPickDao dao;

    @Override
    public int add(BusParentPick record) {
       return dao.insert(record);
    }
}
