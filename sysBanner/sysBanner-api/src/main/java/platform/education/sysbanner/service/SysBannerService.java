package platform.education.sysbanner.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;

import platform.education.sysbanner.model.SysBanner;
import platform.education.sysbanner.vo.SysBannerCondition;

import java.util.List;

public interface SysBannerService {
    /**
    * @Description: 通过id查找相关数据
    * @Param: * @param var1
    * @return: platform.education.sysbanner.model.SysBanner
    * @Author: cmb
    * @Date: 2020/11/10
    */

    SysBanner findSysBannerById(Integer var1);
/**
* @Description: 添加数据
* @Param: * @param var1
* @return: platform.education.sysbanner.model.SysBanner
* @Author: cmb
* @Date: 2020/11/10
*/

    SysBanner add(SysBanner var1);
/**
* @Description: 修改对象
* @Param: * @param var1
* @return: platform.education.sysbanner.model.SysBanner
* @Author: cmb
* @Date: 2020/11/10
*/

    SysBanner modify(SysBanner var1);
/**
* @Description: 删除对象
* @Param: * @param var1
* @return: void
* @Author: cmb
* @Date: 2020/11/10
*/

    void remove(SysBanner var1);
//通过对象查找数据
    List<SysBanner> findSysBannerCondition(SysBannerCondition var1, Page var2, Order var3);

    List<SysBanner> findSysBannerCondition(SysBannerCondition var1);

    List<SysBanner> findSysBannerCondition(SysBannerCondition var1, Page var2);

    List<SysBanner> findSysBannerCondition(SysBannerCondition var1, Order var2);

    Long count();
/**
* @Description: 获取记录总数
* @Param: * @param var1
* @return: java.lang.Long
* @Author: cmb
* @Date: 2020/11/10
*/

    Long count(SysBannerCondition var1);
}
