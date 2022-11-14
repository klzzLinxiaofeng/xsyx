package platform.szxyzxx.wuyu.development.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.wuyu.development.vo.DevelopmentIndicators;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/11 10:09
 * @Version 1.0
 */
public interface DevelopmentIndicatorsDao {
    List<DevelopmentIndicators> findByAll(String code,String schoolYear,String schoolTrem, Page page);
    DevelopmentIndicators findById(Integer id);
    Integer update(DevelopmentIndicators developmentIndicators);
    Integer create(DevelopmentIndicators developmentIndicators);
    Integer updateDelete(Integer id);
}
