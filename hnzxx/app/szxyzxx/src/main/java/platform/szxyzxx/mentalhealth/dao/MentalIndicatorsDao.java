package platform.szxyzxx.mentalhealth.dao;

import framework.generic.dao.Page;
import platform.szxyzxx.mentalhealth.vo.MentalIndicators;
import platform.szxyzxx.web.common.vo.UserInfo;

import java.util.List;

/**
 * @Author zhangyong
 * @Date 2022/11/4 12:43
 * @Version 1.0
 */
public interface MentalIndicatorsDao {
    Integer create(MentalIndicators mentalIndicators);
    List<MentalIndicators> findByAll(UserInfo userInfo, Page page);
    MentalIndicators findById(Integer id);
    Integer update(MentalIndicators mentalIndicators);
    Integer updateDelete(Integer id);
}
