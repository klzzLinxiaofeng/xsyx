package platform.szxyzxx.laborquality.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.laborquality.dao.LaborIndicatorsDao;
import platform.szxyzxx.laborquality.service.LaborIndicatorService;
import platform.szxyzxx.laborquality.vo.LaborIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
@Service
public class LaborIndicatorServiceImpl implements LaborIndicatorService {
    @Autowired
    private LaborIndicatorsDao idealIndicatorsDao;

    @Override
    public Integer create(LaborIndicators idealIndicators) {
        return idealIndicatorsDao.create(idealIndicators);
    }

    @Override
    public List<LaborIndicators> findByAll(UserInfo userInfo, Page page) {
        return idealIndicatorsDao.findByAll(userInfo,page);
    }

    @Override
    public LaborIndicators findById(Integer id) {
        return idealIndicatorsDao.findById(id);
    }

    @Override
    public Integer update(LaborIndicators idealIndicators) {
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
