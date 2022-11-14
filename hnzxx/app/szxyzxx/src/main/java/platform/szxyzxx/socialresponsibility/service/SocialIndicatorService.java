package platform.szxyzxx.socialresponsibility.service;

import framework.generic.dao.Page;
import platform.szxyzxx.socialresponsibility.vo.SocialIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
public interface SocialIndicatorService {
    Integer create(SocialIndicators socialIndicators);
    List<SocialIndicators> findByAll(UserInfo userInfo, Page page);
    SocialIndicators findById(Integer id);
    Integer update(SocialIndicators socialIndicators);
    String updateDelete(String ids);
}
