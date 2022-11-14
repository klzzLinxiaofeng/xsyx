package platform.szxyzxx.screening.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.screening.dao.ScreeningDao;
import platform.szxyzxx.screening.service.ScreeningService;
import platform.szxyzxx.screening.vo.Screening;

import java.util.List;

@Service
public class ScreeningServiceImpl implements ScreeningService {
    @Autowired
    private ScreeningDao screeningDao;
    @Override
    public List<Screening> findByAll(String paichaName, String screeningArea, String startTime, String endTime, Page page) {
        return screeningDao.findByAll(paichaName,screeningArea,startTime,endTime,page);
    }

    @Override
    public List<Screening> findByDaoAll(String paichaName, String screeningArea, String startTime, String endTime) {
        return screeningDao.findByDaoAll(paichaName,screeningArea,startTime,endTime);
    }

    @Override
    public Integer create(Screening screening) {
        return screeningDao.create(screening);
    }

    @Override
    public Integer update(Screening screening) {
        return screeningDao.update(screening);
    }

    @Override
    public Screening findById(Integer id) {
        return screeningDao.findById(id);
    }
}
