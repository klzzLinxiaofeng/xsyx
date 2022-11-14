package platform.szxyzxx.activitieshome.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.activitieshome.vo.HomeIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;


import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 12:43
 * @Version 1.0
 */
public interface HomeIndicatorsDao {
    Integer create(HomeIndicators homeIndicators);
    List<HomeIndicators> findByAll(UserInfo userInfo,Page page);
    HomeIndicators findById(Integer id);
    Integer update(HomeIndicators homeIndicators);
    Integer updateDelete(Integer id);
}
