package platform.szxyzxx.mentalhealth.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.mentalhealth.dao.MentalIndicatorsDao;
import platform.szxyzxx.mentalhealth.service.MentalIndicatorService;
import platform.szxyzxx.mentalhealth.vo.MentalIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
@Service
public class MentalIndicatorServiceImpl implements MentalIndicatorService {
    @Autowired
    private MentalIndicatorsDao mentalIndicatorsDao;

    @Override
    public Integer create(MentalIndicators mentalIndicators) {
        return mentalIndicatorsDao.create(mentalIndicators);
    }

    @Override
    public List<MentalIndicators> findByAll(UserInfo userInfo, Page page) {
        return mentalIndicatorsDao.findByAll(userInfo,page);
    }

    @Override
    public MentalIndicators findById(Integer id) {
        return mentalIndicatorsDao.findById(id);
    }

    @Override
    public Integer update(MentalIndicators mentalIndicators) {
        return mentalIndicatorsDao.update(mentalIndicators);
    }

    @Override
    public String updateDelete(String ids) {

        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                mentalIndicatorsDao.updateDelete(Integer.parseInt(split[i]));
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }
}
