package platform.szxyzxx.activitieshome.service;

import framework.generic.dao.Page;
import platform.szxyzxx.activitieshome.vo.HomeIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
public interface HomeIndicatorService {
    Integer create(HomeIndicators homeIndicators);
    List<HomeIndicators> findByAll(UserInfo userInfo,Page page);
    HomeIndicators findById(Integer id);
    Integer update(HomeIndicators homeIndicators);
    String updateDelete(String ids);
}
