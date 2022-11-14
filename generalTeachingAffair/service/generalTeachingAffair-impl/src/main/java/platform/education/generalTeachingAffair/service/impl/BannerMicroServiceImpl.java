package platform.education.generalTeachingAffair.service.impl;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import platform.education.generalTeachingAffair.dao.BannerMicroDao;
import platform.education.generalTeachingAffair.service.BannerMicroService;
import platform.education.generalTeachingAffair.vo.MicroBanner;
import platform.education.generalTeachingAffair.vo.MicroBannerCondition;

import java.util.Date;
import java.util.List;

/**
 *  @author: yhc
 *  @Date: 2021/1/18 16:04
 *  @Description:
 */
public class BannerMicroServiceImpl implements BannerMicroService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private BannerMicroDao bannerMicroDao;

	public void setBannerMicroDao(BannerMicroDao bannerMicroDao) {
		this.bannerMicroDao = bannerMicroDao;
	}

	@Override
	public MicroBanner findMicroBannerById(Integer id) {
		try {
			return bannerMicroDao.findById(id);
		} catch (Exception e) {
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public MicroBanner add(MicroBanner microBanner) {
		if(microBanner == null) {
    		return null;
    	}
    	Date createDate = microBanner.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	microBanner.setCreateDate(createDate);
    	microBanner.setModifyDate(createDate);
		return bannerMicroDao.create(microBanner);
	}

	@Override
	public MicroBanner modify(MicroBanner microBanner) {
		if(microBanner == null) {
    		return null;
    	}
    	Date modify = microBanner.getModifyDate();
    	microBanner.setModifyDate(modify != null ? modify : new Date());
		return bannerMicroDao.update(microBanner);
	}
	
	@Override
	public void remove(MicroBanner microBanner) {
		try {
			bannerMicroDao.delete(microBanner);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", microBanner.getId(), e);
			}
		}
	}
	
	@Override
	public List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition, Page page, Order order) {
		return bannerMicroDao.findMicroBannerByCondition(microBannerCondition, page, order);
	}
	
//	@Override
//	public List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition) {
//		return microBannerDao.findMicroBannerByCondition(microBannerCondition, null, null);
//	}
//
//	@Override
//	public List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition, Page page) {
//		return microBannerDao.findMicroBannerByCondition(microBannerCondition, page, null);
//	}
//
//	@Override
//	public List<MicroBanner> findMicroBannerByCondition(MicroBannerCondition microBannerCondition, Order order) {
//		return microBannerDao.findMicroBannerByCondition(microBannerCondition, null, order);
//	}
	
	@Override
	public Long count() {
		return this.bannerMicroDao.count(null);
	}

	@Override
	public Long count(MicroBannerCondition microBannerCondition) {
		return this.bannerMicroDao.count(microBannerCondition);
	}

}
