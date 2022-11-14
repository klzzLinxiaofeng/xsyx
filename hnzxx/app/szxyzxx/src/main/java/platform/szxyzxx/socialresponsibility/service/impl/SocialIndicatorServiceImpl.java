package platform.szxyzxx.socialresponsibility.service.impl;

import framework.generic.dao.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.education.generalTeachingAffair.service.PublicClassService;
import platform.szxyzxx.socialresponsibility.dao.SocialalIndicatorsDao;
import platform.szxyzxx.socialresponsibility.service.SocialIndicatorService;
import platform.szxyzxx.socialresponsibility.vo.SocialIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
@Service
public class SocialIndicatorServiceImpl implements SocialIndicatorService {
    @Autowired
    private SocialalIndicatorsDao socialIndicatorsDao;

    @Override
    public Integer create(SocialIndicators socialIndicators) {
        return socialIndicatorsDao.create(socialIndicators);
    }

    @Override
    public List<SocialIndicators> findByAll(UserInfo userInfo, Page page) {
        return socialIndicatorsDao.findByAll(userInfo,page);
    }

    @Override
    public SocialIndicators findById(Integer id) {
        return socialIndicatorsDao.findById(id);
    }

    @Override
    public Integer update(SocialIndicators socialIndicators) {
        return socialIndicatorsDao.update(socialIndicators);
    }

    @Override
    public String updateDelete(String ids) {

        if (ids != null && !("").equals(ids)) {
            String[] split = ids.split(",");
            // 批量删除
            for (int i = 0; i < split.length; i++) {
                socialIndicatorsDao.updateDelete(Integer.parseInt(split[i]));
            }
        }
        return PublicClassService.OPERATE_SUCCESS;
    }
}
