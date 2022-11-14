package platform.szxyzxx.pingyumoban.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.szxyzxx.pingyumoban.dao.PingYuTypeDao;
import platform.szxyzxx.pingyumoban.service.PingYuTypeService;
import platform.szxyzxx.pingyumoban.vo.PingYuType;

import java.util.List;

@Service
public class PingYuTypeServiceImpl implements PingYuTypeService {
    @Autowired
   private PingYuTypeDao pingYuTypeDao;

    @Override
    public List<PingYuType> findByAll(String name, Page page) {
        return pingYuTypeDao.findByAll( name,  page);
    }

    @Override
    public PingYuType findById(Integer id) {
        return pingYuTypeDao.findById(id);
    }

    @Override
    public Integer create(PingYuType pingYuType) {
          return pingYuTypeDao.create(pingYuType);
    }

    @Override
    public Integer update(PingYuType pingYuType) {
        return pingYuTypeDao.update(pingYuType.getId(),pingYuType.getName());
    }

    @Override
    public Integer updateDelete(Integer id) {
        return pingYuTypeDao.updateShanChu(id);
    }
}
