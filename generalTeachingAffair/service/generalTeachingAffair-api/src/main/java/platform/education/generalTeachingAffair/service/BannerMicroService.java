package platform.education.generalTeachingAffair.service;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.MicroBanner;
import platform.education.generalTeachingAffair.vo.MicroBannerCondition;

import java.util.List;

public interface BannerMicroService {
    MicroBanner findMicroBannerById(Integer id);

    MicroBanner add(MicroBanner microBanner);

    MicroBanner modify(MicroBanner microBanner);

    void remove(MicroBanner microBanner);

    List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition, Page page, Order order);

//    List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition);
//
//    List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition, Page page);
//
//    List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition, Order order);

    Long count();

    Long count(MicroBannerCondition microBannerCondition);

}
