package platform.szxyzxx.lifebehavior.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.lifebehavior.vo.LifeIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 12:43
 * @Version 1.0
 */
public interface LifeIndicatorsDao {
    Integer create(LifeIndicators lifeIndicators);
    List<LifeIndicators> findByAll(UserInfo userInfo,Page page);
    LifeIndicators findById(Integer id);
    Integer update(LifeIndicators lifeIndicators);
    Integer updateDelete(Integer id);
}
