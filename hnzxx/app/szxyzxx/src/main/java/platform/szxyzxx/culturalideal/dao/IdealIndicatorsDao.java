package platform.szxyzxx.culturalideal.dao;

import framework.generic.dao.Page;

import platform.szxyzxx.culturalideal.vo.IdealIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 12:43
 * @Version 1.0
 */
public interface IdealIndicatorsDao {
    Integer create(IdealIndicators idealIndicators);
    List<IdealIndicators> findByAll(UserInfo userInfo,Page page);
    IdealIndicators findById(Integer id);
    Integer update(IdealIndicators idealIndicators);
    Integer updateDelete(Integer id);
}
