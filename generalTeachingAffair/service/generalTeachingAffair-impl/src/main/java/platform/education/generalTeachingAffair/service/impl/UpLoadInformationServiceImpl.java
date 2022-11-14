package platform.education.generalTeachingAffair.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.generic.dao.Order;
import framework.generic.dao.Page;
import platform.education.generalTeachingAffair.dao.UpLoadInformationDao;
import platform.education.generalTeachingAffair.model.UpLoadInformation;
import platform.education.generalTeachingAffair.service.UpLoadInformationService;
import platform.education.generalTeachingAffair.vo.UpLoadInformationCondition;

public class UpLoadInformationServiceImpl implements UpLoadInformationService {

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private UpLoadInformationDao upLoadInformationDao;
	
	@Override
	public UpLoadInformation add(UpLoadInformation upLoadInformation) {
		return upLoadInformationDao.create(upLoadInformation);
	}

	@Override
	public UpLoadInformation findUpLoadInformationById(Integer id) {
		try {
			return upLoadInformationDao.findUpLoadInformationById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}

	@Override
	public UpLoadInformation modify(UpLoadInformation upLoadInformation) {
		return upLoadInformationDao.update(upLoadInformation);
	}

	@Override
	public void remove(UpLoadInformation upLoadInformation) {
		try{
			upLoadInformationDao.delete(upLoadInformation);
		}catch(Exception e){
			e.printStackTrace();
			log.info("删除数据库无存在ID为 {} ");
		}
	}

	@Override
	public List<UpLoadInformation> findUpLoadInformationByCondition(
			UpLoadInformationCondition upLoadInformationCondition, Page page,Order order) {
		return upLoadInformationDao.findUpLoadInformationByCondition(upLoadInformationCondition, page, order);
	}
	
	public UpLoadInformationDao getUpLoadInformationDao() {
		return upLoadInformationDao;
	}

	public void setUpLoadInformationDao(UpLoadInformationDao upLoadInformationDao) {
		this.upLoadInformationDao = upLoadInformationDao;
	}
}
