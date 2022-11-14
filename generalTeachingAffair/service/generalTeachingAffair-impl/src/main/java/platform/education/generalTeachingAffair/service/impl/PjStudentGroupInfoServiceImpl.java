package platform.education.generalTeachingAffair.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import platform.education.generalTeachingAffair.model.PjStudentGroupInfo;
import platform.education.generalTeachingAffair.vo.PjStudentGroupInfoCondition;
import platform.education.generalTeachingAffair.service.PjStudentGroupInfoService;
import platform.education.generalTeachingAffair.dao.PjStudentGroupInfoDao;
import framework.generic.dao.Order;
import framework.generic.dao.Page;

public class PjStudentGroupInfoServiceImpl implements PjStudentGroupInfoService{

	private Logger log = LoggerFactory.getLogger(getClass());
	
	private PjStudentGroupInfoDao pjStudentGroupInfoDao;

	public void setPjStudentGroupInfoDao(PjStudentGroupInfoDao pjStudentGroupInfoDao) {
		this.pjStudentGroupInfoDao = pjStudentGroupInfoDao;
	}
	
	@Override
	public PjStudentGroupInfo findPjStudentGroupInfoById(Integer id) {
		try {
			return pjStudentGroupInfoDao.findById(id);
		} catch (Exception e) {
			e.printStackTrace();
			log.info("数据库无存在ID为 {} ", id);
		}
		return null;
	}
	
	@Override
	public PjStudentGroupInfo add(PjStudentGroupInfo pjStudentGroupInfo) {
		if(pjStudentGroupInfo == null) {
    		return null;
    	}
    	Date createDate = pjStudentGroupInfo.getCreateDate();
    	if(createDate == null) {
    		createDate = new Date();
    	}
    	pjStudentGroupInfo.setCreateDate(createDate);
    	pjStudentGroupInfo.setModifyDate(createDate);
		return pjStudentGroupInfoDao.create(pjStudentGroupInfo);
	}

	@Override
	public PjStudentGroupInfo modify(PjStudentGroupInfo pjStudentGroupInfo) {
		if(pjStudentGroupInfo == null) {
    		return null;
    	}
    	Date modify = pjStudentGroupInfo.getModifyDate();
    	pjStudentGroupInfo.setModifyDate(modify != null ? modify : new Date());
		return pjStudentGroupInfoDao.update(pjStudentGroupInfo);
	}
	
	@Override
	public void remove(PjStudentGroupInfo pjStudentGroupInfo) {
		try {
			pjStudentGroupInfoDao.delete(pjStudentGroupInfo);
		} catch(Exception e) {
			if(log.isDebugEnabled()) {
				log.debug("删除数据库无存在ID为 {} ,异常为：{}", pjStudentGroupInfo.getId(), e);
			}
		}
	}
	
	@Override
	public List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition, Page page, Order order) {
		return pjStudentGroupInfoDao.findPjStudentGroupInfoByCondition(pjStudentGroupInfoCondition, page, order);
	}
	
	@Override
	public List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition) {
		return pjStudentGroupInfoDao.findPjStudentGroupInfoByCondition(pjStudentGroupInfoCondition, null, null);
	}
	
	@Override
	public List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition, Page page) {
		return pjStudentGroupInfoDao.findPjStudentGroupInfoByCondition(pjStudentGroupInfoCondition, page, null);
	}
	
	@Override
	public List<PjStudentGroupInfo> findPjStudentGroupInfoByCondition(PjStudentGroupInfoCondition pjStudentGroupInfoCondition, Order order) {
		return pjStudentGroupInfoDao.findPjStudentGroupInfoByCondition(pjStudentGroupInfoCondition, null, order);
	}
	
	@Override
	public Long count() {
		return this.pjStudentGroupInfoDao.count(null);
	}

	@Override
	public Long count(PjStudentGroupInfoCondition pjStudentGroupInfoCondition) {
		return this.pjStudentGroupInfoDao.count(pjStudentGroupInfoCondition);
	}

	@Override
	public void deleteByTeamId(Integer teamId) {
		this.pjStudentGroupInfoDao.deleteByTeamId(teamId);
	}

}
