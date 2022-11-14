package platform.szxyzxx.culturalideal.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.culturalideal.dao.IdealIndicatorsDao;
import platform.szxyzxx.culturalideal.service.IdealIndicatorService;
import platform.szxyzxx.culturalideal.vo.IdealIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
@Service
public class IdealIndicatorServiceImpl implements IdealIndicatorService {
    @Autowired
    private IdealIndicatorsDao idealIndicatorsDao;

    @Override
    public Integer create(IdealIndicators idealIndicators) {
        return idealIndicatorsDao.create(idealIndicators);
    }

    @Override
    public List<IdealIndicators> findByAll(UserInfo userInfo,Page page) {
        return idealIndicatorsDao.findByAll(userInfo,page);
    }

    @Override
    public IdealIndicators findById(Integer id) {
        return idealIndicatorsDao.findById(id);
    }

    @Override
    public Integer update(IdealIndicators idealIndicators) {
        return idealIndicatorsDao.update(idealIndicators);
    }

    @Override
    public String updateDelete(String ids) {

        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                idealIndicatorsDao.updateDelete(Integer.parseInt(split[i]));
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }
}
