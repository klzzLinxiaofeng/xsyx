package platform.szxyzxx.wuyu.development.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.wuyu.development.dao.DevelopmentIndicatorsDao;
import platform.szxyzxx.wuyu.development.service.DevelopmentIndicatorsService;
import platform.szxyzxx.wuyu.development.vo.DevelopmentIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 11:14
 * @Version 1.0
 */
@Service
public class DevelopmentIndicatorsServiceImpl implements DevelopmentIndicatorsService {
    @Autowired
    private DevelopmentIndicatorsDao developmentIndicatorsDao;
    @Override
    public List<DevelopmentIndicators> findByAll(String code, String schoolYear, String schoolTrem, Page page) {
        return developmentIndicatorsDao.findByAll(code,schoolYear,schoolTrem,page);
    }

    @Override
    public DevelopmentIndicators findById(Integer id) {
        return developmentIndicatorsDao.findById(id);
    }

    @Override
    public Integer update(DevelopmentIndicators developmentIndicators) {
        return developmentIndicatorsDao.update(developmentIndicators);
    }

    @Override
    public Integer create(DevelopmentIndicators developmentIndicators) {
        return developmentIndicatorsDao.create(developmentIndicators);
    }
    @Override
    public String updateDelete(String ids) {

        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                developmentIndicatorsDao.updateDelete(Integer.parseInt(split[i]));
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }
}
