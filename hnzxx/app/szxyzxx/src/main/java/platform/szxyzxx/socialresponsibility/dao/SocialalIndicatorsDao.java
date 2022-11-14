package platform.szxyzxx.socialresponsibility.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.socialresponsibility.vo.SocialIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 12:43
 * @Version 1.0
 */
public interface SocialalIndicatorsDao {
    Integer create(SocialIndicators socialIndicators);
    List<SocialIndicators> findByAll(UserInfo userInfo, Page page);
    SocialIndicators findById(Integer id);
    Integer update(SocialIndicators socialIndicators);
    Integer updateDelete(Integer id);
}
