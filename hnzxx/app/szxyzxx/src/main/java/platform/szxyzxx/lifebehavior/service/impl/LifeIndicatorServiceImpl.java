package platform.szxyzxx.lifebehavior.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.lifebehavior.dao.LifeIndicatorsDao;
import platform.szxyzxx.lifebehavior.service.LifeIndicatorService;
import platform.szxyzxx.lifebehavior.vo.LifeIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
@Service
public class LifeIndicatorServiceImpl implements LifeIndicatorService {
    @Autowired
    private LifeIndicatorsDao lifeIndicatorsDao;

    @Override
    public Integer create(LifeIndicators lifeIndicators) {
        return lifeIndicatorsDao.create(lifeIndicators);
    }

    @Override
    public List<LifeIndicators> findByAll(UserInfo userInfo,Page page) {
        return lifeIndicatorsDao.findByAll(userInfo,page);
    }

    @Override
    public LifeIndicators findById(Integer id) {
        return lifeIndicatorsDao.findById(id);
    }

    @Override
    public Integer update(LifeIndicators lifeIndicators) {
        return lifeIndicatorsDao.update(lifeIndicators);
    }

    @Override
    public String updateDelete(String ids) {

        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                lifeIndicatorsDao.updateDelete(Integer.parseInt(split[i]));
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }
}
