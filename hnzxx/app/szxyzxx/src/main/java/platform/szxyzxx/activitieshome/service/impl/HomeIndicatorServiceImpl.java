package platform.szxyzxx.activitieshome.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.activitieshome.dao.HomeIndicatorsDao;
import platform.szxyzxx.activitieshome.service.HomeIndicatorService;
import platform.szxyzxx.activitieshome.vo.HomeIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
@Service
public class HomeIndicatorServiceImpl implements HomeIndicatorService {
    @Autowired
    private HomeIndicatorsDao homeIndicatorsDao;

    @Override
    public Integer create(HomeIndicators homeIndicators) {
        return homeIndicatorsDao.create(homeIndicators);
    }

    @Override
    public List<HomeIndicators> findByAll(UserInfo userInfo,Page page) {
        return homeIndicatorsDao.findByAll(userInfo,page);
    }

    @Override
    public HomeIndicators findById(Integer id) {
        return homeIndicatorsDao.findById(id);
    }

    @Override
    public Integer update(HomeIndicators homeIndicators) {
        return homeIndicatorsDao.update(homeIndicators);
    }

    @Override
    public String updateDelete(String ids) {

        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                homeIndicatorsDao.updateDelete(Integer.parseInt(split[i]));
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }
}
