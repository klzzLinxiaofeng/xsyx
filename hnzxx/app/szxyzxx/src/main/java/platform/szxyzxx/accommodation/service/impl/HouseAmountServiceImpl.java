package platform.szxyzxx.accommodation.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.accommodation.dao.HouseAmountDao;
import platform.szxyzxx.accommodation.pojo.HouseAmount;
import platform.szxyzxx.accommodation.service.HouseAmountService;

import java.util.List;

@Service
public class HouseAmountServiceImpl implements HouseAmountService {
    @Autowired
    private HouseAmountDao houseAmountDao;
    @Override
    public List<HouseAmount> findByAll() {
        return houseAmountDao.findByAll();
    }

    @Override
    public Integer create(HouseAmount houseAmount) {
        return houseAmountDao.create(houseAmount);
    }

    @Override
    public Integer update(HouseAmount houseAmount) {
        return houseAmountDao.update(houseAmount);
    }
}
