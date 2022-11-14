package platform.education.sysbanner.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.sysbanner.model.SysBanner;
import platform.education.sysbanner.vo.SysBannerCondition;

import java.util.List;

public interface SysBannerDao extends GenericDao<SysBanner, Integer> {
    List<SysBanner> findSysBannerByCondition(SysBannerCondition var1, Page var2, Order var3);

    SysBanner findById(Integer var1);

    Long count(SysBannerCondition var1);
}
