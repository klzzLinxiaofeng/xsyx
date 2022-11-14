package platform.szxyzxx.culturalideal.service;

import framework.generic.dao.Page;
import platform.szxyzxx.culturalideal.vo.IdealIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
public interface IdealIndicatorService {
    Integer create(IdealIndicators idealIndicators);
    List<IdealIndicators> findByAll(UserInfo userInfo,Page page);
    IdealIndicators findById(Integer id);
    Integer update(IdealIndicators idealIndicators);
    String updateDelete(String ids);
}
