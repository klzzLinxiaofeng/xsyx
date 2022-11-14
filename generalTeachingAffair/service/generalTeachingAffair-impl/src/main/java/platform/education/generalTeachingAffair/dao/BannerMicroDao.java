package platform.education.generalTeachingAffair.dao;

import framework.generic.dao.GenericDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.vo.MicroBanner;
import platform.education.generalTeachingAffair.vo.MicroBannerCondition;

import java.util.List;

public interface BannerMicroDao extends GenericDao<MicroBanner, Integer> {

	List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition, Page page, Order order);
	
	MicroBanner findById(Integer id);
	
	Long count(MicroBannerCondition microBannerCondition);
	
}
