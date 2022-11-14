package platform.szxyzxx.laborquality.service;

import framework.generic.dao.Page;
import platform.szxyzxx.laborquality.vo.LaborIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/3 13:13
 * @Version 1.0
 */
public interface LaborIndicatorService {
    Integer create(LaborIndicators laborIndicators);
    List<LaborIndicators> findByAll(UserInfo userInfo, Page page);
    LaborIndicators findById(Integer id);
    Integer update(LaborIndicators laborIndicators);
    String updateDelete(String ids);
}
