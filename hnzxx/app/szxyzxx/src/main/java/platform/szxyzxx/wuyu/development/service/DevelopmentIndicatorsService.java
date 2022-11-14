package platform.szxyzxx.wuyu.development.service;

import framework.generic.dao.Page;
import platform.szxyzxx.wuyu.development.vo.DevelopmentIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 11:13
 * @Version 1.0
 */
public interface DevelopmentIndicatorsService {
    List<DevelopmentIndicators> findByAll(String code, String schoolYear, String schoolTrem, Page page);
    DevelopmentIndicators findById(Integer id);
    Integer update(DevelopmentIndicators developmentIndicators);
    Integer create(DevelopmentIndicators developmentIndicators);
    String updateDelete(String ids);
}
