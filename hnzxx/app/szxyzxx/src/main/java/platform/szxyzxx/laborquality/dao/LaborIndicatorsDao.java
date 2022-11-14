package platform.szxyzxx.laborquality.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.laborquality.vo.LaborIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 12:43
 * @Version 1.0
 */
public interface LaborIndicatorsDao {
    Integer create(LaborIndicators laborIndicators);
    List<LaborIndicators> findByAll(UserInfo userInfo, Page page);
    LaborIndicators findById(Integer id);
    Integer update(LaborIndicators laborIndicators);
    Integer updateDelete(Integer id);
}
