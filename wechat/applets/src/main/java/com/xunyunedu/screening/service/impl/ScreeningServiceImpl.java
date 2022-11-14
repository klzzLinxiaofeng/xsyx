package com.xunyunedu.screening.service.impl;

import com.xunyunedu.screening.dao.ScreeningDao;
import com.xunyunedu.screening.service.ScreeningService;
import com.xunyunedu.screening.vo.Screening;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    @Autowired
    private ScreeningDao screeningDao;
    @Override
    public List<Screening> findByAll(Integer userId) {
        return screeningDao.findByAll(userId);
    }


    @Override
    public Integer create(Screening screening) {
        return screeningDao.create(screening);
    }
    @Override
    public Screening findById(Integer id) {
        return screeningDao.findById(id);
    }
}
