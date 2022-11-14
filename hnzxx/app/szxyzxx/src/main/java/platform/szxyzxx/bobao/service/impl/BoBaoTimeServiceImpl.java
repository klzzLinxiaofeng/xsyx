package platform.szxyzxx.bobao.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.bobao.dao.BoBaoTimeDao;
import platform.szxyzxx.bobao.service.BoBaoTimeService;
import platform.szxyzxx.bobao.vo.BoBaoTime;

@Service
public class BoBaoTimeServiceImpl implements BoBaoTimeService {
    @Autowired
    private BoBaoTimeDao boBaoTimeDao;

    @Override
    public Integer createBoBaoTime(BoBaoTime boBaoTime) {
        return boBaoTimeDao.createBoBaoTime(boBaoTime);
    }

    @Override
    public Integer deleteBoBaoTime(Integer id) {
        return boBaoTimeDao.deleteBoBaoTime(id);
    }

    @Override
    public Integer updateBoBaoTime(BoBaoTime boBaoTime) {
        return boBaoTimeDao.updateBoBaoTime(boBaoTime);
    }
}
